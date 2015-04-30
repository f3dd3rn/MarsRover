package main.java.com.thoughtworks.mars_rover.model;
/**
 * KeyToEnumUtil is a one-way mapping from key to enum.
 * 
 * @author stephanie
 *
 * @param <K> type of key that needs to be mapped to enum
 * @param <V> enum class
 */
public class KeyToEnumUtil<K,V> {
	/**
	 * Loops through all given values to find the matching enum value and returns it.
	 * 
	 * @param key: generic: key that's supposed to be mapped to enum
	 * @param values: generic[]: all values of specified enum class
	 * @return generic: enum value
	 */
	public V getByKey(K key, V[] values) {
		for (V enumaration : values) {
			if(enumaration.toString().equals(key)) {
				return enumaration;
			}
		}
		throw new IllegalArgumentException("Unable to find value for " + key + " in values: " + values.toString());
	}
}
