package tecec.contract.repository;

import java.util.List;

import tecec.dto.Advisor;

public interface IAdvisorRepository {
	void insertAdvisor(Advisor advisor);

	void updateAdvisor(Advisor advisor);

	void deleteAdvisor(String pkAdvisor);

	Advisor getAdvisorByName(String name);

	Advisor getAdvisorByPk(String pkAdvisor);

	Advisor getAdvisorByEmail(String email);

	List<Advisor> getAdvisors(String nameFilter);
	
	boolean doesAdviseAnyMonograph(String pKAdvisor);
}
