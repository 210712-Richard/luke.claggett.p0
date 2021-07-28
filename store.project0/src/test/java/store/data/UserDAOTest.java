package store.data;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import store.user.UserType;
import store.user.Users;

public class UserDAOTest {

	private UserDAO dao;
	private static Users user;
	
	@BeforeAll
	public static void beforeStart() {
		user = new Users(0, "DefaultUser", "DefaultfName", "DefaultlName", UserType.CUSTOMER);
	}
	
	@BeforeEach
	public void beforeTests() {
		dao = new UserDAO();
		// The default user of the application.

	}
	
	@Test
	public void testGetUserReturnsValidUser() {
		// Returns a valid user given the same correct username and password
		// combination.
		Users retUser = dao.getUser(user.getUsername());
		assertTrue(user.equals(retUser), "Assert that the user returned is the correct User.");
	}
	
	@Test
	public void testGetUserReturnsInvalidUser() {
		// Returns null if the wrong username or password is used.
		Users invalidUser = dao.getUser("WrongUsername");
		assertNull("Assert that the wrong username results in a null User.", invalidUser);
		// Returns null if either the username or password is null
		invalidUser = dao.getUser(null);
		assertNull("Assert that a null username results in a null User.", invalidUser);
	}
	
	@Test
	public void testCreateUserReturnsValidUser() {
		// Returns a valid user that was added to the list
		String username = "NewTestUser";
		String fName = "NewFirstName";
		String lName = "NewLastName";
		UserType type = UserType.CUSTOMER;

		Users newUser = dao.createUser(username, fName, lName, type);

		// The parameters are set.
		assertEquals(username, newUser.getUsername(), "Assert that the new User has the same username passed in.");
		assertEquals(fName, newUser.getfName(), "Assert that the new User has the same password passed in.");
		assertEquals(lName, newUser.getlName(), "Assert that the new User has the same password passed in.");
		assertEquals(type, newUser.getType(), "Assert that the new User has the same type passed in.");
	}
	
	@Test
	public void testCreateUserReturnsInvalidUser() {
		// Blank or null username results in null User
		assertNull("Assert that a blank username returns a null User.",
				dao.createUser("  ", user.getfName(), user.getlName(), user.getType()));
		assertNull("Assert that a null username returns a null User.",
				dao.createUser(null, user.getfName(), user.getlName(), user.getType()));

		// Blank or null first name results in null User
		assertNull("Assert that a blank password returns a null User.",
				dao.createUser(user.getUsername(), "  ", user.getlName(), user.getType()));
		assertNull("Assert that a null password returns a null User.",
				dao.createUser(user.getUsername(), null, user.getlName(), user.getType()));
		
		// Blank or null last name results in null User
				assertNull("Assert that a blank password returns a null User.",
						dao.createUser(user.getUsername(), user.getfName(), "  ", user.getType()));
				assertNull("Assert that a null password returns a null User.",
						dao.createUser(user.getUsername(), user.getfName(), null, user.getType()));

		// Blank or null AccountType results in null User
		assertNull("Assert that a null AccountType returns a null User.",
				dao.createUser(user.getUsername(), user.getfName(), user.getlName(), null));
	}
}

