package com.example.board.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.board.domain.Post;
import com.example.board.service.PostService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("post")
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;
	
	@GetMapping("insert")
	public String post() {
		return "post/insertPost";
	}
	
	@ResponseBody
	@PostMapping("")
	public ResponseEntity<?> write(@RequestBody Post post, HttpSession session) {
		System.out.println(post);
		String username = (String)session.getAttribute("principal");
		System.out.println(username);
		boolean result = postService.write(post, username);
		return ResponseEntity.ok(result? "작성완료" : "작성실패");
	}
}
