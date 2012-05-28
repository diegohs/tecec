package tecec.ui.control;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.reader.ITurnReader;
import tecec.contract.writer.ITurnWriter;
import tecec.dto.Turn;
import tecec.ui.contract.control.IUpdateTurnController;

public class UpdateTurnController extends BaseController implements IUpdateTurnController {

	private String pKTurn;
	private String turnName;
	private ITurnWriter turnWriter;
	private ITurnReader turnReader;
	
	public UpdateTurnController(ITurnWriter turnWriter, ITurnReader turnReader) {
		this.turnWriter = turnWriter;
		this.turnReader = turnReader;
	}
	
	@Override
	public void setPKTurn(String pKTurn) {
		this.pKTurn = pKTurn;
		
		Turn turn = this.turnReader.getTurnByPK(pKTurn);
		
		if (turn != null) {
			this.setTurnName(turn.getName());
		}
	}

	@Override
	public void setTurnName(String name) {
		String old = this.turnName;
		
		this.turnName = name;
		
		super.notifyOfPropertyChange("turnName", old, name);
	}

	@Override
	public String getTurnName() {
		return this.turnName;
	}

	@Override
	public void updateTurn() throws RuleViolationException {
		RuleViolation violation = getUpdateViolation();
		
		if (violation != null) {
			throw new RuleViolationException(violation);
		}
		
		this.turnWriter.updateTurn(this.pKTurn, this.turnName);
	}

	@Override
	public RuleViolation getUpdateViolation() {
		return this.turnWriter.getUpdateViolation(this.pKTurn, this.turnName);
	}

}
