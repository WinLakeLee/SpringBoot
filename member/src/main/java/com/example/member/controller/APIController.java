package com.example.member.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.member.domain.dto.MemberDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("api")
public class APIController {
	
	@GetMapping("welcome")
	public ResponseEntity<String> welcome() {
		return ResponseEntity.ok("welcome");
	}
	
	@PostMapping("join")
	public ResponseEntity<MemberDTO> join(@RequestBody MemberDTO dto) {
		log.info(dto.getUserName() + dto.getAge() + dto.getAddress());
		return ResponseEntity.ok(dto);
	}
	
	@ResponseBody
	@DeleteMapping("chat/{username}")
	public ResponseEntity<String> chat(@PathVariable("username") String username) {
			return ResponseEntity.ok(username + "이 입장함");
	}

	@PutMapping("modify")
	public ResponseEntity<Integer> modify(@RequestParam("id") Integer id) {
		log.info(Integer.toString(id));
		return ResponseEntity.ok(id);
	}
}
