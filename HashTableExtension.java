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


import java.util.NoSuchElementException;

/*
 * Class that extends hashTable
 * 
 * <p>Bugs: None
 * 
 * @author Rex Wasserman
 */
public class HashTableExtension extends HashTableMap<String, String> {

  HashTableMap<String, String> originalHashTable; // original hash table of people who use this
                                                  // before extension

  // constructor
  public HashTableExtension() {
    originalHashTable = new HashTableMap<String, String>();
  }

  /*
   * This method adds the trio with city, state, and population. Save city as a key. Combine state
   * and population into a string, and save it as a value. If successful, return true. Otherwise,
   * return false.
   */
  public boolean addTrio(String city, String state, int population) {


    String key = city.trim();
    String value = state.trim() + ", " + String.valueOf(population);
    if (originalHashTable.put(key, value) == true) {
      return true;
    }
    return false;

  }

  /*
   * This method removes the previously added trio from the list. Use key to check if it was
   * previously saved. If it was, remove it from the ListOfSavedCities, and remove it from the list
   * as well. If successful, return the name of state for front-end. Otherwise, return null.
   */
  public String removeCity(String city) {

    if (originalHashTable.containsKey(city) == false) {
      return null;
    }
    String valueForReturn = originalHashTable.remove(city);
    return valueForReturn.substring(0, valueForReturn.indexOf(','));

  }

  /*
   * This method sets the new the population value of stored trio. Check if the trio is stored using
   * city as a key. Temporarily store the state value for new addition. Remove the previous trio.
   * Add the new trio with new population value. If successful, return true. Otherwise, return
   * false.
   */
  public boolean setPopulation(String city, int population) {
    if (originalHashTable.containsKey(city) == true) {
      String state = getState(city);
      originalHashTable.remove(city);
      addTrio(city, state, population);
      return true;
    }
    return false;
  }

  /*
   * This method sets the new the state value of stored trio. Check if the trio is stored using city
   * as a key. Temporarily store the population value for new addition. Remove the previous trio.
   * Add the new trio with new state value. If successful, return true. Otherwise, return false.
   */
  public boolean setState(String city, String state) {
    if (originalHashTable.containsKey(city) == true) {
      int population = getPopulation(city);
      originalHashTable.remove(city);
      addTrio(city, state, population);
      return true;
    }
    return false;
  }

  /*
   * This methods gets the value in form of combination of state and population. Not directly used,
   * but will be used inside this class to easily separated them for various usages. Search through
   * the hash table map, using city as a key Return value, which consists of state and
   * population(stored as a string)
   */
  public String getStateAndPopulation(String city) {
    return originalHashTable.get(city);
  }

  /*
   * This methods gets the value of population. Bring the value from getStateAndPopulation method,
   * and separate it. Return value, which is the second part of input. If something wrong, throw an
   * exception.
   */
  public int getPopulation(String city) throws NoSuchElementException {
    return Integer.parseInt(
        getStateAndPopulation(city).substring(getStateAndPopulation(city).indexOf(",") + 1).trim());
  }

  /*
   * This methods gets the value of state. Bring the value from getStateAndPopulation method, and
   * separate it. Return value, which is the first part of input. If something wrong, throw an
   * exception.
   */
  public String getState(String city) throws NoSuchElementException {
    return getStateAndPopulation(city).substring(0, getStateAndPopulation(city).indexOf(","))
        .trim();
  }

  /*
   * Below overridden methods are to connect the user interface and original hash map
   * implementation.
   */
  @Override
  public boolean containsKey(String key) {
    return originalHashTable.containsKey(key);
  }


  @Override
  public void clear() {
    originalHashTable.clear();
  }

  @Override
  public int size() {
    return originalHashTable.size();
  }
}
