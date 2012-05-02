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
	public Student getStudentByPk(String pkStudent) {
		return studentRepository.getStudentByPk(pkStudent);
	}

}
