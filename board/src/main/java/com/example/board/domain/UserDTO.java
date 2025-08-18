package com.example.board.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	
	@NotNull(message = "username은 null이면 안됩니다")
	@NotBlank(message = "username은 반드시 입력해야 합니다")
	@Size(min = 1, max = 20, message = "username은 1~20글자로 입력해야 함")
	private String userName;
	
	@NotNull(message = "password는 null이면 안됩니다")
	@NotBlank(message = "password는 반드시 입력해야 합니다")
	@Size(min = 1, max = 20, message = "password는 1~20글자로 입력해야 함")
	private String password;
	
	@NotNull(message = "email은 null이면 안됩니다")
	@NotBlank(message = "email은 반드시 입력해야 합니다")
	@Email(message = "이메일 형식이 아님")
	private String email;
}
