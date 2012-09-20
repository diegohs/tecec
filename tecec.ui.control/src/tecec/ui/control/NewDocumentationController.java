package tecec.ui.control;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.writer.IDocumentationWriter;

import tecec.ui.contract.control.INewDocumentationController;

public class NewDocumentationController extends BaseController implements INewDocumentationController {

	private IDocumentationWriter documentationWriter;
	private String fileName;
	private byte[] data;
	
	public NewDocumentationController (IDocumentationWriter documentationWriter) {
		if (documentationWriter == null)
			throw new IllegalArgumentException ("documentationWriter");
		this.documentationWriter = documentationWriter;
	}
	
	@Override
	public String getDocumentationFileName() {
		return this.fileName;
	}

	@Override
	public void setDocumentationFileName(String fileName) {
		String oldValue = getDocumentationFileName();
		this.fileName = fileName;
		notifyOfPropertyChange ("fileName");
	}

	@Override
	public byte[] getDocumentationData() {
		return this.data;
	}

	@Override
	public void setDocumentationData(byte[] data) {
		byte[] old = getDocumentationData();
		this.data = data;
		notifyOfPropertyChange ("data");
	}

	@Override
	public void createDocumentation() throws RuleViolationException {
		RuleViolation violation = getCreationViolation ();
		if (violation != null)
			throw new RuleViolationException (violation);
		
		documentationWriter.createDocumentation(this.fileName, this.data);
		setDocumentationData(null);
		setDocumentationFileName("");		
	}

	@Override
	public RuleViolation getCreationViolation() {
		return this.documentationWriter.getCreationViolation(this.fileName, this.data);
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}
	
}
