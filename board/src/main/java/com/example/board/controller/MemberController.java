package com.example.board.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.board.domain.ResponseDTO;
import com.example.board.domain.User;
import com.example.board.domain.UserDTO;
import com.example.board.service.MemberService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("auth")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@GetMapping("join")
	public String join() {
		return "user/join";
	}

	@PostMapping("join")
	@ResponseBody
	public ResponseDTO<?> Register(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
//			HashMap<String, ?> errorMap = bindingResult.getAllErrors().stream().collect(Collectors.toMap(Errors::, null));
			
		}
		User findUser = memberService.getUser(userDTO.getUserName());
		if (findUser.getUserName() == null) {
			memberService.joinUser(userDTO);
			return new ResponseDTO<>(HttpStatus.ACCEPTED, userDTO.getUserName() + "님 회원가입 성공");
		} else {
			return new ResponseDTO<>(HttpStatus.BAD_REQUEST, userDTO.getUserName() + "님은 이미 가입한 회원입니다");
		}
	}

	@PostMapping("join2")
	public String Register2(UserDTO userDTO, Model model) {
		System.out.println(userDTO.toString());
		memberService.joinUser(userDTO);
		model.addAttribute("msg", userDTO.getUserName() + "님 회원가입 성공");
		return "index";
	}

	@GetMapping("login")
	public String login() {
		return "user/login";
	}

	@PostMapping("login")
	@ResponseBody
	public ResponseEntity<?> login(@RequestBody Map<String, String> map, HttpSession session) {
		HttpStatus status = memberService.loginUser(map);
		if (status.is2xxSuccessful()) {
			session.setAttribute("principal", map.get("userName"));
		}
		return new ResponseEntity<>(status);
	}

	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.removeAttribute("principal");
		return "redirect:/";
	}

	@GetMapping("info")
	public String info(HttpSession session, Model model) {
		User user = memberService.getUser((String) session.getAttribute("principal"));
		model.addAttribute("info", user);
		return "user/info";
	}

	@PutMapping("update")
	@ResponseBody
	public ResponseEntity<?> update(@RequestBody Map<String, Object> map, HttpSession session) {
		User findUser = memberService.updateUser(map);
		session.setAttribute("principal", findUser.getUserName());
		return ResponseEntity.ok("수정 완료");

	}

	@DeleteMapping("user")
	public ResponseEntity<?> delete(@RequestBody Map<String, Object> map, @RequestParam("id") Integer id,
			HttpSession session) {
		System.out.println(map);
		boolean result = memberService.deleteUser(map, id);
		session.invalidate();
		return ResponseEntity.ok(result ? "회원 탈퇴 성공" : "회원 탈퇴 실패");
	}
}