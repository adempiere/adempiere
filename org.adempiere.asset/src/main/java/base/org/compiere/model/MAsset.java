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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.compiere.FA.exceptions.AssetCheckDocumentException;
import org.compiere.util.CLogMgt;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.TimeUtil;

/**
 * Asset Model
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * 
 *  @author Jorg Janke
 *  @version $Id: MAsset.java,v 1.3 2006/07/30 00:51:03 jjanke Exp $
 *  @author red1 - FR: [ 2214883 ] Remove SQL code and Replace for Query
 */
@SuppressWarnings("serial")
public class MAsset extends X_A_Asset
	//implements MAssetType.Model //commented by @win
{
	/** ChangeType - Asset Group changed */
	public static final int CHANGETYPE_setAssetGroup = Table_ID * 100 + 1;
	/**
	 *
	 */
	private static final long serialVersionUID = -7537696364072606170L;

	/**	Product Info					*/
	private MProduct		m_product = null;


	/**
	 * 	Get Asset From Shipment
	 *	@param ctx context
	 *	@param M_InOutLine_ID shipment line
	 *	@param trxName transaction
	 *	@return asset or null
	 */
	public static MAsset getFromShipment (Properties ctx, int M_InOutLine_ID, String trxName)
	{
		final String whereClause = I_A_Asset.COLUMNNAME_M_InOutLine_ID+"=?";
		MAsset retValue = new Query(ctx,I_A_Asset.Table_Name,whereClause,trxName)
				.setParameters(M_InOutLine_ID)
				.first();
		return retValue;
	}	//	getFromShipment

	/**	Logger							*/
	private static CLogger s_log = CLogger.getCLogger (MAsset.class);


	/**************************************************************************
	 * 	Asset Constructor
	 * @param ctx context
	 * @param A_Asset_ID asset
	 * @param trxName
	 */
	public static MAsset get (Properties ctx, int A_Asset_ID, String trxName)
	{
		return (MAsset)MTable.get(ctx, MAsset.Table_Name).getPO(A_Asset_ID, trxName);
	}	//	get
	
	/**
	 * Get Assets from given M_Product_ID and M_ASI_ID.
	 * <p>Note: The A_Asset_Product table is not checked !!!
	 * @param ctx
	 * @param M_Product_ID (optional)
	 * @param M_ASI_ID
	 * @return array of MAsset
	 */
	public static Collection<MAsset> forASI(Properties ctx, int M_Product_ID, int M_ASI_ID)
	{
		ArrayList<Object> params = new ArrayList<Object>();
		String whereClause = COLUMNNAME_M_AttributeSetInstance_ID + "=?";
		params.add(M_ASI_ID);
		if (M_Product_ID > 0) {
			whereClause += " AND " + COLUMNNAME_M_Product_ID + "=?";
			params.add(M_Product_ID);
		}
		//
		return new Query(ctx, MAsset.Table_Name, whereClause, null)
					.setParameters(params)
					.list();
	}
	
	/** Create constructor */
	public MAsset (Properties ctx, int A_Asset_ID, String trxName)
	{
		super (ctx, A_Asset_ID,trxName);
		if (A_Asset_ID == 0)
		{
			setA_Asset_Status(A_ASSET_STATUS_New);
			//commented out by @win
			/*
			setA_Asset_Type("MFX");
			setA_Asset_Type_ID(1); // MFX
			*/
			//end comment by @win
		}
	}	//	MAsset

	/**
	 * Load Constructor
	 * @param ctx context
	 * @param rs result set record
	 */
	public MAsset (Properties ctx, ResultSet rs, String trxName)
	{
		super (ctx, rs, trxName);
	}	//	MAsset
	
	/**
	 * Construct from MMatchInv
	 * @param match match invoice
	 */
	protected MAsset (MMatchInv match)
	{
		this(match.getCtx(), 0, match.get_TrxName());
		
		MInvoiceLine invoiceLine = new MInvoiceLine(getCtx(), match.getC_InvoiceLine_ID(), get_TrxName());
		MInOutLine inoutLine = new MInOutLine(getCtx(), match.getM_InOutLine_ID(), get_TrxName());
		
		setIsOwned(true);
		setIsInPosession(true);
		setA_Asset_CreateDate(inoutLine.getM_InOut().getMovementDate());
		if (invoiceLine.getC_Project_ID() > 0)
			setC_Project_ID(invoiceLine.getC_Project_ID());
		if (invoiceLine.getC_Activity_ID() > 0)
			setC_Activity_ID(invoiceLine.getC_Activity_ID());
		
		// Asset Group:
		int A_Asset_Group_ID = invoiceLine.getA_Asset_Group_ID();
		MProduct product = MProduct.get(getCtx(), invoiceLine.getM_Product_ID());
		if (A_Asset_Group_ID <= 0) {
			A_Asset_Group_ID = product.getA_Asset_Group_ID();
		}
		setA_Asset_Group_ID(A_Asset_Group_ID);
		setHelp(Msg.getMsg(MClient.get(getCtx()).getAD_Language(), "CreatedFromInvoiceLine", 
				new Object[] {invoiceLine.getC_Invoice().getDocumentNo(), invoiceLine.getLine()}));
		
		String name = "";
		if (inoutLine.getM_Product_ID()>0)
		{
			name += product.getName() + "-";
			setM_Product_ID(inoutLine.getM_Product_ID());
			setM_AttributeSetInstance_ID(inoutLine.getM_AttributeSetInstance_ID());
		}
		name += invoiceLine.getC_Invoice().getDocumentNo(); //Goodwill - change naming style
		setName(name);
		log.fine("name=" + name);
		setDescription(invoiceLine.getDescription());
	}

	/**
	 * Construct from MIFixedAsset (import)
	 * @param match match invoice
	 */
	protected MAsset (MIFixedAsset importFixedAsset)
	{
		this(importFixedAsset.getCtx(), 0, importFixedAsset.get_TrxName());
		
		setAD_Org_ID(importFixedAsset.getAD_Org_ID()); //added by @win
		setIsOwned(true);
		setIsInPosession(true);
		
		String inventoryNo = importFixedAsset.getInventoryNo();
		if (inventoryNo != null) {
			inventoryNo = inventoryNo.trim();
			setInventoryNo(inventoryNo);
			setValue(inventoryNo);
		}
		setA_Asset_CreateDate(importFixedAsset.getAssetServiceDate());
		//setAssetServiceDate(ifa.getAssetServiceDate()); //commented by @win
		/* commented by @win
		setA_Asset_Class_ID(ifa.getA_Asset_Class_ID());
		*/ // commented by @win
		MProduct product = importFixedAsset.getProduct();
		if (product != null) {
			setM_Product_ID(product.getM_Product_ID());
			setA_Asset_Group_ID(importFixedAsset.getA_Asset_Group_ID());
			MAttributeSetInstance asi = MAttributeSetInstance.create(getCtx(), product, get_TrxName());
			setM_AttributeSetInstance_ID(asi.getM_AttributeSetInstance_ID());
		}
		
		setDateAcct(importFixedAsset.getDateAcct());
		setName(importFixedAsset.getName());
		setDescription(importFixedAsset.getDescription());

	}

	/**
	 * @author Edwin Ang
	 * @param project
	 */
	protected MAsset (MProject project)
	{
		this(project.getCtx(), 0, project.get_TrxName());
		setIsOwned(true);
		setIsInPosession(true);
		setA_Asset_CreateDate(new Timestamp(System.currentTimeMillis()));
		setHelp(Msg.getMsg(MClient.get(getCtx()).getAD_Language(), "CreatedFromProject", new Object[] { project.getName()}));
		setDateAcct(new Timestamp(System.currentTimeMillis()));
		setDescription(project.getDescription());
		setC_Project_ID(project.getC_Project_ID());
	}
	
	public MAsset(MInOut mInOut, MInOutLine sLine, int deliveryCount) {
		this(mInOut.getCtx(), 0, mInOut.get_TrxName());
		setIsOwned(false);
		setIsInPosession(false);
		setA_Asset_CreateDate(new Timestamp(System.currentTimeMillis()));
		setHelp(Msg.getMsg(MClient.get(getCtx()).getAD_Language(), "CreatedFromShipment: ", new Object[] { mInOut.getDocumentNo()}));
		setDateAcct(new Timestamp(System.currentTimeMillis()));
		setDescription(sLine.getDescription());
		
	}

	/**
	 * Create Asset from Inventory
	 * @param inventory	inventory
	 * @param invoiceLine 	inventory line
	 * @param quantity
	 * @param costs
	 * @return Asset Entity
	 */
	
	public MAsset (MInventory inventory, MInventoryLine invoiceLine, BigDecimal quantity, BigDecimal costs)
	{
		super(invoiceLine.getCtx(), 0, invoiceLine.get_TrxName());
		setClientOrg(invoiceLine);
		
		MProduct product = MProduct.get(getCtx(), invoiceLine.getM_Product_ID());
		// Defaults from group:
		MAssetGroup assetGroup = MAssetGroup.get(invoiceLine.getCtx(), invoiceLine.getM_Product().getM_Product_Category().getA_Asset_Group_ID());
		if (assetGroup == null)
			assetGroup = MAssetGroup.get(invoiceLine.getCtx(), product.getA_Asset_Group_ID());
		setAssetGroup(assetGroup);
		
		
		//setValue(prod)
		setName(product.getName());
		setHelp(invoiceLine.getDescription());
		//	Header
		setAssetServiceDate(inventory.getMovementDate());
		setIsOwned(true);
		setIsInPosession(true);
		
		//	Product
		setM_Product_ID(product.getM_Product_ID());
		//	Guarantee & Version
		//setGuaranteeDate(TimeUtil.addDays(shipment.getMovementDate(), product.getGuaranteeDays()));
		setVersionNo(product.getVersionNo());
		// ASI
		if (invoiceLine.getM_AttributeSetInstance_ID() != 0)
		{
			MAttributeSetInstance asi = new MAttributeSetInstance (getCtx(), invoiceLine.getM_AttributeSetInstance_ID(), get_TrxName());
			setASI(asi);
		}
		//setSerNo(invLine.getSerNo());
		setQty(quantity);

		// Costs:
		//setA_Asset_Cost(costs);  //commented by @win, set at asset addition
		// Activity
		Optional.ofNullable(invoiceLine).ifPresent(il -> {
			if (il.getC_Activity_ID() > 0)
				setC_Activity_ID(il.getC_Activity_ID());

			if (il.getC_Project_ID() > 0)
				setC_Project_ID(il.getC_Project_ID());
		});

		Optional.ofNullable(inventory).ifPresent(i -> {
			if (i.getC_Activity_ID() > 0)
				setC_Project_ID(i.getC_Activity_ID());

			if (i.getC_Project_ID() > 0)
				setC_Project_ID(i.getC_Project_ID());
		});

		
		//
		
		if (MAssetType.isFixedAsset(this)) {
			setA_Asset_Status(A_ASSET_STATUS_New);
		}
		else {
			setA_Asset_Status(A_ASSET_STATUS_Activated);
			setProcessed(true);
		}
		
		//added by @win
		setA_Asset_Status(A_ASSET_STATUS_New);
		//end added by @win
		
		
	}
	
	/**
	 * Set Asset Group; also it sets other default fields
	 * @param assetGroup
	 */
	public void setAssetGroup(MAssetGroup assetGroup) {
		setA_Asset_Group_ID(assetGroup.getA_Asset_Group_ID());
		
		/* commented out by @win
		setA_Asset_Type_ID(assetGroup.getA_Asset_Type_ID());
		setGZ_TipComponenta(assetGroup.getGZ_TipComponenta()); // TODO: move to GZ
		MAssetType assetType = MAssetType.get(getCtx(), assetGroup.getA_Asset_Type_ID());
		assetType.update(SetGetUtil.wrap(this), true);
		*/ //end commet by @win
	}
	
	public MAssetGroup getAssetGroup() {
		return MAssetGroup.get(getCtx(), getA_Asset_Group_ID());
	}
	
	/**
	 * Set ASI Info (M_AttributeSetInstance_ID, Lot, SerNo)
	 * @param asi
	 */
	public void setASI(MAttributeSetInstance asi) {
		setM_AttributeSetInstance_ID(asi.getM_AttributeSetInstance_ID());
		setLot(asi.getLot());
		setSerNo(asi.getSerNo());
	}



	/**************************************************************************
	 * 	Get Deliveries
	 * 	@return deliveries
	 */
	public MAssetDelivery[] getDeliveries()
	{
		final String whereClause = I_A_Asset_Delivery.COLUMNNAME_A_Asset_ID+"=?";
		List<MAssetDelivery> list = new Query(getCtx(),I_A_Asset_Delivery.Table_Name,whereClause,get_TrxName())
		.setParameters(getA_Asset_ID())
		.setOrderBy("Created DESC")
		.list();
		//
		MAssetDelivery[] retValue = new MAssetDelivery[list.size()];
		list.toArray(retValue);
		return retValue;
	}	//	getDeliveries

	/**
	 * 	Get Delivery count
	 * 	@return delivery count
	 */
	public int getDeliveryCount()
	{
		String sql = "SELECT COUNT(*) FROM A_Asset_Delivery WHERE A_Asset_ID=?";
		return DB.getSQLValue(get_TrxName(),
			sql, getA_Asset_ID());
	}	//	getDeliveries


	/**************************************************************************
	 * 	Can we download.
	 * 	Based on guarantee date and availability of download
	 * 	@return true if downloadable
	 */
	public boolean isDownloadable()
	{
		if (!isActive())
			return false;

		//	Guarantee Date
		Timestamp guarantee = getGuaranteeDate();
		if (guarantee == null)
			return false;
		guarantee = TimeUtil.getDay(guarantee);
		Timestamp now = TimeUtil.getDay(System.currentTimeMillis());
		//	valid
		if (!now.after(guarantee))	//	not after guarantee date
		{
			getProduct();
			return m_product != null
				&& m_product.hasDownloads();
		}
		//
		return false;
	}	//	isDownloadable

	/**************************************************************************
	 * 	Get Product Version No
	 *	@return VersionNo
	 */
	public String getProductVersionNo()
	{
		return getProduct().getVersionNo();
	}	//	getProductVersionNo

	/**
	 * 	Get Product R_MailText_ID
	 *	@return R_MailText_ID
	 */
	public int getProductR_MailText_ID()
	{
		return getProduct().getR_MailText_ID();
	}	//	getProductR_MailText_ID

	/**
	 * 	Get Product Info
	 * 	@return product
	 */
	private MProduct getProduct()
	{
		if (m_product == null)
			m_product = MProduct.get (getCtx(), getM_Product_ID());
		return m_product;
	}	//	getProductInfo

	/**
	 * 	Get Active Addl. Product Downloads
	 *	@return array of downloads
	 */
	public MProductDownload[] getProductDownloads()
	{
		if (m_product == null)
			getProduct();
		if (m_product != null)
			return m_product.getProductDownloads(false);
		return null;
	}	//	getProductDownloads

	/**
	 * 	Get Additional Download Names
	 *	@return names
	 */
	public String[] getDownloadNames()
	{
		MProductDownload[] dls = getProductDownloads();
		if (dls != null && dls.length > 0)
		{
			String[] retValue = new String[dls.length];
			for (int i = 0; i < retValue.length; i++)
				retValue[i] = dls[i].getName();
			log.fine("#" + dls.length);
			return retValue;
		}
		return new String[]{};
	}	//	addlDownloadNames

	/**
	 * 	Get Additional Download URLs
	 *	@return URLs
	 */
	public String[] getDownloadURLs()
	{
		MProductDownload[] dls = getProductDownloads();
		if (dls != null && dls.length > 0)
		{
			String[] retValue = new String[dls.length];
			for (int i = 0; i < retValue.length; i++)
			{
				String url = dls[i].getDownloadURL();
				int pos = Math.max(url.lastIndexOf('/'), url.lastIndexOf('\\'));
				if (pos != -1)
					url = url.substring(pos+1);
				retValue[i] = url;
			}
			return retValue;
		}
		return new String[]{};
	}	//	addlDownloadURLs

	/**
	 * Before Save
	 * @param newRecord new
	 * @return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		// Set parent asset:
		if (getA_Parent_Asset_ID() <= 0)
		{
			setA_Parent_Asset_ID(getA_Asset_ID());
		}
		
		//Goodwill - Set inventory number:
		String invNo = getInventoryNo();
		if (invNo == null || invNo.trim().length() == 0 || invNo.equals("0"))
		{
			invNo = "" + get_ID();
			setInventoryNo(invNo);
		}
		if (invNo != null)
		{
			setInventoryNo(invNo.trim());
		}
		log.fine("InventoryNo=" + getInventoryNo());
		
		// If no asset group, than set the default one:
		if (getA_Asset_Group_ID() <= 0)
		{
			setA_Asset_Group_ID(MAssetGroup.getDefault_ID(SetGetUtil.wrap(this)));
		}
		
		//Goodwill - Check for Asset Group and Product's Asset Group
		if (!getA_Asset_Group().equals(getM_Product().getM_Product_Category().getA_Asset_Group()))
		{
			throw new AssetCheckDocumentException("Asset Group and Product's Asset Group are different");
		}//End of Asset Group Check
		
		//Goodwill - Check for Asset Group Accounting
		if (getA_Asset_Group_ID() > 0)
		{
			List<MAssetGroupAcct> assetGrpAcct = MAssetGroupAcct.forA_Asset_Group_ID(getCtx(), getA_Asset_Group_ID());
			if (assetGrpAcct.size() == 0)
			{
				log.saveError("Create Asset Error: ", Msg.translate(getCtx(), "No Asset Group Account"));
				return false;
			}
		}//End of Asset Group Accounting Check
		
		/* @win temporary commented out
		
		if (getA_Asset_Class_ID() <= 0 && getA_Asset_Group_ID() > 0)
		{
			MAssetGroup.updateAsset(SetGetUtil.wrap(this), getA_Asset_Group_ID());
		}
		*/
		//end @win comment
		
		// Copy fields from C_BPartner_Location
		if (is_ValueChanged(COLUMNNAME_C_BPartner_Location_ID) && getC_BPartner_Location_ID() > 0)
		{
			// Goodwill BF: �Error: org.compiere.model.MAsset cannot be cast to org.compiere.model.SetGetModel�
			SetGetUtil.copyValues(SetGetUtil.wrap(this), MBPartnerLocation.Table_Name, getC_BPartner_Location_ID(),
					new String[]{MBPartnerLocation.COLUMNNAME_C_Location_ID}
			);
		}
		//
		// Create ASI if not exist:
		if (getM_Product_ID() > 0 && getM_AttributeSetInstance_ID() <= 0)
		{
			MProduct product = MProduct.get(getCtx(), getM_Product_ID());
			MAttributeSetInstance asi = new MAttributeSetInstance(getCtx(), 0, product.getM_AttributeSet_ID(), get_TrxName());
			asi.setSerNo(getSerNo());
			asi.setDescription();
			asi.saveEx();
			setM_AttributeSetInstance_ID(asi.getM_AttributeSetInstance_ID());
		}
		// TODO: With the lines below, after creating the asset, the whole system goes much slower ??? 
//		else if (is_ValueChanged(COLUMNNAME_SerNo) && getM_AttributeSetInstance_ID() > 0) {
//			asi = new MAttributeSetInstance(getCtx(), getM_AttributeSetInstance_ID(), get_TrxName());
//			asi.setSerNo(getSerNo());
//			asi.setDescription();
//			asi.saveEx();
//		}
//		else if ((newRecord || is_ValueChanged(COLUMNNAME_M_AttributeSetInstance_ID)) && getM_AttributeSetInstance_ID() > 0) {
//			asi = new MAttributeSetInstance(getCtx(), getM_AttributeSetInstance_ID(), get_TrxName());
//			setASI(asi);
//		}
		//
		
		// Update status
		updateStatus();
		
		// Validate AssetType
		//@win commented out
		//MAssetType.validate(this);
		//@win end
		//
		
		return true;
	}	//	beforeSave
	
	
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		if(!success)
		{
			return success;
		}
		
		//Goodwill - Set inventory number:
		String invNo = getInventoryNo();
		if(invNo == null || invNo.trim().length() == 0 || invNo.equals("0"))
		{
			invNo = "" + get_ID();
			setInventoryNo(invNo);
		}
		if(invNo != null)
		{
			setInventoryNo(invNo.trim());
		}
		log.fine("InventoryNo=" + getInventoryNo());
		
		// Set parent
		if(getA_Parent_Asset_ID() <= 0)
		{
			int A_Asset_ID = getA_Asset_ID();
			setA_Parent_Asset_ID(A_Asset_ID);
			DB.executeUpdateEx("UPDATE A_Asset SET A_Parent_Asset_ID=A_Asset_ID WHERE A_Asset_ID=" + A_Asset_ID, get_TrxName());
			log.fine("A_Parent_Asset_ID=" + getA_Parent_Asset_ID());
		}
		
		// If new record, create accounting and workfile
		if (newRecord)
		{
			//@win: set value at asset group as default value for asset
			MAssetGroup assetgroup = new MAssetGroup(getCtx(), getA_Asset_Group_ID(), get_TrxName());
			String isDepreciated = (assetgroup.isDepreciated()) ? "Y" : "N";
			String isOwned = (assetgroup.isOwned()) ? "Y" : "N";
			setIsDepreciated(assetgroup.isDepreciated());
			setIsOwned(assetgroup.isOwned());
			DB.executeUpdateEx("UPDATE A_Asset SET IsDepreciated='" + isDepreciated + "', isOwned ='" + isOwned + "' WHERE A_Asset_ID=" + getA_Asset_ID(), get_TrxName());
			//end @win
			
			// for each asset group acounting create an asset accounting and a workfile too
			for (MAssetGroupAcct assetgrpacct :  MAssetGroupAcct.forA_Asset_Group_ID(getCtx(), getA_Asset_Group_ID()))
			{			
				// Asset Accounting
				MAssetAcct assetacct = new MAssetAcct(this, assetgrpacct);
				assetacct.setAD_Org_ID(getAD_Org_ID()); //added by @win
				assetacct.saveEx();
				
				// Asset Depreciation Workfile
				MDepreciationWorkfile assetwk = new MDepreciationWorkfile(this, assetacct.getPostingType(), assetgrpacct);
				assetwk.setAD_Org_ID(getAD_Org_ID()); //added by @win
				assetwk.setUseLifeYears(0);
				assetwk.setUseLifeMonths(0);
				assetwk.setUseLifeYears_F(0);
				assetwk.setUseLifeMonths_F(0);
				assetwk.saveEx();
				
				// Change Log
				MAssetChange.createAndSave(getCtx(), "CRT", new PO[]{this, assetwk, assetacct}, null);
			}
			
		}
		else
		{
			MAssetChange.createAndSave(getCtx(), "UPD", new PO[]{this}, null);
		}
		
		//
		// Update child.IsDepreciated flag
		if (!newRecord && is_ValueChanged(COLUMNNAME_IsDepreciated))
		{
			final String sql = "UPDATE " + MDepreciationWorkfile.Table_Name
				+" SET " + MDepreciationWorkfile.COLUMNNAME_IsDepreciated+"=?"
				+" WHERE " + MDepreciationWorkfile.COLUMNNAME_A_Asset_ID+"=?";
			DB.executeUpdateEx(sql, new Object[]{isDepreciated(), getA_Asset_ID()}, get_TrxName());
		}
		
		return true;
	}	//	afterSave
	
	
	protected boolean beforeDelete()
	{
		// delete addition
		{
			String sql = "DELETE FROM "+MAssetAddition.Table_Name+" WHERE "+MAssetAddition.COLUMNNAME_Processed+"=? AND "+MAssetAddition.COLUMNNAME_A_Asset_ID+"=?";
			int no = DB.executeUpdateEx(sql, new Object[]{false, getA_Asset_ID()}, get_TrxName());
			log.info("@A_Asset_Addition@ @Deleted@ #" + no);
		}
		//
		// update invoice line
		{
			final String sql = "UPDATE "+MInvoiceLine.Table_Name+" SET "
										+" "+MInvoiceLine.COLUMNNAME_A_Asset_ID+"=?"
										+","+MInvoiceLine.COLUMNNAME_A_Processed+"=?"
								+" WHERE "+MInvoiceLine.COLUMNNAME_A_Asset_ID+"=?";
			int no = DB.executeUpdateEx(sql, new Object[]{null, false, getA_Asset_ID()}, get_TrxName());
			log.info("@C_InvoiceLine@ @Updated@ #" + no);
		}
		return true;
	}       //      beforeDelete
	
	/**
	 * 
	 * @see #beforeSave(boolean)
	 */
	public void updateStatus()
	{
		String status = getA_Asset_Status();
		setProcessed(!status.equals(A_ASSET_STATUS_New));
//		setIsDisposed(!status.equals(A_ASSET_STATUS_New) && !status.equals(A_ASSET_STATUS_Activated));
		setIsDisposed(status.equals(A_ASSET_STATUS_Disposed));
		setIsFullyDepreciated(status.equals(A_ASSET_STATUS_Depreciated));
		if(isFullyDepreciated() || status.equals(A_ASSET_STATUS_Disposed))
		{
			setIsDepreciated(false);
		}
		/* commented by @win 
		MAssetClass assetClass = MAssetClass.get(getCtx(), getA_Asset_Class_ID());
		if (assetClass != null && assetClass.isDepreciated())
		{
			setIsDepreciated(true);
		}
		*/ //end comment by @win
		if (status.equals(A_ASSET_STATUS_Activated) || getAssetActivationDate() == null)
		{
			setAssetActivationDate(getAssetServiceDate());
		}
	}
	
	/**
	 * Change asset status to newStatus
	 * @param newStatus see A_ASSET_STATUS_
	 * @param date state change date; if null context "#Date" will be used
	 */
	public void changeStatus(String newStatus, Timestamp date)
	{
		String status = getA_Asset_Status();
		if (CLogMgt.isLevelFinest()) log.finest("Entering: " + status + "->" + newStatus + ", date=" + date);
		
		//
		// If date is null, use context #Date
		if(date == null) {
			date = Env.getContextAsDate(getCtx(), "#Date");
		}
		
		//
		//	Activation/Addition
		if(newStatus.equals(A_ASSET_STATUS_Activated))
		{
			setAssetActivationDate(date);
		}
		//
		// Preservation
		if(newStatus.equals(A_ASSET_STATUS_Preservation))
		{
			setAssetDisposalDate(date);
			// TODO: move to MAsetDisposal
			Collection<MDepreciationWorkfile> workFiles = MDepreciationWorkfile.forA_Asset_ID(getCtx(), getA_Asset_ID(), get_TrxName());
			for(MDepreciationWorkfile assetwk : workFiles) {
				assetwk.truncDepreciation();
				assetwk.saveEx();
			}
		}
		// Disposal
		if(newStatus.equals(A_ASSET_STATUS_Disposed))
		{ // casat, vandut
			setAssetDisposalDate(date);
		}
		
		// Set new status
		setA_Asset_Status(newStatus);
	}	//	changeStatus
	
	// Temporary used variables:
	/**			*/
	private int m_UseLifeMonths_F = 0;
	public int getUseLifeMonths_F()											{	return m_UseLifeMonths_F;	}
	public void setUseLifeMonths_F(int UseLifeMonths_F)						{	m_UseLifeMonths_F = UseLifeMonths_F; }
	/**			*/
	private int m_A_Current_Period = 0;
	public int getA_Current_Period()										{	return m_A_Current_Period;	}
	public void setA_Current_Period(int A_Current_Period)					{	m_A_Current_Period = A_Current_Period; }
	/**			*/
	private Timestamp m_DateAcct = null;
	public Timestamp getDateAcct()											{	return m_DateAcct;	}
	public void setDateAcct(Timestamp DateAcct)								{	m_DateAcct = DateAcct; }
	/**			*/
	private int m_A_Depreciation_ID = 0;
	public int getA_Depreciation_ID()										{	return m_A_Depreciation_ID;	}
	public void setA_Depreciation_ID(int A_Depreciation_ID)					{	m_A_Depreciation_ID = A_Depreciation_ID; }
	/**			*/
	private int m_A_Depreciation_F_ID = 0;
	public int getA_Depreciation_F_ID()										{	return m_A_Depreciation_F_ID;	}
	public void setA_Depreciation_F_ID(int A_Depreciation_F_ID)				{	m_A_Depreciation_F_ID = A_Depreciation_F_ID; }
	/**			*/
	private BigDecimal m_A_Asset_Cost = Env.ZERO;
	private BigDecimal m_A_Accumulated_Depr = Env.ZERO;
	private BigDecimal m_A_Accumulated_Depr_F = Env.ZERO;
	public BigDecimal getA_Asset_Cost()										{	return m_A_Asset_Cost;	}
	public void setA_Asset_Cost(BigDecimal A_Asset_Cost)					{	m_A_Asset_Cost = A_Asset_Cost; }
	public BigDecimal getA_Accumulated_Depr()								{	return m_A_Accumulated_Depr;	}
	public void setA_Accumulated_Depr(BigDecimal A_Accumulated_Depr)		{	m_A_Accumulated_Depr = A_Accumulated_Depr; }
	public BigDecimal getA_Accumulated_Depr_F()								{	return m_A_Accumulated_Depr_F;	}
	public void setA_Accumulated_Depr_F(BigDecimal A_Accumulated_Depr_F)	{	m_A_Accumulated_Depr_F = A_Accumulated_Depr_F; }

}
