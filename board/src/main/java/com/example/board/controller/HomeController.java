package com.example.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.board.domain.Post;
import com.example.board.service.PostService;

@Controller
public class HomeController {

	@Autowired
	private PostService postService;
	
	@GetMapping({"/", "", "index"})
	public String index(Model model) {
		List<Post>list = postService.getPostList();
		System.out.println(list);
		model.addAttribute("postList", list);
		return "index";
	}
	
}