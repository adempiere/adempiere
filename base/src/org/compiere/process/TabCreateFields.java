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
package org.compiere.process;

import java.util.List;

import org.adempiere.core.domains.models.I_AD_Column;
import org.compiere.model.MColumn;
import org.compiere.model.MField;
import org.compiere.model.MTab;
import org.compiere.model.Query;
import org.compiere.util.AdempiereSystemError;


/**
 *	Create Field from Table Column.
 *	(which do not exist in the Tab yet)
 *	
 *  @author Jorg Janke
 *  @version $Id: TabCreateFields.java,v 1.3 2006/07/30 00:51:02 jjanke Exp $
 * 
 * @author Teo Sarca
 * 			<li>BF [ 2827782 ] TabCreateFields process not setting entity type well
 * 				https://sourceforge.net/tracker/?func=detail&atid=879332&aid=2827782&group_id=176962
 * 
 * @author Silvano Trinchero
 *      <li>BF [ 2891218] Wrong behavior in entity type settings for customization entity types
 *        https://sourceforge.net/tracker/?func=detail&aid=2891218&group_id=176962&atid=879332 
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *			<a href="https://github.com/adempiere/adempiere/issues/1325">
 * 			@see FR [ 1325 ] ColumnName UUID is generated as mandatory</a>
 */
public class TabCreateFields extends SvrProcess
{
	/**	Tab NUmber				*/
	private int	tabId= 0;
	/**	Counter				*/
	private int count = 0;
	
	/**
	 * 	prepare
	 */
	protected void prepare ()
	{
		tabId = getRecord_ID();
	}	//	prepare

	/**
	 * 	Process
	 *	@return info
	 *	@throws Exception
	 */
	protected String doIt () throws Exception {
		MTab tab = new MTab (getCtx(), tabId, get_TrxName());
		if (tabId == 0 || tab.get_ID() == 0)
			throw new AdempiereSystemError("@NotFound@: @AD_Tab_ID@ " + tabId);
		log.info(tab.toString());
		//
		String whereClause = "NOT EXISTS(SELECT 1 FROM AD_Field f WHERE AD_Column.AD_Column_ID = f.AD_Column_ID"
				+ " 						AND AD_Column.AD_Table_ID = ?"	//	#1
				+ " 						AND f.AD_Tab_ID = ?)"		//	#2
				+ " AND AD_Table_ID=?"			//	#3
				+ " AND NOT (Name LIKE 'Created%' OR Name LIKE 'Updated%')";
		List<MColumn> columns = new Query(getCtx(), I_AD_Column.Table_Name, whereClause, get_TrxName())
			.setParameters(tab.getAD_Table_ID(), tab.getAD_Tab_ID(), tab.getAD_Table_ID())
			.setOnlyActiveRecords(true)
			.setOrderBy(I_AD_Column.COLUMNNAME_Name)
			.list();
		//	Iterate It
		columns.stream().forEach(column -> {
			//
			MField field = new MField (tab);
			field.setColumn(column);
							
			// F3P: assign field entity type -> changed to obey to the following rule:
			//	if column entitytype == D, then get tab's entity type
			//  if not, keep field entity type (ie: entitytype is D if and only if both are D)
			
			if ("D".equals(column.getEntityType())) {
				field.setEntityType(tab.getEntityType());
			} else {
				field.setEntityType(column.getEntityType());
			}
			// end F3P
			
			if (column.isKey()
					|| column.getColumnName().equals(I_AD_Column.COLUMNNAME_UUID)) {
				field.setIsDisplayed(false);
			}
			//	Save
			if (field.save()) {
				addLog(0, null, null, column.getName());
				count++;
			}
		});
		//	
		return "@Created@ #" + count;
	}	//	doIt
}	//	TabCreateFields
