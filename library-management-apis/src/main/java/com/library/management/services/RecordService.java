package com.library.management.services;

import java.util.List;

import com.library.management.payloads.RecordDto;

public interface RecordService {

	RecordDto createRecord(RecordDto recordDto, Integer userId, Integer bookId);

	RecordDto updateRecord(RecordDto recordDto, Integer recordId);

	String returnBook(RecordDto recordDto, Integer recordId);

	void deleteRecord(Integer recordId);

	List<RecordDto> getAllRecord();

}
