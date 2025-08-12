package com.example.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.board.domain.User;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository <User, Integer>{
	boolean existsByUserName(String userName);
	Optional<User> findByUserName(String userName);
	Optional<User> findByUserNameAndPassword(String userName, String password);
}
