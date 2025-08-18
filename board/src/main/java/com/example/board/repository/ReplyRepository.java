package com.example.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.board.domain.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {
	
}
