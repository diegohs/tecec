package tecec.business.reporting;

import java.util.*;

import tecec.contract.io.IReportExporter;
import tecec.contract.reporting.*;
import tecec.dto.*;

public class StudentReporter extends BaseReporter<Student> implements
		IStudentReporter {

	public StudentReporter(IReportExporter exporter) {
		super(exporter);
	}

	@Override
	public String[] format(Student source) {
		ArrayList<String> result = new ArrayList<String>();

		result.add(source.getName());
		result.add(source.getEmail());

		return result.toArray(new String[2]);
	}

	@Override
	public String[] formatHeader() {
		return new String[] { "Nome", "E-mail" };
	}
}
