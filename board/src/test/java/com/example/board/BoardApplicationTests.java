package com.example.board;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.board.domain.Post;
import com.example.board.repository.PostRepository;

@SpringBootTest
class BoardApplicationTests {

	@Autowired
	private PostRepository postRepository;

	@Test
	void contextLoads() {
		List<Post> list = postRepository.findAll();
		for(Post p : list) {
			System.out.println(p.getTitle());
		}
	}

}
