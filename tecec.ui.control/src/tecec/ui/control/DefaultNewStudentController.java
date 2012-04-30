package tecec.ui.control;

import tecec.contract.repository.StudentRepository;
import tecec.dto.Student;

public class DefaultNewStudentController implements
		tecec.ui.contract.NewStudentController {
	private Student student;
	private StudentRepository studentRepository;

	public DefaultNewStudentController(StudentRepository studentRepository) {
		if (studentRepository == null) {
			throw new IllegalArgumentException("studentRepository");
		}

		this.studentRepository = studentRepository;
		this.student = new Student();
	}

	@Override
	public Student getStudent() {
		return this.student;
	}

	@Override
	public void storeStudent() {
		String errorMessage = getInvalidFieldsMessage();

		if (errorMessage != null) {
			throw new RuntimeException(errorMessage);
		}
		studentRepository.insertStudent(this.student);
	}

	@Override
	public String getInvalidFieldsMessage() {
		if (this.student.getName() == null
				|| this.student.getName().trim().isEmpty()) {
			return "O nome do estudante deve ser preenchido.";
		}

		if (this.student.getName().length() > 128) {
			return "O nome do estudante deve ser menor que 128 caracteres.";
		}

		if (this.student.getEmail() == null
				|| this.student.getEmail().trim().isEmpty()) {
			return "O e-mail do estudante deve ser preenchido";
		}

		if (this.student.getEmail().length() > 128) {
			return "O e-mail do estudante deve ser menor que 128 caracteres.";
		}
		return null;
	}
}
