package aspect;

import org.aspectj.lang.annotation.Pointcut;


// Aspect 적용되는 메서드 범위 지정 
public class CommonPointcut {
	@Pointcut("execution(public * chap07..*(..))") 
	public void commonTarget() {
	}
}
