package tecec.ui.control;

import java.util.List;

import tecec.contract.reader.IMonographReader;

import tecec.contract.writer.IMonographWriter;

import tecec.dto.Monograph;

import tecec.ui.contract.control.IMonographViewerController;

import tecec.ui.contract.view.INewMonographUI;
import tecec.ui.contract.view.IUpdateMonographUI;

public class MonographViewerController extends BaseController implements IMonographViewerController {

	private String nameFilter;
	private Monograph selectedMonograph;
	private IMonographReader monographReader;
	private IMonographWriter monographWriter;

	private INewMonographUI newMonographUI;
	private IUpdateMonographUI updateMonographUI;

	public MonographViewerController (IMonographReader monographReader, IMonographWriter monographWriter, INewMonographUI newMonographUI, IUpdateMonographUI updateMonographUI) {
		this.monographReader = monographReader;
		this.monographWriter = monographWriter;
		this.newMonographUI = newMonographUI;
		this.updateMonographUI = updateMonographUI;
	}

	@Override
	public void setNameFilter(String nameFilter) {
		String old = this.nameFilter;
		this.nameFilter = nameFilter;

		super.notifyOfPropertyChange("nameFilter", old, nameFilter);
		super.notifyOfPropertyChange("monograph", null, getMonograph());
	}

	@Override
	public String getNameFilter() {
		return this.nameFilter;
	}

	@Override
	public void setSelectedMonograph(Monograph monograph) {
		Monograph old = this.selectedMonograph;
		this.selectedMonograph = monograph;

		super.notifyOfPropertyChange("selectedMonograph", old, monograph);
		super.notifyOfPropertyChange("canUpdateMonograph", old, monograph);
		super.notifyOfPropertyChange("canDeleteMonograph", old, monograph);

	}

	@Override
	public Monograph getSelectedMonograph() {
		return this.selectedMonograph;
	}

	@Override
	public List<Monograph> getMonograph() {
		List <Monograph> monograph = this.monographReader.getMonograph(this.nameFilter);
		return monograph;	
	}

	@Override
	public void deleteMonograph() {
		this.monographWriter.deleteMonograph(this.selectedMonograph.getpKMonograph());
		super.notifyOfPropertyChange("monograph", null, getMonograph());	
	}

	@Override
	public boolean getCanUpdateMonograph() {
		return this.selectedMonograph != null;	
	}

	@Override
	public boolean getCanDeleteMonograph() {
		return this.selectedMonograph != null;
	}

	@Override
	public void showNewMonographUI() {
		this.newMonographUI.setVisible(true);
		super.notifyOfPropertyChange("monograph", null, getMonograph());
	}	

	@Override
	public void showUpdateMonographUI() {
		this.updateMonographUI.setPKMonograph(this.selectedMonograph.getpKMonograph());
		this.updateMonographUI.setVisible(true);		
		super.notifyOfPropertyChange("monograph", null, getMonograph());
	}
}
