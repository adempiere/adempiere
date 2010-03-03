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
 *  - Antonio Cañaveral, e-Evolution								  *
 *                                                                    *
 * Sponsors:                                                          *
 *  - E-evolution (http://www.e-evolution.com/)                       *
 *********************************************************************/
package org.adempiere.process.rpl.exp;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.adempiere.process.rpl.IExportProcessor;
import org.compiere.model.MClient;
import org.compiere.model.MColumn;
import org.compiere.model.MEXPFormat;
import org.compiere.model.MEXPFormatLine;
import org.compiere.model.MEXPProcessor;
import org.compiere.model.MEXPProcessorType;
import org.compiere.model.MReplicationStrategy;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.model.X_EXP_FormatLine;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;


/**
 * @author Trifon N. Trifonov
 * @author Antonio Cañaveral, e-Evolution
 * 				<li>[ 2195016 ] Implementation delete records messages
 * 				<li>http://sourceforge.net/tracker/index.php?func=detail&aid=2195016&group_id=176962&atid=879332
 * @author victor.perez@e-evolution.com, e-Evolution
 * 				<li>[ 2195090 ] Stabilization of replication
 * 				<li>https://sourceforge.net/tracker/?func=detail&atid=879332&aid=2936561&group_id=176962
 *				<li>BF [2947622] The replication ID (Primary Key) is not working
 *				<li>https://sourceforge.net/tracker/?func=detail&aid=2947622&group_id=176962&atid=879332
 *
 */
public class ExportHelper {
	
	/**	Logger					*/
	private static CLogger log = CLogger.getCLogger(ExportHelper.class);
	
	/** XML Document 			*/
	private Document outDocument = null; 
	
	/** Custom Date Format		*/
	private SimpleDateFormat	m_customDateFormat = null;
	
	/** Client					*/
	private int		m_AD_Client_ID = -1;
	
	/** Replication Strategy	*/
	MReplicationStrategy m_rplStrategy = null;
	
	
	public ExportHelper(MClient client, MReplicationStrategy rplStrategy) {
		m_AD_Client_ID = client.getAD_Client_ID();
		m_rplStrategy = rplStrategy;
	}
	
	public ExportHelper(Properties ctx , int AD_Client_ID) {
		m_AD_Client_ID = AD_Client_ID;
	}
	
		/**
	 * 	Process - Generate Export Format
	 *	@return info
	 */
	@SuppressWarnings("unchecked")
	public String exportRecord (PO po, Integer ReplicationMode , String ReplicationType, Integer ReplicationEvent) throws Exception
	{
		MClient client = MClient.get (po.getCtx(), m_AD_Client_ID);
		log.info("Client = " + client.toString());
		
		log.info("po.getAD_Org_ID() = " + po.getAD_Org_ID());
		
		log.info("po.get_TrxName() = " + po.get_TrxName());
		if (po.get_TrxName() == null || po.get_TrxName().equals("")) {
			po.set_TrxName("exportRecord");
		}
		
		
		log.info("Table = " + po.get_TableName());
		
		if (po.get_KeyColumns().length < 1) {
			throw new Exception(Msg.getMsg (po.getCtx(), "ExportNoneColumnKeyNotSupported"));//TODO: Create Mesagge.
		}
		// TODO - get proper Export Format!
		String version = "3.2.0";
		//int EXP_Format_ID = 1000006;
		MEXPFormat exportFormat = null;
		//exportFormat = new MFormat(po.getCtx(), EXP_Format_ID, po.get_TrxName());
		exportFormat = MEXPFormat.getFormatByAD_Client_IDAD_Table_IDAndVersion(po.getCtx(), m_AD_Client_ID, po.get_Table_ID(), version, po.get_TrxName());
		log.fine("exportFormat = " + exportFormat);
		if (exportFormat == null || exportFormat.getEXP_Format_ID() == 0) {
			// Fall back to System Client
			MClient systemClient = MClient.get (po.getCtx(), 0);
			log.info(systemClient.toString());
			exportFormat = MEXPFormat.getFormatByAD_Client_IDAD_Table_IDAndVersion(po.getCtx(), 0, po.get_Table_ID(), version, po.get_TrxName());
			
			if (exportFormat == null || exportFormat.getEXP_Format_ID() == 0) {
				throw new Exception(Msg.getMsg(po.getCtx(), "EXPFormatNotFound"));	
			}
		}

		outDocument = createNewDocument();

		HashMap<String, Integer> variableMap = new HashMap<String, Integer>();

		Element rootElement = outDocument.createElement(exportFormat.getValue());
		if (exportFormat.getDescription() != null && !"".equals(exportFormat.getDescription())) 
		{
		    rootElement.appendChild(outDocument.createComment(exportFormat.getDescription()));
		}	
		rootElement.setAttribute("AD_Client_Value", client.getValue());
		rootElement.setAttribute("Version", exportFormat.getVersion());
		rootElement.setAttribute("ReplicationMode", ReplicationMode.toString());
		rootElement.setAttribute("ReplicationType", ReplicationType);
		rootElement.setAttribute("ReplicationEvent", ReplicationEvent.toString());
		outDocument.appendChild(rootElement);
		generateExportFormat(rootElement, exportFormat, po, po.get_ID(), variableMap);
		    		
		MEXPProcessor mExportProcessor = null;
		mExportProcessor = new MEXPProcessor (po.getCtx(), m_rplStrategy.getEXP_Processor_ID(), po.get_TrxName() );
		log.fine("ExportProcessor = " + mExportProcessor);
		int EXP_ProcessorType_ID = 0;
		EXP_ProcessorType_ID = mExportProcessor.getEXP_Processor_Type_ID();
		MEXPProcessorType expProcessor_Type = new MEXPProcessorType(po.getCtx(), EXP_ProcessorType_ID, po.get_TrxName() );
		
        
		String javaClass = expProcessor_Type.getJavaClass();
		try {
			Class clazz = Class.forName(javaClass);
			IExportProcessor exportProcessor = (IExportProcessor)clazz.newInstance();
			
			exportProcessor.process(po.getCtx(), mExportProcessor, outDocument, Trx.get( po.get_TrxName(), false ));
			
		} catch (Exception e) {
			log.severe(e.toString());
			throw e;
		}

		return outDocument.toString();
	}
	
	
	/**
	 * 	Process - Generate Export Format
	 *  @param 
	 * 
	 *	@return Document
	 */
	public Document exportRecord (MEXPFormat exportFormat, String where , Integer ReplicationMode , String ReplicationType, Integer ReplicationEvent) throws Exception
	{
		MClient client = MClient.get (exportFormat.getCtx(), m_AD_Client_ID);
		MTable table = MTable.get(exportFormat.getCtx(), exportFormat.getAD_Table_ID());
		log.info("Table = " + table);
		
		Collection<PO> records = new Query(exportFormat.getCtx(),table.getTableName(), exportFormat.getWhereClause(), exportFormat.get_TrxName())
		.setOnlyActiveRecords(true)
		.list();
		
		for (PO po : records)
		{	
				log.info("Client = " + client.toString());
				log.finest("po.getAD_Org_ID() = " + po.getAD_Org_ID());
				log.finest("po.get_TrxName() = " + po.get_TrxName());
				if (po.get_TrxName() == null || po.get_TrxName().equals("")) {
					po.set_TrxName("exportRecord");
				}
				
				if (po.get_KeyColumns().length < 1) {
					throw new Exception(Msg.getMsg (po.getCtx(), "ExportNoneColumnKeyNotSupported"));//TODO: Create Mesagge.
				}
				
				outDocument = createNewDocument();
		

				HashMap<String, Integer> variableMap = new HashMap<String, Integer>();		
				Element rootElement = outDocument.createElement(exportFormat.getValue());
				if (exportFormat.getDescription() != null && !"".equals(exportFormat.getDescription())) 
				{
				    rootElement.appendChild(outDocument.createComment(exportFormat.getDescription()));
				}
				rootElement.setAttribute("AD_Client_Value", client.getValue());
				rootElement.setAttribute("Version", exportFormat.getVersion());
				rootElement.setAttribute("ReplicationMode", ReplicationMode.toString());
				rootElement.setAttribute("ReplicationType", ReplicationType);
				rootElement.setAttribute("ReplicationEvent", ReplicationEvent.toString());						
				outDocument.appendChild(rootElement);
				generateExportFormat(rootElement, exportFormat, po, po.get_ID(), variableMap);			
		}// finish record read
		return outDocument;
	}	


	/*
	 * Trifon Generate Export Format process; RESULT = 
	 * <C_Invoice>
	 *   <DocumentNo>101</DocumentNo>
	 * </C_Invoice>
	 */
	private void generateExportFormat(Element rootElement, MEXPFormat exportFormat, PO masterPO, int masterID, HashMap<String, Integer> variableMap) throws SQLException, Exception 
	{
		Collection<MEXPFormatLine> formatLines = exportFormat.getFormatLines();
		@SuppressWarnings("unused")
		boolean elementHasValue = false;
		
		//for (int i = 0; i < formatLines.length; i++) {
		for (MEXPFormatLine formatLine : formatLines)
		{	
			if ( formatLine.getType().equals(X_EXP_FormatLine.TYPE_XMLElement) ) {
				// process single XML Attribute
				// Create new element
				Element newElement = outDocument.createElement(formatLine.getValue());
				log.info("Format Line Seach key"+ formatLine.getValue());
				if (formatLine.getAD_Column_ID() == 0) {
					throw new Exception(Msg.getMsg (masterPO.getCtx(), "EXPColumnMandatory"));
				}
				MColumn column = MColumn.get(masterPO.getCtx(), formatLine.getAD_Column_ID());
				if (column == null) {
					throw new Exception(Msg.getMsg (masterPO.getCtx(), "EXPColumnMandatory"));
				}
				if ( column.isVirtualColumn() ) {
					log.info("This is Virtual Column!");
				} else { }
				//log.info("["+column.getColumnName()+"]");
				
				Object value = masterPO.get_Value(column.getColumnName());
				String valueString = null;
				if (value != null) {
					valueString = value.toString();
				} else {
					//  Could remove this exception and create empty XML Element when column do not have value. 
					if (formatLine.isMandatory()) {
						//throw new Exception(Msg.getMsg (masterPO.getCtx(), "EXPFieldMandatory"));
					}
				}
				if (column.getAD_Reference_ID() == DisplayType.Date) {
					if (valueString != null) {
						if (formatLine.getDateFormat() != null && !"".equals(formatLine.getDateFormat())) {
							m_customDateFormat = new SimpleDateFormat( formatLine.getDateFormat() ); // "MM/dd/yyyy"
						
							valueString = m_customDateFormat.format(Timestamp.valueOf (valueString));
							newElement.setAttribute("DateFormat", m_customDateFormat.toPattern()); // Add "DateForamt attribute"
						} else 
						{
								newElement.setAttribute("DateFormat", valueString);	
						}
								
					}
				} else if (column.getAD_Reference_ID() == DisplayType.DateTime) {
					if (valueString != null) {
						if (formatLine.getDateFormat() != null && !"".equals(formatLine.getDateFormat())) {
							m_customDateFormat = new SimpleDateFormat( formatLine.getDateFormat() ); // "MM/dd/yyyy"
							valueString = m_customDateFormat.format(Timestamp.valueOf (valueString));
							newElement.setAttribute("DateFormat", m_customDateFormat.toPattern()); // Add "DateForamt attribute"
						} else {
							newElement.setAttribute("DateFormat", valueString);	
						}
					}
				}
				log.info("EXP Field - column=["+column.getColumnName()+"]; value=" + value);
				if (valueString != null && !"".equals(valueString) && !"null".equals(valueString)) {
					Text newText = outDocument.createTextNode(valueString);
					newElement.appendChild(newText);
					rootElement.appendChild(newElement);
					elementHasValue = true;
				} else {
					// Empty field.
					if (formatLine.isMandatory()) {
						Text newText = outDocument.createTextNode("");
						newElement.appendChild(newText);
						rootElement.appendChild(newElement);
						elementHasValue = true;
					}
				}
			} else if ( formatLine.getType().equals(X_EXP_FormatLine.TYPE_XMLAttribute) ) {
				// process single XML Attribute
				if (formatLine.getAD_Column_ID() == 0) {
					throw new Exception(Msg.getMsg (masterPO.getCtx(), "EXPColumnMandatory"));
				}
				MColumn column = MColumn.get(masterPO.getCtx(), formatLine.getAD_Column_ID());
				if (column == null) {
					throw new Exception(Msg.getMsg (masterPO.getCtx(), "EXPColumnMandatory"));
				}
				if ( column.isVirtualColumn() ) {
					log.info("This is Virtual Column!");
				} else { }
				//log.info("["+column.getColumnName()+"]");
				
				Object value = masterPO.get_Value(column.getColumnName());
				String valueString = null;
				if (value != null) {
					valueString = value.toString();
				} else {
					if (formatLine.isMandatory()) {
						throw new Exception(Msg.getMsg (masterPO.getCtx(), "EXPFieldMandatory"));
					}
				}
/*				if (column.getAD_Reference_ID() == DisplayType.Date) {
					if (valueString != null) {
						if (formatLines[i].getDateFormat() != null && !"".equals(formatLines[i].getDateFormat())) {
							m_customDateFormat = new SimpleDateFormat( formatLines[i].getDateFormat() ); // "MM/dd/yyyy"
							//Date date = m_customDateFormat.parse ( valueString );
							valueString = m_customDateFormat.format(Timestamp.valueOf (valueString));
						} else {
							valueString = m_dateFormat.format (Timestamp.valueOf (valueString));
						}
								
					}
				} else if (column.getAD_Reference_ID() == DisplayType.DateTime) {
					if (valueString != null) {
						if (formatLines[i].getDateFormat() != null && !"".equals(formatLines[i].getDateFormat())) {
							m_customDateFormat = new SimpleDateFormat( formatLines[i].getDateFormat() ); // "MM/dd/yyyy"
							//Date date = m_customDateFormat.parse ( valueString );
							valueString = m_customDateFormat.format(Timestamp.valueOf (valueString));
						} else {
							valueString = m_dateTimeFormat.format (Timestamp.valueOf (valueString));
						}
					}
				}*/
				log.info("EXP Field - column=["+column.getColumnName()+"]; value=" + value);
				if (valueString != null && !"".equals(valueString) && !"null".equals(valueString)) {
					rootElement.setAttribute(formatLine.getValue(), valueString);
					elementHasValue = true;

				} else {
					// Empty field.
				}
			} 
			else if ( formatLine.getType().equals(X_EXP_FormatLine.TYPE_EmbeddedEXPFormat) ) 
			{
				// process Embedded Export Format
				
				int embeddedFormat_ID = formatLine.getEXP_EmbeddedFormat_ID();
				//get from cache
				MEXPFormat embeddedFormat = MEXPFormat.get(masterPO.getCtx(), embeddedFormat_ID, masterPO.get_TrxName());
				
				MTable tableEmbedded = MTable.get(masterPO.getCtx(), embeddedFormat.getAD_Table_ID());
				log.info("Table Embedded = " + tableEmbedded);
				
				final StringBuffer whereClause = new StringBuffer(masterPO.get_KeyColumns()[0] +"=?");

				if (embeddedFormat.getWhereClause() != null & !"".equals(embeddedFormat.getWhereClause())) 
				{
				    whereClause.append(" AND ").append(embeddedFormat.getWhereClause());
				}
				Collection<PO> instances = new Query(masterPO.getCtx(),
					tableEmbedded.getTableName(), whereClause.toString(),
					masterPO.get_TrxName()).setClient_ID().setParameters(
					new Object[] { masterID }).list();

				for (PO instance : instances) 
				{		
        				Element embeddedElement = outDocument.createElement(formatLine.getValue());
        				if (formatLine.getDescription() != null && !"".equals(formatLine.getDescription())) 
        				{
        					embeddedElement.appendChild(outDocument.createComment(formatLine.getDescription()));
        				}
        					
        				generateExportFormat(embeddedElement, embeddedFormat, instance, instance.get_ID(), variableMap);
        				rootElement.appendChild(embeddedElement);
				}


			} 
			else if ( formatLine.getType().equals(X_EXP_FormatLine.TYPE_ReferencedEXPFormat) ) 
			{
				// process Referenced Export Format
				
				int embeddedFormat_ID = formatLine.getEXP_EmbeddedFormat_ID();
				//get from cache
				MEXPFormat embeddedFormat =  MEXPFormat.get(masterPO.getCtx(), embeddedFormat_ID, masterPO.get_TrxName());
				
				MTable tableEmbedded = MTable.get(masterPO.getCtx(), embeddedFormat.getAD_Table_ID());
				log.info("Table Embedded = " + tableEmbedded);

				final StringBuffer whereClause = new StringBuffer(tableEmbedded.getTableName() + "_ID =?");
				if (embeddedFormat.getWhereClause() != null & !"".equals(embeddedFormat.getWhereClause())) 
				{
				    whereClause.append(" AND ").append(embeddedFormat.getWhereClause());
				}
				
				Object value = masterPO.get_Value(tableEmbedded.getTableName() + "_ID");
				if (value == null)
				{	
				    continue;
				}
				
				Collection<PO> instances = new Query(masterPO.getCtx(),tableEmbedded.getTableName(), whereClause.toString(),masterPO.get_TrxName())
                                				.setClient_ID()
                                				.setParameters(value)
                                				.list();

				for (PO instance : instances)
				{		
        				Element embeddedElement = outDocument.createElement(formatLine.getValue());
        				if (formatLine.getDescription() != null && !"".equals(formatLine.getDescription())) 
        				{
        					embeddedElement.appendChild(outDocument.createComment(formatLine.getDescription()));
        				}
        				
        				generateExportFormat(embeddedElement, embeddedFormat, instance, instance.get_ID(), variableMap);
        						rootElement.appendChild(embeddedElement);
				}		

			}
			
			else {
				throw new Exception(Msg.getMsg (masterPO.getCtx(), "EXPUnknownLineType"));
			}
		} 
	}

	/**
	 * @param variableMap
	 * @param variableName
	 */
	@SuppressWarnings("unused")
	private void increaseVariable(HashMap<String, Integer> variableMap,	String variableName) {
		if (variableName != null && !"".equals(variableName) ) {
			Integer var = variableMap.get(variableName);
			if (var == null) {
				var = new Integer(0);
			}
			int intValue = var.intValue();
			intValue++;
			variableMap.put(variableName, new Integer(intValue));
		}
	}

	/**
	 * Utility method which is responsible to create new XML Document
	 * 
	 * @return Document
	 * @throws ParserConfigurationException
	 */
	// create new Document
	Document createNewDocument() throws ParserConfigurationException 
	{
		Document result = null;
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		
		result = documentBuilder.newDocument();
		return result;
	}
	

}
