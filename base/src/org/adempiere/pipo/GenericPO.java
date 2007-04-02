/*******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution * Copyright (C)
 * 1999-2006 Adempiere, Inc. All Rights Reserved. * This program is free
 * software; you can redistribute it and/or modify it * under the terms version
 * 2 of the GNU General Public License as published * by the Free Software
 * Foundation. This program is distributed in the hope * that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied * warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. * See the GNU General
 * Public License for more details. * You should have received a copy of the GNU
 * General Public License along * with this program; if not, write to the Free
 * Software Foundation, Inc., * 59 Temple Place, Suite 330, Boston, MA
 * 02111-1307 USA. *
 * 
 * Copyright (C) 2004 Marco LOMBARDO. lombardo@mayking.com Contributor(s):
 * __________________________________________
 ******************************************************************************/

// ----------------------------------------------------------------------
// Generic PO.
// Used to insert/update data from a adempieredata.xml file.
package org.adempiere.pipo;

// import for GenericPO
import java.util.*;
import java.sql.*;
import org.compiere.model.*;

public class GenericPO extends PO {

	// private Logger log = Logger.getCLogger(getClass());

	/** Standard Constructor */
	public GenericPO(Properties ctx, int ID) {
		super(ctx, ID, null, null);
	}

	/** Load Constructor */
	public GenericPO(Properties ctx, ResultSet rs) {
		super(ctx, 0, null, rs);
	}

	public GenericPO(Properties ctx, int ID, String trxName) {
		super(ctx, ID, trxName, null);
	}

	public GenericPO(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, 0, trxName, rs);
	}

	private int Table_ID = 0;

	/** Load Meta Data */
	protected POInfo initPO(Properties ctx) {
		Table_ID = Integer.valueOf(ctx.getProperty("adempieredataTable_ID"))
				.intValue();
		// log.info("Table_ID: "+Table_ID);
		POInfo poi = POInfo.getPOInfo(ctx, Table_ID);
		return poi;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer("GenericPO[Table=").append(
				"" + Table_ID + ",ID=").append(get_ID()).append("]");
		return sb.toString();
	}

	public static final int AD_ORGTRX_ID_AD_Reference_ID = 130;

	/**
	 * Set Trx Organization. Performing or initiating organization
	 */
	public void setAD_OrgTrx_ID(int AD_OrgTrx_ID) {
		if (AD_OrgTrx_ID == 0)
			set_Value("AD_OrgTrx_ID", null);
		else
			set_Value("AD_OrgTrx_ID", new Integer(AD_OrgTrx_ID));
	}

	/**
	 * Get Trx Organization. Performing or initiating organization
	 */
	public int getAD_OrgTrx_ID() {
		Integer ii = (Integer) get_Value("AD_OrgTrx_ID");
		if (ii == null)
			return 0;
		return ii.intValue();
	}

	// setValue
	public void setValue(String columnName, Object value) {
		set_Value(columnName, value);
	}

	// setValueNoCheck
	public void setValueNoCheck(String columnName, Object value) {
		set_ValueNoCheck(columnName, value);
	}

	// setValue
	public void setValue(int index, Object value) {
		set_Value(index, value);
	}

	public void copyRS(PO From, PO To) {
		copyValues(From, To);
	}

	@Override
	protected int get_AccessLevel() {
		// TODO Auto-generated method stub
		return 0;
	}

} // GenericPO

