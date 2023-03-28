package com.library.management.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.management.payloads.ApiResponse;
import com.library.management.payloads.MemberShipDto;
import com.library.management.services.MemberShipService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/memberShip")
public class MemberShipController {

	@Autowired
	private MemberShipService memberShipService;

	@PostMapping("/add")
	public ResponseEntity<MemberShipDto> createNewType(@Valid @RequestBody MemberShipDto memberShipDto) {

		MemberShipDto newType = this.memberShipService.createMemberShipType(memberShipDto);
		return new ResponseEntity<MemberShipDto>(newType, HttpStatus.CREATED);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteMemberShip(@PathVariable Integer id) {

		this.memberShipService.deleteMemberShipType(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("MemberShipType deleted successfully", true),
				HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<List<MemberShipDto>> getAll() {
		List<MemberShipDto> result = this.memberShipService.getAll();
		return new ResponseEntity<List<MemberShipDto>>(result, HttpStatus.OK);

	}
}
