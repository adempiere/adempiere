-- Sep 4, 2009 10:23:26 PM COT
-- FR-2851987-Add company logo
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58183,53909,0,19,741,'Logo_ID',TO_DATE('2009-09-04 22:23:25','YYYY-MM-DD HH24:MI:SS'),100,'U',10,'Y','N','N','N','N','N','N','N','N','N','Logo',TO_DATE('2009-09-04 22:23:25','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 4, 2009 10:23:26 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58183 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 4, 2009 10:23:31 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58184,53909,0,19,516,'Logo_ID',TO_DATE('2009-09-04 22:23:31','YYYY-MM-DD HH24:MI:SS'),100,'U',10,'Y','N','N','N','N','N','N','N','N','N','Logo',TO_DATE('2009-09-04 22:23:31','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 4, 2009 10:23:31 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58184 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 4, 2009 10:23:39 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58185,53909,0,19,496,'Logo_ID',TO_DATE('2009-09-04 22:23:38','YYYY-MM-DD HH24:MI:SS'),100,'U',10,'Y','N','N','N','N','N','N','N','N','N','Logo',TO_DATE('2009-09-04 22:23:38','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 4, 2009 10:23:39 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58185 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 4, 2009 10:23:45 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58186,53909,0,19,618,'Logo_ID',TO_DATE('2009-09-04 22:23:44','YYYY-MM-DD HH24:MI:SS'),100,'U',10,'Y','N','N','N','N','N','N','N','N','N','Logo',TO_DATE('2009-09-04 22:23:44','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 4, 2009 10:23:45 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58186 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 4, 2009 10:23:52 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58187,53909,0,19,53039,'Logo_ID',TO_DATE('2009-09-04 22:23:51','YYYY-MM-DD HH24:MI:SS'),100,'U',10,'Y','N','N','N','N','N','N','N','N','N','Logo',TO_DATE('2009-09-04 22:23:51','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 4, 2009 10:23:52 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58187 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 4, 2009 10:24:00 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58188,53909,0,19,500,'Logo_ID',TO_DATE('2009-09-04 22:23:59','YYYY-MM-DD HH24:MI:SS'),100,'U',10,'Y','N','N','N','N','N','N','N','N','N','Logo',TO_DATE('2009-09-04 22:23:59','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 4, 2009 10:24:00 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58188 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 4, 2009 10:24:04 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58189,53909,0,19,53197,'Logo_ID',TO_DATE('2009-09-04 22:24:04','YYYY-MM-DD HH24:MI:SS'),100,'U',10,'Y','N','N','N','N','N','N','N','N','N','Logo',TO_DATE('2009-09-04 22:24:04','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 4, 2009 10:24:04 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58189 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 4, 2009 10:24:11 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58190,53909,0,19,53205,'Logo_ID',TO_DATE('2009-09-04 22:24:10','YYYY-MM-DD HH24:MI:SS'),100,'U',10,'Y','N','N','N','N','N','N','N','N','N','Logo',TO_DATE('2009-09-04 22:24:10','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 4, 2009 10:24:11 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58190 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 4, 2009 10:24:17 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58191,53909,0,19,53199,'Logo_ID',TO_DATE('2009-09-04 22:24:16','YYYY-MM-DD HH24:MI:SS'),100,'U',10,'Y','N','N','N','N','N','N','N','N','N','Logo',TO_DATE('2009-09-04 22:24:16','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 4, 2009 10:24:17 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58191 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 4, 2009 10:24:42 PM COT
UPDATE AD_Column SET AD_Reference_ID=32, EntityType='D',Updated=TO_DATE('2009-09-04 22:24:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58183
;

-- Sep 4, 2009 10:24:58 PM COT
UPDATE AD_Column SET AD_Reference_ID=32, EntityType='D',Updated=TO_DATE('2009-09-04 22:24:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58184
;

-- Sep 4, 2009 10:25:15 PM COT
UPDATE AD_Column SET AD_Reference_ID=32, EntityType='D',Updated=TO_DATE('2009-09-04 22:25:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58185
;

-- Sep 4, 2009 10:25:31 PM COT
UPDATE AD_Column SET AD_Reference_ID=32, EntityType='D',Updated=TO_DATE('2009-09-04 22:25:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58186
;

-- Sep 4, 2009 10:25:48 PM COT
UPDATE AD_Column SET AD_Reference_ID=32, EntityType='EE01',Updated=TO_DATE('2009-09-04 22:25:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58187
;

-- Sep 4, 2009 10:26:02 PM COT
UPDATE AD_Column SET AD_Reference_ID=32, EntityType='D',Updated=TO_DATE('2009-09-04 22:26:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58188
;

-- Sep 4, 2009 10:26:15 PM COT
UPDATE AD_Column SET AD_Reference_ID=32, EntityType='EE01',Updated=TO_DATE('2009-09-04 22:26:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58189
;

-- Sep 4, 2009 10:26:28 PM COT
UPDATE AD_Column SET AD_Reference_ID=32, EntityType='EE01',Updated=TO_DATE('2009-09-04 22:26:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58190
;

-- Sep 4, 2009 10:26:39 PM COT
UPDATE AD_Column SET AD_Reference_ID=32, EntityType='EE01',Updated=TO_DATE('2009-09-04 22:26:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58191
;

update AD_Table set entitytype = 'EE01' where AD_Table_ID=53039;

