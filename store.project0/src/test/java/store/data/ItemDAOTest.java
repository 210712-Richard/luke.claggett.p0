package store.data;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import store.items.Items;
import util.MockitoHelper;

public class ItemDAOTest {

	private ItemDAO dao;
	private static Items item;
	
	private ArrayList<Items> inv; // Inventory list to make sure methods are called.
	private static MockitoHelper<ArrayList> mock; // Mock to verify methods. Note: Mockito does not work well with
													// static fields.

	@BeforeAll
	public static void beforeStart() {
		// The default book added to the inventory.

		mock = new MockitoHelper<ArrayList>(ArrayList.class);
	}

	@BeforeEach
	public void beforeTests() {
		dao = new ItemDAO();
		item = new Items(0, "Halftime", 40.00, 5);
	}
	
	@Test
	public void testGetItems() {
		// Make sure that returns a list of books with onlyInStock is true.
		List<Items> retList = dao.getBooks();
	}
	
	@Test
	public void testAddItem() {
		// Create the Book object and add it to the list.
		Items i = new Items(66, "TestAddBook", 20.00, 4);
		Items isAdded = dao.addBook(i);
		
		assertTrue(dao.getBooks().contains(isAdded), "Assert that the item was added to inventory.");

	}
}

