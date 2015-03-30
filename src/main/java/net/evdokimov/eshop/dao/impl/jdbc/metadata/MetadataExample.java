package net.evdokimov.eshop.dao.impl.jdbc.metadata;

import java.sql.*;

/**
 * Created by 1 on 20.03.2015.
 */
public class MetadataExample {
    public static void main(String[] args) throws SQLException {
        Connection conn = getConnection();

        DatabaseMetaData metaData = conn.getMetaData();
        dumbDBGeneralInfo(metaData);
        dump("Schemas: ", metaData.getTableTypes());

    }

    private static void dumbDBGeneralInfo(DatabaseMetaData metaData) throws SQLException {
        System.out.println("DB info:");
        System.out.println("DB product name = " + metaData.getDatabaseProductName());
        System.out.println();

    }

    private static void dump(String string, ResultSet rs) throws SQLException {
        StringBuilder builder = new StringBuilder(string);
        while (rs.next()) {
            builder.append(rs.getString(1) + "\n");
        }
        System.out.println(builder);
    }



    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/eshop_db?user=username&password=password");
    }
}
