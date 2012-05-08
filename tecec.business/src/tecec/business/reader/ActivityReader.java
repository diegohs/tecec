package tecec.business.reader;

import java.util.List;

import tecec.contract.reader.IActivityReader;
import tecec.contract.repository.IActivityRepository;
import tecec.dto.Activity;

public class ActivityReader implements IActivityReader {

	private tecec.contract.repository.IActivityRepository activityRepository;
	
	public ActivityReader(IActivityRepository activityRepository) {
		this.activityRepository = activityRepository;
	}

	@Override
	public List<Activity> getActivities(String titleFilter) {
		return this.activityRepository.getActivities(titleFilter);
	}

	@Override
	public Activity getActivityByPK(String pKActivity) {
		return this.activityRepository.getActivityByPK(pKActivity);
	}

}
