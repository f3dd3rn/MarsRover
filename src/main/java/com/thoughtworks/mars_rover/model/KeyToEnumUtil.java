package main.java.com.thoughtworks.mars_rover.model;

public class KeyToEnumUtil<K,V> {

	public V getByKey(K key, V[] values) {
		for (V enumaration : values) {
			if(enumaration.toString().equals(key)) {
				return enumaration;
			}
		}
		throw new IllegalArgumentException("Unable to find value for " + key + " in values: " + values.toString());
	}
}
