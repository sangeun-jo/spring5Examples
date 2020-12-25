package spring;

import java.time.LocalDateTime; 

// 회원정보를 담고있는 객체 

public class Member {
	
	private long id;
	private String email;
	private String password; 
	private String name;
	private LocalDateTime registerDateTime; 
	
	public Member(String email, String password, String name, LocalDateTime registerDateTime){
		this.email = email;
		this.password = password;
		this.name = name;
		this.registerDateTime = registerDateTime; 
	}
	
	void setId(Long id) {
		this.id = id; 
	}
	
	public long getId() {
		return id; 
	}
	
	public String getEmail() {
		return email; 
	}
	
	public String getPassword() {
		return password; 
	}
	
	public String getName() {
		return name; 
	}
	
	public LocalDateTime getRegisterDateTime() {
		return registerDateTime; 
	}
	
	public void changePassword(String oldPassword, String newPassword) {
		if(!password.equals(oldPassword)) {
			throw new WrongIdPasswordException(); 
		}
		this.password = newPassword;
	}
}
