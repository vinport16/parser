package parser;

import java.util.function.Function;
import java.util.Map;
import java.util.HashMap;

final class Cache<T, V> {

	// Cache stored as HashMap
	private Map<T, V> cache = new HashMap<T, V>();

	// Checks if key contained in cache
	public boolean containsKey(T key) {
		return cache.containsKey(key);
	}

	// Takes a key and a constructor. Returns the value if the key is contained
	// in the cache, otherwise determine value using the constructor, insert into
	// cache, and return the value
	V get(T key, Function<? super T, ? extends V> constructor) {
		if (key == null) {
			throw new NullPointerException("Key provided is null");
		} else if (constructor == null) {
			throw new NullPointerException("Constructor provided is null");
		}
		if (cache.containsKey(key)) {
			return cache.get(key);
		} else {
			V value = constructor.apply(key);
			cache.put(key, value);
			return value;
		}
	}
}