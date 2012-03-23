package model.player;

/**
 * A class that simulates an account. 
 * 
 * Contains firstname, lastname, username, password for a person. And a balance for that persons account. 
 * @author lisastenberg
 *
 */
public class Account {
	private String firstName, lastName, userName, passWord;
	private Balance balance;
	
	public Account(String fName, String lName, String user, String pass) {
		setFirstName(fName);
		setLastName(lName);
		setUserName(user);
		setPassWord(pass);
	}

	/**
	 * @author lisastenberg
	 * @return the first name of the person that owns the account.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @author lisastenberg
	 * Set the firstName of the account.
	 * @param firstName what you want to set firstName to.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @author lisastenberg
	 * @return the last name of the person that owns the account.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @author lisastenberg
	 * Set the lastName of the account.
	 * @param lastName what you want to set lastName to.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @author lisastenberg
	 * @return the username of the person that owns the account.
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @author lisastenberg
	 * Set the username of the account.
	 * @param userName what you want to set userName to.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @author lisastenberg
	 * @return the password of the account.
	 */
	public String getPassWord() {
		return passWord;
	}

	/**
	 * @author lisastenberg
	 * Set the passWord of the account.
	 * @param passWord what you want to set passWord to.
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	/**
	 * @author lisastenberg
	 * @return the balance of the account.
	 */
	public Balance getBalance() {
		return balance;
	}
}
