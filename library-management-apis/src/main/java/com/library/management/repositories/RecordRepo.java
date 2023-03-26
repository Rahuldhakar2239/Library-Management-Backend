package com.library.management.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.management.entities.Record;
import com.library.management.entities.User;

public interface RecordRepo extends JpaRepository<Record, Integer> {

	List<Record> findByuser(User user);
}
