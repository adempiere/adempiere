/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/
package org.eevolution.manufacturing.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import org.adempiere.core.domains.models.X_T_BOMLine;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.ValueNamePair;

/**
 * Multi-Level BOM & Formula Detail
 * 
 * @author victor.perez@e-evolution.com,Sergio Ramazzina
 * @version $Id: PrintBOM.java,v 1.2 2005/04/19 12:54:30 srama Exp $
 * 
 */
public class PrintBOM extends PrintBOMAbstract
{
	private int LevelNo = 1;
	private int SeqNo = 0;
	private String levels = new String("....................");

	/**
	 * Perform process.
	 * 
	 * @return Message (clear text)
	 * @throws Exception
	 *             if not successful
	 */
	protected String doIt() throws Exception
	{
		try 
		{
			loadBOM();
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "PrintBOM", e.toString());
			throw new Exception(e.getLocalizedMessage());
		}

		return "@OK@";
	} // doIt

	/**
	 * Action: Fill Tree with all nodes
	 */
	private void loadBOM() throws Exception
	{
		int count = 0;
		if (getProductId() == 0) 
			raiseError("Error: ","Product ID not found");

		X_T_BOMLine tboml = new X_T_BOMLine(getCtx(), 0, get_TrxName());
		tboml.setPP_Product_BOM_ID(0);
		tboml.setPP_Product_BOMLine_ID(0);
		tboml.setM_Product_ID(getProductId());
		tboml.setSel_Product_ID(getProductId());
		tboml.setImplosion(isImplosion());
		tboml.setLevelNo(0);
		tboml.setLevels("0");
		tboml.setSeqNo(0);
		tboml.setAD_PInstance_ID(getAD_PInstance_ID());
		tboml.save();

		if (isImplosion())
		{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String sql = "SELECT PP_Product_BOMLine_ID FROM PP_Product_BOMLine " 
					+ "WHERE IsActive = 'Y' AND M_Product_ID = ? ";
			try
			{
				stmt = DB.prepareStatement(sql, get_TrxName());
				stmt.setInt(1, getProductId());
				rs = stmt.executeQuery();

				while (rs.next())
				{
					parentImplotion(rs.getInt(1));
					++count;
				}
				if (count == 0)
					raiseError("Error: ","Product is not a component");
			}
			catch (SQLException e)
			{
				log.log(Level.SEVERE, e.getLocalizedMessage() + sql, e);
				throw new Exception("SQLException: "+e.getLocalizedMessage());
			}
			finally
			{
				DB.close(rs, stmt);
				rs = null;
				stmt = null;
			}
		}
		else
		{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String sql = "SELECT PP_Product_BOM_ID FROM PP_Product_BOM " 
					+ "WHERE IsActive = 'Y' AND M_Product_ID = ? ";
			try
			{
				stmt = DB.prepareStatement(sql, get_TrxName());
				stmt.setInt(1, getProductId());
				rs = stmt.executeQuery();
				while (rs.next())
				{
					parentExplotion(rs.getInt(1));
					++count;
				}
				if (count == 0)
					raiseError("Error: ","Product is not a BOM");				
			}
			catch (SQLException e)
			{
				log.log(Level.SEVERE, e.getLocalizedMessage() + sql, e);
				throw new Exception("SQLException: "+e.getLocalizedMessage());
			}
			finally
			{
				DB.close(rs, stmt);
				rs = null;
				stmt = null;
			}
		}
	}

	/**
	 * Generate an Implotion for this BOM Line
	 * 
	 * @param PP_Product_BOMLine_ID
	 *            ID BOM Line
	 */
	public void parentImplotion(int PP_Product_BOMLine_ID) throws Exception
	{
		int PP_Product_BOM_ID = 0;
		int M_Product_ID = 0;
		
		X_T_BOMLine tboml = new X_T_BOMLine(getCtx(), 0, get_TrxName());
		 
		PP_Product_BOM_ID = DB.getSQLValue(get_TrxName(), 
				"SELECT PP_Product_BOM_ID FROM PP_Product_BOMLine WHERE PP_Product_BOMLine_ID=?",PP_Product_BOMLine_ID);
		if (PP_Product_BOM_ID < 0)
			throw new Exception(CLogger.retrieveErrorString("Error: PrintBOM.parentImplotion()"));
		M_Product_ID = DB.getSQLValue(get_TrxName(),
				"SELECT M_Product_ID FROM PP_Product_BOM WHERE PP_Product_BOM_ID=?", PP_Product_BOM_ID);
		if (M_Product_ID < 0)
			throw new Exception(CLogger.retrieveErrorString("Error: PrintBOM.parentImplotion()"));

		tboml.setPP_Product_BOM_ID(PP_Product_BOM_ID);
		tboml.setPP_Product_BOMLine_ID(PP_Product_BOMLine_ID);
		tboml.setM_Product_ID(M_Product_ID);
		tboml.setLevelNo(LevelNo);
		tboml.setSel_Product_ID(getProductId());
		tboml.setImplosion(isImplosion());

		if (LevelNo >= 11)
			tboml.setLevels(levels + ">" + LevelNo);
		else if (LevelNo >= 1) tboml.setLevels(levels.substring(0, LevelNo) + LevelNo);

		tboml.setSeqNo(SeqNo);
		tboml.setAD_PInstance_ID(getAD_PInstance_ID());
		tboml.save();

		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT PP_Product_BOM_ID, M_Product_ID FROM PP_Product_BOM " 
					+ "WHERE IsActive = 'Y' AND M_Product_ID = ? ";
		try
		{
			stmt = DB.prepareStatement(sql, get_TrxName());
			stmt.setInt(1, M_Product_ID);
			rs = stmt.executeQuery();

			while (rs.next())
			{
				SeqNo += 1;
				component(rs.getInt(2));
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, e.getLocalizedMessage() + sql, e);
			throw new Exception("SQLException: "+e.getLocalizedMessage());
		}
		finally
		{
			DB.close(rs, stmt);
			rs = null;
			stmt = null;
		}
	}

	/**
	 * Generate an Explotion for this BOM
	 * 
	 * @param PP_Product_BOMLine_ID
	 *            ID BOM Line
	 */
	public void parentExplotion(int PP_Product_BOM_ID) throws Exception
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT PP_Product_BOMLine_ID, M_Product_ID FROM PP_Product_BOMLine boml "
				+ "WHERE IsActive = 'Y' AND PP_Product_BOM_ID = ? ORDER BY Line ";
		try
		{
			stmt = DB.prepareStatement(sql, get_TrxName());
			stmt.setInt(1, PP_Product_BOM_ID);
			rs = stmt.executeQuery();
			while (rs.next())
			{
				SeqNo += 1;
				X_T_BOMLine tboml = new X_T_BOMLine(getCtx(), 0, get_TrxName());
				tboml.setPP_Product_BOM_ID(PP_Product_BOM_ID);
				tboml.setPP_Product_BOMLine_ID(rs.getInt(1));
				tboml.setM_Product_ID(rs.getInt(2));
				tboml.setLevelNo(LevelNo);
				tboml.setLevels(levels.substring(0, LevelNo) + LevelNo);
				tboml.setSeqNo(SeqNo);
				tboml.setAD_PInstance_ID(getAD_PInstance_ID());
				tboml.setSel_Product_ID(getProductId());
				tboml.setImplosion(isImplosion());
				tboml.save();
				component(rs.getInt(2));
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, e.getLocalizedMessage() + sql, e);
			throw new Exception("SQLException: "+e.getLocalizedMessage());
		}
		finally
		{
			DB.close(rs, stmt);
			rs = null;
			stmt = null;
		}
	}

	/**
	 * Find if this product as component
	 * 
	 * @param M_Product_ID
	 *            ID Component
	 */
	public void component(int M_Product_ID) throws Exception
	{
		if (isImplosion())
		{
			LevelNo += 1;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String sql = "SELECT PP_Product_BOMLine_ID FROM PP_Product_BOMLine " 
				+ "WHERE IsActive = 'Y' AND M_Product_ID = ? ";
			try
			{
				stmt = DB.prepareStatement(sql, get_TrxName());
				stmt.setInt(1, M_Product_ID);
				rs = stmt.executeQuery();
				while (rs.next())
				{
					parentImplotion(rs.getInt(1));
				}
				rs.close();
				stmt.close();
				LevelNo -= 1;
				return;
			}
			catch (SQLException e)
			{
				log.log(Level.SEVERE, e.getLocalizedMessage() + sql, e);
				throw new Exception("SQLException: "+e.getLocalizedMessage());
			}
			finally
			{
				DB.close(rs, stmt);
				rs = null;
				stmt = null;
			}
		}
		else
		{
			String sql = "SELECT PP_Product_BOM_ID FROM PP_Product_BOM  " 
					+ "WHERE IsActive = 'Y' AND Value = ? ";
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try
			{
				String Value = DB.getSQLValueString(get_TrxName(), 
						"SELECT Value FROM M_PRODUCT WHERE M_PRODUCT_ID=?", M_Product_ID);
				if (Value == null) 
				{
					throw new Exception(CLogger.retrieveErrorString("Error: PrintBOM.component()"));
				}
				stmt = DB.prepareStatement(sql, get_TrxName());
				stmt.setString(1, Value);
				rs = stmt.executeQuery();
				boolean level = false;
				while (rs.next())
				{
					if (!level) LevelNo += 1;
					level = true;
					parentExplotion(rs.getInt(1));
					LevelNo -= 1;
				}
			}
			catch (SQLException e)
			{
				log.log(Level.SEVERE, e.getLocalizedMessage() + sql, e);
				throw new Exception("SQLException: "+e.getLocalizedMessage());
			}
			finally
			{
				DB.close(rs, stmt);
				rs = null;
				stmt = null;
			}
		}
		return;
	}
	
	private void raiseError(String string, String hint) throws Exception
	{
		String msg = string;
		ValueNamePair pp = CLogger.retrieveError();
		if (pp != null) msg = pp.getName() + " - ";
		msg += hint;
		throw new Exception(msg);
	}

}
