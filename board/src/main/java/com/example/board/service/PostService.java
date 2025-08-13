package com.example.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.board.domain.Post;
import com.example.board.domain.User;
import com.example.board.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

	private final PostRepository postRespository;
	private final MemberService memberService;
	
	public boolean write(Post post, String username) {
		User findUser = memberService.getUser(username);
		try{
			Post newPost = Post.builder()
					.title(post.getTitle())
					.content(post.getContent())
					.cnt(0)
					.user(findUser)
					.build();
			postRespository.save(newPost);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Transactional(readOnly = true)
	public List<Post> getPostList() {
		List<Post> userList = postRespository.findAll();
		return userList;
	}
}
