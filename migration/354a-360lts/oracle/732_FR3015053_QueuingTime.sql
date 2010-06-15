-- 06-13-2010 05:36:04 PM CST
-- queuing time_explanation
UPDATE AD_Field SET Description='Queue time is the time a job waits at a work center before begin handled.', Help='Queuing time has no implication on costs, but on Capacity Requirement Planning (CRP) to calculate the total time needed to manufacture a product.',Updated=TO_DATE('2010-06-13 17:36:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=53405
;

-- 06-13-2010 05:45:24 PM CST
-- queuing time_explanation
UPDATE AD_Field SET Name='Queuing Time', Description='Queue time is the time a job waits at a work center before begin handled.', Help='Queuing time has no implication on costs, but on Capacity Requirement Planning (CRP) to calculate the total time needed to manufacture a product.' WHERE AD_Column_ID=53310 AND IsCentrallyMaintained='Y'
;

-- Jun 13, 2010 8:18:43 PM COT
-- FR3015053_queuing time_explanation
UPDATE AD_Element SET Description='Queue time is the time a job waits at a work center before begin handled.', Help='Queuing time has no implication on costs, but on Capacity Requirement Planning (CRP) to calculate the total time needed to manufacture a product.',Updated=TO_DATE('2010-06-13 20:18:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53234
;

-- Jun 13, 2010 8:18:43 PM COT
-- FR3015053_queuing time_explanation
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53234
;

-- Jun 13, 2010 8:18:43 PM COT
-- FR3015053_queuing time_explanation
UPDATE AD_Column SET ColumnName='QueuingTime', Name='Queuing Time', Description='Queue time is the time a job waits at a work center before begin handled.', Help='Queuing time has no implication on costs, but on Capacity Requirement Planning (CRP) to calculate the total time needed to manufacture a product.' WHERE AD_Element_ID=53234
;

-- Jun 13, 2010 8:18:43 PM COT
-- FR3015053_queuing time_explanation
UPDATE AD_Process_Para SET ColumnName='QueuingTime', Name='Queuing Time', Description='Queue time is the time a job waits at a work center before begin handled.', Help='Queuing time has no implication on costs, but on Capacity Requirement Planning (CRP) to calculate the total time needed to manufacture a product.', AD_Element_ID=53234 WHERE UPPER(ColumnName)='QUEUINGTIME' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Jun 13, 2010 8:18:43 PM COT
-- FR3015053_queuing time_explanation
UPDATE AD_Process_Para SET ColumnName='QueuingTime', Name='Queuing Time', Description='Queue time is the time a job waits at a work center before begin handled.', Help='Queuing time has no implication on costs, but on Capacity Requirement Planning (CRP) to calculate the total time needed to manufacture a product.' WHERE AD_Element_ID=53234 AND IsCentrallyMaintained='Y'
;

-- Jun 13, 2010 8:18:43 PM COT
-- FR3015053_queuing time_explanation
UPDATE AD_Field SET Name='Queuing Time', Description='Queue time is the time a job waits at a work center before begin handled.', Help='Queuing time has no implication on costs, but on Capacity Requirement Planning (CRP) to calculate the total time needed to manufacture a product.' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53234) AND IsCentrallyMaintained='Y'
;

