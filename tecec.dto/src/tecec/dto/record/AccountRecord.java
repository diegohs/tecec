package tecec.dto.record;

public class AccountRecord {
	private String id;
	private String fKStudent;
	private String userName;
	private String studentName;
	
	public String getfKStudent() {
		return fKStudent;
	}
	public void setfKStudent(String fKStudent) {
		this.fKStudent = fKStudent;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
}
