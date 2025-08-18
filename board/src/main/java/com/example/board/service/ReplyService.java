package com.example.board.service;

import javax.security.sasl.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.board.domain.Reply;
import com.example.board.domain.User;
import com.example.board.repository.PostRepository;
import com.example.board.repository.ReplyRepository;
import com.example.board.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyService {

	private final ReplyRepository replyRepository;
	private final PostRepository postRepository;
	private final UserRepository userRepository;

	public HttpStatus write(Integer postId, String content, String userName) {
		try {
			Reply reply = Reply.builder().content(content)
					.post(postRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 글")))
					.user(userRepository.findByUserName(userName)
							.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 유저")))
					.build();
			replyRepository.save(reply);
		} catch (EntityNotFoundException e) {
			return HttpStatus.BAD_REQUEST;
		}
		return HttpStatus.ACCEPTED;
	}

	public HttpStatus delete(Integer id, String userName) {
		try {
			Reply reply = replyRepository.findById(id).orElseThrow();
			User writer = reply.getUser();
			User currentUser = userRepository.findByUserName(userName)
					.orElseThrow(() -> new EntityNotFoundException("없는 유저입니다"));
			if (writer.equals(currentUser)) {
				replyRepository.delete(reply);
				return HttpStatus.OK;
			} else
				throw new AuthenticationException();
		} catch (EntityNotFoundException e) {
			return HttpStatus.BAD_REQUEST;
		} catch (AuthenticationException e) {
			return HttpStatus.NOT_ACCEPTABLE;
		}
	}
}
