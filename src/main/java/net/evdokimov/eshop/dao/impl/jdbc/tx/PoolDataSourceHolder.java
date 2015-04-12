package net.evdokimov.eshop.dao.impl.jdbc.tx;

import com.jolbox.bonecp.BoneCPDataSource;

import java.sql.Connection;
import java.sql.SQLException;


public class PoolDataSourceHolder extends BaseDataSource {
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/eshop_db";

    private final static BoneCPDataSource dataSource;

    static {
        dataSource = new BoneCPDataSource();
        dataSource.setJdbcUrl(JDBC_URL);
        dataSource.setUsername("username");
        dataSource.setPassword("password");
    }

    public PoolDataSourceHolder() {
        System.out.println("PoolDataSource is created");
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
