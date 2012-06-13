package tecec.business.writer;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

import tecec.contract.repository.IDocumentationRepository;
import tecec.contract.writer.IDocumentationWriter;

import tecec.dto.Documentation;

public class DocumentationWriter implements IDocumentationWriter {
	
	private IDocumentationRepository documentationRepository;
	
	public DocumentationWriter (IDocumentationRepository documentationRepository) {
		if (documentationRepository == null) {
			throw new IllegalArgumentException ("documentationRepository");
		}
		
		this.documentationRepository = documentationRepository;
	}
	
	
	@Override
	public RuleViolation getCreationViolation(String fileName, byte[] data) {
		if (fileName == null || fileName.trim().isEmpty()) {
			return new RuleViolation ("O nome do arquivo deve ser preenchido.");
		} else {
			if (fileName.length() > 128) {
				return new RuleViolation ("O nome do arquivo deve ser válido.");
			}
		}
		
		if (data == null || data.length<=0) {
			return new RuleViolation ("Um arquivo deve ser selecionado.");
		}
		
		return null;
	}

	@Override
	public RuleViolation getUpdateViolation(Documentation newDocumentation) {
		Documentation search = this.documentationRepository.getDocumentationByPK(newDocumentation.getpKDocumentation());
		
		if (search != null) {
			if (!search.getpKDocumentation().equals(newDocumentation.getpKDocumentation())) {
				return new RuleViolation ("Já existe outro documento cadastrado com esta chave.");
			}
		}
		
		return null;
	}
	
	

	@Override
	public void createDocumentation(String fileName, byte[] data)
			throws RuleViolationException {
		
		RuleViolation violation = getCreationViolation (fileName, data);
		
		if (violation != null)
			throw new RuleViolationException (violation);
		
		Documentation doc = new Documentation();
		doc.setData(data);
		doc.setFileName(fileName);
		
		this.documentationRepository.insertDocumentation(doc);
		
	}

	@Override
	public void updateDocumentation(String pKDocumentation, String newFileName,
			byte[] newData) throws RuleViolationException {
		
		RuleViolation violation = getCreationViolation (newFileName, newData);
		
		if (violation != null)
			throw new RuleViolationException (violation);
		
		Documentation doc = new Documentation ();
		doc.setData(newData);
		doc.setFileName(newFileName);
		doc.setpKDocumentation(pKDocumentation);
		
		this.documentationRepository.updateDocumentation(doc);
		
	}

	@Override
	public void deleteDocumentation(String pKDocumentation) {
		this.documentationRepository.deleteDocumentation(pKDocumentation);
	}

}
