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
package org.eevolution.model;

import java.util.*;
import java.sql.*;
import java.math.*;

import org.compiere.util.*;
import org.compiere.model.*;

/**
 *  Order Line Model.
 * 	<code>
 * 			MOrderLine ol = new MOrderLine(m_order);
			ol.setM_Product_ID(wbl.getM_Product_ID());
			ol.setQtyOrdered(wbl.getQuantity());
			ol.setPrice();
			ol.setPriceActual(wbl.getPrice());
			ol.setTax();
			ol.save();

 *	</code>
 *  @author Victor Perez www.e-evolution.com     
 *  @version $Id: MOrderLine.java,v 1.22 2004/03/22 07:15:03 vpj-cd Exp $
 */
public class MPPOrderBOMLine extends X_PP_Order_BOMLine
{
	/**
	 *  Default Constructor
	 *  @param ctx context
	 *  @param  C_OrderLine_ID  order line to load
	 */
	public MPPOrderBOMLine(Properties ctx, int PP_Order_BOMLine_ID,String trxName)
	{
		super (ctx, PP_Order_BOMLine_ID,trxName);  
		if (PP_Order_BOMLine_ID == 0)
		{
                setQtyDelivered(Env.ZERO);
                setQtyPost(Env.ZERO);
                setQtyReject(Env.ZERO);
                setQtyRequiered(Env.ZERO);
                setQtyReserved(Env.ZERO);
                setQtyScrap(Env.ZERO);
		}	
	}	//	PP_Order_BOMLine_ID
        

	/**
	 *  Load Constructor
	 *  @param ctx context
	 *  @param rs result set record
	 */
	public MPPOrderBOMLine(Properties ctx, ResultSet rs,String trxName)
	{
		super (ctx, rs,trxName);
	}	//	MOrderLine

	private MPPOrder m_parent = null;

	/**
	 * 	Set Defaults from Order.
	 * 	Does not set Parent !!
	 * 	@param order order
	 */
	public void setMPPOrderBOM (MPPOrderBOM bom)
	{
		//setClientOrg(bom);
		//setC_BPartner_ID(order.getC_BPartner_ID());
		//setC_BPartner_Location_ID(order.getC_BPartner_Location_ID());
		//setM_Warehouse_ID(order.getM_Warehouse_ID());
		//setDateOrdered(order.getDateOrdered());
		//setDatePromised(order.getDatePromised());
		//m_M_PriceList_ID = order.getM_PriceList_ID();
		//m_IsSOTrx = order.isSOTrx();
		//setC_Currency_ID(order.getC_Currency_ID());
	}	//	setOrder
        
        
         private MProduct 	m_product = null;
         private int			m_M_Locator_ID = 0;
         
    /**************************************************************************
     * 	after Save
     *	@param newRecord new
     *	@return save
     */
    protected boolean afterSave(boolean newRecord, boolean success) {
        
		if (!newRecord)
			return success;
    		//Qty Ordered to Phantom                
    		BigDecimal QtyOrdered = getQtyRequiered();
                System.out.println(" Padre Product" +  getM_Product_ID() + " getQtyBatch" + getQtyBatch() + " getQtyRequiered"  + getQtyRequiered() + " QtyScrap" + getQtyScrap());
    		//Phantom
    		if(getComponentType().equals(MPPProductBOMLine.COMPONENTTYPE_Phantom))
    		{
    			 
    			 int PP_Product_BOM_ID = MPPProductBOM.getBOMSearchKey(getM_Product_ID());
    			 if (PP_Product_BOM_ID==0)
    			 	return true;
    			 
    			 MPPProductBOM bom = new MPPProductBOM(getCtx(),PP_Product_BOM_ID,get_TrxName());
    			 if (bom!= null)
    			 {
    			 	MPPProductBOMLine[] PP_Product_BOMline = bom.getLines();
    			 	
    			 	if (PP_Product_BOMline == null)
    			 		return true;
    			 	for(int i = 0 ; i < PP_Product_BOMline.length ; i++ )
    			 	{
    			 		MPPOrderBOMLine PP_Order_BOMLine = new MPPOrderBOMLine(getCtx(),0,get_TrxName());
    			 		MProduct product = new MProduct(getCtx(),PP_Product_BOMline[i].getM_Product_ID(),get_TrxName());
                                        PP_Order_BOMLine.setDescription(PP_Product_BOMline[i].getDescription());
                                        PP_Order_BOMLine.setHelp(PP_Product_BOMline[i].getHelp());
                                        PP_Order_BOMLine.setM_ChangeNotice_ID(PP_Product_BOMline[i].getM_ChangeNotice_ID());
                                        PP_Order_BOMLine.setAssay(PP_Product_BOMline[i].getAssay());
                                        PP_Order_BOMLine.setQtyBatch(PP_Product_BOMline[i].getQtyBatch());
                                        PP_Order_BOMLine.setQtyBOM(PP_Product_BOMline[i].getQtyBOM());
                                        PP_Order_BOMLine.setIsQtyPercentage(PP_Product_BOMline[i].isQtyPercentage());
                                        PP_Order_BOMLine.setComponentType(PP_Product_BOMline[i].getComponentType());          
                                        PP_Order_BOMLine.setC_UOM_ID(PP_Product_BOMline[i].getC_UOM_ID());
                                        PP_Order_BOMLine.setForecast(PP_Product_BOMline[i].getForecast());
                                        PP_Order_BOMLine.setIsCritical(PP_Product_BOMline[i].isCritical());
                                        PP_Order_BOMLine.setIssueMethod(PP_Product_BOMline[i].getIssueMethod());    		                                                  
                                        PP_Order_BOMLine.setLine(MPPOrder.getLines(getPP_Order_ID()).length+10);                                        
                                        PP_Order_BOMLine.setLeadTimeOffset(PP_Product_BOMline[i].getLeadTimeOffset());
                                        PP_Order_BOMLine.setM_AttributeSetInstance_ID(PP_Product_BOMline[i].getM_AttributeSetInstance_ID());
                                        PP_Order_BOMLine.setPP_Order_BOM_ID(getPP_Order_BOM_ID());
                                        PP_Order_BOMLine.setPP_Order_ID(getPP_Order_ID());
                                        PP_Order_BOMLine.setM_Product_ID(PP_Product_BOMline[i].getM_Product_ID());
                                        PP_Order_BOMLine.setScrap(PP_Product_BOMline[i].getScrap());
                                        PP_Order_BOMLine.setValidFrom(PP_Product_BOMline[i].getValidFrom());
                                        PP_Order_BOMLine.setValidTo(PP_Product_BOMline[i].getValidTo());
                                        PP_Order_BOMLine.setM_Warehouse_ID(getM_Warehouse_ID());
    		            
                                        if (PP_Order_BOMLine.isQtyPercentage()) 
                                        {                                            
                                            BigDecimal qty = PP_Order_BOMLine.getQtyBatch().multiply(QtyOrdered);                
                                            System.out.println("product:"+product.getName() +" Qty:"+qty + " QtyOrdered:"+ QtyOrdered + " PP_Order_BOMLine.getQtyBatch():" + PP_Order_BOMLine.getQtyBatch());
                                            if(PP_Order_BOMLine.getComponentType().equals(PP_Order_BOMLine.COMPONENTTYPE_Packing))
                                            PP_Order_BOMLine.setQtyRequiered(qty.divide(new BigDecimal(100),8,qty.ROUND_UP));
                                            else if (PP_Order_BOMLine.getComponentType().equals(PP_Order_BOMLine.COMPONENTTYPE_Component) || PP_Order_BOMLine.getComponentType().equals(PP_Order_BOMLine.COMPONENTTYPE_Phantom))
                                            PP_Order_BOMLine.setQtyRequiered(qty.divide(new BigDecimal(100),8,qty.ROUND_UP));
                                            else if (PP_Order_BOMLine.getComponentType().equals(PP_Order_BOMLine.COMPONENTTYPE_Tools))
                                            PP_Order_BOMLine.setQtyRequiered(PP_Order_BOMLine.getQtyBOM());                                             
                                            
                                            //System.out.println("PP_Order_BOMLinegetQtyRequiered" + PP_Order_BOMLine.getQtyRequiered());
                                        }
                                        else 
                                        {   
                                             //System.out.println("product: "+product.getName() + " QtyOrdered:"+ QtyOrdered + " PP_Order_BOMLine.getQtyBOM():" + PP_Order_BOMLine.getQtyBOM());
                                            if (PP_Order_BOMLine.getComponentType().equals(PP_Order_BOMLine.COMPONENTTYPE_Component) || PP_Order_BOMLine.getComponentType().equals(PP_Order_BOMLine.COMPONENTTYPE_Phantom))                    
                                                    PP_Order_BOMLine.setQtyRequiered(PP_Order_BOMLine.getQtyBOM().multiply(QtyOrdered));
                                            else if (PP_Order_BOMLine.getComponentType().equals(PP_Order_BOMLine.COMPONENTTYPE_Packing))                    
                                                    PP_Order_BOMLine.setQtyRequiered(PP_Order_BOMLine.getQtyBOM().multiply(QtyOrdered));
                                            else if (PP_Order_BOMLine.getComponentType().equals(PP_Order_BOMLine.COMPONENTTYPE_Tools))
                                                    PP_Order_BOMLine.setQtyRequiered(PP_Order_BOMLine.getQtyBOM());                                           
                                        }                                                    

                                        // Set Scrap of Component
                                        BigDecimal Scrap = PP_Order_BOMLine.getScrap();    	       
                                        if (!Scrap.equals(Env.ZERO))
                                        {	
                                        Scrap = Scrap.divide(new BigDecimal(100),8,BigDecimal.ROUND_UP);                                   	
                                        PP_Order_BOMLine.setQtyRequiered(PP_Order_BOMLine.getQtyRequiered().divide( Env.ONE.subtract(Scrap) , 8 ,BigDecimal.ROUND_HALF_UP ));
                                        }
                                        System.out.println("Cantidad Requerida" + PP_Order_BOMLine.getQtyRequiered());
                                        PP_Order_BOMLine.save();     	                                        
    			 	}
    			 }
    			
    		}// end Phantom    	
    		return true;
    		
    	}
	
	

	/**
	 * 	String Representation
	 * 	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("PPProductBOMLine[")
			.append(get_ID())
			.append ("]");
		return sb.toString ();
	}

        
        
	
       
        

	/**
	 * 	Set UOM.
	 * 	make access public
	 *	@param C_UOM_ID uom
	 */
	public void setC_UOM_ID (int C_UOM_ID)
	{
		super.setC_UOM_ID (C_UOM_ID);
	}	//	setC_UOM_ID


        
       /**
	 * 	Get Product
	 *	@return product or null
	 */
	public MProduct getProduct()
	{
		if (m_product == null && getM_Product_ID() != 0)
			m_product = new MProduct (getCtx(), getM_Product_ID() ,get_TrxName());
		return m_product;
	}
        
        	/**
	 * 	Save Temp M_Locator_ID
	 *	@param M_Locator_ID id
	 */
	public void setTempM_Locator_ID (int M_Locator_ID)
	{
		m_M_Locator_ID = M_Locator_ID;
	}	//	setTempM_Locator_ID



	/**
	 * 	Get Parent
	 *	@return parent
	 */
	public MPPOrder getParent()
	{
	if (m_parent == null)
		m_parent = new MPPOrder(getCtx(), getPP_Order_ID(), get_TrxName());
	return m_parent;
	}	//	getParent

}
