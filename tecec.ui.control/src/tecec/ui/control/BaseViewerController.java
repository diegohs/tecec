package tecec.ui.control;

import java.util.List;

public abstract class BaseViewerController extends BaseController implements tecec.ui.contract.control.IViewerController {
	
	protected abstract List<String[]> getExportSource();
	
	public boolean getCanExport()
	{
		return true;
	}
	
	public void export()
	{
		
	}
}
