package tecec.contract.reporting;

import java.util.List;

public interface IReporter<T> extends tecec.contract.io.IReportExporter {
	String[] format(T source);
	List<String[]> format(List<T> sources);
}
