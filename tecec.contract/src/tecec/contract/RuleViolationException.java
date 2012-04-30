package tecec.contract;

public class RuleViolationException extends Exception {

	public RuleViolationException(RuleViolation violation) {
		super("Regra violada: " + violation.getDescription());
	}
	
}
