package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid; 

import spring.DuplicateMemberException;
import spring.MemberRegisterService;
import spring.RegisterRequest;

@Controller 
public class RegisterController {
	
	private MemberRegisterService memberRegisterService; 
	
	public void setMemberRegisterService(MemberRegisterService memberRegisterService) { 
		this.memberRegisterService = memberRegisterService; 
	}
	
	@RequestMapping("/register/step1")
	public String handleStep1() {
		return "register/step1"; 
	}
	
	@PostMapping("/register/step2")
	public String handleStep2(
			@RequestParam(value="agree", defaultValue="false") Boolean agree, Model model) {
		if(!agree) {
			return "register/step1"; 
		}
		model.addAttribute("registerRequest", new RegisterRequest()); 
		return "register/step2"; 
	}
	
	@GetMapping("/register/step2")
	public String handleStep2Get() {
		return "redirect:/register/step1";  //get 방식으로 register/step2 요청 시 약관 동의 화면으로 리다이렉트 
	}
	
	@PostMapping("/register/step3")
	public String handleStep3(@Valid RegisterRequest regReq, Errors errors) {
		//new RegisterRequestValidator().validate(regReq, errors);
		if(errors.hasErrors())
			return "register/step2"; 
		try {
			memberRegisterService.regist(regReq);
			return "register/step3"; 
		} catch (DuplicateMemberException e) {
			errors.rejectValue("email", "duplicate");  
			return "register/step2"; 
		}
	}
	
	@InitBinder //컨트롤러 범위 Validator 
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new RegisterRequestValidator()); // 대상 컨트롤러 
	}

}
