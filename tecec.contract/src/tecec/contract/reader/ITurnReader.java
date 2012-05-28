package tecec.contract.reader;

import java.util.List;

import tecec.dto.Turn;

public interface ITurnReader {
	List <Turn> getTurns (String nameFilter);
	
	Turn getTurnByPK(String pKTurn);
}
