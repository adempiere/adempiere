UPDATE M_Product_Category_ACCT
SET P_WIP_Acct = (SELECT P_WIP_Acct
                             FROM C_AcctSchema_Default AD
                                 WHERE AD.C_AcctSchema_ID=M_Product_Category_ACCT.C_AcctSchema_ID )
WHERE P_WIP_Acct IS NULL;

UPDATE M_Product_Category_ACCT
SET P_FloorStock_Acct = (SELECT P_FloorStock_Acct
                             FROM C_AcctSchema_Default AD
                                 WHERE AD.C_AcctSchema_ID=M_Product_Category_ACCT.C_AcctSchema_ID )
WHERE P_FloorStock_Acct IS NULL;

UPDATE M_Product_Category_ACCT
SET P_MethodChangeVariance_Acct = (SELECT P_MethodChangeVariance_Acct
                             FROM C_AcctSchema_Default AD
                                 WHERE AD.C_AcctSchema_ID=M_Product_Category_ACCT.C_AcctSchema_ID )
WHERE P_MethodChangeVariance_Acct IS NULL;

UPDATE M_Product_Category_ACCT
SET P_UsageVariance_Acct  = (SELECT P_UsageVariance_Acct
                             FROM C_AcctSchema_Default AD
                                 WHERE AD.C_AcctSchema_ID=M_Product_Category_ACCT.C_AcctSchema_ID )
WHERE P_UsageVariance_Acct IS NULL;

UPDATE M_Product_Category_ACCT
SET P_RateVariance_Acct   = (SELECT P_RateVariance_Acct
                             FROM C_AcctSchema_Default AD
                                 WHERE AD.C_AcctSchema_ID=M_Product_Category_ACCT.C_AcctSchema_ID )
WHERE P_RateVariance_Acct IS NULL;

UPDATE M_Product_Category_ACCT
SET P_MixVariance_Acct   = (SELECT P_MixVariance_Acct
                             FROM C_AcctSchema_Default AD
                                 WHERE AD.C_AcctSchema_ID=M_Product_Category_ACCT.C_AcctSchema_ID )
WHERE P_MixVariance_Acct IS NULL;

UPDATE M_Product_Category_ACCT
SET P_Labor_Acct    = (SELECT P_Labor_Acct
                             FROM C_AcctSchema_Default AD
                                 WHERE AD.C_AcctSchema_ID=M_Product_Category_ACCT.C_AcctSchema_ID )
WHERE P_Labor_Acct IS NULL;

UPDATE M_Product_Category_ACCT
SET P_Burden_Acct     = (SELECT P_Burden_Acct
                             FROM C_AcctSchema_Default AD
                                 WHERE AD.C_AcctSchema_ID=M_Product_Category_ACCT.C_AcctSchema_ID )
WHERE P_Burden_Acct IS NULL;

UPDATE M_Product_Category_ACCT
SET P_CostOfProduction_Acct      = (SELECT P_CostOfProduction_Acct
                             FROM C_AcctSchema_Default AD
                                 WHERE AD.C_AcctSchema_ID=M_Product_Category_ACCT.C_AcctSchema_ID )
WHERE P_CostOfProduction_Acct IS NULL;

UPDATE M_Product_Category_ACCT
SET P_OutsideProcessing_Acct      = (SELECT P_OutsideProcessing_Acct
                             FROM C_AcctSchema_Default AD
                                 WHERE AD.C_AcctSchema_ID=M_Product_Category_ACCT.C_AcctSchema_ID )
WHERE P_OutsideProcessing_Acct IS NULL;

UPDATE M_Product_Category_ACCT
SET P_Overhead_Acct        = (SELECT P_Overhead_Acct
                             FROM C_AcctSchema_Default AD
                                 WHERE AD.C_AcctSchema_ID=M_Product_Category_ACCT.C_AcctSchema_ID )
WHERE P_Overhead_Acct IS NULL;

UPDATE M_Product_Category_ACCT
SET P_Scrap_Acct      = (SELECT P_Scrap_Acct
                             FROM C_AcctSchema_Default AD
                                 WHERE AD.C_AcctSchema_ID=M_Product_Category_ACCT.C_AcctSchema_ID )
WHERE P_Scrap_Acct IS NULL;

--Para Productos

UPDATE M_Product_ACCT
SET P_WIP_Acct = (SELECT P_WIP_Acct
                             FROM C_AcctSchema_Default AD
                                 WHERE AD.C_AcctSchema_ID=M_Product_ACCT.C_AcctSchema_ID )
WHERE P_WIP_Acct IS NULL;

UPDATE M_Product_ACCT
SET P_FloorStock_Acct = (SELECT P_FloorStock_Acct
                             FROM C_AcctSchema_Default AD
                                 WHERE AD.C_AcctSchema_ID=M_Product_ACCT.C_AcctSchema_ID )
WHERE P_FloorStock_Acct IS NULL;

UPDATE M_Product_ACCT
SET P_MethodChangeVariance_Acct = (SELECT P_MethodChangeVariance_Acct
                             FROM C_AcctSchema_Default AD
                                 WHERE AD.C_AcctSchema_ID=M_Product_ACCT.C_AcctSchema_ID )
WHERE P_MethodChangeVariance_Acct IS NULL;

UPDATE M_Product_ACCT
SET P_UsageVariance_Acct  = (SELECT P_UsageVariance_Acct
                             FROM C_AcctSchema_Default AD
                                 WHERE AD.C_AcctSchema_ID=M_Product_ACCT.C_AcctSchema_ID )
WHERE P_UsageVariance_Acct IS NULL;

UPDATE M_Product_ACCT
SET P_RateVariance_Acct   = (SELECT P_RateVariance_Acct
                             FROM C_AcctSchema_Default AD
                                 WHERE AD.C_AcctSchema_ID=M_Product_ACCT.C_AcctSchema_ID )
WHERE P_RateVariance_Acct IS NULL;

UPDATE M_Product_ACCT
SET P_MixVariance_Acct   = (SELECT P_MixVariance_Acct
                             FROM C_AcctSchema_Default AD
                                 WHERE AD.C_AcctSchema_ID=M_Product_ACCT.C_AcctSchema_ID )
WHERE P_MixVariance_Acct IS NULL;

UPDATE M_Product_ACCT
SET P_Labor_Acct    = (SELECT P_Labor_Acct
                             FROM C_AcctSchema_Default AD
                                 WHERE AD.C_AcctSchema_ID=M_Product_ACCT.C_AcctSchema_ID )
WHERE P_Labor_Acct IS NULL;

UPDATE M_Product_ACCT
SET P_Burden_Acct     = (SELECT P_Burden_Acct
                             FROM C_AcctSchema_Default AD
                                 WHERE AD.C_AcctSchema_ID=M_Product_ACCT.C_AcctSchema_ID )
WHERE P_Burden_Acct IS NULL;

UPDATE M_Product_ACCT
SET P_CostOfProduction_Acct      = (SELECT P_CostOfProduction_Acct
                             FROM C_AcctSchema_Default AD
                                 WHERE AD.C_AcctSchema_ID=M_Product_ACCT.C_AcctSchema_ID )
WHERE P_CostOfProduction_Acct IS NULL;

UPDATE M_Product_ACCT
SET P_OutsideProcessing_Acct      = (SELECT P_OutsideProcessing_Acct
                             FROM C_AcctSchema_Default AD
                                 WHERE AD.C_AcctSchema_ID=M_Product_ACCT.C_AcctSchema_ID )
WHERE P_OutsideProcessing_Acct IS NULL;

UPDATE M_Product_ACCT
SET P_Overhead_Acct        = (SELECT P_Overhead_Acct
                             FROM C_AcctSchema_Default AD
                                 WHERE AD.C_AcctSchema_ID=M_Product_ACCT.C_AcctSchema_ID )
WHERE P_Overhead_Acct IS NULL;

UPDATE M_Product_ACCT
SET P_Scrap_Acct      = (SELECT P_Scrap_Acct
                             FROM C_AcctSchema_Default AD
                                 WHERE AD.C_AcctSchema_ID=M_Product_ACCT.C_AcctSchema_ID )
WHERE P_Scrap_Acct IS NULL;
