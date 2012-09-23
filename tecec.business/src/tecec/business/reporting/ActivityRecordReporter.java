package tecec.business.reporting;

import java.util.*;

import tecec.contract.io.IReportExporter;
import tecec.contract.reporting.*;
import tecec.dto.record.*;

public class ActivityRecordReporter extends BaseReporter<ActivityRecord> implements IActivityRecordReporter {

	public ActivityRecordReporter(IReportExporter exporter) {
		super(exporter);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String[] format(ActivityRecord source) {
		ArrayList<String> result = new ArrayList<String>();

		result.add(source.getCourse().getName());
		result.add(source.getStudent().getName());
		result.add(source.getMonograph().getTitle());
		result.add(source.getStage().getName() + " " + source.getStage().getYear());
		result.add(source.getActivity().getTitle());
		result.add(source.getActivityDueDate());
		result.add(source.getHandInDate());
		result.add(source.getHandInGrade());
		
		return result.toArray(new String[7]);
	}

	@Override
	public String[] formatHeader() {
		return new String[] { "Curso", "Estudante", "Monografia", "Etapa", "Atividade", "Data de Entrega", "Entregue em", "Nota" };
	}
}
