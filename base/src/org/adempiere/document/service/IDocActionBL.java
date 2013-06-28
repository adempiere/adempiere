package org.adempiere.document.service;

import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.util.ISingletonService;
import org.compiere.process.DocAction;

public interface IDocActionBL extends ISingletonService
{

	/**
	 * 
	 * @param document
	 * @param docAction
	 * @return true if document was processed
	 * @throws IllegalArgumentException if 'document' is not instance of {@link DocAction}.
	 */
	boolean processIt(Object document, String docAction);

	/**
	 * Process document. If there is any error it throws exception. If success the document is saved.
	 * 
	 * @param document
	 * @param docAction
	 * @param expectedDocStatus (optional) If specified (not null), after processing it is checked that document shall have expected DocStatus
	 * @throws IllegalArgumentException if document is not a valid {@link DocAction}
	 * @throws AdempiereException if processing fails or document does not have expected DocStatus
	 */
	void processEx(Object document, String docAction, String expectedDocStatus);

	/**
	 * Check if a document is completed.
	 * 
	 * A documented is considered complete when it is COmpleted or CLosed.
	 * 
	 * Please note that a reversed document is not considered to be complete.
	 * 
	 * @param document
	 * @return true if document is completed
	 * @throws IllegalArgumentException if document is not a valid {@link DocAction}.
	 */
	boolean isComplete(Object document);

	boolean isDocumentTable(String tableName);

	/**
	 * Convert given <code>document</code> to {@link DocAction} interface
	 * 
	 * @param document
	 * @return document as {@link DocAction}
	 * @throws IllegalArgumentException if document is null or it cannot be converted to {@link DocAction}
	 */
	DocAction getDocAction(Object document);

	/**
	 * Convert given <code>document</code> to {@link DocAction} interface. If the document cannot be converted to {@link DocAction} null is returned.
	 * 
	 * @param document
	 * @return document as {@link DocAction} or null
	 */
	DocAction getDocActionOrNull(Object document);

	/**
	 * Retrieve C_DocType_ID for given record. C_DocType_ID and C_DocTypeTarget_ID columns will be checked.
	 * 
	 * Please note, is not necessary that the given table to be a true document.
	 * 
	 * @param ctx
	 * @param AD_Table_ID
	 * @param Record_ID
	 * @return
	 */
	int getC_DocType_ID(Properties ctx, int AD_Table_ID, int Record_ID);

	/**
	 * Retrieve document status for given record.
	 * 
	 * @param ctx
	 * @param AD_Table_ID
	 * @param Record_ID
	 * @return
	 */
	String getDocStatusOrNull(Properties ctx, int AD_Table_ID, int Record_ID);

	/**
	 * Retrieve DocumentNo for given record. If no value was found, the model will be loaded and {@link #getDocumentNo(Object)} will be used.
	 * 
	 * @param ctx
	 * @param AD_Table_ID
	 * @param Record_ID
	 * @return document no
	 */
	String getDocumentNo(Properties ctx, final int adTableId, final int recordId);

	/**
	 * Retrieve DocumentNo for given model. Steps to fetch the DocumentNo are (in this order):
	 * <ul>
	 * <li>if <code>model</code> is a {@link DocAction} instance, then {@link DocAction#getDocumentNo()} will be used
	 * <li>if <code>model</code> has DocumentNo column and is not null, that column will be used
	 * <li>if <code>model</code> has Value column and is not null, that column will be used
	 * <li>if <code>model</code> has Name column and is not null, that column will be used
	 * <li>if none are present, record's ID converted to String will be used.
	 * </ul>
	 * 
	 * NOTE: this algorithm was implemented due to requirements from http://dewiki908/mediawiki/index.php/Mo73_03918_Massendruck_f%C3%BCr_Mahnungen_%282013021410000132%29#IT2_-_G01_-_Mass_Printing.
	 * 
	 * @param model
	 * @return document no
	 */
	String getDocumentNo(Object model);
}
