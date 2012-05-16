package tecec.ui.control;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

import tecec.contract.reader.IMonographReader;

import tecec.contract.writer.IMonographWriter;

import tecec.dto.Monograph;

import tecec.ui.contract.control.IUpdateMonographController;

public class UpdateMonographController extends BaseController implements IUpdateMonographController {
	
	private String pKMonograph;
	private String monographTitle;
	private IMonographWriter monographWriter;
	private IMonographReader monographReader;	
	
	/* Construtor */
	public UpdateMonographController (IMonographWriter monographWriter, IMonographReader monographReader) {
		this.monographWriter = monographWriter;
		this.monographReader = monographReader;
	}

	@Override
	public void setPKMonograph(String pKMonograph) {
		this.pKMonograph = pKMonograph;
		
		Monograph monograph = this.monographReader.getMonographByPK(pKMonograph);
		
		if (monograph != null)
			this.setMonographTitle(monograph.getTitle());
	}

	@Override
	public void setMonographTitle(String title) {
		String old = this.monographTitle;
		this.monographTitle = title;		
		super.notifyOfPropertyChange("monographTitle", old, title);
		
	}

	@Override
	public String getMonographTitle() {
		return this.monographTitle;
	}

	@Override
	public void updateMonograph() throws RuleViolationException {
		RuleViolation violation = getUpdateViolation();
		if (violation != null) {
			throw new RuleViolationException(violation);
		}
		this.monographWriter.updateMonograph(this.pKMonograph, this.monographTitle);
				
	}

	@Override
	public RuleViolation getUpdateViolation() {
		return this.monographWriter.getUpdateViolation(this.pKMonograph,this.monographTitle);
	}
}
