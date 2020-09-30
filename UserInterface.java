import java.util.ArrayList;
import java.util.Scanner;

//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
// Title: UserInterface.java
// Files: UserIntervace.java, Entry.java, HashTableMap.java, MapADT
// Course: CS400 - Fall 2020
// Name: Rex Wasserman
// Email: rwasserman2@wisc.edu
// Team: DB
// TA: Yelun Bao
// Lecturer: Gary Dahl
////////////////////////////////////////////////////////////////////////////////

/*
 * This class implements the user interface for the State-Detector project.
 * 
 * No bugs known of.
 * 
 * @author Rex Wasserman
 */
public class UserInterface {

  backEnd backEnd = new backEnd();

  /*
   * Main class that launches program and runs program.
   * 
   * @param None
   */
  public static void main(String[] args) {

    // intro
    System.out.println("Welcome to State Detector!");
    Scanner scnr = new Scanner(System.in);
    System.out.println("Enter your name: ");
    String name = scnr.next();
    System.out.println("Welcome " + name);

    // starts userInterface
    new UserInterface().userInterface();
    // userInterface();

    // Exit
    System.out.println("Goodbye!");
    System.out.println("Report and questions or errors to rwasserman2@wisc.edu.");
    System.out.println("Designed for CS400 - Fall 2020");

  }


  /*
   * Method that lists the available options for the user.
   * 
   * @param None
   */
  public void printMenu() {

    // Lists menu
    System.out.println("---------------------------------------------------------------");
    System.out.println("Menu:");
    System.out.println("---------------------------------------------------------------");
    System.out.println("\"A\" to Add a new trio to the HashTableMap.");
    System.out.println("\"S\" to Search for a city's information.");
    System.out.println("\"P\" to check the Population of a state by capital.");
    System.out.println("\"W\" to check Which state a capital belongs to.");
    System.out.println("\"U\" to Update the home state of a city");
    System.out.println("\"X\" to check the number of current valid capital in the table");
    System.out.println("\"R\" to remove a specific capital");
    System.out.println("\"Q\" to Quit");
    System.out.println("---------------------------------------------------------------");

  }

  /*
   * Handles user input
   * 
   * @param theCom the orginial command
   * 
   * @param Scanner scnr a Scanner for user input
   */
  public void recieveInput(String theCom, Scanner scnr) {
    boolean validCommand = false;
    theCom.toLowerCase();
    // command is at first. Trim and delete the rest. Check.
    if (theCom.length() == 0) {
      return;
    }

    System.out.println("Command: " + theCom);

    char comman = theCom.charAt(0);
    String command = Character.toString(comman);

    String city = "City";
    String state = "State";
    int pop = 5000;

    if ("a".equals(command)) {
      commandA(scnr);
      validCommand = true;
    }
    if ("s".equals(command)) {
      commandS(scnr);
      validCommand = true;
    }
    if ("p".equals(command)) {
      commandP(scnr);
      validCommand = true;
    }
    if ("w".equals(command)) {
      commandW(scnr);
      validCommand = true;
    }
    if ("u".equals(command)) {
      commandU(scnr);
      validCommand = true;
    }
    if ("x".equals(command)) {
      commandX(scnr);
      validCommand = true;
    }
    if ("r".equals(command)) {
      commandR(scnr);
      validCommand = true;
    }
    if ("q".equals(command)) {
      commandQ(scnr);
      validCommand = true;

    }
    return;
  }

  /*
   * Method that adds a valid city to your list.
   * 
   * @param Scanner scn a Scanner for user input
   */
  public void commandA(Scanner scn) {


    System.out.println("Enter City Name to Add: ");
    String city = scn.next();

    while (!inputValidCapital(city)) {
      System.out.println("Retry");
      city = scn.next();
    }

    System.out.println("Enter State Name to Add: ");
    String state = scn.next();
    while (!inputValidState(state)) {
      System.out.println("Retry");
      System.out.println("Enter State Name to Add: ");
      state = scn.next();
    }

    int pop = 0;
    try {
      System.out.println("Enter Population to Add: ");
      pop = scn.nextInt();
    } catch (Exception e) {
      System.out.println("Failed");
      System.out.println("Enter Population to Add: ");
      pop = scn.nextInt();
    }

    if (backEnd.add(city, state, pop)) {
      System.out.println("Added.");

    } else {
      System.out.println("Error. Retry.");
    }
  }

  /*
   * Method that returns the corresponding city information (state and population)
   * 
   * @param Scanner scn a Scanner for user input
   */
  public void commandS(Scanner scn) {

    String city;
    String state;
    int pop;

    try {
      System.out.println("Which City?");
      city = scn.next();
      state = backEnd.whatIsMyState(city);
      pop = backEnd.whatIsMyPopulation(city);

      while (!inputValidCapital(city)) {
        System.out.println("Which City?");
        city = scn.next();
      }

      while (!inputValidState(state)) {
        System.out.println("State?");
        state = scn.next();
      }
    } catch (Exception e) {
      System.out.println("Failed");
      System.out.println("Which City?");
      city = scn.next();
      state = backEnd.whatIsMyState(city);
      pop = backEnd.whatIsMyPopulation(city);
    }

    System.out.println("The state is: " + state);
    System.out.println("The population of " + state + " is " + pop);
  }

  /*
   * Gets the population of the city needed
   * 
   * @param Scanner scn a Scanner for user input
   */
  public void commandP(Scanner scn) {
    String city;

    try {
      System.out.println("Which City?");
      city = scn.next();

      while (!inputValidCapital(city)) {
        System.out.println("Which City?");
        city = scn.next();
      }

    } catch (Exception e) {
      System.out.println("Failed");
      System.out.println("Which City?");
      city = scn.next();
    }

    try {
      System.out.println("Population of " + city + " is " + backEnd.whatIsMyPopulation(city));
    } catch (Exception e) {
      System.out.println("Failed Try Again");
    }
  }

  /*
   * Gets the state affiliated with the capital
   * 
   * @param Scanner scn a Scanner for user input
   */
  public void commandW(Scanner scn) {
    String city;
    try {
      System.out.println("Which City?");
      city = scn.next();

      while (!inputValidCapital(city)) {
        System.out.println("Which City?");
        city = scn.next();
      }

    } catch (Exception e) {
      System.out.println("Failed");
      System.out.println("Which City?");
      city = scn.next();
    }

    try {
      System.out.println("State that " + city + " is in is " + backEnd.whatIsMyState(city));
    } catch (Exception e) {
      System.out.println("Failed Try Again");
    }
  }

  /*
   * Method that allows the user to update the home state of a city,
   * 
   * @param Scanner scn a Scanner for user input
   */
  public void commandU(Scanner scn) {
    String city;
    String state;
    try {
      System.out.println("Which City?");
      city = scn.next();
      System.out.println("Which State should it be changed to?");
      state = scn.next();

      while (!inputValidCapital(city)) {
        System.out.println("Which City?");
        city = scn.next();
      }
      while (!inputValidState(state)) {
        System.out.println("Which State should it be changed to?");
        state = scn.next();
      }

      try {
        backEnd.changeMyState(city, state);
      } catch (Exception e) {
        System.out.println("Failed Try Again");
      }
    } catch (Exception e) {
      System.out.println("Failed");
    }
  }

  /*
   * Method that allows the user to remove all states in the hashTable
   * 
   * @param Scanner scn a Scanner for user input
   */
  public void commandX(Scanner scn) {
    if (backEnd.numberOfTrios() == 0) {
      System.out.println("There are none.");
    } else {
      System.out.println(backEnd.numberOfTrios());
    }
  }

  /*
   * Method that allows the user to remove any valid city.
   * 
   * @param Scanner scn a Scanner for user input
   */
  public void commandR(Scanner scn) {
    System.out.println("What city should be removed?");
    String city = scn.next();
    String removed = backEnd.removeThisCity(city);
    if (removed != null) {
      System.out.println(removed + " was successfully removed");
    } else {
      System.out.println("Try again. Failed to remove " + city);
    }

  }

  /*
   * Quits the program
   * 
   * @param scn Unused
   */
  public void commandQ(Scanner scn) {
    return;
  }

  /*
   * User interface that drive user interaction with the entire program
   */
  public void userInterface() {
    Scanner scnr = new Scanner(System.in);
    String command = "z";
    while ((command.charAt(0) != 'Q') && (command.charAt(0) != 'q')) {


      printMenu();

      command = scnr.next();

      recieveInput(command, scnr);
    }
  }

  /*
   * Overloaded User interface that drives the program with parameter of Scanner for testing
   * 
   * @param Scanner scnr a Scanner with input
   * 
   */
  public void userInterface(Scanner scnr) {
    String command = "z";
    while ((command.charAt(0) != 'Q') && (command.charAt(0) != 'q')) {


      printMenu();

      command = scnr.next();

      recieveInput(command, scnr);
    }
  }

  /*
   * Method that checks to see if the String the user inpits is a valud state in the USA.
   * 
   * @param String state the state we want to check is valud
   * 
   * @return True if valid, false otherwise.
   */
  public boolean inputValidState(String state) {

    boolean valid = false;

    ArrayList<String> stateCapital = new ArrayList<String>();

    String[] raw = {"Alabama", "Alaska", "American Samoa", "Arizona", "Arkansas", "California",
        "Colorado", "Connecticut", "Delaware", "District of Columbia", "Florida", "Georgia", "Guam",
        "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana",
        "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Minor Outlying Islands",
        "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey",
        "New Mexico", "New York", "North Carolina", "North Dakota", "Northern Mariana Islands",
        "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Puerto Rico", "Rhode Island",
        "South Carolina", "South Dakota", "Tennessee", "Texas", "U.S. Virgin Islands", "Utah",
        "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"};

    // adds all capitals to the stateCapitals
    for (int p = 0; p < raw.length; p++) {
      stateCapital.add(raw[p]);
    }

    int length = stateCapital.size();
    for (int i = 0; i < length; i++) {
      if (state.contentEquals(stateCapital.get(i))) {
        valid = true;
      }
    }
    return valid;
  }

  /*
   * Method that checks to see if the String the user inputs is a valid capital of a USA state.
   * 
   * @param String city, a String that we want to check is valid.
   * 
   * @return True if it is a valid capital, false otherwise.
   * 
   */
  public boolean inputValidCapital(String city) {

    boolean valid = false;

    ArrayList<String> stateCapital = new ArrayList<String>();

    String[] raw = {"Montgomery", "Juneau", "Phoenix", "Little_Rock", "Sacramento", "Denver",
        "Hartford", "Delaware", "Tallahassee", "Atlanta", "Honolulu", "Boise", "Springfield",
        "Indianapolis", "Des Moines", "Topeka", "Frankfort", "Baton Rouge", "Augusta", "Annapolis",
        "Boston", "Lansing", "Saint Paul", "Jackson", "Jefferson City", "Helena", "Lincoln",
        "Carson City", "Concord", "Trenton", "Santa Fe", "Albany", "Raleigh", "Bismarck",
        "Columbus", "Oklahoma City", "Salem", "Harrisburg", "Providence", "Columbia", "Pierre",
        "Nashville", "Austin", "Salt Lake City", "Montpelier", "Richmond", "Olympia", "Charleston",
        "Madison", "Cheyenne"};

    // adds all capitals to the stateCapitals
    for (int p = 0; p < raw.length; p++) {
      stateCapital.add(raw[p]);
    }

    int length = stateCapital.size();
    for (int i = 0; i < length; i++) {
      if (city.contentEquals(stateCapital.get(i))) {
        valid = true;
      }

    }

    return valid;
  }
}
