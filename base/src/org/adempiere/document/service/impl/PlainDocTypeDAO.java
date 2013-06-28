package org.adempiere.document.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.adempiere.ad.wrapper.IPOJOFilter;
import org.adempiere.ad.wrapper.POJOLookupMap;
import org.adempiere.document.service.IDocTypeDAO;
import org.adempiere.util.TypedAccessor;
import org.adempiere.util.comparator.AccessorComparator;
import org.adempiere.util.comparator.ComparableComparator;
import org.compiere.model.I_C_DocType;

public class PlainDocTypeDAO implements IDocTypeDAO
{
	private final POJOLookupMap db = POJOLookupMap.get();

	public POJOLookupMap getDB()
	{
		return db;
	}

	public void dumpStatus()
	{
		db.dumpStatus();
	}

	@Override
	public int getDocTypeIdOrNull(Properties ctx, final String docBaseType, final int adClientId, final int adOrgId, String trxName)
	{
		final List<I_C_DocType> result = db.getRecords(I_C_DocType.class, new IPOJOFilter<I_C_DocType>()
		{

			@Override
			public boolean accept(I_C_DocType pojo)
			{
				if (!pojo.isActive())
				{
					return false;
				}

				if (pojo.getAD_Client_ID() != adClientId)
				{
					return false;
				}

				if (pojo.getAD_Org_ID() != 0 && pojo.getAD_Org_ID() != adOrgId)
				{
					return false;
				}

				if (pojo.getDocBaseType() != docBaseType)
				{
					return false;
				}

				return true;
			}

		});

		Collections.sort(result, new AccessorComparator<I_C_DocType, Boolean>(
				new ComparableComparator<Boolean>(),
				new TypedAccessor<Boolean>()
				{

					@Override
					public Boolean getValue(Object o)
					{
						return ((I_C_DocType)o).isDefault();
					}
				}));
		Collections.reverse(result);

		if (result.isEmpty())
		{
			return -1;
		}

		if (result.get(0).isDefault())
		{
			return result.get(0).getC_DocType_ID();
		}
		
		Collections.sort(result, new AccessorComparator<I_C_DocType, Integer>(
				new ComparableComparator<Integer>(),
				new TypedAccessor<Integer>()
				{

					@Override
					public Integer getValue(Object o)
					{
						return ((I_C_DocType)o).getAD_Org_ID();
					}
				}));
		
		Collections.reverse(result);

		return result.get(0).getC_DocType_ID();
	}

}
