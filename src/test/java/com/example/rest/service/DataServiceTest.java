package com.example.rest.service;

import com.example.rest.entity.DataClass;
import com.example.rest.repository.DataClassRepository;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class DataServiceTest {

    @Mock
    private DataClassRepository dataClassRepository;

    @InjectMocks
    private DataService testedService;

    private AutoCloseable autoCloseableMocks;

    @BeforeEach
    void setUp() {
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    @SneakyThrows
    void tearDown() {
        autoCloseableMocks.close();
    }

    @Test
    void getByIdTest() {
        // Given

        DataClass dataClass = DataClass.builder()
                .content("Some content get by ID")
                .id(1L)
                .build();

        Optional<DataClass> expected = Optional.of(dataClass);

        when(dataClassRepository.findById(anyLong())).thenReturn(expected);

        // When

        Optional<DataClass> actual = testedService.getDataById(1L);

        // Then

        assertEquals(expected, actual);
    }

    @Test
    void getAllTest() {
        // Given

        List<DataClass> expectedData = LongStream.rangeClosed(0, 10)
                .mapToObj(num -> DataClass.builder()
                        .content("Some content get all " + num)
                        .id(num)
                        .build()
                )
                .collect(Collectors.toList());


        when(dataClassRepository.findAll()).thenReturn(new ArrayList<>(expectedData)); // to avoid comparing by reference

        // When

        List<DataClass> actual = testedService.getAllData();

        assertThat(actual)
                .isEqualTo(expectedData);
    }

    @Test
    void saveAllTest() {
        // Given

        List<DataClass> expectedData = LongStream.rangeClosed(11, 20)
                .mapToObj(num -> DataClass.builder()
                        .content("Some content save all " + num)
                        .id(num)
                        .build()
                )
                .collect(Collectors.toList());


        // When

        List<DataClass> actual = testedService.saveAllData(expectedData);

        // Then

        verify(dataClassRepository, times(1)).saveAll(eq(new ArrayList<>(expectedData)));
    }
}