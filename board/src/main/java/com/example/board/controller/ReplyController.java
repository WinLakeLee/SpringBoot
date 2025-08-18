package com.example.board.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.service.ReplyService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("reply")
@RequiredArgsConstructor
public class ReplyController {

	private final ReplyService replyService;

	@PostMapping("{postid}")
	public ResponseEntity<?> replyWrite(@PathVariable(name = "postid") Integer postId,
			@RequestBody Map<String, String> map, HttpSession session) {
		HttpStatus result = replyService.write(postId, map.get("content"), (String) session.getAttribute("principal"));
		return new ResponseEntity<>(result.is2xxSuccessful() ? postId + "번 게시물에 작성 완료" : "작성 실패", result);
	}

	@DeleteMapping("{replyId}")
	public ResponseEntity<?> deleteReply(@PathVariable(name = "replyId")Integer replyId, HttpSession session) {
		HttpStatus result = replyService.delete(replyId,
				(String) session.getAttribute("principal"));
		return new ResponseEntity<>(result.is2xxSuccessful() ? "댓글 삭제 성공" : "삭제 실패", result);
	}
}
