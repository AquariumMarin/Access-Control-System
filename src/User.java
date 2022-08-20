//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: User.java
// Course: CS 300 Spring 2022
//
// Author: Marin Suzuki
// Email: msuzuki9@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: None
// Online Sources: None
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class contains methods for creating a new user with the given username, password, and admin
 * status, reporting whether the password is correct, returning the name of the user, reporting
 * whether the user is an admin, setting the new password, and setting the new admin status.
 * 
 * @author Marin Suzuki
 */
public class User {
  private final String USERNAME; // The name of the user
  private String password; // The password of the user
  private boolean isAdmin; // Whether or not the user has Admin powers

  /**
   * Creates a new user with the given username, password, and admin status
   * 
   * @param username name of a user
   * @param password a string password to log in
   * @param isAdmin  whether or not the user is Admin
   */
  public User(String username, String password, boolean isAdmin) {
    this.USERNAME = username;
    this.password = password;
    this.isAdmin = isAdmin;
  }

  /**
   * Report whether the password is correct
   * 
   * @param password a string password to log in
   * @return true if a given password is a valid login, or return false
   */
  public boolean isValidLogin(String password) {

    if (this.password.equals(password)) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Return the name of the user
   * 
   * @return the name of user
   */
  public String getUsername() {
    return this.USERNAME;
  }

  /**
   * Report whether the user is an admin
   * 
   * @return true if the user is an admin, or return false
   */
  public boolean getIsAdmin() {
    return this.isAdmin;
  }

  /**
   * Set the new password
   * 
   * @param password a string password to log in
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Set the new admin status
   * 
   * @param isAdmin whether or not the user is Admin
   */
  public void setIsAdmin(boolean isAdmin) {
    this.isAdmin = isAdmin;
  }

}
