package com.example.rest.service;

import com.example.rest.entity.DataClass;
import com.example.rest.repository.DataClassRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DataService {

    private final DataClassRepository dataClassRepository;

    public DataService(DataClassRepository dataClassRepository) {
        this.dataClassRepository = dataClassRepository;
    }

    public Optional<DataClass> getDataById(long id) {
        return dataClassRepository.findById(id);
    }

    public List<DataClass> getAllData() {
        return dataClassRepository.findAll();
    }

    public List<DataClass> saveAllData(Iterable<DataClass> dataClasses) {
        return dataClassRepository.saveAll(dataClasses);
    }
}
