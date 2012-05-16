package tecec.ui.control;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.writer.IMonographWriter;
import tecec.ui.contract.control.INewMonographController;

public class NewMonographController extends BaseController implements INewMonographController{
	
	private IMonographWriter monographWriter;
	String monographTitle;
	
	public NewMonographController(IMonographWriter monographWriter){
		if(monographWriter == null)
			throw new IllegalArgumentException("monographWriter");
		this.monographWriter = monographWriter;
	}

	@Override
	public String getMonographTitle() {
		return this.monographTitle;
	}

	@Override
	public void setMonographTitle(String title) {
		String oldValue = getMonographTitle();
		
		this.monographTitle = title;
		
		notifyOfPropertyChange("monographTitle", oldValue, title);
	}

	@Override
	public void createMonograph() throws RuleViolationException {
		RuleViolation violation = getCreationViolation ();
		
		if (violation != null)
			throw new RuleViolationException (violation);
		
		monographWriter.createMonograph(this.monographTitle);
		setMonographTitle("");
	}

	@Override
	public RuleViolation getCreationViolation() {
		return this.monographWriter.getCreationViolation(this.getMonographTitle());
	}

}
