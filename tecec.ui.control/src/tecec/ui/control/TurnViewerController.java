package tecec.ui.control;

import java.util.List;

import tecec.contract.reader.ITurnReader;
import tecec.contract.writer.ITurnWriter;
import tecec.dto.Turn;
import tecec.ui.contract.control.ITurnViewerController;
import tecec.ui.contract.view.INewTurnUI;
import tecec.ui.contract.view.IUpdateTurnUI;

public class TurnViewerController extends BaseController implements ITurnViewerController {

	private String nameFilter;
	private Turn selectedTurn;
	private ITurnReader turnReader;
	private ITurnWriter turnWriter;
	
	private INewTurnUI newTurnUI;
	private IUpdateTurnUI updateTurnUI;

	public TurnViewerController(ITurnReader turnReader, ITurnWriter turnWriter, INewTurnUI newTurnUI, IUpdateTurnUI updateTurnUI) {
		this.turnReader = turnReader;
		this.turnWriter = turnWriter;
		this.newTurnUI = newTurnUI;
		this.updateTurnUI = updateTurnUI;
	}
	
	@Override
	public void setNameFilter(String nameFilter) {
		String old = this.nameFilter;
		
		this.nameFilter = nameFilter;
		
		super.notifyOfPropertyChange("nameFilter", old, nameFilter);
		super.notifyOfPropertyChange("turns", null, getTurns());
	}

	@Override
	public String getNameFilter() {
		return this.nameFilter;
	}

	@Override
	public void setSelectedTurn(Turn turn) {
		Turn old = this.selectedTurn;
		
		this.selectedTurn = turn;

		super.notifyOfPropertyChange("selectedTurn", old, turn);
		super.notifyOfPropertyChange("canUpdateTurn", old, turn);
		super.notifyOfPropertyChange("canDeleteTurn", old, turn);
	}

	@Override
	public Turn getSelectedTurn() {
		return this.selectedTurn;
	}

	@Override
	public List<Turn> getTurns() {
		List<Turn> turns = this.turnReader.getTurns(this.nameFilter);
		
		return turns;
	}

	@Override
	public void showNewTurnUI() {
		this.newTurnUI.setVisible(true);
		
		super.notifyOfPropertyChange("turns", null, getTurns());
	}

	@Override
	public void showUpdateTurnUI() {
		this.updateTurnUI.setpKTurn(this.selectedTurn.getPKTurn());
		this.updateTurnUI.setVisible(true);
		
		super.notifyOfPropertyChange("turns", null, getTurns());
	}

	@Override
	public boolean getCanUpdateTurn() {
		return this.selectedTurn != null;
	}

	@Override
	public boolean getCanDeleteTurn() {
		return this.selectedTurn != null;
	}

	@Override
	public void deleteTurn() {
		this.turnWriter.deleteTurn(this.selectedTurn.getPKTurn());
		
		super.notifyOfPropertyChange("turns", null, getTurns());
	}
}
