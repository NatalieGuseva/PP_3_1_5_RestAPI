package org.example;


import org.example.model.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class Communication {

    private RestTemplate restTemplate = new RestTemplate();

    private HttpHeaders httpHeaders = new HttpHeaders();
    private final String URL = "http://94.198.50.185:7081/api/users";
    private String SESSION_ID;

    public List<User> getAllUsers() {

        ResponseEntity<List<User>> responseEntity =
                restTemplate.exchange(URL, HttpMethod.GET, null,
                        new ParameterizedTypeReference<>() {
                        });

        List<User> allUsers = responseEntity.getBody();
        List<String> strings = responseEntity.getHeaders().get("Set-Cookie");
        SESSION_ID = strings.get(0).substring(0, strings.get(0).indexOf(';'));
        return allUsers;
    }

    public void saveUsers(User user) {
        httpHeaders.set("Cookie", SESSION_ID);
        HttpEntity<User> entity = new HttpEntity<>(user, httpHeaders);
        ResponseEntity<String> exchange = restTemplate.exchange(URL, HttpMethod.POST, entity, String.class);
        System.out.println("exchange.getBody()1 = " + exchange.getBody());
    }

    public void updateUsers(User user) {
        HttpEntity<User> entity = new HttpEntity<>(user, httpHeaders);
        ResponseEntity<String> exchange = restTemplate.exchange(URL, HttpMethod.PUT, entity, String.class);
        System.out.println("exchange.getBody()2 = " + exchange.getBody());
    }

    public void deleteUsers(Long id) {
        HttpEntity<User> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> exchange = restTemplate.exchange(URL + "/" + id, HttpMethod.DELETE, entity, String.class);
        System.out.println("exchange.getBody()3 = " + exchange.getBody());
    }
}

