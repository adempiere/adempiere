/**
 * 
 */
package org.adempiere.pipo.exception;

import org.adempiere.exceptions.AdempiereException;

/**
 * Throws when IDFinder methods have found more then one id (result) for search criteria
 * @author Teo Sarca, teo.sarca@gmail.com
 *
 */
public class NonUniqueIDLookupException extends AdempiereException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3523717056935105698L;

	public NonUniqueIDLookupException(String what)
	{
		super("Non Unique ID Lookup found for "+what);
	}
}
