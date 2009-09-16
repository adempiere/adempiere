-- Jan 7, 2009 10:45:11 AM ECT
-- Create new Cost Element for Manufacturing
UPDATE AD_Field SET DisplayLogic=NULL,Updated=TO_TIMESTAMP('2009-01-07 10:45:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=11288
;

-- Jan 7, 2009 11:42:56 AM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2019,0,53052,53266,35,'M_AttributeSetInstance_ID',TO_TIMESTAMP('2009-01-07 11:42:47','YYYY-MM-DD HH24:MI:SS'),0,'Product Attribute Set Instance','EE01',22,'The values of the actual Product Attribute Instances.  The product level attributes are defined on Product level.','Y','Y','N','N','Attribute Set Instance',70,TO_TIMESTAMP('2009-01-07 11:42:47','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jan 7, 2009 11:42:56 AM ECT
-- Create new Cost Element for Manufacturing
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53266 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jan 7, 2009 11:46:38 AM ECT
-- Create new Cost Element for Manufacturing
UPDATE AD_Process SET Description='Let create every cost elements defined for a Organization, Accounting Schema, Warehouse, Resource, Cost Type ,Product and Product Attribute Set Instance .',Updated=TO_TIMESTAMP('2009-01-07 11:46:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_ID=53052
;

-- Jan 7, 2009 11:46:38 AM ECT
-- Create new Cost Element for Manufacturing
UPDATE AD_Process_Trl SET IsTranslated='N' WHERE AD_Process_ID=53052
;

-- Jan 7, 2009 11:46:38 AM ECT
-- Create new Cost Element for Manufacturing
UPDATE AD_Menu SET Description='Let create every cost elements defined for a Organization, Accounting Schema, Warehouse, Resource, Cost Type ,Product and Product Attribute Set Instance .', IsActive='Y', Name='Create Element',Updated=TO_TIMESTAMP('2009-01-07 11:46:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Menu_ID=53076
;

-- Jan 7, 2009 11:46:38 AM ECT
-- Create new Cost Element for Manufacturing
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=53076
;

