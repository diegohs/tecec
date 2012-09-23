package tecec.ui.control;

import java.util.List;


import tecec.contract.reader.IDocumentationReader;
import tecec.contract.reporting.IAccountReporter;
import tecec.contract.writer.IDocumentationWriter;

import tecec.dto.Documentation;

import tecec.ui.contract.control.IDocumentationViewerController;

import tecec.ui.contract.view.INewDocumentationUI;

import tecec.ui.contract.view.IUpdateDocumentationUI;

public class DocumentationViewerController extends BaseViewerController implements IDocumentationViewerController {

	private String nameFilter;
	
	private Documentation selectedDocumentation;
	
	private IDocumentationWriter documentationWriter;
	private IDocumentationReader documentationReader;
	private INewDocumentationUI newDocumentationUI;
	private IUpdateDocumentationUI updateDocumentationUI;
	
	public DocumentationViewerController (INewDocumentationUI newDocumentationUI, 
			IUpdateDocumentationUI updateDocumentationUI, IDocumentationWriter documentationWriter,
			IDocumentationReader documentationReader, IAccountReporter reporter) {
		super(reporter);
		
		this.newDocumentationUI = newDocumentationUI;
		this.updateDocumentationUI = updateDocumentationUI;
		this.documentationWriter = documentationWriter;
		this.documentationReader = documentationReader;
	}
	

	@Override
	public void setNameFilter(String nameFilter) {
		this.nameFilter = nameFilter;

		super.notifyOfPropertyChange("nameFilter");
		super.notifyOfPropertyChange("documentations");
	}

	@Override
	public String getNameFilter() {
		return this.nameFilter;
	}

	@Override
	public void setSelectedDocumentation(Documentation documentation) {
		this.selectedDocumentation = documentation;
		
		super.notifyOfPropertyChange("selectedDocumentation");
		super.notifyOfPropertyChange("canUpdateDocumentation");
		super.notifyOfPropertyChange("canDeleteDocumentation");
	}

	@Override
	public Documentation getSelectedDocumentation() {
		return this.selectedDocumentation;
	}

	@Override
	public List<Documentation> getDocumentations() {
		if (nameFilter == null)
			nameFilter = "";
		
		return this.documentationReader.getDocumentations(nameFilter);
	}

	@Override
	public void deleteDocumentation() {
		this.documentationWriter.deleteDocumentation(this.selectedDocumentation.getpKDocumentation());
		super.notifyOfPropertyChange("documentation");
	}

	@Override
	public boolean getCanUpdateDocumentation() {
		return this.selectedDocumentation != null;
	}

	@Override
	public boolean getCanDeleteDocumentation() {
		return this.selectedDocumentation != null;
	}

	@Override
	public void showNewDocumentationUI() {
		this.newDocumentationUI.setVisible(true);
		super.notifyOfPropertyChange("documentations");
	}

	@Override
	public void showUpdateDocumentationUI() {
		this.updateDocumentationUI.setPKDocumentation(this.selectedDocumentation.getpKDocumentation());
		this.updateDocumentationUI.setVisible(true);		
		super.notifyOfPropertyChange("documentations");		
	}


	@Override
	public void refresh() {
		setNameFilter("");
	}


	@Override
	protected List<String[]> getExportSource() {
		// TODO Auto-generated method stub
		return null;
	}

}
