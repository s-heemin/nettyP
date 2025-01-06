package org.supercat.growstone.setups;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringReader;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class DBSqlExecutorBuilder {
    private final Logger logger = LoggerFactory.getLogger(DBSqlExecutorBuilder.class);

    private String host = StringUtils.EMPTY;
    private String userName = StringUtils.EMPTY;
    private String password = StringUtils.EMPTY;

    private Function<Runnable, CompletableFuture<Void>> asyncExecutor;

    public static DBSqlExecutorBuilder newBuilder() {
        return new DBSqlExecutorBuilder();
    }

    public DBSqlExecutorBuilder withHost(String host) {
        this.host = host;
        return this;
    }

    public DBSqlExecutorBuilder withUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public DBSqlExecutorBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public DBSqlExecutorBuilder withAsyncExecutor(Function<Runnable, CompletableFuture<Void>> executor) {
        this.asyncExecutor = executor;
        return this;
    }

    private SqlSessionFactory createFactory() {
        Objects.requireNonNull(asyncExecutor);

        var stringReader = new StringReader(MybatisConnectString.fetchString(host, userName, password));
        var factory = new SqlSessionFactoryBuilder().build(stringReader);
        factory.getConfiguration().addMappers(
                "org.supercat.growstone.mappers"
        );
        factory.openSession(true).close();

        return factory;
    }

    public DBSqlExcutor executeBuild() {
        var factory = createFactory();
        return DBSqlExcutor.of(factory, asyncExecutor);
    }

}
