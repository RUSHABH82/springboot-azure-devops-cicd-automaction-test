package com.example.springbootazuredevopscicdautomactiontest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;
import java.util.Properties;

@SpringBootTest
class SpringbootAzureDevopsCicdAutomactionTestApplicationTests {


    private static final String BASE_URL = "https://springboot123-g3gxhxf7cwdwfpc6.canadacentral-01.azurewebsites.net/";

    @Test
    void contextLoads() {
    }


    @Test
    void testRoot() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(URI.create(BASE_URL), String.class);
        Assertions.assertTrue(response.hasBody());
        Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
        Assertions.assertEquals("Welcome to my App\nThanks for visiting me!\nCD test!", response.getBody());
    }

    @Test
    void testPing() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(URI.create(BASE_URL + "/ping"), String.class);
        Assertions.assertTrue(response.hasBody());
        Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
        Assertions.assertEquals("Pong", response.getBody());
    }

    @Test
    void testSystemEnv() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.getForEntity(URI.create(BASE_URL + "/info/env"), Map.class);
        Assertions.assertTrue(response.hasBody());
        Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
        Assertions.assertTrue(response.getBody().containsKey("PATH"));
    }

    @Test
    void testSystemProperties() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Properties> response = restTemplate.getForEntity(URI.create(BASE_URL + "/info/property"), Properties.class);
        Assertions.assertTrue(response.hasBody());
        Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
        Assertions.assertTrue(response.getBody().containsKey("java.version"));
    }


}
