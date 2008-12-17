-- Dez 02, 2008 12:27:12 PM ECT
-- fix Region Alaska
update c_region set Description='Alaska',Updated=TO_DATE('2008-12-02 12:27:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 where c_region_id=118
;
