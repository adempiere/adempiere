/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.                                     *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net                                                  *
 * or https://github.com/adempiere/adempiere/blob/develop/license.html        *
 *****************************************************************************/

package org.compiere.FA.process;

import java.sql.Timestamp;
import org.compiere.process.SvrProcess;

/** Generated Process for (Deliver Assets)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.3
 */
public abstract class AssetDeliveryAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "Asset_Delivery";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Deliver Assets";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 201;
	/**	Parameter Name for Asset Group	*/
	public static final String A_ASSET_GROUP_ID = "A_Asset_Group_ID";
	/**	Parameter Name for Product	*/
	public static final String M_PRODUCT_ID = "M_Product_ID";
	/**	Parameter Name for Business Partner 	*/
	public static final String C_BPARTNER_ID = "C_BPartner_ID";
	/**	Parameter Name for User/Contact	*/
	public static final String AD_USER_ID = "AD_User_ID";
	/**	Parameter Name for Fixed Asset	*/
	public static final String A_ASSET_ID = "A_Asset_ID";
	/**	Parameter Name for Guarantee Date	*/
	public static final String GUARANTEEDATE = "GuaranteeDate";
	/**	Parameter Name for Expired Guarantee Mail	*/
	public static final String NOGUARANTEE_MAILTEXT_ID = "NoGuarantee_MailText_ID";
	/**	Parameter Name for Attach Asset	*/
	public static final String ATTACHASSET = "AttachAsset";
	/**	Parameter Name for Send EMail	*/
	public static final String SENDEMAIL = "SendEMail";
	/**	Parameter Value for Asset Group	*/
	private int assetGroupId;
	/**	Parameter Value for Product	*/
	private int productId;
	/**	Parameter Value for Business Partner 	*/
	private int bPartnerId;
	/**	Parameter Value for User/Contact	*/
	private int userId;
	/**	Parameter Value for Fixed Asset	*/
	private int assetId;
	/**	Parameter Value for Guarantee Date	*/
	private Timestamp guaranteeDate;
	/**	Parameter Value for Expired Guarantee Mail	*/
	private int mailTextId;
	/**	Parameter Value for Attach Asset	*/
	private boolean isAttachAsset;
	/**	Parameter Value for Send EMail	*/
	private boolean isSendEMail;

	@Override
	protected void prepare() {
		assetGroupId = getParameterAsInt(A_ASSET_GROUP_ID);
		productId = getParameterAsInt(M_PRODUCT_ID);
		bPartnerId = getParameterAsInt(C_BPARTNER_ID);
		userId = getParameterAsInt(AD_USER_ID);
		assetId = getParameterAsInt(A_ASSET_ID);
		guaranteeDate = getParameterAsTimestamp(GUARANTEEDATE);
		mailTextId = getParameterAsInt(NOGUARANTEE_MAILTEXT_ID);
		isAttachAsset = getParameterAsBoolean(ATTACHASSET);
		isSendEMail = getParameterAsBoolean(SENDEMAIL);
	}

	/**	 Getter Parameter Value for Asset Group	*/
	protected int getAssetGroupId() {
		return assetGroupId;
	}

	/**	 Setter Parameter Value for Asset Group	*/
	protected void setAssetGroupId(int assetGroupId) {
		this.assetGroupId = assetGroupId;
	}

	/**	 Getter Parameter Value for Product	*/
	protected int getProductId() {
		return productId;
	}

	/**	 Setter Parameter Value for Product	*/
	protected void setProductId(int productId) {
		this.productId = productId;
	}

	/**	 Getter Parameter Value for Business Partner 	*/
	protected int getBPartnerId() {
		return bPartnerId;
	}

	/**	 Setter Parameter Value for Business Partner 	*/
	protected void setBPartnerId(int bPartnerId) {
		this.bPartnerId = bPartnerId;
	}

	/**	 Getter Parameter Value for User/Contact	*/
	protected int getUserId() {
		return userId;
	}

	/**	 Setter Parameter Value for User/Contact	*/
	protected void setUserId(int userId) {
		this.userId = userId;
	}

	/**	 Getter Parameter Value for Fixed Asset	*/
	protected int getAssetId() {
		return assetId;
	}

	/**	 Setter Parameter Value for Fixed Asset	*/
	protected void setAssetId(int assetId) {
		this.assetId = assetId;
	}

	/**	 Getter Parameter Value for Guarantee Date	*/
	protected Timestamp getGuaranteeDate() {
		return guaranteeDate;
	}

	/**	 Setter Parameter Value for Guarantee Date	*/
	protected void setGuaranteeDate(Timestamp guaranteeDate) {
		this.guaranteeDate = guaranteeDate;
	}

	/**	 Getter Parameter Value for Expired Guarantee Mail	*/
	protected int getMailTextId() {
		return mailTextId;
	}

	/**	 Setter Parameter Value for Expired Guarantee Mail	*/
	protected void setMailTextId(int mailTextId) {
		this.mailTextId = mailTextId;
	}

	/**	 Getter Parameter Value for Attach Asset	*/
	protected boolean isAttachAsset() {
		return isAttachAsset;
	}

	/**	 Setter Parameter Value for Attach Asset	*/
	protected void setAttachAsset(boolean isAttachAsset) {
		this.isAttachAsset = isAttachAsset;
	}

	/**	 Getter Parameter Value for Send EMail	*/
	protected boolean isSendEMail() {
		return isSendEMail;
	}

	/**	 Setter Parameter Value for Send EMail	*/
	protected void setSendEMail(boolean isSendEMail) {
		this.isSendEMail = isSendEMail;
	}

	/**	 Getter Parameter Value for Process ID	*/
	public static final int getProcessId() {
		return ID_FOR_PROCESS;
	}

	/**	 Getter Parameter Value for Process Value	*/
	public static final String getProcessValue() {
		return VALUE_FOR_PROCESS;
	}

	/**	 Getter Parameter Value for Process Name	*/
	public static final String getProcessName() {
		return NAME_FOR_PROCESS;
	}
}