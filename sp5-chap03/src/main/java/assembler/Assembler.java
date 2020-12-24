package assembler;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberRegisterService;

public class Assembler {
	
	private MemberDao memberDao; 
	private MemberRegisterService regSvc; 
	private ChangePasswordService pwdSvc; 
	
	public Assembler() { //여기서 객체 관리 
		memberDao = new MemberDao(); 
		regSvc = new MemberRegisterService(memberDao); 
		pwdSvc = new ChangePasswordService(); 
		pwdSvc.setMemberDao(memberDao); 
	}
	
	public MemberDao getMemberDao() {
		return memberDao; 
	}
	
	public MemberRegisterService getMemberRegisterService() {
		return regSvc; 
	}
	
	public ChangePasswordService getChangePasswordService() {
		return pwdSvc; 
	}
}
