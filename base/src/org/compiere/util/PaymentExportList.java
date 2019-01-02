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
import org.compiere.model.MBank;
import org.compiere.model.MBankAccount;
import org.compiere.model.MPaySelection;
import org.compiere.model.MPaySelectionCheck;
import org.compiere.model.MPayment;
import org.compiere.model.MPaymentBatch;

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
	/**	File	*/
	private File file = null;
	
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
			this.file = file;
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
	 * Open File from Payment Selection
	 * @param file
	 * @param checks
	 */
	public void openFileWriter(File file, List<MPaySelectionCheck> checks) {
		MPaySelectionCheck check = checks.get(0);
		MPaySelection paymentSelection = check.getParent();
		MBankAccount bankAccount = MBankAccount.get(Env.getCtx(), paymentSelection.getC_BankAccount_ID());
		MBank bank = MBank.get(Env.getCtx(), bankAccount.getC_Bank_ID());
		String fileName = getFileName(file, bank.getName(), paymentSelection.getDocumentNo());
		openFileWriter(fileName);
	}
	
	/**
	 * Open File from Payments
	 * @param file
	 * @param bankAccount
	 * @param payments
	 * @param suffix
	 */
	public void openFileWriter(File file, MBankAccount bankAccount, List<MPayment> payments, String suffix) {
		if(Util.isEmpty(suffix)) {
			suffix = "";
		}
		MPayment firstPayment = payments.get(0);
		MPaymentBatch paymentBatch = (MPaymentBatch) firstPayment.getC_PaymentBatch();
		MBank bank = MBank.get(Env.getCtx(), bankAccount.getC_Bank_ID());
		String fileName = getFileName(file, bank.getName(), paymentBatch.getDocumentNo() + suffix);
		openFileWriter(fileName);
	}
	
	/**
	 * Open file with new name and delete if exist
	 * @param file
	 * @param bankName
	 * @param documentNo
	 */
	public void openFileWriter(File file, String bankName, String documentNo) {
		String fileName = getFileName(file, bankName, documentNo);
		openFileWriter(fileName);
	}
	
	/**
	 * Open File with a new name
	 * @param file
	 * @param newName
	 */
	public void openFileWriter(String newName) {
		File newFile = new File(newName);
		deleteIfExist(newFile);
		openFileWriter(newFile);
	}
	
	/**
	 * Get Parent File Path
	 * @param file
	 * @return
	 */
	public String getParentFileName(File file) {
		StringBuffer pathName = new StringBuffer();
		if(file.isFile() || !file.exists()) {
			pathName.append(file.getParent());
		} else {
			pathName.append(file.getAbsolutePath());
		}
		//	Return
		return pathName.toString();
	}
	
	/**
	 * Get File Name for document
	 * @param file
	 * @param bankName
	 * @param documentNo
	 * @return
	 */
	public String getFileName(File file, String bankName, String documentNo) {
		if(file == null) {
			return null;
		}
		//	Extension
		String extension = ".txt";
		//	Set new File Name
		StringBuffer pathName = new StringBuffer(getParentFileName(file));
		//	Add Separator
		pathName.append(File.separator)
				.append(bankName)
				.append("_")
				.append(documentNo)
				.append(extension);
		//	Return
		return pathName.toString().replace(" ", "_");
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
	
	public void setExportedPayments(int exportedPayments) {
		this.exportedPayments = exportedPayments;
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
		String format = "%1$";
		if(!left) {
			format = "%1$-";
		}
		return String.format(format + length + "s", text).replace(" ", padd);
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
	 * Left padding optional fixed length
	 * @param text
	 * @param length
	 * @param padding
	 * @param isFixedLength
	 * @return
	 */
	public String leftPadding(String text, int length, String padding, boolean isFixedLength) {
		return leftPadding(text, length, padding, isFixedLength, false, null);
	}
	
	/**
	 * Right padding optional fixed length
	 * @param text
	 * @param length
	 * @param padding
	 * @param isFixedLength
	 * @return
	 */
	public String rightPadding(String text, int length, String padding, boolean isFixedLength) {
		return rightPadding(text, length, padding, isFixedLength, false, null);
	}
	
	/**
	 * Left padding, it also cut text if it is necessary
	 * @param text
	 * @param length
	 * @param padding
	 * @param isFixedLength
	 * @param isMandatory
	 * @param mandatoryMessage
	 * @return
	 */
	public String leftPadding(String text, int length, String padding, boolean isFixedLength, boolean isMandatory, String mandatoryMessage) {
		return addPadding(text, length, padding, isFixedLength, isMandatory, mandatoryMessage, true);
	}
	
	/**
	 * Right padding, it also cut text if it is necessary
	 * @param text
	 * @param length
	 * @param padding
	 * @param isFixedLength
	 * @param isMandatory
	 * @param mandatoryMessage
	 * @return
	 */
	public String rightPadding(String text, int length, String padding, boolean isFixedLength, boolean isMandatory, String mandatoryMessage) {
		return addPadding(text, length, padding, isFixedLength, isMandatory, mandatoryMessage, false);
	}
	
	/**
	 * Add Padding, for using internal
	 * @param text
	 * @param length
	 * @param padding
	 * @param isFixedLength
	 * @param isMandatory
	 * @param mandatoryMessage
	 * @param isLeft
	 * @return
	 */
	private String addPadding(String text, int length, String padding, boolean isFixedLength, boolean isMandatory, String mandatoryMessage, boolean isLeft) {
		if(Util.isEmpty(text)) {
			if(isMandatory
					&& !Util.isEmpty(mandatoryMessage)) {
				addError(mandatoryMessage);
				//	
				return null;
			}
			//	Return void text
			text = "";
		}
		String processedText = text;
		//	Process it
		if(isFixedLength) {
			processedText = processedText.substring(0, processedText.length() >= length? length: processedText.length());
		}
		//	For padding 
		if(isLeft) {
			processedText = leftPadding(processedText, length, padding);
		} else {
			processedText = rightPadding(processedText, length, padding);
		}
		//	Return
		return processedText;
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
	
	/**
	 * Get business partner account information as PO
	 * @param payment
	 * @param defaultWhenNull if payment account is null try get a account of bp
	 * @return
	 */
	public MBPBankAccount getBPAccountInfo(MPayment payment, boolean defaultWhenNull) {
		if(payment.getC_BP_BankAccount_ID() != 0) {
			return (MBPBankAccount) payment.getC_BP_BankAccount();
		}
		//	Get any bp account
		if(defaultWhenNull) {
			List<MBPBankAccount> bpAccountList = MBPBankAccount.getByPartner(Env.getCtx(), payment.getC_BPartner_ID());
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
	
	/**
	 * Get File Name of generated TXT
	 * @return
	 */
	public File getFile() {
		return file;
	}
	
}	//	PaymentExport
