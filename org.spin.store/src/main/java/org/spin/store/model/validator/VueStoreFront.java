/************************************************************************************
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, C.A.                     *
 * Contributor(s): Yamel Senih ysenih@erpya.com                                     *
 * This program is free software: you can redistribute it and/or modify             *
 * it under the terms of the GNU General Public License as published by             *
 * the Free Software Foundation, either version 2 of the License, or                *
 * (at your option) any later version.                                              *
 * This program is distributed in the hope that it will be useful,                  *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of                   *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.	See the                     *
 * GNU General Public License for more details.                                     *
 * You should have received a copy of the GNU General Public License                *
 * along with this program.	If not, see <https://www.gnu.org/licenses/>.            *
 ************************************************************************************/
package org.spin.store.model.validator;
import org.adempiere.core.domains.models.I_W_Basket;
import org.adempiere.core.domains.models.I_W_BasketLine;
import org.adempiere.core.domains.models.X_W_Basket;
import org.adempiere.core.domains.models.X_W_BasketLine;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MAttachment;
import org.compiere.model.MAttribute;
import org.compiere.model.MAttributeValue;
import org.compiere.model.MBPartner;
import org.compiere.model.MClient;
import org.compiere.model.MProduct;
import org.compiere.model.MStore;
import org.compiere.model.MTax;
import org.compiere.model.MTaxCategory;
import org.compiere.model.MUser;
import org.compiere.model.MUserRoles;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.spin.store.model.MWCategory;
import org.spin.store.util.ElasticSearchHelper;
import org.spin.store.util.VueStoreFrontUtil;
import org.spin.store.util.support.elasticsearch.Attribute;
import org.spin.store.util.support.elasticsearch.Category;
import org.spin.store.util.support.elasticsearch.Product;
import org.spin.store.util.support.elasticsearch.TaxCategory;

/**
 * Handle Vue Store Front integration from ADempiere
 * @author Yamel Senih ysenih@erpya.com
 *
 */
public class VueStoreFront implements ModelValidator {

	/** Logger */
	private static CLogger log = CLogger.getCLogger(VueStoreFront.class);
	/** Client */
	private int clientId = -1;
	
	@Override
	public void initialize(ModelValidationEngine engine, MClient client) {
		// client = null for global validator
		if (client != null) {
			clientId = client.getAD_Client_ID();
			log.info(client.toString());
		} else {
			log.info("Initializing global validator: " + this.toString());
		}		
		engine.addModelChange(MProduct.Table_Name, this);
		engine.addModelChange(MWCategory.Table_Name, this);
		engine.addModelChange(MTaxCategory.Table_Name, this);
		engine.addModelChange(MTax.Table_Name, this);
		engine.addModelChange(MAttribute.Table_Name, this);
		engine.addModelChange(MAttributeValue.Table_Name, this);
		engine.addModelChange(MAttachment.Table_Name, this);
		engine.addModelChange(MUser.Table_Name, this);
		engine.addModelChange(I_W_Basket.Table_Name, this);
		engine.addModelChange(I_W_BasketLine.Table_Name, this);
	}

	@Override
	public int getAD_Client_ID() {
		return clientId;
	}

	@Override
	public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID) {
		log.info("AD_User_ID=" + AD_User_ID);
		return null;
	}

	@Override
	public String modelChange(PO po, int type) throws Exception {
		if(type == TYPE_BEFORE_NEW) {
			if(po.get_TableName().equals(I_W_BasketLine.Table_Name)) {
				X_W_BasketLine basketLine = (X_W_BasketLine) po;
				VueStoreFrontUtil.setBasketLineDefaultValues(basketLine);
			} else if(po instanceof MUser) {
				MUser user = (MUser) po;
				if(user.isWebstoreUser()) {
					MStore defaultStore = VueStoreFrontUtil.getDefaultStore(Env.getAD_Org_ID(Env.getCtx()));
					if(defaultStore == null) {
						throw new AdempiereException("@W_Store_ID@ @NotFound@");
					}
					MBPartner customer = VueStoreFrontUtil.getTemplate(user.getCtx(), defaultStore.get_ValueAsInt(VueStoreFrontUtil.COLUMNNAME_C_TemplateBPartner_ID), user.get_TrxName());
					customer.setValue(user.getEMail());
					customer.setTaxID(user.getEMail());
					customer.setName(user.getName());
					customer.saveEx();
					user.setC_BPartner_ID(customer.getC_BPartner_ID());
				}
			}
		} else if(type == TYPE_BEFORE_CHANGE) {
			if(po.get_TableName().equals(I_W_BasketLine.Table_Name)) {
				X_W_BasketLine basketLine = (X_W_BasketLine) po;
				if(basketLine.is_ValueChanged(I_W_BasketLine.COLUMNNAME_Qty)
						|| basketLine.is_ValueChanged(I_W_BasketLine.COLUMNNAME_Price)) {
					VueStoreFrontUtil.setBasketLineDefaultValues(basketLine);
				}
			}
		} else if(type == TYPE_BEFORE_DELETE) {
			
		} else if(type == TYPE_AFTER_NEW) {
			if(po instanceof MProduct) {
				MProduct product = (MProduct) po;
				if(product.is_ValueChanged(MProduct.COLUMNNAME_IsWebStoreFeatured)
						&& !product.isWebStoreFeatured()) {
					ElasticSearchHelper.getInstance().connect().index(Product.newInstance().withProduct(product)).close();
				} else {
					ElasticSearchHelper.getInstance().connect().index(Product.newInstance().withProduct(product)).close();
				}
			} else if(po instanceof MWCategory) {
				MWCategory group = (MWCategory) po;
				ElasticSearchHelper.getInstance().connect().index(Category.newInstance().withCategoy(group)).close();
			} else if(po instanceof MTaxCategory) {
				MTaxCategory taxCategory = (MTaxCategory) po;
				ElasticSearchHelper.getInstance().connect().index(TaxCategory.newInstance().withTaxCategory(taxCategory)).close();
			} else if(po instanceof MTax) {
				MTax tax = (MTax) po;
				MTaxCategory taxCategory = (MTaxCategory) tax.getC_TaxCategory();
				ElasticSearchHelper.getInstance().connect().index(TaxCategory.newInstance().withTaxCategory(taxCategory)).close();
			} else if(po instanceof MAttribute) {
				MAttribute attribute = (MAttribute) po;
				ElasticSearchHelper.getInstance().connect().index(Attribute.newInstance().withAttribute(attribute)).close();
			} else if(po instanceof MAttributeValue) {
				MAttributeValue attributeValue = (MAttributeValue) po;
				MAttribute attribute = (MAttribute) attributeValue.getM_Attribute();
				ElasticSearchHelper.getInstance().connect().index(Attribute.newInstance().withAttribute(attribute)).close();
			} else if(po instanceof MAttachment) {
				MAttachment attachment = (MAttachment) po;
				if(attachment.getAD_Table_ID() == MProduct.Table_ID) {
					MProduct product = MProduct.get(attachment.getCtx(), attachment.getRecord_ID());
					ElasticSearchHelper.getInstance().connect().index(Product.newInstance().withProduct(product)).close();
				}
			} else if(po instanceof MUser) {
				MUser user = (MUser) po;
				if(user.isWebstoreUser()) {
					MStore defaultStore = VueStoreFrontUtil.getDefaultStore(Env.getAD_Org_ID(Env.getCtx()));
					if(defaultStore == null) {
						throw new AdempiereException("@W_Store_ID@ @NotFound@");
					}
					int defaultRoleId = defaultStore.get_ValueAsInt(VueStoreFrontUtil.COLUMNNAME_CustomerRole_ID);
					if(defaultRoleId <= 0) {
						throw new AdempiereException("@" + VueStoreFrontUtil.COLUMNNAME_CustomerRole_ID + "@ @NotFound@");
					}
					//	Add role
					new MUserRoles(user.getCtx(), user.getAD_User_ID(), defaultRoleId, user.get_TrxName()).saveEx();
					MBPartner customer = VueStoreFrontUtil.getTemplate(user.getCtx(), defaultStore.get_ValueAsInt(VueStoreFrontUtil.COLUMNNAME_C_TemplateBPartner_ID), user.get_TrxName());
					customer.setValue(user.getEMail());
					customer.setTaxID(user.getEMail());
					customer.setName(user.getName());
					customer.saveEx();
				}
			}
		} else if(type == TYPE_AFTER_CHANGE) {
			if(po instanceof MProduct) {
				MProduct product = (MProduct) po;
				ElasticSearchHelper.getInstance().connect().index(Product.newInstance().withProduct(product)).close();
			} else if(po instanceof MWCategory) {
				MWCategory group = (MWCategory) po;
				ElasticSearchHelper.getInstance().connect().index(Category.newInstance().withCategoy(group)).close();
			} else if(po instanceof MTaxCategory) {
				MTaxCategory taxCategory = (MTaxCategory) po;
				ElasticSearchHelper.getInstance().connect().index(TaxCategory.newInstance().withTaxCategory(taxCategory)).close();
			} else if(po instanceof MTax) {
				MTax tax = (MTax) po;
				MTaxCategory taxCategory = (MTaxCategory) tax.getC_TaxCategory();
				ElasticSearchHelper.getInstance().connect().index(TaxCategory.newInstance().withTaxCategory(taxCategory)).close();
			} else if(po instanceof MAttribute) {
				MAttribute attribute = (MAttribute) po;
				ElasticSearchHelper.getInstance().connect().index(Attribute.newInstance().withAttribute(attribute)).close();
			} else if(po instanceof MAttributeValue) {
				MAttributeValue attributeValue = (MAttributeValue) po;
				MAttribute attribute = (MAttribute) attributeValue.getM_Attribute();
				ElasticSearchHelper.getInstance().connect().index(Attribute.newInstance().withAttribute(attribute)).close();
			} else if(po instanceof MAttachment) {
				MAttachment attachment = (MAttachment) po;
				if(attachment.getAD_Table_ID() == MProduct.Table_ID) {
					MProduct product = MProduct.get(attachment.getCtx(), attachment.getRecord_ID());
					ElasticSearchHelper.getInstance().connect().index(Product.newInstance().withProduct(product)).close();
				}
			}
		} else if(type == TYPE_AFTER_DELETE) {
			if(po instanceof MProduct) {
				MProduct product = (MProduct) po;
				ElasticSearchHelper.getInstance().connect().delete(Product.newInstance().withProduct(product)).close();
			} else if(po instanceof MWCategory) {
				MWCategory group = (MWCategory) po;
				ElasticSearchHelper.getInstance().connect().delete(Category.newInstance().withCategoy(group)).close();
			} else if(po instanceof MTaxCategory) {
				MTaxCategory taxCategory = (MTaxCategory) po;
				ElasticSearchHelper.getInstance().connect().delete(TaxCategory.newInstance().withTaxCategory(taxCategory)).close();
			} else if(po instanceof MTax) {
				MTax tax = (MTax) po;
				MTaxCategory taxCategory = (MTaxCategory) tax.getC_TaxCategory();
				ElasticSearchHelper.getInstance().connect().delete(TaxCategory.newInstance().withTaxCategory(taxCategory)).close();
			} else if(po instanceof MAttribute) {
				MAttribute attribute = (MAttribute) po;
				ElasticSearchHelper.getInstance().connect().delete(Attribute.newInstance().withAttribute(attribute)).close();
			} else if(po instanceof MAttributeValue) {
				MAttributeValue attributeValue = (MAttributeValue) po;
				MAttribute attribute = (MAttribute) attributeValue.getM_Attribute();
				ElasticSearchHelper.getInstance().connect().delete(Attribute.newInstance().withAttribute(attribute)).close();
			} else if(po instanceof MAttachment) {
				MAttachment attachment = (MAttachment) po;
				if(attachment.getAD_Table_ID() == MProduct.Table_ID) {
					MProduct product = MProduct.get(attachment.getCtx(), attachment.getRecord_ID());
					ElasticSearchHelper.getInstance().connect().delete(Product.newInstance().withProduct(product)).close();
				}
			} else if(po.get_TableName().equals(I_W_Basket.Table_Name)) {
				X_W_Basket basket = (X_W_Basket) po;
				VueStoreFrontUtil.deletePackageFromBasket(basket.getCtx(), basket.getW_Basket_ID(), basket.get_TrxName());
			} else if(po.get_TableName().equals(I_W_BasketLine.Table_Name)) {
				X_W_BasketLine basketLine = (X_W_BasketLine) po;
				VueStoreFrontUtil.deletePackageLineFromBasketLine(basketLine.getCtx(), basketLine.getW_BasketLine_ID(), basketLine.get_TrxName());
			}
		}
		return null;
	}

	@Override
	public String docValidate(PO po, int timing) {
		return null;
	}
}
