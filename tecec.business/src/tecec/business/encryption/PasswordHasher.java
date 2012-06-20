package tecec.business.encryption;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.util.UUID;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import tecec.contract.encryption.IPasswordHasher;

public class PasswordHasher implements IPasswordHasher {
	private final String HASHING_SALT = "8f861c8c-d4af-4e";

	@Override
	public String hash(String plainPassword) throws Exception {
		KeySpec spec = new PBEKeySpec(plainPassword.toCharArray(),
				HASHING_SALT.getBytes(), 2048, 160);

		SecretKeyFactory keyFactory;

		try {
			keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		} catch (NoSuchAlgorithmException e) {
			throw e;
		}

		byte[] hash = keyFactory.generateSecret(spec).getEncoded();

		String hashString = new BigInteger(1, hash).toString(16);
		String saltString = UUID.randomUUID().toString().replace("-", "0");

		return saltString + hashString;
	}

	@Override
	public boolean arePasswordsEqual(String plainPassword, String hashedPassword)
			throws Exception {
		String hash = hashedPassword.substring(36, hashedPassword.length());

		String newHashedPassword = hash(plainPassword);

		String newHash = newHashedPassword.substring(36, hashedPassword.length());

		return hash.equals(newHash);
	}
}
