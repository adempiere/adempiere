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

import java.sql.Timestamp;
import java.util.Collection;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.X_T_Selection;
import org.compiere.model.MBPartner;
import org.compiere.model.MDocType;
import org.compiere.model.MLocator;
import org.compiere.model.MOrg;
import org.compiere.model.MPInstance;
import org.compiere.model.MStorage;
import org.compiere.model.MWarehouse;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Msg;
import org.eevolution.engines.Warehouse.WMRuleEngine;
import org.eevolution.model.MDDOrder;
import org.eevolution.model.MDDOrderLine;
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
	protected int p_C_DocType_ID = 0;
	protected int p_WM_Area_Type_ID = 0;
	protected int p_WM_Section_Type_ID = 0;
	protected int p_AD_User_ID = 0 ;
	protected MDDOrder order = null;
	protected MLocator m_locator = null;
	private Timestamp Today = new Timestamp (System.currentTimeMillis());  
	/**
	 * 	Get Parameters
	 */
	protected void prepare ()
	{
		
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
			else if (name.equals("M_Locator_ID"))
			{
				p_M_Locator_ID = para.getParameterAsInt();
				m_locator = new MLocator(getCtx(), p_M_Locator_ID, get_TrxName());
			}
			else if (name.equals("AD_User_ID"))
			{
				p_AD_User_ID = para.getParameterAsInt();
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
		int seq = 10;
		for (X_T_Selection s : getSelected())
		{
			MWMInOutBoundLine boundline = new MWMInOutBoundLine(getCtx(), s.getT_Selection_ID(), get_TrxName());		
			seq ++;
		}
		return ""; //;"@DocumentNo@ " + bound.getDocumentNo();
	}
	
	/**
	 * createDO
	 * @param MWMInOutBoundLine boundline
	 */
	protected void createDO(MWMInOutBoundLine boundline)
	{
		if(order == null)
		{	
			order =new MDDOrder(boundline.getCtx(), 0 , boundline.get_TrxName());
			order.setC_DocType_ID(0);
			//m_order.setBPartner(bp)
		}
		
		WMRuleEngine engineRule = WMRuleEngine.get();
		Collection<MStorage> storages = engineRule.getMStorage(boundline, p_WM_Area_Type_ID, p_WM_Section_Type_ID);
		
		for (MStorage storage: storages)
		{			
			MDDOrderLine orderLine = new MDDOrderLine(order);
			orderLine.setM_Locator_ID(storage.getM_Locator_ID());
			orderLine.setM_LocatorTo_ID(p_M_Locator_ID);
			orderLine.setC_UOM_ID(boundline.getC_UOM_ID());
			orderLine.setM_Product_ID(boundline.getM_Product_ID());
			orderLine.setConfirmedQty(storage.getQtyOnHand());
			orderLine.setQtyEntered(storage.getQtyOnHand());
			orderLine.setQtyOrdered(storage.getQtyOnHand());
			orderLine.saveEx();
		}	
	}
	
	/**
	 * get Selected
	 * @return collection getSelected
	 */
	protected Collection<X_T_Selection> getSelected()
	{
		final String whereClause = MPInstance.COLUMNNAME_AD_PInstance_ID + "= ?";
		return new Query(getCtx(),X_T_Selection.Table_Name,whereClause, get_TrxName())
		.setParameters(new Object[]{this.getAD_PInstance_ID()})
		.list();
	}
	
	/**
	 * create Distribution Order
	 * @param boundline
	 * @param storages
	 * @throws AdempiereException
	 */
	protected void createDDOrder(MWMInOutBoundLine boundline, Collection<MStorage> storages)
	throws AdempiereException
	{		
			int M_Shipper_ID = 0;
			MDDOrder order = null;
			Integer DD_Order_ID = 0;
			int docTypeDO_ID = getDocType(MDocType.DOCBASETYPE_DistributionOrder);
			
			//get the warehouse in transit
			MWarehouse[] wsts = MWarehouse.getInTransitForOrg(getCtx(), m_locator.getAD_Org_ID());
			if (wsts == null)
			{	
				throw new AdempiereException("Do not exist transit warehouse");
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
				//order.setAD_User_ID(bp.getPrimaryAD_User_ID());
				order.setAD_User_ID(p_AD_User_ID );
				order.setC_DocType_ID(docTypeDO_ID);  
				order.setM_Warehouse_ID(wsts[0].get_ID());
				order.setDocAction(MDDOrder.DOCACTION_Complete);
				order.setDateOrdered(getToday());                       
				order.setDatePromised(getToday());
				order.setM_Shipper_ID(M_Shipper_ID);	    	                
				order.setIsInDispute(false);
				order.setIsInTransit(false);
				//order.setSalesRep_ID(m_product_planning.getPlanner_ID());
				order.setSalesRep_ID(bp.getPrimaryAD_User_ID());
				order.saveEx();
			}
			
			for (MStorage storage: storages)
			{			
				MDDOrderLine oline = new MDDOrderLine(order);
				oline.setM_Locator_ID(storage.getM_Locator_ID());
				oline.setM_LocatorTo_ID(p_M_Locator_ID);
				oline.setC_UOM_ID(boundline.getC_UOM_ID());
				oline.setM_Product_ID(boundline.getM_Product_ID());
				oline.setDateOrdered(getToday());                       
				oline.setDatePromised(getToday());
				oline.setConfirmedQty(storage.getQtyOnHand());
				oline.setQtyEntered(storage.getQtyOnHand());
				oline.setQtyOrdered(storage.getQtyOnHand());
				oline.setTargetQty(storage.getQtyOnHand());
				oline.setIsInvoiced(false);
				oline.saveEx();
			}	
	}
	
	/**
	 * get Document Type
	 * @param docBaseType
	 * @return int Document Type
	 */
	protected int getDocType(String docBaseType)
	{
		MDocType[] doc = MDocType.getOfDocBaseType(getCtx(), docBaseType);

		if (doc == null || doc.length == 0) 
		{
			String reference = Msg.getMsg(getCtx(), "SequenceDocNotFound");
			String textMsg = "Not found default document type for docbasetype "+ docBaseType;
			throw new AdempiereException(textMsg);
		} 
		else
		{
			log.info("Doc Type for "+docBaseType+": "+ doc[0].getC_DocType_ID());
			return doc[0].getC_DocType_ID();
		}
	}
	
	/**
	 * getToday 
	 * @return Timestamp with today
	 */
	protected Timestamp getToday()
	{
		return this.Today;
	}
}
