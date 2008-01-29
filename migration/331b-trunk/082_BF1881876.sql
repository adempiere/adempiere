-- Jan 29, 2008 10:48:50 AM COT
-- [ adempiere-Bugs-1881876 ] Periods not shown when using auto period control
UPDATE AD_Val_Rule SET Code='(EXISTS ( SELECT * FROM C_PeriodControl pc WHERE C_Period.C_Period_ID = pc.C_Period_ID AND pc.PeriodStatus = ''O'') OR EXISTS ( SELECT * FROM C_AcctSchema a, C_Period p WHERE C_Period.C_Period_ID = p.C_Period_ID AND a.AutoPeriodControl = ''Y'' AND ( (p.StartDate BETWEEN SYSDATE - a.Period_OpenHistory AND SYSDATE + a.Period_OpenFuture) OR (p.EndDate BETWEEN SYSDATE - a.Period_OpenHistory AND SYSDATE + a.Period_OpenFuture))))',Updated=TO_DATE('2008-01-29 10:48:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Val_Rule_ID=215
;

