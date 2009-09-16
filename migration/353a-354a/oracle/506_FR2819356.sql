-- Jul 17, 2009 6:29:53 PM MYT
-- Add more chart type support to Performance indicator - ID: 2819356
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53895,0,'GoalDisplay',TO_DATE('2009-07-17 18:29:50','YYYY-MM-DD HH24:MI:SS'),100,'Type of goal display on dashboard','D','Display goal on dashboard as html table or graph.','Y','Goal Display','Goal Display',TO_DATE('2009-07-17 18:29:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 17, 2009 6:29:53 PM MYT
-- Add more chart type support to Performance indicator - ID: 2819356
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53895 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Jul 17, 2009 6:31:10 PM MYT
-- Add more chart type support to Performance indicator - ID: 2819356
INSERT INTO AD_Reference (AD_Client_ID,AD_Org_ID,AD_Reference_ID,Created,CreatedBy,Description,EntityType,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType) VALUES (0,0,53316,TO_DATE('2009-07-17 18:31:09','YYYY-MM-DD HH24:MI:SS'),100,'Type of goal display on dashboard','D','Y','N','PA_DashboardContent GoalDisplay',TO_DATE('2009-07-17 18:31:09','YYYY-MM-DD HH24:MI:SS'),100,'L')
;

-- Jul 17, 2009 6:31:10 PM MYT
-- Add more chart type support to Performance indicator - ID: 2819356
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53316 AND EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Reference_ID!=t.AD_Reference_ID)
;

-- Jul 17, 2009 6:31:36 PM MYT
-- Add more chart type support to Performance indicator - ID: 2819356
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53501,53316,TO_DATE('2009-07-17 18:31:35','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','HTML Table',TO_DATE('2009-07-17 18:31:35','YYYY-MM-DD HH24:MI:SS'),100,'T')
;

-- Jul 17, 2009 6:31:36 PM MYT
-- Add more chart type support to Performance indicator - ID: 2819356
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53501 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Jul 17, 2009 6:32:00 PM MYT
-- Add more chart type support to Performance indicator - ID: 2819356
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53502,53316,TO_DATE('2009-07-17 18:31:59','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Chart',TO_DATE('2009-07-17 18:31:59','YYYY-MM-DD HH24:MI:SS'),100,'C')
;

-- Jul 17, 2009 6:32:00 PM MYT
-- Add more chart type support to Performance indicator - ID: 2819356
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53502 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Jul 17, 2009 6:33:02 PM MYT
-- Add more chart type support to Performance indicator - ID: 2819356
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57922,53895,0,17,53316,50010,'GoalDisplay',TO_DATE('2009-07-17 18:33:01','YYYY-MM-DD HH24:MI:SS'),100,'T','Type of goal display on dashboard','D',1,'Display goal on dashboard as html table or graph.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Goal Display',0,TO_DATE('2009-07-17 18:33:01','YYYY-MM-DD HH24:MI:SS'),100,1.000000000000)
;

-- Jul 17, 2009 6:33:02 PM MYT
-- Add more chart type support to Performance indicator - ID: 2819356
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57922 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 17, 2009 6:33:18 PM MYT
-- Add more chart type support to Performance indicator - ID: 2819356
ALTER TABLE PA_DashboardContent ADD GoalDisplay CHAR(1) DEFAULT 'T'
;

-- Jul 17, 2009 6:35:13 PM MYT
-- Add more chart type support to Performance indicator - ID: 2819356
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,57922,57344,0,50010,TO_DATE('2009-07-17 18:35:11','YYYY-MM-DD HH24:MI:SS'),100,'Type of goal display on dashboard',14,'@PA_Goal_ID@!0','D','Display goal on dashboard as html table or graph.','Y','Y','Y','N','N','N','N','N','Goal Display',120,0,TO_DATE('2009-07-17 18:35:11','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 17, 2009 6:35:13 PM MYT
-- Add more chart type support to Performance indicator - ID: 2819356
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57344 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

COMMIT;


