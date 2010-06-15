-- Jun 3, 2010 9:40:58 AM CEST
-- FR [3010957] - Custom Separator Character
-- http://sourceforge.net/tracker/?func=detail&aid=3010957&group_id=176962&atid=879335
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Reference_ID,AD_Ref_List_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,209,53583,TO_DATE('2010-06-03 09:40:57','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Custom Separator Char',TO_DATE('2010-06-03 09:40:57','YYYY-MM-DD HH24:MI:SS'),100,'U')
;

-- Jun 3, 2010 9:40:58 AM CEST
-- FR [3010957] - Custom Separator Character
-- http://sourceforge.net/tracker/?func=detail&aid=3010957&group_id=176962&atid=879335
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53583 AND NOT EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Ref_List_ID=t.AD_Ref_List_ID)
;

-- Jun 3, 2010 10:06:57 AM CEST
-- FR [3010957] - Custom Separator Character
-- http://sourceforge.net/tracker/?func=detail&aid=3010957&group_id=176962&atid=879335
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,54158,0,'SeparatorChar',TO_DATE('2010-06-03 10:06:56','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Separator Character','Separator Character',TO_DATE('2010-06-03 10:06:56','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 3, 2010 10:06:57 AM CEST
-- FR [3010957] - Custom Separator Character
-- http://sourceforge.net/tracker/?func=detail&aid=3010957&group_id=176962&atid=879335
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54158 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Jun 3, 2010 10:08:18 AM CEST
-- FR [3010957] - Custom Separator Character
-- http://sourceforge.net/tracker/?func=detail&aid=3010957&group_id=176962&atid=879335
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,59193,54158,0,10,381,'SeparatorChar',TO_DATE('2010-06-03 10:08:17','YYYY-MM-DD HH24:MI:SS'),100,'U',1,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Separator Character',0,TO_DATE('2010-06-03 10:08:17','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 3, 2010 10:08:18 AM CEST
-- FR [3010957] - Custom Separator Character
-- http://sourceforge.net/tracker/?func=detail&aid=3010957&group_id=176962&atid=879335
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59193 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 3, 2010 11:26:37 AM CEST
-- FR [3010957] - Custom Separator Character
-- http://sourceforge.net/tracker/?func=detail&aid=3010957&group_id=176962&atid=879335
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,DisplayLogic,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59193,58882,0,315,TO_DATE('2010-06-03 11:26:36','YYYY-MM-DD HH24:MI:SS'),100,0,'@FormatType@=''U''','D','Y','Y','Y','N','N','N','N','N','Separator Character',100,0,TO_DATE('2010-06-03 11:26:36','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 3, 2010 11:26:37 AM CEST
-- FR [3010957] - Custom Separator Character
-- http://sourceforge.net/tracker/?func=detail&aid=3010957&group_id=176962&atid=879335
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58882 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 3, 2010 11:27:45 AM CEST
-- FR [3010957] - Custom Separator Character
-- http://sourceforge.net/tracker/?func=detail&aid=3010957&group_id=176962&atid=879335
ALTER TABLE AD_ImpFormat ADD SeparatorChar NVARCHAR2(1) DEFAULT NULL 
;

-- Jun 3, 2010 11:31:04 AM CEST
-- FR [3010957] - Custom Separator Character
-- http://sourceforge.net/tracker/?func=detail&aid=3010957&group_id=176962&atid=879335
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=3713
;

-- Jun 3, 2010 11:31:04 AM CEST
-- FR [3010957] - Custom Separator Character
-- http://sourceforge.net/tracker/?func=detail&aid=3010957&group_id=176962&atid=879335
UPDATE AD_Field SET SeqNo=10,IsDisplayed='Y' WHERE AD_Field_ID=3714
;

-- Jun 3, 2010 11:31:04 AM CEST
-- FR [3010957] - Custom Separator Character
-- http://sourceforge.net/tracker/?func=detail&aid=3010957&group_id=176962&atid=879335
UPDATE AD_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=3715
;

-- Jun 3, 2010 11:31:04 AM CEST
-- FR [3010957] - Custom Separator Character
-- http://sourceforge.net/tracker/?func=detail&aid=3010957&group_id=176962&atid=879335
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=3717
;

-- Jun 3, 2010 11:31:04 AM CEST
-- FR [3010957] - Custom Separator Character
-- http://sourceforge.net/tracker/?func=detail&aid=3010957&group_id=176962&atid=879335
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=3718
;

-- Jun 3, 2010 11:31:04 AM CEST
-- FR [3010957] - Custom Separator Character
-- http://sourceforge.net/tracker/?func=detail&aid=3010957&group_id=176962&atid=879335
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=3716
;

-- Jun 3, 2010 11:31:04 AM CEST
-- FR [3010957] - Custom Separator Character
-- http://sourceforge.net/tracker/?func=detail&aid=3010957&group_id=176962&atid=879335
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=3737
;

-- Jun 3, 2010 11:31:04 AM CEST
-- FR [3010957] - Custom Separator Character
-- http://sourceforge.net/tracker/?func=detail&aid=3010957&group_id=176962&atid=879335
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=3720
;

-- Jun 3, 2010 11:31:04 AM CEST
-- FR [3010957] - Custom Separator Character
-- http://sourceforge.net/tracker/?func=detail&aid=3010957&group_id=176962&atid=879335
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=58882
;

-- Jun 3, 2010 11:31:16 AM CEST
-- FR [3010957] - Custom Separator Character
-- http://sourceforge.net/tracker/?func=detail&aid=3010957&group_id=176962&atid=879335
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2010-06-03 11:31:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58882
;

