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

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;


/**
 *	RMA Line Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MRMALine.java,v 1.3 2006/07/30 00:51:03 jjanke Exp $
 */
public class MRMALine extends X_M_RMALine
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4939372209739721247L;

	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param M_RMALine_ID id
	 *	@param trxName transaction
	 */
	public MRMALine (Properties ctx, int M_RMALine_ID, String trxName)
	{
		super (ctx, M_RMALine_ID, trxName);
		if (M_RMALine_ID == 0)
		{
			setQty(Env.ONE);
			this.setQtyDelivered(Env.ONE);
		}
        
        init();
	}	//	MRMALine

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MRMALine (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
        init();
	}	//	MRMALine
	
	/**	Shipment Line			*/
	private MInOutLine m_ioLine = null;
	/** Parent                  */
	private MRMA       m_parent = null;   
    
    private int precision = 0;
    private int taxId = 0;
    private BigDecimal unitAmount = Env.ZERO;
    private BigDecimal originalQty = Env.ZERO;
    
    /**
     * Initialise parameters that are required
     */
    private void init()
    {
        if (getC_Charge_ID() != 0)
        {
            // Retrieve tax Exempt
            String sql = "SELECT C_Tax_ID FROM C_Tax WHERE AD_Client_ID=? AND IsActive='Y' "
                + "AND IsTaxExempt='Y' AND ValidFrom < SYSDATE ORDER BY IsDefault DESC";
            
            // Set tax for charge as exempt        
            taxId = DB.getSQLValueEx(null, sql, Env.getAD_Client_ID(getCtx()));
            m_ioLine = null;
        }
        else
        {
            getShipLine();
        }
        
        if (m_ioLine != null)
        {
            // Get pricing details (Based on invoice if found, on order otherwise)
            //   --> m_ioLine.isInvoiced just work for sales orders - so it doesn't work for purchases
            if (getInvoiceLineId() != 0)
            {
                MInvoiceLine invoiceLine = new MInvoiceLine(getCtx(), getInvoiceLineId(), get_TrxName());
                precision = invoiceLine.getPrecision();
                unitAmount = invoiceLine.getPriceEntered();
                originalQty = invoiceLine.getQtyInvoiced();
                taxId = invoiceLine.getC_Tax_ID();
            }
            else if (m_ioLine.getC_OrderLine_ID() != 0)
            {
                MOrderLine orderLine = new MOrderLine (getCtx(), m_ioLine.getC_OrderLine_ID(), get_TrxName());
                precision = orderLine.getPrecision();
                unitAmount = orderLine.getPriceEntered();
                originalQty = orderLine.getQtyDelivered();
                taxId = orderLine.getC_Tax_ID();
            }
            else
            {
                throw new IllegalStateException("No Invoice/Order line found the Shipment/Receipt line associated");
            }
        }
        else if (getC_Charge_ID() != 0)
        {
            MCharge charge = MCharge.get(this.getCtx(), getC_Charge_ID());
            unitAmount = charge.getChargeAmt();
        }
    }
	
    /**
     *  Get Parent
     *  @return parent
     */
    public MRMA getParent()
    {
        if (m_parent == null)
            m_parent = new MRMA(getCtx(), getM_RMA_ID(), get_TrxName());
        return m_parent;
    }   //  getParent
	
	/**
	 * 	Set M_InOutLine_ID
	 *	@param M_InOutLine_ID
	 */
	public void setM_InOutLine_ID (int M_InOutLine_ID)
	{
		super.setM_InOutLine_ID (M_InOutLine_ID);
		m_ioLine = null;
	}	//	setM_InOutLine_ID
	
	/**
	 * 	Get Ship Line
	 *	@return ship line
	 */
	public MInOutLine getShipLine()
	{
		if ((m_ioLine == null || is_ValueChanged("M_InOutLine_ID")) && getM_InOutLine_ID() != 0)
			m_ioLine = new MInOutLine (getCtx(), getM_InOutLine_ID(), get_TrxName());
		return m_ioLine;
	}	//	getShipLine
	
    /**
     * Retrieves the invoiceLine Id associated with the Shipment/Receipt Line
     * @return Invoice Line ID
     */
    private int getInvoiceLineId()
    {
    	int invoiceLine_ID = new Query(getCtx(), MInvoiceLine.Table_Name, "M_InOutLine_ID=?", get_TrxName())
    	.setParameters(new Object[]{getM_InOutLine_ID()})
    	.firstIdOnly();
    	return invoiceLine_ID <= 0 ? 0 : invoiceLine_ID;
    }
    
    /**
     * Calculates the unit amount for the product/charge
     * @return Unit Amount
     */
    public BigDecimal getUnitAmt()
    {
        return unitAmount;
    }
    
    /**
     *  Get Total Amt for the line including tax
     *  @return amt
     */
    public BigDecimal getTotalAmt()
    {
        BigDecimal totalAmt = Env.ZERO;
        BigDecimal taxAmt = Env.ZERO;
        
        if (Env.ZERO.compareTo(getQty()) != 0 && Env.ZERO.compareTo(getAmt()) != 0)
        {
            totalAmt = getQty().multiply(getAmt());
            if (!getParent().isTaxIncluded())
            {
                MTax tax = MTax.get (getCtx(), taxId);
                taxAmt = tax.calculateTax(getQty().multiply(unitAmount), 
                        getParent().isTaxIncluded(), precision);
            }
        }
        
        totalAmt = totalAmt.add(taxAmt);
        return totalAmt;
    }   //  getAmt
    
    /**
     * Get whether the Ship line has been invoiced
     * @return true if invoiced
     */
    public boolean isShipLineInvoiced()
    {
        return (getInvoiceLineId() != 0);
    }
    
    @Override
    protected boolean beforeSave(boolean newRecord)
    {
		if (newRecord && getParent().isComplete()) {
			log.saveError("ParentComplete", Msg.translate(getCtx(), "M_RMALine"));
			return false;
		}
        if (this.getM_InOutLine_ID() == 0 && this.getC_Charge_ID() == 0)
        {
            log.saveError("FillMandatory", "Shipment/Receipt Line or charge should be entered");
            return false;
        }
        
        if (this.getM_InOutLine_ID() != 0 && this.getC_Charge_ID() != 0)
        {
            log.saveError("Error", "Either shipment/receipt line or charge should be selected");
            return false;
        }
        
        init();
        if (m_ioLine != null)
        {
            if (m_ioLine.getMovementQty().compareTo(getQty()) < 0)
            {
                log.saveError("Error", "Amount to be returned is greater than the amount shipped");
                return false;
            }
            
            if (newRecord)
            {
                String whereClause = "M_RMA_ID=" + this.getM_RMA_ID() + " and M_InOutLine_ID=" + this.getM_InOutLine_ID();
                
                int lineIds[] = MRMALine.getAllIDs(MRMALine.Table_Name, whereClause, this.get_TrxName());
                
                if (lineIds.length > 0)
                {
                    log.saveError("Error", "Shipment/Receipt line is already defined in another line");
                    return false;
                }
            }
        }

        // Set default amount for charge and qty
        if (this.getC_Charge_ID() != 0 && this.getQty().doubleValue() <= 0)
        {
            if (getQty().signum() == 0)
                this.setQty(Env.ONE);
            if (getAmt().signum() == 0)
                this.setAmt(getUnitAmt());
        }
        
        // Set amount for products
        if (this.getM_InOutLine_ID() != 0)
        {
            this.setAmt(getUnitAmt());
            
            if (newRecord && getQty().signum() == 0)
            {
                this.setQty(originalQty);
            }
        }
        
        this.setLineNetAmt(getTotalAmt());
        
        return true;
    }
    
    @Override
    protected  boolean afterSave(boolean newRecord, boolean success)
    {
        if (!success)
        {
            return success;
        }
        
        MRMA rma = getParent();
        rma.updateAmount();
        
        if (!rma.save(get_TrxName()))
        {
            throw new IllegalStateException("Could not update RMA grand total");
        }
        
        return true;
    }
    
    @Override
    protected  boolean afterDelete(boolean success)
    {
        if (!success)
        {
            return success;
        }
        
        MRMA rma = getParent();
        rma.updateAmount();
        
        if (!rma.save(get_TrxName()))
        {
            throw new IllegalStateException("Could not update RMA grand total");
        }
        
        return true;
    }
    
    /**
     *  Add to Description
     *  @param description text
     */
    public void addDescription (String description)
    {
        String desc = getDescription();
        if (desc == null)
            setDescription(description);
        else
            setDescription(desc + " | " + description);
    }   //  addDescription
    
    /**
     * Get precision
     * Based on Invoice if the shipment was invoiced, on Order otherwise
     */
    public int getPrecision()
    {
        return precision;
    }
    
    /**
     * Get UOM
     * Based on Shipment line if present
     * Default to Each (100) for charge
     * @return UOM if based on shipment line and 100 for charge based
     */
    public int getC_UOM_ID()
    {
        if (m_ioLine == null) // Charge
        {
            return 100; // Each
        }
        
        return m_ioLine.getC_UOM_ID();
    }
    
    /**
     * Get Product
     * @return product if based on shipment line and 0 for charge based
     */
    public int getM_Product_ID()
    {
        if (getC_Charge_ID() != 0)
        {
            return 0;
        }
        if (m_ioLine == null)
        {
            return 0;
        }
        return m_ioLine.getM_Product_ID();
    }
    
    /**
     * Get Project
     * @return project if based on shipment line and 0 for charge based
     */
    public int getC_Project_ID()
    {
        if (m_ioLine == null)
        {
            return 0;
        }
        return m_ioLine.getC_Project_ID();
    }
    
    /**
     * Get Project Phase
     * @return project phase if based on shipment line and 0 for charge based
     */
    public int getC_ProjectPhase_ID()
    {
        if (m_ioLine == null)
        {
            return 0;
        }
        return m_ioLine.getC_ProjectPhase_ID();
    }
    
    /**
     * Get Project Task
     * @return project task if based on shipment line and 0 for charge based
     */
    public int getC_ProjectTask_ID()
    {
        if (m_ioLine == null)
        {
            return 0;
        }
        return m_ioLine.getC_ProjectTask_ID();
    }
    
    /**
     * Get Activity
     * @return project phase if based on shipment line and 0 for charge based
     */
    public int getC_Activity_ID()
    {
        if (m_ioLine == null)
        {
            return 0;
        }
        return m_ioLine.getC_Activity_ID();
    }
    
    /**
     * Get Campaign
     * @return campaign if based on shipment line and 0 for charge based
     */
    public int getC_Campaign_ID()
    {
        if (m_ioLine == null)
        {
            return 0;
        }
        return m_ioLine.getC_Campaign_ID();
    }
    
    /**
     * Get Org Trx
     * @return Org Trx if based on shipment line and 0 for charge based
     */
    public int getAD_OrgTrx_ID()
    {
        if (m_ioLine == null)
        {
            return 0;
        }
        return m_ioLine.getAD_OrgTrx_ID();
    }
    
    /**
     * Get User1
     * @return user1 if based on shipment line and 0 for charge based
     */
    public int getUser1_ID()
    {
        if (m_ioLine == null)
        {
            return 0;
        }
        return m_ioLine.getUser1_ID();
    }
    
    /**
     * Get User2
     * @return user2 if based on shipment line and 0 for charge based
     */
    public int getUser2_ID()
    {
        if (m_ioLine == null)
        {
            return 0;
        }
        return m_ioLine.getUser2_ID();
    }
    
    /**
     * Get Attribute Set Instance
     * @return ASI if based on shipment line and 0 for charge based
     */
    public int getM_AttributeSetInstance_ID()
    {
        if (m_ioLine == null)
        {
            return 0;
        }
        return m_ioLine.getM_AttributeSetInstance_ID();
    }
    
    /**
     * Get Locator
     * @return locator if based on shipment line and 0 for charge based
     */
    public int getM_Locator_ID()
    {
        if (m_ioLine == null)
        {
            return 0;
        }
        return m_ioLine.getM_Locator_ID();
    }
    
    /**
     * Get Tax
     * @return Tax based on Invoice/Order line and Tax exempt for charge based
     */ 
    public int getC_Tax_ID()
    {
        return taxId;
    }
    
}	//	MRMALine
