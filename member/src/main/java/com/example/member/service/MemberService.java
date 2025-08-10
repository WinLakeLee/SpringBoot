package com.example.member.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.member.domain.dto.MemberDTO;
import com.example.member.domain.entity.MemberEntity;
import com.example.member.repository.MemberRepository;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	
	public Optional<Integer> findUser(String userName) {
		Optional<Integer> result = Optional.ofNullable(memberRepository.findIdByUserName(userName));
		return result;
	}
	
	public String joinUser(MemberDTO memberDto) {
		Boolean result = findUser(memberDto.getUserName()).isEmpty();
		if(result) {
			MemberEntity entity = MemberEntity.builder()
					.userName(memberDto.getUserName())
					.age(memberDto.getAge())
					.address(memberDto.getAddress())
					.build();
			memberRepository.save(entity);
			return entity.getUserName();
		} else {
			throw new EntityExistsException();
		}
	}
	public Optional<MemberDTO> searchUser(Integer Id) {
		MemberEntity user = memberRepository.findById(Id).orElseThrow();
		Optional <MemberDTO> SearchedUser = Optional.of(DTOBuilder(user));
		return SearchedUser;
	}
	
	public MemberEntity EntityBuilder(MemberDTO dto) {
		MemberEntity entity = MemberEntity.builder()
				.memberId(dto.getMemberId())
				.userName(dto.getUserName())
				.age(dto.getAge())
				.address(dto.getAddress())
				.createdTime(dto.getCreatedDate())
				.grade(dto.getGrade())
				.build();
		return entity;
	}
	
	public MemberDTO DTOBuilder(MemberEntity entity) {
		MemberDTO dto = MemberDTO.builder()
				.memberId(entity.getMemberId())
				.userName(entity.getUserName())
				.age(entity.getAge())
				.address(entity.getAddress())
				.createdDate(entity.getCreatedTime())
				.grade(entity.getGrade())
				.build();
		return dto;
	}
	
}
