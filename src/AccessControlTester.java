//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: AccessControlTester.java
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
 * This class contains testers for constructor, all the accessor and mutator methods defined in the
 * User class, isValidLogin(), addUser(String username), addUser(String username, boolean isAdmin),
 * removeUser() methods in AccessControl class.
 * 
 * @author Marin Suzuki
 */
public class AccessControlTester {

  /**
   * Main method
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    System.out.println(runAllTests());
  }

  /**
   * This tester must check for the correctness of the constructor, and all the accessor and mutator
   * methods defined in the User class.
   *
   * @return true if all tests pass, and false if any of your tests fails.
   *
   */
  public static boolean testUserConstructorAndMethods() {

    // test for constructor, isValidLogin(), getUsername(), getIsAdmin()
    try {
      User UwStudent = new User("student", "badger", false); // make constructor 
      if (!UwStudent.getUsername().equals("student") || !UwStudent.isValidLogin("badger")
          || UwStudent.getIsAdmin()) { // User has a lot of information about password, user name...
        System.out.println("constructor is wrong");
        return false; // incorrect
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // incorrect
    }

    // test for setPassword()
    try {
      User UwStudent = new User("student", "badger", false);
      UwStudent.setPassword("newPass");
      if (!UwStudent.isValidLogin("newPass")) {
        return false; // incorrect
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // incorrect
    }

    // test for setIsAdmin()
    try {
      User UwStudent = new User("student", "badger", false);
      UwStudent.setIsAdmin(true);
      if (!UwStudent.getIsAdmin()) {
        return false; // incorrect
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // incorrect
    }

    return true; // no bug detected

  }

  /**
   * This method tests checks the correctness of AccessControl.isValidLogin() method
   *
   * @return true if all tests pass, and false if any of your tests fails.
   *
   */
  public static boolean testAccessControlIsValidLoginNotValidUser() {

    // Checks the correctness of AccessControl.isValidLogin() method when called with incorrect
    // username
    try {
      AccessControl testUsers = new AccessControl(); //TODO
      if (AccessControl.isValidLogin("wrongUserName", "root")) { // wrong username as input
        System.out.println("isValidLogin() is wrong");
        return false; // incorrect
      }
    } catch (Exception e) {
      e.printStackTrace(); //TODO
      return false; // incorrect
    }

    // Checks the correctness of AccessControl.isValidLogin() method when called with incorrect
    // username
    try {
      AccessControl testUsers = new AccessControl();
      if (AccessControl.isValidLogin(null, "root")) { // wrong username as input
        System.out.println("isValidLogin() is wrong");
        return false; // incorrect
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // incorrect
    }

    // Checks the correctness of AccessControl.isValidLogin() method when
    // called not matching (username, password) pair
    try {
      AccessControl testUsers1 = new AccessControl(); //TODO static is unique to class, so it does not matter
      // if you use testUser1 or testUsers because they are sharing staticfields even though the 
      // instance name is different
      testUsers1.setCurrentUser("admin"); // current user is set and now has admin power
      testUsers1.addUser("UwStudent1", true); // add new user
      testUsers1.setCurrentUser("UwStudent1"); // set new user as current user
      testUsers1.changePassword("backy"); // password for UwStudent1 changed
      if (AccessControl.isValidLogin("admin", "backy")) { // wrong pair as input
        System.out.println("isValidLogin() is wrong");
        return false; // incorrect
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // incorrect
    }

    // Check if the input is valid
    try {
      AccessControl testUsers = new AccessControl();
      testUsers.setCurrentUser("admin"); // current user is set and now has admin power
      testUsers.addUser("UwStudent1-1", true); // add new user
      testUsers.setCurrentUser("UwStudent1-1"); // set new user as current user
      testUsers.changePassword("backy"); // password for UwStudent1-1 changed
      if (!AccessControl.isValidLogin("UwStudent1-1", "backy")) { // correct pair as input
        System.out.println("isValidLogin() is wrong");
        return false; // incorrect
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // incorrect
    }

    // Check if the input is valid
    try {
      AccessControl testUsers = new AccessControl();
      testUsers.setCurrentUser("admin"); // current user is set and now has admin power
      testUsers.addUser("UwStudent1-1-1", true); // add new user
      testUsers.setCurrentUser("UwStudent1-1-1"); // set new user as current user
      testUsers.resetPassword("UwStudent1-1-1"); // password for UwStudent1-1 changed
      if (!AccessControl.isValidLogin("UwStudent1-1-1", "changeme")) { // correct pair as input
        System.out.println("isValidLogin() is wrong");
        return false; // incorrect
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // incorrect
    }

    return true; // no bug detected;
  }

  /**
   * This method tests addUser(String username) method
   *
   * @return true if all tests pass, and false if any of your tests fails.
   *
   */
  public static boolean testAddUserWithNoAdminPowers() {
    // Creates a new AccessControl object and does not log in an admin.
    // This test must fail if addUser(String username) does not return false
    try {
      AccessControl testUsers1 = new AccessControl(); // no admin log in yet
      if (testUsers1.addUser("UwStudent")) { // false should be returned
        return false; // incorrect
      }
    } catch (Exception e) {
      System.out.println("Unexpected exception occurred");
      return false; // incorrect
    }

    // This test must fail if a user was added to the list of user after the method returns.
    try {
      AccessControl testUsers1 = new AccessControl(); // no admin log in yet
      testUsers1.addUser("UwStudent2"); // UwStudent2 should not be added
      if (AccessControl.isValidLogin("UwStudent2", "changeme")) { // not matched found
        return false; // incorrect
      }
    } catch (Exception e) {
      System.out.println("Unexpected exception occurred");
      return false; // incorrect
    }

    // if admin log in, successful adding
    try {
      AccessControl testUsers1 = new AccessControl(); // no admin log in yet
      testUsers1.setCurrentUser("admin"); // admin log in
      testUsers1.addUser("UwStudent2-2"); // UwStudent2-2 should be added
      if (!AccessControl.isValidLogin("UwStudent2-2", "changeme")) { // matched found
        return false; // incorrect
      }
    } catch (Exception e) {
      System.out.println("Unexpected exception occurred");
      return false; // incorrect
    }

    return true; // no bug detected
  }

  /**
   * This method tests checks the correctness of addUser and removeUser methods
   *
   * @return true if all tests pass, and false if any of your tests fails.
   *
   */
  public static boolean testAddRemoveUserWithAdminPowers() {

    // Checks the correctness of addUser(username, isAdmin) when the current user has admin powers
    try {
      AccessControl testUsers2 = new AccessControl();
      testUsers2.setCurrentUser("admin"); // current user is set and now has admin powers
      testUsers2.addUser("UwStudent3", true); // UwStudent3 should be added
      if (!AccessControl.isValidLogin("UwStudent3", "changeme")) { // should be valid and return
                                                                   // true
        return false; // incorrect
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // incorrect
    }

    // Checks the correctness of addUser(username) when the current user has admin powers
    try {
      AccessControl testUsers2 = new AccessControl();
      testUsers2.setCurrentUser("admin"); // current user is set and now has admin powers
      testUsers2.addUser("UwStudent3-3", true); // UwStudent3 should be added
      if (!AccessControl.isValidLogin("UwStudent3-3", "changeme")) { // should be valid and return
        // true
        return false; // incorrect
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // incorrect
    }

    // Checks the removeUser methods when the current user has admin powers
    try {
      AccessControl testUsers3 = new AccessControl();
      testUsers3.setCurrentUser("admin"); // current user is set and now has admin powers
      testUsers3.addUser("UwStudent4"); // UwStudent3 should be added
      testUsers3.removeUser("UwStudent4"); // UwStudent3 should be removed
      if (AccessControl.isValidLogin("UwStudent4", "changeme")) { // should be invalid and return
                                                                  // false
        return false; // incorrect
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // incorrect
    }

    return true; // no bug detected
  }

  /**
   * This method call all the tester methods.
   *
   * @return true if all tests pass, and false if any of your tests fails.
   *
   */
  public static boolean runAllTests() {
    return testUserConstructorAndMethods() && testAccessControlIsValidLoginNotValidUser()
        && testAddUserWithNoAdminPowers() && testAddRemoveUserWithAdminPowers();
  }
}
