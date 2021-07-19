package store.menu;
import javax.swing.JOptionPane;

import store.data.UserDAO;
import store.user.UserType;
import store.user.Users;
import store.user.services.UserService;



public class Menu {
	private static Users activeUser = null; // The current logged in user
	private static UserService us = new UserService(); // Used to modify user states


private Users loggedUser = null;
public void start() {
	JOptionPane.showMessageDialog(null, "Welcome to Project 0 Book store!", "Project 0 Book Store", JOptionPane.INFORMATION_MESSAGE);
	mainLoop: while(true) {
		switch(startMenu()) {
		case 1:
			
			JOptionPane.showMessageDialog(null, "Login", "Project 0 Book Store", JOptionPane.INFORMATION_MESSAGE);
			String userInput = JOptionPane.showInputDialog("Please enter your username.");
			
			activeUser = us.login(userInput); // Will check if the user exists.

			if (activeUser == null) { // If the user was not found.
				System.out.println("Login details did not match, please try again.\n");
			} else if (activeUser.getType() == UserType.CUSTOMER) { // User is a customer
				System.out.println("Customer");
				break;
			} else if (activeUser.getType() == UserType.EMPLOYEE) { // User is a manager

				System.out.println("Employee");
				break;
			}
			 else { // Somehow, the user is none of those things.
				System.out.println("There is a problem with your account. Please contact an administrator.");
			}
			break;
			
		case 2:
			JOptionPane.showMessageDialog(null, "Register", "Project 0 Book Store", JOptionPane.INFORMATION_MESSAGE);
			activeUser = createAccount(UserType.CUSTOMER);
			if (activeUser == null) {
				System.out.println("There was a problem setting up your account. Please try again.\n");
			} else {
				//openCustomerMenu();
				break;
			}
			break;
		case 3:
			JOptionPane.showMessageDialog(null, "Goodbye!", "Project 0 Book Store", JOptionPane.INFORMATION_MESSAGE);

			System.exit(0);
			break;
		case 4:
			JOptionPane.showMessageDialog(null, "Please enter a valid number between 1 and 3", "Project 0 Book Store", JOptionPane.ERROR_MESSAGE);

		}
	}
}
private int startMenu() {
	
	try {
	Integer userChoice = Integer.parseInt(JOptionPane.showInputDialog("What would you like to do today?\n "
																		+ "1. Log into your account.\n "
																		+ "2. Register a new account. \n "
																		+ "3. Leave our book store."));
	if (userChoice <1 || userChoice >3) {
		return 4;
	}
	else {
	return userChoice;
	}
}
	catch (Exception Ex) {
		JOptionPane.showMessageDialog(null, "Please enter a valid number between 1 and 3", "Project 0 Book Store", JOptionPane.ERROR_MESSAGE);
		
	}
	return (Integer) null;
}

private static Users createAccount(UserType type) {

	String newUsername;
	do {
		newUsername = JOptionPane.showInputDialog("Please enter your username.");

		
		if (!us.isUsernameUnique(newUsername)) {
			if (!us.isUsernameUnique(newUsername) && (newUsername.isBlank())) {
				System.out.println("NO NULL");
			}
			else if (!newUsername.isBlank() ) {
			System.out.println(newUsername + " is already is use. Please try again.\n");
		}
	}
} while (!us.isUsernameUnique(newUsername));

	String fName = JOptionPane.showInputDialog("Please enter your first name.");
	String lName = JOptionPane.showInputDialog("Please enter your last name.");


	System.out.println("Please enter a password:");
	System.out.println("Please enter an email address:");

	System.out.println("Registering your account...");

	// This will create the new customer account.

	Users u = us.register(newUsername, fName, lName, UserType.CUSTOMER);

	return u;
	}
}

