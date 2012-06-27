-- Sep 3, 2009 8:48:57 PM ECT
-- Warehouse Management
INSERT INTO AD_EntityType (AD_Client_ID,AD_EntityType_ID,AD_Org_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,ModelPackage,Name,Processing,Updated,UpdatedBy,Version) 
VALUES (0,50007,0,TO_TIMESTAMP('2009-09-03 20:48:57','YYYY-MM-DD HH24:MI:SS'),0,'e-Evolution Warehouse Management','EE03','Project
http://www.adempiere.com/index.php/Sponsored_Development:_Libero_Warehouse_Management
Sponsored Development www.e-evolution.com','Y','org.eevolution.model','e-Evolution Warehouse Management','N',TO_TIMESTAMP('2009-09-03 20:48:57','YYYY-MM-DD HH24:MI:SS'),0,'1.00')
;

-- Sep 3, 2009 8:46:19 PM ECT
-- Warehouse Management
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) 
VALUES (0,0,53507,183,TO_TIMESTAMP('2009-09-03 20:46:19','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','Warehouse Management Order',TO_TIMESTAMP('2009-09-03 20:46:19','YYYY-MM-DD HH24:MI:SS'),0,'WMO');

-- Sep 3, 2009 8:49:16 PM ECT
-- Warehouse Management
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) 
SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53507 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Sep 3, 2009 8:49:16 PM ECT
-- Warehouse Management
INSERT INTO AD_Val_Rule (AD_Client_ID,AD_Org_ID,AD_Val_Rule_ID,Code,Created,CreatedBy,Description,EntityType,IsActive,Name,Type,Updated,UpdatedBy) 
VALUES (0,0,52062,'C_DocType.DocBaseType IN (''WMO'') AND C_DocType.IsSOTrx=''N''',TO_TIMESTAMP('2009-09-03 20:49:16','YYYY-MM-DD HH24:MI:SS'),0,'Document Type Warehouse Management','EE03','Y','C_DocType Inbound','S',TO_TIMESTAMP('2009-09-03 20:49:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 3, 2009 8:49:16 PM ECT
-- Warehouse Management
INSERT INTO AD_Val_Rule (AD_Client_ID,AD_Org_ID,AD_Val_Rule_ID,Code,Created,CreatedBy,Description,EntityType,IsActive,Name,Type,Updated,UpdatedBy) 
VALUES (0,0,52060,'C_DocType.DocBaseType IN (''WMO'') AND C_DocType.IsSOTrx=''Y''',TO_TIMESTAMP('2009-09-03 20:49:16','YYYY-MM-DD HH24:MI:SS'),0,'Document Type Warehouse Management','EE03','Y','C_DocType Outbound','S',TO_TIMESTAMP('2009-09-03 20:49:16','YYYY-MM-DD HH24:MI:SS'),0)
;
