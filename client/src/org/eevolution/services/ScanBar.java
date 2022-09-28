/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2003-2016 e-Evolution Consultants. All Rights Reserved.      *
 * Copyright (C) 2003-2016 Victor Pèrez Juárez 								  *
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
 * Contributor(s): Victor Pérez Juúrez  (victor.perez@e-evolution.com)		  *
 * Sponsors: e-Evolution Consultants (http://www.e-evolution.com/)            *
 *****************************************************************************/

package org.eevolution.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Vector;

import org.adempiere.core.domains.models.I_M_AttributeSetInstance;
import org.adempiere.core.domains.models.I_M_InOutLine;
import org.adempiere.core.domains.models.I_M_InventoryLine;
import org.adempiere.core.domains.models.I_M_Product;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.minigrid.IMiniTable;
import org.compiere.model.MAttributeSet;
import org.compiere.model.MAttributeSetInstance;
import org.compiere.model.MInOutLine;
import org.compiere.model.MInventoryLine;
import org.compiere.model.MMovementLine;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProduct;
import org.compiere.model.MStorage;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfo;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 * Implementation Scan Bar UI
 * 
 * @author victor.perez@e-evoluton.com, www.e-evolution.com
 */
public abstract class ScanBar {

	/** Logger */
	public static CLogger log = CLogger.getCLogger(ScanBar.class);
	private LinkedHashMap<MProduct, BigDecimal> scanLines = null;

	protected Boolean isLot = null;
	protected Boolean isSerNo = null;
	protected Boolean isSerNoMandotory = null;
	protected MProduct product = null;
	protected String lotNo = null;
	protected String serNo = null;
	protected int locatorId = 0;
	protected int locatorToId = 0;
	protected MTable table = null;
	protected MTable tableLine = null;
	protected int recordId = 0;
	protected PO parent = null;

	protected static int ID = 0;
	protected static int PRODUCT_ID = 1;
	protected static int QTY_ORDERED = 2;
	protected static int QTY_PICKED = 3;

	public LinkedHashMap<String, Vector> data = null;
	public LinkedHashMap<Integer, ArrayList<Object>> source = null;


	/**
	 * getData
	 * 
	 * @return LinkedHashMap data
	 */
	public LinkedHashMap<String, Vector> getData() {
		if (data == null)
			loadData();
		return data;
	}
	
	public MProduct getMProduct(String barCode) {
		MProduct product = getProductByUPC(barCode);
		if (product != null)
			return product;
		product = getProductByValueC(barCode);
		if (product != null)
			return product;

		int prefixPosition = barCode.toUpperCase().indexOf("P");
		if (prefixPosition > 0 && prefixPosition <= 3)
			product = getProductByValueC(barCode.substring(prefixPosition + 1));
		return product;
	}

	public MProduct getProductByUPC(String barCode) {
		StringBuilder whereClause = new StringBuilder();
		whereClause.append("UPPER(").append(I_M_Product.COLUMNNAME_UPC).append(")=?");
		return new Query(Env.getCtx(), I_M_Product.Table_Name,
				whereClause.toString(), null).setClient_ID()
				.setParameters(barCode.toUpperCase()).first();
	}

	public MProduct getProductByValueC(String barCode) {
		StringBuilder whereClause = new StringBuilder();
		whereClause.append("UPPER(").append(I_M_Product.COLUMNNAME_Value).append(")=?");
		return new Query(Env.getCtx(), I_M_Product.Table_Name,
				whereClause.toString(), null).setClient_ID()
				.setParameters(barCode.toUpperCase()).first();
	}

	public LinkedHashMap<MProduct, BigDecimal> getLines() {
		return scanLines;
	}

	public boolean findProduct(String searchKey) {
		if (scanLines != null)
			return scanLines.containsKey(searchKey);

		return false;
	}

	public BigDecimal getProductQty(String serachKey) {
		return scanLines.get(serachKey);
	}

	public Vector<String> getColumnNames() {
		// Header Info
		Vector<String> columnNames = new Vector<String>(5);
		columnNames.add(Msg.translate(Env.getCtx(), "Value"));
		columnNames.add(Msg.translate(Env.getCtx(), "Name"));
		columnNames.add(Msg.translate(Env.getCtx(), "Lot"));
		columnNames.add(Msg.translate(Env.getCtx(), "SerNo"));
		columnNames.add(Msg.translate(Env.getCtx(), "QtyEntered"));
		return columnNames;
	}

	public void setColumnClass(IMiniTable dataTable) {
		dataTable.setColumnClass(0, String.class, true); // 0-Value
		dataTable.setColumnClass(1, String.class, true); // 1-Name
		dataTable.setColumnClass(2, String.class, true); // 3-Lot
		dataTable.setColumnClass(3, String.class, true); // 4-SerNo
		dataTable.setColumnClass(4, BigDecimal.class, true); // 5-Qty Entered
		// Table UI
		dataTable.autoSize();
	}

	public boolean isLot() {
		if (isLot == null) {
			MAttributeSet attributeSet = null;
			if (product.getM_AttributeSet_ID() > 0) {
				attributeSet = (MAttributeSet) product.getM_AttributeSet();
				isLot = attributeSet.isLot();
				boolean isExcludeEntry = attributeSet.excludeEntry(getTableLine().getAD_Table_ID(), Env.isSOTrx(product.getCtx()));
				if (isExcludeEntry)
					isLot = false;
			} else
				isLot = false;
		}

		return isLot;
	}

	public boolean isSerNo() {
		if (isSerNo == null) {
			MAttributeSet attributeSet = null;
			if (product.getM_AttributeSet_ID() > 0) {
				attributeSet = (MAttributeSet) product.getM_AttributeSet();
				isSerNo = attributeSet.isSerNo();
				isSerNoMandotory = attributeSet.isSerNoMandatory();
				boolean isExcludeEntry = attributeSet.excludeEntry(getTableLine().getAD_Table_ID(), Env.isSOTrx(product.getCtx()));
				if (isExcludeEntry)
					isSerNo = false;
			} else
				isSerNo = false;
		}

		return isSerNo;
	}

	public boolean isSerNoMandatory() {
		if (isSerNoMandotory != null)
			return isSerNoMandotory;

		isSerNo();

		return isSerNoMandotory;
	}

	public void setProduct(MProduct product) {
		this.product = product;
		this.lotNo = null;
		this.serNo = null;
		this.isLot = null;
		this.isSerNo = null;
		isLot();
		isSerNo();
	}

	public String getLotNo() {
		return lotNo;
	}

	public String getSerNo() {
		return serNo;
	}

	protected void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}

	protected void setSerNo(String serNo) {
		this.serNo = serNo;
	}

	public MProduct getProduct() {
		return product;
	}

	protected void setData(LinkedHashMap<String, Vector> data) {
		this.data = data;
	}
	
	protected Vector<Vector<Object>> getDataModel()
	{
		Vector<Vector<Object>>  dataModel = new Vector<Vector<Object>>() ;
		for (Vector<Object> line : getData().values())
		{
			Vector<Object> targetLine = new Vector<Object>();
			targetLine.add(line.get(0));
			targetLine.add(line.get(1));
			targetLine.add(line.get(2));
			targetLine.add(line.get(3));
			targetLine.add(line.get(4));
			dataModel.add(targetLine);
		}
		return dataModel;
	}

	protected PO getParent() {
		if (parent == null)
			parent = getTable().getPO(getRecord_ID(), null);

		return parent;
	}

	protected LinkedHashMap<Integer, ArrayList<Object>> getSource() {
		if (source != null)
			return source;

		PO po = getParent();
		int C_Order_ID = po.get_ValueAsInt(MOrder.COLUMNNAME_C_Order_ID);
		if (C_Order_ID > 0) {
			StringBuilder whereClause = new StringBuilder();
			whereClause.append(MOrderLine.COLUMNNAME_C_Order_ID).append(
					"=? AND ");
			whereClause.append("(").append(MOrderLine.COLUMNNAME_QtyOrdered)
					.append("-");
			whereClause.append(MOrderLine.COLUMNNAME_QtyDelivered)
					.append(")>0");
			List<MOrderLine> orderLines = new Query(Env.getCtx(),
					MOrderLine.Table_Name, whereClause.toString(), null)
					.setClient_ID().setParameters(C_Order_ID).list();

			source = new LinkedHashMap<Integer, ArrayList<Object>>();
			for (MOrderLine line : orderLines) {
				if (line.getQtyOrdered().subtract(line.getQtyDelivered())
						.signum() > 0) {
					ArrayList<Object> parameters = new ArrayList<Object>();
					parameters.add(ID, line.getC_OrderLine_ID());
					parameters.add(PRODUCT_ID, line.getM_Product_ID());
					parameters.add(
							QTY_ORDERED,
							line.getQtyOrdered().subtract(
									line.getQtyDelivered()));
					parameters.add(
							QTY_PICKED,
							line.getQtyOrdered().subtract(
									line.getQtyDelivered()));
					source.put(line.get_ID(), parameters);
				}
			}

		} else {
			StringBuilder whereClause = new StringBuilder();
			whereClause.append(getTable().getKeyColumns()[0]).append("=? ");
			List<PO> lines = new Query(Env.getCtx(), getTable().getTableName(),
					whereClause.toString(), null).setClient_ID()
					.setParameters(getRecord_ID()).list();

			source = new LinkedHashMap<Integer, ArrayList<Object>>();
			for (PO line : lines) {
				if (line.get_ValueAsInt(MProduct.COLUMNNAME_M_Product_ID) > 0) {
					ArrayList<Object> parameters = new ArrayList<Object>();
					parameters.add(ID, line.get_ID());
					parameters.add(PRODUCT_ID, line
							.get_ValueAsInt(MProduct.COLUMNNAME_M_Product_ID));
					parameters.add(QTY_ORDERED,
							line.get_Value(MInOutLine.COLUMNNAME_PickedQty));
					parameters.add(QTY_PICKED,
							line.get_Value(MInOutLine.COLUMNNAME_PickedQty));
					source.put(line.get_ID(), parameters);
				}
			}
		}

		return source;
	}

	protected ArrayList<Object> checkProduct(MProduct product, BigDecimal qty, boolean reset) {

		for (Entry<Integer, ArrayList<Object>> entry : getSource().entrySet()) {
			ArrayList<Object> values = entry.getValue();
			int M_Product_ID = (Integer) values.get(PRODUCT_ID);
			BigDecimal qtyPicked = (BigDecimal) values.get(QTY_PICKED);
			if (product.get_ID() != M_Product_ID)
				continue;

			if (qtyPicked.signum() <= 0)
				continue;

			Integer id = (Integer) values.get(ID);
			BigDecimal qtyOrdered = (BigDecimal) values.get(QTY_ORDERED);

			if (qtyPicked.compareTo(qty) >= 0) {				
				if (qtyPicked != null && qtyPicked.signum() >= 0) {
					if(reset)
						qtyPicked = qtyOrdered.subtract(qty);
					else	
						qtyPicked = qtyPicked.subtract(qty);
					
					if(qtyPicked.signum() < 0)
						return null;
					
					values.set(QTY_PICKED, qtyPicked);
					source.put(entry.getKey(), values);
					return values;
				}
			}
		}
		return null;
	}

	protected int getM_Locater_ID() {
		return locatorId;
	}
	
	protected int getM_LocaterTo_ID() {
		return locatorToId;
	}

	protected int getRecord_ID() {
		if (recordId <= 0)
			recordId = getProcessInfo().getRecord_ID();

		return recordId;
	}

	protected MTable getTable() {
		if (table == null)
		{	
			table = MTable.get(Env.getCtx(), getProcessInfo()
					.getTable_ID());
			if("WM_InOutBound".equals(table.getTableName()))
				tableLine = MTable.get(Env.getCtx(), getTable().getTableName()
						+ "LineMA"); 
			else 	
			tableLine = MTable.get(Env.getCtx(), getTable().getTableName()
					+ "Line"); 
		}	
		return table;
	}

	protected MTable getTableLine()
	{
		return tableLine;
	}
	

	public void createLine() {
		int lineNo = DB.getSQLValueEx(null,
				"SELECT Line FROM " + tableLine.getTableName() + " WHERE "
						+ table.getTableName() + "_ID=?", getRecord_ID());
		if (lineNo <= 0)
			lineNo = 10;

		for (Vector<Object> line : getData().values()) {
			
			String value = (String) line.get(0);
			String lotNo = (String) line.get(2);
			String serNo = (String) line.get(3);
			Boolean isASI = (lotNo != null && lotNo.length() > 0)
					|| (serNo != null && serNo.length() > 0) ? true : false;
			BigDecimal qty = (BigDecimal) line.get(4);
			int id = (Integer) line.get(5);
			Integer referenceId = (Integer) line.get(6);
			
			PO poLine = null;
			MAttributeSetInstance asi = null;
		

			MProduct product = new Query(Env.getCtx(), I_M_Product.Table_Name,
					"Value = ? ", null).setClient_ID().setParameters(value)
					.firstOnly();
			String desc = null;

			poLine = tableLine.getPO(id, null);
			if (product.getM_AttributeSet_ID() > 0 && isASI) {

				if (poLine != null && poLine.get_ValueAsInt(I_M_AttributeSetInstance.COLUMNNAME_M_AttributeSetInstance_ID) > 0)
					asi = new MAttributeSetInstance(Env.getCtx(),
							poLine.get_ValueAsInt(I_M_AttributeSetInstance.COLUMNNAME_M_AttributeSetInstance_ID), null);
				else
					asi = getAttributeSetInstance(product, lotNo, serNo,getM_Locater_ID(), null);
			}
			
			poLine.set_ValueOfColumn(table.getKeyColumns()[0], getRecord_ID());
			poLine.set_ValueOfColumn(I_M_Product.COLUMNNAME_M_Product_ID,
					product.get_ID());
			poLine.set_ValueOfColumn(I_M_Product.COLUMNNAME_C_UOM_ID,
					product.getC_UOM_ID());
			poLine.set_ValueOfColumn(I_M_InOutLine.COLUMNNAME_Line, lineNo);
			poLine.set_ValueOfColumn(I_M_InOutLine.COLUMNNAME_IsActive, true);
			int locatorColumnId = poLine
					.get_ColumnIndex(I_M_InOutLine.COLUMNNAME_M_Locator_ID);
			if (locatorColumnId > 0 && getM_Locater_ID() > 0)
				poLine.set_ValueOfColumn(I_M_InOutLine.COLUMNNAME_M_Locator_ID,
						getM_Locater_ID());

			if (asi == null && isASI )  
			{				
				if (asi == null && isASI) {
					asi = new MAttributeSetInstance(Env.getCtx(), 0,
							product.getM_AttributeSet_ID(), null);
					if (lotNo != null) {
						asi.setLot(lotNo);
						desc = lotNo;
					}
					if (serNo != null) {
						asi.setSerNo(serNo);
						if (desc != null)
							desc = desc + " - " + serNo;
						else
							desc = serNo;
					}
					asi.setDescription(desc);
					asi.saveEx();
				}
			}
			
			if (poLine instanceof MInventoryLine) {
				MStorage storage = MStorage.get(Env.getCtx(),
						getM_Locater_ID(), product.getM_Product_ID(),
						asi == null ? 0 : asi.getM_AttributeSetInstance_ID(),
						null);
				poLine.set_CustomColumn(I_M_InventoryLine.COLUMNNAME_QtyCount, qty);
				poLine.set_CustomColumn(I_M_InventoryLine.COLUMNNAME_QtyBook,
						storage == null ? Env.ZERO : storage.getQtyOnHand());
			} else if (poLine instanceof MInOutLine) {
				MInOutLine ioLine = (MInOutLine) poLine;
				ioLine.setQty(qty);
				ioLine.setC_OrderLine_ID(referenceId);
			} else if (poLine instanceof MMovementLine )
			{	
				MMovementLine movementLine = (MMovementLine) poLine;
				movementLine.setM_LocatorTo_ID(getM_LocaterTo_ID());
				movementLine.setMovementQty(qty);
			}	
			else
				poLine.set_ValueOfColumn(I_M_InOutLine.COLUMNNAME_MovementQty, qty);
				
			
			poLine.set_ValueOfColumn(
					MAttributeSetInstance.COLUMNNAME_M_AttributeSetInstance_ID,
					asi == null ? 0 : asi.get_ID());
			if(poLine.is_Changed())
				poLine.saveEx();
			
			lineNo = lineNo + 10;
		}
	}

	private PO getPOWithASI(MProduct product,
			String lotNo, String serNo, String trxName) {
		
		StringBuilder whereClause = new StringBuilder();
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(getRecord_ID());
		parameters.add(product.get_ID());
		
		whereClause.append("EXISTS (SELECT 1 FROM ").append(tableLine.Table_Name);
		whereClause.append("INNER JOIN M_AttributeSetInstance asi ON (asi.M_AttributeSetInstance_ID=")
		.append(tableLine.Table_Name)
		.append(".M_AttributeSetInstance_ID) ");
		whereClause.append("WHERE ").append(tableLine.Table_Name).append(",").append(table.getKeyColumns()[0]).append("=? AND M_Product_ID=?");
		
		if(lotNo != null && lotNo.length() > 0)
		{	
			whereClause.append(" AND ").append(MAttributeSetInstance.COLUMNNAME_Lot).append("=? ");
			parameters.add(lotNo);
		}	
		
		if(serNo != null && serNo.length() > 0)
		{	
			whereClause.append(" AND ").append(MAttributeSetInstance.COLUMNNAME_SerNo).append("=? ");
			parameters.add(serNo);
		}	
				
		whereClause.append(")");
		
		return new Query(Env.getCtx(),  tableLine.Table_Name,
				whereClause.toString(), null).setClient_ID()
				.setParameters(parameters).firstOnly();
	}

	protected MAttributeSetInstance getAttributeSetInstance(MProduct product,
			String lotNo, String serNo, int M_Locator_ID, String trxName) {
		StringBuilder whereClause = new StringBuilder(
				"EXISTS (SELECT 1 FROM M_Storage s WHERE s.M_Product_ID=? AND s.M_Locator_ID=? AND s.M_AttributeSetInstance_ID=M_AttributeSetInstance.M_AttributeSetInstance_ID ORDER BY s.QtyOnhand )");

		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(product.get_ID());
		parameters.add(M_Locator_ID);

		if (lotNo != null && lotNo.length() > 0) {
			whereClause.append(" AND ")
					.append(MAttributeSetInstance.COLUMNNAME_Lot).append("=?");
			parameters.add(lotNo);

		}

		if (serNo != null && serNo.length() > 0) {
			whereClause.append(" AND ")
					.append(MAttributeSetInstance.COLUMNNAME_SerNo)
					.append("=?");
			parameters.add(serNo);
		}

		return new Query(Env.getCtx(), MAttributeSetInstance.Table_Name,
				whereClause.toString(), trxName).setClient_ID()
				.setParameters(parameters).first();
	}

	protected void loadData() {

		data = new LinkedHashMap<String, Vector>();
		for (PO po : getDocumentLines()) {
			MProduct product = null;
			MAttributeSetInstance asi = null;
			String lotNo = null;
			String serNo = null;
			Integer referenceId = null;
			BigDecimal qty = null;
			boolean reset = false;
			
			int M_Product_ID = po
					.get_ValueAsInt(MProduct.COLUMNNAME_M_Product_ID);
			int M_AttributeSetInstance_ID = po
					.get_ValueAsInt(MAttributeSetInstance.COLUMNNAME_M_AttributeSetInstance_ID);
			if (M_Product_ID > 0)
				product = MProduct.get(Env.getCtx(), M_Product_ID);
			else
				continue;

			if (M_AttributeSetInstance_ID > 0) {
				asi = new MAttributeSetInstance(Env.getCtx(),
						M_AttributeSetInstance_ID, null);
				lotNo = asi.getLot();
				serNo = asi.getSerNo();
			} else
			{	
				M_AttributeSetInstance_ID = 0;
				reset = true;
				lotNo = null;
				serNo = null;
			}

			if (po instanceof MInOutLine) {
				MInOutLine ioLine = (MInOutLine) po;
				referenceId = ioLine.getC_OrderLine_ID();
				qty = ioLine.getMovementQty();
			}
			
			if (po instanceof MInventoryLine) {
				 MInventoryLine invenotryLine = (MInventoryLine) po;
				 qty = invenotryLine.getQtyCount();
			}
			
			if(getSource() != null && source.size() >0)
			{
				ArrayList<Object> values = checkProduct(product, qty , reset);
			
				if(values == null)
					throw new AdempiereException("@M_Product_ID@ ; "+product.getName() + " @InValid@");
			}	

			String key = product.getValue();
			if (lotNo != null && lotNo.length() > 0)
				key = key + lotNo;
			if (serNo != null && serNo.length() > 0)
				key = key + serNo;

			Vector<Object> line = new Vector<Object>(6);
			line.add(product.getValue()); // 0
			line.add(product.getName()); // 1
			line.add(lotNo); // 2
			line.add(serNo); // 3
			line.add(qty); // 4
			line.add(po.get_ID()); // 5
			line.add(referenceId != null ? referenceId.intValue() : 0); // 6
			data.put(key, line);
		}
	}
	
	private List<PO> getDocumentLines() {
		StringBuilder whereClause = new StringBuilder();
		whereClause.append(getTable().getKeyColumns()[0]).append("=? ");
		return new Query(Env.getCtx(),  tableLine.getTableName(),
				whereClause.toString(), null).setClient_ID()
				.setParameters(getRecord_ID()).list();
	}

	public abstract ProcessInfo getProcessInfo();
}
