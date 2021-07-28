package store.starter;
import controllers.ItemsController;
import controllers.UsersController;
import io.javalin.Javalin;
public class Starter {
	public static void main(String[] args) {
//		Menu m = new Menu();
//		m.start();
		Javalin app = Javalin.create().start(8080);

		UsersController usersControl = new UsersController();
		ItemsController itemsControl = new ItemsController();
		
		// As a user, I can log in
				app.post("/users", usersControl::login);

				// As a customer, I can register for a account
				app.put("/users/:username", usersControl::register);
				// As a employee I want to add books.
				app.put("/employees/:productName", itemsControl::addItem);
				// As a customer I want to get the item list
				app.get("/items", itemsControl::getItems);
				
	}
}
