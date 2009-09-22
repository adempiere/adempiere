SET DEFINE OFF
SET SQLBLANKLINES ON

-- Sep 14, 2009 10:27:34 AM ECT
-- Warehouse Management
UPDATE AD_View_Definition SET JoinClause='INNER JOIN C_BPartner bp ON (bp.C_BPartner_ID=ol.C_BPartner_ID)',Updated=TO_DATE('2009-09-14 10:27:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_View_Definition_ID=50009
;

-- Sep 14, 2009 10:28:33 AM ECT
-- Warehouse Management
UPDATE AD_Browse SET WhereClause='iob.IsSOTrx=''Y'' AND iob.DocStatus=''DR''',Updated=TO_DATE('2009-09-14 10:28:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_ID=50001
;

-- Sep 15, 2009 7:52:34 AM ECT
-- Warehouse Management
DELETE  FROM  AD_Table_Trl WHERE AD_Table_ID=53042
;

-- Sep 15, 2009 7:52:34 AM ECT
-- Warehouse Management
DELETE FROM AD_Table WHERE AD_Table_ID=53042
;

-- Sep 15, 2009 7:54:42 AM ECT
-- Warehouse Management
DELETE  FROM  AD_Table_Trl WHERE AD_Table_ID=53033
;

-- Sep 15, 2009 7:54:42 AM ECT
-- Warehouse Management
DELETE FROM AD_Table WHERE AD_Table_ID=53033
;

-- Sep 15, 2009 7:55:56 AM ECT
-- Warehouse Management
DELETE  FROM  AD_Table_Trl WHERE AD_Table_ID=53033
;

-- Sep 15, 2009 7:55:56 AM ECT
-- Warehouse Management
DELETE FROM AD_Table WHERE AD_Table_ID=53033
;

-- Sep 15, 2009 8:00:29 AM ECT
-- Warehouse Management
UPDATE AD_Client SET IsUseBetaFunctions='Y',Updated=TO_DATE('2009-09-15 08:00:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Client_ID=0
;

