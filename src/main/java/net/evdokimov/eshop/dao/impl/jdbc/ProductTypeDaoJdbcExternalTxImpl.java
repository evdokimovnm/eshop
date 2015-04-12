package net.evdokimov.eshop.dao.impl.jdbc;

import net.evdokimov.eshop.dao.ProductTypeDao;
import net.evdokimov.eshop.dao.exception.DaoSystemException;
import net.evdokimov.eshop.dao.exception.NoSuchEntityException;
import net.evdokimov.eshop.entity.ProductType;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProductTypeDaoJdbcExternalTxImpl implements ProductTypeDao {

    private static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";

    static {
        JdbcUtils.initDriver(DRIVER_CLASS_NAME);
    }

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private static final String SELECT_ID_BY_TYPE_SQL = "SELECT id FROM product_type WHERE type=?";
    private static final String SELECT_PRODUCT_TYPE_LIST_SQL = "SELECT * FROM product_type";

    @Override
    public int getProductTypeId(ProductType productType) throws DaoSystemException, NoSuchEntityException {
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        try {
            Connection connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ID_BY_TYPE_SQL);
            preparedStatement.setString(1, productType.getType());
            rs = preparedStatement.executeQuery();
            if(!rs.next()) {
                throw new NoSuchEntityException("No IdType for name = " + productType.getType());
            }
            return rs.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoSystemException("Some exception");
        } finally {
            JdbcUtils.closeQuietly(rs, preparedStatement);
        }
    }

    @Override
    public List<ProductType> getProductTypeAll() throws DaoSystemException {
        Statement st = null;
        ResultSet rs = null;
        List<ProductType> result = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(SELECT_PRODUCT_TYPE_LIST_SQL);
            while (rs.next()) {
                result.add(new ProductType(rs.getInt("id"), rs.getString("type")));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoSystemException("Some exception");
        } finally {
            JdbcUtils.closeQuietly(rs, st);
        }
    }
}
