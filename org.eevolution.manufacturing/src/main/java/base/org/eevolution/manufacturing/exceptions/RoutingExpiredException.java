/**
 * 
 */
package org.eevolution.manufacturing.exceptions;

import java.sql.Timestamp;

import org.adempiere.core.domains.models.I_AD_Workflow;
import org.adempiere.exceptions.AdempiereException;

/**
 * Thrown when Routing(Workflow) is not valid on given date
 * @author Teo Sarca
 */
public class RoutingExpiredException extends AdempiereException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7522979292063177848L;

	public RoutingExpiredException(I_AD_Workflow wf, Timestamp date)
	{
		super(buildMessage(wf, date));
	}
	
	private static final String buildMessage(I_AD_Workflow wf, Timestamp date)
	{
		return "@NotValid@ @AD_Workflow_ID@:"+wf.getValue()+" - @Date@:"+date;
	}

}
