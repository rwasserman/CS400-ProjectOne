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

import java.util.HashMap;
import java.util.Scanner;

/**
 * A class driving the tests
 * 
 * @author Shourya
 * @author Rex Wasserman
 */
public class TestHashMap {

  /**
   * A method to run the tests
   * 
   * @param args
   */
  public static void main(String[] args) {

    TestHashMap testObject = new TestHashMap();
    System.out.println("Initial Test of this application returned: " + testObject.testAll());

  }

  /**
   * Testing by adding a wrong value pair
   * 
   * @return true if passes, false if fails
   */
  public boolean test1() {

    UserInterface Test = new UserInterface();
    Scanner sc = new Scanner("A Madison,Wisonsin,1\nq\n");
    Test.userInterface(sc);

    if (Test.backEnd.numberOfTrios() != 1)
      return false;;
    return true;

  }

  /**
   * Testing by adding a wrong value pair
   * 
   * @return true if passes, false if fails
   */
  public boolean test2() {

    UserInterface Test = new UserInterface();
    Scanner sc = new Scanner("A Moon,Wisonsin,1\nq\n");
    Test.userInterface(sc);

    if (Test.backEnd.numberOfTrios() != 0)
      return false;
    return true;
  }

  /**
   * Testing by adding a Duplicate value pair
   * 
   * @return true if passes, false if fails
   */
  public boolean test3() {

    UserInterface Test = new UserInterface();
    Scanner sc = new Scanner("A Madison,Wisonsin,1\nA Madison,Wisconsin,1\nq\n");
    Test.userInterface(sc);

    if (Test.backEnd.numberOfTrios() != 1) {
      System.out.println(" Size is " + Test.backEnd.numberOfTrios() + " It should be 1");
      return false;
    }
    return true;
  }

  /**
   * Stress Testing by adding all of the key value pairs
   * 
   * @return true if passes, false if fails
   */
  public boolean test4() {

    UserInterface Test = new UserInterface();
    Scanner sc = new Scanner(
        "A Montgomery,A,1\nA Juneau,B,2\nA Phoenix,B,2\nA Little Rock,B,2\nA Sacramento,B,2\nA Denver,B,2\nA Hartford,B,2\nA Delaware,B,2\nA Tallahassee,B,2\nA Atlanta,B,2\nA Honolulu,B,2\nA Boise,B,2\nA Springfield,B,2\nA Indianapolis,B,2\nA Des Moines,B,2\nA Topeka,B,2\nA Frankfort,B,2\nA Baton Rouge,B,2\nA Augusta,B,2\nA Annapolis,B,2\nA Boston,B,2\nA Lansing,B,2\nA Saint Paul,B,2\nA Jackson,B,2\nA Jefferson City,B,2\nA Helena,B,2\nA Lincoln,B,2\nA Carson City,B,2\nA Concord,B,2\nA Trenton,B,2\nA Santa Fe,B,2\nA Albany,B,2\nA Raleigh,B,2\nA Bismarck,B,2\nA Columbus,B,2\nA Oklahoma City,B,2\nA Salem,B,2\nA Harrisburg,B,2\nA Providence,B,2\nA Columbia,B,2\nA Pierre,B,2\nA Nashville,B,2\nA Austin,B,2\nA Salt Lake City,B,2\nA Montpelier,B,2\nA Richmond,B,2\nA Olympia,B,2\nA Charleston,B,2\nA Madison,B,2\nA Cheyenne,B,2\nq\n");
    Test.userInterface(sc);

    if (Test.backEnd.numberOfTrios() != 50) {
      System.out.println(" Size is " + Test.backEnd.numberOfTrios() + " It should be 50");
      return false;
    }
    return true;
  }

  /**
   * test to see if the city look up system work as planned
   * 
   * @return true or false depending on outcome
   */
  public boolean test5() {

    UserInterface Test = new UserInterface();
    Scanner sc = new Scanner("A Montgomery,M,1\nA Juneau,J,2\nA Phoenix,P,3\nq\n");
    Test.userInterface(sc);
    Test.backEnd.yourHashTable.remove("Montgomery");
    Test.backEnd.yourHashTable.size();
    if (Test.backEnd.numberOfTrios() != 2) {
      System.out.println(" Size is " + Test.backEnd.numberOfTrios() + " It should be 2");
      return false;
    }
    /*
     * System.out.println(" State is " + Test.backEnd1.whatIsMyState("montgomery")) ;
     * System.out.println(" Pop is " + Test.backEnd1.whatIsMyPopulation("montgomery"));
     */
    if (Test.backEnd.whatIsMyState("Phoenix").compareTo("P") != 0) {
      System.out.println("Test Failed Lookup");
      return false;
    }
    return true;

  }

  /**
   * A method calling all test methods
   * 
   * @return true if pass the test, false if fails
   */
  public boolean testAll() {

    if (test1()) {
      System.out.println("Test Failed 1");
      return false;
    }
    if (!test2()) {
      System.out.println("Test Failed 2");
      return false;
    }
    if (test3()) {
      System.out.println("Test Failed 3");
      return false;
    }
    if (test4()) {
      System.out.println("Test Failed 4");
      return false;
    }
    if (test5()) {
      System.out.println("Test Failed 5");
      return false;
    }

    return true;

  }

}
