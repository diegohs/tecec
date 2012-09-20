package tecec.ui.control;

import java.util.List;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.reader.IStudentReader;
import tecec.contract.writer.IStudentWriter;
import tecec.dto.Student;
import tecec.ui.contract.control.IStudentViewerController;
import tecec.ui.contract.view.INewStudentUI;
import tecec.ui.contract.view.IUpdateStudentUI;

public class StudentViewerController  extends BaseController implements IStudentViewerController{

	private String nameFilter;
	private Student selectedStudent;
	
	private IStudentReader studentReader;
	private IStudentWriter studentWriter;	
	private INewStudentUI newStudentUI;
	private IUpdateStudentUI updateStudentUI;
	
	public StudentViewerController (IStudentReader studentReader, IStudentWriter studentWriter, 
			INewStudentUI newStudentUI, IUpdateStudentUI updateAdviorUI) {
		this.studentReader = studentReader;
		this.studentWriter = studentWriter;
		this.newStudentUI = newStudentUI;
		this.updateStudentUI = updateAdviorUI;
	}
	
	@Override
	public void setNameFilter(String nameFilter) {
		String old = this.nameFilter;		
		this.nameFilter = nameFilter;
		super.notifyOfPropertyChange("nameFilter");		
		super.notifyOfPropertyChange("students");		
	}

	@Override
	public String getNameFilter() {
		return this.nameFilter;
	}

	@Override
	public void setSelectedStudent(Student student) {
		Student old = this.selectedStudent;		
		this.selectedStudent = student;
		super.notifyOfPropertyChange("selectedStudent");
		super.notifyOfPropertyChange("canUpdateStudent");
		super.notifyOfPropertyChange("canDeleteStudent");		
	}

	@Override
	public Student getSelectedStudent() {
		return this.selectedStudent;
	}

	@Override
	public List<Student> getStudents() {
		List<Student> students = this.studentReader.getStudents(this.nameFilter);		
		return students;
	}
	
	

	@Override
	public void deleteStudent() throws RuleViolationException {
		this.studentWriter.deleteStudent(this.selectedStudent.getPKStudent());			
		super.notifyOfPropertyChange("students");
		
	}

	@Override
	public boolean getCanUpdateStudent() {
		return this.selectedStudent != null;
	}

	@Override
	public boolean getCanDeleteStudent() {
		return this.selectedStudent != null;
	}

	@Override
	public void showNewStudentUI() {
		this.newStudentUI.refresh();
		this.newStudentUI.setVisible(true);		
		super.notifyOfPropertyChange("students");			
	}

	@Override
	public void showUpdateStudentUI() {
		this.updateStudentUI.setpkStudent(this.selectedStudent.getPKStudent());
		this.updateStudentUI.setVisible(true);		
		super.notifyOfPropertyChange("students");		
	}

	@Override
	public void refresh() {
		setNameFilter("");
	}

	@Override
	public RuleViolation getDeletionViolation() {
		return this.studentWriter.getDeletionViolation(this.selectedStudent.getPKStudent());
	}

}