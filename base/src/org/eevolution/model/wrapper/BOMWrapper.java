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

import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.model.PO;
import org.eevolution.model.reasoner.StorageReasoner;

import org.eevolution.model.MPPOrderBOM;
import org.eevolution.model.MPPProductBOM;

/**
 * @author Gunther Hoppe, tranSIT GmbH Ilmenau/Germany
 * @version 1.0, October 14th 2005
 */
public class BOMWrapper extends AbstractPOWrapper {

	final public static String BOM_TYPE_PRODUCT = "productBOM";
	final public static String BOM_TYPE_ORDER = "orderBOM";
	
	public static String tableName(String type) {
		
		if(BOM_TYPE_PRODUCT.equals(type)) {
			
			return MPPProductBOM.Table_Name;
		}
		else if(BOM_TYPE_ORDER.equals(type)) {
			
			//eevolution vpj-cd new version return MPPOrderBOM.Table_Name;
			return MPPOrderBOM.Table_Name;
		}
		
		return "";
	}

	public static String idColumn(String type) {
		
		String value = null;
		if(BOM_TYPE_PRODUCT.equals(type)) {
			
//			eevolution vpj-cd new version value = MPPProductBOM.Table_Name;
			return "PP_Product_BOM";
		}
		else if(BOM_TYPE_ORDER.equals(type)) {
			
//			eevolution vpj-cd new version value = MPPOrderBOM.Table_Name;
			return "PP_Order_BOM";
		}
		
		return value+"_ID";
	}
	
	public BOMWrapper(Properties ctx, int id, String trxName, String type) {

		super(ctx, id, trxName, type);
	}
	
	protected PO receivePO(Properties ctx, int id, String trxName, String type) {

		PO po = null;
		if(BOM_TYPE_PRODUCT.equals(type)) {
			
			po = new MPPProductBOM(ctx, id, trxName);
		}
		else if(BOM_TYPE_ORDER.equals(type)) {
			
			po = new MPPOrderBOM(ctx, id, trxName);
		}
		
		return po;
	}
	
	public String getName() {
		
		String name = null;
		if(get() instanceof MPPProductBOM) {
			
			name = ((MPPProductBOM)get()).getName();
		}
		else if(get() instanceof MPPOrderBOM) {
			
			name = ((MPPOrderBOM)get()).getName();
		}
		
		return name;
	}
	
	public String getDescription() {
		
		String name = null;
		if(get() instanceof MPPProductBOM) {
			
			name = ((MPPProductBOM)get()).getDescription();
		}
		else if(get() instanceof MPPOrderBOM) {
			
			name = ((MPPOrderBOM)get()).getDescription();
		}
		
		return name;
	}

	public String getRevision() {
		
		String name = null;
		if(get() instanceof MPPProductBOM) {
			
			name = ((MPPProductBOM)get()).getRevision();
		}
		else if(get() instanceof MPPOrderBOM) {
			
			name = ((MPPOrderBOM)get()).getRevision();
		}
		
		return name;
	}
	
	public String getDocumentNo() {
		
		String value = null;
		if(get() instanceof MPPProductBOM) {
			
			value = ((MPPProductBOM)get()).getDocumentNo();
		}
		else if(get() instanceof MPPOrderBOM) {
			
			value = ((MPPOrderBOM)get()).getDocumentNo();
		}
		
		return value;
	}
	
	public int getM_Product_ID() {

		int id = 0;
		if(get() instanceof MPPProductBOM) {
			
			id = ((MPPProductBOM)get()).getM_Product_ID();
		}
		else if(get() instanceof MPPOrderBOM) {
			
			id = ((MPPOrderBOM)get()).getM_Product_ID();
		}
		
		return id;
	}

	public int getM_AttributeSetInstance_ID() {

		int id = 0;
		if(get() instanceof MPPProductBOM) {
			
			id = ((MPPProductBOM)get()).getM_AttributeSetInstance_ID();
		}
		else if(get() instanceof MPPOrderBOM) {
			
			id = ((MPPOrderBOM)get()).getM_AttributeSetInstance_ID();
		}
		
		return id;
	}

	public int getC_UOM_ID() {

		int id = 0;
		if(get() instanceof MPPProductBOM) {
			
			id = ((MPPProductBOM)get()).getC_UOM_ID();
		}
		else if(get() instanceof MPPOrderBOM) {
			
			id = ((MPPOrderBOM)get()).getC_UOM_ID();
		}
		
		return id;
	}

	public Timestamp getValidFrom() {

		Timestamp value = null;
		if(get() instanceof MPPProductBOM) {
			
			value = ((MPPProductBOM)get()).getValidFrom();
		}
		else if(get() instanceof MPPOrderBOM) {
			
			value = ((MPPOrderBOM)get()).getValidFrom();
		}
		
		return value;
	}

	public Timestamp getValidTo() {

		Timestamp value = null;
		if(get() instanceof MPPProductBOM) {
			
			value = ((MPPProductBOM)get()).getValidTo();
		}
		else if(get() instanceof MPPOrderBOM) {
			
			value = ((MPPOrderBOM)get()).getValidTo();
		}
		
		return value;
	}	
	
	public String getValue() {
		
		String value = null;
		if(get() instanceof MPPProductBOM) {
			
			value = ((MPPProductBOM)get()).getValue();
		}
		else if(get() instanceof MPPOrderBOM) {
			
			value = ((MPPOrderBOM)get()).getValue();
		}
		
		return value;
	}
	
	public String getBOMType() {
		
		String value = null;
		if(get() instanceof MPPProductBOM) {
			
			value = ((MPPProductBOM)get()).getBOMType();
		}
		else if(get() instanceof MPPOrderBOM) {
			
			value = ((MPPOrderBOM)get()).getBOMType();
		}
		
		return value;
	}
	
	public int getPP_Order_ID() {
		
		int id = 0;
		
		if(get() instanceof MPPOrderBOM) {
			
			MPPOrderBOM bom = (MPPOrderBOM)get();
			id = bom.getPP_Order_ID();
		}
		
		return id;
	}
	
	public BOMLineWrapper[] getLines() {
		
		int[] ids = null;

		String type = null;
		if(get() instanceof MPPProductBOM) {
			
			type = BOM_TYPE_PRODUCT;
		}
		else if(get() instanceof MPPOrderBOM) {
			
			type = BOM_TYPE_ORDER;
		}
		
		StorageReasoner mr = new StorageReasoner();
		ids = mr.getPOIDs(BOMLineWrapper.tableName(type), idColumn(type)+" = "+getID(), null);

		BOMLineWrapper[] lines = new BOMLineWrapper[ids.length];
		
		
		for(int i = 0; i < ids.length; i++) {
			
			lines[i] = new BOMLineWrapper(getCtx(), ids[i], null, type);
		}
		return lines;
	}
}
