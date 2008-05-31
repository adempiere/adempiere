-- Mar 1, 2008 10:25:14 PM CST
-- Qty Safety stock
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53354,0,'SafetyStock',TO_TIMESTAMP('2008-03-01 22:25:08','YYYY-MM-DD HH24:MI:SS'),0,'Safety stock is a term used to describe a level of stock that is maintained below the cycle stock to buffer against stock-outs','EE01','Safety stock is defined as extra units of inventory carried as protection against possible stockouts. It is held when an organization cannot accurately predict demand and/or lead time for the product.

Rereference:
http://en.wikipedia.org/wiki/Safety_stock','Y','Qty Safety Stock','Qty Safety Stock',TO_TIMESTAMP('2008-03-01 22:25:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 1, 2008 10:25:14 PM CST
-- Qty Safety stock
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53354 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Mar 1, 2008 10:26:36 PM CST
-- Qty Safety stock
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54380,53354,0,29,53020,'SafetyStock',TO_TIMESTAMP('2008-03-01 22:26:35','YYYY-MM-DD HH24:MI:SS'),0,'Safety stock is a term used to describe a level of stock that is maintained below the cycle stock to buffer against stock-outs','EE01',22,'Safety stock is defined as extra units of inventory carried as protection against possible stockouts. It is held when an organization cannot accurately predict demand and/or lead time for the product.

Rereference:
http://en.wikipedia.org/wiki/Safety_stock','Y','N','N','N','N','N','N','N','N','N','Y','Qty Safety Stock',0,TO_TIMESTAMP('2008-03-01 22:26:35','YYYY-MM-DD HH24:MI:SS'),0,1.000000000000)
;

-- Mar 1, 2008 10:26:36 PM CST
-- Qty Safety stock
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54380 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 1, 2008 10:26:40 PM CST
-- Qty Safety stock
ALTER TABLE PP_Product_Planning ADD COLUMN SafetyStock NUMERIC
;

-- Mar 1, 2008 10:27:09 PM CST
-- Qty Safety stock
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,54380,54420,0,53030,TO_TIMESTAMP('2008-03-01 22:27:08','YYYY-MM-DD HH24:MI:SS'),0,'Safety stock is a term used to describe a level of stock that is maintained below the cycle stock to buffer against stock-outs',22,'EE01','Safety stock is defined as extra units of inventory carried as protection against possible stockouts. It is held when an organization cannot accurately predict demand and/or lead time for the product.

Rereference:
http://en.wikipedia.org/wiki/Safety_stock','Y','Y','Y','N','N','N','N','N','Qty Safety Stock',TO_TIMESTAMP('2008-03-01 22:27:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 1, 2008 10:27:09 PM CST
-- Qty Safety stock
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54420 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 1, 2008 10:28:00 PM CST
-- Qty Safety stock
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=54420
;

-- Mar 1, 2008 10:28:00 PM CST
-- Qty Safety stock
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=53533
;

-- Mar 1, 2008 10:28:00 PM CST
-- Qty Safety stock
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=53534
;

-- Mar 1, 2008 10:28:29 PM CST
-- Qty Safety stock
UPDATE AD_Element SET Name='Safety Stock Qty', PrintName='Safety Stock Qty',Updated=TO_TIMESTAMP('2008-03-01 22:28:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53354
;

-- Mar 1, 2008 10:28:29 PM CST
-- Qty Safety stock
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53354
;

-- Mar 1, 2008 10:28:29 PM CST
-- Qty Safety stock
UPDATE AD_Column SET ColumnName='SafetyStock', Name='Safety Stock Qty', Description='Safety stock is a term used to describe a level of stock that is maintained below the cycle stock to buffer against stock-outs', Help='Safety stock is defined as extra units of inventory carried as protection against possible stockouts. It is held when an organization cannot accurately predict demand and/or lead time for the product.

Rereference:
http://en.wikipedia.org/wiki/Safety_stock' WHERE AD_Element_ID=53354
;

-- Mar 1, 2008 10:28:30 PM CST
-- Qty Safety stock
UPDATE AD_Field SET Name='Safety Stock Qty', Description='Safety stock is a term used to describe a level of stock that is maintained below the cycle stock to buffer against stock-outs', Help='Safety stock is defined as extra units of inventory carried as protection against possible stockouts. It is held when an organization cannot accurately predict demand and/or lead time for the product.

Rereference:
http://en.wikipedia.org/wiki/Safety_stock' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53354) AND IsCentrallyMaintained='Y'
;

-- Mar 1, 2008 10:28:30 PM CST
-- Qty Safety stock
UPDATE AD_Process_Para SET ColumnName='SafetyStock', Name='Safety Stock Qty', Description='Safety stock is a term used to describe a level of stock that is maintained below the cycle stock to buffer against stock-outs', Help='Safety stock is defined as extra units of inventory carried as protection against possible stockouts. It is held when an organization cannot accurately predict demand and/or lead time for the product.

Rereference:
http://en.wikipedia.org/wiki/Safety_stock', AD_Element_ID=53354 WHERE UPPER(ColumnName)='SafetyStock' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 1, 2008 10:28:30 PM CST
-- Qty Safety stock
UPDATE AD_Process_Para SET ColumnName='SafetyStock', Name='Safety Stock Qty', Description='Safety stock is a term used to describe a level of stock that is maintained below the cycle stock to buffer against stock-outs', Help='Safety stock is defined as extra units of inventory carried as protection against possible stockouts. It is held when an organization cannot accurately predict demand and/or lead time for the product.

Rereference:
http://en.wikipedia.org/wiki/Safety_stock' WHERE AD_Element_ID=53354 AND IsCentrallyMaintained='Y'
;

-- Mar 1, 2008 10:28:30 PM CST
-- Qty Safety stock
UPDATE AD_PrintFormatItem SET PrintName='Safety Stock Qty', Name='Safety Stock Qty' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53354)
;

-- Mar 1, 2008 10:28:30 PM CST
-- Qty Safety stock
UPDATE AD_PrintFormatItem SET PrintName='Safety Stock Qty', Name='Safety Stock Qty' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53354)
;

