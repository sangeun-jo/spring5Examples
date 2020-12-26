package main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List; 

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppCtx;
import spring.Member;
import spring.MemberDao;

public class MainForMemberDao{
	
	public static MemberDao memberDao; 
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class); 
		
		memberDao = ctx.getBean(MemberDao.class); 
		
		selectAll();
		udpdateMember(); 
		inserMember(); 
		
		ctx.close();
	}
	
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddHHmmss"); 

	private static void inserMember() {
		System.out.println("----- insertMember"); 
		String prefix = formatter.format(LocalDateTime.now()); 
		Member member = new Member(prefix + "@test.com", prefix, prefix, LocalDateTime.now()); 
		memberDao.insert(member);
		System.out.println(member.getId() + " 데이터 추가"); 
	}

	private static void udpdateMember() {
		System.out.println("----- updateMember");
		Member member = memberDao.selectByEmail("dog8536@naver.com");
		String oldPw = member.getPassword(); 
		String newPw = Double.toHexString(Math.random()); 
		member.changePassword(oldPw, newPw); 
		memberDao.update(member); 
		System.out.println("암호 변경: " + oldPw + " > " + newPw); 
	}


	private static void selectAll() {
		System.out.println("----- selectAll");
		int total = memberDao.count(); 
		System.out.println("전체 데이터: " + total); 
		List<Member> members = memberDao.selectAll(); 
		for (Member m : members) {
			System.out.println(m.getId() + ":" + m.getEmail() + ":" + m.getName()); 
		}
	}
	
	
}
