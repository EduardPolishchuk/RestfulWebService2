package com.example.rest.controller;

import com.example.rest.entity.DataClass;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class GreetingsController {

    private static final String TEMPLATE = "Hello, %s!";

    @RequestMapping("/greeting")
    public HttpEntity<DataClass> greeting(
            @RequestParam(value = "name", defaultValue = "World") String name) {

        DataClass greeting = new DataClass(String.format(TEMPLATE, name));

        return new ResponseEntity<>(greeting, HttpStatus.OK);
    }
}
