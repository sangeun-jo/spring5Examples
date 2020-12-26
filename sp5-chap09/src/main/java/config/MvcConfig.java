package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc // 스프링 MVC 설정 활성화. 내부적으로 다양한 빈 기본 설정 추가해줌 
public class MvcConfig implements WebMvcConfigurer{
	
	
	//스프링 MVC를 개별 설정을 조절할 때 사용 
	
	
	//default 서블릿 설정 조정 
	@Override 
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	//ViewResulver와 관련된 설정 조절 
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/view/", ".jsp"); // /WED-INF/view 폴더에 있는 확장자 jsp 파일을 뷰 파일로 사용 
	}
}
