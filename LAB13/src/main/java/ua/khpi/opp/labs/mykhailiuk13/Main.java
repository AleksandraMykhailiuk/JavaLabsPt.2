package ua.khpi.opp.labs.mykhailiuk13;

/**
 * 13 laboratory work
 * Parallel execution. Multithreading
 * Create multi threading environment for lab 12
 * @version 10 Apr 2023
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
