/**
 * 
 */
package org.eevolution.hr.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.core.domains.models.X_HR_Concept_Category;
import org.compiere.model.Query;
import org.compiere.util.CCache;
import org.compiere.util.Env;

/**
 * HR Concept Category
 * 
 * @author Cristina Ghita, www.arhipac.ro
 */
public class MHRConceptCategory extends X_HR_Concept_Category
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8470029939291479283L;

	private static CCache<Integer, MHRConceptCategory> s_cache = new CCache<Integer, MHRConceptCategory>(Table_Name, 20);
	private static CCache<String, MHRConceptCategory> s_cacheValue = new CCache<String, MHRConceptCategory>(Table_Name+"_Value", 20);

	/**
	 *
	 * @param ctx
	 * @param conceptCategoryId
	 * @return
	 */
	@Deprecated
	public static MHRConceptCategory get(Properties ctx, int conceptCategoryId)
	{
		return getById(ctx, conceptCategoryId, null);
	}

	/**
	 * Get Payroll Concpet Category
	 * @param ctx
	 * @param conceptCategoryId
	 * @param trxName
	 * @return
	 */
	public static MHRConceptCategory getById(Properties ctx, int conceptCategoryId, String trxName)
	{
		if (conceptCategoryId <= 0)
			return null;
		// Try cache
		MHRConceptCategory conceptCategory = s_cache.get(conceptCategoryId);
		if (conceptCategory != null)
			return conceptCategory;

		// Load from DB
		conceptCategory = new MHRConceptCategory(ctx, conceptCategoryId, trxName);
		if (conceptCategory.get_ID() != conceptCategoryId)
			return null;

		if (conceptCategory != null)
			s_cache.put(conceptCategoryId, conceptCategory);

		return conceptCategory;
	}

	/**
	 * Ger Concept Category by Value
	 * @param ctx
	 * @param value
	 * @return
	 */
	@Deprecated
	public static MHRConceptCategory forValue(Properties ctx, String value)
	{
		return getByValue(ctx, value , null);
	}

	/**
	 * Ger Concept Category by Value
	 * @param ctx
	 * @param value
	 * @param trxName
	 * @return
	 */
	public static MHRConceptCategory getByValue(Properties ctx, String value, String trxName)
	{
		if (value == null)
			return null;
		final int clientId = Env.getAD_Client_ID(ctx);
		// Try cache
		final String key = clientId+"#"+value;
		MHRConceptCategory conceptCategory = s_cacheValue.get(key);
		if (conceptCategory != null)
			return conceptCategory;
		// Try database
		final String whereClause = COLUMNNAME_Value+"=? AND AD_Client_ID IN (?,?)";
		conceptCategory = new Query(ctx, Table_Name, whereClause, trxName)
				.setParameters(value, 0, clientId)
				.setOnlyActiveRecords(true)
				.setOrderBy("AD_Client_ID DESC")
				.first();
		if (conceptCategory != null)
		{
			s_cacheValue.put(key, conceptCategory);
			s_cache.put(conceptCategory.get_ID(), conceptCategory);
		}
		return conceptCategory;
	}

	public MHRConceptCategory(Properties ctx, int HR_Concept_Category_ID, String trxName)
	{
		super(ctx, HR_Concept_Category_ID, trxName);
	}
	public MHRConceptCategory(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}
}
