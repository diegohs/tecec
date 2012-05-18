package tecec.ui.contract.record;

import tecec.dto.Monograph;

public class MonographRecord {
	Monograph monograph;
	String area;
	String student;
	String course;
	String advisor;
	String coadvisor;
	String status;
	
	public Monograph getMonograph(){
		return monograph;
	}
	
	public void setMonograph(Monograph monograph){
		this.monograph = monograph;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getStudent() {
		return student;
	}

	public void setStudent(String student) {
		this.student = student;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getAdvisor() {
		return advisor;
	}

	public void setAdvisor(String advisor) {
		this.advisor = advisor;
	}

	public String getCoadvisor() {
		return coadvisor;
	}

	public void setCoadvisor(String coadvisor) {
		this.coadvisor = coadvisor;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
