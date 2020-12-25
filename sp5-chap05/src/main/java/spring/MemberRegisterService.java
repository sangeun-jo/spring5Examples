package spring;

import java.time.LocalDateTime; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//임시 db에 회원을 등록하는 클래스
@Component
public class MemberRegisterService {
	@Autowired  
	private MemberDao memberDao;  // MemberRegisterService에서 memberDao가 필요할 때 자동으로 객체 주입
	
	public MemberRegisterService() {
	}
	
	public Long regist(RegisterRequest req) {
		Member member = memberDao.selectByEmail(req.getEmail());  //임시 db에서 이메일이 존재하는지 체크 
		if(member != null) { // 만약 존재한다면 중복 가입 예외 발생
			throw new DuplicateMemberException("dup email " + req.getEmail()); 
		}
		Member newMember = new Member(req.getEmail(), req.getPassword(), req.getName(), LocalDateTime.now()); // 새 멤버 객체 생성
		memberDao.insert(newMember); // 임시 db에 삽입 
		return newMember.getId(); // 몇 번째 가입자인지 반환 
	}
}
