/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
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

import java.math.*;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import org.compiere.util.*;
import org.eevolution.model.*;

/**
 *	Inventory Move Line Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MMovementLine.java,v 1.3 2006/07/30 00:51:03 jjanke Exp $
 */
public class MMovementLine extends X_M_MovementLine
{
	/**
	 * 	Standard Cosntructor
	 *	@param ctx context
	 *	@param M_MovementLine_ID id
	 *	@param trxName transaction
	 */
	public MMovementLine (Properties ctx, int M_MovementLine_ID, String trxName)
	{
		super (ctx, M_MovementLine_ID, trxName);
		if (M_MovementLine_ID == 0)
		{
		//	setM_LocatorTo_ID (0);	// @M_LocatorTo_ID@
		//	setM_Locator_ID (0);	// @M_Locator_ID@
		//	setM_MovementLine_ID (0);			
		//	setLine (0);	
		//	setM_Product_ID (0);
			setM_AttributeSetInstance_ID(0);	//	ID
			setMovementQty (Env.ZERO);	// 1
			setTargetQty (Env.ZERO);	// 0
			setScrappedQty(Env.ZERO);
			setConfirmedQty(Env.ZERO);
			setProcessed (false);
		}	
	}	//	MMovementLine
    /**     Static Logger   */
    private static CLogger  s_log   = CLogger.getCLogger (MMovementLine.class);

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MMovementLine (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MMovementLine

	/**
	 * 	Parent constructor
	 *	@param parent parent
	 */
	public MMovementLine (MMovement parent)
	{
		this (parent.getCtx(), 0, parent.get_TrxName());
		setClientOrg(parent);
		setM_Movement_ID(parent.getM_Movement_ID());
	}	//	MMovementLine
	
	/**
	 * 	Get AttributeSetInstance To
	 *	@return ASI
	 */
	public int getM_AttributeSetInstanceTo_ID ()
	{
		int M_AttributeSetInstanceTo_ID = super.getM_AttributeSetInstanceTo_ID();
		if (M_AttributeSetInstanceTo_ID == 0)
			M_AttributeSetInstanceTo_ID = super.getM_AttributeSetInstance_ID();
		return M_AttributeSetInstanceTo_ID;
	}	//	getM_AttributeSetInstanceTo_ID
	
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
	 * 	Get Product
	 *	@return product or null if not defined
	 */
	public MProduct getProduct()
	{
		if (getM_Product_ID() != 0)
			return MProduct.get(getCtx(), getM_Product_ID());
		return null;
	}	//	getProduct
	
	/**
	 * 	Set Movement Qty - enforce UOM 
	 *	@param MovementQty qty
	 */
	public void setMovementQty (BigDecimal MovementQty)
	{
		if (MovementQty != null)
		{
			MProduct product = getProduct();
			if (product != null)
			{
				int precision = product.getUOMPrecision(); 
				MovementQty = MovementQty.setScale(precision, BigDecimal.ROUND_HALF_UP);
			}
		}
		super.setMovementQty(MovementQty);
	}	//	setMovementQty
	
	/** Parent							*/
	private MMovement m_parent = null;
	
	/**
	 * get Parent
	 * @return Parent Movement
	 */
	public MMovement getParent() 
	{
		if (m_parent == null)
			m_parent = new MMovement (getCtx(), getM_Movement_ID(), get_TrxName());
		return m_parent;
	}	//	getParent

	
	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		//	Set Line No
		if (getLine() == 0)
		{
			String sql = "SELECT COALESCE(MAX(Line),0)+10 AS DefaultValue FROM M_MovementLine WHERE M_Movement_ID=?";
			int ii = DB.getSQLValue (get_TrxName(), sql, getM_Movement_ID());
			setLine (ii);
		}
		
		if (getM_Locator_ID() == getM_LocatorTo_ID())
		{
			log.saveError("Error", Msg.parseTranslation(getCtx(), "@M_Locator_ID@ == @M_LocatorTo_ID@"));
			return false;
		}

		if (getMovementQty().signum() == 0)
		{
			log.saveError("FillMandatory", Msg.getElement(getCtx(), "MovementQty"));
			return false;
		}

		//	Qty Precision
		if (newRecord || is_ValueChanged(COLUMNNAME_MovementQty))
			setMovementQty(getMovementQty());
		
		//      Mandatory Instance
		MProduct product = getProduct();
		if (getM_AttributeSetInstance_ID() == 0) {
			if (product != null && product.isASIMandatory(false)) {
				log.saveError("FillMandatory", Msg.getElement(getCtx(), COLUMNNAME_M_AttributeSetInstance_ID));
				return false;
			}
		}
		if (getM_AttributeSetInstanceTo_ID() == 0)
		{
			if (getM_AttributeSetInstance_ID() != 0)        //      set to from
				setM_AttributeSetInstanceTo_ID(getM_AttributeSetInstance_ID());
			else
			{
				if (product != null && product.isASIMandatory(true))
				{
					log.saveError("FillMandatory", Msg.getElement(getCtx(), COLUMNNAME_M_AttributeSetInstanceTo_ID));
					return false;
				}
			}
		}       //      ASI

		return true;
	}	//	beforeSave
	/** 
    *      Set Distribution Order Line. 
    *      Does not set Quantity! 
    *      @param oLine order line 
    *      @param M_Locator_ID locator 
    *      @param Qty used only to find suitable locator 
    */ 
    public void setOrderLine (MDDOrderLine oLine, int M_Locator_ID, BigDecimal Qty) 
    { 
            setDD_OrderLine_ID(oLine.getDD_OrderLine_ID()); 
            setLine(oLine.getLine()); 
            //setC_UOM_ID(oLine.getC_UOM_ID()); 
            MProduct product = oLine.getProduct(); 
            if (product == null) 
            { 
                    set_ValueNoCheck("M_Product_ID", null); 
                    set_ValueNoCheck("M_AttributeSetInstance_ID", null); 
                    set_ValueNoCheck("M_Locator_ID", null); 
            } 
            else 
            { 
                    setM_Product_ID(oLine.getM_Product_ID()); 
                    setM_AttributeSetInstance_ID(oLine.getM_AttributeSetInstance_ID()); 
                    // 
                    if (product.isItem()) 
                    { 
                                    setM_Locator_ID(M_Locator_ID); 
                    } 
                    else 
                            set_ValueNoCheck("M_Locator_ID", null); 
            } 
            //setC_Charge_ID(oLine.getC_Charge_ID()); 
            setDescription(oLine.getDescription()); 
            //setIsDescription(oLine.isDescription()); 
            // 
            //setC_Project_ID(oLine.getC_Project_ID()); 
            //setC_ProjectPhase_ID(oLine.getC_ProjectPhase_ID()); 
            //setC_ProjectTask_ID(oLine.getC_ProjectTask_ID()); 
            //setC_Activity_ID(oLine.getC_Activity_ID()); 
            //setC_Campaign_ID(oLine.getC_Campaign_ID()); 
            //setAD_OrgTrx_ID(oLine.getAD_OrgTrx_ID()); 
            //setUser1_ID(oLine.getUser1_ID()); 
            //setUser2_ID(oLine.getUser2_ID()); 
    }       //      setOrderLine 

    /** 
     *      Set M_Locator_ID 
     *      @param M_Locator_ID id 
     */ 
    public void setM_Locator_ID (int M_Locator_ID) 
    { 
            if (M_Locator_ID < 0) 
                    throw new IllegalArgumentException ("M_Locator_ID is mandatory."); 
            //      set to 0 explicitly to reset 
            set_Value ("M_Locator_ID", new Integer(M_Locator_ID)); 
    }       //      setM_Locator_ID 

    /** 
     *      Set M_Locator_ID 
     *      @param M_Locator_ID id 
     */ 
    public void setM_LocatorTo_ID (int M_Locator_ID) 
    { 
            if (M_Locator_ID < 0) 
                    throw new IllegalArgumentException ("M_LocatorTo_ID is mandatory."); 
            //      set to 0 explicitly to reset 
            set_Value ("M_Locator_ID", new Integer(M_Locator_ID)); 
    }       //      setM_Locator_ID 

    /** 
     *      Get Movement lines Of Distribution Order Line 
     *      @param ctx context 
     *      @param DD_OrderLine_ID line 
     *      @param where optional addition where clause 
     *  @param trxName transaction 
     *      @return array of receipt lines 
     */ 
    public static MMovementLine[] getOfOrderLine (Properties ctx, 
            int DD_OrderLine_ID, String where, String trxName) 
    { 
            ArrayList<MMovementLine> list = new ArrayList<MMovementLine>(); 
            String sql = "SELECT * FROM M_MovementLine WHERE DD_OrderLine_ID=?"; 
            if (where != null && where.length() > 0) 
                    sql += " AND " + where; 
            PreparedStatement pstmt = null; 
            try 
            { 
                    pstmt = DB.prepareStatement (sql, trxName); 
                    pstmt.setInt (1, DD_OrderLine_ID); 
                    ResultSet rs = pstmt.executeQuery (); 
                    while (rs.next ()) 
                            list.add(new MMovementLine(ctx, rs, trxName)); 
                    rs.close (); 
                    pstmt.close (); 
                    pstmt = null; 
            } 
            catch (Exception e) 
            { 
                    s_log.log(Level.SEVERE, sql, e); 
            } 
            try 
            { 
                    if (pstmt != null) 
                            pstmt.close (); 
                    pstmt = null; 
            } 
            catch (Exception e) 
            { 
                    pstmt = null; 
            } 
            MMovementLine[] retValue = new MMovementLine[list.size ()]; 
            list.toArray (retValue); 
            return retValue; 
    }       //      getOfOrderLine 
	
}	//	MMovementLine
