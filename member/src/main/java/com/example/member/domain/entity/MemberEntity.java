package com.example.member.domain.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.member.util.MemberGrade;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member")
@EntityListeners(AuditingEntityListener.class)
public class MemberEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer memberId;
	
	@Column(name = "user_name", unique = true, nullable = false, length = 100, columnDefinition = "varchar(100)")
	String userName;
	
	@Column
	Integer age;
	
	@Column(name = "addr", nullable = false)
	String address;
	
	@CreationTimestamp
	@Column(name = "created_date")
	LocalDateTime createdTime;
	
	@Enumerated(EnumType.STRING)
	@ColumnDefault("'USER'")
	MemberGrade grade;
}