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

package org.eevolution.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Vector;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.minigrid.IMiniTable;
import org.compiere.model.I_M_AttributeSetInstance;
import org.compiere.model.I_M_Product;
import org.compiere.model.MAttributeSet;
import org.compiere.model.MAttributeSetInstance;
import org.compiere.model.MProduct;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfo;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.eevolution.model.I_WM_InOutBoundLine;
import org.eevolution.model.MWMInOutBoundLine;
import org.eevolution.model.MWMInOutBoundLineMA;

/**
 * Implementation Scan Bar UI
 * 
 * @author victor.perez@e-evoluton.com, www.e-evolution.com
 */
public abstract class ExpressReceiptScanBar {

	/** Logger */
	public static CLogger log = CLogger.getCLogger(ExpressReceiptScanBar.class);
	private LinkedHashMap<MProduct, BigDecimal> scanLines = null;

	protected Boolean isLot = null;
	protected Boolean isSerNo = null;
	protected Boolean isSerNoMandotory = null;
	protected MProduct product = null;
	protected String lotNo = null;
	protected String serNo = null;
	protected int locatorId = 0;
	protected MTable table = null;
	protected MTable tableLine = null;
	protected int recordId = 0;
	protected PO parent = null;

	protected static int ID = 0;
	protected static int PRODUCT_ID = 1;
	protected static int QTY_ORDERED = 2;
	protected static int QTY_PICKED = 3;
	protected static int QTY_DELIVERED = 4;

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
		whereClause.append("UPPER(").append(I_M_Product.COLUMNNAME_UPC)
				.append(")=?");
		return new Query(Env.getCtx(), I_M_Product.Table_Name,
				whereClause.toString(), null).setClient_ID()
				.setParameters(barCode.toUpperCase()).first();
	}

	public MProduct getProductByValueC(String barCode) {
		StringBuilder whereClause = new StringBuilder();
		whereClause.append("UPPER(").append(I_M_Product.COLUMNNAME_Value)
				.append(")=?");
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
			MAttributeSet attributeUse = null;
			if (product.getM_AttributeSet_ID() > 0) {
				attributeUse = (MAttributeSet) product.getM_AttributeSet();
				isLot = attributeUse.isLot();
			} else
				isLot = false;
		}

		return isLot;
	}

	public boolean isSerNo() {
		if (isSerNo == null) {
			MAttributeSet attributeUse = null;
			if (product.getM_AttributeSet_ID() > 0) {
				attributeUse = (MAttributeSet) product.getM_AttributeSet();
				isSerNo = attributeUse.isSerNo();
				isSerNoMandotory = attributeUse.isSerNoMandatory();
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
		this.lotNo = null;
		this.serNo = null;
		this.isLot = null;
		this.isSerNo = null;
		if (product == null)
		{	
			this.product =null;
			return;
		}
		
		this.product = product;
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

	protected Vector<Vector<Object>> getDataModel() {
		Vector<Vector<Object>> dataModel = new Vector<Vector<Object>>();
		for (Vector<Object> line : getData().values()) {
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
		List<MWMInOutBoundLine> InOutBoundLines =  new Query(Env.getCtx(), I_WM_InOutBoundLine.Table_Name, "WM_InOutBound_ID=?" , null)
		.setClient_ID()
		.setParameters(getRecord_ID())
		.list();

		source = new LinkedHashMap<Integer, ArrayList<Object>>();
		for (MWMInOutBoundLine line : InOutBoundLines)
		{	
				ArrayList<Object> parameters = new ArrayList<Object>();
				parameters.add(ID, line.getC_OrderLine_ID());
				parameters.add(PRODUCT_ID, line.getM_Product_ID());
				parameters.add(QTY_ORDERED, line.getPickedQty());
				parameters.add(QTY_PICKED, line.getPickedQty());
				parameters.add(QTY_DELIVERED , BigDecimal.ZERO);
				source.put(line.get_ID(), parameters);
		}		
		return source;
	}

	protected ArrayList<Object> checkProduct(MProduct product, BigDecimal qty,
			boolean reset) {

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

			if (qtyPicked != null && qtyPicked.signum() >= 0) {
				
					if(qtyPicked.compareTo(qty) <= 0 )
					{
						values.set(QTY_DELIVERED, qtyPicked);
						qtyPicked = Env.ZERO;
					}
					else
					{
						if (reset)
						{	
							values.set(QTY_DELIVERED, qty);
							qtyPicked = qtyOrdered.subtract(qty);
						}
						else
						{	
							values.set(QTY_DELIVERED, qty);
							qtyPicked = qtyPicked.subtract(qty);
						}	
	
						if (qtyPicked.signum() < 0)
							return null;						
					}

					values.set(QTY_PICKED, qtyPicked);
					source.put(entry.getKey(), values);
					return values;
			}
		}
		return null;
	}

	protected int getM_Locater_ID() {
		return locatorId;
	}

	protected int getDefaultWarehouseId() {
		int M_Locator_ID = Env.getContextAsInt(Env.getCtx(), getWindowNo(), "M_Locator_ID");
		int M_Warehoue_ID = DB.getSQLValue(null, "SELECT M_Warehouse_ID FROM M_Locator WHERE M_Locator_ID = ? " , M_Locator_ID);
		return M_Warehoue_ID;
	}
	
	protected int getRecord_ID() {
		if (recordId <= 0)
			recordId = getProcessInfo().getRecord_ID();

		return recordId;
	}

	protected MTable getTable() {
		if (table == null) {
			table = MTable.get(Env.getCtx(), getProcessInfo()
					.getTable_ID());
		}
		return table;
	}

	protected MTable getTableLine() {
		return tableLine;
	}

	public void createLines() {

		for (Vector<Object> line : getData().values()) {
			int id = (Integer) line.get(5);
			MWMInOutBoundLine ioBoundLine = new MWMInOutBoundLine(Env.getCtx(), id , null );
			for (MWMInOutBoundLineMA ioBoundLineMA : getLinesMA(ioBoundLine))
			{
				ioBoundLineMA.deleteEx(true);
			}
		}
		
		for (Vector<Object> line : getData().values()) {
			String value = (String) line.get(0);
			String lotNo = (String) line.get(2);
			String serNo = (String) line.get(3);
			Boolean isASI = (lotNo != null && lotNo.length() > 0)
					|| (serNo != null && serNo.length() > 0) ? true : false;
			BigDecimal qty = (BigDecimal) line.get(4);
			int id = (Integer) line.get(5);
			Integer referenceId = (Integer) line.get(6);

			MWMInOutBoundLine boundLine = null;
			MAttributeSetInstance asi = null;

			MProduct product = new Query(Env.getCtx(), I_M_Product.Table_Name,
					"Value = ? ", null).setClient_ID().setParameters(value)
					.firstOnly();
			String desc = null;

			if (referenceId > 0)
				boundLine = getMWMInOutBoundLine(referenceId, product.get_ID());

			if (product.getM_AttributeSet_ID() > 0 && isASI) {

				asi = getASI(product, lotNo, serNo);

				if (asi == null)
					asi = getAttributeSetInstance(product, lotNo, serNo,
							getM_Locater_ID(), null);
			}

			MWMInOutBoundLineMA boundLineMA = new MWMInOutBoundLineMA(
					Env.getCtx(), 0, null);
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
			boundLineMA.setWM_InOutBoundLine_ID(boundLine.get_ID());
			boundLineMA.setM_AttributeSetInstance_ID(asi == null ? 0 : asi
					.getM_AttributeSetInstance_ID());
			boundLineMA.setMovementQty(qty);
			boundLineMA.saveEx();
			boundLine.setMovementQty(boundLine.getMovementQty().add(qty));
			boundLine.saveEx();
		}
	}

	private MWMInOutBoundLine getMWMInOutBoundLine(int id, int M_Product_ID) {
		StringBuilder whereClause = new StringBuilder();
		whereClause.append(MWMInOutBoundLine.COLUMNNAME_WM_InOutBound_ID)
				.append("=? AND ");
		whereClause.append(MWMInOutBoundLine.COLUMNNAME_C_OrderLine_ID).append(
				"=? AND ");
		whereClause.append(MWMInOutBoundLine.COLUMNNAME_M_Product_ID).append(
				"=? ");
		return new Query(Env.getCtx(), MWMInOutBoundLine.Table_Name,
				whereClause.toString(), null).setClient_ID()
				.setParameters(getRecord_ID(), id, M_Product_ID).firstOnly();
	}
	
	private MAttributeSetInstance getASI(MProduct product,
	String lotNo, String serNo ) {
	StringBuilder whereClause = new StringBuilder();
	ArrayList<Object> parameters = new ArrayList<Object>();	
	whereClause.append(I_M_AttributeSetInstance.COLUMNNAME_M_AttributeSet_ID).append("=? ");
	parameters.add(product.getM_AttributeSet_ID());
	
	if (lotNo != null && lotNo.length() > 0) {
		whereClause.append(" AND ")
				.append("UPPER(").append(I_M_AttributeSetInstance.COLUMNNAME_Lot).append(")=? ");
		parameters.add(lotNo.toUpperCase().trim());
	}
	
	if (serNo != null && serNo.length() > 0) {
		whereClause.append(" AND ")
				.append("UPPER(").append(MAttributeSetInstance.COLUMNNAME_SerNo)
				.append(")=? ");
		parameters.add(serNo.toUpperCase().trim());
	}
	
	return new Query(Env.getCtx(), I_M_AttributeSetInstance.Table_Name,
			whereClause.toString(), product.get_TrxName()).setClient_ID()
			.setParameters(parameters).first();
	}

	private MAttributeSetInstance getAttributeSetInstance(MProduct product,
			String lotNo, String serNo, int M_Locator_ID, String trxName) {
		StringBuilder whereClause = new StringBuilder(
				"EXISTS (SELECT 1 FROM M_Storage s WHERE s.M_Product_ID=? AND s.M_Locator_ID=? AND s.M_AttributeSetInstance_ID=M_AttributeSetInstance.M_AttributeSetInstance_ID)");

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
		for (MWMInOutBoundLine inBoundline : getInOutBoundLines()) {
			inBoundline.setMovementQty(Env.ZERO);
			inBoundline.saveEx();
		}
		
		for (MWMInOutBoundLineMA inBoundlineMA : getDocumentLines()) {

			MProduct product = null;
			MAttributeSetInstance asi = null;
			String lotNo = null;
			String serNo = null;
			Integer referenceId = null;
			BigDecimal qty = null;
			boolean reset = false;

			MWMInOutBoundLine inBoundline = (MWMInOutBoundLine) inBoundlineMA
					.getWM_InOutBoundLine();
			// inBoundline.setMovementQty(Env.ZERO);
			// inBoundline.saveEx();
			int M_Product_ID = inBoundline.getM_Product_ID();
			int M_AttributeSetInstance_ID = inBoundlineMA
					.getM_AttributeSetInstance_ID();
			if (M_Product_ID > 0)
				product = MProduct.get(Env.getCtx(), M_Product_ID);
			else
				continue;

			if (M_AttributeSetInstance_ID > 0) {
				asi = new MAttributeSetInstance(Env.getCtx(),
						M_AttributeSetInstance_ID, null);
				lotNo = asi.getLot();
				serNo = asi.getSerNo();
			} else {
				M_AttributeSetInstance_ID = 0;
				reset = true;
			}

			//referenceId = inBoundline.getC_OrderLine_ID();
			qty = inBoundlineMA.getMovementQty();
			ArrayList<Object> values = null;
			while (qty.signum() != 0)
			{
				if (getSource() != null && source.size() > 0) {
					values = checkProduct(product, qty, reset);
	
					if (values == null)
						throw new AdempiereException("@M_Product_ID@ ; "
								+ product.getName() + " @InValid@");
				}
				
				referenceId = (Integer) values.get(ID);
				String key = product.getValue();
				if (lotNo != null && lotNo.length() > 0)
					key = key + lotNo;
				if (serNo != null && serNo.length() > 0)
					key = key + serNo;
				if(serNo == null)
					key = key + "#" +  referenceId;
				
				Vector<Object> line = new Vector<Object>(6);
				line.add(product.getValue());
				line.add(product.getName());
				line.add(lotNo);
				line.add(serNo);
				BigDecimal qtyDelivered = (BigDecimal) values.get(QTY_DELIVERED);
				qty = qty.subtract(qtyDelivered);
				line.add(qtyDelivered);
				line.add(inBoundline.get_ID());
				line.add(referenceId != null ? referenceId.intValue() : 0);
				data.put(key, line);
			}
		}
	}

	private List<MWMInOutBoundLineMA> getDocumentLines() {
		StringBuilder whereClause = new StringBuilder();
			whereClause
				.append(" EXISTS (SELECT 1 FROM WM_InOutBoundLine il ")
				.append(" INNER JOIN WM_InOutBoundLineMA ma ON (ma.WM_InOutBoundLine_ID=il.WM_InOutBoundLine_ID) ")
				.append(" WHERE il.WM_InOutBoundLine_ID=WM_InOutBoundLineMA.WM_InOutBoundLine_ID AND il.WM_InOutBound_ID=? ORDER BY ma.WM_InOutBoundLine_ID,  ma.WM_InOutBoundLineMA_ID) ");
		return new Query(Env.getCtx(), MWMInOutBoundLineMA.Table_Name,
				whereClause.toString(), null).setClient_ID()
				.setParameters(getRecord_ID())
				.setOrderBy(MWMInOutBoundLineMA.COLUMNNAME_WM_InOutBoundLine_ID)
				.list();
	}
	
	private List<MWMInOutBoundLine> getInOutBoundLines() {
		StringBuilder whereClause = new StringBuilder();
			whereClause
				.append(MWMInOutBoundLine.COLUMNNAME_WM_InOutBound_ID).append("=?");
		return new Query(Env.getCtx(), MWMInOutBoundLine.Table_Name,
				whereClause.toString(), null).setClient_ID()
				.setParameters(getRecord_ID()).list();
	}
	
	public List<MWMInOutBoundLineMA> getLinesMA(MWMInOutBoundLine inOutBoundLine) {
		StringBuilder whereClause = new StringBuilder(MWMInOutBoundLine.COLUMNNAME_WM_InOutBoundLine_ID).append("=?");
		return new Query(inOutBoundLine.getCtx(), MWMInOutBoundLineMA.Table_Name, whereClause.toString(), inOutBoundLine.get_TrxName())
		.setClient_ID()
		.setParameters(inOutBoundLine.getWM_InOutBoundLine_ID())
		.list();
	}

	public abstract ProcessInfo getProcessInfo();

	public abstract int getWindowNo();
}
