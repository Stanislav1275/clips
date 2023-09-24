package com.aikoni6.project.Utils;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DadJokeUtils {
    //private final String url = "https://v2.jokeapi.dev/joke/Any";
    private final String url = "https://v2.jokeapi.dev/joke/Any?format=txt";
    @SneakyThrows
    public String getJoke(){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }

}
