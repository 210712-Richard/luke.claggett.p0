package store.data;

import java.util.ArrayList;
import java.util.List;


import store.items.Items;

public class ItemDAO {
	// DAO = Database Access Object
	// This is a class that is dedicated to accessing data from persistence.
	private static String filename = "items.dat";
	private static List<Items> items;
	
	static {
		DataSerializer<Items> ds = new DataSerializer<Items>();
		items = ds.readObjectsFromFile(filename);
		
		// Helper for myself. If no users exist in the users.dat file (first startup) than I should create a few
		if(items == null) {
			items = new ArrayList<Items>();
			items.add(new Items(items.size(), "To Kill a mockingbird", 20.00, 3));
			items.add(new Items(items.size(), "Atlas Shrugged", 40.00, 1));
			items.add(new Items(items.size(), "The Way of Kings", 10.00, 2));
			ds.writeObjectsToFile(items, filename);
		}
	}
	public void addBook(Items i) {
		DataSerializer<Items> ds = new DataSerializer<Items>();
		items = ds.readObjectsFromFile(filename);

		items.add(i);
		ds.writeObjectsToFile(items, filename);
	}
	public Items createBook(String bookName, Double price, Integer quantity) {

		if (bookName == null || bookName.equals("") || price == null || quantity == null) {
			return null;
		}
		Items newItem = new Items(items.size(), bookName, price, quantity); // Create the new user
		items.add(newItem); // Add the new user to the list

		return newItem;
	}
	
	public static Items getBook(Integer id) {
		
		for(Items item : items) {
			if(item.getID().equals(id)) {
				return item;
			}
		}
		
		return null;
	}
	
	public static List<Items> getBooks() {
		return items;
	}
	
	public void updateUser(Items item) {
		// due to us holding the entire list in memory, we will actually automatically update the user
		// in the list anytime we change the fields of the user object.
		// I'll leave this method as a placeholder for our Week 3 Database integration.
	}
	
	public void writeToFile() {
		new DataSerializer<Items>().writeObjectsToFile(items, filename);

	}
	
	public static Boolean checkBookName(String bookName) {

		if (bookName == null || bookName.equals("")) { // If the username entered was null or blank.

			return false;
		}
		for (Items item : items) { // Iterate through the list of users.
			if (bookName.equals(item.getProductName())) { // If the username has been taken

				return false;
			}
		}

		return true; // Means the username is unique
	}

}
