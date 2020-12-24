package spring;

// 사용자가 입력한 정보를 담아오는 클레스 

public class RegisterRequest {
	
	private String email;
	private String password;
	private String confirmPassword;
	private String name; 
	
	public String getEmail() {
		return email; 
	}
	
	public void setEmail(String email) {
		this.email = email; 
	}
	
	public void setPassword(String password) {
		this.password = password; 
	}
	
	public String getPassword() {
		return password; 
	}
	
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword; 
	}
	
	public String getConfirmPassword() {
		return confirmPassword; 
	}
	
	public void setName(String name) {
		this.name = name; 
	}
	
	public String getName() {
		return name; 
	}
	
	public boolean isPasswordEqualToConfirmPassword() {
		return password.equals(confirmPassword); 
	}
}
