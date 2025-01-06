package org.supercat.growstone.components.playerComponents;

import com.supercat.growstone.network.messages.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.*;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.models.DMPlayerCurrency;
import org.supercat.growstone.setups.SDB;

public class PlayerCurrencyComponent {
    private static final Logger logger = LoggerFactory.getLogger(PlayerCurrencyComponent.class);

    private WorldPlayer player;
    public DMPlayerCurrency model;
    private final CloseableLock lock = new CloseableLock();

    public PlayerCurrencyComponent(WorldPlayer player) {
        this.player = player;
    }

    public void initialize() {
        model = SDB.dbContext.currency.getByPlayerId(player.getId());
        if (model == null) {
            model = DMPlayerCurrency.of(player.getId(), 0, 0, 0, 0, 0);
        }
    }

    public TCurrencies getTCurrencies() {
        return TBuilderOf.buildOf(model);
    }


    public int addCurrency(ZResource.Type type, long amount) {
        switch (type) {
            case GOLD:
                return addGold(amount);
            case FREE_RUBY:
            case PAY_RUBY:
                return addRuby(type, amount);
            case FREE_DIAMOND:
            case PAY_DIAMOND:
                return addDiamond(type, amount);
            default:
                return StatusCode.INVALID_RESOURCE;
        }
    }

    public int useCurrency(ZResource.Type type, long amount) {
        if(amount <= 0) {
            return StatusCode.SUCCESS;
        }

        switch (type) {
            case GOLD:
                return useGold(amount);
            case FREE_RUBY:
            case PAY_RUBY:
                return useRuby(amount);
            case FREE_DIAMOND:
            case PAY_DIAMOND:
                return useDiamond(amount);
            default:
                return StatusCode.INVALID_RESOURCE;
        }
    }
    public long getCurrency(ZResource.Type type) {
        switch (type) {
            case GOLD:
                return getGold();
            case FREE_RUBY:
            case PAY_RUBY:
                return getRuby();
            case FREE_DIAMOND:
            case PAY_DIAMOND:
                return getDiamond();
            default:
                return 0;
        }
    }
    public long getGold() {
        return model.gold;
    }

    public long getRuby() {
        return model.free_ruby + model.paid_ruby;
    }

    public long getDiamond() {
        return model.free_diamond + model.paid_diamond;
    }

    public int addGold(long amount) {
        try (var ignored = lock.begin()) {
            model.gold += amount;

            SDB.dbContext.currency.save(model);

            return StatusCode.SUCCESS;
        } catch (Exception e) {
            SLog.logException(e);
        } finally {
            logger.info("model saved - playerId({}) gold({})", player.getId(), getGold());
        }

        currencyNotify();

        return StatusCode.FAIL;
    }

    public int addRuby(ZResource.Type type, long amount) {
        try (var ignored = lock.begin()) {
            if (type == ZResource.Type.FREE_RUBY) {
                model.free_ruby += amount;
            } else if (type == ZResource.Type.PAY_RUBY) {
                model.paid_ruby += amount;
            } else {
                return StatusCode.INVALID_RESOURCE;
            }

            SDB.dbContext.currency.save(model);

            currencyNotify();

            return StatusCode.SUCCESS;
        } catch (Exception e) {
            SLog.logException(e);
        } finally {
            logger.info("model saved - playerId({}) freeRuby({}) paidRuby({})", player.getId(), model.free_ruby, model.paid_ruby);
        }

        return StatusCode.FAIL;
    }

    public int addDiamond(ZResource.Type type, long amount) {
        try (var ignored = lock.begin()) {
            if (type == ZResource.Type.FREE_DIAMOND) {
                model.free_diamond += amount;
            } else if (type == ZResource.Type.PAY_DIAMOND) {
                model.paid_diamond += amount;
            } else {
                return StatusCode.INVALID_RESOURCE;
            }

            SDB.dbContext.currency.save(model);

            currencyNotify();

            return StatusCode.SUCCESS;
        } catch (Exception e) {
            SLog.logException(e);
        } finally {
            logger.info("model saved - playerId({}) freeDiamond({}) paidDiamond({})", player.getId(), model.free_diamond, model.paid_diamond);
        }

        return StatusCode.FAIL;

    }

    public int useGold(long amount) {
        try (var ignored = lock.begin()) {
            if (amount > getGold()) {
                return StatusCode.NOT_ENOUGH_CURRENCY;
            }

            model.gold -= amount;

            SDB.dbContext.currency.save(model);

            currencyNotify();

            return StatusCode.SUCCESS;
        } catch (Exception e) {
            SLog.logException(e);
        } finally {
            logger.info("model saved - playerId({}) useAmount({}) gold({})", player.getId(), amount, model.gold);
        }

        return StatusCode.FAIL;
    }

    public int useRuby(long amount) {
        try (var ignored = lock.begin()) {
            if (amount > getRuby()) {
                return StatusCode.NOT_ENOUGH_CURRENCY;
            }

            // 먼저 paid_ruby에서 소모
            long paidToUse = Math.min(model.paid_ruby, amount);
            model.paid_ruby -= paidToUse;

            // 남은 양을 free_ruby에서 소모
            long remainingAmount = amount - paidToUse;
            model.free_ruby = Math.max(0, model.free_ruby - remainingAmount);

            SDB.dbContext.currency.save(model);

            currencyNotify();

            return StatusCode.SUCCESS;
        } catch (Exception e) {
            SLog.logException(e);
        } finally {
            logger.info("model saved - playerId({}) useAmount({}) freeRuby({}) paidRuby({})", player.getId(), amount, model.free_ruby, model.paid_ruby);
        }

        return StatusCode.FAIL;
    }

    public int useDiamond(long amount) {
        try (var ignored = lock.begin()) {
            if (amount > getDiamond()) {
                return StatusCode.NOT_ENOUGH_CURRENCY;
            }

            model.paid_diamond = Math.max(0, model.paid_diamond - amount);

            model.free_diamond = Math.max(0, model.free_diamond - (amount - model.paid_diamond));

            SDB.dbContext.currency.save(model);

            currencyNotify();

            return StatusCode.SUCCESS;
        } catch (Exception e) {
            SLog.logException(e);
        } finally {
            logger.info("model saved - playerId({}) useAmount({}) freeDiamond({}) paidDiamond({})", player.getId(), amount, model.free_diamond, model.paid_diamond);
        }

        return StatusCode.FAIL;
    }

    private void currencyNotify() {
        player.sendPacket(0, ZCurrencyNotify.newBuilder()
                .setCurrencies(TBuilderOf.buildOf(model)));
    }
}
