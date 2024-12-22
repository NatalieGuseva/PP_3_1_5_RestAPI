package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class RestApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(RestApplication.class, args);
        Communication communication = context.getBean("communication", Communication.class);

        communication.getAllUsers();
        communication.saveUser();
        communication.updateUser();
        System.out.println(communication.deleteUser(3L));
        System.out.println(communication.finalApp());

    }


}
