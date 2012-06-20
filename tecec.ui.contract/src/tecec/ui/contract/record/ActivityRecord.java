package tecec.ui.contract.record;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import tecec.dto.*;

public class ActivityRecord {
	private Course course;
	private Monograph monograph;
	private Stage stage;
	private Activity activity;
	private HandIn handIn;
	private Student student;
	
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public Monograph getMonograph() {
		return monograph;
	}
	public void setMonograph(Monograph monograph) {
		this.monograph = monograph;
	}
	public Stage getStage() {
		return stage;
	}
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	public Activity getActivity() {
		return activity;
	}
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	public HandIn getHandIn() {
		return handIn;
	}
	public void setHandIn(HandIn handIn) {
		this.handIn = handIn;
	}
	public String getHandInRemark() {
		if (handIn != null) {
			return handIn.getRemark();
		}
		
		return "";
	}
	
	public String getHandInGrade(){
		if (this.handIn != null) {
			return this.handIn.getGrade();
		}
		
		return "";
	}
	
	public String getActivityDueDate(){
		if (this.activity != null) {
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			
			return format.format(this.activity.getDueDate());
		}
		
		return "";
	}
	
	public String getHandInDate(){
		if (this.handIn != null) {
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			
			return format.format(this.handIn.getHandedOn());
		}
		
		return "";
	}
	
}
