-- Oct 2, 2010 8:54:45 PM CDT
-- Cost Engine
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,54310,0,'AmtLL',TO_TIMESTAMP('2010-10-02 20:54:42','YYYY-MM-DD HH24:MI:SS'),100,'Amount Lower Level Cost','D','Amount Lower Level Cost','Y','Amount Lower Level','Amt LL',TO_TIMESTAMP('2010-10-02 20:54:42','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Oct 2, 2010 8:54:45 PM CDT
-- Cost Engine
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54310 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Oct 2, 2010 8:55:10 PM CDT
-- Cost Engine
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59893,54310,0,12,808,'AmtLL',TO_TIMESTAMP('2010-10-02 20:55:08','YYYY-MM-DD HH24:MI:SS'),100,'Amount Lower Level Cost','D',22,'Amount Lower Level Cost','Y','Y','N','N','N','N','N','Y','N','N','N','N','Y','Amount Lower Level',TO_TIMESTAMP('2010-10-02 20:55:08','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Oct 2, 2010 8:55:10 PM CDT
-- Cost Engine
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59893 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 2, 2010 8:55:27 PM CDT
-- Cost Engine
UPDATE AD_Column SET IsMandatory='N',Updated=TO_TIMESTAMP('2010-10-02 20:55:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=59893
;

-- Oct 2, 2010 8:55:28 PM CDT
-- Cost Engine
ALTER TABLE M_CostDetail ADD COLUMN AmtLL NUMERIC DEFAULT NULL 
;

-- Oct 2, 2010 8:55:33 PM CDT
-- Cost Engine
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2010-10-02 20:55:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=59893
;

-- Oct 2, 2010 8:57:18 PM CDT
-- Cost Engine
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,54311,0,'CostAdjustmentLL',TO_TIMESTAMP('2010-10-02 20:57:18','YYYY-MM-DD HH24:MI:SS'),100,'Product Cost Adjustment Lower Level','D','product cost adjustments','Y','Cost Adjustment Lower Level','Cost Adjustment Lower Level',TO_TIMESTAMP('2010-10-02 20:57:18','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Oct 2, 2010 8:57:18 PM CDT
-- Cost Engine
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54311 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Oct 2, 2010 8:58:10 PM CDT
-- Cost Engine
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,59894,54311,0,37,808,'CostAdjustmentLL',TO_TIMESTAMP('2010-10-02 20:58:09','YYYY-MM-DD HH24:MI:SS'),100,'Product Cost Adjustment Lower Level','D',22,'product cost adjustments','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Cost Adjustment Lower Level',0,TO_TIMESTAMP('2010-10-02 20:58:09','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Oct 2, 2010 8:58:10 PM CDT
-- Cost Engine
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59894 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 2, 2010 8:58:13 PM CDT
-- Cost Engine
ALTER TABLE M_CostDetail ADD COLUMN CostAdjustmentLL NUMERIC DEFAULT NULL 
;

-- Oct 2, 2010 9:03:26 PM CDT
-- Cost Engine
ALTER TABLE M_CostDetail ADD COLUMN CostAdjustmentDateLL TIMESTAMP DEFAULT NULL 
;

-- Oct 2, 2010 9:04:30 PM CDT
-- Cost Engine
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,54312,0,'CostAmtLL',TO_TIMESTAMP('2010-10-02 21:04:26','YYYY-MM-DD HH24:MI:SS'),100,'Value with Cost Lower Level','D','Y','Cost Value Lower Level','Cost Value Lower Level',TO_TIMESTAMP('2010-10-02 21:04:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Oct 2, 2010 9:04:30 PM CDT
-- Cost Engine
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54312 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Oct 2, 2010 9:05:02 PM CDT
-- Cost Engine
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,59897,54312,0,37,808,'CostAmtLL',TO_TIMESTAMP('2010-10-02 21:04:59','YYYY-MM-DD HH24:MI:SS'),100,'Value with Cost Lower Level','D',22,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Cost Value Lower Level',0,TO_TIMESTAMP('2010-10-02 21:04:59','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Oct 2, 2010 9:05:02 PM CDT
-- Cost Engine
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59897 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 2, 2010 9:05:06 PM CDT
-- Cost Engine
ALTER TABLE M_CostDetail ADD COLUMN CostAmtLL NUMERIC DEFAULT NULL 
;

-- Oct 2, 2010 9:06:48 PM CDT
-- Cost Engine
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,59893,59666,0,748,TO_TIMESTAMP('2010-10-02 21:06:47','YYYY-MM-DD HH24:MI:SS'),100,'Amount Lower Level Cost',22,'D','Amount Lower Level Cost','Y','Y','Y','N','N','N','N','N','Amount Lower Level',TO_TIMESTAMP('2010-10-02 21:06:47','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Oct 2, 2010 9:06:48 PM CDT
-- Cost Engine
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59666 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 2, 2010 9:06:50 PM CDT
-- Cost Engine
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,59894,59668,0,748,TO_TIMESTAMP('2010-10-02 21:06:50','YYYY-MM-DD HH24:MI:SS'),100,'Product Cost Adjustment Lower Level',22,'D','product cost adjustments','Y','Y','Y','N','N','N','N','N','Cost Adjustment Lower Level',TO_TIMESTAMP('2010-10-02 21:06:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Oct 2, 2010 9:06:50 PM CDT
-- Cost Engine
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59668 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 2, 2010 9:06:51 PM CDT
-- Cost Engine
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,59897,59669,0,748,TO_TIMESTAMP('2010-10-02 21:06:50','YYYY-MM-DD HH24:MI:SS'),100,'Value with Cost Lower Level',22,'D','Y','Y','Y','N','N','N','N','N','Cost Value Lower Level',TO_TIMESTAMP('2010-10-02 21:06:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Oct 2, 2010 9:06:51 PM CDT
-- Cost Engine
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59669 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 2, 2010 9:06:52 PM CDT
-- Cost Engine
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,59789,59670,0,748,TO_TIMESTAMP('2010-10-02 21:06:51','YYYY-MM-DD HH24:MI:SS'),100,1,'D','Y','Y','Y','N','N','N','N','N','Process Now',TO_TIMESTAMP('2010-10-02 21:06:51','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Oct 2, 2010 9:06:52 PM CDT
-- Cost Engine
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59670 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 2, 2010 9:07:15 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=59670
;

-- Oct 2, 2010 9:07:15 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=360,IsDisplayed='Y' WHERE AD_Field_ID=59666
;

-- Oct 2, 2010 9:07:15 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=370,IsDisplayed='Y' WHERE AD_Field_ID=59667
;

-- Oct 2, 2010 9:07:15 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=380,IsDisplayed='Y' WHERE AD_Field_ID=59668
;

-- Oct 2, 2010 9:07:15 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=390,IsDisplayed='Y' WHERE AD_Field_ID=59669
;

-- Oct 2, 2010 9:07:15 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=400,IsDisplayed='Y' WHERE AD_Field_ID=59632
;

-- Oct 2, 2010 9:11:34 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=59666
;

-- Oct 2, 2010 9:11:34 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=58846
;

-- Oct 2, 2010 9:11:34 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=59523
;

-- Oct 2, 2010 9:11:34 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=12341
;

-- Oct 2, 2010 9:11:34 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=12340
;

-- Oct 2, 2010 9:11:34 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=58849
;

-- Oct 2, 2010 9:11:34 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=58848
;

-- Oct 2, 2010 9:11:34 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=59622
;

-- Oct 2, 2010 9:11:34 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=59669
;

-- Oct 2, 2010 9:11:34 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=59533
;

-- Oct 2, 2010 9:11:34 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=59668
;

-- Oct 2, 2010 9:11:34 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=59534
;

-- Oct 2, 2010 9:11:34 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=59667
;

-- Oct 2, 2010 9:11:34 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=12177
;

-- Oct 2, 2010 9:11:34 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=12151
;

-- Oct 2, 2010 9:11:34 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=12156
;

-- Oct 2, 2010 9:11:34 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=12342
;

-- Oct 2, 2010 9:11:34 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=12343
;

-- Oct 2, 2010 9:11:34 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=12345
;

-- Oct 2, 2010 9:11:34 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=12344
;

-- Oct 2, 2010 9:11:34 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=59521
;

-- Oct 2, 2010 9:11:34 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=340,IsDisplayed='Y' WHERE AD_Field_ID=59452
;

-- Oct 2, 2010 9:11:34 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=350,IsDisplayed='Y' WHERE AD_Field_ID=58865
;

-- Oct 2, 2010 9:11:34 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=360,IsDisplayed='Y' WHERE AD_Field_ID=12346
;

-- Oct 2, 2010 9:11:34 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=370,IsDisplayed='Y' WHERE AD_Field_ID=12150
;

-- Oct 2, 2010 9:11:34 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=380,IsDisplayed='Y' WHERE AD_Field_ID=58864
;

-- Oct 2, 2010 9:11:34 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=390,IsDisplayed='Y' WHERE AD_Field_ID=12153
;

-- Oct 2, 2010 9:12:10 PM CDT
-- Cost Engine
UPDATE AD_Element SET Name='Cost Value LL',Updated=TO_TIMESTAMP('2010-10-02 21:12:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=54312
;

-- Oct 2, 2010 9:12:10 PM CDT
-- Cost Engine
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=54312
;

-- Oct 2, 2010 9:12:10 PM CDT
-- Cost Engine
UPDATE AD_Column SET ColumnName='CostAmtLL', Name='Cost Value LL', Description='Value with Cost Lower Level', Help=NULL WHERE AD_Element_ID=54312
;

-- Oct 2, 2010 9:12:11 PM CDT
-- Cost Engine
UPDATE AD_Process_Para SET ColumnName='CostAmtLL', Name='Cost Value LL', Description='Value with Cost Lower Level', Help=NULL, AD_Element_ID=54312 WHERE UPPER(ColumnName)='COSTAMTLL' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Oct 2, 2010 9:12:11 PM CDT
-- Cost Engine
UPDATE AD_Process_Para SET ColumnName='CostAmtLL', Name='Cost Value LL', Description='Value with Cost Lower Level', Help=NULL WHERE AD_Element_ID=54312 AND IsCentrallyMaintained='Y'
;

-- Oct 2, 2010 9:12:11 PM CDT
-- Cost Engine
UPDATE AD_Field SET Name='Cost Value LL', Description='Value with Cost Lower Level', Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=54312) AND IsCentrallyMaintained='Y'
;

-- Oct 2, 2010 9:12:11 PM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem SET PrintName='Cost Value Lower Level', Name='Cost Value LL' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=54312)
;

-- Oct 2, 2010 9:13:18 PM CDT
-- Cost Engine
UPDATE AD_Element SET Name='Cost Adjustment LL',Updated=TO_TIMESTAMP('2010-10-02 21:13:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=54311
;

-- Oct 2, 2010 9:13:18 PM CDT
-- Cost Engine
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=54311
;

-- Oct 2, 2010 9:13:18 PM CDT
-- Cost Engine
UPDATE AD_Column SET ColumnName='CostAdjustmentLL', Name='Cost Adjustment LL', Description='Product Cost Adjustment Lower Level', Help='product cost adjustments' WHERE AD_Element_ID=54311
;

-- Oct 2, 2010 9:13:18 PM CDT
-- Cost Engine
UPDATE AD_Process_Para SET ColumnName='CostAdjustmentLL', Name='Cost Adjustment LL', Description='Product Cost Adjustment Lower Level', Help='product cost adjustments', AD_Element_ID=54311 WHERE UPPER(ColumnName)='COSTADJUSTMENTLL' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Oct 2, 2010 9:13:18 PM CDT
-- Cost Engine
UPDATE AD_Process_Para SET ColumnName='CostAdjustmentLL', Name='Cost Adjustment LL', Description='Product Cost Adjustment Lower Level', Help='product cost adjustments' WHERE AD_Element_ID=54311 AND IsCentrallyMaintained='Y'
;

-- Oct 2, 2010 9:13:18 PM CDT
-- Cost Engine
UPDATE AD_Field SET Name='Cost Adjustment LL', Description='Product Cost Adjustment Lower Level', Help='product cost adjustments' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=54311) AND IsCentrallyMaintained='Y'
;

-- Oct 2, 2010 9:13:18 PM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem SET PrintName='Cost Adjustment Lower Level', Name='Cost Adjustment LL' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=54311)
;

-- Oct 2, 2010 9:16:18 PM CDT
-- Cost Engine
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,54313,0,'CostAdjustmentDateLL',TO_TIMESTAMP('2010-10-02 21:16:17','YYYY-MM-DD HH24:MI:SS'),100,'Date Product Cost Adjustment Lower Level','D','Date product cost adjustments Lower Level','Y','Cost Adjustment Date','Cost Adjustment Date',TO_TIMESTAMP('2010-10-02 21:16:17','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Oct 2, 2010 9:16:18 PM CDT
-- Cost Engine
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54313 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Oct 2, 2010 9:19:20 PM CDT
-- Cost Engine
UPDATE AD_Element SET Name='Amount LL', PrintName='Amt Lower Level',Updated=TO_TIMESTAMP('2010-10-02 21:19:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=54310
;

-- Oct 2, 2010 9:19:20 PM CDT
-- Cost Engine
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=54310
;

-- Oct 2, 2010 9:19:20 PM CDT
-- Cost Engine
UPDATE AD_Column SET ColumnName='AmtLL', Name='Amount LL', Description='Amount Lower Level Cost', Help='Amount Lower Level Cost' WHERE AD_Element_ID=54310
;

-- Oct 2, 2010 9:02:59 PM CDT
-- Cost Engine
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,59896,54313,0,15,808,'CostAdjustmentDateLL',TO_TIMESTAMP('2010-10-02 21:02:56','YYYY-MM-DD HH24:MI:SS'),100,'Date Product Cost Adjustment Lower Level','D',8,'Date product cost adjustments Lower Level','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Cost Adjustment Date Lower Level',0,TO_TIMESTAMP('2010-10-02 21:02:56','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Oct 2, 2010 9:02:59 PM CDT
-- Cost Engine
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59896 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 2, 2010 9:06:50 PM CDT
-- Cost Engine
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,59896,59667,0,748,TO_TIMESTAMP('2010-10-02 21:06:49','YYYY-MM-DD HH24:MI:SS'),100,'Date Product Cost Adjustment LL',8,'D','Date product cost adjustments Lower Level','Y','Y','Y','N','N','N','N','N','Cost Adjustment Date Lower Level',TO_TIMESTAMP('2010-10-02 21:06:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Oct 2, 2010 9:06:50 PM CDT
-- Cost Engine
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59667 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 2, 2010 9:19:20 PM CDT
-- Cost Engine
UPDATE AD_Process_Para SET ColumnName='AmtLL', Name='Amount LL', Description='Amount Lower Level Cost', Help='Amount Lower Level Cost', AD_Element_ID=54310 WHERE UPPER(ColumnName)='AMTLL' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Oct 2, 2010 9:19:20 PM CDT
-- Cost Engine
UPDATE AD_Process_Para SET ColumnName='AmtLL', Name='Amount LL', Description='Amount Lower Level Cost', Help='Amount Lower Level Cost' WHERE AD_Element_ID=54310 AND IsCentrallyMaintained='Y'
;

-- Oct 2, 2010 9:19:20 PM CDT
-- Cost Engine
UPDATE AD_Field SET Name='Amount LL', Description='Amount Lower Level Cost', Help='Amount Lower Level Cost' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=54310) AND IsCentrallyMaintained='Y'
;

-- Oct 2, 2010 9:19:20 PM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem SET PrintName='Amt Lower Level', Name='Amount LL' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=54310)
;

-- Oct 2, 2010 9:20:00 PM CDT
-- Cost Engine
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2010-10-02 21:20:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59666
;

-- Oct 2, 2010 9:20:05 PM CDT
-- Cost Engine
UPDATE AD_Field SET IsSameLine='N',Updated=TO_TIMESTAMP('2010-10-02 21:20:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=12147
;

-- Oct 2, 2010 9:20:36 PM CDT
-- Cost Engine
UPDATE AD_Element SET Name='Current Cost Price LL',Updated=TO_TIMESTAMP('2010-10-02 21:20:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53296
;

-- Oct 2, 2010 9:20:36 PM CDT
-- Cost Engine
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53296
;

-- Oct 2, 2010 9:20:36 PM CDT
-- Cost Engine
UPDATE AD_Column SET ColumnName='CurrentCostPriceLL', Name='Current Cost Price LL', Description='Current Price Lower Level Is the sum of the costs of the components of this product manufactured for this level.', Help='Current Price Lower Level is used for get the total costs for lower level the a product manufactured.

The Current Price Lower Level always will be calculated.

You can see the Current Cost Price and Current Cost Price Lower Level with Cost  Bill of Material & Formula Detail Report.
 
The sum the Current Cost Price + Current Cost Price Lower Level is the total cost to a product manufactured.
' WHERE AD_Element_ID=53296
;

-- Oct 2, 2010 9:20:36 PM CDT
-- Cost Engine
UPDATE AD_Process_Para SET ColumnName='CurrentCostPriceLL', Name='Current Cost Price LL', Description='Current Price Lower Level Is the sum of the costs of the components of this product manufactured for this level.', Help='Current Price Lower Level is used for get the total costs for lower level the a product manufactured.

The Current Price Lower Level always will be calculated.

You can see the Current Cost Price and Current Cost Price Lower Level with Cost  Bill of Material & Formula Detail Report.
 
The sum the Current Cost Price + Current Cost Price Lower Level is the total cost to a product manufactured.
', AD_Element_ID=53296 WHERE UPPER(ColumnName)='CURRENTCOSTPRICELL' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Oct 2, 2010 9:20:36 PM CDT
-- Cost Engine
UPDATE AD_Process_Para SET ColumnName='CurrentCostPriceLL', Name='Current Cost Price LL', Description='Current Price Lower Level Is the sum of the costs of the components of this product manufactured for this level.', Help='Current Price Lower Level is used for get the total costs for lower level the a product manufactured.

The Current Price Lower Level always will be calculated.

You can see the Current Cost Price and Current Cost Price Lower Level with Cost  Bill of Material & Formula Detail Report.
 
The sum the Current Cost Price + Current Cost Price Lower Level is the total cost to a product manufactured.
' WHERE AD_Element_ID=53296 AND IsCentrallyMaintained='Y'
;

-- Oct 2, 2010 9:20:36 PM CDT
-- Cost Engine
UPDATE AD_Field SET Name='Current Cost Price LL', Description='Current Price Lower Level Is the sum of the costs of the components of this product manufactured for this level.', Help='Current Price Lower Level is used for get the total costs for lower level the a product manufactured.

The Current Price Lower Level always will be calculated.

You can see the Current Cost Price and Current Cost Price Lower Level with Cost  Bill of Material & Formula Detail Report.
 
The sum the Current Cost Price + Current Cost Price Lower Level is the total cost to a product manufactured.
' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53296) AND IsCentrallyMaintained='Y'
;

-- Oct 2, 2010 9:20:36 PM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem SET PrintName='Current Cost Price Lower Level', Name='Current Cost Price LL' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53296)
;

-- Oct 2, 2010 9:20:55 PM CDT
-- Cost Engine
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2010-10-02 21:20:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59669
;

-- Oct 2, 2010 9:21:00 PM CDT
-- Cost Engine
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2010-10-02 21:21:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59668
;

-- Oct 2, 2010 9:21:08 PM CDT
-- Cost Engine
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2010-10-02 21:21:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59667
;

-- Oct 2, 2010 9:39:28 PM CDT
-- Cost Engine
UPDATE AD_Column SET AD_Element_ID=54313, ColumnName='CostAdjustmentDateLL', Description='Date Product Cost Adjustment Lower Level', Help='Date product cost adjustments Lower Level', Name='Cost Adjustment Date LL',Updated=TO_TIMESTAMP('2010-10-02 21:39:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=59896
;

-- Oct 2, 2010 9:39:28 PM CDT
-- Cost Engine
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=59896
;

-- Oct 2, 2010 9:39:28 PM CDT
-- Cost Engine
UPDATE AD_Field SET Name='Cost Adjustment Date LL', Description='Date Product Cost Adjustment Lower Level', Help='Date product cost adjustments Lower Level' WHERE AD_Column_ID=59896 AND IsCentrallyMaintained='Y'
;

-- Oct 3, 2010 12:32:54 AM CDT
-- Cost Engine
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,54314,0,'CumulatedAmtLL',TO_TIMESTAMP('2010-10-03 00:32:49','YYYY-MM-DD HH24:MI:SS'),100,'Total Amount','D','Sum of all amounts','Y','Accumulated Amt LL','Accumulated Amt Lower Lavel',TO_TIMESTAMP('2010-10-03 00:32:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Oct 3, 2010 12:32:54 AM CDT
-- Cost Engine
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54314 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Oct 3, 2010 12:33:14 AM CDT
-- Cost Engine
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,59898,54314,0,12,808,'CumulatedAmtLL',TO_TIMESTAMP('2010-10-03 00:33:09','YYYY-MM-DD HH24:MI:SS'),100,'Total Amount','D',22,'Sum of all amounts','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Accumulated Amt LL',0,TO_TIMESTAMP('2010-10-03 00:33:09','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Oct 3, 2010 12:33:14 AM CDT
-- Cost Engine
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59898 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 3, 2010 12:33:47 AM CDT
-- Cost Engine
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,59898,59671,0,748,TO_TIMESTAMP('2010-10-03 00:33:43','YYYY-MM-DD HH24:MI:SS'),100,'Total Amount',22,'D','Sum of all amounts','Y','Y','Y','N','N','N','N','N','Accumulated Amt LL',TO_TIMESTAMP('2010-10-03 00:33:43','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Oct 3, 2010 12:33:47 AM CDT
-- Cost Engine
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59671 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 3, 2010 12:34:09 AM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=59671
;

-- Oct 3, 2010 12:34:09 AM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=59622
;

-- Oct 3, 2010 12:34:09 AM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=59669
;

-- Oct 3, 2010 12:34:09 AM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=59533
;

-- Oct 3, 2010 12:34:09 AM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=59668
;

-- Oct 3, 2010 12:34:09 AM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=59534
;

-- Oct 3, 2010 12:34:09 AM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=59667
;

-- Oct 3, 2010 12:34:09 AM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=12177
;

-- Oct 3, 2010 12:34:09 AM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=12151
;

-- Oct 3, 2010 12:34:09 AM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=12156
;

-- Oct 3, 2010 12:34:09 AM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=12342
;

-- Oct 3, 2010 12:34:09 AM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=12343
;

-- Oct 3, 2010 12:34:09 AM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=12345
;

-- Oct 3, 2010 12:34:09 AM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=12344
;

-- Oct 3, 2010 12:34:09 AM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=340,IsDisplayed='Y' WHERE AD_Field_ID=59521
;

-- Oct 3, 2010 12:34:09 AM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=350,IsDisplayed='Y' WHERE AD_Field_ID=59452
;

-- Oct 3, 2010 12:34:09 AM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=360,IsDisplayed='Y' WHERE AD_Field_ID=58865
;

-- Oct 3, 2010 12:34:09 AM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=370,IsDisplayed='Y' WHERE AD_Field_ID=12346
;

-- Oct 3, 2010 12:34:09 AM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=380,IsDisplayed='Y' WHERE AD_Field_ID=12150
;

-- Oct 3, 2010 12:34:09 AM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=390,IsDisplayed='Y' WHERE AD_Field_ID=58864
;

-- Oct 3, 2010 12:34:09 AM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=400,IsDisplayed='Y' WHERE AD_Field_ID=12153
;

-- Oct 3, 2010 12:34:09 AM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=410,IsDisplayed='Y' WHERE AD_Field_ID=59632
;

-- Oct 3, 2010 12:34:34 AM CDT
-- Cost Engine
UPDATE AD_Field SET AD_FieldGroup_ID=116,Updated=TO_TIMESTAMP('2010-10-03 00:34:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59671
;

-- Oct 3, 2010 12:34:39 AM CDT
-- Cost Engine
UPDATE AD_Field SET AD_FieldGroup_ID=116,Updated=TO_TIMESTAMP('2010-10-03 00:34:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59622
;

-- Oct 3, 2010 12:34:45 AM CDT
-- Cost Engine
UPDATE AD_Field SET AD_FieldGroup_ID=116,Updated=TO_TIMESTAMP('2010-10-03 00:34:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59669
;

-- Oct 3, 2010 12:34:49 AM CDT
-- Cost Engine
UPDATE AD_Field SET AD_FieldGroup_ID=116,Updated=TO_TIMESTAMP('2010-10-03 00:34:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59668
;

-- Oct 3, 2010 12:34:54 AM CDT
-- Cost Engine
UPDATE AD_Field SET AD_FieldGroup_ID=116,Updated=TO_TIMESTAMP('2010-10-03 00:34:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59667
;

-- Oct 3, 2010 12:34:59 AM CDT
-- Cost Engine
UPDATE AD_Field SET AD_FieldGroup_ID=116,Updated=TO_TIMESTAMP('2010-10-03 00:34:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59666
;

-- Oct 3, 2010 12:35:01 AM CDT
-- Cost Engine
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_TIMESTAMP('2010-10-03 00:35:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59671
;

-- Oct 3, 2010 12:35:02 AM CDT
-- Cost Engine
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_TIMESTAMP('2010-10-03 00:35:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59666
;

-- Oct 3, 2010 12:35:03 AM CDT
-- Cost Engine
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_TIMESTAMP('2010-10-03 00:35:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59669
;

-- Oct 3, 2010 12:35:05 AM CDT
-- Cost Engine
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_TIMESTAMP('2010-10-03 00:35:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59667
;

-- Oct 3, 2010 12:35:10 AM CDT
-- Cost Engine
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_TIMESTAMP('2010-10-03 00:35:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59668
;

-- Oct 3, 2010 12:35:17 AM CDT
-- Cost Engine
UPDATE AD_Field SET IsCentrallyMaintained='Y',Updated=TO_TIMESTAMP('2010-10-03 00:35:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=12153
;

-- Oct 3, 2010 12:35:22 AM CDT
-- Cost Engine
UPDATE AD_Field SET AD_FieldGroup_ID=105,Updated=TO_TIMESTAMP('2010-10-03 00:35:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59632
;

-- Oct 3, 2010 12:38:13 AM CDT
-- Cost Engine
UPDATE AD_Field SET IsSameLine='N',Updated=TO_TIMESTAMP('2010-10-03 00:38:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59534
;

-- Oct 3, 2010 12:39:55 AM CDT
-- Cost Engine
UPDATE AD_Field SET IsSameLine='N',Updated=TO_TIMESTAMP('2010-10-03 00:39:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58848
;

-- Oct 3, 2010 12:39:59 AM CDT
-- Cost Engine
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2010-10-03 00:39:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59671
;

-- Oct 3, 2010 12:42:38 AM CDT
-- Cost Engine
ALTER TABLE M_CostDetail ADD COLUMN CumulatedAmtLL NUMERIC DEFAULT NULL 
;

-- Oct 3, 2010 1:33:04 AM CDT
-- Cost Engine
UPDATE AD_Element SET Name='Future Cost Price LL',Updated=TO_TIMESTAMP('2010-10-03 01:33:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53763
;

-- Oct 3, 2010 1:33:04 AM CDT
-- Cost Engine
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53763
;

-- Oct 3, 2010 1:33:04 AM CDT
-- Cost Engine
UPDATE AD_Column SET ColumnName='FutureCostPriceLL', Name='Future Cost Price LL', Description=NULL, Help=NULL WHERE AD_Element_ID=53763
;

-- Oct 3, 2010 1:33:04 AM CDT
-- Cost Engine
UPDATE AD_Process_Para SET ColumnName='FutureCostPriceLL', Name='Future Cost Price LL', Description=NULL, Help=NULL, AD_Element_ID=53763 WHERE UPPER(ColumnName)='FUTURECOSTPRICELL' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Oct 3, 2010 1:33:04 AM CDT
-- Cost Engine
UPDATE AD_Process_Para SET ColumnName='FutureCostPriceLL', Name='Future Cost Price LL', Description=NULL, Help=NULL WHERE AD_Element_ID=53763 AND IsCentrallyMaintained='Y'
;

-- Oct 3, 2010 1:33:04 AM CDT
-- Cost Engine
UPDATE AD_Field SET Name='Future Cost Price LL', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53763) AND IsCentrallyMaintained='Y'
;

-- Oct 3, 2010 1:33:04 AM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem SET PrintName='Future Cost price Lower Level', Name='Future Cost Price LL' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53763)
;

-- Oct 3, 2010 1:33:40 AM CDT
-- Cost Engine
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59899,54314,0,12,771,'CumulatedAmtLL',TO_TIMESTAMP('2010-10-03 01:33:39','YYYY-MM-DD HH24:MI:SS'),100,'Total Amount','D',22,'Sum of all amounts','Y','Y','N','N','N','N','N','N','N','N','N','N','N','Accumulated Amt LL',TO_TIMESTAMP('2010-10-03 01:33:39','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Oct 3, 2010 1:33:40 AM CDT
-- Cost Engine
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59899 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 3, 2010 1:33:42 AM CDT
-- Cost Engine
ALTER TABLE M_Cost ADD COLUMN CumulatedAmtLL NUMERIC DEFAULT NULL 
;

-- Oct 3, 2010 1:34:07 AM CDT
-- Cost Engine
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,59899,59672,0,701,TO_TIMESTAMP('2010-10-03 01:34:07','YYYY-MM-DD HH24:MI:SS'),100,'Total Amount',22,'D','Sum of all amounts','Y','Y','Y','N','N','N','N','N','Accumulated Amt LL',TO_TIMESTAMP('2010-10-03 01:34:07','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Oct 3, 2010 1:34:07 AM CDT
-- Cost Engine
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59672 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 3, 2010 1:34:24 AM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=59672
;

-- Oct 3, 2010 1:34:24 AM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=12176
;

-- Oct 3, 2010 1:34:24 AM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=12319
;

-- Oct 3, 2010 1:35:11 AM CDT
-- Cost Engine
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2010-10-03 01:35:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59672
;

-- Oct 3, 2010 1:37:37 AM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=12176
;

-- Oct 3, 2010 1:37:37 AM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=56626
;

-- Oct 3, 2010 1:37:37 AM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=12175
;

-- Oct 3, 2010 1:37:37 AM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=59672
;

-- Oct 3, 2010 1:38:02 AM CDT
-- Cost Engine
UPDATE AD_Field SET IsSameLine='N',Updated=TO_TIMESTAMP('2010-10-03 01:38:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56626
;

-- Oct 3, 2010 1:38:58 AM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=12175
;

-- Oct 3, 2010 1:38:58 AM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=59672
;

-- Oct 3, 2010 1:38:58 AM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=56626
;

-- Oct 3, 2010 1:40:16 AM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=12318
;

-- Oct 3, 2010 1:40:16 AM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=12325
;

-- Oct 3, 2010 1:40:16 AM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=12176
;

-- Oct 3, 2010 1:40:16 AM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=12175
;

-- Oct 3, 2010 1:40:16 AM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=59672
;

-- Oct 3, 2010 1:40:16 AM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=11352
;

-- Oct 3, 2010 1:40:16 AM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=56625
;

-- Oct 5, 2010 2:01:37 PM CDT
-- Cost Engine
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59908,54312,0,12,478,'CostAmtLL',TO_TIMESTAMP('2010-10-05 14:01:34','YYYY-MM-DD HH24:MI:SS'),100,'Value with Cost Lower Level','D',22,'Y','Y','N','N','N','N','N','N','N','N','Y','N','Y','Cost Value LL',TO_TIMESTAMP('2010-10-05 14:01:34','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Oct 5, 2010 2:01:37 PM CDT
-- Cost Engine
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59908 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 5, 2010 2:01:40 PM CDT
-- Cost Engine
ALTER TABLE T_InventoryValue ADD COLUMN CostAmtLL NUMERIC DEFAULT NULL 
;

-- Oct 5, 2010 2:14:01 PM CDT
-- Cost Engine
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,59909,316,0,37,478,'GrandTotal',TO_TIMESTAMP('2010-10-05 14:14:00','YYYY-MM-DD HH24:MI:SS'),100,'Total amount of document','D',22,'The Grand Total displays the total amount including Tax and Freight in document currency','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Grand Total',0,TO_TIMESTAMP('2010-10-05 14:14:00','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Oct 5, 2010 2:14:01 PM CDT
-- Cost Engine
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59909 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;


-- Oct 5, 2010 2:17:44 PM CDT
-- Cost Engine
UPDATE AD_Column SET AD_Element_ID=2822, ColumnName='CumulatedAmt', Description='Total Amount', Help='Sum of all amounts', Name='Accumulated Amt',Updated=TO_TIMESTAMP('2010-10-05 14:17:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=59909
;

-- Oct 5, 2010 2:17:44 PM CDT
-- Cost Engine
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=59909
;

-- Oct 5, 2010 2:17:44 PM CDT
-- Cost Engine
UPDATE AD_Field SET Name='Accumulated Amt', Description='Total Amount', Help='Sum of all amounts' WHERE AD_Column_ID=59909 AND IsCentrallyMaintained='Y'
;

-- Oct 5, 2010 2:17:48 PM CDT
-- Cost Engine
ALTER TABLE T_InventoryValue ADD COLUMN CumulatedAmt NUMERIC DEFAULT NULL 
;

-- Oct 5, 2010 2:19:49 PM CDT
-- Cost Engine
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintColor_ID,AD_PrintFont_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsSuppressRepeats,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59908,0,100,130,51642,50052,0,0,TO_TIMESTAMP('2010-10-05 14:19:47','YYYY-MM-DD HH24:MI:SS'),100,'D','N','Y','N','N','N','N','N','N','N','Y','N','N','N','Y','N','N','N','Y','Y','N','N','N','N','N','N','X',1,0,0,'Cost Amt LL','C','F','Cost Amt LL',20,90,'N',0,TO_TIMESTAMP('2010-10-05 14:19:47','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Oct 5, 2010 2:19:49 PM CDT
-- Cost Engine
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51642 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Oct 5, 2010 2:19:49 PM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=59908) WHERE AD_PrintFormatItem_ID = 51642 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=59908 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 51642) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Oct 5, 2010 2:20:43 PM CDT
-- Cost Engine
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintColor_ID,AD_PrintFont_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsSuppressRepeats,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59909,0,100,130,51643,50052,0,0,TO_TIMESTAMP('2010-10-05 14:20:42','YYYY-MM-DD HH24:MI:SS'),100,'D','N','Y','N','N','N','N','N','N','N','Y','N','N','N','Y','N','N','N','Y','Y','N','N','N','N','N','N','X',1,0,0,'Total Cost','C','F','Total Cost',20,100,'N',0,TO_TIMESTAMP('2010-10-05 14:20:42','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Oct 5, 2010 2:20:44 PM CDT
-- Cost Engine
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51643 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Oct 5, 2010 2:20:44 PM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=59909) WHERE AD_PrintFormatItem_ID = 51643 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=59909 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 51643) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Oct 5, 2010 2:21:01 PM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', IsSummarized='Y', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2010-10-05 14:21:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_PrintFormatItem_ID=51642
;

-- Oct 5, 2010 2:21:16 PM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', IsSummarized='Y', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2010-10-05 14:21:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_PrintFormatItem_ID=51643
;

-- Oct 5, 2010 2:32:02 PM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem SET MaxWidth=110, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2010-10-05 14:32:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_PrintFormatItem_ID=51574
;

-- Oct 5, 2010 2:33:35 PM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', MaxWidth=80, Name='Product Ctg', PrintName='Product Ctg', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2010-10-05 14:33:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_PrintFormatItem_ID=51575
;

-- Oct 5, 2010 2:33:35 PM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=51575
;

-- Oct 5, 2010 2:33:45 PM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem SET IsFixedWidth='Y', IsGroupBy='N', IsPageBreak='N', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2010-10-05 14:33:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_PrintFormatItem_ID=51575
;

-- Oct 5, 2010 2:50:35 PM CDT
-- Cost Engine
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59910,54311,0,22,53288,'CostAdjustmentLL',TO_TIMESTAMP('2010-10-05 14:50:21','YYYY-MM-DD HH24:MI:SS'),100,'Product Cost Adjustment Lower Level','D',131089,'product cost adjustments','Y','N','N','N','N','N','N','N','N','N','Cost Adjustment LL',TO_TIMESTAMP('2010-10-05 14:50:21','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Oct 5, 2010 2:50:35 PM CDT
-- Cost Engine
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59910 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 5, 2010 2:50:36 PM CDT
-- Cost Engine
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59911,54313,0,16,53288,'CostAdjustmentDateLL',TO_TIMESTAMP('2010-10-05 14:50:36','YYYY-MM-DD HH24:MI:SS'),100,'Date Product Cost Adjustment Lower Level','D',29,'Date product cost adjustments Lower Level','Y','N','N','N','N','N','N','N','N','N','Cost Adjustment Date',TO_TIMESTAMP('2010-10-05 14:50:36','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Oct 5, 2010 2:50:36 PM CDT
-- Cost Engine
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59911 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 5, 2010 2:50:37 PM CDT
-- Cost Engine
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59912,54310,0,12,53288,'AmtLL',TO_TIMESTAMP('2010-10-05 14:50:36','YYYY-MM-DD HH24:MI:SS'),100,'Amount Lower Level Cost','D',131089,'Amount Lower Level Cost','Y','N','N','N','N','N','N','N','N','N','Amount LL',TO_TIMESTAMP('2010-10-05 14:50:36','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Oct 5, 2010 2:50:37 PM CDT
-- Cost Engine
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59912 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 5, 2010 2:50:38 PM CDT
-- Cost Engine
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59913,54312,0,12,53288,'CostAmtLL',TO_TIMESTAMP('2010-10-05 14:50:37','YYYY-MM-DD HH24:MI:SS'),100,'Value with Cost Lower Level','D',131089,'Y','N','N','N','N','N','N','N','N','N','Cost Value LL',TO_TIMESTAMP('2010-10-05 14:50:37','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Oct 5, 2010 2:50:38 PM CDT
-- Cost Engine
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59913 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 5, 2010 2:50:38 PM CDT
-- Cost Engine
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59914,2822,0,12,53288,'CumulatedAmt',TO_TIMESTAMP('2010-10-05 14:50:38','YYYY-MM-DD HH24:MI:SS'),100,'Total Amount','D',131089,'Sum of all amounts','Y','N','N','N','N','N','N','N','N','N','Accumulated Amt',TO_TIMESTAMP('2010-10-05 14:50:38','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Oct 5, 2010 2:50:38 PM CDT
-- Cost Engine
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59914 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 5, 2010 2:50:39 PM CDT
-- Cost Engine
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59915,54314,0,12,53288,'CumulatedAmtLL',TO_TIMESTAMP('2010-10-05 14:50:38','YYYY-MM-DD HH24:MI:SS'),100,'Total Amount','D',131089,'Sum of all amounts','Y','N','N','N','N','N','N','N','N','N','Accumulated Amt LL',TO_TIMESTAMP('2010-10-05 14:50:38','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Oct 5, 2010 2:50:39 PM CDT
-- Cost Engine
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59915 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 5, 2010 2:53:59 PM CDT
-- Cost Engine
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsSuppressRepeats,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59913,0,51644,50053,0,0,TO_TIMESTAMP('2010-10-05 14:53:58','YYYY-MM-DD HH24:MI:SS'),100,'T','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','Y','N','N','N','X',1,0,0,'Cost Amt LL','C','F','Cost Amt LL',0,85,'N',0,TO_TIMESTAMP('2010-10-05 14:53:58','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Oct 5, 2010 2:53:59 PM CDT
-- Cost Engine
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51644 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Oct 5, 2010 2:53:59 PM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=59913) WHERE AD_PrintFormatItem_ID = 51644 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=59913 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 51644) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Oct 5, 2010 2:54:22 PM CDT
-- Cost Engine
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsSuppressRepeats,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59854,0,51645,50053,0,0,TO_TIMESTAMP('2010-10-05 14:54:21','YYYY-MM-DD HH24:MI:SS'),100,'T','N','Y','N','Y','N','N','N','Y','N','Y','N','N','N','N','N','N','N','Y','Y','N','N','Y','N','N','N','X',1,0,60,'Adjustment LL','C','F','Adjustment LL',0,86,'N',0,TO_TIMESTAMP('2010-10-05 14:54:21','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Oct 5, 2010 2:54:22 PM CDT
-- Cost Engine
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51645 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Oct 5, 2010 2:54:22 PM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=59854) WHERE AD_PrintFormatItem_ID = 51645 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=59854 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 51645) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Oct 5, 2010 2:54:54 PM CDT
-- Cost Engine
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintColor_ID,AD_PrintFont_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsSuppressRepeats,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59911,0,100,130,51646,50053,0,0,TO_TIMESTAMP('2010-10-05 14:54:53','YYYY-MM-DD HH24:MI:SS'),100,'D','N','Y','N','N','N','N','N','N','N','Y','N','N','N','Y','N','N','N','Y','Y','N','N','N','N','N','N','X',1,0,0,'Cost Adjustment Date LL','C','F',20,120,'N',0,TO_TIMESTAMP('2010-10-05 14:54:53','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Oct 5, 2010 2:54:54 PM CDT
-- Cost Engine
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51646 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Oct 5, 2010 2:55:29 PM CDT
-- Cost Engine
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintColor_ID,AD_PrintFont_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsSuppressRepeats,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59912,0,100,130,51647,50053,0,0,TO_TIMESTAMP('2010-10-05 14:55:28','YYYY-MM-DD HH24:MI:SS'),100,'D','N','Y','N','N','N','N','N','N','N','Y','N','N','N','Y','N','N','N','Y','Y','N','N','N','N','N','N','X',1,0,0,'Amt LL','C','F','Amt LL',20,130,'N',0,TO_TIMESTAMP('2010-10-05 14:55:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Oct 5, 2010 2:55:29 PM CDT
-- Cost Engine
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51647 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Oct 5, 2010 2:55:29 PM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=59912) WHERE AD_PrintFormatItem_ID = 51647 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=59912 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 51647) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Oct 5, 2010 2:55:48 PM CDT
-- Cost Engine
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintColor_ID,AD_PrintFont_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsSuppressRepeats,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59915,0,100,130,51648,50053,0,0,TO_TIMESTAMP('2010-10-05 14:55:47','YYYY-MM-DD HH24:MI:SS'),100,'D','N','Y','N','N','N','N','N','N','N','Y','N','N','N','Y','N','N','N','Y','Y','N','N','N','N','N','N','X',1,0,0,'Cumulated Amt LL','C','F',20,140,'N',0,TO_TIMESTAMP('2010-10-05 14:55:47','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Oct 5, 2010 2:55:48 PM CDT
-- Cost Engine
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51648 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Oct 5, 2010 2:56:00 PM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=51647
;

-- Oct 5, 2010 2:56:00 PM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=51646
;

-- Oct 5, 2010 2:56:00 PM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=51648
;

-- Oct 5, 2010 2:56:00 PM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem SET SeqNo=90,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=51644
;

-- Oct 5, 2010 2:56:00 PM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem SET SeqNo=100,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=51645
;

-- Oct 5, 2010 2:56:00 PM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem SET SeqNo=110,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=51639
;

-- Oct 5, 2010 2:56:00 PM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem SET SeqNo=120,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=51640
;

-- Oct 5, 2010 2:56:00 PM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem SET SeqNo=130,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=51641
;

-- Oct 5, 2010 3:06:42 PM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem SET MaxWidth=110, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2010-10-05 15:06:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_PrintFormatItem_ID=51631
;

-- Oct 5, 2010 3:06:55 PM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem SET MaxWidth=50, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2010-10-05 15:06:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_PrintFormatItem_ID=51633
;

-- Oct 5, 2010 3:07:12 PM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintName='Begin Qty', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2010-10-05 15:07:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_PrintFormatItem_ID=51634
;

-- Oct 5, 2010 3:07:12 PM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=51634
;

-- Oct 5, 2010 3:07:55 PM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', MaxWidth=40, PrintName='Begin Balance', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2010-10-05 15:07:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_PrintFormatItem_ID=51635
;

-- Oct 5, 2010 3:07:55 PM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=51635
;

-- Oct 5, 2010 3:08:16 PM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem SET IsFixedWidth='Y', IsGroupBy='N', IsHeightOneLine='Y', IsPageBreak='N', MaxWidth=40, Name='Cost TL', PrintName='Cost TL', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2010-10-05 15:08:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_PrintFormatItem_ID=51591
;

-- Oct 5, 2010 3:08:16 PM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=51591
;

-- Oct 5, 2010 3:08:28 PM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', MaxWidth=50, SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2010-10-05 15:08:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_PrintFormatItem_ID=51591
;

-- Oct 5, 2010 3:08:35 PM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', MaxWidth=50, SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2010-10-05 15:08:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_PrintFormatItem_ID=51635
;

-- Oct 5, 2010 3:08:59 PM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', MaxWidth=50, Name='Adjustment TL', PrintName='Adj TL', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2010-10-05 15:08:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_PrintFormatItem_ID=51638
;

-- Oct 5, 2010 3:08:59 PM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=51638
;

-- Oct 5, 2010 3:09:07 PM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem SET IsFixedWidth='Y', IsGroupBy='N', IsHeightOneLine='Y', IsPageBreak='N', MaxWidth=50, SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2010-10-05 15:09:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_PrintFormatItem_ID=51644
;

-- Oct 5, 2010 3:09:19 PM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', MaxWidth=50, PrintName='Adj LL', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2010-10-05 15:09:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_PrintFormatItem_ID=51645
;

-- Oct 5, 2010 3:09:19 PM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=51645
;

-- Oct 5, 2010 3:09:27 PM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintName='Ending Qty', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2010-10-05 15:09:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_PrintFormatItem_ID=51639
;

-- Oct 5, 2010 3:09:27 PM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=51639
;

-- Oct 5, 2010 3:09:35 PM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', MaxWidth=50, SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2010-10-05 15:09:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_PrintFormatItem_ID=51640
;

-- Oct 5, 2010 3:09:47 PM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem SET IsFixedWidth='Y', IsGroupBy='N', IsPageBreak='N', MaxWidth=40, SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2010-10-05 15:09:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_PrintFormatItem_ID=51641
;

-- Oct 5, 2010 3:11:55 PM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsHeightOneLine='Y', IsPageBreak='N', MaxWidth=60, SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2010-10-05 15:11:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_PrintFormatItem_ID=51641
;

-- Oct 5, 2010 3:21:53 PM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintName='Cost LL', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2010-10-05 15:21:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_PrintFormatItem_ID=51644
;

-- Oct 5, 2010 3:21:53 PM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=51644
;

-- Oct 5, 2010 3:22:29 PM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem SET IsFixedWidth='Y', IsGroupBy='N', IsHeightOneLine='Y', IsPageBreak='N', MaxWidth=40, SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2010-10-05 15:22:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_PrintFormatItem_ID=51632
;

-- Oct 5, 2010 3:26:18 PM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintName='B. Qty', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2010-10-05 15:26:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_PrintFormatItem_ID=51634
;

-- Oct 5, 2010 3:26:18 PM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=51634
;

-- Oct 5, 2010 3:26:26 PM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintName='B. Balance', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2010-10-05 15:26:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_PrintFormatItem_ID=51635
;

-- Oct 5, 2010 3:26:26 PM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=51635
;

-- Oct 5, 2010 3:26:38 PM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintName='E. Qty', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2010-10-05 15:26:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_PrintFormatItem_ID=51639
;

-- Oct 5, 2010 3:26:38 PM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=51639
;

-- Oct 5, 2010 3:26:49 PM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintName='E. Balance', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2010-10-05 15:26:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_PrintFormatItem_ID=51640
;

-- Oct 5, 2010 3:26:49 PM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=51640
;

DROP VIEW RV_M_Transaction_Costing;
CREATE OR REPLACE VIEW RV_M_Transaction_Costing AS
SELECT 
t.M_Transaction_ID, t.AD_Client_ID,t.AD_Org_ID,
t.MovementType,t.MovementDate,t.MovementQty,
t.M_AttributeSetInstance_ID, t.M_AttributeSet_ID, t.SerNo, t.Lot, t.M_Lot_ID, t.GuaranteeDate,
p.M_Product_ID,p.Value,p.Name,p.Description,p.UPC,p.SKU,
p.C_UOM_ID,p.M_Product_Category_ID,p.Classification, p.Group1, p.Group2, p.Weight,p.Volume,p.VersionNo,
t.DocumentNo,
t.C_DocType_ID,
t.M_Locator_ID, 
t.X, t.Y, t.Z,
t.M_Warehouse_ID,
t.M_InventoryLine_ID,t.M_Inventory_ID,
t.M_MovementLine_ID,t.M_Movement_ID,
t.M_InOutLine_ID,t.M_InOut_ID,
t.M_ProductionLine_ID,t.M_ProductionPlan_ID,t.M_Production_ID,
t.C_ProjectIssue_ID,t.C_Project_ID,
t.PP_Cost_Collector_ID,
cd.C_AcctSchema_ID,
cd.M_CostType_ID,
cd.M_CostElement_ID,
cd.CostAdjustment,	
cd.CostAdjustmentLL,	
cd.CostAdjustmentDate,
cd.CostAdjustmentDateLL,
cd.DateAcct,
cd.CumulatedQty AS BeginningQtyBalance,
cd.CurrentCostPrice,
cd.CurrentCostPriceLL,
cd.isReversal,
cd.IsSOTrx,
cd.M_CostDetail_ID,
cd.CumulatedAmt + cd.CumulatedAmtLL AS BeginningBalance,
cd.qty,
cd.Amt, 
cd.AmtLL, 
cd.CostAmt,
cd.CostAmtLL,
cd.CumulatedAmt,
cd.CumulatedAmtLL,
cd.CumulatedQty + Qty AS EndingQtyBalance,
cd.CumulatedAmt + cd.CumulatedAmtLL  + CostAmt + CostAmtLL + CostAdjustment + CostAdjustmentLL AS EndingBalance
FROM M_Product p 
INNER JOIN RV_Transaction t ON (t.M_Product_ID=p.M_Product_ID)
LEFT OUTER JOIN M_CostDetail cd ON (cd.M_Transaction_ID=t.M_Transaction_ID AND cd.M_Product_ID=p.M_Product_ID) 
LEFT OUTER JOIN M_CostType ct ON (ct.M_CostType_ID=cd.M_CostType_ID)
LEFT OUTER JOIN M_CostElement ce ON (ce.M_CostElement_ID=cd.M_CostElement_ID);
