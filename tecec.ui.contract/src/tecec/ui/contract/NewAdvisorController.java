package tecec.ui.contract;

public interface NewAdvisorController {
	tecec.dto.Advisor getAdvisor();

	void storeAdvisor();

	String getInvalidFieldsMessage();

}
