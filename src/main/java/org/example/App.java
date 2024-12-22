package org.example;

import org.example.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class App {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(SpringApplication.class);
        Communication communication = context.getBean("communication", Communication.class);

        List<User> allUsers = communication.getAllUsers();
        System.out.println(allUsers);
        User user = new User(3L, "James", "Brown", (byte) 25);
        communication.saveUsers(user);
        user.setName("Thomas");
        user.setLastName("Shelby");
        communication.updateUsers(user);
        communication.deleteUsers(3L);
    }
}
