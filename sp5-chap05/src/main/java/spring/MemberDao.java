package spring;

import java.util.Collection; 
import java.util.HashMap;
import java.util.Map; 

import org.springframework.stereotype.Component;

@Component 
public class MemberDao { // 임시 db 역할 
	
	private static long nextId = 0; 
	
	private Map<String, Member> map = new HashMap<>();   
	
	public Member selectByEmail(String email) { //이메일로 임시 db에서 멤버 검색 
		return map.get(email); 
	}
	
	public void insert(Member member) { // 임시 db에 데이터 삽입 시 인덱스 부여 
		member.setId(++nextId); 
		map.put(member.getEmail(), member); 
	}
	
	public void update(Member member) { // 멤버 정보를 업데이트하여 임시 db에 저장
		map.put(member.getEmail(), member); 
	}
	
	public Collection<Member> selectAll() {
		return map.values(); 
	}
	
}
