package org.pipservices.commons.refer;

import java.util.*;

/**
 * Basic implementation of IReferences that stores component as a flat list
 */
public class References implements IReferences {	
	protected List<Reference> _references = new ArrayList<Reference>();
	protected Object _lock = new Object();
	
	public References() { }
	
	public References(Object[] tuples) {
		if (tuples != null) {
	        for (int index = 0; index < tuples.length; index += 2) {
	            if (index + 1 >= tuples.length) break;
	
	            put(tuples[index], tuples[index + 1]);
	        }
		}
	}
	
	public void put(Object locator, Object component) {
		if (locator == null)
			throw new NullPointerException("Locator cannot be null");
		if (component == null)
			throw new NullPointerException("Reference cannot be null");

		synchronized (_lock) {
			// Add reference to the set
			_references.add(new Reference(locator, component));
		}
	}
	
	public Object remove(Object locator) {
		if (locator == null) return null;

		synchronized (_lock) {
			for (int index = _references.size() - 1; index >= 0; index--) {
				Reference reference = _references.get(index);
				if (reference.match(locator)) {
					_references.remove(index);
					return reference.getComponent();
				}
			}
		}
		
		return null;
	}

	public List<Object> removeAll(Object locator) {
		List<Object> components = new ArrayList<Object>();
		
		if (locator == null) return components;

		synchronized (_lock) {
			for (int index = _references.size() - 1; index >= 0; index--) {
				Reference reference = _references.get(index);
				if (reference.match(locator)) {
					_references.remove(index);
					components.add(reference.getComponent());
				}
			}
		}
		
		return components;
	}
	
	public List<Object> getAllLocators()
    {
		List<Object> locators = new ArrayList<Object>();
		
		synchronized (_lock) {
			for (Reference reference : _references) 
				locators.add(reference.getComponent());
		}
		
        return locators;
    }

	public List<Object> getAll() {
		List<Object> components = new ArrayList<Object>();
		
		synchronized (_lock) {
			for (Reference reference : _references) 
				components.add(reference.getComponent());
		}
		
		return components;
	}
		
    public Object getOneOptional(Object locator) {
    	try {
	        List<Object> components = find(Object.class, locator, false);
	        return components.size() > 0 ? components.get(0) : null;
    	} catch (Exception ex) {
    		return null;
    	}
    }

    public <T> T getOneOptional(Class<T> type, Object locator) {
    	try {
	        List<T> components = find(type, locator, false);
	        return components.size() > 0 ? components.get(0) : null;
    	} catch (Exception ex) {
    		return null;
    	}
    }

    public Object getOneRequired(Object locator) throws ReferenceException {
        List<Object> components = find(Object.class, locator, true);
        return components.size() > 0 ? components.get(0) : null;
    }

    public <T> T getOneRequired(Class<T> type, Object locator) throws ReferenceException {
        List<T> components = find(type, locator, true);
        return components.size() > 0 ? components.get(0) : null;
    }

    public List<Object> getOptional(Object locator) {
    	try {
    		return find(Object.class, locator, false);
    	} catch (Exception ex) {
    		return new ArrayList<Object>();
    	}
    }

    public <T> List<T> getOptional(Class<T> type, Object locator) {
    	try {
    		return find(type, locator, false);
    	} catch (Exception ex) {
    		return new ArrayList<T>();
    	}
    }

    public List<Object> getRequired(Object locator) throws ReferenceException {
        return find(Object.class, locator, true);
    }

    public <T> List<T> getRequired(Class<T> type, Object locator) throws ReferenceException {
        return find(type, locator, true);
    }
    
    public List<Object> find(Object locator, boolean required) throws ReferenceException
    {
    	return find(Object.class, locator, required);
    }
    
    @SuppressWarnings("unchecked")
	public <T> List<T> find(Class<T> type, Object locator, boolean required) throws ReferenceException
    {
    	if (type == null)
    		throw new NullPointerException("Type cannot be null");
    	if (locator == null)
    		throw new NullPointerException("Locator cannot be null");        

        List<T> components = new ArrayList<T>();

        synchronized (_lock){
            // Search all references
            for (int index = _references.size() - 1; index >= 0; index--)
            {
                Reference reference = _references.get(index);
                if (reference.match(locator))
                {
                    Object component = reference.getComponent();
                    if ( type.isInstance(component))
                    {
                        components.add((T)component);
                    }
                }
            }
        }

        if (components.size() == 0 && required)
        {
            throw new ReferenceException(null, locator);
        }

        return components;
    }

    
    public void clear()
    {
    	synchronized (_lock) {
            _references.clear();
        }
    }
	
	public static References fromTuples(Object... tuples) throws ReferenceException {
		return new References(tuples);
	}
}
