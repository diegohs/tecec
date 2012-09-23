package tecec.business.reporting;

import java.util.*;

import tecec.contract.io.IReportExporter;
import tecec.contract.reporting.*;
import tecec.dto.record.*;

public class AreaReporter extends BaseReporter<AreaRecord> implements IAreaReporter {

	public AreaReporter(IReportExporter exporter) {
		super(exporter);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String[] format(AreaRecord source) {
		ArrayList<String> result = new ArrayList<String>();
		
		result.add(source.getMainAreaName());
		result.add(source.getArea().getName());
		result.add(source.getArea().getDescription());
		
		return result.toArray(new String[3]);
	}	
	
	@Override
	public String[] formatHeader() {
		return new String[] { "Super-Área", "Nome", "Descrição" };
	}
}
