package tecec.business.reporting;

import java.util.*;

import tecec.contract.io.IReportExporter;
import tecec.contract.reporting.*;
import tecec.dto.record.*;

public class MonographReporter extends BaseReporter<MonographRecord> implements IMonographReporter {
	
	public MonographReporter(IReportExporter exporter) {
		super(exporter);
	}

	@Override
	public String[] format(MonographRecord source) {
		ArrayList<String> result = new ArrayList<String>();
		
		result.add(source.getMonograph().getTitle());
		result.add(source.getArea());
		result.add(source.getCourse());
		result.add(source.getStudent());
		result.add(source.getAdvisor());
		result.add(source.getCoadvisor());
		result.add(source.getStatus());
		
		return result.toArray(new String[7]);
	}
	
	@Override
	public String[] formatHeader() {
		return new String[] { "T�tulo", "�rea", "Curso", "Estudante", "1� Coordenador", "2� Coordenador", "Status" };
	}
}
