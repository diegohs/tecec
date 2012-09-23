package tecec.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.swing.JFileChooser;

import au.com.bytecode.opencsv.CSVWriter;


public class CSVExporter implements tecec.contract.io.IReportExporter {
	
	private CSVWriter _writer;

	@Override
	public boolean setup(String fileName) throws IOException {
		JFileChooser chooser = new JFileChooser();
		
		int result = chooser.showSaveDialog(null);
		
		if (result == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			
			_writer = new CSVWriter(new FileWriter(file + ".csv"));
			
			return true;
		}
		else
		{
			if (_writer != null) {
				_writer.close();
				_writer = null;
			}
			
			return false;
		}
	}

	@Override
	public void writeEntries(List<String[]> entries) throws IOException {
		for (String[] entry : entries) {
			_writer.writeNext(entry);
		}
		
		_writer.close();
	}

}
