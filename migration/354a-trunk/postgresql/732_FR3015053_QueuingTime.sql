-- 06-13-2010 05:36:04 PM CST
-- queuing time_explanation
UPDATE AD_Field SET Description='Queue time is the time a ob waits at a work center before begin handled.', Help='Queuing time has no implication on costs, but on Capacity Requirement Planning (CRP) to calculate the total time needed to manufacture a product.',Updated=TO_TIMESTAMP('2010-06-13 17:36:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=53405
;

-- 06-13-2010 05:45:24 PM CST
-- queuing time_explanation
UPDATE AD_Field SET Name='Queuing Time', Description='Queue time is the time a ob waits at a work center before begin handled.', Help='Queuing time has no implication on costs, but on Capacity Requirement Planning (CRP) to calculate the total time needed to manufacture a product.' WHERE AD_Column_ID=53310 AND IsCentrallyMaintained='Y'
;