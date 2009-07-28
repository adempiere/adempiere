/**********************************************************************
 * This file is part of Adempiere ERP Bazaar                          * 
 * http://www.adempiere.org                                           * 
 *                                                                    * 
 * Copyright (C) Victor Perez	                                      * 
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
 *  - Victor Perez (victor.perez@e-evolution.com	 )                *
 *                                                                    *
 * Sponsors:                                                          *
 *  - e-Evolution (http://www.e-evolution.com/)                       *
 **********************************************************************/

package org.eevolution.process;

import java.util.logging.Level;

import org.adempiere.model.MViewColumn;
import org.adempiere.model.MViewJoin;
import org.compiere.model.MColumn;
import org.compiere.model.MTable;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;

/**
 *	Create Column View
 *  @author victor.perez@e-evolution.com, www.e-evolution.com
 *  @version $Id: $
 */
public class CreateViewColumn extends SvrProcess
{	
	/** Record ID */
	protected int p_Record_ID = 0;	
	/**
	 * 	Get Parameters
	 */
	protected void prepare ()
	{
		
		p_Record_ID = getRecord_ID();
		ProcessInfoParameter[] parameters = getParameter();
		for (ProcessInfoParameter para: parameters)
		{
			String name = para.getParameterName();
			if (para.getParameter() == null)
				;
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}

	/**
	 * 	Process - Generate Export Format
	 *	@return info
	 */
	@SuppressWarnings("unchecked")
	protected String doIt () throws Exception
	{	
		MViewJoin join = new MViewJoin(getCtx(), p_Record_ID, get_TrxName());
		

			for(MViewColumn vcol:join.getADViewColunms())
			{	
				vcol.deleteEx(true);
			}
			
			for(MColumn attr: join.getEntityAttributes())
			{	
				MViewColumn column = new MViewColumn(attr);
				column.setAD_ViewJoin_ID(join.getAD_ViewJoin_ID());
				column.setSelectClause(join.getTableName() + "." + attr.getColumnName());
				column.setColumnName(MTable.get(getCtx(),join.getAD_Table_ID()).getTableName()+"_"+attr.getColumnName());
				column.setAD_View_ID(join.getAD_View_ID());
				column.saveEx();
				addLog(attr.getColumnName());
			}		
		return "@Ok@";
	}
}
