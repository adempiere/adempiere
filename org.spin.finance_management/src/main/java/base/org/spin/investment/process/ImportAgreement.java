/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.										*
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/

package org.spin.investment.process;

import java.math.BigDecimal;
import java.util.List;

import org.adempiere.model.ImportValidator;
import org.adempiere.process.ImportProcess;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.Util;
import org.spin.investment.model.I_I_FM_Agreement;
import org.spin.investment.model.MFMAccount;
import org.spin.investment.model.MFMAgreement;
import org.spin.investment.model.X_I_FM_Agreement;

/**
 * Import process for Agreement
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 *      <li> FR [ 1644 ] Import Loan Movements
 *		@see https://github.com/adempiere/adempiere/issues/1644
 */
public class ImportAgreement extends ImportAgreementAbstract implements ImportProcess {

	@Override
	protected String doIt() throws Exception {
		StringBuffer sql = null;
		int no = 0;
		String clientCheck = getWhereClause();
		//	Delete Old
		if(isDeleteOldImported()) {
			sql = new StringBuffer ("DELETE I_FM_Agreement "
					+ "WHERE I_IsImported='Y'").append(clientCheck);
			no = DB.executeUpdateEx(sql.toString(), get_TrxName());
			log.fine("Delete Old Impored =" + no);
		}
		
		//	Set Client, Org, IsActive, Created/Updated
		sql = new StringBuffer ("UPDATE I_FM_Agreement "
				+ "SET AD_Client_ID = COALESCE (AD_Client_ID, ").append(getAD_Client_ID()).append("),"
						+ " AD_Org_ID = COALESCE (AD_Org_ID, 0),"
						+ " IsActive = COALESCE (IsActive, 'Y'),"
						+ " Created = COALESCE (Created, SysDate),"
						+ " CreatedBy = COALESCE (CreatedBy, 0),"
						+ " Updated = COALESCE (Updated, SysDate),"
						+ " UpdatedBy = COALESCE (UpdatedBy, 0),"
						+ " I_ErrorMsg = ' ',"
						+ " I_IsImported = 'N' "
						+ "WHERE I_IsImported<>'Y' OR I_IsImported IS NULL");
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		log.fine("Reset=" + no);
		
		ModelValidationEngine.get().fireImportValidate(this, null, null, ImportValidator.TIMING_BEFORE_VALIDATE);
		
		//	Document Type
		sql = new StringBuffer ("UPDATE I_FM_Agreement o "	//	Document Type Name
			  + "SET C_DocType_ID=(SELECT C_DocType_ID FROM C_DocType d WHERE d.Name=o.DocTypeName"
			  + " AND d.DocBaseType='FMA' AND o.AD_Client_ID=d.AD_Client_ID) "
			  + "WHERE C_DocType_ID IS NULL AND IsSOTrx = I_FM_Agreement.IsSOTrx AND DocTypeName IS NOT NULL AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Set DocType=" + no);
		//	Error Document Type
		sql = new StringBuffer ("UPDATE I_FM_Agreement "	//	Error Invalid Doc Type Name
			  + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid DocTypeName, ' "
			  + "WHERE C_DocType_ID IS NULL AND DocTypeName IS NOT NULL"
			  + " AND I_IsImported<>'Y'").append (clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0)
			log.warning ("Invalid DocTypeName=" + no);
		
		//	Agreement Type
		sql = new StringBuffer ("UPDATE I_FM_Agreement o "	//	Document Type Name
			  + "SET FM_AgreementType_ID=(SELECT FM_AgreementType_ID FROM FM_AgreementType d WHERE d.Value=o.AgreementTypeValue"
			  + " AND o.AD_Client_ID=d.AD_Client_ID) "
			  + "WHERE FM_AgreementType_ID IS NULL AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Set AgreementType=" + no);
		//	Error Agreement Type
		sql = new StringBuffer ("UPDATE I_FM_Agreement "	//	Error Invalid Doc Type Name
			  + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid AgreementTypeValue, ' "
			  + "WHERE FM_AgreementType_ID IS NULL AND AgreementTypeValue IS NOT NULL"
			  + " AND I_IsImported<>'Y'").append (clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0)
			log.warning ("Invalid AgreementTypeValue=" + no);
		
		//	BP from Value
		sql = new StringBuffer ("UPDATE I_FM_Agreement o "
			  + "SET C_BPartner_ID=(SELECT MAX(C_BPartner_ID) FROM C_BPartner bp"
			  + " WHERE o.BPartnerValue=bp.Value AND o.AD_Client_ID=bp.AD_Client_ID) "
			  + "WHERE C_BPartner_ID IS NULL AND BPartnerValue IS NOT NULL"
			  + " AND I_IsImported<>'Y'").append (clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Set BP from Value=" + no);
		//	Error Business Partner
		sql = new StringBuffer ("UPDATE I_FM_Agreement "	//	Error Invalid BPartnerValue
			  + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid BPartnerValue, ' "
			  + "WHERE C_BPartner_ID IS NULL AND BPartnerValue IS NOT NULL"
			  + " AND I_IsImported<>'Y'").append (clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0)
			log.warning ("Invalid BPartnerValue=" + no);
		
		//	Financial Product from Value
		sql = new StringBuffer ("UPDATE I_FM_Agreement o "
			  + "SET FM_Product_ID=(SELECT MAX(FM_Product_ID) FROM FM_Product bp"
			  + " WHERE o.FinancialProductValue=bp.Value AND o.AD_Client_ID=bp.AD_Client_ID) "
			  + "WHERE FM_Product_ID IS NULL AND FinancialProductValue IS NOT NULL"
			  + " AND I_IsImported<>'Y'").append (clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Set BP from Value=" + no);
		//	Error Financial Product
		sql = new StringBuffer ("UPDATE I_FM_Agreement "	//	Error Invalid FinancialProductValue
			  + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid FinancialProductValue, ' "
			  + "WHERE FM_Product_ID IS NULL AND FinancialProductValue IS NOT NULL"
			  + " AND I_IsImported<>'Y'").append (clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0)
			log.warning ("Invalid FinancialProductValue=" + no);
		
		//	Set Currency
		sql = new StringBuffer ("UPDATE I_FM_Agreement i "
			+ "SET C_Currency_ID=(SELECT C_Currency_ID FROM C_Currency c"
			+ " WHERE i.ISO_Code=c.ISO_Code AND c.AD_Client_ID IN (0,i.AD_Client_ID)) "
			+ "WHERE C_Currency_ID IS NULL"
			+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0)
			log.info("Set Currency=" + no);
		//	Error Currency
		sql = new StringBuffer ("UPDATE I_FM_Agreement "
				+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=No Currency,' "
				+ "WHERE C_Currency_ID IS NULL "
				+ "AND I_IsImported<>'E' "
				+ " AND I_IsImported<>'Y'").append(clientCheck);
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			if (no != 0)
				log.warning("No Currency=" + no);
		
		ModelValidationEngine.get().fireImportValidate(this, null, null, ImportValidator.TIMING_AFTER_VALIDATE);
		
		//	Validate Only
		if(isValidateOnly()) {
			commitEx();
			return "Validated";
		}
		//	Import
		int noInsert = importAgreement();
		//	Set Error to indicator to not imported
		sql = new StringBuffer ("UPDATE I_FM_Agreement "
			+ "SET I_IsImported='N', Updated=SysDate "
			+ "WHERE I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		addLog (0, null, new BigDecimal (no), "@Errors@");
		//
		addLog (0, null, new BigDecimal (noInsert), "@FM_Agreement_ID@: @Inserted@");
		return "#" + noInsert;
	}
	
	/**
	 * Import Agreement
	 * return int
	 */
	private int importAgreement() {
		int noInsert = 0;
		//	Get List
		List<X_I_FM_Agreement> listForImport = new Query(getCtx(), getImportTableName(), "I_IsImported='N'", get_TrxName())
			//	Index
			.setOrderBy("C_BPartner_ID, C_DocType_ID, FM_AgreementType_ID, DocumentNo, AccountNo")
			.<X_I_FM_Agreement>list();
		//	Old Temp Values
		int oldBPartnerId = 0;
		int oldDocTypeId = 0;
		int oldAgreementId = 0;
		String oldDocumentNo = "";
		String oldAccountNo = "";
		MFMAgreement agreement = null;
		//	Import Agreement
		for(X_I_FM_Agreement agreementImport : listForImport) {
			String cmpDocumentNo = agreementImport.getDocumentNo();
			String cmpAccountNo = agreementImport.getAccountNo();
			if (cmpDocumentNo == null)
				cmpDocumentNo = "";
			//	For Account
			if (cmpAccountNo == null)
				cmpAccountNo = "";
			//	New Agreement
			if (oldBPartnerId != agreementImport.getC_BPartner_ID() 
				|| oldDocTypeId != agreementImport.getC_DocType_ID()
				|| oldAgreementId != agreementImport.getFM_AgreementType_ID() 
				|| !oldDocumentNo.equals(cmpDocumentNo)
				|| !oldAccountNo.equals(cmpAccountNo)) {
				//	Complete Old
				completeAgreement(agreement);
				//	
				agreement = createAgreement(agreementImport);
				agreement.saveEx();
				//	Set old values
				oldBPartnerId = agreementImport.getC_BPartner_ID();
				oldDocTypeId = agreementImport.getC_DocType_ID();
				oldAgreementId = agreementImport.getFM_AgreementType_ID();
				oldDocumentNo = agreementImport.getDocumentNo();
				oldAccountNo = agreementImport.getAccountNo();
				//	
				if (oldDocumentNo == null)
					oldDocumentNo = "";
				//	For Account
				if (oldAccountNo == null)
					oldAccountNo = "";
				//	
				if(!Util.isEmpty(agreementImport.getAccountNo())) {
					List<MFMAccount> accounts = agreement.getAccounts();
					if(accounts == null
							|| accounts.size() == 0) {
						//	Create Account
						MFMAccount account = new MFMAccount(agreement);
						account.setAccountNo(agreementImport.getAccountNo());
						account.saveEx();
						//	Set Reference
						agreementImport.setFM_Account_ID(account.getFM_Account_ID());
					}
				}
				//	Set Reference
				agreementImport.setFM_Agreement_ID(agreement.getFM_Agreement_ID());
				agreementImport.setI_IsImported("Y");
				agreementImport.setProcessed(true);
				agreementImport.saveEx();
				//	noInsert
				noInsert++;
			}
		}
		//	Complete Current
		completeAgreement(agreement);
		//	Default Return
		return noInsert;
	}
	
	/**
	 * Complete Agreement
	 * @param agreement
	 */
	private void completeAgreement(MFMAgreement agreement) {
		if(agreement != null) {
			agreement.setDocAction(MFMAgreement.DOCACTION_Complete);
			agreement.processIt(MFMAgreement.DOCACTION_Complete);
			agreement.saveEx();
		}
	}
	
	/**
	 * Create new Agreement
	 * @param agreementImport
	 * @return
	 */
	private MFMAgreement createAgreement(X_I_FM_Agreement agreementImport) {
		MFMAgreement agreement = new MFMAgreement(agreementImport);
		//	
		return agreement;
	}

	@Override
	public String getImportTableName() {
		return I_I_FM_Agreement.Table_Name;
	}

	@Override
	public String getWhereClause() {
		return " AND AD_Client_ID=" + getAD_Client_ID();
	}
}