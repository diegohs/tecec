package tecec.contract.reader;

import java.util.List;

import tecec.dto.Profile;

public interface IProfileReader {
	List<Profile> getProfiles(String nameFilter);
	Profile getProfileByPK(String pKProfile);
}
