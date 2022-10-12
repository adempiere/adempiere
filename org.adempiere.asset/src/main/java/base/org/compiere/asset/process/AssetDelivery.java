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
package org.compiere.asset.process;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.adempiere.core.domains.models.I_A_Asset;
import org.adempiere.core.domains.models.I_A_Asset_Delivery;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.asset.model.MAsset;
import org.compiere.asset.model.MAssetDelivery;
import org.compiere.model.MClient;
import org.compiere.model.MMailText;
import org.compiere.model.MProductDownload;
import org.compiere.model.MUser;
import org.compiere.model.Query;
import org.compiere.util.Msg;
import org.compiere.util.Util;
import org.spin.queue.notification.DefaultNotifier;
import org.spin.queue.util.QueueLoader;

/**
 *	Deliver Assets Electronically
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: AssetDelivery.java,v 1.2 2006/07/30 00:51:02 jjanke Exp $
 * 	@author 	Michael Judd BF [ 2736995 ] - toURL() in java.io.File has been deprecated
 * 	@author 	Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 * 	@see 		https://github.com/adempiere/adempiere/issues/3085
 */
public class AssetDelivery extends AssetDeliveryAbstract {
	private MClient		client = null;
	//
	private MMailText	mailTemplate = null;
	/**	Counter	*/
	private AtomicInteger count;
	/**	Error	*/
	private AtomicInteger errors;
	/**	Reminders	*/
	private AtomicInteger reminders;
	/**	Delivered	*/
	private AtomicInteger delivered;


	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare() {
		super.prepare();
		if (getGuaranteeDate() == null) {
			setGuaranteeDate(new Timestamp (System.currentTimeMillis()));
		}
		//
		client = MClient.get(getCtx());
		count = new AtomicInteger();
		errors = new AtomicInteger();
		reminders = new AtomicInteger();
		delivered = new AtomicInteger();
	}	//	prepare

	/**
	 *  Perform process.
	 *  @return Message to be translated
	 *  @throws Exception
	 */
	protected String doIt() throws java.lang.Exception {
		log.info("");
		long start = System.currentTimeMillis();
		if(!isSelection()) {
			StringBuffer whereClause = new StringBuffer();
			List<Object> parameters = new ArrayList<Object>();
			//	Asset selected
			if (getAssetId() != 0) {
				whereClause.append("A_Asset_ID = ?");
				parameters.add(getAssetId());
			}
			//	Asset Group
			if (getAssetGroupId() != 0) {
				if(whereClause.length() > 0) {
					whereClause.append(" AND ");
				}
				whereClause.append("A_Asset_Group_ID = ?");
				parameters.add(getAssetGroupId());
			}
			//	Product
			if (getProductId() != 0) {
				if(whereClause.length() > 0) {
					whereClause.append(" AND ");
				}
				whereClause.append("M_Product_ID = ?");
				parameters.add(getProductId());
			}
			//	Validate Parameters
			if(parameters.size() == 0) {
				throw new AdempiereException("@RestrictSelection@");
			}
			//	No mail to expired
			if (getMailTextId() == 0) {
				if(whereClause.length() > 0) {
					whereClause.append(" AND ");
				}
				whereClause.append("GuaranteeDate >= ?");
				parameters.add(getGuaranteeDate());
			}
			//	Get from Process
			new Query(getCtx(), I_A_Asset.Table_Name, whereClause.toString(), get_TrxName())
				.setParameters(parameters)
				.setOrderBy(I_A_Asset.COLUMNNAME_Value)
				.<MAsset>list()
				.forEach(asset -> deliverAsset(asset));
		} else {
			((List<MAsset>) getInstancesForSelection(get_TrxName())).forEach(asset -> deliverAsset(asset));
		}
		//	
		log.info("Count=" + count + ", Errors=" + errors + ", Reminder=" + reminders
			+ " - " + (System.currentTimeMillis()-start) + "ms");
		return "@IsDelivered@=" + delivered + " - @Sent@=" + count + " - @Errors@=" + errors;
	}	//	doIt
	
	/**
	 * Deliver Asset
	 * @param asset
	 */
	private void deliverAsset(MAsset asset) {
		if(getBPartnerId() != 0) {
			int businessPartnerId = -1;
			int userId = -1;
			boolean isReturned = getParameterAsBoolean("IsReturnedToOrganization");
			if(!isReturned) {
				businessPartnerId = getBPartnerId();
				userId = getUserId();
			}
			asset.setIsInPosession(isReturned);
			asset.setC_BPartner_ID(businessPartnerId);
			asset.setAD_User_ID(userId);
			asset.saveEx();
			MAssetDelivery assetDelivery = new MAssetDelivery(asset, getBPartnerId(), 0, getParameterAsTimestamp(I_A_Asset_Delivery.COLUMNNAME_MovementDate));
			String description = getParameterAsString(I_A_Asset_Delivery.COLUMNNAME_Description);
			if(!Util.isEmpty(description)) {
				assetDelivery.setDescription(description);
			}
			assetDelivery.saveEx();
			delivered.getAndIncrement();
		}
		//	Send Mail
		if(isSendEMail()) {
			notifyAsset(asset);
		}
	}

	/**
	 * Notify Mail of asset
	 * @param asset
	 */
	private void notifyAsset(MAsset asset) {
		//	Guarantee Expired
		if (asset.getGuaranteeDate() != null && asset.getGuaranteeDate().before(getGuaranteeDate())) {
			if (getMailTextId() != 0) {
				sendNoGuaranteeMail(asset);
				reminders.getAndIncrement();
			}
		} else {
			String msg = sendDeliveryeMail (asset);
			addLog(asset.getA_Asset_ID(), null, null, msg);
			if (msg.startsWith ("** ")) {
				errors.getAndIncrement();
			} else {
				count.getAndIncrement();
			}
		}
	}

	/**
	 * 	Send No Guarantee EMail
	 * 	@param asset
	 * 	@return message - delivery errors start with **
	 */
	private String sendNoGuaranteeMail (MAsset asset) {
		if (asset.getAD_User_ID() == 0) {
			return Msg.parseTranslation(getCtx(), "@A_Asset_ID@ @AD_User_ID@ @NotFound@");
		}
		MUser user = new MUser (getCtx(), asset.getAD_User_ID(), get_TrxName());
		//	Template
		if (mailTemplate == null || mailTemplate.getR_MailText_ID() != getMailTextId()) {
			mailTemplate = new MMailText (getCtx(), getMailTextId(), get_TrxName());
		}
		//	Validate
		if (Util.isEmpty(mailTemplate.getMailHeader())) {
			return Msg.parseTranslation(getCtx(), "@Subject@ @NotFound@");
		}
		//	Create Mail
		mailTemplate.setPO(user);
		mailTemplate.setPO(asset);
		String message = mailTemplate.getMailText(true);
		//	Get instance for notifier
		DefaultNotifier notifier = (DefaultNotifier) QueueLoader.getInstance().getQueueManager(DefaultNotifier.QUEUETYPE_DefaultNotifier)
				.withContext(getCtx())
				.withTransactionName(get_TrxName());
		//	Send notification to queue
		notifier
			.clearMessage()
			.withApplicationType(DefaultNotifier.DefaultNotificationType_UserDefined)
			.withUserId(getAD_User_ID())
			.addRecipient(user.getAD_User_ID())
			.withText(message)
			.withDescription(mailTemplate.getMailHeader())
			.withEntity(MAsset.Table_ID, asset.getA_Asset_ID());
		//	Add to queue
		notifier.addToQueue();
		//
		return user.getEMail();
	}	//	sendNoGuaranteeMail

	
	/**************************************************************************
	 * 	Deliver Asset
	 * 	@param asset
	 * 	@return message - delivery errors start with **
	 */
	private String sendDeliveryeMail (MAsset asset) {
		log.fine("A_Asset_ID=" + asset.getA_Asset_ID());
		long start = System.currentTimeMillis();
		//
		if (asset.getAD_User_ID() == 0) {
			return Msg.parseTranslation(getCtx(), "@A_Asset_ID@ @AD_User_ID@ @NotFound@");
		}
		MUser user = new MUser (getCtx(), asset.getAD_User_ID(), get_TrxName());
		if (asset.getProductR_MailText_ID() == 0)
			return Msg.parseTranslation(getCtx(), "@ProductR_MailText_ID@ @NotFound@");
		if (mailTemplate == null || mailTemplate.getR_MailText_ID() != asset.getProductR_MailText_ID())
			mailTemplate = new MMailText (getCtx(), asset.getProductR_MailText_ID(), get_TrxName());
		if (mailTemplate.getMailHeader() == null || mailTemplate.getMailHeader().length() == 0) {
			return Msg.parseTranslation(getCtx(), "@Subject@ @NotFound@");
		}
		//	
		mailTemplate.setUser(user);
		mailTemplate.setPO(asset);
		String message = mailTemplate.getMailText(true);
		//	Get instance for notifier
		DefaultNotifier notifier = (DefaultNotifier) QueueLoader.getInstance().getQueueManager(DefaultNotifier.QUEUETYPE_DefaultNotifier)
				.withContext(getCtx())
				.withTransactionName(get_TrxName());
		//	Send notification to queue
		notifier
			.clearMessage()
			.withApplicationType(DefaultNotifier.DefaultNotificationType_UserDefined)
			.withUserId(getAD_User_ID())
			.addRecipient(user.getAD_User_ID())
			.withText(message)
			.withDescription(mailTemplate.getMailHeader())
			.withEntity(MAsset.Table_ID, asset.getA_Asset_ID());
		if (isAttachAsset()) {
			MProductDownload[] pdls = asset.getProductDownloads();
			if (pdls != null) {
				for (MProductDownload download : pdls) {
					File fileToDownload = download.getDownloadFile(client.getDocumentDir());
					notifier.addAttachment(fileToDownload);
				}
			} else {
				log.warning("No DowloadURL for Asset=" + asset);
			}
		}
		//	Add to queue
		notifier.addToQueue();
		//
		log.fine((System.currentTimeMillis()-start) + " ms");
		//	success
		return user.getEMail() + " - " + asset.getProductVersionNo();
	}	//	deliverIt
}	//	AssetDelivery
