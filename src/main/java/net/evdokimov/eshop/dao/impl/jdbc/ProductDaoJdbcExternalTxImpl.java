package net.evdokimov.eshop.dao.impl.jdbc;

import net.evdokimov.eshop.dao.ProductDao;
import net.evdokimov.eshop.dao.exception.DaoSystemException;
import net.evdokimov.eshop.dao.exception.NoSuchEntityException;
import net.evdokimov.eshop.entity.Product;


import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProductDaoJdbcExternalTxImpl implements ProductDao {

    private static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
    /*private static final String DRIVER_CLASS_NAME = "org.hsqldb.jdbc.JDBCDriver";*/

    private static final String SELECT_ALL_SQL = "SELECT id, name, type_id FROM products";
    private static final String SELECT_BY_ID_SQL = "SELECT id, name, type_id FROM products WHERE id=";
    private static final String INSERT = "INSERT INTO products (name, type_id) VALUES (?, ?);";
    private static final String REMOVE = "DELETE FROM products WHERE id=";
    private static final String SELECT_BY_TYPE_ID_SQL = "SELECT * FROM products WHERE type_id=?";

    static {
        JdbcUtils.initDriver(DRIVER_CLASS_NAME);
    }

    private DataSource dataSource;//для того чтобы получить именно ту connection

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Product selectById(int id) throws DaoSystemException, NoSuchEntityException {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Connection conn = dataSource.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(SELECT_BY_ID_SQL + id);
            if(!rs.next()) {
                throw new NoSuchEntityException("No Product for id = " + id);
            }
            return new Product(rs.getInt("id"), rs.getString("name"), rs.getInt("type_id"));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoSystemException("Some exception", e);
        } finally {
            JdbcUtils.closeQuietly(rs, stmt);
        }
    }

    @Override
    public List<Product> selectByTypeId(int type_id) throws DaoSystemException, NoSuchEntityException {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        List<Product> result = new ArrayList<>();
        try {
            Connection conn = dataSource.getConnection();
            pStmt = conn.prepareStatement(SELECT_BY_TYPE_ID_SQL);
            pStmt.setInt(1, type_id);
            rs = pStmt.executeQuery();
            if(!rs.next()) {
                throw new NoSuchEntityException("No products with this type");
            }
            do  {
                result.add(new Product(rs.getInt("id"), rs.getString("name"), type_id));
            } while (rs.next());
            return result;
        } catch (SQLException e) {
            throw new DaoSystemException("Some exception", e);
        }
    }

    @Override
    public List<Product> selectAll() throws DaoSystemException, NoSuchEntityException {
        List<Product> result = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Connection conn = dataSource.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(SELECT_ALL_SQL);
            if(!rs.next()) {
                throw new NoSuchEntityException("No Products");
            }
            do {
                result.add(new Product(rs.getInt("id"), rs.getString("name"), rs.getInt("type_id")));
            } while (rs.next());
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoSystemException("Some exception", e);
        } finally {
            JdbcUtils.closeQuietly(rs, stmt);
        }
    }

    @Override
    public void insert(Product product) throws DaoSystemException {
        PreparedStatement pStmt = null;
        try {
            Connection conn = dataSource.getConnection();
            pStmt = conn.prepareStatement(INSERT);
            pStmt.setString(1, product.getName());
            pStmt.setInt(2, product.getTypeId());
            pStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoSystemException("Some exception", e);
        } finally {
            JdbcUtils.closeQuietly(pStmt);
        }
    }

    /*public int[] insetBatch(List<Product> products) throws DaoSystemException {
        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement prepareStatement = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            for (Product product : products) {
                prepareStatement.setString(1, product.getName());
                prepareStatement.addBatch();
            }
            prepareStatement.executeBatch();

            int [] keys = new int [products.size()];
            ResultSet rs = prepareStatement.getGeneratedKeys();
            for(int i = 0; rs.next(); i++) {
                keys[i] = rs.getInt(1);
            }
            return keys;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoSystemException(e.getMessage());
        }
    }*/

    @Override
    public void delete(int id) throws DaoSystemException, NoSuchEntityException {
        Statement stmt = null;
        try {
            Connection conn = dataSource.getConnection();
            stmt = conn.createStatement();
            int i = stmt.executeUpdate(REMOVE + id);
            if(i == 0) {
                throw new NoSuchEntityException("No Product for id = " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoSystemException(e.getMessage());
        } finally {
            JdbcUtils.closeQuietly(stmt);
        }
    }
}