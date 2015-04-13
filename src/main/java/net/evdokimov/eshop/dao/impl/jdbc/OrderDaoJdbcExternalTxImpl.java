package net.evdokimov.eshop.dao.impl.jdbc;


import net.evdokimov.eshop.dao.OrderDao;
import net.evdokimov.eshop.dao.exception.DaoSystemException;
import net.evdokimov.eshop.entity.Product;
import net.evdokimov.eshop.entity.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

public class OrderDaoJdbcExternalTxImpl implements OrderDao {

    private static final String INSERT = "INSERT INTO orders (user_id, product_id, quantity) VALUES (?, ?, ?);";

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void insertOrder(User user, Map<Product, Integer> order) throws DaoSystemException {
        PreparedStatement ps = null;
        Set<Product> keys = order.keySet();
        try {
            Connection conn = dataSource.getConnection();
            ps = conn.prepareStatement(INSERT);
            for(Product key : keys) {
                ps.setInt(1, user.getId());
                ps.setInt(2, key.getId());
                ps.setInt(3, order.get(key));
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoSystemException("Some exception", e);
        } finally {
            JdbcUtils.closeQuietly(ps);
        }
    }
}
