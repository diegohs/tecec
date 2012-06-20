package tecec.dto;

public class Profile {
	private String pKProfile;
	private String name;	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {		
		this.name = name;
	}

	public String getpKProfile() {
		return pKProfile;
	}

	public void setpKProfile(String pKProfile) {
		this.pKProfile = pKProfile;
	}

	@Override
	public String toString() {
		return this.getName();
	}
}
