package tecec.dto;

public class Student {

	private String pKStudent;
	private String name;
	private String email;

	public String getPKStudent() {
		return pKStudent;
	}

	public void setPKStudent(String pkStudent) {
		this.pKStudent = pkStudent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String toString () {
		return getName() + " / " + getEmail();
	}

}
