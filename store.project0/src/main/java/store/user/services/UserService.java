package store.user.services;

import store.data.UserDAO;
import store.user.UserType;
import store.user.Users;
import store.items.Items;

import java.util.List;

import store.data.ItemDAO;

public class UserService {
	
	public UserDAO ud = new UserDAO();
	public ItemDAO id = new ItemDAO();
	
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
	
	public Items registerBook(String bookName, Double price, Integer quantity) {
		Items i = id.createBook(bookName, price, quantity); //Sends the parameters to the DAO to create a new user
		id.writeToFile(); //Save the new user to file.

		return i; //Return the new user object.
	}
	
	public Boolean isUsernameUnique(String username) {

		boolean isUnique = ud.checkUsername(username); //Check the DAO to see if username is taken
		ud.writeToFile(); //Save the file
		
		return isUnique;
	}
	
	public List<Items> getBooks() {
		List<Items> retList = ItemDAO.getBooks();
		id.writeToFile();
		
		return retList;
	}
}