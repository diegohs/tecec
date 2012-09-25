package tecec.business.reporting;

import java.text.SimpleDateFormat;
import java.util.*;

import tecec.contract.io.IReportExporter;
import tecec.contract.reporting.*;
import tecec.dto.Activity;
import tecec.dto.record.*;

public class ActivityReporter extends BaseReporter<Activity> implements IActivityReporter {

	public ActivityReporter(IReportExporter exporter) {
		super(exporter);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String[] format(Activity source) {
		ArrayList<String> result = new ArrayList<String>();
		
		result.add(source.getTitle());
		result.add(source.getDescription());
		result.add((new SimpleDateFormat("dd/MM/yyyy HH:mm")).format(source.getDueDate()));
		
		return result.toArray(new String[3]);
	}
	
	@Override
	public String[] formatHeader() {
		return new String[] { "Título", "Descrição", "Data" };
	}
}
