-- Aug 29, 2009 3:42:18 PM EEST
-- BF[2846831] - List only active UOM Conversions
UPDATE AD_Val_Rule SET Code='(
EXISTS (
	/* UOM is a default UOM and no product selected */
	SELECT * 
	FROM C_UOM uu 
	WHERE C_UOM.C_UOM_ID=uu.C_UOM_ID AND IsActive =''Y'' AND IsDefault=''Y'' AND @M_Product_ID@=0
)
OR EXISTS (
	/* UOM is the products UOM */
	SELECT * 
	FROM M_Product p 
	WHERE C_UOM.C_UOM_ID=p.C_UOM_ID AND @M_Product_ID@=p.M_Product_ID
)
OR EXISTS (
	/* For the products UOM there is a conversion that is explicitly bound to the product */
	SELECT * 
	FROM M_Product p INNER JOIN C_UOM_Conversion c ON (p.C_UOM_ID=c.C_UOM_ID AND p.M_PRODUCT_ID=c.M_Product_ID AND c.IsActive =''Y'' )
	WHERE C_UOM.C_UOM_ID=c.C_UOM_TO_ID AND @M_Product_ID@=p.M_Product_ID
)
OR EXISTS (
	/* For the products UOM there is a conversion that is not bound to any product explicitly */
	SELECT * 
	FROM M_Product p INNER JOIN C_UOM_Conversion c ON (p.C_UOM_ID=c.C_UOM_ID AND c.M_Product_ID IS NULL AND c.IsActive =''Y'' )
	WHERE C_UOM.C_UOM_ID=c.C_UOM_TO_ID AND @M_Product_ID@=p.M_Product_ID
))',Updated=TO_TIMESTAMP('2009-08-29 15:42:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Val_Rule_ID=210
;

