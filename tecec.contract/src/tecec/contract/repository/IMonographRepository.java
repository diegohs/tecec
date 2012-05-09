package tecec.contract.repository;

import java.util.List;
import tecec.dto.Monograph;

public interface IMonographRepository {
	void insertMonograph(tecec.dto.Monograph monograph);
	void updateMonograph(tecec.dto.Monograph monograph);
	void deleteMonograph(String pKMonograph);
	
	tecec.dto.Monograph getMonographByPK(String pKMonograph);
	List<Monograph> getMonograph(String titleFilter);
}
