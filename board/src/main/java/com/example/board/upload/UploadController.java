package com.example.board.upload;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/images")
public class UploadController {

	private final UploadService uploadService;

	@PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> upload(@RequestPart(name = "file") MultipartFile file, HttpSession session)
			throws IOException {
		ImageFile imgFile = uploadService.save(file, (String) session.getAttribute("principal"));
		return new ResponseEntity<>(ImageFileDTO.from(imgFile), HttpStatus.OK);
	}
}
