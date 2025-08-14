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

	@GetMapping({ "/", "", "index" })
	public String index(Model model,
			@PageableDefault(size = 3, sort = "postId", direction = Direction.DESC, page = 0) Pageable pageable) {
		Page<Post> list = postService.getPostList(pageable);
		model.addAttribute("postList", list);
		model.addAttribute("pageDTO", new PageDTO(list));
		System.out.println(list.getContent());
		System.out.println(list.getTotalPages());
		System.out.println(list.getTotalElements());
		System.out.println(list.getNumber());
		System.out.println(list.getSize());
		System.out.println(list.hasPrevious());
		System.out.println(list.hasNext());
		System.out.println(list.isFirst());
		System.out.println(list.isLast());
		System.out.println(list.hasContent());
		
		return "index";
	}

}