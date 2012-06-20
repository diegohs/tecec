package tecec.contract.writer;

import java.io.IOException;

public interface IHandInWriter {
	void handIn(String pKMonograph, String pKActivity, String file) throws IOException;
	void updateHandIn(String pKHandIn, String grade, String remark);
	void deleteHandIn(String pKHandIn);
}
