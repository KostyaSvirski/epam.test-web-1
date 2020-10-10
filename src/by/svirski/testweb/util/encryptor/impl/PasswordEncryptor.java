package by.svirski.testweb.util.encryptor.impl;

import by.svirski.testweb.util.encryptor.CustomEncryptor;

public class PasswordEncryptor implements CustomEncryptor{

	public PasswordEncryptor() {
	}

	@Override
	public int encrypt(String value) {
		int encryptedPass = value.hashCode();
		return encryptedPass;
	}

	
}
