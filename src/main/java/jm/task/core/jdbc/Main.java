package jm.task.core.jdbc;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Александр", "Пушкин", (byte) 20);
        System.out.println("User с именем - Александр добавлен в базу данных.");
        userService.saveUser("Максим", "Горький", (byte) 21);
        System.out.println("User с именем - Максим добавлен в базу данных.");
        userService.saveUser("Лев", "Толстой", (byte) 22);
        System.out.println("User с именем - Лев добавлен в базу данных.");
        userService.saveUser("Николай", "Пржевальский", (byte) 23);
        System.out.println("User с именем - Николай добавлен в базу данных.");

        for (User user : userService.getAllUsers()) {
            System.out.println(user.toString());
        }

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
