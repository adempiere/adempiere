-- Jan 7, 2009 9:01:23 PM ECT
-- Manufacturing Cost
UPDATE AD_Element SET Description='Current Price Lower Level Is the sum of the costs of the components of this product manufactured for this level.', Help='Current Price Lower Level is used for get the total costs for lower level the a product manufactured.

The Current Price Lower Level always will be calculated.

You can see the Current Cost Price and Current Cost Price Lower Level with Cost  Bill of Material & Formula Detail Report.
 
The sum the Current Cost Price + Current Cost Price Lower Level is the total cost to a product manufactured.
',Updated=TO_TIMESTAMP('2009-01-07 21:01:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53296
;

-- Jan 7, 2009 9:01:23 PM ECT
-- Manufacturing Cost
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53296
;

-- Jan 7, 2009 9:01:23 PM ECT
-- Manufacturing Cost
UPDATE AD_Column SET ColumnName='CurrentCostPriceLL', Name='Current Cost Price Lower Level', Description='Current Price Lower Level Is the sum of the costs of the components of this product manufactured for this level.', Help='Current Price Lower Level is used for get the total costs for lower level the a product manufactured.

The Current Price Lower Level always will be calculated.

You can see the Current Cost Price and Current Cost Price Lower Level with Cost  Bill of Material & Formula Detail Report.
 
The sum the Current Cost Price + Current Cost Price Lower Level is the total cost to a product manufactured.
' WHERE AD_Element_ID=53296
;

-- Jan 7, 2009 9:01:23 PM ECT
-- Manufacturing Cost
UPDATE AD_Process_Para SET ColumnName='CurrentCostPriceLL', Name='Current Cost Price Lower Level', Description='Current Price Lower Level Is the sum of the costs of the components of this product manufactured for this level.', Help='Current Price Lower Level is used for get the total costs for lower level the a product manufactured.

The Current Price Lower Level always will be calculated.

You can see the Current Cost Price and Current Cost Price Lower Level with Cost  Bill of Material & Formula Detail Report.
 
The sum the Current Cost Price + Current Cost Price Lower Level is the total cost to a product manufactured.
', AD_Element_ID=53296 WHERE UPPER(ColumnName)='CURRENTCOSTPRICELL' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Jan 7, 2009 9:01:23 PM ECT
-- Manufacturing Cost
UPDATE AD_Process_Para SET ColumnName='CurrentCostPriceLL', Name='Current Cost Price Lower Level', Description='Current Price Lower Level Is the sum of the costs of the components of this product manufactured for this level.', Help='Current Price Lower Level is used for get the total costs for lower level the a product manufactured.

The Current Price Lower Level always will be calculated.

You can see the Current Cost Price and Current Cost Price Lower Level with Cost  Bill of Material & Formula Detail Report.
 
The sum the Current Cost Price + Current Cost Price Lower Level is the total cost to a product manufactured.
' WHERE AD_Element_ID=53296 AND IsCentrallyMaintained='Y'
;

-- Jan 7, 2009 9:01:23 PM ECT
-- Manufacturing Cost
UPDATE AD_Field SET Name='Current Cost Price Lower Level', Description='Current Price Lower Level Is the sum of the costs of the components of this product manufactured for this level.', Help='Current Price Lower Level is used for get the total costs for lower level the a product manufactured.

The Current Price Lower Level always will be calculated.

You can see the Current Cost Price and Current Cost Price Lower Level with Cost  Bill of Material & Formula Detail Report.
 
The sum the Current Cost Price + Current Cost Price Lower Level is the total cost to a product manufactured.
' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53296) AND IsCentrallyMaintained='Y'
;

