ALTER TABLE AD_OrgInfo ADD CONSTRAINT AD_OrgInfo_AD_Image_ID FOREIGN KEY(Logo_ID) REFERENCES AD_Image(AD_Image_ID);

ALTER TABLE AD_OrgInfo DROP CONSTRAINT AD_OrgInfo_AD_Image_ID;

ALTER TABLE AD_ClientInfo ADD CONSTRAINT Logo_ADClientInfo FOREIGN KEY(Logo_ID) REFERENCES AD_Image(AD_Image_ID);

ALTER TABLE AD_ClientInfo ADD CONSTRAINT LogoReport_ADClientInfo FOREIGN KEY(LogoReport_ID) REFERENCES AD_Image(AD_Image_ID);

ALTER TABLE AD_ClientInfo ADD CONSTRAINT LogoWeb_ADClientInfo FOREIGN KEY(LogoWeb_ID) REFERENCES AD_Image(AD_Image_ID);

ALTER TABLE AD_OrgInfo ADD CONSTRAINT Logo_ADOrgInfo FOREIGN KEY(Logo_ID) REFERENCES AD_Image(AD_Image_ID);

ALTER TABLE AD_Registration ADD CONSTRAINT CLocation_ADRegistration FOREIGN KEY(C_Location_ID) REFERENCES C_Location(C_Location_ID);

ALTER TABLE C_AcctSchema_Default ADD CONSTRAINT CReceivableServices_CAcctSchem FOREIGN KEY(C_Receivable_Services_Acct) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE C_AcctSchema_Default ADD CONSTRAINT PAverageCostVariance_CAcctSche FOREIGN KEY(P_AverageCostVariance_Acct) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE C_AcctSchema_Default ADD CONSTRAINT PBurden_CAcctSchemaDefault FOREIGN KEY(P_Burden_Acct) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE C_AcctSchema_Default ADD CONSTRAINT PCostAdjustment_CAcctSchemaDef FOREIGN KEY(P_CostAdjustment_Acct) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE C_AcctSchema_Default ADD CONSTRAINT PCostOfProduction_CAcctSchemaD FOREIGN KEY(P_CostOfProduction_Acct) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE C_AcctSchema_Default ADD CONSTRAINT PFloorStock_CAcctSchemaDefault FOREIGN KEY(P_FloorStock_Acct) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE C_AcctSchema_Default ADD CONSTRAINT PInventoryClearing_CAcctSchema FOREIGN KEY(P_InventoryClearing_Acct) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE C_AcctSchema_Default ADD CONSTRAINT PLabor_CAcctSchemaDefault FOREIGN KEY(P_Labor_Acct) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE C_AcctSchema_Default ADD CONSTRAINT PMethodChangeVariance_CAcctSch FOREIGN KEY(P_MethodChangeVariance_Acct) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE C_AcctSchema_Default ADD CONSTRAINT PMixVariance_CAcctSchemaDefaul FOREIGN KEY(P_MixVariance_Acct) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE C_AcctSchema_Default ADD CONSTRAINT POutsideProcessing_CAcctSchema FOREIGN KEY(P_OutsideProcessing_Acct) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE C_AcctSchema_Default ADD CONSTRAINT POverhead_CAcctSchemaDefault FOREIGN KEY(P_Overhead_Acct) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE C_AcctSchema_Default ADD CONSTRAINT PRateVariance_CAcctSchemaDefau FOREIGN KEY(P_RateVariance_Acct) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE C_AcctSchema_Default ADD CONSTRAINT PScrap_CAcctSchemaDefault FOREIGN KEY(P_Scrap_Acct) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE C_AcctSchema_Default ADD CONSTRAINT PUsageVariance_CAcctSchemaDefa FOREIGN KEY(P_UsageVariance_Acct) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE C_AcctSchema_Default ADD CONSTRAINT PWIP_CAcctSchemaDefault FOREIGN KEY(P_WIP_Acct) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE C_AcctSchema_GL ADD CONSTRAINT CommitmentOffset_CAcctSchemaGL FOREIGN KEY(CommitmentOffset_Acct) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE C_AcctSchema_GL ADD CONSTRAINT CommitmentOffsetSales_CAcctSch FOREIGN KEY(CommitmentOffsetSales_Acct) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE C_BPartner ADD CONSTRAINT Logo_CBPartner FOREIGN KEY(Logo_ID) REFERENCES AD_Image(AD_Image_ID);

ALTER TABLE C_BP_Customer_Acct ADD CONSTRAINT CReceivableServices_CBPCustome FOREIGN KEY(C_Receivable_Services_Acct) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE C_BP_Group_Acct ADD CONSTRAINT CReceivableServices_CBPGroupAc FOREIGN KEY(C_Receivable_Services_Acct) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE CM_Media ADD CONSTRAINT ADImage_CMMedia FOREIGN KEY(AD_Image_ID) REFERENCES AD_Image(AD_Image_ID);

ALTER TABLE C_POSKey ADD CONSTRAINT ADImage_CPOSKey FOREIGN KEY(AD_Image_ID) REFERENCES AD_Image(AD_Image_ID);

ALTER TABLE DD_OrderLine ADD CONSTRAINT MAttributeSetInstance_DDOrderL FOREIGN KEY(M_AttributeSetInstance_ID) REFERENCES M_AttributeSetInstance(M_AttributeSetInstance_ID);

ALTER TABLE DD_OrderLine ADD CONSTRAINT MAttributeSetInstanceTo_DDOrde FOREIGN KEY(M_AttributeSetInstanceTo_ID) REFERENCES M_AttributeSetInstance(M_AttributeSetInstance_ID);

ALTER TABLE HR_Attribute ADD CONSTRAINT HRAttribute_HRAttribute FOREIGN KEY(HR_Attribute_Acct) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE HR_Concept_Acct ADD CONSTRAINT HRExpense_HRConceptAcct FOREIGN KEY(HR_Expense_Acct) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE HR_Concept_Acct ADD CONSTRAINT HRRevenue_HRConceptAcct FOREIGN KEY(HR_Revenue_Acct) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE HR_Concept_Acct ADD CONSTRAINT Use_HRConceptAcct FOREIGN KEY(User2_ID) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE HR_Concept_Category ADD CONSTRAINT HRConcept_HRConceptCategory FOREIGN KEY(HR_Concept_Acct) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE M_MatchInv ADD CONSTRAINT MAttributeSetInstance_MMatchIn FOREIGN KEY(M_AttributeSetInstance_ID) REFERENCES M_AttributeSetInstance(M_AttributeSetInstance_ID);

ALTER TABLE M_MatchPO ADD CONSTRAINT MAttributeSetInstance_MMatchPO FOREIGN KEY(M_AttributeSetInstance_ID) REFERENCES M_AttributeSetInstance(M_AttributeSetInstance_ID);

ALTER TABLE M_Movement ADD CONSTRAINT CBPartnerLocation_MMovement FOREIGN KEY(C_BPartner_Location_ID) REFERENCES C_Location(C_Location_ID);

ALTER TABLE M_MovementLine ADD CONSTRAINT MAttributeSetInstanceTo_MMovem FOREIGN KEY(M_AttributeSetInstanceTo_ID) REFERENCES M_AttributeSetInstance(M_AttributeSetInstance_ID);

ALTER TABLE M_Product_Acct ADD CONSTRAINT PAverageCostVariance_MProductA FOREIGN KEY(P_AverageCostVariance_Acct) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE M_Product_Acct ADD CONSTRAINT PBurden_MProductAcct FOREIGN KEY(P_Burden_Acct) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE M_Product_Acct ADD CONSTRAINT PCostAdjustment_MProductAcct FOREIGN KEY(P_CostAdjustment_Acct) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE M_Product_Acct ADD CONSTRAINT PCostOfProduction_MProductAcct FOREIGN KEY(P_CostOfProduction_Acct) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE M_Product_Acct ADD CONSTRAINT PFloorStock_MProductAcct FOREIGN KEY(P_FloorStock_Acct) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE M_Product_Acct ADD CONSTRAINT PInventoryClearing_MProductAcc FOREIGN KEY(P_InventoryClearing_Acct) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE M_Product_Acct ADD CONSTRAINT PLabor_MProductAcct FOREIGN KEY(P_Labor_Acct) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE M_Product_Acct ADD CONSTRAINT PMethodChangeVariance_MProduct FOREIGN KEY(P_MethodChangeVariance_Acct) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE M_Product_Acct ADD CONSTRAINT PMixVariance_MProductAcct FOREIGN KEY(P_MixVariance_Acct) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE M_Product_Acct ADD CONSTRAINT POutsideProcessing_MProductAcc FOREIGN KEY(P_OutsideProcessing_Acct) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE M_Product_Acct ADD CONSTRAINT POverhead_MProductAcct FOREIGN KEY(P_Overhead_Acct) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE M_Product_Acct ADD CONSTRAINT PRateVariance_MProductAcct FOREIGN KEY(P_RateVariance_Acct) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE M_Product_Acct ADD CONSTRAINT PScrap_MProductAcct FOREIGN KEY(P_Scrap_Acct) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE M_Product_Acct ADD CONSTRAINT PUsageVariance_MProductAcct FOREIGN KEY(P_UsageVariance_Acct) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE M_Product_Acct ADD CONSTRAINT PWIP_MProductAcct FOREIGN KEY(P_WIP_Acct) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE M_Product_Category_Acct ADD CONSTRAINT PAverageCostVariance_MProductC FOREIGN KEY(P_AverageCostVariance_Acct) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE M_Product_Category_Acct ADD CONSTRAINT PBurden_MProductCategoryAcct FOREIGN KEY(P_Burden_Acct) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE M_Product_Category_Acct ADD CONSTRAINT PCostAdjustment_MProductCatego FOREIGN KEY(P_CostAdjustment_Acct) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE M_Product_Category_Acct ADD CONSTRAINT PCostOfProduction_MProductCate FOREIGN KEY(P_CostOfProduction_Acct) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE M_Product_Category_Acct ADD CONSTRAINT PFloorStock_MProductCategoryAc FOREIGN KEY(P_FloorStock_Acct) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE M_Product_Category_Acct ADD CONSTRAINT PInventoryClearing_MProductCat FOREIGN KEY(P_InventoryClearing_Acct) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE M_Product_Category_Acct ADD CONSTRAINT PLabor_MProductCategoryAcct FOREIGN KEY(P_Labor_Acct) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE M_Product_Category_Acct ADD CONSTRAINT PMethodChangeVariance_MProdCat FOREIGN KEY(P_MethodChangeVariance_Acct) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE M_Product_Category_Acct ADD CONSTRAINT PMixVariance_MProductCategoryA FOREIGN KEY(P_MixVariance_Acct) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE M_Product_Category_Acct ADD CONSTRAINT POutsideProcessing_MProductCat FOREIGN KEY(P_OutsideProcessing_Acct) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE M_Product_Category_Acct ADD CONSTRAINT POverhead_MProductCategoryAcct FOREIGN KEY(P_Overhead_Acct) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE M_Product_Category_Acct ADD CONSTRAINT PRateVariance_MProductCategory FOREIGN KEY(P_RateVariance_Acct) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE M_Product_Category_Acct ADD CONSTRAINT PScrap_MProductCategoryAcct FOREIGN KEY(P_Scrap_Acct) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE M_Product_Category_Acct ADD CONSTRAINT PUsageVariance_MProductCategor FOREIGN KEY(P_UsageVariance_Acct) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE M_Product_Category_Acct ADD CONSTRAINT PWIP_MProductCategoryAcct FOREIGN KEY(P_WIP_Acct) REFERENCES C_ValidCombination(C_ValidCombination_ID);

ALTER TABLE M_RequisitionLine ADD CONSTRAINT MAttributeSetInstance_MRequisi FOREIGN KEY(M_AttributeSetInstance_ID) REFERENCES M_AttributeSetInstance(M_AttributeSetInstance_ID);

ALTER TABLE PP_Cost_Collector ADD CONSTRAINT MAttributeSetInstance_PPCostCo FOREIGN KEY(M_AttributeSetInstance_ID) REFERENCES M_AttributeSetInstance(M_AttributeSetInstance_ID);

ALTER TABLE PP_Cost_Collector ADD CONSTRAINT MLocator_PPCostCollector FOREIGN KEY(M_Locator_ID) REFERENCES M_Locator(M_Locator_ID);

ALTER TABLE PP_Cost_CollectorMA ADD CONSTRAINT MAttributeSetInstance_PPCostMA FOREIGN KEY(M_AttributeSetInstance_ID) REFERENCES M_AttributeSetInstance(M_AttributeSetInstance_ID);

ALTER TABLE PP_Order ADD CONSTRAINT MAttributeSetInstance_PPOrder FOREIGN KEY(M_AttributeSetInstance_ID) REFERENCES M_AttributeSetInstance(M_AttributeSetInstance_ID);

ALTER TABLE PP_Order_BOM ADD CONSTRAINT MAttributeSetInstance_PPOrderB FOREIGN KEY(M_AttributeSetInstance_ID) REFERENCES M_AttributeSetInstance(M_AttributeSetInstance_ID);

ALTER TABLE PP_Order_BOMLine ADD CONSTRAINT MAttributeSetInstance_PPOrdBL FOREIGN KEY(M_AttributeSetInstance_ID) REFERENCES M_AttributeSetInstance(M_AttributeSetInstance_ID);

ALTER TABLE PP_Order_BOMLine ADD CONSTRAINT MLocator_PPOrderBOMLine FOREIGN KEY(M_Locator_ID) REFERENCES M_Locator(M_Locator_ID);

ALTER TABLE PP_Order_Cost ADD CONSTRAINT MAttributeSetInstance_PPOrderC FOREIGN KEY(M_AttributeSetInstance_ID) REFERENCES M_AttributeSetInstance(M_AttributeSetInstance_ID);

ALTER TABLE PP_Product_BOM ADD CONSTRAINT MAttributeSetInstance_PPProduc FOREIGN KEY(M_AttributeSetInstance_ID) REFERENCES M_AttributeSetInstance(M_AttributeSetInstance_ID);

ALTER TABLE PP_Product_BOMLine ADD CONSTRAINT MAttributeSetInstance_PPProdBL FOREIGN KEY(M_AttributeSetInstance_ID) REFERENCES M_AttributeSetInstance(M_AttributeSetInstance_ID);

ALTER TABLE AD_RelationType ADD (CONSTRAINT ADReferenceSource_ADRelationTy FOREIGN KEY (AD_Reference_Source_ID) REFERENCES AD_Reference);

ALTER TABLE AD_RelationType ADD (CONSTRAINT ADReferenceTarget_ADRelationTy FOREIGN KEY (AD_Reference_Target_ID) REFERENCES AD_Reference);

ALTER TABLE C_DunningRun ADD (CONSTRAINT CDunning_CDunningRun FOREIGN KEY (C_Dunning_ID) REFERENCES C_Dunning);

ALTER TABLE C_DunningRunEntry ADD (CONSTRAINT CDunningLevel_CDunningRunEntry FOREIGN KEY (C_DunningLevel_ID) REFERENCES C_DunningLevel);

ALTER TABLE C_POS ADD (CONSTRAINT OSKKeyLayout_CPOS FOREIGN KEY (OSK_KeyLayout_ID) REFERENCES C_POSKeyLayout);

ALTER TABLE C_POS ADD (CONSTRAINT OSNPKeyLayout_CPOS FOREIGN KEY (OSNP_KeyLayout_ID) REFERENCES C_POSKeyLayout);

ALTER TABLE C_POSKey ADD (CONSTRAINT ADPrintFont_CPOSKey FOREIGN KEY (AD_PrintFont_ID) REFERENCES AD_PrintFont);

ALTER TABLE C_POSKey ADD (CONSTRAINT SubKeyLayout_CPOSKey FOREIGN KEY (SubKeyLayout_ID) REFERENCES C_POSKeyLayout);

ALTER TABLE C_POSKeyLayout ADD (CONSTRAINT ADPrintColor_CPOSKeyLayout FOREIGN KEY (AD_PrintColor_ID) REFERENCES AD_PrintColor);

ALTER TABLE C_POSKeyLayout ADD (CONSTRAINT ADPrintFont_CPOSKeyLayout FOREIGN KEY (AD_PrintFont_ID) REFERENCES AD_PrintFont);

ALTER TABLE I_Order ADD (CONSTRAINT COrderSource_IOrder FOREIGN KEY (C_OrderSource_ID) REFERENCES C_OrderSource);

ALTER TABLE I_ProductPlanning ADD (CONSTRAINT Planner_IProductPlanning FOREIGN KEY (Planner_ID) REFERENCES AD_User);

ALTER TABLE I_ProductPlanning ADD (CONSTRAINT CBPartner_IProductPlanning FOREIGN KEY (C_BPartner_ID) REFERENCES C_BPartner);

ALTER TABLE I_ProductPlanning ADD (CONSTRAINT MForecast_IProductPlanning FOREIGN KEY (M_Forecast_ID) REFERENCES M_Forecast);

ALTER TABLE I_ProductPlanning ADD (CONSTRAINT MForecastLine_IProductPlanning FOREIGN KEY (M_ForecastLine_ID) REFERENCES M_ForecastLine);

ALTER TABLE I_ProductPlanning ADD (CONSTRAINT MProduct_IProductPlanning FOREIGN KEY (M_Product_ID) REFERENCES M_Product);

ALTER TABLE I_ProductPlanning ADD (CONSTRAINT MWarehouse_IProductPlanning FOREIGN KEY (M_Warehouse_ID) REFERENCES M_Warehouse);

ALTER TABLE I_ProductPlanning ADD (CONSTRAINT PPProductBOM_IProductPlanning FOREIGN KEY (PP_Product_BOM_ID) REFERENCES PP_Product_BOM);

ALTER TABLE I_ProductPlanning ADD (CONSTRAINT PPProductPlanning_IProductPlan FOREIGN KEY (PP_Product_Planning_ID) REFERENCES PP_Product_Planning);

ALTER TABLE I_ProductPlanning ADD (CONSTRAINT SResource_IProductPlanning FOREIGN KEY (S_Resource_ID) REFERENCES S_Resource);

ALTER TABLE PP_Order_Node_Trl ADD (CONSTRAINT PPOrderNode_PPOrderNodeTrl FOREIGN KEY (PP_Order_Node_ID) REFERENCES PP_Order_Node);
