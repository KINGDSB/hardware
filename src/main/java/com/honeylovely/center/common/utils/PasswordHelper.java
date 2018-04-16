package com.honeylovely.center.common.utils;


public class PasswordHelper {
	public static final String DEFAULT_ENCODING = "UTF-8";
	public static final String HASH_ALGORITHM = "md5";
	public static final int HASH_ITERATIONS = 2;

	public static String salt() {
		return salt(8);
	}

	public static String salt(int saltSize) {
		return Encodes.encodeHex(Digests.generateSalt(saltSize));
	}
	
	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	public static String entryptPassword(final String password, final String salt) {
		String passwd = Encodes.encodeHex(password.getBytes());
		byte[] hashPassword = Digests.md5(Encodes.decodeHex(passwd), Encodes.decodeHex(salt), HASH_ITERATIONS);
		return Encodes.encodeHex(hashPassword);
	}
	
	/**
	 * 验证原密码是否正确
	 * @return
	 */
	public static boolean checkPassword(final String password, final String oldPassword, final String salt){
		if(oldPassword.equals(entryptPassword(password, salt))) {
			return true;
		}else{
			return false;
		}
	}
}
