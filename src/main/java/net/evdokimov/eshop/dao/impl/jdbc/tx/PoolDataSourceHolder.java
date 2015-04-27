package net.evdokimov.eshop.dao.impl.jdbc.tx;

import com.jolbox.bonecp.BoneCPDataSource;
import net.evdokimov.eshop.dao.impl.jdbc.JdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;


public class PoolDataSourceHolder extends BaseDataSource {
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/eshop_db";
    //private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/eshop";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    private static final BoneCPDataSource dataSource;
    private static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
    /*private static final String DRIVER_CLASS_NAME = "org.hsqldb.jdbc.JDBCDriver";*/

    static {
        JdbcUtils.initDriver(DRIVER_CLASS_NAME);
        dataSource = new BoneCPDataSource();
        dataSource.setJdbcUrl(JDBC_URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
    }

    public PoolDataSourceHolder() {
        System.out.println("PoolDataSource is created");
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
