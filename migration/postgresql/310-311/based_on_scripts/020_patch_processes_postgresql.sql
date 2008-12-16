UPDATE AD_Process 
SET Classname='org.compiere.process.AD_PrintPaper_Default', 
    ProcedureName=NULL
WHERE value = 'AD_PrintPaper_Default';

UPDATE AD_Process 
SET Classname='org.compiere.process.C_AcctSchema_Default_Copy', 
    ProcedureName=NULL
WHERE value like 'C_AcctSchema_Default_Copy';

UPDATE AD_Process 
SET Classname='org.compiere.process.C_BP_Group_Acct_Copy', 
    ProcedureName=NULL
WHERE value = 'C_BP_Group_Acct_Copy';

UPDATE AD_Process 
SET Classname='org.compiere.process.M_Product_BOM_Check', 
    ProcedureName=NULL
WHERE value = 'M_Product_BOM';

UPDATE AD_Process 
SET Classname='org.compiere.process.M_Product_Category_Acct_Copy', 
    ProcedureName=NULL
where value = 'M_Product_Category_Acct_Copy';

UPDATE AD_Process 
SET Classname='org.compiere.process.M_Product_CostingUpdate', 
    ProcedureName=NULL
WHERE value = 'M_Product_CostingUpdate';

UPDATE AD_Process 
SET Classname='org.compiere.process.M_Production_Run', 
    ProcedureName=NULL
WHERE value like 'M_Production';

UPDATE AD_Process 
SET Classname='org.compiere.process.T_InventoryValue_Create', 
    ProcedureName=NULL
WHERE value = 'RV_T_InventoryValue';

UPDATE AD_Process 
SET Classname='org.compiere.process.M_PriceList_Create', 
    ProcedureName=NULL
WHERE value = 'M_PriceList Create';
