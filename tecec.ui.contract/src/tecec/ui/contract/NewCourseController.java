package tecec.ui.contract;

public interface NewCourseController {
	tecec.dto.Course getCourse();

	void storeCourse();

	String getInvalidFieldsMessage();
}
