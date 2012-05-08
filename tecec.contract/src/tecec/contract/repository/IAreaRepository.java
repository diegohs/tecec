package tecec.contract.repository;

import java.util.List;

public interface IAreaRepository {
	void insertArea(tecec.dto.Area pArea);
	void updateArea(tecec.dto.Area pArea);
	void deleteArea(String pKArea);
	tecec.dto.Area getAreaByNameAndMainArea(String pName, String pMainArea);
	tecec.dto.Area getAreaByPK(String pKArea);
	List<tecec.dto.Area> getAreas(String nameFilter);
	List<tecec.dto.Area> getSubAreas(String pKArea);
}
