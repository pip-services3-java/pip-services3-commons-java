package org.pipservices.commons.refer;

import java.util.*;

import org.pipservices.commons.errors.*;

/**
 * Set of component references with abilities to add new references, find reference using locators 
 * or remove reference from the set
 */
public interface IReferences {
	/**
	 * Puts a new component reference to the set with explicit locator
	 * @param locator a locator to find the reference
	 * @param component a component reference to be added
	 */
	void put(Object locator, Object component) throws ApplicationException;

	/**
	 * Removes component reference from the set. 
	 * The method removes only the last reference.
	 * @param locator a locator to find the reference to remove
	 * @return a removed reference
	 */
	Object remove(Object locator) throws ApplicationException;	

	/**
	 * Removes all component references from the set. 
	 * @param locator a locator to find the reference to remove
	 * @return a list with removed references
	 */
	List<Object> removeAll(Object locator) throws ApplicationException;
	
	/* <summary>
	 Gets all stored component locators
	 </summary>
	 <returns>a list with component locators</returns>
	 */
	List<Object> getAllLocators();
	
	/**
	 * Gets all stored component references
	 * @return a list with component references
	 */
	List<Object> getAll();	
		
	/**
	 * Gets a list of component references that match provided locator
	 * @param locator a locator to find references
	 * @return a list with found component references
	 */
	List<Object> getOptional(Object locator);

	/**
	 * Gets a list of component references that match provided locator
	 * and matching to the specified type.
	 * @param locator a locator to find references
	 * @return a list with found component references
	 */
	<T> List<T> getOptional(Class<T> type, Object locator);

	/**
	 * Gets a list of component references that match provided locator.
	 * If no references found an exception is thrown
	 * @param locator a locator to find references
	 * @return a list with found component references
	 * @throws ReferenceException when no single component reference is found 
	 */
	List<Object> getRequired(Object locator) throws ReferenceException;

	/**
	 * Gets a list of component references that match provided locator.
	 * and matching to the specified type.
	 * If no references found an exception is thrown
	 * @param locator a locator to find references
	 * @return a list with found component references
	 * @throws ReferenceException when no single component reference is found 
	 */
	<T> List<T> getRequired(Class<T> type, Object locator) throws ReferenceException;

	/**
	 * Gets a component references that matches provided locator.
	 * The search is performed from latest added references.
	 * @param locator a locator to find a reference
	 * @return a found component reference or <code>null</code> if nothing was found
	 */
	Object getOneOptional(Object locator);

	/**
	 * Gets a component references that matches provided locator
	 * and matching to the specified type.
	 * The search is performed from latest added references.
	 * @param locator a locator to find a reference
	 * @return a found component reference or <code>null</code> if nothing was found
	 */
	<T> T getOneOptional(Class<T> type, Object locator);
	
	/**
	 * Gets a component references that matches provided locator.
	 * The search is performed from latest added references.
	 * @param locator a locator to find a reference
	 * @return a found component reference
	 * @throws ReferenceException when requested component wasn't found
	 */
	Object getOneRequired(Object locator) throws ReferenceException;

	/**
	 * Gets a component references that matches provided locator
	 * and matching to the specified type.
	 * The search is performed from latest added references.
	 * @param locator a locator to find a reference
	 * @return a found component reference
	 * @throws ReferenceException when requested component wasn't found
	 */
	<T> T getOneRequired(Class<T> type, Object locator) throws ReferenceException;

	/**
	 * Find all references by specified query criteria
	 * @param query a query criteria
	 * @param required force to raise exception is no reference is found
	 * @return list of found references
	 * @throws ReferenceException when requested component wasn't found
	 */
	List<Object> find(Object locator, boolean required) throws ReferenceException;

	/**
	 * Find all references by specified query criteria
	 * and matching to the specified type.
	 * @param query a query criteria
	 * @param required force to raise exception is no reference is found
	 * @return list of found references
	 * @throws ReferenceException when requested component wasn't found
	 */
	<T> List<T> find(Class<T> type, Object locator, boolean required) throws ReferenceException;
}
