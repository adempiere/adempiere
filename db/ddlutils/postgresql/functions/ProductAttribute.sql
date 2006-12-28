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

CREATE OR REPLACE FUNCTION productattribute (
    IN INTEGER -- $1 m_attributesetinstance_id
) RETURNS VARCHAR AS 
$$
  DECLARE
    lot         VARCHAR;
    serno       VARCHAR;
    gdate       TIMESTAMP WITH TIME ZONE;
    result      VARCHAR;
    r           RECORD;
    
  BEGIN
    IF COALESCE($1,0) = 0 THEN
      RETURN '';
    END IF;
    SELECT t.lot, t.serno, t.guaranteedate
      INTO lot, serno, gdate
      FROM m_attributesetinstance t
      WHERE t.m_attributesetinstance_id = $1;
    result := '';
    IF lot IS NOT NULL THEN
      result := result || lot || ' ';
    END IF;
    IF serno IS NOT NULL THEN
      result := result || '#' || serno || ' ';
    END IF;
    IF gdate IS NOT NULL THEN
      result := result || date_trunc('minute', gdate) || ' ';
    END IF;
    FOR r IN SELECT ai.value, a.name FROM m_attributeinstance ai
      INNER JOIN m_attribute a ON ai.m_attribute_id = a.m_attribute_id
      WHERE a.IsIstanceAttribute = 'Y' AND ai.m_attributesetinstance_id = $1
    LOOP
      result := result || r.value;
      result := result || ':' || r.name || ' ';
    END LOOP;
    
    IF CHAR_LENGTH(result) > 0 THEN
      result := '"' || result || '"';
    END IF;
    RETURN result;
  END;
$$ LANGUAGE plpgsql;
