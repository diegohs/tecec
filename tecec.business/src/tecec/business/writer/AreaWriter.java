package tecec.business.writer;

import java.util.List;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.repository.IAreaRepository;
import tecec.dto.Area;

public class AreaWriter implements tecec.contract.writer.IAreaWriter {

	private tecec.contract.repository.IAreaRepository areaRepository;

	public AreaWriter(IAreaRepository areaRepository) {
		this.areaRepository = areaRepository;
	}

	@Override
	public RuleViolation getCreationViolation(Area newArea) {
		if (newArea.getName() == null || newArea.getName().isEmpty()) {
			return new RuleViolation("O campo 'Nome' deve ser preenchido.");
		}

		Area area = this.areaRepository.getAreaByPK(newArea.getpKArea());

		if (area != null) {
			return new RuleViolation("Já existe uma area com a chave "
					+ area.getpKArea());
		}

		area = this.areaRepository.getAreaByNameAndMainArea(newArea.getName(),
				newArea.getpKArea());

		if (area != null) {
			return new RuleViolation("Já existe uma área com o nome "
					+ area.getName());
		}

		return null;
	}

	@Override
	public RuleViolation getUpdateViolation(Area newArea) {
		if (newArea.getName() == null || newArea.getName().isEmpty()) {
			return new RuleViolation("O campo 'Nome' deve ser preenchido.");
		}

		Area area = this.areaRepository.getAreaByNameAndMainArea(
				newArea.getName(), newArea.getpKArea());

		if (area != null && !area.getpKArea().equals(newArea.getpKArea())) {
			return new RuleViolation("Já existe uma área com o nome "
					+ area.getName());
		}

		if (newArea.getfKMainArea() != null
				&& !newArea.getfKMainArea().isEmpty()) {
			area = this.areaRepository.getAreaByPK(newArea.getfKMainArea());

			if (area == null) {
				return new RuleViolation(
						"Não foi possível encontrar a área com chave "
								+ newArea.getfKMainArea());
			}
		}

		return null;
	}

	@Override
	public void createArea(Area newArea) throws RuleViolationException {
		RuleViolation violation = getCreationViolation(newArea);

		if (violation != null) {
			throw new RuleViolationException(violation);
		}

		this.areaRepository.insertArea(newArea);
	}

	@Override
	public void updateArea(Area area) throws RuleViolationException {
		RuleViolation violation = getUpdateViolation(area);

		if (violation != null) {
			throw new RuleViolationException(violation);
		}

		this.areaRepository.updateArea(area);
	}

	@Override
	public void deleteArea(String pKArea) throws RuleViolationException {
		RuleViolation violation = getDeletionViolation(pKArea);
		
		if (violation != null) {
			throw new RuleViolationException(violation);
		}
		
		this.areaRepository.deleteArea(pKArea);
	}

	@Override
	public RuleViolation getDeletionViolation(String pKArea) {
		List<Area> subareas = this.areaRepository.getSubAreas(pKArea);
		
		if (subareas.size() > 0) {
			return new RuleViolation("Não é possível excluir uma área que possui subáreas cadastradas.");
		}
		
		return null;
	}

}
