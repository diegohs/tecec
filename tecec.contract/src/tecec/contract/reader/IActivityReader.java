package tecec.contract.reader;

import java.util.List;

import tecec.dto.Activity;

public interface IActivityReader {
	List<Activity> getActivities(String titleFilter);
	Activity getActivityByPK(String pKActivity);
}
