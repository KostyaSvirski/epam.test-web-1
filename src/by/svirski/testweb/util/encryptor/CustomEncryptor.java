package by.svirski.testweb.util.encryptor;

/**
 * interface represents encryptor
 * 
 * @author Kostya Svirski
 * @version 1.0
 */
public interface CustomEncryptor {
	
	/**
	 * method for process encryption 
	 * @param value - parameter that should be encrypted
	 * @return - encrypted value
	 */
	int encrypt(String value);
}
