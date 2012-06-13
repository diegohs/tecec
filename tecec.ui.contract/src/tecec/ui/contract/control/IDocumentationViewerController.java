package tecec.ui.contract.control;

import java.util.List;

import tecec.dto.Documentation;

public interface IDocumentationViewerController {
	
	void setNameFilter (String nameFilter);
	String getNameFilter ();
	
	void setSelectedDocumentation (String documentation);
	Documentation getSelectedDocumentation ();
	List <Documentation> getDocumentations ();
	
	void deleteDocumentation ();
	
	boolean getCanUpdateDocumentation ();
	boolean getCanDeleteDocumentation ();
	
	void showNewDocumentationUI ();
	void showUpdateDocumentationUI ();
	
	

}
