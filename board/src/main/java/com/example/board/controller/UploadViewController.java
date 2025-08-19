package com.example.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.board.upload.ImageFileDTO;
import com.example.board.upload.UploadService;

@Controller
@RequestMapping("/upload")
public class UploadViewController {

	@Autowired
	private UploadService uploadService;
	
	@GetMapping
	public String upload(Model model) {
		List<ImageFileDTO> imageList = uploadService.allImageFiles();
		model.addAttribute("imageList", imageList);
		System.out.println(imageList);
		return "upload/imageList";
	}
}
