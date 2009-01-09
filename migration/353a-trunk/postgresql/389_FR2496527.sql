-- Jan 9, 2009 4:35:20 PM COT
-- Hide Sync Column for Virtual Columns
UPDATE AD_Field SET DisplayLogic='@IsView@=''N'' & @ColumnSQL@=''''',Updated=TO_TIMESTAMP('2009-01-09 16:35:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=5122
;

