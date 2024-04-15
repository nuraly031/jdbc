package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Nuraly","One",(byte) 39);
        userService.saveUser("Dasha","Four",(byte) 21);
        userService.saveUser("Nikita","Two",(byte) 21);
        userService.saveUser("Oganess","Three", (byte) 23);
        userService.saveUser("Jackie","Chan", (byte) 23);
        userService.getAllUsers();
        userService.removeUserById(5);
        System.out.println("-------------------------");
        userService.getAllUsers();
        System.out.println("-------------------------");
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}