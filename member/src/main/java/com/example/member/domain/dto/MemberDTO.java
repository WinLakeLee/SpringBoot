package com.example.member.domain.dto;

import java.time.LocalDateTime;

import com.example.member.util.MemberGrade;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

	Integer memberId;
	String userName;
	Integer age;
	String address;
	LocalDateTime createdDate;
	MemberGrade grade;
}
