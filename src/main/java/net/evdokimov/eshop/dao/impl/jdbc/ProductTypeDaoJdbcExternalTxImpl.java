package net.evdokimov.eshop.dao.impl.jdbc;

import net.evdokimov.eshop.dao.ProductTypeDao;
import net.evdokimov.eshop.dao.exception.DaoSystemException;
import net.evdokimov.eshop.dao.exception.NoSuchEntityException;
import net.evdokimov.eshop.entity.ProductType;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by 1 on 09.04.2015.
 */
public class ProductTypeDaoJdbcExternalTxImpl implements ProductTypeDao {

    private static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";

    static {
        JdbcUtils.initDriver(DRIVER_CLASS_NAME);
    }

    private DataSource dataSource;//для того чтобы получить именно то connection

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private static final String SELECT_ID_BY_TYPE_SQL = "SELECT id FROM product_type WHERE type='";

    @Override
    public int getProductTypeId(ProductType productType) throws DaoSystemException, NoSuchEntityException {
        try {
            Connection connection = dataSource.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(SELECT_ID_BY_TYPE_SQL + productType.getType() + "'");
            if(!rs.next()) {
                throw new NoSuchEntityException("No IdType for name = " + productType.getType());
            }
            return rs.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoSystemException("Some exception");
        }
    }
}
