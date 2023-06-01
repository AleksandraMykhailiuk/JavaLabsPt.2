package ua.khpi.opp.labs.mykhailiuk11;

/**
 * 11 laboratory work
 * Regular expressions. Data validation
 * Create a validation via patterns and file reading to lab 10
 * @version 21 Mar 2023
 * @author Oleksandra Mykhailiuk
 */
public class Main {
	/**
	 * The point of entrance
	 */
	public static void main(String[] args) {
		boolean autoMode = false;
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-a") || args[i].equals("-auto")) {
				autoMode = true;
			}
		}
		AddressBookApplication addressBookApplication = AddressBookApplication.getInstance(autoMode);
		addressBookApplication.start();
	}
}
