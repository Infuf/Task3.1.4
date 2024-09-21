package com.example.RestTemplateTask314.controller;

import com.example.RestTemplateTask314.model.UserDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

//5ebfebe7cb975dfcf9
@RestController("/user")
public class SimpleController {
    public SimpleController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private final RestTemplate restTemplate;

    @GetMapping
    public void makeRequests() {
        String url = "http://94.198.50.185:7081/api/users";

        ResponseEntity<UserDTO[]> responseEntity = restTemplate.getForEntity(url, UserDTO[].class);
        HttpHeaders headers = responseEntity.getHeaders();

        List<String> cookies = headers.get(HttpHeaders.SET_COOKIE);
        String cookie = cookies.get(0);
        System.out.println(cookie);

        UserDTO userDTO = new UserDTO(3l, "James", "Brown", 44);

        HttpHeaders header = new HttpHeaders();
        header.add("Cookie", cookie);

        HttpEntity<UserDTO> entity = new HttpEntity<>(userDTO, header);
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                String.class
        );

        userDTO.setName("Thomas");
        userDTO.setLastName("Shelby");

        HttpEntity<UserDTO> entity2 = new HttpEntity<>(userDTO, header);
        ResponseEntity<String> response2 = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                entity2,
                String.class
        );

        HttpEntity<UserDTO> entity3 = new HttpEntity<>(header);
        ResponseEntity<String> response3 = restTemplate.exchange(
                url + "/3",
                HttpMethod.DELETE,
                entity3,
                String.class
        );

        String superSecretCode = response.getBody();
        superSecretCode += response2.getBody();
        superSecretCode += response3.getBody();
        System.out.println(superSecretCode);

    }
}
