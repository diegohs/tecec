package tecec.business.reader;

import java.util.List;

import tecec.contract.reader.IStatusReader;
import tecec.contract.repository.IStatusRepository;
import tecec.dto.Status;

public class StatusReader implements IStatusReader {
	
	private IStatusRepository statusRepository;
	
	public StatusReader (IStatusRepository statusRepository) {
		this.statusRepository = statusRepository;
	}
	

	@Override
	public List<Status> getStatus(String nameFilter) {
		return statusRepository.getStatus(nameFilter);
	}

	@Override
	public Status getStatusByPK(String pKStatus) {
		return statusRepository.getStatusByPK(pKStatus);
	}	
}
