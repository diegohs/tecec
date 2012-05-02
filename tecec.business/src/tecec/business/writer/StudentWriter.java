package tecec.business.writer;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.repository.IStudentRepository;
import tecec.contract.writer.IStudentWriter;
import tecec.dto.Student;

public class StudentWriter implements IStudentWriter {

	private IStudentRepository studentRepository;

	public StudentWriter(IStudentRepository studentRepository) {
		if (studentRepository == null)
			throw new IllegalArgumentException("studentRepository");
		this.studentRepository = studentRepository;
	}

	@Override
	public RuleViolation getUpdateViolation(String pkStudent, String newName,
			String email) {
		Student student = this.studentRepository.getStudentByPk(pkStudent);
		
		if (student == null)
			return new RuleViolation ("O estudante selecionado não existe no banco de dados.");
		
		if (newName == null || newName.trim().isEmpty()) 
			return new RuleViolation("O nome do estudante deve ser preenchido.");
		
		if (email == null || email.trim().isEmpty())
			return new RuleViolation("O e-mail do estudante deve ser preenchido.");
		
		student = this.studentRepository.getStudentByEmail(email);
		
		if (student != null) {
			if (!student.getPKStudent().equals(pkStudent)) {
				return new RuleViolation ("Já existe outro estudante cadastrado com este e-mail.");
			}
		}
		
		return null;
	}

	@Override
	public void updateStudent(String pkStudent, String newName, String email)
			throws RuleViolationException {
		
		RuleViolation violation = getUpdateViolation(pkStudent, newName,email);
		if (violation != null)
			throw new RuleViolationException (violation);
		
		Student student = new Student ();
		student.setPKStudent(pkStudent);
		student.setName(newName);
		student.setEmail(email);
		
		this.studentRepository.updateStudent(student);
	}
	

	@Override
	public void createStudent(String name, String email)
			throws RuleViolationException {
		RuleViolation violation = getCreationViolation(name, email);

		if (violation != null)
			throw new RuleViolationException(violation);
		Student student = new Student();
		student.setName(name);
		student.setEmail(email);
		this.studentRepository.insertStudent(student);
	}

	@Override
	public RuleViolation getCreationViolation(String name, String email) {

		if (name == null || name.trim().isEmpty()) {
			return new RuleViolation(
					"O nome do estudante deve ser preenchido.");
		} else {
			if (name.length() > 128) {
				return new RuleViolation(
						"O nome do estudante deve ser menor que 128 caracteres.");
			}
		}

		if (email == null || email.trim().isEmpty()) {
			return new RuleViolation(
					"O e-mail do estudante deve ser preenchido.");
		} else {
			if (email.length() > 128) {
				return new RuleViolation(
						"O e-mail do estudante deve ser menor que 128 caracteres.");
			}
		}

		Student studentName = studentRepository.getStudentByName(name);
		Student studentEmail = studentRepository.getStudentByEmail(email);

		if (studentName != null && studentEmail != null) {
			return new RuleViolation(
					"Já existe outro estudante cadastrado com o mesmo nome e e-mail.");
		}

		/* Nao pode ter duas pessoas com o mesmo e-mail, acho que devemos permitir nome
		 * pois eu já estudei com pessoas com nomes idênticos, acontece*/
		if (studentEmail != null) {
			return new RuleViolation(
					"Já existe outro estudante cadastrado com o mesmo e-mail.");
		}

		return null;
	}

	@Override
	public void deleteStudent(String pkStudent) {
		this.studentRepository.deleteStudent(pkStudent);		
	}

}
