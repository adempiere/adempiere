-- Feb 15, 2010 5:40:55 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Column SET AD_Reference_ID=19, AD_Reference_Value_ID=NULL, IsIdentifier='Y', IsUpdateable='N', SeqNo=1,Updated=TO_DATE('2010-02-15 17:40:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53389
;

-- Feb 15, 2010 5:41:22 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Column SET Help=NULL, IsIdentifier='Y', SeqNo=2,Updated=TO_DATE('2010-02-15 17:41:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53400
;

-- Feb 15, 2010 5:41:22 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Field SET Name='Resource', Description='Resource', Help=NULL WHERE AD_Column_ID=53400 AND IsCentrallyMaintained='Y'
;

-- Feb 15, 2010 5:41:44 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Column SET AD_Reference_ID=19, AD_Reference_Value_ID=NULL, IsIdentifier='Y', SeqNo=3,Updated=TO_DATE('2010-02-15 17:41:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53390
;

