/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2015 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpya.com                                  *
 *****************************************************************************/
package org.spin.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.adempiere.core.domains.models.X_AD_UserSocialMedia;
import org.compiere.model.Query;

/**
 * @author Yamel Senih, ySenih@erpya.com, ERPCyA http://www.erpya.com
 *		Add for handle social media for user
 */
public class MADUserSocialMedia extends X_AD_UserSocialMedia {

	public MADUserSocialMedia(Properties ctx, int AD_UserSocialMedia_ID, String trxName) {
		super(ctx, AD_UserSocialMedia_ID, trxName);
	}

	public MADUserSocialMedia(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Get user Social Media
	 * @param ctx
	 * @param userId
	 * @param transactionName
	 * @return
	 */
	public static List<MADUserSocialMedia> getSocialMedias (Properties ctx, int userId, String transactionName) {
		return new Query(ctx, MADUserSocialMedia.Table_Name, MADUserSocialMedia.COLUMNNAME_AD_User_ID + " = ?", transactionName)
				.setParameters(userId)
				.setOnlyActiveRecords(true)
				.list();
	}	//	get
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7151089570474994623L;

}
