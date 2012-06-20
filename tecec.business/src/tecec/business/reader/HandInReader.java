package tecec.business.reader;

import tecec.contract.reader.IHandInReader;
import tecec.contract.repository.IHandInRepository;
import tecec.dto.HandIn;

public class HandInReader implements IHandInReader {

	IHandInRepository handInRepository;
	
	public HandInReader(IHandInRepository handInRepository) {
		this.handInRepository = handInRepository;
	}

	@Override
	public HandIn getHandInByActivityAndMonograph(String pKActivity, String pKMonograph) {
		return this.handInRepository.getHandInByActivityAndMonograph(pKActivity, pKMonograph);
	}

}
