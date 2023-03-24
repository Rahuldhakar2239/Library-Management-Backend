package com.library.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.management.entities.Record;

public interface RecordRepo extends JpaRepository<Record, Integer> {

}
