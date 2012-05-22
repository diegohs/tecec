package tecec.contract.repository;

import java.util.List;

import tecec.dto.Profile;

public interface IProfileRepository {
	void insertProfile(Profile profile);
	void updateProfile(Profile profile);
	void deleteProfile(String pKProfile);
	Profile getProfileByName(String name);
	Profile getProfileByPK(String pKProfile);
	List<Profile> getProfiles(String nameFilter);
}
