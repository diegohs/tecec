package tecec.ui.control;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.writer.ITurnWriter;

public class NewTurnController extends BaseController implements
		tecec.ui.contract.control.INewTurnController {
	
	private ITurnWriter turnWriter;
	private String turnName;

	public NewTurnController(ITurnWriter turnWriter) {
		if (turnWriter == null) {
			throw new IllegalArgumentException("turnWriter");
		}

		this.turnWriter = turnWriter;
	}

	@Override
	public void createTurn() throws RuleViolationException {
		RuleViolation violation = getCreationViolation();

		if (violation != null) {
			throw new RuleViolationException(violation);
		}

		turnWriter.createTurn(this.turnName);

		setTurnName("");
	}

	@Override
	public String getTurnName() {
		return this.turnName;
	}

	@Override
	public void setTurnName(String name) {
		String oldValue = getTurnName();

		this.turnName = name;

		notifyOfPropertyChange("turnName", oldValue, name);
	}

	@Override
	public RuleViolation getCreationViolation() {
		return turnWriter.getCreationViolation(this.getTurnName());
	}

}
