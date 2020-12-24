package spring;

//암호 변경 시 호출 
public class ChangePasswordService {
	
	private MemberDao memberDao; 
	
	public void changePassword(String email, String oldPwd, String newPwd) { 
		Member member = memberDao.selectByEmail(email); 
		if(member == null) { // 해당 멤버가 없을 떄 처리코드 호출 
			throw new MemberNotFoundException();  
		}
		
		member.changePassword(oldPwd, newPwd); // 회원 비번 변경 
		 
		memberDao.update(member); // 임시 db에서 객체 업데이트 
		
	}
	
	public void setMemberDao(MemberDao memberDao) { // 임시 db 클레스 접근하기 위한 코드(의존 객체 주입)
		this.memberDao = memberDao; 
	}
}
