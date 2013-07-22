package org.adempiere.document.service.impl;

import java.util.Properties;

import org.adempiere.document.service.IDocActionBL;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.InterfaceWrapperHelper;
import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.util.CLogger;

public abstract class AbstractDocActionBL implements IDocActionBL
{
	private static final CLogger logger = CLogger.getCLogger(AbstractDocActionBL.class);

	protected abstract String retrieveString(Properties ctx, int adTableId, int recordId, final String columnName);

	protected abstract Object retrieveModelOrNull(Properties ctx, int adTableId, int recordId);

	@Override
	public boolean processIt(final Object document, final String action)
	{

		final DocAction docAction = getDocAction(document);

		try
		{
			return processIt0(docAction, action);
		}
		catch (Exception e)
		{
			logger.saveError(e.getClass().getSimpleName()
					+ " while trying to process document '" + document
					+ "' with docAction '" + action + "'", e);
			throw new AdempiereException(e);
		}
	}
	
	protected boolean processIt0(final DocAction doc, final String action) throws Exception
	{
		return doc.processIt(action);
	}

	@Override
	public void processEx(Object document, String docAction, String expectedDocStatus)
	{
		final DocAction doc = getDocAction(document);

		boolean ok = false;
		try
		{
			ok = processIt0(doc, docAction);
		}
		catch (Exception e)
		{
			logger.saveError(e.getClass().getSimpleName()
					+ " while trying to process document '" + document
					+ "' with docAction '" + docAction + "'", e);
			throw new AdempiereException("Error Processing " + doc.getDocumentInfo() + " (@DocAction@:" + docAction + "): " + doc.getProcessMsg(), e);
		}

		if (!ok)
		{
			throw new AdempiereException("Error Processing " + doc.getDocumentInfo() + " (@DocAction@:" + docAction + "): " + doc.getProcessMsg());
		}
		// IMPORTANT: we need to save 'doc', not 'document', because in case 'document' is a grid tab, then the PO 'doc'
		// is a different instance. If we save 'document' in that case, the changes to 'doc' will be lost.
		InterfaceWrapperHelper.save(doc);
		InterfaceWrapperHelper.refresh(document);

		if (expectedDocStatus != null && !expectedDocStatus.equals(doc.getDocStatus()))
		{
			throw new AdempiereException("Document " + doc.getDocumentInfo() + " does not have expected status (Expected=" + expectedDocStatus + ", actual=" + doc.getDocStatus() + ")");
		}
	}

	@Override
	public DocAction getDocAction(final Object document)
	{
		final boolean throwEx = true;
		return getDocAction(document, throwEx);
	}

	@Override
	public DocAction getDocActionOrNull(final Object document)
	{
		final boolean throwEx = false;
		return getDocAction(document, throwEx);
	}

	protected abstract DocAction getDocAction(final Object document, boolean throwEx);

	@Override
	public boolean isComplete(Object document)
	{
		final DocAction doc = getDocAction(document);
		final String docStatus = doc.getDocStatus();
		return DocumentEngine.STATUS_Completed.equals(docStatus)
				|| DocumentEngine.STATUS_Closed.equals(docStatus);
	}

	@Override
	public String getDocStatusOrNull(Properties ctx, int adTableId, int recordId)
	{
		return retrieveString(ctx, adTableId, recordId, "DocStatus");
	}

	@Override
	public String getDocumentNo(final Properties ctx, final int adTableId, final int recordId)
	{
		if (adTableId <= 0 || recordId <= 0)
		{
			return null;
		}

		//
		// First we try to fetch the DocumentNo column from database
		final String documentNo = retrieveString(ctx, adTableId, recordId, "DocumentNo");
		if (documentNo != null)
		{
			return documentNo;
		}

		//
		// Second, we load the model and we use the algorithm from getDocumentNo(model)
		final Object model = retrieveModelOrNull(ctx, adTableId, recordId);
		if (model == null)
		{
			return null;
		}
		return getDocumentNo(model);
	}

	@Override
	public String getDocumentNo(Object model)
	{
		//
		// First try: document's DocumentNo if available
		final DocAction doc = getDocActionOrNull(model);
		if (doc != null)
		{
			return doc.getDocumentNo();
		}

		// Try DocumentNo column if available
		final String documentNo = InterfaceWrapperHelper.getValueOrNull(model, "DocumentNo");
		if (documentNo != null)
		{
			return documentNo;
		}

		// Try Value column if available
		final String value = InterfaceWrapperHelper.getValueOrNull(model, "Value");
		if (value != null)
		{
			return value;
		}

		// Try Name column if available
		final String name = InterfaceWrapperHelper.getValueOrNull(model, "Name");
		if (name != null)
		{
			return name;
		}

		// Fallback: use model's ID
		final int recordId = InterfaceWrapperHelper.getId(model);
		return String.valueOf(recordId);
	}
}
