package tecec.contract.io;

import java.io.IOException;
import java.util.List;

public interface IReportExporter {
	boolean setup(String fileName) throws IOException; 	
	void writeEntries(List<String[]> entries) throws IOException;
}
