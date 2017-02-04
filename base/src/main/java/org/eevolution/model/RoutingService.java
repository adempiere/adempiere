/**
 * 
 */
package org.eevolution.model;

import java.math.BigDecimal;

import org.compiere.model.I_AD_WF_Node;
import org.compiere.model.I_AD_Workflow;
import org.compiere.model.I_S_Resource;

/**
 * Rounting(Workflow Service)
 * @author Teo Sarca, www.arhipac.ro
 */
public interface RoutingService
{
	public BigDecimal estimateWorkingTime(I_AD_WF_Node node);
	
	/**
	 * Estimate Activity Working Time for given qty.
	 * Please not that SetupTime or any other times are not considered.
	 * @param node activity
	 * @param qty qty required
	 * @return working time (using Workflow DurationUnit UOM)
	 */
	public BigDecimal estimateWorkingTime(I_PP_Order_Node node, BigDecimal qty);
	
	public BigDecimal estimateWorkingTime(I_PP_Cost_Collector cc);

	/**
	 * Calculate node duration for 1 item, AD_Workflow.DurationUnit UOM will be used
	 * @param node operation
	 * @return node duration for 1 item (AD_Workflow.DurationUnit UOM)
	 */
	public BigDecimal calculateDuration(I_AD_WF_Node node);
	
	/**
	 * Calculate workflow duration for given qty
	 * @param node operation
	 * @return node duration for 1 item (AD_Workflow.DurationUnit UOM)
	 */
	public BigDecimal calculateDuration(I_AD_Workflow wf, I_S_Resource plant, BigDecimal qty);
	
	/**
	 * Calculate activity duration based on reported data from Cost Collector.
	 * @param cc cost collector
	 * @return activity duration (using Workflow DurationUnit UOM)
	 */
	public BigDecimal calculateDuration(I_PP_Cost_Collector cc);
	
	/**
	 * Return cost collector base value in resource UOM (e.g. duration)
	 * @param S_Resource_ID resource
	 * @param cc cost collector
	 * @return value (e.g. duration)
	 */
	public BigDecimal getResourceBaseValue(int S_Resource_ID, I_PP_Cost_Collector cc);

	/**
	 * Return node base value in resource UOM (e.g. duration)
	 * @param S_Resource_ID resource
	 * @param node
	 * @return value (e.g. duration)
	 */
	public BigDecimal getResourceBaseValue(int S_Resource_ID, I_AD_WF_Node node);
}
