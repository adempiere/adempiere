-- Jun 20, 2008 6:33:56 PM CDT
-- Fixed Libero Issue
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,53354,0,53010,53184,29,'SafetyStock',TO_DATE('2008-06-20 18:33:47','YYYY-MM-DD HH24:MI:SS'),0,'Safety Stock Qty','EE01',22,'Safety stock is defined as extra units of inventory carried as protection against possible stockouts. It is held when an organization cannot accurately predict demand and/or lead time for the product.','Y','Y','N','N','Safety Stock Qty',130,TO_DATE('2008-06-20 18:33:47','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 20, 2008 6:33:56 PM CDT
-- Fixed Libero Issue
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53184 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jun 20, 2008 6:34:14 PM CDT
-- Fixed Libero Issue
UPDATE AD_Process_Para SET SeqNo=105,Updated=TO_DATE('2008-06-20 18:34:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53184
;

ALTER TABLE PP_Product_Planning DROP CONSTRAINT ddnetworkdistribution_ppproduc;
ALTER TABLE PP_Product_Planning DROP CONSTRAINT adworkflow_ppproductplanning;
ALTER TABLE PP_Product_Planning DROP CONSTRAINT mwarehouse_ppproductplanning;
ALTER TABLE PP_Product_Planning DROP CONSTRAINT planner_ppproductplanning;
ALTER TABLE PP_Product_Planning DROP CONSTRAINT ppproductbom_ppproductplanning;
ALTER TABLE PP_Product_Planning DROP CONSTRAINT sresource_ppproductplanning;

DROP INDEX pp_product_planning_uq;
