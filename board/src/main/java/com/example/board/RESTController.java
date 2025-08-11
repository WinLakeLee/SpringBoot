package com.example.board;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping(value = "/rest")
public class RESTController {

	@GetMapping("/user")
	public ResponseEntity<User> getUser() {
		// DB에서 해당 회원정보를 꺼내오는 작업
		User user = User.builder()
						.userName("qwer")
						.password("1234")
						.email("aaa@naver.com")
						.build();
		
		HttpHeaders header = new HttpHeaders();
		header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		
		return new ResponseEntity<>(user, header, HttpStatus.OK);
	}
	
	@PostMapping("/user")
	public ResponseEntity<String> postUser(@RequestBody User user) {
		System.out.println(user.toString());
		return ResponseEntity.ofNullable(user.getUserName()+ "님 환영합니다.");
	}
	
	@DeleteMapping("/user")
	public ResponseEntity<String> deleteUser(@RequestParam("id") int id) {
		System.out.println("삭제할 회원 아이디 : " + id);
		HttpHeaders header = new HttpHeaders();
		header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

		String response = id + "번 회원 삭제완료";
		return new ResponseEntity<>(response, header, HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/user")
	public ResponseEntity<String> putUser(@RequestBody User user, @RequestParam("id") int id){
		
		System.out.println(id + "번 유저");
		System.out.println(user.toString());
		HttpHeaders header = new HttpHeaders();
		String response = user.getUserName() + "회원 수정 완료";
		
		return new ResponseEntity<>(response, header, HttpStatus.OK);
	}
	
	@DeleteMapping("/test/{id}")
	public ResponseEntity<String> deleteTest(@PathVariable("id") int id) {
		System.out.println(id);
		String response = "경로에 담긴 아이디 : " + id;
		return ResponseEntity.ofNullable(response);
		
	}
	
//	@PutMapping("/test/{id}")
//	public ResponseEntity<User> PUTUser(@PathVariable("id") int id,
//										@RequestBody User user) {
//		User TargetUser = UserService.findUser(id);
//		TargetUser = User.builder()
//				.userName(user.getUserName())
//				.password(user.getPassword())
//				.email(user.getEmail())
//				.build();
//		
//	}

	@GetMapping("/users")
	public ResponseEntity<List<User>> getUsers() {
		User user1 = User.builder()
						.userName("aaaa")
						.password("1234")
						.email("aaa@naver.com")
						.build();
		User user2 = User.builder()
				.userName("bbbb")
				.password("4321")
				.email("bbb@gmail.com")
				.build();
		User user3 = User.builder()
				.userName("cccc")
				.password("1324")
				.email("aaa@naver.com")
				.build();
		
		List<User> list= new ArrayList<>();
		list.add(user1);
		list.add(user2);
		list.add(user3);
		
		HttpHeaders header = new HttpHeaders();
		
		return new ResponseEntity<>(list, header, HttpStatus.OK);
	}
	
	@Operation(summary = "GET 메서드 예제", description = "이것 저것 설명 넣는 곳")
	@GetMapping("/swagger")
	public String swagger(
			@Parameter(name = "name", description = "이름", required = true) @RequestParam String name,
			@Parameter(name = "age", description = "나이", required = true) @RequestParam int age
			) {
		
		String response = "이름 : " + name + "나이 : " + age;
		return response;
	}
}
