/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/

package org.eevolution.model.wrapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.model.PO;

import org.eevolution.model.MPPOrderBOMLine;
import org.eevolution.model.MPPProductBOMLine;

/**
 * @author Gunther Hoppe, tranSIT GmbH Ilmenau/Germany
 * @version 1.0, October 14th 2005
 */
public class BOMLineWrapper extends AbstractPOWrapper {

	public static String tableName(String type) {
		
		if(BOMWrapper.BOM_TYPE_PRODUCT.equals(type)) {
			
			return MPPProductBOMLine.Table_Name;
		}
		else if(BOMWrapper.BOM_TYPE_ORDER.equals(type)) {
			
			return MPPOrderBOMLine.Table_Name;
		}
		
		return "";
	}
	
	public static String idColumn(String type) {
		
		return tableName(type)+"_ID";
	}
	
	public BOMLineWrapper(Properties ctx, int id, String trxName, String type) {

		super(ctx, id, trxName, type);
	}
	
	protected PO receivePO(Properties ctx, int id, String trxName, String type) {

		PO po = null;
		if(BOMWrapper.BOM_TYPE_PRODUCT.equals(type)) {
			
			po = new MPPProductBOMLine(ctx, id, trxName);
		}
		else if(BOMWrapper.BOM_TYPE_ORDER.equals(type)) {
			
			po = new MPPOrderBOMLine(ctx, id, trxName);
		}
		
		return po;
	}
	
	public String getComponentType() {
		
		String type = null;
		if(get() instanceof MPPProductBOMLine) {
			
			type = ((MPPProductBOMLine)get()).getComponentType();
		}
		else if(get() instanceof MPPOrderBOMLine) {
			
			type = ((MPPOrderBOMLine)get()).getComponentType();
		}
		
		return type;
	}

	public BigDecimal getAssay() {
		
		BigDecimal assay = null;
		if(get() instanceof MPPProductBOMLine) {
			
			assay = ((MPPProductBOMLine)get()).getAssay();
		}
		else if(get() instanceof MPPOrderBOMLine) {
			
			assay = ((MPPOrderBOMLine)get()).getAssay();
		}
		
		return assay;
	}
        
        public int getM_ChangeNotice_ID() {
		
		int M_ChangeNotice_ID = 0;
		if(get() instanceof MPPProductBOMLine) {
			
			M_ChangeNotice_ID = ((MPPProductBOMLine)get()).getM_ChangeNotice_ID();
		}
		else if(get() instanceof MPPOrderBOMLine) {
			
			M_ChangeNotice_ID = ((MPPOrderBOMLine)get()).getM_ChangeNotice_ID();
		}
		
		return M_ChangeNotice_ID;
	}
        
        public String getHelp() {
		
		String Help = null;
		if(get() instanceof MPPProductBOMLine) {
			
			Help = ((MPPProductBOMLine)get()).getHelp();
		}
		else if(get() instanceof MPPOrderBOMLine) {
			
			Help = ((MPPOrderBOMLine)get()).getHelp();
		}
		
		return Help;
	}


	public BigDecimal getQtyBatch() {
		
		BigDecimal qty = null;
		if(get() instanceof MPPProductBOMLine) {
			
			qty = ((MPPProductBOMLine)get()).getQtyBatch();
		}
		else if(get() instanceof MPPOrderBOMLine) {
			
			qty = ((MPPOrderBOMLine)get()).getQtyBatch();
		}
		
		return qty;
	}

	public BigDecimal getForecast() {
		
		BigDecimal fc = null;
		if(get() instanceof MPPProductBOMLine) {
			
			fc = ((MPPProductBOMLine)get()).getForecast();
		}
		else if(get() instanceof MPPOrderBOMLine) {
			
			fc = ((MPPOrderBOMLine)get()).getForecast();
		}
		
		return fc;
	}

	public Integer getLeadTimeOffset() {
		
		Integer offset = null;
		if(get() instanceof MPPProductBOMLine) {
			
			offset = ((MPPProductBOMLine)get()).getLeadTimeOffset();
		}
		else if(get() instanceof MPPOrderBOMLine) {
			
			offset = ((MPPOrderBOMLine)get()).getLeadTimeOffset();
		}
		
		return offset;
	}

	public boolean isQtyPercentage() {
		
		boolean percentage = false;
		if(get() instanceof MPPProductBOMLine) {
			
			percentage = ((MPPProductBOMLine)get()).isQtyPercentage();
		}
		else if(get() instanceof MPPOrderBOMLine) {
			
			percentage = ((MPPOrderBOMLine)get()).isQtyPercentage();
		}
		
		return percentage;
	}

	public boolean isCritical() {
		
		boolean critical = false;
		if(get() instanceof MPPProductBOMLine) {
			
			critical = ((MPPProductBOMLine)get()).isCritical();
		}
		else if(get() instanceof MPPOrderBOMLine) {
			
			critical = ((MPPOrderBOMLine)get()).isCritical();
		}
		
		return critical;
	}
	
	public String getIssueMethod() {
		
		String issue = null;
		if(get() instanceof MPPProductBOMLine) {
			
			issue = ((MPPProductBOMLine)get()).getIssueMethod();
		}
		else if(get() instanceof MPPOrderBOMLine) {
			
			issue = ((MPPOrderBOMLine)get()).getIssueMethod();
		}
		
		return issue;
	}

	public int getLine() {
		
		int line = 0;
		if(get() instanceof MPPProductBOMLine) {
			
			line = ((MPPProductBOMLine)get()).getLine();
		}
		else if(get() instanceof MPPOrderBOMLine) {
			
			line = ((MPPOrderBOMLine)get()).getLine();
		}
		
		return line;
	}
	
	public String getDescription() {
		
		String type = null;
		if(get() instanceof MPPProductBOMLine) {
			
			type = ((MPPProductBOMLine)get()).getDescription();
		}
		else if(get() instanceof MPPOrderBOMLine) {
			
			type = ((MPPOrderBOMLine)get()).getDescription();
		}
		
		return type;
	}
	
	public int getM_Product_ID() {

		int id = 0;
		if(get() instanceof MPPProductBOMLine) {
			
			id = ((MPPProductBOMLine)get()).getM_Product_ID();
		}
		else if(get() instanceof MPPOrderBOMLine) {
			
			id = ((MPPOrderBOMLine)get()).getM_Product_ID();
		}
		
		return id;
	}

	public int getPP_Order_ID() {
		
		int id = 0;
		
		if(get() instanceof MPPOrderBOMLine) {
			
			MPPOrderBOMLine line = (MPPOrderBOMLine)get();
			id = line.getPP_Order_ID();
		}
		
		return id;
	}

	public int getPP_BOM_ID() {

		int id = 0;
		if(get() instanceof MPPProductBOMLine) {
			
			id = ((MPPProductBOMLine)get()).getPP_Product_BOM_ID();
		}
		else if(get() instanceof MPPOrderBOMLine) {
			
			id = ((MPPOrderBOMLine)get()).getPP_Order_BOM_ID();
		}
		
		return id;
	}

	public int getM_AttributeSetInstance_ID() {

		int id = 0;
		if(get() instanceof MPPProductBOMLine) {
			
			id = ((MPPProductBOMLine)get()).getM_AttributeSetInstance_ID();
		}
		else if(get() instanceof MPPOrderBOMLine) {
			
			id = ((MPPOrderBOMLine)get()).getM_AttributeSetInstance_ID();
		}
		
		return id;
	}

	public void setM_AttributeSetInstance_ID(int id) {

		if(get() instanceof MPPProductBOMLine) {
			
			((MPPProductBOMLine)get()).setM_AttributeSetInstance_ID(id);
		}
		else if(get() instanceof MPPOrderBOMLine) {
			
			((MPPOrderBOMLine)get()).setM_AttributeSetInstance_ID(id);
		}
	}	
	
	public void setQtyBOM(BigDecimal qty) {

		if(get() instanceof MPPProductBOMLine) {
			
			((MPPProductBOMLine)get()).setQtyBOM(qty);
		}
		else if(get() instanceof MPPOrderBOMLine) {
			
			((MPPOrderBOMLine)get()).setQtyBOM(qty);
		}
	}

	public BigDecimal getQtyBOM() {

		BigDecimal value = null;
		if(get() instanceof MPPProductBOMLine) {
			
			value = ((MPPProductBOMLine)get()).getQtyBOM();
		}
		else if(get() instanceof MPPOrderBOMLine) {
			
			value = ((MPPOrderBOMLine)get()).getQtyBOM();
		}
		
		return value;
	}

	public int getC_UOM_ID() {

		int value = 0;
		if(get() instanceof MPPProductBOMLine) {
			
			value = ((MPPProductBOMLine)get()).getC_UOM_ID();
		}
		else if(get() instanceof MPPOrderBOMLine) {
			
			value = ((MPPOrderBOMLine)get()).getC_UOM_ID();
		}
		
		return value;
	}	
	
	public int getPo() {

		int value = 0;
		if(get() instanceof MPPProductBOMLine) {
			
			value = ((MPPProductBOMLine)get()).getLine();
		}
		else if(get() instanceof MPPOrderBOMLine) {
			
			value = ((MPPOrderBOMLine)get()).getLine();
		}
		
		return value;
	}
	
	public BigDecimal getScrap() {

		BigDecimal value = new BigDecimal(0);
		if(get() instanceof MPPProductBOMLine) {
			
			value = ((MPPProductBOMLine)get()).getScrap();
		}
		else if(get() instanceof MPPOrderBOMLine) {
			
			value = ((MPPOrderBOMLine)get()).getScrap();
		}
		
		return value;
	}
	
	public Timestamp getValidFrom() {

		Timestamp value = null;
		if(get() instanceof MPPProductBOMLine) {
			
			value = ((MPPProductBOMLine)get()).getValidFrom();
		}
		else if(get() instanceof MPPOrderBOMLine) {
			
			value = ((MPPOrderBOMLine)get()).getValidFrom();
		}
		
		return value;
	}

	public Timestamp getValidTo() {

		Timestamp value = null;
		if(get() instanceof MPPProductBOMLine) {
			
			value = ((MPPProductBOMLine)get()).getValidTo();
		}
		else if(get() instanceof MPPOrderBOMLine) {
			
			value = ((MPPOrderBOMLine)get()).getValidTo();
		}
		
		return value;
	}	
}
