package controllers;

import io.javalin.http.Context;
import store.user.UserType;
import store.user.Users;
import store.user.services.UserService;

public class UsersController {

	private UserService us = new UserService();
	
	public void login(Context ctx) {

		// Create a user from the body
		Users user = ctx.bodyAsClass(Users.class);
		// Call the login method to get the user
		user = us.login(user.getUsername());
		if (user != null) { // The user was found
			// Save the user for the session
			ctx.sessionAttribute("loggedUser", user);
			// Send the user back.
			ctx.json(user);
			return;
		}
		// The user couldn't be found.
		ctx.status(401);
	}
	
	public void register(Context ctx) {

		Users loggedUser = ctx.sessionAttribute("loggedUser");
		// If the user is logged in and is not an admin
		if (loggedUser != null) {
			ctx.status(403);
			return;
		} else {
			// Get the user
			Users newUser = ctx.bodyAsClass(Users.class);

			if (!us.isUsernameUnique(newUser.getUsername())) {
				ctx.status(409);
				ctx.html("Username has been taken");
				return;
			}

			if (newUser.getType().equals(UserType.CUSTOMER)) {
				// Register the user
				newUser = us.register(newUser.getUsername(), newUser.getfName(), newUser.getlName(), newUser.getType());

				if (newUser == null) {
					ctx.status(401);
					ctx.html("Invalid input with account");
					return;
				}

				// Return the new user
				ctx.status(201);
				ctx.json(newUser);
				return;
			}
			ctx.status(403);
		}
	}
}
