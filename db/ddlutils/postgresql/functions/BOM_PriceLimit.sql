/*
 *This file is part of Adempiere ERP Bazaar
 *http://www.adempiere.org
 *
 *Copyright (C) 2006 Timo Kontro
 *Copyright (C) 1999-2006 ComPiere, inc
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

/*
 * Loops recursively through BOM and returns BOM's total pricelimit.
 */
CREATE OR REPLACE FUNCTION adempiere.bompricelimit(
    IN NUMERIC, -- $1 product id
    IN NUMERIC  -- $2 pricelist version id
) RETURNS NUMERIC AS 
$$
  DECLARE
    price        NUMERIC;
    productprice NUMERIC;
    boms         RECORD;
  BEGIN
    SELECT COALESCE(t.PriceLimit, 0) INTO price FROM m_productprice as t
      WHERE t.m_pricelist_version_id = $2 AND t.m_product_id = $1;
    IF price = 0 THEN
      FOR boms IN SELECT t.m_productbom_id, t.bomqty 
          FROM m_product_bom as t, m_product as p
          WHERE t.m_productbom_id = p.m_product_id
          AND t.m_product_id = $1 LOOP
        productprice := bompricelimit(boms.m_productbom_id, $2);
        price := price + (boms.bomqty * productprice);
      END LOOP;
    END IF;
    return price;
  END;
$$ LANGUAGE plpgsql;
