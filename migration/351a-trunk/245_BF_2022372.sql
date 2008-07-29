-- Jul 29, 2008 2:35:59 AM CDT
-- Fix Payroll bug
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=55163
;

-- Jul 29, 2008 2:36:07 AM CDT
-- Fix Payroll bug
DELETE  FROM  AD_Field_Trl WHERE AD_Field_ID=55163
;

-- Jul 29, 2008 2:36:07 AM CDT
-- Fix Payroll bug
DELETE FROM AD_Field WHERE AD_Field_ID=55163
;

-- Jul 29, 2008 2:36:16 AM CDT
-- Fix Payroll bug
DELETE  FROM  AD_Column_Trl WHERE AD_Column_ID=55045
;

-- Jul 29, 2008 2:36:16 AM CDT
-- Fix Payroll bug
DELETE FROM AD_Column WHERE AD_Column_ID=55045
;

ALTER TABLE HR_Movement DROP COLUMN Posted;
