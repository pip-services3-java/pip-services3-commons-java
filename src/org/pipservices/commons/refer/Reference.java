package org.pipservices.commons.refer;

/**
 * Placeholder to store component references.
 */
public class Reference {
	private Object _locator;
	private Object _reference;

	/**
	 * Create a new reference for an object
	 * @param locator a component locator for the reference
	 * @param reference a component reference
	 */
	public Reference(Object locator, Object reference) {
//		if (locator == null)
//			throw new NullPointerException("Locator cannot be null");
		if (reference == null)
			throw new NullPointerException("Object reference cannot be null");
		
		_locator = locator;
		_reference = reference;
	}
	
	/**
	 * Checks if locator matches the current component
	 * @param locator a location object. It can be standard Descriptor or something else
	 * @return <code>true</code> if component matches the locator or <code>false</code> otherwise.
	 */
	public boolean match(Object locator) {
		// Locate by direct reference matching
		if (_reference.equals(locator))
			return true;
		// Locate by type
		else if (locator instanceof Class<?>)
			return ((Class<?>)locator).isInstance(_reference);
		// Locate by direct locator matching
		else if (_locator != null)
			return _locator.equals(locator);
		else
			return false;
	}
	
	/**
	 * Gets component locator
	 * @return a component locator
	 */
	public Object getLocator() {
		return _locator;
	}
	
	/**
	 * Gets component reference
	 * @return a component itself
	 */
	public Object getComponent() { 
		return _reference; 
	}
}
