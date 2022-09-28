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
 * Contributor: Carlos Ruiz - globalqss                                       *
 *****************************************************************************/
package org.compiere.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Optional;
import java.util.logging.Level;

import org.adempiere.core.domains.models.X_C_ProjectMember;
import org.adempiere.core.domains.models.X_C_ProjectMemberType;
import org.adempiere.core.domains.models.X_I_BPartner;
import org.adempiere.exceptions.DBException;
import org.adempiere.model.ImportValidator;
import org.adempiere.process.ImportProcess;
import org.compiere.model.MBPBankAccount;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MContactInterest;
import org.compiere.model.MLocation;
import org.compiere.model.MProject;
import org.compiere.model.MUser;
import org.compiere.model.ModelValidationEngine;
import org.compiere.util.DB;
import org.compiere.util.Msg;

/**
 *	Import BPartners from I_BPartner
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: ImportBPartner.java,v 1.2 2006/07/30 00:51:02 jjanke Exp $
 * 
 * @author Teo Sarca, www.arhipac.ro
 * 			<li>FR [ 2788074 ] ImportBPartner: add IsValidateOnly option
 * 				https://sourceforge.net/tracker/?func=detail&aid=2788074&group_id=176962&atid=879335
 * 			<li>FR [ 2788278 ] Data Import Validator - migrate core processes
 * 				https://sourceforge.net/tracker/?func=detail&aid=2788278&group_id=176962&atid=879335
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/752">
 * 		@see FR [ 752 ] Blood group should be a BP attribute</a>
 * @author Victor Perez, victor.perez@e-evolution.com, e-Evolution www.e-evolution.com
 * 		<a href="https://github.com/adempiere/adempiere/issues/1345">
 * 		@see BF [ 1345 ] Error when try import Business Partner</a>
 * */
public class ImportBPartner extends SvrProcess
implements ImportProcess
{
	/**	Client to be imported to		*/
	private int				m_AD_Client_ID = 0;
	/**	Delete old Imported				*/
	private boolean			m_deleteOldImported = false;
	/**	Only validate, don't import		*/
	private boolean			p_IsValidateOnly = false;

	/** Effective						*/
	private Timestamp		m_DateValue = null;

	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (name.equals("AD_Client_ID"))
				m_AD_Client_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("DeleteOldImported"))
				m_deleteOldImported = "Y".equals(para[i].getParameter());
			else if (name.equals("IsValidateOnly"))
				p_IsValidateOnly = para[i].getParameterAsBoolean();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		if (m_DateValue == null)
			m_DateValue = new Timestamp (System.currentTimeMillis());
	}	//	prepare


	/**
	 *  Perform process.
	 *  @return Message
	 *  @throws Exception
	 */
	protected String doIt() throws Exception
	{
		StringBuilder sql = null;
		int no = 0;
		String clientCheck = getWhereClause();

		//	****	Prepare	****

		//	Delete Old Imported
		if (m_deleteOldImported)
		{
			sql = new StringBuilder ("DELETE I_BPartner "
					+ "WHERE I_IsImported='Y'").append(clientCheck);
			no = DB.executeUpdateEx(sql.toString(), get_TrxName());
			log.fine("Delete Old Impored =" + no);
		}

		//	Set Client, Org, IsActive, Created/Updated
		sql = new StringBuilder ("UPDATE I_BPartner "
				+ "SET AD_Client_ID = COALESCE (AD_Client_ID, ").append(m_AD_Client_ID).append("),"
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
		
		//	Set BP_Group
		sql = new StringBuilder ("UPDATE I_BPartner i "
				+ "SET GroupValue=(SELECT MAX(Value) FROM C_BP_Group g WHERE g.IsDefault='Y'"
				+ " AND g.AD_Client_ID=i.AD_Client_ID) ");
		sql.append("WHERE GroupValue IS NULL AND C_BP_Group_ID IS NULL"
				+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		log.fine("Set Group Default=" + no);
		//
		sql = new StringBuilder ("UPDATE I_BPartner i "
				+ "SET C_BP_Group_ID=(SELECT C_BP_Group_ID FROM C_BP_Group g"
				+ " WHERE i.GroupValue=g.Value AND g.AD_Client_ID=i.AD_Client_ID) "
				+ "WHERE C_BP_Group_ID IS NULL"
				+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		log.fine("Set Group=" + no);
		//
		sql = new StringBuilder ("UPDATE I_BPartner "
				+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR="+ Msg.parseTranslation(getCtx(), "@Invalid@ @C_BP_Group_ID@") +", ' "
				+ "WHERE C_BP_Group_ID IS NULL"
				+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		log.config("Invalid Group=" + no);

		//	Set Country
		sql = new StringBuilder ("UPDATE I_BPartner i "
				+ "SET C_Country_ID=(SELECT C_Country_ID FROM C_Country c"
				+ " WHERE UPPER(i.CountryCode)=UPPER(c.CountryCode) AND c.AD_Client_ID IN (0, i.AD_Client_ID)) "
				+ "WHERE C_Country_ID IS NULL"
				+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		log.fine("Set Country=" + no);
		//
		sql = new StringBuilder ("UPDATE I_BPartner "
				+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR="+ Msg.parseTranslation(getCtx(), "@Invalid@ @C_Country_ID@") +", ' "
				+ "WHERE C_Country_ID IS NULL AND (City IS NOT NULL OR Address1 IS NOT NULL)"
				+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		log.config("Invalid Country=" + no);

		//	Set Region
		sql = new StringBuilder ("UPDATE I_BPartner i "
				+ "Set RegionName=(SELECT MAX(Name) FROM C_Region r"
				+ " WHERE r.IsDefault='Y' AND r.C_Country_ID=i.C_Country_ID"
				+ " AND r.AD_Client_ID IN (0, i.AD_Client_ID)) " );
		sql.append("WHERE RegionName IS NULL AND C_Region_ID IS NULL"
				+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		log.fine("Set Region Default=" + no);
		//
		sql = new StringBuilder ("UPDATE I_BPartner i "
				+ "Set C_Region_ID=(SELECT C_Region_ID FROM C_Region r"
				+ " WHERE UPPER(r.Name)=UPPER(i.RegionName) AND r.C_Country_ID=i.C_Country_ID"
				+ " AND r.AD_Client_ID IN (0, i.AD_Client_ID)) "
				+ "WHERE C_Region_ID IS NULL"
				+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		log.fine("Set Region=" + no);
		//	For Place of birth
		//	Set Country
		sql = new StringBuilder ("UPDATE I_BPartner i "
				+ "SET BirthCountry_ID=(SELECT C_Country_ID FROM C_Country c"
				+ " WHERE i.BirthCountryCode=c.CountryCode AND c.AD_Client_ID IN (0, i.AD_Client_ID)) "
				+ "WHERE BirthCountry_ID IS NULL"
				+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		log.fine("Set Country=" + no);
		//
		sql = new StringBuilder ("UPDATE I_BPartner "
				+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=" + Msg.parseTranslation(getCtx(), "@Invalid@ @BirthCountry_ID@") + ", ' "
				+ "WHERE BirthCountry_ID IS NULL AND BirthCity IS NOT NULL"
				+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		log.config("Invalid Country=" + no);

		//	Set Region
		sql = new StringBuilder ("UPDATE I_BPartner i "
				+ "Set BirthRegionName=(SELECT MAX(Name) FROM C_Region r"
				+ " WHERE r.IsDefault='Y' AND r.C_Country_ID=i.BirthCountry_ID"
				+ " AND r.AD_Client_ID IN (0, i.AD_Client_ID)) " );
		sql.append("WHERE BirthRegionName IS NULL AND BirthRegion_ID IS NULL"
				+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		log.fine("Set Region Default=" + no);
		//
		sql = new StringBuilder ("UPDATE I_BPartner i "
				+ "Set BirthRegion_ID=(SELECT C_Region_ID FROM C_Region r"
				+ " WHERE UPPER(r.Name)=UPPER(i.BirthRegionName) AND r.C_Country_ID=i.BirthCountry_ID"
				+ " AND r.AD_Client_ID IN (0, i.AD_Client_ID)) "
				+ "WHERE BirthRegion_ID IS NULL"
				+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		log.fine("Set Region=" + no);
		
		//
		sql = new StringBuilder ("UPDATE I_BPartner i "
				+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR="+ Msg.parseTranslation(getCtx(), "@Invalid@ @C_Country_ID@") +", ' "
				+ "WHERE C_Region_ID IS NULL "
				+ " AND EXISTS (SELECT * FROM C_Country c"
				+ " WHERE c.C_Country_ID=i.C_Country_ID AND c.HasRegion='Y')"
				+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		log.config("Invalid Region=" + no);

		//	Set Greeting
		sql = new StringBuilder ("UPDATE I_BPartner i "
				+ "SET C_Greeting_ID=(SELECT C_Greeting_ID FROM C_Greeting g"
				+ " WHERE UPPER(i.BPContactGreeting)=UPPER(g.Name) AND g.AD_Client_ID IN (0, i.AD_Client_ID)) "
				+ "WHERE C_Greeting_ID IS NULL AND BPContactGreeting IS NOT NULL"
				+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		log.fine("Set Greeting=" + no);
		//
		sql = new StringBuilder ("UPDATE I_BPartner i "
				+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR="+ Msg.parseTranslation(getCtx(), "@Invalid@ @C_Greeting_ID@") +", ' "
				+ "WHERE C_Greeting_ID IS NULL AND BPContactGreeting IS NOT NULL"
				+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		log.config("Invalid Greeting=" + no);

		// Existing User. Lookup by AD_User.Email + AD_User.Name - BPartner
		sql = new StringBuilder ("UPDATE I_BPartner i "
				+ "SET C_BPartner_ID="
				+ "(SELECT MAX(C_BPartner_ID) FROM AD_User u "
				+ "WHERE UPPER(i.EMail)=UPPER(u.EMail) AND UPPER(i.ContactName)=UPPER(u.Name) AND u.AD_Client_ID=i.AD_Client_ID "
				+ "AND EXISTS (SELECT 1 FROM C_BPartner bp WHERE bp.C_BPartner_ID = u.C_BPartner_ID AND bp.Value = i.Value)) "
				+ "WHERE i.EMail IS NOT NULL AND I_IsImported='N'").append(clientCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		log.fine("Found EMail User=" + no);
		// Existing User. Lookup by AD_User.Email - BPartner
		sql = new StringBuilder ("UPDATE I_BPartner i "
				+ "SET C_BPartner_ID="
				+ "(SELECT MAX(C_BPartner_ID) FROM AD_User u "
				+ "WHERE UPPER(i.EMail)=UPPER(u.EMail) AND u.AD_Client_ID=i.AD_Client_ID "
				+ "AND EXISTS (SELECT 1 FROM C_BPartner bp WHERE bp.C_BPartner_ID = u.C_BPartner_ID AND bp.Value = i.Value)) "
				+ "WHERE i.C_BPartner_ID IS NULL AND i.EMail IS NOT NULL AND I_IsImported='N'").append(clientCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		log.fine("Found EMail User=" + no);
		// Existing User. Lookup by AD_User.Email - AD_User
		sql = new StringBuilder ("UPDATE I_BPartner i "
				+ "SET AD_User_ID="
				+ "(SELECT MAX(AD_User_ID) FROM AD_User u "
				+ "WHERE UPPER(i.EMail)=UPPER(u.EMail) AND UPPER(i.ContactName)=UPPER(u.Name) AND u.AD_Client_ID=i.AD_Client_ID "
				+ "AND EXISTS (SELECT 1 FROM C_BPartner bp WHERE bp.C_BPartner_ID = u.C_BPartner_ID AND bp.Value = i.Value)) "
				+ "WHERE i.EMail IS NOT NULL AND I_IsImported='N'").append(clientCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		log.fine("Found EMail User=" + no);
		// Existing User. Lookup by AD_User.Email - AD_User
		sql = new StringBuilder ("UPDATE I_BPartner i "
				+ "SET AD_User_ID="
				+ "(SELECT MAX(AD_User_ID) FROM AD_User u "
				+ "WHERE UPPER(i.EMail)=UPPER(u.EMail) AND u.AD_Client_ID=i.AD_Client_ID "
				+ "AND EXISTS (SELECT 1 FROM C_BPartner bp WHERE bp.C_BPartner_ID = u.C_BPartner_ID AND bp.Value = i.Value)) "
				+ "WHERE i.AD_User_ID IS NULL AND i.EMail IS NOT NULL AND I_IsImported='N'").append(clientCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		log.fine("Found EMail User=" + no);
		//	Existing BPartner ? Match Value
		sql = new StringBuilder ("UPDATE I_BPartner i "
				+ "SET C_BPartner_ID=(SELECT C_BPartner_ID FROM C_BPartner p"
				+ " WHERE UPPER(i.Value)=UPPER(p.Value) AND p.AD_Client_ID=i.AD_Client_ID) "
				+ "WHERE C_BPartner_ID IS NULL AND Value IS NOT NULL"
				+ " AND I_IsImported='N'").append(clientCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		log.fine("Found BPartner=" + no);

		//	Existing Contact ? Match Name
		sql = new StringBuilder ("UPDATE I_BPartner i "
				+ "SET AD_User_ID=(SELECT MAX(AD_User_ID) FROM AD_User c"
				+ " WHERE UPPER(i.ContactName)=UPPER(c.Name) AND i.C_BPartner_ID=c.C_BPartner_ID AND c.AD_Client_ID=i.AD_Client_ID) "
				+ "WHERE C_BPartner_ID IS NOT NULL AND AD_User_ID IS NULL AND ContactName IS NOT NULL"
				+ " AND I_IsImported='N'").append(clientCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		log.fine("Found Contact=" + no);

		//  Existing Project ? Project Value
		sql = new StringBuilder("UPDATE  I_BPartner i SET C_Project_ID = (SELECT C_Project_ID FROM C_Project p "
				+ "WHERE UPPER(p.Value) = UPPER(i.ProjectValue)) WHERE i.ProjectValue IS NOT NULL AND I_IsImported='N'");
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		log.fine("Project =" + no);


		//  Existing Project Member Type? Project Member Type Value
		sql = new StringBuilder("UPDATE  I_BPartner i SET C_ProjectMemberType_ID = (SELECT C_ProjectMemberType_ID FROM C_ProjectMemberType pmt "
				+ "WHERE UPPER(pmt.Value) = UPPER(i.ProjectMemberTypeValue)) WHERE i.IsProjectMember='Y' AND I_IsImported='N'");
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		log.fine("Project Member Type =" + no);


//		Existing Location ? Exact Match
		sql = new StringBuilder ("UPDATE I_BPartner i "
				+ "SET C_BPartner_Location_ID=(SELECT C_BPartner_Location_ID"
				+ " FROM C_BPartner_Location bpl INNER JOIN C_Location l ON (bpl.C_Location_ID=l.C_Location_ID)"
				+ " WHERE i.C_BPartner_ID=bpl.C_BPartner_ID AND bpl.AD_Client_ID=i.AD_Client_ID"
				+ " AND (i.Address1=l.Address1 OR (i.Address1 IS NULL AND l.Address1 IS NULL))"
				+ " AND (i.Address2=l.Address2 OR (i.Address2 IS NULL AND l.Address2 IS NULL))"
				+ " AND (i.Address3=l.Address3 OR (i.Address3 IS NULL AND l.Address3 IS NULL))"
				+ " AND (i.Address4=l.Address4 OR (i.Address4 IS NULL AND l.Address4 IS NULL))"
				+ " AND (i.City=l.City OR (i.City IS NULL AND l.City IS NULL))"
				+ " AND (i.Postal=l.Postal OR (i.Postal IS NULL AND l.Postal IS NULL))"
				+ " AND (i.Postal_Add=l.Postal_Add OR (l.Postal_Add IS NULL AND l.Postal_Add IS NULL))"
				+ " AND i.C_Region_ID=l.C_Region_ID AND i.C_Country_ID=l.C_Country_ID) "
				+ "WHERE C_BPartner_ID IS NOT NULL AND C_BPartner_Location_ID IS NULL"
				+ " AND I_IsImported='N'").append(clientCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		log.fine("Found Location=" + no);

		//	Interest Area
		sql = new StringBuilder ("UPDATE I_BPartner i "
				+ "SET R_InterestArea_ID=(SELECT R_InterestArea_ID FROM R_InterestArea ia "
				+ "WHERE UPPER(i.InterestAreaName)=UPPER(ia.Name) AND ia.AD_Client_ID=i.AD_Client_ID) "
				+ "WHERE R_InterestArea_ID IS NULL AND InterestAreaName IS NOT NULL"
				+ " AND I_IsImported='N'").append(clientCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		log.fine("Set Interest Area=" + no);

		// Value is mandatory error
		sql = new StringBuilder ("UPDATE I_BPartner "
				+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR="+ Msg.parseTranslation(getCtx(), "@FillMandatory@ @Value@") +", ' "
				+ "WHERE Value IS NULL "
				+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		log.config("Value is mandatory=" + no);
		
		// Set Bank
		sql = new StringBuilder ("UPDATE I_BPartner i "
				+ "SET C_Bank_ID=(SELECT MAX(C_Bank_ID) FROM C_Bank b "
				+ "WHERE UPPER(i.BankName)=UPPER(b.Name) AND b.AD_Client_ID=i.AD_Client_ID) "
				+ "WHERE C_Bank_ID IS NULL AND BankName IS NOT NULL AND IsACH='Y'"
				+ " AND I_IsImported='N'").append(clientCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		log.fine("Set Bank=" + no);
		
		// Invalid Bank
		sql = new StringBuilder ("UPDATE I_BPartner "
				+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=" + Msg.parseTranslation(getCtx(), "@Invalid@ @C_Bank_ID@") + ", ' "
				+ "WHERE C_Bank_ID IS NULL AND BankName IS NOT NULL AND IsACH='Y'"
				+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		log.config("Invalid Country=" + no);
		
		ModelValidationEngine.get().fireImportValidate(this, null, null, ImportValidator.TIMING_AFTER_VALIDATE);

		commitEx();
		if (p_IsValidateOnly)
		{
			return "Validated";
		}
		//	-------------------------------------------------------------------
		int noInsert = 0;
		int noUpdate = 0;

		//	Go through Records
		sql = new StringBuilder ("SELECT * FROM I_BPartner "
				+ "WHERE I_IsImported='N'").append(clientCheck);
		// gody: 20070113 - Order so the same values are consecutive.
		sql.append(" ORDER BY Value, I_BPartner_ID");
		String whereClause = "";
		PreparedStatement pstmt =  null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			rs = pstmt.executeQuery();

			// Remember Previous BP Value BP is only first one, others are contacts.
			// All contacts share BP location.
			// bp and bpl declarations before loop, we need them for data.
			String Old_BPValue = "" ; 
			MBPartner bp = null;
			MBPartnerLocation bpl = null;

			while (rs.next())
			{	
				// Remember Value - only first occurance of the value is BP
				String New_BPValue = rs.getString("Value") ;

				X_I_BPartner importPartner = new X_I_BPartner (getCtx(), rs, get_TrxName());
				log.fine("I_BPartner_ID=" + importPartner.getI_BPartner_ID()
						+ ", C_BPartner_ID=" + importPartner.getC_BPartner_ID()
						+ ", C_BPartner_Location_ID=" + importPartner.getC_BPartner_Location_ID()
						+ ", AD_User_ID=" + importPartner.getAD_User_ID());


				if ( ! New_BPValue.equals(Old_BPValue)) {
					//	****	Create/Update BPartner	****
					bp = null;

					if (importPartner.getC_BPartner_ID() == 0)	//	Insert new BPartner
					{
						bp = new MBPartner(importPartner);
						ModelValidationEngine.get().fireImportValidate(this, importPartner, bp, ImportValidator.TIMING_AFTER_IMPORT);
						if (importPartner.getBirthCountry_ID() > 0)
							setPlaceOfBirthId(importPartner, bp);
						setTypeOfBPartner(importPartner,bp);
						
						if (bp.save())
						{
							importPartner.setC_BPartner_ID(bp.getC_BPartner_ID());
							log.finest("Insert BPartner - " + bp.getC_BPartner_ID());
							noInsert++;
						}
						else
						{
							sql = new StringBuilder ("UPDATE I_BPartner i "
									+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
							.append("'Cannot Insert BPartner, ' ")
							.append("WHERE I_BPartner_ID=").append(importPartner.getI_BPartner_ID());
							DB.executeUpdateEx(sql.toString(), get_TrxName());
							continue;
						}
					}
					else				//	Update existing BPartner
					{
						bp = new MBPartner(getCtx(), importPartner.getC_BPartner_ID(), get_TrxName());
						//	if (impBP.getValue() != null)			//	not to overwite
						//		bp.setValue(impBP.getValue());
						if (importPartner.getName() != null)
						{
							bp.setName(importPartner.getName());
							bp.setName2(importPartner.getName2());
						}
						if (importPartner.getDUNS() != null)
							bp.setDUNS(importPartner.getDUNS());
						if (importPartner.getTaxID() != null)
							bp.setTaxID(importPartner.getTaxID());
						if (importPartner.getNAICS() != null)
							bp.setNAICS(importPartner.getNAICS());
						if (importPartner.getDescription() != null)
							bp.setDescription(importPartner.getDescription());
						if (importPartner.getC_BP_Group_ID() != 0)
							bp.setC_BP_Group_ID(importPartner.getC_BP_Group_ID());
						//	Employee values
						if(importPartner.getBirthday() != null)
							bp.setBirthday(importPartner.getBirthday());
						if(importPartner.getFathersName() != null)
							bp.setFathersName(importPartner.getFathersName());
						if(importPartner.getBloodGroup() != null)
							bp.setBloodGroup(importPartner.getBloodGroup());
						//if(impBP.getPlaceOfBirth() != null)  //todo : implement PlaceOfBirth_ID
						//	bp.setPlaceOfBirth(impBP.getPlaceOfBirth());
						if(importPartner.getGender() != null)
							bp.setGender(importPartner.getGender());
						//	
						ModelValidationEngine.get().fireImportValidate(this, importPartner, bp, ImportValidator.TIMING_AFTER_IMPORT);
						if (importPartner.getBirthCountry_ID() > 0)
							setPlaceOfBirthId(importPartner, bp);
						setTypeOfBPartner(importPartner,bp);
						
						//
						if (bp.save())
						{
							log.finest("Update BPartner - " + bp.getC_BPartner_ID());
							noUpdate++;
						}
						else
						{
							sql = new StringBuilder ("UPDATE I_BPartner i "
									+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
							.append("'Cannot Update BPartner, ' ")
							.append("WHERE I_BPartner_ID=").append(importPartner.getI_BPartner_ID());
							DB.executeUpdateEx(sql.toString(), get_TrxName());
							continue;
						}
					}
				}else {
					importPartner.setC_BPartner_ID(bp.getC_BPartner_ID());
					importPartner.save();
				}
				Old_BPValue = New_BPValue ;
				
				whereClause = " EXISTS(SELECT 1 FROM C_Location l, I_BPartner i "
										+ "WHERE "
										+ "l.C_Location_ID = C_BPartner_Location.C_Location_ID AND i.I_BPartner_ID = " + importPartner.getI_BPartner_ID();
				
				if (importPartner.getAddress1()!=null
						&& !importPartner.getAddress1().isEmpty())
					whereClause+=" AND l.Address1 = i.Address1";
				if (importPartner.getAddress2()!=null
						&& !importPartner.getAddress2().isEmpty())
					whereClause+=" AND l.Address2 = i.Address2";
				if (importPartner.getAddress3()!=null
						&& !importPartner.getAddress3().isEmpty())
					whereClause+=" AND l.Address3 = i.Address3";
				if (importPartner.getAddress4()!=null
						&& !importPartner.getAddress4().isEmpty())
					whereClause+=" AND l.Address4 = i.Address4";
				if (importPartner.getCity()!=null
						&& !importPartner.getCity().isEmpty())
					whereClause+=" AND l.City = i.City";
				if (importPartner.getC_Region_ID()!=0)
					whereClause+=" AND l.C_Region_ID = i.C_Region_ID" ;
				if (importPartner.getC_Country_ID()!=0)
					whereClause+=" AND l.C_Country_ID = i.C_Country_ID" ;
				if (importPartner.getPostal()!=null
						&& !importPartner.getPostal().isEmpty())
					whereClause+=" AND l.Postal = i.Postal";
				if (importPartner.getPostal_Add()!=null
						&& !importPartner.getPostal_Add().isEmpty())
					whereClause+=" AND l.Postal_Add = i.Postal_Add";
				
				whereClause+= ")";
				bpl = Arrays.stream(MBPartnerLocation.getForBPartner(getCtx(), importPartner.getC_BPartner_ID(), whereClause, importPartner.get_TrxName()))
						.findFirst()
						.orElse(null);
				if (bpl!=null)		//	Update Location
				{
					//bpl = new MBPartnerLocation(getCtx(), importPartner.getC_BPartner_Location_ID(), get_TrxName());
					MLocation location = new MLocation(getCtx(), bpl.getC_Location_ID(), get_TrxName());
					location.setC_Country_ID(importPartner.getC_Country_ID());
					location.setC_Region_ID(importPartner.getC_Region_ID());
					location.setCity(importPartner.getCity());
					location.setAddress1(importPartner.getAddress1());
					location.setAddress2(importPartner.getAddress2());
					location.setAddress3(importPartner.getAddress3());
					location.setAddress4(importPartner.getAddress4());
					location.setPostal(importPartner.getPostal());
					location.setPostal_Add(importPartner.getPostal_Add());
					if (!location.save())
						log.warning("Location not updated");
					else
						bpl.setC_Location_ID(location.getC_Location_ID());
					if (importPartner.getPhone() != null)
						bpl.setPhone(importPartner.getPhone());
					if (importPartner.getPhone2() != null)
						bpl.setPhone2(importPartner.getPhone2());
					if (importPartner.getFax() != null)
						bpl.setFax(importPartner.getFax());
					ModelValidationEngine.get().fireImportValidate(this, importPartner, bpl, ImportValidator.TIMING_AFTER_IMPORT);
					bpl.saveEx();
				}
				else 	//	New Location
					if (importPartner.getC_Country_ID() != 0
							&& importPartner.getAddress1() != null
							&& importPartner.getCity() != null)
					{
						MLocation location = new MLocation(getCtx(), importPartner.getC_Country_ID(),
								importPartner.getC_Region_ID(), importPartner.getCity(), get_TrxName());
						location.setAddress1(importPartner.getAddress1());
						location.setAddress2(importPartner.getAddress2());
						location.setAddress3(importPartner.getAddress3());
						location.setAddress4(importPartner.getAddress4());
						location.setPostal(importPartner.getPostal());
						location.setPostal_Add(importPartner.getPostal_Add());
						if (location.save())
							log.finest("Insert Location - " + location.getC_Location_ID());
						else
						{
							rollback();
							noInsert--;
							sql = new StringBuilder ("UPDATE I_BPartner i "
									+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
							.append("'Cannot Insert Location, ' ")
							.append("WHERE I_BPartner_ID=").append(importPartner.getI_BPartner_ID());
							DB.executeUpdateEx(sql.toString(), get_TrxName());
							continue;
						}
						//
						bpl = new MBPartnerLocation (bp);
						bpl.setC_Location_ID(location.getC_Location_ID());
						bpl.setPhone(importPartner.getPhone());
						bpl.setPhone2(importPartner.getPhone2());
						bpl.setFax(importPartner.getFax());
						ModelValidationEngine.get().fireImportValidate(this, importPartner, bpl, ImportValidator.TIMING_AFTER_IMPORT);
						if (bpl.save())
						{
							log.finest("Insert BP Location - " + bpl.getC_BPartner_Location_ID());
							importPartner.setC_BPartner_Location_ID(bpl.getC_BPartner_Location_ID());
						}
						else
						{
							rollback();
							noInsert--;
							sql = new StringBuilder ("UPDATE I_BPartner i "
									+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
							.append("'Cannot Insert BPLocation, ' ")
							.append("WHERE I_BPartner_ID=").append(importPartner.getI_BPartner_ID());
							DB.executeUpdateEx(sql.toString(), get_TrxName());
							continue;
						}
					}
				

				//	****	Create/Update Contact	****
				MUser user = null;
				if (importPartner.getAD_User_ID() != 0)
				{
					user = new MUser (getCtx(), importPartner.getAD_User_ID(), get_TrxName());
					if (user.getC_BPartner_ID() == 0)
						user.setC_BPartner_ID(bp.getC_BPartner_ID());
					else if (user.getC_BPartner_ID() != bp.getC_BPartner_ID())
					{
						rollback();
						noInsert--;
						sql = new StringBuilder ("UPDATE I_BPartner i "
								+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
						.append("'BP of User <> BP, ' ")
						.append("WHERE I_BPartner_ID=").append(importPartner.getI_BPartner_ID());
						DB.executeUpdateEx(sql.toString(), get_TrxName());
						continue;
					}
					if (importPartner.getC_Greeting_ID() != 0)
						user.setC_Greeting_ID(importPartner.getC_Greeting_ID());
					String name = importPartner.getContactName();
					if (name == null || name.length() == 0)
						name = importPartner.getEMail();
					user.setName(name);
					if (importPartner.getTitle() != null)
						user.setTitle(importPartner.getTitle());
					if (importPartner.getContactDescription() != null)
						user.setDescription(importPartner.getContactDescription());
					if (importPartner.getComments() != null)
						user.setComments(importPartner.getComments());
					if (importPartner.getPhone() != null)
						user.setPhone(importPartner.getPhone());
					if (importPartner.getPhone2() != null)
						user.setPhone2(importPartner.getPhone2());
					if (importPartner.getFax() != null)
						user.setFax(importPartner.getFax());
					if (importPartner.getEMail() != null)
						user.setEMail(importPartner.getEMail());
					if (importPartner.getBirthday() != null)
						user.setBirthday(importPartner.getBirthday());
					if (bpl != null)
						user.setC_BPartner_Location_ID(bpl.getC_BPartner_Location_ID());
					ModelValidationEngine.get().fireImportValidate(this, importPartner, user, ImportValidator.TIMING_AFTER_IMPORT);
					if (user.save())
					{
						log.finest("Update BP Contact - " + user.getAD_User_ID());
					}
					else
					{
						rollback();
						noInsert--;
						sql = new StringBuilder ("UPDATE I_BPartner i "
								+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
						.append("'Cannot Update BP Contact, ' ")
						.append("WHERE I_BPartner_ID=").append(importPartner.getI_BPartner_ID());
						DB.executeUpdateEx(sql.toString(), get_TrxName());
						continue;
					}
					// Set Project data
					setProjectInfo(importPartner , user);
				}
				else 	//	New Contact
					if (importPartner.getContactName() != null || importPartner.getEMail() != null)
					{
						user = new MUser (bp);
						if (importPartner.getC_Greeting_ID() != 0)
							user.setC_Greeting_ID(importPartner.getC_Greeting_ID());
						String name = importPartner.getContactName();
						if (name == null || name.length() == 0)
							name = importPartner.getEMail();
						user.setName(name);
						user.setTitle(importPartner.getTitle());
						user.setDescription(importPartner.getContactDescription());
						user.setComments(importPartner.getComments());
						user.setPhone(importPartner.getPhone());
						user.setPhone2(importPartner.getPhone2());
						user.setFax(importPartner.getFax());
						user.setEMail(importPartner.getEMail());
						user.setBirthday(importPartner.getBirthday());
						// Contact Project Data Imformation
						if (bpl != null)
							user.setC_BPartner_Location_ID(bpl.getC_BPartner_Location_ID());
						ModelValidationEngine.get().fireImportValidate(this, importPartner, user, ImportValidator.TIMING_AFTER_IMPORT);
						if (user.save())
						{
							log.finest("Insert BP Contact - " + user.getAD_User_ID());
							importPartner.setAD_User_ID(user.getAD_User_ID());
						}
						else
						{
							rollback();
							noInsert--;
							sql = new StringBuilder ("UPDATE I_BPartner i "
									+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
							.append("'Cannot Insert BPContact, ' ")
							.append("WHERE I_BPartner_ID=").append(importPartner.getI_BPartner_ID());
							DB.executeUpdateEx(sql.toString(), get_TrxName());
							continue;
						}
						// Set Project information
						setProjectInfo(importPartner , user);
					}

				//	Interest Area
				if (importPartner.getR_InterestArea_ID() != 0 && user != null)
				{
					MContactInterest ci = MContactInterest.get(getCtx(), 
							importPartner.getR_InterestArea_ID(), user.getAD_User_ID(),
							true, get_TrxName());
					ci.saveEx();		//	don't subscribe or re-activate
				}
				//
				whereClause = "IsACH = 'Y' AND EXISTS (SELECT 1 FROM I_BPartner i WHERE i.I_BPartner_ID = " + importPartner.getI_BPartner_ID() + "AND i.A_Name = C_BP_BankAccount.A_Name";
				if (importPartner.isACH()) 
					whereClause += " AND C_Bank_ID = i.C_Bank_ID";
				
				whereClause+=")";
				
				MBPBankAccount bpBankAccount = MBPBankAccount.getByPartner(getCtx(), importPartner.getC_BPartner_ID(), whereClause, importPartner.get_TrxName())
												.stream()
												.findFirst()
												.orElse(new MBPBankAccount(getCtx(), 0, get_TrxName()));
				
				if ((importPartner.isACH()
						&& importPartner.getC_Bank_ID() !=0
							&& importPartner.getA_Name()!=null)
						|| (!importPartner.isACH()
								&& importPartner.getA_Name()!=null)) {
						
					
					bpBankAccount.setC_BPartner_ID(importPartner.getC_BPartner_ID());
					bpBankAccount.setIsACH(importPartner.isACH());
					
					bpBankAccount.setIsACH(importPartner.isACH());
					bpBankAccount.setCreditCardType(importPartner.getCreditCardType());
					bpBankAccount.setCreditCardNumber(importPartner.getCreditCardNumber());
					bpBankAccount.setCreditCardVV(importPartner.getCreditCardVV());
					bpBankAccount.setCreditCardExpMM(importPartner.getCreditCardExpMM());
					bpBankAccount.setCreditCardExpYY(importPartner.getCreditCardExpYY());
					
					bpBankAccount.setBPBankAcctUse(importPartner.getBPBankAcctUse());
					bpBankAccount.setIBAN(importPartner.getIBAN());
					bpBankAccount.setC_Bank_ID(importPartner.getC_Bank_ID());
					bpBankAccount.setBankAccountType(importPartner.getBankAccountType());
					bpBankAccount.setRoutingNo(importPartner.getRoutingNo());
					bpBankAccount.setAccountNo(importPartner.getAccountNo());
					
					bpBankAccount.setA_Name(importPartner.getA_Name());
					bpBankAccount.setA_Street(importPartner.getA_Street());
					bpBankAccount.setA_City(importPartner.getA_City());
					bpBankAccount.setA_Zip(importPartner.getA_Zip());
					bpBankAccount.setA_State(importPartner.getA_State());
					bpBankAccount.setA_Country(importPartner.getA_Country());
					bpBankAccount.setA_Ident_DL(importPartner.getA_Ident_DL());
					bpBankAccount.setA_Ident_SSN(importPartner.getA_Ident_SSN());
					bpBankAccount.setA_EMail(importPartner.getA_EMail());
					bpBankAccount.setR_AvsAddr(importPartner.getR_AvsAddr());
					bpBankAccount.setR_AvsZip(importPartner.getR_AvsZip());
					bpBankAccount.save();
					
					importPartner.setC_BP_BankAccount_ID(bpBankAccount.getC_BP_BankAccount_ID());
				}
				//
				importPartner.setI_IsImported(true);
				importPartner.setProcessed(true);
				importPartner.setProcessing(false);
				importPartner.saveEx();
				commitEx();
			}	//	for all I_Product
			DB.close(rs, pstmt);
		}
		catch (SQLException e)
		{
			rollback();
			//log.log(Level.SEVERE, "", e);
			throw new DBException(e, sql.toString());
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
			//	Set Error to indicator to not imported
			sql = new StringBuilder ("UPDATE I_BPartner "
					+ "SET I_IsImported='N', Updated=SysDate "
					+ "WHERE I_IsImported<>'Y'").append(clientCheck);
			no = DB.executeUpdateEx(sql.toString(), get_TrxName());
			addLog (0, null, new BigDecimal (no), "@Errors@");
			addLog (0, null, new BigDecimal (noInsert), "@C_BPartner_ID@: @Inserted@");
			addLog (0, null, new BigDecimal (noUpdate), "@C_BPartner_ID@: @Updated@");
		}
		return "";
	}	//	doIt

	private void setProjectInfo(X_I_BPartner impportPartner , MUser user) {

		// Contact Project Data Imformation
		if (impportPartner.isProjectManager()) {
			user.setIsProjectManager(true);
			Optional<MProject> projectOptional = Optional.ofNullable((MProject) impportPartner.getC_Project());
			projectOptional.ifPresent( project -> {
				if (project.getProjectManager_ID() <= 0)
				{
					project.setProjectManager_ID(user.get_ID());
					project.saveEx();
				}
			});


		}
		if (impportPartner.isProjectMember()) {
			user.setIsProjectMember(true);
			Optional<MProject> projectOptional = Optional.ofNullable((MProject) impportPartner.getC_Project());
			projectOptional.ifPresent( project -> {
				Optional<X_C_ProjectMemberType> projectMemberTyoeOptinal = Optional.ofNullable((X_C_ProjectMemberType)impportPartner.getC_ProjectMemberType());
				X_C_ProjectMember projectMember =  new X_C_ProjectMember(getCtx() , 0 , get_TrxName());
				projectMember.setC_Project_ID(project.getC_Project_ID());
				projectMember.setAD_User_ID(user.get_ID());
				projectMember.setNotificationType(user.getNotificationType());
				projectMemberTyoeOptinal.ifPresent(projectMemberType ->
						projectMember.setC_ProjectMemberType_ID(projectMemberType.getC_ProjectMemberType_ID())
				);
				projectMember.saveEx();
			});
		}
	}


	//@Override
	public String getWhereClause()
	{
		return " AND AD_Client_ID=" + m_AD_Client_ID;
	}


	//@Override
	public String getImportTableName()
	{
		return X_I_BPartner.Table_Name;
	}
	
	/**
	 * Get Birth of place
	 * @param impBP
	 * @param partner
	 * @return
	 */
	private void setPlaceOfBirthId(X_I_BPartner impBP, MBPartner partner) {
		//	****	Create/Update BPartner Location	****
		int placeOfBirthId = impBP.getPlaceOfBirth_ID();
		if(placeOfBirthId == 0
		&& impBP.getC_BPartner_ID() > 0) {
			placeOfBirthId = partner.getPlaceOfBirth_ID();
		}
		MLocation location = new MLocation(getCtx(), placeOfBirthId, get_TrxName());
		location.setC_Country_ID(impBP.getBirthCountry_ID());
		location.setC_Region_ID(impBP.getBirthRegion_ID());
		location.setCity(impBP.getBirthCity());
		location.setPostal(impBP.getBirthPostal());
		if (!location.save())
			log.warning("Location not updated");
		//	return location
		impBP.setPlaceOfBirth_ID(location.getC_Location_ID());
		partner.setPlaceOfBirth_ID(location.getC_Location_ID());
	}
	
	/**
	 * Set type of Business Partner 
	 *
	 * @param X_I_BPartner impBP
	 * @param MBPartner bp
	 */
	private void setTypeOfBPartner(X_I_BPartner impBP, MBPartner bp){
		if (impBP.isVendor()){		
			bp.setIsVendor(true);
			bp.setIsCustomer(false); // It is put to false since by default in C_BPartner is true
		}
		if (impBP.isEmployee()){ 		
			bp.setIsEmployee(true);
			bp.setIsCustomer(false); // It is put to false since by default in C_BPartner is true
		}
		// it has to be the last if, to subscribe the bp.setIsCustomer (false) of the other two
		if (impBP.isCustomer()){		
			bp.setIsCustomer(true);
		}
	}	// setTypeOfBPartner
	
}	//	ImportBPartner
