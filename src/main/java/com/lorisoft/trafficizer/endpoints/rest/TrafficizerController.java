package com.lorisoft.trafficizer.endpoints.rest;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class TrafficizerController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

}