-- Nov 20, 2013 6:49:00 PM IST
-- Made changes for the Period Parameter in FinReport
UPDATE AD_Ref_Table SET OrderByClause='C_Period.StartDate DESC', WhereClause='C_Period.IsActive=''Y'' AND (SELECT y.IsActive FROM C_Year y WHERE y.C_Year_ID=C_Period.C_Year_ID)=''Y''',Updated=TO_DATE('2013-11-20 18:49:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=275
;