//package org.adempiere.engine.storage;
//
//import java.math.BigDecimal;
//import java.sql.Savepoint;
//import java.util.List;
//import java.util.Properties;
//
//import org.adempiere.engine.IInventoryAllocation;
//import org.adempiere.engine.storage.StorageEngine;
//import org.adempiere.exceptions.AdempiereException;
//import org.compiere.model.MBPartner;
//import org.compiere.model.MClient;
//import org.compiere.model.MDocType;
//import org.compiere.model.MInOut;
//import org.compiere.model.MInOutLine;
//import org.compiere.model.MInOutLineMA;
//import org.compiere.model.MInventory;
//import org.compiere.model.MInventoryLine;
//import org.compiere.model.MLocator;
//import org.compiere.model.MMatchPO;
//import org.compiere.model.MMovement;
//import org.compiere.model.MMovementLine;
//import org.compiere.model.MOrder;
//import org.compiere.model.MOrderLine;
//import org.compiere.model.MProduct;
//import org.compiere.model.MProductPO;
//import org.compiere.model.MStorage;
//import org.compiere.model.MTransaction;
//import org.compiere.model.MWarehouse;
//import org.compiere.model.PO;
//import org.compiere.model.Query;
//import org.compiere.model.X_C_Order;
//import org.compiere.model.X_M_InOut;
//import org.compiere.process.SvrProcess;
//import org.compiere.util.Env;
//import org.compiere.util.Trx;
//import org.compiere.util.Util;
//
//public class StorageEngineTest extends SvrProcess {
//	
//
//	private int m_product_id;
//	private int m_attributeSetInstance_id;
//	private Properties ctx;
//	private String trxName;
//	private StringBuffer processMsg = new StringBuffer();
//	private MOrder purchaseOrder;
//	private MInOut mInOutReceipt;
//	private int m_warehouse_id;
//	private int m_locator_id;
//	private BigDecimal qtyToOrder;
//	private MProduct product;
//	private MBPartner vendor;
//	private boolean testsPassed = true;
//	
//	
//
//	@Override
//	protected void prepare() {
//		m_product_id = getParameterAsInt("M_Product_ID");
//		m_attributeSetInstance_id = getParameterAsInt("M_AttributeSetInstance_ID");
//	}
//
//	
//	@Override
//	protected String doIt() throws Exception {
//		
//		
//		ctx = getCtx();
//		
//		product = MProduct.get(ctx, m_product_id);
//		if (product != null && !product.isStocked())
//			return "Product is not stocked: " + product.toString();
//		
//		if (m_attributeSetInstance_id == 0)
//			m_attributeSetInstance_id = product.getM_AttributeSetInstance_ID();
//		
//		m_warehouse_id = Env.getContextAsInt(ctx, "M_Warehouse_ID");
//		m_locator_id = MWarehouse.get(ctx, m_warehouse_id).getDefaultLocator().get_ID();
//		
//		MClient.MMPOLICY_FiFo.equals(product.getMMPolicy());
//		
//		qtyToOrder = new BigDecimal(10);
//
//
//		// Create a new transaction
//		trxName = Trx.createTrxName("StorageEngineTest");
//		Trx trx = Trx.get(trxName, false);
//		
//		// Set a savepoint so the changes can be undone
//		Savepoint savepoint = trx.setSavepoint(null);
//
//		try 
//		{
//			// Test storage data for consistency
//			Util.assume(testStorageData(), processMsg.toString() + " -->Test of storage data failed!");		
//			
//			// Test Sales Order Reservations
//			Util.assume(testSOReservation(), processMsg.toString() + " -->Test of storage reservation failed!");
//					
//			// Make a PO and test ordered
//			Util.assume(testPOOrdered(), processMsg.toString() + " -->Test of storage ordered failed!");
//
//			// Make a MR and test reduction in ordered amount and increase in inventory
//			Util.assume(testMR(), processMsg.toString() + " -->Test of material receipt failed!");
//			
//			// Match MR and PO
//			Util.assume(testMatchPO(), processMsg.toString() + " -->Test of MatchPO failed!");
//
//			// Test Inventory
//			Util.assume(testInventory(), processMsg.toString() + " -->Test of Inventory failed!");
//			
//			// Test Inventory Move
//			Util.assume(testMovement(), processMsg.toString() + " -->Test of Inventory Move failed!");
//			
//			// TODO Production
//			
//		}
//		catch (AdempiereException e) 
//		{
//			
//			processMsg.append(" --X ").append(e.getLocalizedMessage());
//			testsPassed = false;
//			log.severe(e.getLocalizedMessage());
//			
//		}
//		finally 
//		{
//			// Undo all changes
//			trx.rollback(savepoint);
//			trx.close();
//			
//		}
//		
//		if (testsPassed)
//		{
//			
//			processMsg = new StringBuffer("All Tests Passed!");
//			
//		}
//		return processMsg.toString();
//	}
//	
//	private boolean testMovement() {
//		// Move from context warehouse to HQ
//
//		BigDecimal qtyOnHandOriginal = MStorage.getQtyOnHand(ctx, m_product_id, m_attributeSetInstance_id, m_locator_id, trxName);
//		
//		MLocator newLocator = MLocator.get(ctx, m_warehouse_id, "New Locator",
//				 "x", "y", "z");
//		newLocator.set_TrxName(trxName);
//		newLocator.saveEx();
//		
//		int m_locatorTo_id = newLocator.getM_Locator_ID();
//		
//		processMsg.append("<br>*** Test of Inventory Move");
//		MMovement mMovement = new MMovement(ctx,0,trxName);
//		mMovement.saveEx();
//
//		MMovementLine moveLine = new MMovementLine(mMovement);
//		moveLine.setM_Product_ID(m_product_id);
//		moveLine.setM_AttributeSetInstance_ID(m_attributeSetInstance_id);
//		moveLine.setM_AttributeSetInstanceTo_ID(m_attributeSetInstance_id);
//		moveLine.setM_Locator_ID(m_locator_id);
//		moveLine.setM_LocatorTo_ID(m_locatorTo_id);
//		moveLine.setMovementQty(Env.ONE);
//		moveLine.save();
//		
//		Util.assume(mMovement.processIt(X_M_InOut.DOCACTION_Complete), 
//				"Could not complete Movement.");
//		
//		BigDecimal qtyOnHand = MStorage.getQtyOnHand(ctx, m_product_id, m_attributeSetInstance_id, m_locator_id, trxName);
//		
//		Util.assume(qtyOnHand.add(Env.ONE).compareTo(qtyOnHandOriginal) == 0, "Quantity on hand was not reduced as expected.");
//
//		BigDecimal qtyOnHandNew = MStorage.getQtyOnHand(ctx, m_product_id, m_attributeSetInstance_id, m_locatorTo_id, trxName);
//		Util.assume(qtyOnHandNew.compareTo(Env.ONE) == 0, "Quantity on hand at new locator did not increase.");
//
//		processMsg.append(" --> Tested OK");
//		return true;
//	}
//
//	private boolean testInventory() {
//		
//		processMsg.append("<br>*** Test of Physical Inventory ");
//		MInventory mInventory = new MInventory(ctx,0,trxName);
//		mInventory.setM_Warehouse_ID(m_warehouse_id);
//		mInventory.saveEx();
//
//		BigDecimal qtyBook = MStorage.getQtyOnHand(ctx, m_product_id, m_attributeSetInstance_id, m_locator_id, trxName);
//		BigDecimal qtyCount = qtyBook.subtract(Env.ONE);
//		MInventoryLine il = new MInventoryLine(mInventory, m_locator_id, m_product_id, m_attributeSetInstance_id, qtyBook, qtyCount);
//		il.saveEx();
//		
//		Util.assume(mInventory.processIt(X_M_InOut.DOCACTION_Complete), 
//				"Could not complete Inventory.");
//		
//		BigDecimal qtyOnHand = MStorage.getQtyOnHand(ctx, m_product_id, m_attributeSetInstance_id, m_locator_id, trxName);
//		
//		Util.assume(qtyOnHand.compareTo(qtyCount) == 0, "Quantity on hand was not reduced as expected.");
//		
//		processMsg.append(" --> Tested OK");
//		return true;
//	}
//
//	private boolean testMatchPO() {
//		processMsg.append("<br>*** Test of Material Receipt ");
//				
//		BigDecimal currentQtyOrdered = Env.ZERO;
//		
//		currentQtyOrdered = MStorage.getOrderedQty(ctx, m_product_id, m_warehouse_id, m_attributeSetInstance_id, trxName);
//
//		MInOutLine mrLine = mInOutReceipt.getLines()[0];
//		MOrderLine poLine = purchaseOrder.getLines()[0];
//		MMatchPO match = new MMatchPO(ctx,0,trxName);
//		match.setAD_Org_ID(mrLine.getAD_Org_ID());
//		match.setM_InOutLine_ID (mrLine.getM_InOutLine_ID());	
//		match.setC_OrderLine_ID(poLine);
//		match.setDateTrx (mInOutReceipt.getDateAcct());
//		match.setM_Product_ID (mrLine.getM_Product_ID());
//		match.setM_AttributeSetInstance_ID(mrLine.getM_AttributeSetInstance_ID());
//		match.setM_MPolicyTicket_ID(mrLine.getM_MPolicyTicket_ID());
//		match.setQty (mrLine.getMovementQty());
//		match.saveEx();
//		
//		BigDecimal newQtyOrdered = Env.ZERO;
//		newQtyOrdered = MStorage.getOrderedQty(ctx, m_product_id, m_warehouse_id, m_attributeSetInstance_id, trxName);
//		
//
//		Util.assume(newQtyOrdered.add(mrLine.getMovementQty()).compareTo(currentQtyOrdered)==0,
//				"Qty Ordered did not change as expected after Matching PO with Shipment");
//				
//		match.delete(true);
//
//		newQtyOrdered = MStorage.getOrderedQty(ctx, m_product_id, m_warehouse_id, m_attributeSetInstance_id, trxName);
//		
//		Util.assume(newQtyOrdered.compareTo(currentQtyOrdered)==0,
//				"Qty Ordered did not revert to the original value as expected after deleting the MatchingPO");
//	
//		processMsg.append(" --> OK");
//		return true;
//	}
//
//	private boolean testMR() {
//		processMsg.append("<br>*** Test of Material Receipt ");
//		
//		BigDecimal currentQtyOrdered = Env.ZERO;
//		currentQtyOrdered = MStorage.getOrderedQty(ctx, m_product_id, m_warehouse_id, m_attributeSetInstance_id, trxName);
//
//		BigDecimal currentQtyAvailable = MStorage.getQtyAvailable(m_warehouse_id, m_locator_id, m_product_id, m_attributeSetInstance_id, trxName);
//
//		processMsg.append("<br>**** Creating Material receipt ");
//
//		mInOutReceipt = new MInOut(ctx,0,trxName);
//		mInOutReceipt.setIsSOTrx(false);
//		
//		// Find the docType with no confirmations required
//		MDocType dt = null;
//		MDocType[] docTypes = MDocType.getOfDocBaseType(ctx, MDocType.DOCBASETYPE_MaterialReceipt);
//		for (MDocType docType : docTypes) {
//			if (!docType.isPickQAConfirm() && !docType.isShipConfirm()) {
//				dt = docType;
//				break;
//			}
//		}
//		if (dt != null)
//			mInOutReceipt.setC_DocType_ID(dt.getC_DocType_ID());
//		else
//			mInOutReceipt.setC_DocType_ID();  // May cause issues with confirmations
//		
//		mInOutReceipt.setBPartner(vendor);
//		mInOutReceipt.setM_Warehouse_ID(m_warehouse_id);
//		mInOutReceipt.setMovementType(MTransaction.MOVEMENTTYPE_VendorReceipts);
//		mInOutReceipt.saveEx();
//		
//		MInOutLine mrLine = new MInOutLine(mInOutReceipt);
//		mrLine.setM_Product_ID(m_product_id);
//		mrLine.setM_AttributeSetInstance_ID(m_attributeSetInstance_id);
//		mrLine.setM_Locator_ID(m_locator_id);
//		mrLine.setQty(qtyToOrder);
//		mrLine.saveEx();
//
//		Util.assume(mInOutReceipt.processIt(X_M_InOut.DOCACTION_Complete), 
//				"Could not complete material receipt.");
//		Util.assume(X_M_InOut.DOCACTION_Complete.equals(mInOutReceipt.getDocStatus()), "MR was not completed. Doc Status=" + mInOutReceipt.getDocStatus());
//		processMsg.append(" --> Material receipt completed OK.");
//		
//		MStorage storage = null;
//
//		processMsg.append("<br>**** Testing Material receipt assumptions ");
//		for (MInOutLine line : mInOutReceipt.getLines()) { // Should only be one
//			if (currentQtyAvailable.signum() < 0)
//			{
//				
//				// MR allocated to negative inventory.  There will be MA lines
//				String whereClause = "M_InOutLineMA.M_InOutLine_ID=?";
//				List<MInOutLineMA> list = new Query(ctx, MInOutLineMA.Table_Name, whereClause, trxName)
//												.setClient_ID()
//												.setParameters(new Object[]{line.get_ID()})
//												.list();
//				for (MInOutLineMA ma : list)
//				{
//					Util.assume(ma.getM_MPolicyTicket_ID() > 0, "MR Line MA M_PolicyTicket_ID not > 0. A ticket should be assigned for each MR Line. " + line.toString());					
//				}
//
//			}
//			else 
//			{
//				
//				Util.assume(line.getM_MPolicyTicket_ID() > 0, "MR Line M_PolicyTicket_ID not > 0. A ticket should be assigned for each MR Line. " + line.toString());
//				storage = MStorage.get(ctx, m_locator_id, m_product_id, m_attributeSetInstance_id, line.getM_MPolicyTicket_ID(), trxName);
//				Util.assume(storage!=null, "Storage record not created!");
//				Util.assume(storage.getQtyOnHand().compareTo(qtyToOrder)==0, "Quantity on hand for the storage location was not increased!");
//				
//			}
//		}
//		processMsg.append(" --> Tested OK.");
//	
//		// Check the quantities
//		processMsg.append("<br>**** Checking quantities ");
//		// QtyAvailable
//		Util.assume(currentQtyAvailable.add(qtyToOrder).compareTo(MStorage.getQtyAvailable(m_warehouse_id, m_locator_id, m_product_id, m_attributeSetInstance_id, trxName)) == 0,
//					"Quantity available did not increase after Material Receipt.");
//		
//		BigDecimal newQtyOrdered = Env.ZERO;
//		newQtyOrdered = MStorage.getOrderedQty(ctx, m_product_id, m_warehouse_id, m_attributeSetInstance_id, trxName);
//
//		BigDecimal changeInOrderedQty = newQtyOrdered.subtract(currentQtyOrdered);
//		
//		Util.assume(changeInOrderedQty.compareTo(Env.ZERO) == 0,
//				"Quantity ordered (" + newQtyOrdered + ") changed.  Without match PO, it should stay the same.");
//		processMsg.append(" --> Tested OK");
//
//		return true;
//	}
//
//	private boolean testStorageData() {
//		
//		boolean noError = true;
//		
//		processMsg.append("<br>*** Test of storage data ");
//		// Get all the storage data
//		List<MStorage> storages = new Query(ctx,MStorage.Table_Name,"",trxName)
//				.setClient_ID()
//				.list();
//		
//		if (storages.size() == 0) {
//			processMsg.append("<br>   No storage data! Nothing to test. ");
//			return false;
//		}
//		
//		for (MStorage storage : storages) {
//			// If M_MPolicyTickte_ID is null/zero, quantities should be zero - the storage record should only be used
//			// for reservations and orders
//			if (storage.getM_MPolicyTicket_ID() == 0) {
//				if (storage.getQtyOnHand().compareTo(Env.ZERO) != 0 
//						|| storage.getQtyReserved().compareTo(Env.ZERO) != 0
//						|| storage.getQtyOrdered().compareTo(Env.ZERO) != 0) {
//					processMsg.append("<br>Warning: Quantities are not zero when material policy ticket is zero: ")
//						.append(storage.toString() + ". Please run the Storage Cleanup process!");
//					noError = false;
//				}
//			}
//			else {
//				if (storage.getQtyOnHand().compareTo(Env.ZERO) != 0 
//						&& (storage.getQtyReserved().compareTo(Env.ZERO) != 0
//								|| storage.getQtyOrdered().compareTo(Env.ZERO) != 0)) {
//					processMsg.append("<br>Error: Quantity on hand is not zero when qty reserved or qty ordered are also not zero: ")
//						.append(storage.toString() + ". Please run the Storage Cleanup process!");
//					noError = false;
//				}				
//			}
//		}
//		
//		processMsg.append(" --> OK");
//		return noError;
//	}
//
//	private boolean testSOReservation() {
//
//		processMsg.append("<br>*** Test of Sales Reservations ");
//		
//		BigDecimal currentQtyReserved = Env.ZERO;
//		
//		MBPartner customer = new Query(ctx, MBPartner.Table_Name, "isCustomer='Y'", trxName)
//									.setClient_ID()
//									.setOnlyActiveRecords(true)
//									.first();
//		
//		if (customer==null)
//			throw new AdempiereException("No active Customer Business Partner found.");
//		
//		//MStorage onhandStorage = MStorage.getM_Locator_ID(m_warehouse_id, m_product_id, product.getM_AttributeSetInstance_ID(), 0, qtyToOrder, trxName);
//		BigDecimal currentQtyAvailable = MStorage.getQtyAvailable(m_warehouse_id, m_locator_id, m_product_id, m_attributeSetInstance_id, trxName);
//		currentQtyReserved = MStorage.getReservedQty(ctx, m_product_id, m_warehouse_id, m_attributeSetInstance_id, trxName);
//		
//		MOrder so = new MOrder(ctx, 0, trxName);
//		so.setBPartner(customer);
//		so.setIsSOTrx(true); // Sales order
//		so.setC_DocTypeTarget_ID();
//		so.setM_Warehouse_ID(m_warehouse_id);
//		so.saveEx();
//		
//		MOrderLine sol = new MOrderLine(so);
//		sol.setM_Product_ID(m_product_id);
//		sol.setM_AttributeSetInstance_ID(m_attributeSetInstance_id);
//		sol.setQty(qtyToOrder);
//		sol.setM_Warehouse_ID(m_warehouse_id);
//		sol.saveEx();
//		
//		Util.assume(so.processIt(X_C_Order.DOCACTION_Complete), 
//					"Could not complete sales order.");
//		processMsg.append("<br>**** Order Completed.");
//		// Check the quantities
//		// QtyAvailable
//		BigDecimal changeInQtyAvailable = MStorage.getQtyAvailable(m_warehouse_id, m_locator_id, m_product_id, m_attributeSetInstance_id, trxName).subtract(currentQtyAvailable);
//		Util.assume(changeInQtyAvailable.add(qtyToOrder).signum() == 0,
//					"Quantity available did not changed on a standared sales order. It should be reduced by the qty ordered.");
//		processMsg.append("<br>**** Qty Available tested OK.");
//		
//		sol.load(trxName);
//		MStorage reservedStorage = MStorage.getReservedOrdered(ctx, m_product_id, m_warehouse_id, m_attributeSetInstance_id, sol.getM_MPolicyTicket_ID(), trxName);
//		Util.assume(reservedStorage != null, "No reserved storage found!");
//		Util.assume(reservedStorage.getQtyReserved().compareTo(qtyToOrder) == 0, "Reserved qty incorrect!");
//		processMsg.append("<br>**** Storage record tested OK.");
//		
//		BigDecimal changeInReservedQty = reservedStorage.getQtyReserved().subtract(currentQtyReserved);
//		
//		Util.assume(changeInReservedQty.compareTo(qtyToOrder) == 0,
//				"Quantity reserved (" + reservedStorage.getQtyReserved() + ") should be " 
//		+ currentQtyReserved.add(qtyToOrder));
//		processMsg.append("<br>**** Qty Reserved tested OK.");
//
//		return true;
//	}
//
//	private boolean testPOOrdered() {
//
//		processMsg.append("<br>*** Test of Purchase Order ");
//		
//		BigDecimal currentQtyOrdered = MStorage.getOrderedQty(ctx, m_product_id, m_warehouse_id, m_attributeSetInstance_id, trxName);
//		
//		BigDecimal currentQtyAvailable = MStorage.getQtyAvailable(m_warehouse_id, m_locator_id, m_product_id, m_attributeSetInstance_id, trxName);
//		
//		MProductPO[] productPOs = MProductPO.getOfProduct(ctx, m_product_id, trxName);
//		
//		vendor = null;
//		for (MProductPO productPO : productPOs) {
//			if (productPO != null && productPO.getC_BPartner_ID() > 0) {
//				vendor = (MBPartner) productPO.getC_BPartner();
//				break;
//			}
//		}
//		
//		if (vendor == null)
//			throw new AdempiereException("Product has no Vendor Business Partner defined.");
//		
//		purchaseOrder = new MOrder(ctx, 0, trxName);
//		purchaseOrder.setBPartner(vendor);
//		purchaseOrder.setIsSOTrx(false); // Purchase order
//		purchaseOrder.setC_DocTypeTarget_ID();
//		purchaseOrder.setM_Warehouse_ID(m_warehouse_id);
//		purchaseOrder.saveEx();
//		
//		MOrderLine poLine = new MOrderLine(purchaseOrder);
//		poLine.setM_Product_ID(m_product_id);
//		poLine.setM_AttributeSetInstance_ID(m_attributeSetInstance_id);
//		poLine.setQty(qtyToOrder);
//		poLine.setM_Warehouse_ID(m_warehouse_id);
//		poLine.saveEx();
//		
//		Util.assume(purchaseOrder.processIt(X_C_Order.DOCACTION_Complete), 
//					"Could not complete purchase order.");
//		processMsg.append("<br>**** Purchase order completed.");
//
//		// Check the quantities
//		// QtyAvailable
//		Util.assume(currentQtyAvailable.compareTo(MStorage.getQtyAvailable(m_warehouse_id, m_locator_id, m_product_id, m_attributeSetInstance_id, trxName)) == 0,
//					"Quantity available changed on a standared purchase order. It should remain the same.");
//		processMsg.append("<br>**** Qty Available tested OK.");
//		
//		// Check specific material policy ticket
//		poLine.load(trxName);
//		MStorage orderedStorage = MStorage.getReservedOrdered(ctx, m_product_id, m_warehouse_id, m_attributeSetInstance_id, poLine.getM_MPolicyTicket_ID(), trxName);
//		Util.assume(orderedStorage != null, "No ordered storage found!");
//		Util.assume(orderedStorage.getQtyOrdered().compareTo(qtyToOrder)==0, "Ordered qty incorrect!");
//		processMsg.append("<br>**** Storage record tested OK.");
//
//		// Check general qty ordered
//		BigDecimal changeInOrderedQty = MStorage.getOrderedQty(ctx, m_product_id, m_warehouse_id, m_attributeSetInstance_id, trxName).subtract(currentQtyOrdered);
//		
//		Util.assume(changeInOrderedQty.compareTo(qtyToOrder) == 0,
//				"Quantity ordered (" + orderedStorage.getQtyOrdered() + ") should be " 
//		+ currentQtyOrdered.add(qtyToOrder));
//		processMsg.append("<br>**** Qty Ordered tested OK.");
//		
//		Util.assume(purchaseOrder.processIt(X_C_Order.DOCACTION_Void), 
//				"Could not void purchase order.");
//		processMsg.append("<br>**** Purchase order voided.");
//
//		orderedStorage = MStorage.getReservedOrdered(ctx, m_product_id, m_warehouse_id, m_attributeSetInstance_id, poLine.getM_MPolicyTicket_ID(), trxName);
//		Util.assume(orderedStorage.getQtyOrdered().compareTo(Env.ZERO)==0, "Ordered not adjusted to zero after void!");
//
//		// Recreate the order for other tests
//		purchaseOrder = new MOrder(ctx, 0, trxName);
//		purchaseOrder.setBPartner(vendor);
//		purchaseOrder.setIsSOTrx(false); // Purchase order
//		purchaseOrder.setC_DocTypeTarget_ID();
//		purchaseOrder.setM_Warehouse_ID(m_warehouse_id);
//		purchaseOrder.saveEx();
//		
//		poLine = new MOrderLine(purchaseOrder);
//		poLine.setM_Product_ID(m_product_id);
//		poLine.setM_AttributeSetInstance_ID(m_attributeSetInstance_id);
//		poLine.setQty(qtyToOrder);
//		poLine.setM_Warehouse_ID(m_warehouse_id);
//		poLine.saveEx();
//		
//		Util.assume(purchaseOrder.processIt(X_C_Order.DOCACTION_Complete), 
//				"Could not complete 2nd purchase order.");
//
//		return true;
//	}
//
//}
