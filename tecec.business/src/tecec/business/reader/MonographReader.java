package tecec.business.reader;

import java.util.List;

import tecec.contract.reader.IMonographReader;
import tecec.contract.repository.IMonographRepository;
import tecec.dto.Monograph;

public class MonographReader implements IMonographReader {
	
	private IMonographRepository mongraphRepository;
	
	public MonographReader(IMonographRepository monographRepository){
		this.mongraphRepository = monographRepository;
	}

	@Override
	public List<Monograph> getMonograph(String nameFilter) {
		return this.mongraphRepository.getMonograph(nameFilter);
	}

	@Override
	public Monograph getMonographByPK(String pKMonograph) {
		return this.mongraphRepository.getMonographByPK(pKMonograph);
	}
	
	
	

}
