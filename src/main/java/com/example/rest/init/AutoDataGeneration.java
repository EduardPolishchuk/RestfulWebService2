package com.example.rest.init;

import com.example.rest.entity.DataClass;
import com.example.rest.service.DataService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class AutoDataGeneration implements ApplicationRunner {

    private final DataService dataService;

    public AutoDataGeneration(DataService dataService) {
        this.dataService = dataService;
    }

    @Override
    public void run(ApplicationArguments args) {
        List<DataClass> dataToStore = IntStream.rangeClosed(1, 100)
                .mapToObj(num -> DataClass.builder()
                        .content("Some content: " + num)
                        .build()
                ).collect(Collectors.toList());

        dataService.saveAllData(dataToStore);
    }
}
