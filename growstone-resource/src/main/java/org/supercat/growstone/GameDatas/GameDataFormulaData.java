package org.supercat.growstone.GameDatas;

import com.google.common.collect.ImmutableMap;
import com.supercat.growstone.network.messages.ZStat;
import org.jdom2.Element;
import org.supercat.growstone.XMLHelper;

import java.util.HashMap;

public class GameDataFormulaData {
    public ImmutableMap<ZStat.Type, Double> formulaDatas;
    public final double defaultAttack;
    public final double defaultDefense;
    public final double defaultHp;
    public final double defaultAttackSpeed;
    public final double defaultMoveSpeed;

    public static GameDataFormulaData ofPath(String absolutePath) {
        var doc = XMLHelper.load(absolutePath);
        return new GameDataFormulaData(doc.getRootElement());
    }

    private GameDataFormulaData(Element e) {
        var tempMap = new HashMap<ZStat.Type, Double>();
        // 유저 캐릭터 생성시 기본 스탯
        defaultAttack = XMLHelper.getChildDouble(e, "BASE_ATTACK",0);
        defaultDefense = XMLHelper.getChildDouble(e, "BASE_DEFENSE",0);
        defaultHp = XMLHelper.getChildDouble(e, "BASE_HP",0);
        defaultAttackSpeed = XMLHelper.getChildDouble(e, "BASE_ATTACK_SPEED",0);
        defaultMoveSpeed = XMLHelper.getChildDouble(e, "BASE_MOVE_SPEED",0);

        var temp = e.getChildren("CombatPowerByStat");
        for (var ele : temp) {
            var stat = XMLHelper.getAttributeEnum(ele, "Stat", ZStat.Type.NONE);
            var value = XMLHelper.getAttributeDouble(ele, "FactorValue", 0.0);

            tempMap.put(stat, value);
        }
        this.formulaDatas = ImmutableMap.copyOf(tempMap);
    }
}
