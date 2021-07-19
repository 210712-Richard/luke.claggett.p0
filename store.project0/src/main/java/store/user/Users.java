package store.user;

import java.io.Serializable;

public class Users implements Serializable{
	private Integer id;
	private String username;
	private String fName;
	private String lName;
	private UserType type;

public Users(Integer id, String username, String fName, String lName, UserType type) {
	this.id = id;
	this.username = username;
	this.fName = fName;
	this.lName = lName;
	this.type = type;
}

public Integer getID() {
	return id;
}
public void setID(Integer id) {
	this.id = id;
}
public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getfName() {
	return fName;
}

public void setfName(String fName) {
	this.fName = fName;
}

public String getlName() {
	return lName;
}

public void setlName(String lName) {
	this.lName = lName;
}

public UserType getType() {
	return type;
}

public void setType(UserType type) {
	this.type = type;
}

}

