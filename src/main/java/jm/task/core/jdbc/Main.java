package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;


import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Петя","Пупкин", (byte) 5);
        userService.saveUser("Вася","Пупкин", (byte) 5);
        userService.saveUser("Витя","Пупкин", (byte) 5);
        userService.saveUser("Дима","Пупкин", (byte) 5);
        userService.getAllUsers().forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
