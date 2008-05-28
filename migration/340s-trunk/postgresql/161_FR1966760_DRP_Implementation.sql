-- May 18, 2008 3:09:07 AM CDT
-- DRP Implementation
UPDATE AD_Process_Para SET AD_Val_Rule_ID=189,Updated=TO_TIMESTAMP('2008-05-18 03:09:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53133
;

-- May 18, 2008 3:09:43 AM CDT
-- DRP Implementation
UPDATE AD_Column SET DefaultValue='0',Updated=TO_TIMESTAMP('2008-05-18 03:09:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53376
;

-- May 18, 2008 3:09:45 AM CDT
-- DRP Implementation
insert into t_alter_column values('pp_product_planning','AD_Org_ID','NUMERIC(10)',null,'0')
;

-- May 18, 2008 3:12:22 AM CDT
-- DRP Implementation
UPDATE PP_Product_Planning SET AD_Org_ID=0 WHERE AD_Org_ID IS NULL
;

-- May 18, 2008 3:12:53 AM CDT
-- DRP Implementation
UPDATE AD_Column SET DefaultValue='0',Updated=TO_TIMESTAMP('2008-05-18 03:12:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53390
;

-- May 18, 2008 3:12:56 AM CDT
-- DRP Implementation
insert into t_alter_column values('pp_product_planning','M_Warehouse_ID','NUMERIC(10)',null,'0')
;

-- May 18, 2008 3:13:08 AM CDT
-- DRP Implementation
UPDATE AD_Column SET DefaultValue='0',Updated=TO_TIMESTAMP('2008-05-18 03:13:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53400
;

-- May 18, 2008 3:13:11 AM CDT
-- DRP Implementation
insert into t_alter_column values('pp_product_planning','S_Resource_ID','NUMERIC(10)',null,'0')
;

-- May 18, 2008 4:37:46 AM CDT
-- DRP Implementation
UPDATE AD_Field SET Included_Tab_ID=53050,Updated=TO_TIMESTAMP('2008-05-18 04:37:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=54218
;

-- May 18, 2008 8:52:52 PM CDT
-- DRP Implementation
UPDATE AD_Column SET DefaultValue=NULL,Updated=TO_TIMESTAMP('2008-05-18 20:52:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53950
;

-- May 18, 2008 9:04:33 PM CDT
-- DRP Implementation
UPDATE AD_Column SET AD_Reference_Value_ID=130,Updated=TO_TIMESTAMP('2008-05-18 21:04:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9550
;

-- May 18, 2008 9:07:40 PM CDT
-- DRP Implementation
UPDATE AD_Element SET Name='Distribution Order', PrintName='Distribution Order',Updated=TO_TIMESTAMP('2008-05-18 21:07:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53311
;

-- May 18, 2008 9:07:40 PM CDT
-- DRP Implementation
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53311
;

-- May 18, 2008 9:07:40 PM CDT
-- DRP Implementation
UPDATE AD_Column SET ColumnName='DD_Order_ID', Name='Distribution Order', Description=NULL, Help=NULL WHERE AD_Element_ID=53311
;

-- May 18, 2008 9:07:40 PM CDT
-- DRP Implementation
UPDATE AD_Field SET Name='Distribution Order', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53311) AND IsCentrallyMaintained='Y'
;

-- May 18, 2008 9:07:40 PM CDT
-- DRP Implementation
UPDATE AD_Process_Para SET ColumnName='DD_Order_ID', Name='Distribution Order', Description=NULL, Help=NULL, AD_Element_ID=53311 WHERE UPPER(ColumnName)='DD_ORDER_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- May 18, 2008 9:07:40 PM CDT
-- DRP Implementation
UPDATE AD_Process_Para SET ColumnName='DD_Order_ID', Name='Distribution Order', Description=NULL, Help=NULL WHERE AD_Element_ID=53311 AND IsCentrallyMaintained='Y'
;

-- May 18, 2008 9:07:40 PM CDT
-- DRP Implementation
UPDATE AD_PrintFormatItem SET PrintName='Distribution Order', Name='Distribution Order' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53311)
;

-- May 18, 2008 9:07:41 PM CDT
-- DRP Implementation
UPDATE AD_PrintFormatItem SET PrintName='Distribution Order', Name='Distribution Order' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53311)
;

-- May 18, 2008 9:07:56 PM CDT
-- DRP Implementation
UPDATE AD_Column SET AD_Reference_ID=30,Updated=TO_TIMESTAMP('2008-05-18 21:07:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53979
;

-- May 18, 2008 9:11:17 PM CDT
-- DRP Implementation
UPDATE AD_Element SET Name='Distribution Order Line', PrintName='Distribution Order Line',Updated=TO_TIMESTAMP('2008-05-18 21:11:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53313
;

-- May 18, 2008 9:11:17 PM CDT
-- DRP Implementation
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53313
;

-- May 18, 2008 9:11:17 PM CDT
-- DRP Implementation
UPDATE AD_Column SET ColumnName='DD_OrderLine_ID', Name='Distribution Order Line', Description=NULL, Help=NULL WHERE AD_Element_ID=53313
;

-- May 18, 2008 9:11:17 PM CDT
-- DRP Implementation
UPDATE AD_Field SET Name='Distribution Order Line', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53313) AND IsCentrallyMaintained='Y'
;

-- May 18, 2008 9:11:17 PM CDT
-- DRP Implementation
UPDATE AD_Process_Para SET ColumnName='DD_OrderLine_ID', Name='Distribution Order Line', Description=NULL, Help=NULL, AD_Element_ID=53313 WHERE UPPER(ColumnName)='DD_ORDERLINE_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- May 18, 2008 9:11:17 PM CDT
-- DRP Implementation
UPDATE AD_Process_Para SET ColumnName='DD_OrderLine_ID', Name='Distribution Order Line', Description=NULL, Help=NULL WHERE AD_Element_ID=53313 AND IsCentrallyMaintained='Y'
;

-- May 18, 2008 9:11:17 PM CDT
-- DRP Implementation
UPDATE AD_PrintFormatItem SET PrintName='Distribution Order Line', Name='Distribution Order Line' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53313)
;

-- May 18, 2008 9:11:17 PM CDT
-- DRP Implementation
UPDATE AD_PrintFormatItem SET PrintName='Distribution Order Line', Name='Distribution Order Line' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53313)
;

-- May 18, 2008 9:12:47 PM CDT
-- DRP Implementation
UPDATE AD_Column SET AD_Reference_ID=19, IsUpdateable='N',Updated=TO_TIMESTAMP('2008-05-18 21:12:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53930
;

-- May 18, 2008 9:13:43 PM CDT
-- DRP Implementation
UPDATE AD_Column SET DefaultValue='@SQL=SELECT NVL(MAX(Line),0)+10 AS DefaultValue FROM DD_OrderLine WHERE DD_OrderLine_ID=@DD_OrderLine_ID@',Updated=TO_TIMESTAMP('2008-05-18 21:13:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53926
;

-- May 18, 2008 9:15:03 PM CDT
-- DRP Implementation
UPDATE AD_Column SET IsMandatory='N',Updated=TO_TIMESTAMP('2008-05-18 21:15:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=8551
;

-- May 18, 2008 9:15:06 PM CDT
-- DRP Implementation
insert into t_alter_column values('m_movementline','M_AttributeSetInstance_ID','NUMERIC(10)',null,'NULL')
;

-- May 18, 2008 9:15:28 PM CDT
-- DRP Implementation
UPDATE AD_Column SET IsMandatory='N',Updated=TO_TIMESTAMP('2008-05-18 21:15:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53948
;

-- May 18, 2008 9:15:31 PM CDT
-- DRP Implementation
insert into t_alter_column values('dd_orderline','M_AttributeSetInstance_ID','NUMERIC(10)',null,'NULL')
;

-- May 18, 2008 9:15:31 PM CDT
-- DRP Implementation
insert into t_alter_column values('dd_orderline','M_AttributeSetInstance_ID',null,'NULL',null)
;

-- May 18, 2008 9:15:47 PM CDT
-- DRP Implementation
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-05-18 21:15:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53949
;

-- May 18, 2008 9:15:50 PM CDT
-- DRP Implementation
insert into t_alter_column values('dd_orderline','M_LocatorTo_ID','NUMERIC(10)',null,'NULL')
;

-- May 18, 2008 9:15:50 PM CDT
-- DRP Implementation
insert into t_alter_column values('dd_orderline','M_LocatorTo_ID',null,'NOT NULL',null)
;

-- May 18, 2008 9:17:33 PM CDT
-- DRP Implementation
UPDATE AD_Column SET AD_Reference_Value_ID=171, AD_Val_Rule_ID=NULL,Updated=TO_TIMESTAMP('2008-05-18 21:17:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53929
;

-- May 18, 2008 9:17:42 PM CDT
-- DRP Implementation
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-05-18 21:17:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53929
;

-- May 18, 2008 9:17:44 PM CDT
-- DRP Implementation
insert into t_alter_column values('dd_orderline','M_Product_ID','NUMERIC(10)',null,'NULL')
;

-- May 18, 2008 9:17:44 PM CDT
-- DRP Implementation
insert into t_alter_column values('dd_orderline','M_Product_ID',null,'NOT NULL',null)
;

-- May 18, 2008 9:25:14 PM CDT
-- DRP Implementation
UPDATE AD_Column SET DefaultValue='@#Date@',Updated=TO_TIMESTAMP('2008-05-18 21:25:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53884
;

-- May 18, 2008 9:25:17 PM CDT
-- DRP Implementation
insert into t_alter_column values('dd_order','DateOrdered','TIMESTAMP',null,'NULL')
;

-- May 18, 2008 9:25:37 PM CDT
-- DRP Implementation
UPDATE AD_Column SET DefaultValue='@#Date@',Updated=TO_TIMESTAMP('2008-05-18 21:25:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53886
;

-- May 18, 2008 9:26:54 PM CDT
-- DRP Implementation
UPDATE AD_Column SET DefaultValue='A',Updated=TO_TIMESTAMP('2008-05-18 21:26:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53888
;

-- May 18, 2008 9:26:56 PM CDT
-- DRP Implementation
insert into t_alter_column values('dd_order','DeliveryRule','CHAR(1)',null,'A')
;

-- May 18, 2008 9:26:57 PM CDT
-- DRP Implementation
UPDATE DD_Order SET DeliveryRule='A' WHERE DeliveryRule IS NULL
;

-- May 18, 2008 9:32:52 PM CDT
-- DRP Implementation
UPDATE AD_Column SET DefaultValue='@#AD_User_ID@',Updated=TO_TIMESTAMP('2008-05-18 21:32:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53915
;

-- May 18, 2008 9:36:09 PM CDT
-- DRP Implementation
INSERT INTO AD_Val_Rule (AD_Client_ID,AD_Org_ID,AD_Val_Rule_ID,Code,Created,CreatedBy,EntityType,IsActive,Name,Type,Updated,UpdatedBy) VALUES (0,0,52024,'M_Warehouse.AD_Org_ID=@AD_Org_ID@ AND M_Warehouse.IsInTransit=''Y''',TO_TIMESTAMP('2008-05-18 21:36:07','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','M_Warehouse In Transit','S',TO_TIMESTAMP('2008-05-18 21:36:07','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 18, 2008 9:36:36 PM CDT
-- DRP Implementation
UPDATE AD_Column SET AD_Val_Rule_ID=52024,Updated=TO_TIMESTAMP('2008-05-18 21:36:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53906
;

-- May 18, 2008 9:40:54 PM CDT
-- DRP Implementation
UPDATE AD_Column SET DefaultValue='@DateOrdered@',Updated=TO_TIMESTAMP('2008-05-18 21:40:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53939
;

-- May 18, 2008 9:41:19 PM CDT
-- DRP Implementation
UPDATE AD_Column SET DefaultValue=NULL,Updated=TO_TIMESTAMP('2008-05-18 21:41:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53940
;

-- May 18, 2008 9:41:22 PM CDT
-- DRP Implementation
UPDATE AD_Column SET DefaultValue=NULL,Updated=TO_TIMESTAMP('2008-05-18 21:41:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53939
;

-- May 18, 2008 9:42:05 PM CDT
-- DRP Implementation
UPDATE AD_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=54024
;

-- May 18, 2008 9:42:05 PM CDT
-- DRP Implementation
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=54033
;

-- May 18, 2008 9:42:05 PM CDT
-- DRP Implementation
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=54034
;

-- May 18, 2008 9:42:05 PM CDT
-- DRP Implementation
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=54035
;

-- May 18, 2008 9:42:05 PM CDT
-- DRP Implementation
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=54036
;

-- May 18, 2008 9:42:05 PM CDT
-- DRP Implementation
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=54037
;

-- May 18, 2008 9:42:05 PM CDT
-- DRP Implementation
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=54038
;

-- May 18, 2008 9:42:05 PM CDT
-- DRP Implementation
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=54039
;

-- May 18, 2008 9:46:29 PM CDT
-- DRP Implementation
UPDATE AD_Column SET IsMandatory='N',Updated=TO_TIMESTAMP('2008-05-18 21:46:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53929
;

-- May 18, 2008 9:46:32 PM CDT
-- DRP Implementation
insert into t_alter_column values('dd_orderline','M_Product_ID','NUMERIC(10)',null,'NULL')
;

-- May 18, 2008 9:46:32 PM CDT
-- DRP Implementation
insert into t_alter_column values('dd_orderline','M_Product_ID',null,'NULL',null)
;

-- May 18, 2008 10:07:29 PM CDT
-- DRP Implementation
UPDATE AD_Tab SET TabLevel=1,Updated=TO_TIMESTAMP('2008-05-18 22:07:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=53050
;

-- May 18, 2008 10:17:59 PM CDT
-- DRP Implementation
UPDATE AD_Column SET DefaultValue=NULL,Updated=TO_TIMESTAMP('2008-05-18 22:17:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53950
;

-- May 18, 2008 10:18:06 PM CDT
-- DRP Implementation
UPDATE AD_Column SET DefaultValue=NULL,Updated=TO_TIMESTAMP('2008-05-18 22:18:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53949
;

-- May 18, 2008 11:53:25 PM CDT
-- DRP Implementation
UPDATE AD_Column SET AD_Reference_ID=18, AD_Reference_Value_ID=191,Updated=TO_TIMESTAMP('2008-05-18 23:53:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53949
;

-- May 18, 2008 11:53:41 PM CDT
-- DRP Implementation
UPDATE AD_Column SET AD_Reference_ID=18, AD_Reference_Value_ID=191,Updated=TO_TIMESTAMP('2008-05-18 23:53:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53950
;

-- May 19, 2008 12:57:53 AM CDT
-- DRP Implementation
UPDATE AD_Window SET Name='Distribution Order',Updated=TO_TIMESTAMP('2008-05-19 00:57:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Window_ID=53012
;

-- May 19, 2008 12:57:53 AM CDT
-- DRP Implementation
UPDATE AD_Window_Trl SET IsTranslated='N' WHERE AD_Window_ID=53012
;

-- May 19, 2008 12:57:53 AM CDT
-- DRP Implementation
UPDATE AD_Menu SET Description='Distribution Order allow create Order inter warehouse to supply a demand ', IsActive='Y', Name='Distribution Order',Updated=TO_TIMESTAMP('2008-05-19 00:57:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Menu_ID=53068
;

-- May 19, 2008 12:57:53 AM CDT
-- DRP Implementation
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=53068
;

-- May 19, 2008 1:00:49 AM CDT
-- DRP Implementation
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53470,0,'IsRequiredDRP',TO_TIMESTAMP('2008-05-19 01:00:42','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','Required DRP','Required DRP',TO_TIMESTAMP('2008-05-19 01:00:42','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 19, 2008 1:00:49 AM CDT
-- DRP Implementation
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53470 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- May 19, 2008 1:01:06 AM CDT
-- DRP Implementation
UPDATE AD_Element SET Name='Required MRP', PrintName='Required MRP',Updated=TO_TIMESTAMP('2008-05-19 01:01:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53262
;

-- May 19, 2008 1:01:06 AM CDT
-- DRP Implementation
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53262
;

-- May 19, 2008 1:01:06 AM CDT
-- DRP Implementation
UPDATE AD_Column SET ColumnName='IsRequiredMRP', Name='Required MRP', Description=NULL, Help=NULL WHERE AD_Element_ID=53262
;

-- May 19, 2008 1:01:06 AM CDT
-- DRP Implementation
UPDATE AD_Field SET Name='Required MRP', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53262) AND IsCentrallyMaintained='Y'
;

-- May 19, 2008 1:01:06 AM CDT
-- DRP Implementation
UPDATE AD_Process_Para SET ColumnName='IsRequiredMRP', Name='Required MRP', Description=NULL, Help=NULL, AD_Element_ID=53262 WHERE UPPER(ColumnName)='ISREQUIREDMRP' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- May 19, 2008 1:01:06 AM CDT
-- DRP Implementation
UPDATE AD_Process_Para SET ColumnName='IsRequiredMRP', Name='Required MRP', Description=NULL, Help=NULL WHERE AD_Element_ID=53262 AND IsCentrallyMaintained='Y'
;

-- May 19, 2008 1:01:06 AM CDT
-- DRP Implementation
UPDATE AD_PrintFormatItem SET PrintName='Required MRP', Name='Required MRP' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53262)
;

-- May 19, 2008 1:01:06 AM CDT
-- DRP Implementation
UPDATE AD_PrintFormatItem SET PrintName='Required MRP', Name='Required MRP' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53262)
;

-- May 19, 2008 1:02:19 AM CDT
-- DRP Implementation
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,55334,53470,0,20,53020,'IsRequiredDRP',TO_TIMESTAMP('2008-05-19 01:02:15','YYYY-MM-DD HH24:MI:SS'),0,'EE01',1,'Y','N','N','N','N','Y','N','N','Y','N','N','Required DRP',TO_TIMESTAMP('2008-05-19 01:02:15','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 19, 2008 1:02:19 AM CDT
-- DRP Implementation
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=55334 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;


-- May 19, 2008 1:02:42 AM CDT
-- DRP Implementation
ALTER TABLE PP_Product_Planning ADD COLUMN IsRequiredDRP CHAR(1) DEFAULT 'N' CHECK (IsRequiredDRP IN ('Y','N')) NOT NULL
;

-- May 19, 2008 1:03:10 AM CDT
-- DRP Implementation
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,55334,55434,0,53030,TO_TIMESTAMP('2008-05-19 01:03:05','YYYY-MM-DD HH24:MI:SS'),0,1,'EE01','Y','Y','Y','N','N','N','N','N','Required DRP',TO_TIMESTAMP('2008-05-19 01:03:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 19, 2008 1:03:10 AM CDT
-- DRP Implementation
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=55434 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- May 19, 2008 1:03:31 AM CDT
-- DRP Implementation
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=53516
;

-- May 19, 2008 1:03:31 AM CDT
-- DRP Implementation
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=53517
;

-- May 19, 2008 1:03:31 AM CDT
-- DRP Implementation
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=53518
;

-- May 19, 2008 1:03:31 AM CDT
-- DRP Implementation
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=54392
;

-- May 19, 2008 1:03:31 AM CDT
-- DRP Implementation
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=53519
;

-- May 19, 2008 1:03:31 AM CDT
-- DRP Implementation
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=53520
;

-- May 19, 2008 1:03:31 AM CDT
-- DRP Implementation
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=53521
;

-- May 19, 2008 1:03:31 AM CDT
-- DRP Implementation
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=55434
;

-- May 19, 2008 1:03:31 AM CDT
-- DRP Implementation
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=53522
;

-- May 19, 2008 1:03:31 AM CDT
-- DRP Implementation
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=53523
;

-- May 19, 2008 1:03:31 AM CDT
-- DRP Implementation
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=53524
;

-- May 19, 2008 1:03:31 AM CDT
-- DRP Implementation
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=53525
;

-- May 19, 2008 1:03:31 AM CDT
-- DRP Implementation
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=53526
;

-- May 19, 2008 1:03:31 AM CDT
-- DRP Implementation
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=53527
;

-- May 19, 2008 1:03:31 AM CDT
-- DRP Implementation
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=53528
;

-- May 19, 2008 1:03:31 AM CDT
-- DRP Implementation
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=53529
;

-- May 19, 2008 1:03:31 AM CDT
-- DRP Implementation
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=53530
;

-- May 19, 2008 1:03:31 AM CDT
-- DRP Implementation
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=53531
;

-- May 19, 2008 1:03:31 AM CDT
-- DRP Implementation
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=53532
;

-- May 19, 2008 1:03:31 AM CDT
-- DRP Implementation
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=54420
;

-- May 19, 2008 1:03:31 AM CDT
-- DRP Implementation
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=53533
;

-- May 19, 2008 1:03:31 AM CDT
-- DRP Implementation
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=53534
;

-- May 19, 2008 1:03:40 AM CDT
-- DRP Implementation
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2008-05-19 01:03:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=55434
;

-- May 19, 2008 1:05:27 AM CDT
-- DRP Implementation
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,53470,0,53016,53149,20,'IsRequiredDRP',TO_TIMESTAMP('2008-05-19 01:05:23','YYYY-MM-DD HH24:MI:SS'),0,'EE01',1,'Y','Y','N','N','Required Calculate DRP',35,TO_TIMESTAMP('2008-05-19 01:05:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 19, 2008 1:05:27 AM CDT
-- DRP Implementation
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53149 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- May 19, 2008 1:05:46 AM CDT
-- DRP Implementation
UPDATE AD_Element SET Name='Required Calculate DRP', PrintName='Required Calculate DRP',Updated=TO_TIMESTAMP('2008-05-19 01:05:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53470
;

-- May 19, 2008 1:05:46 AM CDT
-- DRP Implementation
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53470
;

-- May 19, 2008 1:05:46 AM CDT
-- DRP Implementation
UPDATE AD_Column SET ColumnName='IsRequiredDRP', Name='Required Calculate DRP', Description=NULL, Help=NULL WHERE AD_Element_ID=53470
;

-- May 19, 2008 1:05:46 AM CDT
-- DRP Implementation
UPDATE AD_Field SET Name='Required Calculate DRP', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53470) AND IsCentrallyMaintained='Y'
;

-- May 19, 2008 1:05:46 AM CDT
-- DRP Implementation
UPDATE AD_Process_Para SET ColumnName='IsRequiredDRP', Name='Required Calculate DRP', Description=NULL, Help=NULL, AD_Element_ID=53470 WHERE UPPER(ColumnName)='ISREQUIREDDRP' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- May 19, 2008 1:05:46 AM CDT
-- DRP Implementation
UPDATE AD_Process_Para SET ColumnName='IsRequiredDRP', Name='Required Calculate DRP', Description=NULL, Help=NULL WHERE AD_Element_ID=53470 AND IsCentrallyMaintained='Y'
;

-- May 19, 2008 1:05:46 AM CDT
-- DRP Implementation
UPDATE AD_PrintFormatItem SET PrintName='Required Calculate DRP', Name='Required Calculate DRP' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53470)
;

-- May 19, 2008 1:05:46 AM CDT
-- DRP Implementation
UPDATE AD_PrintFormatItem SET PrintName='Required Calculate DRP', Name='Required Calculate DRP' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53470)
;

-- May 19, 2008 1:06:04 AM CDT
-- DRP Implementation
UPDATE AD_Element SET Name='Required Calculate MRP', PrintName='Required Calculate MRP',Updated=TO_TIMESTAMP('2008-05-19 01:06:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53262
;

-- May 19, 2008 1:06:04 AM CDT
-- DRP Implementation
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53262
;

-- May 19, 2008 1:06:04 AM CDT
-- DRP Implementation
UPDATE AD_Column SET ColumnName='IsRequiredMRP', Name='Required Calculate MRP', Description=NULL, Help=NULL WHERE AD_Element_ID=53262
;

-- May 19, 2008 1:06:04 AM CDT
-- DRP Implementation
UPDATE AD_Field SET Name='Required Calculate MRP', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53262) AND IsCentrallyMaintained='Y'
;

-- May 19, 2008 1:06:04 AM CDT
-- DRP Implementation
UPDATE AD_Process_Para SET ColumnName='IsRequiredMRP', Name='Required Calculate MRP', Description=NULL, Help=NULL, AD_Element_ID=53262 WHERE UPPER(ColumnName)='ISREQUIREDMRP' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- May 19, 2008 1:06:04 AM CDT
-- DRP Implementation
UPDATE AD_Process_Para SET ColumnName='IsRequiredMRP', Name='Required Calculate MRP', Description=NULL, Help=NULL WHERE AD_Element_ID=53262 AND IsCentrallyMaintained='Y'
;

-- May 19, 2008 1:06:04 AM CDT
-- DRP Implementation
UPDATE AD_PrintFormatItem SET PrintName='Required Calculate MRP', Name='Required Calculate MRP' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53262)
;

-- May 19, 2008 1:06:04 AM CDT
-- DRP Implementation
UPDATE AD_PrintFormatItem SET PrintName='Required Calculate MRP', Name='Required Calculate MRP' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53262)
;

-- May 19, 2008 1:07:52 AM CDT
-- DRP Implementation
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,113,0,53022,53150,19,'AD_Org_ID',TO_TIMESTAMP('2008-05-19 01:07:48','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client','EE01',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','Organization',10,TO_TIMESTAMP('2008-05-19 01:07:48','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 19, 2008 1:07:52 AM CDT
-- DRP Implementation
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53150 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- May 19, 2008 1:09:41 AM CDT
-- DRP Implementation
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,459,0,53022,53151,19,189,'M_Warehouse_ID',TO_TIMESTAMP('2008-05-19 01:09:36','YYYY-MM-DD HH24:MI:SS'),0,'Storage Warehouse and Service Point','EE01',22,'The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','Y','Y','N','N','Warehouse',20,TO_TIMESTAMP('2008-05-19 01:09:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 19, 2008 1:09:41 AM CDT
-- DRP Implementation
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53151 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- May 19, 2008 1:11:27 AM CDT
-- DRP Implementation
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,0,53022,53152,10,'Version',TO_TIMESTAMP('2008-05-19 01:11:17','YYYY-MM-DD HH24:MI:SS'),0,'Version of the table definition','EE01',20,'The Version indicates the version of this table definition.','Y','Y','N','N','Version',30,TO_TIMESTAMP('2008-05-19 01:11:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 19, 2008 1:11:27 AM CDT
-- DRP Implementation
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53152 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- May 19, 2008 1:11:38 AM CDT
-- DRP Implementation
UPDATE AD_Process_Para SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-05-19 01:11:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53150
;

-- May 19, 2008 1:12:03 AM CDT
-- DRP Implementation
UPDATE AD_Process SET Classname='org.eevolution.process.MRP',Updated=TO_TIMESTAMP('2008-05-19 01:12:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_ID=53022
;

-- May 19, 2008 1:28:00 AM CDT
-- DRP Implementation
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgTip,MsgType,Updated,UpdatedBy,Value) VALUES (0,53035,0,TO_TIMESTAMP('2008-05-19 01:27:57','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','Do not exist Transit Warehouse to this Organization','Do not exist Transit Warehouse to this Organization','I',TO_TIMESTAMP('2008-05-19 01:27:57','YYYY-MM-DD HH24:MI:SS'),0,'DRP-010')
;

-- May 19, 2008 1:28:00 AM CDT
-- DRP Implementation
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53035 AND EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Message_ID!=t.AD_Message_ID)
;


-- May 19, 2008 1:28:51 AM CDT
-- DRP Implementation
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgTip,MsgType,Updated,UpdatedBy,Value) VALUES (0,53036,0,TO_TIMESTAMP('2008-05-19 01:28:47','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','Org targer do not linked to BPartner','Org targer do not linked to BPartner','I',TO_TIMESTAMP('2008-05-19 01:28:47','YYYY-MM-DD HH24:MI:SS'),0,'DRP-020')
;

-- May 19, 2008 1:28:51 AM CDT
-- DRP Implementation
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53036 AND EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Message_ID!=t.AD_Message_ID)
;

