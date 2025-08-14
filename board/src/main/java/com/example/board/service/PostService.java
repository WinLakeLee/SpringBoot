package com.example.board.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	public Page<Post> getPostList(Pageable pageable) {
		Page<Post> PostList = postRespository.findAll(pageable);
		return PostList;
	}
}
