package store.menu;
import java.util.List;

import javax.swing.JOptionPane;

import store.data.UserDAO;
import store.user.UserType;
import store.user.Users;
import store.user.services.UserService;
import store.data.ItemDAO;
import store.items.Items;

public class Menu {
	private static Users activeUser = null; // The current logged in user
	private static UserService us = new UserService(); // Used to modify user states

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
				customerMenu();
				break;
			} else if (activeUser.getType() == UserType.EMPLOYEE) { // User is a manager

				System.out.println("Employee");
				employeeMenu();
				break;
			}
			 else { // Somehow, the user is none of those things.
				System.out.println("There is a problem with your account. Please contact an administrator.");
			}
			break;
			
		case 2:
			JOptionPane.showMessageDialog(null, "Register", "Project 0 Book Store", JOptionPane.INFORMATION_MESSAGE);
			createAccount(UserType.CUSTOMER);
			break;
		case 3:
			JOptionPane.showMessageDialog(null, "Goodbye!", "Project 0 Book Store", JOptionPane.INFORMATION_MESSAGE);
			break mainLoop;
		case 4:
			JOptionPane.showMessageDialog(null, "Please enter a valid number between 1 and 3", "Project 0 Book Store", JOptionPane.ERROR_MESSAGE);

		}
	}
}

private void employeeMenu() {
	employeeLoop: while (true) {
		
		switch(employeeInputMenu()) {
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
			JOptionPane.showMessageDialog(null, "New Book", "Project 0 Book Store", JOptionPane.INFORMATION_MESSAGE);
			createBook();
			break;
		case 3:
			JOptionPane.showMessageDialog(null, "Goodbye!", "Project 0 Book Store", JOptionPane.INFORMATION_MESSAGE);
			activeUser = null;
			break employeeLoop;
		case 4:
			JOptionPane.showMessageDialog(null, "Please enter a valid number between 1 and 3", "Project 0 Book Store", JOptionPane.ERROR_MESSAGE);
			break;
		}
	}
}

private void customerMenu() {
	customerLoop: while (true) {
		switch(customerInputMenu()) {
		case 1:
			//Get a list of items, and allow customer to purchase.
			List<Items> itemList = us.getBooks();
			String output = "";
			for (int i = 0; i < itemList.size(); i++) {
				Items item = itemList.get(i);
				output +=("\t" + "Name: " + item.getProductName() + " Price " + item.getProductPrice() + " Quanity " + item.getQuantity());
			}
			System.out.println(output);
			
			
//			JOptionPane.showMessageDialog(null, "Login", "Project 0 Book Store", JOptionPane.INFORMATION_MESSAGE);
//			String userInput = JOptionPane.showInputDialog("Please enter your username.");
//			
//			activeUser = us.login(userInput); // Will check if the user exists.
//
//			if (activeUser == null) { // If the user was not found.
//				System.out.println("Login details did not match, please try again.\n");
//			} else if (activeUser.getType() == UserType.CUSTOMER) { // User is a customer
//				System.out.println("Customer");
//				break;
//			} else if (activeUser.getType() == UserType.EMPLOYEE) { // User is a manager
//
//				System.out.println("Employee");
//				break;
//			}
//			 else { // Somehow, the user is none of those things.
//				System.out.println("There is a problem with your account. Please contact an administrator.");
//			}
			break;
			
		case 2:
			JOptionPane.showMessageDialog(null, "New Book", "Project 0 Book Store", JOptionPane.INFORMATION_MESSAGE);
			createBook();
			break;
		case 3:
			JOptionPane.showMessageDialog(null, "Goodbye!", "Project 0 Book Store", JOptionPane.INFORMATION_MESSAGE);
			break customerLoop;
		case 4:
			JOptionPane.showMessageDialog(null, "Please enter a valid number between 1 and 3", "Project 0 Book Store", JOptionPane.ERROR_MESSAGE);

		}
		
	}
	
}

private int customerInputMenu() {
	try {
		Integer userChoice = Integer.parseInt(JOptionPane.showInputDialog("What would you like to do today?\n "
																			+ "1. Purchase products.\n "
																			+ "2. Null. \n "
																			+ "3. Leave our customer menu."));
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
		
private int employeeInputMenu() {
	  try {
Integer userChoice = Integer.parseInt(JOptionPane.showInputDialog("What would you like to do today?\n "
															+ "1. Log into your account.\n "
															+ "2. Add a new book. \n "
															+ "3. Leave our employee menu. ONE"));
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

private int startMenu() {
	activeUser = null;
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
	return 4;
}

private static void createAccount(UserType type) {

	String newUsername;
	String fName;
	String lName;
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

	
	do {
		fName = JOptionPane.showInputDialog("Please enter your first name.");
			if (fName.isBlank()) {
				System.out.println("NO NULL fname");
			}
	}
 while (fName.isBlank());
	do {
		lName = JOptionPane.showInputDialog("Please enter your last name.");
			if (lName.isBlank()) {
				System.out.println("NO NULL lname");
			}
	}
 while (lName.isBlank());

	System.out.println("Please enter a password:");
	System.out.println("Please enter an email address:");

	System.out.println("Registering your account...");

	// This will create the new customer account.

	us.register(newUsername, fName, lName, UserType.CUSTOMER);
	}

private static Items createBook() {
	String newBookName;
	Double price = 0.00;
	Integer quantity = 0;
	do {
		newBookName = JOptionPane.showInputDialog("Please enter your book name.");

		
		if (!ItemDAO.checkBookName(newBookName)) {
			if (!us.isUsernameUnique(newBookName) && (newBookName.isBlank())) {
				System.out.println("NO NULL");
			}
			else if (!newBookName.isBlank() ) {
			System.out.println(newBookName + " is already is use. Please try again.\n");
		}
	}
} while (!ItemDAO.checkBookName(newBookName));

	while (true) {
		try {
			price = Double.parseDouble(JOptionPane.showInputDialog("Please enter the book price."));
			quantity = Integer.parseInt(JOptionPane.showInputDialog("Please enter the book quantity."));
			break;
		}
		catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Please enter a valid price", "Project 0 book store", JOptionPane.ERROR_MESSAGE);
		}
		
	}


	System.out.println("Please enter a password:");
	System.out.println("Please enter an email address:");

	System.out.println("Registering your book...");

	// This will create the new customer account.

	Items i = us.registerBook(newBookName, price, quantity);

	return i;
	}
}