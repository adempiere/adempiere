/**************************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                               *
 * This program is free software; you can redistribute it and/or modify it    		  *
 * under the terms version 2 or later of the GNU General Public License as published  *
 * by the Free Software Foundation. This program is distributed in the hope           *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied         *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.                   *
 * See the GNU General Public License for more details.                               *
 * You should have received a copy of the GNU General Public License along            *
 * with this program; if not, printLine to the Free Software Foundation, Inc.,        *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                             *
 * For the text or an alternative of this public license, you may reach us            *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, S.A. All Rights Reserved.  *
 * Contributor: Yamel Senih ysenih@erpya.com                                          *
 * See: www.erpya.com                                                                 *
 *************************************************************************************/
package org.spin.util.impexp;

import java.util.Locale;

/**
 * Generic Bank Transaction implementation
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 * <li> FR [ 1700 ] Add Quicken Interchange Format support
 * @see https://github.com/adempiere/adempiere/issues/1700
 */
public class GenericQIFBankTransaction extends QIFBankTransaction {

	public GenericQIFBankTransaction() {
		setFileType(HEAD_TYPE_Bank);
	}
	
	@Override
	public Locale getLocale() {
		return Locale.US;
	}

	@Override
	public String getDatePattern() {
		return "dd/MM/yyyy";
	}

	@Override
	public String getDecimalSeparator() {
		return ".";
	}

	@Override
	protected String processValue(String value) {
		return value.replaceAll("[	+^:&áàäéèëíìïóòöúùñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ$]", "");
	}

	@Override
	public String getDecimalPattern() {
		return "###,###,###,###,###,###,###.########";
	}

	@Override
	public boolean isCompleteData() {
		return true;
	}

	@Override
	public String getPayeeName() {
		return null;
	}

	@Override
	public String getPayeeDescription() {
		// TODO Auto-generated method stub
		return null;
	}	
}
