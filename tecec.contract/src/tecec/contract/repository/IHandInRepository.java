package tecec.contract.repository;

import tecec.dto.HandIn;

public interface IHandInRepository {
	void insertHandIn(HandIn handIn);
	void deleteHandIn(String pKHandIn);
	void updateHandIn(String pKHandIn, String grade, String remark);
	
	HandIn getHandInByActivityAndMonograph(String pKActivity, String pKMonograph);
}
