package tecec.dto;

public class Monograph {
	
	private String pKMonograph;
	private String fKArea;
	private String fKStudent;
	private String fKCourse;
	private String fKAdvisor;
	private String fKCoadvisor;
	private String fKStatus;
	private String title;
	
	public String getPKMonograph() {
		return pKMonograph;
	}
	public void setPKMonograph(String pKMonograph) {
		this.pKMonograph = pKMonograph;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFKArea() {
		return fKArea;
	}
	public void setFKArea(String fKArea) {
		this.fKArea = fKArea;
	}
	public String getFKStudent() {
		return fKStudent;
	}
	public void setFKStudent(String fKStudent) {
		this.fKStudent = fKStudent;
	}
	public String getFKCourse() {
		return fKCourse;
	}
	public void setFKCourse(String fKCourse) {
		this.fKCourse = fKCourse;
	}
	public String getFKAdvisor() {
		return fKAdvisor;
	}
	public void setFKAdvisor(String fKAdvisor) {
		this.fKAdvisor = fKAdvisor;
	}
	public String getFKCoadvisor() {
		return fKCoadvisor;
	}
	public void setFKCoadvisor(String fKCoadvisor) {
		this.fKCoadvisor = fKCoadvisor;
	}
	public String getFKStatus() {
		return fKStatus;
	}
	public void setFKStatus(String fKStatus) {
		this.fKStatus = fKStatus;
	}

}