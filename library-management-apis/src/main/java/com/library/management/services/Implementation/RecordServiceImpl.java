package com.library.management.services.Implementation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import com.library.management.services.TransactionService;

@Service
public class RecordServiceImpl implements RecordService {

	@Autowired
	private RecordRepo recordRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private BookRepo bookRepo;

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private ModelMapper modelMapper;

	Calendar c = Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	public int fineImpose(String dueDate, String returnDate) {
		int amount = 0;
		if (returnDate == "") {
			return amount;
		}
		try {
			Date d1 = sdf.parse(dueDate);
			Date d2 = sdf.parse(returnDate);

			long difference_In_Time = d2.getTime() - d1.getTime();
//			long difference_In_Years = (difference_In_Time / (1000l * 60 * 60 * 24 * 365));
			long difference_In_Days = (difference_In_Time / (1000 * 60 * 60 * 24)) % 365;

			if (difference_In_Days > 0) {
				amount = (int) (5 * (difference_In_Days));
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return amount;

	}

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

			String today = sdf.format(c.getTime());
//			System.out.println(today);
			c.add(Calendar.DATE, 7); // Add 7 days to current date
			String after = sdf.format(c.getTime());
//			System.out.println(after);

			// modelMapper convert postDto object to Post class object
			Record record = this.modelMapper.map(recordDto, Record.class);
			record.setIssue_date(today);
			record.setDue_date(after);
			record.setReturn_date("");
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

		Book book = this.bookRepo.findById(record.getBook_Id())
				.orElseThrow(() -> new ResourceNotFoundException("Book", "bookId", record.getBook_Id()));

//		send all data otherwise put Method will set null to those fields(just send -- "return_date": "06-04-2023 02:42:56" -- format)
		record.setIssue_date(recordDto.getIssue_date());
		record.setDue_date(recordDto.getDue_date());
		record.setReturn_date(recordDto.getReturn_date());
		record.setFine_amount(fineImpose(record.getDue_date(), recordDto.getReturn_date()));
		record.setBook_Id(recordDto.getBook_Id());
		record.setBook_name(recordDto.getBook_name());

		Record updatedRecord = this.recordRepo.save(record);
		return this.modelMapper.map(updatedRecord, RecordDto.class);
	}

//	we can hit this API for one time per record. (If we want to update return_date or any other field then hit update API)"
	@Override
	public String returnBook(RecordDto recordDto, Integer recordId) {
		Record record = this.recordRepo.findById(recordId)
				.orElseThrow(() -> new ResourceNotFoundException("Record", "Id", recordId));

		if (recordDto.getReturn_date() != "" && !record.isIs_book_return()) {
			record.setIs_book_return(true);
			Book book = this.bookRepo.findById(record.getBook_Id())
					.orElseThrow(() -> new ResourceNotFoundException("Book", "bookId", record.getBook_Id()));

			book.setCount(book.getCount() + 1);
			if (book.getCount() > 0) {
				book.setIsAvailable(true);
			}
			record.setReturn_date(recordDto.getReturn_date());
			record.setFine_amount(fineImpose(record.getDue_date(), recordDto.getReturn_date()));

			Record updatedRecord = this.recordRepo.save(record);

//			************************** Create Transaction history******************************
			if (updatedRecord.getFine_amount() > 0) {
				this.transactionService.createTransaction(updatedRecord.getUser(), updatedRecord.getFine_amount());
			}
//			************************** Create Transaction history******************************

			return "Thank you for Returning the Book !";
		}
		return "return_date should not be empty ! please  try again || For Updating query Update API";

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

	@Override
	public List<RecordDto> getAllRecordByUserId(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		List<Record> records = this.recordRepo.findByuser(user);

		List<RecordDto> RecordDtos = records.stream().map((record) -> this.modelMapper.map(record, RecordDto.class))
				.collect(Collectors.toList());
		return RecordDtos;
	}

}
