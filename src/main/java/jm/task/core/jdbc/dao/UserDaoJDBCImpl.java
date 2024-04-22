package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }



    public void createUsersTable() {
        try {
            Statement statement = getConnection().createStatement();
            String SQL = "CREATE TABLE IF NOT EXISTS userss (id INT, name VARCHAR, lastname VARCHAR, age smallint)";
            statement.execute(SQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try {
            Statement statement = getConnection().createStatement();
            String SQL = "DROP TABLE IF EXISTS userss";
            statement.execute(SQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            PreparedStatement preparedStatement = getConnection()
                    .prepareStatement("INSERT INTO userss (name, lastName, age) VALUES (?,?,?)");

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try {
            Statement statement = getConnection().createStatement();
            String SQL = "DELETE FROM userss WHERE id =" + id;
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> person = new ArrayList<>();
        try {
            Statement statement = getConnection().createStatement();
            String SQL = "SELECT * FROM userss";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                User user = new User();

                user.setId((long) resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge((byte) resultSet.getObject("age", Byte.class));

                person.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return person;
    }

    public void cleanUsersTable() {
        try {
            Statement statement = getConnection().createStatement();
            String SQL = "DELETE FROM userss";
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}