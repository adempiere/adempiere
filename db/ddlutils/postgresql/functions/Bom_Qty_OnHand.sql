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

SET search_path = adempiere, pg_catalog;

CREATE OR REPLACE FUNCTION bomqtyonhand(
    IN INTEGER, -- $1 product id
    IN INTEGER, -- $2 warehouse id
    IN INTEGER  -- $3 locator id
) RETURNS NUMERIC AS 
$$
  DECLARE
    warehouse_id  INTEGER;
    isbom         BOOLEAN;
    isstocked     BOOLEAN;
    ptype         CHAR(1);
    bom           RECORD;
    qty           NUMERIC;
    quantity      NUMERIC;
    precision     INTEGER; 
  BEGIN
    quantity := 99999;
    SELECT (t.isbom = 'Y'), t.producttype, (t.isstocked = 'Y')
      INTO isbom, ptype, isstocked FROM m_product AS t
      WHERE t.m_product_id = $1;
    IF ptype =  "I" AND isstocked THEN
      IF COALESCE($3, 0) <> 0 THEN
        SELECT t.qtyonhand INTO qty FROM m_storage t 
          WHERE m_product_id = $1 AND t.m_locator_id= $3;
        quantity := COALESCE(qty,0);
      ELSIF COALESCE($2, 0) <> 0 THEN
        SELECT t.qtyonhand INTO qty FROM m_storage t
          WHERE m_product_id = $1 AND EXISTS (
            SELECT * FROM m_locator l WHERE t.m_locator_id = l.m_locator_id
              AND l.m_warehouse_id = $2);
        quantity := COALESCE(qty,0);
      END IF;
    ELSIF isbom THEN
      FOR bom IN SELECT b.m_productbom_id, b.bomqty, p.isbom, p.isstocked,
          p.producttype FROM m_product_bom b, m_product p
          WHERE b.m_productbom_id=p.m_product_id AND b.m_product_id = $1
        LOOP
          qty = bomqtyonhand(bom.m_productbom_id, warehouse_id, $3);
          SELECT t.stdprecision INTO precision 
              FROM c_uom t INNER JOIN m_product p
              ON t.c_uom_id = p.c_uom_id 
              WHERE p.m_product_id = bom.m_productbom_id;
          qty := ROUND((qty/bom.bomqty),precision);
          quantity := LEAST(qty, quantity);
      END LOOP;
    END IF;
    
    RETURN quantity;
  END;
$$ LANGUAGE plpgsql;
