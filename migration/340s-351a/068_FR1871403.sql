-- Jan 16, 2008 8:58:57 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
INSERT INTO AD_Column (Name,IsMandatory,IsTranslated,IsIdentifier,SeqNo,Version,IsActive,AD_Table_ID,AD_Column_ID,ColumnName,AD_Client_ID,AD_Org_ID,FieldLength,IsParent,Created,IsSyncDatabase,AD_Reference_ID,CreatedBy,Updated,AD_Element_ID,IsUpdateable,IsKey,IsSelectionColumn,UpdatedBy,IsAlwaysUpdateable,IsEncrypted,EntityType) VALUES ('Group1','N','N','N',0,0,'Y',477,54237,'Group1',0,0,255,'N',TO_DATE('2008-01-16 20:58:54','YYYY-MM-DD HH24:MI:SS'),'N',10,0,TO_DATE('2008-01-16 20:58:54','YYYY-MM-DD HH24:MI:SS'),52018,'Y','N','N',0,'N','N','D')
;

-- Jan 16, 2008 8:58:57 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54237 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 16, 2008 8:59:29 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
INSERT INTO AD_Column (Name,IsMandatory,IsTranslated,IsIdentifier,SeqNo,Version,IsActive,AD_Table_ID,AD_Column_ID,ColumnName,AD_Client_ID,AD_Org_ID,FieldLength,IsParent,Created,IsSyncDatabase,AD_Reference_ID,CreatedBy,Updated,AD_Element_ID,IsUpdateable,IsKey,IsSelectionColumn,UpdatedBy,IsAlwaysUpdateable,IsEncrypted,EntityType) VALUES ('Group2','N','N','N',0,0,'Y',477,54238,'Group2',0,0,255,'N',TO_DATE('2008-01-16 20:59:26','YYYY-MM-DD HH24:MI:SS'),'N',10,0,TO_DATE('2008-01-16 20:59:26','YYYY-MM-DD HH24:MI:SS'),52019,'Y','N','N',0,'N','N','D')
;

-- Jan 16, 2008 8:59:29 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54238 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 16, 2008 9:00:22 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
INSERT INTO AD_Column (Name,IsMandatory,IsTranslated,Description,IsIdentifier,SeqNo,Help,Version,IsActive,AD_Table_ID,AD_Column_ID,ColumnName,AD_Client_ID,AD_Org_ID,FieldLength,IsParent,Created,IsSyncDatabase,AD_Reference_ID,CreatedBy,Updated,AD_Element_ID,IsUpdateable,IsKey,IsSelectionColumn,UpdatedBy,IsAlwaysUpdateable,IsEncrypted,EntityType) VALUES ('Classification','N','N','Classification for grouping','N',0,'The Classification can be used to optionally group products.',0,'Y',477,54239,'Classification',0,0,12,'N',TO_DATE('2008-01-16 21:00:19','YYYY-MM-DD HH24:MI:SS'),'N',10,0,TO_DATE('2008-01-16 21:00:19','YYYY-MM-DD HH24:MI:SS'),852,'Y','N','N',0,'N','N','D')
;

-- Jan 16, 2008 9:00:22 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54239 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 16, 2008 9:00:28 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
ALTER TABLE M_DiscountSchemaLine ADD Classification NVARCHAR2(12)
;

-- Jan 16, 2008 9:00:34 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
ALTER TABLE M_DiscountSchemaLine ADD Group2 NVARCHAR2(255)
;

-- Jan 16, 2008 9:00:39 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
ALTER TABLE M_DiscountSchemaLine ADD Group1 NVARCHAR2(255)
;


-- Jan 16, 2008 9:02:42 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
INSERT INTO AD_Field (IsEncrypted,AD_Org_ID,UpdatedBy,IsDisplayed,IsCentrallyMaintained,IsActive,Created,AD_Client_ID,AD_Field_ID,Description,DisplayLength,AD_Column_ID,IsFieldOnly,CreatedBy,Help,Updated,Name,AD_Tab_ID,IsSameLine,IsHeading,IsReadOnly,EntityType) VALUES ('N',0,0,'Y','Y','Y',TO_DATE('2008-01-16 21:02:39','YYYY-MM-DD HH24:MI:SS'),0,54331,'Classification for grouping',12,54239,'N',0,'The Classification can be used to optionally group products.',TO_DATE('2008-01-16 21:02:39','YYYY-MM-DD HH24:MI:SS'),'Classification',405,'N','N','N','D')
;

-- Jan 16, 2008 9:02:42 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54331 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Jan 16, 2008 9:02:45 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
INSERT INTO AD_Field (IsEncrypted,AD_Org_ID,UpdatedBy,IsDisplayed,IsCentrallyMaintained,IsActive,Created,AD_Client_ID,AD_Field_ID,DisplayLength,AD_Column_ID,IsFieldOnly,CreatedBy,Updated,Name,AD_Tab_ID,IsSameLine,IsHeading,IsReadOnly,EntityType) VALUES ('N',0,0,'Y','Y','Y',TO_DATE('2008-01-16 21:02:42','YYYY-MM-DD HH24:MI:SS'),0,54332,255,54237,'N',0,TO_DATE('2008-01-16 21:02:42','YYYY-MM-DD HH24:MI:SS'),'Group1',405,'N','N','N','D')
;

-- Jan 16, 2008 9:02:45 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54332 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Jan 16, 2008 9:02:48 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
INSERT INTO AD_Field (IsEncrypted,AD_Org_ID,UpdatedBy,IsDisplayed,IsCentrallyMaintained,IsActive,Created,AD_Client_ID,AD_Field_ID,DisplayLength,AD_Column_ID,IsFieldOnly,CreatedBy,Updated,Name,AD_Tab_ID,IsSameLine,IsHeading,IsReadOnly,EntityType) VALUES ('N',0,0,'Y','Y','Y',TO_DATE('2008-01-16 21:02:45','YYYY-MM-DD HH24:MI:SS'),0,54333,255,54238,'N',0,TO_DATE('2008-01-16 21:02:45','YYYY-MM-DD HH24:MI:SS'),'Group2',405,'N','N','N','D')
;

-- Jan 16, 2008 9:02:48 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54333 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Jan 16, 2008 9:04:13 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=5243
;

-- Jan 16, 2008 9:04:13 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
UPDATE AD_Field SET SeqNo=10,IsDisplayed='Y' WHERE AD_Field_ID=5244
;

-- Jan 16, 2008 9:04:13 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
UPDATE AD_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=5245
;

-- Jan 16, 2008 9:04:13 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=5247
;

-- Jan 16, 2008 9:04:13 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=5246
;

-- Jan 16, 2008 9:04:13 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=5248
;

-- Jan 16, 2008 9:04:13 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=8642
;

-- Jan 16, 2008 9:04:13 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=5253
;

-- Jan 16, 2008 9:04:13 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=5250
;

-- Jan 16, 2008 9:04:13 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=5251
;

-- Jan 16, 2008 9:04:13 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=54331
;

-- Jan 16, 2008 9:04:13 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=54332
;

-- Jan 16, 2008 9:04:13 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=54333
;

-- Jan 16, 2008 9:04:13 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=5254
;

-- Jan 16, 2008 9:04:13 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=5258
;

-- Jan 16, 2008 9:04:13 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=5255
;

-- Jan 16, 2008 9:04:13 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=5259
;

-- Jan 16, 2008 9:04:13 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=5256
;

-- Jan 16, 2008 9:04:13 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=5257
;

-- Jan 16, 2008 9:04:13 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=5349
;

-- Jan 16, 2008 9:04:13 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=5260
;

-- Jan 16, 2008 9:04:13 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=5264
;

-- Jan 16, 2008 9:04:13 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=5261
;

-- Jan 16, 2008 9:04:13 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=5265
;

-- Jan 16, 2008 9:04:13 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=5262
;

-- Jan 16, 2008 9:04:13 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=5263
;

-- Jan 16, 2008 9:04:13 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=5350
;

-- Jan 16, 2008 9:04:13 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=5266
;

-- Jan 16, 2008 9:04:13 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=5270
;

-- Jan 16, 2008 9:04:13 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=5267
;

-- Jan 16, 2008 9:04:13 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=5271
;

-- Jan 16, 2008 9:04:13 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=5268
;

-- Jan 16, 2008 9:04:13 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=5269
;

-- Jan 16, 2008 9:04:13 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
UPDATE AD_Field SET SeqNo=340,IsDisplayed='Y' WHERE AD_Field_ID=5348
;

-- Jan 16, 2008 9:04:22 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
UPDATE AD_Field SET IsSameLine='N',Updated=TO_DATE('2008-01-16 21:04:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=5251
;

-- Jan 16, 2008 9:04:25 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2008-01-16 21:04:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=5249
;

-- Jan 16, 2008 9:04:36 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
UPDATE AD_Field SET IsSameLine='N',Updated=TO_DATE('2008-01-16 21:04:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=5249
;

-- Jan 16, 2008 9:04:42 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2008-01-16 21:04:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=54331
;

-- Jan 16, 2008 9:04:45 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2008-01-16 21:04:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=54333
;

-- Jan 16, 2008 9:09:32 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
UPDATE AD_Field SET DisplayLength=20,Updated=TO_DATE('2008-01-16 21:09:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=54332
;

-- Jan 16, 2008 9:09:36 PM CST
-- [ 1871403 ] Adding new field of product into Price List Schema
UPDATE AD_Field SET DisplayLength=20,Updated=TO_DATE('2008-01-16 21:09:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=54333
;

