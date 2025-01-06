package org.supercat.growstone;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.datasource.DataSourceFactory;

import javax.sql.DataSource;
import java.util.Properties;

public class HikariDataSourceFactory implements DataSourceFactory {
    private Properties properties;

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public DataSource getDataSource() {
        HikariConfig c = new HikariConfig(properties);
        c.addDataSourceProperty("cachePrepStmts", true);

        // connection 마다 유지할 prepared statements cache 크기
        c.addDataSourceProperty("prepStmtCacheSize", 500);

        // 캐싱 대상이 될 prepared statements의 query 길이
        c.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);

        // Database의 Server prepared statements 기능을 사용할지 여부
        c.addDataSourceProperty("useServerPrepStmts", false);

        // 이 값을 false로 하면, 매 query마다 SELECT @@session.tx_read_only 가 실행된다.
        c.addDataSourceProperty("useLocalSessionState", true);

        // ResultSet을 가져올때 Metadata를 서버에서 캐싱한다.
        c.addDataSourceProperty("cacheResultSetMetadata", true);

        // URL 단위로, 매 Connection이 연결될때마다 서버 설정값을 캐싱 해둔다.
        c.addDataSourceProperty("cacheServerConfiguration", true);

        // Autocommit 값이 실제로 바뀌었을때에만 auto commit 설정 쿼리를 전송한다.
        c.addDataSourceProperty("elideSetAutoCommits", true);

        // query 쏠때마다, time 측정하는 기능을 disable 한다.
        c.addDataSourceProperty("maintainTimeStats", false);


        return new HikariDataSource(c);
    }
}
