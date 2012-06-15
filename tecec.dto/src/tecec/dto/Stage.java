package tecec.dto;

public class Stage {
	
	private String pKStage;
	private String name;
	private String year;
	
	public String getpKStage() {
		return pKStage;
	}
	
	public void setpKStage(String pKStage) {
		this.pKStage = pKStage;
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

	@Override
	public boolean equals(Object arg0) {
		if (arg0 == null) {
			return false;
		}
		
		if (!(arg0 instanceof Stage)) {
			return false;
		}
		
		Stage stage2 = (Stage)arg0;
		
		return this.getName().equals(stage2.getName()) &&
			   this.getpKStage().equals(stage2.getpKStage()) &&
			   this.getYear().equals(stage2.getYear());
	}
	
	@Override
	public String toString() {
		return this.getYear() + " " + this.getName();
	}
}
