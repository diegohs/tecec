package tecec.business.writer;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.repository.ITurnRepository;
import tecec.contract.writer.ITurnWriter;
import tecec.dto.Turn;

public class TurnWriter implements ITurnWriter {
	
	private ITurnRepository turnRepository;
	
	public TurnWriter (ITurnRepository turnRepository) {
		if (turnRepository == null)
			throw new IllegalArgumentException ("turnRepository");
		
		this.turnRepository = turnRepository;
	}
	
	@Override
	public RuleViolation getCreationViolation(String name) {
		
		if (name == null || name.trim().isEmpty()) {
			return new RuleViolation ("O nome do turno deve ser preenchido");			
		} else {
			if (name.length() > 128) {
				return new RuleViolation ("O nome do turno deve ser menor que 128 caracteres.");
			}
		}
		
		Turn turn = turnRepository.getTurnByName(name);
		
		if (turn != null) {
			return new RuleViolation ("Já existe outro turno cadastrado com o mesmo nome.");
		}
		
		return null;

	}

	@Override
	public RuleViolation getUpdateViolation(String pKTurn, String newName) {
		
		Turn turn = this.turnRepository.getTurnByPK(pKTurn);
		
		if (turn == null)
			return new RuleViolation ("O nome do turno selecionado não existe no banco de dados.");
		
		if (newName == null || newName.trim().isEmpty())
			return new RuleViolation ("O nome do turno deve ser preenchido.");
		
		turn  = this.turnRepository.getTurnByName(newName);
		
		if (turn != null){
			if (!turn.getPKTurn().equals(pKTurn)) {
				return new RuleViolation ("Já existe outro turno cadastrado com este nome.");
				
			}
		}
		return null;
	}

	@Override
	public void createTurn(String name) throws RuleViolationException {
		RuleViolation violation = getCreationViolation (name);
		
		if (violation != null)
			throw new RuleViolationException (violation);
		
		Turn turn = new Turn ();
		turn.setName(name);
		this.turnRepository.insertTurn(turn);
		
	}

	@Override
	public void updateTurn(String pKTurn, String newName)
			throws RuleViolationException {
		RuleViolation violation = getUpdateViolation (pKTurn, newName);
		
		if (violation != null)
			throw new RuleViolationException (violation);
		
		Turn turn = new Turn ();
		turn.setPKTurn(pKTurn);
		turn.setName(newName);
		
		this.turnRepository.updateTurn(turn);
		
	}

	@Override
	public void deleteTurn(String pKTurn) {
		this.turnRepository.deleteTurn(pKTurn);
		
	}
	
}
