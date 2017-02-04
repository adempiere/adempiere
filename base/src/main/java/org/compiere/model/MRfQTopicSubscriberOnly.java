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


/**
 *	Subcriber Topic Only List (positive - i.e. must be a match if exists)
 *	
 *  @author Jorg Janke
 *  @version $Id: MRfQTopicSubscriberOnly.java,v 1.3 2006/07/30 00:51:03 jjanke Exp $
 */
public class MRfQTopicSubscriberOnly extends X_C_RfQ_TopicSubscriberOnly
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7744686970311507813L;

	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param C_RfQ_TopicSubscriberOnly_ID id
	 *	@param trxName transaction
	 */
	public MRfQTopicSubscriberOnly (Properties ctx, int C_RfQ_TopicSubscriberOnly_ID, String trxName)
	{
		super (ctx, C_RfQ_TopicSubscriberOnly_ID, trxName);
	}	//	MRfQTopicSubscriberOnly

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MRfQTopicSubscriberOnly (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MRfQTopicSubscriberOnly

}	//	MRfQTopicSubscriberOnly
