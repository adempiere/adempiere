-- Jun 1, 2009 3:51:17 PM MYT
-- Posted button hidden for matching document - ID: 2799353
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=12168
;

-- Jun 1, 2009 3:51:17 PM MYT
-- Posted button hidden for matching document - ID: 2799353
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=11151
;

-- Jun 1, 2009 3:52:37 PM MYT
-- Posted button hidden for matching document - ID: 2799353
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=12170
;

-- Jun 1, 2009 3:52:37 PM MYT
-- Posted button hidden for matching document - ID: 2799353
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=11098
;

-- Jun 1, 2009 3:53:35 PM MYT
-- Posted button hidden for matching document - ID: 2799353
UPDATE AD_Field SET DisplayLogic='@Processed@=Y & @#ShowAcct@=Y',Updated=TO_TIMESTAMP('2009-06-01 15:53:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=11098
;

-- Jun 1, 2009 3:54:02 PM MYT
-- Posted button hidden for matching document - ID: 2799353
UPDATE AD_Field SET DisplayLogic='@Processed@=Y & @#ShowAcct@=Y',Updated=TO_TIMESTAMP('2009-06-01 15:54:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=11151
;

COMMIT;


