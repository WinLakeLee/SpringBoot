package com.example.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.domain.RoleType;
import com.example.board.domain.User;
import com.example.board.exception.BoardException;
import com.example.board.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@CrossOrigin(originPatterns = "http://localhost")
@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping
	public ResponseEntity<String> insertUser(
			@Validated @RequestBody User user) {
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return new ResponseEntity<>((user.getUserName() + "회원가입 완료"),  HttpStatus.CREATED);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") int id) {
		userRepository.findById(id).orElseThrow(() -> new BoardException(id + "번 회원은 존재하지 않습니다."));
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<String> updateUser(@Validated @RequestBody User user) {
		User findUser = userRepository.findById(user.getId()).orElseThrow(() -> new BoardException(user.getId() + "번 회원은 존재하지 않습니다."));
		findUser.setUserName(user.getUserName());
		findUser.setPassword(user.getPassword());
		findUser.setEmail(user.getEmail());
		userRepository.save(findUser);
		return ResponseEntity.ok(findUser.getUserName() + "님 회원정보 수정완료");
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") Integer id) {
		User findUser = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		userRepository.delete(findUser);
		return ResponseEntity.ok("삭제 완료");
	}
	
	@GetMapping("list")
	public ResponseEntity<List<User>> getUserList() {
		List<User> users = userRepository.findAll();
		return ResponseEntity.ok(users);
	}
	
}