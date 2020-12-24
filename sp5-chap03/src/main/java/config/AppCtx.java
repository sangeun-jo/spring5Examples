package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.ChangePasswordService; 
import spring.MemberDao;
import spring.MemberInfoPrinter;
import spring.MemberListPrinter;
import spring.MemberPrinter;
import spring.MemberRegisterService;
import spring.VersionPrinter;

@Configuration 
public class AppCtx { // 의존성 객체 관리하는 클레스 
	
	// ===== 생성자를 통해 객체 주입 ===== 
	@Bean 
	public MemberDao memberDao() {
		return new MemberDao(); 
	}
	
	@Bean
	public MemberRegisterService memberRegSvc() {
		return new MemberRegisterService(memberDao()); 
	}
	
	@Bean
	public ChangePasswordService changePwdSvc() {
		ChangePasswordService pwdSvc = new ChangePasswordService(); 
		pwdSvc.setMemberDao(memberDao());
		return pwdSvc; 
	}
	
	@Bean
	public MemberPrinter memberPrinter() {
		return new MemberPrinter(); 
	}
	
	@Bean 
	public MemberListPrinter listPrinter() { 
		return new MemberListPrinter(memberDao(), memberPrinter()); 
	}
	
	// ===== setter 매서드로 객체 주입 ===== 
	
	@Bean
	public MemberInfoPrinter infoPrinter() {
		MemberInfoPrinter infoPrinter = new MemberInfoPrinter(); 
		infoPrinter.setMemberDao(memberDao());
		infoPrinter.setPrinter(memberPrinter());
		return infoPrinter; 
	}
	
	@Bean
	public VersionPrinter versionPrinter() {
		VersionPrinter versionPrinter = new VersionPrinter(); 
		versionPrinter.setMajorVersion(5);
		versionPrinter.setMinorVersion(0);
		return versionPrinter; 
	}
}