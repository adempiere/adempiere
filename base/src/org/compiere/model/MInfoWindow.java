/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.AccessSqlParser.TableInfo;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Info Window Model
 * 
 * @author Jorg Janke
 * @version $Id: MInfoWindow.java,v 1.2 2006/07/30 00:51:03 jjanke Exp $
 */
public class MInfoWindow extends X_AD_InfoWindow
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 4040291733093824436L;
	/**
	 * cache list process button info
	 */
	private MInfoProcess[]		m_infoProcess;

	/**
	 * Standard Constructor
	 * 
	 * @param ctx context
	 * @param AD_InfoWindow_ID id
	 * @param trxName transaction
	 */
	public MInfoWindow(Properties ctx, int AD_InfoWindow_ID, String trxName)
	{
		super(ctx, AD_InfoWindow_ID, trxName);
	} // MInfoWindow

	/**
	 * Load Constructor
	 * 
	 * @param ctx context
	 * @param rs result set
	 * @param trxName transaction
	 */
	public MInfoWindow(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	} // MInfoWindow

	public static MInfoWindow get(String tableName, String trxName)
	{
		Query query = new Query(Env.getCtx(), MTable.get(Env.getCtx(), MInfoWindow.Table_ID),
				MInfoWindow.COLUMNNAME_AD_Table_ID + "=? AND IsValid='Y' ", null);
		MTable table = MTable.get(Env.getCtx(), tableName);
		if (table != null)
		{
			List<MInfoWindow> iws = query.setParameters(table.getAD_Table_ID())
					.setOrderBy("AD_Client_ID Desc, AD_Org_ID Desc, IsDefault Desc, AD_InfoWindow_ID Desc")
					.setOnlyActiveRecords(true).setApplyAccessFilter(true).list();

			// verify role has access and return the first with access /
			for (MInfoWindow iw : iws)
			{
				Boolean access = MRole.getDefault().getInfoAccess(iw.getAD_InfoWindow_ID());
				if (access != null && access.booleanValue())
					return iw;
			}
		}
		return null;
	}

	/**
	 * return true if the current role can access to the specified info window ;
	 * otherwise return null
	 */
	public static MInfoWindow get(int infoWindowID, String trxName)
	{
		MInfoWindow iw = new MInfoWindow(Env.getCtx(), infoWindowID, null);
		Boolean access = MRole.getDefault().getInfoAccess(iw.getAD_InfoWindow_ID());
		if (access != null && access.booleanValue())
			return iw;
		return null;
	}

	/**
	 * Get list {@link MInfoProcess} of this infoWindow
	 * 
	 * @param requery true get from db, false try get from cache
	 * @return empty array when not exists Info Process
	 */
	public MInfoProcess[] getInfoProcess(boolean requery)
	{
		// try from cache
		if ((this.m_infoProcess != null) && (!requery))
		{
			set_TrxName(this.m_infoProcess, get_TrxName());
			return this.m_infoProcess;
		}

		// get list info process from db, order by seqNo
		List<MInfoProcess> list = new Query(getCtx(), MInfoProcess.Table_Name, "AD_InfoWindow_ID=?", get_TrxName())
				.setParameters(getAD_InfoWindow_ID()).setOnlyActiveRecords(true).setOrderBy("SeqNo").list();

		checkProcessRight(list);
		m_infoProcess = list.toArray(new MInfoProcess[list.size()]);

		return m_infoProcess;
	}

	/**
	 * if user haven't right to run a process, set infoProcess to null
	 * 
	 * @param lsInfoProcess
	 */
	protected void checkProcessRight(List<MInfoProcess> lsInfoProcess)
	{
		Iterator<MInfoProcess> iterator = lsInfoProcess.iterator();
		while (iterator.hasNext())
		{
			MInfoProcess testInfoProcess = iterator.next();
			Boolean access = MRole.getDefault().getProcessAccess(testInfoProcess.getAD_Process_ID());
			if (access == null || !access.booleanValue())
			{
				iterator.remove();
			}
		}
	}

	public MInfoColumn[] getInfoColumns(TableInfo[] tableInfos)
	{
		Query query = new Query(getCtx(), MTable.get(getCtx(), I_AD_InfoColumn.Table_ID),
				I_AD_InfoColumn.COLUMNNAME_AD_InfoWindow_ID + "=?", get_TrxName());
		List<MInfoColumn> list = query.setParameters(getAD_InfoWindow_ID()).setOnlyActiveRecords(true)
				.setOrderBy("SeqNo, AD_InfoColumn_ID").list();
		for (int i = list.size() - 1; i >= 0; i--)
		{
			MInfoColumn infoColumn = list.get(i);
			if (!infoColumn.isColumnAccess(tableInfos))
				list.remove(i);
		}
		return list.toArray(new MInfoColumn[0]);
	}

	public MInfoColumn[] getInfoColumns()
	{
		Query query = new Query(getCtx(), MTable.get(getCtx(), I_AD_InfoColumn.Table_ID),
				I_AD_InfoColumn.COLUMNNAME_AD_InfoWindow_ID + "=?", get_TrxName());
		List<MInfoColumn> list = query.setParameters(getAD_InfoWindow_ID()).setOnlyActiveRecords(true)
				.setOrderBy("SeqNo, AD_InfoColumn_ID").list();
		return list.toArray(new MInfoColumn[0]);
	}

	@Override
	protected boolean afterSave(boolean newRecord, boolean success)
	{
		if (!success)
			return success;
		if (newRecord) // Add to all automatic roles
		{
			MRole[] roles = MRole.getOf(getCtx(), "IsManual='N'");
			for (int i = 0; i < roles.length; i++)
			{
				MInfoWindowAccess wa = new MInfoWindowAccess(this, roles[i].getAD_Role_ID());
				wa.saveEx();
			}
		}

		return super.afterSave(newRecord, success);
	}

	@Override
	protected boolean beforeSave(boolean newRecord)
	{
		return super.beforeSave(newRecord);
	}

	/**
	 * Validate the Info Window
	 * 
	 * @return
	 */
	public void validate()
	{
		// default, before complete check is invalid
		this.setIsValid(false);

		// add DISTINCT clause
		StringBuilder builder = new StringBuilder("SELECT ");
		if (this.isDistinct())
			builder.append("DISTINCT ");

		MInfoColumn[] infoColumns = this.getInfoColumns();
		// none column make this invalid
		if (infoColumns.length == 0)
		{
			return;
		}

		// build select clause
		for (int columnIndex = 0; columnIndex < infoColumns.length; columnIndex++)
		{
			if (columnIndex > 0)
			{
				builder.append(", ");
			}
			builder.append(infoColumns[columnIndex].getSelectClause());
		}

		// build from clause
		builder.append(" FROM ").append(this.getFromClause());

		// build where clause add (1=2) because not need get result, decrease
		// load
		if (this.getWhereClause() != null && this.getWhereClause().trim().length() > 0)
		{
			builder.append(" WHERE (1=2) AND (").append(this.getWhereClause()).append(")");
		}
		else
		{
			builder.append(" WHERE 1=2");
		}

		// build other (having) clause
		if (this.getOtherClause() != null && this.getOtherClause().trim().length() > 0)
		{
			builder.append(" ").append(this.getOtherClause());
		}

		// build order (having) clause
		if (this.getOrderByClause() != null && this.getOrderByClause().trim().length() > 0)
		{
			builder.append(" ORDER BY ").append(this.getOrderByClause());
		}

		// replace Env value by dummy value
		while (builder.indexOf("@") >= 0)
		{
			int start = builder.indexOf("@");
			int end = builder.indexOf("@", start + 1);
			if (start >= 0 && end > start)
			{
				builder.replace(start, end + 1, "0");
			}
			else
			{
				break;
			}
		}

		// try run sql
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement(builder.toString(), null);
			pstmt.executeQuery();
		}
		catch (Exception ex)
		{
			log.log(Level.WARNING, ex.getMessage());
			return;
		}
		finally
		{
			DB.close(pstmt);
			pstmt = null;
		}

		// valid state
		this.setIsValid(true);
	}
} // MInfoWindow
