-- May 28, 2011 1:07:06 AM CDT
-- fixed Qty Entry
UPDATE AD_Column SET DefaultValue=NULL,Updated=TO_TIMESTAMP('2011-05-28 01:07:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=53581
;

-- May 28, 2011 1:08:35 AM CDT
-- fixed Qty Entry
INSERT INTO t_alter_column values('pp_order_bomline','QtyEntered','NUMERIC',null,'NULL')
;

-- May 28, 2011 1:32:30 AM CDT
-- fixed Qty Entry
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_TIMESTAMP('2011-05-28 01:32:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=53776
;
