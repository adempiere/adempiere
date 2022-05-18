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
 * Contributor(s): Carlos Parada www.erpya.com                                *
 *****************************************************************************/
package org.spin.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.Env;

/**
 * User Authentication Model Class
 * @author Carlos Parada, cparada@erpya.com, ERPCyA http://www.erpya.com
 */
public class MADUserAuthentication extends X_AD_User_Authentication{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create User Authentication
	 * @return
	 */
	public static MADUserAuthentication createUserAuthentication() {
		return new MADUserAuthentication(Env.getCtx(), 0, null);
	}

	/**
	 * Constructor
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MADUserAuthentication(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	
	/**
	 * Constructor
	 * @param ctx
	 * @param AD_User_Authentication_ID
	 * @param trxName
	 */
	public MADUserAuthentication(Properties ctx, int AD_User_Authentication_ID, String trxName) {
		super(ctx, AD_User_Authentication_ID, trxName);
	}
	
	/**
	 * Set User
	 * @param userId
	 * @return
	 */
	public MADUserAuthentication withUserId(int userId) {
		setAD_User_ID(userId);
		return this;
	}

	/**
	 * Set Application Registation
	 * @param appRegistrationId
	 * @return
	 */
	public MADUserAuthentication withAppRegistrationId(int appRegistrationId) {
		setAD_AppRegistration_ID(appRegistrationId);
		return this;
	}

	/**
	 * Set Access Token
	 * @param accessToken
	 * @return
	 */
	public MADUserAuthentication withAccessToken(String accessToken) {
		setAccessToken(accessToken);
		return this;
	}
	
	/**
	 * Set RefreshToken
	 * @param refreshToken
	 * @return
	 */
	public MADUserAuthentication withRefreshToken(String refreshToken) {
		setRefreshToken(refreshToken);
		return this;
	}
}
