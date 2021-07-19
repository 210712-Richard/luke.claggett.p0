package store.user.services;

import store.data.UserDAO;
import store.user.UserType;
import store.user.Users;

public class UserService {
	
	public UserDAO ud = new UserDAO();
	
	public Users login(String name) {
		Users u = ud.getUser(name);
		ud.writeToFile();
		return u;
	}
	
	public Users register(String username, String fName, String lName, UserType type) {
		Users u = ud.createUser(username, fName, lName, type); //Sends the parameters to the DAO to create a new user
		ud.writeToFile(); //Save the new user to file.

		return u; //Return the new user object.
	}
	
	public Boolean isUsernameUnique(String username) {

		boolean isUnique = ud.checkUsername(username); //Check the DAO to see if username is taken
		ud.writeToFile(); //Save the file
		
		return isUnique;
	}
}