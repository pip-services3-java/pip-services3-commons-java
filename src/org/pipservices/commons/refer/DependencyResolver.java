package org.pipservices.commons.refer;

import java.util.*;

import org.pipservices.commons.config.*;
import org.pipservices.commons.convert.*;
import org.pipservices.commons.errors.*;

public class DependencyResolver implements IReferenceable, IReconfigurable {
	private Map<String, Object> _dependencies = new HashMap<String, Object>();
	private IReferences _references;
	
	public DependencyResolver() {

	}

	public DependencyResolver(ConfigParams config)  {
		try {
			configure(config);
		} catch (ConfigException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void configure(ConfigParams config) throws ConfigException {
		ConfigParams dependencies = config.getSection("dependencies");
		for (String name : dependencies.keySet()) {
			String locator = dependencies.get(name);
			if (locator == null) continue;
			
			try {
				Descriptor descriptor = Descriptor.fromString(locator);
				if (descriptor != null)
					_dependencies.put(name, descriptor);
				else
					_dependencies.put(name, locator);
			} catch (Exception ex) {
				_dependencies.put(name, locator);
			}
		}
	}

	@Override
	public void setReferences(IReferences references) throws ReferenceException {
		_references = references;
	}

	public void put(String name, Object locator) {
		_dependencies.put(name, locator);
	}

	private Object find(String name) {
		if (name == null)
			throw new NullPointerException("Dependency name cannot be null");
		if (_references == null)
			throw new NullPointerException("References shall be set");
		
		return _dependencies.get(name);
	}
	
	/**
	 * Gets a list of component references that match provided locator
	 * @param name a dependency name
	 * @return a list with found component references
	 */
	public List<Object> getOptional(String name) {
		Object locator = find(name);		
		return locator != null ? _references.getOptional(locator) : null;
	}

	/**
	 * Gets a list of component references that match provided locator
	 * and matching to the specified type.
	 * @param name a dependency name
	 * @return a list with found component references
	 */
	public <T> List<T> getOptional(Class<T> type, String name) {
		Object locator = find(name);
		return locator != null ? _references.getOptional(type, locator) : null;
	}

	/**
	 * Gets a list of component references that match provided locator.
	 * If no references found an exception is thrown
	 * @param name a dependency name
	 * @return a list with found component references
	 * @throws ReferenceException when no single component reference is found 
	 */
	public List<Object> getRequired(String name) throws ReferenceException {
		Object locator = find(name);
		if (locator == null)
			throw new ReferenceException(null, name);
		
		return _references.getRequired(locator);
	}

	/**
	 * Gets a list of component references that match provided locator.
	 * and matching to the specified type.
	 * If no references found an exception is thrown
	 * @param name a dependency name
	 * @return a list with found component references
	 * @throws ReferenceException when no single component reference is found 
	 */
	public <T> List<T> getRequired(Class<T> type, String name) throws ReferenceException {
		Object locator = find(name);
		if (locator == null)
			throw new ReferenceException(null, name);
		
		return _references.getRequired(type, locator);
	}

	/**
	 * Gets a component references that matches provided locator.
	 * The search is performed from latest added references.
	 * @param name a dependency name
	 * @return a found component reference or <code>null</code> if nothing was found
	 */
	public Object getOneOptional(String name) {
		Object locator = find(name);
		return locator != null ? _references.getOneOptional(locator) : null;
	}

	/**
	 * Gets a component references that matches provided locator
	 * and matching to the specified type.
	 * The search is performed from latest added references.
	 * @param name a dependency name
	 * @return a found component reference or <code>null</code> if nothing was found
	 */
	public <T> T getOneOptional(Class<T> type, String name) {
		Object locator = find(name);
		return locator != null ? _references.getOneOptional(type, locator) : null;
	}
	
	/**
	 * Gets a component references that matches provided locator.
	 * The search is performed from latest added references.
	 * @param name a dependency name
	 * @return a found component reference
	 * @throws ReferenceException when requested component wasn't found
	 */
	public Object getOneRequired(String name) throws ReferenceException {
		Object locator = find(name);
		if (locator == null)
			throw new ReferenceException(null, name);
		
		return _references.getOneRequired(locator);
	}

	/**
	 * Gets a component references that matches provided locator
	 * and matching to the specified type.
	 * The search is performed from latest added references.
	 * @param name a dependency name
	 * @return a found component reference
	 * @throws ReferenceException when requested component wasn't found
	 */
	public <T> T getOneRequired(Class<T> type, String name) throws ReferenceException {
		Object locator = find(name);
		if (locator == null)
			throw new ReferenceException(null, name);
		
		return _references.getOneRequired(type, locator);
	}

	/**
	 * Find all references by specified query criteria
	 * @param required force to raise exception is no reference is found
	 * @return list of found references
	 * @throws ReferenceException when requested component wasn't found
	 */
	public List<Object> find(String name, boolean required) throws ReferenceException {
		if (isNullOrWhiteSpace(name)) { throw new NullPointerException(name); }
		
        Object locator = find(name);
        if (locator == null)
        {
            if (required){
                throw new ReferenceException(null, name);
            }
	        return null;
        }
        return _references.find(locator, required);
	}

	/**
	 * Find all references by specified query criteria
	 * and matching to the specified type.
	 * @param required force to raise exception is no reference is found
	 * @return list of found references
	 * @throws ReferenceException when requested component wasn't found
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> find(Class<T> type, String name, boolean required) throws ReferenceException {
		if (isNullOrWhiteSpace(name)) { throw new NullPointerException(name); }

        Object locator = find(name);
        if (locator == null)
        {
            if (required){
                throw new ReferenceException(null, name);
            }
	        return null;
        }
        return (List<T>) _references.find(locator, required);
	}
	
	
	public static DependencyResolver fromTuples(Object... tuples) {
		DependencyResolver result = new DependencyResolver();
    	if (tuples == null || tuples.length == 0)
    		return result;
    	
        for (int index = 0; index < tuples.length; index += 2) {
            if (index + 1 >= tuples.length) break;

            String name = StringConverter.toString(tuples[index]);
            Object locator = tuples[index + 1];

            result.put(name, locator);
        }
        
        return result;
	}
	
	public static boolean isNullOrWhiteSpace(String s) {
        return s == null || isWhitespace(s);
    }
    
    private static boolean isWhitespace(String s) {
        int length = s.length();
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                if (!Character.isWhitespace(s.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
