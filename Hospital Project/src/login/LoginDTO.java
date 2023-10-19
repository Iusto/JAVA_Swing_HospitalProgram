package login;

public class LoginDTO {
	private String id;
	private String password;
	
	public LoginDTO() {
	}

	public LoginDTO(String id, String password) {
		this.id = id;
		this.password = password;
	}

	@Override
	public String toString() {
		return "loginDTO [id=" + id + ", password=" + password + "]";
	}	
	
	// getter & setter
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
