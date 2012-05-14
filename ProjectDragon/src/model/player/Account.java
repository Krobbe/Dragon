package model.player;

/**
 * A class that simulates an account.
 * 
 * Contains firstname, lastname, username, password for a person. And a balance
 * for that persons account.
 * 
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

		balance = new Balance(0);
	}

	/**
	 * @author lisastenberg
	 * @return the first name of the person that owns the account.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @author lisastenberg Set the firstName of the account.
	 * @param firstName
	 *            what you want to set firstName to.
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
	 * @author lisastenberg Set the lastName of the account.
	 * @param lastName
	 *            what you want to set lastName to.
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
	 * @author lisastenberg Set the username of the account.
	 * @param userName
	 *            what you want to set userName to.
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
	 * @author lisastenberg Set the passWord of the account.
	 * @param passWord
	 *            what you want to set passWord to.
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
	
	/**
	 * toString method for the Account class
	 * @author forssenm
	 * @return returns a string containing the first name, last name, username and balance
	 */
	public String toString() {
		String result = ("Firstname: " + this.firstName + "\n" + "Lastname: "
				+ this.lastName + "\n" + "Username: " + this.userName + "\n"
				+ "Balance: " + this.balance);
		return result;
	}
	
	/**
	 * Equals method for the account class. Checks specifically if the accounts'
	 * username and password match.
	 * @author robinandersson
	 * @param the object to compare with
	 * @return returns true if they are the same object
	 */
	@Override
	public boolean equals(Object o) {
		if(o == null) {
			return false;
		} else if(o.getClass() != this.getClass()){
			return false;
		}
		
		Account tmpAccount = (Account)o;
		
		return tmpAccount.getUserName().equals(this.getUserName()) &&
				tmpAccount.getPassWord().equals(this.getPassWord());
		
	}

	// Since we at the current state aren't planning on using any hashtables
	// this code was added
	// for the cause of good practice
	public int hashCode() {
		assert false : "hashCode not designed";
		return 42; // any arbitrary constant will do
	}
}
