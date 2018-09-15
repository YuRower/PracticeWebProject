package ua.shvidkoy.webproject.model.entity;

public class User extends AbstractEntity {

	private String firstName;
	private String lastName;
	private String login;
	private String password;
	private int userRoleId;
	private int userPhotoId;

	public User() {}
	
	public User(String firstName, String lastName, String login,  String password, int userRoleId, int userPhotoId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.login = login;
		this.password = password;
		this.userRoleId = userRoleId;
		this.userPhotoId = userPhotoId;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public int getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}

	public int getUserPhotoId() {
		return userPhotoId;
	}

	public void setUserPhotoId(int userPhotoId) {
		this.userPhotoId = userPhotoId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", login=" + login + ", password=" + password
				+ ", userRoleId=" + userRoleId + ", userPhotoId=" + userPhotoId + "]";
	}

}