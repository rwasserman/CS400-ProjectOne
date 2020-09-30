// --== CS400 File Header Information ==--
// Name: Khalid Ali Shallhoub
// Email: Shallhoub@wisc.edu
// Team: DB
// TA: Yelun
// Lecturer: Gary Dahl
// Notes to Grader:
import java.util.NoSuchElementException;

public class backEnd extends HashTableMap<String, String> {
	
	HashTableMap<String, String> yourHashTable; //Uses YOUR own HashTableMap implementation
	
	/**
	 * Public constructor method for creating a new instance of your HashTableMap
	 */
	public backEnd() {
		yourHashTable = new HashTableMap<String, String>();
	}
	
	/**
	 * This will use your HashTableMap and add a new trio to it.
	 * @param city Desired capital city of a US state.
	 * @param state Designated state for the entered US state.
	 * @param population Number of residents in that state.
	 * @return boolean Representing if the trio was added. Will return false
	 * 				if the city already exists within your map.
	 */
	public boolean add(String city, String state, int population) {
		String key = city.trim();
		String value = state.trim() + ", " + String.valueOf(population);
		if ( yourHashTable.put(key, value) ) {
			return true;
		}
		return false;
	}
	
	/**
	 * This method will search for an entered city within your HashTableMap and return
	 * a string representing the state and the population.
	 * @param city Used here as a key to retrieve the state and population.
	 * @return String Of the following: <state> + ", " + <population>
	 */
	public String search(String city) {
		return yourHashTable.get(city);
	}
	
	/**
	 * This method will give the front-end user the population ONLY of a certain city/state.
	 * @param city Used here as a key to retrieve its home state's population
	 * @return int The number of people within that state that the city is in.
	 * @throws NoSuchElementException If the city doesn't exist within this HashTableMap.
	 */
	public int whatIsMyPopulation(String city) throws NoSuchElementException {
		return Integer.parseInt( search(city).substring(search(city).indexOf(",") + 1).trim() );
	}
	
	/**
	 * This method will give the front-end user the STATE only of a certain city.
	 * @param city Used here as a key to retrieve its state.
	 * @return String Representing the US state.
	 * @throws NoSuchElementException If the city doesn't exist within this HashTableMap.
	 */
	public String whatIsMyState(String city) throws NoSuchElementException {
		return search(city).substring(0, search(city).indexOf(",")).trim();
	}
	
	/**
	 * This method allows the front-end user to change the population of a city/state that
	 * exists within the user's HashTableMap.
	 * @param city Used here as a key to change the population.
	 * @param newPopulation The desired user's new population.
	 * @return boolean Representing if the change was successful.
	 */
	public boolean changeMyPopulation(String city, int newPopulation) {
		if ( yourHashTable.containsKey(city) ) {
			String state = whatIsMyState(city);
			yourHashTable.remove(city);
			add(city, state, newPopulation);
			return true;
		}
		return false;
	}
	
	/**
	 * This method allows the front-end user to change the home state of a city/state that
	 * exists within the user's HashTableMap. 
	 * @param city Used here as key to change its home state.
	 * @param newState The desired user's new state.
	 * @return boolean representing if the change was successful.
	 */
	public boolean changeMyState(String city, String newState) {
		if (yourHashTable.containsKey(city) ) {
			int population = whatIsMyPopulation(city);
			yourHashTable.remove(city);
			add( city, newState, population );
			return true;
		}
		return false;
	}
	
	
	/**
	 * This method allows the front-end user to use it and remove a certain city from
	 * the HashTableMap, and update the current cities.
	 * @param city Used here as a key to remove it from the HashTableMap
	 * @return null, if the city doesn't exist within this HashTableMap
	 * @return the state that was removed.
	 */
	public String removeThisCity(String city) {
		
		if ( !yourHashTable.containsKey(city) ) {
			return null;
		}
		String tempState = yourHashTable.remove(city);
		return tempState.substring( 0,  tempState.indexOf(',')) ;
		
	}
	
	/**
	 * This method returns the current number of the city-state-population trios.
	 * @return int The number of trios in the HashTableMap.
	 */
	public int numberOfTrios() {
		return yourHashTable.size();
	}
	
	/**
	 * Overriden method to see if the table contains a certain key.
	 * @return boolean Representing if the key exists or not.
	 */
	@Override
	public boolean containsKey(String key) {
		return yourHashTable.containsKey(key);
	}
	
	/**
	 * Overriden method that clears out the table.
	 */
	@Override
	public void clear() {
		yourHashTable.clear();
	}
}
