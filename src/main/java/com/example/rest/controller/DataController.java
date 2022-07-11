package com.example.rest.controller;

import com.example.rest.entity.DataClass;
import com.example.rest.exception.NoSuchDataException;
import com.example.rest.service.DataService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/get-data/v1")
public class DataController {

    private final DataService dataService;

    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @RequestMapping("/")
    public HttpEntity<DataClass> getDataById(
            @RequestParam(value = "id") Long id) {

        return new ResponseEntity<>(
                dataService.getDataById(id).orElseThrow(NoSuchDataException::new),
                HttpStatus.OK
        );
    }

    @RequestMapping("/all")
    public HttpEntity<List<DataClass>> getAllData() {

        return new ResponseEntity<>(dataService.getAllData(), HttpStatus.OK);
    }
}
