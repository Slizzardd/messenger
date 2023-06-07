package ua.com.alevel.messenger_nure.persistence.dao.impl;

import ua.com.alevel.messenger_nure.config.DatabaseHandler;
import ua.com.alevel.messenger_nure.persistence.dao.UserDao;
import ua.com.alevel.messenger_nure.persistence.entity.user.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class UserDaoImpl implements UserDao {

    private final DatabaseHandler databaseHandler = new DatabaseHandler();
    private final static String CREATE_USER_QUERY = "insert into users values(default,?,?,?,?,?,?,?)";

    private final static String FIND_USER_BY_LOGIN_QUERY = "select * from users where login = ?";

    @Override
    public void create(User entity) throws SQLException, ClassNotFoundException {
        try (PreparedStatement preparedStatement = databaseHandler.getDbConnection().prepareStatement(CREATE_USER_QUERY)) {
            preparedStatement.setTimestamp(1, new Timestamp(entity.getCreated().getTime()));
            preparedStatement.setTimestamp(2, new Timestamp(entity.getUpdated().getTime()));
            preparedStatement.setBoolean(3, entity.getVisible());
            preparedStatement.setString(4, entity.getPhoneNumber());
            preparedStatement.setString(5, entity.getEmail());
            preparedStatement.setString(6, entity.getLogin());
            preparedStatement.setString(7, entity.getPassword());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public User findByLogin(String login) {
        try (PreparedStatement preparedStatement = databaseHandler.getDbConnection().prepareStatement(FIND_USER_BY_LOGIN_QUERY)) {
            preparedStatement.setString(1, login);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    return initUserByResultSer(resultSet);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public boolean existById(Long id) {
        return false;
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    private User initUserByResultSer(ResultSet resultSet){
        try{
            Long id = resultSet.getLong("id");
            Timestamp created = resultSet.getTimestamp("created");
            Timestamp updated = resultSet.getTimestamp("updated");
            Boolean visible = resultSet.getBoolean("visible");
            String login = resultSet.getString("login");
            String email = resultSet.getString("email");
            String phoneNumber = resultSet.getString("phone_number");
            String password = resultSet.getNString("password");

            User user = new User();
            user.setId(id);
            user.setCreated(created);
            user.setUpdated(updated);
            user.setLogin(login);
            user.setVisible(visible);
            user.setEmail(email);
            user.setPhoneNumber(phoneNumber);
            user.setPassword(password);
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
