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
				//log.trace("User was not found. Username: " + username);
				System.out.println("Login details did not match, please try again.\n");
			} else if (activeUser.getType() == UserType.CUSTOMER) { // User is a customer
				//log.trace("User is a customer. Username " + username);
				System.out.println("Customer");
				//openCustomerMenu();
				break;
			} else if (activeUser.getType() == UserType.EMPLOYEE) { // User is a manager
				//log.trace("User is a manager. Username " + username);
				//openManagerMenu();
				System.out.println("Employee");
				break;
			}
			 else { // Somehow, the user is none of those things.
				//log.error("Problem with the user account");
				System.out.println("There is a problem with your account. Please contact an administrator.");
			}
			break;
			

//			if (userInput.isBlank()) {
//				JOptionPane.showMessageDialog(null, "Please enter a valid username your username cannot be blank.ttrt", "Project 0 Book Store", JOptionPane.ERROR_MESSAGE);
//
//				continue;
//			}
//			else {
//				break;
//				
//			}
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

			
			/*
			 * case 1:
				// Login
				// Have the user input their username and password
				System.out.println("Please enter your username:");
				String username = scanner.nextLine();
				log.debug("User entered username " + username);
				System.out.println("Please enter your password");
				String password = scanner.nextLine();
				log.debug("User entered password " + password);

				System.out.println("Logging you in...");
				log.trace("Going into UserService login");
				activeUser = us.login(username, password); // Will check if the user exists.

				if (activeUser == null) { // If the user was not found.
					log.trace("User was not found. Username: " + username);
					System.out.println("Login details did not match, please try again.\n");
				} else if (activeUser.isActive() == false) { // If the user has been deactivated.
					log.trace("User attempted to login with deactivated account. Username: " + username);
					System.out.println("Your account has been deactivated. Please contact an administrator.\n");
					break main;
				} else if (activeUser.getAccountType() == AccountType.CUSTOMER) { // User is a customer
					log.trace("User is a customer. Username " + username);
					openCustomerMenu();
				} else if (activeUser.getAccountType() == AccountType.MANAGER) { // User is a manager
					log.trace("User is a manager. Username " + username);
					openManagerMenu();
				} else if (activeUser.getAccountType() == AccountType.ADMINISTRATOR) { // User is a admin
					log.trace("User is an administrator. Username " + username);
					openAdminMenu();
				} else { // Somehow, the user is none of those things.
					log.error("Problem with the user account");
					System.out.println("There is a problem with your account. Please contact an administrator.");
				}
				break;
			case 2:
				// Register for a customer account
				activeUser = createAccount(AccountType.CUSTOMER);
				if (activeUser == null) {
					System.out.println("There was a problem setting up your account. Please try again.\n");
				} else {
					openCustomerMenu();
				}
				break;
			case 3:
				// Close
				log.trace("User is closing the application.");
				System.out.println("Have a nice day!");
				System.out.println("Closing Storefront...");
				break main;
			default:
				// Error
				log.trace("User tried to enter an invalid input.");
				System.out.println("Invalid Input. Please try again.\n");
				break;
			 */
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
	//log.trace(((activeUser == null) ? "User" : activeUser.getUsername()) + " is entering createAccount.");
	//log.debug("In createAccount with parameters type = " + type);
	String newUsername;
	do {
		newUsername = JOptionPane.showInputDialog("Please enter your username.");
		//log.debug(((activeUser == null) ? "User" : activeUser.getUsername()) + " entered newUsername: "
		//		+ newUsername);
		// Checks to see if the username has been taken or not.
		// False means that it is in use already, true otherwise.
		
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
	//log.debug(((activeUser == null) ? "User" : activeUser.getUsername()) + " entered newPassword: " + newPassword);
	System.out.println("Please enter an email address:");
	//log.debug(((activeUser == null) ? "User" : activeUser.getUsername()) + " entered newEmail: " + newEmail);

	System.out.println("Registering your account...");

	// This will create the new customer account.

	//log.trace(((activeUser == null) ? "User" : activeUser.getUsername()) + " is leaving createAccount.");
	Users u = us.register(newUsername, fName, lName, UserType.CUSTOMER);
	//log.trace(((activeUser == null) ? "User" : activeUser.getUsername()) + " is exiting createAccount.");
	//log.debug("Returning User: " + u);
	return u;
	}
}

