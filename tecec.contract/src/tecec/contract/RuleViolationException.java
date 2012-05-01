package tecec.contract;

public class RuleViolationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RuleViolationException(RuleViolation violation) {
		super("Regra violada: " + violation.getDescription());
	}
	
}
