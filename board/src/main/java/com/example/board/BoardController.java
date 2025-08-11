package com.example.board;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

	@GetMapping({"/", ""})
	public String home() {
		return "home";
	}

	@GetMapping("hi")
	public String h2(Model model) {
		model.addAttribute("name", "홍길동");
		return "hi";
	}
 	
}