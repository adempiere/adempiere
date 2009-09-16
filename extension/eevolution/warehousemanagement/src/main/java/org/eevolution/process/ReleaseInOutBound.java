/**********************************************************************
 * This file is part of Adempiere ERP Bazaar                          * 
 * http://www.adempiere.org                                           * 
 *                                                                    * 
 * Copyright (C) Victor Perez	                                      * 
 * Copyright (C) Contributors                                         * 
 *                                                                    * 
 * This program is free software; you can redistribute it and/or      * 
 * modify it under the terms of the GNU General Public License        * 
 * as published by the Free Software Foundation; either version 2     * 
 * of the License, or (at your option) any later version.             * 
 *                                                                    * 
 * This program is distributed in the hope that it will be useful,    * 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of     * 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the       * 
 * GNU General Public License for more details.                       * 
 *                                                                    * 
 * You should have received a copy of the GNU General Public License  * 
 * along with this program; if not, write to the Free Software        * 
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,         * 
 * MA 02110-1301, USA.                                                * 
 *                                                                    * 
 * Contributors:                                                      * 
 *  - Victor Perez (victor.perez@e-evolution.com	 )                *
 *                                                                    *
 * Sponsors:                                                          *
 *  - e-Evolution (http://www.e-evolution.com/)                       *
 **********************************************************************/

package org.eevolution.process;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MBPartner;
import org.compiere.model.MDocType;
import org.compiere.model.MLocator;
import org.compiere.model.MOrderLine;
import org.compiere.model.MOrg;
import org.compiere.model.MProduct;
import org.compiere.model.MProductPO;
import org.compiere.model.MQuery;
import org.compiere.model.MRequisition;
import org.compiere.model.MRequisitionLine;
import org.compiere.model.MStorage;
import org.compiere.model.MTable;
import org.compiere.model.MWarehouse;
import org.compiere.model.PrintInfo;
import org.compiere.model.Query;
import org.compiere.model.X_C_BP_Group;
import org.compiere.print.MPrintFormat;
import org.compiere.print.ReportCtl;
import org.compiere.print.ReportEngine;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.eevolution.engines.Warehouse.WMRuleEngine;
import org.eevolution.model.I_WM_InOutBound;
import org.eevolution.model.I_WM_InOutBoundLine;
import org.eevolution.model.MDDOrder;
import org.eevolution.model.MDDOrderLine;
import org.eevolution.model.MPPMRP;
import org.eevolution.model.MWMInOutBound;
import org.eevolution.model.MWMInOutBoundLine;

/**
 *	
 *  @author victor.perez@e-evolution.com, www.e-evolution.com
 *  @version $Id: $
 */
public class ReleaseInOutBound extends SvrProcess
{	
	/** Record ID */
	protected int p_Record_ID = 0;	
	protected int p_M_Locator_ID = 0;
	protected String p_DocAction = null;
	protected String p_DeliveryRule = null;
	protected int p_C_DocType_ID = 0;
	protected int p_WM_Area_Type_ID = 0;
	protected int p_WM_Section_Type_ID = 0;
	protected boolean p_IsPrintPickList = false;
	protected boolean p_IsCreateSupply = false;
	
	private MLocator m_locator = null;
	private int AD_User_ID = 0;
	private Timestamp Today = new Timestamp (System.currentTimeMillis()); 
	
	private MDDOrder order = null;
	
	/**
	 * 	Get Parameters
	 */
	protected void prepare ()
	{
		AD_User_ID = Env.getAD_User_ID(getCtx());
		p_Record_ID = getRecord_ID();
		for (ProcessInfoParameter para:getParameter())
		{
			String name = para.getParameterName();
			if (para.getParameter() == null)
				;
			else if (name.equals("WM_Area_Type_ID"))
			{
				p_WM_Area_Type_ID = para.getParameterAsInt();
			}
			else if (name.equals("WM_Section_Type_ID"))
			{
				p_WM_Section_Type_ID = para.getParameterAsInt();
			}
			else if (name.equals("DeliveryRule"))
			{
				p_DeliveryRule = (String)para.getParameter();
			}
			else if (name.equals("DocAction"))
			{
				p_DocAction = (String)para.getParameter();
			}
			else if (name.equals("C_DocType_ID"))
			{
				p_C_DocType_ID = para.getParameterAsInt();
			}
			else if (name.equals("M_Locator_ID"))
			{
				p_M_Locator_ID = para.getParameterAsInt();
				m_locator = new MLocator(getCtx(), p_M_Locator_ID, get_TrxName());
			}
			else if (name.equals("IsPrintPickList"))
			{
				p_IsPrintPickList = "Y".equals(para.getParameter());
			}
			else if (name.equals("IsCreateSupply"))
			{
				p_IsCreateSupply = "Y".equals(para.getParameter());
			}
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}

	/**
	 * 	Process - Generate Export Format
	 *	@return info
	 */
	@SuppressWarnings("unchecked")
	protected String doIt () throws Exception
	{
		String whereClause = "EXISTS (SELECT T_Selection_ID FROM T_Selection WHERE  T_Selection.AD_PInstance_ID=? AND T_Selection.T_Selection_ID=WM_InOutBoundLine.WM_InOutboundLine_ID)";		
		Collection <MWMInOutBoundLine> lines = new Query(getCtx(), I_WM_InOutBoundLine.Table_Name, whereClause, get_TrxName())
										.setClient_ID()
										.setParameters(new Object[]{getAD_PInstance_ID()})
										.list();
		
		int seq = 10;
		for (MWMInOutBoundLine boundline: lines)
		{
			MWMInOutBound bound = boundline.getParent();
			if(MWMInOutBound.DOCSTATUS_Completed.equals(bound.getDocStatus()) 
			|| MWMInOutBound.DOCSTATUS_Closed.equals(bound.getDocStatus())
			|| MWMInOutBound.DOCSTATUS_Voided.equals(bound.getDocStatus()))
			{
				continue;
			}
			else
			{
				bound.completeIt();
				bound.saveEx();
			}
			createDDOrder(boundline);
			seq ++;
		}
		
		
		if(p_IsPrintPickList && order != null)
		{
			// Get Format & Data
			ReportEngine re = this.getReportEngine("DistributionOrder_Header  ** TEMPLATE **","DD_Order_Header_v", order.getDD_Order_ID());
			if(re == null )
			{
				return"";
			}
			ReportCtl.preview(re);
			re.print(); // prints only original
		}
		
		return "@DocumentNo@ " + order.getDocumentNo();
	}

	
	/**
	 * create Distribution Order
	 * @param boundline
	 */
	protected void createDDOrder(MWMInOutBoundLine boundline)
	{		
		
			WMRuleEngine engineRule = WMRuleEngine.get();
			Collection<MStorage> storages = engineRule.getMStorage(boundline, p_WM_Area_Type_ID, p_WM_Section_Type_ID);
		
			int M_Shipper_ID = 0;
			MDDOrder order = null;
			//get the warehouse in transit
			MWarehouse[] wsts = MWarehouse.getInTransitForOrg(getCtx(), m_locator.getAD_Org_ID());
			if (wsts == null)
			{	
				throw new AdempiereException("Do not exist Transit Warehouse");
			}
				
			//Org Must be linked to BPartner
			MOrg org = MOrg.get(getCtx(),  m_locator.getAD_Org_ID());
			int C_BPartner_ID = org.getLinkedC_BPartner_ID(get_TrxName()); 
			if (C_BPartner_ID == 0)
			{
				throw new AdempiereException("Do not exist Business Parter link for organization:" + org.getName());
			}
				
			MBPartner bp = MBPartner.get(getCtx(), C_BPartner_ID);
			
			if(order == null)
			{
				order = new MDDOrder(getCtx() , 0 , get_TrxName());
				order.setAD_Org_ID(m_locator.getAD_Org_ID());
				order.setC_BPartner_ID(C_BPartner_ID);
				if(p_C_DocType_ID > 0)
				{	
					order.setC_DocType_ID(p_C_DocType_ID);
				}	
				else
				{
					order.setC_DocType_ID(MDocType.getDocType(MDocType.DOCBASETYPE_DistributionOrder));
				}
				
				order.setM_Warehouse_ID(wsts[0].get_ID());
				if(p_DocAction != null)
				{	
					order.setDocAction(p_DocAction);
				}
				else
				{
					order.setDocAction(MDDOrder.DOCACTION_Prepare);
				}
				
				order.setDateOrdered(getToday());                       
				order.setDatePromised(getToday());
				order.setM_Shipper_ID(M_Shipper_ID);	    	                
				order.setIsInDispute(false);
				order.setIsInTransit(false);
				order.setSalesRep_ID(bp.getPrimaryAD_User_ID());
				order.saveEx();
			}
	
			BigDecimal qtySupply = Env.ZERO;
			for (MStorage storage: storages)
			{			
				if (qtySupply.compareTo(boundline.getQtyToPick()) <= 0)
				{
					MDDOrderLine oline = new MDDOrderLine(order);
					oline.setM_Locator_ID(storage.getM_Locator_ID());
					oline.setM_LocatorTo_ID(p_M_Locator_ID);
					oline.setC_UOM_ID(boundline.getC_UOM_ID());
					oline.setM_Product_ID(boundline.getM_Product_ID());
					oline.setDateOrdered(getToday());                       
					oline.setDatePromised(boundline.getPickDate());
					oline.setConfirmedQty(storage.getQtyOnHand());
					oline.setQtyEntered(storage.getQtyOnHand());
					oline.setQtyOrdered(storage.getQtyOnHand());
					oline.setTargetQty(storage.getQtyOnHand());
					oline.setIsInvoiced(false);
					oline.saveEx();
					qtySupply = qtySupply.add(storage.getQtyOnHand());					
				}
				else
				{
					break;
				}				
			}
			
			if (p_IsCreateSupply && qtySupply.compareTo(boundline.getQtyToPick()) < 0)
			{
				MProduct product = MProduct.get(getCtx(), boundline.getM_Product_ID());
				
				BigDecimal QtyPlanned = boundline.getQtyToPick().subtract(qtySupply);
				// Requisition
				if (product.isPurchased()) // then create M_Requisition
				{
					createRequisition(product, QtyPlanned ,boundline.getPickDate());
				}
				// Manufacturing Order
				else if (product.isBOM())
				{
					MOrderLine ol = boundline.getMOrderLine();
					MPPMRP.createMOMakeTo(ol, QtyPlanned);	
				}
				else
				{
					throw new IllegalStateException("Internal Error: Don't know what document to "
													+"create for "+product.getName());
				}	
			}
	}
	
	/**
	 * Create Requisition
	 * @param product Product
	 * @param QtyPlanned Qty Planned
	 * @param demandDate 
	 */
	public  void createRequisition(MProduct product, BigDecimal QtyPlanned, Timestamp demandDate)
	{
		//s_log.info("Create Requisition");
		int C_BPartner_ID = 0;
		int M_PriceList_ID = 0;
		MProductPO po = null;
		for (MProductPO ppo : MProductPO.getOfProduct(getCtx(), product.get_ID(), get_TrxName()))
		{
			if (ppo.isCurrentVendor() && ppo.getC_BPartner_ID() != 0)
			{
				C_BPartner_ID = ppo.getC_BPartner_ID();
				po = ppo;
				break;
			}
		}
		
			final String sql = "SELECT COALESCE(bp."+MBPartner.COLUMNNAME_PO_PriceList_ID
			+",bpg."+X_C_BP_Group.COLUMNNAME_PO_PriceList_ID+")"
			+" FROM C_BPartner bp"
			+" INNER JOIN C_BP_Group bpg ON (bpg.C_BP_Group_ID=bp.C_BP_Group_ID)"
			+" WHERE bp.C_BPartner_ID=?";
			M_PriceList_ID = DB.getSQLValueEx(get_TrxName(), sql, C_BPartner_ID);

		MRequisition req = new  MRequisition(getCtx(),0, get_TrxName()); 
		req.setAD_Org_ID(m_locator.getAD_Org_ID());
		req.setAD_User_ID(AD_User_ID);                                                        
		req.setDateRequired(demandDate);
		req.setDescription("Generate from Outbound Order"); // TODO: add translation
		req.setM_Warehouse_ID(m_locator.getM_Warehouse_ID());
		req.setC_DocType_ID(MDocType.getDocType(MDocType.DOCBASETYPE_PurchaseRequisition));
		if (M_PriceList_ID > 0)
			req.setM_PriceList_ID(M_PriceList_ID);
		req.saveEx();

		MRequisitionLine reqline = new  MRequisitionLine(req);
		reqline.setLine(10);
		reqline.setAD_Org_ID(m_locator.getAD_Org_ID());
		reqline.setC_BPartner_ID(C_BPartner_ID);
		reqline.setM_Product_ID(product.getM_Product_ID());
		reqline.setPrice();
		reqline.setPriceActual(Env.ZERO);
		reqline.setQty(QtyPlanned);
		reqline.saveEx();
	}
	
	/*
	 * get the a Report Engine Instance using the view table 
	 * @param tableName
	 */
	private ReportEngine getReportEngine(String formatName, String tableName,int record_id)
	{
		// Get Format & Data
		int format_id= MPrintFormat.getPrintFormat_ID(formatName, MTable.getTable_ID(tableName), getAD_Client_ID());
		MPrintFormat format = MPrintFormat.get(getCtx(), format_id, true);
		if (format == null)
		{
			addLog("@NotFound@ @AD_PrintFormat_ID@");
			return null;
		}
		// query
		MQuery query = new MQuery(tableName);
		query.addRestriction("DD_Order_ID", MQuery.EQUAL, record_id);
		// Engine
		PrintInfo info = new PrintInfo(tableName,  MTable.getTable_ID(tableName), record_id);
		ReportEngine re = new ReportEngine(getCtx(), format, query, info);
		return re;
	}
	
	/**
	 * get Today
	 * @return Today
	 */
	protected Timestamp getToday()
	{
		return this.Today;
	}
}
