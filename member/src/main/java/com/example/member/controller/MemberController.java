package com.example.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.member.domain.dto.MemberDTO;
import com.example.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("member")
public class MemberController {
	
	private final MemberService memberService;
	
	@PostMapping("")
	public ResponseEntity<String> join(@RequestBody MemberDTO member) {
		String userName = memberService.joinUser(member);
		return new ResponseEntity<>(userName + "님 가입완료", HttpStatus.ACCEPTED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findUser(@PathVariable("id") Integer id) {
		return memberService.searchUser(id)
				.<ResponseEntity<?>>map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
											   .body("존재하지 않는 아이디입니다"));
	}
}
