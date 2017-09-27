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
 *  - E-evolution (http://www.e-evolution.com/)                       *
 *********************************************************************/
package org.adempiere.process.rpl.imp;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import javax.xml.xpath.XPathExpressionException;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.process.rpl.XMLHelper;
import org.adempiere.process.rpl.exp.ExportHelper;
import org.compiere.model.I_AD_Client;
import org.compiere.model.MClient;
import org.compiere.model.MColumn;
import org.compiere.model.MEXPFormat;
import org.compiere.model.MEXPFormatLine;
import org.compiere.model.MLocation;
import org.compiere.model.MReplicationStrategy;
import org.compiere.model.MTable;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.model.X_AD_ReplicationDocument;
import org.compiere.model.X_AD_ReplicationTable;
import org.compiere.process.DocAction;
import org.compiere.util.CLogger;
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
 * @author	victor.perez@e-evolution.com, e-Evolution
 * 				<li>[ 2195090 ] Stabilization of replication
 * 				<li>https://sourceforge.net/tracker/?func=detail&atid=879332&aid=2936561&group_id=176962
 *				<li>BF [2947622] The replication ID (Primary Key) is not working
 *				<li>https://sourceforge.net/tracker/?func=detail&aid=2947622&group_id=176962&atid=879332
 *
 */
public class ImportHelper {

	/** Instance Logger 			*/
	private CLogger log = CLogger.getCLogger(ImportHelper.class);
	
	/** Static Logger 				*/
	private static CLogger s_log = CLogger.getCLogger(ImportHelper.class);
	
	/** Custom Date Format			*/
	private SimpleDateFormat	customDateFormat = null;
	/** Set change PO			*/
	boolean isChanged= false;
	
	/** Context						*/
	private Properties ctx = null;
	private StringBuffer resultBuff = null;

	public ImportHelper(Properties ctx) 
	{
		this.ctx = ctx;
		resultBuff = new StringBuffer("");
	}

	public String getResultLog() {
		return resultBuff.toString();
	}
	/**
	 * Import XML Document
	 * 
	 * @param documentToBeImported
	 * @param trxName
	 * @throws Exception
	 * @throws SQLException
	 * @throws XPathExpressionException
	 */
	public void importXMLDocument(Document documentToBeImported, String trxName) 
		throws Exception, SQLException,	XPathExpressionException 
	{
		Element rootElement = documentToBeImported.getDocumentElement();

		// Find which Export format to Load...
		String AD_Client_Value = null;
		AD_Client_Value = rootElement.getAttribute("AD_Client_Value");
		log.info("AD_Client_Value = " + AD_Client_Value);
		if (AD_Client_Value == null || Util.isEmpty(AD_Client_Value)) 
		{
			throw new Exception(Msg.getMsg(ctx, "XMLClientValueMandatory"));
		}

		String version = null;
		version = rootElement.getAttribute("Version");
		log.info("Version = " + version);
		if (version == null || Util.isEmpty(version)) 
		{
			throw new Exception(Msg.getMsg(ctx, "XMLVersionAttributeMandatory"));
		}
		///Getting Attributes.
		int replicationMode = new Integer(rootElement.getAttribute("ReplicationMode"));
		String replicationType = rootElement.getAttribute("ReplicationType"); // Why we read it from the XML??? This is wrong!
		int replicationEvent = new Integer(rootElement.getAttribute("ReplicationEvent"));
		
		MClient client = null;
		client = getAD_ClientByValue(ctx, AD_Client_Value, trxName);
		if (client == null) 
		{
			throw new Exception(Msg.getMsg(ctx, "XMLClientNotFound"));
		}
		log.info("XML ROOT AD_Client = " + client.toString());

		String EXP_Format_Value = null;
		EXP_Format_Value = rootElement.getNodeName();
		log.info("EXP_Format_Value = " + EXP_Format_Value);

		MEXPFormat expFormat = null;
		expFormat = MEXPFormat.getFormatByValueAD_Client_IDAndVersion(ctx, EXP_Format_Value, client.getAD_Client_ID(), version, trxName);
		if (expFormat == null || expFormat.getEXP_Format_ID() == 0) 
		{
			// Fall back to SYSTEM Client.
			// Try to search Export format defined for SYSTEM Client!!!
			MClient systemClient = null;
			systemClient = MClient.get(ctx, 0);
			if (systemClient == null) 
			{
				throw new Exception(Msg.getMsg(ctx, "XMLClientNotFound"));
			}
			log.info("SYSTEM Client = " + systemClient.toString());
			expFormat = MEXPFormat.getFormatByValueAD_Client_IDAndVersion(ctx, EXP_Format_Value, systemClient.getAD_Client_ID(), version, trxName);
		}
		if (expFormat == null || expFormat.getEXP_Format_ID() == 0) 
		{
			throw new Exception(Msg.getMsg(ctx, "EXPFormatNotFound"));
		}
		log.info("expFormat = " + expFormat.toString());
		isChanged = false;
		PO po = importElement(ctx, rootElement, expFormat, replicationType, trxName);
		if (ModelValidator.TYPE_BEFORE_DELETE == replicationEvent
				|| ModelValidator.TYPE_BEFORE_DELETE_REPLICATION == replicationEvent
				|| ModelValidator.TYPE_DELETE == replicationEvent)
		;
		else if (!po.is_Changed() && !isChanged)
		{
		    log.info("Object not changed = " + po.toString());
		    return;
		}

		if (po != null)
		{
			Env.setContext(po.getCtx(), "#AD_Client_ID", po.getAD_Client_ID());

			if (MReplicationStrategy.REPLICATION_TABLE==replicationMode)
			{
				// Here must invoke other method else we get cycle...
				if (ModelValidator.TYPE_BEFORE_DELETE == replicationEvent
					||	ModelValidator.TYPE_BEFORE_DELETE_REPLICATION == replicationEvent
					||	ModelValidator.TYPE_DELETE == replicationEvent)
				{
					po.deleteEx(true);
				}
				else
				{
					if (X_AD_ReplicationTable.REPLICATIONTYPE_Broadcast.equals(replicationType))
					{
						MReplicationStrategy rplStrategy = new MReplicationStrategy(client.getCtx(), client.getAD_ReplicationStrategy_ID(), po.get_TrxName());
						ExportHelper expHelper = new ExportHelper(client, rplStrategy);
						expHelper.exportRecord(	po, 
								MReplicationStrategy.REPLICATION_TABLE,
								X_AD_ReplicationTable.REPLICATIONTYPE_Merge,
								ModelValidator.TYPE_AFTER_CHANGE);
						po.saveReplica(true);
					}
					else if (X_AD_ReplicationTable.REPLICATIONTYPE_Merge.equals(replicationType)
						||  X_AD_ReplicationTable.REPLICATIONTYPE_Reference.equals(replicationType))
					{
						po.saveReplica(true);
					}
					else if (X_AD_ReplicationTable.REPLICATIONTYPE_Local.equals(replicationType))
					{
						//Do nothing??	
					}
					else
					{
						// Replication Type is not one of the possible values...ERROR
						throw new Exception(Msg.getMsg(ctx, "EXPReplicationTypeNonValidType"));
					}
				}	
			}
			else if (MReplicationStrategy.REPLICATION_DOCUMENT == replicationMode
					&& X_AD_ReplicationDocument.REPLICATIONTYPE_Merge.equals(replicationType)
					&& po instanceof DocAction)
			{
				DocAction document = (DocAction)po;
				String action = document.getDocAction();
				String status = document.getDocStatus();
				log.info("Document:"+document.toString() + " DocStauts:" + status + " DocAction:"+action);

				if (ModelValidator.TIMING_AFTER_REVERSECORRECT==replicationEvent)
				{
					if(status.equals(DocAction.STATUS_Reversed) && action.equals(DocAction.ACTION_None))
					{
						po.saveEx();
						return;
					}
				}	 

				if ( (action.equals(DocAction.ACTION_Complete) && status.equals(DocAction.STATUS_InProgress))
					||  (action.equals(DocAction.ACTION_Close) && status.equals(DocAction.STATUS_Completed)))
				{
					if (!document.processIt(action))
					{
						log.info("PO.toString() = can not " + po.get_Value("DocAction"));
					}
					po.saveEx();
				}
				else
				{
					po.saveEx();
					return;
				}
			}
		}
		resultBuff.append("PO Saved Successfully;");
	}

	/**
	 *
	 * @param ctx
	 * @param result
	 * @param rootElement
	 * @param expFormat
	 * @param replicationType
	 * @param trxName
	 * @return
	 * @throws Exception
	 * @throws XPathExpressionException
	 */
	private PO importElement(Properties ctx, Element rootElement, MEXPFormat expFormat, String replicationType, String trxName) 
			throws Exception, XPathExpressionException
	{
		//Getting the Object for the replicate
		PO po = getObjectFromFormat(ctx, expFormat, rootElement, replicationType, trxName);

		if (po == null) {
			throw new Exception(Msg.getMsg(ctx, "Can't Load PO Object"));
		}

		// If this is just for push and exists we do nothing
		if (X_AD_ReplicationTable.REPLICATIONTYPE_Reference.equals(replicationType)) {
			if (po.get_ID() == 0) {
				return null;
			}
		}
		log.info("PO.toString() = " + po.toString());

		if (po.get_KeyColumns().length < 1) {
			throw new Exception(Msg.getMsg(ctx, "EDIMultiColumnNotSupported"));
		}

		Collection<MEXPFormatLine> formatLines = expFormat.getFormatLinesOrderedBy(MEXPFormatLine.COLUMNNAME_IsMandatory 
		+ " , " + MEXPFormatLine.COLUMNNAME_Position);
		if (formatLines == null || formatLines.size() < 1) 
		{
			throw new Exception(Msg.getMsg(ctx, "EXPFormatNoLines"));
		}
		// Iterate all Export Format Lines (even this marked as part of unique index)
		//  and set value of column!
		for (MEXPFormatLine formatLine : formatLines) 
		{
			log.info("=================== Beginnig of Format Line ===============================");
			log.info("formatLine: [" + formatLine.toString() + "]");			
			// Get the value
			Object value = getValueFromFormat(formatLine, po, rootElement, replicationType);
			if (value == null) // @Trifon - If XML document contains XML element then even if value is EMPTY we will update the DB record! - OLD: || value.toString().equals(""))
				continue;	
			//Set the value
			setReplicaValues(value, formatLine, po);
		}
		return po;
	}

	/**
	 * Get the value from format
	 * @param line
	 * @param po
	 * @param rootElement
	 * @param replicationType
	 * @return
	 * @throws Exception
	 */
	private Object getValueFromFormat(MEXPFormatLine line, PO po, Element rootElement, String replicationType) throws Exception
	{
		Object value = null;

		if (MEXPFormatLine.TYPE_XMLElement.equals(line.getType())) 
		{
			// XML Element
			value = XMLHelper.getString(line.getValue(), rootElement);
			log.info("value=[" + value + "]");
		} 
		else if (MEXPFormatLine.TYPE_ReferencedEXPFormat.equals(line.getType())) 
		{
			// Referenced Export Format. Get it from cache
			MEXPFormat referencedExpFormat = MEXPFormat.get(ctx, line.getEXP_EmbeddedFormat_ID(), po.get_TrxName());
			log.info("referencedExpFormat = " + referencedExpFormat);

			int refRecord_ID = 0;
			// Find Record_ID by ???Value??? In fact by Columns set as Part Of Unique Index in Export Format!
			String xPath = null;
			xPath = "" + line.getValue() + ""; 

			log.info("Seach for XML Element = " + xPath);
			Element referencedNode = XMLHelper.getElement(xPath, rootElement);

			log.info("referencedNode = " + referencedNode);
			if (referencedNode != null)
			{
				// SPECIAL HANDLING of C_Location !!!
				// In Case of C_Location. Try to save C_Location First, similar to Embedded code
				if (referencedExpFormat.getAD_Table().getTableName().equals(MLocation.Table_Name)) {
					PO embeddedPo = null;
					// Import embedded PO
					log.info("=== BEGIN RECURSION CALL ===");
					embeddedPo = importElement(ctx, referencedNode, referencedExpFormat, replicationType, po.get_TrxName());
					log.info("embeddedPo = " + embeddedPo);
					if (!embeddedPo.is_Changed()) {
					    log.info("Object not changed = " + po.toString());
					} else {	
						embeddedPo.saveReplica(true);
						isChanged = true;
					}
					resultBuff.append(" !!! C_Location !!! Saved Successfully; ");
					refRecord_ID = embeddedPo.get_ID();
				} else {
					refRecord_ID = getID(ctx, referencedExpFormat, referencedNode, line.getValue(), replicationType, po.get_TrxName());
				}
				log.info("refRecord_ID = " + refRecord_ID);
				value = new Integer(refRecord_ID);
			}
			else
			{
				log.info("NULL VALUE FOR " + xPath.toString());
				value = null;
			}
			log.info("value=[" + value + "]");
		} 
		else if (MEXPFormatLine.TYPE_EmbeddedEXPFormat.equals(line.getType())) 
		{
			if (po.is_Changed()) {
				isChanged = true;
				po.saveReplica(true);
			} else {
				return value;
			}

			// Embedded Export Format It is used for Parent-Son records like Order&OrderLine
			//get from cache
			MEXPFormat referencedExpFormat = MEXPFormat.get(ctx, line.getEXP_EmbeddedFormat_ID(), po.get_TrxName());
			log.info("embeddedExpFormat = " + referencedExpFormat);

			// Skip View tables!
			if ( referencedExpFormat.getAD_Table().isView() ) {
				// SKIP
			} else {
				NodeList nodeList = XMLHelper.getNodeList(line.getValue(), rootElement);
//				NodeList nodeList = XMLHelper.getNodeList("/"+rootElement.getNodeName() + "/" + line.getValue(), rootElement);
				for (int j = 0; j < nodeList.getLength(); j++) 
				{
					Element referencedElement = (Element)nodeList.item(j);
					log.info("EmbeddedEXPFormat - referencedElement.getNodeName = " + referencedElement.getNodeName());

					PO embeddedPo = null;
					// Import embedded PO
					log.info("=== BEGIN RECURSION CALL ===");
					embeddedPo = importElement(ctx, referencedElement, referencedExpFormat, replicationType, po.get_TrxName());
					log.info("embeddedPo = " + embeddedPo);
					if (!embeddedPo.is_Changed()) {
						log.info("Object not changed = " + po.toString());
						continue;
					} else {
						embeddedPo.saveReplica(true);
						isChanged = true;
					}
					resultBuff.append(" Embedded Saved Successfully; ");
				}
			}
		} 
		else if (MEXPFormatLine.TYPE_XMLAttribute.equals(line.getType())) 
		{
			// XML Attribute
			value = XMLHelper.getString("@" + line.getValue(), rootElement);
			log.info("value=[" + value + "]");
		} 
		else 
		{
			// Export Format Line is not one of the possible values...ERROR
			throw new Exception(Msg.getMsg(ctx, "EXPFormatLineNonValidType"));
		}
		return value;
	}
	
	/**
	 * 
	 * @param value
	 * @param line
	 * @param po
	 * @throws Exception
	 */
	private void setReplicaValues(Object value, MEXPFormatLine line, PO po) throws Exception
	{
		MColumn column = MColumn.get(ctx, line.getAD_Column_ID());
		log.info("column=[" + column + "]");

		if (value !=null)
		{
			if (!MEXPFormatLine.TYPE_EmbeddedEXPFormat.equals(line.getType()) ) 
			{
				// Clazz
				Class<?> clazz = DisplayType.getClass(column.getAD_Reference_ID(), true);

				//	Handle Posted
				if (   column.getColumnName().equalsIgnoreCase("Posted") 
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
				value = handleDateTime(value, column, line);
				log.info("formatLinesType = " + line.getType());
				
				if (MEXPFormatLine.TYPE_EmbeddedEXPFormat.equals( line.getType() ) )  
				{
					// DO NOTHING
					throw new Exception("We can't be here!!!");
				} else 
				{
					if ( DisplayType.DateTime == column.getAD_Reference_ID() 
					  || DisplayType.Date     == column.getAD_Reference_ID()) 
					{
						// 
						po.set_ValueOfColumn(line.getAD_Column_ID(), value);
						log.info("Set value of column ["+column.getColumnName()+"]=["+value+"]");
					} 
					else if (  DisplayType.isID(column.getAD_Reference_ID())
							|| DisplayType.Integer	==	column.getAD_Reference_ID()
							) 
					{
						//
						if (!Util.isEmpty(value.toString())) {
							int intValue = Integer.parseInt(value.toString());
							value = new Integer( intValue );
						} else {
							value = null;
						}

						log.info("About to set int value of column ["+column.getColumnName()+"]=["+value+"]");
						po.set_ValueOfColumn(line.getAD_Column_ID(), value);
						log.info("Set int value of column ["+column.getColumnName()+"]=["+value+"]");
					} 
					else if ( DisplayType.isNumeric(column.getAD_Reference_ID())	
							&& column.getAD_Reference_ID() != DisplayType.Integer)
					{
						//
						if (!Util.isEmpty(value.toString())) {
							value = new BigDecimal(value.toString());
							//value = new Double( doubleValue );
						} else {
							value = null;
						}
						
						log.info("About to set BigDecimal value of column ["+column.getColumnName()+"]=["+value+"]");
						po.set_ValueOfColumn(line.getAD_Column_ID(), value);
						log.info("Set BigDecimal value of column ["+column.getColumnName()+"]=["+value+"]");
					} 
					else if(DisplayType.YesNo == column.getAD_Reference_ID())
					{
						if (clazz == Boolean.class) {						 
							String v = value.toString().equals("true") ? "Y" : "N";	
							po.set_ValueOfColumn(line.getAD_Column_ID(), v);
						}
					}
					else 
					{
						//
						try {
							log.info("About to set value of column ["+column.getColumnName()+"]=["+value+"]");

							if (clazz == Boolean.class) {
								String v = value.toString().equals("true") ? "Y" : "N";
								po.set_ValueOfColumn(line.getAD_Column_ID(), v);
							} else {
								po.set_ValueOfColumn(line.getAD_Column_ID(), clazz.cast(value));
							}
							log.info("Set value of column ["+column.getColumnName()+"]=["+value+"]");
						} catch (ClassCastException ex) {
							ex.printStackTrace();
							throw new Exception(ex);
						}
					}
					resultBuff.append(column.getColumnName()).append("=").append(value).append("; ");
				}//end else			
			}//end if TYPE_EmbeddedEXPFormat			
		}//end if value !=null
	}
		
	public static MClient getAD_ClientByValue(Properties ctx, String value, String trxName) 
		throws SQLException 
	{
		final String whereClause = I_AD_Client.COLUMNNAME_Value + "= ? ";
		MClient result = new Query(ctx, I_AD_Client.Table_Name, whereClause, trxName)
								.setParameters(value)
								.firstOnly();

		s_log.info("Client_Value =[" + value + "]");
		if (result != null) {
			s_log.info("AD_Client_ID = " + result.getAD_Client_ID());
		}
		return result;
	}
	
	/**
	 * This Method gets the PO record, from the exportFormat
	 * @param ctx
	 * @param expFormat
	 * @param rootElement
	 * @param rootNodeName
	 * @param trxName
	 * @throws Exception
	 * */
	private PO getObjectFromFormat(Properties ctx, MEXPFormat expFormat, Element rootElement, String replicationType, String trxName) throws Exception
	{
		List<PO> values = null;

		if (expFormat == null || rootElement == null) {
			throw new IllegalArgumentException("expFormat, and rootElement can't be null!");
		}
		
		log.info("expFormat = " + expFormat);
		log.info("rootElement.getNodeName() = " + rootElement.getNodeName());

		if (rootElement.getParentNode() != null) {
			log.info("rootElement.ParentName = " + rootElement.getParentNode().getNodeName());	
		}
		
		// Get list with all Unique columns!
		Collection<MEXPFormatLine> uniqueFormatLines = expFormat.getUniqueColumns();
		if (uniqueFormatLines == null || uniqueFormatLines.size() < 1) {
			throw new AdempiereException(Msg.getMsg(ctx, "EXPFormatLineNoUniqueColumns"));
		}

		int replication_id = 0;
		Object[] cols 	= new Object[uniqueFormatLines.size()];
		Object[] params = new Object[uniqueFormatLines.size()];
		StringBuffer whereClause = new StringBuffer("");
		int col = 0;
		String formatLines = "";
		for (MEXPFormatLine uniqueFormatLine : uniqueFormatLines) 
		{
			MColumn column = MColumn.get(ctx, uniqueFormatLine.getAD_Column_ID());
			log.info("UNIQUE column = ["+column.getAD_Table().getTableName()+"."+column.getColumnName()+"]");
			String valuecol = column.getColumnName();

			formatLines = formatLines + "|"+ valuecol;

			if (MEXPFormatLine.TYPE_XMLElement.equals(uniqueFormatLine.getType())) 
			{
				// XML Element
				String xPath = null;
				xPath = ""+ uniqueFormatLine.getValue();
				cols[col] = XMLHelper.getString(xPath, rootElement);
				log.info("values[" + col + "]=" + cols[col]);
			} else if (MEXPFormatLine.TYPE_XMLAttribute.equals(uniqueFormatLine.getType()))
			{
				// XML Attribute
				String xPath = null;
				xPath = "@"+ uniqueFormatLine.getValue();
				cols[col] = XMLHelper.getString(xPath, rootElement);
				log.info("values[" + col + "]=" + cols[col]);
			} else if (MEXPFormatLine.TYPE_ReferencedEXPFormat.equals(uniqueFormatLine.getType()))
			{
				// Referenced Export Format
				log.info("referencedExpFormat.EXP_EmbeddedFormat_ID = " + uniqueFormatLine.getEXP_EmbeddedFormat_ID());
				//get from cache
				MEXPFormat referencedExpFormat = MEXPFormat.get(ctx, uniqueFormatLine.getEXP_EmbeddedFormat_ID(), trxName);
				log.info("referencedExpFormat = " + referencedExpFormat);
				
				int record_ID = 0;
				// Find Record_ID by ???Value??? In fact by Columns set as Part Of Unique Index in Export Format!
				Element referencedNode = ((Element) rootElement.getElementsByTagName(uniqueFormatLine.getValue()).item(0));
				log.info("referencedNode = " + referencedNode);
				if (referencedNode == null) 
				{
					throw new IllegalArgumentException("referencedNode can't be found!");
				}
				record_ID = getID(ctx, referencedExpFormat, referencedNode, uniqueFormatLine.getValue(), replicationType, trxName);

				// If ReferencedExport format has checkbox "CreateNonExisting" marked then NON existing record MUST be created!!!
				// Add new column to EXP_FormatLine "IsCreateNonExisting"
				//boolean createNonExistingRecords = uniqueFormatLine.getValue().equalsIgnoreCase("C_BPartner");
				boolean createNonExistingRecords = uniqueFormatLine.get_ValueAsBoolean("IsCreateNonExisting");
				if (createNonExistingRecords && record_ID <= 0) {
					PO po = importElement(ctx, referencedNode, referencedExpFormat, replicationType, trxName);
					log.info("Embeded - po = " + po);
					if (!po.is_Changed()) {
					    log.info("Object not changed = " + po.toString());
					} else {	
						po.saveReplica(true);
						isChanged = true;
					}
					resultBuff.append(" !!! Created NEW NON existing REFERENCE record and saved successfully; ");
					record_ID = po.get_ID();
				}
				log.info("record_ID = " + record_ID);

				cols[col] = new Integer(record_ID);
			}
			else
			{
				// Export Format Line is not one of two possible values...ERROR
				throw new Exception(Msg.getMsg(ctx, "EXPFormatLineNonValidType"));
			}
			
			if (	DisplayType.DateTime == column.getAD_Reference_ID() 
				||	DisplayType.Date 	 == column.getAD_Reference_ID()) 
			{
				Timestamp value = (Timestamp)handleDateTime(cols[col], column , uniqueFormatLine);
				params[col] = value;
			}
			else if(column.getAD_Reference_ID() == DisplayType.String)
			{
				params[col] = (String)cols[col];
			}
			else if (  DisplayType.isID(column.getAD_Reference_ID())
					|| DisplayType.Integer	==	column.getAD_Reference_ID()) 
			{
				Object value =  cols[col];
				if (!Util.isEmpty(value.toString())) {
					//double doubleValue = Double.parseDouble(value.toString());
					value = new Integer(value.toString());
					if (DisplayType.ID == column.getAD_Reference_ID()) {
						replication_id = (Integer) value;
					}
				} else {
					value = null;
				}

				params[col] = value;
			}	
			else if( DisplayType.isNumeric(column.getAD_Reference_ID()))
			{
				valuecol="Round("+valuecol+",2)";
				params[col] = new BigDecimal((String)cols[col]).setScale(2, BigDecimal.ROUND_HALF_UP);
			}
			else
			{	
				params[col] = cols[col];
			}
			
			if (col == 0) {
				whereClause.append(" ").append(valuecol).append(" = ? ");
			} else {
				whereClause.append(" AND ").append(valuecol).append(" = ? ");
			}
			col++;
		}

		Query query = new Query(ctx, MTable.get(ctx, expFormat.getAD_Table_ID()), whereClause.toString(), trxName)
						.setParameters(params);
		values = query.list();

		if (values.size()>1) //The Return Object must be always one
		{
			throw new AdempiereException(Msg.getMsg(ctx, "EXPFormatLineNoUniqueColumns") + " : " +expFormat.getName() + "(" +formatLines+")");
		}

		if (values.size()<=0) //Means that is a new record
		{
			PO po = MTable.get(ctx, expFormat.getAD_Table_ID()).getPO(0, trxName);

			if (replication_id > 0 )
			{
				po.set_CustomColumn(po.get_KeyColumns()[0], replication_id);
			}

			return po;
		}
		
		return values.get(0);//Return the first (unique) record.
	}
	
	/**
	 * This Method gets the ID value of the current referenced element to put in the imported Object
	 * Exp: AD_Org_ID of the Product.
	 * Must be allays one result.
	 * @param ctx
	 * @param expFormat
	 * @param rootElement
	 * @param rootNodeName
	 * @param trxName
	 * @throws Exception
	 * */
	private int getID(Properties ctx, MEXPFormat expFormat, Element rootElement, String rootNodeName, String replicationType, String trxName) throws Exception 
	{
		int record_id = 0;
		PO po = getObjectFromFormat(ctx, expFormat, rootElement, replicationType, trxName);
		if (po != null) {
			record_id = po.get_ID();
		}
		log.info("record_id = " + record_id);

		return record_id;
	}

	/**
	 * Handle Date Time
	 * @param value
	 * @param column
	 * @param formatLine
	 * @return
	 * @throws ParseException
     */
	private Object handleDateTime(Object value, MColumn column, MEXPFormatLine formatLine) throws ParseException 
	{
		String valueString = null;
		valueString = value.toString(); // We are sure that value is not null
		Object result = value;

		if (DisplayType.Date == column.getAD_Reference_ID()) {
			if (valueString != null) {
				if (formatLine.getDateFormat() != null && !Util.isEmpty(formatLine.getDateFormat())) {
					customDateFormat = new SimpleDateFormat( formatLine.getDateFormat() ); // "MM/dd/yyyy"; MM/dd/yyyy hh:mm:ss
					result = new Timestamp(customDateFormat.parse(valueString).getTime());
					log.info("Custom Date Format; Parsed value = " + result.toString());
				} else {
					result = Timestamp.valueOf(valueString);
				}
			}
		}
		else if (DisplayType.DateTime == column.getAD_Reference_ID()) 
		{
			if (valueString != null) {
				if (formatLine.getDateFormat() != null && !Util.isEmpty(formatLine.getDateFormat())) {
					customDateFormat = new SimpleDateFormat( formatLine.getDateFormat() ); // "MM/dd/yyyy"
					result = new Timestamp(customDateFormat.parse(valueString).getTime());
					log.info("Custom Date Format; Parsed value = " + result.toString());
				} else {
					//NOW Using Standard Japanese Format yyyy-mm-dd hh:mi:ss.mil so don't care about formats....
				    result = Timestamp.valueOf(valueString);
				}
			}
		}
		return result;
	}
}
