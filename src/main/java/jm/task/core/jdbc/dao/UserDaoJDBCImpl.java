package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        String query = """
                                   CREATE TABLE IF NOT EXISTS users(
                                   id SERIAL PRIMARY KEY,
                                   name TEXT, 
                                   last_name TEXT,
                                   age int);
                    """ ;

        try (Connection connection = Util.open();
             Statement statement = connection.createStatement()) {
            statement.execute(query);
            System.out.println("Таблица создана");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {

        String query = """
                DROP TABLE IF EXISTS users;
                """;

        try (Connection connection = Util.open();
             Statement statement = connection.createStatement()) {
            statement.execute(query);
            System.out.println("Таблица удалена");

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        String userData = "INSERT INTO users (name, last_name, age) VALUES (?,?,?)";

        try (Connection connection = Util.open();
             PreparedStatement statement = connection.prepareStatement(userData)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);

            statement.executeUpdate();
            System.out.println("User с именем " + name +" добавлен в базу данных");
        } catch (SQLException e) {
            System.out.println("Ошибка хз");
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {

        String query = "DELETE FROM users WHERE id = ?";

        try (Connection connection = Util.open();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, id);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Пользователь успешно удален");
            } else {
                System.out.println("Пользователь с данным ID не найден");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {

        String query = "Select id, name, last_name, age from users";
        ArrayList<User> users = new ArrayList<>();

        try (Connection connection = Util.open();
             PreparedStatement statement = connection
                     .prepareStatement(query);
        ResultSet resultSet = statement.executeQuery()) {


            while(resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при получении пользователей");
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        String query = """
                TRUNCATE TABLE users;
                """;

        try (Connection connection = Util.open();
             Statement statement = connection.createStatement()) {
            statement.execute(query);
            System.out.println("Таблица очищена");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
