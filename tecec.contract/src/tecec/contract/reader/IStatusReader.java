package tecec.contract.reader;

import java.util.List;

import tecec.dto.Status;

public interface IStatusReader {
	List <Status> getStatus(String nameFilter);
	Status getStatusByPk (String pKStatus);
}
