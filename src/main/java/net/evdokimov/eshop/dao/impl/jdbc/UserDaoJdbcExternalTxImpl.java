package net.evdokimov.eshop.dao.impl.jdbc;

import com.sun.org.apache.xpath.internal.SourceTree;
import net.evdokimov.eshop.dao.UserDao;
import net.evdokimov.eshop.dao.exception.DaoBusinessException;
import net.evdokimov.eshop.dao.exception.DaoSystemException;
import net.evdokimov.eshop.dao.exception.NoSuchEntityException;
import net.evdokimov.eshop.entity.Product;
import net.evdokimov.eshop.entity.User;

import javax.sql.DataSource;
import java.sql.*;


public class UserDaoJdbcExternalTxImpl implements UserDao {

    private static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";

    private static final String INSERT_SQL = "INSERT INTO users (login, password) VALUES (?, ?);";
    private static final String SELECT_BY_ID_AND_PASSWORD_SQL = "SELECT id, login, password FROM users WHERE login=? AND password=?;";


    static {
        JdbcUtils.initDriver(DRIVER_CLASS_NAME);
    }

    private DataSource dataSource;//для того чтобы получить именно то connection

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }



    @Override
    public User selectByLoginAndPassword(String login, String password) throws DaoSystemException, NoSuchEntityException {
        PreparedStatement prepareStatement = null;
        ResultSet rs =null;
        try {
            Connection conn = dataSource.getConnection();
            prepareStatement = conn.prepareStatement(SELECT_BY_ID_AND_PASSWORD_SQL);
            prepareStatement.setString(1, login);
            prepareStatement.setString(2, password);
            rs = prepareStatement.executeQuery();
            if (!rs.next()) {
                throw new NoSuchEntityException("No users with login = " + login);
            } else {
                do{
                    return new User(rs.getInt("id"), rs.getString("login"), rs.getString("password"));
                } while (rs.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoSystemException("Some exception: ", e);
        } finally {
            JdbcUtils.closeQuietly(rs, prepareStatement);
        }


    }

    @Override
    public int insert(User user) throws DaoSystemException, DaoBusinessException {
        PreparedStatement prepareStatement = null;
        ResultSet rs = null;
        try {
            Connection conn = dataSource.getConnection();
            prepareStatement = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
            prepareStatement.setString(1, user.getLogin());
            prepareStatement.setString(2, user.getPassword());
            try {
                prepareStatement.executeUpdate();
            } catch (SQLException e) {
                throw new DaoBusinessException("That login is already exist", e);
            }
            rs = prepareStatement.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoSystemException("Some exception: ", e);
        } finally {
            JdbcUtils.closeQuietly(rs, prepareStatement);
        }
    }

}

/*class Test {
    private static final String INSERT_SQL = "INSERT INTO users (login, password) VALUES (?, ?);";
    private static final String SELECT_BY_ID_AND_PASSWORD_SQL = "SELECT id, login, password FROM users WHERE login=? AND password=?;";

    public static void main(String[] args) throws SQLException, DaoSystemException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/eshop_db?user=username&password=password");
        PreparedStatement prepareStatement = null;
        ResultSet rs =null;
        String login = "nikita";
        String password = "0523";
        User user = new User(4, "fgsdg", "sgsdg");
        try {
            prepareStatement = conn.prepareStatement(INSERT_SQL);
            prepareStatement.setString(1, user.getLogin());
            prepareStatement.setString(2, user.getPassword());
            rs = prepareStatement.executeQuery();
            System.out.println(rs.getInt("id"));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoSystemException("Some exception: ", e);
        } finally {
            JdbcUtils.closeQuietly(rs, prepareStatement);
        }
    }
}*/
