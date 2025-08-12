package com.example.board.service;

import java.util.Map;

import org.hibernate.action.internal.EntityActionVetoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.board.domain.RoleType;
import com.example.board.domain.User;
import com.example.board.repository.UserRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class MemberService {

	private final UserRepository userRepository;

	public User joinUser(User user) {
		boolean isExists = userRepository.findByUserName(user.getUserName()).isPresent();
		if (!isExists) {
			user.setRole(RoleType.USER);
			User savedUser = userRepository.save(user);
			return savedUser;
		} else
			throw new EntityExistsException();
	}

	public User getUser(String userName) {
		User findUser = userRepository.findByUserName(userName).orElseGet(() -> new User());
		return findUser;
	}

	public HttpStatus loginUser(Map<String, String> map) {
		try {
			User loginUser = getUser(map.get("userName"));
			if(loginUser.getUserName().isEmpty())
				throw new EntityNotFoundException("존재하지 않는 유저");
			if (map.get("password").equals(loginUser.getPassword()))
				return HttpStatus.OK;
			else if (map.get("password") != loginUser.getPassword())
				throw new EntityActionVetoException("비밀번호가 틀림", null);
			else
				throw new EntityNotFoundException();
		} catch (EntityNotFoundException e) {
			return HttpStatus.BAD_REQUEST;
		} catch (EntityActionVetoException e) {
			return HttpStatus.UNAUTHORIZED;
		} catch (Exception e) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
	}
}
