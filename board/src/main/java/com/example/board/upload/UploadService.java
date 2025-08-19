package com.example.board.upload;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.board.repository.UserRepository;

@Service
public class UploadService {
	
	// 업로드 요청시 허용할 파일의 mime목록
	private static final Set<String> ALLOWED_TYPES = Set.of(
			"image/jpg", 
			"image/png", 
			"image/jpeg", 
			"image/gif",
			"image/webp"
		);
	private final Path root;
	private final ImageFileRepository imageFileRepository;
	private final UserRepository userRepository;
	
	UploadService(StorageProperties props, ImageFileRepository imageFileRepository, UserRepository userRepository) {
		this.root = Paths.get(props.getUploadDir()).toAbsolutePath().normalize();
		this.imageFileRepository = imageFileRepository;
		this.userRepository = userRepository;
	}	

	public void validate(MultipartFile file) {
		if (file == null || file.isEmpty())
			throw new IllegalArgumentException("파일이 없습니다");

		String contentType = file.getContentType();
		if (contentType == null || !ALLOWED_TYPES.contains(contentType))
			throw new IllegalArgumentException("지원하지 않는 파일 형식입니다");
	}

	public static String ext(String name) {
		String clean = StringUtils.getFilename(name);
		if (clean == null)
			return "";
		int i = clean.lastIndexOf(".");
		return (i >= 0) ? clean.substring(i + 1).toLowerCase() : "";
	}

	@Transactional
	public ImageFile save(MultipartFile file, String userName) throws IOException {
		validate(file);
		String original = file.getOriginalFilename();
		String ex = ext(original);
		String stored = UUID.randomUUID() + "." + ex;
//		String st = UUID.randomUUID() + "_" + original;
		
		Path target = root.resolve(stored).normalize();
		
		try (InputStream in = file.getInputStream()) {
			Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
		}
		String url = "/images/" + stored;
		String path = target.toString();
		
		ImageFile imageFile = new ImageFile(original, stored, file.getContentType(), file.getSize(), url, path, userRepository.findByUserName(userName).orElseThrow());
		return imageFileRepository.save(imageFile);
	}
	
	public List<ImageFileDTO> allImageFiles() {
		return imageFileRepository.findAll().stream().map(ImageFileDTO::from).collect(Collectors.toList());
	}
}
