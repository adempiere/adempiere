-- Sep 28, 2008 2:39:23 PM ICT
-- FR [ 1745154 ] Cost in Reversing Material Related Docs
INSERT INTO AD_Element (AD_Org_ID,AD_Element_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,AD_Client_ID,UpdatedBy) VALUES (0,53689,'ReversalLine_ID',TO_DATE('2008-09-28 14:39:22','YYYY-MM-DD HH24:MI:SS'),0,'Use to keep the reversal line ID for reversing costing purpose','D','Y','Reversal Line','Reversal Line',TO_DATE('2008-09-28 14:39:22','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 28, 2008 2:39:24 PM ICT
-- FR [ 1745154 ] Cost in Reversing Material Related Docs
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53689 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Sep 28, 2008 2:39:36 PM ICT
-- FR [ 1745154 ] Cost in Reversing Material Related Docs
INSERT INTO AD_Column (AD_Org_ID,AD_Element_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,AD_Client_ID,Version,AD_Column_ID,AD_Reference_Value_ID) VALUES (0,53689,18,320,'ReversalLine_ID',TO_DATE('2008-09-28 14:39:35','YYYY-MM-DD HH24:MI:SS'),0,'Use to keep the reversal line ID for reversing costing purpose','D',22,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Reversal Line',0,TO_DATE('2008-09-28 14:39:35','YYYY-MM-DD HH24:MI:SS'),0,0,1,56355,295)
;

-- Sep 28, 2008 2:39:36 PM ICT
-- FR [ 1745154 ] Cost in Reversing Material Related Docs
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56355 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 28, 2008 3:06:08 PM ICT
-- FR [ 1745154 ] Cost in Reversing Material Related Docs
INSERT INTO AD_Reference (AD_Org_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,AD_Client_ID,ValidationType) VALUES (0,53284,TO_DATE('2008-09-28 15:06:03','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','N','M_MovementLine',TO_DATE('2008-09-28 15:06:03','YYYY-MM-DD HH24:MI:SS'),0,0,'T')
;

-- Sep 28, 2008 3:06:08 PM ICT
-- FR [ 1745154 ] Cost in Reversing Material Related Docs
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53284 AND EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Reference_ID!=t.AD_Reference_ID)
;

-- Sep 28, 2008 3:06:43 PM ICT
-- FR [ 1745154 ] Cost in Reversing Material Related Docs
INSERT INTO AD_Ref_Table (AD_Display,AD_Key,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,IsValueDisplayed,Updated,UpdatedBy,AD_Client_ID,AD_Reference_ID,AD_Table_ID) VALUES (3820,3582,0,TO_DATE('2008-09-28 15:06:43','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','N',TO_DATE('2008-09-28 15:06:43','YYYY-MM-DD HH24:MI:SS'),0,0,53284,324)
;

-- Sep 28, 2008 3:08:12 PM ICT
-- FR [ 1745154 ] Cost in Reversing Material Related Docs
INSERT INTO AD_Column (AD_Org_ID,AD_Reference_Value_ID,AD_Element_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,AD_Client_ID,Version,AD_Column_ID) VALUES (0,53284,53689,18,324,'ReversalLine_ID',TO_DATE('2008-09-28 15:08:11','YYYY-MM-DD HH24:MI:SS'),0,'Use to keep the reversal line ID for reversing costing purpose','D',22,'Y','Y','N','N','N','N','N','N','N','N','Y','N','Y','Reversal Line',TO_DATE('2008-09-28 15:08:11','YYYY-MM-DD HH24:MI:SS'),0,0,1,56356)
;

-- Sep 28, 2008 3:08:12 PM ICT
-- FR [ 1745154 ] Cost in Reversing Material Related Docs
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56356 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 28, 2008 3:09:10 PM ICT
-- FR [ 1745154 ] Cost in Reversing Material Related Docs
INSERT INTO AD_Column (AD_Org_ID,AD_Reference_Value_ID,AD_Element_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,AD_Client_ID,Version,AD_Column_ID) VALUES (0,296,53689,18,322,'ReversalLine_ID',TO_DATE('2008-09-28 15:09:08','YYYY-MM-DD HH24:MI:SS'),0,'Use to keep the reversal line ID for reversing costing purpose','D',22,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Reversal Line',TO_DATE('2008-09-28 15:09:08','YYYY-MM-DD HH24:MI:SS'),0,0,1,56357)
;

-- Sep 28, 2008 3:09:10 PM ICT
-- FR [ 1745154 ] Cost in Reversing Material Related Docs
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56357 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 28, 2008 3:14:43 PM ICT
-- FR [ 1745154 ] Cost in Reversing Material Related Docs
ALTER TABLE M_InOutLine ADD ReversalLine_ID NUMBER(10)
;

-- Sep 28, 2008 3:15:02 PM ICT
-- FR [ 1745154 ] Cost in Reversing Material Related Docs
ALTER TABLE M_MovementLine ADD ReversalLine_ID NUMBER(10)
;

-- Sep 28, 2008 3:15:18 PM ICT
-- FR [ 1745154 ] Cost in Reversing Material Related Docs
ALTER TABLE M_InventoryLine ADD ReversalLine_ID NUMBER(10)
;

