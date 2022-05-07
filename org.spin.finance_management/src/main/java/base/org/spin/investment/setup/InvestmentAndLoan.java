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
package org.spin.investment.setup;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;
import java.util.Properties;

import org.compiere.model.I_C_TaxCategory;
import org.compiere.model.I_C_UOM;
import org.compiere.model.I_M_PriceList_Version;
import org.compiere.model.I_M_Product_Category;
import org.compiere.model.MCharge;
import org.compiere.model.MCurrency;
import org.compiere.model.MDocType;
import org.compiere.model.MPriceListVersion;
import org.compiere.model.MProduct;
import org.compiere.model.MProductPrice;
import org.compiere.model.MRefList;
import org.compiere.model.MTable;
import org.compiere.model.Query;
import org.compiere.model.X_AD_ModelValidator;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;
import org.compiere.util.Util;
import org.spin.investment.model.X_FM_Agreement;
import org.spin.investment.model.X_FM_AgreementType;
import org.spin.investment.model.X_FM_FunctionalApplicability;
import org.spin.investment.model.X_FM_FunctionalSetting;
import org.spin.investment.model.X_FM_Product;
import org.spin.investment.model.X_FM_ProductCategory;
import org.spin.investment.model.X_FM_TransactionType;
import org.spin.util.ISetupDefinition;

/**
 * Testing class for setup
 * @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 */
public class InvestmentAndLoan implements ISetupDefinition {

	private static final String SETUP_DESCRIPTION = "(*Created from Setup Automatically*)";
	private final String FRENCH_LOAN_AMORTIZATION = "org.spin.util.FrenchLoanAmortization";
	private final String LOAN_MODEL_VALIDATOR = "org.spin.model.LoanManagementModelValidator";
	
	@Override
	public String doIt(Properties context, String transactionName) {
		//	Do it here your setup
		createTransactionTypes(context, transactionName);
		//	Agreement Type
		createAgreementType(context, transactionName);
		//	Create Financial Product Category
		X_FM_ProductCategory financialProductCategory = createFinancialProductCategory(context, "ILC", "Interest Loan Category", transactionName);
		createFinancialProduct(context, financialProductCategory.getFM_ProductCategory_ID(), "AP", "Amortization Product", transactionName);
		X_FM_FunctionalSetting functionalSetting = createFunctionalSetting(context, "FLA", "French Loan Amortization", FRENCH_LOAN_AMORTIZATION, transactionName);
		createApplicability(context, functionalSetting.getFM_FunctionalSetting_ID(), financialProductCategory.getFM_ProductCategory_ID(),  X_FM_Agreement.Table_Name, X_FM_FunctionalApplicability.EVENTTYPE_TableAction, X_FM_FunctionalApplicability.EVENTMODELVALIDATOR_DocumentAfterPrepare, transactionName);
		createDocumentType(context, MDocType.DOCBASETYPE_FinancialAgreement, "Loan", transactionName);
		//	Add Model Validator
		createModelValidator(context, transactionName);
		//	financial management
		return "@AD_SetupDefinition_ID@ @Ok@";
	}
	
	/**
	 * Create Model Vaidator
	 * @param context
	 * @param transactionName
	 * @return
	 */
	private X_AD_ModelValidator createModelValidator(Properties context, String transactionName) {
		X_AD_ModelValidator modelValidator = new Query(context, X_AD_ModelValidator.Table_Name, X_AD_ModelValidator.COLUMNNAME_ModelValidationClass + " = ?", transactionName)
				.setParameters(LOAN_MODEL_VALIDATOR)
				.setClient_ID()
				.<X_AD_ModelValidator>first();
		//	Validate
		if(modelValidator != null
				&& modelValidator.getAD_ModelValidator_ID() > 0) {
			return modelValidator;
		}
		//	
		modelValidator = new X_AD_ModelValidator(context, 0, transactionName);
		modelValidator.setName("Loan Management Validator");
		modelValidator.setEntityType("FMS");
		modelValidator.setDescription(SETUP_DESCRIPTION);
		modelValidator.setSeqNo(200);
		modelValidator.setModelValidationClass(LOAN_MODEL_VALIDATOR);
		modelValidator.saveEx();
		return modelValidator;
	}
	
	/**
	 * Create Document Type
	 * @param documentBaseType
	 * @param name
	 * @param transactionName
	 * @return
	 */
	private MDocType createDocumentType(Properties context, String documentBaseType, String name, String transactionName) {
		MDocType documentType = new Query(context, MDocType.Table_Name, MDocType.COLUMNNAME_Name + " = ?", transactionName)
				.setParameters(name)
				.setClient_ID()
				.<MDocType>first();
		//	Validate
		if(documentType != null
				&& documentType.getC_DocType_ID() > 0) {
			return documentType;
		}
		documentType = new MDocType(Env.getCtx(), 0, transactionName);
		documentType.setName(name);
		documentType.setPrintName(name);
		documentType.setDocBaseType(documentBaseType);
		documentType.setGL_Category_ID();
		documentType.setIsSOTrx(true);
		documentType.setDescription(SETUP_DESCRIPTION);
		documentType.saveEx();
		return documentType;
	}
	
	/**
	 * Create Financial Product
	 * @param transactionName
	 * @return
	 */
	private X_FM_Product createFinancialProduct(Properties context, int financialProductCategoryId, String value, String name, String transactionName) {
		X_FM_Product financialProduct = new Query(context, X_FM_Product.Table_Name, X_FM_Product.COLUMNNAME_Value + " = ?", transactionName)
				.setParameters(value)
				.setClient_ID()
				.<X_FM_Product>first();
		//	Validate
		if(financialProduct != null
				&& financialProduct.getFM_Product_ID() > 0) {
			return financialProduct;
		}
		financialProduct = new X_FM_Product(Env.getCtx(), 0, transactionName);
		financialProduct.setValue(value);
		financialProduct.setName(name);
		financialProduct.setFM_ProductCategory_ID(financialProductCategoryId);
		financialProduct.setC_Currency_ID(MCurrency.get(Env.getCtx(), "USD").getC_Currency_ID());
		MProduct product = createProduct(context, value, name, transactionName);
		financialProduct.setM_Product_ID(product.getM_Product_ID());
		MCharge charge = createCharge(context, name, transactionName);
		financialProduct.setC_Charge_ID(charge.getC_Charge_ID());
		financialProduct.setPaymentFrequency(X_FM_Product.PAYMENTFREQUENCY_SingleFee);
		financialProduct.setGraceDays(0);
		financialProduct.saveEx();
		return financialProduct;
	}
	
	/**
	 * Create product for financial product
	 * @param value
	 * @param name
	 * @param transactionName
	 * @return
	 */
	private MProduct createProduct(Properties context, String value, String name, String transactionName) {
		MProduct product = new Query(context, MProduct.Table_Name, MProduct.COLUMNNAME_Value + " = ?", transactionName)
				.setParameters(value)
				.setClient_ID()
				.<MProduct>first();
		//	Validate
		if(product != null
				&& product.getM_Product_ID() > 0) {
			return product;
		}
		product = new MProduct(Env.getCtx(), 0, transactionName);
		product.setValue(value);
		product.setName(name);
		product.setDescription(SETUP_DESCRIPTION);
		product.setM_Product_Category_ID(new Query(Env.getCtx(), I_M_Product_Category.Table_Name, I_M_Product_Category.COLUMNNAME_Value + " = ?", transactionName).setParameters("Standard").setClient_ID().firstId());
		product.setC_TaxCategory_ID(new Query(Env.getCtx(), I_C_TaxCategory.Table_Name, I_C_TaxCategory.COLUMNNAME_Name + " = ?", transactionName).setParameters("Standard").setClient_ID().firstId());
		product.setC_UOM_ID(new Query(Env.getCtx(), I_C_UOM.Table_Name, I_C_UOM.COLUMNNAME_X12DE355 + " = ?", transactionName).setParameters("Kg").setClient_ID().firstId());
		product.setProductType(MProduct.PRODUCTTYPE_Service);
		product.saveEx();
		//	Add to price List
		createPriceList(product.getM_Product_ID(), null, transactionName);
		return product;
	}
	
	/**
	 * Create Price List
	 * @param productId
	 * @param optionalPrice
	 * @param transactionName
	 */
	private void createPriceList(int productId, BigDecimal optionalPrice, String transactionName) {
		new Query(Env.getCtx(), I_M_PriceList_Version.Table_Name, null, transactionName)
		.setOnlyActiveRecords(true)
		.setClient_ID()
		.<MPriceListVersion>list()
		.forEach(priceListVersion -> {
			MProductPrice productPrice = new MProductPrice(Env.getCtx(), priceListVersion.getM_PriceList_Version_ID(), productId, transactionName);
			productPrice.setPriceList(Optional.ofNullable(optionalPrice).orElse(Env.ONEHUNDRED));
			productPrice.setPriceStd(Optional.ofNullable(optionalPrice).orElse(Env.ONEHUNDRED));
			productPrice.setPriceLimit(Optional.ofNullable(optionalPrice).orElse(Env.ONEHUNDRED));
			productPrice.saveEx();
		});
	}
	
	/**
	 * Create charge
	 * @param name
	 * @param transactionName
	 * @return
	 */
	private MCharge createCharge(Properties context, String name, String transactionName) {
		MCharge charge = new Query(context, MCharge.Table_Name, MCharge.COLUMNNAME_Name + " = ?", transactionName)
				.setParameters(name)
				.setClient_ID()
				.<MCharge>first();
		//	Validate
		if(charge != null
				&& charge.getC_Charge_ID() > 0) {
			return charge;
		}
		charge = new MCharge(Env.getCtx(), 0, transactionName);
		charge.setName(name);
		charge.setDescription(SETUP_DESCRIPTION);
		charge.setC_TaxCategory_ID(new Query(Env.getCtx(), I_C_TaxCategory.Table_Name, I_C_TaxCategory.COLUMNNAME_Name + " = ?", transactionName).setParameters("Standard").setClient_ID().firstId());
		charge.saveEx();
		charge.saveEx();
		return charge;
	}
	
	/**
	 * Create Financial Product Category
	 * @param context
	 * @param transactionName
	 * @return
	 */
	private X_FM_ProductCategory createFinancialProductCategory(Properties context, String value, String name, String transactionName) {
		X_FM_ProductCategory financialProductCategory = new Query(context, X_FM_ProductCategory.Table_Name, X_FM_ProductCategory.COLUMNNAME_Value + " = ?", transactionName)
				.setParameters(value)
				.setClient_ID()
				.<X_FM_ProductCategory>first();
		//	Validate
		if(financialProductCategory != null
				&& financialProductCategory.getFM_ProductCategory_ID() > 0) {
			return financialProductCategory;
		}
		financialProductCategory = new X_FM_ProductCategory(Env.getCtx(), 0, transactionName);
		financialProductCategory.setValue(value);
		financialProductCategory.setName(name);
		financialProductCategory.setDescription(SETUP_DESCRIPTION);
		financialProductCategory.setType(X_FM_ProductCategory.TYPE_Loan);
		financialProductCategory.saveEx();
		return financialProductCategory;
	}
	
	/**
	 * Create a functional setting based
	 * @param value
	 * @param name
	 * @param className
	 * @param transactionName
	 * @return
	 */
	private X_FM_FunctionalSetting createFunctionalSetting(Properties context, String value, String name, String className, String transactionName) {
		X_FM_FunctionalSetting functionalSetting = new Query(context, X_FM_FunctionalSetting.Table_Name, X_FM_FunctionalSetting.COLUMNNAME_Value + " = ?", transactionName)
				.setParameters(value)
				.setClient_ID()
				.<X_FM_FunctionalSetting>first();
		//	Validate
		if(functionalSetting != null
				&& functionalSetting.getFM_FunctionalSetting_ID() > 0) {
			return functionalSetting;
		}
		functionalSetting = new X_FM_FunctionalSetting(context, 0, transactionName);
		functionalSetting.setValue(value);
		functionalSetting.setName(name);
		functionalSetting.setClassname(className);
		functionalSetting.setDescription(SETUP_DESCRIPTION);
		functionalSetting.saveEx();
		return functionalSetting;
	}
	
	/**
	 * Create Applicability
	 * @param functionaSettingId
	 * @param financialProductCategoryId
	 * @param tableId
	 * @param eventType
	 * @param eventModelValidator
	 * @param transactionName
	 * @return
	 */
	private X_FM_FunctionalApplicability createApplicability(Properties context, int functionaSettingId, int financialProductCategoryId, String tableName, String eventType, String eventModelValidator, String transactionName) {
		int tableId = MTable.getTable_ID(tableName);
		X_FM_FunctionalApplicability functionalSettingApplicability = new Query(context, X_FM_FunctionalApplicability.Table_Name, 
				X_FM_FunctionalApplicability.COLUMNNAME_AD_Table_ID + " = ? "
						+ "AND " + X_FM_FunctionalApplicability.COLUMNNAME_EventType + " = ? "
						+ "AND " + X_FM_FunctionalApplicability.COLUMNNAME_EventModelValidator + " = ?", transactionName)
				.setParameters(tableId, eventType, eventModelValidator)
				.setClient_ID()
				.<X_FM_FunctionalApplicability>first();
		//	Validate
		if(functionalSettingApplicability != null
				&& functionalSettingApplicability.getFM_FunctionalApplicability_ID() > 0) {
			return functionalSettingApplicability;
		}
		functionalSettingApplicability = new X_FM_FunctionalApplicability(Env.getCtx(), 0, transactionName);
		functionalSettingApplicability.setFM_FunctionalSetting_ID(functionaSettingId);
		functionalSettingApplicability.setFM_ProductCategory_ID(financialProductCategoryId);
		if(!Util.isEmpty(tableName)) {
			functionalSettingApplicability.setAD_Table_ID(MTable.getTable_ID(tableName));
		}
		if(!Util.isEmpty(eventType)) {
			functionalSettingApplicability.setEventType(eventType);
		}
		if(!Util.isEmpty(eventModelValidator)) {
			functionalSettingApplicability.setEventModelValidator(eventModelValidator);
		}
		functionalSettingApplicability.setSeqNo(10);
		functionalSettingApplicability.setValidFrom(TimeUtil.getDay(2020, 5, 1));
		functionalSettingApplicability.setDescription(SETUP_DESCRIPTION);
		functionalSettingApplicability.saveEx();
		return functionalSettingApplicability;
	}
	
	/**
	 * Create Agreement Type
	 * @param context
	 * @param transactionName
	 * @return
	 */
	private X_FM_AgreementType createAgreementType(Properties context, String transactionName) {
		String value = "EAT";
		X_FM_AgreementType agreementType = new Query(context, X_FM_AgreementType.Table_Name, X_FM_AgreementType.COLUMNNAME_Value + " = ?", transactionName)
				.setParameters(value)
				.setClient_ID()
				.<X_FM_AgreementType>first();
		//	Validate
		if(agreementType != null
				&& agreementType.getFM_AgreementType_ID() > 0) {
			return agreementType;
		}
		agreementType = new X_FM_AgreementType(Env.getCtx(), 0, transactionName);
		agreementType.setValue(value);
		agreementType.setName("Testing Contract");
		agreementType.setDescription(SETUP_DESCRIPTION);
		agreementType.setText("This is a testing contract usinng for financial investment and loan, you can use this or create a new agreement type");
		agreementType.saveEx();
		return agreementType;
	}
	
	/**
	 * Create Transaction Types
	 * @param context
	 * @param transactionName
	 */
	private void createTransactionTypes(Properties context, String transactionName) {
		Arrays.asList(MRefList.getList(context, X_FM_TransactionType.TYPE_AD_Reference_ID, false))
		.forEach(valuesPair -> {
			createTransactionType(context, valuesPair.getValue(), valuesPair.getValue(), valuesPair.getName(), transactionName);
		});
	}
	
	/**
	 * Create transaction Type
	 * @param context
	 * @param type
	 * @param value
	 * @param name
	 * @param transactionName
	 * @return
	 */
	private X_FM_TransactionType createTransactionType(Properties context, String type, String value, String name, String transactionName) {
		X_FM_TransactionType transactionType = new Query(context, X_FM_TransactionType.Table_Name, X_FM_TransactionType.COLUMNNAME_Type + " = ?", transactionName)
				.setParameters(type)
				.setClient_ID()
				.<X_FM_TransactionType>first();
		//	Validate
		if(transactionType != null
				&& transactionType.getFM_TransactionType_ID() > 0) {
			return transactionType;
		}
		transactionType = new X_FM_TransactionType(Env.getCtx(), 0, transactionName);
		transactionType.setValue(value);
		transactionType.setName(name);
		transactionType.setType(type);
		transactionType.saveEx();
		return transactionType;
	}
}
