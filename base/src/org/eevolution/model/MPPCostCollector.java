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
//package org.compiere.mfg.model;
package org.eevolution.model;


import java.util.*;
import java.sql.*;
import java.math.*;
import java.util.logging.*;
import java.io.*;

import org.compiere.process.*;
import org.compiere.util.*;
import org.compiere.model.*;
import org.compiere.wf.*;
import org.compiere.print.*;
import org.compiere.process.*;
import org.compiere.util.*;
/**
 *	Inventory Movement Model
 *	
 *  @author Victor Perez www.e-evolution.com     
 *  @version $Id: MMovement.java,v 1.1 2004/06/19 02:10:34 vpj-cd Exp $
 */
public class MPPCostCollector extends X_PP_Cost_Collector implements DocAction
{
	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param PP_Cost_Collector id
	 */
	public MPPCostCollector(Properties ctx, int PP_Cost_Collector_ID, String trxName)
	{
		super (ctx, PP_Cost_Collector_ID,trxName);
		if (PP_Cost_Collector_ID == 0)
		{
		//	setC_DocType_ID (0);
			setDocAction (DOCACTION_Complete);	// CO
			setDocStatus (DOCSTATUS_Drafted);	// DR
			//setIsApproved (false);
			//setIsInTransit (false);
			setMovementDate (new Timestamp(System.currentTimeMillis()));	// @#Date@
			setPosted (false);
			super.setProcessed (false);
		}	
	}	//	MMovement

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 */
	public MPPCostCollector(Properties ctx, ResultSet rs,String trxName)
	{
		super (ctx, rs,trxName);
	}	//	MMovement

	
	/**
	 * 	Add to Description
	 *	@param description text
	 */
	public void addDescription (String description)
	{
		String desc = getDescription();
		if (desc == null)
			setDescription(description);
		else
			setDescription(desc + " | " + description);
	}	//	addDescription
	
	/**
	 * 	Set Processed.
	 * 	Propergate to Lines/Taxes
	 *	@param processed processed
	 */
	public void setProcessed (boolean processed)
	{
		super.setProcessed (processed);
		if (get_ID() == 0)
			return;
		String sql = "UPDATE PP_Cost_Collector SET Processed='"
			+ (processed ? "Y" : "N")
			+ "' WHERE PP_Cost_Collector_ID =" + getPP_Cost_Collector_ID();
		int noLine = DB.executeUpdate(sql, get_TrxName());
		//m_lines = null;
		log.fine("setProcessed - " + processed + " - Lines=" + noLine);
	}	//	setProcessed
	
	
	/**************************************************************************
	 * 	Process document
	 *	@param processAction document action
	 *	@return true if performed
	 */
	public boolean processIt (String processAction)
	{
		m_processMsg = null;
		DocumentEngine engine = new DocumentEngine (this, getDocStatus());
		return engine.processIt (processAction, getDocAction());
	}	//	processIt
	
	/**	Process Message 			*/
	private String		m_processMsg = null;
	/**	Just Prepared Flag			*/
	private boolean		m_justPrepared = false;

	/**
	 * 	Unlock Document.
	 * 	@return true if success 
	 */
	public boolean unlockIt()
	{
		log.info("unlockIt - " + toString());
		setProcessing(false);
		return true;
	}	//	unlockIt
	
	/**
	 * 	Invalidate Document
	 * 	@return true if success 
	 */
	public boolean invalidateIt()
	{
		log.info("invalidateIt - " + toString());
		setDocAction(DOCACTION_Prepare);
		return true;
	}	//	invalidateIt
	
	/**
	 *	Prepare Document
	 * 	@return new status (In Progress or Invalid) 
	 */
	public String prepareIt()
	{
		log.info("prepareIt - " + toString());

		org.compiere.model.MDocType dt = MDocType.get(getCtx(), getC_DocType_ID()); //getC_DocType_ID()

		//	Std Period open?
		if (!MPeriod.isOpen(getCtx(), getMovementDate(), dt.getDocBaseType()))
		{
			m_processMsg = "@PeriodClosed@";
			return DocAction.STATUS_Invalid;
		}
		

		
		m_justPrepared = true;
		if (!DOCACTION_Complete.equals(getDocAction()))
			setDocAction(DOCACTION_Complete);
		return DocAction.STATUS_InProgress;
	}	//	prepareIt
	
	/**
	 * 	Approve Document
	 * 	@return true if success 
	 */
        
	public boolean  approveIt()
	{
		log.info("approveIt - " + toString());
		//setIsApproved(true);
		return true;
	}	//	approveIt
	
	/**
	 * 	Reject Approval
	 * 	@return true if success 
	 */
        
	public boolean rejectIt()
	{
		log.info("rejectIt - " + toString());
		//setIsApproved(false);
		return true;
	}	//	rejectIt
	
	/**
	 * 	Complete Document
	 * 	@return new status (Complete, In Progress, Invalid, Waiting ..)
	 */
	public String completeIt()
	{
			//	Re-Check
			if (!m_justPrepared)
			{
				String status = prepareIt();
				if (!DocAction.STATUS_InProgress.equals(status))
					return status;
			}
			

			MProduct product = new MProduct (getCtx(),getM_Product_ID(),get_TrxName());

			//	Qty & Type
			String MovementType = getMovementType();
			
			BigDecimal QtyIssue = Env.ZERO;
			BigDecimal QtyReceipt = Env.ZERO;
			
			BigDecimal Qty = getMovementQty();
			if (MovementType.charAt(1) == '-')
			{	
				Qty = Qty.negate();
				QtyIssue = Qty;
			}
			else
			{
				 QtyReceipt = Qty.negate();
			}
		
			//	Update Order Line
			MPPOrderBOMLine obomline = null;
			if (getPP_Order_BOMLine_ID()!= 0)
			{
				obomline = new MPPOrderBOMLine(getCtx(),getPP_Order_BOMLine_ID(),get_TrxName());
			}	
			
			log.info(" Qty=" + getMovementQty());
            //Operation activity begin
            MDocType doctype = new MDocType(Env.getCtx(),getC_DocType_ID(),get_TrxName());
            if(!doctype.equals(MDocType.DOCBASETYPE_ManufacturingOperationActivity))
            {
			//	Stock Movement 
			if (product != null 
				&& product.isStocked() )
			{
				//Ignore the Material Policy when is Reverse Correction
				checkMaterialPolicy( obomline, getMovementQty());
				
				log.fine("Material Transaction");
				MTransaction mtrx = null; 
				int reservationAttributeSetInstance_ID = getM_AttributeSetInstance_ID();

				if (getM_AttributeSetInstance_ID() == 0)
				{
					MPPOrderBOMLineMA mas[] = MPPOrderBOMLineMA.get(getCtx(),
						getPP_Cost_Collector_ID(), get_TrxName());
					for (int j = 0; j < mas.length; j++)
					{
						MPPOrderBOMLineMA ma = mas[j];
						BigDecimal QtyMA = ma.getMovementQty();
						
						if (MovementType.charAt(1) == '-')
						{	
							QtyMA = QtyMA.negate();
							QtyIssue = QtyMA;
						}
						else
						{
							 QtyReceipt = QtyMA.negate();
						}
						
                        if (!MStorage.add(getCtx(), getM_Warehouse_ID(),
                            getM_Locator_ID(),
                            getM_Product_ID(), 
                            ma.getM_AttributeSetInstance_ID(), reservationAttributeSetInstance_ID,
                            QtyMA, QtyIssue, QtyReceipt, get_TrxName()))
                            {
                               m_processMsg = "Cannot correct Inventory (MA)";
                               return DocAction.STATUS_Invalid;
                            }
                                                
                             //	Create Transaction
                             mtrx = new MTransaction (getCtx(), this.getAD_Org_ID() ,
                             MovementType,getM_Locator_ID(),
                             getM_Product_ID(), ma.getM_AttributeSetInstance_ID(), 
                             QtyMA, getMovementDate(), get_TrxName());                                                                                                           
                                                    	
                             mtrx.setPP_Order_ID(getPP_Order_ID());
                             mtrx.setPP_Order_BOMLine_ID(getPP_Order_BOMLine_ID());
                             if (!mtrx.save(get_TrxName()))
                             {
                                m_processMsg = "Could not create Material Transaction (MA)";
                                return DocAction.STATUS_Invalid;
                             }
                                                
					}
				}
				//	sLine.getM_AttributeSetInstance_ID() != 0
				if (mtrx == null)
				{
					//	Fallback: Update Storage -
                    if (!MStorage.add(getCtx(), getM_Warehouse_ID(), 
                        getM_Locator_ID(),
                        getM_Product_ID(), 
                        getM_AttributeSetInstance_ID(), reservationAttributeSetInstance_ID, Qty, QtyIssue, QtyReceipt, get_TrxName()))
                        {
                              m_processMsg = "Cannot correct Inventory";
                              return DocAction.STATUS_Invalid;
                        }
					//	FallBack: Create Transaction
                                           
                    mtrx = new MTransaction (getCtx(),getAD_Org_ID(),MovementType,getM_Locator_ID(),getM_Product_ID(),getM_AttributeSetInstance_ID(), Qty, getMovementDate(), get_TrxName());
                    mtrx.setPP_Order_ID(getPP_Order_ID());
                    mtrx.setPP_Order_BOMLine_ID(getPP_Order_BOMLine_ID());
                    if (!mtrx.save(get_TrxName()))
                    {
                      m_processMsg = "Could not create Material Transaction";
                      return DocAction.STATUS_Invalid;
                    }
                                                
				}										
					
			}	//	stock movement
			
			if (MovementType.charAt(1) == '-')				
			{
			//	Update Order Line
			if (getPP_Order_BOMLine_ID()!= 0)
			{
				
				obomline = new MPPOrderBOMLine(getCtx(),getPP_Order_BOMLine_ID(),get_TrxName());
				obomline.setQtyDelivered(obomline.getQtyDelivered().add(getMovementQty()));
				obomline.setQtyScrap(obomline.getQtyScrap().add(getScrappedQty()));
				obomline.setQtyReject(obomline.getQtyReject().add(getQtyReject()));  
				obomline.setDateDelivered(getMovementDate());	//	overwrite=last	
                obomline.setM_AttributeSetInstance_ID(getM_AttributeSetInstance_ID());
				log.fine("OrderLine - Reserved=" + obomline.getQtyReserved() + ", Delivered=" + obomline.getQtyDelivered());				
				obomline.setQtyReserved(obomline.getQtyReserved().subtract(getMovementQty()));
										
				if (!obomline.save(get_TrxName()))
				{
					m_processMsg = "Could not update Order Line";
					return DocAction.STATUS_Invalid;
				}
				else
					log.fine("OrderLine -> Reserved=" + obomline.getQtyReserved() 
						+ ", Delivered=" + obomline.getQtyDelivered());
			}
			}
			else if  (MovementType.charAt(1) == '+')
			{
				MPPOrder order = new MPPOrder(getCtx(), getPP_Order_ID(),get_TrxName());
				order.setQtyDelivered(order.getQtyDelivered().add(getMovementQty()));                
                order.setQtyScrap(order.getQtyScrap().add(getScrappedQty()));
                order.setQtyReject(order.getQtyReject().add(getQtyReject()));                
				order.setDateDelivered(getMovementDate());	//	overwrite=last				
				log.fine("OrderLine - Reserved=" + order.getQtyReserved() + ", Delivered=" + order.getQtyDelivered());				
				order.setQtyReserved(order.getQtyReserved().subtract(getMovementQty()));
				if (!order.save(get_TrxName()))
				{
					m_processMsg = "Could not update Order";
					return DocAction.STATUS_Invalid;
				}
				else
					log.fine("Order -> Delivered=" + order.getQtyDelivered());										
			}
			 //                             //fjv e-evolution Operation Activity Report begin
                        }
            			// I need refactory this code because can be improve Victor perez
                        if(doctype.equals(MDocType.DOCBASETYPE_ManufacturingOperationActivity))
                           {
                                        MPPOrderNode onodeact =new MPPOrderNode(Env.getCtx(),getPP_Order_Node_ID(),null);
                                                        onodeact.setDocStatus("CO");
                                                        onodeact.setQtyScrap(onodeact.getQtyScrap().add(getScrappedQty()));
                                                        onodeact.setQtyReject(onodeact.getQtyReject().add(getQtyReject()));
                                                        onodeact.setQtyDelivered(onodeact.getQtyDelivered().add(getMovementQty()));
                                                        onodeact.setDurationReal(onodeact.getDurationReal()+getDurationReal().intValue());
                                                        onodeact.setSetupTimeReal(onodeact.getSetupTimeReal()+getSetupTimeReal().intValue());
                                                        onodeact.save();

                                                        ArrayList list = new ArrayList();
                                                        int count =0;
                                               try
                                             {
                                                    StringBuffer sql=new StringBuffer("SELECT PP_Order_Node_ID FROM PP_Order_Node WHERE IsActive='Y' AND  PP_Order_ID=? Order By Value");
                                                     PreparedStatement pstmt = DB.prepareStatement(sql.toString(),null);
                                                    // pstmt.setInt(1, AD_Client_ID);
                                                     pstmt.setInt(1, getPP_Order_ID());
                                                    //pstmt.setInt(2, m_M_PriceList_ID);
                                                    ResultSet rs = pstmt.executeQuery();
                                                    //while (!m_calculated && rsplv.next())
                                                    while (rs.next())
                                                    {

                                                        Integer nodeid = new Integer(rs.getInt(1));
                                                        list.add(count,nodeid.toString());

                                                        count++;

                                                    }
                                                    rs.close();
                                                    pstmt.close();
                                              }
                                                catch (SQLException enode)
                                                {
                                                }
                                    boolean ultimonodo = false;  
                                    
                                    for (int v =0 ; v < list.size(); v++)
                                    {
                                        if (list.get(v).equals(new Integer(getPP_Order_Node_ID()).toString()))
                                        {
                                            //String nextnode = new String(list.get(v+1));
                                             try
                                             {
                                                    StringBuffer sqlnn=new StringBuffer("SELECT PP_Order_Node_ID FROM PP_Order_NodeNext WHERE IsActive='Y' AND  PP_Order_ID=? and PP_Order_Node_ID=?");
                                                     PreparedStatement pstmtnn = DB.prepareStatement(sqlnn.toString(),null);
                                                    // pstmt.setInt(1, AD_Client_ID);
                                                     pstmtnn.setInt(1, getPP_Order_ID());
                                                    pstmtnn.setInt(2, getPP_Order_Node_ID());
                                                    ResultSet rsnn = pstmtnn.executeQuery();
                                                    //while (!m_calculated && rsplv.next())
                                                    System.out.println("***** SQL ultm nodo " +sqlnn.toString());
                                                    if (rsnn.next())
                                                    {
                                                            
                                                       ultimonodo=false;
                                                    
                                                        

                                                    }
                                                    else
                                                    {
                                                        ultimonodo=true;
                                                    }
                                                    rsnn.close();
                                                    pstmtnn.close();
                                              }
                                                catch (SQLException enodenn)
                                                {
                                                }
                                            if (!ultimonodo)
                                            {
                                                //System.out.println("***** No ES EL ULTIMO NODO");
                                            }
                                            else
                                            {
                                                    try
                                             {
                                                    StringBuffer sql1=new StringBuffer("SELECT DocStatus,PP_Order_Node_ID,DurationRequiered FROM PP_Order_Node WHERE IsActive='Y' AND  PP_Order_ID=? and PP_Order_Node_ID!=?");
                                                     PreparedStatement pstmt1 = DB.prepareStatement(sql1.toString(),null);
                                                    // pstmt.setInt(1, AD_Client_ID);
                                                     pstmt1.setInt(1, getPP_Order_ID());
                                                    pstmt1.setInt(2, getPP_Order_Node_ID());
                                                    ResultSet rs1 = pstmt1.executeQuery();
                                                    //while (!m_calculated && rsplv.next())
 //                                                  System.out.println("***** SQL1 " +sql1 + " variable " +getPP_Order_ID());
                                                    while (rs1.next())
                                                    {
//                                                        System.out.println("***** Nodo " +rs1.getInt(2) +" status " +rs1.getString(1));
//                                                        if(!rs1.getString(1).equals("CL"))
//                                                        {
//                                                            
//                                                            MPPOrderNode onodenext =new MPPOrderNode(Env.getCtx(),rs1.getInt(2),get_TrxName());
//                                                        onodenext.setDocStatus("CL");
//                                                        onodenext.save();
//                                                        }
                                                         createnewnode(rs1.getInt(2),rs1.getBigDecimal(3));  

                                                    }
                                                    rs1.close();
                                                    pstmt1.close();
                                                    
                                                     
                                              }
                                                catch (SQLException enode)
                                                {
                                                }
                                            }
                                        }

                                    }  
                                    
                                                 // crear orden de compra al cmpletar
                                    int p_PP_Order_Node_ID=0;
                                    BigDecimal m_MovementQty=Env.ZERO;

                                      try
                                    {
                                        StringBuffer sql=new StringBuffer("SELECT PP_Order_Node_ID,MovementQty FROM PP_Cost_Collector WHERE IsActive='Y' AND AD_Client_ID=? and PP_Cost_Collector_ID=? ");
                                         PreparedStatement pstmt = DB.prepareStatement(sql.toString(),null);
                                         pstmt.setInt(1, getAD_Client_ID());
                                         pstmt.setInt(2, getPP_Cost_Collector_ID());
                                        //pstmt.setInt(2, m_M_PriceList_ID);
                                        ResultSet rs = pstmt.executeQuery();
                                        //while (!m_calculated && rsplv.next())
                                        while (rs.next())
                                        {
                                            p_PP_Order_Node_ID= rs.getInt(1);
                                            m_MovementQty=rs.getBigDecimal(2);
                                        }
                                        rs.close();
                                        pstmt.close();
                                    }
                                    catch (SQLException e)
                                    {
                                    }

                    

                                  //  if(isSubcontracting())
                                 //   {
                                         int M_Product_ID =0;
                                         String salvado="";
                                         BigDecimal DeliveryTime=Env.ZERO;
                                         try
                                            {
                                              StringBuffer plv=new StringBuffer("SELECT M_Product_ID FROM PP_Order_Node WHERE IsActive='Y' AND PP_Order_Node_ID=? ");
                                                        PreparedStatement pstmtplv = DB.prepareStatement(plv.toString(),null);
                                                        pstmtplv.setInt(1, p_PP_Order_Node_ID);
                                                        //pstmt.setInt(2, m_M_PriceList_ID);
                                                        ResultSet rsplv = pstmtplv.executeQuery();
                                                        //while (!m_calculated && rsplv.next())
                                                        if (rsplv.next())
                                                        {
                                                            M_Product_ID= rsplv.getInt(1);
                                                        }
                                                        rsplv.close();
                                                        pstmtplv.close();
                                            }
                                        catch (SQLException e)
                                            {
                                            }

                                           if (M_Product_ID==0)
                                           {
                                               salvado="No hay un servicio asociado a este subcontrato";
                                               return salvado;
                                           }
                                           else
                                           {
                                                try
                                            {
                                              StringBuffer pp=new StringBuffer("SELECT DeliveryTime_Promised FROM PP_Product_Planning WHERE IsActive='Y' AND M_Product_ID=? ");
                                                        PreparedStatement pstmtpp = DB.prepareStatement(pp.toString(),null);
                                                        pstmtpp.setInt(1, M_Product_ID);
                                                        //pstmt.setInt(2, m_M_PriceList_ID);
                                                        ResultSet rspp = pstmtpp.executeQuery();
                                                        //while (!m_calculated && rsplv.next())
                                                        if (rspp.next())
                                                        {
                                                            DeliveryTime= rspp.getBigDecimal(1);
                                                        }
                                                        rspp.close();
                                                        pstmtpp.close();
                                            }
                                        catch (SQLException e)
                                            {
                                            }
                                           }
                                          //   MPPProfileBOM profileorder = new MPPProfileBOM(Env.getCtx(),m_PP_ProfileBOM_ID);
                                        int m_Client_ID = Integer.parseInt(Env.getContext(Env.getCtx(), "#AD_Client_ID"));    
                                        int m_AD_Org_ID = Integer.parseInt(Env.getContext(Env.getCtx(), "#AD_Org_ID"));                   
                     
                                      int C_BPartner_ID=0;
                                              try
                                        {
                                          StringBuffer sqlpo=new StringBuffer("SELECT C_BPartner_ID FROM M_Product_PO WHERE IsActive='Y' AND M_Product_ID=? ");
                                                    PreparedStatement pstmtpo = DB.prepareStatement(sqlpo.toString(),null);
                                                    pstmtpo.setInt(1, M_Product_ID);
                                                    //pstmt.setInt(2, m_M_PriceList_ID);
                                                    ResultSet rspo = pstmtpo.executeQuery();
                                                    //while (!m_calculated && rsplv.next())
                                                    while (rspo.next())
                                                    {
                                                        C_BPartner_ID= rspo.getInt(1);
                                                    }
                                                    rspo.close();
                                                    pstmtpo.close();
                                        }
                                    catch (SQLException epo)
                                        {
                                        }

                                      if (C_BPartner_ID==0)
                                       {
                                           salvado="No hay un proveedor asociado a este servicio de subcontrato";
                                           return salvado;
                                       }
                                      MPPOrder mpcorder = new MPPOrder(Env.getCtx(),getPP_Order_ID(),get_TrxName());
                                      String documentno = mpcorder.getDocumentNo();

                                     Timestamp today=new Timestamp(System.currentTimeMillis());

                                            MOrder order = new MOrder(Env.getCtx(),0,get_TrxName());
                                            order.setC_BPartner_ID(C_BPartner_ID);
                                            order.setIsSOTrx(false);
                                            order.setC_DocTypeTarget_ID();
                                            order.setDatePromised(TimeUtil.addDays(today, DeliveryTime.intValue()));
                                            order.setDescription(documentno);
                                            if (order.save())
                                            {
                                            MOrderLine oline = new MOrderLine(order);
                                            oline.setM_Product_ID(M_Product_ID);
                                            oline.setQtyEntered(m_MovementQty);
                                            oline.setQtyOrdered(m_MovementQty);
                                            oline.setDatePromised(TimeUtil.addDays(today, DeliveryTime.intValue()));
                                            if(oline.save())
                                            {
                                            MOrderLine oline1 = new MOrderLine(order);
                                            oline1.setPriceEntered(oline.getPriceActual());
                                            oline1.setQtyOrdered(oline.getQtyEntered());
                                            oline1.save();
                                            }

                                            MPPCostCollector cc = new MPPCostCollector(Env.getCtx(),getPP_Cost_Collector_ID(),get_TrxName());
                                            cc.setProcessing(true);
                                            cc.setDescription(order.getDocumentNo());
                                            cc.save();
                                            }
                                            else
                                            {
                                                return DocAction.STATUS_Invalid;
                                            }
                                         //fjv e-evolution Operation Activity Report end	
//                        }
                        } 
                        
                       
		//	for all lines	
		setProcessed(true);
		setDocAction(DOCACTION_Close);
                 setDocStatus(DOCSTATUS_Completed);    //fjv e-evolution add field Docstatus
		return DocAction.STATUS_Completed;
	}	//	completeIt
	
	/**
	 * 	Post Document - nothing
	 * 	@return true if success 
	 */
	public boolean postIt()
	{
		log.info("postIt - " + toString());
		return false;
	}	//	postIt
	
	/**
	 * 	Void Document.
	 * 	@return true if success 
	 */
	public boolean voidIt()
	{
		log.info("voidIt - " + toString());
		return false;
	}	//	voidIt
	
	/**
	 * 	Close Document.
	 * 	@return true if success 
	 */
	public boolean closeIt()
	{
		log.info("closeIt - " + toString());

		//	Close Not delivered Qty
                // fjviejo e-evolution operation activity
                boolean ultimonodo=false;
                 try
                                             {
                                                    StringBuffer sqlnn=new StringBuffer("SELECT PP_Order_Node_ID FROM PP_Order_NodeNext WHERE IsActive='Y' AND  PP_Order_ID=? and PP_Order_Node_ID=?");
                                                     PreparedStatement pstmtnn = DB.prepareStatement(sqlnn.toString(),null);
                                                    // pstmt.setInt(1, AD_Client_ID);
                                                     pstmtnn.setInt(1, getPP_Order_ID());
                                                    pstmtnn.setInt(2, getPP_Order_Node_ID());
                                                    ResultSet rsnn = pstmtnn.executeQuery();
                                                    //while (!m_calculated && rsplv.next())
                                                    System.out.println("***** SQL ultm nodo " +sqlnn.toString());
                                                    if (rsnn.next())
                                                    {
                                                            
                                                       ultimonodo=false;
                                                    
                                                        

                                                    }
                                                    else
                                                    {
                                                        ultimonodo=true;
                                                    }
                                                    rsnn.close();
                                                    pstmtnn.close();
                                              }
                                                catch (SQLException enodenn)
                                                {
                                                }
                
                if(!ultimonodo)
                {
                    
                     MPPOrderNode onodeact =new MPPOrderNode(Env.getCtx(),getPP_Order_Node_ID(),null);
                                                            onodeact.setDocStatus("CL");
                                                         //   onodeact.setAction(DOCACTION_None);
                                                            onodeact.save();
                     try
                                             {
                                                    StringBuffer sql1=new StringBuffer("SELECT PP_Cost_Collector_ID FROM PP_Cost_Collector WHERE IsActive='Y' AND  PP_Order_ID=? AND PP_Order_Node_ID=?");
                                                     PreparedStatement pstmt1 = DB.prepareStatement(sql1.toString(),null);
                                                    // pstmt.setInt(1, AD_Client_ID);
                                                     pstmt1.setInt(1, getPP_Order_ID());
                                                    pstmt1.setInt(2, getPP_Order_Node_ID());
                                                    ResultSet rs1 = pstmt1.executeQuery();
                                                    //while (!m_calculated && rsplv.next())
                                                    //System.out.println("***** SQL1 " +sql1 + " variable " +getPP_Order_ID());
                                                    while (rs1.next())
                                                    {
                                                             MPPCostCollector costcoll = new MPPCostCollector(Env.getCtx(),rs1.getInt(1),get_TrxName());
                                                                            costcoll.setDocStatus("CL");
                                                                            costcoll.setDocAction(DOCACTION_None);
                                                                            costcoll.save();
                                                                            
                                                   }
                                                    rs1.close();
                                                    pstmt1.close();
                                                    
                                                     
                                              }
                                                catch (SQLException enode1)
                                                {
                                                }
                }
                else
                {
                        try
                                             {
                                                    StringBuffer sql1=new StringBuffer("SELECT DocStatus,PP_Order_Node_ID,DurationRequiered FROM PP_Order_Node WHERE IsActive='Y' AND  PP_Order_ID=?");
                                                     PreparedStatement pstmt1 = DB.prepareStatement(sql1.toString(),null);
                                                    // pstmt.setInt(1, AD_Client_ID);
                                                     pstmt1.setInt(1, getPP_Order_ID());
                                                    //pstmt1.setInt(2, getPP_Order_Node_ID());
                                                    ResultSet rs1 = pstmt1.executeQuery();
                                                    //while (!m_calculated && rsplv.next())
                                                    //System.out.println("***** SQL1 " +sql1 + " variable " +getPP_Order_ID());
                                                    while (rs1.next())
                                                    {
                                                        System.out.println("***** Nodo " +rs1.getInt(2) +" status " +rs1.getString(1));
                                                        if(!rs1.getString(1).equals("CL"))
                                                        {
                                                            
                                                            MPPOrderNode onodenext =new MPPOrderNode(Env.getCtx(),rs1.getInt(2),get_TrxName());
                                                            onodenext.setDocStatus("CL");
                                                            onodenext.save();
                                                            
                                                            
                                                        }
 

                                                    }
                                                    rs1.close();
                                                    pstmt1.close();
                                                    
                                                     
                                              }
                                                catch (SQLException enode)
                                                {
                                                }
                        closenew(getPP_Order_ID(),getPP_Order_Node_ID());
                }
                // fjviejo e-evolution operation activity end
		setDocAction(DOCACTION_None);
		return true;
	}	//	closeIt
	
	/**
	 * 	Reverse Correction
	 * 	@return false 
	 */
	public boolean reverseCorrectIt()
	{
		log.info("reverseCorrectIt - " + toString());
		return false;
	}	//	reverseCorrectionIt
	
	/**
	 * 	Reverse Accrual - none
	 * 	@return false 
	 */
	public boolean reverseAccrualIt()
	{
		log.info("reverseAccrualIt - " + toString());
		return false;
	}	//	reverseAccrualIt
	
	/** 
	 * 	Re-activate
	 * 	@return false 
	 */
	public boolean reActivateIt()
	{
		log.info("reActivateIt - " + toString());
		return false;
	}	//	reActivateIt
	
	
	/*************************************************************************
	 * 	Get Summary
	 *	@return Summary of Document
	 */
	public String getSummary()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(getDescription());
		//	: Total Lines = 123.00 (#1)
		//sb.append(": ")
		//	.append(Msg.translate(getCtx(),"ApprovalAmt")).append("=").append(getApprovalAmt())
		//	.append(" (#").append(")");
		//	 - Description
		if (getDescription() != null && getDescription().length() > 0)
			sb.append(" - ").append(getDescription());
		return sb.toString();
	}	//	getSummary
	
	/**
	 * 	Get Process Message
	 *	@return clear text error message
	 */
	public String getProcessMsg()
	{
		return m_processMsg;
	}	//	getProcessMsg
	
	/**
	 * 	Get Document Owner (Responsible)
	 *	@return AD_User_ID
	 */
	public int getDoc_User_ID()
	{
		return getCreatedBy();
	}	//	getDoc_User_ID

	/**
	 * 	Get Document Currency
	 *	@return C_Currency_ID
	 */
	public int getC_Currency_ID()
	{
	//	MPriceList pl = MPriceList.get(getCtx(), getM_PriceList_ID());
	//	return pl.getC_Currency_ID();
		return 0;
	}

	/**
	 * 	Get Document Approval Amount
	 *	@return amount
	 */
	public BigDecimal getApprovalAmt()
	{
		return new  BigDecimal(0);
	}	//	getApprovalAmt
	
           /**
     * 	Create PDF
     *	@return File or null
     */
    public File createPDF ()
    {
		try
		{
			File temp = File.createTempFile(get_TableName()+get_ID()+"_", ".pdf");
			return createPDF (temp);
		}
		catch (Exception e)
		{
			log.severe("Could not create PDF - " + e.getMessage());
		}
		return null;
    }	//	getPDF
    
    	/**
	 * 	Create PDF file
	 *	@param file output file
	 *	@return file if success
	 */
	public File createPDF (File file)
	{
		ReportEngine re = ReportEngine.get (getCtx(), ReportEngine.ORDER, getPP_Order_ID());
		if (re == null)
			return null;
		return re.getPDF(file);
	}	//	createPDF
        
        	/**
	 * 	Get Document Info
	 *	@return document info (untranslated)
	 */
	public String getDocumentInfo()
	{
		org.compiere.model.MDocType dt = org.compiere.model.MDocType.get(getCtx(), getC_DocType_ID());
		return dt.getName() + " " + getDocumentNo();
	}	//	getDocumentInfo
        
        public String getDocumentNo()
        {
            return "";
        }    
        
	  protected void createnewnode(int node, BigDecimal duration)
          {
	
    	
                   //fjv e-evolution Operation Activity Report begin
                                try
                                {
                                         String sqlar="SELECT PP_Cost_Collector_ID FROM PP_Cost_Collector WHERE IsActive='Y' AND  PP_Order_ID="+getPP_Order_ID() +"  and PP_Order_Node_ID="+node;
                                                             PreparedStatement pstmtar = DB.prepareStatement(sqlar,null);
                                                            // pstmt.setInt(1, AD_Client_ID);
                                                           //  pstmtar.setInt(1, getPP_Order_ID());
                                                            //pstmtar.setInt(2, rs1.getInt(2));
                                                             // System.out.println("***** SQLar " +sqlar + " variables " +getPP_Order_ID() +" nodo "+node);
                                                            ResultSet rsar = pstmtar.executeQuery();
                                                            
                                                            //while (!m_calculated && rsplv.next())
                                                            if(rsar.next())
                                                            {
                                                               // System.out.println("***** NODO Ya Existe");
                                                            }
                                                            else
                                                            {
                                                                // System.out.println("***** ENTRA AL eLSE ");
                                                                MPPCostCollector costnew = new MPPCostCollector(Env.getCtx(),0,get_TrxName());
                                                                costnew.setPP_Order_ID(getPP_Order_ID());
                                                                costnew.setC_DocTypeTarget_ID(getC_DocTypeTarget_ID());
                                                                costnew.setC_DocType_ID(getC_DocType_ID());
                                                                costnew.setS_Resource_ID(getS_Resource_ID());
                                                                costnew.setM_Warehouse_ID(getM_Warehouse_ID());
                                                                costnew.setM_Locator_ID(getM_Locator_ID());
                                                                costnew.setM_Product_ID(getM_Product_ID());
                                                                costnew.setM_AttributeSetInstance_ID(getM_AttributeSetInstance_ID());
                                                                costnew.setPP_Order_Workflow_ID(getPP_Order_Workflow_ID());
                                                                costnew.setAD_User_ID(getAD_User_ID());
                                                                costnew.setMovementDate(getMovementDate());
                                                                costnew.setDateAcct(getDateAcct());
                                                                costnew.setPP_Order_Node_ID(node);
                                                                costnew.setMovementQty(getMovementQty());
                                                                costnew.setDurationReal(duration);
                                                                //costnew.setDurationUnit(getDurationUnit());
                                                                costnew.setMovementType(getMovementType());
                                                                costnew.save();
                                                                //    costnew.completeIt();
                                                                
                                                            }
                                                            
                                                            rsar.close();
                                                            pstmtar.close();
                                                            
                            }
                            catch (SQLException exnode)
                            {
                            }
                          //completenew(getPP_Order_ID(),node);
                             //fjv e-evolution Operation Activity Report end
       
    	
        }
          
             protected boolean beforeSave(boolean newRecord) {
        
	
    	
                   //fjv e-evolution Operation Activity Report begin
//                                    MPPOrderNode onodeact =new MPPOrderNode(Env.getCtx(),getPP_Order_Node_ID(),null);
//                                        onodeact.setDocStatus("IP");
//                                        onodeact.save();
                                        if (newRecord)
                                            setDocStatus("IP");
                          
                             //fjv e-evolution Operation Activity Report end
       
    	return true;
        }
        
        protected boolean afterSave(boolean newRecord, boolean success) {
        
		if (!newRecord)
			return success;
    	
                   //fjv e-evolution Operation Activity Report begin
                                    MPPOrderNode onodeact =new MPPOrderNode(Env.getCtx(),getPP_Order_Node_ID(),null);
                                        onodeact.setDocStatus("IP");
                                        //onodeact.setAD_WF_Node_ID(getPP_Order_Workflow_ID());
                                        onodeact.save();
                                        
                                       // setDocStatus("IP");
                          
                             //fjv e-evolution Operation Activity Report end
       
    	return true;
    } //aftersave
           protected void closenew(int order, int node)
          {
                     try
                                {
                                         String sqlcom="SELECT PP_Cost_Collector_ID FROM PP_Cost_Collector WHERE IsActive='Y' AND  PP_Order_ID="+order;
                                                             PreparedStatement pstmtcom = DB.prepareStatement(sqlcom,null);
                                                            // pstmt.setInt(1, AD_Client_ID);
                                                           //  pstmtar.setInt(1, getPP_Order_ID());
                                                            //pstmtar.setInt(2, rs1.getInt(2));
                                                              System.out.println("***** SQLar " +sqlcom + " variables " +order +" nodo "+node);
                                                            ResultSet rscom = pstmtcom.executeQuery();
                                                            while(rscom.next())
                                                            {
                                                                MDocType doc = new MDocType(Env.getCtx(),getC_DocType_ID(),get_TrxName());
                                                                String doct ="";
                                                                doct=doc.getDocBaseType();
                                                                if(doct.equals("MOA"))
                                                                {
                                                                    MPPCostCollector costcoll = new MPPCostCollector(Env.getCtx(),rscom.getInt(1),get_TrxName());
                                                                    costcoll.setDocStatus("CL");
                                                                    costcoll.setDocAction(DOCACTION_None);
                                                                    costcoll.save();
                                                                }
                                                            }
                                }
                     catch (SQLException excom)
                     {
                     }
            }
           
            protected void completenew(int order, int node)
          {
                     try
                                {
                                         String sqlcom="SELECT PP_Cost_Collector_ID,DocStatus FROM PP_Cost_Collector WHERE IsActive='Y' AND  PP_Order_ID="+order;
                                                             PreparedStatement pstmtcom = DB.prepareStatement(sqlcom,null);
                                                            // pstmt.setInt(1, AD_Client_ID);
                                                           //  pstmtar.setInt(1, getPP_Order_ID());
                                                            //pstmtar.setInt(2, rs1.getInt(2));
                                                              System.out.println("***** SQLar " +sqlcom + " variables " +order +" nodo "+node);
                                                            ResultSet rscom = pstmtcom.executeQuery();
                                                            while(rscom.next())
                                                            {
                                                                        MDocType doc = new MDocType(Env.getCtx(),getC_DocType_ID(),get_TrxName());
                                                                String doct ="";
                                                                doct=doc.getDocBaseType();
                                                                if(doct.equals("MOA"))
                                                                   {
                                                                        if(!rscom.getString(2).equals("C0") && !rscom.getString(2).equals("CL"))
                                                                        {
                                                                            MPPCostCollector costcoll = new MPPCostCollector(Env.getCtx(),rscom.getInt(1),get_TrxName());
                                                                            costcoll.completeIt();
                                                                        }
                                                                    }
                                                            }
                                }
                     catch (SQLException excom)
                     {
                     }
            }
            
            /**
        	 * 	Check Material Policy.
        	 * 	(NOT USED)
        	 * 	Sets line ASI
        	 */
        	private void checkMaterialPolicy(MPPOrderBOMLine line , BigDecimal qty)
        	{
        		int no = MPPOrderBOMLineMA.deleteOrderBOMLineMA(line.getPP_Order_BOMLine_ID(), get_TrxName());
        		if (no > 0)
        			log.config("Delete old #" + no);
        		
        		
        			//	Check Line
        			boolean needSave = false;
        			BigDecimal qtyASI = Env.ZERO ;
        			//	Attribute Set Instance
        			if (line.getM_AttributeSetInstance_ID() == 0)
        			{
        				MProduct product = MProduct.get(getCtx(), line.getM_Product_ID());
        				if (qty.signum() > 0)	//	Incoming Trx
        				{
        					MAttributeSetInstance asi = new MAttributeSetInstance(getCtx(), 0, get_TrxName());
        					asi.setM_AttributeSet_ID(product.getM_AttributeSet_ID());
        					if (!asi.save())
        					{
        						throw new IllegalStateException("Error try create ASI Reservation");
        					}	
        					if (asi.save())
        					{
        						line.setM_AttributeSetInstance_ID(asi.getM_AttributeSetInstance_ID());
        						needSave = true;
        					}
        				}
        				else	//	Outgoing Trx
        				{
        					String MMPolicy = product.getMMPolicy();
        					MStorage[] storages = MStorage.getAllWithASI(getCtx(), 
        						line.getM_Product_ID(),	line.getM_Locator_ID(), 
        						MClient.MMPOLICY_FiFo.equals(MMPolicy), get_TrxName());
        					BigDecimal qtyToDeliver = qty.negate();
        					
        					
        					for (MStorage storage: storages)
        					{
        						//consume ASI Zero
        						if (storage.getM_AttributeSetInstance_ID() == 0)
        						{
        							qtyASI = qtyASI.add(storage.getQtyOnHand());
        							qtyToDeliver = qtyToDeliver.subtract(storage.getQtyOnHand());
        							continue;
        						}
        						
        						if (storage.getQtyOnHand().compareTo(qtyToDeliver) >= 0)
        						{
        							MPPOrderBOMLineMA ma = new MPPOrderBOMLineMA (line, 
        									storage.getM_AttributeSetInstance_ID(),
        									qtyToDeliver);
        								if (!ma.save())
        								{
        									throw new IllegalStateException("Error try create ASI Reservation");
        								}		
        								qtyToDeliver = Env.ZERO;
        								log.fine( ma + ", QtyToDeliver=" + qtyToDeliver);		
        								//return;
        						}
        						else
        						{	
        							MPPOrderBOMLineMA ma = new MPPOrderBOMLineMA (line, 
        										storage.getM_AttributeSetInstance_ID(),
        										storage.getQtyOnHand());
        									if (!ma.save())
        									{
        										throw new IllegalStateException("Error try create ASI Reservation");
        									}	
        								qtyToDeliver = qtyToDeliver.subtract(storage.getQtyOnHand());
        								log.fine( ma + ", QtyToDeliver=" + qtyToDeliver);		
        						}
        					}
        									
        					//	No AttributeSetInstance found for remainder
        					if (qtyToDeliver.signum() != 0 || qtyASI.signum() != 0)
        					{
        						MPPOrderBOMLineMA ma = new MPPOrderBOMLineMA (line, 0 , qtyToDeliver.add(qtyASI));
        						
        						if (!ma.save())
        							;
        						log.fine("##: " + ma);
        					}
        				}	//	outgoing Trx
        			
        			if (needSave && !line.save())
        				log.severe("NOT saved " + line);
        		}	//	for all lines

        	}	//	checkMaterialPolicy
        
}	//	MMovement

