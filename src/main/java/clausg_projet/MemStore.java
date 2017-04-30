package clausg_projet;

import java.util.ArrayDeque;
import java.util.Hashtable;

public class MemStore {
	private static final int defaultMaxSize = 10000;

	private Hashtable<Integer, Object> table;
	private ArrayDeque<Integer> lruList;
	private int nextKey;
	private int maxSize;


	public MemStore(int maxSize) {
		nextKey = 0;
		table = new Hashtable<>();
		lruList = new ArrayDeque<>();
		this.maxSize = maxSize;
	}

	public MemStore() {
		this(defaultMaxSize);
	}


	/**
	 * Maps the a newkey to the specified value in this table and returns the key.
	 * //TODO : synchronisation
	 * @param value the value
	 * @return the new key
	 */
	public Integer store(Object value) {
		Integer key = nextKey++;
		_store(key, value);
		return key;
	}

	/**
	 * Maps the specified key to the specified value in this table.
	 * //TODO : synchronisation
	 * @param key the key
	 * @param value the value
	 */
	public void store(Integer key, Object value) {
		_store(key, value);
	}

	/**
	 * Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
	 * //TODO : synchronisation
	 * @param key  the key whose associated value is to be returned
	 * @return the value to which the specified key is mapped, or null if this map contains no mapping for the key
	 */
	public Object get(Integer key) {
		return table.get(key);
	}

	/**
	 * Removes the key (and its corresponding value) from this table
	 * @param key the key that needs to be removed
	 * @throws NullPointerException if the key is null
	 */
	public void remove(Integer key) throws NullPointerException {
		table.remove(key);
		lruList.remove(key);
	}

	/**
	 * Maps the specified key to the specified value in this table.
	 * @param key the key
	 * @param value the value
	 */
	private void _store(Integer key, Object value) {
		table.put(key, value);
		lruList.remove(key);
		lruList.addLast(key);
		if (lruList.size() > maxSize) {
			Integer k = lruList.getFirst();
			table.remove(k);
		}
	}
}
