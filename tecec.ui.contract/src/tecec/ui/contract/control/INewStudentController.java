package tecec.ui.contract.control;

public interface INewStudentController {
	tecec.dto.Student getStudent();

	void storeStudent();

	String getInvalidFieldsMessage();

}
