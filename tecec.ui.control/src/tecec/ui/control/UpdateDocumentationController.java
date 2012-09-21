package tecec.ui.control;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

import tecec.contract.reader.IDocumentationReader;

import tecec.contract.writer.IDocumentationWriter;

import tecec.dto.Documentation;
import tecec.ui.contract.control.IUpdateDocumentationController;

public class UpdateDocumentationController extends BaseController implements
		IUpdateDocumentationController {

	private String pKDocumentation, fileName;
	private byte[] documentationData;

	private IDocumentationWriter documentationWriter;
	private IDocumentationReader documentationReader;

	public UpdateDocumentationController(
			IDocumentationWriter documentationWriter,
			IDocumentationReader documentationReader) {
		this.documentationReader = documentationReader;
		this.documentationWriter = documentationWriter;

	}

	@Override
	public void setPKDocumentation(String pKDocumentation) {
		this.pKDocumentation = pKDocumentation;

		Documentation documentation = this.documentationReader
				.getDocumentationByPK(pKDocumentation);

		this.setDocumentationData(documentation.getData());
		this.setDocumentationFileName(documentation.getFileName());
	}

	@Override
	public void setDocumentationFileName(String fileName) {
		this.fileName = fileName;

		super.notifyOfPropertyChange("documentationFileName");
		super.notifyOfPropertyChange("canUpdate");

	}

	@Override
	public void setDocumentationData(byte[] data) {
		this.documentationData = data;

		super.notifyOfPropertyChange("documentationData");
		super.notifyOfPropertyChange("canUpdate");
	}

	@Override
	public String getDocumentationFileName() {
		return this.fileName;
	}

	@Override
	public byte[] getDocumentationData() {
		return this.documentationData;
	}

	@Override
	public boolean getCanUpdate() {
		return this.documentationData != null
				&& this.documentationData.length > 0
				&& !this.fileName.trim().isEmpty() && this.fileName != null;
	}

	@Override
	public void updateDocumentation() throws RuleViolationException {
		RuleViolation violation = getUpdateViolation();

		if (violation != null)
			throw new RuleViolationException(violation);

		this.documentationWriter.updateDocumentation(this.pKDocumentation,
				this.fileName, this.documentationData);

		refresh();
	}

	@Override
	public RuleViolation getUpdateViolation() {
		Documentation documentation = new Documentation();
		documentation.setpKDocumentation(this.pKDocumentation);
		documentation.setData(this.documentationData);
		documentation.setFileName(this.fileName);
		return this.documentationWriter.getUpdateViolation(documentation);

	}

	@Override
	public void refresh() {
		setPKDocumentation(this.pKDocumentation);
	}

}
