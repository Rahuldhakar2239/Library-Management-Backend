package com.library.management.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.management.entities.Book;

public interface BookRepo extends JpaRepository<Book, Integer> {
	List<Book> findByisAvailable(boolean isAvailable);
}
