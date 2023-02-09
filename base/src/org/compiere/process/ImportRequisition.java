/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.										*
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/

package org.compiere.process;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.adempiere.model.ImportValidator;
import org.adempiere.process.ImportProcess;
import org.adempiere.core.domains.models.I_AD_User;
import org.adempiere.core.domains.models.I_C_BPartner;
import org.adempiere.core.domains.models.I_C_Charge;
import org.adempiere.core.domains.models.I_C_DocType;
import org.adempiere.core.domains.models.I_C_ElementValue;
import org.adempiere.core.domains.models.I_C_ProjectPhase;
import org.adempiere.core.domains.models.I_C_TaxCategory;
import org.adempiere.core.domains.models.I_C_UOM;
import org.adempiere.core.domains.models.I_I_Requisition;
import org.adempiere.core.domains.models.I_M_AttributeInstance;
import org.adempiere.core.domains.models.I_M_PriceList;
import org.adempiere.core.domains.models.I_M_Product;
import org.adempiere.core.domains.models.I_M_Product_Category;
import org.adempiere.core.domains.models.I_M_Requisition;
import org.adempiere.core.domains.models.I_M_RequisitionLine;
import org.adempiere.core.domains.models.I_M_Warehouse;
import org.compiere.model.MActivity;
import org.compiere.model.MAttributeSet;
import org.compiere.model.MAttributeSetInstance;
import org.compiere.model.MCampaign;
import org.compiere.model.MDocType;
import org.compiere.model.MOrder;
import org.compiere.model.MOrg;
import org.compiere.model.MProduct;
import org.compiere.model.MProject;
import org.compiere.model.MRequisition;
import org.compiere.model.MRequisitionLine;
import org.compiere.model.MTable;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.Query;
import org.adempiere.core.domains.models.X_I_Requisition;
import org.adempiere.core.domains.models.X_M_Product_Class;
import org.adempiere.core.domains.models.X_M_Product_Classification;
import org.adempiere.core.domains.models.X_M_Product_Group;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.TimeUtil;
import org.compiere.util.Util;

/** Generated Process for (Import Requisitions)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.3
 */
public class ImportRequisition extends ImportRequisitionAbstract implements ImportProcess {
	/** Effective						*/
	private Timestamp dateValue = null;
	/**	Default error	*/
	private StringBuffer stringError = new StringBuffer("");
	/**	Current Requisition	*/
	private int currentRequisitionId;

	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare() {
		super.prepare();
		if (dateValue == null) {
			dateValue = new Timestamp (System.currentTimeMillis());
		}
		//	Validate Document Action
		if(getDocAction() == null) {
			setDocAction(MOrder.DOCACTION_Prepare);
		}
	}	//	prepare

	@Override
	protected String doIt() throws Exception {
		StringBuffer sql = null;
		int no = 0;
		String clientCheck = " AND AD_Client_ID=" + getAD_Client_ID();
		//	for user
		if(getUserId() !=0) {
			clientCheck += " AND CreatedBy = " + getUserId();
		}
		//	****	Prepare	****

		//	Delete Old Imported
		if (isDeleteOldImported()) {
			sql = new StringBuffer ("DELETE I_Requisition "
				  + "WHERE I_IsImported='Y'").append (clientCheck);
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			log.fine("Delete Old Impored =" + no);
		}

		//	Set Client, Org, IsActive, Created/Updated
		sql = new StringBuffer ("UPDATE I_Requisition "
			  + "SET AD_Client_ID = COALESCE (AD_Client_ID,").append (getAD_Client_ID()).append ("),"
			  + " AD_Org_ID = COALESCE (AD_Org_ID,").append (getOrgId()).append ("),"
			  + " IsActive = COALESCE (IsActive, 'Y'),"
			  + " Created = COALESCE (Created, SysDate),"
			  + " CreatedBy = COALESCE (CreatedBy, 0),"
			  + " Updated = COALESCE (Updated, SysDate),"
			  + " UpdatedBy = COALESCE (UpdatedBy, 0),"
			  + " I_ErrorMsg = ' ',"
			  + " I_IsImported = 'N' "
			  + "WHERE I_IsImported<>'Y' OR I_IsImported IS NULL");
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.info ("Reset=" + no);

		sql = new StringBuffer ("UPDATE I_Requisition o "
			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Org, '"
			+ "WHERE (AD_Org_ID IS NULL OR AD_Org_ID=0"
			+ " OR EXISTS (SELECT * FROM AD_Org oo WHERE o.AD_Org_ID=oo.AD_Org_ID AND (oo.IsSummary='Y' OR oo.IsActive='N')))"
			+ " AND I_IsImported<>'Y'").append (clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0)
			log.warning ("Invalid Org=" + no);
		
		AtomicInteger importedRecord = new AtomicInteger(0);
        AtomicInteger withErrors = new AtomicInteger(0);
        getImportIds(false, false, get_TrxName()).forEach(importRequisitionId -> {
        	X_I_Requisition importRequisition = new X_I_Requisition(getCtx(), importRequisitionId, get_TrxName());
            importRequisition.setI_ErrorMsg(null);
            fillIdValues(importRequisition, get_TrxName());
            if (importRequisition(importRequisition, get_TrxName())) {
            	importedRecord.updateAndGet(record -> record + 1);
            } else {
            	withErrors.updateAndGet(error -> error + 1);
            }
            if (!stringError.toString().isEmpty() && stringError.toString().length() > 0) {
            	importRequisition.setI_ErrorMsg(Msg.parseTranslation(getCtx(), stringError.toString()));
            }
            //	
            importRequisition.saveEx();
        });
        //	Import 
        return "@M_Requisition_ID@ @Import@ @Records@ " + importedRecord.get() + " @Errors@ " + withErrors.get();
	}
	
    /**
     * get employee ids
     * @param isImported
     * @param isProcessed
     * @return
     */
    private List<Integer> getImportIds(boolean isImported, boolean isProcessed, String transactionName) {
        StringBuilder whereClause = new StringBuilder();
        whereClause.append(I_I_Requisition.COLUMNNAME_I_IsImported).append("=? AND ")
                .append(I_I_Requisition.COLUMNNAME_Processed).append("=?");
        //	Get
        return new Query(getCtx(), I_I_Requisition.Table_Name, whereClause.toString(), transactionName)
                .setOnlyActiveRecords(true)
                .setParameters(isImported, isProcessed)
                .setOrderBy(I_I_Requisition.COLUMNNAME_M_Warehouse_ID + ", " + I_I_Requisition.COLUMNNAME_DateDoc + ", " + I_I_Requisition.COLUMNNAME_DocumentNo + ", " + I_I_Requisition.COLUMNNAME_Line)
                .getIDsAsList();

    }
    
    /**
     * Import all requisition
     * @param importRequisition
     * @param transactionName
     * @return
     * @return boolean
     */
    private boolean importRequisition(X_I_Requisition importRequisition, String transactionName) {
        //	Document Type
        int referenceId = importRequisition.getM_Requisition_ID();
        MRequisition requisition = null;
        if(referenceId <= 0) {
        	if(isConsolidateRequisition()) {
        		if(currentRequisitionId <= 0) {
        			referenceId = getIdOnlyClient(I_M_Requisition.Table_Name, 
                			I_M_Requisition.COLUMNNAME_C_DocType_ID + " = ? AND " + I_M_Requisition.COLUMNNAME_DateDoc + " = ? AND " + I_M_Requisition.COLUMNNAME_M_Warehouse_ID + " = ? AND " + I_M_Requisition.COLUMNNAME_DocStatus + " = 'DR'", transactionName, 
                			importRequisition.getC_DocType_ID(), importRequisition.getDateDoc(), importRequisition.getM_Warehouse_ID());
        			currentRequisitionId = referenceId;
        		} else {
        			referenceId = currentRequisitionId;
        		}
        	} else {
        		referenceId = getIdOnlyClient(I_M_Requisition.Table_Name, 
            			I_M_Requisition.COLUMNNAME_DocumentNo + " = ? AND " + I_M_Requisition.COLUMNNAME_C_DocType_ID + " = ? AND " + I_M_Requisition.COLUMNNAME_DateDoc + " = ? AND " + I_M_Requisition.COLUMNNAME_M_Warehouse_ID + " = ? AND " + I_M_Requisition.COLUMNNAME_DocStatus + " = 'DR'", transactionName, 
            			importRequisition.getDocumentNo(), importRequisition.getC_DocType_ID(), importRequisition.getDateDoc(), importRequisition.getM_Warehouse_ID());
        	}
        	if(referenceId > 0) {
        		importRequisition.setM_Requisition_ID(referenceId);
        	}
        }
        requisition = createRequisition(importRequisition, transactionName);
    	ModelValidationEngine.get().fireImportValidate(this, importRequisition, requisition, ImportValidator.TIMING_AFTER_IMPORT);
    	return requisition != null;
    }
    
    /**
     * Create Requisition
     * @param importRequisition
     * @param transactionName
     * @return
     * @return MRequisition
     */
    private MRequisition createRequisition(X_I_Requisition importRequisition, String transactionName) {
    	MRequisition requisition = new MRequisition(getCtx(), importRequisition.getM_Requisition_ID(), transactionName);
    	//	Create
    	boolean isValid = true;
    	if(requisition.getM_Requisition_ID() <= 0) {
    		requisition.setAD_Org_ID(importRequisition.getAD_Org_ID());
    		if(importRequisition.getDateRequired() != null) {
    			requisition.setDateRequired(importRequisition.getDateRequired());
    		} else {
    			isValid = false;
    			addError(I_M_Requisition.COLUMNNAME_DateRequired);
    		}
    		if(importRequisition.getC_DocType_ID() > 0) {
    			requisition.setC_DocType_ID(importRequisition.getC_DocType_ID());
    		} else {
    			isValid = false;
    			addError(I_M_Requisition.COLUMNNAME_C_DocType_ID);
    		}
    		if(importRequisition.getDateDoc() != null) {
    			requisition.setDateDoc(importRequisition.getDateDoc());
    		} else {
    			isValid = false;
    			addError(I_M_Requisition.COLUMNNAME_DateDoc);
    		}
    		if(!Util.isEmpty(importRequisition.getDocumentNo())) {
    			requisition.setDocumentNo(importRequisition.getDocumentNo());
    		}
    		if(importRequisition.getAD_OrgTrx_ID() > 0) {
    			requisition.setAD_OrgTrx_ID(importRequisition.getAD_OrgTrx_ID());
    		}
    		if(importRequisition.getM_Warehouse_ID() > 0) {
    			requisition.setM_Warehouse_ID(importRequisition.getM_Warehouse_ID());
    		} else {
    			isValid = false;
    			addError(I_M_Requisition.COLUMNNAME_M_Warehouse_ID);
    		}
    		if(importRequisition.getM_PriceList_ID() > 0) {
    			requisition.setM_PriceList_ID(importRequisition.getM_PriceList_ID());
    		} else {
    			isValid = false;
    			addError(I_M_Requisition.COLUMNNAME_M_PriceList_ID);
    		}
    		if(!Util.isEmpty(importRequisition.getPriorityRule())) {
    			requisition.setPriorityRule(importRequisition.getPriorityRule());
    		}
    		if(importRequisition.getAD_User_ID() > 0) {
    			requisition.setAD_User_ID(importRequisition.getAD_User_ID());
    		}
    		if(importRequisition.getC_Project_ID() > 0) {
    			requisition.setC_Project_ID(importRequisition.getC_Project_ID());
    		}
    		if(importRequisition.getC_Campaign_ID() > 0) {
    			requisition.setC_Campaign_ID(importRequisition.getC_Campaign_ID());
    		}
    		if(!Util.isEmpty(importRequisition.getDescription())) {
    			requisition.setDescription(importRequisition.getDescription());
    		}
    		if(!Util.isEmpty(importRequisition.getHelp())) {
    			requisition.setHelp(importRequisition.getHelp());
    		}
    		if(importRequisition.getUser1_ID() > 0) {
    			requisition.setUser1_ID(importRequisition.getUser1_ID());
    		}
    		if(importRequisition.getUser2_ID() > 0) {
    			requisition.setUser2_ID(importRequisition.getUser2_ID());
    		}
    		if(importRequisition.getUser3_ID() > 0) {
    			requisition.setUser3_ID(importRequisition.getUser3_ID());
    		}
    		if(importRequisition.getUser4_ID() > 0) {
    			requisition.setUser4_ID(importRequisition.getUser4_ID());
    		}
    		requisition.setDocStatus(MRequisition.DOCSTATUS_Drafted);
    		requisition.setDocAction(getDocAction());
    		if(isValid) {
    			requisition.saveEx();
    			createRequisitionLine(requisition, importRequisition, transactionName);
    			importRequisition.setM_Requisition_ID(requisition.getM_Requisition_ID());
    			importRequisition.saveEx();
    			return requisition;
    		} else {
    			return null;
    		}
    	}
    	createRequisitionLine(requisition, importRequisition, transactionName);
    	//	Create Line
    	return requisition;
    }
    
    /**
     * Create Requisition line
     * @param importRequisition
     * @param transactionName
     * @return
     * @return MRequisition
     */
    private MRequisitionLine createRequisitionLine(MRequisition requisition, X_I_Requisition importRequisition, String transactionName) {
    	MRequisitionLine requisitionLine = new MRequisitionLine(getCtx(), importRequisition.getM_RequisitionLine_ID(), transactionName);
    	//	Create
    	boolean isValid = true;
    	if(requisitionLine.getM_RequisitionLine_ID() <= 0) {
    		requisitionLine = new MRequisitionLine(requisition);
    		requisitionLine.setAD_Org_ID(importRequisition.getAD_Org_ID());
    		if(importRequisition.getAD_OrgTrx_ID() > 0) {
    			requisitionLine.setAD_OrgTrx_ID(importRequisition.getAD_OrgTrx_ID());
    		}
    		if(importRequisition.getC_BPartner_ID() > 0) {
    			requisitionLine.setC_BPartner_ID(importRequisition.getC_BPartner_ID());
    		}
    		if(importRequisition.getC_ProjectTask_ID() > 0) {
    			requisitionLine.setC_ProjectTask_ID(importRequisition.getC_ProjectTask_ID());
    		}
    		if(importRequisition.getC_Project_ID() > 0) {
    			requisitionLine.setC_Project_ID(importRequisition.getC_Project_ID());
    		}
    		if(importRequisition.getC_ProjectPhase_ID() > 0) {
    			requisitionLine.setC_ProjectPhase_ID(importRequisition.getC_ProjectPhase_ID());
    		}
    		if(importRequisition.getM_Product_ID() > 0) {
    			requisitionLine.setM_Product_ID(importRequisition.getM_Product_ID());
    		} else {
    			isValid = false;
    			addError(I_M_RequisitionLine.COLUMNNAME_M_Product_ID);
    		}
    		if(importRequisition.getC_UOM_ID() > 0) {
    			requisitionLine.setC_UOM_ID(importRequisition.getC_UOM_ID());
    		} else {
    			isValid = false;
    			addError(I_M_RequisitionLine.COLUMNNAME_C_UOM_ID);
    		}
    		//	Quantities
    		if(Optional.ofNullable(importRequisition.getQty()).orElse(Env.ZERO).compareTo(Env.ZERO) > 0) {
    			requisitionLine.setQty(importRequisition.getQty());
    		} else {
    			isValid = false;
    			addError(I_M_RequisitionLine.COLUMNNAME_Qty);
    		}
    		requisitionLine.setPriceActual(Optional.ofNullable(importRequisition.getPriceActual()).orElse(Env.ZERO));
    		if(importRequisition.getC_Campaign_ID() > 0) {
    			requisitionLine.setC_Campaign_ID(importRequisition.getC_Campaign_ID());
    		}
    		if(importRequisition.getLine() > 0) {
    			requisitionLine.setLine(importRequisition.getLine());
    		}
    		if(!Util.isEmpty(importRequisition.getLineDescription())) {
    			requisitionLine.setDescription(importRequisition.getLineDescription());
    		}
    		if(importRequisition.getUser1_ID() > 0) {
    			requisitionLine.setUser1_ID(importRequisition.getUser1_ID());
    		}
    		if(importRequisition.getUser2_ID() > 0) {
    			requisitionLine.setUser2_ID(importRequisition.getUser2_ID());
    		}
    		if(importRequisition.getUser3_ID() > 0) {
    			requisitionLine.setUser3_ID(importRequisition.getUser3_ID());
    		}
    		if(importRequisition.getUser4_ID() > 0) {
    			requisitionLine.setUser4_ID(importRequisition.getUser4_ID());
    		}
    		if(isValid) {
    			requisitionLine.saveEx();
    			importRequisition.setM_RequisitionLine_ID(requisitionLine.getM_RequisitionLine_ID());
    			importRequisition.saveEx();
    			return requisitionLine;
    		} else {
    			return null;
    		}
    	}
    	importRequisition.setM_RequisitionLine_ID(requisitionLine.getM_RequisitionLine_ID());
		importRequisition.saveEx();
    	//	Create Line
    	return requisitionLine;
    }
	
    /**
     * Import Product before requisition
     * @param importRequisition
     * @param transactionName
     * @return
     * @return boolean
     */
    private boolean importProduct(X_I_Requisition importRequisition, String transactionName) {
    	boolean ok = false;
    	try {
    		int referenceId = 0;
    		// Class
    		if (importRequisition.getM_Product_Class_ID() == 0 
    				&& !Util.isEmpty(importRequisition.getProductClass_Value())
    				&& !Util.isEmpty(importRequisition.getProductClass_Name())) {
    			String value = importRequisition.getProductClass_Value();
    			String whereClause = "VALUE= '" + value + "'";
    			// Have we already added it?
    			X_M_Product_Class productClass = MTable.get(getCtx(), X_M_Product_Class.Table_ID)
    				.createQuery(whereClause, get_TrxName())  // will lock the table
    				.setOnlyActiveRecords(true)
    				.setClient_ID()
    				.first();
    			if (productClass == null) {
    				productClass = new X_M_Product_Class(getCtx(), 0, get_TrxName());
    				productClass.setValue(importRequisition.getProductClass_Value());
    				productClass.setName(importRequisition.getProductClass_Name());
    				productClass.setIsActive(true);
    				productClass.setIsDefault(false);
    				productClass.saveEx();
    			}
    			importRequisition.setM_Product_Class_ID(productClass.getM_Product_Class_ID());
    			ModelValidationEngine.get().fireImportValidate(this, importRequisition, productClass, ImportValidator.TIMING_AFTER_IMPORT);
    		}
    		// Classification
    		if (importRequisition.getM_Product_Classification_ID() == 0 
    				&& !Util.isEmpty(importRequisition.getProductClassification_Value())
    				&& !Util.isEmpty(importRequisition.getProductClassification_Name())) {
    			String value = importRequisition.getProductClassification_Value();
    			String whereClause = "VALUE= '" + value + "'";
    			// Have we already added it?
    			X_M_Product_Classification productClassification = MTable.get(getCtx(), X_M_Product_Classification.Table_ID)
    				.createQuery(whereClause, get_TrxName())  // will lock the table
    				.setOnlyActiveRecords(true)
    				.setClient_ID()
    				.first();
    			if (productClassification == null) {
    				productClassification = new X_M_Product_Classification(getCtx(), 0, get_TrxName());
    				productClassification.setValue(importRequisition.getProductClassification_Value());
    				productClassification.setName(importRequisition.getProductClassification_Name());
    				productClassification.setIsActive(true);
    				productClassification.setIsDefault(false);
    				productClassification.saveEx();
    			}
    			importRequisition.setM_Product_Classification_ID(productClassification.getM_Product_Classification_ID());
    			ModelValidationEngine.get().fireImportValidate(this, importRequisition, productClassification, ImportValidator.TIMING_AFTER_IMPORT);
    		}
    		// Group
    		if (importRequisition.getM_Product_Group_ID() == 0 
    				&& !Util.isEmpty(importRequisition.getProductGroup_Value())
    				&& !Util.isEmpty(importRequisition.getProductGroup_Name())) {
    			String value = importRequisition.getProductGroup_Value();
    			String whereClause = "VALUE= '" + value + "'";
    			// Have we already added it?
    			X_M_Product_Group productGroup = MTable.get(getCtx(), X_M_Product_Group.Table_ID)
    				.createQuery(whereClause, get_TrxName())  // will lock the table
    				.setOnlyActiveRecords(true)
    				.setClient_ID()
    				.first();
    			if (productGroup == null) {
    				productGroup = new X_M_Product_Group(getCtx(), 0, get_TrxName());
    				productGroup.setValue(importRequisition.getProductGroup_Value());
    				productGroup.setName(importRequisition.getProductGroup_Name());
    				productGroup.setIsActive(true);
    				productGroup.setIsDefault(false);
    				productGroup.saveEx();
    			}
    			importRequisition.setM_Product_Group_ID(productGroup.getM_Product_Group_ID());
    			ModelValidationEngine.get().fireImportValidate(this, importRequisition, productGroup, ImportValidator.TIMING_AFTER_IMPORT);
    		}
            //	Create product if not exists
    		MProduct product = createProduct(importRequisition);
    		//	Attribute
    		if(product != null) {
    			referenceId = importRequisition.getM_AttributeSetInstance_ID();
	            if(referenceId <= 0
	            		&& !Util.isEmpty(importRequisition.getAttributeSetInstanceValue())) {
	            	referenceId = getIdOnlyClient(I_M_AttributeInstance.Table_Name, "(TRIM(Description)=TRIM(?) OR TRIM(Lot) LIKE TRIM(?) OR TRIM(SerNo) LIKE TRIM(?))", transactionName, importRequisition.getAttributeSetInstanceValue(), importRequisition.getLot(), importRequisition.getSerNo());
	            	if(referenceId > 0) {
	            		importRequisition.setM_AttributeSetInstance_ID(referenceId);
	            	} else if (importRequisition.getLot() != null || importRequisition.getSerNo() != null) {
	        			if (product.isInstanceAttribute()) {
	        				MAttributeSet attributeSet = product.getAttributeSet();
	        				MAttributeSetInstance attributeSetInstance = new MAttributeSetInstance(getCtx(), 0, attributeSet.getM_AttributeSet_ID(), get_TrxName());
	        				if (attributeSet.isLot() && importRequisition.getLot() != null) {
	        					attributeSetInstance.setLot(importRequisition.getLot(), importRequisition.getM_Product_ID());
	        				}
	        				if (attributeSet.isSerNo() && importRequisition.getSerNo() != null) {
	        					attributeSetInstance.setSerNo(importRequisition.getSerNo());
	        				}
	        				if (attributeSet.isGuaranteeDate()) {
	        					attributeSetInstance.setGuaranteeDate(importRequisition.getGuaranteeDate());
	        				}
	        				attributeSetInstance.setDescription();
	        				attributeSetInstance.saveEx();
	        				attributeSetInstance.getM_AttributeSetInstance_ID();
	        				if(referenceId > 0) {
	        	        		importRequisition.setM_AttributeSetInstance_ID(referenceId);
	        	        	}
	        				ModelValidationEngine.get().fireImportValidate(this, importRequisition, attributeSetInstance, ImportValidator.TIMING_AFTER_IMPORT);
	        			} else { 
	                		addError(I_M_AttributeInstance.COLUMNNAME_M_AttributeSetInstance_ID);
	                	}
	        		}
	            }
    		}
            ok = true;
    	} catch (Exception e) {
			addError(e.getLocalizedMessage());
		}
    	return ok;
    }
    
	/**
	 * 	Import Constructor
	 *	@param importRequisition import
	 */
	private MProduct createProduct(X_I_Requisition importRequisition) {
		MProduct product = new MProduct(importRequisition.getCtx(), importRequisition.getM_Product_ID(), importRequisition.get_TrxName());
		//	Validate mandatory values
		boolean isValid = true;
		if(product.getM_Product_ID() <= 0) {
			if(importRequisition.getM_Product_Category_ID() <= 0) {
				isValid = false;
				addError(I_M_Product.COLUMNNAME_M_Product_Category_ID);
			}
			if(importRequisition.getC_TaxCategory_ID() <= 0) {
				isValid = false;
				addError(I_M_Product.COLUMNNAME_C_TaxCategory_ID);
			}
			if(importRequisition.getC_UOM_ID() <= 0) {
				isValid = false;
				addError(I_M_Product.COLUMNNAME_C_UOM_ID);
			}
			if(Util.isEmpty(importRequisition.getProductValue())) {
				isValid = false;
				addError(I_M_Product.COLUMNNAME_Value);
			}
			if(Util.isEmpty(importRequisition.getProductName())) {
				isValid = false;
				addError(I_M_Product.COLUMNNAME_Name);
			}
			if(Util.isEmpty(importRequisition.getProductType())) {
				isValid = false;
				addError(I_M_Product.COLUMNNAME_ProductType);
			}
		}
		if(isValid) {
			if(importRequisition.getAD_Org_ID() > 0) {
				product.setAD_Org_ID(importRequisition.getAD_Org_ID());
			}
			if(!Util.isEmpty(importRequisition.getProductValue())) {
				product.setValue(importRequisition.getProductValue());
			}
			if(!Util.isEmpty(importRequisition.getProductName())) {
				product.setName(importRequisition.getProductName());
			}
			if(!Util.isEmpty(importRequisition.getDescription())) {
				product.setDescription(importRequisition.getDescription());
			}
			if(!Util.isEmpty(importRequisition.getDocumentNote())) {
				product.setDocumentNote(importRequisition.getDocumentNote());
			}
			if(!Util.isEmpty(importRequisition.getHelp())) {
				product.setHelp(importRequisition.getHelp());
			}
			if(!Util.isEmpty(importRequisition.getUPC())) {
				product.setUPC(importRequisition.getUPC());
			}
			if(!Util.isEmpty(importRequisition.getSKU())) {
				product.setSKU(importRequisition.getSKU());
			}
			if(importRequisition.getC_UOM_ID() > 0) {
				product.setC_UOM_ID(importRequisition.getC_UOM_ID());
			}
			if(importRequisition.getM_Product_Category_ID() > 0) {
				product.setM_Product_Category_ID(importRequisition.getM_Product_Category_ID());
			}
			if(!Util.isEmpty(importRequisition.getProductType())) {
				product.setProductType(importRequisition.getProductType());
			}
			if(!Util.isEmpty(importRequisition.getImageURL())) {
				product.setImageURL(importRequisition.getImageURL());
			}
			if(!Util.isEmpty(importRequisition.getDescriptionURL())) {
				product.setDescriptionURL(importRequisition.getDescriptionURL());
			}
			if(importRequisition.getM_Product_Class_ID() > 0) {
				product.setM_Product_Class_ID(importRequisition.getM_Product_Class_ID());
			}
			if(importRequisition.getM_Product_Classification_ID() > 0) {
				product.setM_Product_Classification_ID(importRequisition.getM_Product_Classification_ID());
			}
			if(importRequisition.getM_Product_Group_ID() > 0) {
				product.setM_Product_Group_ID(importRequisition.getM_Product_Group_ID());
			}
			if(importRequisition.getC_TaxCategory_ID() > 0) {
				product.setC_TaxCategory_ID(importRequisition.getC_TaxCategory_ID());
			}
			product.saveEx();
			importRequisition.setM_Product_ID(product.getM_Product_ID());
		} else {
			product = null;
		}
		ModelValidationEngine.get().fireImportValidate(this, importRequisition, product, ImportValidator.TIMING_AFTER_IMPORT);
		return product;
	}	//	MProduct
	
	  /**
     * Fill values and validate requisition data
     * @param importRequisition
     * @param transactionName
     */
    private void fillIdValues(X_I_Requisition importRequisition, String transactionName) {
    	ModelValidationEngine.get().fireImportValidate(this, null, null, ImportValidator.TIMING_BEFORE_VALIDATE);
        int referenceId = 0;
        if (importRequisition.getAD_OrgTrx_ID() > 0) {
        	referenceId = importRequisition.getAD_OrgTrx_ID();
        }
        if (referenceId <= 0) {
        	referenceId = getIdOnlyClient(MOrg.Table_Name, MOrg.COLUMNNAME_Value + "=?", transactionName, importRequisition.getOrgTrxValue());
        }
        if (referenceId > 0) {
        	importRequisition.setAD_OrgTrx_ID(referenceId);
        }
        //	Document Type
        referenceId = importRequisition.getC_DocType_ID();
        if(referenceId <= 0
        		&& !Util.isEmpty(importRequisition.getDocTypeName())) {
        	referenceId = getIdOnlyClient(I_C_DocType.Table_Name, I_C_DocType.COLUMNNAME_Name + " = ? AND " + I_C_DocType.COLUMNNAME_DocBaseType + " = ?", transactionName, importRequisition.getDocTypeName(), MDocType.DOCBASETYPE_PurchaseRequisition);
        	if(referenceId > 0) {
        		importRequisition.setC_DocType_ID(referenceId);
        	} else { 
        		addError(I_C_DocType.COLUMNNAME_C_DocType_ID);
        	}
        }
        //	Price List
        referenceId = importRequisition.getM_PriceList_ID();
        if(referenceId <= 0
        		&& !Util.isEmpty(importRequisition.getPriceListName())) {
        	referenceId = getIdOnlyClient(I_M_PriceList.Table_Name, I_M_PriceList.COLUMNNAME_Name + " = ? AND " + I_M_PriceList.COLUMNNAME_IsSOPriceList + " = 'N'", transactionName, importRequisition.getPriceListName());
        	if(referenceId > 0) {
        		importRequisition.setM_PriceList_ID(referenceId);
        	} else { 
        		addError(I_M_PriceList.COLUMNNAME_M_PriceList_ID);
        	}
        }
        //	Warehouse
        referenceId = importRequisition.getM_Warehouse_ID();
        if(referenceId <= 0
        		&& !Util.isEmpty(importRequisition.getWarehouseValue())) {
        	referenceId = getIdOnlyClient(I_M_Warehouse.Table_Name, I_M_Warehouse.COLUMNNAME_Value + " = ?", transactionName, importRequisition.getWarehouseValue());
        	if(referenceId > 0) {
        		importRequisition.setM_Warehouse_ID(referenceId);
        	} else { 
        		addError(I_M_Warehouse.COLUMNNAME_M_Warehouse_ID);
        	}
        }
        //	Business Partner
        referenceId = importRequisition.getC_BPartner_ID();
        if(referenceId <= 0
        		&& !Util.isEmpty(importRequisition.getBPartnerValue())) {
        	referenceId = getIdOnlyClient(I_C_BPartner.Table_Name, "(" + I_C_BPartner.COLUMNNAME_Value + " = ? OR " + I_C_BPartner.COLUMNNAME_TaxID + " = ?)" , transactionName, importRequisition.getBPartnerValue(), importRequisition.getBPartnerValue());
        	if(referenceId > 0) {
        		importRequisition.setC_BPartner_ID(referenceId);
        	} else { 
        		addError(I_C_BPartner.COLUMNNAME_C_BPartner_ID);
        	}
        }
        //	Product
        referenceId = importRequisition.getM_Product_ID();
        if(referenceId <= 0
        		&& !Util.isEmpty(importRequisition.getProductValue())) {
        	referenceId = getIdOnlyClient(I_M_Product.Table_Name, "(" + I_M_Product.COLUMNNAME_Value + " = ? OR " + I_M_Product.COLUMNNAME_UPC + " = ? OR " + I_M_Product.COLUMNNAME_SKU + " = ?)" , transactionName, importRequisition.getProductValue(), importRequisition.getProductValue(), importRequisition.getProductValue());
        	if(referenceId > 0) {
        		importRequisition.setM_Product_ID(referenceId);
        	}
        }
        //	Charge
        referenceId = importRequisition.getC_Charge_ID();
        if(referenceId <= 0
        		&& !Util.isEmpty(importRequisition.getChargeName())) {
        	referenceId = getIdOnlyClient(I_C_Charge.Table_Name, I_C_Charge.COLUMNNAME_Name + " = ?", transactionName, importRequisition.getChargeName());
        	if(referenceId > 0) {
        		importRequisition.setC_Charge_ID(referenceId);
        	} else { 
        		addError(I_C_Charge.COLUMNNAME_C_Charge_ID);
        	}
        }
        // Set Activity
        MActivity activity = null;
        if (importRequisition.getC_Activity_ID() > 0) {
        	activity = MActivity.getById(getCtx(), importRequisition.getC_Activity_ID(), transactionName);
        }
        if (activity == null && importRequisition.getActivityValue() != null) {
        	activity = MActivity.getByValue(getCtx(), importRequisition.getActivityValue(), transactionName);
        }
        if (activity != null && activity.getC_Activity_ID() > 0) {
        	importRequisition.setC_Activity_ID(activity.getC_Activity_ID());
        }
        // Set Campaign
        MCampaign campaign = null;
        if (importRequisition.getC_Campaign_ID() > 0) {
        	campaign = MCampaign.getById(getCtx(), importRequisition.getC_Campaign_ID(), transactionName);
        }
        if (campaign == null && importRequisition.getCampaignValue() != null) {
        	campaign = MCampaign.getByValue(getCtx(), importRequisition.getCampaignValue(), transactionName);
        }
        if (campaign != null && campaign.getC_Campaign_ID() > 0) {
        	importRequisition.setC_Campaign_ID(campaign.getC_Campaign_ID());
        }
        //Set Project
        MProject project = null;
        if (importRequisition.getC_Project_ID() > 0) {
        	project = MProject.getById(getCtx(), importRequisition.getC_Project_ID(), transactionName);
        }
        if (project == null && importRequisition.getProjectValue() != null) {
        	project = MProject.getByValue(getCtx(), importRequisition.getProjectValue(), transactionName);
        }
        if (project != null && project.getC_Project_ID() > 0) {
        	importRequisition.setC_Project_ID(project.getC_Project_ID());
        }
        //	Product Category
        referenceId = importRequisition.getM_Product_Category_ID();
        if(referenceId <= 0
        		&& !Util.isEmpty(importRequisition.getProductCategory_Value())) {
        	referenceId = getIdOnlyClient(I_M_Product_Category.Table_Name, I_M_Product_Category.COLUMNNAME_Value + " = ?", transactionName, importRequisition.getProductCategory_Value());
        	if(referenceId > 0) {
        		importRequisition.setM_Product_Category_ID(referenceId);
        	} else { 
        		addError(I_M_Product_Category.COLUMNNAME_M_Product_Category_ID);
        	}
        }
        //	Tax Category
        referenceId = importRequisition.getC_TaxCategory_ID();
        if(referenceId <= 0
        		&& !Util.isEmpty(importRequisition.getTaxCategoryName())) {
        	referenceId = getIdOnlyClient(I_C_TaxCategory.Table_Name, I_C_TaxCategory.COLUMNNAME_Name + " = ?", transactionName, importRequisition.getTaxCategoryName());
        	if(referenceId > 0) {
        		importRequisition.setC_TaxCategory_ID(referenceId);
        	} else { 
        		addError(I_C_TaxCategory.COLUMNNAME_C_TaxCategory_ID);
        	}
        }
        //	Project Phase
        referenceId = importRequisition.getC_ProjectPhase_ID();
        if(referenceId <= 0
        		&& !Util.isEmpty(importRequisition.getProjectPhaseName())) {
        	referenceId = getIdOnlyClient(I_C_ProjectPhase.Table_Name, I_C_ProjectPhase.COLUMNNAME_Name + " = ?", transactionName, importRequisition.getProjectPhaseName());
        	if(referenceId > 0) {
        		importRequisition.setC_ProjectPhase_ID(referenceId);
        	} else { 
        		addError(I_C_ProjectPhase.COLUMNNAME_C_ProjectPhase_ID);
        	}
        }
        //	User
        referenceId = importRequisition.getAD_User_ID();
        if(referenceId <= 0
        		&& !Util.isEmpty(importRequisition.getUserAgent())) {
        	referenceId = getIdOnlyClient(I_AD_User.Table_Name, "(" + I_AD_User.COLUMNNAME_Value + " = ? OR " + I_AD_User.COLUMNNAME_Name + " = ?)", transactionName, importRequisition.getUserAgent(), importRequisition.getUserAgent());
        	if(referenceId > 0) {
        		importRequisition.setAD_User_ID(referenceId);
        	} else { 
        		addError(I_AD_User.COLUMNNAME_AD_User_ID);
        	}
        }
        //	UOM
        referenceId = importRequisition.getC_UOM_ID();
        if(referenceId <= 0
        		&& !Util.isEmpty(importRequisition.getX12DE355())) {
        	referenceId = getId(I_C_UOM.Table_Name, I_C_UOM.COLUMNNAME_X12DE355 + " = ?", transactionName, importRequisition.getX12DE355());
        	if(referenceId > 0) {
        		importRequisition.setC_UOM_ID(referenceId);
        	} else { 
        		addError(I_C_UOM.COLUMNNAME_C_UOM_ID);
        	}
        }
        //	User 1
        referenceId = importRequisition.getUser1_ID();
        if(referenceId <= 0
        		&& !Util.isEmpty(importRequisition.getUserValue1())) {
        	referenceId = getIdOnlyClient(I_C_ElementValue.Table_Name, I_C_ElementValue.COLUMNNAME_Value + " = ?", transactionName, importRequisition.getUserValue1());
        	if(referenceId > 0) {
        		importRequisition.setUser1_ID(referenceId);
        	} else { 
        		addError(I_C_ElementValue.COLUMNNAME_C_ElementValue_ID);
        	}
        }
        //	User 2
        referenceId = importRequisition.getUser2_ID();
        if(referenceId <= 0
        		&& !Util.isEmpty(importRequisition.getUserValue2())) {
        	referenceId = getIdOnlyClient(I_C_ElementValue.Table_Name, I_C_ElementValue.COLUMNNAME_Value + " = ?", transactionName, importRequisition.getUserValue2());
        	if(referenceId > 0) {
        		importRequisition.setUser2_ID(referenceId);
        	} else { 
        		addError(I_C_ElementValue.COLUMNNAME_C_ElementValue_ID);
        	}
        }
        //	User 3
        referenceId = importRequisition.getUser3_ID();
        if(referenceId <= 0
        		&& !Util.isEmpty(importRequisition.getUserValue3())) {
        	referenceId = getIdOnlyClient(I_C_ElementValue.Table_Name, I_C_ElementValue.COLUMNNAME_Value + " = ?", transactionName, importRequisition.getUserValue3());
        	if(referenceId > 0) {
        		importRequisition.setUser3_ID(referenceId);
        	} else { 
        		addError(I_C_ElementValue.COLUMNNAME_C_ElementValue_ID);
        	}
        }
        //	User 4
        referenceId = importRequisition.getUser4_ID();
        if(referenceId <= 0
        		&& !Util.isEmpty(importRequisition.getUserValue4())) {
        	referenceId = getIdOnlyClient(I_C_ElementValue.Table_Name, I_C_ElementValue.COLUMNNAME_Value + " = ?", transactionName, importRequisition.getUserValue4());
        	if(referenceId > 0) {
        		importRequisition.setUser4_ID(referenceId);
        	} else { 
        		addError(I_C_ElementValue.COLUMNNAME_C_ElementValue_ID);
        	}
        }
        //	Save it
        if (!stringError.toString().isEmpty() && stringError.toString().length() > 0) {
        	importRequisition.setI_ErrorMsg(Msg.parseTranslation(getCtx(), stringError.toString()));
        } else {
        	importProduct(importRequisition, transactionName);
        }
        //	Date
        if(importRequisition.getDateRequired() == null) {
        	importRequisition.setDateRequired(TimeUtil.getDay(System.currentTimeMillis()));
        }
        if(importRequisition.getDateDoc() == null) {
        	importRequisition.setDateDoc(TimeUtil.getDay(System.currentTimeMillis()));
        }
        importRequisition.saveEx();
        //	
        ModelValidationEngine.get().fireImportValidate(this, importRequisition, null, ImportValidator.TIMING_AFTER_VALIDATE);
    }
    
    /**
     * Add error to string
     * @param columnName
     * @return void
     */
    private void addError(String columnName) {
    	if(stringError.length() > 0) {
    		stringError.append(", ");
    	}
    	stringError.append("@").append(columnName).append("@ @NotFound@");
    }
    
    /**
     * get ids based table name
     * @param tableName
     * @param whereClause
     * @param transactionName
     * @param parameters
     * @return
     */
    private int getIdOnlyClient(String tableName, String whereClause, String transactionName, Object... parameters) {
        return new Query(getCtx(), tableName, whereClause, transactionName)
        		.setClient_ID()
        		.setOnlyActiveRecords(true)
                .setParameters(parameters).firstId();
    }
    
    /**
     * get ids based table name
     * @param tableName
     * @param whereClause
     * @param transactionName
     * @param parameters
     * @return
     */
    private int getId(String tableName, String whereClause, String transactionName, Object... parameters) {
        return new Query(getCtx(), tableName, whereClause, transactionName)
        		.setOnlyActiveRecords(true)
                .setParameters(parameters).firstId();
    }
    
	@Override
	public String getImportTableName() {
		return X_I_Requisition.Table_Name;
	}


	@Override
	public String getWhereClause() {
		return " AND AD_Client_ID = " + getAD_Client_ID();
	}
}