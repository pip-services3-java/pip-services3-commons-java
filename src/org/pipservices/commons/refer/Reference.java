package org.pipservices.commons.refer;

/**
 * Contains a reference to a component and locator to find it.
 * It is used by References to store registered component references.
 */
public class Reference {
	private Object _locator;
	private Object _reference;

	/**
	 * Create a new instance of the reference object and assigns its values.
	 * 
	 * @param locator   a locator to find the reference.
	 * @param reference a reference to component.
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
	 * Matches locator to this reference locator.
	 * 
	 * Descriptors are matched using equal method. All other locator types are
	 * matched using direct comparison.
	 * 
	 * @param locator the locator to match.
	 * @return true if locators are matching and false it they don't.
	 * 
	 * @see Descriptor
	 */
	public boolean match(Object locator) {
		// Locate by direct reference matching
		if (_reference.equals(locator))
			return true;
		// Locate by type
		else if (locator instanceof Class<?>)
			return ((Class<?>) locator).isInstance(_reference);
		// Locate by direct locator matching
		else if (_locator != null)
			return _locator.equals(locator);
		else
			return false;
	}

	/**
	 * Gets the stored component locator.
	 * 
	 * @return the component's locator.
	 */
	public Object getLocator() {
		return _locator;
	}

	/**
	 * Gets the stored component reference.
	 * 
	 * @return the component's references.
	 */
	public Object getComponent() {
		return _reference;
	}
}
