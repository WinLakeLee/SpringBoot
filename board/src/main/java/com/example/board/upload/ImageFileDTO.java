package com.example.board.upload;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageFileDTO {

	private Long id;
	private String originalFilename;
	private String storedFilename;
	private Long size;
	private String url;
	private String path;
	private LocalDateTime createAt;
	private String userName;

	public static ImageFileDTO from(ImageFile file) {
		return new ImageFileDTO(
				file.getId(), 
				file.getOriginalFilename(), 
				file.getStoredFilename(), 
				file.getSize(),
				file.getUrl(), 
				file.getPath(), 
				file.getCreateAt(),
				file.getUser().getUserName()
			);
	}
}
