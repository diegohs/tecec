package tecec.contract.repository;

import java.util.List;
import tecec.dto.Status;

public interface IStatusRepository {
	void insertStatus (Status status);
	void updateStatus (Status status);
	void deleteStatus (String pKStatus);
	Status getStatusByDescription (String description);
	Status getStatusByPK (String pKStatus);
	List <Status> getStatus (String nameFilter);
}
