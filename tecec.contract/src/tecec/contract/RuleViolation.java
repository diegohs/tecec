package tecec.contract;

public class RuleViolation {
	
	private String description;
	
	public RuleViolation(String description) {
		this.description = description;
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
}
