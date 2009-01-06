-- Jan 6, 2009 4:04:26 PM COT
-- Bug 2490831 Cost Detail record affects std cost reposting
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=12153
;

UPDATE AD_Field SET IsReadOnly='N',Updated=TO_DATE('2009-01-06 16:04:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=12153
;

UPDATE AD_Column SET IsAlwaysUpdateable='Y',Updated=TO_DATE('2009-01-06 16:09:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=14187
;

