package tecec.ui.control;

import java.util.List;


import tecec.contract.reader.IDocumentationReader;

import tecec.contract.writer.IDocumentationWriter;

import tecec.dto.Documentation;

import tecec.ui.contract.control.IDocumentationViewerController;

import tecec.ui.contract.view.INewDocumentationUI;

import tecec.ui.contract.view.IUpdateDocumentationUI;

public class DocumentationViewerController extends BaseController implements IDocumentationViewerController {

	private String nameFilter;
	
	private Documentation selectedDocumentation;
	
	private IDocumentationWriter documentationWriter;
	private IDocumentationReader documentationReader;
	private INewDocumentationUI newDocumentationUI;
	private IUpdateDocumentationUI updateDocumentationUI;
	
	public DocumentationViewerController (INewDocumentationUI newDocumentationUI, 
			IUpdateDocumentationUI updateDocumentationUI, IDocumentationWriter documentationWriter,
			IDocumentationReader documentationReader) {
		this.newDocumentationUI = newDocumentationUI;
		this.updateDocumentationUI = updateDocumentationUI;
		this.documentationWriter = documentationWriter;
		this.documentationReader = documentationReader;
	}
	

	@Override
	public void setNameFilter(String nameFilter) {
		this.nameFilter = nameFilter;

		super.notifyOfPropertyChange("nameFilter", null, nameFilter);
		super.notifyOfPropertyChange("documentations", null, getDocumentations());
	}

	@Override
	public String getNameFilter() {
		return this.nameFilter;
	}

	@Override
	public void setSelectedDocumentation(Documentation documentation) {
		Documentation old = this.selectedDocumentation;
		this.selectedDocumentation = documentation;
		
		super.notifyOfPropertyChange("selectedDocumentation", old, documentation);
		super.notifyOfPropertyChange("canUpdateDocumentation", old, documentation);
		super.notifyOfPropertyChange("canDeleteDocumentation", old, documentation);
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
		super.notifyOfPropertyChange("documentation", null, getDocumentations());
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
		super.notifyOfPropertyChange("documentations", null, getDocumentations());
	}

	@Override
	public void showUpdateDocumentationUI() {
		this.updateDocumentationUI.setPKDocumentation(this.selectedDocumentation.getpKDocumentation());
		this.updateDocumentationUI.setVisible(true);		
		super.notifyOfPropertyChange("documentations", null, getDocumentations());		
	}


	@Override
	public void refresh() {
		setNameFilter("");
	}

}
