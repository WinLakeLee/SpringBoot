package com.example.board.upload;

import java.time.LocalDateTime;

import com.example.board.domain.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "image_files")
public class ImageFile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String OriginalFilename;

	@Column(nullable = false, unique = true)
	private String storedFilename;
	
	@Column(nullable = false)
	private String contentType;
	
	@Column(nullable = false)
	private Long size;
	
	@Column(length = 300)
	private String url;
	
	@Column(length = 300)
	private String path;
	
	@Column(nullable = false)
	private LocalDateTime createAt;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "userId")
	private User user;

	public ImageFile(String originalFilename, String storedFilename, String contentType, Long size, String url,
			String path, User user) {
		this.OriginalFilename = originalFilename;
		this.storedFilename = storedFilename;
		this.contentType = contentType;
		this.size = size;
		this.url = url;
		this.path = path;
		this.createAt = LocalDateTime.now();
		this.user = user;
	}
}
