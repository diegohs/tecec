package tecec.ui.contract.control;

public interface IViewerController extends IRefreshable {
	void export();
	boolean getCanExport();
}
