package com.example.rest.service;

import com.example.rest.entity.DataClass;
import com.example.rest.repository.DataClassRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DataService {

    private final DataClassRepository dataClassRepository;

    public DataService(DataClassRepository dataClassRepository) {
        this.dataClassRepository = dataClassRepository;
    }

    @NonNull
    public Optional<DataClass> getDataById(long id) {
        log.info("Getting data by id={}", id);

        Optional<DataClass> byId = dataClassRepository.findById(id);

        log.info("Obtained data: {}", byId);

        return byId;
    }

    @NonNull
    public List<DataClass> getAllData() {
        log.info("Getting all data");

        List<DataClass> all = dataClassRepository.findAll();

        log.info("Obtained data: {}", all);

        return all;
    }

    @NonNull
    public List<DataClass> saveAllData(Iterable<DataClass> dataClasses) {
        log.info("Save data: {}", dataClasses);

        return dataClassRepository.saveAll(dataClasses);
    }
}
