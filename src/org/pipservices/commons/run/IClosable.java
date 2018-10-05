package org.pipservices.commons.run;

import org.pipservices.commons.errors.*;

/**
 * Interface for components that require explicit closure.
 * <p>
 * For components that require opening as well as closing 
 * use {@link IOpenable} interface instead.
 * <p>
 * ### Example ###
 * <pre>
 * {@code
 * class MyConnector implements ICloseable {
 *   private Object _client = null;
 * 
 *   ... // The _client can be lazy created
 * 
 *   public void close(String correlationId) {
 *     if (this._client != null) {
 *       this._client.close();
 *       this._client = null;
 *     }
 *   }
 * }
 * }
 * </pre>
 * @see IOpenable
 * @see Closer
 */
public interface IClosable {
	/**
	 * Closes component and frees used resources.
	 * 
	 * @param correlationId (optional) transaction id to trace execution through
	 *                      call chain.
	 * @throws ApplicationException when error or null no errors occured.
	 */
	void close(String correlationId) throws ApplicationException;
}
