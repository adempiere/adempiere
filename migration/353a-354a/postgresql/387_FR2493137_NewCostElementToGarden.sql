-- Jan 7, 2009 10:39:06 AM ECT
-- Period 2009
INSERT INTO M_CostElement (AD_Client_ID,AD_Org_ID,CostElementType,Created,CreatedBy,IsActive,IsCalculated,M_CostElement_ID,Name,Updated,UpdatedBy) VALUES (11,0,'B',TO_TIMESTAMP('2009-01-07 10:38:57','YYYY-MM-DD HH24:MI:SS'),100,'Y','N',50000,'Burden',TO_TIMESTAMP('2009-01-07 10:38:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 10:40:57 AM ECT
-- Create new Cost Element for Manufacturing
UPDATE M_CostElement SET AD_Org_ID=0, Name='Labor',Updated=TO_TIMESTAMP('2009-01-07 10:40:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_CostElement_ID=105
;

-- Jan 7, 2009 10:47:06 AM ECT
-- Create new Cost Element for Manufacturing
UPDATE M_CostElement SET CostingMethod='S',Updated=TO_TIMESTAMP('2009-01-07 10:47:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_CostElement_ID=105
;

-- Jan 7, 2009 10:47:14 AM ECT
-- Create new Cost Element for Manufacturing
UPDATE M_CostElement SET CostingMethod='S',Updated=TO_TIMESTAMP('2009-01-07 10:47:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_CostElement_ID=50000
;

-- Jan 7, 2009 10:47:33 AM ECT
-- Create new Cost Element for Manufacturing
UPDATE M_CostElement SET Name='Material',Updated=TO_TIMESTAMP('2009-01-07 10:47:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_CostElement_ID=100
;

-- Jan 7, 2009 10:48:05 AM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_CostElement (AD_Client_ID,AD_Org_ID,CostElementType,CostingMethod,Created,CreatedBy,IsActive,IsCalculated,M_CostElement_ID,Name,Updated,UpdatedBy) VALUES (11,0,'O','S',TO_TIMESTAMP('2009-01-07 10:48:04','YYYY-MM-DD HH24:MI:SS'),100,'Y','N',50001,'Overhead',TO_TIMESTAMP('2009-01-07 10:48:04','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 10:48:33 AM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_CostElement (AD_Client_ID,AD_Org_ID,CostElementType,CostingMethod,Created,CreatedBy,IsActive,IsCalculated,M_CostElement_ID,Name,Updated,UpdatedBy) VALUES (11,0,'X','S',TO_TIMESTAMP('2009-01-07 10:48:32','YYYY-MM-DD HH24:MI:SS'),100,'Y','N',50002,'Outside Processing',TO_TIMESTAMP('2009-01-07 10:48:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 10:49:11 AM ECT
-- Create new Cost Element for Manufacturing
UPDATE M_CostElement SET Description='Standard Cost',Updated=TO_TIMESTAMP('2009-01-07 10:49:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_CostElement_ID=50000
;

-- Jan 7, 2009 10:49:13 AM ECT
-- Create new Cost Element for Manufacturing
UPDATE M_CostElement SET Description='Standard Cost',Updated=TO_TIMESTAMP('2009-01-07 10:49:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_CostElement_ID=105
;

-- Jan 7, 2009 10:49:14 AM ECT
-- Create new Cost Element for Manufacturing
UPDATE M_CostElement SET Description='Standard Cost',Updated=TO_TIMESTAMP('2009-01-07 10:49:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_CostElement_ID=50001
;

-- Jan 7, 2009 10:49:24 AM ECT
-- Create new Cost Element for Manufacturing
UPDATE M_CostElement SET Description='Standard Cost',Updated=TO_TIMESTAMP('2009-01-07 10:49:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_CostElement_ID=50002
;

-- Jan 7, 2009 10:49:39 AM ECT
-- Create new Cost Element for Manufacturing
UPDATE M_CostElement SET Description='Standard Cost 

Note: Define additional (Material) Costing Method, if you want to maintain/calculate the costs.  For accounting, the costing method defined in the Accounting Schema or Product Category Acct is used.',Updated=TO_TIMESTAMP('2009-01-07 10:49:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_CostElement_ID=100
;
-- Jan 7, 2009 12:14:08 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:08','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,100,100,50007,TO_TIMESTAMP('2009-01-07 12:14:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:08 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:08','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,100,100,50004,TO_TIMESTAMP('2009-01-07 12:14:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:08 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:08','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,100,100,50005,TO_TIMESTAMP('2009-01-07 12:14:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:08 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:08','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,100,100,50002,TO_TIMESTAMP('2009-01-07 12:14:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:08 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:08','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,100,100,50000,TO_TIMESTAMP('2009-01-07 12:14:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:08 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:08','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,100,100,50003,TO_TIMESTAMP('2009-01-07 12:14:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:08 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:08','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,100,100,50001,TO_TIMESTAMP('2009-01-07 12:14:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:08 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:08','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,100,100,50016,TO_TIMESTAMP('2009-01-07 12:14:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:08 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:08','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,100,100,50010,TO_TIMESTAMP('2009-01-07 12:14:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:08 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:08','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,100,100,50009,TO_TIMESTAMP('2009-01-07 12:14:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:08 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:08','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,100,100,50012,TO_TIMESTAMP('2009-01-07 12:14:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:08 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:08','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,100,100,50017,TO_TIMESTAMP('2009-01-07 12:14:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:08 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:08','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,100,100,50015,TO_TIMESTAMP('2009-01-07 12:14:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:08 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:08','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,100,100,50008,TO_TIMESTAMP('2009-01-07 12:14:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:08 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:08','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,100,100,50014,TO_TIMESTAMP('2009-01-07 12:14:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:08 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:08','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,100,100,50013,TO_TIMESTAMP('2009-01-07 12:14:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:08 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:08','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,100,100,50024,TO_TIMESTAMP('2009-01-07 12:14:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:08 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:08','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,100,100,50018,TO_TIMESTAMP('2009-01-07 12:14:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:08 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:08','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,100,100,50019,TO_TIMESTAMP('2009-01-07 12:14:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:08 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:08','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,100,100,50020,TO_TIMESTAMP('2009-01-07 12:14:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:08 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:08','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,100,100,50021,TO_TIMESTAMP('2009-01-07 12:14:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:08 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:08','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,100,100,50022,TO_TIMESTAMP('2009-01-07 12:14:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:08 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:08','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,100,100,50023,TO_TIMESTAMP('2009-01-07 12:14:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:08 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:08','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,100,100,50025,TO_TIMESTAMP('2009-01-07 12:14:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:08 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:08','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,100,100,50026,TO_TIMESTAMP('2009-01-07 12:14:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:08 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:08','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,100,100,50027,TO_TIMESTAMP('2009-01-07 12:14:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:26 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50007,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:26 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,134,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:26 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50004,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:26 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50005,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:26 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50002,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:26 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,137,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:26 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50000,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:26 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50003,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:26 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50001,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:26 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,122,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:26 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,123,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:26 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,124,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:26 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,125,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:26 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,126,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:26 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,127,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:26 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,128,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:26 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,129,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:26 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,130,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:26 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,131,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:26 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,132,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:26 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,135,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:26 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,138,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:26 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,139,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:26 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,140,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:26 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,141,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:26 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,142,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:26 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,143,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:26 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,144,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:26 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,133,TO_TIMESTAMP('2009-01-07 12:14:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,146,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,147,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,148,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,145,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50016,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50010,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50009,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50012,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50017,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50015,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50008,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50014,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,136,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50013,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50024,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50018,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50019,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50020,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50021,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50022,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50023,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50025,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50026,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50027,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,11,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50007,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,11,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,134,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,11,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50004,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,11,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50005,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,11,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50002,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,11,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,137,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,11,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50000,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,11,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50003,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,11,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50001,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,11,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,122,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,11,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,123,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,11,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,124,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,11,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,125,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,11,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,126,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,11,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,127,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,11,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,128,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,11,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,129,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,11,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,130,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,11,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,131,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,11,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,132,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,11,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,135,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,11,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,138,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,11,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,139,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,11,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,140,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,11,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,141,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,11,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,142,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,11,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,143,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,11,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,144,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,11,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,133,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,11,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,146,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,11,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,147,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,11,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,148,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,11,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,145,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,11,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50016,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,11,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50010,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,11,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50009,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,11,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50012,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,11,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50017,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,11,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50015,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,11,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50008,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,11,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50014,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,11,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,136,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,11,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50013,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,11,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50024,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,11,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50018,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,11,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50019,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,11,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50020,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,11,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50021,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,11,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50022,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,11,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50023,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,11,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50025,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,11,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50026,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,11,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50027,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50000,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50007,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50000,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,134,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50000,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50004,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50000,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50005,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50000,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50002,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50000,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,137,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50000,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50000,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50000,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50003,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50000,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50001,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50000,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,122,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50000,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,123,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50000,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,124,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:27 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50000,101,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,125,TO_TIMESTAMP('2009-01-07 12:14:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50000,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,126,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50000,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,127,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50000,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,128,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50000,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,129,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50000,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,130,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50000,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,131,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50000,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,132,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50000,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,135,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50000,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,138,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50000,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,139,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50000,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,140,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50000,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,141,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50000,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,142,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50000,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,143,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50000,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,144,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50000,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,133,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50000,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,146,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50000,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,147,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50000,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,148,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50000,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,145,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50000,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50016,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50000,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50010,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50000,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50009,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50000,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50012,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50000,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50017,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50000,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50015,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50000,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50008,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50000,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50014,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50000,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,136,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50000,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50013,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50000,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50024,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50000,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50018,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50000,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50019,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50000,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50020,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50000,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50021,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50000,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50022,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50000,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50023,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50000,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50025,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50000,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50026,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50000,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50027,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50001,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50007,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50001,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,134,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50001,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50004,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50001,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50005,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50001,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50002,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50001,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,137,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50001,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50000,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50001,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50003,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50001,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50001,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50001,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,122,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50001,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,123,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50001,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,124,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50001,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,125,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50001,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,126,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50001,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,127,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50001,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,128,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50001,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,129,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50001,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,130,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50001,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,131,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50001,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,132,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50001,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,135,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50001,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,138,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50001,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,139,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50001,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,140,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50001,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,141,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50001,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,142,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50001,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,143,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50001,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,144,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50001,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,133,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50001,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,146,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50001,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,147,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50001,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,148,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50001,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,145,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50001,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50016,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50001,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50010,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50001,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50009,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50001,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50012,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50001,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50017,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50001,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50015,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50001,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50008,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50001,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50014,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50001,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,136,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50001,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50013,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50001,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50024,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50001,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50018,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50001,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50019,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50001,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50020,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50001,101,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50021,TO_TIMESTAMP('2009-01-07 12:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50001,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50022,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50001,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50023,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50001,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50025,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50001,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50026,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50001,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50027,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50002,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50007,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50002,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,134,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50002,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50004,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50002,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50005,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50002,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50002,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50002,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,137,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50002,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50000,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50002,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50003,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50002,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50001,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50002,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,122,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50002,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,123,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50002,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,124,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50002,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,125,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50002,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,126,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50002,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,127,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50002,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,128,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50002,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,129,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50002,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,130,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50002,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,131,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50002,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,132,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50002,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,135,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50002,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,138,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50002,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,139,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50002,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,140,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50002,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,141,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50002,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,142,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50002,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,143,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50002,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,144,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50002,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,133,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50002,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,146,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50002,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,147,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50002,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,148,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50002,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,145,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50002,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50016,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50002,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50010,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50002,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50009,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50002,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50012,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50002,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50017,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50002,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50015,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50002,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50008,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50002,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50014,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50002,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,136,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50002,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50013,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50002,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50024,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50002,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50018,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50002,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50019,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50002,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50020,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50002,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50021,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50002,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50022,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50002,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50023,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50002,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50025,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50002,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50026,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50002,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50027,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50004,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50007,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50004,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,134,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50004,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50004,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50004,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50005,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50004,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50002,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50004,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,137,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50004,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50000,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50004,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50003,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50004,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50001,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50004,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,122,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50004,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,123,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50004,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,124,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50004,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,125,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50004,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,126,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50004,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,127,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50004,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,128,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50004,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,129,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50004,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,130,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50004,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,131,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50004,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,132,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50004,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,135,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50004,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,138,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50004,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,139,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50004,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,140,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50004,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,141,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50004,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,142,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50004,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,143,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50004,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,144,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50004,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,133,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50004,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,146,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50004,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,147,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50004,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,148,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50004,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,145,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50004,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50016,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50004,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50010,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50004,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50009,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50004,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50012,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50004,101,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50017,TO_TIMESTAMP('2009-01-07 12:14:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50004,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50015,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50004,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50008,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50004,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50014,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50004,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,136,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50004,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50013,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50004,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50024,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50004,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50018,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50004,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50019,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50004,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50020,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50004,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50021,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50004,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50022,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50004,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50023,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50004,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50025,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50004,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50026,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50004,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50027,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50005,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50007,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50005,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,134,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50005,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50004,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50005,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50005,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50005,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50002,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50005,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,137,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50005,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50000,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50005,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50003,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50005,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50001,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50005,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,122,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50005,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,123,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50005,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,124,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50005,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,125,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50005,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,126,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50005,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,127,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50005,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,128,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50005,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,129,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50005,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,130,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50005,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,131,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50005,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,132,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50005,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,135,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50005,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,138,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50005,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,139,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50005,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,140,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50005,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,141,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50005,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,142,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50005,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,143,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50005,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,144,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50005,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,133,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50005,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,146,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50005,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,147,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50005,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,148,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50005,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,145,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50005,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50016,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50005,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50010,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50005,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50009,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50005,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50012,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50005,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50017,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50005,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50015,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50005,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50008,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50005,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50014,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50005,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,136,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50005,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50013,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50005,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50024,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50005,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50018,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50005,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50019,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50005,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50020,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50005,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50021,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50005,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50022,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50005,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50023,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50005,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50025,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50005,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50026,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50005,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50027,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50006,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50007,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50006,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,134,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50006,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50004,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50006,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50005,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50006,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50002,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50006,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,137,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50006,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50000,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50006,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50003,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50006,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50001,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50006,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,122,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50006,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,123,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50006,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,124,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50006,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,125,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50006,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,126,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50006,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,127,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50006,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,128,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50006,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,129,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50006,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,130,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50006,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,131,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50006,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,132,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50006,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,135,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50006,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,138,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50006,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,139,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50006,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,140,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50006,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,141,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:30 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50006,101,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,142,TO_TIMESTAMP('2009-01-07 12:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50006,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,143,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50006,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,144,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50006,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,133,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50006,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,146,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50006,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,147,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50006,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,148,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50006,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,145,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50006,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50016,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50006,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50010,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50006,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50009,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50006,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50012,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50006,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50017,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50006,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50015,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50006,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50008,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50006,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50014,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50006,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,136,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50006,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50013,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50006,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50024,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50006,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50018,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50006,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50019,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50006,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50020,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50006,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50021,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50006,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50022,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50006,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50023,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50006,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50025,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50006,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50026,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50006,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50027,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,12,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50007,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,12,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,134,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,12,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50004,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,12,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50005,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,12,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50002,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,12,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,137,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,12,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50000,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,12,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50003,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,12,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50001,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,12,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,122,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,12,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,123,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,12,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,124,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,12,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,125,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,12,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,126,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,12,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,127,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,12,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,128,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,12,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,129,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,12,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,130,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,12,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,131,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,12,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,132,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,12,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,135,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,12,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,138,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,12,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,139,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,12,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,140,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,12,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,141,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,12,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,142,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,12,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,143,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,12,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,144,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,12,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,133,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,12,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,146,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,12,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,147,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,12,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,148,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,12,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,145,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,12,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50016,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,12,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50010,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,12,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50009,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,12,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50012,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,12,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50017,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,12,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50015,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,12,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50008,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,12,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50014,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,12,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,136,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,12,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50013,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,12,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50024,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,12,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50018,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,12,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50019,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,12,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50020,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,12,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50021,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,12,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50022,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,12,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50023,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,12,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50025,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,12,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50026,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,12,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50027,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50007,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50007,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50007,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,134,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50007,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50004,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50007,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50005,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:31 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50007,101,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50002,TO_TIMESTAMP('2009-01-07 12:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:32 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50007,101,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,137,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:32 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50007,101,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50000,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:32 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50007,101,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50003,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:32 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50007,101,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50001,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:32 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50007,101,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,122,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:32 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50007,101,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,123,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:32 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50007,101,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,124,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:32 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50007,101,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,125,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:32 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50007,101,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,126,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:32 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50007,101,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,127,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:32 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50007,101,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,128,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:32 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50007,101,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,129,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:32 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50007,101,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,130,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:32 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50007,101,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,131,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:32 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50007,101,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,132,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:32 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50007,101,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,135,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:32 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50007,101,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,138,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:32 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50007,101,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,139,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:32 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50007,101,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,140,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:32 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50007,101,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,141,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:32 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50007,101,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,142,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:32 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50007,101,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,143,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:32 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50007,101,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,144,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:32 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50007,101,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,133,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:32 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50007,101,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,146,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:32 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50007,101,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,147,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:32 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50007,101,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,148,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:32 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50007,101,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,145,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:32 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50007,101,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50016,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:32 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50007,101,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50010,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:32 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50007,101,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50009,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:32 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50007,101,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50012,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:32 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50007,101,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50017,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:32 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50007,101,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50015,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:32 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50007,101,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50008,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:32 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50007,101,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50014,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:32 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50007,101,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,136,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:32 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50007,101,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50013,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:32 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50007,101,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50024,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:32 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50007,101,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50018,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:32 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50007,101,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50019,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:32 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50007,101,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50020,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:32 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50007,101,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50021,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:32 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50007,101,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50022,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:32 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50007,101,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50023,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:32 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50007,101,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50025,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:32 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50007,101,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50026,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:14:32 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,50007,101,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,105,100,50027,TO_TIMESTAMP('2009-01-07 12:14:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:06 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50000,100,50007,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:06 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50000,100,134,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:06 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50000,100,50004,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:06 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50000,100,50005,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:06 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50000,100,50002,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:06 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50000,100,137,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:06 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50000,100,50000,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:06 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50000,100,50003,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:06 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50000,100,50001,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:06 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50000,100,122,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:06 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50000,100,123,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:06 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50000,100,124,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:06 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50000,100,125,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:06 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50000,100,126,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:06 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50000,100,127,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:06 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50000,100,128,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:06 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50000,100,129,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:06 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50000,100,130,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:06 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50000,100,131,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:06 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50000,100,132,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:06 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50000,100,135,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:06 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50000,100,138,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:06 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50000,100,139,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:06 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50000,100,140,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:06 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50000,100,141,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:06 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50000,100,142,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:06 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50000,100,143,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:06 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50000,100,144,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:06 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50000,100,133,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:06 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50000,100,146,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:06 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50000,100,147,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:06 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50000,100,148,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:06 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50000,100,145,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:06 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50000,100,50016,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:06 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50000,100,50010,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:06 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50000,100,50009,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:06 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50000,100,50012,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:06 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50000,100,50017,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:06 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50000,100,50015,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:06 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50000,100,50008,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:06 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50000,100,50014,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:06 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50000,100,136,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:06 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50000,100,50013,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:06 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50000,100,50024,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:06 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50000,100,50018,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:06 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50000,100,50019,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:06 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50000,100,50020,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:06 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50000,100,50021,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:06 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50000,100,50022,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:06 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50000,100,50023,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:06 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50000,100,50025,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:06 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50000,100,50026,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:06 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50000,100,50027,TO_TIMESTAMP('2009-01-07 12:15:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50001,100,50007,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50001,100,134,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50001,100,50004,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50001,100,50005,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50001,100,50002,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50001,100,137,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50001,100,50000,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50001,100,50003,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50001,100,50001,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50001,100,122,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50001,100,123,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50001,100,124,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50001,100,125,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50001,100,126,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50001,100,127,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50001,100,128,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50001,100,129,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50001,100,130,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50001,100,131,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50001,100,132,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50001,100,135,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50001,100,138,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50001,100,139,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50001,100,140,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50001,100,141,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50001,100,142,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50001,100,143,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50001,100,144,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50001,100,133,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50001,100,146,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50001,100,147,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50001,100,148,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50001,100,145,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50001,100,50016,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50001,100,50010,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50001,100,50009,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50001,100,50012,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50001,100,50017,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50001,100,50015,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50001,100,50008,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50001,100,50014,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50001,100,136,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50001,100,50013,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50001,100,50024,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:28 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50001,100,50018,TO_TIMESTAMP('2009-01-07 12:15:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50001,100,50019,TO_TIMESTAMP('2009-01-07 12:15:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50001,100,50020,TO_TIMESTAMP('2009-01-07 12:15:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50001,100,50021,TO_TIMESTAMP('2009-01-07 12:15:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50001,100,50022,TO_TIMESTAMP('2009-01-07 12:15:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50001,100,50023,TO_TIMESTAMP('2009-01-07 12:15:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50001,100,50025,TO_TIMESTAMP('2009-01-07 12:15:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50001,100,50026,TO_TIMESTAMP('2009-01-07 12:15:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:29 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50001,100,50027,TO_TIMESTAMP('2009-01-07 12:15:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:46 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50002,100,50007,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:46 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50002,100,134,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:46 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50002,100,50004,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:46 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50002,100,50005,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:46 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50002,100,50002,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:46 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50002,100,137,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:46 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50002,100,50000,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:46 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50002,100,50003,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:46 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50002,100,50001,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:46 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50002,100,122,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:46 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50002,100,123,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:46 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50002,100,124,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:46 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50002,100,125,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:46 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50002,100,126,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:46 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50002,100,127,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:46 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50002,100,128,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:46 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50002,100,129,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:46 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50002,100,130,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:46 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50002,100,131,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:46 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50002,100,132,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:46 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50002,100,135,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:46 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50002,100,138,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:46 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50002,100,139,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:46 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50002,100,140,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:46 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50002,100,141,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:46 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50002,100,142,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:46 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50002,100,143,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:46 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50002,100,144,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:46 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50002,100,133,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:46 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50002,100,146,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:46 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50002,100,147,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:46 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50002,100,148,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:46 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50002,100,145,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:46 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50002,100,50016,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:46 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50002,100,50010,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:46 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50002,100,50009,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:46 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50002,100,50012,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:46 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50002,100,50017,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:46 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50002,100,50015,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:46 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50002,100,50008,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:46 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50002,100,50014,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:46 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50002,100,136,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:46 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50002,100,50013,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:46 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50002,100,50024,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:46 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50002,100,50018,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:46 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50002,100,50019,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:46 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50002,100,50020,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:46 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50002,100,50021,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:46 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50002,100,50022,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:46 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50002,100,50023,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:46 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50002,100,50025,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:46 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50002,100,50026,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:15:46 PM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO M_Cost (AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Created,CreatedBy,CumulatedAmt,CumulatedQty,CurrentCostPrice,CurrentQty,FutureCostPrice,IsActive,M_AttributeSetInstance_ID,M_CostElement_ID,M_CostType_ID,M_Product_ID,Updated,UpdatedBy) VALUES (11,0,101,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0,0,'Y',0,50002,100,50027,TO_TIMESTAMP('2009-01-07 12:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 7, 2009 12:26:32 PM ECT
-- Create new Cost Element for Manufacturing
UPDATE M_Product SET Name='Assembly Front Leg', Value='PFrontLeg',Updated=TO_TIMESTAMP('2009-01-07 12:26:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50001
;

-- Jan 7, 2009 12:26:32 PM ECT
-- Create new Cost Element for Manufacturing
UPDATE M_Product_Trl SET Description=NULL,DocumentNote=NULL,Name='Assembly Front Leg',IsTranslated='Y' WHERE M_Product_ID=50001
;

-- Jan 7, 2009 12:26:32 PM ECT
-- Create new Cost Element for Manufacturing
UPDATE A_Asset SET Name=SUBSTR((SELECT bp.Name FROM C_BPartner bp WHERE bp.C_BPartner_ID=A_Asset.C_BPartner_ID) || ' - ' || p.Name,1,60),Description=p.Description FROM M_Product p WHERE p.M_Product_ID=A_Asset.M_Product_ID AND A_Asset.IsActive='Y' AND A_Asset.M_Product_ID=50001
;

-- Jan 7, 2009 12:27:13 PM ECT
-- Create new Cost Element for Manufacturing
UPDATE PP_Product_BOM SET Name='Assembly Front Leg', Value='PFrontLeg',Updated=TO_TIMESTAMP('2009-01-07 12:27:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_BOM_ID=50003
;

DELETE FROM M_Cost WHERE AD_Org_ID >0 AND AD_Client_ID < 1000000
;
