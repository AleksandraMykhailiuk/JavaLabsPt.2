package ua.khpi.opp.labs.mykhailiuk14;

/**
 * 14 laboratory work
 * Multithreading. Efficiency of use
 * Add non-synchronized multi threading environment and time measurement for lab 13
 * @version 19 Apr 2023
 * @author Oleksandra Mykhailiuk
 */
public class Main {
	/**
	 * The point of entrance
	 */
	public static void main(String[] args) {
		AddressBookApplication addressBookApplication = AddressBookApplication.getInstance();
		addressBookApplication.start();
	}
}
