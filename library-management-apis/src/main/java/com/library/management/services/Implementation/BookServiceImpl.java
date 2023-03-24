package com.library.management.services.Implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.management.entities.Book;
import com.library.management.exceptions.ResourceNotFoundException;
import com.library.management.payloads.BookDto;
import com.library.management.repositories.BookRepo;
import com.library.management.services.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepo bookRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public BookDto addBook(BookDto bookDto) {
		Book book = this.dtoTobook(bookDto);
		Book addedbook = this.bookRepo.save(book);
		return this.bookToDto(addedbook);
	}

	@Override
	public BookDto getBookById(Integer bookId) {
		Book book = this.bookRepo.findById(bookId)
				.orElseThrow(() -> new ResourceNotFoundException("Book", "BookId", bookId));

		return this.bookToDto(book);
	}

	@Override
	public List<BookDto> getAllBooks() {
		List<Book> books = this.bookRepo.findAll();
		List<BookDto> bookDtos = books.stream().map((book) -> bookToDto(book)).collect(Collectors.toList());

		return bookDtos;
	}

	@Override
	public BookDto updateBook(BookDto bookDto, Integer bookId) {
		Book b = this.bookRepo.findById(bookId)
				.orElseThrow(() -> new ResourceNotFoundException("Book", "BookId", bookId));
		b.setAuthor(bookDto.getAuthor());
		b.setName(bookDto.getName());
		b.setCategory(bookDto.getCategory());
		b.setIsAvailable(bookDto.getIsAvailable());
		b.setCount(bookDto.getCount());

		Book updatedBook = this.bookRepo.save(b);
		BookDto bDto = this.bookToDto(updatedBook);

		return bDto;
	}

	@Override
	public void deleteBook(Integer bookId) {
		Book b = this.bookRepo.findById(bookId)
				.orElseThrow(() -> new ResourceNotFoundException("Book", "BookId", bookId));
		this.bookRepo.delete(b);

	}

	private Book dtoTobook(BookDto bookDto) {
		Book book = this.modelMapper.map(bookDto, Book.class);
		return book;
	}

	private BookDto bookToDto(Book book) {
		BookDto bookDto = this.modelMapper.map(book, BookDto.class);
		return bookDto;
	}

	@Override
	public List<BookDto> getAllBooksByisAvailable(boolean isAvailable) {
		List<Book> books = this.bookRepo.findByisAvailable(isAvailable);
		List<BookDto> bookDtos = books.stream().map((book) -> bookToDto(book)).collect(Collectors.toList());

		return bookDtos;
	}

}
