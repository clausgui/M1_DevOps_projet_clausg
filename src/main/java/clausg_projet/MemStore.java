package clausg_projet;

import java.util.Hashtable;

public class MemStore {
	private Hashtable<Integer, Object> table;
	private Integer nextKey;

	MemStore() {
		nextKey = 0;
		table = new Hashtable<>();
	}

	/**
	 * Maps the a newkey to the specified value in this table and returns the key.
	 * @param value
	 * @return the new key
	 */
	public Integer store(Object value) {
		Integer key = nextKey++;
		table.put(key, value);
		return key;
	}

	/**
	 * Maps the specified key to the specified value in this table.
	 * @param key
	 * @param value
	 */
	public void store(Integer key, Object value) {
		table.put(key, value);
	}

	/**
	 * Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
	 * @param key
	 */
	public Object get(Integer key) {
		return table.get(key);
	}

	/**
	 * Removes the key (and its corresponding value) from this table
	 * @param key
	 */
	public void remove(Integer key) {
		table.remove(key);
	}
}
