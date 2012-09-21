package tecec.ui.control;

import java.util.ArrayList;
import java.util.List;

import tecec.contract.reader.IAdvisorReader;
import tecec.contract.reader.IAreaReader;
import tecec.contract.reader.ICourseReader;
import tecec.contract.reader.IMonographReader;
import tecec.contract.reader.IStatusReader;
import tecec.contract.reader.IStudentReader;
import tecec.contract.writer.IMonographWriter;

import tecec.dto.Advisor;
import tecec.dto.Area;
import tecec.dto.Course;
import tecec.dto.Monograph;
import tecec.dto.Status;
import tecec.dto.Student;

import tecec.ui.contract.control.IMonographViewerController;

import tecec.ui.contract.record.MonographRecord;
import tecec.ui.contract.view.INewMonographUI;
import tecec.ui.contract.view.IUpdateMonographUI;

public class MonographViewerController extends BaseViewerController implements IMonographViewerController {

	private String nameFilter;
	private MonographRecord selectedMonograph;
	
	private IMonographReader monographReader;
	private IMonographWriter monographWriter;
	
	private IAreaReader areaReader;
	private IStudentReader studentReader;
	private ICourseReader courseReader;
	private IAdvisorReader advisorReader;
	private IAdvisorReader coadvisorReader;
	private IStatusReader statusReader;
	

	private INewMonographUI newMonographUI;
	private IUpdateMonographUI updateMonographUI;

	public MonographViewerController (IMonographReader monographReader, IMonographWriter monographWriter,
			INewMonographUI newMonographUI, IUpdateMonographUI updateMonographUI,
			IAreaReader areaReader, IStudentReader studentReader, ICourseReader courseReader,
			IAdvisorReader advisorReader, IAdvisorReader coadvisorReader, IStatusReader statusReader) {
		this.monographReader = monographReader;
		this.monographWriter = monographWriter;
		this.newMonographUI = newMonographUI;
		this.updateMonographUI = updateMonographUI;
		this.areaReader = areaReader;
		this.studentReader = studentReader;
		this.courseReader = courseReader;
		this.advisorReader = advisorReader;
		this.coadvisorReader = coadvisorReader;
		this.statusReader = statusReader;
	}

	@Override
	public void setNameFilter(String nameFilter) {
		this.nameFilter = nameFilter;

		super.notifyOfPropertyChange("nameFilter");
		super.notifyOfPropertyChange("monographs");
	}

	@Override
	public String getNameFilter() {
		return this.nameFilter;
	}
	
	@Override
	public void showNewMonographUI() {
		this.newMonographUI.refresh();
		this.newMonographUI.setVisible(true);
		
		super.notifyOfPropertyChange("monographs");
	}
	
	@Override
	public void showUpdateMonographUI() {
		this.updateMonographUI.setPKMonograph(this.selectedMonograph.getMonograph().getpKMonograph());
		this.updateMonographUI.setVisible(true);		
		
		super.notifyOfPropertyChange("monographs");
	}
	
	@Override
	public void deleteMonograph() {
		this.monographWriter.deleteMonograph(this.selectedMonograph.getMonograph().getpKMonograph());
		
		super.notifyOfPropertyChange("monographs");	
	}
	
	@Override
	public MonographRecord getSelectedMonograph() {
		return this.selectedMonograph;
	}

	@Override
	public void setSelectedMonograph(MonographRecord monograph) {
		this.selectedMonograph = monograph;

		super.notifyOfPropertyChange("selectedMonograph");
		super.notifyOfPropertyChange("canUpdateMonograph");
		super.notifyOfPropertyChange("canDeleteMonograph");

	}
	
	@Override
	public boolean getCanUpdateMonograph() {
		return this.selectedMonograph != null;	
	}

	@Override
	public boolean getCanDeleteMonograph() {
		return this.selectedMonograph != null;
	}

	@Override
	public List<MonographRecord> getMonographs() {
		List<Monograph> monographs = this.monographReader.getMonograph(this.nameFilter);
		List<MonographRecord> monographRecords = new ArrayList<MonographRecord>();
		
		for(Monograph monograph : monographs){
			MonographRecord record = new MonographRecord();
			
			record.setMonograph(monograph);
			
			Area area = this.areaReader.getAreaByPK(monograph.getfKArea());
			Student student = this.studentReader.getStudentByPk(monograph.getfKStudent());
			Course course = this.courseReader.getCourseByPK(monograph.getfKCourse());
			Advisor advisor = this.advisorReader.getAdvisorByPk(monograph.getfKAdvisor());
			Advisor coadvisor = this.coadvisorReader.getAdvisorByPk(monograph.getfKCoadvisor());
			Status status = this.statusReader.getStatusByPK(monograph.getfKStatus());
			
			if(area != null){
				record.setArea(area.getName());
			}
			
			if(student != null){
				record.setStudent(student.getName());
			}
			
			if(course != null){
				record.setCourse(course.getName());
			}
			
			if(advisor != null){
				record.setAdvisor(advisor.getName());
			}
			
			if(coadvisor !=null){
				record.setCoadvisor(coadvisor.getName());
			}
			
			if(status != null){
				record.setStatus(status.getDescription());
			}
			
			monographRecords.add(record);
		}
		return monographRecords;
	}

	@Override
	public void refresh() {
		setNameFilter("");
	}

	@Override
	protected List<String[]> getExportSource() {
		// TODO Auto-generated method stub
		return null;
	}
}
