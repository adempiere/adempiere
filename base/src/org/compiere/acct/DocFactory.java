/*************************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                              *
 * This program is free software; you can redistribute it and/or modify it    		 *
 * under the terms version 2 or later of the GNU General Public License as published *
 * by the Free Software Foundation. This program is distributed in the hope   		 *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied 		 *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           		 *
 * See the GNU General Public License for more details.                       		 *
 * You should have received a copy of the GNU General Public License along    		 *
 * with this program; if not, write to the Free Software Foundation, Inc.,    		 *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     		 *
 * For the text or an alternative of this public license, you may reach us    		 *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, S.A. All Rights Reserved. *
 * Contributor(s): Carlos Parada www.erpya.com				  		                 *
 *************************************************************************************/
package org.compiere.acct;

import java.lang.reflect.Constructor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MColumn;
import org.compiere.model.MDocBaseType;
import org.compiere.model.MDocType;
import org.compiere.model.MTable;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Factory of Accounting Documents
 * @author Carlos Parada, cparada@erpya.com , http://www.erpya.com
 *
 */
public class DocFactory {

	/**Logger*/
	private static CLogger	s_log = CLogger.getCLogger(DocFactory.class);
	/**Accounting Schemes*/
	private MAcctSchema[] accountingSchemes = null;
	/**Table Identifier*/
	private int tableID = 0;
	/**Record Identifier*/
	private int recordID = 0;
	/**Transaction Name*/
	private String trxName = null;
	/**Resultset of PO Document*/
	private ResultSet resultSet = null;
	
	/**
	 * Constructor
	 */
	public DocFactory() {
	}//DocFactory
	
	/**
	 * Set Accounting Schemes
	 * @param accountingSchemes
	 * @return
	 */
	public DocFactory withAccountingSchemes(MAcctSchema[] accountingSchemes) {
		this.accountingSchemes = accountingSchemes;
		return this;
	}	//withAccountingSchemes
	
	/**
	 * Set Table Identifier
	 * @param tableID
	 * @return
	 */
	public DocFactory withTableID(int tableID) {
		this.tableID = tableID;
		return this;
	}	//withTableID
	
	/**
	 * Set Record Identifier
	 * @param recordID
	 * @return
	 */
	public DocFactory withRecordID(int recordID) {
		this.recordID = recordID;
		return this;
	}	//withTrxName
	
	/**
	 * Set Transaction Name
	 * @param trxName
	 * @return
	 */
	public DocFactory withTrxName(String trxName) {
		this.trxName = trxName;
		return this;
	}	//withTrxName
	
	/**
	 * Set ResultSet of PO Document
	 * @param resultSet
	 * @return
	 */
	public DocFactory withResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
		return this;
	}	//withResultSet
	
	/**
	 * Get Doc Class Instance
	 * @return
	 * @throws AdempiereUserError
	 */
	public Doc get() {
		Doc doc = null;
		
		if (accountingSchemes != null) {
			if (tableID > 0) {
				if (resultSet != null)
					doc = getByResultSet();
				
				if (doc==null
						&& recordID > 0)
					doc = getByID();
			}
		}
		
		return doc;
	}	//get
	
	/**
	 * Get Doc Class Instance by Record Identifier
	 * @return
	 */
	private Doc getByID()
	{
		String TableName = null;
		for (int i = 0; i < Doc.getDocumentsTableID().length; i++)
		{
			if (Doc.getDocumentsTableID()[i] == tableID)
			{
				TableName = Doc.getDocumentsTableName()[i];
				break;
			}
		}
		if (TableName == null)
		{
			s_log.severe("Not found AD_Table_ID=" + tableID);
			return null;
		}
		//
		Doc doc = null;
		StringBuffer sql = new StringBuffer("SELECT * FROM ")
			.append(TableName)
			.append(" WHERE ").append(TableName).append("_ID=? AND Processed='Y'");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql.toString(), trxName);
			pstmt.setInt (1, recordID);
			rs = pstmt.executeQuery ();
			if (rs.next ())
			{
				resultSet = rs;
				doc = getByResultSet ();
			}
			else
				s_log.severe("Not Found: " + TableName + "_ID=" + recordID);
		}
		catch (Exception e)
		{
			s_log.log (Level.SEVERE, sql.toString(), e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; 
			pstmt = null;
		}
		return doc;
	}	//	getByID
	
	/**
	 * Get Doc Class Instance by ResultSet of PO Document
	 * @return
	 * @throws AdempiereUserError
	 */
	private Doc getByResultSet () {
		Doc doc = null;
		
		AtomicReference<String> className = new AtomicReference<String>(null);
		
		/* Classname of the Doc class follows this convention:
		 * if the prefix (letters before the first underscore _) is 1 character, then the class is Doc_TableWithoutPrefixWithoutUnderscores
		 
		 * otherwise Doc_WholeTableWithoutUnderscores
		 * i.e. following this query
              SELECT t.ad_table_id, tablename, 
              	CASE 
              		WHEN instr(tablename, '_') = 2 
              		THEN 'Doc_' || substr(tablename, 3) 
              		WHEN instr(tablename, '_') > 2 
              		THEN 'Doc_' || 
              		ELSE '' 
              	REPLACE
              		(
              			tablename, '_', ''
              		)
              	END AS classname 
              FROM ad_table t, ad_column C 
              WHERE t.ad_table_id = C.ad_table_id AND
              	C.columnname = 'Posted' AND
              	isview = 'N' 
              ORDER BY 1
		 * This is:
		 * 224		GL_Journal			Doc_GLJournal
		 * 259		C_Order				Doc_Order
		 * 318		C_Invoice			Doc_Invoice
		 * 319		M_InOut				Doc_InOut
		 * 321		M_Inventory			Doc_Inventory
		 * 323		M_Movement			Doc_Movement
		 * 325		M_Production		Doc_Production
		 * 335		C_Payment			Doc_Payment
		 * 392		C_BankStatement		Doc_BankStatement
		 * 407		C_Cash				Doc_Cash
		 * 472		M_MatchInv			Doc_MatchInv
		 * 473		M_MatchPO			Doc_MatchPO
		 * 623		C_ProjectIssue		Doc_ProjectIssue
		 * 702		M_Requisition		Doc_Requisition
		 * 735		C_AllocationHdr		Doc_AllocationHdr
		 * 53027	PP_Order			Doc_PPOrder
		 * 53035	PP_Cost_Collector	Doc_PPCostCollector
		 * 53037	DD_Order			Doc_DDOrder
		 * 53092	HR_Process			Doc_HRProcess
		 */
		Optional<MTable> maybeTable= Optional.ofNullable(MTable.get(Env.getCtx(), MDocBaseType.Table_Name)) ;
		maybeTable.ifPresent(table ->{
			Optional<MColumn> maybeColumn = Optional.ofNullable(table.getColumn(MDocBaseType.COLUMNNAME_C_DocBaseType_ID));
					maybeColumn.ifPresent(columnDocBaseType->{
						if (columnDocBaseType.getAD_Column_ID() > 0) {
							int C_DocType_ID = 0; 
							try {
								C_DocType_ID = resultSet.getInt(MDocType.COLUMNNAME_C_DocType_ID);
							}catch(Exception e) {
								s_log.warning(e.getMessage());
								C_DocType_ID = 0;
							}finally{
								Optional<MDocBaseType> maybeDocBaseType = Optional.ofNullable(MDocBaseType.get(C_DocType_ID, tableID));
								maybeDocBaseType.ifPresent(docBaseType -> className.set(docBaseType.getAccountingClassname()));
							}
						}
					});
					
				});
		
		if (className.get()==null
				|| (className.get()!=null 
						&& className.get().isEmpty())) {
			String tableName = MTable.getTableName(Env.getCtx(), tableID);
			String packageName = "org.compiere.acct";

			int firstUnderscore = tableName.indexOf("_");
			if (firstUnderscore == 1)
				className.set(packageName + ".Doc_" + tableName.substring(2).replaceAll("_", ""));
			else
				className.set(packageName + ".Doc_" + tableName.replaceAll("_", ""));
		}
		
		try
		{
			Class<?> cClass = Class.forName(className.get());
			Constructor<?> cnstr = cClass.getConstructor(new Class[] {MAcctSchema[].class, ResultSet.class, String.class});
			doc = (Doc) cnstr.newInstance(accountingSchemes, resultSet, trxName);
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, "Doc Class invalid: " + className + " (" + e.toString() + ")");
			throw new AdempiereException("Doc Class invalid: " + className + " (" + e.toString() + ")");
		}
		
		if (doc == null)
			s_log.log(Level.SEVERE, "Unknown AD_Table_ID=" + tableID);
		return doc;
	}   //  getByResultSet
}//DocFactory