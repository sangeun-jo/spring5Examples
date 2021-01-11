package config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.validation.Validator; 

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import controller.RegisterRequestValidator;
import interceptor.AuthCheckInterceptor; 

@Configuration
@EnableWebMvc  //이 설정 추가하면, 빈에 등록하거나 재정의한 컨트롤러를 자동으로 사용함 
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
	
	@Override
	public void extendMessageConverters(
			List<HttpMessageConverter<?>>converters) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
		ObjectMapper objectMapper = Jackson2ObjectMapperBuilder
				.json()
				.serializerByType(LocalDateTime.class,
						new LocalDateTimeSerializer(formatter))
				.build(); 
		converters.add(0, new MappingJackson2HttpMessageConverter(objectMapper)); 
	}

}
