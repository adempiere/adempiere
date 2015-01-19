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

import java.awt.Dimension;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.wf.MWFNode;

/**
 *	Window Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MWindow.java,v 1.2 2006/07/30 00:58:05 jjanke Exp $
 */
public class MWindow extends X_AD_Window
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7604318488890368565L;
	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MWindow.class);
	
	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param AD_Window_ID
	 *	@param trxName transaction
	 */
	public MWindow (Properties ctx, int AD_Window_ID, String trxName)
	{
		super (ctx, AD_Window_ID, trxName);
		if (AD_Window_ID == 0)
		{
			setWindowType (WINDOWTYPE_Maintain);	// M
			setEntityType (ENTITYTYPE_UserMaintained);	// U
			setIsBetaFunctionality (false);
			setIsDefault (false);
			setIsSOTrx (true);	// Y
		}	}	//	M_Window

	/**
	 * 	Koad Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MWindow (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	M_Window
	
	/**
	 * 	Set Window Size
	 *	@param size size
	 */
	public void setWindowSize (Dimension size)
	{
		if (size != null)
		{
			setWinWidth(size.width);
			setWinHeight(size.height);
		}
	}	//	setWindowSize
	
	/**	The Lines						*/
	private MTab[]		m_tabs	= null;

	/**
	 * 	Get Fields
	 *	@param reload reload data
	 *	@return array of lines
	 *	@param trxName transaction
	 */
	public MTab[] getTabs (boolean reload, String trxName)
	{
		if (m_tabs != null && !reload)
			return m_tabs;
		final String whereClause = I_AD_Tab.COLUMNNAME_AD_Window_ID+"=?";
		List<MTab> list = new Query(getCtx(),I_AD_Tab.Table_Name,whereClause,trxName)
		.setParameters(getAD_Window_ID())
		.setOrderBy(I_AD_Tab.COLUMNNAME_SeqNo)
		.list();
		//
		m_tabs = new MTab[list.size ()];
		list.toArray (m_tabs);
		return m_tabs;
	}	//	getFields

	
	/**
	 * 	After Save
	 *	@param newRecord new
	 *	@param success success
	 *	@return success
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		if (newRecord)	//	Add to all automatic roles
		{
			MRole[] roles = MRole.getOf(getCtx(), "IsManual='N'");
			for (int i = 0; i < roles.length; i++)
			{
				MWindowAccess wa = new MWindowAccess(this, roles[i].getAD_Role_ID());
				wa.saveEx();
			}
		}
		//	Menu/Workflow
		else if (is_ValueChanged("IsActive") || is_ValueChanged("Name") 
			|| is_ValueChanged("Description") || is_ValueChanged("Help"))
		{
			MMenu[] menues = MMenu.get(getCtx(), "AD_Window_ID=" + getAD_Window_ID(), get_TrxName());
			for (int i = 0; i < menues.length; i++)
			{
				menues[i].setName(getName());
				menues[i].setDescription(getDescription());
				menues[i].setIsActive(isActive());
				menues[i].saveEx();
			}
			//
			MWFNode[] nodes = getWFNodes(getCtx(), "AD_Window_ID=" + getAD_Window_ID(), get_TrxName());
			for (int i = 0; i < nodes.length; i++)
			{
				boolean changed = false;
				if (nodes[i].isActive() != isActive())
				{
					nodes[i].setIsActive(isActive());
					changed = true;
				}
				if (nodes[i].isCentrallyMaintained())
				{
					nodes[i].setName(getName());
					nodes[i].setDescription(getDescription());
					nodes[i].setHelp(getHelp());
					changed = true;
				}
				if (changed)
					nodes[i].saveEx();
			}
		}
		return success;
	}	//	afterSave

	
	/**
	 * Get workflow nodes with where clause.
	 * Is here as MWFNode is in base
	 * @param ctx context
	 * @param whereClause where clause w/o the actual WHERE
	 * @param trxName transaction
	 * @return nodes
	 */
	public static MWFNode[] getWFNodes (Properties ctx, String whereClause, String trxName)
	{		
		List<MWFNode> list = new Query(ctx,I_AD_WF_Node.Table_Name,whereClause,trxName)
		.list();
		MWFNode[] retValue = new MWFNode[list.size()];
		list.toArray (retValue);
		return retValue;
	}	//	getWFNode
	
	//vpj-cd begin e-evolution
	/**
	 * 	get Window ID
	 *	@param String windowName
	 *	@return int retValue
	 */
	public static int getWindow_ID(String windowName) {
		int retValue = 0;
		String SQL = "SELECT AD_Window_ID FROM AD_Window WHERE Name = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(SQL, null);
			pstmt.setString(1, windowName);
			rs = pstmt.executeQuery();
			if (rs.next())
				retValue = rs.getInt(1);
		}
		catch (SQLException e)
		{
			s_log.log(Level.SEVERE, SQL, e);
			retValue = -1;
		}
		finally
		{
			DB.close(rs, pstmt);
		}
		return retValue;
	}
	//end vpj-cd e-evolution
	
}	//	M_Window
