package spring;

// 중복 가입 시 처리  

public class DuplicateMemberException extends RuntimeException { 
	
	public DuplicateMemberException(String message)  {
		super(message); 
	}
}
