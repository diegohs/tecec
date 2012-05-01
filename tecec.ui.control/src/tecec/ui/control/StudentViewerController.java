package tecec.ui.control;

import java.util.List;

import tecec.contract.reader.IStudentReader;
import tecec.dto.Student;
import tecec.ui.contract.IStudentViewerController;

public class StudentViewerController extends BaseController implements IStudentViewerController{

	private String nameFilter;
	private Student selectedStudent;
	private IStudentReader studentReader;
	
	public StudentViewerController (IStudentReader studentReader) {
		this.studentReader = studentReader;
	}
	
	@Override
	public void setNameFilter(String nameFilter) {
		String old = this.nameFilter;
		this.nameFilter = nameFilter;
		super.notifyOfPropertyChange("nameFilter", old, nameFilter);
		super.notifyOfPropertyChange("students", null, getStudents());		
	}

	@Override
	public String getNameFilter() {
		return this.nameFilter;
	}

	@Override
	public void setSelectedStudent(Student student) {
		Student old = this.selectedStudent;
		this.selectedStudent = student;		
		super.notifyOfPropertyChange("selectedStudent", old, student);		
	}

	@Override
	public Student getSelectedStudent() {
		return this.selectedStudent;
	}

	@Override
	public List<Student> getStudents() {
		List <Student> students = this.studentReader.getStudents(this.nameFilter);
		return students;
	}
	
}
