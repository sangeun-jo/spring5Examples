package config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbConfig {
	
	@Bean(destroyMethod="close")
	public DataSource dataSource() {
		DataSource ds = new DataSource(); //DataSource(): 스프링이 제공하는 DB 연동 기능 
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
}
