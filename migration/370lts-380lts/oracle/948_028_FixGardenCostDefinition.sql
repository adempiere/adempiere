SET DEFINE OFF
SET SQLBLANKLINES ON
SET SCAN OFF

-- Oct 15, 2010 11:23:55 AM CDT
-- Cost Engine
UPDATE M_CostElement SET AD_Org_ID=0, CostElementType='L', IsCalculated ='Y',Updated=TO_DATE('2010-10-15 11:23:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_CostElement_ID=101
;

UPDATE M_CostElement SET IsDefault='N' , Updated=TO_DATE('2010-10-15 11:30:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Client_ID=11;

-- Oct 15, 2010 11:30:44 AM CDT
-- Cost Engine
UPDATE M_CostElement SET IsDefault='Y' , Updated=TO_DATE('2010-10-15 11:30:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_CostElement_ID=100
;
