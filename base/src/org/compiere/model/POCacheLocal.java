package org.compiere.model;

import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.InterfaceWrapperHelper;
import org.compiere.util.CLogger;
import org.compiere.util.Util;

/**
 * @author tsa
 */
public final class POCacheLocal
{
	private static final CLogger logger = CLogger.getCLogger(POCacheLocal.class);

	private final PO parentPO;
	private final String parentColumnName;
	private final String tableName;
	private final String idColumnName;

	private final String loadWhereClause;

	private PO po = null;

	public static POCacheLocal newInstance(PO parent, String parentColumnName, String tableName)
	{
		return new POCacheLocal(parent, parentColumnName, tableName);
	}

	private POCacheLocal(PO parent, String parentColumnName, String tableName)
	{
		Util.assume(parent != null, "parent is null");
		Util.assume(parentColumnName != null, "parentColumnName is null");
		Util.assume(tableName != null, "tableName");

		this.parentPO = parent;
		this.parentColumnName = parentColumnName;
		this.tableName = tableName;

		final POInfo poInfo = POInfo.getPOInfo(parent.getCtx(), tableName);
		this.idColumnName = poInfo.getKeyColumnName();
		if (idColumnName == null)
		{
			throw new IllegalStateException("Table " + tableName + " does not have a simple primary key");
		}

		this.loadWhereClause = idColumnName + "=?";
	}

	public <T> T get(Class<T> clazz)
	{
		return get(clazz, false);
	}

	public <T> T get(Class<T> clazz, boolean requery)
	{
		int id = getId();
		if (id <= 0)
		{
			if (po != null && !po.is_new())
			{
				// Case: there is no ID set but our cached PO is not new => reset PO, return null
				this.po = null;
				return null;
			}
			
			if (po != null)
			{
				// Case: we have a new object => we just return it
				return InterfaceWrapperHelper.create(this.po, clazz);
			}
			else
			{
				// No object set, just return null
				return null;
			}
		}
		
		if (requery || po == null || !isValidPO(this.po))
		{
			this.po = load(this.parentPO.getCtx(), id, this.parentPO.get_TrxName());
		}
		if (this.po == null)
		{
			return null;
		}

		return InterfaceWrapperHelper.create(this.po, clazz);
	}

	public void set(Object obj)
	{
		final PO po = InterfaceWrapperHelper.getPO(obj);
		if (po == null && obj != null)
		{
			throw new AdempiereException("Invalid PO: " + obj);
		}

		//
		//
		if (po == null)
		{
			setId(-1);
			this.po = null;
			return;
		}

		final int id = po.get_ID();
		setId(id);

		if (isValidPO(po))
		{
			this.po = po;
		}
	}

	private boolean isValidPO(PO po)
	{
		if (!isSameTrxName(this.parentPO.get_TrxName(), po.get_TrxName()))
		{
			return false;
		}

		if (!po.get_TableName().equals(tableName))
		{
			logger.warning("PO " + po + " does not expected table: " + tableName);
			return false;
		}

		final int id = getId();
		if (id < 0)
		{
			return false;
		}

		if (po.get_ID() != id)
		{
			return false;
		}

		return true;
	}

	private boolean isSameTrxName(String trxName1, String trxName2)
	{
		if (trxName1 == trxName2)
			return true;
		if (trxName1 == null)
			return false;
		return trxName1.equals(trxName2);
	}

	private PO load(Properties ctx, int id, String trxName)
	{
		if (id < 0)
			return null;

		final PO po = new Query(ctx, this.tableName, this.loadWhereClause, trxName)
				.setParameters(id)
				.firstOnly();
		return po;
	}

	private int getId()
	{
		final int id = parentPO.get_ValueAsInt(parentColumnName);
		return id;
	}

	private boolean setId(int id)
	{
		final Integer value = id < 0 ? null : id;
		final boolean ok = parentPO.set_ValueOfColumnReturningBoolean(parentColumnName, value);
		if (!ok)
		{
			logger.warning("Cannot set " + parentColumnName + "=" + id);
		}
		return ok;
	}

	public String getTableName()
	{
		return tableName;
	}

	@Override
	public String toString()
	{
		return "POCacheLocal ["
				+ "parentPO=" + parentPO
				+ ", parentColumnName=" + parentColumnName
				+ ", tableName=" + tableName
				+ ", idColumnName=" + idColumnName
				+ ", loadWhereClause=" + loadWhereClause
				+ ", po=" + po
				+ "]";
	}

	public POCacheLocal copy(PO parentPO)
	{
		final POCacheLocal poCacheLocalNew = newInstance(parentPO, parentColumnName, tableName);
		poCacheLocalNew.po = this.po;
		return poCacheLocalNew;
	}
}
