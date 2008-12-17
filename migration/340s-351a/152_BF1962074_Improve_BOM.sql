SET SQLBLANKLINES ON 
SET DEFINE OFF 
-- May 11, 2008 2:48:38 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET Name='Copy From',Updated=TO_DATE('2008-05-11 14:48:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53480
;

-- May 11, 2008 2:48:38 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=53480
;

-- May 11, 2008 2:50:31 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Column SET DefaultValue='@#Date@',Updated=TO_DATE('2008-05-11 14:50:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53338
;

-- May 11, 2008 2:52:34 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET Included_Tab_ID=53029,Updated=TO_DATE('2008-05-11 14:52:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53479
;

-- May 11, 2008 2:54:27 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Element SET Name='Is Qty Percentage', PrintName='Is Qty Percentage',Updated=TO_DATE('2008-05-11 14:54:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53252
;

-- May 11, 2008 2:54:27 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53252
;

-- May 11, 2008 2:54:27 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Column SET ColumnName='IsQtyPercentage', Name='Is Qty Percentage', Description=NULL, Help=NULL WHERE AD_Element_ID=53252
;

-- May 11, 2008 2:54:27 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET Name='Is Qty Percentage', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53252) AND IsCentrallyMaintained='Y'
;

-- May 11, 2008 2:54:27 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Process_Para SET ColumnName='IsQtyPercentage', Name='Is Qty Percentage', Description=NULL, Help=NULL, AD_Element_ID=53252 WHERE UPPER(ColumnName)='ISQTYPERCENTAGE' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- May 11, 2008 2:54:27 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Process_Para SET ColumnName='IsQtyPercentage', Name='Is Qty Percentage', Description=NULL, Help=NULL WHERE AD_Element_ID=53252 AND IsCentrallyMaintained='Y'
;

-- May 11, 2008 2:54:27 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_PrintFormatItem pi SET PrintName='Is Qty Percentage', Name='Is Qty Percentage' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53252)
;

-- May 11, 2008 2:54:28 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_PrintFormatItem pi SET PrintName='Is Qty Percentage', Name='Is Qty Percentage' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53252)
;

-- May 11, 2008 2:54:41 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Element SET Name='Issue Method', PrintName='Issue Method',Updated=TO_DATE('2008-05-11 14:54:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53253
;

-- May 11, 2008 2:54:41 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53253
;

-- May 11, 2008 2:54:41 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Column SET ColumnName='IssueMethod', Name='Issue Method', Description=NULL, Help=NULL WHERE AD_Element_ID=53253
;

-- May 11, 2008 2:54:41 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET Name='Issue Method', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53253) AND IsCentrallyMaintained='Y'
;

-- May 11, 2008 2:54:41 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Process_Para SET ColumnName='IssueMethod', Name='Issue Method', Description=NULL, Help=NULL, AD_Element_ID=53253 WHERE UPPER(ColumnName)='ISSUEMETHOD' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- May 11, 2008 2:54:41 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Process_Para SET ColumnName='IssueMethod', Name='Issue Method', Description=NULL, Help=NULL WHERE AD_Element_ID=53253 AND IsCentrallyMaintained='Y'
;

-- May 11, 2008 2:54:41 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_PrintFormatItem pi SET PrintName='Issue Method', Name='Issue Method' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53253)
;

-- May 11, 2008 2:54:41 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_PrintFormatItem pi SET PrintName='Issue Method', Name='Issue Method' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53253)
;

-- May 11, 2008 2:55:05 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Column SET DefaultValue='''O''',Updated=TO_DATE('2008-05-11 14:55:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53359
;

-- May 11, 2008 2:55:43 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Element SET Name='Qty of BOM', PrintName='Qtyo f BOM',Updated=TO_DATE('2008-05-11 14:55:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53255
;

-- May 11, 2008 2:55:43 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53255
;

-- May 11, 2008 2:55:43 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Column SET ColumnName='QtyBOM', Name='Qty of BOM', Description=NULL, Help=NULL WHERE AD_Element_ID=53255
;

-- May 11, 2008 2:55:43 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET Name='Qty of BOM', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53255) AND IsCentrallyMaintained='Y'
;

-- May 11, 2008 2:55:43 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Process_Para SET ColumnName='QtyBOM', Name='Qty of BOM', Description=NULL, Help=NULL, AD_Element_ID=53255 WHERE UPPER(ColumnName)='QTYBOM' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- May 11, 2008 2:55:43 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Process_Para SET ColumnName='QtyBOM', Name='Qty of BOM', Description=NULL, Help=NULL WHERE AD_Element_ID=53255 AND IsCentrallyMaintained='Y'
;

-- May 11, 2008 2:55:43 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_PrintFormatItem pi SET PrintName='Qtyo f BOM', Name='Qty of BOM' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53255)
;

-- May 11, 2008 2:55:43 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_PrintFormatItem pi SET PrintName='Qtyo f BOM', Name='Qty of BOM' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53255)
;

-- May 11, 2008 2:55:49 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Element SET PrintName='Qty of BOM',Updated=TO_DATE('2008-05-11 14:55:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53255
;

-- May 11, 2008 2:55:49 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53255
;

-- May 11, 2008 2:55:49 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Column SET ColumnName='QtyBOM', Name='Qty of BOM', Description=NULL, Help=NULL WHERE AD_Element_ID=53255
;

-- May 11, 2008 2:55:49 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET Name='Qty of BOM', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53255) AND IsCentrallyMaintained='Y'
;

-- May 11, 2008 2:55:49 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Process_Para SET ColumnName='QtyBOM', Name='Qty of BOM', Description=NULL, Help=NULL, AD_Element_ID=53255 WHERE UPPER(ColumnName)='QTYBOM' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- May 11, 2008 2:55:49 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Process_Para SET ColumnName='QtyBOM', Name='Qty of BOM', Description=NULL, Help=NULL WHERE AD_Element_ID=53255 AND IsCentrallyMaintained='Y'
;

-- May 11, 2008 2:55:49 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_PrintFormatItem pi SET PrintName='Qty of BOM', Name='Qty of BOM' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53255)
;

-- May 11, 2008 2:55:49 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_PrintFormatItem pi SET PrintName='Qty of BOM', Name='Qty of BOM' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53255)
;

-- May 11, 2008 2:55:57 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Column SET ColumnSQL=NULL,Updated=TO_DATE('2008-05-11 14:55:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53367
;

-- May 11, 2008 2:56:06 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Element SET Name='Qty Batch', PrintName='Qty Batch',Updated=TO_DATE('2008-05-11 14:56:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53256
;

-- May 11, 2008 2:56:06 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53256
;

-- May 11, 2008 2:56:06 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Column SET ColumnName='QtyBatch', Name='Qty Batch', Description=NULL, Help=NULL WHERE AD_Element_ID=53256
;

-- May 11, 2008 2:56:06 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET Name='Qty Batch', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53256) AND IsCentrallyMaintained='Y'
;

-- May 11, 2008 2:56:06 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Process_Para SET ColumnName='QtyBatch', Name='Qty Batch', Description=NULL, Help=NULL, AD_Element_ID=53256 WHERE UPPER(ColumnName)='QTYBATCH' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- May 11, 2008 2:56:06 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Process_Para SET ColumnName='QtyBatch', Name='Qty Batch', Description=NULL, Help=NULL WHERE AD_Element_ID=53256 AND IsCentrallyMaintained='Y'
;

-- May 11, 2008 2:56:06 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_PrintFormatItem pi SET PrintName='Qty Batch', Name='Qty Batch' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53256)
;

-- May 11, 2008 2:56:06 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_PrintFormatItem pi SET PrintName='Qty Batch', Name='Qty Batch' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53256)
;

-- May 11, 2008 2:56:47 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Element SET Name='Is Critical Component', PrintName='Is Critical Component',Updated=TO_DATE('2008-05-11 14:56:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53251
;

-- May 11, 2008 2:56:47 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53251
;

-- May 11, 2008 2:56:47 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Column SET ColumnName='IsCritical', Name='Is Critical Component', Description=NULL, Help=NULL WHERE AD_Element_ID=53251
;

-- May 11, 2008 2:56:47 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET Name='Is Critical Component', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53251) AND IsCentrallyMaintained='Y'
;

-- May 11, 2008 2:56:47 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Process_Para SET ColumnName='IsCritical', Name='Is Critical Component', Description=NULL, Help=NULL, AD_Element_ID=53251 WHERE UPPER(ColumnName)='ISCRITICAL' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- May 11, 2008 2:56:47 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Process_Para SET ColumnName='IsCritical', Name='Is Critical Component', Description=NULL, Help=NULL WHERE AD_Element_ID=53251 AND IsCentrallyMaintained='Y'
;

-- May 11, 2008 2:56:47 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_PrintFormatItem pi SET PrintName='Is Critical Component', Name='Is Critical Component' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53251)
;

-- May 11, 2008 2:56:47 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_PrintFormatItem pi SET PrintName='Is Critical Component', Name='Is Critical Component' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53251)
;

-- May 11, 2008 2:57:07 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Element SET Name='Component Type', PrintName='Component Type',Updated=TO_DATE('2008-05-11 14:57:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53249
;

-- May 11, 2008 2:57:07 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53249
;

-- May 11, 2008 2:57:07 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Column SET ColumnName='ComponentType', Name='Component Type', Description=NULL, Help=NULL WHERE AD_Element_ID=53249
;

-- May 11, 2008 2:57:07 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET Name='Component Type', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53249) AND IsCentrallyMaintained='Y'
;

-- May 11, 2008 2:57:07 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Process_Para SET ColumnName='ComponentType', Name='Component Type', Description=NULL, Help=NULL, AD_Element_ID=53249 WHERE UPPER(ColumnName)='COMPONENTTYPE' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- May 11, 2008 2:57:07 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Process_Para SET ColumnName='ComponentType', Name='Component Type', Description=NULL, Help=NULL WHERE AD_Element_ID=53249 AND IsCentrallyMaintained='Y'
;

-- May 11, 2008 2:57:07 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_PrintFormatItem pi SET PrintName='Component Type', Name='Component Type' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53249)
;

-- May 11, 2008 2:57:07 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_PrintFormatItem pi SET PrintName='Component Type', Name='Component Type' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53249)
;

-- May 11, 2008 2:58:29 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Element SET Description='The Grouping Components to the Backflush', Name='Backflush Group', PrintName='Backflush Group',Updated=TO_DATE('2008-05-11 14:58:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53248
;

-- May 11, 2008 2:58:29 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53248
;

-- May 11, 2008 2:58:29 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Column SET ColumnName='BackflushGroup', Name='Backflush Group', Description='The Grouping Components to the Backflush', Help=NULL WHERE AD_Element_ID=53248
;

-- May 11, 2008 2:58:29 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET Name='Backflush Group', Description='The Grouping Components to the Backflush', Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53248) AND IsCentrallyMaintained='Y'
;

-- May 11, 2008 2:58:29 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Process_Para SET ColumnName='BackflushGroup', Name='Backflush Group', Description='The Grouping Components to the Backflush', Help=NULL, AD_Element_ID=53248 WHERE UPPER(ColumnName)='BACKFLUSHGROUP' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- May 11, 2008 2:58:29 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Process_Para SET ColumnName='BackflushGroup', Name='Backflush Group', Description='The Grouping Components to the Backflush', Help=NULL WHERE AD_Element_ID=53248 AND IsCentrallyMaintained='Y'
;

-- May 11, 2008 2:58:29 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_PrintFormatItem pi SET PrintName='Backflush Group', Name='Backflush Group' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53248)
;

-- May 11, 2008 2:58:29 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_PrintFormatItem pi SET PrintName='Backflush Group', Name='Backflush Group' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53248)
;

-- May 11, 2008 3:08:32 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Element SET Description='Component Type for a Bill of Material or Formula', Help='The Component Type can be:

1.- By Product: Define a By Product as Component into BOM
2.- Component: Define a normal Component into BOM 
3.- Option: Define an Option for Product Configure BOM
4.- Phantom: Define a Phantom as Component into BOM
5.- Packing: Define a Packing as Component into BOM
6.- Planning : Define Planning as Component into BOM
7.- Tools: Define Tools as Component into BOM
8.- Variant: Define Variant  for Product Configure BOM
',Updated=TO_DATE('2008-05-11 15:08:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53249
;

-- May 11, 2008 3:08:32 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53249
;

-- May 11, 2008 3:08:32 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Column SET ColumnName='ComponentType', Name='Component Type', Description='Component Type for a Bill of Material or Formula', Help='The Component Type can be:

1.- By Product: Define a By Product as Component into BOM
2.- Component: Define a normal Component into BOM 
3.- Option: Define an Option for Product Configure BOM
4.- Phantom: Define a Phantom as Component into BOM
5.- Packing: Define a Packing as Component into BOM
6.- Planning : Define Planning as Component into BOM
7.- Tools: Define Tools as Component into BOM
8.- Variant: Define Variant  for Product Configure BOM
' WHERE AD_Element_ID=53249
;

-- May 11, 2008 3:08:32 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET Name='Component Type', Description='Component Type for a Bill of Material or Formula', Help='The Component Type can be:

1.- By Product: Define a By Product as Component into BOM
2.- Component: Define a normal Component into BOM 
3.- Option: Define an Option for Product Configure BOM
4.- Phantom: Define a Phantom as Component into BOM
5.- Packing: Define a Packing as Component into BOM
6.- Planning : Define Planning as Component into BOM
7.- Tools: Define Tools as Component into BOM
8.- Variant: Define Variant  for Product Configure BOM
' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53249) AND IsCentrallyMaintained='Y'
;

-- May 11, 2008 3:08:32 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Process_Para SET ColumnName='ComponentType', Name='Component Type', Description='Component Type for a Bill of Material or Formula', Help='The Component Type can be:

1.- By Product: Define a By Product as Component into BOM
2.- Component: Define a normal Component into BOM 
3.- Option: Define an Option for Product Configure BOM
4.- Phantom: Define a Phantom as Component into BOM
5.- Packing: Define a Packing as Component into BOM
6.- Planning : Define Planning as Component into BOM
7.- Tools: Define Tools as Component into BOM
8.- Variant: Define Variant  for Product Configure BOM
', AD_Element_ID=53249 WHERE UPPER(ColumnName)='COMPONENTTYPE' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- May 11, 2008 3:08:32 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Process_Para SET ColumnName='ComponentType', Name='Component Type', Description='Component Type for a Bill of Material or Formula', Help='The Component Type can be:

1.- By Product: Define a By Product as Component into BOM
2.- Component: Define a normal Component into BOM 
3.- Option: Define an Option for Product Configure BOM
4.- Phantom: Define a Phantom as Component into BOM
5.- Packing: Define a Packing as Component into BOM
6.- Planning : Define Planning as Component into BOM
7.- Tools: Define Tools as Component into BOM
8.- Variant: Define Variant  for Product Configure BOM
' WHERE AD_Element_ID=53249 AND IsCentrallyMaintained='Y'
;

-- May 11, 2008 3:08:32 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_PrintFormatItem pi SET PrintName='Component Type', Name='Component Type' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53249)
;

-- May 11, 2008 3:08:32 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_PrintFormatItem pi SET PrintName='Component Type', Name='Component Type' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53249)
;

-- May 11, 2008 3:13:03 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Element SET Description='Indicate that a Manufacturing Order can not begin without have this component', Help='Indicate that a Manufacturing Order can not begin without have this component',Updated=TO_DATE('2008-05-11 15:13:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53251
;

-- May 11, 2008 3:13:03 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53251
;

-- May 11, 2008 3:13:03 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Column SET ColumnName='IsCritical', Name='Is Critical Component', Description='Indicate that a Manufacturing Order can not begin without have this component', Help='Indicate that a Manufacturing Order can not begin without have this component' WHERE AD_Element_ID=53251
;

-- May 11, 2008 3:13:03 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET Name='Is Critical Component', Description='Indicate that a Manufacturing Order can not begin without have this component', Help='Indicate that a Manufacturing Order can not begin without have this component' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53251) AND IsCentrallyMaintained='Y'
;

-- May 11, 2008 3:13:03 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Process_Para SET ColumnName='IsCritical', Name='Is Critical Component', Description='Indicate that a Manufacturing Order can not begin without have this component', Help='Indicate that a Manufacturing Order can not begin without have this component', AD_Element_ID=53251 WHERE UPPER(ColumnName)='ISCRITICAL' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- May 11, 2008 3:13:03 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Process_Para SET ColumnName='IsCritical', Name='Is Critical Component', Description='Indicate that a Manufacturing Order can not begin without have this component', Help='Indicate that a Manufacturing Order can not begin without have this component' WHERE AD_Element_ID=53251 AND IsCentrallyMaintained='Y'
;

-- May 11, 2008 3:13:03 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_PrintFormatItem pi SET PrintName='Is Critical Component', Name='Is Critical Component' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53251)
;

-- May 11, 2008 3:13:03 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_PrintFormatItem pi SET PrintName='Is Critical Component', Name='Is Critical Component' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53251)
;

-- May 11, 2008 3:14:25 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Element SET Description='Indicate that this component is based in % Quantity', Help='Indicate that this component is based in % Quantity',Updated=TO_DATE('2008-05-11 15:14:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53252
;

-- May 11, 2008 3:14:25 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53252
;

-- May 11, 2008 3:14:25 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Column SET ColumnName='IsQtyPercentage', Name='Is Qty Percentage', Description='Indicate that this component is based in % Quantity', Help='Indicate that this component is based in % Quantity' WHERE AD_Element_ID=53252
;

-- May 11, 2008 3:14:25 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET Name='Is Qty Percentage', Description='Indicate that this component is based in % Quantity', Help='Indicate that this component is based in % Quantity' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53252) AND IsCentrallyMaintained='Y'
;

-- May 11, 2008 3:14:25 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Process_Para SET ColumnName='IsQtyPercentage', Name='Is Qty Percentage', Description='Indicate that this component is based in % Quantity', Help='Indicate that this component is based in % Quantity', AD_Element_ID=53252 WHERE UPPER(ColumnName)='ISQTYPERCENTAGE' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- May 11, 2008 3:14:25 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Process_Para SET ColumnName='IsQtyPercentage', Name='Is Qty Percentage', Description='Indicate that this component is based in % Quantity', Help='Indicate that this component is based in % Quantity' WHERE AD_Element_ID=53252 AND IsCentrallyMaintained='Y'
;

-- May 11, 2008 3:14:25 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_PrintFormatItem pi SET PrintName='Is Qty Percentage', Name='Is Qty Percentage' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53252)
;

-- May 11, 2008 3:14:25 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_PrintFormatItem pi SET PrintName='Is Qty Percentage', Name='Is Qty Percentage' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53252)
;

-- May 11, 2008 3:23:34 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Column SET ColumnSQL=NULL,Updated=TO_DATE('2008-05-11 15:23:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53359
;

-- May 11, 2008 3:26:14 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Element SET Description='There are two methods for issue the components to Manufacturing Order', Help='Method Issue: The component are delivered one for one and is necessary indicate the delivered quantity for each component.

Method BackFlush: The component are delivered based in BOM, The  delivered quantity for each component is based in BOM or Formula and Manufacturing Order Quantity.

Use the field Backflush Group for grouping the component in a Backflush Method.',Updated=TO_DATE('2008-05-11 15:26:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53253
;

-- May 11, 2008 3:26:14 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53253
;

-- May 11, 2008 3:26:14 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Column SET ColumnName='IssueMethod', Name='Issue Method', Description='There are two methods for issue the components to Manufacturing Order', Help='Method Issue: The component are delivered one for one and is necessary indicate the delivered quantity for each component.

Method BackFlush: The component are delivered based in BOM, The  delivered quantity for each component is based in BOM or Formula and Manufacturing Order Quantity.

Use the field Backflush Group for grouping the component in a Backflush Method.' WHERE AD_Element_ID=53253
;

-- May 11, 2008 3:26:14 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET Name='Issue Method', Description='There are two methods for issue the components to Manufacturing Order', Help='Method Issue: The component are delivered one for one and is necessary indicate the delivered quantity for each component.

Method BackFlush: The component are delivered based in BOM, The  delivered quantity for each component is based in BOM or Formula and Manufacturing Order Quantity.

Use the field Backflush Group for grouping the component in a Backflush Method.' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53253) AND IsCentrallyMaintained='Y'
;

-- May 11, 2008 3:26:14 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Process_Para SET ColumnName='IssueMethod', Name='Issue Method', Description='There are two methods for issue the components to Manufacturing Order', Help='Method Issue: The component are delivered one for one and is necessary indicate the delivered quantity for each component.

Method BackFlush: The component are delivered based in BOM, The  delivered quantity for each component is based in BOM or Formula and Manufacturing Order Quantity.

Use the field Backflush Group for grouping the component in a Backflush Method.', AD_Element_ID=53253 WHERE UPPER(ColumnName)='ISSUEMETHOD' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- May 11, 2008 3:26:14 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Process_Para SET ColumnName='IssueMethod', Name='Issue Method', Description='There are two methods for issue the components to Manufacturing Order', Help='Method Issue: The component are delivered one for one and is necessary indicate the delivered quantity for each component.

Method BackFlush: The component are delivered based in BOM, The  delivered quantity for each component is based in BOM or Formula and Manufacturing Order Quantity.

Use the field Backflush Group for grouping the component in a Backflush Method.' WHERE AD_Element_ID=53253 AND IsCentrallyMaintained='Y'
;

-- May 11, 2008 3:26:14 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_PrintFormatItem pi SET PrintName='Issue Method', Name='Issue Method' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53253)
;

-- May 11, 2008 3:26:14 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_PrintFormatItem pi SET PrintName='Issue Method', Name='Issue Method' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53253)
;

-- May 11, 2008 3:27:31 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET Description=NULL,Updated=TO_DATE('2008-05-11 15:27:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53502
;

-- May 11, 2008 3:27:31 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=53502
;

-- May 11, 2008 3:34:28 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Element SET Description='Indicate the Quantity  use in this BOM', Help='Exist two way the add a compenent to a BOM or Formula:

1.- Adding a Component based in quantity to use in this BOM
2.- Adding a Component based in % to use the Order Quantity of Manufacturing Order in this Formula.
', Name='Quantity of BOM', PrintName='Quantity of BOM',Updated=TO_DATE('2008-05-11 15:34:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53255
;

-- May 11, 2008 3:34:28 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53255
;

-- May 11, 2008 3:34:28 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Column SET ColumnName='QtyBOM', Name='Quantity of BOM', Description='Indicate the Quantity  use in this BOM', Help='Exist two way the add a compenent to a BOM or Formula:

1.- Adding a Component based in quantity to use in this BOM
2.- Adding a Component based in % to use the Order Quantity of Manufacturing Order in this Formula.
' WHERE AD_Element_ID=53255
;

-- May 11, 2008 3:34:28 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET Name='Quantity of BOM', Description='Indicate the Quantity  use in this BOM', Help='Exist two way the add a compenent to a BOM or Formula:

1.- Adding a Component based in quantity to use in this BOM
2.- Adding a Component based in % to use the Order Quantity of Manufacturing Order in this Formula.
' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53255) AND IsCentrallyMaintained='Y'
;

-- May 11, 2008 3:34:28 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Process_Para SET ColumnName='QtyBOM', Name='Quantity of BOM', Description='Indicate the Quantity  use in this BOM', Help='Exist two way the add a compenent to a BOM or Formula:

1.- Adding a Component based in quantity to use in this BOM
2.- Adding a Component based in % to use the Order Quantity of Manufacturing Order in this Formula.
', AD_Element_ID=53255 WHERE UPPER(ColumnName)='QTYBOM' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- May 11, 2008 3:34:28 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Process_Para SET ColumnName='QtyBOM', Name='Quantity of BOM', Description='Indicate the Quantity  use in this BOM', Help='Exist two way the add a compenent to a BOM or Formula:

1.- Adding a Component based in quantity to use in this BOM
2.- Adding a Component based in % to use the Order Quantity of Manufacturing Order in this Formula.
' WHERE AD_Element_ID=53255 AND IsCentrallyMaintained='Y'
;

-- May 11, 2008 3:34:28 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_PrintFormatItem pi SET PrintName='Quantity of BOM', Name='Quantity of BOM' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53255)
;

-- May 11, 2008 3:34:28 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_PrintFormatItem pi SET PrintName='Quantity of BOM', Name='Quantity of BOM' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53255)
;

-- May 11, 2008 3:36:47 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Element SET Description='Indicate the Quantity % use in this Formula', Help='Exist two way the add a compenent to a BOM or Formula:

1.- Adding a Component based in quantity to use in this BOM
2.- Adding a Component based in % to use the Order Quantity of Manufacturing Order in this Formula.
', Name='Quantity %', PrintName='Quantity %',Updated=TO_DATE('2008-05-11 15:36:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53256
;

-- May 11, 2008 3:36:47 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53256
;

-- May 11, 2008 3:36:47 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Column SET ColumnName='QtyBatch', Name='Quantity %', Description='Indicate the Quantity % use in this Formula', Help='Exist two way the add a compenent to a BOM or Formula:

1.- Adding a Component based in quantity to use in this BOM
2.- Adding a Component based in % to use the Order Quantity of Manufacturing Order in this Formula.
' WHERE AD_Element_ID=53256
;

-- May 11, 2008 3:36:47 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET Name='Quantity %', Description='Indicate the Quantity % use in this Formula', Help='Exist two way the add a compenent to a BOM or Formula:

1.- Adding a Component based in quantity to use in this BOM
2.- Adding a Component based in % to use the Order Quantity of Manufacturing Order in this Formula.
' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53256) AND IsCentrallyMaintained='Y'
;

-- May 11, 2008 3:36:48 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Process_Para SET ColumnName='QtyBatch', Name='Quantity %', Description='Indicate the Quantity % use in this Formula', Help='Exist two way the add a compenent to a BOM or Formula:

1.- Adding a Component based in quantity to use in this BOM
2.- Adding a Component based in % to use the Order Quantity of Manufacturing Order in this Formula.
', AD_Element_ID=53256 WHERE UPPER(ColumnName)='QTYBATCH' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- May 11, 2008 3:36:48 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Process_Para SET ColumnName='QtyBatch', Name='Quantity %', Description='Indicate the Quantity % use in this Formula', Help='Exist two way the add a compenent to a BOM or Formula:

1.- Adding a Component based in quantity to use in this BOM
2.- Adding a Component based in % to use the Order Quantity of Manufacturing Order in this Formula.
' WHERE AD_Element_ID=53256 AND IsCentrallyMaintained='Y'
;

-- May 11, 2008 3:36:48 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_PrintFormatItem pi SET PrintName='Quantity %', Name='Quantity %' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53256)
;

-- May 11, 2008 3:36:48 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_PrintFormatItem pi SET PrintName='Quantity %', Name='Quantity %' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53256)
;

-- May 11, 2008 3:41:30 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Element SET Description='Indicate the Scrap Quantity that is generate in a manufacturing process', Help='Scrap is useful to determinate a rigth Standard Cost and management a good supply.',Updated=TO_DATE('2008-05-11 15:41:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53257
;

-- May 11, 2008 3:41:30 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53257
;

-- May 11, 2008 3:41:30 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Column SET ColumnName='Scrap', Name='Scrap', Description='Indicate the Scrap Quantity that is generate in a manufacturing process', Help='Scrap is useful to determinate a rigth Standard Cost and management a good supply.' WHERE AD_Element_ID=53257
;

-- May 11, 2008 3:41:30 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET Name='Scrap', Description='Indicate the Scrap Quantity that is generate in a manufacturing process', Help='Scrap is useful to determinate a rigth Standard Cost and management a good supply.' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53257) AND IsCentrallyMaintained='Y'
;

-- May 11, 2008 3:41:30 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Process_Para SET ColumnName='Scrap', Name='Scrap', Description='Indicate the Scrap Quantity that is generate in a manufacturing process', Help='Scrap is useful to determinate a rigth Standard Cost and management a good supply.', AD_Element_ID=53257 WHERE UPPER(ColumnName)='SCRAP' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- May 11, 2008 3:41:30 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Process_Para SET ColumnName='Scrap', Name='Scrap', Description='Indicate the Scrap Quantity that is generate in a manufacturing process', Help='Scrap is useful to determinate a rigth Standard Cost and management a good supply.' WHERE AD_Element_ID=53257 AND IsCentrallyMaintained='Y'
;

-- May 11, 2008 3:41:30 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_PrintFormatItem pi SET PrintName='Scrap', Name='Scrap' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53257)
;

-- May 11, 2008 3:41:30 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_PrintFormatItem pi SET PrintName='Scrap', Name='Scrap' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53257)
;

-- May 11, 2008 3:58:19 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Element SET Description='Indicated the % of participation this component into a of the BOM Planning', Help='The BOM of Planning Type are useful to Planning the Product family.

For example is possible create a BOM Planning for an Automobile

10% Automobile Red
35% Automobile Blue
45% Automobile Black
19% Automobile Green
1%  Automobile Orange

When Material Plan is calculated MRP generate a Manufacturing Order meet to demand to each  of the Automobile',Updated=TO_DATE('2008-05-11 15:58:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53250
;

-- May 11, 2008 3:58:19 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53250
;

-- May 11, 2008 3:58:19 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Column SET ColumnName='Forecast', Name='Forecast', Description='Indicated the % of participation this component into a of the BOM Planning', Help='The BOM of Planning Type are useful to Planning the Product family.

For example is possible create a BOM Planning for an Automobile

10% Automobile Red
35% Automobile Blue
45% Automobile Black
19% Automobile Green
1%  Automobile Orange

When Material Plan is calculated MRP generate a Manufacturing Order meet to demand to each  of the Automobile' WHERE AD_Element_ID=53250
;

-- May 11, 2008 3:58:19 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET Name='Forecast', Description='Indicated the % of participation this component into a of the BOM Planning', Help='The BOM of Planning Type are useful to Planning the Product family.

For example is possible create a BOM Planning for an Automobile

10% Automobile Red
35% Automobile Blue
45% Automobile Black
19% Automobile Green
1%  Automobile Orange

When Material Plan is calculated MRP generate a Manufacturing Order meet to demand to each  of the Automobile' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53250) AND IsCentrallyMaintained='Y'
;

-- May 11, 2008 3:58:19 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Process_Para SET ColumnName='Forecast', Name='Forecast', Description='Indicated the % of participation this component into a of the BOM Planning', Help='The BOM of Planning Type are useful to Planning the Product family.

For example is possible create a BOM Planning for an Automobile

10% Automobile Red
35% Automobile Blue
45% Automobile Black
19% Automobile Green
1%  Automobile Orange

When Material Plan is calculated MRP generate a Manufacturing Order meet to demand to each  of the Automobile', AD_Element_ID=53250 WHERE UPPER(ColumnName)='FORECAST' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- May 11, 2008 3:58:19 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Process_Para SET ColumnName='Forecast', Name='Forecast', Description='Indicated the % of participation this component into a of the BOM Planning', Help='The BOM of Planning Type are useful to Planning the Product family.

For example is possible create a BOM Planning for an Automobile

10% Automobile Red
35% Automobile Blue
45% Automobile Black
19% Automobile Green
1%  Automobile Orange

When Material Plan is calculated MRP generate a Manufacturing Order meet to demand to each  of the Automobile' WHERE AD_Element_ID=53250 AND IsCentrallyMaintained='Y'
;

-- May 11, 2008 3:58:19 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_PrintFormatItem pi SET PrintName='Forecast', Name='Forecast' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53250)
;

-- May 11, 2008 3:58:19 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_PrintFormatItem pi SET PrintName='Forecast', Name='Forecast' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53250)
;

-- May 11, 2008 4:01:01 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Element SET Description='Indicated the Feature for Product Configure', Help='Indicated the Feature for Product Configure',Updated=TO_DATE('2008-05-11 16:01:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53246
;

-- May 11, 2008 4:01:01 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53246
;

-- May 11, 2008 4:01:01 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Column SET ColumnName='Feature', Name='Feature', Description='Indicated the Feature for Product Configure', Help='Indicated the Feature for Product Configure' WHERE AD_Element_ID=53246
;

-- May 11, 2008 4:01:01 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET Name='Feature', Description='Indicated the Feature for Product Configure', Help='Indicated the Feature for Product Configure' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53246) AND IsCentrallyMaintained='Y'
;

-- May 11, 2008 4:01:01 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Process_Para SET ColumnName='Feature', Name='Feature', Description='Indicated the Feature for Product Configure', Help='Indicated the Feature for Product Configure', AD_Element_ID=53246 WHERE UPPER(ColumnName)='FEATURE' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- May 11, 2008 4:01:01 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Process_Para SET ColumnName='Feature', Name='Feature', Description='Indicated the Feature for Product Configure', Help='Indicated the Feature for Product Configure' WHERE AD_Element_ID=53246 AND IsCentrallyMaintained='Y'
;

-- May 11, 2008 4:01:01 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_PrintFormatItem pi SET PrintName='Feature', Name='Feature' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53246)
;

-- May 11, 2008 4:01:01 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_PrintFormatItem pi SET PrintName='Feature', Name='Feature' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53246)
;

-- May 11, 2008 4:03:40 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Element SET Description='Indicated the Quantity Assay to use into Quality Order', Help='Indicated the Quantity Assay to use into Quality Order', Name='Quantity Assay', PrintName='Quantity Assay',Updated=TO_DATE('2008-05-11 16:03:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53247
;

-- May 11, 2008 4:03:40 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53247
;

-- May 11, 2008 4:03:40 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Column SET ColumnName='Assay', Name='Quantity Assay', Description='Indicated the Quantity Assay to use into Quality Order', Help='Indicated the Quantity Assay to use into Quality Order' WHERE AD_Element_ID=53247
;

-- May 11, 2008 4:03:40 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET Name='Quantity Assay', Description='Indicated the Quantity Assay to use into Quality Order', Help='Indicated the Quantity Assay to use into Quality Order' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53247) AND IsCentrallyMaintained='Y'
;

-- May 11, 2008 4:03:40 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Process_Para SET ColumnName='Assay', Name='Quantity Assay', Description='Indicated the Quantity Assay to use into Quality Order', Help='Indicated the Quantity Assay to use into Quality Order', AD_Element_ID=53247 WHERE UPPER(ColumnName)='ASSAY' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- May 11, 2008 4:03:40 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Process_Para SET ColumnName='Assay', Name='Quantity Assay', Description='Indicated the Quantity Assay to use into Quality Order', Help='Indicated the Quantity Assay to use into Quality Order' WHERE AD_Element_ID=53247 AND IsCentrallyMaintained='Y'
;

-- May 11, 2008 4:03:40 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_PrintFormatItem pi SET PrintName='Quantity Assay', Name='Quantity Assay' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53247)
;

-- May 11, 2008 4:03:40 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_PrintFormatItem pi SET PrintName='Quantity Assay', Name='Quantity Assay' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53247)
;

-- May 11, 2008 4:07:03 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Element SET Help='When the components are deliver is possible to indicated The Backflush Group this way you only can deliver the components that are for this Backflush Group.',Updated=TO_DATE('2008-05-11 16:07:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53248
;

-- May 11, 2008 4:07:03 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53248
;

-- May 11, 2008 4:07:03 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Column SET ColumnName='BackflushGroup', Name='Backflush Group', Description='The Grouping Components to the Backflush', Help='When the components are deliver is possible to indicated The Backflush Group this way you only can deliver the components that are for this Backflush Group.' WHERE AD_Element_ID=53248
;

-- May 11, 2008 4:07:03 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET Name='Backflush Group', Description='The Grouping Components to the Backflush', Help='When the components are deliver is possible to indicated The Backflush Group this way you only can deliver the components that are for this Backflush Group.' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53248) AND IsCentrallyMaintained='Y'
;

-- May 11, 2008 4:07:03 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Process_Para SET ColumnName='BackflushGroup', Name='Backflush Group', Description='The Grouping Components to the Backflush', Help='When the components are deliver is possible to indicated The Backflush Group this way you only can deliver the components that are for this Backflush Group.', AD_Element_ID=53248 WHERE UPPER(ColumnName)='BACKFLUSHGROUP' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- May 11, 2008 4:07:03 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Process_Para SET ColumnName='BackflushGroup', Name='Backflush Group', Description='The Grouping Components to the Backflush', Help='When the components are deliver is possible to indicated The Backflush Group this way you only can deliver the components that are for this Backflush Group.' WHERE AD_Element_ID=53248 AND IsCentrallyMaintained='Y'
;

-- May 11, 2008 4:07:03 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_PrintFormatItem pi SET PrintName='Backflush Group', Name='Backflush Group' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53248)
;

-- May 11, 2008 4:07:04 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_PrintFormatItem pi SET PrintName='Backflush Group', Name='Backflush Group' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53248)
;

-- May 11, 2008 4:07:25 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Element SET Help='Optional Lead Time offest before starting production',Updated=TO_DATE('2008-05-11 16:07:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=2789
;

-- May 11, 2008 4:07:25 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2789
;

-- May 11, 2008 4:07:25 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Column SET ColumnName='LeadTimeOffset', Name='Lead Time Offset', Description='Optional Lead Time offest before starting production', Help='Optional Lead Time offest before starting production' WHERE AD_Element_ID=2789
;

-- May 11, 2008 4:07:26 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET Name='Lead Time Offset', Description='Optional Lead Time offest before starting production', Help='Optional Lead Time offest before starting production' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=2789) AND IsCentrallyMaintained='Y'
;

-- May 11, 2008 4:07:26 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Process_Para SET ColumnName='LeadTimeOffset', Name='Lead Time Offset', Description='Optional Lead Time offest before starting production', Help='Optional Lead Time offest before starting production', AD_Element_ID=2789 WHERE UPPER(ColumnName)='LEADTIMEOFFSET' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- May 11, 2008 4:07:26 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Process_Para SET ColumnName='LeadTimeOffset', Name='Lead Time Offset', Description='Optional Lead Time offest before starting production', Help='Optional Lead Time offest before starting production' WHERE AD_Element_ID=2789 AND IsCentrallyMaintained='Y'
;

-- May 11, 2008 4:07:26 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_PrintFormatItem pi SET PrintName='Lead Time Offset', Name='Lead Time Offset' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=2789)
;

-- May 11, 2008 4:07:26 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_PrintFormatItem pi SET PrintName='Lead Time Offset', Name='Lead Time Offset' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=2789)
;

-- May 11, 2008 4:07:31 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Column SET ColumnSQL=NULL,Updated=TO_DATE('2008-05-11 16:07:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53360
;

-- May 11, 2008 4:09:55 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Element SET Description='BOM Line', Help='The BOM Line is a unique identifier for a BOM line in an BOM.', Name='BOM Line', PrintName='BOM Line',Updated=TO_DATE('2008-05-11 16:09:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53254
;

-- May 11, 2008 4:09:55 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53254
;

-- May 11, 2008 4:09:55 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Column SET ColumnName='PP_Product_BOMLine_ID', Name='BOM Line', Description='BOM Line', Help='The BOM Line is a unique identifier for a BOM line in an BOM.' WHERE AD_Element_ID=53254
;

-- May 11, 2008 4:09:55 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET Name='BOM Line', Description='BOM Line', Help='The BOM Line is a unique identifier for a BOM line in an BOM.' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53254) AND IsCentrallyMaintained='Y'
;

-- May 11, 2008 4:09:56 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Process_Para SET ColumnName='PP_Product_BOMLine_ID', Name='BOM Line', Description='BOM Line', Help='The BOM Line is a unique identifier for a BOM line in an BOM.', AD_Element_ID=53254 WHERE UPPER(ColumnName)='PP_PRODUCT_BOMLINE_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- May 11, 2008 4:09:56 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Process_Para SET ColumnName='PP_Product_BOMLine_ID', Name='BOM Line', Description='BOM Line', Help='The BOM Line is a unique identifier for a BOM line in an BOM.' WHERE AD_Element_ID=53254 AND IsCentrallyMaintained='Y'
;

-- May 11, 2008 4:09:56 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_PrintFormatItem pi SET PrintName='BOM Line', Name='BOM Line' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53254)
;

-- May 11, 2008 4:09:56 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_PrintFormatItem pi SET PrintName='BOM Line', Name='BOM Line' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53254)
;

-- May 11, 2008 4:11:45 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Element SET Description='BOM & Formula', Help=NULL, Name='BOM & Formula', PrintName='BOM & Formula',Updated=TO_DATE('2008-05-11 16:11:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53245
;

-- May 11, 2008 4:11:45 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53245
;

-- May 11, 2008 4:11:45 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Column SET ColumnName='PP_Product_BOM_ID', Name='BOM & Formula', Description='BOM & Formula', Help=NULL WHERE AD_Element_ID=53245
;

-- May 11, 2008 4:11:45 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET Name='BOM & Formula', Description='BOM & Formula', Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53245) AND IsCentrallyMaintained='Y'
;

-- May 11, 2008 4:11:45 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Process_Para SET ColumnName='PP_Product_BOM_ID', Name='BOM & Formula', Description='BOM & Formula', Help=NULL, AD_Element_ID=53245 WHERE UPPER(ColumnName)='PP_PRODUCT_BOM_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- May 11, 2008 4:11:45 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Process_Para SET ColumnName='PP_Product_BOM_ID', Name='BOM & Formula', Description='BOM & Formula', Help=NULL WHERE AD_Element_ID=53245 AND IsCentrallyMaintained='Y'
;

-- May 11, 2008 4:11:45 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_PrintFormatItem pi SET PrintName='BOM & Formula', Name='BOM & Formula' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53245)
;

-- May 11, 2008 4:11:45 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_PrintFormatItem pi SET PrintName='BOM & Formula', Name='BOM & Formula' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53245)
;

-- May 11, 2008 4:13:12 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET DefaultValue='O',Updated=TO_DATE('2008-05-11 16:13:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53503
;

-- May 11, 2008 4:14:13 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET DefaultValue='''O''',Updated=TO_DATE('2008-05-11 16:14:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53503
;

-- May 11, 2008 4:15:18 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET DefaultValue=NULL,Updated=TO_DATE('2008-05-11 16:15:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53503
;

-- May 11, 2008 4:16:06 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Column SET DefaultValue='1',Updated=TO_DATE('2008-05-11 16:16:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53359
;

-- May 11, 2008 4:16:10 PM CDT
-- Fixed Libero BOM Window
ALTER TABLE PP_Product_BOMLine MODIFY IssueMethod CHAR(1) DEFAULT '1'
;

-- May 11, 2008 4:16:11 PM CDT
-- Fixed Libero BOM Window
UPDATE PP_Product_BOMLine SET IssueMethod='1' WHERE IssueMethod IS NULL
;

-- May 11, 2008 4:27:24 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=53492
;

-- May 11, 2008 4:27:24 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=53493
;

-- May 11, 2008 4:27:24 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=53486
;

-- May 11, 2008 4:27:24 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=53487
;

-- May 11, 2008 4:27:24 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=53488
;

-- May 11, 2008 4:27:24 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=53494
;

-- May 11, 2008 4:27:24 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=53489
;

-- May 11, 2008 4:27:24 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=53490
;

-- May 11, 2008 4:27:24 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=53491
;

-- May 11, 2008 4:28:25 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=53496
;

-- May 11, 2008 4:28:25 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=53497
;

-- May 11, 2008 4:28:25 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=53499
;

-- May 11, 2008 4:28:25 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=53500
;

-- May 11, 2008 4:28:25 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=53498
;

-- May 11, 2008 4:28:25 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=53489
;

-- May 11, 2008 4:28:25 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=53490
;

-- May 11, 2008 4:28:25 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=53491
;

-- May 11, 2008 4:28:25 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=53495
;

-- May 11, 2008 4:29:33 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=53500
;

-- May 11, 2008 4:29:33 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=53493
;

-- May 11, 2008 4:29:33 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=53486
;

-- May 11, 2008 4:29:33 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=53487
;

-- May 11, 2008 4:29:33 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=53488
;

-- May 11, 2008 4:29:33 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=53494
;

-- May 11, 2008 4:29:33 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=53496
;

-- May 11, 2008 4:29:33 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=53497
;

-- May 11, 2008 4:29:33 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=53499
;

-- May 11, 2008 4:29:49 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET IsSameLine='N',Updated=TO_DATE('2008-05-11 16:29:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53500
;

-- May 11, 2008 4:30:43 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=53494
;

-- May 11, 2008 4:30:43 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=53500
;

-- May 11, 2008 4:30:43 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=53493
;

-- May 11, 2008 4:30:43 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=53486
;

-- May 11, 2008 4:30:43 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=53487
;

-- May 11, 2008 4:30:43 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=53488
;

-- May 11, 2008 4:30:52 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2008-05-11 16:30:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53494
;

-- May 11, 2008 4:31:48 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET IsFieldOnly='N',Updated=TO_DATE('2008-05-11 16:31:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53500
;

-- May 11, 2008 4:33:07 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET DisplayLogic='@IssueMethod@=1',Updated=TO_DATE('2008-05-11 16:33:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53505
;

-- May 11, 2008 4:39:10 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET DisplayLogic='@BOMUse@=''P''',Updated=TO_DATE('2008-05-11 16:39:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53506
;

-- May 11, 2008 4:41:13 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Element SET Name='Quantity', PrintName='Quantity',Updated=TO_DATE('2008-05-11 16:41:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53255
;

-- May 11, 2008 4:41:13 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53255
;

-- May 11, 2008 4:41:13 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Column SET ColumnName='QtyBOM', Name='Quantity', Description='Indicate the Quantity  use in this BOM', Help='Exist two way the add a compenent to a BOM or Formula:

1.- Adding a Component based in quantity to use in this BOM
2.- Adding a Component based in % to use the Order Quantity of Manufacturing Order in this Formula.
' WHERE AD_Element_ID=53255
;

-- May 11, 2008 4:41:13 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET Name='Quantity', Description='Indicate the Quantity  use in this BOM', Help='Exist two way the add a compenent to a BOM or Formula:

1.- Adding a Component based in quantity to use in this BOM
2.- Adding a Component based in % to use the Order Quantity of Manufacturing Order in this Formula.
' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53255) AND IsCentrallyMaintained='Y'
;

-- May 11, 2008 4:41:13 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Process_Para SET ColumnName='QtyBOM', Name='Quantity', Description='Indicate the Quantity  use in this BOM', Help='Exist two way the add a compenent to a BOM or Formula:

1.- Adding a Component based in quantity to use in this BOM
2.- Adding a Component based in % to use the Order Quantity of Manufacturing Order in this Formula.
', AD_Element_ID=53255 WHERE UPPER(ColumnName)='QTYBOM' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- May 11, 2008 4:41:13 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Process_Para SET ColumnName='QtyBOM', Name='Quantity', Description='Indicate the Quantity  use in this BOM', Help='Exist two way the add a compenent to a BOM or Formula:

1.- Adding a Component based in quantity to use in this BOM
2.- Adding a Component based in % to use the Order Quantity of Manufacturing Order in this Formula.
' WHERE AD_Element_ID=53255 AND IsCentrallyMaintained='Y'
;

-- May 11, 2008 4:41:13 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_PrintFormatItem pi SET PrintName='Quantity', Name='Quantity' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53255)
;

-- May 11, 2008 4:41:13 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_PrintFormatItem pi SET PrintName='Quantity', Name='Quantity' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53255)
;

-- May 11, 2008 4:42:46 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=53502
;

-- May 11, 2008 4:42:46 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=53501
;

-- May 11, 2008 4:42:46 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=53489
;

-- May 11, 2008 4:42:46 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=53490
;

-- May 11, 2008 4:42:46 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=53491
;

-- May 11, 2008 4:42:46 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=53503
;

-- May 11, 2008 4:42:46 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=53505
;

-- May 11, 2008 4:42:46 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=53495
;

-- May 11, 2008 4:43:33 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET IsSameLine='N',Updated=TO_DATE('2008-05-11 16:43:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53495
;

-- May 11, 2008 4:43:37 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET IsSameLine='N',Updated=TO_DATE('2008-05-11 16:43:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53506
;

-- May 11, 2008 4:43:46 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET IsSameLine='N',Updated=TO_DATE('2008-05-11 16:43:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53504
;

-- May 11, 2008 4:43:54 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2008-05-11 16:43:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53505
;

-- May 11, 2008 4:45:12 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=53489
;

-- May 11, 2008 4:45:12 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=53496
;

-- May 11, 2008 4:45:12 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=53497
;

-- May 11, 2008 4:45:12 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=53499
;

-- May 11, 2008 4:45:12 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=53498
;

-- May 11, 2008 4:45:12 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=53502
;

-- May 11, 2008 4:45:12 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=53501
;

-- May 11, 2008 4:46:03 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET IsSameLine='N',Updated=TO_DATE('2008-05-11 16:46:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53502
;

-- May 11, 2008 4:46:05 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2008-05-11 16:46:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53501
;

-- May 11, 2008 4:47:03 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=53490
;

-- May 11, 2008 4:47:03 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=53491
;

-- May 11, 2008 4:47:03 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=53496
;

-- May 11, 2008 4:47:03 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=53497
;

-- May 11, 2008 4:47:03 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=53499
;

-- May 11, 2008 4:47:03 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=53498
;

-- May 11, 2008 4:47:03 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=53502
;

-- May 11, 2008 4:47:03 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=53501
;

-- May 11, 2008 4:48:49 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_WF_Node SET XPosition=5, YPosition=5,Updated=TO_DATE('2008-05-11 16:48:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_WF_Node_ID=50007
;

-- May 11, 2008 4:48:49 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_WF_Node SET XPosition=230, YPosition=5,Updated=TO_DATE('2008-05-11 16:48:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_WF_Node_ID=50008
;

-- May 11, 2008 4:48:49 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_WF_Node SET XPosition=0, YPosition=189,Updated=TO_DATE('2008-05-11 16:48:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_WF_Node_ID=50049
;

-- May 11, 2008 4:48:49 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_WF_Node SET XPosition=234, YPosition=91,Updated=TO_DATE('2008-05-11 16:48:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_WF_Node_ID=50050
;

-- May 11, 2008 4:48:49 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_WF_Node SET XPosition=1, YPosition=91,Updated=TO_DATE('2008-05-11 16:48:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_WF_Node_ID=50051
;

-- May 11, 2008 4:53:01 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Process_Para SET Name='New Product',Updated=TO_DATE('2008-05-11 16:53:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53030
;

-- May 11, 2008 4:53:01 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=53030
;

-- May 11, 2008 4:57:28 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Tab SET IsActive='N',Updated=TO_DATE('2008-05-11 16:57:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=317
;

-- May 11, 2008 4:57:44 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Window SET IsActive='N',Updated=TO_DATE('2008-05-11 16:57:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Window_ID=354
;

-- May 11, 2008 4:57:44 PM CDT
-- Fixed Libero BOM Window
UPDATE AD_Menu SET Description='Maintain Product Bill of Materials', IsActive='N', Name='Product BOM',Updated=TO_DATE('2008-05-11 16:57:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Menu_ID=534
;

