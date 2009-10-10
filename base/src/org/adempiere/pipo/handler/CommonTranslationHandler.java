package org.adempiere.pipo.handler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import javax.xml.transform.sax.TransformerHandler;

import org.adempiere.pipo.AbstractElementHandler;
import org.adempiere.pipo.AttributeFiller;
import org.adempiere.pipo.Element;
import org.adempiere.pipo.ElementHandler;
import org.compiere.model.MSysConfig;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

public class CommonTranslationHandler extends AbstractElementHandler implements ElementHandler{
	
	public static final String CONTEXT_KEY__PARENT_TABLE = "currentParentTableForTranslation";
	public static final String CONTEXT_KEY__PARENT_RECORD_ID = "currentParentTableRecordID_ForTranslation";
	
	public static final String SPECIAL_ATRRIBUTE__TABLE_NAME = "ParentTable";
	

	private HashMap<String, ArrayList<String>> cached_PIPO_ColumnsByTable = new HashMap<String, ArrayList<String>>();//Key: table name. Value: set of PIPO columns
	
	
	public void startElement(Properties ctx, Element element) throws SAXException {
		
		if(! MSysConfig.getBooleanValue("2PACK_HANDLE_TRANSLATIONS", false)){
			return;//translation import option is disabled
		}
		
		if(element.parent.skip){
			return;
		}
		
		if(element.parent.defer){
			element.defer = true;
			return;
		}
		
		String elementValue = element.getElementValue();
		Attributes atts = element.attributes;
		int parentID = element.parent.recordId;
		
		if(parentID ==0)
			throw new SAXException();
			
		String parentTable = atts.getValue(SPECIAL_ATRRIBUTE__TABLE_NAME);
		String language = atts.getValue("AD_Language");
		
		log.info(elementValue+" "+parentTable+" "+atts.getValue("Name"));
		
		if(isRecordExists(parentTable, parentID, language, ctx)){
			
			updateTranslation(parentTable, parentID, ctx, atts);
		}else{
			insertTranslation(parentTable, parentID, ctx, atts);
		}
	}
	

	private boolean isRecordExists(String parentTable, int parentID,
			String language, Properties ctx) {
		
		String sql = 
			"select ad_client_id from "+parentTable +"_trl where "+
			parentTable+"_ID="+parentID+" and ad_language = '"+language+"'";
		
		if(DB.getSQLValue(getTrxName(ctx), sql) == -1){
			return false;
		}else{
			return true;
		}
	}
	
	
	private void insertTranslation(String parentTable, int parentID,
			Properties ctx, Attributes atts) throws SAXException{
		
		ArrayList<String> pipoColumns = getExportableColumns(parentTable);
		
		
		StringBuffer sql = new StringBuffer(
			"insert into "+parentTable+"_trl ("+parentTable+"_ID, "+
			" ad_client_ID, ad_org_id, CreatedBy, UpdatedBy, "+cast(pipoColumns)+
			") values ( ?, ?, ?, ?, ? ");
		
		
		for (int i = 0; i<pipoColumns.size(); i++) {

			sql.append(",?");
		}
		
		sql.append(")");
		
		PreparedStatement pstm = DB.prepareStatement(sql.toString(), getTrxName(ctx));
		
		try {			
			pstm.setInt(1, parentID);
			pstm.setInt(2, 0);
			pstm.setInt(3, 0);
			pstm.setInt(4, 0);
			pstm.setInt(5, 0);
			
			int i = 5;
			for (String columnName : pipoColumns) {
				
				i++;

				String value = atts.getValue(columnName);
				
				if(columnName.equalsIgnoreCase("IsActive") ||
						columnName.equalsIgnoreCase("IsTranslated")){

					value = "true".equals(value) ? "Y" : "N";
				}
				
				pstm.setString(i, value);
				
			}
			
			if(pstm.executeUpdate()<0){
				throw new SAXException();
			}
			pstm.close();
		
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new SAXException();
		}
	}

	
	private void updateTranslation(String parentTable, int parentID,
			Properties ctx, Attributes atts) throws SAXException{
		

		ArrayList<String> pipoColumns = getExportableColumns(parentTable);
		
		StringBuffer sqlBuf = new StringBuffer("update "+parentTable+"_trl set ");
		
		
		for (String columnName : pipoColumns) {

			sqlBuf.append(columnName).append("=?,");
		}
		
		String sql =  sqlBuf.substring(0, sqlBuf.length()-1);
		
		sql += " where ad_language = '"+atts.getValue("AD_Language")+
		"' and "+parentTable+"_ID="+parentID;
		
		try {
			PreparedStatement pstm = DB.prepareStatement(sql,
					getTrxName(ctx));

			int i=0;
			for (String columnName : pipoColumns) {

				String value = atts.getValue(columnName);
				i++;

				if(columnName.equalsIgnoreCase("IsActive") ||
						columnName.equalsIgnoreCase("IsTranslated")){

					value = "true".equals(value) ? "Y" : "N";
				}

				pstm.setString(i, value);
			}

			if(pstm.executeUpdate()<0){
				throw new SAXException();
			}
			pstm.close();

		} catch (Exception e) {
			e.printStackTrace();
			throw new SAXException();
		}
	}


	public void endElement(Properties ctx, Element element) throws SAXException {
		
	}
	
	
	public void create(Properties ctx, TransformerHandler document) throws SAXException {
		
		String parenTableName = Env.getContext(ctx, CONTEXT_KEY__PARENT_TABLE);
		
		int parentRecordID = Env.getContextAsInt(ctx, CONTEXT_KEY__PARENT_RECORD_ID);
		
		createTranslationTags(parenTableName, parentRecordID, document);
	}
	

	private void createTranslationTags(String parentTable,
			int parentRecordID, TransformerHandler document) throws SAXException {
	
		ArrayList<String> exportableColumns = getExportableColumns(parentTable);
		
		String sql = 
			"select "+cast(exportableColumns)+" from "+parentTable+"_trl where "+
			parentTable+"_ID="+parentRecordID;
		
		PreparedStatement pstm = DB.prepareStatement(sql, null);
		try {
			
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()){
				
				AttributesImpl atts = getAttsForOneTrlRow(exportableColumns, rs);
				
				atts.addAttribute("", "", SPECIAL_ATRRIBUTE__TABLE_NAME, "CDATA", parentTable);
				
				document.startElement("", "", "trl", atts);
				document.endElement("", "", "trl");
			}
			
			rs.close();
			pstm.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new SAXException();
		}
	}

	private AttributesImpl getAttsForOneTrlRow(ArrayList<String> exportableColumns,
			ResultSet rs) throws Exception {

		AttributesImpl atts = new AttributesImpl();
		AttributeFiller af = new AttributeFiller(atts);
		
		for (String columnName : exportableColumns) {
			
			if(columnName.equalsIgnoreCase("IsActive")||
					columnName.equalsIgnoreCase("IsTranslated")){

				af.addBoolean(columnName, rs.getString(columnName).equalsIgnoreCase("Y"));

			}else{

				af.addString(columnName, rs.getString(columnName));
			}
		}
		
		return atts;
	}

	/**
	 * 
	 * @param parentTable
	 * @return
	 * @throws SAXException
	 */
	@SuppressWarnings("unchecked")
	private ArrayList<String> getExportableColumns(String parentTable) throws SAXException {
		
		
		Object pipolColumns = cached_PIPO_ColumnsByTable.get(parentTable);
		if(pipolColumns != null){
			return (ArrayList<String>)pipolColumns;
		}
		
		ArrayList<String> new_PIPO_Columns = new ArrayList<String>();
		String sql = "select * from ad_column where ad_table_id = " +
				"(select ad_table_id from ad_table where tableName = ?)" +
				"and isTranslated='Y'"
				+" ORDER BY AD_Column_ID";
		
		PreparedStatement pstm = DB.prepareStatement(sql, null);
		try {
			
			pstm.setString(1, parentTable);
			
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				
				new_PIPO_Columns.add(rs.getString("columnName"));
			}
			
			pstm.close();
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SAXException();
		}

		new_PIPO_Columns.add("AD_Language");
		new_PIPO_Columns.add("IsActive");
		new_PIPO_Columns.add("IsTranslated");
		
		//Putting in cache
		cached_PIPO_ColumnsByTable.put(parentTable, new_PIPO_Columns);
		
		return (ArrayList<String>)new_PIPO_Columns;
	}

	private String cast(ArrayList<String> arg){
		
		return arg.toString().substring(1,  arg.toString().length()-1);
	}

}