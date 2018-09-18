package org.pipservices.commons.run;

import org.pipservices.commons.errors.*;

/**
 * Interface for components that should clean their state.
 * 
 * Cleaning state most often is used during testing. 
 * But there may be situations when it can be done in production.
 * <p>
 * ### Example ###
 * <pre>
 * {@code
 * class MyObjectWithState implements ICleanable {
 *   Object[] _state = new Object[]{};
 *   ...
 *   public void clear(String correlationId) {
 *     this._state = new Object[]{};
 *   }
 * }
 * }
 * </pre>
 * @see Cleaner
 */
public interface ICleanable {
	/**
	 * Clears component state.
	 * 
	 * @param correlationId (optional) transaction id to trace execution through
	 *                      call chain.
	 * @throws ApplicationException when error or null no errors occured.
	 */
	void clear(String correlationId) throws ApplicationException;
}
