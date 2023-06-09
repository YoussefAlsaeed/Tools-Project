package entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	@NotNull
	private String username;
	@NotNull
	private String password;
	@NotNull
	@Enumerated(EnumType.STRING)
	private Role role;
	
//	public User(String username, String password, Role role) {
//		super();
//		this.username = username;
//		this.password = password;
//		this.role = role;
//	}
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
		this.password = password;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	

}