-- 8/05/2008 16:30:23
-- Generated_Order_ID
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53462,0,'Link_Order_ID',TO_DATE('2008-05-08 16:30:19','YYYY-MM-DD HH24:MI:SS'),100,'This field links a sales order to the purchase order that is generated from it.','D','Y','Linked Order','Linked Order',TO_DATE('2008-05-08 16:30:19','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 8/05/2008 16:30:23
-- Generated_Order_ID
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53462 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- 8/05/2008 16:31:41
-- Generated_Order_ID
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,55322,53462,0,18,290,259,'Link_Order_ID',TO_DATE('2008-05-08 16:31:38','YYYY-MM-DD HH24:MI:SS'),100,'This field links a sales order to the purchase order that is generated from it.','D',22,'Y','N','N','N','N','N','N','N','N','N','N','Linked Order',0,TO_DATE('2008-05-08 16:31:38','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 8/05/2008 16:31:41
-- Generated_Order_ID
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=55322 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 8/05/2008 16:31:53
-- Generated_Order_ID
ALTER TABLE C_Order ADD Link_Order_ID NUMBER(10)
;

-- 8/05/2008 16:58:30
-- Generated_Order_ID
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53463,0,'Link_OrderLine_ID',TO_DATE('2008-05-08 16:58:29','YYYY-MM-DD HH24:MI:SS'),100,'This field links a sales order line to the purchase order line that is generated from it.','D','Y','Linked Order Line','Linked Order Line',TO_DATE('2008-05-08 16:58:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 8/05/2008 16:58:30
-- Generated_Order_ID
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53463 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- 8/05/2008 16:59:18
-- Generated_Order_ID
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,55323,53463,0,18,271,260,'Link_OrderLine_ID',TO_DATE('2008-05-08 16:59:17','YYYY-MM-DD HH24:MI:SS'),100,'This field links a sales order line to the purchase order line that is generated from it.','D',22,'Y','N','N','N','N','N','N','N','N','N','N','Linked Order Line',0,TO_DATE('2008-05-08 16:59:17','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 8/05/2008 16:59:18
-- Generated_Order_ID
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=55323 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 8/05/2008 16:59:21
-- Generated_Order_ID
ALTER TABLE C_OrderLine ADD Link_OrderLine_ID NUMBER(10)
;

