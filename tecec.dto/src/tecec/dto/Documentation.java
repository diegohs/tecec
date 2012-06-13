package tecec.dto;

public class Documentation {
	private String pKDocumentation;
	private String fileName;
	private byte[] data;
	
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getpKDocumentation() {
		return pKDocumentation;
	}
	public void setpKDocumentation(String pKDocumentation) {
		this.pKDocumentation = pKDocumentation;
	}
	
	
	
}
