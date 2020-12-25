package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter; 
import org.springframework.context.annotation.FilterType;

import spring.ChangePasswordService; 
import spring.MemberDao;
import spring.MemberInfoPrinter;
import spring.MemberListPrinter;
import spring.MemberPrinter;
import spring.MemberRegisterService;
import spring.MemberSummaryPrinter;
import spring.VersionPrinter;

@Configuration
//spring.MemberDao 클래스를 컴포넌트 스캔에서 제외
@ComponentScan(basePackages = {"spring"})
public class AppCtx { 
	
	@Bean
	@Qualifier("printer") 
	public MemberPrinter memberPrinter1() {
		return new MemberPrinter(); 
	}
	
	@Bean
	public MemberSummaryPrinter memberPrinter2() {
		return new MemberSummaryPrinter(); 
	}
	
	@Bean
	public VersionPrinter versionPrinter() {
		VersionPrinter versionPrinter = new VersionPrinter(); 
		versionPrinter.setMajorVersion(5);
		versionPrinter.setMinorVersion(0);
		return versionPrinter; 
	}
}