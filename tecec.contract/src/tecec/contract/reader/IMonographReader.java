package tecec.contract.reader;

import java.util.List;

import tecec.dto.Monograph;

public interface IMonographReader {
	List<Monograph> getMonograph(String nameFilter);
	List<Monograph> getMonographiesByCourse(String pKCourse);
	Monograph getMonographByStudentAndCourse(String pKStudent, String pKCourse);
	Monograph getMonographByPK (String pKMonograph);
	boolean doesMonographHaveHandIns(String pKMonograph, String pkStage);
}
