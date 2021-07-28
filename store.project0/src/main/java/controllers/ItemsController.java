package controllers;

import java.util.List;

import io.javalin.http.Context;
import store.items.Items;
import store.user.Users;
import store.user.services.UserService;
import store.user.UserType;

public class ItemsController {
	private UserService us = new UserService();

	public void getItems(Context ctx) {
		Users loggedUser = ctx.sessionAttribute("loggedUser");

		List<Items> items = us.getBooks();

		// If the items list returned null
		if (items == null) {
			ctx.status(404);
			ctx.html("No items in the store");
			return;
		}
		ctx.json(items);
	}
	
	public void addItem(Context ctx) {
		// See if the user is logged in
		Users loggedUser = ctx.sessionAttribute("loggedUser");
		// If the user is not logged in or is not a manager
		if (loggedUser == null || !loggedUser.getType().equals(UserType.EMPLOYEE)) {
			ctx.status(403);
			return;
		}

		// Get the item from the body
		Items item = ctx.bodyAsClass(Items.class);

		// Add the item to the inventory
		item = us.registerBook(item.getProductName(), item.getProductPrice(), item.getQuantity());

		// If item has missing or incorrect details
		if (item == null) {
			ctx.status(400);
			ctx.html("Item details were not entered correctly");
			return;
		}

		// Return the item
		ctx.json(item);
	}
}
