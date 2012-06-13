package tecec.contract.repository;

import java.util.List;


import tecec.dto.Documentation;

public interface IDocumentationRepository {
	
	void insertDocumentation (Documentation documentation);
	void updateDocumentation (Documentation documentation);
	void deleteDocumentation (String pKDocumentation);
	
	Documentation getDocumentationByPK(String pKDocumentation);
	Documentation getDocumentationByFileName (String fileName);
	
	List <Documentation> getDocumentations (String nameFilter);

}
