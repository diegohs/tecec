package tecec.dto;

import java.util.Date;

public class HandIn {
	private String PKHandIn;
	private String FKMonograph;
	private String FKActivity;
	private String FKDocumentation;
	private Date HandedOn;
	private String Grade;
	private String Remark;
	
	public String getPKHandIn() {
		return PKHandIn;
	}
	public void setPKHandIn(String pKHandIn) {
		PKHandIn = pKHandIn;
	}
	public String getFKMonograph() {
		return FKMonograph;
	}
	public void setFKMonograph(String fKMonograph) {
		FKMonograph = fKMonograph;
	}
	public String getFKActivity() {
		return FKActivity;
	}
	public void setFKActivity(String fKActivity) {
		FKActivity = fKActivity;
	}
	public String getFKDocumentation() {
		return FKDocumentation;
	}
	public void setFKDocumentation(String fKDocumentation) {
		FKDocumentation = fKDocumentation;
	}
	public Date getHandedOn() {
		return HandedOn;
	}
	public void setHandedOn(Date handedOn) {
		HandedOn = handedOn;
	}
	public String getGrade() {
		return Grade;
	}
	public void setGrade(String grade) {
		Grade = grade;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
}
