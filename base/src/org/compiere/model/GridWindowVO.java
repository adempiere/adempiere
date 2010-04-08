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

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 *  Model Window Value Object
 *
 *  @author Jorg Janke
 *  @version  $Id: GridWindowVO.java,v 1.4 2006/07/30 00:58:04 jjanke Exp $
 */
public class GridWindowVO implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6884332743173214735L;

	/**
	 *  Create Window Value Object
	 *  @param ctx context
	 *  @param WindowNo window no for ctx
	 *  @param AD_Window_ID window id
	 *  @return MWindowVO
	 */
	public static GridWindowVO create (Properties ctx, int WindowNo, int AD_Window_ID)
	{
		return create (ctx, WindowNo, AD_Window_ID, 0);
	}   //  create

	/**
	 *  Create Window Value Object
	 *
	 *  @param ctx context
	 *  @param WindowNo window no for ctx
	 *  @param AD_Window_ID window id
	 *  @param AD_Menu_ID menu id
	 *  @return MWindowVO
	 */
	public static GridWindowVO create (Properties ctx, int WindowNo, int AD_Window_ID, int AD_Menu_ID)
	{
		CLogger.get().config("#" + WindowNo
			+ " - AD_Window_ID=" + AD_Window_ID + "; AD_Menu_ID=" + AD_Menu_ID);
		GridWindowVO vo = new GridWindowVO (ctx, WindowNo);
		vo.AD_Window_ID = AD_Window_ID;

		//  Get Window_ID if required	- (used by HTML UI)
		if (vo.AD_Window_ID == 0 && AD_Menu_ID != 0)
		{
			String sql = "SELECT AD_Window_ID, IsSOTrx, IsReadOnly FROM AD_Menu "
				+ "WHERE AD_Menu_ID=? AND Action='W'";
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql, null);
				pstmt.setInt(1, AD_Menu_ID);
				rs = pstmt.executeQuery();
				if (rs.next())
				{
					vo.AD_Window_ID = rs.getInt(1);
					String IsSOTrx = rs.getString(2);
					Env.setContext(ctx, WindowNo, "IsSOTrx", (IsSOTrx != null && IsSOTrx.equals("Y")));
					//
					String IsReadOnly = rs.getString(3);
					if (IsReadOnly != null && IsReadOnly.equals("Y"))
						vo.IsReadWrite = "Y";
					else
						vo.IsReadWrite = "N";
				}
			}
			catch (SQLException e)
			{
				CLogger.get().log(Level.SEVERE, "Menu", e);
				return null;
			}
			finally
			{
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
			CLogger.get().config("AD_Window_ID=" + vo.AD_Window_ID);
		}

		//  --  Get Window

		StringBuffer sql = new StringBuffer("SELECT Name,Description,Help,WindowType, "
			+ "AD_Color_ID,AD_Image_ID,WinHeight,WinWidth, "
			+ "IsSOTrx ");

		if (Env.isBaseLanguage(vo.ctx, "AD_Window"))
			sql.append("FROM AD_Window w WHERE w.AD_Window_ID=? AND w.IsActive='Y'");
		else
			sql.append("FROM AD_Window_vt w WHERE w.AD_Window_ID=?")
				.append(" AND AD_Language='")
				.append(Env.getAD_Language(vo.ctx)).append("'");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			//	create statement
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, vo.AD_Window_ID);
			// 	get data
			rs = pstmt.executeQuery();
			if (rs.next())
			{
				vo.Name = rs.getString(1);
				vo.Description = rs.getString(2);
				if (vo.Description == null)
					vo.Description = "";
				vo.Help = rs.getString(3);
				if (vo.Help == null)
					vo.Help = "";
				vo.WindowType = rs.getString(4);
				//
				vo.AD_Color_ID = rs.getInt(5);
				vo.AD_Image_ID = rs.getInt(6);
				//vo.IsReadWrite = rs.getString(7);
				//
				vo.WinHeight = rs.getInt(7);
				vo.WinWidth = rs.getInt(8);
				//
				vo.IsSOTrx = "Y".equals(rs.getString(9));
			}
			else
				vo = null;
		}
		catch (SQLException ex)
		{
			CLogger.get().log(Level.SEVERE, sql.toString(), ex);
			return null;
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		// Ensure ASP exceptions
		MRole role = MRole.getDefault(ctx, false);
		final Boolean windowAccess = role.getWindowAccess(vo.AD_Window_ID);
		if (vo != null && windowAccess == null)
			vo = null;		//	Not found
		if (vo != null && windowAccess != null)
			vo.IsReadWrite = (windowAccess.booleanValue() ? "Y" : "N");
		if (vo == null)
		{
			CLogger.get().log(Level.SEVERE, "No Window - AD_Window_ID=" + AD_Window_ID
				+ ", AD_Role_ID=" + role + " - " + sql);
			CLogger.get().saveError("AccessTableNoView", "(Not found)");
			return null;
		}
		//	Read Write
		if (vo.IsReadWrite == null)
		{
			CLogger.get().saveError("AccessTableNoView", "(found)");
			return null;
		}

		//  Create Tabs
		createTabs (vo);
		if (vo.Tabs == null || vo.Tabs.size() == 0)
			return null;

		return vo;
	}   //  create

	/**
	 *  Create Window Tabs
	 *  @param mWindowVO Window Value Object
	 *  @return true if tabs were created
	 */
	private static boolean createTabs (GridWindowVO mWindowVO)
	{
		mWindowVO.Tabs = new ArrayList<GridTabVO>();

		String sql = GridTabVO.getSQL(mWindowVO.ctx);
		int TabNo = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			//	create statement
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, mWindowVO.AD_Window_ID);
			rs = pstmt.executeQuery();
			boolean firstTab = true;
			while (rs.next())
			{
				if (mWindowVO.AD_Table_ID == 0)
					mWindowVO.AD_Table_ID = rs.getInt("AD_Table_ID");
				//  Create TabVO
				GridTabVO mTabVO = GridTabVO.create(mWindowVO, TabNo, rs,
					mWindowVO.WindowType.equals(WINDOWTYPE_QUERY),  //  isRO
					mWindowVO.WindowType.equals(WINDOWTYPE_TRX));   //  onlyCurrentRows
				if (mTabVO == null && firstTab)
					break;		//	don't continue if first tab is null
				if (mTabVO != null)
				{
					if (!mTabVO.IsReadOnly && "N".equals(mWindowVO.IsReadWrite))
						mTabVO.IsReadOnly = true;
					mWindowVO.Tabs.add(mTabVO);
					TabNo++;        //  must be same as mWindow.getTab(x)
					firstTab = false;
				}
			}
		}
		catch (SQLException e)
		{
			CLogger.get().log(Level.SEVERE, "createTabs", e);
			return false;
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}

		//  No Tabs
		if (TabNo == 0 || mWindowVO.Tabs.size() == 0)
		{
			CLogger.get().log(Level.SEVERE, "No Tabs - AD_Window_ID=" 
				+ mWindowVO.AD_Window_ID + " - " + sql);
			return false;
		}

		//	Put base table of window in ctx (for VDocAction)
		Env.setContext(mWindowVO.ctx, mWindowVO.WindowNo, "BaseTable_ID", mWindowVO.AD_Table_ID);
		return true;
	}   //  createTabs

	
	/**************************************************************************
	 *  Private Constructor
	 *  @param Ctx context
	 *  @param windowNo window no
	 */
	private GridWindowVO (Properties Ctx, int windowNo)
	{
		ctx = Ctx;
		WindowNo = windowNo;
	}   //  MWindowVO

	/** Properties      */
	public Properties   ctx;
	/** Window Number	*/
	public int 		    WindowNo;

	/** Window				*/
	public	int			AD_Window_ID = 0;
	/** Name				*/
	public	String		Name = "";
	/** Desription			*/
	public	String		Description = "";
	/** Help				*/
	public	String		Help = "";
	/** Window Type			*/
	public	String		WindowType = "";
	/** Image				*/
	public int          AD_Image_ID = 0;
	/** Color				*/
	public int          AD_Color_ID = 0;
	/** Read Write			*/
	public String		IsReadWrite = null;
	/** Window Width		*/
	public int			WinWidth = 0;
	/** Window Height		*/
	public int			WinHeight = 0;
	/** Sales Order Trx		*/
	public boolean		IsSOTrx = false;

	/** Tabs contains MTabVO elements   */
	public ArrayList<GridTabVO>	Tabs = null;
	/** Base Table		*/
	public int 			AD_Table_ID = 0;

	/** Qyery				*/
	public static final String	WINDOWTYPE_QUERY = "Q";
	/** Transaction			*/
	public static final String	WINDOWTYPE_TRX = "T";
	/** Maintenance			*/
	public static final String	WINDOWTYPE_MMAINTAIN = "M";

	/**
	 *  Set Context including contained elements
	 *  @param newCtx context
	 */
	public void setCtx (Properties newCtx)
	{
		ctx = newCtx;
		for (int i = 0; i < Tabs.size() ; i++)
		{
			GridTabVO tab = (GridTabVO)Tabs.get(i);
			tab.setCtx(newCtx);
		}
	}   //  setCtx

	/**
	 * 	Clone
	 * 	@param windowNo no
	 *	@return WindowVO
	 */
	public GridWindowVO clone (int windowNo)
	{
		GridWindowVO clone = null;
		try
		{
			clone = new GridWindowVO(ctx, windowNo);
			clone.AD_Window_ID = AD_Window_ID;
			clone.Name = Name;
			clone.Description = Description;
			clone.Help = Help;
			clone.WindowType = WindowType;
			clone.AD_Image_ID = AD_Image_ID;
			clone.AD_Color_ID = AD_Color_ID;
			clone.IsReadWrite = IsReadWrite;
			clone.WinWidth = WinWidth;
			clone.WinHeight = WinHeight;
			clone.IsSOTrx = IsSOTrx;
			Env.setContext(ctx, windowNo, "IsSOTrx", clone.IsSOTrx);
			clone.AD_Table_ID = AD_Table_ID;
			Env.setContext(ctx, windowNo, "BaseTable_ID", clone.AD_Table_ID);
			//
			clone.Tabs = new ArrayList<GridTabVO>();
			for (int i = 0; i < Tabs.size(); i++)
			{
				GridTabVO tab = Tabs.get(i);
				GridTabVO cloneTab = tab.clone(clone.ctx, windowNo);
				if (cloneTab == null)
					return null;
				clone.Tabs.add(cloneTab);
			}
		}
		catch (Exception e)
		{
			clone = null;
		}
		return clone;
	}	//	clone

}   //  MWindowVO

