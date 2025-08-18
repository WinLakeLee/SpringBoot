package com.example.board.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping("{id}")
	public String detail(@PathVariable("id") Integer id, HttpSession session , Model model) {
		Post post = postService.getPost(id+1);
		model.addAttribute("post", post);
		return "post/detail";
	}
	
	@GetMapping("update/{id}")
	public String updatePost(@PathVariable("id") Integer id, HttpSession session, Model model) {
		String userName = (String)session.getAttribute("principal");
		Post post = postService.getPost(id, userName);
		model.addAttribute("post", post);
		return "post/updatePost";
	}
	
	@PutMapping("update/{id}")
	public ResponseEntity<?> updatePost(@RequestBody Post post, @PathVariable("id") Integer id, HttpSession session) {
		String currentUser = (String) session.getAttribute("principal");
		HttpStatus status = postService.updatePost(post, id, currentUser);
		return new ResponseEntity<>(post.getPostId() +"번 게시글 수정 완료", status);
	}
	
	@ResponseBody
	@DeleteMapping("{id}")
	public ResponseEntity<?> deletePost(@PathVariable("id") Integer id, HttpSession session) {
		String currentUser = (String) session.getAttribute("principal");
		HttpStatus status = postService.deletePost(id, currentUser);
		return new ResponseEntity<>(id + "번 게시글 삭제 완료", status);
	}
}