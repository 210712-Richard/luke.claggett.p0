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
		//log.trace("App has returned to register.");
		ud.writeToFile(); //Save the new user to file.
		//log.trace("App has returned to register.");
		//log.trace("App is exiting register.");
		//log.debug("register is returning User: " + u);
		return u; //Return the new user object.
	}
	
	public Boolean isUsernameUnique(String username) {
		//log.trace("App has entered isUsernameUnique.");
		//log.debug("isUsernameUnique Parameters: username: " + username);
		boolean isUnique = ud.checkUsername(username); //Check the DAO to see if username is taken
		//log.trace("App has returned to isUsernameUnique.");
		ud.writeToFile(); //Save the file
		//log.trace("App has returned to isUsernameUnique.");
		//log.trace("App is exiting isUsernameUnique.");
		//log.debug("register is returning boolean: " + isUnique);
		return isUnique;
	}
}