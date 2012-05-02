package tecec.ui.contract.control;

public interface INewAdvisorController {
	tecec.dto.Advisor getAdvisor();

	void storeAdvisor();

	String getInvalidFieldsMessage();

}
