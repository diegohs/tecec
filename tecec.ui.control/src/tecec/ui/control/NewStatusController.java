package tecec.ui.control;



import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.writer.IStatusWriter;

import tecec.ui.contract.control.INewStatusController;


public class NewStatusController extends BaseController implements INewStatusController{

	private IStatusWriter statusWriter;
	private String statusDescription;
	
	public NewStatusController (IStatusWriter statusWriter) {
		if (statusWriter == null)
			throw new IllegalArgumentException ("statusWriter");
		this.statusWriter = statusWriter;
	}	

	@Override
	public String getStatusDescription() {
		return this.statusDescription;
	}

	@Override
	public void setStatusDescription(String description) {
		String oldValue = getStatusDescription ();
		
		this.statusDescription = description;
		
		notifyOfPropertyChange("statusDescription", oldValue, description);
	}
	
	@Override
	public void createStatus() throws RuleViolationException {
		RuleViolation violation = getCreationViolation ();
		
		if (violation != null)
			throw new RuleViolationException (violation);
		
		statusWriter.createStatus(this.statusDescription);
		
		setStatusDescription ("");
	}

	@Override
	public RuleViolation getCreationViolation() {
		return statusWriter.getCreationViolation(this.getStatusDescription());	
	}	
}
