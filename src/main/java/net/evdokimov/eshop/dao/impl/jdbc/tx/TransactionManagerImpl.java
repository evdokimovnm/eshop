package net.evdokimov.eshop.dao.impl.jdbc.tx;


import net.evdokimov.eshop.dao.exception.DaoSystemException;
import net.evdokimov.eshop.dao.impl.jdbc.JdbcUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.Callable;

public class TransactionManagerImpl extends BaseDataSource implements TransactionManager {
    /*private static final String JDBC_URL =
            "jdbc:mysql://127.0.0.1:3306/eshop_db?user=username&password=password";*/
    /*private static final String JDBC_URL = "jdbc:hsqldb:mem:eshop_db";
    private static final String user = "SA";
    private static final String password = "";*/

    private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<>();//кеш потока

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public <T, E extends Exception> T doInTransaction(UnitOfWork<T, E> unitOfWork) throws E {

        Connection conn = JdbcUtils.getConnectionQuietly(dataSource);
        JdbcUtils.setAutoCommitQuietly(conn, false);
        connectionHolder.set(conn);
        try {
            T result = unitOfWork.doInTx();
            conn.commit();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            JdbcUtils.rollBackQuietly(conn);
            return null;//А если SQLException?
        } finally {
            JdbcUtils.closeQuietly(conn);
            connectionHolder.remove();
        }
    }

    @Override
    public Connection getConnection() {
        return connectionHolder.get();
    }
}
