package com.library.management.services;

import java.util.List;

import com.library.management.payloads.BookDto;

public interface BookService {

	BookDto addBook(BookDto book);

	BookDto getBookById(Integer bookId);

	List<BookDto> getAllBooks();

	List<BookDto> getAllBooksByisAvailable(boolean isAvailable);

	BookDto updateBook(BookDto book, Integer bookId);

	void deleteBook(Integer bookId);
}
