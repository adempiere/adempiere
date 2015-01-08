/**
 * 
 */
package org.eevolution.exceptions;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_S_Resource;
import org.compiere.process.DocAction;
import org.eevolution.model.I_PP_Order;
import org.eevolution.model.I_PP_Order_Node;

/**
 * @author teo_sarca
 *
 */
public class CRPException extends AdempiereException
{
	private I_PP_Order order = null;
	private I_PP_Order_Node node = null;
	private I_S_Resource resource = null;
	
	public CRPException(String message)
	{
		super(message);
	}
	
	public CRPException(Exception e)
	{
		super(e);
	}
	
	public CRPException setPP_Order(I_PP_Order order)
	{
		this.order = order;
		return this;
	}
	public CRPException setPP_Order_Node(I_PP_Order_Node node)
	{
		this.node = node;
		return this;
	}

	public CRPException setS_Resource(I_S_Resource resource)
	{
		this.resource  = resource;
		return this;
	}

	@Override
	public String getMessage()
	{
		String msg = super.getMessage();
		StringBuffer sb = new StringBuffer(msg);
		//
		if (this.order != null)
		{
			final String info;
			if (order instanceof DocAction)
			{
				info = ((DocAction)order).getSummary();
			}
			else
			{
				info = ""+order.getDocumentNo()+"/"+order.getDatePromised();
			}
			sb.append(" @PP_Order_ID@:").append(info);
		}
		if (this.node != null)
		{
			sb.append(" @PP_Order_Node_ID@:").append(node.getValue()).append("_").append(node.getName());
		}
		if (this.resource != null)
		{
			sb.append(" @S_Resource_ID@:").append(resource.getValue()).append("_").append(resource.getName());
		}
		//
		return sb.toString();
	}
	
	
}
