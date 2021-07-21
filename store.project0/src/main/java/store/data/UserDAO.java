package store.data;

import java.util.ArrayList;
import java.util.List;


import store.user.Users;
import store.user.UserType;

public class UserDAO {
	// DAO = Database Access Object
	// This is a class that is dedicated to accessing data from persistence.
	private static String filename = "users.dat";
	private static List<Users> users;
	
	static {
		DataSerializer<Users> ds = new DataSerializer<Users>();
		users = ds.readObjectsFromFile(filename);
		
		// Helper for myself. If no users exist in the users.dat file (first startup) than I should create a few
		if(users == null) {
			users = new ArrayList<Users>();
			users.add(new Users(users.size(), "SarahR", "Sarah", "Rhode", UserType.CUSTOMER));
			users.add(new Users(users.size(), "Richard", "Richard", "Orr", UserType.CUSTOMER));
			users.add(new Users(users.size(), "HAndrew", "Andrew", "Hofer", UserType.CUSTOMER));
			Users u = new Users(users.size(), "Luke", "Luke", "Claggett", UserType.CUSTOMER);
			u.setType(UserType.EMPLOYEE);
			users.add(u);
			ds.writeObjectsToFile(users, filename);
		}
	}
	public void addUser(Users u) {
		DataSerializer<Users> ds = new DataSerializer<Users>();
		users = ds.readObjectsFromFile(filename);

		users.add(u);
		ds.writeObjectsToFile(users, filename);
	}
	public Users createUser(String username, String fName, String lName, UserType type) {

		if (username == null || username.equals("") || fName == null || fName.equals("") || lName == null
				|| lName.equals("")) {
			return null;
		}
		Users newUser = new Users(users.size(), username, fName, lName, type); // Create the new user
		users.add(newUser); // Add the new user to the list

		return newUser;
	}
	
	public Users getUser(String username) {
		
		for(Users user : users) {
			if(user.getUsername().equals(username)) {
				return user;
			}
		}
		
		return null;
	}
	
	public void updateUser(Users user) {
		// due to us holding the entire list in memory, we will actually automatically update the user
		// in the list anytime we change the fields of the user object.
		// I'll leave this method as a placeholder for our Week 3 Database integration.
	}
	
	public void writeToFile() {
		new DataSerializer<Users>().writeObjectsToFile(users, filename);

	}
	
	public Boolean checkUsername(String username) {

		if (username == null || username.equals("")) { // If the username entered was null or blank.

			return false;
		}
		for (Users user : users) { // Iterate through the list of users.
			if (username.equals(user.getUsername())) { // If the username has been taken

				return false;
			}
		}

		return true; // Means the username is unique
	}

}
