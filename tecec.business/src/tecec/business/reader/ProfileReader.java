package tecec.business.reader;

import java.util.List;

import tecec.contract.repository.IProfileRepository;
import tecec.dto.Profile;

public class ProfileReader implements tecec.contract.reader.IProfileReader {
	private tecec.contract.repository.IProfileRepository profileRepository;

	public ProfileReader(IProfileRepository profileRepository) {
		this.profileRepository = profileRepository;
	}

	@Override
	public List<Profile> getProfiles(String nameFilter) {
		return profileRepository.getProfiles(nameFilter);
	}

	@Override
	public Profile getProfileByPK(String pKProfile) {
		return profileRepository.getProfileByPK(pKProfile);
	}

}
