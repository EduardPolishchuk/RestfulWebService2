package com.example.rest;

import com.example.rest.entity.DataClass;
import com.example.rest.repository.DataClassRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
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
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

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
	@SuppressWarnings("unchecked")
    void getByIdE2ETest() {
        // given

        List<DataClass> expected = IntStream.rangeClosed(0, 20)
                .mapToObj(num -> DataClass.builder()
                        .content("New Content " + num)
                        .build())
                .collect(Collectors.toList());

        dataClassRepository.saveAll(expected);

        // When

        List<DataClass> forObject = restTemplate.getForObject("/get-data/v1/all", List.class);

        // Then

        assertThat(forObject)
                .isEqualTo(expected);
    }


    @Configuration
    @EnableJpaRepositories(basePackages = "com.example.rest.repository")
    @PropertySource("application-integrationtest.properties")
    @EnableTransactionManagement
    static class H2JpaConfig {

    }
}
