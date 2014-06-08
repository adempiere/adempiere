SET SQLBLANKLINES ON
SET DEFINE OFF

-- Sep 17, 2010 4:26:33 PM CDT
-- Cost Engine Report
UPDATE AD_ReportView SET AD_Table_ID=478, Description='Inventory valuation', EntityType='D', IsActive='Y', Name='RV_T_InventoryValue', OrderByClause=NULL, WhereClause=NULL,Updated=TO_DATE('2010-09-17 16:26:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_ReportView_ID=124
;

-- Sep 17, 2010 4:26:34 PM CDT
-- Cost Engine Report
UPDATE AD_Table SET AccessLevel='3', Description=NULL, EntityType='D', Help=NULL, ImportTable=NULL, IsActive='Y', IsChangeLog='N', IsDeleteable='Y', IsHighVolume='N', IsSecurityEnabled='N', IsView='N', Name='T_InventoryValue', ReplicationType='L', TableName='T_InventoryValue',Updated=TO_DATE('2010-09-17 16:26:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Table_ID=478
;

-- Sep 17, 2010 4:26:34 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='AD_Client_ID', Description='Client/Tenant for this installation.', EntityType='D', Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', IsActive='Y', Name='Client', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Client',Updated=TO_DATE('2010-09-17 16:26:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=102
;

-- Sep 17, 2010 4:26:34 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=102
;

-- Sep 17, 2010 4:26:34 PM CDT
-- Cost Engine Report
UPDATE AD_Reference SET Description='Direct Table Access', EntityType='D', Help=NULL, IsActive='Y', Name='Table Direct', ValidationType='D',Updated=TO_DATE('2010-09-17 16:26:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=19
;

-- Sep 17, 2010 4:26:34 PM CDT
-- Cost Engine Report
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=19
;

-- Sep 17, 2010 4:26:34 PM CDT
-- Cost Engine Report
UPDATE AD_Val_Rule SET Code='AD_Client.AD_Client_ID <> 0', Description=NULL, EntityType='D', IsActive='Y', Name='AD_Client Trx Security validation', Type='S',Updated=TO_DATE('2010-09-17 16:26:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Val_Rule_ID=129
;

-- Sep 17, 2010 4:26:35 PM CDT
-- Cost Engine Report
UPDATE AD_Column SET AD_Element_ID=102, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=478, AD_Val_Rule_ID=129, Callout=NULL, ColumnName='AD_Client_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Client/Tenant for this installation.', EntityType='D', FieldLength=22, Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Client', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2010-09-17 16:26:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=6717
;

-- Sep 17, 2010 4:26:35 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='AD_Org_ID', Description='Organizational entity within client', EntityType='D', Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', IsActive='Y', Name='Organization', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Organization',Updated=TO_DATE('2010-09-17 16:26:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=113
;

-- Sep 17, 2010 4:26:35 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=113
;

-- Sep 17, 2010 4:26:36 PM CDT
-- Cost Engine Report
UPDATE AD_Column SET AD_Element_ID=113, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=478, AD_Val_Rule_ID=104, Callout=NULL, ColumnName='AD_Org_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Organizational entity within client', EntityType='D', FieldLength=22, Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Organization', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2010-09-17 16:26:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=6718
;

-- Sep 17, 2010 4:26:36 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='M_Warehouse_ID', Description='Storage Warehouse and Service Point', EntityType='D', Help='The Warehouse identifies a unique Warehouse where products are stored or Services are provided.', IsActive='Y', Name='Warehouse', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Warehouse',Updated=TO_DATE('2010-09-17 16:26:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=459
;

-- Sep 17, 2010 4:26:36 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=459
;

-- Sep 17, 2010 4:26:36 PM CDT
-- Cost Engine Report
UPDATE AD_Column SET AD_Element_ID=459, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=478, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='M_Warehouse_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Storage Warehouse and Service Point', EntityType='D', FieldLength=22, Help='The Warehouse identifies a unique Warehouse where products are stored or Services are provided.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Warehouse', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2010-09-17 16:26:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=6719
;

-- Sep 17, 2010 4:26:36 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='M_Product_ID', Description='Product, Service, Item', EntityType='D', Help='Identifies an item which is either purchased or sold in this organization.', IsActive='Y', Name='Product', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Product',Updated=TO_DATE('2010-09-17 16:26:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=454
;

-- Sep 17, 2010 4:26:36 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=454
;

-- Sep 17, 2010 4:26:37 PM CDT
-- Cost Engine Report
UPDATE AD_Reference SET Description='Search Field', EntityType='D', Help=NULL, IsActive='Y', Name='Search', ValidationType='D',Updated=TO_DATE('2010-09-17 16:26:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=30
;

-- Sep 17, 2010 4:26:37 PM CDT
-- Cost Engine Report
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=30
;

-- Sep 17, 2010 4:26:37 PM CDT
-- Cost Engine Report
UPDATE AD_Column SET AD_Element_ID=454, AD_Process_ID=NULL, AD_Reference_ID=30, AD_Reference_Value_ID=NULL, AD_Table_ID=478, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='M_Product_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Product, Service, Item', EntityType='D', FieldLength=22, Help='Identifies an item which is either purchased or sold in this organization.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Product', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2010-09-17 16:26:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=6720
;

-- Sep 17, 2010 4:26:37 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='M_PriceList_Version_ID', Description='Identifies a unique instance of a Price List', EntityType='D', Help='Each Price List can have multiple versions.  The most common use is to indicate the dates that a Price List is valid for.', IsActive='Y', Name='Price List Version', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Price List Version',Updated=TO_DATE('2010-09-17 16:26:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=450
;

-- Sep 17, 2010 4:26:37 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=450
;

-- Sep 17, 2010 4:26:37 PM CDT
-- Cost Engine Report
UPDATE AD_Column SET AD_Element_ID=450, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=478, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='M_PriceList_Version_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Identifies a unique instance of a Price List', EntityType='D', FieldLength=22, Help='Each Price List can have multiple versions.  The most common use is to indicate the dates that a Price List is valid for.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Price List Version', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2010-09-17 16:26:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=6721
;

-- Sep 17, 2010 4:26:37 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='DateValue', Description='Date of valuation', EntityType='D', Help=NULL, IsActive='Y', Name='Valuation Date', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Valuation Date',Updated=TO_DATE('2010-09-17 16:26:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1727
;

-- Sep 17, 2010 4:26:37 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1727
;

-- Sep 17, 2010 4:26:37 PM CDT
-- Cost Engine Report
UPDATE AD_Reference SET Description='Date mm/dd/yyyy', EntityType='D', Help=NULL, IsActive='Y', Name='Date', ValidationType='D',Updated=TO_DATE('2010-09-17 16:26:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=15
;

-- Sep 17, 2010 4:26:37 PM CDT
-- Cost Engine Report
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=15
;

-- Sep 17, 2010 4:26:38 PM CDT
-- Cost Engine Report
UPDATE AD_Column SET AD_Element_ID=1727, AD_Process_ID=NULL, AD_Reference_ID=15, AD_Reference_Value_ID=NULL, AD_Table_ID=478, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='DateValue', ColumnSQL=NULL, DefaultValue=NULL, Description='Date of valuation', EntityType='D', FieldLength=7, Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Valuation Date', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2010-09-17 16:26:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=6722
;

-- Sep 17, 2010 4:26:38 PM CDT
-- Cost Engine Report
UPDATE AD_Field SET Name='Valuation Date', Description='Date of valuation', Help=NULL WHERE AD_Column_ID=6722 AND IsCentrallyMaintained='Y'
;

-- Sep 17, 2010 4:26:38 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='C_Currency_ID', Description='The Currency for this record', EntityType='D', Help='Indicates the Currency to be used when processing or reporting on this record', IsActive='Y', Name='Currency', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Currency',Updated=TO_DATE('2010-09-17 16:26:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=193
;

-- Sep 17, 2010 4:26:38 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=193
;

-- Sep 17, 2010 4:26:39 PM CDT
-- Cost Engine Report
UPDATE AD_Column SET AD_Element_ID=193, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=478, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='C_Currency_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='The Currency for this record', EntityType='D', FieldLength=22, Help='Indicates the Currency to be used when processing or reporting on this record', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Currency', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2010-09-17 16:26:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=6723
;

-- Sep 17, 2010 4:26:39 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='QtyOnHand', Description='On Hand Quantity', EntityType='D', Help='The On Hand Quantity indicates the quantity of a product that is on hand in a warehouse.', IsActive='Y', Name='On Hand Quantity', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='On Hand Qty',Updated=TO_DATE('2010-09-17 16:26:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=530
;

-- Sep 17, 2010 4:26:39 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=530
;

-- Sep 17, 2010 4:26:39 PM CDT
-- Cost Engine Report
UPDATE AD_Reference SET Description='Quantity data type', EntityType='D', Help=NULL, IsActive='Y', Name='Quantity', ValidationType='D',Updated=TO_DATE('2010-09-17 16:26:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=29
;

-- Sep 17, 2010 4:26:39 PM CDT
-- Cost Engine Report
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=29
;

-- Sep 17, 2010 4:26:39 PM CDT
-- Cost Engine Report
UPDATE AD_Column SET AD_Element_ID=530, AD_Process_ID=NULL, AD_Reference_ID=29, AD_Reference_Value_ID=NULL, AD_Table_ID=478, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='QtyOnHand', ColumnSQL=NULL, DefaultValue=NULL, Description='On Hand Quantity', EntityType='D', FieldLength=22, Help='The On Hand Quantity indicates the quantity of a product that is on hand in a warehouse.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='On Hand Quantity', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2010-09-17 16:26:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=6724
;

-- Sep 17, 2010 4:26:39 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='PricePO', Description='Price based on a purchase order', EntityType='D', Help='The PO Price indicates the price for a product per the purchase order.', IsActive='Y', Name='PO Price', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='PO Price',Updated=TO_DATE('2010-09-17 16:26:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1124
;

-- Sep 17, 2010 4:26:39 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1124
;

-- Sep 17, 2010 4:26:39 PM CDT
-- Cost Engine Report
UPDATE AD_Reference SET Description='Costs + Prices (minimum currency precision but if exists more)', EntityType='D', Help=NULL, IsActive='Y', Name='Costs+Prices', ValidationType='D',Updated=TO_DATE('2010-09-17 16:26:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=37
;

-- Sep 17, 2010 4:26:39 PM CDT
-- Cost Engine Report
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=37
;

-- Sep 17, 2010 4:26:40 PM CDT
-- Cost Engine Report
UPDATE AD_Column SET AD_Element_ID=1124, AD_Process_ID=NULL, AD_Reference_ID=37, AD_Reference_Value_ID=NULL, AD_Table_ID=478, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='PricePO', ColumnSQL=NULL, DefaultValue=NULL, Description='Price based on a purchase order', EntityType='D', FieldLength=22, Help='The PO Price indicates the price for a product per the purchase order.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='PO Price', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2010-09-17 16:26:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=6725
;

-- Sep 17, 2010 4:26:40 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='PriceList', Description='List Price', EntityType='D', Help='The List Price is the official List Price in the document currency.', IsActive='Y', Name='List Price', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='List Price',Updated=TO_DATE('2010-09-17 16:26:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=520
;

-- Sep 17, 2010 4:26:40 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=520
;

-- Sep 17, 2010 4:26:40 PM CDT
-- Cost Engine Report
UPDATE AD_Column SET AD_Element_ID=520, AD_Process_ID=NULL, AD_Reference_ID=37, AD_Reference_Value_ID=NULL, AD_Table_ID=478, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='PriceList', ColumnSQL=NULL, DefaultValue=NULL, Description='List Price', EntityType='D', FieldLength=22, Help='The List Price is the official List Price in the document currency.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='List Price', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2010-09-17 16:26:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=6726
;

-- Sep 17, 2010 4:26:40 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='PriceStd', Description='Standard Price', EntityType='D', Help='The Standard Price indicates the standard or normal price for a product on this price list', IsActive='Y', Name='Standard Price', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Std Price',Updated=TO_DATE('2010-09-17 16:26:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=957
;

-- Sep 17, 2010 4:26:40 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=957
;

-- Sep 17, 2010 4:26:41 PM CDT
-- Cost Engine Report
UPDATE AD_Column SET AD_Element_ID=957, AD_Process_ID=NULL, AD_Reference_ID=37, AD_Reference_Value_ID=NULL, AD_Table_ID=478, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='PriceStd', ColumnSQL=NULL, DefaultValue=NULL, Description='Standard Price', EntityType='D', FieldLength=22, Help='The Standard Price indicates the standard or normal price for a product on this price list', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Standard Price', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2010-09-17 16:26:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=6727
;

-- Sep 17, 2010 4:26:41 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='PriceLimit', Description='Lowest price for a product', EntityType='D', Help='The Price Limit indicates the lowest price for a product stated in the Price List Currency.', IsActive='Y', Name='Limit Price', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Limit Price',Updated=TO_DATE('2010-09-17 16:26:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=955
;

-- Sep 17, 2010 4:26:41 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=955
;

-- Sep 17, 2010 4:26:41 PM CDT
-- Cost Engine Report
UPDATE AD_Column SET AD_Element_ID=955, AD_Process_ID=NULL, AD_Reference_ID=37, AD_Reference_Value_ID=NULL, AD_Table_ID=478, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='PriceLimit', ColumnSQL=NULL, DefaultValue=NULL, Description='Lowest price for a product', EntityType='D', FieldLength=22, Help='The Price Limit indicates the lowest price for a product stated in the Price List Currency.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Limit Price', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2010-09-17 16:26:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=6728
;

-- Sep 17, 2010 4:26:41 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='CostStandard', Description='Standard Costs', EntityType='D', Help='Standard (plan) costs.', IsActive='Y', Name='Standard Cost', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Standard Cost',Updated=TO_DATE('2010-09-17 16:26:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=240
;

-- Sep 17, 2010 4:26:41 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=240
;

-- Sep 17, 2010 4:26:41 PM CDT
-- Cost Engine Report
UPDATE AD_Column SET AD_Element_ID=240, AD_Process_ID=NULL, AD_Reference_ID=37, AD_Reference_Value_ID=NULL, AD_Table_ID=478, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='CostStandard', ColumnSQL=NULL, DefaultValue=NULL, Description='Standard Costs', EntityType='D', FieldLength=22, Help='Standard (plan) costs.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Standard Cost', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2010-09-17 16:26:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=6729
;

-- Sep 17, 2010 4:26:42 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='PricePOAmt', Description='Valuation with PO Price', EntityType='D', Help=NULL, IsActive='Y', Name='PO Price Value', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='PO Price Value',Updated=TO_DATE('2010-09-17 16:26:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1730
;

-- Sep 17, 2010 4:26:42 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1730
;

-- Sep 17, 2010 4:26:42 PM CDT
-- Cost Engine Report
UPDATE AD_Column SET AD_Element_ID=1730, AD_Process_ID=NULL, AD_Reference_ID=37, AD_Reference_Value_ID=NULL, AD_Table_ID=478, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='PricePOAmt', ColumnSQL=NULL, DefaultValue=NULL, Description='Valuation with PO Price', EntityType='D', FieldLength=22, Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='PO Price Value', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2010-09-17 16:26:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=6730
;

-- Sep 17, 2010 4:26:42 PM CDT
-- Cost Engine Report
UPDATE AD_Field SET Name='PO Price Value', Description='Valuation with PO Price', Help=NULL WHERE AD_Column_ID=6730 AND IsCentrallyMaintained='Y'
;

-- Sep 17, 2010 4:26:42 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='PriceListAmt', Description='Valuation with List Price', EntityType='D', Help=NULL, IsActive='Y', Name='List price Value', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='List price Value',Updated=TO_DATE('2010-09-17 16:26:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1729
;

-- Sep 17, 2010 4:26:42 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1729
;

-- Sep 17, 2010 4:26:42 PM CDT
-- Cost Engine Report
UPDATE AD_Column SET AD_Element_ID=1729, AD_Process_ID=NULL, AD_Reference_ID=37, AD_Reference_Value_ID=NULL, AD_Table_ID=478, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='PriceListAmt', ColumnSQL=NULL, DefaultValue=NULL, Description='Valuation with List Price', EntityType='D', FieldLength=22, Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='List price Value', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2010-09-17 16:26:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=6731
;

-- Sep 17, 2010 4:26:42 PM CDT
-- Cost Engine Report
UPDATE AD_Field SET Name='List price Value', Description='Valuation with List Price', Help=NULL WHERE AD_Column_ID=6731 AND IsCentrallyMaintained='Y'
;

-- Sep 17, 2010 4:26:42 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='PriceStdAmt', Description='Valuation with standard price', EntityType='D', Help=NULL, IsActive='Y', Name='Std Price Value', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Std Price Value',Updated=TO_DATE('2010-09-17 16:26:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1731
;

-- Sep 17, 2010 4:26:42 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1731
;

-- Sep 17, 2010 4:26:43 PM CDT
-- Cost Engine Report
UPDATE AD_Column SET AD_Element_ID=1731, AD_Process_ID=NULL, AD_Reference_ID=37, AD_Reference_Value_ID=NULL, AD_Table_ID=478, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='PriceStdAmt', ColumnSQL=NULL, DefaultValue=NULL, Description='Valuation with standard price', EntityType='D', FieldLength=22, Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Std Price Value', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2010-09-17 16:26:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=6732
;

-- Sep 17, 2010 4:26:43 PM CDT
-- Cost Engine Report
UPDATE AD_Field SET Name='Std Price Value', Description='Valuation with standard price', Help=NULL WHERE AD_Column_ID=6732 AND IsCentrallyMaintained='Y'
;

-- Sep 17, 2010 4:26:43 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='PriceLimitAmt', Description='Value with limit price', EntityType='D', Help=NULL, IsActive='Y', Name='Limit price Value', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Limit price Value',Updated=TO_DATE('2010-09-17 16:26:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1728
;

-- Sep 17, 2010 4:26:43 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1728
;

-- Sep 17, 2010 4:26:43 PM CDT
-- Cost Engine Report
UPDATE AD_Column SET AD_Element_ID=1728, AD_Process_ID=NULL, AD_Reference_ID=37, AD_Reference_Value_ID=NULL, AD_Table_ID=478, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='PriceLimitAmt', ColumnSQL=NULL, DefaultValue=NULL, Description='Value with limit price', EntityType='D', FieldLength=22, Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Limit price Value', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2010-09-17 16:26:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=6733
;

-- Sep 17, 2010 4:26:43 PM CDT
-- Cost Engine Report
UPDATE AD_Field SET Name='Limit price Value', Description='Value with limit price', Help=NULL WHERE AD_Column_ID=6733 AND IsCentrallyMaintained='Y'
;

-- Sep 17, 2010 4:26:43 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='CostStandardAmt', Description='Value in Standard Costs', EntityType='D', Help=NULL, IsActive='Y', Name='Standard Cost Value', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Standard Cost Value',Updated=TO_DATE('2010-09-17 16:26:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1726
;

-- Sep 17, 2010 4:26:43 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1726
;

-- Sep 17, 2010 4:26:43 PM CDT
-- Cost Engine Report
UPDATE AD_Column SET AD_Element_ID=1726, AD_Process_ID=NULL, AD_Reference_ID=37, AD_Reference_Value_ID=NULL, AD_Table_ID=478, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='CostStandardAmt', ColumnSQL=NULL, DefaultValue=NULL, Description='Value in Standard Costs', EntityType='D', FieldLength=22, Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Standard Cost Value', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2010-09-17 16:26:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=6734
;

-- Sep 17, 2010 4:26:43 PM CDT
-- Cost Engine Report
UPDATE AD_Field SET Name='Standard Cost Value', Description='Value in Standard Costs', Help=NULL WHERE AD_Column_ID=6734 AND IsCentrallyMaintained='Y'
;

-- Sep 17, 2010 4:26:43 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='AD_PInstance_ID', Description='Instance of the process', EntityType='D', Help=NULL, IsActive='Y', Name='Process Instance', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Process Instance',Updated=TO_DATE('2010-09-17 16:26:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=114
;

-- Sep 17, 2010 4:26:43 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=114
;

-- Sep 17, 2010 4:26:44 PM CDT
-- Cost Engine Report
UPDATE AD_Column SET AD_Element_ID=114, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=478, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='AD_PInstance_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Instance of the process', EntityType='D', FieldLength=22, Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Process Instance', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2010-09-17 16:26:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=7047
;

-- Sep 17, 2010 4:26:44 PM CDT
-- Cost Engine Report
UPDATE AD_Field SET Name='Process Instance', Description='Instance of the process', Help=NULL WHERE AD_Column_ID=7047 AND IsCentrallyMaintained='Y'
;

-- Sep 17, 2010 4:26:44 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='M_AttributeSetInstance_ID', Description='Product Attribute Set Instance', EntityType='D', Help='The values of the actual Product Attribute Instances.  The product level attributes are defined on Product level.', IsActive='Y', Name='Attribute Set Instance', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Attribute Set Instance',Updated=TO_DATE('2010-09-17 16:26:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=2019
;

-- Sep 17, 2010 4:26:44 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2019
;

-- Sep 17, 2010 4:26:44 PM CDT
-- Cost Engine Report
UPDATE AD_Reference SET Description='Product Attribute', EntityType='D', Help=NULL, IsActive='Y', Name='Product Attribute', ValidationType='D',Updated=TO_DATE('2010-09-17 16:26:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=35
;

-- Sep 17, 2010 4:26:44 PM CDT
-- Cost Engine Report
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=35
;

-- Sep 17, 2010 4:26:44 PM CDT
-- Cost Engine Report
UPDATE AD_Column SET AD_Element_ID=2019, AD_Process_ID=NULL, AD_Reference_ID=35, AD_Reference_Value_ID=NULL, AD_Table_ID=478, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='M_AttributeSetInstance_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Product Attribute Set Instance', EntityType='D', FieldLength=10, Help='The values of the actual Product Attribute Instances.  The product level attributes are defined on Product level.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Attribute Set Instance', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_DATE('2010-09-17 16:26:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=14977
;

-- Sep 17, 2010 4:26:44 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='Cost', Description='Cost information', EntityType='D', Help=NULL, IsActive='Y', Name='Cost', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Cost',Updated=TO_DATE('2010-09-17 16:26:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=2319
;

-- Sep 17, 2010 4:26:44 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2319
;

-- Sep 17, 2010 4:26:44 PM CDT
-- Cost Engine Report
UPDATE AD_Reference SET Description='Float Number', EntityType='D', Help=NULL, IsActive='Y', Name='Number', ValidationType='D',Updated=TO_DATE('2010-09-17 16:26:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=22
;

-- Sep 17, 2010 4:26:44 PM CDT
-- Cost Engine Report
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=22
;

-- Sep 17, 2010 4:26:45 PM CDT
-- Cost Engine Report
UPDATE AD_Column SET AD_Element_ID=2319, AD_Process_ID=NULL, AD_Reference_ID=22, AD_Reference_Value_ID=NULL, AD_Table_ID=478, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Cost', ColumnSQL=NULL, DefaultValue=NULL, Description='Cost information', EntityType='D', FieldLength=22, Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Cost', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_DATE('2010-09-17 16:26:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=14978
;

-- Sep 17, 2010 4:26:45 PM CDT
-- Cost Engine Report
UPDATE AD_Field SET Name='Cost', Description='Cost information', Help=NULL WHERE AD_Column_ID=14978 AND IsCentrallyMaintained='Y'
;

-- Sep 17, 2010 4:26:45 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='CostAmt', Description='Value with Cost', EntityType='D', Help=NULL, IsActive='Y', Name='Cost Value', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Cost Value',Updated=TO_DATE('2010-09-17 16:26:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=2962
;

-- Sep 17, 2010 4:26:45 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2962
;

-- Sep 17, 2010 4:26:45 PM CDT
-- Cost Engine Report
UPDATE AD_Reference SET Description='Number with 4 decimals', EntityType='D', Help=NULL, IsActive='Y', Name='Amount', ValidationType='D',Updated=TO_DATE('2010-09-17 16:26:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=12
;

-- Sep 17, 2010 4:26:45 PM CDT
-- Cost Engine Report
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=12
;

-- Sep 17, 2010 4:26:45 PM CDT
-- Cost Engine Report
UPDATE AD_Column SET AD_Element_ID=2962, AD_Process_ID=NULL, AD_Reference_ID=12, AD_Reference_Value_ID=NULL, AD_Table_ID=478, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='CostAmt', ColumnSQL=NULL, DefaultValue=NULL, Description='Value with Cost', EntityType='D', FieldLength=22, Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Cost Value', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_DATE('2010-09-17 16:26:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=14979
;

-- Sep 17, 2010 4:26:45 PM CDT
-- Cost Engine Report
UPDATE AD_Field SET Name='Cost Value', Description='Value with Cost', Help=NULL WHERE AD_Column_ID=14979 AND IsCentrallyMaintained='Y'
;

-- Sep 17, 2010 4:26:45 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='M_CostElement_ID', Description='Product Cost Element', EntityType='D', Help=NULL, IsActive='Y', Name='Cost Element', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Cost Element',Updated=TO_DATE('2010-09-17 16:26:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=2700
;

-- Sep 17, 2010 4:26:45 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2700
;

-- Sep 17, 2010 4:26:45 PM CDT
-- Cost Engine Report
UPDATE AD_Column SET AD_Element_ID=2700, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=478, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='M_CostElement_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Product Cost Element', EntityType='D', FieldLength=10, Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Cost Element', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_DATE('2010-09-17 16:26:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=14980
;

-- Sep 17, 2010 4:26:45 PM CDT
-- Cost Engine Report
UPDATE AD_Field SET Name='Cost Element', Description='Product Cost Element', Help=NULL WHERE AD_Column_ID=14980 AND IsCentrallyMaintained='Y'
;

-- Sep 17, 2010 4:26:45 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='M_CostType_ID', Description='Type of Cost (e.g. Current, Plan, Future)', EntityType='D', Help='You can define multiple cost types. A cost type selected in an Accounting Schema is used for accounting.', IsActive='Y', Name='Cost Type', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Cost Type',Updated=TO_DATE('2010-09-17 16:26:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=2071
;

-- Sep 17, 2010 4:26:45 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2071
;

-- Sep 17, 2010 4:26:46 PM CDT
-- Cost Engine Report
UPDATE AD_Column SET AD_Element_ID=2071, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=478, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='M_CostType_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Type of Cost (e.g. Current, Plan, Future)', EntityType='D', FieldLength=10, Help='You can define multiple cost types. A cost type selected in an Accounting Schema is used for accounting.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Cost Type', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_DATE('2010-09-17 16:26:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=59790
;

-- Sep 17, 2010 4:26:46 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='M_Product_Category_ID', Description='Category of a Product', EntityType='D', Help='Identifies the category which this product belongs to.  Product categories are used for pricing and selection.', IsActive='Y', Name='Product Category', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Product Category',Updated=TO_DATE('2010-09-17 16:26:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=453
;

-- Sep 17, 2010 4:26:46 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=453
;

-- Sep 17, 2010 4:26:46 PM CDT
-- Cost Engine Report
UPDATE AD_Column SET AD_Element_ID=453, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=478, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='M_Product_Category_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Category of a Product', EntityType='D', FieldLength=10, Help='Identifies the category which this product belongs to.  Product categories are used for pricing and selection.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Product Category', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_DATE('2010-09-17 16:26:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=59791
;

-- Sep 17, 2010 4:26:46 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='Classification', Description='Classification for grouping', EntityType='D', Help='The Classification can be used to optionally group products.', IsActive='Y', Name='Classification', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Classification',Updated=TO_DATE('2010-09-17 16:26:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=852
;

-- Sep 17, 2010 4:26:46 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=852
;

-- Sep 17, 2010 4:26:46 PM CDT
-- Cost Engine Report
UPDATE AD_Reference SET Description='Character String', EntityType='D', Help=NULL, IsActive='Y', Name='String', ValidationType='D',Updated=TO_DATE('2010-09-17 16:26:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=10
;

-- Sep 17, 2010 4:26:46 PM CDT
-- Cost Engine Report
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=10
;

-- Sep 17, 2010 4:26:46 PM CDT
-- Cost Engine Report
UPDATE AD_Column SET AD_Element_ID=852, AD_Process_ID=NULL, AD_Reference_ID=10, AD_Reference_Value_ID=NULL, AD_Table_ID=478, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Classification', ColumnSQL=NULL, DefaultValue=NULL, Description='Classification for grouping', EntityType='D', FieldLength=12, Help='The Classification can be used to optionally group products.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Classification', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_DATE('2010-09-17 16:26:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=59792
;

-- Sep 17, 2010 4:26:47 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='Group1', Description=NULL, EntityType='D', Help=NULL, IsActive='Y', Name='Group1', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Group1',Updated=TO_DATE('2010-09-17 16:26:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=52018
;

-- Sep 17, 2010 4:26:47 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=52018
;

-- Sep 17, 2010 4:26:47 PM CDT
-- Cost Engine Report
UPDATE AD_Column SET AD_Element_ID=52018, AD_Process_ID=NULL, AD_Reference_ID=10, AD_Reference_Value_ID=NULL, AD_Table_ID=478, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Group1', ColumnSQL=NULL, DefaultValue=NULL, Description=NULL, EntityType='D', FieldLength=255, Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Group1', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_DATE('2010-09-17 16:26:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=59793
;

-- Sep 17, 2010 4:26:47 PM CDT
-- Cost Engine Report
UPDATE AD_Field SET Name='Group1', Description=NULL, Help=NULL WHERE AD_Column_ID=59793 AND IsCentrallyMaintained='Y'
;

-- Sep 17, 2010 4:26:47 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='Group2', Description=NULL, EntityType='D', Help=NULL, IsActive='Y', Name='Group2', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Group2',Updated=TO_DATE('2010-09-17 16:26:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=52019
;

-- Sep 17, 2010 4:26:47 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=52019
;

-- Sep 17, 2010 4:26:47 PM CDT
-- Cost Engine Report
UPDATE AD_Column SET AD_Element_ID=52019, AD_Process_ID=NULL, AD_Reference_ID=10, AD_Reference_Value_ID=NULL, AD_Table_ID=478, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Group2', ColumnSQL=NULL, DefaultValue=NULL, Description=NULL, EntityType='D', FieldLength=255, Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Group2', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_DATE('2010-09-17 16:26:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=59794
;

-- Sep 17, 2010 4:26:47 PM CDT
-- Cost Engine Report
UPDATE AD_Field SET Name='Group2', Description=NULL, Help=NULL WHERE AD_Column_ID=59794 AND IsCentrallyMaintained='Y'
;

-- Sep 17, 2010 4:26:47 PM CDT
-- Cost Engine Report
UPDATE AD_PrintPaper SET Code='na-letter', Description='1/2 inch margin on all sides', DimensionUnits=NULL, IsActive='Y', IsDefault='Y', IsLandscape='Y', MarginBottom=36, MarginLeft=36, MarginRight=36, MarginTop=36, Name='Letter Landscape', SizeX=NULL, SizeY=NULL,Updated=TO_DATE('2010-09-17 16:26:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_PrintPaper_ID=100
;

-- Sep 17, 2010 4:26:59 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormat (AD_Client_ID,AD_Org_ID,AD_PrintColor_ID,AD_PrintFont_ID,AD_PrintFormat_ID,AD_PrintPaper_ID,AD_ReportView_ID,AD_Table_ID,CreateCopy,Created,CreatedBy,FooterMargin,HeaderMargin,IsActive,IsDefault,IsForm,IsStandardHeaderFooter,IsTableBased,Name,Updated,UpdatedBy) VALUES (0,0,100,163,50052,100,124,478,NULL,TO_DATE('2010-09-17 16:26:47','YYYY-MM-DD HH24:MI:SS'),100,0,0,'Y','N','N','Y','Y','Inventory Valuation Effective Date',TO_DATE('2010-09-17 16:26:47','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 17, 2010 4:27:02 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59792,0,51551,50052,0,0,TO_DATE('2010-09-17 16:26:59','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Classification','C','F','Classification',0,0,'N',0,TO_DATE('2010-09-17 16:26:59','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:27:02 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51551 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:27:06 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,6717,0,51552,50052,0,0,TO_DATE('2010-09-17 16:27:02','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Client','C','F','Client',0,0,'N',0,TO_DATE('2010-09-17 16:27:02','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:27:06 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51552 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:27:09 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,6723,0,51553,50052,0,0,TO_DATE('2010-09-17 16:27:06','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Currency','C','F','Currency',0,0,'N',0,TO_DATE('2010-09-17 16:27:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:27:09 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51553 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:27:12 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59793,0,51554,50052,0,0,TO_DATE('2010-09-17 16:27:09','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Group1','C','F','Group1',0,0,'N',0,TO_DATE('2010-09-17 16:27:09','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:27:12 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51554 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:27:15 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59794,0,51555,50052,0,0,TO_DATE('2010-09-17 16:27:12','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Group2','C','F','Group2',0,0,'N',0,TO_DATE('2010-09-17 16:27:12','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:27:15 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51555 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:27:18 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,6728,0,51556,50052,0,0,TO_DATE('2010-09-17 16:27:15','YYYY-MM-DD HH24:MI:SS'),100,'T','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Limit Price','C','F','Limit Price',0,0,'N',0,TO_DATE('2010-09-17 16:27:15','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:27:18 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51556 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:27:22 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,6733,0,51557,50052,0,0,TO_DATE('2010-09-17 16:27:18','YYYY-MM-DD HH24:MI:SS'),100,'T','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Limit price Value','C','F','Limit price Value',0,0,'N',0,TO_DATE('2010-09-17 16:27:18','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:27:22 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51557 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:27:25 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,6726,0,51558,50052,0,0,TO_DATE('2010-09-17 16:27:22','YYYY-MM-DD HH24:MI:SS'),100,'T','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'List Price','C','F','List Price',0,0,'N',0,TO_DATE('2010-09-17 16:27:22','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:27:25 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51558 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:27:28 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,6731,0,51559,50052,0,0,TO_DATE('2010-09-17 16:27:25','YYYY-MM-DD HH24:MI:SS'),100,'T','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'List price Value','C','F','List price Value',0,0,'N',0,TO_DATE('2010-09-17 16:27:25','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:27:28 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51559 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:27:31 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,6718,0,51560,50052,0,0,TO_DATE('2010-09-17 16:27:28','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Organization','C','F','Organization',0,0,'N',0,TO_DATE('2010-09-17 16:27:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:27:31 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51560 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:27:35 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,6725,0,51561,50052,0,0,TO_DATE('2010-09-17 16:27:31','YYYY-MM-DD HH24:MI:SS'),100,'T','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'PO Price','C','F','PO Price',0,0,'N',0,TO_DATE('2010-09-17 16:27:31','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:27:35 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51561 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:27:38 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,6730,0,51562,50052,0,0,TO_DATE('2010-09-17 16:27:35','YYYY-MM-DD HH24:MI:SS'),100,'T','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'PO Price Value','C','F','PO Price Value',0,0,'N',0,TO_DATE('2010-09-17 16:27:35','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:27:38 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51562 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:27:43 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,6721,0,51563,50052,0,0,TO_DATE('2010-09-17 16:27:38','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Price List Version','C','F','Price List Version',0,0,'N',0,TO_DATE('2010-09-17 16:27:38','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:27:43 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51563 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:27:46 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,7047,0,51564,50052,0,0,TO_DATE('2010-09-17 16:27:43','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Process Instance','C','F','Process Instance',0,0,'N',0,TO_DATE('2010-09-17 16:27:43','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:27:46 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51564 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:27:50 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,6729,0,51565,50052,0,0,TO_DATE('2010-09-17 16:27:46','YYYY-MM-DD HH24:MI:SS'),100,'T','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Standard Cost','C','F','Standard Cost',0,0,'N',0,TO_DATE('2010-09-17 16:27:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:27:50 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51565 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:27:53 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,6734,0,51566,50052,0,0,TO_DATE('2010-09-17 16:27:50','YYYY-MM-DD HH24:MI:SS'),100,'T','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Standard Cost Value','C','F','Standard Cost Value',0,0,'N',0,TO_DATE('2010-09-17 16:27:50','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:27:53 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51566 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:27:56 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,6727,0,51567,50052,0,0,TO_DATE('2010-09-17 16:27:53','YYYY-MM-DD HH24:MI:SS'),100,'T','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Standard Price','C','F','Std Price',0,0,'N',0,TO_DATE('2010-09-17 16:27:53','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:27:56 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51567 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:28:00 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,6732,0,51568,50052,0,0,TO_DATE('2010-09-17 16:27:56','YYYY-MM-DD HH24:MI:SS'),100,'T','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Std Price Value','C','F','Std Price Value',0,0,'N',0,TO_DATE('2010-09-17 16:27:56','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:28:00 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51568 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:28:03 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,6722,0,51569,50052,0,0,TO_DATE('2010-09-17 16:28:00','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Valuation Date','C','F','Valuation Date',0,0,'N',0,TO_DATE('2010-09-17 16:28:00','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:28:03 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51569 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:28:06 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,14977,0,51570,50052,0,0,TO_DATE('2010-09-17 16:28:03','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Attribute Set Instance','C','F','Attribute Set Instance',0,0,'N',0,TO_DATE('2010-09-17 16:28:03','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:28:06 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51570 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:28:09 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,6719,0,51571,50052,0,0,TO_DATE('2010-09-17 16:28:06','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','Y','N','N','N','N','N','Y','Y','Y','Y','N','N','N','N','N','X',1,0,0,'Warehouse','C','F','Warehouse',0,10,'N',10,TO_DATE('2010-09-17 16:28:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:28:09 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51571 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:28:12 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59790,0,51572,50052,0,0,TO_DATE('2010-09-17 16:28:09','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','Y','Y','Y','N','N','N','N','Y','N','Y','Y','N','N','N','N','N','X',1,0,50,'Cost Type','C','F','Cost Type',0,20,'N',20,TO_DATE('2010-09-17 16:28:09','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:28:12 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51572 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:28:17 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,14980,0,51573,50052,0,0,TO_DATE('2010-09-17 16:28:12','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','Y','N','Y','N','N','N','N','Y','N','Y','Y','N','N','N','N','N','X',1,0,60,'Cost Element','C','F','Cost Element',0,30,'N',40,TO_DATE('2010-09-17 16:28:12','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:28:17 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51573 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:28:21 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,6720,0,51574,50052,0,0,TO_DATE('2010-09-17 16:28:17','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','Y','Y','Y','N','N','N','N','Y','N','Y','Y','N','N','N','N','N','X',1,0,160,'Product','C','F','Product',0,40,'N',30,TO_DATE('2010-09-17 16:28:17','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:28:21 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51574 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:28:24 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59791,0,51575,50052,0,0,TO_DATE('2010-09-17 16:28:21','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Product Category','C','F','Product Category',0,50,'N',0,TO_DATE('2010-09-17 16:28:21','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:28:24 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51575 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:28:28 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,6724,0,51576,50052,0,0,TO_DATE('2010-09-17 16:28:24','YYYY-MM-DD HH24:MI:SS'),100,'T','N','Y','N','N','N','Y','N','Y','N','N','N','N','N','N','Y','Y','N','N','Y','N','N','X',1,0,60,'On Hand Quantity','C','F','On Hand Qty',0,60,'N',0,TO_DATE('2010-09-17 16:28:24','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:28:28 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51576 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:28:32 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,14978,0,51577,50052,0,0,TO_DATE('2010-09-17 16:28:28','YYYY-MM-DD HH24:MI:SS'),100,'T','N','Y','N','N','N','Y','N','Y','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,60,'Cost','C','F','Cost',0,70,'N',0,TO_DATE('2010-09-17 16:28:28','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:28:32 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51577 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:28:34 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,14979,0,51578,50052,0,0,TO_DATE('2010-09-17 16:28:32','YYYY-MM-DD HH24:MI:SS'),100,'T','N','Y','N','N','N','Y','N','Y','N','N','N','N','N','N','Y','Y','N','N','Y','N','N','X',1,0,60,'Cost Value','C','F','Cost Value',0,80,'N',0,TO_DATE('2010-09-17 16:28:32','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:28:34 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51578 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:28:37 PM CDT
-- Cost Engine Report
INSERT INTO AD_Process (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_ReportView_ID,AccessLevel,Classname,Created,CreatedBy,Description,EntityType,Help,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,Name,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,Value,WorkflowValue) VALUES (0,0,53222,124,'3','org.eevolution.process.ValuationEffectiveDate',TO_DATE('2010-09-17 16:28:34','YYYY-MM-DD HH24:MI:SS'),100,'Valuation Effective Date','D','This report shows the amount in stock for each product and its values to an effective date, you can use any defined cost , as such Standard, Average, FIFO and LIFO','Y','N','N','Y','Valuation Effective Date','Y',0,0,TO_DATE('2010-09-17 16:28:34','YYYY-MM-DD HH24:MI:SS'),100,'Valuation Effective Date',NULL)
;

-- Sep 17, 2010 4:28:37 PM CDT
-- Cost Engine Report
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53222 AND NOT EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_ID=t.AD_Process_ID)
;

-- Sep 17, 2010 4:28:40 PM CDT
-- Cost Engine Report
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,1727,0,53222,53434,15,'DateValue',TO_DATE('2010-09-17 16:28:37','YYYY-MM-DD HH24:MI:SS'),100,'@Date@','Date of valuation','D',7,'Y','Y','Y','N','Valuation Date',10,TO_DATE('2010-09-17 16:28:37','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 17, 2010 4:28:40 PM CDT
-- Cost Engine Report
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53434 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Sep 17, 2010 4:28:43 PM CDT
-- Cost Engine Report
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,459,0,53222,53435,19,'M_Warehouse_ID',TO_DATE('2010-09-17 16:28:40','YYYY-MM-DD HH24:MI:SS'),100,'Storage Warehouse and Service Point','D',22,'The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','Y','Y','N','N','Warehouse',20,TO_DATE('2010-09-17 16:28:40','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 17, 2010 4:28:43 PM CDT
-- Cost Engine Report
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53435 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Sep 17, 2010 4:28:50 PM CDT
-- Cost Engine Report
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,454,0,53222,53436,30,'M_Product_ID',TO_DATE('2010-09-17 16:28:43','YYYY-MM-DD HH24:MI:SS'),100,'Product, Service, Item','D',22,'Identifies an item which is either purchased or sold in this organization.','Y','Y','N','N','Product',30,TO_DATE('2010-09-17 16:28:43','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 17, 2010 4:28:50 PM CDT
-- Cost Engine Report
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53436 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Sep 17, 2010 4:28:50 PM CDT
-- Cost Engine Report
UPDATE AD_Reference SET Description=NULL, EntityType='D', Help=NULL, IsActive='Y', Name='M_Product Category ', ValidationType='T',Updated=TO_DATE('2010-09-17 16:28:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=163
;

-- Sep 17, 2010 4:28:50 PM CDT
-- Cost Engine Report
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=163
;

-- Sep 17, 2010 4:28:50 PM CDT
-- Cost Engine Report
UPDATE AD_Ref_Table SET AD_Table_ID = 209, AD_Display = 1776, AD_Key = 2020, isValueDisplayed = 'Y', OrderByClause = 'M_Product_Category.Name', EntityType ='D', WhereClause = '' WHERE AD_Reference_ID = 163
;

-- Sep 17, 2010 4:28:55 PM CDT
-- Cost Engine Report
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Reference_Value_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,453,0,53222,53437,19,163,'M_Product_Category_ID',TO_DATE('2010-09-17 16:28:50','YYYY-MM-DD HH24:MI:SS'),100,'Category of a Product','D',22,'Identifies the category which this product belongs to.  Product categories are used for pricing and selection.','Y','Y','N','N','Product Category',40,TO_DATE('2010-09-17 16:28:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 17, 2010 4:28:55 PM CDT
-- Cost Engine Report
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53437 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Sep 17, 2010 4:28:59 PM CDT
-- Cost Engine Report
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2071,0,53222,53438,19,'M_CostType_ID',TO_DATE('2010-09-17 16:28:55','YYYY-MM-DD HH24:MI:SS'),100,'Type of Cost (e.g. Current, Plan, Future)','D',22,'You can define multiple cost types. A cost type selected in an Accounting Schema is used for accounting.','Y','Y','N','N','Cost Type',50,TO_DATE('2010-09-17 16:28:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 17, 2010 4:28:59 PM CDT
-- Cost Engine Report
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53438 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Sep 17, 2010 4:29:03 PM CDT
-- Cost Engine Report
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2700,0,53222,53439,19,'M_CostElement_ID',TO_DATE('2010-09-17 16:28:59','YYYY-MM-DD HH24:MI:SS'),100,'Product Cost Element','D',10,'Y','Y','N','N','Cost Element',60,TO_DATE('2010-09-17 16:28:59','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 17, 2010 4:29:03 PM CDT
-- Cost Engine Report
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53439 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Sep 17, 2010 4:29:06 PM CDT
-- Cost Engine Report
INSERT INTO AD_Process (AD_Client_ID,AD_Org_ID,AD_Process_ID,AccessLevel,Classname,Created,CreatedBy,Description,EntityType,Help,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,Name,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,Value,WorkflowValue) VALUES (0,0,53223,'3','org.eevolution.process.GenerateCostDetail',TO_DATE('2010-09-17 16:29:03','YYYY-MM-DD HH24:MI:SS'),100,'Generate Cost Transaction','D','The Generate Cost Transaction process allows the detailed cost calculation and cost generation  beginning from a date. If you have not yet set COGs Adjustment, you should execute this process before a period''s end in order to fix the cost layers.','Y','N','N','N','Generate Cost Transaction','Y',0,0,TO_DATE('2010-09-17 16:29:03','YYYY-MM-DD HH24:MI:SS'),100,'M_CostDetail Generate Cost Transaction',NULL)
;

-- Sep 17, 2010 4:29:06 PM CDT
-- Cost Engine Report
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53223 AND NOT EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_ID=t.AD_Process_ID)
;

-- Sep 17, 2010 4:29:09 PM CDT
-- Cost Engine Report
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,181,0,53223,53440,19,'C_AcctSchema_ID',TO_DATE('2010-09-17 16:29:06','YYYY-MM-DD HH24:MI:SS'),100,'Rules for accounting','D',22,'An Accounting Schema defines the rules used in accounting such as costing method, currency and calendar','Y','Y','N','N','Accounting Schema',15,TO_DATE('2010-09-17 16:29:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 17, 2010 4:29:09 PM CDT
-- Cost Engine Report
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53440 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Sep 17, 2010 4:29:12 PM CDT
-- Cost Engine Report
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2071,0,53223,53441,19,'M_CostType_ID',TO_DATE('2010-09-17 16:29:09','YYYY-MM-DD HH24:MI:SS'),100,'Type of Cost (e.g. Current, Plan, Future)','D',22,'You can define multiple cost types. A cost type selected in an Accounting Schema is used for accounting.','Y','Y','N','N','Cost Type',20,TO_DATE('2010-09-17 16:29:09','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 17, 2010 4:29:12 PM CDT
-- Cost Engine Report
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53441 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Sep 17, 2010 4:29:15 PM CDT
-- Cost Engine Report
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2700,0,53223,53442,19,'M_CostElement_ID',TO_DATE('2010-09-17 16:29:12','YYYY-MM-DD HH24:MI:SS'),100,'Product Cost Element','D',10,'Y','Y','N','N','Cost Element',30,TO_DATE('2010-09-17 16:29:12','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 17, 2010 4:29:15 PM CDT
-- Cost Engine Report
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53442 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Sep 17, 2010 4:29:20 PM CDT
-- Cost Engine Report
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,454,0,53223,53443,30,'M_Product_ID',TO_DATE('2010-09-17 16:29:15','YYYY-MM-DD HH24:MI:SS'),100,'Product, Service, Item','D',22,'Identifies an item which is either purchased or sold in this organization.','Y','Y','N','N','Product',40,TO_DATE('2010-09-17 16:29:15','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 17, 2010 4:29:20 PM CDT
-- Cost Engine Report
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53443 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Sep 17, 2010 4:29:23 PM CDT
-- Cost Engine Report
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,263,0,53223,53444,15,'DateAcct',TO_DATE('2010-09-17 16:29:20','YYYY-MM-DD HH24:MI:SS'),100,'Accounting Date','D',7,'The Accounting Date indicates the date to be used on the General Ledger account entries generated from this document. It is also used for any currency conversion.','Y','Y','Y','N','Account Date',50,TO_DATE('2010-09-17 16:29:20','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 17, 2010 4:29:23 PM CDT
-- Cost Engine Report
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53444 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Sep 17, 2010 4:29:27 PM CDT
-- Cost Engine Report
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,EntityType,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53288,'3',TO_DATE('2010-09-17 16:29:23','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N','N','N','N','N','RV_M_Transaction_Costing','L','RV_M_Transaction_Costing',TO_DATE('2010-09-17 16:29:23','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 17, 2010 4:29:27 PM CDT
-- Cost Engine Report
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53288 AND NOT EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Table_ID=t.AD_Table_ID)
;

-- Sep 17, 2010 4:29:30 PM CDT
-- Cost Engine Report
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53401,TO_DATE('2010-09-17 16:29:27','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'Table RV_M_Transaction_Costing',1,'Y','N','Y','Y','RV_M_Transaction_Costing','N',1000000,TO_DATE('2010-09-17 16:29:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 17, 2010 4:29:33 PM CDT
-- Cost Engine Report
INSERT INTO AD_ReportView (AD_Client_ID,AD_Org_ID,AD_ReportView_ID,AD_Table_ID,Created,CreatedBy,EntityType,IsActive,Name,OrderByClause,Updated,UpdatedBy) VALUES (0,0,53033,53288,TO_DATE('2010-09-17 16:29:23','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','RV_M_Transaction_Costing','DateAcct',TO_DATE('2010-09-17 16:29:23','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 17, 2010 4:29:33 PM CDT
-- Cost Engine Report
UPDATE AD_Table SET AccessLevel='3', Description=NULL, EntityType='D', Help=NULL, ImportTable='N', IsActive='Y', IsChangeLog='N', IsDeleteable='N', IsHighVolume='N', IsSecurityEnabled='N', IsView='Y', Name='Transaction Costing', ReplicationType='L', TableName='RV_M_Transaction_Costing',Updated=TO_DATE('2010-09-17 16:29:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Table_ID=53288
;

-- Sep 17, 2010 4:29:33 PM CDT
-- Cost Engine Report
UPDATE AD_Table_Trl SET IsTranslated='N' WHERE AD_Table_ID=53288
;

-- Sep 17, 2010 4:29:33 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='M_Transaction_ID', Description=NULL, EntityType='D', Help=NULL, IsActive='Y', Name='Inventory Transaction', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Inventory Transaction',Updated=TO_DATE('2010-09-17 16:29:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1035
;

-- Sep 17, 2010 4:29:33 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1035
;

-- Sep 17, 2010 4:29:37 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59797,1035,0,19,53288,'M_Transaction_ID',TO_DATE('2010-09-17 16:29:33','YYYY-MM-DD HH24:MI:SS'),100,'D',10,'Y','N','N','N','N','N','N','N','Y','N','N','Inventory Transaction',TO_DATE('2010-09-17 16:29:33','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:29:37 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59797 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:29:40 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59798,102,0,19,53288,'AD_Client_ID',TO_DATE('2010-09-17 16:29:37','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.','D',10,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','N','N','N','Y','N','N','Client',TO_DATE('2010-09-17 16:29:37','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:29:40 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59798 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:29:43 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59799,113,0,19,53288,'AD_Org_ID',TO_DATE('2010-09-17 16:29:41','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client','D',10,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','N','N','N','Y','N','N','Organization',TO_DATE('2010-09-17 16:29:41','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:29:43 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59799 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:29:43 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='MovementType', Description='Method of moving the inventory', EntityType='D', Help='The Movement Type indicates the type of movement (in, out, to production, etc)', IsActive='Y', Name='Movement Type', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Movement Type',Updated=TO_DATE('2010-09-17 16:29:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1039
;

-- Sep 17, 2010 4:29:43 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1039
;

-- Sep 17, 2010 4:29:43 PM CDT
-- Cost Engine Report
UPDATE AD_Reference SET Description='Reference List', EntityType='D', Help=NULL, IsActive='Y', Name='List', ValidationType='D',Updated=TO_DATE('2010-09-17 16:29:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=17
;

-- Sep 17, 2010 4:29:43 PM CDT
-- Cost Engine Report
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=17
;

-- Sep 17, 2010 4:29:43 PM CDT
-- Cost Engine Report
UPDATE AD_Reference SET Description=NULL, EntityType='D', Help=NULL, IsActive='Y', Name='M_Transaction Movement Type', ValidationType='L',Updated=TO_DATE('2010-09-17 16:29:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=189
;

-- Sep 17, 2010 4:29:43 PM CDT
-- Cost Engine Report
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=189
;

-- Sep 17, 2010 4:29:43 PM CDT
-- Cost Engine Report
UPDATE AD_Ref_List SET AD_Reference_ID=189, Description=NULL, EntityType='D', IsActive='Y', Name='Customer Returns', Value='C+',Updated=TO_DATE('2010-09-17 16:29:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=313
;

-- Sep 17, 2010 4:29:43 PM CDT
-- Cost Engine Report
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=313
;

-- Sep 17, 2010 4:29:44 PM CDT
-- Cost Engine Report
UPDATE AD_Ref_List SET AD_Reference_ID=189, Description=NULL, EntityType='D', IsActive='Y', Name='Customer Shipment', Value='C-',Updated=TO_DATE('2010-09-17 16:29:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=312
;

-- Sep 17, 2010 4:29:44 PM CDT
-- Cost Engine Report
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=312
;

-- Sep 17, 2010 4:29:44 PM CDT
-- Cost Engine Report
UPDATE AD_Ref_List SET AD_Reference_ID=189, Description=NULL, EntityType='D', IsActive='Y', Name='Inventory In', Value='I+',Updated=TO_DATE('2010-09-17 16:29:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=317
;

-- Sep 17, 2010 4:29:44 PM CDT
-- Cost Engine Report
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=317
;

-- Sep 17, 2010 4:29:44 PM CDT
-- Cost Engine Report
UPDATE AD_Ref_List SET AD_Reference_ID=189, Description=NULL, EntityType='D', IsActive='Y', Name='Inventory Out', Value='I-',Updated=TO_DATE('2010-09-17 16:29:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=316
;

-- Sep 17, 2010 4:29:44 PM CDT
-- Cost Engine Report
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=316
;

-- Sep 17, 2010 4:29:44 PM CDT
-- Cost Engine Report
UPDATE AD_Ref_List SET AD_Reference_ID=189, Description=NULL, EntityType='D', IsActive='Y', Name='Movement To', Value='M+',Updated=TO_DATE('2010-09-17 16:29:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=350
;

-- Sep 17, 2010 4:29:44 PM CDT
-- Cost Engine Report
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=350
;

-- Sep 17, 2010 4:29:44 PM CDT
-- Cost Engine Report
UPDATE AD_Ref_List SET AD_Reference_ID=189, Description=NULL, EntityType='D', IsActive='Y', Name='Movement From', Value='M-',Updated=TO_DATE('2010-09-17 16:29:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=349
;

-- Sep 17, 2010 4:29:44 PM CDT
-- Cost Engine Report
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=349
;

-- Sep 17, 2010 4:29:44 PM CDT
-- Cost Engine Report
UPDATE AD_Ref_List SET AD_Reference_ID=189, Description=NULL, EntityType='D', IsActive='Y', Name='Production +', Value='P+',Updated=TO_DATE('2010-09-17 16:29:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=391
;

-- Sep 17, 2010 4:29:44 PM CDT
-- Cost Engine Report
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=391
;

-- Sep 17, 2010 4:29:44 PM CDT
-- Cost Engine Report
UPDATE AD_Ref_List SET AD_Reference_ID=189, Description=NULL, EntityType='D', IsActive='Y', Name='Production -', Value='P-',Updated=TO_DATE('2010-09-17 16:29:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=392
;

-- Sep 17, 2010 4:29:44 PM CDT
-- Cost Engine Report
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=392
;

-- Sep 17, 2010 4:29:44 PM CDT
-- Cost Engine Report
UPDATE AD_Ref_List SET AD_Reference_ID=189, Description=NULL, EntityType='D', IsActive='Y', Name='Vendor Receipts', Value='V+',Updated=TO_DATE('2010-09-17 16:29:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=314
;

-- Sep 17, 2010 4:29:44 PM CDT
-- Cost Engine Report
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=314
;

-- Sep 17, 2010 4:29:44 PM CDT
-- Cost Engine Report
UPDATE AD_Ref_List SET AD_Reference_ID=189, Description=NULL, EntityType='D', IsActive='Y', Name='Vendor Returns', Value='V-',Updated=TO_DATE('2010-09-17 16:29:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=315
;

-- Sep 17, 2010 4:29:44 PM CDT
-- Cost Engine Report
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=315
;

-- Sep 17, 2010 4:29:44 PM CDT
-- Cost Engine Report
UPDATE AD_Ref_List SET AD_Reference_ID=189, Description=NULL, EntityType='D', IsActive='Y', Name='Work Order +', Value='W+',Updated=TO_DATE('2010-09-17 16:29:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=582
;

-- Sep 17, 2010 4:29:44 PM CDT
-- Cost Engine Report
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=582
;

-- Sep 17, 2010 4:29:44 PM CDT
-- Cost Engine Report
UPDATE AD_Ref_List SET AD_Reference_ID=189, Description=NULL, EntityType='D', IsActive='Y', Name='Work Order -', Value='W-',Updated=TO_DATE('2010-09-17 16:29:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=583
;

-- Sep 17, 2010 4:29:44 PM CDT
-- Cost Engine Report
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=583
;

-- Sep 17, 2010 4:29:47 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59800,1039,0,17,189,53288,'MovementType',TO_DATE('2010-09-17 16:29:44','YYYY-MM-DD HH24:MI:SS'),100,'Method of moving the inventory','D',2,'The Movement Type indicates the type of movement (in, out, to production, etc)','Y','N','N','N','N','N','N','N','Y','N','N','Movement Type',TO_DATE('2010-09-17 16:29:44','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:29:47 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59800 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:29:47 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='MovementDate', Description='Date a product was moved in or out of inventory', EntityType='D', Help='The Movement Date indicates the date that a product moved in or out of inventory.  This is the result of a shipment, receipt or inventory movement.', IsActive='Y', Name='Movement Date', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Movement Date',Updated=TO_DATE('2010-09-17 16:29:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1037
;

-- Sep 17, 2010 4:29:47 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1037
;

-- Sep 17, 2010 4:29:47 PM CDT
-- Cost Engine Report
UPDATE AD_Reference SET Description='Date with time', EntityType='D', Help=NULL, IsActive='Y', Name='Date+Time', ValidationType='D',Updated=TO_DATE('2010-09-17 16:29:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=16
;

-- Sep 17, 2010 4:29:47 PM CDT
-- Cost Engine Report
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=16
;

-- Sep 17, 2010 4:29:49 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59801,1037,0,16,53288,'MovementDate',TO_DATE('2010-09-17 16:29:47','YYYY-MM-DD HH24:MI:SS'),100,'Date a product was moved in or out of inventory','D',29,'The Movement Date indicates the date that a product moved in or out of inventory.  This is the result of a shipment, receipt or inventory movement.','Y','N','N','N','N','N','N','N','Y','N','N','Movement Date',TO_DATE('2010-09-17 16:29:47','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:29:49 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59801 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:29:49 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='MovementQty', Description='Quantity of a product moved.', EntityType='D', Help='The Movement Quantity indicates the quantity of a product that has been moved.', IsActive='Y', Name='Movement Quantity', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Qty',Updated=TO_DATE('2010-09-17 16:29:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1038
;

-- Sep 17, 2010 4:29:49 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1038
;

-- Sep 17, 2010 4:29:54 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59802,1038,0,29,53288,'MovementQty',TO_DATE('2010-09-17 16:29:49','YYYY-MM-DD HH24:MI:SS'),100,'Quantity of a product moved.','D',22,'The Movement Quantity indicates the quantity of a product that has been moved.','Y','N','N','N','N','N','N','N','Y','N','N','Movement Quantity',TO_DATE('2010-09-17 16:29:49','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:29:54 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59802 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:29:57 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59803,2019,0,35,53288,'M_AttributeSetInstance_ID',TO_DATE('2010-09-17 16:29:54','YYYY-MM-DD HH24:MI:SS'),100,'Product Attribute Set Instance','D',10,'The values of the actual Product Attribute Instances.  The product level attributes are defined on Product level.','Y','N','N','N','N','N','N','N','Y','N','N','Attribute Set Instance',TO_DATE('2010-09-17 16:29:54','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:29:57 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59803 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:29:57 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='M_AttributeSet_ID', Description='Product Attribute Set', EntityType='D', Help='Define Product Attribute Sets to add additional attributes and values to the product. You need to define a Attribute Set if you want to enable Serial and Lot Number tracking.', IsActive='Y', Name='Attribute Set', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Attribute Set',Updated=TO_DATE('2010-09-17 16:29:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=2017
;

-- Sep 17, 2010 4:29:57 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2017
;

-- Sep 17, 2010 4:30:01 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59804,2017,0,19,53288,'M_AttributeSet_ID',TO_DATE('2010-09-17 16:29:57','YYYY-MM-DD HH24:MI:SS'),100,'Product Attribute Set','D',10,'Define Product Attribute Sets to add additional attributes and values to the product. You need to define a Attribute Set if you want to enable Serial and Lot Number tracking.','Y','N','N','N','N','N','N','N','Y','N','N','Attribute Set',TO_DATE('2010-09-17 16:29:57','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:30:01 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59804 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:30:01 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='SerNo', Description='Product Serial Number ', EntityType='D', Help='The Serial Number identifies a tracked, warranted product.  It can only be used when the quantity is 1.', IsActive='Y', Name='Serial No', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Serial No',Updated=TO_DATE('2010-09-17 16:30:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=568
;

-- Sep 17, 2010 4:30:01 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=568
;

-- Sep 17, 2010 4:30:03 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59805,568,0,10,53288,'SerNo',TO_DATE('2010-09-17 16:30:01','YYYY-MM-DD HH24:MI:SS'),100,'Product Serial Number ','D',40,'The Serial Number identifies a tracked, warranted product.  It can only be used when the quantity is 1.','Y','N','N','N','N','N','N','N','Y','N','N','Serial No',TO_DATE('2010-09-17 16:30:01','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:30:03 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59805 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:30:04 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='Lot', Description='Lot number (alphanumeric)', EntityType='D', Help='The Lot Number indicates the specific lot that a product was part of.', IsActive='Y', Name='Lot No', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Lot No',Updated=TO_DATE('2010-09-17 16:30:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=446
;

-- Sep 17, 2010 4:30:04 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=446
;

-- Sep 17, 2010 4:30:06 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59806,446,0,10,53288,'Lot',TO_DATE('2010-09-17 16:30:04','YYYY-MM-DD HH24:MI:SS'),100,'Lot number (alphanumeric)','D',40,'The Lot Number indicates the specific lot that a product was part of.','Y','N','N','N','N','N','N','N','Y','N','N','Lot No',TO_DATE('2010-09-17 16:30:04','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:30:06 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59806 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:30:06 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='M_Lot_ID', Description='Product Lot Definition', EntityType='D', Help='The individual Lot of a Product', IsActive='Y', Name='Lot', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Lot',Updated=TO_DATE('2010-09-17 16:30:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=2021
;

-- Sep 17, 2010 4:30:06 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2021
;

-- Sep 17, 2010 4:30:09 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59807,2021,0,19,53288,'M_Lot_ID',TO_DATE('2010-09-17 16:30:06','YYYY-MM-DD HH24:MI:SS'),100,'Product Lot Definition','D',10,'The individual Lot of a Product','Y','N','N','N','N','N','N','N','Y','N','N','Lot',TO_DATE('2010-09-17 16:30:06','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:30:09 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59807 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:30:09 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='GuaranteeDate', Description='Date when guarantee expires', EntityType='D', Help='Date when the normal guarantee or availability expires', IsActive='Y', Name='Guarantee Date', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Guarantee date',Updated=TO_DATE('2010-09-17 16:30:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1936
;

-- Sep 17, 2010 4:30:09 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1936
;

-- Sep 17, 2010 4:30:12 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59808,1936,0,16,53288,'GuaranteeDate',TO_DATE('2010-09-17 16:30:09','YYYY-MM-DD HH24:MI:SS'),100,'Date when guarantee expires','D',29,'Date when the normal guarantee or availability expires','Y','N','N','N','N','N','N','N','Y','N','N','Guarantee Date',TO_DATE('2010-09-17 16:30:09','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:30:12 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59808 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:30:15 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59809,454,0,19,53288,'M_Product_ID',TO_DATE('2010-09-17 16:30:12','YYYY-MM-DD HH24:MI:SS'),100,'Product, Service, Item','D',10,'Identifies an item which is either purchased or sold in this organization.','Y','N','N','N','N','N','N','N','Y','N','N','Product',TO_DATE('2010-09-17 16:30:12','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:30:15 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59809 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:30:15 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='Value', Description='Search key for the record in the format required - must be unique', EntityType='D', Help='A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).', IsActive='Y', Name='Search Key', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Search Key',Updated=TO_DATE('2010-09-17 16:30:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=620
;

-- Sep 17, 2010 4:30:15 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=620
;

-- Sep 17, 2010 4:30:17 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59810,620,0,10,53288,'Value',TO_DATE('2010-09-17 16:30:15','YYYY-MM-DD HH24:MI:SS'),100,'Search key for the record in the format required - must be unique','D',40,'A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','N','N','N','N','N','N','Y','Y','N','N','Search Key',TO_DATE('2010-09-17 16:30:15','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:30:17 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59810 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:30:17 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='Name', Description='Alphanumeric identifier of the entity', EntityType='D', Help='The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.', IsActive='Y', Name='Name', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Name',Updated=TO_DATE('2010-09-17 16:30:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=469
;

-- Sep 17, 2010 4:30:17 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=469
;

-- Sep 17, 2010 4:30:19 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,59811,469,0,10,53288,'Name',TO_DATE('2010-09-17 16:30:17','YYYY-MM-DD HH24:MI:SS'),100,'Alphanumeric identifier of the entity','D',255,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','Y','N','N','N','Y','Y','N','N','Name',1,TO_DATE('2010-09-17 16:30:17','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:30:19 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59811 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:30:19 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='Description', Description='Optional short description of the record', EntityType='D', Help='A description is limited to 255 characters.', IsActive='Y', Name='Description', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Description',Updated=TO_DATE('2010-09-17 16:30:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=275
;

-- Sep 17, 2010 4:30:19 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=275
;

-- Sep 17, 2010 4:30:22 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59812,275,0,10,53288,'Description',TO_DATE('2010-09-17 16:30:19','YYYY-MM-DD HH24:MI:SS'),100,'Optional short description of the record','D',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','Y','Y','N','N','Description',TO_DATE('2010-09-17 16:30:19','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:30:22 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59812 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:30:22 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='UPC', Description='Bar Code (Universal Product Code or its superset European Article Number)', EntityType='D', Help='Use this field to enter the bar code for the product in any of the bar code symbologies (Codabar, Code 25, Code 39, Code 93, Code 128, UPC (A), UPC (E), EAN-13, EAN-8, ITF, ITF-14, ISBN, ISSN, JAN-13, JAN-8, POSTNET and FIM, MSI/Plessey, and Pharmacode) ', IsActive='Y', Name='UPC/EAN', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='UPC/EAN',Updated=TO_DATE('2010-09-17 16:30:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=603
;

-- Sep 17, 2010 4:30:22 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=603
;

-- Sep 17, 2010 4:30:25 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59813,603,0,10,53288,'UPC',TO_DATE('2010-09-17 16:30:22','YYYY-MM-DD HH24:MI:SS'),100,'Bar Code (Universal Product Code or its superset European Article Number)','D',30,'Use this field to enter the bar code for the product in any of the bar code symbologies (Codabar, Code 25, Code 39, Code 93, Code 128, UPC (A), UPC (E), EAN-13, EAN-8, ITF, ITF-14, ISBN, ISSN, JAN-13, JAN-8, POSTNET and FIM, MSI/Plessey, and Pharmacode) ','Y','N','N','N','N','N','N','N','Y','N','N','UPC/EAN',TO_DATE('2010-09-17 16:30:22','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:30:25 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59813 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:30:25 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='SKU', Description='Stock Keeping Unit', EntityType='D', Help='The SKU indicates a user defined stock keeping unit.  It may be used for an additional bar code symbols or your own schema.', IsActive='Y', Name='SKU', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='SKU',Updated=TO_DATE('2010-09-17 16:30:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=549
;

-- Sep 17, 2010 4:30:25 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=549
;

-- Sep 17, 2010 4:30:27 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59814,549,0,10,53288,'SKU',TO_DATE('2010-09-17 16:30:25','YYYY-MM-DD HH24:MI:SS'),100,'Stock Keeping Unit','D',30,'The SKU indicates a user defined stock keeping unit.  It may be used for an additional bar code symbols or your own schema.','Y','N','N','N','N','N','N','N','Y','N','N','SKU',TO_DATE('2010-09-17 16:30:25','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:30:27 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59814 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:30:27 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='C_UOM_ID', Description='Unit of Measure', EntityType='D', Help='The UOM defines a unique non monetary Unit of Measure', IsActive='Y', Name='UOM', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='UOM',Updated=TO_DATE('2010-09-17 16:30:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=215
;

-- Sep 17, 2010 4:30:27 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=215
;

-- Sep 17, 2010 4:30:29 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59815,215,0,19,53288,'C_UOM_ID',TO_DATE('2010-09-17 16:30:27','YYYY-MM-DD HH24:MI:SS'),100,'Unit of Measure','D',10,'The UOM defines a unique non monetary Unit of Measure','Y','N','N','N','N','N','N','N','Y','N','N','UOM',TO_DATE('2010-09-17 16:30:27','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:30:29 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59815 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:30:32 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59816,453,0,19,53288,'M_Product_Category_ID',TO_DATE('2010-09-17 16:30:29','YYYY-MM-DD HH24:MI:SS'),100,'Category of a Product','D',10,'Identifies the category which this product belongs to.  Product categories are used for pricing and selection.','Y','N','N','N','N','N','N','N','Y','N','N','Product Category',TO_DATE('2010-09-17 16:30:29','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:30:32 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59816 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:30:35 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59817,852,0,10,53288,'Classification',TO_DATE('2010-09-17 16:30:32','YYYY-MM-DD HH24:MI:SS'),100,'Classification for grouping','D',12,'The Classification can be used to optionally group products.','Y','N','N','N','N','N','N','N','Y','N','N','Classification',TO_DATE('2010-09-17 16:30:32','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:30:35 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59817 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:30:38 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59818,52018,0,10,53288,'Group1',TO_DATE('2010-09-17 16:30:35','YYYY-MM-DD HH24:MI:SS'),100,'D',255,'Y','N','N','N','N','N','N','N','Y','N','N','Group1',TO_DATE('2010-09-17 16:30:35','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:30:38 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59818 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:30:41 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59819,52019,0,10,53288,'Group2',TO_DATE('2010-09-17 16:30:38','YYYY-MM-DD HH24:MI:SS'),100,'D',255,'Y','N','N','N','N','N','N','N','Y','N','N','Group2',TO_DATE('2010-09-17 16:30:38','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:30:41 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59819 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:30:41 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='Weight', Description='Weight of a product', EntityType='D', Help='The Weight indicates the weight  of the product in the Weight UOM of the Client', IsActive='Y', Name='Weight', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Weight',Updated=TO_DATE('2010-09-17 16:30:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=629
;

-- Sep 17, 2010 4:30:41 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=629
;

-- Sep 17, 2010 4:30:44 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59820,629,0,37,53288,'Weight',TO_DATE('2010-09-17 16:30:41','YYYY-MM-DD HH24:MI:SS'),100,'Weight of a product','D',22,'The Weight indicates the weight  of the product in the Weight UOM of the Client','Y','N','N','N','N','N','N','N','Y','N','N','Weight',TO_DATE('2010-09-17 16:30:41','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:30:44 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59820 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:30:44 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='Volume', Description='Volume of a product', EntityType='D', Help='The Volume indicates the volume of the product in the Volume UOM of the Client', IsActive='Y', Name='Volume', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Volume',Updated=TO_DATE('2010-09-17 16:30:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=627
;

-- Sep 17, 2010 4:30:44 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=627
;

-- Sep 17, 2010 4:30:47 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59821,627,0,37,53288,'Volume',TO_DATE('2010-09-17 16:30:44','YYYY-MM-DD HH24:MI:SS'),100,'Volume of a product','D',22,'The Volume indicates the volume of the product in the Volume UOM of the Client','Y','N','N','N','N','N','N','N','Y','N','N','Volume',TO_DATE('2010-09-17 16:30:44','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:30:47 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59821 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:30:47 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='VersionNo', Description='Version Number', EntityType='D', Help=NULL, IsActive='Y', Name='Version No', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Version No',Updated=TO_DATE('2010-09-17 16:30:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1949
;

-- Sep 17, 2010 4:30:47 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1949
;

-- Sep 17, 2010 4:30:49 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59822,1949,0,10,53288,'VersionNo',TO_DATE('2010-09-17 16:30:47','YYYY-MM-DD HH24:MI:SS'),100,'Version Number','D',20,'Y','N','N','N','N','N','N','N','Y','N','N','Version No',TO_DATE('2010-09-17 16:30:47','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:30:49 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59822 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:30:49 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='DocumentNo', Description='Document sequence number of the document', EntityType='D', Help='The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".

If the document type of your document has no automatic document sequence defined, the field is empty if you create a new document. This is for documents which usually have an external number (like vendor invoice).  If you leave the field empty, the system will generate a document number for you. The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).', IsActive='Y', Name='Document No', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Document No',Updated=TO_DATE('2010-09-17 16:30:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=290
;

-- Sep 17, 2010 4:30:49 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=290
;

-- Sep 17, 2010 4:30:49 PM CDT
-- Cost Engine Report
UPDATE AD_Reference SET Description='Character String up to 2000 characters', EntityType='D', Help=NULL, IsActive='Y', Name='Text', ValidationType='D',Updated=TO_DATE('2010-09-17 16:30:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=14
;

-- Sep 17, 2010 4:30:49 PM CDT
-- Cost Engine Report
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=14
;

-- Sep 17, 2010 4:30:54 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59823,290,0,14,53288,'DocumentNo',TO_DATE('2010-09-17 16:30:49','YYYY-MM-DD HH24:MI:SS'),100,'Document sequence number of the document','D',30,'The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".

If the document type of your document has no automatic document sequence defined, the field is empty if you create a new document. This is for documents which usually have an external number (like vendor invoice).  If you leave the field empty, the system will generate a document number for you. The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','N','N','N','N','N','N','Y','Y','N','N','Document No',TO_DATE('2010-09-17 16:30:49','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:30:54 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59823 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:30:54 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='C_DocType_ID', Description='Document type or rules', EntityType='D', Help='The Document Type determines document sequence and processing rules', IsActive='Y', Name='Document Type', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Doc Type',Updated=TO_DATE('2010-09-17 16:30:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=196
;

-- Sep 17, 2010 4:30:54 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=196
;

-- Sep 17, 2010 4:30:56 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59824,196,0,19,53288,'C_DocType_ID',TO_DATE('2010-09-17 16:30:54','YYYY-MM-DD HH24:MI:SS'),100,'Document type or rules','D',10,'The Document Type determines document sequence and processing rules','Y','N','N','N','N','N','N','N','Y','N','N','Document Type',TO_DATE('2010-09-17 16:30:54','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:30:56 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59824 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:30:56 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='M_Locator_ID', Description='Warehouse Locator', EntityType='D', Help='The Locator indicates where in a Warehouse a product is located.', IsActive='Y', Name='Locator', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Locator',Updated=TO_DATE('2010-09-17 16:30:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=448
;

-- Sep 17, 2010 4:30:56 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=448
;

-- Sep 17, 2010 4:31:00 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59825,448,0,19,53288,'M_Locator_ID',TO_DATE('2010-09-17 16:30:56','YYYY-MM-DD HH24:MI:SS'),100,'Warehouse Locator','D',10,'The Locator indicates where in a Warehouse a product is located.','Y','N','N','N','N','N','N','N','Y','N','N','Locator',TO_DATE('2010-09-17 16:30:56','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:31:00 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59825 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:31:00 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='X', Description='X dimension, e.g., Aisle', EntityType='D', Help='The X dimension indicates the Aisle a product is located in.', IsActive='Y', Name='Aisle (X)', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='X',Updated=TO_DATE('2010-09-17 16:31:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=633
;

-- Sep 17, 2010 4:31:00 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=633
;

-- Sep 17, 2010 4:31:04 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59826,633,0,10,53288,'X',TO_DATE('2010-09-17 16:31:00','YYYY-MM-DD HH24:MI:SS'),100,'X dimension, e.g., Aisle','D',60,'The X dimension indicates the Aisle a product is located in.','Y','N','N','N','N','N','N','N','Y','N','N','Aisle (X)',TO_DATE('2010-09-17 16:31:00','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:31:04 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59826 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:31:04 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='Y', Description='Y dimension, e.g., Bin', EntityType='D', Help='The Y dimension indicates the Bin a product is located in', IsActive='Y', Name='Bin (Y)', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Y',Updated=TO_DATE('2010-09-17 16:31:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=635
;

-- Sep 17, 2010 4:31:04 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=635
;

-- Sep 17, 2010 4:31:06 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59827,635,0,10,53288,'Y',TO_DATE('2010-09-17 16:31:04','YYYY-MM-DD HH24:MI:SS'),100,'Y dimension, e.g., Bin','D',60,'The Y dimension indicates the Bin a product is located in','Y','N','N','N','N','N','N','N','Y','N','N','Bin (Y)',TO_DATE('2010-09-17 16:31:04','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:31:06 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59827 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:31:07 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='Z', Description='Z dimension, e.g., Level', EntityType='D', Help='The Z dimension indicates the Level a product is located in.', IsActive='Y', Name='Level (Z)', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Z',Updated=TO_DATE('2010-09-17 16:31:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=637
;

-- Sep 17, 2010 4:31:07 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=637
;

-- Sep 17, 2010 4:31:11 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59828,637,0,10,53288,'Z',TO_DATE('2010-09-17 16:31:07','YYYY-MM-DD HH24:MI:SS'),100,'Z dimension, e.g., Level','D',60,'The Z dimension indicates the Level a product is located in.','Y','N','N','N','N','N','N','N','Y','N','N','Level (Z)',TO_DATE('2010-09-17 16:31:07','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:31:11 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59828 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:31:13 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59829,459,0,19,53288,'M_Warehouse_ID',TO_DATE('2010-09-17 16:31:11','YYYY-MM-DD HH24:MI:SS'),100,'Storage Warehouse and Service Point','D',10,'The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','Y','N','N','N','N','N','N','N','Y','N','N','Warehouse',TO_DATE('2010-09-17 16:31:11','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:31:13 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59829 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:31:13 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='M_InventoryLine_ID', Description='Unique line in an Inventory document', EntityType='D', Help='The Physical Inventory Line indicates the inventory document line (if applicable) for this transaction', IsActive='Y', Name='Phys.Inventory Line', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Phys.Inventory Line',Updated=TO_DATE('2010-09-17 16:31:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1028
;

-- Sep 17, 2010 4:31:13 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1028
;

-- Sep 17, 2010 4:31:15 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59830,1028,0,19,53288,'M_InventoryLine_ID',TO_DATE('2010-09-17 16:31:13','YYYY-MM-DD HH24:MI:SS'),100,'Unique line in an Inventory document','D',10,'The Physical Inventory Line indicates the inventory document line (if applicable) for this transaction','Y','N','N','N','N','N','N','N','Y','N','N','Phys.Inventory Line',TO_DATE('2010-09-17 16:31:13','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:31:15 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59830 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:31:16 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='M_Inventory_ID', Description='Parameters for a Physical Inventory', EntityType='D', Help='The Physical Inventory indicates a unique parameters for a physical inventory.', IsActive='Y', Name='Phys.Inventory', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Phys.Inventory',Updated=TO_DATE('2010-09-17 16:31:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1027
;

-- Sep 17, 2010 4:31:16 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1027
;

-- Sep 17, 2010 4:31:19 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59831,1027,0,19,53288,'M_Inventory_ID',TO_DATE('2010-09-17 16:31:16','YYYY-MM-DD HH24:MI:SS'),100,'Parameters for a Physical Inventory','D',10,'The Physical Inventory indicates a unique parameters for a physical inventory.','Y','N','N','N','N','N','N','N','Y','N','N','Phys.Inventory',TO_DATE('2010-09-17 16:31:16','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:31:19 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59831 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:31:19 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='M_MovementLine_ID', Description='Inventory Move document Line', EntityType='D', Help='The Movement Line indicates the inventory movement document line (if applicable) for this transaction', IsActive='Y', Name='Move Line', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Move Line',Updated=TO_DATE('2010-09-17 16:31:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1031
;

-- Sep 17, 2010 4:31:19 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1031
;

-- Sep 17, 2010 4:31:21 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59832,1031,0,19,53288,'M_MovementLine_ID',TO_DATE('2010-09-17 16:31:19','YYYY-MM-DD HH24:MI:SS'),100,'Inventory Move document Line','D',10,'The Movement Line indicates the inventory movement document line (if applicable) for this transaction','Y','N','N','N','N','N','N','N','Y','N','N','Move Line',TO_DATE('2010-09-17 16:31:19','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:31:21 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59832 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:31:21 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='M_Movement_ID', Description='Movement of Inventory', EntityType='D', Help='The Inventory Movement uniquely identifies a group of movement lines.', IsActive='Y', Name='Inventory Move', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Move',Updated=TO_DATE('2010-09-17 16:31:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1030
;

-- Sep 17, 2010 4:31:21 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1030
;

-- Sep 17, 2010 4:31:23 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59833,1030,0,19,53288,'M_Movement_ID',TO_DATE('2010-09-17 16:31:21','YYYY-MM-DD HH24:MI:SS'),100,'Movement of Inventory','D',10,'The Inventory Movement uniquely identifies a group of movement lines.','Y','N','N','N','N','N','N','N','Y','N','N','Inventory Move',TO_DATE('2010-09-17 16:31:21','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:31:23 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59833 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:31:23 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='M_InOutLine_ID', Description='Line on Shipment or Receipt document', EntityType='D', Help='The Shipment/Receipt Line indicates a unique line in a Shipment/Receipt document', IsActive='Y', Name='Shipment/Receipt Line', PO_Description='Line on Receipt document', PO_Help=NULL, PO_Name='Receipt Line', PO_PrintName='Receipt Line', PrintName='Shipment/Receipt Line',Updated=TO_DATE('2010-09-17 16:31:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1026
;

-- Sep 17, 2010 4:31:23 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1026
;

-- Sep 17, 2010 4:31:26 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59834,1026,0,19,53288,'M_InOutLine_ID',TO_DATE('2010-09-17 16:31:23','YYYY-MM-DD HH24:MI:SS'),100,'Line on Shipment or Receipt document','D',10,'The Shipment/Receipt Line indicates a unique line in a Shipment/Receipt document','Y','N','N','N','N','N','N','N','Y','N','N','Shipment/Receipt Line',TO_DATE('2010-09-17 16:31:23','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:31:26 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59834 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:31:28 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59835,1025,0,19,53288,'M_InOut_ID',TO_DATE('2010-09-17 16:31:26','YYYY-MM-DD HH24:MI:SS'),100,'Material Shipment Document','D',10,'The Material Shipment / Receipt ','Y','N','N','N','N','N','N','N','Y','N','N','Shipment/Receipt',TO_DATE('2010-09-17 16:31:26','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:31:28 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59835 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:31:28 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='M_ProductionLine_ID', Description='Document Line representing a production', EntityType='D', Help='The Production Line indicates the production document line (if applicable) for this transaction', IsActive='Y', Name='Production Line', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Production Line',Updated=TO_DATE('2010-09-17 16:31:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1033
;

-- Sep 17, 2010 4:31:28 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1033
;

-- Sep 17, 2010 4:31:31 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59836,1033,0,19,53288,'M_ProductionLine_ID',TO_DATE('2010-09-17 16:31:28','YYYY-MM-DD HH24:MI:SS'),100,'Document Line representing a production','D',10,'The Production Line indicates the production document line (if applicable) for this transaction','Y','N','N','N','N','N','N','N','Y','N','N','Production Line',TO_DATE('2010-09-17 16:31:28','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:31:31 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59836 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:31:31 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='M_ProductionPlan_ID', Description='Plan for how a product is produced', EntityType='D', Help='The Production Plan identifies the items and steps in generating a product.', IsActive='Y', Name='Production Plan', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Production Plan',Updated=TO_DATE('2010-09-17 16:31:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1342
;

-- Sep 17, 2010 4:31:31 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1342
;

-- Sep 17, 2010 4:31:33 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59837,1342,0,19,53288,'M_ProductionPlan_ID',TO_DATE('2010-09-17 16:31:31','YYYY-MM-DD HH24:MI:SS'),100,'Plan for how a product is produced','D',10,'The Production Plan identifies the items and steps in generating a product.','Y','N','N','N','N','N','N','N','Y','N','N','Production Plan',TO_DATE('2010-09-17 16:31:31','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:31:33 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59837 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:31:33 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='M_Production_ID', Description='Plan for producing a product', EntityType='D', Help='The Production uniquely identifies a Production Plan', IsActive='Y', Name='Production', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Production',Updated=TO_DATE('2010-09-17 16:31:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1032
;

-- Sep 17, 2010 4:31:33 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1032
;

-- Sep 17, 2010 4:31:36 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59838,1032,0,19,53288,'M_Production_ID',TO_DATE('2010-09-17 16:31:33','YYYY-MM-DD HH24:MI:SS'),100,'Plan for producing a product','D',10,'The Production uniquely identifies a Production Plan','Y','N','N','N','N','N','N','N','Y','N','N','Production',TO_DATE('2010-09-17 16:31:33','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:31:36 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59838 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:31:36 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='C_ProjectIssue_ID', Description='Project Issues (Material, Labor)', EntityType='D', Help='Issues to the project initiated by the "Issue to Project" process. You can issue Receipts, Time and Expenses, or Stock.', IsActive='Y', Name='Project Issue', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Project Issue',Updated=TO_DATE('2010-09-17 16:31:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=2178
;

-- Sep 17, 2010 4:31:36 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2178
;

-- Sep 17, 2010 4:31:39 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59839,2178,0,19,53288,'C_ProjectIssue_ID',TO_DATE('2010-09-17 16:31:36','YYYY-MM-DD HH24:MI:SS'),100,'Project Issues (Material, Labor)','D',10,'Issues to the project initiated by the "Issue to Project" process. You can issue Receipts, Time and Expenses, or Stock.','Y','N','N','N','N','N','N','N','Y','N','N','Project Issue',TO_DATE('2010-09-17 16:31:36','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:31:39 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59839 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:31:39 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='C_Project_ID', Description='Financial Project', EntityType='D', Help='A Project allows you to track and control internal or external activities.', IsActive='Y', Name='Project', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Project',Updated=TO_DATE('2010-09-17 16:31:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=208
;

-- Sep 17, 2010 4:31:39 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=208
;

-- Sep 17, 2010 4:31:42 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59840,208,0,19,53288,'C_Project_ID',TO_DATE('2010-09-17 16:31:39','YYYY-MM-DD HH24:MI:SS'),100,'Financial Project','D',10,'A Project allows you to track and control internal or external activities.','Y','N','N','N','N','N','N','N','Y','N','N','Project',TO_DATE('2010-09-17 16:31:39','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:31:42 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59840 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:31:42 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='PP_Cost_Collector_ID', Description=NULL, EntityType='EE01', Help=NULL, IsActive='Y', Name='Manufacturing Cost Collector', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Manufacturing Cost Collector',Updated=TO_DATE('2010-09-17 16:31:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53310
;

-- Sep 17, 2010 4:31:42 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53310
;

-- Sep 17, 2010 4:31:45 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59841,53310,0,19,53288,'PP_Cost_Collector_ID',TO_DATE('2010-09-17 16:31:42','YYYY-MM-DD HH24:MI:SS'),100,'D',10,'Y','N','N','N','N','N','N','N','Y','N','N','Manufacturing Cost Collector',TO_DATE('2010-09-17 16:31:42','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:31:45 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59841 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:31:45 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='DateAcct', Description='Accounting Date', EntityType='D', Help='The Accounting Date indicates the date to be used on the General Ledger account entries generated from this document. It is also used for any currency conversion.', IsActive='Y', Name='Account Date', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Acct Date',Updated=TO_DATE('2010-09-17 16:31:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=263
;

-- Sep 17, 2010 4:31:45 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=263
;

-- Sep 17, 2010 4:31:48 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59842,263,0,16,53288,'DateAcct',TO_DATE('2010-09-17 16:31:45','YYYY-MM-DD HH24:MI:SS'),100,'Accounting Date','D',29,'The Accounting Date indicates the date to be used on the General Ledger account entries generated from this document. It is also used for any currency conversion.','Y','N','N','N','N','N','N','N','Y','N','N','Account Date',TO_DATE('2010-09-17 16:31:45','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:31:48 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59842 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:31:52 PM CDT
-- Cost Engine Report
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,54277,0,'BeginningQtyBalance',TO_DATE('2010-09-17 16:31:48','YYYY-MM-DD HH24:MI:SS'),100,'Quantity Balance prior to any transactions','D','The Quantity Beginning Balance is the balance prior','Y','Beginning Qty Balance','Beginning Qty Balance',TO_DATE('2010-09-17 16:31:48','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 17, 2010 4:31:52 PM CDT
-- Cost Engine Report
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54277 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Sep 17, 2010 4:31:55 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59843,54277,0,29,53288,'BeginningQtyBalance',TO_DATE('2010-09-17 16:31:52','YYYY-MM-DD HH24:MI:SS'),100,'Quantity Balance prior to any transactions','D',22,'The Quantity Beginning Balance is the balance prior','Y','N','N','N','N','N','N','N','Y','N','N','Beginning Qty Balance',TO_DATE('2010-09-17 16:31:52','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:31:55 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59843 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:31:56 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='CurrentCostPrice', Description='The currently used cost price', EntityType='D', Help=NULL, IsActive='Y', Name='Current Cost Price', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Current Cost Price',Updated=TO_DATE('2010-09-17 16:31:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1394
;

-- Sep 17, 2010 4:31:56 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1394
;

-- Sep 17, 2010 4:31:58 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59844,1394,0,37,53288,'CurrentCostPrice',TO_DATE('2010-09-17 16:31:56','YYYY-MM-DD HH24:MI:SS'),100,'The currently used cost price','D',22,'Y','N','N','N','N','N','N','N','Y','N','N','Current Cost Price',TO_DATE('2010-09-17 16:31:56','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:31:58 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59844 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:31:58 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='BeginningBalance', Description='Balance prior to any transactions', EntityType='D', Help='The Beginning Balance is the balance prior to making any adjustments for payments or disbursements.', IsActive='Y', Name='Beginning Balance', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Beginning Balance',Updated=TO_DATE('2010-09-17 16:31:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1378
;

-- Sep 17, 2010 4:31:58 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1378
;

-- Sep 17, 2010 4:32:01 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59845,1378,0,37,53288,'BeginningBalance',TO_DATE('2010-09-17 16:31:58','YYYY-MM-DD HH24:MI:SS'),100,'Balance prior to any transactions','D',22,'The Beginning Balance is the balance prior to making any adjustments for payments or disbursements.','Y','N','N','N','N','N','N','N','Y','N','N','Beginning Balance',TO_DATE('2010-09-17 16:31:58','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:32:01 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59845 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:32:01 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='Qty', Description='Quantity', EntityType='D', Help='The Quantity indicates the number of a specific product or item for this document.', IsActive='Y', Name='Quantity', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Qty',Updated=TO_DATE('2010-09-17 16:32:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=526
;

-- Sep 17, 2010 4:32:01 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=526
;

-- Sep 17, 2010 4:32:04 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59846,526,0,29,53288,'Qty',TO_DATE('2010-09-17 16:32:01','YYYY-MM-DD HH24:MI:SS'),100,'Quantity','D',22,'The Quantity indicates the number of a specific product or item for this document.','Y','N','N','N','N','N','N','N','Y','N','N','Quantity',TO_DATE('2010-09-17 16:32:01','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:32:04 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59846 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:32:04 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='Amt', Description='Amount', EntityType='D', Help='Amount', IsActive='Y', Name='Amount', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Amt',Updated=TO_DATE('2010-09-17 16:32:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=160
;

-- Sep 17, 2010 4:32:04 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=160
;

-- Sep 17, 2010 4:32:07 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59847,160,0,12,53288,'Amt',TO_DATE('2010-09-17 16:32:04','YYYY-MM-DD HH24:MI:SS'),100,'Amount','D',22,'Amount','Y','N','N','N','N','N','N','N','Y','N','N','Amount',TO_DATE('2010-09-17 16:32:04','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:32:07 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59847 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:32:10 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59848,2962,0,12,53288,'CostAmt',TO_DATE('2010-09-17 16:32:07','YYYY-MM-DD HH24:MI:SS'),100,'Value with Cost','D',22,'Y','N','N','N','N','N','N','N','Y','N','N','Cost Value',TO_DATE('2010-09-17 16:32:07','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:32:10 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59848 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:32:14 PM CDT
-- Cost Engine Report
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,54278,0,'EndingQtyBalance',TO_DATE('2010-09-17 16:32:10','YYYY-MM-DD HH24:MI:SS'),100,'Quantity Ending  or closing balance','D','The Qunatity Ending Balance is the result of adjusting the Quantity Beginning Balance ','Y','Ending Qty Balance','Ending Qty Balance',TO_DATE('2010-09-17 16:32:10','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 17, 2010 4:32:14 PM CDT
-- Cost Engine Report
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54278 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Sep 17, 2010 4:32:18 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59849,54278,0,29,53288,'EndingQtyBalance',TO_DATE('2010-09-17 16:32:14','YYYY-MM-DD HH24:MI:SS'),100,'Quantity Ending  or closing balance','D',22,'The Qunatity Ending Balance is the result of adjusting the Quantity Beginning Balance ','Y','N','N','N','N','N','N','N','Y','N','N','Ending Qty Balance',TO_DATE('2010-09-17 16:32:14','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:32:18 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59849 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:32:18 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='EndingBalance', Description='Ending  or closing balance', EntityType='D', Help='The Ending Balance is the result of adjusting the Beginning Balance by any payments or disbursements.', IsActive='Y', Name='Ending balance', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Ending balance',Updated=TO_DATE('2010-09-17 16:32:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1396
;

-- Sep 17, 2010 4:32:18 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1396
;

-- Sep 17, 2010 4:32:22 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59850,1396,0,37,53288,'EndingBalance',TO_DATE('2010-09-17 16:32:18','YYYY-MM-DD HH24:MI:SS'),100,'Ending  or closing balance','D',22,'The Ending Balance is the result of adjusting the Beginning Balance by any payments or disbursements.','Y','N','N','N','N','N','N','N','Y','N','N','Ending balance',TO_DATE('2010-09-17 16:32:18','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:32:22 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59850 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:32:22 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='C_AcctSchema_ID', Description='Rules for accounting', EntityType='D', Help='An Accounting Schema defines the rules used in accounting such as costing method, currency and calendar', IsActive='Y', Name='Accounting Schema', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Accounting Schema',Updated=TO_DATE('2010-09-17 16:32:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=181
;

-- Sep 17, 2010 4:32:22 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=181
;

-- Sep 17, 2010 4:32:25 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59851,181,0,19,53288,'C_AcctSchema_ID',TO_DATE('2010-09-17 16:32:22','YYYY-MM-DD HH24:MI:SS'),100,'Rules for accounting','D',10,'An Accounting Schema defines the rules used in accounting such as costing method, currency and calendar','Y','N','N','N','N','N','N','N','Y','N','N','Accounting Schema',TO_DATE('2010-09-17 16:32:22','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:32:25 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59851 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:32:28 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59852,2071,0,19,53288,'M_CostType_ID',TO_DATE('2010-09-17 16:32:25','YYYY-MM-DD HH24:MI:SS'),100,'Type of Cost (e.g. Current, Plan, Future)','D',10,'You can define multiple cost types. A cost type selected in an Accounting Schema is used for accounting.','Y','N','N','N','N','N','N','N','Y','N','N','Cost Type',TO_DATE('2010-09-17 16:32:25','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:32:28 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59852 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:32:31 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59853,2700,0,19,53288,'M_CostElement_ID',TO_DATE('2010-09-17 16:32:28','YYYY-MM-DD HH24:MI:SS'),100,'Product Cost Element','D',10,'Y','N','N','N','N','N','N','N','Y','N','N','Cost Element',TO_DATE('2010-09-17 16:32:28','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:32:31 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59853 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:32:31 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='CostAdjustment', Description='Product Cost Adjustment', EntityType='D', Help='product cost adjustments', IsActive='Y', Name='Cost Adjustment', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Cost Adjustment',Updated=TO_DATE('2010-09-17 16:32:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=54251
;

-- Sep 17, 2010 4:32:31 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=54251
;

-- Sep 17, 2010 4:32:35 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59854,54251,0,37,53288,'CostAdjustment',TO_DATE('2010-09-17 16:32:31','YYYY-MM-DD HH24:MI:SS'),100,'Product Cost Adjustment','D',22,'product cost adjustments','Y','N','N','N','N','N','N','N','Y','N','N','Cost Adjustment',TO_DATE('2010-09-17 16:32:31','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:32:35 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59854 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:32:35 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='CostAdjustmentDate', Description='Product Cost Adjustment', EntityType='D', Help='product cost adjustments', IsActive='Y', Name='Cost Adjustment Date', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Cost Adjustment Date',Updated=TO_DATE('2010-09-17 16:32:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=54252
;

-- Sep 17, 2010 4:32:35 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=54252
;

-- Sep 17, 2010 4:32:38 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59855,54252,0,16,53288,'CostAdjustmentDate',TO_DATE('2010-09-17 16:32:35','YYYY-MM-DD HH24:MI:SS'),100,'Product Cost Adjustment','D',29,'product cost adjustments','Y','N','N','N','N','N','N','N','Y','N','N','Cost Adjustment Date',TO_DATE('2010-09-17 16:32:35','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:32:38 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59855 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:32:38 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='CurrentCostPriceLL', Description='Current Price Lower Level Is the sum of the costs of the components of this product manufactured for this level.', EntityType='EE01', Help='Current Price Lower Level is used for get the total costs for lower level the a product manufactured.

The Current Price Lower Level always will be calculated.

You can see the Current Cost Price and Current Cost Price Lower Level with Cost  Bill of Material & Formula Detail Report.
 
The sum the Current Cost Price + Current Cost Price Lower Level is the total cost to a product manufactured.
', IsActive='Y', Name='Current Cost Price Lower Level', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Current Cost Price Lower Level',Updated=TO_DATE('2010-09-17 16:32:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53296
;

-- Sep 17, 2010 4:32:38 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53296
;

-- Sep 17, 2010 4:32:42 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59856,53296,0,37,53288,'CurrentCostPriceLL',TO_DATE('2010-09-17 16:32:38','YYYY-MM-DD HH24:MI:SS'),100,'Current Price Lower Level Is the sum of the costs of the components of this product manufactured for this level.','D',22,'Current Price Lower Level is used for get the total costs for lower level the a product manufactured.

The Current Price Lower Level always will be calculated.

You can see the Current Cost Price and Current Cost Price Lower Level with Cost  Bill of Material & Formula Detail Report.
 
The sum the Current Cost Price + Current Cost Price Lower Level is the total cost to a product manufactured.
','Y','N','N','N','N','N','N','N','Y','N','N','Current Cost Price Lower Level',TO_DATE('2010-09-17 16:32:38','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:32:42 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59856 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:32:42 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='IsReversal', Description='This is a reversing transaction', EntityType='D', Help='The Reversal check box indicates if this is a reversal of a prior transaction.', IsActive='Y', Name='Reversal', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Reversal',Updated=TO_DATE('2010-09-17 16:32:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1476
;

-- Sep 17, 2010 4:32:42 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1476
;

-- Sep 17, 2010 4:32:42 PM CDT
-- Cost Engine Report
UPDATE AD_Reference SET Description='CheckBox', EntityType='D', Help=NULL, IsActive='Y', Name='Yes-No', ValidationType='D',Updated=TO_DATE('2010-09-17 16:32:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=20
;

-- Sep 17, 2010 4:32:42 PM CDT
-- Cost Engine Report
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=20
;

-- Sep 17, 2010 4:32:45 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59857,1476,0,20,53288,'IsReversal',TO_DATE('2010-09-17 16:32:42','YYYY-MM-DD HH24:MI:SS'),100,'This is a reversing transaction','D',1,'The Reversal check box indicates if this is a reversal of a prior transaction.','Y','N','N','N','N','N','N','N','Y','N','N','Reversal',TO_DATE('2010-09-17 16:32:42','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:32:45 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59857 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:32:45 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='IsSOTrx', Description='This is a Sales Transaction', EntityType='D', Help='The Sales Transaction checkbox indicates if this item is a Sales Transaction.', IsActive='Y', Name='Sales Transaction', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Sales Transaction',Updated=TO_DATE('2010-09-17 16:32:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1106
;

-- Sep 17, 2010 4:32:45 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1106
;

-- Sep 17, 2010 4:32:48 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59858,1106,0,20,53288,'IsSOTrx',TO_DATE('2010-09-17 16:32:45','YYYY-MM-DD HH24:MI:SS'),100,'This is a Sales Transaction','D',1,'The Sales Transaction checkbox indicates if this item is a Sales Transaction.','Y','N','N','N','N','N','N','N','Y','N','N','Sales Transaction',TO_DATE('2010-09-17 16:32:45','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:32:48 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59858 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:32:48 PM CDT
-- Cost Engine Report
UPDATE AD_Element SET ColumnName='M_CostDetail_ID', Description='Cost Detail Information', EntityType='D', Help=NULL, IsActive='Y', Name='Cost Detail', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Cost Detail',Updated=TO_DATE('2010-09-17 16:32:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=2817
;

-- Sep 17, 2010 4:32:48 PM CDT
-- Cost Engine Report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2817
;

-- Sep 17, 2010 4:32:51 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59859,2817,0,19,53288,'M_CostDetail_ID',TO_DATE('2010-09-17 16:32:48','YYYY-MM-DD HH24:MI:SS'),100,'Cost Detail Information','D',10,'Y','N','N','N','N','N','N','N','Y','N','N','Cost Detail',TO_DATE('2010-09-17 16:32:48','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2010 4:32:51 PM CDT
-- Cost Engine Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59859 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 17, 2010 4:32:54 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormat (AD_Client_ID,AD_Org_ID,AD_PrintColor_ID,AD_PrintFont_ID,AD_PrintFormat_ID,AD_PrintPaper_ID,AD_ReportView_ID,AD_Table_ID,CreateCopy,Created,CreatedBy,FooterMargin,HeaderMargin,IsActive,IsDefault,IsForm,IsStandardHeaderFooter,IsTableBased,Name,Updated,UpdatedBy) VALUES (0,0,100,163,50053,100,53033,53288,NULL,TO_DATE('2010-09-17 16:32:51','YYYY-MM-DD HH24:MI:SS'),100,0,0,'Y','N','N','Y','Y','Transaction Value Report',TO_DATE('2010-09-17 16:32:51','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 17, 2010 4:32:57 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59811,0,51579,50053,0,0,TO_DATE('2010-09-17 16:32:54','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Name','C','F','Name',0,0,'N',0,TO_DATE('2010-09-17 16:32:54','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:32:57 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51579 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:33:00 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59851,0,51580,50053,0,0,TO_DATE('2010-09-17 16:32:57','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','Y','N','N','N','N','N','Y','N','N','Y','N','N','N','N','N','X',1,0,0,'Accounting Schema','C','F','Accounting Schema',0,0,'N',10,TO_DATE('2010-09-17 16:32:57','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:33:00 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51580 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:33:03 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59826,0,51581,50053,0,0,TO_DATE('2010-09-17 16:33:00','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Aisle (X)','C','F','X',0,0,'N',0,TO_DATE('2010-09-17 16:33:00','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:33:03 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51581 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:33:06 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59804,0,51582,50053,0,0,TO_DATE('2010-09-17 16:33:03','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Attribute Set','C','F','Attribute Set',0,0,'N',0,TO_DATE('2010-09-17 16:33:03','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:33:06 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51582 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:33:10 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59803,0,51583,50053,0,0,TO_DATE('2010-09-17 16:33:06','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Attribute Set Instance','C','F','Attribute Set Instance',0,0,'N',0,TO_DATE('2010-09-17 16:33:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:33:10 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51583 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:33:13 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59827,0,51584,50053,0,0,TO_DATE('2010-09-17 16:33:10','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Bin (Y)','C','F','Y',0,0,'N',0,TO_DATE('2010-09-17 16:33:10','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:33:13 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51584 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:33:17 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59817,0,51585,50053,0,0,TO_DATE('2010-09-17 16:33:13','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Classification','C','F','Classification',0,0,'N',0,TO_DATE('2010-09-17 16:33:13','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:33:17 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51585 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:33:20 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59798,0,51586,50053,0,0,TO_DATE('2010-09-17 16:33:17','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Client','C','F','Client',0,0,'N',0,TO_DATE('2010-09-17 16:33:17','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:33:20 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51586 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:33:23 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59855,0,51587,50053,0,0,TO_DATE('2010-09-17 16:33:20','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Cost Adjustment Date','C','F','Cost Adjustment Date',0,0,'N',0,TO_DATE('2010-09-17 16:33:20','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:33:23 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51587 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:33:26 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59859,0,51588,50053,0,0,TO_DATE('2010-09-17 16:33:23','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Cost Detail','C','F','Cost Detail',0,0,'N',0,TO_DATE('2010-09-17 16:33:23','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:33:26 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51588 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:33:30 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59853,0,51589,50053,0,0,TO_DATE('2010-09-17 16:33:26','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','N','N','N','N','N','N','Y','N','N','Y','N','N','N','N','N','X',1,0,0,'Cost Element','C','F','Cost Element',0,0,'N',60,TO_DATE('2010-09-17 16:33:26','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:33:30 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51589 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:33:34 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59852,0,51590,50053,0,0,TO_DATE('2010-09-17 16:33:30','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','Y','Y','Y','N','N','N','N','Y','N','N','Y','N','N','N','N','N','X',1,0,80,'Cost Type','C','F','Cost Type',0,0,'N',30,TO_DATE('2010-09-17 16:33:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:33:34 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51590 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:33:37 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59848,0,51591,50053,0,0,TO_DATE('2010-09-17 16:33:34','YYYY-MM-DD HH24:MI:SS'),100,'T','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Cost Value','C','F','Cost Value',0,0,'N',0,TO_DATE('2010-09-17 16:33:34','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:33:37 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51591 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:33:40 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59844,0,51592,50053,0,0,TO_DATE('2010-09-17 16:33:37','YYYY-MM-DD HH24:MI:SS'),100,'T','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Current Cost Price','C','F','Current Cost Price',0,0,'N',0,TO_DATE('2010-09-17 16:33:37','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:33:40 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51592 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:33:45 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59856,0,51593,50053,0,0,TO_DATE('2010-09-17 16:33:40','YYYY-MM-DD HH24:MI:SS'),100,'T','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Current Cost Price Lower Level','C','F','Current Cost Price Lower Level',0,0,'N',0,TO_DATE('2010-09-17 16:33:40','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:33:45 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51593 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:33:49 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59812,0,51594,50053,0,0,TO_DATE('2010-09-17 16:33:45','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Description','C','F','Description',0,0,'N',0,TO_DATE('2010-09-17 16:33:45','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:33:49 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51594 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:33:51 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59823,0,51595,50053,0,0,TO_DATE('2010-09-17 16:33:49','YYYY-MM-DD HH24:MI:SS'),100,'B','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Document No','C','F','Document No',0,0,'N',0,TO_DATE('2010-09-17 16:33:49','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:33:51 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51595 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:33:54 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59824,0,51596,50053,0,0,TO_DATE('2010-09-17 16:33:51','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Document Type','C','F','Doc Type',0,0,'N',0,TO_DATE('2010-09-17 16:33:51','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:33:54 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51596 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:33:57 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59818,0,51597,50053,0,0,TO_DATE('2010-09-17 16:33:54','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Group1','C','F','Group1',0,0,'N',0,TO_DATE('2010-09-17 16:33:54','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:33:57 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51597 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:33:59 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59819,0,51598,50053,0,0,TO_DATE('2010-09-17 16:33:57','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Group2','C','F','Group2',0,0,'N',0,TO_DATE('2010-09-17 16:33:57','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:34:00 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51598 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:34:04 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59808,0,51599,50053,0,0,TO_DATE('2010-09-17 16:34:00','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Guarantee Date','C','F','Guarantee date',0,0,'N',0,TO_DATE('2010-09-17 16:34:00','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:34:04 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51599 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:34:08 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59833,0,51600,50053,0,0,TO_DATE('2010-09-17 16:34:04','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Inventory Move','C','F','Move',0,0,'N',0,TO_DATE('2010-09-17 16:34:04','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:34:08 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51600 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:34:11 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59797,0,51601,50053,0,0,TO_DATE('2010-09-17 16:34:08','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Inventory Transaction','C','F','Inventory Transaction',0,0,'N',0,TO_DATE('2010-09-17 16:34:08','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:34:11 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51601 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:34:14 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59828,0,51602,50053,0,0,TO_DATE('2010-09-17 16:34:11','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Level (Z)','C','F','Z',0,0,'N',0,TO_DATE('2010-09-17 16:34:11','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:34:14 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51602 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:34:18 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59825,0,51603,50053,0,0,TO_DATE('2010-09-17 16:34:14','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Locator','C','F','Locator',0,0,'N',0,TO_DATE('2010-09-17 16:34:14','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:34:18 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51603 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:34:21 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59807,0,51604,50053,0,0,TO_DATE('2010-09-17 16:34:18','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Lot','C','F','Lot',0,0,'N',0,TO_DATE('2010-09-17 16:34:18','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:34:21 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51604 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:34:26 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59806,0,51605,50053,0,0,TO_DATE('2010-09-17 16:34:21','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Lot No','C','F','Lot No',0,0,'N',0,TO_DATE('2010-09-17 16:34:21','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:34:26 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51605 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:34:29 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59841,0,51606,50053,0,0,TO_DATE('2010-09-17 16:34:26','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Manufacturing Cost Collector','C','F','Manufacturing Cost Collector',0,0,'N',0,TO_DATE('2010-09-17 16:34:26','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:34:29 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51606 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:34:32 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59832,0,51607,50053,0,0,TO_DATE('2010-09-17 16:34:29','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Move Line','C','F','Move Line',0,0,'N',0,TO_DATE('2010-09-17 16:34:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:34:32 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51607 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:34:35 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59801,0,51608,50053,0,0,TO_DATE('2010-09-17 16:34:32','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Movement Date','C','F','Movement Date',0,0,'N',0,TO_DATE('2010-09-17 16:34:32','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:34:35 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51608 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:34:38 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59802,0,51609,50053,0,0,TO_DATE('2010-09-17 16:34:35','YYYY-MM-DD HH24:MI:SS'),100,'T','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Movement Quantity','C','F','Qty',0,0,'N',0,TO_DATE('2010-09-17 16:34:35','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:34:38 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51609 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:34:41 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59799,0,51610,50053,0,0,TO_DATE('2010-09-17 16:34:38','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Organization','C','F','Organization',0,0,'N',0,TO_DATE('2010-09-17 16:34:38','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:34:41 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51610 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:34:43 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59831,0,51611,50053,0,0,TO_DATE('2010-09-17 16:34:41','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Phys.Inventory','C','F','Phys.Inventory',0,0,'N',0,TO_DATE('2010-09-17 16:34:41','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:34:43 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51611 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:34:45 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59830,0,51612,50053,0,0,TO_DATE('2010-09-17 16:34:43','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Phys.Inventory Line','C','F','Phys.Inventory Line',0,0,'N',0,TO_DATE('2010-09-17 16:34:43','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:34:45 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51612 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:34:48 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59816,0,51613,50053,0,0,TO_DATE('2010-09-17 16:34:45','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','Y','N','Y','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,80,'Product Category','C','F','Product Category',0,0,'N',0,TO_DATE('2010-09-17 16:34:45','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:34:48 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51613 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:34:51 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59838,0,51614,50053,0,0,TO_DATE('2010-09-17 16:34:48','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Production','C','F','Production',0,0,'N',0,TO_DATE('2010-09-17 16:34:48','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:34:51 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51614 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:34:53 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59836,0,51615,50053,0,0,TO_DATE('2010-09-17 16:34:51','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Production Line','C','F','Production Line',0,0,'N',0,TO_DATE('2010-09-17 16:34:51','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:34:53 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51615 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:34:56 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59837,0,51616,50053,0,0,TO_DATE('2010-09-17 16:34:53','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Production Plan','C','F','Production Plan',0,0,'N',0,TO_DATE('2010-09-17 16:34:53','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:34:56 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51616 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:34:59 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59840,0,51617,50053,0,0,TO_DATE('2010-09-17 16:34:56','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Project','C','F','Project',0,0,'N',0,TO_DATE('2010-09-17 16:34:56','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:34:59 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51617 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:35:02 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59839,0,51618,50053,0,0,TO_DATE('2010-09-17 16:34:59','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Project Issue','C','F','Project Issue',0,0,'N',0,TO_DATE('2010-09-17 16:34:59','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:35:02 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51618 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:35:05 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59857,0,51619,50053,0,0,TO_DATE('2010-09-17 16:35:02','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Reversal','C','F','Reversal',0,0,'N',0,TO_DATE('2010-09-17 16:35:02','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:35:05 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51619 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:35:08 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59814,0,51620,50053,0,0,TO_DATE('2010-09-17 16:35:05','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'SKU','C','F','SKU',0,0,'N',0,TO_DATE('2010-09-17 16:35:05','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:35:08 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51620 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:35:11 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59858,0,51621,50053,0,0,TO_DATE('2010-09-17 16:35:08','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Sales Transaction','C','F','Sales Transaction',0,0,'N',0,TO_DATE('2010-09-17 16:35:08','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:35:11 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51621 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:35:15 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59810,0,51622,50053,0,0,TO_DATE('2010-09-17 16:35:11','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Search Key','C','F','Search Key',0,0,'N',0,TO_DATE('2010-09-17 16:35:11','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:35:15 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51622 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:35:18 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59805,0,51623,50053,0,0,TO_DATE('2010-09-17 16:35:15','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Serial No','C','F','Serial No',0,0,'N',0,TO_DATE('2010-09-17 16:35:15','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:35:18 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51623 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:35:21 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59835,0,51624,50053,0,0,TO_DATE('2010-09-17 16:35:18','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Shipment/Receipt','C','F','Shipment/Receipt',0,0,'N',0,TO_DATE('2010-09-17 16:35:18','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:35:21 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51624 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:35:24 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59834,0,51625,50053,0,0,TO_DATE('2010-09-17 16:35:21','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Shipment/Receipt Line','C','F','Shipment/Receipt Line',0,0,'N',0,TO_DATE('2010-09-17 16:35:21','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:35:24 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51625 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:35:26 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59813,0,51626,50053,0,0,TO_DATE('2010-09-17 16:35:24','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'UPC/EAN','C','F','UPC/EAN',0,0,'N',0,TO_DATE('2010-09-17 16:35:24','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:35:26 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51626 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:35:29 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59822,0,51627,50053,0,0,TO_DATE('2010-09-17 16:35:26','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Version No','C','F','Version No',0,0,'N',0,TO_DATE('2010-09-17 16:35:26','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:35:29 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51627 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:35:32 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59821,0,51628,50053,0,0,TO_DATE('2010-09-17 16:35:29','YYYY-MM-DD HH24:MI:SS'),100,'T','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Volume','C','F','Volume',0,0,'N',0,TO_DATE('2010-09-17 16:35:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:35:32 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51628 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:35:35 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59829,0,51629,50053,0,0,TO_DATE('2010-09-17 16:35:32','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','Y','Y','Y','N','N','N','N','Y','N','N','Y','N','N','N','N','N','X',1,0,80,'Warehouse','C','F','Warehouse',0,0,'N',20,TO_DATE('2010-09-17 16:35:32','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:35:35 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51629 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:35:38 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59820,0,51630,50053,0,0,TO_DATE('2010-09-17 16:35:35','YYYY-MM-DD HH24:MI:SS'),100,'T','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Weight','C','F','Weight',0,0,'N',0,TO_DATE('2010-09-17 16:35:35','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:35:38 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51630 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:35:41 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59809,0,51631,50053,0,0,TO_DATE('2010-09-17 16:35:38','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','Y','Y','Y','N','N','N','N','Y','N','Y','Y','N','N','N','N','N','X',1,0,120,'Product','C','F','Product',0,10,'N',40,TO_DATE('2010-09-17 16:35:38','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:35:41 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51631 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:35:46 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59815,0,51632,50053,0,0,TO_DATE('2010-09-17 16:35:41','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'UOM','C','F','UOM',0,20,'N',0,TO_DATE('2010-09-17 16:35:41','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:35:46 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51632 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:35:53 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59842,0,51633,50053,0,0,TO_DATE('2010-09-17 16:35:46','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','Y','N','Y','N','N','N','N','Y','N','Y','Y','N','N','N','N','N','X',1,0,60,'Account Date','C','F','Acct Date',0,30,'N',50,TO_DATE('2010-09-17 16:35:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:35:53 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51633 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:35:56 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59843,0,51634,50053,0,0,TO_DATE('2010-09-17 16:35:53','YYYY-MM-DD HH24:MI:SS'),100,'T','N','Y','N','N','N','Y','N','Y','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,30,'Beginning Qty Balance','C','F','Beginning Qty Balance',0,40,'N',0,TO_DATE('2010-09-17 16:35:53','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:35:56 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51634 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:35:59 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59845,0,51635,50053,0,0,TO_DATE('2010-09-17 16:35:56','YYYY-MM-DD HH24:MI:SS'),100,'T','N','Y','N','N','N','Y','N','Y','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,60,'Beginning Balance','C','F','Beginning Balance',0,50,'N',0,TO_DATE('2010-09-17 16:35:56','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:35:59 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51635 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:36:02 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59846,0,51636,50053,0,0,TO_DATE('2010-09-17 16:35:59','YYYY-MM-DD HH24:MI:SS'),100,'T','N','Y','N','N','N','Y','N','Y','N','N','N','N','N','N','Y','Y','N','N','Y','N','N','X',1,0,30,'Quantity','C','F','Qty',0,60,'N',0,TO_DATE('2010-09-17 16:35:59','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:36:02 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51636 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:36:04 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59847,0,51637,50053,0,0,TO_DATE('2010-09-17 16:36:02','YYYY-MM-DD HH24:MI:SS'),100,'T','N','Y','N','N','N','Y','N','Y','N','N','N','N','N','N','Y','Y','N','N','Y','N','N','X',1,0,60,'Amount','C','F','Amt',0,70,'N',0,TO_DATE('2010-09-17 16:36:02','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:36:04 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51637 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:36:06 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59854,0,51638,50053,0,0,TO_DATE('2010-09-17 16:36:04','YYYY-MM-DD HH24:MI:SS'),100,'T','N','Y','N','N','N','Y','N','Y','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,60,'Cost Adjustment','C','F','Cost Adjustment',0,80,'N',0,TO_DATE('2010-09-17 16:36:04','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:36:06 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51638 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:36:09 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59849,0,51639,50053,0,0,TO_DATE('2010-09-17 16:36:06','YYYY-MM-DD HH24:MI:SS'),100,'T','N','Y','N','N','N','Y','N','Y','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,30,'Ending Qty Balance','C','F','Ending Qty Balance',0,90,'N',0,TO_DATE('2010-09-17 16:36:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:36:09 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51639 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:36:17 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59850,0,51640,50053,0,0,TO_DATE('2010-09-17 16:36:09','YYYY-MM-DD HH24:MI:SS'),100,'T','N','Y','N','N','N','Y','N','Y','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,60,'Ending balance','C','F','Ending balance',0,100,'N',0,TO_DATE('2010-09-17 16:36:09','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:36:17 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51640 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:36:20 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,59800,0,51641,50053,0,0,TO_DATE('2010-09-17 16:36:17','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Movement Type','C','F','Movement Type',0,110,'N',0,TO_DATE('2010-09-17 16:36:17','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 17, 2010 4:36:20 PM CDT
-- Cost Engine Report
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51641 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Sep 17, 2010 4:36:23 PM CDT
-- Cost Engine Report
INSERT INTO AD_Process (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_ReportView_ID,AccessLevel,Created,CreatedBy,Description,EntityType,Help,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,Name,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,Value,WorkflowValue) VALUES (0,0,53224,53033,'3',TO_DATE('2010-09-17 16:36:20','YYYY-MM-DD HH24:MI:SS'),100,'Transaction Valuation','D','This report shows the history  transactions with quantities and cost for each product for any Cost Type, such as Standard, Average, FIFO / LIFO
','Y','N','N','Y','Transaction Valuation','Y',0,0,TO_DATE('2010-09-17 16:36:20','YYYY-MM-DD HH24:MI:SS'),100,'Transaction Valuation',NULL)
;

-- Sep 17, 2010 4:36:23 PM CDT
-- Cost Engine Report
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53224 AND NOT EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_ID=t.AD_Process_ID)
;

-- Sep 17, 2010 4:36:26 PM CDT
-- Cost Engine Report
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,459,0,53224,53445,19,'M_Warehouse_ID',TO_DATE('2010-09-17 16:36:23','YYYY-MM-DD HH24:MI:SS'),100,'Storage Warehouse and Service Point','D',22,'The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','Y','Y','N','N','Warehouse',1,TO_DATE('2010-09-17 16:36:23','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 17, 2010 4:36:26 PM CDT
-- Cost Engine Report
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53445 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Sep 17, 2010 4:36:30 PM CDT
-- Cost Engine Report
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,0,53224,53446,15,'MovementDate',TO_DATE('2010-09-17 16:36:26','YYYY-MM-DD HH24:MI:SS'),100,'Date a product was moved in or out of inventory','D',0,'The Movement Date indicates the date that a product moved in or out of inventory.  This is the result of a shipment, receipt or inventory movement.','Y','Y','N','Y','Movement Date',10,TO_DATE('2010-09-17 16:36:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 17, 2010 4:36:30 PM CDT
-- Cost Engine Report
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53446 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Sep 17, 2010 4:36:33 PM CDT
-- Cost Engine Report
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,454,0,53224,53447,30,'M_Product_ID',TO_DATE('2010-09-17 16:36:30','YYYY-MM-DD HH24:MI:SS'),100,'Product, Service, Item','D',22,'Identifies an item which is either purchased or sold in this organization.','Y','Y','N','N','Product',15,TO_DATE('2010-09-17 16:36:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 17, 2010 4:36:33 PM CDT
-- Cost Engine Report
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53447 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Sep 17, 2010 4:36:36 PM CDT
-- Cost Engine Report
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,0,53224,53448,19,'M_Product_Category_ID',TO_DATE('2010-09-17 16:36:33','YYYY-MM-DD HH24:MI:SS'),100,'-1','Category of a Product','D',0,'Identifies the category which this product belongs to.  Product categories are used for pricing and selection.','Y','Y','N','N','Product Category',20,TO_DATE('2010-09-17 16:36:33','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 17, 2010 4:36:36 PM CDT
-- Cost Engine Report
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53448 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Sep 17, 2010 4:36:36 PM CDT
-- Cost Engine Report
UPDATE AD_Reference SET Description='Table List', EntityType='D', Help=NULL, IsActive='Y', Name='Table', ValidationType='D',Updated=TO_DATE('2010-09-17 16:36:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=18
;

-- Sep 17, 2010 4:36:36 PM CDT
-- Cost Engine Report
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=18
;

-- Sep 17, 2010 4:36:36 PM CDT
-- Cost Engine Report
UPDATE AD_Reference SET Description='Vendor selection', EntityType='D', Help=NULL, IsActive='Y', Name='C_BPartner Vendors Avtive', ValidationType='T',Updated=TO_DATE('2010-09-17 16:36:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=223
;

-- Sep 17, 2010 4:36:36 PM CDT
-- Cost Engine Report
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=223
;

-- Sep 17, 2010 4:36:36 PM CDT
-- Cost Engine Report
UPDATE AD_Ref_Table SET AD_Table_ID = 291, AD_Display = 2902, AD_Key = 2893, isValueDisplayed = 'N', OrderByClause = '', EntityType ='D', WhereClause = 'C_BPartner.IsVendor=''Y'' AND EXISTS (SELECT * FROM M_Product_PO po 
WHERE C_BPartner.C_BPartner_ID=po.C_BPartner_ID)' WHERE AD_Reference_ID = 223
;

-- Sep 17, 2010 4:36:39 PM CDT
-- Cost Engine Report
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Reference_Value_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,0,53224,53449,18,223,'C_BPartner_ID',TO_DATE('2010-09-17 16:36:36','YYYY-MM-DD HH24:MI:SS'),100,'Identifies a Business Partner','D',0,'A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','Y','N','N','Business Partner ',30,TO_DATE('2010-09-17 16:36:36','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 17, 2010 4:36:39 PM CDT
-- Cost Engine Report
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53449 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Sep 17, 2010 4:36:41 PM CDT
-- Cost Engine Report
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,0,53224,53450,10,'Value',TO_DATE('2010-09-17 16:36:39','YYYY-MM-DD HH24:MI:SS'),100,'Search key for the record in the format required - must be unique','D',10,'A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Y','N','Y','Search Key',40,TO_DATE('2010-09-17 16:36:39','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 17, 2010 4:36:41 PM CDT
-- Cost Engine Report
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53450 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Sep 17, 2010 4:36:51 PM CDT
-- Cost Engine Report
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2071,0,53224,53451,19,'M_CostType_ID',TO_DATE('2010-09-17 16:36:41','YYYY-MM-DD HH24:MI:SS'),100,'Type of Cost (e.g. Current, Plan, Future)','D',22,'You can define multiple cost types. A cost type selected in an Accounting Schema is used for accounting.','Y','Y','N','N','Cost Type',50,TO_DATE('2010-09-17 16:36:41','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 17, 2010 4:36:51 PM CDT
-- Cost Engine Report
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53451 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Sep 17, 2010 4:36:54 PM CDT
-- Cost Engine Report
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2700,0,53224,53452,19,'M_CostElement_ID',TO_DATE('2010-09-17 16:36:51','YYYY-MM-DD HH24:MI:SS'),100,'Product Cost Element','D',10,'Y','Y','N','N','Cost Element',60,TO_DATE('2010-09-17 16:36:51','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 17, 2010 4:36:54 PM CDT
-- Cost Engine Report
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53452 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Sep 17, 2010 4:37:00 PM CDT
-- Cost Engine Report
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Process_ID,Action,Created,CreatedBy,Description,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53291,0,53222,'R',TO_DATE('2010-09-17 16:36:54','YYYY-MM-DD HH24:MI:SS'),100,'Valuation Effective Date','D','Y','N','N','N','Valuation Effective Date',TO_DATE('2010-09-17 16:36:54','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 17, 2010 4:37:00 PM CDT
-- Cost Engine Report
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53291 AND NOT EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Menu_ID=t.AD_Menu_ID)
;

-- Sep 17, 2010 4:37:00 PM CDT
-- Cost Engine Report
INSERT INTO AD_TREENODEMM(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, Parent_ID, SeqNo, AD_Tree_ID, Node_ID)VALUES(0, 0, 0, 0, 522,6, 10, 53291)
;

-- Sep 17, 2010 4:37:04 PM CDT
-- Cost Engine Report
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Process_ID,Action,Created,CreatedBy,Description,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53292,0,53223,'P',TO_DATE('2010-09-17 16:37:00','YYYY-MM-DD HH24:MI:SS'),100,'Generate Cost Transaction','D','Y','N','N','N','Generate Cost Transaction',TO_DATE('2010-09-17 16:37:00','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 17, 2010 4:37:04 PM CDT
-- Cost Engine Report
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53292 AND NOT EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Menu_ID=t.AD_Menu_ID)
;

-- Sep 17, 2010 4:37:04 PM CDT
-- Cost Engine Report
INSERT INTO AD_TREENODEMM(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, Parent_ID, SeqNo, AD_Tree_ID, Node_ID)VALUES(0, 0, 0, 0, 522,5, 10, 53292)
;

-- Sep 17, 2010 4:37:07 PM CDT
-- Cost Engine Report
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Process_ID,Action,Created,CreatedBy,Description,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53293,0,53224,'R',TO_DATE('2010-09-17 16:37:04','YYYY-MM-DD HH24:MI:SS'),100,'Transaction Valuation','D','Y','N','N','N','Transaction Valuation',TO_DATE('2010-09-17 16:37:04','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 17, 2010 4:37:07 PM CDT
-- Cost Engine Report
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53293 AND NOT EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Menu_ID=t.AD_Menu_ID)
;

-- Sep 17, 2010 4:37:07 PM CDT
-- Cost Engine Report
INSERT INTO AD_TREENODEMM(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, Parent_ID, SeqNo, AD_Tree_ID, Node_ID)VALUES(0, 0, 0, 0, 522,7, 10, 53293)
;

-- Sep 18, 2010 2:51:41 AM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=51637
;

-- Sep 18, 2010 2:51:41 AM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem SET SeqNo=70,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=51591
;

-- Sep 18, 2010 2:52:26 AM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', YPosition=0, IsPageBreak='N', PrintName='Adjustment', Name='Adjustment',Updated=TO_DATE('2010-09-18 02:52:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_PrintFormatItem_ID=51638
;

-- Sep 18, 2010 2:52:26 AM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=51638
;

-- Sep 18, 2010 2:52:39 AM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', YPosition=0, IsPageBreak='N', PrintName='Ending Balance', Name='Ending Balance',Updated=TO_DATE('2010-09-18 02:52:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_PrintFormatItem_ID=51640
;

-- Sep 18, 2010 2:52:39 AM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=51640
;

-- Sep 18, 2010 3:00:44 AM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', YPosition=0, IsPageBreak='N', IsSummarized='Y',Updated=TO_DATE('2010-09-18 03:00:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_PrintFormatItem_ID=51591
;

-- Sep 18, 2010 3:00:48 AM CDT
-- Cost Engine
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', YPosition=0, IsPageBreak='N', IsSummarized='Y',Updated=TO_DATE('2010-09-18 03:00:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_PrintFormatItem_ID=51638
;

-- Sep 18, 2010 3:04:14 AM CDT
-- Cost Engine
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2010-09-18 03:04:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59622
;

-- Sep 18, 2010 3:04:21 AM CDT
-- Cost Engine
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2010-09-18 03:04:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59452
;

-- Sep 18, 2010 3:04:41 AM CDT
-- Cost Engine
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2010-09-18 03:04:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59632
;


DROP VIEW RV_TRANSACTION;
CREATE OR REPLACE VIEW RV_TRANSACTION
AS 
SELECT t.M_Transaction_ID, t.AD_Client_ID,t.AD_Org_ID,
    t.MovementType,t.MovementDate,t.MovementQty,
    -- Instance
    t.M_AttributeSetInstance_ID, asi.M_AttributeSet_ID, asi.SerNo, asi.Lot, asi.M_Lot_ID,asi.GuaranteeDate,
    -- Product
    t.M_Product_ID,p.Value,p.Name,p.Description,p.UPC,p.SKU,
    p.C_UOM_ID,p.M_Product_Category_ID,p.Classification, p.Weight,p.Volume,p.VersionNo,
    -- Locator
    t.M_Locator_ID, l.M_Warehouse_ID, l.X, l.Y, l.Z,
    -- Inventory
    t.M_InventoryLine_ID,il.M_Inventory_ID,
    -- Movement
    t.M_MovementLine_ID,ml.M_Movement_ID,
    -- In/Out
    t.M_InOutLine_ID,iol.M_InOut_ID,
    -- Production
    t.M_ProductionLine_ID,prdl.M_ProductionPlan_ID,prdp.M_Production_ID,
    -- ProjectIssue
    t.C_ProjectIssue_ID,pjl.C_Project_ID,
    t.PP_Cost_Collector_ID,
    COALESCE(il.Line,ml.Line,iol.Line,prdl.Line,pjl.Line) AS Line,
    COALESCE(i.movementdate, m.movementdate, io.dateacct, prd.movementdate, pjl.movementdate, cc.dateacct) AS dateacct, 
    COALESCE(i.documentno, m.documentno, io.documentno, prd.name, pj.value, cc.documentno) AS documentno,
    COALESCE(i.c_doctype_id, m.c_doctype_id, io.c_doctype_id, 0, 0, cc.c_doctype_id) AS c_doctype_id   
FROM M_Transaction t
  INNER JOIN M_Locator l ON (t.M_Locator_ID=l.M_Locator_ID)
  INNER JOIN M_Product p ON (t.M_Product_ID=p.M_Product_ID)
  LEFT OUTER JOIN M_AttributeSetInstance asi ON (t.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)
  LEFT OUTER JOIN M_InventoryLine il ON (t.M_InventoryLine_ID=il.M_InventoryLine_ID)
  LEFT OUTER JOIN m_inventory i ON (il.m_inventory_id = i.m_inventory_id)
  LEFT OUTER JOIN M_MovementLine ml ON (t.M_MovementLine_ID=ml.M_MovementLine_ID)
  LEFT OUTER JOIN m_movement m ON (ml.m_movement_id = m.m_movement_id)
  LEFT OUTER JOIN M_InOutLine iol ON (t.M_InOutLine_ID=iol.M_InOutLine_ID)
  LEFT OUTER JOIN m_inout io ON (iol.m_inout_id = io.m_inout_id)
  LEFT OUTER JOIN M_ProductionLine prdl ON (t.M_ProductionLine_ID=prdl.M_ProductionLine_ID)
  LEFT OUTER JOIN M_ProductionPlan prdp ON (prdl.M_ProductionPlan_ID=prdp.M_ProductionPlan_ID)
  LEFT OUTER JOIN m_production prd ON prdp.m_production_id = prd.m_production_id
  LEFT OUTER JOIN C_ProjectIssue pjl ON (t.C_ProjectIssue_ID=pjl.C_ProjectIssue_ID)
  LEFT OUTER JOIN c_project pj ON pjl.c_project_id = pj.c_project_id
  LEFT OUTER JOIN pp_cost_collector cc ON t.pp_cost_collector_id = cc.pp_cost_collector_id;
  
CREATE OR REPLACE VIEW RV_M_Transaction_Costing AS
SELECT 
t.M_Transaction_ID, t.AD_Client_ID,t.AD_Org_ID,
t.MovementType,t.MovementDate,t.MovementQty,
t.M_AttributeSetInstance_ID, t.M_AttributeSet_ID, t.SerNo, t.Lot, t.M_Lot_ID, t.GuaranteeDate,
p.M_Product_ID,p.Value,p.Name,p.Description,p.UPC,p.SKU,
p.C_UOM_ID,p.M_Product_Category_ID,p.Classification, p.Group1, p.Group2, p.Weight,p.Volume,p.VersionNo,
t.DocumentNo,
t.C_DocType_ID,
t.M_Locator_ID, 
t.X, t.Y, t.Z,
t.M_Warehouse_ID,
t.M_InventoryLine_ID,t.M_Inventory_ID,
t.M_MovementLine_ID,t.M_Movement_ID,
t.M_InOutLine_ID,t.M_InOut_ID,
t.M_ProductionLine_ID,t.M_ProductionPlan_ID,t.M_Production_ID,
t.C_ProjectIssue_ID,t.C_Project_ID,
t.PP_Cost_Collector_ID,
cd.C_AcctSchema_ID,
cd.M_CostType_ID,
cd.M_CostElement_ID,
cd.CostAdjustment,	
cd.CostAdjustmentDate,
cd.DateAcct,
cd.CumulatedQty AS BeginningQtyBalance,
cd.CurrentCostPrice,
cd.CurrentCostPriceLL,
cd.isReversal,
cd.IsSOTrx,
cd.M_CostDetail_ID,
cd.CumulatedAmt AS BeginningBalance,
cd.qty,
cd.Amt, 
cd.CostAmt,
cd.CumulatedQty + Qty AS EndingQtyBalance,
cd.CumulatedAmt  + CostAmt + CostAdjustment AS EndingBalance
FROM M_Product p 
INNER JOIN RV_Transaction t ON (t.M_Product_ID=p.M_Product_ID)
LEFT OUTER JOIN M_CostDetail cd ON (cd.M_Transaction_ID=t.M_Transaction_ID AND cd.M_Product_ID=p.M_Product_ID) 
LEFT OUTER JOIN M_CostType ct ON (ct.M_CostType_ID=cd.M_CostType_ID)
LEFT OUTER JOIN M_CostElement ce ON (ce.M_CostElement_ID=cd.M_CostElement_ID);

-- 27/09/2010 07:41:10 PM CDT
-- Cost Engine Report
UPDATE AD_PrintFormatItem SET SortNo=0,IsOrderBy='N' WHERE AD_PrintFormatItem_ID=51629
;

-- 27/09/2010 07:41:10 PM CDT
-- Cost Engine Report
UPDATE AD_PrintFormatItem SET SortNo=20,IsOrderBy='Y' WHERE AD_PrintFormatItem_ID=51590
;

-- 27/09/2010 07:41:10 PM CDT
-- Cost Engine Report
UPDATE AD_PrintFormatItem SET SortNo=30,IsOrderBy='Y' WHERE AD_PrintFormatItem_ID=51631
;

-- 27/09/2010 07:41:10 PM CDT
-- Cost Engine Report
UPDATE AD_PrintFormatItem SET SortNo=40,IsOrderBy='Y' WHERE AD_PrintFormatItem_ID=51633
;

-- 27/09/2010 07:41:10 PM CDT
-- Cost Engine Report
UPDATE AD_PrintFormatItem SET SortNo=50,IsOrderBy='Y' WHERE AD_PrintFormatItem_ID=51589
;