package tecec.business.reporting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import tecec.contract.io.IReportExporter;

public abstract class BaseReporter<T> {

	private IReportExporter _exporter;
	
	public BaseReporter(IReportExporter exporter) {
		_exporter = exporter;
	}
	
	protected abstract String[] format(T source);
	
	public List<String[]> format(List<T> sources) {
		List<String[]> result = new ArrayList<String[]>();
		
		String[] header = formatHeader();
		
		if (header != null) {
			result.add(header);
		}
		
		for (T source : sources) {
			result.add(format(source));
		}
		
		return result;
	}
	
	public String[] formatHeader()
	{
		return null;
	}

	public boolean setup(String fileName) throws IOException {
		return _exporter.setup(fileName);
	}

	public void writeEntries(List<String[]> entries) throws IOException {
		_exporter.writeEntries(entries);
	}

}
