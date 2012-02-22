/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
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
 * Copyright (C) 2003-2008 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.engine.IDocumentLine;

/**
 * Production Line
 * @author victor.perez@e-evolution.com,www.e-evolution.com
 */
public class MProductionLine extends X_M_ProductionLine implements
		IDocumentLine {

	/**
	 * get true if Production Line is Parent Product
	 * @return true
	 */
	public boolean isParent()
	{
	 final StringBuffer whereClause = new StringBuffer();
		whereClause.append(X_M_ProductionLine.COLUMNNAME_M_ProductionLine_ID);
		whereClause.append(" IN (SELECT M_ProductionLine_ID FROM M_ProductionLine pl INNER JOIN M_ProductionPlan pp ON (pp.M_ProductionPlan_ID=pl.M_ProductionPlan_ID)");
		whereClause.append(" WHERE pl.M_ProductionPlan_ID=? AND ");
		whereClause.append(" pp.M_Product_ID = pl.M_Product_ID )");

	 return new Query(getCtx(), X_M_ProductionLine.Table_Name, whereClause.toString(),get_TrxName())
		.setClient_ID()
		.setParameters(getM_ProductionPlan_ID())
		.match();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -7317898222308270276L;

	/** Standard Constructor */
	public MProductionLine(Properties ctx, int M_ProductionLine_ID,
			String trxName) {
		super(ctx, M_ProductionLine_ID, trxName);
		if (M_ProductionLine_ID == 0) {
		}
	}

	/** Load Constructor */
	public MProductionLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	@Override
	public int getC_DocType_ID() {
		MDocType[] docs = MDocType.getOfDocBaseType(getCtx(),
				MDocType.DOCBASETYPE_MaterialProduction);
		if (docs != null && docs.length > 0)
			return docs[0].getC_DocType_ID();

		return 0;
	}

	@Override
	public Timestamp getDateAcct() {
		return getM_ProductionPlan().getM_Production().getMovementDate();
	}

	@Override
	public int getM_AttributeSetInstanceTo_ID() {
		return getM_AttributeSetInstance_ID();
	}

	@Override
	public int getM_LocatorTo_ID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BigDecimal getPriceActual() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDocumentLine getReversalDocumentLine() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getReversalLine_ID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isSOTrx() {
		return false;
	}
}