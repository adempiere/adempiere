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
package org.adempiere.pipo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;

import javax.xml.transform.sax.TransformerHandler;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.model.X_AD_Package_Imp_Detail;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Util;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

public abstract class AbstractElementHandler implements ElementHandler {

	protected CLogger log = CLogger.getCLogger("PackIn");
	
	/**
	 * Get ID from Name for a table.
	 * TODO: substitute with PO.getAllIDs
	 *
	 * @param tableName
	 * @param name
	 * 
	 */
	public int get_ID (Properties ctx, String tableName, String name) {
		return IDFinder.get_ID(tableName, name, getClientId(ctx), getTrxName(ctx));
	}

	/**
	 * Get ID from UUID for a table.
	 *
	 * @param tableName
	 * @param columName
	 * @param name
	 */
	public int getIdFromUUID(Properties ctx, String tableName, String uuid) {
		return IDFinder.getIdFromUUID(ctx, tableName, uuid, getClientId(ctx), getTrxName(ctx));
	}
	
	/**
	 * Get UUID from id
	 * @param ctx
	 * @param tableName
	 * @param value
	 * @return
	 */
	public String getUUIDFromId(Properties ctx, String tableName, int value) {
		return IDFinder.getUUIDFromId(tableName, value, getClientId(ctx), getTrxName(ctx));
	}
	
	/**
	 * Get ID from column value for a table.
	 *
	 * @param tableName
	 * @param columName
	 * @param name
	 */
	public int get_IDWithColumn (Properties ctx, String tableName, String columnName, Object value) {
		return IDFinder.get_IDWithColumn(tableName, columnName, value, getClientId(ctx), getTrxName(ctx));
	}
	
	/**
     *	Write results to log and records in history table
     *
     *      @param success
     * 		@param tableName
     * 		@param objectType
     * 		@param objectID
     * 		@param objectStatus
     * 		@throws SAXException
     *       	
     */
    public int recordLog (Properties ctx, int success, String objectName,String objectType, int objectID,
    		int objectIDBackup, String objectStatus, String tableName, int AD_Table_ID) throws SAXException{    	
    	StringBuffer recordLayout = new StringBuffer();
    	int id = 0;
    	TransformerHandler hd_document = getLogDocument(ctx);
		AttributesImpl attsOut = new AttributesImpl();
		String result = success == 1 ? "Success" : "Failure";
    	
		recordLayout.append("Type:")
    			.append(objectType)
    			.append("  -   Name:")
    			.append(objectName)
    			.append("  -  ID:")
    			.append(objectID)
    			.append("  -  Action:")
    			.append(objectStatus)
    			.append("  -  " + result);
    		
    		hd_document.startElement("","",result,attsOut);
    		hd_document.characters(recordLayout.toString().toCharArray(),0,recordLayout.length());
    		hd_document.endElement("","",result);
    		
    		X_AD_Package_Imp_Detail detail = new X_AD_Package_Imp_Detail(ctx, 0, getTrxName(ctx));
    		detail.setAD_Package_Imp_ID(getPackageImpId(ctx));
    		detail.setAD_Org_ID(Env.getAD_Org_ID(ctx) );
    		detail.setType(objectType);
    		detail.setName(objectName);
    		detail.setAction(objectStatus);
    		detail.setSuccess(result);
    		detail.setAD_Original_ID(objectID);
    		detail.setAD_Backup_ID(objectIDBackup);
    		detail.setTableName(tableName);
    		detail.setAD_Table_ID(AD_Table_ID);
    		detail.saveEx(getTrxName(ctx));
    		id = detail.get_ID();
    	
    	return id;  
    }
    
    /**
	 * Get ID from Name for a table with a Master reference.
	 *
	 * @param tableName
	 * @param name
	 * @param tableNameMaster
	 * @param nameMaster
	 */
	public int get_IDWithMaster (Properties ctx, String tableName, String name, String tableNameMaster, String nameMaster) {
		return IDFinder.get_IDWithMaster(tableName, name, tableNameMaster, nameMaster, getTrxName(ctx));
	}

    /**
     * Get ID from Name for a table with a Master reference.
     *
     * @param tableName
     * @param name
     * @param tableNameMaster
     * @param nameMaster
     */    
    
	public int get_IDWithMasterAndColumn (Properties ctx, String tableName, String columnName, String name, String tableNameMaster, int masterID) {
		return IDFinder.get_IDWithMasterAndColumn(tableName, columnName, name, tableNameMaster, masterID, 
				getTrxName(ctx));
	}

	/**
	 * Get ID from Name for a table with a Master reference ID.
	 *
	 * @param tableName
	 * @param name
	 * @param tableNameMaster
	 * @param masterID
	 */    
	public int get_IDWithMaster (Properties ctx, String tableName, String name, String tableNameMaster, int masterID) {
		return IDFinder.get_IDWithMaster(tableName, name, tableNameMaster, masterID, getTrxName(ctx));
	}

	/**
	 * Get ID from Name for a table.
	 * TODO: substitute with PO.getAllIDs
	 *
	 * @param tableName
	 * @param name
	 */
	public int getIDbyName (Properties ctx, String tableName, String name) {
		return IDFinder.getIDbyName(tableName, name, getClientId(ctx), getTrxName(ctx));
	}
	
	/**
	 * Get ID from Value for a table.
	 * TODO: substitute with PO.getAllIDs
	 *
	 * @param tableName
	 * @param name
	 */
	public int getIDbyValue (Properties ctx, String tableName, String value) {
		return IDFinder.getIDbyValue(tableName, value, getClientId(ctx), getTrxName(ctx));
	}
	
    /**
     *	Make backup copy of record.
     *
     *      @param tablename
     *  	
     *  	
     *       	
     */
    public int copyRecord(Properties ctx, String tableName, PO from){
    	int idBackup = 0;
    	String colValue=null;
    	int tableId = get_IDWithColumn(ctx, "AD_Table", "TableName", tableName);    	
		POInfo poInfo = POInfo.getPOInfo(ctx, tableId, getTrxName(ctx));
		for (int i = 0; i < poInfo.getColumnCount(); i++){
			colValue = null;
			int columnId = poInfo.getAD_Column_ID(poInfo.getColumnName(i));
		    StringBuffer sqlD = new StringBuffer();
    		int referenceId = poInfo.getColumnDisplayType(i);
    		
    		idBackup = DB.getNextID (getClientId(ctx), "AD_Package_Imp_Backup", getTrxName(ctx));
    		
    		sqlD = new StringBuffer("SELECT MAX(AD_PACKAGE_IMP_DETAIL_ID) FROM AD_PACKAGE_IMP_DETAIL");
    		int idDetail = DB.getSQLValue(getTrxName(ctx),sqlD.toString())+1;
    		
    		if (referenceId == 10 || referenceId == 14 || referenceId == 34 || referenceId == 17)
    			if (from != null && from.get_Value(i)!= null)
    				colValue = from.get_Value(i).toString().replaceAll("'","''");	    		
			else if (referenceId == 20|| referenceId == 28)
				if (from != null && from.get_Value(i)!= null)	    				    				
    				colValue = from.get_Value(i).toString().replaceAll("'","''");
			else
				;//Ignore
    			    		
    		StringBuffer sqlB = new StringBuffer ("INSERT INTO AD_Package_Imp_Backup") 
    				.append( "(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, " ) 
    				.append( "AD_PACKAGE_IMP_BACKUP_ID, AD_PACKAGE_IMP_DETAIL_ID, AD_PACKAGE_IMP_ID," ) 
    				.append( " AD_TABLE_ID, AD_COLUMN_ID, AD_REFERENCE_ID, COLVALUE)" )
    				.append( "VALUES(" )
    				.append( " "+ Env.getAD_Client_ID(ctx) )
    				.append( ", "+ Env.getAD_Org_ID(ctx) )
    				.append( ", "+ Env.getAD_User_ID(ctx) )
    				.append( ", "+ Env.getAD_User_ID(ctx) )
					.append( ", " + idBackup )
					.append( ", " + idDetail )
    				.append( ", " + getPackageImpId(ctx) )
    				.append( ", " + tableId )
    				.append( ", " + (columnId == -1 ? "null" : columnId) )
    				.append( ", " + (referenceId == -1 ? "null" : referenceId) )
    				.append( ", '" + (colValue != null ? colValue : (from != null ? from.get_Value(i) : "null")) )
    				.append( "')");
    		
    		int no = DB.executeUpdate (sqlB.toString(), getTrxName(ctx));
    		if (no == -1)
				log.info("Insert to import backup failed");
		}		
		return idBackup;
    }
    
    /**
     *	Open input file for processing
     *
     * 	@param String file with path
     * 	
     */
    public FileInputStream OpenInputfile (String filePath) {
    	
    	FileInputStream fileTarget = null;
    	
    	try {    	
    		fileTarget = new FileInputStream(filePath);
    	}
    	catch (FileNotFoundException e ) {
    		System.out.println("File not found: " + filePath);
    		
    		return null;
    	}
    	return fileTarget;
    }
    
    /**
     *	Open output file for processing
     *
     * 	@param String file with path
     * 	
     */
    public OutputStream OpenOutputfile (String filePath) {
    	
    	OutputStream fileTarget = null;
    	
    	try {    	
    		fileTarget = new FileOutputStream(filePath);
    	}
    	catch (FileNotFoundException e ) {
    		System.out.println("File not found: " + filePath);
    		
    		return null;
    	}
    	return fileTarget;
    }
    
    /**
     *	Copyfile
     *
     * 	@param String file with path
     * 	
     */
    public int copyFile (InputStream source,OutputStream target) {
    
    	 int byteCount = 0;
    	 int success = 0;
	        try {
	           while (true) {
	              int data = source.read();
	              if (data < 0)
	                 break;
	              target.write(data);
	              byteCount++;
	           }
	           source.close();
	           target.close();
	           //System.out.println("Successfully copied " + byteCount + " bytes.");
	        }
	        catch (Exception e) {
	           System.out.println("Error occurred while copying.  "+ byteCount + " bytes copied.");
	           log.log(Level.SEVERE, e.getLocalizedMessage(), e);
	           
	           success = -1;
	        }
	    return success;
    }
    
    /**
     * Get client id
     * @param ctx
     * @return int
     */
    protected int getClientId(Properties ctx) {
    	return Env.getContextAsInt(ctx, "AD_Client_ID");
    }
    
    /**
     * Get AD_Package_Imp_ID
     * @param ctx
     * @return int
     */
    protected int getPackageImpId(Properties ctx) {
    	return Env.getContextAsInt(ctx, "AD_Package_Imp_ID");
    }
    
    /**
     * Get update system maintained dictionary flag
     * @param ctx
     * @return update mode
     */
    protected String getUpdateMode(Properties ctx) {
    	return Env.getContext(ctx, "UpdateMode");
    }
    
    /**
     * Get current transaction name
     * @param ctx
     * @return transaction name
     */
    protected String getTrxName(Properties ctx) {
    	return Env.getContext(ctx, "TrxName");
    }
    
    /**
     * Get share document
     * @param ctx
     * @return TransformerHandler 
     */
    protected TransformerHandler getLogDocument(Properties ctx) {
    	return (TransformerHandler)ctx.get("LogDocument");
    }

    /**
     * @param ctx
     * @return package directory
     */
    protected String getPackageDirectory(Properties ctx) {
    	return Env.getContext(ctx, "PackageDirectory");
    }
    
    /**
     * Process element by entity type and user setting.
     * @param ctx
     * @param entityType
     * @return boolean
     */
    protected boolean isProcessElement(Properties ctx, String entityType) {
    	if ("D".equals(entityType) || "C".equals(entityType)) {
    		return "true".equalsIgnoreCase(getUpdateMode(ctx));
    	} else {
    		return true;
    	}
    }
    
    /**
     * return null for empty string ("").
     * @param atts
     * @param qName
     * @return string value
     */
    protected String getStringValue(Attributes atts, String qName) {
    	String s = atts.getValue(qName);
    	return ("".equals(s) ? null : s);
    }
    
    /**
     * Get Boolean value from attributes
     * @param atts
     * @param columnName
     * @return
     */
    protected boolean getBooleanValue(Attributes atts, String columnName) {
    	String value = atts.getValue(columnName);
    	if(!Util.isEmpty(value)) {
    		return Boolean.valueOf(value).booleanValue();
    	}
    	//	Default
    	return false;
    }
    
    /**
     * get UUID value from attributes
     * @param atts
     * @param columnName
     * @return
     */
    protected String getUUIDValue(Attributes atts, String columnName) {
    	return getStringValue(atts, AttributeFiller.getUUIDAttribute(columnName));
    }
    
    /**
     * Get Integer Value with default value
     * @param atts
     * @param name
     * @param defaultValue
     * @return
     */
    protected int getIntValue(Attributes atts, String name, int defaultValue) {
		String value = atts.getValue(name);
		if (Util.isEmpty(value, true))
			return defaultValue;
		 int i = Integer.parseInt(value.trim());
		 return i;
	}
    
    /**
     * Get Long Value
     * @param atts
     * @param name
     * @param defaultValue
     * @return
     */
    protected long getLongValue(Attributes atts, String name, int defaultValue) {
		String value = atts.getValue(name);
		if (Util.isEmpty(value, true))
			return defaultValue;
		 long i = Integer.parseInt(value.trim());
		 return i;
	}
    
    /**
     * Get Double Value
     * @param atts
     * @param name
     * @param defaultValue
     * @return
     */
    protected double getDoubleValue(Attributes atts, String name, double defaultValue) {
		String value = atts.getValue(name);
		if (Util.isEmpty(value, true))
			return defaultValue;
		 double i = Double.parseDouble(value.trim());
		 return i;
	}
    
    /**
     * Get Timestamp Value
     * @param atts
     * @param name
     * @return
     */
    protected Timestamp getTimestampValue(Attributes atts, String name) {
    	long time = getLongValue(atts, name, 0);
    	if(time > 0) {
    		return new Timestamp(time);
    	}
    	// default
    	return null;
    }
	
    /**
     * get Integer value with 0 as default
     * @param atts
     * @param name
     * @return
     */
	protected int getIntValue(Attributes atts, String name) {
		return getIntValue(atts, name, 0);
	}
	
	/**
	 * get Big decimal Value
	 * @param atts
	 * @param name
	 * @return
	 */
	protected BigDecimal getBigDecimalValue(Attributes atts, String name) {
		return new BigDecimal(getDoubleValue(atts, name, 0.0));
	}
    
}
