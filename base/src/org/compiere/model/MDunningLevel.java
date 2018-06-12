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
import java.util.List;
import java.util.Properties;


/**
 *	Dunning Level Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MDunningLevel.java,v 1.3 2006/07/30 00:51:02 jjanke Exp $
 *  
 */
public class MDunningLevel extends X_C_DunningLevel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4869909989789113387L;
	
	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param C_DunningLevel_ID id
	 *	@param trxName transaction
	 */
	public MDunningLevel (Properties ctx, int C_DunningLevel_ID, String trxName) {
		super (ctx, C_DunningLevel_ID, trxName);
	}	//	MDunningLevel

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MDunningLevel (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MDunningLevel
	
	private MDunning dunning = null;
	
	/**
	 * 	get Parent
	 *	@return Parent Dunning
	 */
	public MDunning getParent() {
		if (dunning==null) 
			dunning = new MDunning(getCtx(), getC_Dunning_ID(), get_TrxName());
		return dunning;
	}
	
	/**
	 * 	get Previous Levels
	 *	@return Array of previous DunningLevels
	 */
	public List<MDunningLevel> getPreviousLevels() {
		// Prevent generation if not Sequentially
		if (!getParent().isCreateLevelsSequentially()) {
			return null;
		}
		//	Return list
		return new Query(
				getCtx(),
				I_C_DunningLevel.Table_Name,
				"C_Dunning_ID = ? AND (DaysAfterDue + DaysBetweenDunning) < ?",
				get_TrxName())
		.setOnlyActiveRecords(true)
		.setParameters(getC_Dunning_ID(), getDaysAfterDue().intValue() + getDaysBetweenDunning())
		.<MDunningLevel>list();
	}
}	//	MDunningLevel
