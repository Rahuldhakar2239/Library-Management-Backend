package com.library.management.services.Implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.management.entities.MemberShip;
import com.library.management.exceptions.ResourceNotFoundException;
import com.library.management.payloads.MemberShipDto;
import com.library.management.repositories.MemberShipRepo;
import com.library.management.services.MemberShipService;

@Service
public class MemberShipSeviceImpl implements MemberShipService {

	@Autowired
	private MemberShipRepo memberShipRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public MemberShipDto createMemberShipType(MemberShipDto memberShipDto) {
		MemberShip member = this.modelMapper.map(memberShipDto, MemberShip.class);
		member.setType(memberShipDto.getType());
		member.setMax_book_issue(memberShipDto.getMax_book_issue());
		member.setPrice(memberShipDto.getPrice());
		member.setValidity(memberShipDto.getValidity());

		MemberShip NewMember = this.memberShipRepo.save(member);
		return this.modelMapper.map(NewMember, MemberShipDto.class);

	}

	@Override
	public void deleteMemberShipType(Integer id) {
		MemberShip m = this.memberShipRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("MemberShipType", "memberShipID", id));
		this.memberShipRepo.delete(m);

	}

	@Override
	public List<MemberShipDto> getAll() {
		List<MemberShip> list = this.memberShipRepo.findAll();
		List<MemberShipDto> listDtos = list.stream().map((m) -> this.modelMapper.map(m, MemberShipDto.class))
				.collect(Collectors.toList());
		return listDtos;
	}

}
