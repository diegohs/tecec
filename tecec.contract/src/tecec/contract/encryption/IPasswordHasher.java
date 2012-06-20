package tecec.contract.encryption;

public interface IPasswordHasher {
	String hash(String plainPassword) throws Exception;
	boolean arePasswordsEqual(String plainPassword, String hashedPassword) throws Exception;
}
