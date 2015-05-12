package net.evdokimov.eshop.dao.impl.jdbc;


import java.sql.*;


public final class JdbcUtils {
    private static boolean initialized;

    private JdbcUtils() {
    }

    public static synchronized void initDriver(String driverClass) {
        if(!initialized) {
            try {
                Class.forName(driverClass);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException("Can't initialized driver: '" + driverClass + "'");
            }
            initialized = true;
        }
    }

    public static void closeQuietly(Connection conn) {
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                /*NOP*/
            }
        }
    }

    public static void closeQuietly(Statement stmt) {
        if(stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                /*NOP*/
            }
        }
    }

    public static void closeQuietly(ResultSet rs) {
        if(rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                /*NOP*/
            }
        }
    }

    public static void closeQuietly(ResultSet rs, Statement stmt) {
        closeQuietly(rs);
        closeQuietly(stmt);
    }


    public static void rollBackQuietly(Connection conn) {
        if(conn != null) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                /*NOP*/
            }
        }
    }

    public static Connection getConnectionQuietly(String JdbcUrl, String s1, String s2) {
        try {
            return DriverManager.getConnection(JdbcUrl, s1, s2);
        } catch (SQLException e) {
            /*NOP*/
            return null;
        }
    }

    public static Connection getConnectionQuietly(String JdbcUrl) {
        try {
            return DriverManager.getConnection(JdbcUrl);
        } catch (SQLException e) {
            /*NOP*/
            return null;
        }
    }

    public static Connection getConnectionQuietly(javax.sql.DataSource source) {
        try {
            return source.getConnection();
        } catch (SQLException e) {
            /*NOP*/
            return null;
        }
    }


    public static void setAutoCommitQuietly(Connection conn, boolean b) {
        try {
            conn.setAutoCommit(b);
        } catch (SQLException e) {
            e.printStackTrace();
            /*NOP*/
        }
    }
}
