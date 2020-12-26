package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
}
