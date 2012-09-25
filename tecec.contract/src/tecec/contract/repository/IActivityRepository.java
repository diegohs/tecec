package tecec.contract.repository;

import java.util.List;

import tecec.dto.Activity;

public interface IActivityRepository {
	void insertActivity(Activity activity);
	void updateActivity(Activity activity);
	void deleteActivity(String pKActivity);
	
	List<Activity> getActivities(String titleFilter);
	List<Activity> getActivitiesByStage(String pKStage, String filter);
	Activity getActivityByPK(String pKActivity);
}
