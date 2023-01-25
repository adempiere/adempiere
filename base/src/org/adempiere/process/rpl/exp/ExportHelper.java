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
import java.util.logging.Level;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.adempiere.core.domains.models.X_EXP_FormatLine;
import org.adempiere.process.rpl.IExportProcessor;
import org.adempiere.process.rpl.IExportProcessorAsWrapper;
import org.adempiere.process.rpl.IExportProcessorDefinition;
import org.apache.commons.codec.binary.Base64;
import org.compiere.Adempiere;
import org.compiere.model.MClient;
import org.compiere.model.MColumn;
import org.compiere.model.MEXPFormat;
import org.compiere.model.MEXPFormatLine;
import org.compiere.model.MEXPProcessor;
import org.compiere.model.MEXPProcessorType;
import org.compiere.model.MImage;
import org.compiere.model.MReplicationDocument;
import org.compiere.model.MReplicationStrategy;
import org.compiere.model.MReplicationTable;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.Query;
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
 * @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 * 				<li>Add exporter based on wrapper
 */
public class ExportHelper {

	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(ExportHelper.class);

	/** XML Document 		*/
	private Document outDocument = null;

	/** Custom Date Format		*/
	private SimpleDateFormat	customDateFormat = null;

	/** Client					*/
	private int clientId = -1;

	/** Replication Strategy	*/
	MReplicationStrategy replicationStrategy = null;


	public ExportHelper(MClient client, MReplicationStrategy replicationStrategy) {
		clientId = client.getAD_Client_ID();
		this.replicationStrategy = replicationStrategy;
	}

	/**
	 * Export Helper constructor
	 * @param ctx
	 * @param clientId
	 */
	public ExportHelper(Properties ctx , int clientId) {
		this.clientId = clientId;
	}
	
	/**
	 * Process - Generate Export Format from document event
	 * @param po
	 * @param document
	 * @param replicationEvent
	 * @return
	 * @throws Exception
	 */
	public String exportRecord (PO po, MReplicationDocument document, Integer replicationEvent) throws Exception {
		return exportRecord (po, MReplicationStrategy.REPLICATION_DOCUMENT, document.getReplicationType(), replicationEvent, document.getEXP_Format_ID());
	}
	
	/**
	 * Process - Generate Export Format from document event
	 * @param po
	 * @param table
	 * @param replicationEvent
	 * @return
	 * @throws Exception
	 */
	public String exportRecord (PO po, MReplicationTable table, Integer replicationEvent) throws Exception {
		return exportRecord (po, MReplicationStrategy.REPLICATION_TABLE, table.getReplicationType(), replicationEvent, table.getEXP_Format_ID());
	}
	
	
	/**
	 * Process - Generate Export Format
	 * Old Compatibility
 	 * @param po
	 * @param replicationMode
	 * @param replicationType
	 * @param replicationEvent
	 * @param optionalExportFormatId optional export format
	 * @return info
	 * @throws Exception
	 */
	public String exportRecord (PO po, Integer replicationMode, String replicationType, Integer replicationEvent) throws Exception {
		return exportRecord(po, replicationMode, replicationType, replicationEvent, 0);
	}

	/**
	 * Process - Generate Export Format
 	 * @param po
	 * @param replicationMode
	 * @param replicationType
	 * @param replicationEvent
	 * @param optionalExportFormatId optional export format
	 * @return info
	 * @throws Exception
	 */
	public String exportRecord (PO po, Integer replicationMode, String replicationType, Integer replicationEvent, int optionalExportFormatId) throws Exception {
		MClient client = MClient.get (po.getCtx(), clientId);
		log.info("Client = " + client.toString());

		log.info("po.getAD_Org_ID() = " + po.getAD_Org_ID());

		log.info("po.get_TrxName() = " + po.get_TrxName());
		if (po.get_TrxName() == null || po.get_TrxName().equals("")) {
			po.set_TrxName("exportRecord");
		}

		log.info("Table = " + po.get_TableName());

		if (po.get_KeyColumns().length < 1) {
			throw new Exception(Msg.getMsg (po.getCtx(), "ExportNoneColumnKeyNotSupported")); //TODO: Create Message.
		}
		// 
		String version = Adempiere.getImplementationVersion();
		MEXPFormat exportFormat = null;
		if(optionalExportFormatId != 0) {
			exportFormat = MEXPFormat.get(po.getCtx(), optionalExportFormatId, po.get_TrxName());
		}
		//	Validate null
		if (exportFormat == null 
				|| exportFormat.getEXP_Format_ID() == 0) {
			exportFormat = MEXPFormat.getFormatByAD_Client_IDAD_Table_IDAndVersion(po.getCtx(), clientId, po.get_Table_ID(), version, po.get_TrxName());
		}
		log.fine("exportFormat = " + exportFormat);
		if (exportFormat == null 
				|| exportFormat.getEXP_Format_ID() == 0) {
			// Fall back to System Client
			MClient systemClient = MClient.get (po.getCtx(), 0);
			log.log(Level.ALL, "SYSTEM client = " + systemClient.toString());
			exportFormat = MEXPFormat.getFormatByAD_Client_IDAD_Table_IDAndVersion(po.getCtx(), 0, po.get_Table_ID(), version, po.get_TrxName());

			if (exportFormat == null || exportFormat.getEXP_Format_ID() == 0) {
				throw new Exception(Msg.getMsg(po.getCtx(), "EXPFormatNotFound"));
			}
		}
		MEXPProcessor mExportProcessor = new MEXPProcessor (po.getCtx(), replicationStrategy.getEXP_Processor_ID(), po.get_TrxName() );
		log.fine("ExportProcessor = " + mExportProcessor);
		MEXPProcessorType expProcessorType = new MEXPProcessorType(po.getCtx(), mExportProcessor.getEXP_Processor_Type_ID(), po.get_TrxName());
		IExportProcessorDefinition processorInstance = expProcessorType.getProcessorInstance();
		//	Export based on wrapper
		if(IExportProcessorAsWrapper.class.isAssignableFrom(processorInstance.getClass())) {
			return WrapperUtil.exportRecord(po, client, replicationMode, replicationType, replicationEvent, replicationStrategy, exportFormat);
		}
		//	else
		outDocument = createNewDocument();

		HashMap<String, Integer> variableMap = new HashMap<String, Integer>();

		Element rootElement = outDocument.createElement(exportFormat.getValue());
		if (exportFormat.getDescription() != null && !"".equals(exportFormat.getDescription())) 
		{
		    rootElement.appendChild(outDocument.createComment(exportFormat.getDescription()));
		}
		rootElement.setAttribute("AD_Client_Value", client.getValue());
		rootElement.setAttribute("Version", exportFormat.getVersion());
		rootElement.setAttribute("ReplicationMode", replicationMode.toString());
		rootElement.setAttribute("ReplicationType", replicationType);
		rootElement.setAttribute("ReplicationEvent", replicationEvent.toString());
		outDocument.appendChild(rootElement);
		generateExportFormat(rootElement, exportFormat, po, po.get_ID(), variableMap);
		//	Export
		if(IExportProcessor.class.isAssignableFrom(processorInstance.getClass())) {
			IExportProcessor exportProcessor = (IExportProcessor) processorInstance;
			exportProcessor.process(po.getCtx(), mExportProcessor, outDocument, Trx.get( po.get_TrxName(), false ));
		}
		return outDocument.toString();
	}

	/**
	 * Process - Generate Export Format
	 * @param exportFormat
	 * @param where
	 * @param replicationMode
	 * @param replicationType
	 * @param replicationEvent
	 *
	 * @return Document
	 * @throws Exception
	 */
	public Document exportRecord (MEXPFormat exportFormat, String where, Integer replicationMode, String replicationType, Integer replicationEvent) throws Exception
	{
		MClient client = MClient.get (exportFormat.getCtx(), clientId);
		MTable table = MTable.get(exportFormat.getCtx(), exportFormat.getAD_Table_ID());
		log.info("Table = " + table);

		Collection<PO> records = new Query(exportFormat.getCtx(), table.getTableName(), exportFormat.getWhereClause(), exportFormat.get_TrxName())
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
				throw new Exception(Msg.getMsg (po.getCtx(), "ExportNoneColumnKeyNotSupported"));//TODO: Create Message.
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
			rootElement.setAttribute("ReplicationMode", replicationMode.toString());
			rootElement.setAttribute("ReplicationType", replicationType);
			rootElement.setAttribute("ReplicationEvent", replicationEvent.toString());
			outDocument.appendChild(rootElement);
			generateExportFormat(rootElement, exportFormat, po, po.get_ID(), variableMap);
		}// finish record read
		return outDocument;
	}


	/**
	 * Trifon Generate Export Format process; result =
	 * <C_Invoice>
	 *   <DocumentNo>101</DocumentNo>
	 * </C_Invoice>
	 * @param rootElement
	 * @param exportFormat
	 * @param masterPO
	 * @param masterID
	 * @param variableMap
	 * @throws SQLException
	 * @throws Exception
	 */
	private void generateExportFormat(Element rootElement, MEXPFormat exportFormat, PO masterPO, int masterID, HashMap<String, Integer> variableMap) throws SQLException, Exception 
	{
		Collection<MEXPFormatLine> formatLines = exportFormat.getFormatLines();
		@SuppressWarnings("unused")
		boolean elementHasValue = false;

		for (MEXPFormatLine formatLine : formatLines)
		{
			if ( formatLine.getType().equals(X_EXP_FormatLine.TYPE_XMLElement) ) {
				// process single XML Attribute
				// Create new element
				Element newElement = outDocument.createElement(formatLine.getValue());
				log.info("Format Line Search key: "+ formatLine.getValue());
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
							customDateFormat = new SimpleDateFormat( formatLine.getDateFormat() ); // "MM/dd/yyyy"

							valueString = customDateFormat.format(Timestamp.valueOf (valueString));
							newElement.setAttribute("DateFormat", customDateFormat.toPattern()); // Add "DateForamt attribute"
						} else
						{
							newElement.setAttribute("DateFormat", valueString);
						}
					}
				} else if (column.getAD_Reference_ID() == DisplayType.DateTime) {
					if (valueString != null) {
						if (formatLine.getDateFormat() != null && !"".equals(formatLine.getDateFormat())) {
							customDateFormat = new SimpleDateFormat( formatLine.getDateFormat() ); // "MM/dd/yyyy"
							valueString = customDateFormat.format(Timestamp.valueOf (valueString));
							newElement.setAttribute("DateFormat", customDateFormat.toPattern()); // Add "DateForamt attribute"
						} else {
							newElement.setAttribute("DateFormat", valueString);
						}
					}
				} else if (column.getAD_Reference_ID() == DisplayType.Image     // TODO - Trifon - Handle Binary and Image data
					//	|| column.getAD_Reference_ID() == DisplayType.Binary
				) {
					if (value != null) {
						int imageId = ((Integer)value).intValue();
						// Read AD_Image
						MImage image = null;
						image = new MImage(masterPO.getCtx(), imageId, null); //image = MImage.get(masterPO.getCtx(), imageId);

						if (image.getAD_Image_ID() > 0) {
							elementHasValue = true;
							newElement.setAttribute("id", String.valueOf( image.getAD_Image_ID() ) );

							Element nameElement = outDocument.createElement( "Name" );
							Text newText = outDocument.createTextNode( image.getName() );
							nameElement.appendChild( newText );
							newElement.appendChild( nameElement );

							if (image.getDescription() != null && image.getDescription().length() > 0) {
								Element descriptionElement = outDocument.createElement( "Description" );
								Text newDesc = outDocument.createTextNode( image.getDescription() );
								descriptionElement.appendChild( newDesc );
								newElement.appendChild( descriptionElement );
							}

							if (image.getImageURL() != null && image.getImageURL().length() > 0) {
								Element imageURLElement = outDocument.createElement( "ImageURL" );
								Text newImageUrl = outDocument.createTextNode( image.getImageURL() );
								imageURLElement.appendChild( newImageUrl );
								newElement.appendChild( imageURLElement );
							}

							if (image.getBinaryData() != null && image.getBinaryData().length > 0) {
								Element binaryDataElement = outDocument.createElement( "BinaryData" );
								byte[] encodedBytes = null;
								encodedBytes = Base64.encodeBase64( image.getBinaryData() );

								String encodedString = new String( encodedBytes );
							    //System.out.println("encodedBytes " + valueString);

							    //byte[] decodedBytes = Base64.decodeBase64(encodedBytes);
							    //System.out.println("decodedBytes " + new String(decodedBytes));

								Text encodedText = outDocument.createTextNode( encodedString );
								binaryDataElement.appendChild( encodedText );
								newElement.appendChild( binaryDataElement );
							}
						}
					}
				}
				log.info("EXP Field - column=["+column.getColumnName()+"]; value=" + value);
				if (valueString != null && !"".equals(valueString) && !"null".equals(valueString)) {
					if (column.getAD_Reference_ID() == DisplayType.Image) {
						// already added
					} else {
						Text newText = outDocument.createTextNode(valueString);
						newElement.appendChild(newText);
					}
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
				Collection<PO> instances = new Query(masterPO.getCtx(), tableEmbedded.getTableName(), whereClause.toString(),
						masterPO.get_TrxName())
					.setApplyAccessFilter(true) //Adempiere-65 change
					.setParameters(new Object[] { masterID })
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
				
				String columnName = "";
				if(formatLine.getAD_Reference_ID()== DisplayType.Table | formatLine.getAD_Reference_ID()==DisplayType.Search)
				{
					MColumn column = MColumn.get(masterPO.getCtx(), formatLine.getAD_Column_ID());
					columnName = column.getColumnName();
				}
				else
				{
					columnName = tableEmbedded.getTableName() + "_ID";
				}

				Object value = masterPO.get_Value(columnName);
				if (value == null)
				{
				    continue;
				}
				
				Collection<PO> instances = null;
				if (tableEmbedded.getTableName().equals("C_Country") || tableEmbedded.getTableName().equals("C_UOM")
//						|| tableEmbedded.getTableName().equals("AD_Client") NOT - lead to duplication!
//						|| tableEmbedded.getAccessLevel()
				) {
					// SYSTEM records
					instances = new Query(masterPO.getCtx(), tableEmbedded.getTableName(), whereClause.toString(), masterPO.get_TrxName())
//						.setClient_ID()
						.setParameters(value)
						.list();
					// Tenant specific records
					Collection<PO> tenantSpecificInstances = new Query(masterPO.getCtx(), tableEmbedded.getTableName(), whereClause.toString(), masterPO.get_TrxName())
						//.setClient_ID()
						.setApplyAccessFilter(true) //Adempiere-65 change
						.setParameters(value)
						.list();

					instances.addAll( tenantSpecificInstances );
				} else {
					instances = new Query(masterPO.getCtx(), tableEmbedded.getTableName(), whereClause.toString(), masterPO.get_TrxName())
						//.setClient_ID()
						.setApplyAccessFilter(true) //Adempiere-65 change
						.setParameters(value)
						.list();
				}

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
	 * Utility method which is responsible to create new XML Document
	 * 
	 * @return Document
	 * @throws ParserConfigurationException
	 */
	Document createNewDocument() throws ParserConfigurationException 
	{
		Document result = null;
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

		result = documentBuilder.newDocument();
		return result;
	}
}
