package tecec.business.writer;

import java.util.Date;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.repository.IActivityRepository;
import tecec.contract.writer.IActivityWriter;
import tecec.dto.Activity;

public class ActivityWriter implements IActivityWriter {

	private tecec.contract.repository.IActivityRepository activityRepository;

	public ActivityWriter(IActivityRepository activityRepository) {
		this.activityRepository = activityRepository;
	}

	@Override
	public void insertActivity(Activity activity) throws RuleViolationException {
		RuleViolation violation = getInsertViolation(activity);

		if (violation != null) {
			throw new RuleViolationException(violation);
		}

		this.activityRepository.insertActivity(activity);
	}

	@Override
	public void updateActivity(Activity activity) throws RuleViolationException {
		RuleViolation violation = getUpdateViolation(activity);

		if (violation != null) {
			throw new RuleViolationException(violation);
		}

		this.activityRepository.updateActivity(activity);
	}

	@Override
	public void deleteActivity(String pKActivity) {
		this.activityRepository.deleteActivity(pKActivity);
	}

	@Override
	public RuleViolation getInsertViolation(Activity activity) {
		if (activity.getTitle() == null || activity.getTitle().isEmpty()) {
			return new RuleViolation(
					"O título da atividade deve ser preenchido.");
		}

		if (activity.getDueDate() == null) {
			return new RuleViolation(
					"A data de entrega da atividade deve ser uma data válida.");
		}

		return null;
	}

	@Override
	public RuleViolation getUpdateViolation(Activity activity) {
		if (activity.getTitle() == null || activity.getTitle().isEmpty()) {
			return new RuleViolation(
					"O título da atividade deve ser preenchido.");
		}

		if (activity.getDueDate() == null) {
			return new RuleViolation(
					"A data de entrega da atividade deve ser uma data válida.");
		}

		if (activity.getDueDate().before(new Date())) {
			return new RuleViolation(
					"Não é possível cadastrar uma atividade com data de entrega anterior à data atual.");
		}

		return null;
	}

}
