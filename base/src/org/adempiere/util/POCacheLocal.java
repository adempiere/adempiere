/**
 * 
 */
package org.adempiere.util;

import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MTable;
import org.compiere.model.PO;

/**
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 */
public final class POCacheLocal<T extends PO>
{
	private final PO parent;
	private final String idColumnName;
	private final String po_tableName;
	private T po = null;
	
	public static <T extends PO> POCacheLocal<T> newInstance(PO parent, Class<T> cl)
	{
		return new POCacheLocal<T>(parent, cl);
	}
	
	public static <T extends PO> POCacheLocal<T> newInstance(PO parent, Class<T> cl, String idColumnName)
	{
		return new POCacheLocal<T>(parent, cl, idColumnName);
	}
	
	private POCacheLocal(PO parent, Class<T> cl)
	{
		this(parent, cl, null);
	}
	
	private POCacheLocal(PO parent, Class<T> cl, String idColumnName)
	{
		this.parent = parent;
		try
		{
			this.po_tableName = (String)cl.getField("Table_Name").get(null);
		}
		catch (Exception e)
		{
			throw new AdempiereException(e);
		}
		if (idColumnName == null)
		{
			this.idColumnName = this.po_tableName + "_ID";
		}
		else
		{
			this.idColumnName = idColumnName;
		}
	}
	
	public T get(boolean requery)
	{
		int id = get_id();
		if (id <= 0)
		{
			this.po = null;
			return null;
		}
		if (requery || !isValidPO(this.po))
		{
			this.po = load(this.parent.getCtx(), id, this.parent.get_TrxName());
		}
		return this.po;
	}
	
	public void set(T po)
	{
		if (isValidPO(po))
		{
			this.po = po;
		}
	}
	
	private boolean isValidPO(T po)
	{
		int id = get_id();
		return id > 0
				&& po != null
				&& po.get_ID() == id
				&& Util.equals(this.parent.get_TrxName(), po.get_TrxName())
		;
	}

	@SuppressWarnings("unchecked")
	protected T load(Properties ctx, int id, String trxName)
	{
		return (T)MTable.get(ctx, this.po_tableName).getPO(id, trxName);
	}
	
	private int get_id()
	{
		return parent.get_ValueAsInt(idColumnName);
	}
}
