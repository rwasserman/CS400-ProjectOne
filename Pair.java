// ////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
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
 * Class that creates a linkedList
 * 
 * <p>Bugs: None
 * 
 * @author Rex Wasserman
 */
public class Pair {
  private Object keyValue;
  private Object value;

  /*
   * Constructor
   */
  public Pair(Object key, Object value) {
    this.keyValue = key;
    this.value = value;
  }

  /*
   * Getter for value
   */
  public Object getValue() {
    return this.value;
  }

  /*
   * Getter for key value
   */
  public Object getKey() {
    return this.keyValue;
  }
}
