package com.example.rest.repository;

import com.example.rest.entity.DataClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataClassRepository extends JpaRepository<DataClass, Long> {
}