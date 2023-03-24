package com.library.management.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.management.payloads.ApiResponse;
import com.library.management.payloads.BookDto;
import com.library.management.services.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/books")
public class BookController {
	@Autowired
	private BookService bookService;

	@PostMapping("/")
	public ResponseEntity<BookDto> addNewBook(@Valid @RequestBody BookDto bookDto) {
		BookDto addBookDto = this.bookService.addBook(bookDto);
		return new ResponseEntity<>(addBookDto, HttpStatus.CREATED);
	}

	@GetMapping("/")
	public ResponseEntity<List<BookDto>> getAllBooks() {
		return ResponseEntity.ok(this.bookService.getAllBooks());
	}

	@GetMapping("/{bookId}")
	public ResponseEntity<BookDto> getSingleBook(@PathVariable Integer bookId) {
		return ResponseEntity.ok(this.bookService.getBookById(bookId));
	}

//	get all available books
	@GetMapping("/isAvailable/{isAvailable}")
	public ResponseEntity<List<BookDto>> getAllBooks(@PathVariable Boolean isAvailable) {
		return ResponseEntity.ok(this.bookService.getAllBooksByisAvailable(isAvailable));
	}

	@PutMapping("/{bookId}")
	public ResponseEntity<BookDto> updateBook(@Valid @RequestBody BookDto bookDto, @PathVariable Integer bookId) {

		BookDto updatedBook = this.bookService.updateBook(bookDto, bookId);
		return ResponseEntity.ok(updatedBook);
	}

	@DeleteMapping("/{bookId}")
	public ResponseEntity<ApiResponse> deleteBook(@PathVariable Integer bookId) {

		this.bookService.deleteBook(bookId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Book deleted successfully", true), HttpStatus.OK);
	}

}
