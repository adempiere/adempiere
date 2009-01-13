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
package org.eevolution.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Properties;
import java.util.logging.Level;

import javax.sql.RowSet;

import org.compiere.model.MAcctSchema;
import org.compiere.model.MCost;
import org.compiere.model.MCostElement;
import org.compiere.model.MProduct;
import org.compiere.model.MQuery;
import org.compiere.model.MTable;
import org.compiere.model.PrintInfo;
import org.compiere.print.MPrintFormat;
import org.compiere.print.ReportCtl;
import org.compiere.print.ReportEngine;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Language;
import org.compiere.util.ValueNamePair;
import org.eevolution.model.X_T_BOMLine;

/**
 * Cost Multi-Level BOM & Formula Review
 * 
 * @author victor.perez@e-evolution.com
 * 
 */
public class CostBillOfMaterial extends SvrProcess
{
	private static final Properties ctx = Env.getCtx();
	private int p_AD_Org_ID = 0 ;
	private int p_C_AcctSchema_ID = 0 ;
	private MAcctSchema as = null;
	private int p_M_Product_ID = 0;
	private boolean p_implosion = false;
	private int LevelNo = 1;
	private int SeqNo = 0;
	private String levels = new String("....................");
	private int AD_PInstance_ID = 0;
	
	private Collection <MCostElement> elements = null;

	/**
	 * Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		for (ProcessInfoParameter para : getParameter())
		{
			String name = para.getParameterName();
			if (para.getParameter() == null)
				;
			else if (name.equals("AD_Org_ID"))
			{
				p_AD_Org_ID = ((BigDecimal) para.getParameter()).intValue();
			}
			else if (name.equals("C_AcctSchema_ID"))
			{
				p_C_AcctSchema_ID= ((BigDecimal) para.getParameter()).intValue();
				as = MAcctSchema.get(getCtx(), p_C_AcctSchema_ID);
			}
			else if (name.equals("M_Product_ID"))
			{
				p_M_Product_ID = ((BigDecimal) para.getParameter()).intValue();
			}
			else if (name.equals("Implosion"))
			{
				p_implosion = ((String) para.getParameter()).equals("N") ? false : true;
			}
			else
				log.log(Level.SEVERE, "prepare - Unknown Parameter: " + name);
		}
	} // prepare

	/**
	 * Perform process.
	 * 
	 * @return Message (clear text)
	 * @throws Exception
	 *             if not successful
	 */
	protected String doIt() throws Exception
	{
		AD_PInstance_ID = getAD_PInstance_ID();

		try 
		{
			loadBOM();
			print();
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "PrintBOM", e.toString());
			throw new Exception(e.getLocalizedMessage());
		}
		finally
		{
			String sql = "DELETE FROM T_BomLine WHERE AD_PInstance_ID = " + AD_PInstance_ID;
			DB.executeUpdate(sql, null);		
		}

		return "@OK@";
	} // doIt

	/**
	 * Print result generate for this report
	 */
	void print() throws Exception
	{
		Language language = Language.getLoginLanguage(); // Base Language
		MPrintFormat pf = null;
		int pfid = 0;
		int vid = MTable.getTable_ID("RV_PP_Cost_BOMLine");
		
		// get print format for client, else copy system to client  
		RowSet pfrs = MPrintFormat.getAccessiblePrintFormats(vid, -1, null);
		pfrs.next();
		pfid = pfrs.getInt("AD_PrintFormat_ID");
		
		if(pfrs.getInt("AD_Client_ID") != 0) pf = MPrintFormat.get(getCtx(), pfid, false);
		else pf = MPrintFormat.copyToClient(getCtx(), pfid, getAD_Client_ID());
		pfrs.close();		

		if (pf == null) raiseError("Error: ","No Print Format");

		pf.setLanguage(language);
		pf.setTranslationLanguage(language);
		// query
		MQuery query = MQuery.get(getCtx(), AD_PInstance_ID, "RV_PP_Cost_BOMLine");
		query.addRestriction("AD_PInstance_ID", MQuery.EQUAL, AD_PInstance_ID);

		PrintInfo info = new PrintInfo("RV_PP_Cost_BOMLine", vid , getRecord_ID());
		ReportEngine re = new ReportEngine(getCtx(), pf, query, info);

		ReportCtl.preview(re);
		// wait for report window to be closed as t_bomline   
		// records are deleted when process ends 
		while (re.getView().isDisplayable()) 
		{
			Env.sleep(1);
		}	
	}

	/**
	 * Action: Fill Tree with all nodes
	 */
	private void loadBOM() throws Exception
	{
		elements = MCostElement.getByCostingMethod(getCtx(), MCostElement.COSTINGMETHOD_StandardCosting);
		int count = 0;
		if (p_M_Product_ID == 0) 
			raiseError("Error: ","Product ID not found");

		for(MCostElement element : elements)
		{
			MProduct product = MProduct.get(getCtx(), p_M_Product_ID);
			Collection <MCost> costs = MCost.getByCostingMethod(
					product,
					as,
					p_AD_Org_ID,
					0,
					MCostElement.COSTINGMETHOD_StandardCosting,
					element.getCostElementType());
			BigDecimal currentCostPrice = Env.ZERO;
			BigDecimal currentCostPriceLL = Env.ZERO;
			for (MCost cost : costs)
			{
				currentCostPrice = currentCostPrice.add(cost.getCurrentCostPrice());
				currentCostPriceLL = currentCostPriceLL.add(cost.getCurrentCostPriceLL());
			}
			
			X_T_BOMLine tboml = new X_T_BOMLine(ctx, 0, null);
			tboml.setPP_Product_BOM_ID(0);
			tboml.setPP_Product_BOMLine_ID(0);
			tboml.setM_Product_ID(p_M_Product_ID);
			tboml.setSel_Product_ID(p_M_Product_ID);
			tboml.setM_CostElement_ID(element.getM_CostElement_ID());
			tboml.setCurrentCostPrice(currentCostPrice);
			tboml.setCurrentCostPriceLL(currentCostPriceLL);
			tboml.setQtyBOM(Env.ONE);
			tboml.setImplosion(p_implosion);
			tboml.setC_AcctSchema_ID(p_C_AcctSchema_ID);
			tboml.setLevelNo(0);
			tboml.setLevels("0");
			tboml.setSeqNo(0);
			tboml.setAD_PInstance_ID(AD_PInstance_ID);
			tboml.save();
		}

			PreparedStatement stmt = null;
			ResultSet rs = null;
			String sql = "SELECT PP_Product_BOM_ID FROM PP_Product_BOM " 
					+ "WHERE IsActive = 'Y' AND M_Product_ID = ? ";
			try
			{
				stmt = DB.prepareStatement(sql, get_TrxName());
				stmt.setInt(1, p_M_Product_ID);
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
		
		final String sql = "SELECT PP_Product_BOMLine_ID, M_Product_ID, QtyBOM , QtyBatch FROM PP_Product_BOMLine boml "
				+ "WHERE IsActive = 'Y' AND PP_Product_BOM_ID = ? ORDER BY Line ";
		try
		{
			stmt = DB.prepareStatement(sql, get_TrxName());
			stmt.setInt(1, PP_Product_BOM_ID);
			rs = stmt.executeQuery();
			while (rs.next())
			{
				SeqNo += 1;
				
				
				for(MCostElement element : elements)
				{	
					int M_Product_ID = rs.getInt(2);
					BigDecimal qtyBOM = rs.getBigDecimal(3);
					BigDecimal qtyBatch =  rs.getBigDecimal(4);
					BigDecimal qty = Env.ZERO; 
					if(qtyBOM.signum() > 0 )
						qty = qtyBOM;
					else if(qtyBatch.signum() > 0)
						qty = qtyBatch.divide(Env.ONEHUNDRED,BigDecimal.ROUND_HALF_UP);
					
					MProduct product = MProduct.get(getCtx(), M_Product_ID);
					Collection <MCost> costs = MCost.getByCostingMethod(
							product,
							as,
							p_AD_Org_ID,
							0,
							MCostElement.COSTINGMETHOD_StandardCosting,
							element.getCostElementType());
					BigDecimal currentCostPrice = Env.ZERO;
					BigDecimal currentCostPriceLL = Env.ZERO;
					for (MCost cost : costs)
					{
						currentCostPrice = currentCostPrice.add(cost.getCurrentCostPrice());
						currentCostPriceLL = currentCostPriceLL.add(cost.getCurrentCostPriceLL());
					}
					
					X_T_BOMLine tboml = new X_T_BOMLine(ctx, 0, null);
					tboml.setPP_Product_BOM_ID(PP_Product_BOM_ID);
					tboml.setPP_Product_BOMLine_ID(rs.getInt(1));
					tboml.setM_Product_ID(M_Product_ID);
					tboml.setM_CostElement_ID(element.getM_CostElement_ID());
					tboml.setCurrentCostPrice(currentCostPrice);
					tboml.setCurrentCostPriceLL(currentCostPriceLL);
					tboml.setQtyBOM(qty);
					tboml.setLevelNo(LevelNo);
					tboml.setLevels(levels.substring(0, LevelNo) + LevelNo);
					tboml.setSeqNo(SeqNo);
					tboml.setC_AcctSchema_ID(p_C_AcctSchema_ID);
					tboml.setAD_PInstance_ID(AD_PInstance_ID);
					tboml.setSel_Product_ID(p_M_Product_ID);
					tboml.setImplosion(false);
					tboml.save();
					component(rs.getInt(2));
				}
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
