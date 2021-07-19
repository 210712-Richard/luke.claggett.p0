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
		//log.trace("App has entered createUser.");
		//log.debug("createUser Parameters: username: " + username + ", password: " + password + ", email: " + email
		//		+ ", type: " + type);
		if (username == null || username.isBlank() || fName == null || fName.isBlank() || lName == null
				|| lName.isBlank()) {
			return null;
		}
		Users newUser = new Users(users.size(), username, fName, lName, type); // Create the new user
		//log.debug("newUser has been created: " + newUser);
		users.add(newUser); // Add the new user to the list
		//log.debug("New user was added to the list: " + users.contains(newUser));
		//log.trace("App is now leaving createUser.");
		//log.debug("createUser is returning User: " + newUser);
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
		//new Serializer<Users>().writeObjectsToFile(users, filename); // Call the serializer to write to the file

	}
	
	public Boolean checkUsername(String username) {
		//log.trace("App has entered checkUsername.");
		//log.debug("checkUsername Parameters: username: " + username);
		if (username == null || username.isBlank()) { // If the username entered was null or blank.
			//log.warn("User entered a null or blank username");
			//log.trace("App is leaving checkUsername.");
			//log.debug("App is returning Boolean: " + false);
			return false;
		}
		for (Users user : users) { // Iterate through the list of users.
			if (username.equals(user.getUsername())) { // If the username has been taken
				//log.debug(username + " has been found: " + user.getUsername());
				//log.trace("App is now leaving checkUsername.");
				//log.debug("checkUsername is returning Boolean: " + false);
				return false;
			}
		}
		//log.trace("App is now leaving checkUsername.");
		//log.debug("checkUsername is returning Boolean: " + true);
		return true; // Means the username is unique
	}

}
