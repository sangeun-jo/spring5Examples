package chap09;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


//클라이언트의 요청을 알맞게 처리할 컨트롤러

@Controller //컨트롤러로 사용하겠다 
public class HelloController {
	
	@GetMapping("/hello")  //메서드가 처리할 요청 경로(get 요청)
	public String hello(Model medel, // model은 처리결과를 view에 전달하는 역할 
			@RequestParam(value = "name", required = false) String name) { //요청 파라미터의 값을 메서드에 전달 
		medel.addAttribute("greeting", "안녕하세요, " + name);  //greeting이라는 속성에 값 설정
		return "hello";  //컨트롤러의 처리결과를 보여줄 view 이름 
	}
}
