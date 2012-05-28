package tecec.contract.repository;

import java.util.List;

import tecec.dto.Turn;



public interface ITurnRepository {	
	
	void insertTurn (Turn turn);
	
	void updateTurn (Turn turn);
	
	void deleteTurn (String pKTurn);
	
	Turn getTurnByName (String name);
	
	Turn getTurnByPK (String pKTurn);
	
	List <Turn> getTurns (String nameFilter);


}
