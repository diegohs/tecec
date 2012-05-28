package tecec.business.reader;

import java.util.List;

import tecec.contract.reader.ITurnReader;

import tecec.contract.repository.ITurnRepository;

import tecec.dto.Turn;

public class TurnReader implements ITurnReader {
	
	private ITurnRepository turnRepository;
	
	public TurnReader (ITurnRepository turnRepository) {
		this.turnRepository = turnRepository;
	}

	@Override
	public List<Turn> getTurns(String nameFilter) {
		return turnRepository.getTurns(nameFilter);
	}

	@Override
	public Turn getTurnByPK(String pKTurn) {
		return turnRepository.getTurnByPK(pKTurn);
	}
	


}
