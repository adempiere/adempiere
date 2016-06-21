/**
 * 
 */
package org.eevolution.model.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_AD_WF_Node;
import org.compiere.model.I_AD_Workflow;
import org.compiere.model.I_C_UOM;
import org.compiere.model.I_S_Resource;
import org.compiere.model.MResource;
import org.compiere.model.MResourceType;
import org.compiere.model.MUOM;
import org.compiere.model.PO;
import org.compiere.model.X_AD_Workflow;
import org.compiere.util.Env;
import org.compiere.wf.MWFNode;
import org.compiere.wf.MWorkflow;
import org.eevolution.model.I_PP_Cost_Collector;
import org.eevolution.model.I_PP_Order_Node;
import org.eevolution.model.MPPOrderNode;
import org.eevolution.model.RoutingService;

/**
 * Default Routing Service Implementation
 * @author Teo Sarca
 */
public class DefaultRoutingServiceImpl implements RoutingService
{
//	private final CLogger log = CLogger.getCLogger(getClass());
	
	public BigDecimal estimateWorkingTime(I_AD_WF_Node node)
	{
		final double duration;
		if (node.getUnitsCycles().signum() == 0)
		{
			duration = node.getDuration();
		}
		else
		{
			duration = node.getDuration() / node.getUnitsCycles().doubleValue();
		}
		return BigDecimal.valueOf(duration);
	}
	public BigDecimal estimateWorkingTime(I_PP_Order_Node node, BigDecimal qty)
	{
		BigDecimal unitDuration = new BigDecimal(node.getDuration());
		BigDecimal cycles = calculateCycles(node.getUnitsCycles(), qty);
		BigDecimal duration = unitDuration.multiply(cycles);
		return duration;
	}
	
	public BigDecimal estimateWorkingTime(I_PP_Cost_Collector cc)
	{
		final String trxName = (cc instanceof PO ? ((PO)cc).get_TrxName() : null);
		final BigDecimal qty = cc.getMovementQty();
		MPPOrderNode node = MPPOrderNode.get(Env.getCtx(), cc.getPP_Order_Node_ID(), trxName);
		return estimateWorkingTime(node, qty);
	}

	
	/**
	 * Calculate how many cycles are needed for given qty and units per cycle
	 * @param unitsCycle
	 * @param qty
	 * @return number of cycles
	 */
	protected BigDecimal calculateCycles(int unitsCycle, BigDecimal qty)
	{
		BigDecimal cycles = qty;
		BigDecimal unitsCycleBD = new BigDecimal(unitsCycle);
		if (unitsCycleBD.signum() > 0)
		{
			cycles = qty.divide(unitsCycleBD);
		}
		return cycles;
	}
	
	/**
	 * Calculate node duration in DurationUnit UOM (see AD_Workflow.DurationUnit)
	 * @param node
	 * @param cc
	 * @reeturn duration
	 */
	protected BigDecimal calculateDuration(I_AD_WF_Node node, I_PP_Cost_Collector cc)
	{
		if (node == null)
		{
			node = cc.getPP_Order_Node().getAD_WF_Node();
		}
		final I_AD_Workflow workflow = node.getAD_Workflow();
		final double batchSize = workflow.getQtyBatchSize().doubleValue();
		final double setupTime;
		final double duration;
		if (cc != null)
		{
			setupTime = cc.getSetupTimeReal().doubleValue();
			duration = cc.getDurationReal().doubleValue();
		}
		else
		{
			setupTime = node.getSetupTime();
			// Estimate total duration for 1 unit of final product as duration / units cycles
			duration = estimateWorkingTime(node).doubleValue(); 
		}
		
		double totalDuration;
		if(batchSize > 0)
			totalDuration = ((setupTime / batchSize) + duration);
		else
			totalDuration = setupTime  + duration;
		
		return BigDecimal.valueOf(totalDuration);
	}
	
	public BigDecimal calculateDuration(I_AD_WF_Node node)
	{
		return calculateDuration(node, null);
	}
	public BigDecimal calculateDuration(I_PP_Cost_Collector cc)
	{
		return calculateDuration(getAD_WF_Node(cc), cc);
	}

	public BigDecimal calculateDuration(I_AD_Workflow wf, I_S_Resource plant, BigDecimal qty)
	{
		if (plant == null)
			return Env.ZERO;
		final Properties ctx = ((PO)wf).getCtx();
		final MResourceType S_ResourceType = MResourceType.get(ctx, plant.getS_ResourceType_ID());  	

		BigDecimal AvailableDayTime  = new BigDecimal(S_ResourceType.getTimeSlotHours());
		int AvailableDays = S_ResourceType.getAvailableDaysWeek();

		double durationBaseSec = getDurationBaseSec(wf.getDurationUnit());

		double durationTotal = 0.0; 
		MWFNode[] nodes = ((MWorkflow)wf).getNodes(false, Env.getAD_Client_ID(ctx));
		for (I_AD_WF_Node node : nodes)
		{
			// Qty independent times:
			durationTotal += node.getQueuingTime();
			durationTotal += node.getSetupTime();
			durationTotal += node.getWaitingTime();
			durationTotal += node.getMovingTime();
			
			// Get OverlapUnits - number of units that must be completed before they are moved the next activity 
			double overlapUnits = qty.doubleValue();
			if (node.getOverlapUnits() > 0 && node.getOverlapUnits() < overlapUnits)
			{
				overlapUnits = node.getOverlapUnits();
			}
			double durationBeforeOverlap = node.getDuration() * overlapUnits;
			
			durationTotal += durationBeforeOverlap;
		}
		BigDecimal requiredTime = BigDecimal.valueOf(durationTotal * durationBaseSec / 60 / 60);
		// TODO: implement here, Victor's suggestion - https://sourceforge.net/forum/message.php?msg_id=5179460

		// Weekly Factor  	
		BigDecimal WeeklyFactor = new BigDecimal(7).divide(new BigDecimal(AvailableDays), 8, RoundingMode.UP);

		return (requiredTime.multiply(WeeklyFactor)).divide(AvailableDayTime, 0, RoundingMode.UP);
	}

	protected BigDecimal convertDurationToResourceUOM(BigDecimal duration, int S_Resource_ID, I_AD_WF_Node node)
	{
		MResource resource = MResource.get(Env.getCtx(), S_Resource_ID);
		I_AD_Workflow wf = MWorkflow.get(Env.getCtx(), node.getAD_Workflow_ID());
		I_C_UOM resourceUOM = MUOM.get(Env.getCtx(), resource.getC_UOM_ID());
		return convertDuration(duration, wf.getDurationUnit(), resourceUOM);
	}
	
	@Override
	public BigDecimal getResourceBaseValue(int S_Resource_ID, I_PP_Cost_Collector cc)
	{
		return getResourceBaseValue(S_Resource_ID, null, cc);
	}
	@Override
	public BigDecimal getResourceBaseValue(int S_Resource_ID, I_AD_WF_Node node)
	{
		return getResourceBaseValue(S_Resource_ID, node, null);
	}
	protected BigDecimal getResourceBaseValue(int S_Resource_ID, I_AD_WF_Node node, I_PP_Cost_Collector cc)
	{
		if (node == null)
			node = cc.getPP_Order_Node().getAD_WF_Node();
		final Properties ctx = (node instanceof PO ? ((PO)node).getCtx() : Env.getCtx());
		final MResource resource = MResource.get(ctx, S_Resource_ID);
		final MUOM resourceUOM = MUOM.get(ctx, resource.getC_UOM_ID());
		//
		if (isTime(resourceUOM))
		{
			BigDecimal duration = calculateDuration(node, cc);
			I_AD_Workflow wf = MWorkflow.get(ctx, node.getAD_Workflow_ID());
			BigDecimal convertedDuration = convertDuration(duration, wf.getDurationUnit(), resourceUOM);
			return convertedDuration;
		}
		else
		{
			throw new AdempiereException("@NotSupported@ @C_UOM_ID@ - "+resourceUOM);
		}
	}

	protected I_AD_WF_Node getAD_WF_Node(I_PP_Cost_Collector cc)
	{
		I_PP_Order_Node activity = cc.getPP_Order_Node();
		return activity.getAD_WF_Node();
	}
	
	/**
	 * Convert durationUnit to seconds
	 * @param durationUnit
	 * @return duration in seconds
	 */
	public long getDurationBaseSec (String durationUnit)
	{
		if (durationUnit == null)
			return 0;
		else if (X_AD_Workflow.DURATIONUNIT_Second.equals(durationUnit))
			return 1;
		else if (X_AD_Workflow.DURATIONUNIT_Minute.equals(durationUnit))
			return 60;
		else if (X_AD_Workflow.DURATIONUNIT_Hour.equals(durationUnit))
			return 3600;
		else if (X_AD_Workflow.DURATIONUNIT_Day.equals(durationUnit))
			return 86400;
		else if (X_AD_Workflow.DURATIONUNIT_Month.equals(durationUnit))
			return 2592000;
		else if (X_AD_Workflow.DURATIONUNIT_Year.equals(durationUnit))
			return 31536000;
		return 0;
	}	//	getDurationSec
	
	/**
	 * Convert uom to seconds
	 * @param uom time UOM 
	 * @return duration in seconds
	 * @throws AdempiereException if UOM is not supported
	 */
	public long getDurationBaseSec(I_C_UOM uom)
	{
		MUOM uomImpl = (MUOM)uom;
		//
		if(uomImpl.isWeek())
		{
			return 60*60*24*7;
		}
		if(uomImpl.isDay())
		{
			return 60*60*24;
		}
		else if (uomImpl.isHour())
		{
			return 60*60;
		}
		else if (uomImpl.isMinute())
		{
			return 60;
		}
		else if (uomImpl.isSecond())
		{
			return 1;
		}
		else
		{
			throw new AdempiereException("@NotSupported@ @C_UOM_ID@="+uom.getName());
		}
	}
	
	/**
	 * Check if it's an UOM that measures time 
	 * @param uom 
	 * @return true if is time UOM
	 */
	public boolean isTime(I_C_UOM uom)
	{
		String x12de355 = uom.getX12DE355();
		return MUOM.X12_SECOND.equals(x12de355)
		|| MUOM.X12_MINUTE.equals(x12de355)
		|| MUOM.X12_HOUR.equals(x12de355)
		|| MUOM.X12_DAY.equals(x12de355)
		|| MUOM.X12_DAY_WORK.equals(x12de355)
		|| MUOM.X12_WEEK.equals(x12de355)
		|| MUOM.X12_MONTH.equals(x12de355)
		|| MUOM.X12_MONTH_WORK.equals(x12de355)
		|| MUOM.X12_YEAR.equals(x12de355)
		;
	}
	
	/**
	 * Convert duration from given UOM to given UOM
	 * @param duration
	 * @param fromDurationUnit duration UOM
	 * @param toUOM target UOM
	 * @return duration converted to toUOM
	 */
	public BigDecimal convertDuration(BigDecimal duration, String fromDurationUnit, I_C_UOM toUOM)
	{
		double fromMult = getDurationBaseSec(fromDurationUnit);
		double toDiv = getDurationBaseSec(toUOM);
		BigDecimal convertedDuration = BigDecimal.valueOf(duration.doubleValue() * fromMult / toDiv);
		//https://adempiere.atlassian.net/browse/MFG-4
		// Adjust scale to UOM precision
		/*int precision = toUOM.getStdPrecision();
		if (convertedDuration.scale() > precision)
			convertedDuration = convertedDuration.setScale(precision, RoundingMode.HALF_UP);*/
		//
		return convertedDuration;
	}
}
