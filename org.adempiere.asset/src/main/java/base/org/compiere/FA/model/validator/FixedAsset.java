/**
 * 
 */
package org.compiere.FA.model.validator;

import java.util.List;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.FillMandatoryException;
import org.compiere.acct.Fact;
import org.compiere.model.I_M_InOut;
import org.compiere.model.I_M_Product;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MClient;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MMatchInv;
import org.compiere.model.MProduct;
import org.compiere.model.MProductCategory;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.PO;
import org.compiere.model.X_C_InvoiceLine;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Msg;
import org.compiere.FA.exceptions.AssetProductStockedException;
import org.compiere.FA.model.MAsset;
import org.compiere.FA.model.MAssetAddition;
import org.compiere.FA.model.MAssetDisposed;
import org.compiere.FA.model.MAssetGroup;
import org.compiere.FA.model.SetGetModel;
import org.compiere.FA.model.SetGetUtil;



/**
 * Fixed Assets Model Validator
 * @author Teo_Sarca, SC ARHIPAC SERVICE SRL
 * @author Goodwill Consulting
 *
 */
public class FixedAsset
implements org.compiere.model.ModelValidator, org.compiere.model.FactsValidator
{
	/** Logger */
	private static CLogger log = CLogger.getCLogger(FixedAsset.class);
	/** Client */
	private int clientId = -1;

	
	public int getAD_Client_ID() {
		return clientId;
	}

	
	public void initialize(ModelValidationEngine engine, MClient client)
	{
		if (client != null)
		{
			clientId = client.getAD_Client_ID();
		}

		engine.addModelChange(MInvoiceLine.Table_Name, this);				
		engine.addModelChange(MMatchInv.Table_Name, this);
		engine.addModelChange(MAssetGroup.Table_Name, this);
		engine.addDocValidate(MInvoice.Table_Name, this);
		engine.addDocValidate(MInOut.Table_Name, this);
	}

	public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID)
	{
		return null;
	}

	public String modelChange(PO po, int type) throws Exception
	{
		if (po instanceof MMatchInv
				&& (TYPE_AFTER_NEW == type 
						|| (TYPE_AFTER_CHANGE == type && po.is_ValueChanged(MMatchInv.COLUMNNAME_Processed))))
		{
			MMatchInv mi = (MMatchInv)po;
			if (mi.isProcessed())
			{
				MInvoiceLine invoiceLine = new MInvoiceLine(mi.getCtx(), mi.getC_InvoiceLine_ID(), mi.get_TrxName());
				if (invoiceLine.isA_CreateAsset()
						&& !invoiceLine.isA_Processed()
						/* commented by @win
						&& MAssetType.isFixedAssetGroup(mi.getCtx(), invoiceLine.getA_Asset_Group_ID())
						*/
					)
				{
					int loopQty = 1; //Goodwill - Loop counter for collective asset validation
					
					//Goodwill - If the Invoice Line is an expense type
					if (MInvoiceLine.A_CAPVSEXP_Expense.equals(invoiceLine.getA_CapvsExp()))
						invoiceLine.set_ValueOfColumn("IsCollectiveAsset", false);
					
					//Goodwill - If the Invoice Line is a capital type and with an Asset_ID
					if (MInvoiceLine.A_CAPVSEXP_Capital.equals(invoiceLine.getA_CapvsExp())
							&& invoiceLine.getA_Asset_ID() > 0)
						invoiceLine.set_ValueOfColumn("IsCollectiveAsset", false);
					
					//Goodwill - If the Invoice Line is a capital type and without Asset_ID
					if (MInvoiceLine.A_CAPVSEXP_Capital.equals(invoiceLine.getA_CapvsExp())
							&& invoiceLine.getA_Asset_ID() <= 0)
					{
						if (!invoiceLine.get_ValueAsBoolean("IsCollectiveAsset"))
							loopQty = mi.getQty().intValue();
					}
					
					//Goodwill - Loop for creating asset addition
					for (int i = 0; i < loopQty; i++)
					{
						MAssetAddition.createAsset(mi);
					}
				}
			}
		}

		// Invoice Line
		else if (po instanceof MInvoiceLine)
		{			
			MInvoiceLine il = (MInvoiceLine)po;			
			if (type == TYPE_CHANGE || type == TYPE_NEW) 
				beforeSave(il, type == TYPE_NEW);
			modelChange_InvoiceLine(SetGetUtil.wrap(po), type);
		}
		
		// Asset Group
		else if (po.get_TableName().equals(MAssetGroup.Table_Name))
		{
			MAssetGroup assetGroup = (MAssetGroup)po;
			if (type == TYPE_NEW || type == TYPE_CHANGE) 
				beforeSave(assetGroup, type == TYPE_NEW);
			else if (type == TYPE_AFTER_NEW || type == TYPE_AFTER_CHANGE) 
				afterSave(assetGroup, type == TYPE_AFTER_NEW);
			else if (type == TYPE_DELETE) 
				beforeDelete(assetGroup);
		}
		
		return null;
		
	}

	public String docValidate(PO po, int timing)
	{
			
		log.info(po.get_TableName() + " Timing: " + timing);
		String result = null;
		
		// TABLE C_Invoice
		String tableName = po.get_TableName();
		if(tableName.equals(MInvoice.Table_Name)){
			// Invoice - Validate Fixed Assets Invoice (LRO)
			//if (timing==TIMING_AFTER_PREPARE)
			//{
			//	MInvoice invoice = (MInvoice)po;
			//	validateFixedAssetsInvoice_LRO(invoice);
			//}
			
			if(timing==TIMING_AFTER_COMPLETE){
				MInvoice invoice = (MInvoice)po;
				if (invoice.isSOTrx()) {
					MInvoiceLine[] invoiceLines = invoice.getLines();
					for (MInvoiceLine invoiceLine: invoiceLines) {
						if (invoiceLine.isA_CreateAsset() && !invoiceLine.isA_Processed()) {
							if (invoiceLine.getA_Asset_ID() <= 0)
								throw new AdempiereException("@A_Asset_ID@ @NotFound@");
							
							MAssetDisposed.createAssetDisposed(invoiceLine);
						}
					}
				}
			} //end MInvoice TIMING_AFTER_COMPLETE
			
			if(timing==TIMING_AFTER_VOID)
			{
				MInvoice invoice = (MInvoice)po;
				String error = afterVoid(invoice);
				if (error != null)
					return error;
			}
			
			if(timing==TIMING_BEFORE_REVERSECORRECT)
			{
				MInvoice invoice = (MInvoice)po;
				String error = beforeReverseCorrect(invoice);
				if (error != null)
					return error;
			}
		}

		if (tableName.equals(MInOut.Table_Name))
		{
			if (timing == TIMING_AFTER_COMPLETE)
			{
				MInOut inOut = (MInOut) po;
				for (MInOutLine inOutLine :  inOut.getLines())
				{
					MProduct product = inOutLine.getProduct();
					MProductCategory productCategory = MProductCategory.get(product.getCtx(), product.getM_Product_Category_ID());
					MAssetGroup assetGroup = MAssetGroup.get(product.getCtx(), productCategory.getA_Asset_Group_ID()); 
						//	Create Asset for SO
					if (product != null
							&& inOut.isSOTrx()
							&& product.isCreateAsset()
							&& !assetGroup.isFixedAsset()
							&& inOutLine.getMovementQty().signum() > 0
							&& !inOut.isReversal()) {
						log.fine("Asset");
						//info.append("@A_Asset_ID@: ");
						int noAssets = inOutLine.getMovementQty().intValue();
						if (!product.isOneAssetPerUOM())
							noAssets = 1;
						for (int i = 0; i < noAssets; i++) {
							//if (i > 0)
							//	info.append(" - ");
							int deliveryCount = i + 1;
							if (!product.isOneAssetPerUOM())
								deliveryCount = 0;
							MAsset asset = new MAsset(inOut, inOutLine, deliveryCount);
							asset.saveEx(inOut.get_TrxName());
						}
					}
				}	//	Asset

			}
			if ( timing == TIMING_AFTER_REVERSECORRECT)
			{
				MInOut inOut = (MInOut) po;
				I_M_InOut inOutReversal = inOut.getReversal();
				for (MInOutLine inOutLine :  inOut.getLines()) {
					//	De-Activate Asset
					MAsset asset = MAsset.getFromShipment(inOut.getCtx(), inOutLine.getM_InOutLine_ID(), inOut.get_TrxName());
					if (asset != null) {
						asset.setIsActive(false);
						asset.setDescription(asset.getDescription() + " (" + inOutReversal.getDocumentNo() + " #" + inOutLine.getLine() + "<-)");
						asset.saveEx();
					}
				}
			}
		}
		return result;
	} // docValidate
	
	/**
	 * Model Change Invoice Line
	 * @param model model
	 * @param changeType set when called from model validator (See TYPE_*); else -1, when called from callout
	 */
	public static void modelChange_InvoiceLine(SetGetModel model, int changeType) {
		//
		// Set Asset Related Fields:
		if (-1 == changeType || TYPE_BEFORE_NEW == changeType || TYPE_BEFORE_CHANGE == changeType) {
			//int invoice_id = SetGetUtil.get_AttrValueAsInt(m, MInvoiceLine.COLUMNNAME_C_Invoice_ID);
			//boolean isSOTrx = DB.isSOTrx(MInvoice.Table_Name, MInvoice.COLUMNNAME_C_Invoice_ID+"="+invoice_id);
			boolean isAsset = false;
			/* comment by @win
			boolean isFixedAsset = false;
			*/
			int assetGroupId = 0;
			
			//Goodwill - invoice is an Asset type Invoice
			isAsset = SetGetUtil.get_AttrValueAsBoolean(model, MInvoiceLine.COLUMNNAME_A_CreateAsset);

			//@win commenting this out to enable relating AR Invoice to Asset Disposal
			/*
			if (!isSOTrx) {
				int product_id = SetGetUtil.get_AttrValueAsInt(m, MInvoiceLine.COLUMNNAME_M_Product_ID);
				if (product_id > 0) {
					MProduct prod = MProduct.get(m.getCtx(), product_id);
					isAsset = (prod != null && prod.get_ID() > 0 && prod.isCreateAsset());
					assetGroup_ID = prod.getA_Asset_Group_ID();
					
					//isFixedAsset = MAssetType.isFixedAssetGroup(m.getCtx(), assetGroup_ID); //commented by @win - remove asset type
				}
			}
			*/
			
			int productId = SetGetUtil.get_AttrValueAsInt(model, MInvoiceLine.COLUMNNAME_M_Product_ID);
			if (productId > 0) {
				MProduct product = MProduct.get(model.getCtx(), productId, model.get_TrxName());
				if (product.isCreateAsset())
				{
					isAsset = (product != null && product.get_ID() > 0 && product.isCreateAsset());
					assetGroupId = product.getA_Asset_Group_ID();
				}
				//Goodwill - if the product is not Asset Type
				else 
					assetGroupId = SetGetUtil.get_AttrValueAsInt(model, MInvoiceLine.COLUMNNAME_A_Asset_Group_ID);
			}			
			// end modification by @win
			
			model.set_AttrValue(MInvoiceLine.COLUMNNAME_A_CreateAsset, isAsset);
			if (isAsset) {
				model.set_AttrValue(MInvoiceLine.COLUMNNAME_A_Asset_Group_ID, assetGroupId);
				/* comment by @win
				m.set_AttrValue(MInvoiceLine.COLUMNNAME_IsFixedAssetInvoice, isFixedAsset);
				*/
				model.set_AttrValue("IsFixedAssetInvoice", isAsset);
				model.set_AttrValue(MInvoiceLine.COLUMNNAME_A_CreateAsset, "Y");
				
			}
			else {
				model.set_AttrValue(MInvoiceLine.COLUMNNAME_A_Asset_Group_ID, null);
				model.set_AttrValue(MInvoiceLine.COLUMNNAME_A_Asset_ID, null);
				model.set_AttrValue("IsFixedAssetInvoice", false);
			}
			//
			// Validate persistent object: 
			if (isAsset && (model instanceof MInvoiceLine)) {
				MInvoiceLine invoiceLine = (MInvoiceLine)model;
				//
				// If is expense, then asset is mandatory
				if (MInvoiceLine.A_CAPVSEXP_Expense.equals(invoiceLine.getA_CapvsExp()) && invoiceLine.getA_Asset_ID() <= 0) {
					throw new FillMandatoryException(MInvoiceLine.COLUMNNAME_A_Asset_ID);
				}
				//
				// Check Amounts & Qty
				if (invoiceLine.getLineNetAmt().signum() == 0) {
					throw new FillMandatoryException(MInvoiceLine.COLUMNNAME_QtyEntered, MInvoiceLine.COLUMNNAME_PriceEntered);
				}
				//
				// Check Product - fixed assets products shouldn't be stocked (but inventory objects are allowed)
				MProduct product = invoiceLine.getProduct();
				if (product.isStocked() && invoiceLine.get_ValueAsBoolean("IsFixedAssetInvoice")) {
					throw new AssetProductStockedException(product);
				}
			}
		}
		
		//
		// Update Invoice Header:
		if (TYPE_AFTER_NEW == changeType || TYPE_AFTER_CHANGE == changeType || TYPE_AFTER_DELETE == changeType) {
			int invoiceId = SetGetUtil.get_AttrValueAsInt(model, MInvoiceLine.COLUMNNAME_C_Invoice_ID);
			String sql =
				"UPDATE C_Invoice i SET IsFixedAssetInvoice"
						+"=(SELECT COALESCE(MAX(il.IsFixedAssetInvoice),'N')"
						+" FROM C_InvoiceLine il"
						+" WHERE il.C_Invoice_ID=i.C_Invoice_ID"
						+" AND il."+MInvoiceLine.COLUMNNAME_IsDescription+"='N'"
						+")"
				+" WHERE C_Invoice_ID=?";
			DB.executeUpdateEx(sql, new Object[]{invoiceId}, model.get_TrxName());
		}
	}
	
	/**
	 *  Before Save Asset Group
	 *  @param assetGroup
	 *  @param newRecord
     *	@return error message or null
     *	@exception Exception if the recipient wishes the change to be not accept.
	 */
	private String beforeSave(MAssetGroup assetGroup, boolean newRecord) throws Exception
	{		
		if (assetGroup.is_ValueChanged("IsDefault"))
		{
			int no = DB.getSQLValue(assetGroup.get_TrxName(),
					"SELECT count(*) FROM A_Asset_Group WHERE IsActive='Y' AND IsDefault='Y' AND Ad_Client_ID=? AND Ad_Org_ID=?",
					assetGroup.getAD_Client_ID(),assetGroup.getAD_Org_ID());
			
			if (no == 1 && !assetGroup.isDefault() && !newRecord)
			{
				throw new IllegalStateException("One active Default is expected");		
			}
									
		}
		return null;
	} //beforeSave
	
	/**
	 *  After Save Asset Group
	 *  @param assetGroup
	 *  @param newRecord
     *	@return error message or null
     *	@exception Exception if the recipient wishes the change to be not accept.
	 */
	private String afterSave(MAssetGroup assetGroup, boolean newRecord) throws Exception
	{		
		if ( assetGroup.isDefault()) // now current group
		{
			DB.executeUpdateEx("UPDATE A_Asset_Group SET IsDefault='N' WHERE IsActive='Y' AND Ad_Client_ID=? AND Ad_Org_ID=? AND A_Asset_Group_ID !=?", 
				new	Object[]{assetGroup.getAD_Client_ID(),assetGroup.getAD_Org_ID(),assetGroup.getA_Asset_Group_ID()},assetGroup.get_TrxName());
		}
		return null;
	} //afterSave


	/**
	 * Before Delete
 	 * @param assetGroup
	 * @return
	 * @throws Exception
	 */
	private String beforeDelete(MAssetGroup assetGroup) throws Exception
	{
		int no = DB.getSQLValue(assetGroup.get_TrxName(),
				"SELECT count(*) FROM A_Asset_Group WHERE IsActive='Y' AND IsDefault='Y' AND Ad_Client_ID=? AND Ad_Org_ID=? AND A_Asset_Group_ID=? ",
				assetGroup.getAD_Client_ID(), assetGroup.getAD_Org_ID(), assetGroup.getA_Asset_Group_ID());
			
		if (no == 1)
		{
			throw new IllegalStateException("One active Default is expected");		
		}
		return null;
	} //beforeDelete
	
	private boolean beforeSave(MInvoiceLine invoiceLine, boolean newRecord)
	{
		//Goodwill - Check for asset invoice 
		if (invoiceLine.isA_CreateAsset())
		{
			I_M_Product product = invoiceLine.getM_Product();
			if (X_C_InvoiceLine.A_CAPVSEXP_Capital.equals(invoiceLine.getA_CapvsExp()) && product.getM_Product_Category().getA_Asset_Group_ID() == 0)
				throw new AdempiereException("@A_Asset_Group_ID@ @NotFound@ @To@ @M_Product_ID@ : " + product.getName());

			// Check for asset group and product differences
			if (X_C_InvoiceLine.A_CAPVSEXP_Capital.equals(invoiceLine.getA_CapvsExp()) && invoiceLine.getA_Asset_ID() > 0)
			{
				MAsset asset = MAsset.get(invoiceLine.getCtx(), invoiceLine.getA_Asset_ID(), null);
				if (invoiceLine.getA_Asset_Group_ID() != asset.getA_Asset_Group_ID())
				{
					throw new AdempiereException(Msg.translate(invoiceLine.getCtx(), "Asset Group on Invoice Line is different from Asset Group on Asset"));
				}
				if (invoiceLine.getM_Product_ID() != asset.getM_Product_ID())
				{
					throw new AdempiereException(Msg.translate(invoiceLine.getCtx(), "Product on Invoice Line is different from Asset Product"));
				}
			}
		
			//Expense Asset_ID check
			if (X_C_InvoiceLine.A_CAPVSEXP_Expense.equals(invoiceLine.getA_CapvsExp()) && invoiceLine.getA_Asset_ID() > 0)
			{
				MAsset asset = MAsset.get(invoiceLine.getCtx(), invoiceLine.getA_Asset_ID(), null);
				invoiceLine.setA_Asset_ID(asset.getA_Asset_ID());
				invoiceLine.setA_Asset_Group_ID(asset.getA_Asset_Group_ID());
				invoiceLine.setA_CapvsExp(X_C_InvoiceLine.A_CAPVSEXP_Expense);
			}
		}//Goodwill - End check for asset invoice
		
		return true;
	}
	
	/**
	 * 	Before Reverse Correct Invoice
	 * 	@param invoice
	 *	@return error message or null 
	 */
	private String beforeReverseCorrect(MInvoice invoice)
	{
		// Goodwill - Check Asset Addition's status
		if (invoice.get_ValueAsBoolean("IsFixedAssetInvoice"))
		{
			final String sql = "SELECT A_Asset_Addition_ID "
					+"FROM A_Asset_Addition WHERE C_Invoice_ID=? ";
			int A_Asset_Addition_ID = DB.getSQLValueEx(invoice.get_TrxName(), sql, invoice.get_ID());
			MAssetAddition assetAdd = new MAssetAddition(invoice.getCtx(), A_Asset_Addition_ID, invoice.get_TrxName());
			if (assetAdd.getDocStatus().equals(MAssetAddition.DOCSTATUS_Completed)
				|| assetAdd.getDocStatus().equals(MAssetAddition.DOCSTATUS_Closed))
			{
				return "Can't Void or Reverse Invoice with Completed Asset Addition";
			}
		}
		// End Check	
		return null;
	}	//	beforeReverseCorrect	
	
	/**
	 *  After Void Invoice
	 *  @param invoice
     *	@return error message or null
	 */
	private String afterVoid(MInvoice invoice)
	{
		// Goodwill - check if invoice is for fixed asset
		if (invoice.get_ValueAsBoolean("IsFixedAssetInvoice"))
		{
			final String sql = "SELECT A_Asset_Addition_ID "
					+"FROM A_Asset_Addition WHERE C_Invoice_ID=? ";
			int A_Asset_Addition_ID = DB.getSQLValueEx(invoice.get_TrxName(), sql, invoice.get_ID());
			MAssetAddition assetAdd = new MAssetAddition(invoice.getCtx(), A_Asset_Addition_ID, invoice.get_TrxName());
			
			// Void asset addition if it's not completed
			if (MAssetAddition.DOCSTATUS_Drafted.equals(assetAdd.getDocStatus())
				|| MAssetAddition.DOCSTATUS_InProgress.equals(assetAdd.getDocStatus())
				|| MAssetAddition.DOCSTATUS_Invalid.equals(assetAdd.getDocStatus())
				|| MAssetAddition.DOCSTATUS_Approved.equals(assetAdd.getDocStatus())
				|| MAssetAddition.DOCSTATUS_NotApproved.equals(assetAdd.getDocStatus()))
			{
				if (!assetAdd.processIt(MAssetAddition.DOCACTION_Void))
				{
					log.warning("Asset Addition Process Failed: " + assetAdd + " - " + assetAdd.getProcessMsg());
					throw new IllegalStateException("Asset Addition Process Failed: " + assetAdd + " - " + assetAdd.getProcessMsg());
				}
				assetAdd.saveEx();
			}
		}		
		return null; 
	}	//	afterVoid

	
	public String factsValidate(MAcctSchema schema, List<Fact> facts, PO po) {
		// TODO: implement it
		return null;
	}
}
