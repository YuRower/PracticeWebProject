package ua.shvidkoy.webproject.utill;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordHasher {
	private PasswordHasher() {
	}

	public static String getHash(String plainPassword) {
		return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
	}

	public static boolean checkPassword(String candidatePassword, String hash) {
		return BCrypt.checkpw(candidatePassword, hash);
	}

	public static void main(String[] args) {
		String password = "password";
		String hash = getHash(password);
		System.out.println(hash + " " + hash.length());
		hash = getHash(password);
		System.out.println(hash + " " + hash.length());
		hash = getHash(password);
		System.out.println(hash + " " + hash.length());
		System.out.println(checkPassword(password, hash));
	}
}
