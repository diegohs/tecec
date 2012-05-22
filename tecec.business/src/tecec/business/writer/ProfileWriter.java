package tecec.business.writer;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.repository.IProfileRepository;
import tecec.dto.Profile;

public class ProfileWriter implements tecec.contract.writer.IProfileWriter {

	private tecec.contract.repository.IProfileRepository profileRepository;

	public ProfileWriter(IProfileRepository profileRepository) {
		if (profileRepository == null) {
			throw new IllegalArgumentException("profileRepository");
		}

		this.profileRepository = profileRepository;
	}

	@Override
	public void createProfile(String name) throws RuleViolationException {
		RuleViolation violation = getCreationViolation(name);

		if (violation != null) {
			throw new RuleViolationException(violation);
		}

		Profile profile = new Profile();

		profile.setName(name);

		this.profileRepository.insertProfile(profile);
	}

	@Override
	public void updateProfile(String pKProfile, String newName) throws RuleViolationException {
		RuleViolation violation = getUpdateViolation(pKProfile, newName);
		
		if (violation != null) {
			throw new RuleViolationException(violation);
		}
		
		Profile profile = new Profile();
		
		profile.setpKProfile(pKProfile);
		profile.setName(newName);
		
		this.profileRepository.updateProfile(profile);
	}

	@Override
	public RuleViolation getCreationViolation(String name) {
		if (name == null || name.trim().isEmpty()) {
			return new RuleViolation("O nome do perfil deve ser preenchido.");
		} else {
			if (name.length() > 128) {
				return new RuleViolation(
						"O nome do perfil deve ser menor que 128 caracteres.");
			}
		}

		Profile profile = profileRepository.getProfileByName(name);

		if (profile != null) {
			return new RuleViolation(
					"Já existe outro perfil cadastrado com o mesmo nome.");
		}

		return null;
	}

	@Override
	public RuleViolation getUpdateViolation(String pKProfile, String newName) {
		Profile profile = this.profileRepository.getProfileByPK(pKProfile);

		if (profile == null) {
			return new RuleViolation(
					"O perfil selecionado não existe no banco de dados.");
		}
		
		if (newName == null || newName.trim().isEmpty()) {
			return new RuleViolation("O nome do perfil deve ser preenchido.");
		}

		profile = this.profileRepository.getProfileByName(newName);

		if (profile != null) {
			if (!profile.getpKProfile().equals(pKProfile)) {
				return new RuleViolation(
						"Já existe outro perfil cadastrado com este nome.");
			}
		}

		return null;
	}

	@Override
	public void deleteProfile(String pKProfile) {
		this.profileRepository.deleteProfile(pKProfile);
	}

}
