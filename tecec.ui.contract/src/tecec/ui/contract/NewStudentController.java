package tecec.ui.contract;

public interface NewStudentController {
	tecec.dto.Student getStudent();

	void storeStudent();

	String getInvalidFieldsMessage();

}
