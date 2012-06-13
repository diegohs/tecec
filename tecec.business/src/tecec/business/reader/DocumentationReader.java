package tecec.business.reader;

import java.util.List;

import tecec.contract.reader.IDocumentationReader;
import tecec.contract.repository.IDocumentationRepository;
import tecec.dto.Documentation;

public class DocumentationReader implements IDocumentationReader {
	
	IDocumentationRepository documentationRepository;
	
	public DocumentationReader (IDocumentationRepository documentationRepository) {
		this.documentationRepository = documentationRepository;
	}

	@Override
	public List<Documentation> getDocumentations(String nameFilter) {
		return this.documentationRepository.getDocumentations(nameFilter);
	}

	@Override
	public Documentation getDocumentationByPK(String pKDocumentation) {
		return this.documentationRepository.getDocumentationByPK(pKDocumentation);
	}

}
