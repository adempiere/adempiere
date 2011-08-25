-- Jun 15, 2010 7:27:00 PM EEST
-- Disable automatic field selection in Find windows
UPDATE AD_Column SET Callout='org.compiere.model.Callout_AD_Column.columnName',Updated=TO_DATE('2010-06-15 19:27:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=116
;

UPDATE AD_Column SET IsSelectionColumn='Y'
WHERE IsSelectionColumn='N' AND (ColumnName IN ('Value', 'Name', 'Description') OR ColumnName LIKE '%Name%')
;

