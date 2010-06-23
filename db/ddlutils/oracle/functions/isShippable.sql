/************************************************************************
 * Function IsShippable - Return Y or N depending if this is a physical
 * 'shippable' product or not.
 * Author: Daniel Tamm (usrdno)
************************************************************************/
CREATE OR REPLACE FUNCTION isshippable
(
	product_id IN NUMBER
)
 RETURN CHAR AS
	v_IsStocked	CHAR;
	v_IsBom		CHAR;
	v_ProductType	CHAR;
	v_return	CHAR;
BEGIN
	IF product_id = NULL THEN
		return 'N';
	END IF;
	
	SELECT IsStocked, IsBom, ProductType 
	INTO v_IsStocked, v_IsBom, v_ProductType
	FROM M_Product WHERE M_Product_ID=product_id;

	IF (v_IsStocked='Y' AND v_ProductType='I' AND v_IsBom='N') THEN
		v_return := 'Y';
	ELSE
		v_return := 'N';
	END IF;
	
	return v_return;	
END isshippable;
/
