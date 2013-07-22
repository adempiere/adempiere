package org.adempiere.util.trxConstraints.api;

import org.adempiere.exceptions.AdempiereException;

/**
 * Exception can be thrown if the system detects a violation of an {@link ITrxConstraints} instance.
 * 
 * @author ts
 * 
 */
public class TrxConstraintException extends AdempiereException
{
	private static final long serialVersionUID = -8255074450371034066L;

	public TrxConstraintException(String msg)
	{
		super(msg);
	}
}
