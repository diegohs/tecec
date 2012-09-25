package tecec.business.reader;

import java.util.List;

import tecec.contract.reader.IStudentReader;
import tecec.contract.repository.IStudentRepository;
import tecec.dto.Student;

public class StudentReader implements IStudentReader {
	private IStudentRepository studentRepository;

	public StudentReader(IStudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@Override
	public List<Student> getStudents(String nameFilter) {
		return studentRepository.getStudents(nameFilter);
	}

	@Override
	public Student getStudentByPk(String pkStudent, String filter) {
		return studentRepository.getStudentByPk(pkStudent, filter);
	}

	@Override
	public boolean doesUserHaveMonographiesInCourse(String pKStudent,
			String pKCourse) {
		return this.studentRepository.doesStudentHaveMonographiesInCourse(pKStudent, pKCourse);
	}

	@Override
	public List<Student> getStudentsByCourse(String pKCourse) {
		return this.studentRepository.getStudentByCourse(pKCourse);
	}

}
