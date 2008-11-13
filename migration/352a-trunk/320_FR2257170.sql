-- 2008-nov-11 08:54:58 CET
-- [ 2257170 ] Implement shipper and freight category functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,ReadOnlyLogic,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56376,2111,0,13,259,'M_FreightCategory_ID',TO_DATE('2008-11-11 08:54:56','YYYY-MM-DD HH24:MI:SS'),100,'Category of the Freight','D',1,'Freight Categories are used to calculate the Freight for the Shipper selected','Y','Y','Y','N','N','N','N','N','N','N','N','N','Y','Freight Category','@DocStatus@!=CO',0,TO_DATE('2008-11-11 08:54:56','YYYY-MM-DD HH24:MI:SS'),100,0)
;

INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56376 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

ALTER TABLE C_Order ADD M_FreightCategory_ID NUMBER(10) DEFAULT  NULL 
;

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56376,56446,0,186,TO_DATE('2008-11-11 09:04:37','YYYY-MM-DD HH24:MI:SS'),100,'Category of the Freight',1,'D','Freight Categories are used to calculate the Freight for the Shipper selected','Y','Y','Y','N','N','N','N','N','Freight Category',TO_DATE('2008-11-11 09:04:37','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56446 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=56446
;

UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=2878
;

UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=1107
;

UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=1104
;

UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=1077
;

UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=1103
;

UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=8653
;

UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=1098
;

UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=3272
;

UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=2112
;

UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=2109
;

UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=3113
;

UPDATE AD_Field SET SeqNo=340,IsDisplayed='Y' WHERE AD_Field_ID=1099
;

UPDATE AD_Field SET SeqNo=350,IsDisplayed='Y' WHERE AD_Field_ID=2593
;

UPDATE AD_Field SET SeqNo=360,IsDisplayed='Y' WHERE AD_Field_ID=2589
;

UPDATE AD_Field SET SeqNo=370,IsDisplayed='Y' WHERE AD_Field_ID=1324
;

UPDATE AD_Field SET SeqNo=380,IsDisplayed='Y' WHERE AD_Field_ID=7038
;

UPDATE AD_Field SET SeqNo=390,IsDisplayed='Y' WHERE AD_Field_ID=7826
;

UPDATE AD_Field SET SeqNo=400,IsDisplayed='Y' WHERE AD_Field_ID=7825
;

UPDATE AD_Field SET SeqNo=410,IsDisplayed='Y' WHERE AD_Field_ID=1112
;

UPDATE AD_Field SET SeqNo=420,IsDisplayed='Y' WHERE AD_Field_ID=1113
;

UPDATE AD_Field SET SeqNo=430,IsDisplayed='Y' WHERE AD_Field_ID=1082
;

UPDATE AD_Field SET SeqNo=440,IsDisplayed='Y' WHERE AD_Field_ID=1084
;

UPDATE AD_Field SET SeqNo=450,IsDisplayed='Y' WHERE AD_Field_ID=6560
;

UPDATE AD_Field SET SeqNo=460,IsDisplayed='Y' WHERE AD_Field_ID=1083
;

UPDATE AD_Field SET SeqNo=470,IsDisplayed='Y' WHERE AD_Field_ID=3660
;

UPDATE AD_Field SET SeqNo=480,IsDisplayed='Y' WHERE AD_Field_ID=1000169
;

UPDATE AD_Field SET SeqNo=490,IsDisplayed='Y' WHERE AD_Field_ID=1000170
;

UPDATE AD_Field SET SeqNo=500,IsDisplayed='Y' WHERE AD_Field_ID=52014
;

UPDATE AD_Field SET SeqNo=510,IsDisplayed='Y' WHERE AD_Field_ID=1000171
;

UPDATE AD_Field SET SeqNo=520,IsDisplayed='Y' WHERE AD_Field_ID=1000172
;

UPDATE AD_Field SET AD_FieldGroup_ID=130,Updated=TO_DATE('2008-11-11 09:12:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56446
;

UPDATE AD_Field SET DisplayLogic='@DeliveryViaRule@=''S'' & @OrderType@=''SO''',Updated=TO_DATE('2008-11-11 09:13:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56446
;

UPDATE AD_Field SET DisplayLength=14, IsSameLine='Y',Updated=TO_DATE('2008-11-11 09:13:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56446
;

UPDATE AD_Column SET AD_Reference_ID=19,Updated=TO_DATE('2008-11-11 09:28:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56376
;

INSERT INTO AD_Val_Rule (AD_Client_ID,AD_Org_ID,AD_Val_Rule_ID,Code,Created,CreatedBy,Description,EntityType,IsActive,Name,Type,Updated,UpdatedBy) VALUES (0,0,52034,'M_FreightCategory_ID in (select M_FreightCategory_ID from M_Freight where M_Shipper_ID=@M_Shipper_ID@)',TO_DATE('2008-11-11 10:28:03','YYYY-MM-DD HH24:MI:SS'),100,'Select only freight categories defined at the shipper','D','Y','M_FreightCategory of Shipper','S',TO_DATE('2008-11-11 10:28:03','YYYY-MM-DD HH24:MI:SS'),100)
;

UPDATE AD_Column SET AD_Val_Rule_ID=52034,Updated=TO_DATE('2008-11-11 10:35:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56376
;

UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=2878
;

UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=56446
;