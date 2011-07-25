/************************************************************************
 * Function IsShippable - Return Y or N depending if this is a physical
 * 'shippable' product or not.
 * Author: Daniel Tamm (usrdno)
************************************************************************/
CREATE OR REPLACE FUNCTION isshippable(product_id numeric)
  RETURNS character AS
$BODY$
DECLARE
	v_IsStocked	character(1);
	v_IsBom		character(1);
	v_ProductType	character(1);
	v_return	character(1);
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
END;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
