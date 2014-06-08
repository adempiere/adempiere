SET DEFINE OFF
SET SQLBLANKLINES ON

-- Sep 18, 2009 10:51:24 PM ECT
-- Warehouse Management
UPDATE AD_Browse SET WhereClause='NOT EXISTS (SELECT 1 FROM M_InOutLine WHERE M_InOutLine.C_OrderLine_ID = iobl.C_OrderLine_ID AND iobl.PickedQty >= M_InOutLine.MovementQty)  AND iob.IsSOTrx=''Y'' AND iob.DocStatus=''CO'' AND iobl.PickedQty > iobl.MovementQty',Updated=TO_DATE('2009-09-18 22:51:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_ID=50002
;

-- Sep 18, 2009 10:51:37 PM ECT
-- Warehouse Management
UPDATE AD_Browse SET WhereClause='IsSOTrx=''Y'' AND DocStatus=''CO'' AND NOT EXISTS (SELECT 1 FROM WM_InOutBoundLine WHERE WM_InOutBoundLine.C_OrderLine_ID = ol.C_OrderLine_ID)',Updated=TO_DATE('2009-09-18 22:51:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_ID=50000
;

-- Sep 19, 2009 3:13:19 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58410,53913,0,19,53038,'WM_InOutBoundLine_ID',TO_DATE('2009-09-19 15:13:17','YYYY-MM-DD HH24:MI:SS'),0,'EE03',11,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','In & Out Bound Order Line',0,TO_DATE('2009-09-19 15:13:17','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 19, 2009 3:13:19 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58410 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 19, 2009 3:13:23 PM ECT
-- Warehouse Management
ALTER TABLE DD_OrderLine ADD WM_InOutBoundLine_ID NUMBER(10) DEFAULT NULL 
;

