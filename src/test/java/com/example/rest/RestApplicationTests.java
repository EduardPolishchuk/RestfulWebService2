package com.example.rest;

import com.example.rest.entity.DataClass;
import com.example.rest.repository.DataClassRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT,
        classes = {RestApplication.class, RestApplicationTests.H2JpaConfig.class})
class RestApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private DataClassRepository dataClassRepository;

    @BeforeEach
    void setUp() {
        dataClassRepository.deleteAll();
    }

    @Test
    @SneakyThrows
    void getAllE2ETest() {
        // given

        List<DataClass> expected = IntStream.rangeClosed(0, 20)
                .mapToObj(num -> DataClass.builder()
                        .content("New Content, get all" + num)
                        .build())
                .collect(Collectors.toList());

        dataClassRepository.saveAll(expected);

        // When

        List<DataClass> actual = Arrays.asList(
                Objects.requireNonNull(restTemplate.getForEntity("/get-data/v1/all", DataClass[].class).getBody())
        );

        // Then

        assertEquals(expected, actual);
    }

    @Test
    @SneakyThrows
    void getByIdE2ETest() {
        // given

        DataClass expected = dataClassRepository.save(DataClass.builder()
                .content("New Content, get by id")
                .build());
        long newDataId = expected.getId();

        // When

        ResponseEntity<DataClass> actual = restTemplate.getForEntity("/get-data/v1/?id=" + newDataId, DataClass.class);

        // Then

        assertEquals(expected, actual.getBody());
    }

    @Configuration
    @EnableJpaRepositories(basePackages = "com.example.rest.repository")
    @PropertySource("application-integrationtest.properties")
    @EnableTransactionManagement
    static class H2JpaConfig {

    }
}
