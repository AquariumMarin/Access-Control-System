//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: AccessControl.java
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

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * This class represents a single terminal (computer). The AccessControl system as a whole allows
 * many users to be logged in at once–just at different terminals. This class also contains methods
 * for constructors, reporting whether a given username/password pair is a valid login, changing the
 * password of the current user, logging out the current user, mutator that you can use to write
 * tests without simulating user input, adding new users, removing a user, giving a user admin
 * power, removing the admin power, and resetting the password.
 * 
 * @author Marin Suzuki
 */
public class AccessControl {

  private static ArrayList<User> users; // An ArrayList of valid users
  private User currentUser; // Who is currently logged in, if anyone?
  private static final String DEFAULT_PASSWORD = "changeme"; // Default password given to new users
                                                             // or when we reset a password of a
                                                             // specific user.

  /**
   * A no-argument constructor
   */
  public AccessControl() {
    if (users == null) {
      users = new ArrayList<User>();
      User firstUser = new User("admin", "root", true);
      users.add(firstUser);
    }
    this.currentUser = null;
  }

  /**
   * Report whether a given username/password pair is a valid login
   * 
   * @param username name of a user
   * @param password a string password to log in
   * @return return true if the username/password pair matches any user in your users ArrayList and
   *         false otherwise.
   */
  public static boolean isValidLogin(String username, String password) {
    for (int i = 0; i < users.size(); i++) { //TODO
      if (users.get(i).getUsername().equals(username) && (users.get(i).isValidLogin(password))) {
        return true; //TODO
      }
    }
    return false; // not found
  }

  /**
   * Change the current password of the current user
   * 
   * @param password a string password to log in
   */
  public void changePassword(String newPassword) {
    this.currentUser.setPassword(newPassword);
  }

  /**
   * Log out the current user
   */
  public void logout() {
    this.currentUser = null;
  }

  /**
   * A mutator you can use to write tests without simulating user input. It sets the current user to
   * the user from the users list whose username matches the string provided as input to the method
   * (exact match case sensitive).
   * 
   * @param username name of a user
   */
  public void setCurrentUser(String username) {
    // look for the same username in arraylist
    for (int i = 0; i < users.size(); i++) {
      if (users.get(i).getUsername().equals(username)) {
        this.currentUser = users.get(i);
      }
    }
  }

  /**
   * Create a new user with the default password and isAdmin==false and add it to the users
   * ArrayList. IllegalArgumentException will be thrown if username is null or if its length is less
   * than 5 ( < 5), or if a user is duplicated. Return true if the current user has Admin power and
   * the new user was successfully added. Return false otherwise.
   * 
   * @param username name of a user
   * @return true if the current user has Admin power and the new user was successfully added.
   *         Return false if the the current user is null or does not have Admin power
   * @throw IllegalArgumentException if username is null or if its length is less than 5 ( < 5), or
   *        if a user with the same username is already in the list of users.
   */
  public boolean addUser(String username) throws IllegalArgumentException {

    // Return false if the the current user is null or does not have Admin power
    if (this.currentUser == null || !this.currentUser.getIsAdmin()) {
      return false;
    }

    // throws an IllegalArgumentException with a descriptive error message if
    // username is null or if its length is less than 5 ( < 5), or if
    // a user with the same username is already in the list of users
    // (usernames must be unique)
    if (username == null || username.length() < 5) {
      throw new IllegalArgumentException("username is null or length of username is less than 5");
    }

    for (int i = 0; i < users.size(); i++) {
      if (users.get(i).getUsername().equals(username)) {
        throw new IllegalArgumentException("duplicate username!");
      }
    }

    User newUser = new User(username, DEFAULT_PASSWORD, false); // Create a new user With the
                                                                // default password and
                                                                // isAdmin==false

    users.add(newUser); // and add it to the users ArrayList

    // Return true if the current user has Admin power and the new user was successfully added.
    return true;
  }

  /**
   * Create a new user, specify their admin status, and add it to the list of users.
   * IllegalArgumentException will be thrown if username is null or if its length is less than 5 ( <
   * 5), or if a user with the same username is already in the list of users. Return true if the
   * current user has Admin power and the new user was successfully added. Return false otherwise.
   * 
   * @param username name of a user
   * @param isAdmin  true if it has admin powers, false it does not have.
   * @return Return true if the current user has Admin power and the new user was successfully
   *         added. Return false if the the current user is null or does not have Admin power.
   * @throw IllegalArgumentException if username is null or if its length is less than 5 ( < 5), or
   *        if a user is duplicated.
   */
  public boolean addUser(String username, boolean isAdmin) throws IllegalArgumentException {

    // Return false if the the current user is null or does not have Admin power
    if (this.currentUser == null || !this.currentUser.getIsAdmin()) {
      return false;
    }

    // throws an IllegalArgumentException with a descriptive error message if
    // username is null or if its length is less than 5 ( < 5), or if
    // a user with the same username is already in the list of users
    if (username == null || username.length() < 5) {
      throw new IllegalArgumentException("username is null or length of username is less than 5");
    }

    for (int i = 0; i < users.size(); i++) {
      if (users.get(i).getUsername().equals(username)) {
        throw new IllegalArgumentException("duplicate username!");
      }
    }

    User newUser = new User(username, DEFAULT_PASSWORD, isAdmin); // Create a new user, specify
                                                                  // their admin status,and add it
                                                                  // to the list of users.

    users.add(newUser); // and add it to the users ArrayList

    // Return true if the current user has Admin power and the new user was successfully added.
    return true; // successfully added, currentUser is not null and has Admin power

  }

  /**
   * Remove a user given their unique username. NoSuchElementException will be thrown if no match
   * with username is found in the list of users. Return true if the current user has Admin powers
   * and the user whose username is passed as input was successfully removed. Return false if the
   * the current user is null or does not have Admin power
   * 
   * @param username name of a user
   * @return Return true if the current user has Admin powers and the user whose username is passed
   *         as input was successfully removed. Return false if the current user is null or does not
   *         have Admin power
   * @throw NoSuchElementException if no match with username is found in the list of users
   */
  public boolean removeUser(String username) throws NoSuchElementException {

    // Return false if the the current user is null or does not have Admin power
    if (this.currentUser == null || !this.currentUser.getIsAdmin()) {
      return false;
    }

    boolean matchedUserNameFound = false; // look for the same username in arraylist

    for (int i = 0; i < users.size(); i++) {
      if (users.get(i).getUsername().equals(username)) {
        matchedUserNameFound = true;
        users.remove(i); // Remove a user given their unique username TODO
      }
    }

    // throws a NoSuchElementException with a descriptive error message
    // if no match with username is found in the list of users
    if (username == null || matchedUserNameFound == false) {
      throw new NoSuchElementException("No matched item found");
    }

    // Return true if the current user has Admin powers and
    // the user whose username is passed as input was successfully removed.
    return true;
  }

  /**
   * Give a user admin power. NoSuchElementException with a descriptive error message will be thrown
   * if no match with username is found in the list of users. Return true if this operation
   * terminates successfully, false otherwise.
   * 
   * @param username name of a user
   * @return Return true if this operation terminates successfully
   * @throw NoSuchElementException if no match with username is found in the list of users
   */
  public boolean giveAdmin(String username) throws NoSuchElementException {
    boolean matchedUserNameFound = false; // look for the same username in arraylist

    // Return false if the current user is null or does not have admin powers
    if (this.currentUser == null || !this.currentUser.getIsAdmin()) {
      return false;
    }

    // Give a user admin power and check if matched name exists
    for (int i = 0; i < users.size(); i++) {
      if (users.get(i).getUsername().equals(username)) {
        matchedUserNameFound = true;
        users.get(i).setIsAdmin(true);
      }
    }

    // throws a NoSuchElementException with a descriptive error message
    // if no match with username is found in the list of users
    if (matchedUserNameFound == false) {
      throw new NoSuchElementException("no matched item found");
    }

    // Return true if this operation terminates successfully
    return true;
  }

  /**
   * Remove the admin power of a user given their username. NoSuchElementException will be thrown
   * with a descriptive error message if no match with username is found in the list of users.
   * Return true if this operation terminates successfully. Return false if the current user is null
   * or does not have admin powers.
   * 
   * @param username name of a user
   * @return Return true if this operation terminates successfully. Return false if the current user
   *         is null or does not have admin powers.
   * @throw NoSuchElementException if no match with username is found in the list of users
   */
  public boolean takeAdmin(String username) throws NoSuchElementException {
    boolean matchedUserNameFound = false;

    // Return false if the current user is null or does not have admin powers
    if (currentUser == null || !currentUser.getIsAdmin()) {
      return false;
    }

    // Remove the admin power of a user given their username
    for (int i = 0; i < users.size(); i++) {
      if (users.get(i).getUsername().equals(username)) {
        matchedUserNameFound = true;
        users.get(i).setIsAdmin(false);
      }
    }

    // throws a NoSuchElementException with a descriptive error message
    // if no match with username is found in the list of users
    if (matchedUserNameFound == false) {
      throw new NoSuchElementException("no matched item found");
    }

    // Return true if this operation terminates successfully
    return true;

  }

  /**
   * Reset the password of a user given their username. NoSuchElementException with a descriptive
   * error message will be thrown if no match with username is found in the list of users. Return
   * true if this operation terminates successfully. Return false if the current user is null or
   * does not have admin powers.
   * 
   * @param username name of a user
   * @return Return true if this operation terminates successfully. Return false if the current user
   *         is null or does not have admin powers.
   * @throw NoSuchElementException if no match with username is found in the list of users
   */
  public boolean resetPassword(String username) throws NoSuchElementException {
    boolean matchedUserNameFound = false;

    // Return false if the current user is null or does not have admin powers
    if (currentUser == null || !currentUser.getIsAdmin()) {
      return false;
    }

    // Reset the password of a user given their username
    for (int i = 0; i < users.size(); i++) {
      if (users.get(i).getUsername().equals(username)) {
        matchedUserNameFound = true;
        users.get(i).setPassword(DEFAULT_PASSWORD);
      }
    }

    // throws a NoSuchElementException with a descriptive error message
    // if no match with username is found in the list of users
    if (matchedUserNameFound == false) {
      throw new NoSuchElementException("no matched item found");
    }

    // Return true if this operation terminates successfully
    return true;
  }

  /**
   * Main method
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    // A STATIC method that creates a new AccessControl object
    // An implementation for this method will be provided to you

  }

}
