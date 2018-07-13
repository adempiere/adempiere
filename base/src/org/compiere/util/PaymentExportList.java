/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.util;

import org.compiere.model.MBPBankAccount;
import org.compiere.model.MPaySelectionCheck;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 	Abstract class implementation for old compatibility
 * 	@author  victor.perez , victor.perez@e-evolution.com http://www.e-evolution.com
 * 		<li> FR [ 468 ] Bug with old compatibility
 *		@see https://github.com/adempiere/adempiere/issues/468
 *	@author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/1805">
 * 		@see FR [ 1805 ] Add helper method for Payment Export Class</a>
 */
public abstract class PaymentExportList implements PaymentExport {
	/**************************************************************************
	 *  Export to File
	 *  @param checks array of checks
	 *  @param file file to export checks
	 *  @return number of lines
	 */
	@Deprecated
	public int exportToFile(MPaySelectionCheck[] checks, File file, StringBuffer error) {
		return exportToFile( Arrays.asList(checks) , file , error);
	}

	/**************************************************************************
	 *  Export to File
	 *  @param checks list of checks
	 *  @param file file to export checks
	 *  @return number of lines
	 */
	public abstract int exportToFile(List<MPaySelectionCheck> checks, File file, StringBuffer error);
	
	/**	File Writer	*/
	private FileWriter fileWriter = null;
	/**	Exported Payments	*/
	private int exportedPayments = 0;
	/**	Error Message	*/
	private StringBuffer errorMessage = new StringBuffer();
	
	/**
	 * Open File Writer
	 * @param file
	 * @throws IOException
	 */
	public void openFileWriter(File file) {
		if(fileWriter != null) {
			return;
		}
		//	Open it
		try {
			fileWriter = new FileWriter(file);
		} catch (IOException e) {
			addError(e.getLocalizedMessage());
		}
	}
	
	/**
	 * Close File Writer and set to null
	 * @throws IOException
	 */
	public void closeFileWriter() {
		if(fileWriter == null) {
			return;
		}
		//	Open it
		try {
			fileWriter.flush();
			fileWriter.close();
			//	Set to null it
			fileWriter = null;
		} catch (IOException e) {
			addError(e.getLocalizedMessage());
		}
	}
	
	/**
	 * Write a line to file
	 * @param line
	 * @throws IOException 
	 */
	public boolean writeLine(String line) {
		if(fileWriter == null) {
			return false;
		}
		//	Write line
		boolean ok = false;
		try {
			fileWriter.write(line);
			exportedPayments++;
			ok = true;
		} catch (IOException e) {
			addError(e.getLocalizedMessage());
		}
		return ok;
	}
	
	/**
	 * Delete File
	 * @param file
	 */
	public void deleteIfExist(File file) {
		if(file == null) {
			return;
		}
		//	Delete it
		if (file.exists()) {
			file.delete();
		}
	}
	
	/**
	 * Add error to buffer
	 * @param error
	 */
	public void addError(String error) {
		if(errorMessage.length() > 0) {
			errorMessage.append(Env.NL);
		}
		//	Add error
		errorMessage.append(error);
	}
	
	/**
	 * Get Error Message
	 * @return
	 */
	public String getError() {
		return errorMessage.toString();
	}
	
	public int getExportedPayments() {
		return exportedPayments;
	}
	
	/**
	 * Add left padding
	 * @param text
	 * @param length
	 * @param padd
	 * @return
	 */
	public String leftPadding(String text, int length, String padd) {
		return addPadding(text, length, padd, true);
	}
	
	/**
	 * Add right padding
	 * @param text
	 * @param length
	 * @param padd
	 * @return
	 */
	public String rightPadding(String text, int length, String padd) {
		return addPadding(text, length, padd, false);
	}
	
	/**
	 * Add padding, used for add to left and right padding
	 * @param text
	 * @param length
	 * @param padd
	 * @param left
	 * @return
	 */
	private String addPadding(String text, int length, String padd, boolean left) {
		if(text == null
				|| padd ==null) {
			return text;
		}
		//	
		String format = "%1$-";
		if(!left) {
			format = "%1$";
		}
		return String.format(format + length + "s", text).replace(" ","0");
	}
	
	/**
	 * Process or change value for import
	 * you can implement it method for replace special characters
	 * @param value
	 * @return
	 */
	public String processValue(String value) {
		if(Util.isEmpty(value)) {
			return value;
		}
		//	
		return value.replaceAll("[+^:&áàäéèëíìïóòöúùñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ$]", "");
	}
	
	/**
	 * Get business partner account information as PO
	 * @param check
	 * @param defaultWhenNull if payment selection account is null try get a account of bp
	 * @return
	 */
	public MBPBankAccount getBPAccountInfo(MPaySelectionCheck check, boolean defaultWhenNull) {
		if(check.getC_BP_BankAccount_ID() != 0) {
			return (MBPBankAccount) check.getC_BP_BankAccount();
		}
		//	Get any bp account
		if(defaultWhenNull) {
			List<MBPBankAccount> bpAccountList = MBPBankAccount.getByPartner(check.getCtx(), check.getC_BPartner_ID());
			if(bpAccountList == null
					|| bpAccountList.size() == 0) {
				return null;
			}
			//	Get 
			Optional<MBPBankAccount> first = bpAccountList.stream().filter(account -> account.isACH()).findFirst();
			if(first.isPresent()) {
				return first.get();
			} else {
				bpAccountList.get(0);
			}
		}
		//	default
		return null;
	}
	
}	//	PaymentExport
