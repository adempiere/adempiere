/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 Adempiere, Inc. All Rights Reserved.               *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *                                                                            *
 * Copyright (C) 2005 Robert Klein. robeklein@hotmail.com                     *
 * Contributor(s): Low Heng Sin hengsin@avantz.com                            *
 *****************************************************************************/
package org.adempiere.pipo.handler;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import javax.xml.transform.sax.TransformerHandler;

import org.adempiere.pipo.AbstractElementHandler;
import org.adempiere.pipo.Element;
import org.adempiere.pipo.IDFinder;
import org.adempiere.pipo.exception.POSaveFailedException;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.model.X_AD_Package_Exp_Detail;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * 
 * @author Robert Klein.
 * @author Low Heng Sin
 *
 */
public class DataElementHandler extends AbstractElementHandler {

	private PO genericPO = null;
	int AD_Backup_ID = -1;
	String objectStatus = null;
	String d_tablename = null;
	
	private DataRowElementHandler rowHandler = new DataRowElementHandler();
	private DataColumnElementHandler columnHandler = new DataColumnElementHandler();

	public DataElementHandler() {
	}

	public void startElement(Properties ctx, Element element) throws SAXException {
		String elementValue = element.getElementValue();
		Attributes atts = element.attributes;
		if (elementValue.equals("adempieredata") || elementValue.equals("data")) {
			log.info(elementValue);
			if (atts.getValue("clientname") != null) {
				int AD_Client_ID = IDFinder.get_ID("AD_Client", atts.getValue("clientname"), getClientId(ctx), getTrxName(ctx));
				Env.setContext(ctx, "AD_Client_ID", AD_Client_ID);
				log.info("adempieredata: client set to "+AD_Client_ID+" "+atts.getValue("clientname"));
			}
		}
		else if (elementValue.equals("dtable")) {
			log.info(elementValue+" "+atts.getValue("name"));
			d_tablename = atts.getValue("name");
		}
		// row element, adempieredata
		else if (elementValue.equals("drow")) {
			rowHandler.startElement(ctx, element);
		}		
		// column element, adempieredata
		else if (elementValue.equals("dcolumn")) {
			columnHandler.startElement(ctx, element);
		}
	}

	public void endElement(Properties ctx, Element element) throws SAXException {
		String elementValue = element.getElementValue();
		if (elementValue.equals("drow")) {
			rowHandler.endElement(ctx, element);
		}
	}

	class DataRowElementHandler extends AbstractElementHandler {

		public void startElement(Properties ctx, Element element) throws SAXException {
			String elementValue = element.getElementValue();
			Attributes atts = element.attributes;
			log.info(elementValue+" "+atts.getValue("name"));			

			MTable table = MTable.get(ctx, d_tablename);
			// use keyXname and lookupkeyXname if available
			if (atts.getValue("key1name") != null && atts.getValue("key1name").trim().length() > 0)
			{
				String sql = "select * from "+d_tablename;
				String whereand = " where";

				List<String>params = new ArrayList<String>(); 
				int keyIndex = 1;
				while (true) {					
					String currentKey = "key" + keyIndex + "name";
					if (atts.getValue(currentKey) != null && !atts.getValue(currentKey).equals("")) {
						params.add(atts.getValue("lookup"+currentKey));
						sql = sql+whereand+" "+atts.getValue(currentKey)+"=?";
						whereand = " and";
						keyIndex++;
					} else {
						break;
					}					
				}
				
				if (getClientId(ctx) > 0) {
					sql = sql + whereand + " " + d_tablename + ".AD_Client_ID in (0, " + getClientId(ctx) + ")";
					sql = sql + " Order by " + d_tablename + ".AD_Client_ID Desc";
				} else {
					sql = sql + whereand + " " + d_tablename + ".AD_Client_ID = 0";
				}
				
				// Load GenericPO from rs, in fact ID could not exist e.g. Attribute Value
				try {
					PreparedStatement pstmt = DB.prepareStatement(sql, getTrxName(ctx));
					for (int i = 0; i < params.size(); i++) {
						pstmt.setString(i+1, params.get(i));
					}
					
					ResultSet rs = pstmt.executeQuery();
					if (rs.next()) {
						objectStatus = "Update";
						genericPO = table.getPO(rs, getTrxName(ctx));
						rs.close();
						pstmt.close();
						pstmt = null;
					}
					else {
						genericPO = table.getPO(0, getTrxName(ctx));
						rs.close();
						pstmt.close();
						pstmt = null;
						objectStatus = "New";
					}					
				}
				catch (Exception e) {
					log.log(Level.SEVERE, e.getLocalizedMessage(), e);
				}
			} else {
				//fallback to name attribute
				String nameAttribute = atts.getValue("name");
				int id = get_ID(ctx, d_tablename, nameAttribute);
				genericPO = table.getPO(id, getTrxName(ctx));
				if (id > 0){
					AD_Backup_ID = copyRecord(ctx,d_tablename,genericPO);
					objectStatus = "Update";			
				}
				else{
					objectStatus = "New";
					AD_Backup_ID =0;
				}
			}
			
			// for debug GenericPO.
			if (false) {
				POInfo poInfo = POInfo.getPOInfo(ctx, get_ID(ctx, "AD_Table", d_tablename), getTrxName(ctx));
				if (poInfo == null)
					log.info("poInfo is null.");
				for (int i = 0; i < poInfo.getColumnCount(); i++) {
					log.info(d_tablename+" column: "+poInfo.getColumnName(i));
				}
			}
			// globalqss: set AD_Client_ID to the client setted in adempieredata
			if (getClientId(ctx) > 0 && genericPO.getAD_Client_ID() != getClientId(ctx))
				genericPO.set_ValueOfColumn("AD_Client_ID", getClientId(ctx));			
		}

		public void endElement(Properties ctx, Element element) throws SAXException {
			if (genericPO != null) {
				if (genericPO.save(getTrxName(ctx))== true)
					record_log (ctx, 1, genericPO.get_TableName(),"Data", genericPO.get_ID(),AD_Backup_ID, objectStatus,d_tablename,get_IDWithColumn(ctx, "AD_Table", "TableName", d_tablename));
				else {
					record_log (ctx, 0, genericPO.get_TableName(),"Data", genericPO.get_ID(),AD_Backup_ID, objectStatus,d_tablename,get_IDWithColumn(ctx, "AD_Table", "TableName", d_tablename));
					throw new POSaveFailedException("GenericPO");
				}
				
				genericPO = null;
			}
		}

		public void create(Properties ctx, TransformerHandler document)
				throws SAXException {
		}

	}
	
	class DataColumnElementHandler extends AbstractElementHandler {

		public void startElement(Properties ctx, Element element) throws SAXException {
			String elementValue = element.getElementValue();
			Attributes atts = element.attributes;
			log.info(elementValue+" "+atts.getValue("name"));
			String columnName = atts.getValue("name");	    	
			int tableid = get_IDWithColumn(ctx, "AD_Table", "TableName", d_tablename);
			int id =get_IDWithMasterAndColumn (ctx,"AD_Column", "ColumnName", columnName, "AD_Table", tableid);
			if (id <= 0) {
				log.log(Level.WARNING, "Column not found in AD_Column, TableName=" + d_tablename + " ColumnName=" + columnName);
				return;
			}
			StringBuffer sql = new StringBuffer ("SELECT IsUpdateable FROM AD_column WHERE AD_Column_ID = ?");
			String isUpdateable = DB.getSQLValueString(getTrxName(ctx), sql.toString(),id);
			sql = new StringBuffer ("SELECT IsKey FROM AD_column WHERE AD_Column_ID = ?");
			String isKey = DB.getSQLValueString(getTrxName(ctx), sql.toString(),id);
			if (("New".equals(objectStatus)) || (isKey.equals("N") && 
					isUpdateable.equals("Y") &&
					(!atts.getValue("name").equals("CreatedBy")||!atts.getValue("name").equals("UpdatedBy")))) {
				String value = atts.getValue("value");
				if (value != null && !value.equals("null") && value.trim().length() > 0) {
					if (atts.getValue("class").equals("String") || atts.getValue("class").equals("Text")
							|| atts.getValue("class").equals("List")|| atts.getValue("class").equals("Yes-No")				
							|| atts.getValue("class").equals("Button")
							|| atts.getValue("class").equals("Memo")|| atts.getValue("class").equals("Text Long")
							|| atts.getValue("name").equals("AD_Language") || atts.getValue("name").equals("EntityType")) {
						genericPO.set_ValueOfColumn(atts.getValue("name"), value);
					}
					else if (atts.getValue("class").equals("Number") || atts.getValue("class").equals("Amount")
							|| atts.getValue("class").equals("Quantity")|| atts.getValue("class").equals("Costs+Prices")){
						genericPO.set_ValueOfColumn(atts.getValue("name"), new BigDecimal(value));
					}
					else if (atts.getValue("class").equals("Integer") || atts.getValue("class").equals("ID")
							|| atts.getValue("class").equals("Table Direct")|| atts.getValue("class").equals("Table")
							|| atts.getValue("class").equals("Location (Address)")|| atts.getValue("class").equals("Account")
							|| atts.getValue("class").equals("Color)")|| atts.getValue("class").equals("Search")						
							|| atts.getValue("class").equals("Locator (WH)")|| atts.getValue("class").equals("Product Attribute")) {
						genericPO.set_ValueOfColumn(atts.getValue("name"), Integer.valueOf(value));
					}	
					else if (atts.getValue("class").equals("Boolean")) {
						genericPO.set_ValueOfColumn(atts.getValue("name"), new Boolean(value.equals("true") ? true : false));
					}
					else if (atts.getValue("class").equals("Date") || atts.getValue("class").equals("Date+Time")
							|| atts.getValue("class").equals("Time")) {
						genericPO.set_ValueOfColumn(atts.getValue("name"), Timestamp.valueOf(value));
					}//Binary,  Radio, RowID, Image not supported
				} else { // value is null 
					if (atts.getValue("lookupname") != null && !"".equals(atts.getValue("lookupname"))) {
						// globalqss - bring support from XML2AD to lookupname
						String m_tablename = atts.getValue("name").substring(0, atts.getValue("name").length()-3);
						genericPO.set_ValueOfColumn(atts.getValue("name"), new Integer(getIDbyName(ctx, m_tablename, atts.getValue("lookupname"))));
					} else if ( atts.getValue("sql") != null && atts.getValue("sql").trim().length() > 0) {
						String idSql = atts.getValue("sql");
						idSql = idSql.replace("@AD_Client_ID@", Integer.toString(getClientId(ctx)));
						int result = DB.getSQLValue(getTrxName(ctx), idSql);
						if (result >= 0)
							genericPO.set_ValueOfColumn(atts.getValue("name"), result);
					}
				}
			}
		}

		public void endElement(Properties ctx, Element element) throws SAXException {
		}

		public void create(Properties ctx, TransformerHandler document)
				throws SAXException {
		}

	}

	/**
	 * @param ctx
	 * @param document
	 */
	public void create(Properties ctx, TransformerHandler document)
			throws SAXException {
		String sql = Env.getContext(ctx, X_AD_Package_Exp_Detail.COLUMNNAME_SQLStatement);
		int table_id = Env.getContextAsInt(ctx, X_AD_Package_Exp_Detail.COLUMNNAME_AD_Table_ID);
		Statement stmt = DB.createStatement();
		AttributesImpl atts = new AttributesImpl();
		document.startElement("","","data",atts);		
		try {
			ResultSet rs = stmt.executeQuery(sql);		
			ResultSetMetaData meta = rs.getMetaData(); 
			int columns = meta.getColumnCount(); 
			int i = 1;
			String col_Name = null;
			String sql1 = "SELECT TableName FROM AD_Table WHERE AD_Table_ID=?";
			String table_Name = DB.getSQLValueString(null,sql1,table_id);
			atts.clear();
			atts.addAttribute("","","name","CDATA",table_Name);
			document.startElement("","","dtable",atts);
			while (rs.next()){
				atts.clear();				
				int keyIndex = 0;
				String nameatts = ""; 
				for (i=1 ;i <= columns;i++){
					col_Name = meta.getColumnName(i).toUpperCase();
					if (col_Name.equals("NAME") && rs.getObject("name") != null)
						nameatts = ""+rs.getObject("name");					
					String sql2 = "SELECT ColumnName FROM AD_Column "
						+ "WHERE isIdentifier = 'Y' AND "
						+ "AD_Table_ID = ? AND "
						+ "Upper(ColumnName)= '"+col_Name+"'";
					String cName = DB.getSQLValueString(null,sql2,table_id);
					if (cName != null){
						if (cName.toUpperCase().equals(col_Name)){
							keyIndex++;
							atts.addAttribute("","","key" + keyIndex + "name","CDATA",cName);
							atts.addAttribute("","","lookupkey" + keyIndex + "name","CDATA",""+rs.getObject(col_Name));														
						}
					}
				}
				
				//fall back to Id
				if (keyIndex == 0) {
					for (i=1 ;i <= columns;i++){
						col_Name = meta.getColumnName(i).toUpperCase();
						if (col_Name.equals("NAME") && rs.getObject("name") != null)
							nameatts = ""+rs.getObject("name");					
						String sql2 = "SELECT ColumnName FROM AD_Column "
							+ "WHERE isKey = 'Y' AND "
							+ "AD_Table_ID = ? AND "
							+ "Upper(ColumnName)= '"+col_Name+"'";
						String cName = DB.getSQLValueString(null,sql2,table_id);
						if (cName != null){
							if (cName.toUpperCase().equals(col_Name)){
								keyIndex++;
								atts.addAttribute("","","key" + keyIndex + "name","CDATA",cName);
								atts.addAttribute("","","lookupkey" + keyIndex + "name","CDATA",""+rs.getObject(col_Name));
							}
						}
					}
				}
				atts.addAttribute("","","name","CDATA",nameatts);
				if ( keyIndex == 0 ){
					atts.addAttribute("","","key1name","CDATA","");
					atts.addAttribute("","","lookupkey1name","CDATA","");
					keyIndex++;
				}	
				if ( keyIndex == 1 ){
					atts.addAttribute("","","key2name","CDATA","");
					atts.addAttribute("","","lookupkey2name","CDATA","");
				}	
				document.startElement("","","drow",atts);				
				for (i=1 ;i <= columns;i++){
					atts.clear();
					col_Name = meta.getColumnName(i).toUpperCase();
					String sql2 = "Select A.ColumnName, B.Name, A.AD_Reference_ID, A.AD_Reference_Value_ID "
						+ "From AD_Column A, AD_Reference B " 
						+ "Where Upper(A.columnname) = ? and " 
						+ "A.AD_TABLE_ID = ? and " 
						+ "A.AD_Reference_ID = B.AD_Reference_ID "
						+ "AND A.ColumnName != 'AD_Client_ID' ";
					PreparedStatement pstmt = null;
					try
					{
						pstmt = DB.prepareStatement(sql2, getTrxName(ctx));
						pstmt.setString(1, col_Name);
						pstmt.setInt(2, table_id);
						ResultSet rs1 = pstmt.executeQuery();						
						while (rs1.next()){
							//added 9/3/05
							atts.clear();
							atts.addAttribute("","","name","CDATA", rs1.getString("ColumnName"));							
							atts.addAttribute("","","class","CDATA", rs1.getString("Name"));
							
							//rs1.getString("ColumnName") 				: NAME / VALUE
							//rs1.getString("Name")       				: STRING
							//rs1.getString("AD_Reference_ID")       	: number (table/table direct/ search)
							//rs1.getString("AD_Reference_Value_ID")    : number (null when type is table direct)
							
							if (rs1.getInt("AD_Reference_ID") == DisplayType.Table || rs1.getInt("AD_Reference_ID") == DisplayType.Search 
									|| rs1.getInt("AD_Reference_ID") == DisplayType.TableDir ){
								String tableName = "";
														
								if (rs1.getInt("AD_Reference_ID") == DisplayType.TableDir){
									//if rs1.getString("ColumnName") = C_EDIFORMAT_ID, tablename = C_EDIFORMAT
									tableName = rs1.getString("ColumnName").substring(0,rs1.getString("ColumnName").length()-3);
								}else{
									//ad_ref_table.ad_reference_id = ad_reference_value_id
									int referenceId = rs1.getInt("AD_Reference_Value_ID");

									if (referenceId != 0)
										tableName = getTableNameFromReferenceId(referenceId);
									else
										tableName = rs1.getString("ColumnName").substring(0,rs1.getString("ColumnName").length()-3);
								}
								
								if (rs.getObject(i) == null)
									atts.addAttribute("","","value","CDATA", "");
								else{
									String insertSql = getSqlFromTableName(tableName,Integer.parseInt(rs.getObject(i).toString()),ctx);
									
									String parentSql = getParentSql(tableName,ctx,Integer.parseInt(rs.getObject(i).toString()));									
									if (!parentSql.equals(""))
										insertSql = insertSql + parentSql;
									
									insertSql = insertSql + " Order By " + tableName + ".AD_Client_ID Desc ";
									
									atts.addAttribute("","","value","CDATA", "");
									atts.addAttribute("", "", "sql", "CDATA",  insertSql);
								}
								
							} else {
								if (rs1.getString("Name").equals("Date")||rs1.getString("Name").equals("Date+Time")||rs1.getString("Name").equals("Time"))
									atts.addAttribute("","","value","CDATA", "" + rs.getTimestamp(i));
								else
									atts.addAttribute("","","value","CDATA", "" + rs.getObject(i));																
							}
							if (!rs1.getString("ColumnName").equals("Created")&&!rs1.getString("ColumnName").equals("CreatedBy")&&
									!rs1.getString("ColumnName").equals("Updated")&&!rs1.getString("ColumnName").equals("UpdatedBy")){
								document.startElement("","","dcolumn",atts);
								document.endElement("","","dcolumn");
							}
						}					
						rs1.close();
						pstmt.close();
						pstmt = null;
					}	
					catch (Exception e)	{
						log.log(Level.SEVERE,"getData", e);
					}
				}
				document.endElement("","","drow");	
			}
			rs.close();
			stmt.close();
			stmt = null;
			document.endElement("","","dtable");
		}	
		
		catch (Exception e)	{
			log.log(Level.SEVERE,"getData", e);
		}
		
		document.endElement("","","data");
		
	}
	
	private String getTableNameFromReferenceId(int referenceId){
		String tableName = "";
		String sql = " SELECT t.TableName FROM AD_Table T, AD_Ref_Table R "
						+" WHERE T.AD_Table_ID=R.AD_Table_ID AND R.AD_Reference_ID=? ";
		tableName= DB.getSQLValueString(null,sql,referenceId);
			
		return tableName;
	}
	
	private String getSqlFromTableName(String tableName, int id, Properties ctx) throws Exception{
		String result = null;
		
		String sql = "SELECT count(a.ad_table_id) FROM ad_table a INNER JOIN ad_column b ON (a.ad_table_id = b.ad_table_id) "
			+ "AND a.tablename = '" + tableName + "' AND UPPER(b.columnname) = 'VALUE'";
		int count = DB.getSQLValue(null, sql);
		
		String insertSqlValue = count <= 0 
			? ( "SELECT Name FROM "+tableName+" WHERE "+tableName+"_ID=?" )
			: ( "SELECT Value FROM "+tableName+" WHERE "+tableName+"_ID=?" );
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			pstmt = DB.prepareStatement(insertSqlValue, getTrxName(ctx));
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();						
			if (rs.next()){
				
				if (count <= 0)
					result = "SELECT " +tableName+"_ID FROM "+tableName+" WHERE Name='"+rs.getString(1)+"'";
				else
					result = "SELECT " +tableName+"_ID FROM "+tableName+" WHERE Value='"+rs.getString(1)+"'";
				result = result + " AND " + tableName + ".AD_Client_ID IN ( @AD_Client_ID@ , 0 )";
			}
				
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		} finally {
			DB.close(rs, pstmt);
		}
			
		return result;
	}
	
	private String getParentSql(String tableName, Properties ctx, int recordId) throws Exception{
		String parentSql = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			String sql = "SELECT A.ColumnName, A.AD_Reference_ID, A.AD_Reference_Value_ID "
						+ "FROM AD_Column A, AD_Table T " 
						+ "Where A.AD_TABLE_ID = T.AD_Table_ID AND A.IsParent = 'Y' "
						+ "AND UPPER(T.TableName)=? "
						+ "AND A.ColumnName != 'AD_Client_ID' ";
			
			pstmt = DB.prepareStatement(sql, getTrxName(ctx));
			pstmt.setString(1,tableName.toUpperCase());
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				if (rs.getInt("AD_Reference_ID") == DisplayType.Table || rs.getInt("AD_Reference_ID") == DisplayType.Search || rs.getInt("AD_Reference_ID") == DisplayType.TableDir){
					String parentTableName = "";
					
					if (rs.getInt("AD_Reference_ID") == DisplayType.TableDir){
						//if rs1.getString("ColumnName") = C_EDIFORMAT_ID, tablename = C_EDIFORMAT
						parentTableName = rs.getString("ColumnName").substring(0,rs.getString("ColumnName").length()-3);
					}else{
						//ad_ref_table.ad_reference_id = ad_reference_value_id
						int referenceId = rs.getInt("AD_Reference_Value_ID");
						parentTableName = getTableNameFromReferenceId(referenceId);
					}
					
					String parentIdSql = "SELECT "+parentTableName+"_ID FROM "+tableName+" WHERE "+tableName+"_ID = "+recordId;
					int parentRecordId = DB.getSQLValue(null, parentIdSql);
					
					parentSql = " AND "+rs.getString("ColumnName")+ " IN (" +getParentSql(parentTableName, ctx, parentRecordId)+")";
					parentSql = parentSql + " AND " + tableName + ".AD_Client_ID IN ( @AD_Client_ID@ , 0 )";
				}
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		} finally{
			DB.close(rs, pstmt);
		}
		
		return parentSql;
	}	
}

 	  	 
