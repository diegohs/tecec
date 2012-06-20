package tecec.ui.contract.control;

import java.util.List;

import tecec.dto.Documentation;

public interface IDocumentationViewerController extends IRefreshable  {
	
	void setNameFilter (String nameFilter);
	String getNameFilter ();
	
	void setSelectedDocumentation (Documentation documentation);
	Documentation getSelectedDocumentation ();
	List <Documentation> getDocumentations ();
	
	void deleteDocumentation ();
	
	boolean getCanUpdateDocumentation ();
	boolean getCanDeleteDocumentation ();
	
	void showNewDocumentationUI ();
	void showUpdateDocumentationUI ();
	
	

}
