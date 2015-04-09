/*
package net.evdokimov.eshop.dao.impl.jdbc;

import net.evdokimov.eshop.dao.ProductDao;
import net.evdokimov.eshop.dao.exception.DaoSystemException;
import net.evdokimov.eshop.dao.exception.NoSuchEntityException;
import net.evdokimov.eshop.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProductDaoJdbcImpl implements ProductDao {

    public static final String JDBC_URL =
            "jdbc:mysql://127.0.0.1:3306/eshop_db?user=username&password=password";

    public ProductDaoJdbcImpl() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(JDBC_URL)) {

            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE IF EXISTS products;");
            statement.execute("CREATE TABLE products (id INT , name VARCHAR (64));");
            statement.execute("ALTER TABLE eshop_db.products \n" +
                    "CHANGE COLUMN id id INT(11) NOT NULL AUTO_INCREMENT ,\n" +
                    "ADD PRIMARY KEY (id);");

            statement.execute("INSERT INTO products (id, name) VALUES (1, 'Milk')");
            statement.execute("INSERT  INTO  products (id, name) VALUES (2, 'Tomato')");
            statement.execute("INSERT  INTO  products (id, name) VALUES (3, 'Eggs')");
        }
    }

    @Override
    public Product selectById(int id) throws DaoSystemException, NoSuchEntityException {
        System.out.println("selectById - called!");
        try (Connection connection = DriverManager.getConnection(JDBC_URL)) {

            PreparedStatement statement = connection.prepareStatement("SELECT name FROM products WHERE id=?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            rs.next();
            String name = rs.getString("name");

            return new Product(id, name);
        } catch (SQLException e) {
            System.out.println(e);
            throw new NoSuchEntityException("Nо Product for id == '" + id + "'");
        }
    }

    @Override
    public List<Product> selectAll() throws DaoSystemException {
        List<Product> result = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL)) {

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT id, name FROM products");
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                result.add(new Product(id, name));
            }
            return result;
        } catch (SQLException e) {
            throw new DaoSystemException(":", e);
        }
    }

    @Override
    public void insert(Product product) throws DaoSystemException {

    }

    @Override
    public void delete(int id) throws DaoSystemException {

    }

}

*/
/*class Test {
    public static void main(String[] args) throws DaoSystemException, NoSuchEntityException, SQLException, ClassNotFoundException {
        ProductDaoJdbcImpl dao = new ProductDaoJdbcImpl();
        Product p = dao.selectById(2);
        System.out.println(p.getId()+" "+ p.getName());
        List products = dao.selectAll();
        System.out.println(products);
    }
}*//*


*/
