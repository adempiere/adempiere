CREATE OR REPLACE FUNCTION BomqtyavailableASI
(
	Product_ID 		IN NUMBER,
	AttributeSetInstance_ID  IN NUMBER,
    Warehouse_ID	IN NUMBER,
	Locator_ID		IN NUMBER	--	Only used, if warehouse is null
)
RETURN NUMBER
/******************************************************************************
 * ** Compiere Product **             Copyright (c) 1999-2001 Accorto, Inc. USA
 * Open  Source  Software        Provided "AS IS" without warranty or liability
 * When you use any parts (changed or unchanged), add  "Powered by Compiere" to
 * your product name;  See license details http://www.compiere.org/license.html
 ******************************************************************************
 *	Return quantity available for BOM ASI
 */
AS
BEGIN
	RETURN BomqtyonhandASI(Product_ID, AttributeSetInstance_ID, Warehouse_ID, Locator_ID)
		- BomqtyreservedASI(Product_ID, AttributeSetInstance_ID, Warehouse_ID, Locator_ID);
END BomqtyavailableASI;
/
