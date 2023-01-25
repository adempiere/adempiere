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
import java.util.concurrent.atomic.AtomicReference;

import org.adempiere.core.domains.models.X_M_PackageLine;
import org.compiere.util.Env;
/**
 *	Package Line Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MPackageLine.java,v 1.3 2006/07/30 00:51:02 jjanke Exp $
 */
public class MPackageLine extends X_M_PackageLine
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6018805803189654348L;

	/**	Parent	*/
	private MPackage parent = null;
	
	/**
	 * 	Get Parent
	 *	@return parent
	 */
	public MPackage getParent() {
		if (parent == null) {
			parent = new MPackage(getCtx(), getM_Package_ID(), get_TrxName());
		}
		return parent;
	}	//	getParent
	
	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param M_PackageLine_ID id
	 *	@param trxName transaction
	 */
	public MPackageLine (Properties ctx, int M_PackageLine_ID, String trxName)
	{
		super (ctx, M_PackageLine_ID, trxName);
		if (M_PackageLine_ID == 0)
		{
		//	setM_Package_ID (0);
		//	setM_InOutLine_ID (0);
			setQty (Env.ZERO);
		}
	}	//	MPackageLine

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MPackageLine (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MPackageLine
	
	/**
	 * 	Parent Constructor
	 *	@param parent header
	 */
	public MPackageLine (MPackage parent)
	{
		this (parent.getCtx(), 0, parent.get_TrxName());
		setClientOrg(parent);
		setM_Package_ID(parent.getM_Package_ID());
	}	//	MPackageLine
	
	/**
	 * 	Set Shipment Line
	 *	@param shipmentLine line
	 */
	public void setInOutLine(MInOutLine shipmentLine) {
		setM_InOutLine_ID (shipmentLine.getM_InOutLine_ID());
		setQty (shipmentLine.getMovementQty());
	}	//	setInOutLine
	
	/**
	 * 	Set Shipment Line
	 *	@param movementLine line
	 */
	public void setMovementLine(MMovementLine movementLine) {
		setM_MovementLine_ID(movementLine.getM_MovementLine_ID());
		setQty(movementLine.getMovementQty());
	}	//	setInOutLine
	
	/**
	 * 	After Save
	 *	@param newRecord new
	 *	@param success success
	 *	@return saved
	 */
	protected boolean afterSave (boolean newRecord, boolean success) {
		if (!success) {
			return success;
		}
		if(newRecord
				|| is_ValueChanged(COLUMNNAME_Width)
				|| is_ValueChanged(COLUMNNAME_Height)
				|| is_ValueChanged(COLUMNNAME_Depth)
				|| is_ValueChanged(COLUMNNAME_Weight)
				|| is_ValueChanged(COLUMNNAME_Volume)) {
			return updateHeader();
		}
		return success;
	}	//	afterSave
	
	/**
	 * 	After Delete
	 *	@param success success
	 *	@return deleted
	 */
	protected boolean afterDelete (boolean success) {
		if (!success) {
			return success;
		}
		return updateHeader();
	}	//	afterDelete

	/**
	 *	Update Tax & Header
	 *	@return true if header updated
	 */
	private boolean updateHeader() {
		//	Recalculate Tax for this Tax
		if (getParent().isProcessed()) {
			return false;
		}
		//	Update header values
		AtomicReference<BigDecimal> weight = new AtomicReference<BigDecimal>(Env.ZERO);
		AtomicReference<BigDecimal> volume = new AtomicReference<BigDecimal>(Env.ZERO);
		AtomicReference<BigDecimal> width = new AtomicReference<BigDecimal>(Env.ZERO);
		AtomicReference<BigDecimal> heigh = new AtomicReference<BigDecimal>(Env.ZERO);
		AtomicReference<BigDecimal> depth = new AtomicReference<BigDecimal>(Env.ZERO);
		//	
		getParent().getLines(true).forEach(packageLine -> {
			weight.updateAndGet(value -> value.add(packageLine.getWeight()));
			volume.updateAndGet(value -> value.add(packageLine.getVolume()));
			//	Package
			width.updateAndGet(value -> value.max(packageLine.getWidth()));
			heigh.updateAndGet(value -> value.max(packageLine.getHeight()));
			depth.updateAndGet(value -> value.max(packageLine.getDepth()));
		});
		parent.setWeight(weight.get());
		parent.setVolume(volume.get());
		parent.setWidth(width.get());
		parent.setHeight(heigh.get());
		parent.setDepth(depth.get());
		parent.saveEx();
		//	Update Head without
//		int updated = DB.executeUpdateEx("UPDATE " + I_M_Package.Table_Name 
//				+ " SET " 
//				+ I_M_Package.COLUMNNAME_Weight + " = ?, "
//				+ I_M_Package.COLUMNNAME_Volume + " = ?, "
//				+ I_M_Package.COLUMNNAME_Width + " = ?, "
//				+ I_M_Package.COLUMNNAME_Height + " = ?, "
//				+ I_M_Package.COLUMNNAME_Depth + " = ? ",
//				new Object[]{weight.get(), volume.get(), width.get(), heigh.get(), depth.get()}, get_TrxName());
		parent = null;
		log.fine("Header updated");
		return true;
	}	//	updateHeaderTax
	
}	//	MPackageLine
