package spring;


import java.util.Collection;

//데이터에서 읽어와서 멤버 전체를 프린트 
public class MemberListPrinter { 
	
	private MemberDao memberDao;
	private MemberPrinter printer; 
	
	public MemberListPrinter(MemberDao memberDao, MemberPrinter printer) {
		this.memberDao = memberDao;
		this.printer = printer; 
	}
	
	public void printAll() {
		Collection<Member> members = memberDao.selectAll(); 
		members.forEach(m -> printer.print(m)); 
	}
	
}
