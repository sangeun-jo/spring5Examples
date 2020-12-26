package config;

import org.apache.tomcat.jdbc.pool.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberInfoPrinter; 
import spring.MemberListPrinter;
import spring.MemberPrinter;
import spring.MemberRegisterService; 

@Configuration
@EnableTransactionManagement 
public class AppCtx {
	
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		DataSource ds = new DataSource(); 
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver"); 
		ds.setUrl("jdbc:mysql://localhost/spring5fs?characterEncoding=utf8&serverTimezone=Asia/Seoul"); 
		ds.setUsername("spring5");
		ds.setPassword("spring5");
		
		ds.setInitialSize(2); //미리 만들어놓을 db 연결 개수 
		ds.setMaxActive(10); //연결할 수 있는 db 최대 개수 
		
		ds.setTestWhileIdle(true);  // 비활성 상태 커넥션 풀 검사 
		ds.setMinEvictableIdleTimeMillis(1000*60*3); //커넥션 풀 비활성 상태로 최대 유지 시간 3분 
		ds.setTimeBetweenEvictionRunsMillis(1000*10); //10초 주기로 검사 
		
		return ds; 
	}
	
	@Bean //트렌잭션 관리자 
	public PlatformTransactionManager transationManager() {
		DataSourceTransactionManager tm = new DataSourceTransactionManager(); 
		tm.setDataSource(dataSource()); 
		return tm; 
	}
	
	@Bean
	public MemberDao memberDao() {
		return new MemberDao(dataSource()); 
	}
	
	@Bean
	public MemberRegisterService memberRegSvc() {
		return  new MemberRegisterService(memberDao()); 
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
	
	@Bean
	public MemberInfoPrinter infoPrinter() {
		MemberInfoPrinter infoPrinter = new MemberInfoPrinter(); 
		infoPrinter.setMemberDao(memberDao());
		infoPrinter.setPrinter(memberPrinter());
		return infoPrinter; 
	}

}
