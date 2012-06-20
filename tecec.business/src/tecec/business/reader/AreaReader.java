package tecec.business.reader;

import java.util.List;

import tecec.contract.repository.IAreaRepository;
import tecec.dto.Area;

public class AreaReader implements tecec.contract.reader.IAreaReader {

	private tecec.contract.repository.IAreaRepository areaRepository;

	public AreaReader(IAreaRepository areaRepository) {		
		this.areaRepository = areaRepository;
	}
	
	@Override
	public List<Area> getAreas(String nameFilter) {
		return this.areaRepository.getAreas(nameFilter);
	}
	
	@Override
	public Area getAreaByPK(String pKArea) {
		return this.areaRepository.getAreaByPK(pKArea); 
	}

	@Override
	public boolean doesAreaHaveMonographies(String pKArea) {
		return this.areaRepository.doesAreaHaveMonographies(pKArea);
	}

}
