package org.pipservices.commons.refer;

import org.pipservices.commons.errors.ConfigException;

/**
 * Interface for components that depends on other components. 
 * 
 * If component requires explicit notification to unset references
 * it shall additionally implement IUnreferenceable interface.
 * <p>
 * ### Example ###
 * <pre>
 * {@code
 *  public class MyController implements IReferenceable {
 *     public IMyPersistence _persistence;
 *     ...    
 *     public void setReferences(IReferences references) {
 *       this._persistence = (IMyPersistence)references.getOneRequired(
 *         new Descriptor("mygroup", "persistence", "*", "*", "1.0")
 *       );
 *     }
 *     ...
 *  }
 *  }
 *  </pre>
 *  
 * @see IReferences
 * @see IUnreferenceable
 * @see Referencer
 */
public interface IReferenceable {
	/**
	 * Sets references to dependent components.
	 * 
	 * @param references references to locate the component dependencies.
	 * @throws ReferenceException when no references found.
	 * @throws ConfigException when configuration is wrong
	 * @see IReferences
	 */
	void setReferences(IReferences references) throws ReferenceException, ConfigException;
}
