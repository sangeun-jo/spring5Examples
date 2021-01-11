package spring;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore; 

// 회원정보를 담고있는 객체 

public class Member {
	
	private long id;
	private String email;
	@JsonIgnore
	private String password; 
	private String name;
	//@JsonFormat(shape=Shape.STRING)
	//@JsonFormat(pattern="yyyyMMddHHss")
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
	
	public boolean matchPassword(String password) {
		return this.password.equals(password); 
	}
}
