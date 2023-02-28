package backend;

import java.util.HashMap;
import java.util.Map;

public class UserRepo {

	private static UserRepo instance = new UserRepo();

	public static UserRepo getInstance() {
		return instance;
	}

	private Map <String, User> users = new HashMap<>();
	
	public void add(User u) {
		users.put(u.getUsername(), u);
	}
	
	private UserRepo() {
		add(new User("admin", "admin", "admin", "admin")); 
		add(new User("luca", "chiere", "luca", "aaaa")); 
	}
	
	public User getUser(String username) {
		return users.get(username);
	}
	
	public User getUser(String username, String password) {
		User u = getUser(username);
		return (u!=null && u.getPassword().equals(password)) ? u : null;
	}
}
