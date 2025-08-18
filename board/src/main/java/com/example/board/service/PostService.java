package com.example.board.service;

import java.util.Optional;

import javax.security.sasl.AuthenticationException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.board.domain.Post;
import com.example.board.domain.User;
import com.example.board.repository.PostRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

	private final PostRepository postRespository;
	private final MemberService memberService;

	public boolean write(Post post, String username) {
		User findUser = memberService.getUser(username);
		try {
			Post newPost = Post.builder().title(post.getTitle()).content(post.getContent()).cnt(0).user(findUser)
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

	public Post getPost(Integer id) {
		Post post = postRespository.findById(id).orElseThrow();
		return post;
	}

	public Post getPost(Integer id, String userName) {
		try {
			Optional<Post> post = postRespository.findById(id);
			if (post.isEmpty())
				throw new EntityNotFoundException();
			if (!post.get().getUser().getUserName().equals(userName))
				throw new AuthenticationException();
			else
				return post.get();
		} catch (Exception e) {
			return null;
		}
	}

	public HttpStatus updatePost(Post post, Integer id, String userName) {
		try {
			Post findPost = postRespository.findById(id).orElseThrow(() -> new EntityNotFoundException());
			if (findPost.getUser().getUserName().equals(userName)) {
				findPost.setTitle(post.getTitle());
				findPost.setContent(post.getContent());
				postRespository.save(findPost);
				return HttpStatus.OK;
			} else {
				throw new AuthenticationException();
			}
		} catch (EntityNotFoundException e) {
			return HttpStatus.BAD_REQUEST;
		} catch (AuthenticationException e) {
			return HttpStatus.NOT_ACCEPTABLE;
		}
	}
	
	public HttpStatus deletePost(Integer id, String userName) {
		try {
			Post findPost = postRespository.findById(id).orElseThrow(() -> new EntityNotFoundException());
			if (findPost.getUser().getUserName().equals(userName)) {
				postRespository.delete(findPost);
				return HttpStatus.OK;
			} else {
				throw new AuthenticationException();
			}
		} catch (EntityNotFoundException e) {
			return HttpStatus.BAD_REQUEST;
		} catch (AuthenticationException e) {
			return HttpStatus.NOT_ACCEPTABLE;
		}
	}

}
