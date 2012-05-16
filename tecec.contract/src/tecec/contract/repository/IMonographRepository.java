package tecec.contract.repository;

import java.util.List;
import tecec.dto.Monograph;

public interface IMonographRepository {
	void insertMonograph (Monograph monograph);
	void updateMonograph (Monograph monograph);
	void deleteMonograph (String pKMonograph);
	Monograph getMonographByTitle (String title);
	Monograph getMonographByPK (String pKMonograph);
	List<Monograph> getMonograph (String nameFilter);
}
