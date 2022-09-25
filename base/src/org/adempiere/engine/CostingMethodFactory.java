/**
 * 
 */
package org.adempiere.engine;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.adempiere.core.domains.models.I_M_CostType;
import org.adempiere.core.domains.models.X_M_CostType;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MCostType;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.compiere.util.Util;


/**
 * @author teo_sarca
 *
 */
public class CostingMethodFactory
{
	private static final CostingMethodFactory s_instance = new CostingMethodFactory();
	
	public static CostingMethodFactory get()
	{
		return s_instance;
	}
	
	private Map<String, Class<? extends ICostingMethod>>
	s_map = new HashMap<String, Class<? extends ICostingMethod>>();
	
	private CostingMethodFactory()
	{
		s_map.put(X_M_CostType.COSTINGMETHOD_Fifo, FifoLifoCostingMethod.class);
		s_map.put(X_M_CostType.COSTINGMETHOD_Lifo, FifoLifoCostingMethod.class);
		s_map.put(X_M_CostType.COSTINGMETHOD_AverageInvoice, AverageInvoiceCostingMethod.class);
		s_map.put(X_M_CostType.COSTINGMETHOD_AveragePO, AveragePOCostingMethod.class);
		s_map.put(X_M_CostType.COSTINGMETHOD_LastInvoice, LastInvoiceCostingMethod.class);
		s_map.put(X_M_CostType.COSTINGMETHOD_LastPOPrice, LastPOPriceCostingMethod.class);
		s_map.put(X_M_CostType.COSTINGMETHOD_StandardCosting, StandardCostingMethod.class);
		new Query(Env.getCtx(), I_M_CostType.Table_Name, "", null)
		.setOnlyActiveRecords(true)
		.setClient_ID()
		.<MCostType>list()
		.forEach(costType ->{
			Optional<String> maybeClassName = Optional.ofNullable(costType.getClassname());
			maybeClassName.ifPresent(className ->{
				if (!Util.isEmpty(className)) {
					try {
						@SuppressWarnings("unchecked")
						Class<? extends ICostingMethod> clazz = (Class<? extends ICostingMethod>) Class.forName(className);
						s_map.put(costType.getCostingMethod(), clazz);
					} catch (ClassNotFoundException e) {
						throw new AdempiereException("No implementation found for costing method "+costType.getCostingMethod() + " - " + className.toString());
					}
				}
			});
		});
	}

	
	/**
	 * Get Costing method
	 * @param ce cost element
	 * @param costingMethod costing method. Optional. If null, we get the costing method
	 * 					from cost element
	 * @return costing method class instance
	 */
	public ICostingMethod getCostingMethod(String costingMethod)
	{
		Class<? extends ICostingMethod> cl = s_map.get(costingMethod);
		if (cl == null)
		{
			throw new AdempiereException("No implementation found for costing method "+costingMethod);
		}
		ICostingMethod cm;
		try
		{
			cm = cl.newInstance();
		}
		catch (Exception e)
		{
			throw new AdempiereException(e);
		}
		return cm;
	}
}
