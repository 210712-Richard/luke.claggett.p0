package store.user.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import store.data.UserDAO;
import store.user.UserType;
import store.user.Users;
import store.user.services.UserService;
import util.MockitoHelper;

public class UserServiceTest {
	private UserService service = null;
	private Users user = null;
	private UserDAO dao = null;

	private static MockitoHelper<UserDAO> mockHelper; // Sets up a Mock UserDAO for UserService for method verification.

	@BeforeAll
	public static void beforeStart() {
		mockHelper = new MockitoHelper<UserDAO>(UserDAO.class);
	}

	@BeforeEach
	public void beforeTests() {
		service = new UserService();

		user = new Users();
		user.setID(999);
		user.setUsername("Test");
		user.setfName("TestfName");
		user.setlName("TestlName");
		user.setType(UserType.CUSTOMER);
	}

	@Test
	public void testIsUsernameUniqueReturns() {
		// Checks if it returns true and false for unique usernames.
		assertTrue(service.isUsernameUnique("Test"), "Assert that does return true for new usernames.");
		assertFalse(service.isUsernameUnique("Luke"), "Assert that does return false for registered usernames");
		assertFalse(service.isUsernameUnique(""), "Assert that empty usernames return false");
		assertFalse(service.isUsernameUnique(null), "Assert that null usernames return false.");
	}

	@Test
	public void testIsUsernameUniqueCallsMethods() {
		dao = mockHelper.setPrivateMock(service, "ud");
		String username = "Test";
		service.isUsernameUnique(username);
	}

	@Test
	public void testLoginReturnsValidUser() {
		String username = "Luke";
		Users loggedInTrue = service.login("Luke"); // This is the starting user

		assertEquals(username, loggedInTrue.getUsername(),
				"Assert that the username given is the same as the logged in user.");
	}

	@Test
	public void testLoginCallsMethods() {
		dao = mockHelper.setPrivateMock(service, "ud");
		String username = "DefaultUser";

		service.login(username);

		ArgumentCaptor<String> usernameCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> passwordCaptor = ArgumentCaptor.forClass(String.class);

		Mockito.verify(dao).getUser(usernameCaptor.capture());
		Mockito.verify(dao).writeToFile(); // Verify that writeToFile is called.

		assertEquals(username, usernameCaptor.getValue(), "Assert that method called with username given.");
	}

	@Test
	public void testLoginReturnsInvalidUser() {
		Users loggedInFalse = service.login("FakeUser");
		assertEquals(loggedInFalse, null, "Assert that a bad username results in a null user being returned.");

		Users loggedInNull = service.login(null);
		assertEquals(loggedInNull, null,
				"Assert that a null username results in a null user being returned.");
	}

	@Test
	public void testRegisterReturnsValidUser() {
		// Implement tests for registering valid users
		String username = "regiserTest";
		String fName = "registerTestfName";
		String lName = "registerTestlName";
		UserType type = UserType.CUSTOMER;

		Users newUser = service.register(username, fName, lName, type);

		assertEquals(username, newUser.getUsername(),
				"Assert that the username given is the username of the new User.");
		assertEquals(fName, newUser.getfName(),
				"Assert that the first name given is the first name of the new User.");
		assertEquals(lName, newUser.getlName(), "Assert that the last name given is the last name of the new User.");
		assertEquals(type, newUser.getType(),
				"Assert that the AccountType given is the AccountType of the new User.");
	}

	@Test
	public void testRegisterReturnsInvalidUser() {
		// Implement tests for registering invalid users
		String username = "username";
		String fName = "fName";
		String lName = "lName";
		UserType type = UserType.CUSTOMER;

		// Null Username
		Users nullUsername = service.register(null, fName, lName, type);
		assertNull("Assert that a null username results in a null user.", nullUsername);
	}

	@Test
	public void testRegisterReturnsCallsMethods() {
		// Tests making sure method calls UserDAO methods
		dao = mockHelper.setPrivateMock(service, "ud");

		service.register(user.getUsername(), user.getfName(), user.getlName(), user.getType());

		// Set the captors to make sure the methods are called.
		ArgumentCaptor<String> usernameCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> fNameCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> lNameCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<UserType> typeCaptor = ArgumentCaptor.forClass(UserType.class);

		// Verify the methods are called
		Mockito.verify(dao).createUser(usernameCaptor.capture(), fNameCaptor.capture(), lNameCaptor.capture(),
				typeCaptor.capture());
		Mockito.verify(dao).writeToFile(); // Verify that writeToFile is called.

		// Verify it is the same values
		assertEquals(user.getUsername(), usernameCaptor.getValue(),
				"Assert that createUser called with username given.");
		assertEquals(user.getfName(), fNameCaptor.getValue(),
				"Assert that createUser called with first name given.");
		assertEquals(user.getlName(), lNameCaptor.getValue(), "Assert that createUser called with last name given.");
		assertEquals(user.getType(), typeCaptor.getValue(),
				"Assert that createUser called with AccountType given.");

	}
}

