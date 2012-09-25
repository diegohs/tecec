package tecec.dto;

public class Account {
	private String id;
	private String password;
	private String userName;
	private String fKStudent;
	
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFKStudent() {
		return fKStudent;
	}
	public void setFKStudent(String fKStudent) {
		this.fKStudent = fKStudent;
	}
}
