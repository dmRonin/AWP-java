package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();

//        userService.createUsersTable();
//        userService.saveUser("Петя","Пупкин", (byte) 5);
//        userService.saveUser("Витя","Пупкин", (byte) 5);
//        userService.saveUser("Дима","Пупкин", (byte) 5);
//        userService.removeUserById(1);
//        userService.removeUserById(6);
//        userService.removeUserById(10);
//        userService.getAllUsers().forEach(System.out::println);
//        userService.cleanUsersTable();
//        userService.dropUsersTable();
    }
}
