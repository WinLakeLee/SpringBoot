package com.example.board.advice;

import java.util.Map;
import java.util.stream.Collectors;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.example.board.domain.ResponseDTO;

@Component
@Aspect
public class ValidationCheckAdvice {

	// 포인트컷 표현식 * <- 와일드카드 .. <- 하위 패키지 생략 가능
	// com.example 하위 패키지 중 controller 패키지의 Controller로 끝나는 모든 클래스의 모든 메서드의 모든 매개변수와
	// 반환타입
	@Around("excution(* com.example..controller.*Controller.*(..))")
	public Object validationCheck(ProceedingJoinPoint jp) throws Throwable {
		Object[] args = jp.getArgs();
		for (Object arg : args) {
			if (arg instanceof BindingResult bindingResult)
				if (bindingResult.hasErrors()) {
					Map<String, String> errorMap = bindingResult.getFieldErrors().stream()
							.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
					return new ResponseDTO<>(HttpStatus.BAD_REQUEST, errorMap);
				}
		}
		return jp.proceed();
	}
}
