package tecec.ui.control;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.reader.IAdvisorReader;
import tecec.contract.writer.IAdvisorWriter;
import tecec.dto.Advisor;
import tecec.ui.contract.control.IUpdateAdvisorController;

public class UpdateAdvisorController extends BaseController implements
		IUpdateAdvisorController {

	private String pkAdvisor;
	private String advisorName;
	private String advisorEmail;
	private IAdvisorWriter advisorWriter;
	private IAdvisorReader advisorReader;

	public UpdateAdvisorController(IAdvisorWriter advisorWriter,
			IAdvisorReader advisorReader) {
		this.advisorWriter = advisorWriter;
		this.advisorReader = advisorReader;
	}

	@Override
	public void setPKAdvisor(String pkAdvisor) {
		this.pkAdvisor = pkAdvisor;
		Advisor advisor = this.advisorReader.getAdvisorByPk(pkAdvisor);
		if (advisor != null)
			this.setAdvisorName(advisor.getName());
	}

	@Override
	public void setAdvisorName(String name) {
		String old = this.advisorName;

		this.advisorName = name;

		super.notifyOfPropertyChange("advisorName", old, name);

	}

	@Override
	public void setAdvisorEmail(String email) {
		String old = this.advisorEmail;
		this.advisorEmail = email;
		super.notifyOfPropertyChange("advisorEmail", old, email);
	}

	@Override
	public String getAdvisorName() {
		return this.advisorName;
	}

	@Override
	public String getAdvisorEmail() {
		return this.advisorEmail;
	}

	@Override
	public void updateAdvisor() throws RuleViolationException {
		RuleViolation violation = getUpdateViolation();

		if (violation != null) {
			throw new RuleViolationException(violation);
		}
		this.advisorWriter.updateAdvisor(this.pkAdvisor, this.advisorName,
				this.advisorEmail);

	}

	@Override
	public RuleViolation getUpdateViolation() {
		return this.advisorWriter.getUpdateViolation(this.pkAdvisor,
				this.advisorName, this.advisorEmail);
	}
}