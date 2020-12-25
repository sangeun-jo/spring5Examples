package aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

@Aspect //반복되는 코드를 모듈화하는 관리자
@Order(2)
public class ExeTimeAspect {  
	
	@Around("CommonPointcut.commonTarget()") //부가기능과 핵심기능을 구현  
	public Object measure(ProceedingJoinPoint joinPoint) throws Throwable{
		long start = System.nanoTime(); 
		try {
			Object result = joinPoint.proceed();  //핵심 기능이 실행되는 부분 
			return result; 
		} finally { 
			long finish = System.nanoTime(); 
			Signature sig = joinPoint.getSignature(); 
			System.out.printf("%s.%s(%s) 실행시간 : %d ns\n", 
					joinPoint.getTarget().getClass().getSimpleName(), //클래스 이름
					sig.getName(), //메서드 이름 
					Arrays.toString(joinPoint.getArgs()),  //인자 목록 
					(finish-start));
		}
	}
	
}
