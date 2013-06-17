package org.adempiere.ad.migration.xml.impl;

import java.util.Properties;

import org.adempiere.ad.migration.xml.IXMLHandler;
import org.adempiere.model.InterfaceWrapperHelper;
import org.compiere.model.I_AD_Column;
import org.compiere.model.I_AD_MigrationData;
import org.compiere.model.I_AD_MigrationStep;
import org.compiere.model.MColumn;
import org.compiere.model.MTable;
import org.compiere.model.X_AD_MigrationStep;
import org.compiere.util.CLogger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

class MigrationDataHandler implements IXMLHandler<I_AD_MigrationData>
{
	private final transient CLogger logger = CLogger.getCLogger(getClass());

	public static final String NODENAME = "Data";

	/**
	 * Node: Column - Column Name
	 */
	private static final String NODE_Column = "Column";
	private static final String NODE_AD_Column_ID = "AD_Column_ID";
	private static final String NODE_isOldNull = "isOldNull";
	private static final String NODE_oldValue = "oldValue";
	private static final String NODE_isNewNull = "isNewNull";

	@Override
	public Node toXmlNode(Document document, I_AD_MigrationData data)
	{
		logger.info("Exporting data: " + data);

		final I_AD_Column column = data.getAD_Column();
		final Element dataNode = document.createElement(NODENAME);
		dataNode.setAttribute(NODE_Column, column.getColumnName());

		// TODO: handle the case when AD_Column_ID is not present in our database.
		// Idea: export/import also the column name. In this way we can configure to not import if the column does not exist
		dataNode.setAttribute(NODE_AD_Column_ID, Integer.toString(data.getAD_Column_ID()));

		final I_AD_MigrationStep step = data.getAD_MigrationStep();
		if (!X_AD_MigrationStep.ACTION_Insert.equals(step.getAction()))
		{
			if (data.isOldNull())
				dataNode.setAttribute(NODE_isOldNull, "true");
			else
				dataNode.setAttribute(NODE_oldValue, data.getOldValue());
		}
		if (data.isNewNull() || data.getNewValue() == null)
		{
			dataNode.setAttribute(NODE_isNewNull, "true");
		}
		else
		{
			dataNode.appendChild(document.createTextNode(data.getNewValue()));
		}

		return dataNode;
	}

	@Override
	public boolean fromXmlNode(I_AD_MigrationData data, Element element)
	{
		data.setIsOldNull("true".equals(element.getAttribute(NODE_isOldNull)));
		data.setOldValue(element.getAttribute(NODE_oldValue));
		data.setColumnName(element.getAttribute(NODE_Column));

		data.setIsNewNull("true".equals(element.getAttribute(NODE_isNewNull)));
		data.setNewValue(element.getTextContent());

		setAD_Column_ID(data, element.getAttribute(NODE_AD_Column_ID));

		InterfaceWrapperHelper.save(data);
		logger.info("Imported data: " + data);
		return true;
	}

	private void setAD_Column_ID(final I_AD_MigrationData data, final String columnIdStr)
	{
		if (columnIdStr == null || columnIdStr.trim().isEmpty())
		{
			data.setAD_Column_ID(-1);
			return;
		}

		final int columnId = Integer.parseInt(columnIdStr.trim());
		if (columnId <= 0)
		{
			data.setAD_Column_ID(-1);
			return;
		}

		final I_AD_MigrationStep step = data.getAD_MigrationStep();
		final int tableId = step.getAD_Table_ID();
		if (tableId <= 0)
		{
			data.setAD_Column_ID(-1);
			return;
		}

		final Properties ctx = InterfaceWrapperHelper.getCtx(data);
		final MTable table = MTable.get(ctx, tableId);
		final MColumn column = table.getColumn(data.getColumnName());
		if (column == null)
		{
			data.setAD_Column_ID(-1);
			return;
		}

		if (column.getAD_Column_ID() != columnId)
		{
			data.setAD_Column_ID(column.getAD_Column_ID());
			return;
		}

		data.setAD_Column_ID(columnId);
	}

}
