/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (coffee) <Company or Author Name> All Rights Reserved.           *
 * This program is free software; you can redistribute it and/or              *
 * modify it under the terms of the GNU General Public License                *
 * as published by the Free Software Foundation; either version 2             *
 * of the License, or (at your option) any later version.                     *
 * This program is distributed in the hope that it will be useful,            *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of             *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.                       *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *                                                                            *
 * @author Teo Sarca, t.sarca@metas.ro METAS GROUP 							  *
 *				                                                              *
 *****************************************************************************/

package org.adempiere.ad.services.impl;

import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.ad.services.IDeveloperModeBL;
import org.adempiere.core.domains.models.X_AD_Message;
import org.adempiere.util.Check;
import org.compiere.model.MEntityType;
import org.compiere.model.MMessage;
import org.compiere.model.MSysConfig;
import org.compiere.model.M_Element;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.CacheMgt;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.Language;


public class DeveloperModeBL implements IDeveloperModeBL
{
	public static final DeveloperModeBL instance = new DeveloperModeBL();

	public static final String SYSCONFIG_DeveloperMode = "de.metas.adempiere.debug";
	public static final String CTX_SysContextMarker = DeveloperModeBL.class.getName() + ".SysContext";
	public static final String CTX_ParentContext = DeveloperModeBL.class.getName() + ".ParentContext";

	private final CLogger logger = CLogger.getCLogger(getClass());

	protected DeveloperModeBL()
	{
		super();
	}

	@Override
	public boolean isEnabled()
	{
		return MSysConfig.getBooleanValue(SYSCONFIG_DeveloperMode, false);
	}

	@Override
	public boolean createMessageOrElement(final String adLanguage, final String text, final boolean checkMessage, final boolean checkElement)
	{
		final boolean[] retValue = new boolean[] { false };
		try
		{
			executeAsSystem(new ContextRunnable()
			{

				@Override
				public void run(Properties sysCtx)
				{
					retValue[0] = createMessageOrElement0(sysCtx, adLanguage, text, checkMessage, checkElement);
				}
			});
		}
		catch (final Exception e)
		{
			logger.log(Level.SEVERE, e.getLocalizedMessage(), e);
			return false;
		}

		return retValue[0];
	}

	private boolean createMessageOrElement0(Properties ctx, final String adLanguage, final String text, boolean checkMessage, boolean checkElement)
	{
		final String trxName = null;

		final MMessage message = checkMessage ? MMessage.get(ctx, text) : null;
		if (message != null)
		{
			return checkInsertTrl(message, adLanguage);
		}

		final M_Element element = checkElement ? M_Element.get(ctx, text) : null;
		if (element != null)
		{
			return checkInsertTrl(element, adLanguage);
		}

		boolean createMessage = checkMessage && isValidMessageValue(text);
		boolean createElement = checkElement && isValidColumnName(text);

		if (!createElement && !createMessage)
		{
			return false;
		}

		if (createElement && createMessage)
		{
			// TODO: ask developer what to do
			createMessage = true;
			createElement = false;
		}

		if (createMessage)
		{
			final MMessage messageNew = new MMessage(ctx, 0, trxName);
			messageNew.setValue(text);
			messageNew.setMsgType(X_AD_Message.MSGTYPE_Information);
			messageNew.setMsgText(text);
			messageNew.setEntityType(getEntityType(ctx));
			// The save will trigger CCache reset for "AD_Message" which will clear message from Msg class
			messageNew.saveEx();
			logger.log(Level.WARNING, "Created: " + messageNew + ", Value=" + messageNew.getValue() + ", EntityType=" + messageNew.getEntityType(), new Exception());
		}
		if (createElement)
		{
			final M_Element elementNew = new M_Element(ctx, 0, trxName);
			elementNew.setColumnName(text);
			elementNew.setName(text);
			elementNew.setPrintName(text);
			elementNew.setEntityType(getEntityType(ctx));
			elementNew.saveEx();
			logger.log(Level.WARNING, "Created: " + element + ", ColumnName=" + elementNew.getColumnName() + ", EntityType=" + elementNew.getEntityType(), new Exception());
		}
		return createMessage || createElement;
	}

	/**
	 * Check and insert translation records if they don't exist
	 * 
	 * @param po
	 * @param adLanguage
	 * @return true if translations were inserted or they are already available
	 */
	private boolean checkInsertTrl(PO po, String adLanguage)
	{
		if (hasTranslation(po, adLanguage))
		{
			return true;
		}

		final boolean changed = po.insertTranslations();
		if (changed)
		{
			// Trigger org.compiere.util.Msg cache reset
			CacheMgt.get().reset(MMessage.Table_Name);
		}
		return changed;
	}

	private boolean isValidColumnName(String columnName)
	{
		return !Check.isEmpty(columnName, true)
				&& columnName.indexOf(" ") == -1
				&& columnName.indexOf(".") == -1
				&& columnName.indexOf("?") == -1
				&& columnName.indexOf("{") == -1
				&& columnName.indexOf("}") == -1;
	}

	private boolean isValidMessageValue(String adMessage)
	{
		return !Check.isEmpty(adMessage, true)
				&& adMessage.indexOf(" ") == -1;
	}

	@Override
	public boolean isJailedSysContext(Properties ctx)
	{
		return ctx != null && "Y".equals(ctx.getProperty(CTX_SysContextMarker));
	}

	private Properties createSysContext()
	{
		final Properties ctx = Env.getCtx();

		final Properties sysCtx = new Properties(ctx);
		sysCtx.setProperty(CTX_SysContextMarker, "Y");
		sysCtx.put(CTX_ParentContext, ctx);
		Env.setContext(sysCtx, "#AD_Client_ID", 0);
		Env.setContext(sysCtx, "#AD_Org_ID", 0);
		Env.setContext(sysCtx, "#AD_Role_ID", 0);
		Env.setContext(sysCtx, "#AD_User_ID", 0);

		return sysCtx;
	}

	private String getEntityType(Properties ctx)
	{
		final StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		boolean skipStackTraceElement = true;
		for (StackTraceElement ste : stackTrace)
		{
			final String classnameFQ = ste.getClassName();
			if (classnameFQ == null)
			{
				continue;
			}

			if (classnameFQ.startsWith(getClass().getName()))
			{
				skipStackTraceElement = false;
				continue;
			}
			if (skipStackTraceElement
					|| classnameFQ.startsWith("java.lang.")
					|| classnameFQ.startsWith("sun."))
			{
				continue;
			}

			int idx = classnameFQ.lastIndexOf(".");
			if (idx <= 0)
				continue;

			final String packageName = classnameFQ.substring(0, idx);

			//
			// Get EntityType by PackageName
			final MEntityType[] entityTypes = MEntityType.getEntityTypes(Env.getCtx());
			MEntityType entityType = null;
			for (MEntityType et : entityTypes)
			{
				String modelPackage = et.getModelPackage();
				if (Check.isEmpty(modelPackage, true)
						|| "org.compiere.model".equals(modelPackage)
						|| "org.adempiere.model".equals(modelPackage))
				{
					continue;
				}
				if (modelPackage.endsWith(".model"))
				{
					modelPackage = modelPackage.substring(0, modelPackage.length() - ".model".length());
				}
				if (packageName.equals(modelPackage) || packageName.startsWith(modelPackage + "."))
				{
					entityType = et;
					break;
				}
			}

			if (entityType != null)
			{
				return entityType.getEntityType();
			}

		}

		String entityType = Env.getContext(ctx, "#EntityType");
		if (Check.isEmpty(entityType))
		{
			entityType = "U";
		}
		return entityType;
	}

	@Override
	public void executeAsSystem(ContextRunnable runnable)
	{
		final Properties sysCtx = createSysContext();

		final String logMigrationScriptsOld = Ini.getProperty(Ini.P_LOGMIGRATIONSCRIPT);
		try
		{
			Ini.setProperty(Ini.P_LOGMIGRATIONSCRIPT, true);

			runnable.run(sysCtx);
		}
		finally
		{
			Ini.setProperty(Ini.P_LOGMIGRATIONSCRIPT, logMigrationScriptsOld);
		}
	}

	private boolean hasTranslation(PO po, String adLanguage)
	{
		if (Language.isBaseLanguage(adLanguage))
		{
			return true;
		}

		final String trxName = null;
		final String tableName = po.get_TableName();
		final int recordId = po.get_ID();
		final String sql = "SELECT COUNT(*) FROM " + tableName + "_Trl WHERE " + tableName + "_ID=? AND AD_Language=?";

		int count = DB.getSQLValueEx(trxName, sql, recordId, adLanguage);
		return count == 1;
	}
}
