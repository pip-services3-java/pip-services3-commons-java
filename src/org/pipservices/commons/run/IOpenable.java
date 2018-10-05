package org.pipservices.commons.run;

import org.pipservices.commons.errors.*;

/**
 * Interface for components that require explicit opening and closing.
 * 
 * For components that perform opening on demand consider using
 * {@link IClosable} interface instead.
 * <p>
 * ### Example ###
 * <pre>
 * {@code
 * class MyPersistence implements IOpenable {
 *   private Object _client;
 *   ...
 *   public boolean isOpen() {
 *     return this._client != null;
 *   } 
 *   
 *   public void open(String correlationId) {
 *     if (this.isOpen()) {
 *       return;
 *     }
 *     ...
 *   }
 * 
 *   public void close(String correlationId) {
 *     if (this._client != null) {
 *       this._client.close();
 *       this._client = null;
 *     }
 *   }
 *  
 *   ...
 * }
 * }
 * </pre>
 * @see IOpenable
 * @see Opener
 */
public interface IOpenable extends IClosable {
	/**
	 * Checks if the component is opened.
	 * 
	 * @return true if the component has been opened and false otherwise.
	 */
	boolean isOpen();

	/**
	 * Opens the component.
	 * 
	 * @param correlationId (optional) transaction id to trace execution through
	 *                      call chain.
	 * @throws ApplicationException when error or null no errors occured.
	 */
	void open(String correlationId) throws ApplicationException;
}
