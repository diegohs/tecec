package tecec.contract.reader;

import java.util.List;

import tecec.dto.Advisor;


public interface IAdvisorReader {
	List<Advisor> getAdvisors (String nameFilter);
	Advisor getAdvisorByPk (String pkAdvisor);
}
