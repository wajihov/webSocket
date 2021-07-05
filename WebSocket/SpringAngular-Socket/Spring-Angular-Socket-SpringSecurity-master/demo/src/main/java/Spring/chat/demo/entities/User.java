package Spring.chat.demo.entities;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import Spring.chat.demo.config.BCryptManagerUtil;

@Entity
public class User implements Serializable {
	
	private static final long serialVersionUID = 5926468583005150707L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String username;
	private String password;
	@OneToMany(mappedBy="user1")
	@JsonIgnore
	private List<Conversation> conversationsuser1 = new ArrayList<Conversation>();
	@OneToMany(mappedBy="user2")
	@JsonIgnore
	private List<Conversation> conversationsuser2 = new ArrayList<Conversation>();
	@OneToMany(mappedBy="user")
	@JsonIgnore
	private List<Message> message = new ArrayList<Message>();
	
	public User() {
		super();
	}
	
	public User(String username, String password, String adresse) {
		super();
		this.username = username;
		this.password = BCryptManagerUtil.passwordEncoder().encode(password);
	}

	public User(int id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
	    this.password = BCryptManagerUtil.passwordEncoder().encode(password);
	}

	public List<Message> getMessage() {
		return message;
	}

	public void setMessage(List<Message> message) {
		this.message = message;
	}

	public List<Conversation> getConversationsuser1() {
		return conversationsuser1;
	}

	public void setConversationsuser1(List<Conversation> conversations) {
		this.conversationsuser1 = conversations;
	}
	public List<Conversation> getConversationsuser2() {
		return conversationsuser2;
	}

	public void setConversationsuser2(List<Conversation> conversations) {
		this.conversationsuser2 = conversations;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = BCryptManagerUtil.passwordEncoder().encode(password);
	}



	@Override
	public String toString() {
		return "{\"id\":" + id + ", \"username\":" + username + "}";
	}
	
	
    	

}
