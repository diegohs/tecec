package tecec.business.reporting;

import java.util.*;

import tecec.contract.io.IReportExporter;
import tecec.contract.reporting.*;
import tecec.dto.record.CourseStudentRecord;

public class CourseReporter extends BaseReporter<CourseStudentRecord> implements ICourseReporter {

	public CourseReporter(IReportExporter exporter) {
		super(exporter);
	}

	@Override
	public String[] format(CourseStudentRecord source) {
		ArrayList<String> result = new ArrayList<String>();
		
		result.add(source.getCourse().getName());
		result.add(source.getCourse().getTurn());
		result.add(source.getCourse().getYear());
		result.add(source.getStudent().getName());
		result.add(source.getStudent().getEmail());
		
		return result.toArray(new String[5]);
	}

	@Override
	public String[] formatHeader() {
		return new String[] { "Nome", "Turno", "Ano", "Aluno", "E-mail" };
	}
}
