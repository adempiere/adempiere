/**
 * 
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.DBException;
import org.compiere.util.DB;

/**
 * Included Role Model
 * @author teo.sarca@gmail.com
 */
public class MRoleIncluded extends X_AD_Role_Included
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3284165639631581484L;

	public MRoleIncluded(Properties ctx, int AD_Role_Included_ID, String trxName)
	{
		super(ctx, AD_Role_Included_ID, trxName);
	}

	public MRoleIncluded(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}

	@Override
	protected boolean beforeSave(boolean newRecord)
	{
		if (getAD_Role_ID() == getIncluded_Role_ID())
		{
			throw new AdempiereException("@AD_Role_ID@ == @Included_Role_ID@");
		}
		return true;
	}
	
	@Override
	protected boolean afterSave(boolean newRecord, boolean success)
	{
		if (!success)
			return success;
		//
		if (newRecord || is_ValueChanged(COLUMNNAME_Included_Role_ID))
		{
			List<Integer> trace = new ArrayList<Integer>();
			if (hasLoop(Table_Name, COLUMNNAME_Included_Role_ID, COLUMNNAME_AD_Role_ID, getIncluded_Role_ID(), trace, get_TrxName()))
			{
				StringBuffer roles = new StringBuffer();
				for (int role_id : trace)
				{
					MRole role = MRole.get(getCtx(), role_id);
					if (roles.length() > 0)
						roles.append(" - ");
					roles.append(role.getName());
				}
				throw new AdempiereException("Loop has detected "+roles);
			}
		}
		//
		return true;
	}

	/**
	 * Check if there is a loop in the tree defined in given table
	 * @param tableName
	 * @param idColumnName Node_ID column name
	 * @param parentIdColumnName Parent_ID column name
	 * @param nodeId current Node_ID
	 * @param trace current tree path (optional)
	 * @param trxName transaction name
	 * @return true if loop detected. If you specified not null trace, you will have in that list the IDs from the loop
	 */
	// TODO: refactor this method and move into org.compiere.util.DB class because it's general and usefull of others too
	private static boolean hasLoop(String tableName, String idColumnName, String parentIdColumnName,
			int nodeId, List<Integer> trace,
			String trxName)
	{
		final List<Integer> trace2;
		if (trace == null)
		{
			trace2 = new ArrayList<Integer>(10);
		}
		else
		{
			trace2 = new ArrayList<Integer>(trace);
		}
		trace2.add(nodeId);
		//
		final String sql = "SELECT "+idColumnName+","+parentIdColumnName+" FROM "+tableName+" WHERE "+parentIdColumnName+"=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, nodeId);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				final int childId = rs.getInt(1);
				if (trace2.contains(childId))
				{
					trace.clear();
					trace.addAll(trace2);
					trace.add(childId);
					return true;
				}
				if (hasLoop(tableName, idColumnName, parentIdColumnName, childId, trace2, trxName))
				{
					trace.clear();
					trace.addAll(trace2);
					return true;
				}
			}
		}
		catch(SQLException e)
		{
			throw new DBException(e, sql);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		//
		return false;
	}

}
