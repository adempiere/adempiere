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
package org.compiere.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.model.MAccount;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MAcctSchemaElement;
import org.compiere.model.MCharge;
import org.compiere.model.MChargeAcct;
import org.compiere.model.MElement;
import org.compiere.model.MElementValue;
import org.compiere.model.MTaxCategory;
import org.compiere.model.X_I_ElementValue;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Util;

/**
 *	Import Accounts from I_ElementValue
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: ImportAccount.java,v 1.2 2006/07/30 00:51:02 jjanke Exp $
 */
public class ImportAccount extends SvrProcess
{
	/**	Client to be imported to		*/
	private int				m_AD_Client_ID = 0;
	/** Default Element					*/
	private int				m_C_Element_ID = 0;
	/**	Update Default Accounts			*/
	private boolean			m_updateDefaultAccounts = false;
	/** Create New Combination			*/
	private boolean			m_createNewCombination = true;

	/**	Delete old Imported				*/
	private boolean			m_deleteOldImported = false;

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
			if (para[i].getParameter() == null)
				;
			else if (name.equals("AD_Client_ID"))
				m_AD_Client_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("C_Element_ID"))
				m_C_Element_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("UpdateDefaultAccounts"))
				m_updateDefaultAccounts = "Y".equals(para[i].getParameter());
			else if (name.equals("CreateNewCombination"))
				m_createNewCombination = "Y".equals(para[i].getParameter());
			else if (name.equals("DeleteOldImported"))
				m_deleteOldImported = "Y".equals(para[i].getParameter());
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
	protected String doIt() throws java.lang.Exception
	{
		StringBuffer sql = null;
		int no = 0;
		String clientCheck = " AND AD_Client_ID=" + m_AD_Client_ID;

		//	****	Prepare	****

		//	Delete Old Imported
		if (m_deleteOldImported)
		{
			sql = new StringBuffer ("DELETE I_ElementValue "
				+ "WHERE I_IsImported='Y'").append(clientCheck);
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			log.fine("Delete Old Impored =" + no);
		}

		//	Set Client, Org, IsActive, Created/Updated
		sql = new StringBuffer ("UPDATE I_ElementValue "
			+ "SET AD_Client_ID = COALESCE (AD_Client_ID, ").append(m_AD_Client_ID).append("),"
			+ " AD_Org_ID = COALESCE (AD_Org_ID, 0),"
			+ " IsActive = COALESCE (IsActive, 'Y'),"
			+ " Created = COALESCE (Created, SysDate),"
			+ " CreatedBy = COALESCE (CreatedBy, 0),"
			+ " Updated = COALESCE (Updated, SysDate),"
			+ " UpdatedBy = COALESCE (UpdatedBy, 0),"
			+ " I_ErrorMsg = ' ',"
			+ " Processed = 'N', "
			+ " I_IsImported = 'N' "
			+ "WHERE I_IsImported<>'Y' OR I_IsImported IS NULL");
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Reset=" + no);

		//	****	Prepare	****

		//	Set Element
		if (m_C_Element_ID != 0)
		{
			sql = new StringBuffer ("UPDATE I_ElementValue "
				+ "SET ElementName=(SELECT Name FROM C_Element WHERE C_Element_ID=").append(m_C_Element_ID).append(") "
				+ "WHERE ElementName IS NULL AND C_Element_ID IS NULL"
				+ " AND I_IsImported<>'Y'").append(clientCheck);
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			log.fine("Set Element Default=" + no);
		}
		//
		sql = new StringBuffer ("UPDATE I_ElementValue i "
			+ "SET C_Element_ID = (SELECT C_Element_ID FROM C_Element e"
			+ " WHERE i.ElementName=e.Name AND i.AD_Client_ID=e.AD_Client_ID)"
			+ "WHERE C_Element_ID IS NULL"
			+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Set Element=" + no);
		//
		sql = new StringBuffer ("UPDATE I_ElementValue "
			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Element, ' "
			+ "WHERE C_Element_ID IS NULL"
			+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.config("Invalid Element=" + no);

		//	No Name, Value
		sql = new StringBuffer ("UPDATE I_ElementValue "
			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=No Name, ' "
			+ "WHERE (Value IS NULL OR Name IS NULL)"
			+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.config("Invalid Name=" + no);

		
		//	Set Column
		sql = new StringBuffer ("UPDATE I_ElementValue i "
			+ "SET AD_Column_ID = (SELECT AD_Column_ID FROM AD_Column c"
			+ " WHERE UPPER(i.Default_Account)=UPPER(c.ColumnName)"
			+ " AND c.AD_Table_ID IN (315,266) AND AD_Reference_ID=25) "
			+ "WHERE Default_Account IS NOT NULL AND AD_Column_ID IS NULL"
			+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Set Column=" + no);
		//
		sql = new StringBuffer ("UPDATE I_ElementValue "
			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Column, ' "
			+ "WHERE AD_Column_ID IS NULL AND Default_Account IS NOT NULL"
			+ " AND UPPER(Default_Account)<>'DEFAULT_ACCT'"		//	ignore default account
			+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.config("Invalid Column=" + no);

		//	Set Post* Defaults (ignore errors)
		String[] yColumns = new String[] {"PostActual", "PostBudget", "PostStatistical", "PostEncumbrance"};
		for (int i = 0; i < yColumns.length; i++)
		{
			sql = new StringBuffer ("UPDATE I_ElementValue SET ")
				.append(yColumns[i]).append("='Y' WHERE ")
				.append(yColumns[i]).append(" IS NULL OR ")
				.append(yColumns[i]).append(" NOT IN ('Y','N')"
				+ " AND I_IsImported<>'Y'").append(clientCheck);
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			log.fine("Set " + yColumns[i] + " Default=" + no);
		}
		//	Summary
		sql = new StringBuffer ("UPDATE I_ElementValue "
			+ "SET IsSummary='N' "
			+ "WHERE IsSummary IS NULL OR IsSummary NOT IN ('Y','N')"
			+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Set IsSummary Default=" + no);

		//	Doc Controlled
		sql = new StringBuffer ("UPDATE I_ElementValue "
			+ "SET IsDocControlled = CASE WHEN AD_Column_ID IS NOT NULL THEN 'Y' ELSE 'N' END "
			+ "WHERE IsDocControlled IS NULL OR IsDocControlled NOT IN ('Y','N')"
			+ " AND I_IsImported='N'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Set IsDocumentControlled Default=" + no);

		//	Check Account Type A (E) L M O R
		sql = new StringBuffer ("UPDATE I_ElementValue "
			+ "SET AccountType='E' "
			+ "WHERE AccountType IS NULL"
			+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Set AccountType Default=" + no);
		//
		sql = new StringBuffer ("UPDATE I_ElementValue "
			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid AccountType, ' "
			+ "WHERE AccountType NOT IN ('A','E','L','M','O','R')"
			+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.config("Invalid AccountType=" + no);

		//	Check Account Sign (N) C B
		sql = new StringBuffer ("UPDATE I_ElementValue "
			+ "SET AccountSign='N' "
			+ "WHERE AccountSign IS NULL"
			+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Set AccountSign Default=" + no);
		//
		sql = new StringBuffer ("UPDATE I_ElementValue "
			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid AccountSign, ' "
			+ "WHERE AccountSign NOT IN ('N','C','D')"
			+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.config("Invalid AccountSign=" + no);

		//	No Value
		sql = new StringBuffer ("UPDATE I_ElementValue "
			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=No Key, ' "
			+ "WHERE (Value IS NULL OR Value='')"
			+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.config("Invalid Key=" + no);

		//	****	Update ElementValue from existing
		sql = new StringBuffer ("UPDATE I_ElementValue i "
			+ "SET C_ElementValue_ID=(SELECT C_ElementValue_ID FROM C_ElementValue ev"
			+ " INNER JOIN C_Element e ON (ev.C_Element_ID=e.C_Element_ID)"
			+ " WHERE i.C_Element_ID=e.C_Element_ID AND i.AD_Client_ID=e.AD_Client_ID"
			+ " AND i.Value=ev.Value) "
			+ "WHERE C_ElementValue_ID IS NULL"
			+ " AND I_IsImported='N'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Found ElementValue=" + no);
		
		//  update Charge
		sql = new StringBuffer ("UPDATE I_ElementValue i "
			+ "SET C_Charge_ID = (SELECT C_Charge_ID FROM C_Charge c"
			+ " WHERE i.ChargeName=c.Name AND i.AD_Client_ID=c.AD_Client_ID)"
			+ "WHERE C_Charge_ID IS NULL"
			+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Set Charge=" + no);

		//  update Tax Category
		sql = new StringBuffer ("UPDATE I_ElementValue i "
			+ "SET C_TaxCategory_ID = (SELECT C_TaxCategory_ID FROM C_TaxCategory c"
			+ " WHERE i.TaxCategoryName=c.Name AND i.AD_Client_ID=c.AD_Client_ID)"
			+ "WHERE C_TaxCategory_ID IS NULL"
			+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Set Tax Category=" + no);
		
		commitEx();

		//	-------------------------------------------------------------------
		int noInsert = 0;
		int noUpdate = 0;

		//	Go through Records
		sql = new StringBuffer ("SELECT * "
			+ "FROM I_ElementValue "
			+ "WHERE I_IsImported='N'").append(clientCheck)
			.append(" ORDER BY I_ElementValue_ID");
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				X_I_ElementValue importElementValue = new X_I_ElementValue(getCtx(), rs, get_TrxName());
				int elementValueId = importElementValue.getC_ElementValue_ID();
				int importElementValueId = importElementValue.getI_ElementValue_ID();
				
				//	****	Create/Update ElementValue
				if (elementValueId == 0)		//	New
				{
					MElementValue elementValue = new MElementValue(importElementValue);
					if (elementValue.save())
					{
						noInsert++;
						if ( !elementValue.isSummary() )
							updateCharge(importElementValue, elementValue);
						importElementValue.setC_ElementValue_ID(elementValue.getC_ElementValue_ID());
						importElementValue.setI_IsImported(true);
						importElementValue.saveEx();
					}
					else
					{
						sql = new StringBuffer ("UPDATE I_ElementValue i "
							+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||").append(DB.TO_STRING("Insert ElementValue "))
							.append("WHERE I_ElementValue_ID=").append(importElementValueId);
						DB.executeUpdate(sql.toString(), get_TrxName());
					}
				}
				else							//	Update existing
				{
					MElementValue elementValue = new MElementValue (getCtx(), elementValueId, get_TrxName());
					if (elementValue.get_ID() != elementValueId)
					{
						
					}
					elementValue.set(importElementValue);
					if (elementValue.save())
					{
						noUpdate++;
						if (! elementValue.isSummary() )
							updateCharge(importElementValue, elementValue);
						importElementValue.setI_IsImported(true);
						importElementValue.saveEx();
					}
					else
					{
						sql = new StringBuffer ("UPDATE I_ElementValue i "
							+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||").append(DB.TO_STRING("Update ElementValue"))
							.append("WHERE I_ElementValue_ID=").append(importElementValueId);
						DB.executeUpdate(sql.toString(), get_TrxName());
					}
				}
				
			}	//	for all I_ElementValue
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			throw new Exception ("create", e);
		}

		//	Set Error to indicator to not imported
		sql = new StringBuffer ("UPDATE I_ElementValue "
			+ "SET I_IsImported='N', Updated=SysDate "
			+ "WHERE I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		addLog (0, null, new BigDecimal (no), "@Errors@");
		addLog (0, null, new BigDecimal (noInsert), "@C_ElementValue_ID@: @Inserted@");
		addLog (0, null, new BigDecimal (noUpdate), "@C_ElementValue_ID@: @Updated@");

		commitEx();

		//	*****	Set Parent
		sql = new StringBuffer ("UPDATE I_ElementValue i "
			+ "SET ParentElementValue_ID=(SELECT C_ElementValue_ID"
			+ " FROM C_ElementValue ev WHERE i.C_Element_ID=ev.C_Element_ID"
			+ " AND i.ParentValue=ev.Value AND i.AD_Client_ID=ev.AD_Client_ID) "
			+ "WHERE ParentElementValue_ID IS NULL"
			+ " AND I_IsImported='Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Found Parent ElementValue=" + no);
		//
		sql = new StringBuffer ("UPDATE I_ElementValue "
			+ "SET I_ErrorMsg=I_ErrorMsg||'Info=ParentNotFound, ' "
			+ "WHERE ParentElementValue_ID IS NULL AND ParentValue IS NOT NULL"
			+ " AND I_IsImported='Y' AND Processed='N'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.config("Not Found Parent ElementValue=" + no);
		//
		sql = new StringBuffer ("SELECT i.ParentElementValue_ID, i.I_ElementValue_ID,"
			+ " e.AD_Tree_ID, i.C_ElementValue_ID, i.Value||'-'||i.Name AS Info "
			+ "FROM I_ElementValue i"
			+ " INNER JOIN C_Element e ON (i.C_Element_ID=e.C_Element_ID) "
			+ "WHERE i.C_ElementValue_ID IS NOT NULL AND e.AD_Tree_ID IS NOT NULL"
			+ " AND i.ParentElementValue_ID IS NOT NULL"
			+ " AND i.I_IsImported='Y' AND Processed='N' AND i.AD_Client_ID=").append(m_AD_Client_ID);
		int noParentUpdate = 0;
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			ResultSet rs = pstmt.executeQuery();
			//
			String updateSQL = "UPDATE AD_TreeNode SET Parent_ID=?, SeqNo=? "
				+ "WHERE AD_Tree_ID=? AND Node_ID=?";
			//begin e-evolution vpj-cd 15 nov 2005 PostgreSQL
			//PreparedStatement updateStmt = DB.prepareStatement(updateSQL, get_TrxName());
			PreparedStatement updateStmt = DB.prepareStatement(updateSQL, ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE, get_TrxName());
			//end	
			//
			while (rs.next())
			{
				updateStmt.setInt(1, rs.getInt(1));		//	Parent
				updateStmt.setInt(2, rs.getInt(2));		//	SeqNo (assume sequenec in import is the same)
				updateStmt.setInt(3, rs.getInt(3));		//	Tree
				updateStmt.setInt(4, rs.getInt(4));		//	Node
				try
				{
					no = updateStmt.executeUpdate();
					noParentUpdate += no;
				}
				catch (SQLException ex)
				{
					log.log(Level.SEVERE, "(ParentUpdate)", ex);
					no = 0;
				}
				if (no == 0)
					log.info("Parent not found for " + rs.getString(5));
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, "(ParentUpdateLoop) " + sql.toString(), e);
		}
		addLog (0, null, new BigDecimal (noParentUpdate), "@ParentElementValue_ID@: @Updated@");

		commitEx();
		
		//	Reset Processing Flag
		sql = new StringBuffer ("UPDATE I_ElementValue "
			+ "SET Processing='-'"
			+ "WHERE I_IsImported='Y' AND Processed='N' AND Processing='Y'"
			+ " AND C_ElementValue_ID IS NOT NULL")
			.append(clientCheck);
		if (m_updateDefaultAccounts)
			sql.append(" AND AD_Column_ID IS NULL");
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Reset Processing Flag=" + no);

		if (m_updateDefaultAccounts)
			updateDefaults(clientCheck);

		//	Update Description
		sql = new StringBuffer("SELECT * FROM C_ValidCombination vc "
			+ "WHERE EXISTS (SELECT * FROM I_ElementValue i "
				+ "WHERE vc.Account_ID=i.C_ElementValue_ID)");
		
		//	Done
		sql = new StringBuffer ("UPDATE I_ElementValue "
			+ "SET Processing='N', Processed='Y'"
			+ "WHERE I_IsImported='Y'")
			.append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Processed=" + no);

		return "";
	}	//	doIt


	private void updateCharge(X_I_ElementValue impEV, MElementValue ev) {
		MCharge charge = (MCharge) impEV.getC_Charge();
		if ( charge.get_ID() == 0 && !Util.isEmpty(impEV.getChargeName()) )
		{					
			charge.setName(impEV.getChargeName());
			charge.setAD_Org_ID(0);
			if ( impEV.getC_TaxCategory_ID() == 0 )
			{
				String sql = "SELECT C_TaxCategory_ID FROM C_TaxCategory WHERE AD_Client_ID = ? ORDER BY IsDefault DESC ";
				int taxc = DB.getSQLValue(get_TrxName(), sql, m_AD_Client_ID);
				charge.setC_TaxCategory_ID(taxc);
			}
			else
			{
				charge.setC_TaxCategory_ID(impEV.getC_TaxCategory_ID());
			}
			
			charge.saveEx();
			impEV.setC_Charge_ID(charge.getC_Charge_ID());
		}

		// add/update charge accounting 
		if (ev != null && !charge.is_new())
		{ 
			// for accounting schemas
			for (MAcctSchema schema : MAcctSchema.getClientAcctSchema(getCtx(), m_AD_Client_ID) )
			{
				// with the same Account Element as the import
				if ( schema.getAcctSchemaElement(MAcctSchemaElement.ELEMENTTYPE_Account).getC_Element_ID() == ev.getC_Element_ID() )
				{
					MChargeAcct chargeacct = MChargeAcct.get(schema, charge.getC_Charge_ID(), get_TrxName());
					if (chargeacct == null)
					{
						chargeacct = new MChargeAcct(getCtx(), 0, get_TrxName());
						chargeacct.setAD_Org_ID(charge.getAD_Org_ID());
						chargeacct.setC_AcctSchema_ID(schema.getC_AcctSchema_ID());
						chargeacct.setC_Charge_ID(charge.getC_Charge_ID());
					}
					
					MAccount expenseAccount = (MAccount) chargeacct.getCh_Expense_A();
					if (expenseAccount == null)
					{
						expenseAccount = MAccount.getDefault(schema, true); //  optional null
					}
					
					if ( expenseAccount.getAccount_ID() != ev.getC_ElementValue_ID() )
					{
						MAccount account = MAccount.get(Env.getCtx(),
								charge.getAD_Client_ID(),
								charge.getAD_Org_ID(),
								schema.getC_AcctSchema_ID(),
								ev.getC_ElementValue_ID(),
								expenseAccount.getC_SubAcct_ID(),
								expenseAccount.getM_Product_ID(),
								expenseAccount.getC_BPartner_ID(),
								expenseAccount.getAD_OrgTrx_ID(),
								expenseAccount.getC_LocFrom_ID(),
								expenseAccount.getC_LocTo_ID(),
								expenseAccount.getC_SalesRegion_ID(),
								expenseAccount.getC_Project_ID(),
								expenseAccount.getC_Campaign_ID(),
								expenseAccount.getC_Activity_ID(),
								expenseAccount.getUser1_ID(),
								expenseAccount.getUser2_ID(),
								expenseAccount.getUser3_ID(),
								expenseAccount.getUser4_ID(),
								expenseAccount.getUserElement1_ID(),
								expenseAccount.getUserElement2_ID(), get_TrxName());
						
						chargeacct.setCh_Expense_Acct(account.getC_ValidCombination_ID());
						
					}
					
					MAccount revenueAccount = (MAccount) chargeacct.getCh_Revenue_A();
					if (revenueAccount == null)
					{
						revenueAccount = MAccount.getDefault(schema, true); //  optional null
					}
					
					if ( revenueAccount.getAccount_ID() != ev.getC_ElementValue_ID() )
					{
						MAccount account = MAccount.get(Env.getCtx(),
								charge.getAD_Client_ID(),
								charge.getAD_Org_ID(),
								schema.getC_AcctSchema_ID(),
								ev.getC_ElementValue_ID(),
								revenueAccount.getC_SubAcct_ID(),
								revenueAccount.getM_Product_ID(),
								revenueAccount.getC_BPartner_ID(),
								revenueAccount.getAD_OrgTrx_ID(),
								revenueAccount.getC_LocFrom_ID(),
								revenueAccount.getC_LocTo_ID(),
								revenueAccount.getC_SalesRegion_ID(),
								revenueAccount.getC_Project_ID(),
								revenueAccount.getC_Campaign_ID(),
								revenueAccount.getC_Activity_ID(),
								revenueAccount.getUser1_ID(),
								revenueAccount.getUser2_ID(),
								revenueAccount.getUser3_ID(),
								revenueAccount.getUser4_ID(),
								revenueAccount.getUserElement1_ID(),
								revenueAccount.getUserElement2_ID(), get_TrxName());
						
						chargeacct.setCh_Revenue_Acct(account.getC_ValidCombination_ID());
					}
					
					chargeacct.saveEx();
				}
			}

		}
	}

	
	/**************************************************************************
	 * 	Update Default Accounts
	 * 	@param clientCheck client where cluase
	 */
	private void updateDefaults (String clientCheck)
	{
		log.config("CreateNewCombination=" + m_createNewCombination);

		//	****	Update Defaults
		StringBuffer sql = new StringBuffer ("SELECT C_AcctSchema_ID FROM C_AcctSchema_Element "
			+ "WHERE C_Element_ID=?").append(clientCheck);
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, m_C_Element_ID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
				updateDefaultAccounts (rs.getInt(1));
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql.toString(), e);
		}

		//	Default Account		DEFAULT_ACCT
		sql = new StringBuffer ("UPDATE C_AcctSchema_Element e "
			+ "SET C_ElementValue_ID=(SELECT C_ElementValue_ID FROM I_ElementValue i"
			+ " WHERE e.C_Element_ID=i.C_Element_ID AND i.C_ElementValue_ID IS NOT NULL"
			+ " AND UPPER(i.Default_Account)='DEFAULT_ACCT') "
			+ "WHERE EXISTS (SELECT * FROM I_ElementValue i"
			+ " WHERE e.C_Element_ID=i.C_Element_ID AND i.C_ElementValue_ID IS NOT NULL"
			+ " AND UPPER(i.Default_Account)='DEFAULT_ACCT' "
			+ "	AND i.I_IsImported='Y' AND i.Processing='-')")
			.append(clientCheck);
		int no = DB.executeUpdate(sql.toString(), get_TrxName());
		addLog (0, null, new BigDecimal (no), "@C_AcctSchema_Element_ID@: @Updated@");
	}	//	updateDefaults

	/**
	 * 	Update Default Accounts.
	 *	_Default.xxxx = C_ValidCombination_ID  =>  Account_ID=C_ElementValue_ID
	 * 	@param C_AcctSchema_ID Accounting Schema
	 */
	private void updateDefaultAccounts (int C_AcctSchema_ID)
	{
		log.config("C_AcctSchema_ID=" + C_AcctSchema_ID);

		MAcctSchema as = new MAcctSchema (getCtx(), C_AcctSchema_ID, get_TrxName());
		if (as.getAcctSchemaElement("AC").getC_Element_ID() != m_C_Element_ID)
		{
			log.log(Level.SEVERE, "C_Element_ID=" + m_C_Element_ID + " not in AcctSchema=" + as);
			return;
		}

		int[] counts = new int[] {0, 0, 0};

		String sql = "SELECT i.C_ElementValue_ID, t.TableName, c.ColumnName, i.I_ElementValue_ID "
			+ "FROM I_ElementValue i"
			+ " INNER JOIN AD_Column c ON (i.AD_Column_ID=c.AD_Column_ID)"
			+ " INNER JOIN AD_Table t ON (c.AD_Table_ID=t.AD_Table_ID) "
			+ "WHERE i.I_IsImported='Y' AND Processing='Y'"
			+ " AND i.C_ElementValue_ID IS NOT NULL AND C_Element_ID=?";
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, m_C_Element_ID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				int elementValueId = rs.getInt(1);
				String TableName = rs.getString(2);
				String ColumnName = rs.getString(3);
				int importElementValueId = rs.getInt(4);
				//	Update it
				int u = updateDefaultAccount(TableName, ColumnName, C_AcctSchema_ID, elementValueId);
				counts[u]++;
				if (u != UPDATE_ERROR)
				{
					sql = "UPDATE I_ElementValue SET Processing='N' "
						+ "WHERE I_ElementValue_ID=" + importElementValueId;
					int no = DB.executeUpdate(sql.toString(), get_TrxName());
					if (no != 1)
						log.log(Level.SEVERE, "Updated=" + no);
				}
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, "", e);
		}
		addLog (0, null, new BigDecimal (counts[UPDATE_ERROR]), as.toString() + ": @Errors@");
		addLog (0, null, new BigDecimal (counts[UPDATE_YES]), as.toString() + ": @Updated@");
		addLog (0, null, new BigDecimal (counts[UPDATE_SAME]), as.toString() + ": OK");

	}	//	createDefaultAccounts


	private static final int	UPDATE_ERROR = 0;
	private static final int	UPDATE_YES = 1;
	private static final int	UPDATE_SAME = 2;

	/**
	 * 	Update Default Account.
	 *  This is the sql to delete unused accounts - with the import still in the table(!):
		DELETE C_ElementValue e
		WHERE NOT EXISTS (SELECT * FROM Fact_Acct f WHERE f.Account_ID=e.C_ElementValue_ID)
		 AND NOT EXISTS (SELECT * FROM C_ValidCombination vc WHERE vc.Account_ID=e.C_ElementValue_ID)
		 AND NOT EXISTS (SELECT * FROM I_ElementValue i WHERE i.C_ElementValue_ID=e.C_ElementValue_ID);
	 * 	@param TableName Table Name
	 * 	@param ColumnName Column Name
	 * 	@param acctSchemaId Account Schema
	 * 	@param elementValueId new Account
	 * 	@return UPDATE_* status
	 */
	private int updateDefaultAccount (String TableName, String ColumnName, int acctSchemaId, int elementValueId)
	{
		log.fine(TableName + "." + ColumnName + " - " + elementValueId);
		int retValue = UPDATE_ERROR;
		StringBuffer sql = new StringBuffer ("SELECT x.")
			.append(ColumnName).append(",Account_ID FROM ")
			.append(TableName).append(" x INNER JOIN C_ValidCombination vc ON (x.")
			.append(ColumnName).append("=vc.C_ValidCombination_ID) ")
			.append("WHERE x.C_AcctSchema_ID=").append(acctSchemaId);
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
			{
				int validCombinationId = rs.getInt(1);
				int accountId = rs.getInt(2);
				//	The current account value is the same
				if (accountId == elementValueId)
				{
					retValue = UPDATE_SAME;
					log.fine("Account_ID same as new value");
				}
				//	We need to update the Account Value
				else
				{
					if (m_createNewCombination)
					{
						MAccount account = MAccount.getValidCombination(getCtx(), validCombinationId, get_TrxName());
						account.setAccount_ID(elementValueId);
						if (account.save())
						{
							retValue = UPDATE_YES;
							int newValidCombinationId = account.getC_ValidCombination_ID();
							if (validCombinationId != newValidCombinationId)
							{
								sql = new StringBuffer ("UPDATE ").append(TableName)
									.append(" SET ").append(ColumnName).append("=").append(newValidCombinationId)
									.append(" WHERE C_AcctSchema_ID=").append(acctSchemaId);
								int no = DB.executeUpdate(sql.toString(), get_TrxName());
								log.fine("New #" + no + " - "
									+ TableName + "." + ColumnName + " - " + elementValueId
									+ " -- " + validCombinationId + " -> " + newValidCombinationId);
								if (no == 1)
									retValue = UPDATE_YES;
							}
						}
						else
							log.log(Level.SEVERE, "Account not saved - " + account);
					}
					else	//	Replace Combination
					{
						//	Only Acct Combination directly
						sql = new StringBuffer ("UPDATE C_ValidCombination SET Account_ID=")
							.append(elementValueId).append(" WHERE C_ValidCombination_ID=").append(validCombinationId);
						int no = DB.executeUpdate(sql.toString(), get_TrxName());
						log.fine("Replace #" + no + " - "
								+ "C_ValidCombination_ID=" + validCombinationId + ", New Account_ID=" + elementValueId);
						if (no == 1)
						{
							retValue = UPDATE_YES;
							//	Where Acct was used
							sql = new StringBuffer ("UPDATE C_ValidCombination SET Account_ID=")
								.append(elementValueId).append(" WHERE Account_ID=").append(accountId);
							no = DB.executeUpdate(sql.toString(), get_TrxName());
							log.fine("ImportAccount.updateDefaultAccount - Replace VC #" + no + " - "
									+ "Account_ID=" + accountId + ", New Account_ID=" + elementValueId);
							sql = new StringBuffer ("UPDATE Fact_Acct SET Account_ID=")
								.append(elementValueId).append(" WHERE Account_ID=").append(accountId);
							no = DB.executeUpdate(sql.toString(), get_TrxName());
							log.fine("ImportAccount.updateDefaultAccount - Replace Fact #" + no + " - "
									+ "Account_ID=" + accountId + ", New Account_ID=" + elementValueId);
						}
					}	//	replace combination
				}	//	need to update
			}	//	for all default accounts
			else
				log.log(Level.SEVERE, "Account not found " + sql);
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql.toString(), e);
		}

		return retValue;
	}	//	updateDefaultAccount

}
