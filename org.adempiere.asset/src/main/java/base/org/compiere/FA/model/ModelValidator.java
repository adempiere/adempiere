/**
 * 
 */
package org.compiere.FA.model;

import java.util.List;

import org.adempiere.exceptions.FillMandatoryException;
import org.compiere.acct.Fact;
import org.compiere.model.*;
import org.compiere.process.DocAction;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Msg;
import org.compiere.FA.exceptions.AssetInvoiceWithMixedLines_LRO;
import org.compiere.FA.exceptions.AssetProductStockedException;



/**
 * Fixed Assets Model Validator
 * @author Teo_Sarca, SC ARHIPAC SERVICE SRL
 * @author Goodwill Consulting
 *
 */
public class ModelValidator
implements org.compiere.model.ModelValidator, org.compiere.model.FactsValidator
{
	/** Logger */
	private static CLogger log = CLogger.getCLogger(ModelValidator.class);
	/** Client */
	private int m_AD_Client_ID = -1;

	
	public int getAD_Client_ID() {
		return m_AD_Client_ID;
	}

	
	public void initialize(ModelValidationEngine engine, MClient client)
	{
		if (client != null)
		{
			m_AD_Client_ID = client.getAD_Client_ID();
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
			MAssetGroup ag = (MAssetGroup)po;
			if (type == TYPE_NEW || type == TYPE_CHANGE) 
				beforeSave(ag, type == TYPE_NEW);
			else if (type == TYPE_AFTER_NEW || type == TYPE_AFTER_CHANGE) 
				afterSave(ag, type == TYPE_AFTER_NEW);
			else if (type == TYPE_DELETE) 
				beforeDelete(ag);
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
			if (timing==TIMING_AFTER_PREPARE)
			{
				MInvoice invoice = (MInvoice)po;
				validateFixedAssetsInvoice_LRO(invoice);
			}
			
			if(timing==TIMING_AFTER_COMPLETE){
				MInvoice mi = (MInvoice)po;
				if (mi.isSOTrx()) {
					MInvoiceLine[] mils = mi.getLines();
					for (MInvoiceLine mil: mils) {
						if (mil.isA_CreateAsset() && !mil.isA_Processed()) {
							MAssetDisposed.createAssetDisposed(mil);
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
						//	Create Asset for SO
					if (product != null
							&& inOut.isSOTrx()
							&& product.isCreateAsset()
							&& !product.getM_Product_Category().getA_Asset_Group().isFixedAsset()
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
							if (!asset.save(inOut.get_TrxName())) {
								//m_processMsg = "Could not create Asset";
								//return DocAction.STATUS_Invalid;
								throw new IllegalStateException("Could not create Asset");
							}
							//info.append(asset.getValue());
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
	 * @param ctx
	 * @param m model 
	 * @param changeType set when called from model validator (See TYPE_*); else -1, when called from callout
	 */
	public static void modelChange_InvoiceLine(SetGetModel m, int changeType) {
		//
		// Set Asset Related Fields:
		if (-1 == changeType || TYPE_BEFORE_NEW == changeType || TYPE_BEFORE_CHANGE == changeType) {
			//int invoice_id = SetGetUtil.get_AttrValueAsInt(m, MInvoiceLine.COLUMNNAME_C_Invoice_ID);
			//boolean isSOTrx = DB.isSOTrx(MInvoice.Table_Name, MInvoice.COLUMNNAME_C_Invoice_ID+"="+invoice_id);
			boolean isAsset = false;
			/* comment by @win
			boolean isFixedAsset = false;
			*/
			int assetGroup_ID = 0;
			
			//Goodwill - invoice is an Asset type Invoice
			isAsset = SetGetUtil.get_AttrValueAsBoolean(m, MInvoiceLine.COLUMNNAME_A_CreateAsset);

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
			
			int product_id = SetGetUtil.get_AttrValueAsInt(m, MInvoiceLine.COLUMNNAME_M_Product_ID);
			if (product_id > 0) {
				MProduct prod = MProduct.get(m.getCtx(), product_id);
				if (prod.isCreateAsset())
				{
					isAsset = (prod != null && prod.get_ID() > 0 && prod.isCreateAsset());
					assetGroup_ID = prod.getA_Asset_Group_ID();
				}
				//Goodwill - if the product is not Asset Type
				else 
					assetGroup_ID = SetGetUtil.get_AttrValueAsInt(m, MInvoiceLine.COLUMNNAME_A_Asset_Group_ID);
			}			
			// end modification by @win
			
			m.set_AttrValue(MInvoiceLine.COLUMNNAME_A_CreateAsset, isAsset);
			if (isAsset) {
				m.set_AttrValue(MInvoiceLine.COLUMNNAME_A_Asset_Group_ID, assetGroup_ID);
				/* comment by @win
				m.set_AttrValue(MInvoiceLine.COLUMNNAME_IsFixedAssetInvoice, isFixedAsset);
				*/
				m.set_AttrValue("IsFixedAssetInvoice", isAsset);
				m.set_AttrValue(MInvoiceLine.COLUMNNAME_A_CreateAsset, "Y");
				
			}
			else {
				m.set_AttrValue(MInvoiceLine.COLUMNNAME_A_Asset_Group_ID, null);
				m.set_AttrValue(MInvoiceLine.COLUMNNAME_A_Asset_ID, null);
				m.set_AttrValue("IsFixedAssetInvoice", false);
			}
			//
			// Validate persistent object: 
			if (isAsset && (m instanceof MInvoiceLine)) {
				MInvoiceLine line = (MInvoiceLine)m;
				//
				// If is expense, then asset is mandatory
				if (MInvoiceLine.A_CAPVSEXP_Expense.equals(line.getA_CapvsExp()) && line.getA_Asset_ID() <= 0) {
					throw new FillMandatoryException(MInvoiceLine.COLUMNNAME_A_Asset_ID);
				}
				//
				// Check Amounts & Qty
				if (line.getLineNetAmt().signum() == 0) {
					throw new FillMandatoryException(MInvoiceLine.COLUMNNAME_QtyEntered, MInvoiceLine.COLUMNNAME_PriceEntered);
				}
				//
				// Check Product - fixed assets products shouldn't be stocked (but inventory objects are allowed)
				MProduct product = line.getProduct();
				if (product.isStocked() && line.get_ValueAsBoolean("IsFixedAssetInvoice")) {
					throw new AssetProductStockedException(product);
				}
			}
		}
		
		//
		// Update Invoice Header:
		if (TYPE_AFTER_NEW == changeType || TYPE_AFTER_CHANGE == changeType || TYPE_AFTER_DELETE == changeType) {
			int invoice_id = SetGetUtil.get_AttrValueAsInt(m, MInvoiceLine.COLUMNNAME_C_Invoice_ID);
			String sql =
				"UPDATE C_Invoice i SET IsFixedAssetInvoice"
						+"=(SELECT COALESCE(MAX(il.IsFixedAssetInvoice),'N')"
						+" FROM C_InvoiceLine il"
						+" WHERE il.C_Invoice_ID=i.C_Invoice_ID"
						+" AND il."+MInvoiceLine.COLUMNNAME_IsDescription+"='N'"
						+")"
				+" WHERE C_Invoice_ID=?";
			DB.executeUpdateEx(sql, new Object[]{invoice_id}, m.get_TrxName());
		}
	}
	
	/**
	 * Check if is a valid fixed asset related invoice (LRO)
	 * @param invoice
	 */
	private void validateFixedAssetsInvoice_LRO(MInvoice invoice)
	{
		if (invoice.get_ValueAsBoolean("IsFixedAssetInvoice"))
		{
			boolean hasFixedAssetLines = false;
			boolean hasNormalLines = false;
			for (MInvoiceLine line : invoice.getLines())
			{
				if (line.get_ValueAsBoolean("IsFixedAssetInvoice"))
				{
					hasFixedAssetLines = true;
				}
				else if (line.getM_Product_ID() > 0)
				{
					MProduct product = MProduct.get(line.getCtx(), line.getM_Product_ID());
					if (product.isItem())
					{
						// Only items are forbiden for FA invoices because in Romania these should use
						// V_Liability vendor account and not V_Liability_FixedAssets vendor account
						hasNormalLines = true;
					}
				}
				//
				// No mixed lines are allowed
				if (hasFixedAssetLines && hasNormalLines)
				{
					throw new AssetInvoiceWithMixedLines_LRO();
				}
			}
		}
	}
	
	/**
	 *  Before Save Asset Group
	 *  @param MAssetGroup ag
	 *  @param boolean newRecord
     *	@return error message or null
     *	@exception Exception if the recipient wishes the change to be not accept.
	 */
	private String beforeSave(MAssetGroup ag, boolean newRecord) throws Exception
	{		
		if (ag.is_ValueChanged("IsDefault"))
		{
			int no = DB.getSQLValue(ag.get_TrxName(), 
					"SELECT count(*) FROM A_Asset_Group WHERE IsActive='Y' AND IsDefault='Y' AND Ad_Client_ID=? AND Ad_Org_ID=?",
					ag.getAD_Client_ID(),ag.getAD_Org_ID());
			
			if (no == 1 && !ag.isDefault() && !newRecord)
			{
				throw new IllegalStateException("One active Default is expected");		
			}
									
		}
		return null;
	} //beforeSave
	
	/**
	 *  After Save Asset Group
	 *  @param MAssetGroup ag
	 *  @param boolean newRecord
     *	@return error message or null
     *	@exception Exception if the recipient wishes the change to be not accept.
	 */
	private String afterSave(MAssetGroup ag, boolean newRecord) throws Exception
	{		
		if ( ag.isDefault()) // now current group
		{
			DB.executeUpdateEx("UPDATE A_Asset_Group SET IsDefault='N' WHERE IsActive='Y' AND Ad_Client_ID=? AND Ad_Org_ID=? AND A_Asset_Group_ID !=?", 
				new	Object[]{ag.getAD_Client_ID(),ag.getAD_Org_ID(),ag.getA_Asset_Group_ID()},ag.get_TrxName());			
		}
		return null;
	} //afterSave
	
	/**
	 *  Before Delete MAssetGroup
	 *  @param MLocator ml
     *	@return error message or null
     *	@exception Exception if the recipient wishes the change to be not accept.
	 */
	
	private String beforeDelete(MAssetGroup ag) throws Exception
	{
		int no = DB.getSQLValue(ag.get_TrxName(), 
					"SELECT count(*) FROM A_Asset_Group WHERE IsActive='Y' AND IsDefault='Y' AND Ad_Client_ID=? AND Ad_Org_ID=? AND A_Asset_Group_ID=? ",
					ag.getAD_Client_ID(),ag.getAD_Org_ID(),ag.getA_Asset_Group_ID());
			
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
			// Check for asset group and product differences
			if (X_C_InvoiceLine.A_CAPVSEXP_Capital.equals(invoiceLine.getA_CapvsExp()) && invoiceLine.getA_Asset_ID() > 0)
			{
				if (invoiceLine.getA_Asset_Group_ID() != invoiceLine.getA_Asset().getA_Asset_Group_ID())
				{
					log.saveError("Asset Group Error", Msg.translate(invoiceLine.getCtx(), "Asset Group on Invoice Line is different from Asset Group on Asset"));
					return false;
				}
				if (invoiceLine.getM_Product_ID() != invoiceLine.getA_Asset().getM_Product_ID())
				{
					log.saveError("Product Error", Msg.translate(invoiceLine.getCtx(), "Product on Invoice Line is different from Asset Product"));
					return false;
				}
			}//
		
			//Expense Asset_ID check
			if (X_C_InvoiceLine.A_CAPVSEXP_Expense.equals(invoiceLine.getA_CapvsExp()) && invoiceLine.getA_Asset_ID() <= 0)
			{
				log.saveError("Asset Error", Msg.translate(invoiceLine.getCtx(), "No Asset ID"));
				return false;
			}
			if (X_C_InvoiceLine.A_CAPVSEXP_Expense.equals(invoiceLine.getA_CapvsExp()) && invoiceLine.getA_Asset_ID() > 0)
			{
				invoiceLine.setA_Asset_ID(invoiceLine.getA_Asset().getA_Asset_ID());
				invoiceLine.setA_Asset_Group_ID(invoiceLine.getA_Asset().getA_Asset_Group_ID());
				invoiceLine.setA_CapvsExp(X_C_InvoiceLine.A_CAPVSEXP_Expense);
			}
		}//Goodwill - End check for asset invoice
		
		return true;
	}
	
	/**
	 * 	Before Reverse Correct Invoice
	 * 	@param MInvoice invoice
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
	 *  @param MInvoice invoice
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
