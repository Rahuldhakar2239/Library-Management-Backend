package com.library.management.services.Implementation;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.management.entities.Book;
import com.library.management.entities.Record;
import com.library.management.entities.User;
import com.library.management.exceptions.ResourceNotFoundException;
import com.library.management.payloads.RecordDto;
import com.library.management.repositories.BookRepo;
import com.library.management.repositories.RecordRepo;
import com.library.management.repositories.UserRepo;
import com.library.management.services.RecordService;

@Service
public class RecordServiceImpl implements RecordService {

	@Autowired
	private RecordRepo recordRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private BookRepo bookRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public RecordDto createRecord(RecordDto recordDto, Integer userId, Integer bookId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

		Book book = this.bookRepo.findById(bookId)
				.orElseThrow(() -> new ResourceNotFoundException("Book", "bookId", bookId));

		if (book.getIsAvailable()) {
			if (book.getCount() > 0) {
				book.setCount(book.getCount() - 1);
				if (book.getCount() == 0) {
					book.setIsAvailable(false);
				}
			}

			// modelMapper convert postDto object to Post class object
			Record record = this.modelMapper.map(recordDto, Record.class);
			record.setDue_date(new Date());
			record.setReturn_date(null);
			record.setBook_Id(bookId);
			record.setBook_name(book.getName());
			record.setUser(user);

			Record newRecord = this.recordRepo.save(record);
			return this.modelMapper.map(newRecord, RecordDto.class);
		}
		// return any message or throw exception that Book is not Available
		return null;

	}

	@Override
	public RecordDto updateRecord(RecordDto recordDto, Integer recordId) {
		Record record = this.recordRepo.findById(recordId)
				.orElseThrow(() -> new ResourceNotFoundException("Record", "Id", recordId));

		if (recordDto.getReturn_date() != null) {
			Book book = this.bookRepo.findById(record.getBook_Id())
					.orElseThrow(() -> new ResourceNotFoundException("Book", "bookId", record.getBook_Id()));

			book.setCount(book.getCount() + 1);
			if (book.getCount() > 0) {
				book.setIsAvailable(true);
			}
		}
		record.setDue_date(recordDto.getDue_date());
		record.setReturn_date(recordDto.getReturn_date());
		record.setBook_Id(recordDto.getBook_Id());
		record.setBook_name(recordDto.getBook_name());

		Record updatedRecord = this.recordRepo.save(record);
		return this.modelMapper.map(updatedRecord, RecordDto.class);
	}

	@Override
	public void deleteRecord(Integer recordId) {
		Record record = this.recordRepo.findById(recordId)
				.orElseThrow(() -> new ResourceNotFoundException("Record", "Id", recordId));

		this.recordRepo.delete(record);

	}

	@Override
	public List<RecordDto> getAllRecord() {
		List<Record> records = this.recordRepo.findAll();
		List<RecordDto> RecordDtos = records.stream().map((record) -> this.modelMapper.map(record, RecordDto.class))
				.collect(Collectors.toList());
		return RecordDtos;
	}

}
