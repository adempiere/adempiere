-- Mar 6, 2008 9:59:53 PM COT
-- [ 1909248 ] Translation columns different from original

/*
SELECT u.table_name, ut.table_name, u.column_name, u.data_type, ut.data_type,
       u.data_length, ut.data_length, u.data_precision, ut.data_precision,
       u.data_scale, ut.data_scale, u.nullable, ut.nullable
  FROM user_tab_columns u, user_tab_columns ut
 WHERE ut.table_name = u.table_name || '_TRL'
   AND u.column_name = ut.column_name
   AND (   u.data_type <> ut.data_type
        OR u.data_length <> ut.data_length
        OR NVL (u.data_precision, 0) <> NVL (ut.data_precision, 0)
        OR NVL (u.data_scale, 0) <> NVL (ut.data_scale, 0)
        OR u.nullable <> ut.nullable
       );

SELECT t.tablename, tt.tablename, c.columnname, c.ad_reference_id,
       ct.ad_reference_id, c.fieldlength, ct.fieldlength, c.ismandatory,
       ct.ismandatory
  FROM AD_TABLE t, AD_COLUMN c, AD_TABLE tt, AD_COLUMN ct
 WHERE t.ad_table_id = c.ad_table_id
   AND tt.ad_table_id = ct.ad_table_id
   AND tt.tablename = t.tablename || '_Trl'
   AND c.columnname = ct.columnname
   AND (   c.fieldlength <> ct.fieldlength
        OR c.ismandatory <> ct.ismandatory
       )
   AND c.columnname NOT IN
          (t.tablename || '_ID',
           'Updated',
           'IsActive',
           'AD_Client_ID',
           'AD_Org_ID',
           'UpdatedBy',
           'Created',
           'CreatedBy',
           'AD_Language'
          );
*/


UPDATE AD_COLUMN SET IsMandatory='N',Updated=TO_DATE('2008-03-06 21:59:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=14614
;

ALTER TABLE R_MAILTEXT_TRL MODIFY MailHeader NVARCHAR2(2000) DEFAULT  NULL 
;

ALTER TABLE R_MAILTEXT_TRL MODIFY MailHeader NULL
;

UPDATE AD_COLUMN SET FieldLength=0, IsMandatory='N',Updated=TO_DATE('2008-03-06 22:16:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=15268
;

ALTER TABLE CM_CONTAINER_ELEMENT_TRL MODIFY ContentHTML NULL
;

UPDATE AD_COLUMN SET FieldLength=0, IsMandatory='N',Updated=TO_DATE('2008-03-06 22:17:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=15380
;

ALTER TABLE CM_CSTAGE_ELEMENT_TRL MODIFY ContentHTML NULL
;

UPDATE AD_COLUMN SET FieldLength=510,Updated=TO_DATE('2008-03-06 22:19:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=1410
;

UPDATE AD_COLUMN SET FieldLength=510,Updated=TO_DATE('2008-03-06 22:21:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=3330
;

ALTER TABLE M_PRODUCT_TRL MODIFY NAME NVARCHAR2(510) DEFAULT  NULL 
;

UPDATE AD_COLUMN SET FieldLength=60,Updated=TO_DATE('2008-03-06 22:26:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=1410
;

UPDATE AD_COLUMN SET FieldLength=60,Updated=TO_DATE('2008-03-06 22:26:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=3330
;

UPDATE AD_COLUMN SET IsMandatory='Y',Updated=TO_DATE('2008-03-06 22:26:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=2596
;

ALTER TABLE AD_ELEMENT MODIFY AD_Org_ID NUMBER(10) DEFAULT  NULL 
;

ALTER TABLE AD_ELEMENT MODIFY AD_Org_ID NOT NULL
;

ALTER TABLE AD_ELEMENT MODIFY Created DATE DEFAULT  NULL 
;

ALTER TABLE AD_ELEMENT MODIFY Created DATE DEFAULT  NULL 
;

UPDATE AD_COLUMN SET IsMandatory='Y',Updated=TO_DATE('2008-03-06 22:28:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=2599
;

ALTER TABLE AD_ELEMENT MODIFY CreatedBy NUMBER(10) DEFAULT  NULL 
;

ALTER TABLE AD_ELEMENT MODIFY CreatedBy NOT NULL
;

ALTER TABLE AD_ELEMENT MODIFY NAME NVARCHAR2(60) DEFAULT  NULL 
;

ALTER TABLE AD_ELEMENT MODIFY NAME NOT NULL
;

UPDATE AD_COLUMN SET IsMandatory='Y',Updated=TO_DATE('2008-03-06 22:28:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=2601
;

ALTER TABLE AD_ELEMENT MODIFY UpdatedBy NUMBER(10) DEFAULT  NULL 
;

ALTER TABLE AD_ELEMENT MODIFY UpdatedBy NOT NULL
;

UPDATE AD_COLUMN SET FieldLength=60,Updated=TO_DATE('2008-03-06 22:30:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=14613
;

UPDATE AD_COLUMN SET FieldLength=0,Updated=TO_DATE('2008-03-06 22:30:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=15380
;

UPDATE AD_COLUMN SET FieldLength=0,Updated=TO_DATE('2008-03-06 22:31:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=15268
;

UPDATE AD_COLUMN SET FieldLength=0,Updated=TO_DATE('2008-03-06 22:31:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=15268
;

UPDATE AD_COLUMN SET FieldLength=0,Updated=TO_DATE('2008-03-06 22:32:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=15366
;

UPDATE AD_COLUMN SET FieldLength=0,Updated=TO_DATE('2008-03-06 22:32:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=15257
;

UPDATE AD_COLUMN SET IsMandatory='N',Updated=TO_DATE('2008-03-06 22:34:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=15394
;

UPDATE AD_COLUMN SET IsMandatory='Y',Updated=TO_DATE('2008-03-06 22:34:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=15588
;

UPDATE AD_COLUMN SET IsMandatory='Y',Updated=TO_DATE('2008-03-06 22:34:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=15589
;

UPDATE AD_COLUMN SET IsMandatory='Y',Updated=TO_DATE('2008-03-06 22:35:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=15307
;

UPDATE AD_COLUMN SET IsMandatory='Y',Updated=TO_DATE('2008-03-06 22:35:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=15308
;
