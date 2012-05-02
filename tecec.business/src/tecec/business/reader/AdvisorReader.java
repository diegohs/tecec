package tecec.business.reader;

import java.util.List;

import tecec.contract.reader.IAdvisorReader;
import tecec.contract.repository.IAdvisorRepository;
import tecec.dto.Advisor;

public class AdvisorReader implements IAdvisorReader {
	private IAdvisorRepository advisorRepository;

	public AdvisorReader(IAdvisorRepository advisorRepository) {
		this.advisorRepository = advisorRepository;
	}

	@Override
	public List<Advisor> getAdvisors(String nameFilter) {
		return advisorRepository.getAdvisors(nameFilter);
	}

	@Override
	public Advisor getAdvisorByPk(String pkAdvisor) {
		return advisorRepository.getAdvisorByPk(pkAdvisor);
	}

}
