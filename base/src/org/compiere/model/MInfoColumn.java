/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.AccessSqlParser.TableInfo;
import org.compiere.util.Env;
import org.compiere.util.Evaluatee;
import org.compiere.util.Evaluator;
import org.compiere.util.Util;

/**
 * Info Window Column Model
 * 
 * @author Jorg Janke
 * @version $Id: MInfoColumn.java,v 1.2 2006/07/30 00:51:03 jjanke Exp $
 */
public class MInfoColumn extends X_AD_InfoColumn
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4317064257861102601L;

	/**
	 * Standard Constructor
	 * 
	 * @param ctx context
	 * @param AD_InfoColumn_ID id
	 * @param trxName transaction
	 */
	public MInfoColumn(Properties ctx, int AD_InfoColumn_ID, String trxName)
	{
		super(ctx, AD_InfoColumn_ID, trxName);
	} // MInfoColumn

	/**
	 * Load Constructor
	 * 
	 * @param ctx context
	 * @param rs result set
	 * @param trxName transaction
	 */
	public MInfoColumn(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	} // MInfoColumn

	/**
	 * Feature #1449 check column read access
	 * 
	 * @author Sachin Bhimani
	 * @param tableInfos
	 * @return false if current role don't have read access to the column, false
	 *         otherwise
	 */
	public boolean isColumnAccess(TableInfo[] tableInfos)
	{
		int index = getSelectClause().indexOf(".");
		if (index == getSelectClause().lastIndexOf(".") && index >= 0)
		{
			String synonym = getSelectClause().substring(0, index);
			String column = getSelectClause().substring(index + 1);
			for (TableInfo tableInfo : tableInfos)
			{
				if (tableInfo.getSynonym() != null && tableInfo.getSynonym().equals(synonym))
				{
					String tableName = tableInfo.getTableName();
					MTable mTable = MTable.get(Env.getCtx(), tableName);
					if (mTable != null)
					{
						MColumn mColumn = mTable.getColumn(column);
						if (mColumn != null)
						{
							if (!MRole.getDefault().isColumnAccess(mTable.getAD_Table_ID(), mColumn.getAD_Column_ID(),
									true))
							{
								return false;
							}
						}
					}
				}
			}
		}
		return true;
	}

	/**
	 * Feature #1449
	 * 
	 * @author Sachin Bhimani
	 * @param ctx
	 * @param windowNo
	 * @return
	 */
	public boolean isDisplayed(final Properties ctx, final int windowNo)
	{
		if (!isDisplayed())
			return false;

		if (Util.isEmpty(getDisplayLogic(), true))
			return true;

		Evaluatee evaluatee = new Evaluatee() {
			public String get_ValueAsString(String variableName)
			{
				return Env.getContext(ctx, windowNo, variableName, true);
			}
		};

		boolean retValue = Evaluator.evaluateLogic(evaluatee, getDisplayLogic());
		if (log.isLoggable(Level.FINEST))
			log.finest(getName() + " (" + getDisplayLogic() + ") => " + retValue);
		return retValue;
	}

} // MInfoColumn
