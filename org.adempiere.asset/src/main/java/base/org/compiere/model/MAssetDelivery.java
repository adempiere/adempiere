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
import java.sql.Timestamp;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.compiere.util.EMail;

/**
 *  Asset Delivery Model
 *
 *  @author Jorg Janke
 *  @version $Id: MAssetDelivery.java,v 1.3 2006/07/30 00:51:03 jjanke Exp $
 *  @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 *  @see	https://github.com/adempiere/adempiere/issues/3085
 */
public class MAssetDelivery extends X_A_Asset_Delivery
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1731010685101745675L;

	/**
	 * 	Constructor
	 * 	@param ctx context
	 * 	@param A_Asset_Delivery_ID id or 0
	 * 	@param trxName trx
	 */
	public MAssetDelivery (Properties ctx, int A_Asset_Delivery_ID, String trxName)
	{
		super (ctx, A_Asset_Delivery_ID, trxName);
		if (A_Asset_Delivery_ID == 0)
		{
			setMovementDate (new Timestamp (System.currentTimeMillis ()));
		}
	}	//	MAssetDelivery

	/**
	 *  Load Constructor
	 *  @param ctx context
	 *  @param rs result set record
	 *	@param trxName transaction
	 */
	public MAssetDelivery (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MAssetDelivery

	/**
	 * 	Create Asset Delivery for HTTP Request
	 * 	@param asset asset
	 * 	@param request request
	 * 	@param businessPartnerId business partner
	 * 	@param userId BP Contact
	 */
	public MAssetDelivery (MAsset asset, HttpServletRequest request, int userId) {
		super (asset.getCtx(), 0, asset.get_TrxName());
		setAD_Client_ID(asset.getAD_Client_ID());
		setAD_Org_ID(asset.getAD_Org_ID());
		//	Asset Info
		setA_Asset_ID (asset.getA_Asset_ID());
		setLot(asset.getLot());
		setSerNo(asset.getSerNo());
		setVersionNo(asset.getVersionNo());
		//
		setMovementDate (new Timestamp (System.currentTimeMillis ()));
		//	Request
		setURL(request.getRequestURL().toString());
		setReferrer(request.getHeader("Referer"));
		setRemote_Addr(request.getRemoteAddr());
		setRemote_Host(request.getRemoteHost());
		//	Who
		setAD_User_ID(userId);
		//
		saveEx();
	}	//	MAssetDelivery

	/**
	 * 	Create Asset Delivery for EMail
	 * 	@param asset asset
	 * 	@param userId BP Contact
	 * 	@param movementDate optional delivery date
	 */
	public MAssetDelivery (MAsset asset, int businessPartnerId, int userId, Timestamp movementDate) {
		super (asset.getCtx(), 0, asset.get_TrxName());
		//	Asset Info
		setA_Asset_ID (asset.getA_Asset_ID());
		setLot(asset.getLot());
		setSerNo(asset.getSerNo());
		setVersionNo(asset.getVersionNo());
		//
		if(movementDate == null) {
			movementDate = new Timestamp (System.currentTimeMillis());
		}
		setMovementDate(movementDate);
		//	Who
		if(userId != 0) {
			setAD_User_ID(userId);
		}
		//	Validate Business Partner
		if(businessPartnerId != 0) {
			setC_BPartner_ID(businessPartnerId);
		}
	}	//	MAssetDelivery
	
	/**
	 * Confirm delivery Asset from email
	 * @param email
	 */
	public void confirmMailDelivery(EMail email) {
		setEMail(email.getTo().toString());
		setMessageID(email.getMessageID());
	}
	
	/**
	 * 	String representation
	 *	@return info
	 */
	public String toString () {
		StringBuffer sb = new StringBuffer ("MAssetDelivery[")
			.append (get_ID ())
			.append(",A_Asset_ID=").append(getA_Asset_ID())
			.append(",MovementDate=").append(getMovementDate())
			.append ("]");
		return sb.toString ();
	}	//	toString

}	//	MAssetDelivery

