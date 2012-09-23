package tecec.ui.control;

import java.io.IOException;
import java.util.List;

import tecec.contract.io.IReportExporter;

public abstract class BaseViewerController extends BaseController implements
		tecec.ui.contract.control.IViewerController {

	private IReportExporter _exporter;

	public BaseViewerController(IReportExporter exporter) {
		_exporter = exporter;
	}

	protected abstract List<String[]> getExportSource();

	public boolean getCanExport() {
		return true;
	}

	public void export() {
		try {
			List<String[]> entries = getExportSource();

			_exporter.setup("");

			_exporter.writeEntries(entries);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
