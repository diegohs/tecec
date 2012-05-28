package tecec.ui.contract.control;

import java.util.List;

import tecec.dto.Turn;

public interface ITurnViewerController {
	void setNameFilter(String nameFilter);
	String getNameFilter();
	
	void setSelectedTurn(Turn turn);
	Turn getSelectedTurn();
	List<Turn> getTurns();
	
	void deleteTurn();
	
	boolean getCanUpdateTurn();
	boolean getCanDeleteTurn();
	
	void showNewTurnUI();
	void showUpdateTurnUI();
}
