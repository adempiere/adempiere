-- Aug 7, 2008 9:55:42 PM CDT
-- Water Mark for Document Printed
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56301,1639,0,19,523,'AD_Image_ID',TO_TIMESTAMP('2008-08-07 21:55:41','YYYY-MM-DD HH24:MI:SS'),0,'Image or Icon','D',22,'Images and Icon can be used to display supported graphic formats (gif, jpg, png).
You can either load the image (in the database) or point to a graphic via a URI (i.e. it can point to a resource, http address)','Y','N','N','N','N','N','N','N','N','N','N','N','Y','Image',0,TO_TIMESTAMP('2008-08-07 21:55:41','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Aug 7, 2008 9:55:42 PM CDT
-- Water Mark for Document Printed
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56301 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Aug 7, 2008 10:05:02 PM CDT
-- Water Mark for Document Printed
ALTER TABLE AD_PrintTableFormat ADD COLUMN AD_Image_ID NUMERIC(10)
;

-- Aug 7, 2008 10:05:41 PM CDT
-- Water Mark for Document Printed
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56301,56335,0,435,TO_TIMESTAMP('2008-08-07 22:05:40','YYYY-MM-DD HH24:MI:SS'),0,'Image or Icon',22,'D','Images and Icon can be used to display supported graphic formats (gif, jpg, png).
You can either load the image (in the database) or point to a graphic via a URI (i.e. it can point to a resource, http address)','Y','Y','Y','N','N','N','N','N','Image',TO_TIMESTAMP('2008-08-07 22:05:40','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Aug 7, 2008 10:05:41 PM CDT
-- Water Mark for Document Printed
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56335 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Aug 7, 2008 10:05:57 PM CDT
-- Water Mark for Document Printed
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=8299
;

-- Aug 7, 2008 10:05:57 PM CDT
-- Water Mark for Document Printed
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=8303
;

-- Aug 7, 2008 10:05:57 PM CDT
-- Water Mark for Document Printed
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=8302
;

-- Aug 7, 2008 10:05:57 PM CDT
-- Water Mark for Document Printed
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=8298
;

-- Aug 7, 2008 10:05:57 PM CDT
-- Water Mark for Document Printed
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=8304
;

-- Aug 7, 2008 10:05:57 PM CDT
-- Water Mark for Document Printed
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=8301
;

-- Aug 7, 2008 10:05:57 PM CDT
-- Water Mark for Document Printed
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=5794
;

-- Aug 7, 2008 10:05:57 PM CDT
-- Water Mark for Document Printed
UPDATE AD_Field SET SeqNo=10,IsDisplayed='Y' WHERE AD_Field_ID=5796
;

-- Aug 7, 2008 10:05:57 PM CDT
-- Water Mark for Document Printed
UPDATE AD_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=5798
;

-- Aug 7, 2008 10:05:57 PM CDT
-- Water Mark for Document Printed
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=5803
;

-- Aug 7, 2008 10:05:57 PM CDT
-- Water Mark for Document Printed
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=5786
;

-- Aug 7, 2008 10:05:57 PM CDT
-- Water Mark for Document Printed
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=5790
;

-- Aug 7, 2008 10:05:57 PM CDT
-- Water Mark for Document Printed
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=8305
;

-- Aug 7, 2008 10:05:57 PM CDT
-- Water Mark for Document Printed
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=5791
;

-- Aug 7, 2008 10:05:57 PM CDT
-- Water Mark for Document Printed
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=5800
;

-- Aug 7, 2008 10:05:57 PM CDT
-- Water Mark for Document Printed
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=5792
;

-- Aug 7, 2008 10:05:57 PM CDT
-- Water Mark for Document Printed
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=8948
;

-- Aug 7, 2008 10:05:57 PM CDT
-- Water Mark for Document Printed
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=5787
;

-- Aug 7, 2008 10:05:57 PM CDT
-- Water Mark for Document Printed
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=8947
;

-- Aug 7, 2008 10:05:57 PM CDT
-- Water Mark for Document Printed
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=8946
;

-- Aug 7, 2008 10:05:57 PM CDT
-- Water Mark for Document Printed
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=5802
;

-- Aug 7, 2008 10:05:57 PM CDT
-- Water Mark for Document Printed
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=5788
;

-- Aug 7, 2008 10:05:57 PM CDT
-- Water Mark for Document Printed
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=5789
;

-- Aug 7, 2008 10:05:57 PM CDT
-- Water Mark for Document Printed
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=5799
;

-- Aug 7, 2008 10:05:57 PM CDT
-- Water Mark for Document Printed
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=8949
;

-- Aug 7, 2008 10:05:57 PM CDT
-- Water Mark for Document Printed
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=8950
;

-- Aug 7, 2008 10:05:57 PM CDT
-- Water Mark for Document Printed
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=5801
;

-- Aug 7, 2008 10:05:57 PM CDT
-- Water Mark for Document Printed
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=5797
;

-- Aug 7, 2008 10:05:57 PM CDT
-- Water Mark for Document Printed
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=5795
;

-- Aug 7, 2008 10:05:57 PM CDT
-- Water Mark for Document Printed
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=5793
;

-- Aug 7, 2008 10:05:57 PM CDT
-- Water Mark for Document Printed
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=8306
;

-- Aug 7, 2008 10:05:57 PM CDT
-- Water Mark for Document Printed
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=8300
;

-- Aug 7, 2008 10:05:57 PM CDT
-- Water Mark for Document Printed
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=56335
;

-- Aug 7, 2008 10:06:41 PM CDT
-- Water Mark for Document Printed
UPDATE AD_Field SET DisplayLogic='@ImageIsAttached@=''N''',Updated=TO_TIMESTAMP('2008-08-07 22:06:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=8300
;

-- Aug 7, 2008 10:06:45 PM CDT
-- Water Mark for Document Printed
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2008-08-07 22:06:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=8300
;
-- Aug 7, 2008 9:47:57 PM CDT
-- Water Mark for Document Printed
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=8306
;

-- Aug 7, 2008 9:47:57 PM CDT
-- Water Mark for Document Printed
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=8300
;

-- Aug 7, 2008 9:47:57 PM CDT
-- Water Mark for Document Printed
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=56335
;

-- Aug 7, 2008 9:52:03 PM CDT
-- Water Mark for Document Printed
UPDATE AD_Field SET Description='Image Water Mark', Help='This Image is show when a document is printed for second time',Updated=TO_TIMESTAMP('2008-08-07 21:52:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56335
;

-- Aug 7, 2008 9:52:03 PM CDT
-- Water Mark for Document Printed
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56335
;


