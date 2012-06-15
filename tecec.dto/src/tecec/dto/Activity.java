package tecec.dto;

import java.util.Date;

public class Activity {
	String pKActivity;
	String title;
	String description;
	Date dueDate;
	String fKStage;
	
	public String getFKStage() {
		return fKStage;
	}
	public void setFKStage(String fKStage) {
		this.fKStage = fKStage;
	}
	public String getpKActivity() {
		return pKActivity;
	}
	public void setpKActivity(String pKActivity) {
		this.pKActivity = pKActivity;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
}
