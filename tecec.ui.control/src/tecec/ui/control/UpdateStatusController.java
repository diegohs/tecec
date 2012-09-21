package tecec.ui.control;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

import tecec.contract.reader.IStatusReader;

import tecec.contract.writer.IStatusWriter;

import tecec.dto.Status;

import tecec.ui.contract.control.IUpdateStatusController;

public class UpdateStatusController extends BaseController implements
		IUpdateStatusController {

	private String pKStatus;
	private String statusDescription;
	private IStatusWriter statusWriter;
	private IStatusReader statusReader;

	/* Construtor */
	public UpdateStatusController(IStatusWriter statusWriter,
			IStatusReader statusReader) {
		this.statusWriter = statusWriter;
		this.statusReader = statusReader;
	}

	@Override
	public void setPKStatus(String pKStatus) {
		this.pKStatus = pKStatus;

		Status status = this.statusReader.getStatusByPK(pKStatus);

		this.setStatusDescription(status.getDescription());
	}

	@Override
	public void setStatusDescription(String description) {
		this.statusDescription = description;
		super.notifyOfPropertyChange("statusDescription");

	}

	@Override
	public String getStatusDescription() {
		return this.statusDescription;
	}

	@Override
	public void updateStatus() throws RuleViolationException {
		RuleViolation violation = getUpdateViolation();
		if (violation != null) {
			throw new RuleViolationException(violation);
		}
		this.statusWriter.updateStatus(this.pKStatus, this.statusDescription);

		refresh();
	}

	@Override
	public RuleViolation getUpdateViolation() {
		return this.statusWriter.getUpdateViolation(this.pKStatus,
				this.statusDescription);
	}

	@Override
	public void refresh() {
		setPKStatus(this.pKStatus);
	}
}
