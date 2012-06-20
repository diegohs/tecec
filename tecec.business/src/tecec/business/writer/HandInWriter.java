package tecec.business.writer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

import tecec.contract.repository.IDocumentationRepository;
import tecec.contract.repository.IHandInRepository;
import tecec.contract.writer.IHandInWriter;
import tecec.dto.Documentation;
import tecec.dto.HandIn;

public class HandInWriter implements IHandInWriter {
	
	IHandInRepository handInRepository;
	IDocumentationRepository documentationRepository;

	public HandInWriter(IHandInRepository handInRepository,
			IDocumentationRepository documentationRepository) {
		this.handInRepository = handInRepository;
		this.documentationRepository = documentationRepository;
	}

	@Override
	public void handIn(String pKMonograph, String pKActivity, String file) throws IOException {
	    File fileObject = new File(file);
	    
		FileInputStream fileStream = new FileInputStream(fileObject);
		
		byte[] array = new byte[(int)fileObject.length()];
		
		fileStream.read(array);
		
		Documentation documentation = new Documentation();
		
		documentation.setpKDocumentation(UUID.randomUUID().toString());
		documentation.setFileName(file);
		documentation.setData(array);
		
		HandIn handIn = new HandIn();
		
		handIn.setPKHandIn(UUID.randomUUID().toString());
		handIn.setFKMonograph(pKMonograph);
		handIn.setFKActivity(pKActivity);
		handIn.setFKDocumentation(documentation.getpKDocumentation());
		
		documentationRepository.insertDocumentation(documentation);
		handInRepository.insertHandIn(handIn);
	}

	@Override
	public void updateHandIn(String pKHandIn, String grade, String remark) {
		this.handInRepository.updateHandIn(pKHandIn, grade, remark);
	}

	@Override
	public void deleteHandIn(String pKHandIn) {
		this.handInRepository.deleteHandIn(pKHandIn);
	}
}
