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
import com.library.management.payloads.RecordDto;
import com.library.management.services.RecordService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/record")
public class RecordController {

	@Autowired
	private RecordService recordService;

//	API for issuing Book
	@PostMapping("/user/{userId}/book/{bookId}")
	public ResponseEntity<RecordDto> createNewRecord(@Valid @RequestBody RecordDto recordDto,
			@PathVariable Integer userId, @PathVariable Integer bookId) {

		RecordDto createRecord = this.recordService.createRecord(recordDto, userId, bookId);
		return new ResponseEntity<RecordDto>(createRecord, HttpStatus.CREATED);

	}

	@DeleteMapping("/{recordId}")
	public ApiResponse deleteRecord(@PathVariable Integer recordId) {

		this.recordService.deleteRecord(recordId);
		return new ApiResponse("Record is deletd Successfully", true);

	}

	@GetMapping("/")
	public ResponseEntity<List<RecordDto>> getAllRecord() {
		List<RecordDto> records = this.recordService.getAllRecord();
		return new ResponseEntity<List<RecordDto>>(records, HttpStatus.OK);

	}

	@PutMapping("/{recordId}")
	public ResponseEntity<RecordDto> editRecord(@RequestBody RecordDto recordDto, @PathVariable Integer recordId) {
		RecordDto record = this.recordService.updateRecord(recordDto, recordId);

		return new ResponseEntity<RecordDto>(record, HttpStatus.OK);
	}

//	this API is only for returning the book to save return_date and calculating fine
	@PutMapping("/book/return/{recordId}")
	public ApiResponse returnBook(@RequestBody RecordDto recordDto, @PathVariable Integer recordId) {
		String message = this.recordService.returnBook(recordDto, recordId);

		return new ApiResponse(message, true);
	}
}
