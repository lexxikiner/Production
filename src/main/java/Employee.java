/*
  Captures all of the information of the employee

  @author Lexxi Kiner
 */

public class Employee {

  String name;
  String username;
  final String password;
  String email;

  /**
   * creates the username, email and possibly a password of the employee
   *
   * @param name - the name of the employee
   * @param password - the password the employee provides
   */
  Employee(String name, String password) {
    if (checkName()) {
      setUsername(name);
      setEmail(name);
    } else {
      this.username = "default";
      this.email = "user@oracleacademy.Test";
    }

    if (isValidPassword()) {
      this.password = password;
    } else {
      this.password = "pw";
    }

  }

  /**
   * this is used to create a database table
   *
   * @param name - the name of the employee
   * @param password - the password the employee provides
   * @param username - the created username for the employee
   * @param email - the created email for the employee
   */
  Employee(String name, String password, String username, String email) {
    this.name = name;
    this.username = username;
    this.email = email;
    this.password = password;

  }

  private void setUsername(String name) {
    int locationOfSpace = name.indexOf(" ");
    String firstName = name.substring(0, locationOfSpace);
    String lastName = name.substring(locationOfSpace + 1);
    this.username = (firstName.charAt(0) + lastName).toLowerCase();
  }

  private boolean checkName() {
    return name.contains(" ");
  }

  private void setEmail(String name) {
    int locationOfSpace = name.indexOf(" ");
    String firstName = name.substring(0, locationOfSpace);
    String lastName = name.substring(locationOfSpace + 1);
    this.email = (firstName + "." + lastName).toLowerCase() + "@oracleacademy.Test";
  }

  private boolean isValidPassword() {
    return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{3,}$");
  }

  public String toString() {
    return "Employee Details\n" + "Name : " + name + "\n"
        + "Username : " + username + "\n" + "Email : " + email + "\n"
        + "Initial Password : " + password;
  }
}
