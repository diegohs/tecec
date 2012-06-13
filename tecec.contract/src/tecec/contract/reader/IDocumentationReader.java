package tecec.contract.reader;

import java.util.List;


import tecec.dto.Documentation;

public interface IDocumentationReader {	
	List <Documentation> getDocumentations (String nameFilter);
	Documentation getDocumentationByPK (String pKDocumentation);
}
