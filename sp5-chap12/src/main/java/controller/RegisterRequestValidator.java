package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors; 
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import spring.RegisterRequest;

// 값 검증하는 메서드 
public class RegisterRequestValidator implements Validator {
	
	private static final String emailRegExp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+
	"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	private Pattern pattern; 
	
	public RegisterRequestValidator() {
		pattern = Pattern.compile(emailRegExp); 
	}
	
	@Override //전달 받은 객체가 RegisterRequest 타입으로 변환가능한지 확인 
	public boolean supports(Class<?> clazz) {
		return RegisterRequest.class.isAssignableFrom(clazz); 
	}
	
	@Override // target: 검사할 객체, errors: 검사 결과 에러코드 설정하기 위한 변수 
	public void validate(Object target, Errors errors) {
		RegisterRequest regReq = (RegisterRequest) target; 
		if(regReq.getEmail() == null || regReq.getEmail().trim().isEmpty()) { // 이메일이 입력 안되있으면 
			errors.rejectValue("email", "required"); // 에러코드로 required 설정 
		} else { 
			Matcher matcher = pattern.matcher(regReq.getEmail()); //정규 표현식을 통해 이메일 형식 검사 
			if (!matcher.matches()) { 
				errors.rejectValue("email", "bad"); // 에러코드 bad 설정 
			}
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");  // name이 null이거나 공백 문자인 경우 에러코드로 required 추가 
		ValidationUtils.rejectIfEmpty(errors, "password", "required");
		ValidationUtils.rejectIfEmpty(errors, "confirmPassword", "required");
		if(!regReq.getPassword().isEmpty()) {
			if(!regReq.isPasswordEqualToConfirmPassword()) {
				errors.rejectValue("confirmPassword", "nomatch");
			}
		}
		
	}
}
