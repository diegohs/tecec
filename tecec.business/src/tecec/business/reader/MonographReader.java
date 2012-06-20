package tecec.business.reader;

import java.util.List;

import tecec.contract.reader.IMonographReader;
import tecec.contract.repository.IMonographRepository;
import tecec.dto.Monograph;

public class MonographReader implements IMonographReader {
	
	private IMonographRepository monographRepository;
	
	public MonographReader(IMonographRepository monographRepository){
		this.monographRepository = monographRepository;
	}

	@Override
	public List<Monograph> getMonograph(String nameFilter) {
		return this.monographRepository.getMonograph(nameFilter);
	}

	@Override
	public Monograph getMonographByPK(String pKMonograph) {
		return this.monographRepository.getMonographByPK(pKMonograph);
	}

	@Override
	public boolean doesMonographHaveHandIns(String pKMonograph, String pkStage) {
		return this.monographRepository.doesMonographHaveHandIns(pKMonograph, pkStage);
	}

	@Override
	public Monograph getMonographByStudentAndCourse(String pKStudent,
			String pKCourse) {
		return this.monographRepository.getMonographByStudentAndCourse(pKStudent, pKCourse);
	}

	@Override
	public List<Monograph> getMonographiesByCourse(String pKCourse) {
		return this.monographRepository.getMonographiesByCourse(pKCourse);
	}
	
	
	

}
