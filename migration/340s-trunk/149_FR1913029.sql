-- Mar 13, 2008 8:15:22 AM BRT
-- FR [ 1913092 ] Add the Payment Rule to Commissioning
INSERT INTO AD_Column (Name,IsMandatory,IsTranslated,Description,IsIdentifier,SeqNo,Help,AD_Reference_Value_ID,Version,IsActive,AD_Table_ID,AD_Column_ID,ColumnName,AD_Client_ID,AD_Org_ID,FieldLength,IsParent,Created,IsSyncDatabase,AD_Reference_ID,CreatedBy,Updated,AD_Element_ID,IsUpdateable,IsKey,IsSelectionColumn,UpdatedBy,IsAlwaysUpdateable,IsEncrypted,EntityType) VALUES ('Payment Rule','N','N','How you pay the invoice','N',0,'The Payment Rule indicates the method of invoice payment.',195,0,'Y',431,54679,'PaymentRule',0,0,1,'N',TO_TIMESTAMP('2008-03-13 08:14:59','YYYY-MM-DD HH24:MI:SS'),'N',28,100,TO_TIMESTAMP('2008-03-13 08:14:59','YYYY-MM-DD HH24:MI:SS'),1143,'Y','N','N',100,'N','N','D')
;

-- Mar 13, 2008 8:15:22 AM BRT
-- FR [ 1913092 ] Add the Payment Rule to Commissioning
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54679 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 13, 2008 8:15:43 AM BRT
-- FR [ 1913092 ] Add the Payment Rule to Commissioning
ALTER TABLE C_CommissionLine ADD PaymentRule CHAR(1)
;

-- Mar 13, 2008 8:19:20 AM BRT
-- FR [ 1913092 ] Add the Payment Rule to Commissioning
INSERT INTO AD_Field (IsEncrypted,SortNo,AD_Org_ID,UpdatedBy,IsDisplayed,IsCentrallyMaintained,IsActive,Created,AD_Client_ID,AD_Field_ID,Description,DisplayLength,AD_Column_ID,IsFieldOnly,CreatedBy,Help,Updated,Name,AD_Tab_ID,IsSameLine,IsHeading,SeqNo,IsReadOnly,AD_FieldGroup_ID,EntityType) VALUES ('N',0,0,100,'Y','Y','Y',TO_TIMESTAMP('2008-03-13 08:19:17','YYYY-MM-DD HH24:MI:SS'),0,54717,'How you pay the invoice',20,54679,'N',100,'The Payment Rule indicates the method of invoice payment.',TO_TIMESTAMP('2008-03-13 08:19:17','YYYY-MM-DD HH24:MI:SS'),'Payment Rule',356,'N','N',200,'N',104,'D')
;

-- Mar 13, 2008 8:19:20 AM BRT
-- FR [ 1913092 ] Add the Payment Rule to Commissioning
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54717 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 13, 2008 8:20:21 AM BRT
-- FR [ 1913092 ] Add the Payment Rule to Commissioning
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=4480
;

-- Mar 13, 2008 8:20:21 AM BRT
-- FR [ 1913092 ] Add the Payment Rule to Commissioning
UPDATE AD_Field SET SeqNo=10,IsDisplayed='Y' WHERE AD_Field_ID=4473
;

-- Mar 13, 2008 8:20:21 AM BRT
-- FR [ 1913092 ] Add the Payment Rule to Commissioning
UPDATE AD_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=4474
;

-- Mar 13, 2008 8:20:21 AM BRT
-- FR [ 1913092 ] Add the Payment Rule to Commissioning
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=4479
;

-- Mar 13, 2008 8:20:21 AM BRT
-- FR [ 1913092 ] Add the Payment Rule to Commissioning
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=4485
;

-- Mar 13, 2008 8:20:21 AM BRT
-- FR [ 1913092 ] Add the Payment Rule to Commissioning
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=4482
;

-- Mar 13, 2008 8:20:21 AM BRT
-- FR [ 1913092 ] Add the Payment Rule to Commissioning
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=4483
;

-- Mar 13, 2008 8:20:21 AM BRT
-- FR [ 1913092 ] Add the Payment Rule to Commissioning
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=4578
;

-- Mar 13, 2008 8:20:21 AM BRT
-- FR [ 1913092 ] Add the Payment Rule to Commissioning
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=4579
;

-- Mar 13, 2008 8:20:21 AM BRT
-- FR [ 1913092 ] Add the Payment Rule to Commissioning
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=4477
;

-- Mar 13, 2008 8:20:21 AM BRT
-- FR [ 1913092 ] Add the Payment Rule to Commissioning
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=4478
;

-- Mar 13, 2008 8:20:21 AM BRT
-- FR [ 1913092 ] Add the Payment Rule to Commissioning
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=4486
;

-- Mar 13, 2008 8:20:21 AM BRT
-- FR [ 1913092 ] Add the Payment Rule to Commissioning
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=4487
;

-- Mar 13, 2008 8:20:21 AM BRT
-- FR [ 1913092 ] Add the Payment Rule to Commissioning
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=4481
;

-- Mar 13, 2008 8:20:21 AM BRT
-- FR [ 1913092 ] Add the Payment Rule to Commissioning
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=54717
;

-- Mar 13, 2008 8:21:40 AM BRT
-- FR [ 1913092 ] Add the Payment Rule to Commissioning
UPDATE AD_Field SET DisplayLength=14, IsSameLine='Y',Updated=TO_TIMESTAMP('2008-03-13 08:21:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=54717
;

