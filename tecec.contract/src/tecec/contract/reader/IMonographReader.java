package tecec.contract.reader;

import java.util.List;
import tecec.dto.Monograph;

public interface IMonographReader {
	List<Monograph> getMonograph(String nameFilter);
	Monograph getMonographByPK(String pKMonograph);
}
