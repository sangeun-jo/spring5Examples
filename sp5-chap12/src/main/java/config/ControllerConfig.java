package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import controller.RegisterController;
import spring.MemberRegisterService;
import survey.SurveyController; 

@Configuration 
public class ControllerConfig {
	
	@Autowired 
	private MemberRegisterService memberRegSvc; //ControllerConfig에서 memberRegSvc 가 필요할 때마다 알아서 객체 생성함.  
	
	
	@Bean
	public RegisterController registerController() {
		RegisterController controller = new RegisterController(); 
		controller.setMemberRegisterService(memberRegSvc); 
		return controller; 
	}
	
	@Bean
	public SurveyController surveyController() {  
		return new SurveyController(); 
	}
}
