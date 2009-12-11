-- Dec 10, 2009 8:36:29 PM COT
-- 2897748_New role defaults
UPDATE AD_Field SET IsActive='N',Updated=TO_DATE('2009-12-10 20:36:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=12641
;

-- Dec 10, 2009 8:36:56 PM COT
UPDATE AD_Column SET DefaultValue='Y',Updated=TO_DATE('2009-12-10 20:36:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=50198
;

-- Dec 10, 2009 8:36:57 PM COT
UPDATE AD_Column SET DefaultValue='Y',Updated=TO_DATE('2009-12-10 20:36:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=50199
;

-- Dec 10, 2009 8:36:58 PM COT
UPDATE AD_Column SET DefaultValue='Y',Updated=TO_DATE('2009-12-10 20:36:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=50200
;

-- Dec 10, 2009 8:36:59 PM COT
UPDATE AD_Column SET DefaultValue='Y',Updated=TO_DATE('2009-12-10 20:36:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=50201
;

-- Dec 10, 2009 8:37:00 PM COT
UPDATE AD_Column SET DefaultValue='Y',Updated=TO_DATE('2009-12-10 20:37:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=50202
;

-- Dec 10, 2009 8:37:02 PM COT
UPDATE AD_Column SET DefaultValue='Y',Updated=TO_DATE('2009-12-10 20:37:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=50203
;

-- Dec 10, 2009 8:37:02 PM COT
UPDATE AD_Column SET DefaultValue='Y',Updated=TO_DATE('2009-12-10 20:37:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=50204
;

-- Dec 10, 2009 8:37:03 PM COT
UPDATE AD_Column SET DefaultValue='Y',Updated=TO_DATE('2009-12-10 20:37:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=50205
;

-- Dec 10, 2009 8:37:05 PM COT
UPDATE AD_Column SET DefaultValue='Y',Updated=TO_DATE('2009-12-10 20:37:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=50207
;

-- Dec 10, 2009 8:37:07 PM COT
UPDATE AD_Column SET DefaultValue='Y',Updated=TO_DATE('2009-12-10 20:37:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=50208
;

-- Dec 10, 2009 8:49:53 PM COT
ALTER TABLE AD_Role MODIFY Allow_Info_Account CHAR(1) DEFAULT 'Y'
;

-- Dec 10, 2009 8:49:55 PM COT
UPDATE AD_Role SET Allow_Info_Account='Y' WHERE Allow_Info_Account IS NULL
;

-- Dec 10, 2009 8:50:06 PM COT
ALTER TABLE AD_Role MODIFY Allow_Info_Asset CHAR(1) DEFAULT 'Y'
;

-- Dec 10, 2009 8:50:06 PM COT
UPDATE AD_Role SET Allow_Info_Asset='Y' WHERE Allow_Info_Asset IS NULL
;

-- Dec 10, 2009 8:50:15 PM COT
ALTER TABLE AD_Role MODIFY Allow_Info_BPartner CHAR(1) DEFAULT 'Y'
;

-- Dec 10, 2009 8:50:15 PM COT
UPDATE AD_Role SET Allow_Info_BPartner='Y' WHERE Allow_Info_BPartner IS NULL
;

-- Dec 10, 2009 8:50:28 PM COT
ALTER TABLE AD_Role MODIFY Allow_Info_CashJournal CHAR(1) DEFAULT 'Y'
;

-- Dec 10, 2009 8:50:28 PM COT
UPDATE AD_Role SET Allow_Info_CashJournal='Y' WHERE Allow_Info_CashJournal IS NULL
;

-- Dec 10, 2009 8:50:46 PM COT
ALTER TABLE AD_Role MODIFY Allow_Info_InOut CHAR(1) DEFAULT 'Y'
;

-- Dec 10, 2009 8:50:46 PM COT
UPDATE AD_Role SET Allow_Info_InOut='Y' WHERE Allow_Info_InOut IS NULL
;

-- Dec 10, 2009 8:50:54 PM COT
ALTER TABLE AD_Role MODIFY Allow_Info_Invoice CHAR(1) DEFAULT 'Y'
;

-- Dec 10, 2009 8:50:54 PM COT
UPDATE AD_Role SET Allow_Info_Invoice='Y' WHERE Allow_Info_Invoice IS NULL
;

-- Dec 10, 2009 8:51:06 PM COT
ALTER TABLE AD_Role MODIFY Allow_Info_Order CHAR(1) DEFAULT 'Y'
;

-- Dec 10, 2009 8:51:07 PM COT
UPDATE AD_Role SET Allow_Info_Order='Y' WHERE Allow_Info_Order IS NULL
;

-- Dec 10, 2009 8:51:20 PM COT
ALTER TABLE AD_Role MODIFY Allow_Info_Payment CHAR(1) DEFAULT 'Y'
;

-- Dec 10, 2009 8:51:20 PM COT
UPDATE AD_Role SET Allow_Info_Payment='Y' WHERE Allow_Info_Payment IS NULL
;

-- Dec 10, 2009 8:51:37 PM COT
ALTER TABLE AD_Role MODIFY Allow_Info_Resource CHAR(1) DEFAULT 'Y'
;

-- Dec 10, 2009 8:51:37 PM COT
UPDATE AD_Role SET Allow_Info_Resource='Y' WHERE Allow_Info_Resource IS NULL
;

-- Dec 10, 2009 8:51:45 PM COT
ALTER TABLE AD_Role MODIFY Allow_Info_Schedule CHAR(1) DEFAULT 'Y'
;

-- Dec 10, 2009 8:51:46 PM COT
UPDATE AD_Role SET Allow_Info_Schedule='Y' WHERE Allow_Info_Schedule IS NULL
;

