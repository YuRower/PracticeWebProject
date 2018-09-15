package ua.shvidkoy.webproject.model.entity;

public class UserToRole {
	private int userId;
	private int roleId;
	
	public UserToRole() {	}
	
	public UserToRole(int userId, int accountId) {
		super();
		this.userId = userId;
		this.roleId = accountId;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAccountId() {
		return this.roleId;
	}

	public void setAccountId(int accountId) {
		this.roleId = accountId;
	}

	@Override
	public String toString() {
		return "UserToRole [userId=" + userId + ", roleId=" + roleId +"]";
}
}
