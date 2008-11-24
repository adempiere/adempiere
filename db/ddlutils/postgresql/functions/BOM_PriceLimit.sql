/*
 *This file is part of Adempiere ERP Bazaar
 *http://www.adempiere.org
 *
 *Copyright (C) 2006-2008 Antonio Cañaveral, e-Evolution
 *
 *This program is free software; you can redistribute it and/or
 *modify it under the terms of the GNU General Public License
 *as published by the Free Software Foundation; either version 2
 *of the License, or (at your option) any later version.
 *
 *This program is distributed in the hope that it will be useful,
 *but WITHOUT ANY WARRANTY; without even the implied warranty of
 *MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *GNU General Public License for more details.
 *
 *You should have received a copy of the GNU General Public License
 *along with this program; if not, write to the Free Software
 *Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.of 
 */
CREATE OR REPLACE FUNCTION bompricelimit(p_product_id numeric, p_pricelist_version_id numeric)
  RETURNS numeric AS
$BODY$
DECLARE
	v_Price		numeric;
	v_ProductPrice	numeric;
	bom		record;
BEGIN
	--	Try to get price from PriceList directly
	SELECT	COALESCE (SUM(PriceLimit), 0)
        INTO	v_Price
   	FROM	M_PRODUCTPRICE
	WHERE M_PriceList_Version_ID=p_PriceList_Version_ID AND M_Product_ID=p_Product_ID;
	IF (v_Price = 0) THEN
		FOR bom in SELECT bl.M_Product_ID AS M_ProductBOM_ID, 
			CASE WHEN bl.IsQtyPercentage = 'N' THEN bl.QtyBOM ELSE bl.QtyBatch / 100 END AS BomQty , p.IsBOM 
		FROM PP_PRODUCT_BOM b
		INNER JOIN M_PRODUCT p ON (p.M_Product_ID=b.M_Product_ID)
		INNER JOIN PP_PRODUCT_BOMLINE bl ON (bl.PP_Product_BOM_ID=b.PP_Product_BOM_ID)
		WHERE b.M_Product_ID = p_Product_ID
		LOOP
			v_ProductPrice := Bompricelimit (bom.M_ProductBOM_ID, p_PriceList_Version_ID);
			v_Price := v_Price + (bom.BOMQty * v_ProductPrice);
		END LOOP;
	END IF;
	--
	RETURN v_Price;
END;
$BODY$
  LANGUAGE 'plpgsql' ;
