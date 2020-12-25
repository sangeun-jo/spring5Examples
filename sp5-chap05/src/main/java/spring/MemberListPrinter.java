package spring;


import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//데이터에서 읽어와서 멤버 전체를 프린트 
@Component("listPrinter")
public class MemberListPrinter { 
	
	private MemberDao memberDao;
	private MemberPrinter printer; 
	
	public MemberListPrinter() {
		
	}
	
	@Autowired
	public void setMemberDao(MemberDao memberDao) { //MemberListPrinter를 생성하면 이 새터가 자동으로 호출됨 
		this.memberDao = memberDao; 
	}
	
	@Autowired
	public void setMemberPrinter(MemberSummaryPrinter printer) {  //MemberListPrinter를 생성하면 이 새터가 자동으로 호출됨 
		this.printer = printer;  
	}
	
	public void printAll() {
		Collection<Member> members = memberDao.selectAll(); 
		members.forEach(m -> printer.print(m)); 
	}
	
}
