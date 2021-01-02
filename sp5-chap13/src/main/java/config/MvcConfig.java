package config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.validation.Validator; 

import controller.RegisterRequestValidator;
import interceptor.AuthCheckInterceptor; 

@Configuration
@EnableWebMvc 
public class MvcConfig implements WebMvcConfigurer{
	
	@Override 
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Override // 요청 처리 결과와 뷰를 연결해줌  
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/view/", ".jsp"); 
	}
	
	@Override  //단순히 요청경로-뷰 이름을 연결만 해주는 컨트롤러 
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/main").setViewName("main"); 
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authCheckInterceptor())
		.addPathPatterns("/edit/**"); 
	}
	
	//스트링 읽어들이는 메서드. 언어 전환(다국어 지원) 시 유용 
	@Bean
	public MessageSource messageSource() { //무조건 빈의 이름을 messageSource로 지정해야함 
		ResourceBundleMessageSource ms = new ResourceBundleMessageSource(); 
		ms.setBasenames("message.label");
		ms.setDefaultEncoding("UTF-8");
		return ms; 
	}
	
	@Bean
	public AuthCheckInterceptor authCheckInterceptor() {
		return new AuthCheckInterceptor(); 
	}

}
