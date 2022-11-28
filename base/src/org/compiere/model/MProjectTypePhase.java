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

import org.adempiere.core.domains.models.X_C_Phase;
import org.compiere.util.Env;

/**
 * 	Project Type Phase Model
 *
 *	@author Jorg Janke
 *	@version $Id: MProjectTypePhase.java,v 1.2 2006/07/30 00:51:02 jjanke Exp $
 */
public class MProjectTypePhase extends X_C_Phase
{
	/**
	 *
	 */
	private static final long serialVersionUID = -5111329904215151458L;

	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param C_Phase_ID id
	 *	@param trxName trx
	 */
	public MProjectTypePhase (Properties ctx, int C_Phase_ID, String trxName)
	{
		super (ctx, C_Phase_ID, trxName);
		if (C_Phase_ID == 0)
		{
		//	setC_Phase_ID (0);			//	PK
		//	setC_ProjectType_ID (0);	//	Parent
		//	setName (null);
			setSeqNo (0);
			setStandardQty (Env.ZERO);
		}
	}	//	MProjectTypePhase

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName
	 */
	public MProjectTypePhase (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MProjectTypePhase

	/**
	 * get Project Type Tasks
	 * @return list task
	 */
	public List<MProjectTypeTask> getTasks() {
		return new Query(getCtx(), MProjectTypeTask.Table_Name, MProjectTypeTask.COLUMNNAME_C_Phase_ID + "=?", get_TrxName())
				.setClient_ID()
				.setParameters(getC_Phase_ID())
				.setOrderBy(COLUMNNAME_SeqNo)
				.list();
	}


}	//	MProjectTypePhase
