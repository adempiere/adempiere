-- May 29, 2008 12:00:35 PM CDT
-- Fix Issue with Libero
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,55336,53311,0,19,53043,'DD_Order_ID',TO_TIMESTAMP('2008-05-29 12:00:33','YYYY-MM-DD HH24:MI:SS'),0,'EE01',22,'Y','N','N','N','N','N','N','N','N','N','Y','Distribution Order',0,TO_TIMESTAMP('2008-05-29 12:00:33','YYYY-MM-DD HH24:MI:SS'),0,1.000000000000)
;

-- May 29, 2008 12:00:35 PM CDT
-- Fix Issue with Libero
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=55336 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- May 29, 2008 12:00:40 PM CDT
-- Fix Issue with Libero
ALTER TABLE PP_MRP ADD COLUMN DD_Order_ID NUMERIC(10)
;

-- May 29, 2008 12:01:04 PM CDT
-- Fix Issue with Libero
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,55337,53313,0,19,53043,'DD_OrderLine_ID',TO_TIMESTAMP('2008-05-29 12:01:03','YYYY-MM-DD HH24:MI:SS'),0,'EE01',22,'Y','N','N','N','N','N','N','N','N','N','Y','Distribution Order Line',0,TO_TIMESTAMP('2008-05-29 12:01:03','YYYY-MM-DD HH24:MI:SS'),0,1.000000000000)
;

-- May 29, 2008 12:01:04 PM CDT
-- Fix Issue with Libero
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=55337 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- May 29, 2008 12:01:06 PM CDT
-- Fix Issue with Libero
ALTER TABLE PP_MRP ADD COLUMN DD_OrderLine_ID NUMERIC(10)
;

-- May 29, 2008 12:03:23 PM CDT
-- Fix Issue with Libero
UPDATE AD_Column SET AD_Element_ID=138, ColumnName='AD_User_ID', Description='User within the system - Internal or Business Partner Contact', Help='The User identifies a unique user in the system. This could be an internal user or a business partner contact', Name='User/Contact',Updated=TO_TIMESTAMP('2008-05-29 12:03:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53590
;

-- May 29, 2008 12:03:23 PM CDT
-- Fix Issue with Libero
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=53590
;

-- May 29, 2008 12:03:23 PM CDT
-- Fix Issue with Libero
UPDATE AD_Field SET Name='User/Contact', Description='User within the system - Internal or Business Partner Contact', Help='The User identifies a unique user in the system. This could be an internal user or a business partner contact' WHERE AD_Column_ID=53590 AND IsCentrallyMaintained='Y'
;

-- May 29, 2008 12:03:27 PM CDT
-- Fix Issue with Libero
ALTER TABLE PP_Order_BOMLine ADD COLUMN AD_User_ID NUMERIC(10)
;

-- May 29, 2008 12:03:27 PM CDT
-- Fix Issue with Libero
ALTER TABLE PP_Order_BOMLine DROP COLUMN UserID 
;

