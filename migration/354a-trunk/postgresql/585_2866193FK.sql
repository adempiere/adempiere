-- BF 2866193 - Missing foreign keys

ALTER TABLE AD_Field ADD CONSTRAINT IncludedTab_ADField FOREIGN KEY (Included_Tab_ID) REFERENCES AD_Tab;

ALTER TABLE AD_HouseKeeping ADD CONSTRAINT ADTable_ADHouseKeeping FOREIGN KEY (AD_Table_ID) REFERENCES AD_Table;

ALTER TABLE AD_OrgInfo ADD CONSTRAINT CCalendar_ADOrgInfo FOREIGN KEY (C_Calendar_ID) REFERENCES C_Calendar;

ALTER TABLE AD_OrgInfo ADD CONSTRAINT DropShipWarehouse_ADOrgInfo FOREIGN KEY (DropShip_Warehouse_ID) REFERENCES M_Warehouse;

ALTER TABLE AD_PrintForm ADD CONSTRAINT DistribOrderPrintFormat_ADPrin FOREIGN KEY (Distrib_Order_PrintFormat_ID) REFERENCES AD_PrintFormat;

ALTER TABLE AD_PrintForm ADD CONSTRAINT ManufOrderPrintFormat_ADPrintF FOREIGN KEY (Manuf_Order_PrintFormat_ID) REFERENCES AD_PrintFormat;

ALTER TABLE AD_PrintForm ADD CONSTRAINT DistribOrderMailText_ADPrintFo FOREIGN KEY (Distrib_Order_MailText_ID) REFERENCES R_MailText;

ALTER TABLE AD_PrintForm ADD CONSTRAINT ManufOrderMailText_ADPrintForm FOREIGN KEY (Manuf_Order_MailText_ID) REFERENCES R_MailText;

ALTER TABLE AD_PrintTableFormat ADD CONSTRAINT ADImage_ADPrintTableFormat FOREIGN KEY (AD_Image_ID) REFERENCES AD_Image;

ALTER TABLE AD_Process ADD CONSTRAINT ADForm_ADProcess FOREIGN KEY (AD_Form_ID) REFERENCES AD_Form;

ALTER TABLE AD_SearchDefinition ADD CONSTRAINT ADColumn_ADSearchDefinition FOREIGN KEY (AD_Column_ID) REFERENCES AD_Column;

ALTER TABLE AD_SearchDefinition ADD CONSTRAINT ADTable_ADSearchDefinition FOREIGN KEY (AD_Table_ID) REFERENCES AD_Table;

ALTER TABLE AD_SearchDefinition ADD CONSTRAINT ADWindow_ADSearchDefinition FOREIGN KEY (AD_Window_ID) REFERENCES AD_Window;

ALTER TABLE AD_SearchDefinition ADD CONSTRAINT POWindow_ADSearchDefinition FOREIGN KEY (PO_Window_ID) REFERENCES AD_Window;

ALTER TABLE AD_Tab ADD CONSTRAINT ParentColumn_ADTab FOREIGN KEY (Parent_Column_ID) REFERENCES AD_Column;

ALTER TABLE C_Charge ADD CONSTRAINT CChargeType_CCharge FOREIGN KEY (C_ChargeType_ID) REFERENCES C_ChargeType;

ALTER TABLE C_Charge_Trl ADD CONSTRAINT ADLangu_CChargeTrl FOREIGN KEY (AD_Language) REFERENCES AD_Language;

ALTER TABLE C_Charge_Trl ADD CONSTRAINT CCharge_CChargeTrl FOREIGN KEY (C_Charge_ID) REFERENCES C_Charge;

ALTER TABLE C_ChargeType_DocType ADD CONSTRAINT CChargeType_CChargeTypeDocType FOREIGN KEY (C_ChargeType_ID) REFERENCES C_ChargeType;

ALTER TABLE C_ChargeType_DocType ADD CONSTRAINT CDocType_CChargeTypeDocType FOREIGN KEY (C_DocType_ID) REFERENCES C_DocType;

ALTER TABLE C_Order ADD CONSTRAINT DropShipUser_COrder FOREIGN KEY (DropShip_User_ID) REFERENCES AD_User;

ALTER TABLE C_Order ADD CONSTRAINT DropShipBPartner_COrder FOREIGN KEY (DropShip_BPartner_ID) REFERENCES C_BPartner;

ALTER TABLE C_Order ADD CONSTRAINT DropShipLocation_COrder FOREIGN KEY (DropShip_Location_ID) REFERENCES C_BPartner_Location;

ALTER TABLE C_OrderLine ADD CONSTRAINT MPromotion_COrderLine FOREIGN KEY (M_Promotion_ID) REFERENCES M_Promotion;

ALTER TABLE C_OrderLine ADD CONSTRAINT PPCostCollector_COrderLine FOREIGN KEY (PP_Cost_Collector_ID) REFERENCES PP_Cost_Collector;

ALTER TABLE Fact_Acct_Summary ADD CONSTRAINT CAcctSchema_FactAcctSummary FOREIGN KEY (C_AcctSchema_ID) REFERENCES C_AcctSchema;

ALTER TABLE Fact_Acct_Summary ADD CONSTRAINT CActivity_FactAcctSummary FOREIGN KEY (C_Activity_ID) REFERENCES C_Activity;

ALTER TABLE Fact_Acct_Summary ADD CONSTRAINT CBPartner_FactAcctSummary FOREIGN KEY (C_BPartner_ID) REFERENCES C_BPartner;

ALTER TABLE Fact_Acct_Summary ADD CONSTRAINT CCampaign_FactAcctSummary FOREIGN KEY (C_Campaign_ID) REFERENCES C_Campaign;

ALTER TABLE Fact_Acct_Summary ADD CONSTRAINT Account_FactAcctSummary FOREIGN KEY (Account_ID) REFERENCES C_ElementValue;

ALTER TABLE Fact_Acct_Summary ADD CONSTRAINT User1_FactAcctSummary FOREIGN KEY (User1_ID) REFERENCES C_ElementValue;

ALTER TABLE Fact_Acct_Summary ADD CONSTRAINT User2_FactAcctSummary FOREIGN KEY (User2_ID) REFERENCES C_ElementValue;

ALTER TABLE Fact_Acct_Summary ADD CONSTRAINT CPeriod_FactAcctSummary FOREIGN KEY (C_Period_ID) REFERENCES C_Period;

ALTER TABLE Fact_Acct_Summary ADD CONSTRAINT CProject_FactAcctSummary FOREIGN KEY (C_Project_ID) REFERENCES C_Project;

ALTER TABLE Fact_Acct_Summary ADD CONSTRAINT CProjectPhase_FactAcctSummary FOREIGN KEY (C_ProjectPhase_ID) REFERENCES C_ProjectPhase;

ALTER TABLE Fact_Acct_Summary ADD CONSTRAINT CProjectTask_FactAcctSummary FOREIGN KEY (C_ProjectTask_ID) REFERENCES C_ProjectTask;

ALTER TABLE Fact_Acct_Summary ADD CONSTRAINT CSalesRegion_FactAcctSummary FOREIGN KEY (C_SalesRegion_ID) REFERENCES C_SalesRegion;

ALTER TABLE Fact_Acct_Summary ADD CONSTRAINT CSubAcct_FactAcctSummary FOREIGN KEY (C_SubAcct_ID) REFERENCES C_SubAcct;

ALTER TABLE Fact_Acct_Summary ADD CONSTRAINT GLBudget_FactAcctSummary FOREIGN KEY (GL_Budget_ID) REFERENCES GL_Budget;

ALTER TABLE Fact_Acct_Summary ADD CONSTRAINT MProduct_FactAcctSummary FOREIGN KEY (M_Product_ID) REFERENCES M_Product;

ALTER TABLE Fact_Acct_Summary ADD CONSTRAINT PAReportCube_FactAcctSummary FOREIGN KEY (PA_ReportCube_ID) REFERENCES PA_ReportCube;

ALTER TABLE I_Order ADD CONSTRAINT CCharge_IOrder FOREIGN KEY (C_Charge_ID) REFERENCES C_Charge;

ALTER TABLE I_PriceList ADD CONSTRAINT CBPartner_IPriceList FOREIGN KEY (C_BPartner_ID) REFERENCES C_BPartner;

ALTER TABLE I_PriceList ADD CONSTRAINT CCurrency_IPriceList FOREIGN KEY (C_Currency_ID) REFERENCES C_Currency;

ALTER TABLE I_PriceList ADD CONSTRAINT CUOM_IPriceList FOREIGN KEY (C_UOM_ID) REFERENCES C_UOM;

ALTER TABLE I_PriceList ADD CONSTRAINT MPriceList_IPriceList FOREIGN KEY (M_PriceList_ID) REFERENCES M_PriceList;

ALTER TABLE I_PriceList ADD CONSTRAINT MPriceListVersion_IPriceList FOREIGN KEY (M_PriceList_Version_ID) REFERENCES M_PriceList_Version;

ALTER TABLE I_PriceList ADD CONSTRAINT MProduct_IPriceList FOREIGN KEY (M_Product_ID) REFERENCES M_Product;

ALTER TABLE M_ChangeRequest ADD CONSTRAINT PPProductBOM_MChangeRequest FOREIGN KEY (PP_Product_BOM_ID) REFERENCES PP_Product_BOM;

ALTER TABLE M_CostDetail ADD CONSTRAINT PPCostCollector_MCostDetail FOREIGN KEY (PP_Cost_Collector_ID) REFERENCES PP_Cost_Collector;

ALTER TABLE M_Forecast ADD CONSTRAINT MPriceList_MForecast FOREIGN KEY (M_PriceList_ID) REFERENCES M_PriceList;

ALTER TABLE M_InOut ADD CONSTRAINT DropShipUser_MInOut FOREIGN KEY (DropShip_User_ID) REFERENCES AD_User;

ALTER TABLE M_InOut ADD CONSTRAINT DropShipBPartner_MInOut FOREIGN KEY (DropShip_BPartner_ID) REFERENCES C_BPartner;

ALTER TABLE M_InOut ADD CONSTRAINT DropShipLocation_MInOut FOREIGN KEY (DropShip_Location_ID) REFERENCES C_BPartner_Location;

ALTER TABLE M_InOutLine ADD CONSTRAINT ReversalLine_MInOutLine FOREIGN KEY (ReversalLine_ID) REFERENCES M_InOutLine;

ALTER TABLE M_InventoryLine ADD CONSTRAINT ReversalLine_MInventoryLine FOREIGN KEY (ReversalLine_ID) REFERENCES M_InventoryLine;

ALTER TABLE M_MovementLine ADD CONSTRAINT ReversalLine_MMovementLine FOREIGN KEY (ReversalLine_ID) REFERENCES M_MovementLine;

ALTER TABLE M_ProductPriceVendorBreak ADD CONSTRAINT CBPartner_MProductPriceVendorB FOREIGN KEY (C_BPartner_ID) REFERENCES C_BPartner;

ALTER TABLE M_ProductPriceVendorBreak ADD CONSTRAINT MPriceListVersion_MProductPric FOREIGN KEY (M_PriceList_Version_ID) REFERENCES M_PriceList_Version;

ALTER TABLE M_ProductPriceVendorBreak ADD CONSTRAINT MProduct_MProductPriceVendorBr FOREIGN KEY (M_Product_ID) REFERENCES M_Product;

ALTER TABLE M_Promotion ADD CONSTRAINT CCampaign_MPromotion FOREIGN KEY (C_Campaign_ID) REFERENCES C_Campaign;

ALTER TABLE M_PromotionDistribution ADD CONSTRAINT MPromotion_MPromotionDistribut FOREIGN KEY (M_Promotion_ID) REFERENCES M_Promotion;

ALTER TABLE M_PromotionDistribution ADD CONSTRAINT MPromotionLine_MPromotionDistr FOREIGN KEY (M_PromotionLine_ID) REFERENCES M_PromotionLine;

ALTER TABLE M_PromotionGroupLine ADD CONSTRAINT MProduct_MPromotionGroupLine FOREIGN KEY (M_Product_ID) REFERENCES M_Product;

ALTER TABLE M_PromotionGroupLine ADD CONSTRAINT MPromotionGroup_MPromotionGrou FOREIGN KEY (M_PromotionGroup_ID) REFERENCES M_PromotionGroup;

ALTER TABLE M_PromotionLine ADD CONSTRAINT MPromotion_MPromotionLine FOREIGN KEY (M_Promotion_ID) REFERENCES M_Promotion;

ALTER TABLE M_PromotionLine ADD CONSTRAINT MPromotionGroup_MPromotionLine FOREIGN KEY (M_PromotionGroup_ID) REFERENCES M_PromotionGroup;

ALTER TABLE M_PromotionPreCondition ADD CONSTRAINT CActivity_MPromotionPreConditi FOREIGN KEY (C_Activity_ID) REFERENCES C_Activity;

ALTER TABLE M_PromotionPreCondition ADD CONSTRAINT CBPartner_MPromotionPreConditi FOREIGN KEY (C_BPartner_ID) REFERENCES C_BPartner;

ALTER TABLE M_PromotionPreCondition ADD CONSTRAINT CBPGroup_MPromotionPreConditio FOREIGN KEY (C_BP_Group_ID) REFERENCES C_BP_Group;

ALTER TABLE M_PromotionPreCondition ADD CONSTRAINT MPriceList_MPromotionPreCondit FOREIGN KEY (M_PriceList_ID) REFERENCES M_PriceList;

ALTER TABLE M_PromotionPreCondition ADD CONSTRAINT MPromotion_MPromotionPreCondit FOREIGN KEY (M_Promotion_ID) REFERENCES M_Promotion;

ALTER TABLE M_PromotionPreCondition ADD CONSTRAINT MWarehouse_MPromotionPreCondit FOREIGN KEY (M_Warehouse_ID) REFERENCES M_Warehouse;

ALTER TABLE M_PromotionReward ADD CONSTRAINT CCharge_MPromotionReward FOREIGN KEY (C_Charge_ID) REFERENCES C_Charge;

ALTER TABLE M_PromotionReward ADD CONSTRAINT MPromotion_MPromotionReward FOREIGN KEY (M_Promotion_ID) REFERENCES M_Promotion;

ALTER TABLE M_PromotionReward ADD CONSTRAINT MPromotionDistribution_MPromot FOREIGN KEY (M_PromotionDistribution_ID) REFERENCES M_PromotionDistribution;

ALTER TABLE M_PromotionReward ADD CONSTRAINT MTargetDistribution_MPromotion FOREIGN KEY (M_TargetDistribution_ID) REFERENCES M_PromotionDistribution;

ALTER TABLE M_Replenish ADD CONSTRAINT MLocator_MReplenish FOREIGN KEY (M_Locator_ID) REFERENCES M_Locator;

ALTER TABLE M_RequisitionLine ADD CONSTRAINT CBPartner_MRequisitionLine FOREIGN KEY (C_BPartner_ID) REFERENCES C_BPartner;

ALTER TABLE M_RequisitionLine ADD CONSTRAINT CUOM_MRequisitionLine FOREIGN KEY (C_UOM_ID) REFERENCES C_UOM;

ALTER TABLE M_RMA ADD CONSTRAINT RefRMA_MRMA FOREIGN KEY (Ref_RMA_ID) REFERENCES M_RMA;

ALTER TABLE M_RMALine ADD CONSTRAINT RefRMALine_MRMALine FOREIGN KEY (Ref_RMALine_ID) REFERENCES M_RMALine;

ALTER TABLE M_Transaction ADD CONSTRAINT PPCostCollector_MTransaction FOREIGN KEY (PP_Cost_Collector_ID) REFERENCES PP_Cost_Collector;

ALTER TABLE PA_Report ADD CONSTRAINT PAReportCube_PAReport FOREIGN KEY (PA_ReportCube_ID) REFERENCES PA_ReportCube;

ALTER TABLE PA_ReportColumn ADD CONSTRAINT ADOrgTrx_PAReportColumn FOREIGN KEY (AD_OrgTrx_ID) REFERENCES AD_Org;

ALTER TABLE PA_ReportCube ADD CONSTRAINT CCalendar_PAReportCube FOREIGN KEY (C_Calendar_ID) REFERENCES C_Calendar;

ALTER TABLE PA_ReportSource ADD CONSTRAINT ADOrgTrx_PAReportSource FOREIGN KEY (AD_OrgTrx_ID) REFERENCES AD_Org;

ALTER TABLE PP_Order_BOMLine_Trl ADD CONSTRAINT ADLangu_PPOrderBOMLineTrl FOREIGN KEY (AD_Language) REFERENCES AD_Language;

ALTER TABLE PP_Order_BOMLine_Trl ADD CONSTRAINT PPOrderBOMLine_PPOrderBOMLineT FOREIGN KEY (PP_Order_BOMLine_ID) REFERENCES PP_Order_BOMLine;

ALTER TABLE PP_Order_BOM_Trl ADD CONSTRAINT ADLangu_PPOrderBOMTrl FOREIGN KEY (AD_Language) REFERENCES AD_Language;

ALTER TABLE PP_Order_BOM_Trl ADD CONSTRAINT PPOrderBOM_PPOrderBOMTrl FOREIGN KEY (PP_Order_BOM_ID) REFERENCES PP_Order_BOM;

ALTER TABLE PP_Order_Node_Trl ADD CONSTRAINT ADLangu_PPOrderNodeTrl FOREIGN KEY (AD_Language) REFERENCES AD_Language;

ALTER TABLE PP_Order_Workflow_Trl ADD CONSTRAINT ADLangu_PPOrderWorkflowTrl FOREIGN KEY (AD_Language) REFERENCES AD_Language;

ALTER TABLE PP_Order_Workflow_Trl ADD CONSTRAINT PPOrderWorkflow_PPOrderWorkflo FOREIGN KEY (PP_Order_Workflow_ID) REFERENCES PP_Order_Workflow;

ALTER TABLE PP_Product_BOMLine_Trl ADD CONSTRAINT ADLangu_PPProductBOMLineTrl FOREIGN KEY (AD_Language) REFERENCES AD_Language;

ALTER TABLE PP_Product_BOMLine_Trl ADD CONSTRAINT PPProductBOMLine_PPProductBOML FOREIGN KEY (PP_Product_BOMLine_ID) REFERENCES PP_Product_BOMLine;

ALTER TABLE PP_Product_BOM_Trl ADD CONSTRAINT ADLangu_PPProductBOMTrl FOREIGN KEY (AD_Language) REFERENCES AD_Language;

ALTER TABLE PP_Product_BOM_Trl ADD CONSTRAINT PPProductBOM_PPProductBOMTrl FOREIGN KEY (PP_Product_BOM_ID) REFERENCES PP_Product_BOM;

ALTER TABLE PP_Product_Planning ADD CONSTRAINT Planner_PPProductPlanning FOREIGN KEY (Planner_ID) REFERENCES AD_User;

ALTER TABLE PP_Product_Planning ADD CONSTRAINT ADWorkflow_PPProductPlanning FOREIGN KEY (AD_Workflow_ID) REFERENCES AD_Workflow;

ALTER TABLE PP_Product_Planning ADD CONSTRAINT DDNetworkDistribution_PPProduc FOREIGN KEY (DD_NetworkDistribution_ID) REFERENCES DD_NetworkDistribution;

ALTER TABLE PP_Product_Planning ADD CONSTRAINT MWarehouse_PPProductPlanning FOREIGN KEY (M_Warehouse_ID) REFERENCES M_Warehouse;

ALTER TABLE PP_Product_Planning ADD CONSTRAINT PPProductBOM_PPProductPlanning FOREIGN KEY (PP_Product_BOM_ID) REFERENCES PP_Product_BOM;

ALTER TABLE PP_Product_Planning ADD CONSTRAINT SResource_PPProductPlanning FOREIGN KEY (S_Resource_ID) REFERENCES S_Resource;

ALTER TABLE U_POSTerminal ADD CONSTRAINT SalesRep_UPOSTerminal FOREIGN KEY (SalesRep_ID) REFERENCES AD_User;

ALTER TABLE U_POSTerminal ADD CONSTRAINT CardBankAccount_UPOSTerminal FOREIGN KEY (Card_BankAccount_ID) REFERENCES C_BankAccount;

ALTER TABLE U_POSTerminal ADD CONSTRAINT CardTransferBankAccount_UPOSTe FOREIGN KEY (CardTransferBankAccount_ID) REFERENCES C_BankAccount;

ALTER TABLE U_POSTerminal ADD CONSTRAINT CashTransferBankAccount_UPOSTe FOREIGN KEY (CashTransferBankAccount_ID) REFERENCES C_BankAccount;

ALTER TABLE U_POSTerminal ADD CONSTRAINT CheckBankAccount_UPOSTerminal FOREIGN KEY (Check_BankAccount_ID) REFERENCES C_BankAccount;

ALTER TABLE U_POSTerminal ADD CONSTRAINT CheckTransferBankAccount_UPOST FOREIGN KEY (CheckTransferBankAccount_ID) REFERENCES C_BankAccount;

ALTER TABLE U_POSTerminal ADD CONSTRAINT CCashBPartner_UPOSTerminal FOREIGN KEY (C_CashBPartner_ID) REFERENCES C_BPartner;

ALTER TABLE U_POSTerminal ADD CONSTRAINT CTemplateBPartner_UPOSTerminal FOREIGN KEY (C_TemplateBPartner_ID) REFERENCES C_BPartner;

ALTER TABLE U_POSTerminal ADD CONSTRAINT CardTransferCashBook_UPOSTermi FOREIGN KEY (CardTransferCashBook_ID) REFERENCES C_CashBook;

ALTER TABLE U_POSTerminal ADD CONSTRAINT CashTransferCashBook_UPOSTermi FOREIGN KEY (CashTransferCashBook_ID) REFERENCES C_CashBook;

ALTER TABLE U_POSTerminal ADD CONSTRAINT CCashBook_UPOSTerminal FOREIGN KEY (C_CashBook_ID) REFERENCES C_CashBook;

ALTER TABLE U_POSTerminal ADD CONSTRAINT CheckTransferCashBook_UPOSTerm FOREIGN KEY (CheckTransferCashBook_ID) REFERENCES C_CashBook;

ALTER TABLE U_POSTerminal ADD CONSTRAINT POPriceList_UPOSTerminal FOREIGN KEY (PO_PriceList_ID) REFERENCES M_PriceList;

ALTER TABLE U_POSTerminal ADD CONSTRAINT SOPriceList_UPOSTerminal FOREIGN KEY (SO_PriceList_ID) REFERENCES M_PriceList;

ALTER TABLE U_POSTerminal ADD CONSTRAINT MWarehouse_UPOSTerminal FOREIGN KEY (M_Warehouse_ID) REFERENCES M_Warehouse;
