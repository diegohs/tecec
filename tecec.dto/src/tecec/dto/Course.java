package tecec.dto;

public class Course {
	private String pKCourse;
	private String name;	
	private String year;
	private String turn;
	
	public String getPKCourse() {
		return pKCourse;
	}
	
	public void setPKCourse(String pKCourse) {
		this.pKCourse = pKCourse;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {		
		this.name = name;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getTurn() {
		return turn;
	}

	public void setTurn(String turn) {
		this.turn = turn;
	}
	
	public String toString () {
		return getName() + " / " + getYear() + " / " + getTurn();
	}
}
