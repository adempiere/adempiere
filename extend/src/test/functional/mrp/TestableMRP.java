/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2008 SC ARHIPAC SERVICE SRL. All Rights Reserved.            *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/
package test.functional.mrp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MProduct;
import org.compiere.model.PO;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.eevolution.model.I_PP_MRP;
import org.eevolution.model.I_PP_Product_Planning;
import org.eevolution.model.X_PP_MRP;
import org.eevolution.process.MRP;

/**
 * Extends {@link MRP} engine process and simulates initial context
 * (product data planning, existing MRP records).
 *    
 * @author Teo Sarca, www.arhipac.ro
 */
class TestableMRP extends org.eevolution.process.MRP
{
	public String name = "junit-test";
	public String description = "";
	public String trxName = null;
	public String productValue = "junit-test";
	public I_PP_Product_Planning planning;
	public Timestamp today;
	public BigDecimal qtyOnHand;
	//
	public List<I_PP_MRP> initialMRP = new ArrayList<I_PP_MRP>();
	public List<I_PP_MRP> expectedMRP = new ArrayList<I_PP_MRP>();
	public List<I_PP_MRP> actualMRP = new ArrayList<I_PP_MRP>();
	//
	public List<MRPNotice> expectedNotices = new ArrayList<MRPNotice>();
	public List<MRPNotice> actualNotices = new ArrayList<MRPNotice>();
	
	public TestableMRP()
	{
	}
	@Override
	public Properties getCtx()
	{
		return Env.getCtx();
	}
	@Override
	protected String get_TrxName()
	{
		return this.trxName;
	}
	@Override
	protected int getAD_Client_ID()
	{
		return this.planning.getAD_Client_ID();
	}
	@Override
	public int getAD_Org_ID()
	{
		return this.planning.getAD_Org_ID();
	}
	@Override
	public int getM_Warehouse_ID()
	{
		return this.planning.getM_Warehouse_ID();
	}
	@Override
	public int getPlant_ID()
	{
		return this.planning.getS_Resource_ID();
	}
	@Override
	public boolean isRequiredDRP()
	{
		return this.planning.isRequiredDRP();
	}
	@Override
	protected I_PP_Product_Planning getProductPlanning(int AD_Client_ID, int AD_Org_ID,
														int S_Resource_ID, int M_Warehouse_ID, MProduct product)
	{
		return this.planning;
	}
	@Override
	protected BigDecimal getQtyOnHand(I_PP_Product_Planning pp)
	{
		return this.qtyOnHand;
	}
	@Override
	protected Timestamp getToday()
	{
		return this.today;
	}
	@Override
	protected void createMRPNote(String code, int AD_Org_ID, int PP_MRP_ID, MProduct product, String documentNo, BigDecimal qty, String comment)
	{
		MRPNotice note = new MRPNotice(code);
		note.AD_Org_ID = AD_Org_ID;
		note.PP_MRP_ID = PP_MRP_ID;
		note.product = product;
		note.documentNo = documentNo;
		note.qty = qty;
		note.comment = comment;
		this.actualNotices.add(note);
	}
	@Override
	protected void createDDOrder(int AD_Org_ID, int PP_MRP_ID, MProduct product, BigDecimal QtyPlanned, Timestamp DemandDateStartSchedule)
	{
		createMRPSupply(AD_Org_ID, PP_MRP_ID, product, QtyPlanned, DemandDateStartSchedule);
	}
	@Override
	protected void createPPOrder(int AD_Org_ID, int PP_MRP_ID, MProduct product, BigDecimal QtyPlanned, Timestamp DemandDateStartSchedule)
	{
		createMRPSupply(AD_Org_ID, PP_MRP_ID, product, QtyPlanned, DemandDateStartSchedule);
	}
	@Override
	protected void createRequisition(int AD_Org_ID, int PP_MRP_ID, MProduct product, BigDecimal QtyPlanned, Timestamp DemandDateStartSchedule)
	{
		createMRPSupply(AD_Org_ID, PP_MRP_ID, product, QtyPlanned, DemandDateStartSchedule);
	}
	private void createMRPSupply(int AD_Org_ID, int PP_MRP_ID, MProduct product, BigDecimal QtyPlanned, Timestamp DemandDateStartSchedule)
	{
		I_PP_MRP mrp = MRPTest.createMRP(this.planning, X_PP_MRP.TYPEMRP_Supply, X_PP_MRP.DOCSTATUS_Drafted,
							QtyPlanned, DemandDateStartSchedule);
		((PO)mrp).saveEx(get_TrxName());
		this.actualMRP.add(mrp);
	}
	@Override
	public String doIt() throws Exception
	{
		this.p_M_Product_ID = this.planning.getM_Product_ID();
		// Create MRP lines:
		for (I_PP_MRP mrp : this.initialMRP)
			((PO)mrp).saveEx(get_TrxName());
		//
		return super.doIt();
	}
	@Override
	protected void deleteMRP(int AD_Client_ID, int AD_Org_ID, int S_Resource_ID, int M_Warehouse_ID)
	{
		// Delete all MRP records for our testing product
		String sql = "DELETE FROM PP_MRP"
				+" WHERE AD_Client_ID=? AND AD_Org_ID=?"
				+" AND M_Warehouse_ID=? AND S_Resource_ID=?"
				+" AND M_Product_ID=?";
		int no = DB.executeUpdateEx(sql,
				new Object[]{AD_Client_ID, AD_Org_ID,
							M_Warehouse_ID, S_Resource_ID, this.p_M_Product_ID},
				get_TrxName());
		log.info("[DEBUG] clean up MRP #"+no);
		//
		super.deleteMRP(AD_Client_ID, AD_Org_ID, S_Resource_ID, M_Warehouse_ID);
	}

	public void dumpStatus()
	{
		log.info("------------ MRP TEST --------------");
		log.info("          Name : "+this.name);
		log.info("   Description : "+this.description);
		log.info("       Product : "+this.productValue);
		log.info("         Today : "+this.today);
		log.info("     QtyOnHand : "+this.qtyOnHand);
		((PO)this.planning).dump();
		//
		log.info("------------ Initial MRP --------------");
		for (I_PP_MRP mrp : this.initialMRP)
			log.info("    "+((PO)mrp).toString());
		log.info("------------ Expected MRP --------------");
		for (I_PP_MRP mrp : this.expectedMRP)
			log.info("    "+((PO)mrp).toString());
		log.info("------------ Actual MRP --------------");
		for (I_PP_MRP mrp : this.actualMRP)
			log.info(("    "+(PO)mrp).toString());
		log.info("------------ Expected NOTICES --------------");
		for (MRPNotice notice : this.expectedNotices)
			log.info("    "+notice.toString());
		log.info("------------ Actual NOTICES --------------");
		for (MRPNotice notice : this.actualNotices)
			log.info("    "+notice.toString());
	}
}
