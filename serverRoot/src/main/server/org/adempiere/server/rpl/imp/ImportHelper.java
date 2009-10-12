/**********************************************************************
 * This file is part of Adempiere ERP Bazaar                          * 
 * http://www.adempiere.org                                           * 
 *                                                                    * 
 * Copyright (C) Trifon Trifonov.                                     * 
 * Copyright (C) Contributors                                         * 
 *                                                                    * 
 * This program is free software; you can redistribute it and/or      * 
 * modify it under the terms of the GNU General Public License        * 
 * as published by the Free Software Foundation; either version 2     * 
 * of the License, or (at your option) any later version.             * 
 *                                                                    * 
 * This program is distributed in the hope that it will be useful,    * 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of     * 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the       * 
 * GNU General Public License for more details.                       * 
 *                                                                    * 
 * You should have received a copy of the GNU General Public License  * 
 * along with this program; if not, write to the Free Software        * 
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,         * 
 * MA 02110-1301, USA.                                                * 
 *                                                                    * 
 * Contributors:                                                      * 
 *  - Trifon Trifonov (trifonnt@users.sourceforge.net)                *
 *                                                                    *
 * Sponsors:                                                          *
 *  - e-Evolution (http://www.e-evolution.com/)                       *
 **********************************************************************/
package org.adempiere.server.rpl.imp;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.logging.Level;

import javax.xml.xpath.XPathExpressionException;

import org.adempiere.process.rpl.exp.ExportHelper;
import org.adempiere.server.rpl.XMLHelper;
import org.compiere.model.MClient;
import org.compiere.model.MColumn;
import org.compiere.model.MEXPFormat;
import org.compiere.model.MEXPFormatLine;
import org.compiere.model.MReplicationStrategy;
import org.compiere.model.MTable;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.model.X_AD_Client;
import org.compiere.model.X_AD_ReplicationTable;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Util;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
/**
 * @author Trifon N. Trifonov
 * @author Antonio Cañaveral, e-Evolution
 * 				<li>[ 2195016 ] Implementation delete records messages
 * 				<li>http://sourceforge.net/tracker/index.php?func=detail&aid=2195016&group_id=176962&atid=879332
 * 				<li>Otras Modificaciones Posteriores
 * @author victor.perez@e-evolution.com, www.e-evolution.com
 * 				<li>BF[2836406] Error when try get ID but do not is the first field
 * 				<li>
 * @author michael.judd@akunagroup.com, http://www.akunagroup.com
 * 				<li> BF[2861697] Incorrect string conversion
 * 				<li> https://sourceforge.net/tracker/index.php?func=detail&aid=2861697&group_id=176962&atid=879332
 * @author michael.judd@akunagroup.com, http://www.akunagroup.com
 * 				<li> BF[2863095] Missing DisplayType.Search in ImportHelper
 * 				<li> https://sourceforge.net/tracker/?func=detail&aid=2863095&group_id=176962&atid=879332
 */
public class ImportHelper {

	/** Instance Logger 			*/
	private CLogger log = CLogger.getCLogger(ImportHelper.class);
	
	/** Static Logger 				*/
	private static CLogger s_log = CLogger.getCLogger(ImportHelper.class);
	
	/** Date Time Format			*/
	private SimpleDateFormat	m_dateTimeFormat = null;

	/** Date Format					*/
	private SimpleDateFormat	m_dateFormat = null;
	
	/** Custom Date Format			*/
	private SimpleDateFormat	m_customDateFormat = null;
	
	/** Context						*/
	private Properties ctx = null;
	
	public ImportHelper(Properties ctx) {
		this.ctx = ctx;
		// Construct DateFromat and DateTimeFormat
		m_dateTimeFormat = DisplayType.getDateFormat(DisplayType.DateTime, Env.getLanguage(ctx));
		m_dateFormat 	 = DisplayType.getDateFormat(DisplayType.Date, Env.getLanguage(ctx));
	}
	
	/**
	 * @param ctx
	 * @param result
	 * @param documentToBeImported
	 * @param trxName
	 * @throws Exception
	 * @throws SQLException
	 * @throws XPathExpressionException
	 */
	public void importXMLDocument(StringBuffer result, Document documentToBeImported, String trxName) 
		throws Exception, SQLException,	XPathExpressionException 
	{
		//Element rootNode = importedDocument.getFirstChild();
		Element rootElement = documentToBeImported.getDocumentElement();
		//NodeList nl = rootElement.getChildNodes();
		// iterate over all address nodes and find the one that has the correct addressee
		//for (int i = 0; i < nl.getLength(); i++) { /* */ }
		
		// Find which Export format to Load...
		String AD_Client_Value = null;
		//AD_Client_Value = XMLHelper.getString("@AD_Client_Value", rootNode);
		AD_Client_Value = rootElement.getAttribute("AD_Client_Value");
		log.info("AD_Client_Value = " + AD_Client_Value);
		if (AD_Client_Value == null || "".equals(AD_Client_Value)) {
			throw new Exception(Msg.getMsg(ctx, "XMLClientValueMandatory"));
		}
		String version = null;
		version = rootElement.getAttribute("Version");
		log.info("Version = " + version);
		if (version == null || "".equals(version)) {
			throw new Exception(Msg.getMsg(ctx, "XMLVersionAttributeMandatory"));
		}
		///Getting Attributes.
		
		;
		
		int ReplicationMode = new Integer(rootElement.getAttribute("ReplicationMode"));
		String ReplicationType = rootElement.getAttribute("ReplicationType");
		int ReplicationEvent = new Integer(rootElement.getAttribute("ReplicationEvent"));
		
		MClient client = null;
		client = getAD_ClientByValue(ctx, AD_Client_Value, trxName);
		if (client == null) {
			throw new Exception(Msg.getMsg(ctx, "XMLClientNotFound"));
		}
		log.info(client.toString());

		String EXP_Format_Value = null;
		EXP_Format_Value = rootElement.getNodeName();
		log.info("EXP_Format_Value = " + EXP_Format_Value);

		MEXPFormat expFormat = null;
		expFormat = MEXPFormat.getFormatByValueAD_Client_IDAndVersion(ctx, EXP_Format_Value, client.getAD_Client_ID(), version, trxName);
		if (expFormat == null || expFormat.getEXP_Format_ID() == 0) {
			// Fall back to SYSTEM Client.
			// Try to search Export format defined for SYSTEM Client!!!
			MClient systemClient = null;
			systemClient = MClient.get(ctx, 0);
			if (systemClient == null) {
				throw new Exception(Msg.getMsg(ctx, "XMLClientNotFound"));
			}
			log.info(systemClient.toString());
			expFormat = MEXPFormat.getFormatByValueAD_Client_IDAndVersion(ctx, EXP_Format_Value, systemClient.getAD_Client_ID(), version, trxName);
		}
		if (expFormat == null || expFormat.getEXP_Format_ID() == 0) {
			throw new Exception(Msg.getMsg(ctx, "EXPFormatNotFound"));
		}
		log.info("expFormat = " + expFormat.toString());
		
		PO po = importElement(ctx, result, rootElement, expFormat, trxName);
		// Here must invoke other method else we get cycle...
		boolean resultSave=false;
		if(ReplicationEvent == ModelValidator.TYPE_BEFORE_DELETE ||
		   ReplicationEvent == ModelValidator.TYPE_BEFORE_DELETE_REPLICATION ||
		   ReplicationEvent == ModelValidator.TYPE_DELETE)
			resultSave=po.delete(true);
		else
		{
			//TODO: Create a Replication Type "BROADCAST" now we are using MERGE
			if(ReplicationType.equals(X_AD_ReplicationTable.REPLICATIONTYPE_Merge))
			{
				resultSave = po.saveReplica(true);
				MReplicationStrategy rplStrategy = new MReplicationStrategy(client.getCtx(), client.getAD_ReplicationStrategy_ID(), null);
				ExportHelper expHelper = new ExportHelper(client, rplStrategy);
				expHelper.exportRecord(	po, MReplicationStrategy.REPLICATION_TABLE,X_AD_ReplicationTable.REPLICATIONTYPE_Reference,ModelValidator.TYPE_AFTER_CHANGE);
			}
			else
				resultSave = po.saveReplica(true);
		}
		
		result.append("ResultSave=").append(resultSave).append("; ");
		/*if (resultSave) 
		{
			if(ReplicationMode == MReplicationStrategy.REPLICATION_DOCUMENT && 
			   ReplicationType == X_AD_ReplicationDocument.REPLICATIONTYPE_Merge)
			{
				String status = po.get_ValueAsString("DocStatus");
				String action = po.get_ValueAsString("DocAction");
				DocAction	m_document;
				m_document=(DocAction) po;
				DocumentEngine engine = new DocumentEngine (m_document, status);
				engine.processIt (action);
			}
			// Success in save
		} else {
			// Failed in save
			throw new Exception(Msg.getMsg(ctx, "EXPFormatFailedSave"));
		}*/
	}

	/**
	 * @param result
	 * @param rootElement
	 * @param expFormat
	 * @throws Exception
	 * @throws XPathExpressionException
	 */
	@SuppressWarnings("unchecked")
	private PO importElement(Properties ctx, StringBuffer result, Element rootElement,
			MEXPFormat expFormat, String trxName) throws Exception, XPathExpressionException 
	{
		// get AD_Table ID from export Format.
		int AD_Table_ID = expFormat.getAD_Table_ID();

		// Load appropriate Model class...
		MTable table = MTable.get(ctx, AD_Table_ID);
		log.info("Table = " + table);

		int record_ID = 0;
		String whereClause="";
		// Find Record_ID by ???Value??? In fact by Columns set as Part Of Unique Index in Export Format!
		if(table.getKeyColumns().length == 1)
		{
			record_ID = getID(ctx, expFormat, rootElement, rootElement.getNodeName(), trxName);
			log.info("record_ID = " + record_ID);
		}else
		{
			whereClause = getID(ctx, expFormat, rootElement, rootElement.getNodeName(),trxName,true);
			log.info("WHERE = " + whereClause);
		}
		PO po=null;
		if(record_ID>0)
			po = table.getPO(record_ID, trxName);
		else if(whereClause.length()>0)
			po=table.getPO(whereClause, trxName);
		else
			po = table.getPO(record_ID, trxName);
		
		if(po==null)
			throw new Exception(Msg.getMsg(ctx, "Can't Load PO Object"));
		
		log.info("PO.toString() = " + po.toString());

		if (po.get_KeyColumns().length < 1) {
			throw new Exception(Msg.getMsg(ctx, "EDIMultiColumnNotSupported"));
		}
		
		StringBuffer orderBy = new StringBuffer(MEXPFormatLine.COLUMNNAME_IsMandatory).append(" DESC ")
			.append(", ").append(MEXPFormatLine.COLUMNNAME_Position)
		;
		MEXPFormatLine[] formatLines = expFormat.getFormatLinesOrderedBy(orderBy.toString());
		if (formatLines == null || formatLines.length < 1) {
			throw new Exception(Msg.getMsg(ctx, "EXPFormatNoLines"));
		}
		Object value = null;
		// Iterate all Export Format Lines (even this marked as part of unique index)
		//  and set value of column!
		for (int i = 0; i < formatLines.length; i++) {
			log.info("=================== Beginnig of Format Line ===============================");
			log.info("formatLines["+i+"]=[" + formatLines[i].toString() + "]");
			
			if (MEXPFormatLine.TYPE_XMLElement.equals(formatLines[i].getType())) {
				// XML Element
				value = XMLHelper.getString(formatLines[i].getValue(), rootElement);
				log.info("value=[" + value + "]");
				
			} else if (MEXPFormatLine.TYPE_ReferencedEXPFormat.equals(formatLines[i].getType())) {
				// Referenced Export Format
/*				<C_BPartner>
					<AD_Org>
						<Value>0</Value>
						<AD_Client_Value>
							<AD_Client_Value>SYSTEM</AD_Client_Value>
						</AD_Client_Value>
					</AD_Org>
					...
				<C_BPartner> */				

				MEXPFormat referencedExpFormat = new MEXPFormat(ctx, formatLines[i].getEXP_EmbeddedFormat_ID(), trxName);
				log.info("referencedExpFormat = " + referencedExpFormat);

				int refRecord_ID = 0;
				// Find Record_ID by ???Value??? In fact by Columns set as Part Of Unique Index in Export Format!
				String xPath = null;
				//xPath = ""+rootElement.getNodeName() + "/" + formatLines[i].getValue() + ""; // Do not work
				xPath = "" + formatLines[i].getValue() + ""; //
				
				log.info("SEARCH FOR XML Element = " + xPath);
				Element referencedNode = XMLHelper.getElement(xPath, rootElement);
				
				//NodeList nodeList = XMLHelper.getNodeList(xPath, rootElement);
				//referencedNode = (Element)nodeList.item(0);
				
				log.info("referencedNode = " + referencedNode);
				if(referencedNode!=null)
				{
					refRecord_ID = getID(ctx, referencedExpFormat, referencedNode, formatLines[i].getValue(), trxName);
					log.info("refRecord_ID = " + refRecord_ID);
					value = new Integer(refRecord_ID);
				}
				else
				{
					log.info("NULL VALUE FOR " + xPath.toString());
					value=null;
				}
				log.info("value=[" + value + "]");
			} else if (MEXPFormatLine.TYPE_EmbeddedEXPFormat.equals(formatLines[i].getType())) {
				boolean resSave = false;
				if (po.get_ID() == 0) {
					resSave = po.saveReplica(true);
					result.append("ResultSave-MasterPO=").append(resSave).append("; ");
					log.info("ResultSave-MasterPO = " + resSave);
				} else {
					resSave = true; 
				}
				if (resSave) {
					// Success in save
				} else {
					throw new Exception("Failed to save Master PO");
				}
				// Embedded Export Format
/*				<C_Order>
					<DocumentNo>GardenWorls</DocumentNo>
					...
					... <-- MUST save Master Record here! Else we can't set orderLine.setC_Order_ID(..) 
    				<C_OrderLine>...</C_OrderLine>
				  	<C_OrderLine>...</C_OrderLine>
				</C_Order> */
				MEXPFormat referencedExpFormat = new MEXPFormat(ctx, formatLines[i].getEXP_EmbeddedFormat_ID(), trxName);
				log.info("embeddedExpFormat = " + referencedExpFormat);

				NodeList nodeList = XMLHelper.getNodeList("/"+rootElement.getNodeName() + "/" + formatLines[i].getValue(), rootElement);
				for (int j = 0; j < nodeList.getLength(); j++) {
					Element referencedElement = (Element)nodeList.item(j);
					log.info("EmbeddedEXPFormat - referencedElement.getNodeName = " + referencedElement.getNodeName());
					
					PO embeddedPo = null;
					// Import embedded PO
					log.info("=== BEGIN RECURSION CALL ===");
					embeddedPo = importElement(ctx, result, referencedElement, referencedExpFormat, trxName);
					log.info("embeddedPo = " + embeddedPo);
					
					//embeddedPo.set_CustomColumn(po.get_KeyColumns()[0], po.get_ID());
					//log.info("embeddedPo.set"+po.get_KeyColumns()[0]+" = [" + po.get_ID()+"]");
					
					boolean rSave = embeddedPo.saveReplica(true);
					result.append("ResultSave-EmbeddedPO=").append(rSave).append("; ");
				}

			} else if (MEXPFormatLine.TYPE_XMLAttribute.equals(formatLines[i].getType())) {
				// XML Attribute
				value = XMLHelper.getString("@" + formatLines[i].getValue(), rootElement);
				log.info("value=[" + value + "]");
			} else {
				// Export Format Line is not one of two possible values...ERROR
				throw new Exception(Msg.getMsg(ctx, "EXPFormatLineNonValidType"));
			}
			if (value == null) {
				
			} else {
/*				if (column.getColumnName().equals("AD_Client_ID")) {
					//Env.setContext(Env.getCtx(), "#AD_Client_ID", value.toString());
				}
				if (column.getColumnName().equals("AD_Org_ID")) {
				    //Env.setContext(Env.getCtx(), "#AD_Org_ID", value.toString());
				} */
				if ( MEXPFormatLine.TYPE_EmbeddedEXPFormat.equals(formatLines[i].getType()) ) {
					// do nothing
				} else {
					MColumn column = MColumn.get(ctx, formatLines[i].getAD_Column_ID());
					log.info("column=[" + column + "]");

					// Clazz
					Class clazz = DisplayType.getClass(column.getAD_Reference_ID(), true);
					//	Handle Posted
					if (column.getColumnName().equalsIgnoreCase("Posted") 
						|| column.getColumnName().equalsIgnoreCase("Processed")
						|| column.getColumnName().equalsIgnoreCase("Processing"))
					{
						clazz = Boolean.class;
					} else if (column.getColumnName().equalsIgnoreCase("Record_ID"))
					{
						clazz = Integer.class;
					} else if (column.getColumnName().equalsIgnoreCase("AD_Language")
						|| column.getColumnName().equalsIgnoreCase("EntityType"))
					{
						clazz = String.class;
					}	
					log.info("clazz = " + clazz.getName());
					// Handle Date and Time
					value = handleDateTime(value, column, formatLines[i]);
					
					log.info("formatLinesType = " + formatLines[i].getType());
					if (MEXPFormatLine.TYPE_EmbeddedEXPFormat.equals( formatLines[i].getType() ) )  {
						// DO NOTHING
						throw new Exception("We can't be here!!!");
					} else {
						if (column.getAD_Reference_ID() == DisplayType.DateTime 
								|| column.getAD_Reference_ID() == DisplayType.Date
							) 
						{
							// 
							po.set_ValueOfColumn(formatLines[i].getAD_Column_ID(), value);
							log.info("Set value of column ["+column.getColumnName()+"]=["+value+"]");
						} else if (column.getAD_Reference_ID() == DisplayType.ID
									|| column.getAD_Reference_ID() == DisplayType.Integer
									|| column.getAD_Reference_ID() == DisplayType.Search
									|| column.getAD_Reference_ID() == DisplayType.TableDir
									|| column.getAD_Reference_ID() == DisplayType.Table
									|| column.getAD_Reference_ID() == DisplayType.Location
								) 
						{
							//
							if (! Util.isEmpty(value.toString()))
							{
								int intValue = Integer.parseInt(value.toString());
								value = new Integer( intValue );
							}else
								value=null;
							log.info("About to set int value of column ["+column.getColumnName()+"]=["+value+"]");
							po.set_ValueOfColumn(formatLines[i].getAD_Column_ID(), value);
							log.info("Set int value of column ["+column.getColumnName()+"]=["+value+"]");
						} else if (column.getAD_Reference_ID() == DisplayType.Amount
									|| column.getAD_Reference_ID() == DisplayType.Number
									|| column.getAD_Reference_ID() == DisplayType.CostPrice
									|| column.getAD_Reference_ID() == DisplayType.Quantity
								) 
						{
							//
							if (! Util.isEmpty(value.toString()))
							{
								double doubleValue = Double.parseDouble(value.toString());
								value = new BigDecimal(doubleValue);
							}else
								value=null;
							//value = new Double( doubleValue );
							log.info("About to set BigDecimal value of column ["+column.getColumnName()+"]=["+value+"]");
							po.set_ValueOfColumn(formatLines[i].getAD_Column_ID(), value);
							log.info("Set BigDecimal value of column ["+column.getColumnName()+"]=["+value+"]");
						} 
						else if(column.getAD_Reference_ID() == DisplayType.YesNo)
						{
							po.set_ValueOfColumn(formatLines[i].getAD_Column_ID(), value);
						}
						else {
							//
							try {
								log.info("About to set value of column ["+column.getColumnName()+"]=["+value+"]");
								if(clazz == Boolean.class)
									po.set_ValueOfColumn(formatLines[i].getAD_Column_ID(), value);
								else
									po.set_ValueOfColumn(formatLines[i].getAD_Column_ID(), clazz.cast(value));
								log.info("Set value of column ["+column.getColumnName()+"]=["+value+"]");
							} catch (ClassCastException ex) {
								ex.printStackTrace();
								throw new Exception(ex);
							}
							
							//po.set_ValueOfColumn(formatLines[i].getAD_Column_ID(), value);
						}
						result.append(column.getColumnName()).append("=").append(value).append("; ");
					}
				}
			}
			
		}
		
		return po;
	}
	
	public static MClient getAD_ClientByValue(Properties ctx, String value, String trxName) 
		throws SQLException 
	{
		MClient result = null;
	
		StringBuffer sql = new StringBuffer("SELECT AD_Client_ID ")
			.append(" FROM ").append(X_AD_Client.Table_Name)
			.append(" WHERE ").append(X_AD_Client.COLUMNNAME_Value).append(" = ?")
		// .append(" AND IsActive = ?")
		;
		//s_log.info(sql.toString());
		s_log.info("Client_Value =[" + value + "]");
		
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, value);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int AD_Client_ID = rs.getInt(1);
				s_log.info("AD_Client_ID = " + AD_Client_ID);
				result = new MClient(ctx, AD_Client_ID, trxName);
			}
			rs.close();
			pstmt.close();
			pstmt = null;
		} catch (SQLException e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
			throw e;
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				pstmt = null;
			} catch (Exception e) {
				pstmt = null;
			}
		}
	
		return result;
	}
	
	
	private PreparedStatement getIDValues(Properties ctx, MEXPFormat expFormat, Element rootElement, String rootNodeName, String trxName) throws Exception {
		if (expFormat == null || rootElement == null || rootNodeName == null) {
			throw new IllegalArgumentException("expFormat, rootNode and RootnodeName can't be null!");
		}
		log.info("expFormat = " + expFormat);
		log.info("rootNode.getNodeName() = " + rootElement.getNodeName());
		log.info("rootNodeName = " + rootNodeName);
		if (rootElement.getParentNode() != null) {
			log.info("rootNode.ParentName = " + rootElement.getParentNode().getNodeName());	
		}
		
		// get AD_Table ID from export Format.
		int AD_Table_ID = expFormat.getAD_Table_ID();
	
		// Load appropriate Model class...
		MTable table = MTable.get(ctx, AD_Table_ID);
		log.info("Table = " + table);
	
		//Select * FROM table.getTableName() WHERE Value or ANY IDENTIFIER column
		StringBuffer sql = new StringBuffer("SELECT * ")
			.append(" FROM ").append(table.getTableName())
			.append(" WHERE ")
		;
		// Get list with all Unique columns!
		MEXPFormatLine[] uniqueFormatLines = expFormat.getUniqueColumns();
		if (uniqueFormatLines == null || uniqueFormatLines.length < 1) {
			throw new Exception(Msg.getMsg(ctx, "EXPFormatLineNoUniqueColumns"));
		}
		Object[] values = new Object[uniqueFormatLines.length];
		for (int i = 0; i < uniqueFormatLines.length; i++) {
			log.info("--- iterate unique column with index = ["+i+"]");
			MColumn column = MColumn.get(ctx, uniqueFormatLines[i].getAD_Column_ID());
			log.info("column = ["+column+"]");
			String valuecol=column.getColumnName();
			if(column.getAD_Reference_ID() == DisplayType.Amount 
					|| column.getAD_Reference_ID() == DisplayType.Number 
					|| column.getAD_Reference_ID() == DisplayType.CostPrice
					|| column.getAD_Reference_ID() == DisplayType.Quantity)
			{
				valuecol="Round("+valuecol+",2)";
			}
			
			if (MEXPFormatLine.TYPE_XMLElement.equals(uniqueFormatLines[i].getType())) {
				// XML Element
				//values[i] = XMLHelper.getString("/"+rootElement.getNodeName() + "/" + uniqueFormatLines[i].getValue(), rootElement);
				String xPath = null;
				//xPath = "/"+rootNodeName + "/" + uniqueFormatLines[i].getValue(); -- works
				//xPath = "/"+ uniqueFormatLines[i].getValue(); // do not work
				xPath = ""+ uniqueFormatLines[i].getValue();
				
				values[i] = XMLHelper.getString(xPath, rootElement);
				//log.info("xml PATH =" + rootElement.getNodeName() + "." + xPath );
				log.info("values[" + i + "]=" +  values[i]);
				
			} else if (MEXPFormatLine.TYPE_ReferencedEXPFormat.equals(uniqueFormatLines[i].getType())) {
				// Referenced Export Format
				log.info("referencedExpFormat.EXP_EmbeddedFormat_ID = " + uniqueFormatLines[i].getEXP_EmbeddedFormat_ID());
				MEXPFormat referencedExpFormat = new MEXPFormat(ctx, uniqueFormatLines[i].getEXP_EmbeddedFormat_ID(), trxName);
				log.info("referencedExpFormat = " + referencedExpFormat);
	/*
			<C_BPartner>
				<AD_Org>
					<Value>0</Value>
					<AD_Client_Value>
						<AD_Client_Value>SYSTEM</AD_Client_Value>
					</AD_Client_Value>
				</AD_Org>
				...
			<C_BPartner>
	*/				
				int record_ID = 0;
				// Find Record_ID by ???Value??? In fact by Columns set as Part Of Unique Index in Export Format!
				Element referencedNode = ((Element) rootElement.getElementsByTagName(uniqueFormatLines[i].getValue()).item(0));
				log.info("referencedNode = " + referencedNode);
				
				record_ID = getID(ctx, referencedExpFormat, referencedNode, uniqueFormatLines[i].getValue(), trxName);
				log.info("record_ID = " + record_ID);
				
				values[i] = new Integer(record_ID);
			} else {
				// Export Format Line is not one of two possible values...ERROR
				throw new Exception(Msg.getMsg(ctx, "EXPFormatLineNonValidType"));
			}
			if (i == 0) {
				sql.append(" ").append(valuecol).append(" = ? ");
			} else {
				sql.append(" AND ").append(valuecol).append(" = ? ");
			}
			
		}
		log.info("sql = " + sql.toString());
		
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement (sql.toString(), trxName);
			for (int i = 0; i < uniqueFormatLines.length; i++) {
				MColumn col = MColumn.get(ctx, uniqueFormatLines[i].getAD_Column_ID());
				
				if (col.getAD_Reference_ID() == DisplayType.DateTime 
						|| col.getAD_Reference_ID() == DisplayType.Date) 
				{
					
					Timestamp value = (Timestamp)handleDateTime(values[i], col , uniqueFormatLines[i]);
					pstmt.setTimestamp(i+1, value);
				}
				else if(col.getAD_Reference_ID() == DisplayType.String)
				{
					String value = (String)values[i];
					pstmt.setString(i+1, value);
				}
				else if(col.getAD_Reference_ID() == DisplayType.Amount 
						|| col.getAD_Reference_ID() == DisplayType.Number 
						|| col.getAD_Reference_ID() == DisplayType.CostPrice
						|| col.getAD_Reference_ID() == DisplayType.Quantity)
				{
					BigDecimal value = new BigDecimal((String)values[i]);
					pstmt.setBigDecimal(i+1, value.setScale(2, BigDecimal.ROUND_HALF_UP));
				}
				else if(col.getAD_Reference_ID() == DisplayType.Integer
						|| col.getAD_Reference_ID() == DisplayType.ID
						|| col.getAD_Reference_ID() == DisplayType.TableDir
						|| col.getAD_Reference_ID() == DisplayType.Table
						|| col.getAD_Reference_ID() == DisplayType.Search)
				{
					String stringValue = (String)values[i].toString();
					int value = Integer.parseInt(stringValue);
					pstmt.setInt(i+1, value);
					log.info("pstmt.setInt["+(i+1)+"] = [" + value +"]");				
				}
				else
				{	
					pstmt.setObject(i+1, values[i]);
					log.info("pstmt.setObject["+(i+1)+"] = [" + values[i]+"]");
				}
			
			}
			
			return pstmt;
			
		} catch (SQLException e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
			throw e;
		} 
	}
	
	public int getID(Properties ctx, MEXPFormat expFormat, Element rootElement, String rootNodeName, String trxName) throws Exception {
		int result = 0;
		
		PreparedStatement pstmt = getIDValues(ctx,expFormat,rootElement,rootNodeName,trxName);
		try
		{
			//We do this, because, what about if the ID is not the first column
			//BF[2836406]
			int AD_Table_ID = expFormat.getAD_Table_ID();
			MTable table = MTable.get(ctx, AD_Table_ID);
			String columns[]=table.getKeyColumns(); // Must be always One
			
			if(columns==null || columns.length!=1)
			{
				throw new Exception(Msg.getMsg(ctx, "EXPFormatLineNoUniqueColumns"));
			}	
			
			ResultSet rs = pstmt.executeQuery();
			if ( rs.next() ) 
			{
				result = rs.getInt(rs.findColumn(columns[0]));
			}
			rs.close ();
			pstmt.close ();
			pstmt = null;
		} catch (SQLException e) {
			throw e;
		} finally {
			try	{
				if (pstmt != null) 
				{
					pstmt.close ();
				}	
				pstmt = null;
			} catch (Exception e) {	pstmt = null; }
		}
		log.info("result = " + result);
		return result;
	}

	public String getID(Properties ctx, MEXPFormat expFormat, Element rootElement,String rootNodeName, String trxName,boolean multikey) throws Exception
	{
		String result="";
		PreparedStatement pstmt = getIDValues(ctx,expFormat,rootElement,rootNodeName,trxName);
		try
		{
		
			int AD_Table_ID = expFormat.getAD_Table_ID();
			MTable table = MTable.get(ctx, AD_Table_ID);
			String columns[]=table.getKeyColumns();
			//BF[2836406]
			if(columns==null || columns.length <= 0)
			{
				throw new Exception(Msg.getMsg(ctx, "EXPFormatNoIDs")); //TODO Correct Exception Name
			}
			log.warning("Multiple columns ID. Table = "+ table.getTableName() +" Columns="+ columns);
			
			ResultSet rs = pstmt.executeQuery();
			if ( rs.next() ) 
			{
				for(String column : columns)
				{
					result += " AND " + column + " = "+ rs.getInt(rs.findColumn(column));
				}
			}
			if(result.length()>0)
				result = result.substring(4);
			
			rs.close ();
			pstmt.close ();
			pstmt = null;
		} catch (SQLException e) {
			throw e;
		} finally {
			try	{
				if (pstmt != null) 
				{		
					pstmt.close ();
				}	
				pstmt = null;
			} catch (Exception e) {	pstmt = null; }
		}
		
		return result;
	}
	
	private Object handleDateTime(Object value, MColumn column, MEXPFormatLine formatLine) throws ParseException {
		String valueString = null;
		valueString = value.toString(); // We are sure that value is not null
		Object result = value;
		
		if (column.getAD_Reference_ID() == DisplayType.Date) {
			if (valueString != null) {
				if (formatLine.getDateFormat() != null && !"".equals(formatLine.getDateFormat())) {
					m_customDateFormat = new SimpleDateFormat( formatLine.getDateFormat() ); // "MM/dd/yyyy"; MM/dd/yyyy hh:mm:ss
					result = new Timestamp(m_customDateFormat.parse(valueString).getTime());
					log.info("Custom Date Format; Parsed value = " + result.toString());
				} else {
					//result = new Timestamp(m_dateFormat.parse(valueString).getTime());
					//log.info("Custom Date Format; Parsed value = " + result.toString());
					//NOW Using Standard Japanese Format yyyy-mm-dd hh:mi:ss.mil so don't care about formats....
					if(valueString==null||valueString.length()<=0)
						result=null;
					else
						result = Timestamp.valueOf(valueString);
				}				
			}
		} else if (column.getAD_Reference_ID() == DisplayType.DateTime) {
			if (valueString != null) {
				if (formatLine.getDateFormat() != null && !"".equals(formatLine.getDateFormat())) {
					m_customDateFormat = new SimpleDateFormat( formatLine.getDateFormat() ); // "MM/dd/yyyy"
					result = new Timestamp(m_customDateFormat.parse(valueString).getTime());
					log.info("Custom Date Format; Parsed value = " + result.toString());
				} else {
					//result = new Timestamp(m_dateTimeFormat.parse(valueString).getTime());
					//log.info("Custom Date Format; Parsed value = " + result.toString());
					//NOW Using Standard Japanese Format yyyy-mm-dd hh:mi:ss.mil so don't care about formats....
				    result = Timestamp.valueOf(valueString);
				}
				
			}
			
		}
		return result;
	}

}
