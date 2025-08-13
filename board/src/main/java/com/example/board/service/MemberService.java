package com.example.board.service;

import java.rmi.ServerException;
import java.util.Map;
import java.util.Optional;

import javax.security.sasl.AuthenticationException;

import org.hibernate.action.internal.EntityActionVetoException;
import org.springframework.http.HttpStatus;
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
			if (loginUser.getUserName().isEmpty())
				throw new EntityNotFoundException();
			if (map.get("password").equals(loginUser.getPassword()))
				return HttpStatus.OK;
			else if (map.get("password") != loginUser.getPassword())
				throw new EntityActionVetoException(null, null);
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

	public User updateUser(Map<String, Object> map) {
		User findUser = getUser((String) map.get("userName"));
		if (findUser.getPassword().equals((String) map.get("password"))) {
			if (((String) map.get("newPassword")).equals(""))
				findUser.setPassword((String) map.get("password"));
			else
				findUser.setPassword((String) map.get("newPassword"));
			findUser.setUserName((String) map.get("userName"));
			findUser.setEmail((String) map.get("email"));
			userRepository.save(findUser);
			return findUser;
		} else
			return null;

	}

	public boolean deleteUser(Map<String, Object> map, Integer id) {
		try {
			User findUser = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());

			if (findUser.getPassword() != ((String) map.get("password")))
				throw new AuthenticationException();
			else if (findUser.getPassword() == (String) map.get("password")) {
				userRepository.deleteById(id);
				return true;
			} else
				throw new ServerException(null);
		} catch (EntityNotFoundException e) {
			System.out.println(e);
			return false;
		} catch (AuthenticationException e) {
			System.out.println(e);
			return false;
		} catch (ServerException e) {
			System.out.println(e);
			return false;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
}
