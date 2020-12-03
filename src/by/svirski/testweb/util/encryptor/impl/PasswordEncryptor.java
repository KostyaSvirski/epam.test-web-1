package by.svirski.testweb.util.encryptor.impl;

import by.svirski.testweb.util.encryptor.CustomEncryptor;

/**
 * class represents encryptor for passwords
 * 
 * @author Kostya Svirski
 * @version 1.0
 */
public class PasswordEncryptor implements CustomEncryptor{

	public PasswordEncryptor() {
	}

	/**
	 * Overridden method {@link CustomEncryptor#encrypt(String)} to encrypt password
	 */
	@Override
	public int encrypt(String value) {
		int encryptedPass = value.hashCode();
		return encryptedPass;
	}

	
}
