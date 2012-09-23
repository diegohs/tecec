package tecec.business.reporting;

import java.util.*;

import tecec.contract.io.IReportExporter;
import tecec.contract.reporting.*;
import tecec.dto.*;

public class CourseReporter extends BaseReporter<Course> implements ICourseReporter {

	public CourseReporter(IReportExporter exporter) {
		super(exporter);
	}

	@Override
	public String[] format(Course source) {
		ArrayList<String> result = new ArrayList<String>();
		
		result.add(source.getName());
		result.add(source.getTurn());
		result.add(source.getYear());
		
		return result.toArray(new String[3]);
	}

	@Override
	public String[] formatHeader() {
		return new String[] { "Nome", "Turno", "Ano" };
	}
}
