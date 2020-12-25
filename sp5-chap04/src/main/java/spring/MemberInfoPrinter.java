package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier; 

public class MemberInfoPrinter {
	
	private MemberDao memDao;
	private MemberPrinter printer; 
	
	public void printMemberInfo(String email) {
		Member member = memDao.selectByEmail(email); 
		if (member == null) {
			System.out.println("데이터 없음\n");
			return; 
		}
		printer.print(member);
		System.out.println(); 
	}
	
	@Autowired
	public void setMemberDao(MemberDao memberDao) { //MemberInfoPrinter를 생성하면 이 새터가 자동으로 호출됨 
		this.memDao = memberDao;
	}
	
	@Autowired
	@Qualifier("printer")
	public void setPrinter(MemberPrinter printer) { //MemberInfoPrinter를 생성하면 이 새터가 자동으로 호출됨 
		this.printer = printer; 
	}
}
