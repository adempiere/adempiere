-- Mar 13, 2010 11:54:49 PM COT
-- BF_2968442_Post without Application Server
UPDATE AD_Element SET Description='Post the accounting immediately for testing (Deprecated)', Help='If selected, the accounting consequences are immediately generated when completing a document.  Otherwise the document is posted by a batch process.  You should select this only if you are testing.
Deprecated column - use instead the functionality Client Accounting.', Name='Post Immediately (Deprecated)',Updated=TO_TIMESTAMP('2010-03-13 23:54:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=2843
;

-- Mar 13, 2010 11:54:49 PM COT
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2843
;

-- Mar 13, 2010 11:54:49 PM COT
UPDATE AD_Column SET ColumnName='IsPostImmediate', Name='Post Immediately (Deprecated)', Description='Post the accounting immediately for testing (Deprecated)', Help='If selected, the accounting consequences are immediately generated when completing a document.  Otherwise the document is posted by a batch process.  You should select this only if you are testing.
Deprecated column - use instead the functionality Client Accounting.' WHERE AD_Element_ID=2843
;

-- Mar 13, 2010 11:54:50 PM COT
UPDATE AD_Process_Para SET ColumnName='IsPostImmediate', Name='Post Immediately (Deprecated)', Description='Post the accounting immediately for testing (Deprecated)', Help='If selected, the accounting consequences are immediately generated when completing a document.  Otherwise the document is posted by a batch process.  You should select this only if you are testing.
Deprecated column - use instead the functionality Client Accounting.', AD_Element_ID=2843 WHERE UPPER(ColumnName)='ISPOSTIMMEDIATE' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 13, 2010 11:54:50 PM COT
UPDATE AD_Process_Para SET ColumnName='IsPostImmediate', Name='Post Immediately (Deprecated)', Description='Post the accounting immediately for testing (Deprecated)', Help='If selected, the accounting consequences are immediately generated when completing a document.  Otherwise the document is posted by a batch process.  You should select this only if you are testing.
Deprecated column - use instead the functionality Client Accounting.' WHERE AD_Element_ID=2843 AND IsCentrallyMaintained='Y'
;

-- Mar 13, 2010 11:54:50 PM COT
UPDATE AD_Field SET Name='Post Immediately (Deprecated)', Description='Post the accounting immediately for testing (Deprecated)', Help='If selected, the accounting consequences are immediately generated when completing a document.  Otherwise the document is posted by a batch process.  You should select this only if you are testing.
Deprecated column - use instead the functionality Client Accounting.' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=2843) AND IsCentrallyMaintained='Y'
;

-- Mar 13, 2010 11:54:50 PM COT
UPDATE AD_PrintFormatItem SET PrintName='Post Immediate', Name='Post Immediately (Deprecated)' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=2843)
;

-- Mar 13, 2010 11:55:40 PM COT
UPDATE AD_Field SET IsSameLine='N',Updated=TO_TIMESTAMP('2010-03-13 23:55:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=12326
;

-- Mar 13, 2010 11:55:55 PM COT
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=12327
;

-- Mar 13, 2010 11:55:55 PM COT
UPDATE AD_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=317
;

-- Mar 13, 2010 11:55:55 PM COT
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=318
;

-- Mar 13, 2010 11:55:55 PM COT
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=319
;

-- Mar 13, 2010 11:55:55 PM COT
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=10318
;

-- Mar 13, 2010 11:55:55 PM COT
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=5160
;

-- Mar 13, 2010 11:55:55 PM COT
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=5759
;

-- Mar 13, 2010 11:55:55 PM COT
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=11025
;

-- Mar 13, 2010 11:55:55 PM COT
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=11205
;

-- Mar 13, 2010 11:55:55 PM COT
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=3813
;

-- Mar 13, 2010 11:55:55 PM COT
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=5887
;

-- Mar 13, 2010 11:55:55 PM COT
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=5161
;

-- Mar 13, 2010 11:55:55 PM COT
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=5162
;

-- Mar 13, 2010 11:55:55 PM COT
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=5163
;

-- Mar 13, 2010 11:55:55 PM COT
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=5164
;

-- Mar 13, 2010 11:55:55 PM COT
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=12099
;

-- Mar 13, 2010 11:55:55 PM COT
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=12098
;

-- Mar 13, 2010 11:55:55 PM COT
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=11024
;

-- Mar 13, 2010 11:55:55 PM COT
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=12326
;

-- Mar 13, 2010 11:55:55 PM COT
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=50158
;

-- Mar 13, 2010 11:55:55 PM COT
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=50159
;

-- Mar 13, 2010 11:55:55 PM COT
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=50160
;

-- Mar 13, 2010 11:55:55 PM COT
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=50184
;

-- Mar 13, 2010 11:55:56 PM COT
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=50185
;

-- Mar 13, 2010 11:55:56 PM COT
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=50186
;

-- Mar 13, 2010 11:55:56 PM COT
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=54238
;

-- Mar 13, 2010 11:55:56 PM COT
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=54680
;

