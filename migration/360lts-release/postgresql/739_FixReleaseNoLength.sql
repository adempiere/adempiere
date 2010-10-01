-- Jul 22, 2010 6:20:04 PM COT
-- Fix ReleaseNo Length
UPDATE AD_Column SET FieldLength=10,Updated=TO_TIMESTAMP('2010-07-22 18:20:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=14655
;

-- Jul 22, 2010 6:20:08 PM COT
-- Fix ReleaseNo Length
INSERT INTO t_alter_column values('ad_issue','ReleaseNo','VARCHAR(10)',null,'.')
;

-- Jul 22, 2010 6:20:35 PM COT
-- Fix ReleaseNo Length
UPDATE AD_Column SET FieldLength=10,Updated=TO_TIMESTAMP('2010-07-22 18:20:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=14896
;

-- Jul 22, 2010 6:20:38 PM COT
-- Fix ReleaseNo Length
INSERT INTO t_alter_column values('r_issueknown','ReleaseNo','VARCHAR(10)',null,null)
;

-- Jul 22, 2010 6:21:40 PM COT
-- Fix ReleaseNo Length
UPDATE AD_Column SET FieldLength=10,Updated=TO_TIMESTAMP('2010-07-22 18:21:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=54371
;

-- Jul 22, 2010 6:21:44 PM COT
-- Fix ReleaseNo Length
INSERT INTO t_alter_column values('ad_migrationscript','ReleaseNo','VARCHAR(10)',null,null)
;

