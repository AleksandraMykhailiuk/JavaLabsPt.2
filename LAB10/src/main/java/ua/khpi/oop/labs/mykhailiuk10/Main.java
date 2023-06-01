package ua.khpi.oop.labs.mykhailiuk10;

/**
 * 10 laboratory work
 * Processing parameterized containers
 * Create a generic autoplay and generaic methods for classes that used before.
 * @version 14 Mar 2023
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
