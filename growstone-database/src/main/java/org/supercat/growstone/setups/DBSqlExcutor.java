package org.supercat.growstone.setups;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.supercat.growstone.SLog;


import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;

public class DBSqlExcutor {
    public static SqlSessionFactory sqlSessionFactory = null;
    private static Function<Runnable, CompletableFuture<Void>> asyncExecutor;
    private DBSqlExcutor(SqlSessionFactory sqlSessionFactory, Function<Runnable, CompletableFuture<Void>> asyncExecutor) {
        this.sqlSessionFactory = sqlSessionFactory;
        this.asyncExecutor = asyncExecutor;
    }

    public static DBSqlExcutor of(SqlSessionFactory sqlSessionFactory, Function<Runnable, CompletableFuture<Void>> asyncExecutor) {
        return new DBSqlExcutor(sqlSessionFactory, asyncExecutor);
    }

    public <T extends Object> T query(Function<SqlSession, T> e) {
        SqlSession session = sqlSessionFactory.openSession(true);

        return execute(session, e, false);
    }

    public CompletableFuture<Void> queryAsync(Consumer<SqlSession> e) {
        return asyncExecutor.apply(() -> query(db -> {
            e.accept(db);
            return null;
        }));
    }


    private <T extends Object> T execute(SqlSession session, Function<SqlSession, T> withSqlSession, boolean ignoreError) {
        try {
            return withSqlSession.apply(session);
        } catch (Exception e) {
            SLog.logException(e);
            throw e;
        } finally {
            if (Objects.nonNull(session)) {
                session.close();
            }
        }
    }

    public void cleanUp() {
        if (Objects.isNull(sqlSessionFactory)) {
            return;
        }

        var configuration = sqlSessionFactory.getConfiguration();
        if (Objects.isNull(configuration)) {
            return;
        }

        var environment = configuration.getEnvironment();
        if (Objects.isNull(environment)) {
            return;
        }

        var dataSource = environment.getDataSource();
        if (Objects.isNull(dataSource)) {
            return;
        }

        if (dataSource instanceof AutoCloseable) {
            try {
                ((AutoCloseable) dataSource).close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
