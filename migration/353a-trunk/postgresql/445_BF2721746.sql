-- Mar 30, 2009 1:20:13 PM MYT
-- [ adempiere-Bugs-2721746 ] GW MM Vendor Return DocType Have Wrong IsSoTrx Value
UPDATE C_DocType SET IsSOTrx='N',Updated=TO_TIMESTAMP('2009-03-30 13:20:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_DocType_ID=151
;

COMMIT ;
