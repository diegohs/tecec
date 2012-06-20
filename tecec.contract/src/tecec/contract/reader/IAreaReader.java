package tecec.contract.reader;

import java.util.List;

import tecec.dto.Area;

public interface IAreaReader {
	List<Area> getAreas(String nameFilter);
	Area getAreaByPK(String pKCourse);
	boolean doesAreaHaveMonographies(String pKArea);
}
