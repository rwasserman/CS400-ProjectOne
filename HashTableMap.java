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
import java.util.LinkedList;

/*
 * Class that creates a HashTable implementation
 * 
 * <p>Bugs: None
 * 
 * @author Rex Wasserman
 */
public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {

  private int capacity; // how many key-value pairs can be stored
  private LinkedList<Pair>[] storingArray; // the array containing the key-value pairs
  private int size; // the current number of key-value pairs stored

  /**
   * A constructor method representing the this current has table. This will initialize the capacity
   * to the user's desire and initialize all the values within the array.
   * 
   * @param capacity how many elements the user wishes to be initialized.
   */
  public HashTableMap(int capacity) {
    this.capacity = capacity;
    storingArray = new LinkedList[capacity];
    for (int i = 0; capacity > i; i++) {
      storingArray[i] = new LinkedList();
    }
  }

  /**
   * This is the default constructor method of this hash table that sets its capacity at 10 and
   * initializes all the array's indexes.
   */
  public HashTableMap() {
    this.capacity = 10;
    storingArray = new LinkedList[10];// with default capacity = 10
    for (int i = 0; 10 > i; i++) {
      storingArray[i] = new LinkedList();
    }
  }

  /**
   * This method adds a key-value pair to this hash table as long as the specified key entered by
   * the user is not already existing in this table. This method then specifies if the addition of
   * the new pair was successful or not.
   * 
   * @param key   The key specified to be entered.
   * @param value The value of the desired key-value pair.
   * @return boolean represnting if the addition was succesful or not.
   */
  @Override
  public boolean put(KeyType key, ValueType value) {

    if (containsKey(key)) { // check if the key already exists
      return false;
    }

    this.storingArray[getTheIndex(key, capacity)].add(new Pair(key, value));
    size++;

    if (isArrayFull()) { // check if this table is full
      LinkedList<Pair>[] tempStoreArray = new LinkedList[capacity * 2];
      reHashThis(tempStoreArray);
    }
    return true;
  }

  /**
   * This method takes in a specifc key that has been determined by the user, hashes it to the index
   * in the current hash table's array and returns it to the user from a stored key-value pair.
   * 
   * @param key The key specified by the user in which he wishes to see it's corresponding value.
   * @return the value of the specified key-value pair based on the key.
   * @throws NoSuchElementException in the case the key doesn't exist in this table.
   */
  @Override
  public ValueType get(KeyType key) throws NoSuchElementException {

    if (!containsKey(key)) {
      throw new NoSuchElementException("They key does not exist");
    }

    for (int i = 0; storingArray[getTheIndex(key, capacity)].size() > i; i++) {
      if (storingArray[getTheIndex(key, capacity)].get(i).getKey().equals(key)) {
        return (ValueType) storingArray[getTheIndex(key, capacity)].get(i).getValue();
      }
    }
    return null;
  }

  /**
   * This is a getter method that retrieve the size of this hash table.
   * 
   * @return int represetning the number of key-pair values currently stored.
   */
  @Override
  public int size() {
    return this.size;
  }

  /**
   * This method checks if a key exists in the current hashtable or not. It tells the user if the
   * key exists or not.
   * 
   * @param key key specified by user to check existence within table.
   * @return boolean representing if the key exists in this hash table or not.
   */
  @Override
  public boolean containsKey(KeyType key) {
    for (int i = 0; capacity > i; i++) {

      if (!(this.storingArray[i].size() == 0)) {

        for (int j = 0; this.storingArray[i].size() > j; j++) {
          if (this.storingArray[i].get(j).getKey().equals(key)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  /**
   * This method removes from the hashtable a key-value pair, updates the size, and gives back the
   * value that was removed.
   * 
   * @param key key specified by user to remove.
   * @return the value of the pair which was removed.
   */
  @Override
  public ValueType remove(KeyType key) {

    if (containsKey(key)) {
      for (int i = 0; storingArray[getTheIndex(key, capacity)].size() > i; i++) {
        if (storingArray[getTheIndex(key, capacity)].get(i).getKey().equals(key)) {

          ValueType temp = (ValueType) storingArray[getTheIndex(key, capacity)].get(i).getValue();
          storingArray[getTheIndex(key, capacity)].remove(i);
          size--;

          return temp;
        }
      }
    }
    return null;
  }

  /**
   * This method clears out the current hash table and replaces it with a new one of the same
   * capacity but with a total size of zero.
   */
  @Override
  public void clear() {
    storingArray = new LinkedList[capacity];

    for (int i = 0; capacity > i; i++) {
      storingArray[i] = new LinkedList();
    }
    size = 0;
  }

  /**
   * This method is a getter method which retrieves the current capacity of the hash table.
   * 
   * @return int of capacity's numerical value
   */
  public int getCapacity() {
    return this.capacity;
  }

  /**
   * This private helper in this hash table serves the table to know whether it has reached the
   * predetermined ratio of size:capacity.
   * 
   * @return boolean whether the current array has reached the ratio.
   */
  private boolean isArrayFull() {
    if (((this.size / this.capacity)) > 0.8) {
      return true;
    }
    return false;
  }

  /**
   * This is a private helper method that takes in a new resized array and rehashes every single
   * stored element and stores them into the new resized array from the current existing array in
   * this hash table. After this method is implemented, the this.storingArray is updated to a new
   * rehashed and larger one.
   * 
   * @param tempArray A new resized array.
   */
  public void reHashThis(LinkedList[] tempArray) {

    for (int i = 0; capacity * 2 > i; i++) {
      tempArray[i] = new LinkedList();
    }

    for (int i = 0; capacity > i; i++) {

      if (!(this.storingArray[i].size() == 0)) {

        for (int n = 0; this.storingArray[i].size() > n; n++) {
          tempArray[getTheIndex(this.storingArray[i].get(n).getKey(), capacity * 2)]
              .add(new Pair(storingArray[i].get(n).getKey(), storingArray[i].get(n).getValue()));
        }
      }
    }

    this.storingArray = tempArray;
    this.capacity = capacity * 2;
  }

  /**
   * This is a private helper method that hashes a key to return the index number that will be used
   * into any given array with a specific capacity.
   * 
   * @param key      The key's value which must be hashed into an index
   * @param capacity The capacity of the Array which we need the index for
   * @return an int representing the index's number of the array
   */
  private int getTheIndex(Object key, int capacity) {
    return (Math.abs(key.hashCode()) % capacity);
  }

}
