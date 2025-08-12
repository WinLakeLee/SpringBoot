package com.example.board.controller;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.board.domain.ResponseDTO;
import com.example.board.domain.User;
import com.example.board.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("auth")
//@RequiredArgsConstructor
public class MemberController {

	@Autowired
	private MemberService memberService;

	@GetMapping("join")
	public String join() {
		return "user/join";
	}

	@PostMapping("join")
	@ResponseBody
		public ResponseDTO<?> Register(@RequestBody User user) {
		System.out.println(user.toString());
		User findUser = memberService.getUser(user.getUserName());
		if(findUser.getUserName() == null) {
			memberService.joinUser(user);
		return new ResponseDTO<>(HttpStatus.ACCEPTED, user.getUserName() + "님 회원가입 성공");
		} else {
			return new ResponseDTO<>(HttpStatus.BAD_REQUEST, user.getUserName() + "님은 이미 가입한 회원입니다");
		}
			
	}

	@PostMapping("join2")
	public String Register2(User user, Model model) {
		System.out.println(user.toString());
		memberService.joinUser(user);
		model.addAttribute("msg", user.getUserName() + "님 회원가입 성공");
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
		if(status.is2xxSuccessful()) {
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
		User user = memberService.getUser((String)session.getAttribute("principal"));
		model.addAttribute("user", user);
		return "info";
	}
}
