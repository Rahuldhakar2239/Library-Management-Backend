package com.library.management.services;

import java.util.List;

import com.library.management.payloads.MemberShipDto;

public interface MemberShipService {
	MemberShipDto createMemberShipType(MemberShipDto memberShipDto);

	void deleteMemberShipType(Integer id);

	List<MemberShipDto> getAll();
}
