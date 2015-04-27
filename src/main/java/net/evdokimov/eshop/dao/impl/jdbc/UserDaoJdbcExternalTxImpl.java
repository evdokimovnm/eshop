package net.evdokimov.eshop.dao.impl.jdbc;

import net.evdokimov.eshop.dao.UserDao;
import net.evdokimov.eshop.dao.exception.DaoBusinessException;
import net.evdokimov.eshop.dao.exception.DaoSystemException;
import net.evdokimov.eshop.dao.exception.NoSuchEntityException;
import net.evdokimov.eshop.entity.User;

import javax.sql.DataSource;
import java.sql.*;


public class UserDaoJdbcExternalTxImpl implements UserDao {

    private static final String INSERT_SQL = "INSERT INTO users (login, password, email, role) VALUES (?, ?, ?, ?);";
    private static final String SELECT_BY_ID_AND_PASSWORD_SQL = "SELECT id, login, password, email, role FROM users WHERE login=? AND password=?;";


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
            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("login"), rs.getString("password"), rs.getString("email"), rs.getString("role"));
            } else {
                throw new NoSuchEntityException("No users with login = " + login);
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
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            Connection conn = dataSource.getConnection();
            pStmt = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
            pStmt.setString(1, user.getLogin());
            pStmt.setString(2, user.getPassword());
            pStmt.setString(3, user.getEmail());
            pStmt.setString(4, user.getRole());
            try {
                pStmt.executeUpdate();
            } catch (SQLException e) {
                throw new DaoBusinessException("That login or email is already exist", e);
            }
            rs = pStmt.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoSystemException("Some exception: ", e);
        } finally {
            JdbcUtils.closeQuietly(rs, pStmt);
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
