package clausg_projet;

import java.util.Hashtable;

public class MemStore {
	private Hashtable<String, String> table;
	
	MemStore() {
		table = new Hashtable<>();
	}
	
	public void put(String key, String value) {
		table.put(key,value);
	}
	
	public String get(String key) {
		return table.get(key);
	}
}