package tecec.ui.control;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.writer.IAdvisorWriter;
import tecec.ui.contract.INewAdvisorController;

public class NewAdvisorController extends BaseController implements
		INewAdvisorController {
	private IAdvisorWriter advisorWriter;
	private String advisorName;
	private String advisorEmail;

	public NewAdvisorController(IAdvisorWriter advisorWriter) {
		if (advisorWriter == null) {
			throw new IllegalArgumentException("advisorWriter");
		}

		this.advisorWriter = advisorWriter;
	}

	@Override
	public String getAdvisorName() {
		return this.advisorName;
	}

	@Override
	public void setAdvisorName(String name) {
		String oldValue = getAdvisorName();
		this.advisorName = name;
		notifyOfPropertyChange("advisorName", oldValue, name);
	}

	@Override
	public String getAdvisorEmail() {
		return advisorEmail;
	}

	@Override
	public void setAdvisorEmail(String email) {
		String oldValue = getAdvisorName();
		this.advisorEmail = email;
		notifyOfPropertyChange("advisorEmail", oldValue, email);
	}

	@Override
	public void createAdvisor() throws RuleViolationException {
		RuleViolation violation = getCreationViolation();
		if (violation != null)
			throw new RuleViolationException(violation);

		advisorWriter.createAdvisor(this.advisorName, this.advisorEmail);
		setAdvisorName("");
		setAdvisorEmail("");
	}

	@Override
	public RuleViolation getCreationViolation() {
		return advisorWriter.getCreationViolation(this.getAdvisorName(),
				this.getAdvisorEmail());
	}
}
