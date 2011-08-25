-- Aug 14, 2011 2:44:55 PM MST
-- modify name size to support russian region
UPDATE AD_Column SET FieldLength=255,Updated=TO_TIMESTAMP('2011-08-14 14:44:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=864
;

-- Aug 14, 2011 2:46:10 PM MST
-- modify name size to support russian region
INSERT INTO t_alter_column values('c_region','Name','VARCHAR(255)',null,null)
;

-- Aug 14, 2011 2:48:18 PM MST
-- modify name size to support russian region
UPDATE AD_Column SET FieldLength=255,Updated=TO_TIMESTAMP('2011-08-14 14:48:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=7902
;

-- Aug 14, 2011 2:48:24 PM MST
-- modify name size to support russian region
INSERT INTO t_alter_column values('i_bpartner','RegionName','VARCHAR(255)',null,'NULL')
;

