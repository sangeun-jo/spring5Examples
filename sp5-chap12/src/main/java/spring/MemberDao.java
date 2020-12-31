package spring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet; //sql 실행 결과 
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List; 

import javax.sql.DataSource; 

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper; // ResultSet에서 한 행의 데이터를 읽어와 자바 객체로 변환 

//auto_increment로 자동으로 생성된 키 값 구하기 
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder; 

public class MemberDao {

	private JdbcTemplate jdbcTemplate; 
	
	public MemberDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource); 
	}
	
	// 회원 추가
	/*
	public void insert(final Member member) {
		KeyHolder keyHolder = new GeneratedKeyHolder(); 
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(
						"insert into MEMBER(EMAIL, PASSWORD, NAME, REGDATE)" + "values(?, ?, ?, ?)", 
						new String[] {"ID"});
				pstmt.setString(1, member.getEmail());
				pstmt.setString(2, member.getPassword());
				pstmt.setString(3, member.getName());
				pstmt.setTimestamp(4, Timestamp.valueOf(member.getRegisterDateTime()));
				return pstmt; 
			}
		}, keyHolder); 
		Number keyValue = keyHolder.getKey(); 
		member.setId(keyValue.longValue());
	}
	
	
	*/ 
	
	//회원 추가 (람다식 사용) 
	public void insert(final Member member) {
		KeyHolder keyHolder = new GeneratedKeyHolder(); 
		jdbcTemplate.update((Connection con)->{
			PreparedStatement pstmt = con.prepareStatement(
					"insert into MEMBER(EMAIL, PASSWORD, NAME, REGDATE)" + "values(?, ?, ?, ?)", 
					new String[] {"ID"});
			pstmt.setString(1, member.getEmail());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setTimestamp(4, Timestamp.valueOf(member.getRegisterDateTime()));
			return pstmt; 
		}, keyHolder);  
		Number keyValue = keyHolder.getKey(); 
		member.setId(keyValue.longValue());
	}
	
	// 이메일로 회원 객체 얻기 
	public Member selectByEmail(String email) {
		List<Member> results = jdbcTemplate.query(
				"select * from MEMBER where EMAIL = ?", 
				new MemberRowMapper(), email); 
		return results.isEmpty() ? null : results.get(0); //결과값이 없으면 null 반환, 있으면 결과값 반환 
	}
	
	// 전체 회원 리스트 얻기 
	public List<Member> selectAll(){
		List<Member> results = jdbcTemplate.query("select * from MEMBER", new MemberRowMapper()); 
		return results; 
	}
	
	// 전체 회원 수 구하기(전체 레코드 개수) 
	public int count() {
		Integer count = jdbcTemplate.queryForObject(
				"select count(*) from MEMBER", Integer.class); 
		return count; 
	}
	
	
	// RowMapper 중복코드 제거용
	public class MemberRowMapper implements RowMapper<Member>{
		@Override
		public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
			Member member = new Member(
				rs.getString("EMAIL"), 
				rs.getString("PASSWORD"),
				rs.getString("NAME"), 
				rs.getTimestamp("REGDATE").toLocalDateTime()); 
			member.setId(rs.getLong("ID"));
			return member;
		}
	}
	
	public void update(Member member) {
		jdbcTemplate.update(
				"update MEMBER set NAME = ?, PASSWORD = ? where EMAIL = ?",
				member.getName(), member.getPassword(), member.getEmail()); 
	}

}
