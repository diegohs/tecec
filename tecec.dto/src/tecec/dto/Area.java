package tecec.dto;

public class Area {
	String pKArea;
	String fKMainArea;
	String name;
	String description;
	
	public String getpKArea() {
		return pKArea;
	}
	public void setpKArea(String pKArea) {
		this.pKArea = pKArea;
	}
	public String getfKMainArea() {
		return fKMainArea;
	}
	public void setfKMainArea(String fKMainArea) {
		this.fKMainArea = fKMainArea;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return this.getName();
	}
}
