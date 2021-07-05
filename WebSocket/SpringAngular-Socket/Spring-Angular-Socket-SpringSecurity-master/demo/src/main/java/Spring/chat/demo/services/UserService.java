package Spring.chat.demo.services;

import java.util.List;
import Spring.chat.demo.entities.User;

public interface UserService {
	public void saveUser( User user);
	public User loadByUsername(String username);
	public User getConnectedUser(String username);
	public List<User> getAllUsers();
}
