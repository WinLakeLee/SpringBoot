package com.example.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.board.domain.PageDTO;
import com.example.board.domain.Post;
import com.example.board.service.PostService;

@Controller
public class HomeController {

	@Autowired
	private PostService postService;

	@GetMapping("/")
	public String index(Model model,
			@PageableDefault(size = 3, sort = "postId", direction = Direction.DESC, page = 0) Pageable pageable) {
		Page<Post> list = postService.getPostList(pageable);
		model.addAttribute("postList", list);
		model.addAttribute("pageDTO", new PageDTO(list));
		return "index";
	}

}