package tecec.contract.reader;


import tecec.dto.HandIn;

public interface IHandInReader {
	HandIn getHandInByActivityAndMonograph(String pKActivity, String pKMonograph, boolean getOnTime, boolean getLate);
}
