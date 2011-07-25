 /**********************************************************************
 * This file is part of ADempiere Business Suite                       *
 * http://www.adempiere.org                                            *
 *                                                                     *
 * Copyright (C) Trifon Trifonov.                                      *
 * Copyright (C) Contributors                                          *
 *                                                                     *
 * This program is free software; you can redistribute it and/or       *
 * modify it under the terms of the GNU General Public License         *
 * as published by the Free Software Foundation; either version 2      *
 * of the License, or (at your option) any later version.              *
 *                                                                     *
 * This program is distributed in the hope that it will be useful,     *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of      *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the        *
 * GNU General Public License for more details.                        *
 *                                                                     *
 * You should have received a copy of the GNU General Public License   *
 * along with this program; if not, write to the Free Software         *
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,          *
 * MA 02110-1301, USA.                                                 *
 *                                                                     *
 * Contributors:                                                       *
 * - Trifon Trifonov (trifonnt@users.sourceforge.net)                  *
 *                                                                     *
 ***********************************************************************
 * 
 * Title.........: 
 * Description...: Return Instance Attribute Info 
 * Test..........: SELECT productAttribute(M_AttributeSetInstance_ID) FROM M_InOutLine WHERE M_AttributeSetInstance_ID > 0 => Empty set (0.00 sec)
 *                 SELECT productAttribute(100) => NULL
 *                 SELECT productAttribute(101) => NULL
 *                 SELECT productAttribute(102) => NULL
 *                  M_Product_ID |                 Name | M_attributeSet_ID | M_AttributesetInstance_ID |
 *                           147 | TShirt - Red Large   |               100 |                       101 |
 *                           148 | TShirt - Green Large |               100 |                       102 |
 *                           136 | Fertilizer #50       |               101 |                         0 |
 *                           133 | Patio Chair          |               102 |                       100 |
 *                 --
 *                 SELECT p.Name
 *                 FROM C_InvoiceLine il 
 *                 LEFT OUTER JOIN M_Product p ON (il.M_Product_ID=p.M_Product_ID);
 * 
 *                 SELECT p.Name || productAttribute(il.M_AttributeSetInstance_ID) 
 *                 FROM C_InvoiceLine il LEFT OUTER JOIN M_Product p ON (il.M_Product_ID=p.M_Product_ID);
 * 
 * Converted to MySQL..: by Trifon Trifonov
 ************************************************************************/

-- ## Drop statement
DROP FUNCTION IF EXISTS productAttribute;

DELIMITER $$
-- ## Create statement
CREATE FUNCTION productAttribute(
  p_M_AttributeSetInstance_ID DECIMAL(10, 0)
)
	RETURNS VARCHAR(255)
 	DETERMINISTIC
BEGIN
  DECLARE v_Name          VARCHAR(2000) DEFAULT NULL;
  DECLARE v_NameAdd       VARCHAR(2000) DEFAULT '';
  --
  DECLARE v_Lot           VARCHAR(40); /*M_AttributeSetInstance.Lot%TYPE;*/
  DECLARE v_SerNo         VARCHAR(40); /*M_AttributeSetInstance.SerNo%TYPE;*/
  DECLARE v_GuaranteeDate DateTime;    /*M_AttributeSetInstance.GuaranteeDate%TYPE;*/
  
  DECLARE v_LotStart      CHAR(1); /*M_AttributeSet.LotCharSOverwrite%TYPE;*/
  DECLARE v_LotEnd        CHAR(1); /*M_AttributeSet.LotCharEOverwrite%TYPE;*/
  DECLARE v_SerNoStart    CHAR(1); /*M_AttributeSet.SerNoCharSOverwrite%TYPE;*/
  DECLARE v_SerNoEnd      CHAR(1); /*M_AttributeSet.SerNoCharEOverwrite%TYPE;*/
  
  -- Declare '_val' variables to read in each record from the cursor
  DECLARE cur_Name VARCHAR(60);
  DECLARE cur_Value VARCHAR(40);
  -- Declare variables used just for cursor and loop control
  DECLARE done BOOL DEFAULT FALSE;
  -- Declare the cursor
  DECLARE cur_Attributes CURSOR FOR
      SELECT ai.Value, a.Name
      FROM M_AttributeInstance ai
      INNER JOIN M_Attribute a ON (ai.M_Attribute_ID=a.M_Attribute_ID AND a.IsInstanceAttribute='Y')
      WHERE ai.M_AttributeSetInstance_ID=p_M_AttributeSetInstance_ID;
        
  -- Declare 'handlers' for exceptions
  DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = TRUE;

  -- Get Product Attribute Set Instance
  IF (p_M_AttributeSetInstance_ID > 0) THEN
      SELECT asi.Lot, asi.SerNo, asi.GuaranteeDate,
          COALESCE(a.SerNoCharSOverwrite, '#'), COALESCE(a.SerNoCharEOverwrite, ''),
          COALESCE(a.LotCharSOverwrite, '�'), COALESCE(a.LotCharEOverwrite, '�')
        INTO v_Lot, v_SerNo, v_GuaranteeDate,
          v_SerNoStart, v_SerNoEnd, v_LotStart, v_LotEnd
      FROM M_AttributeSetInstance asi
      INNER JOIN M_AttributeSet a ON (asi.M_AttributeSet_ID=a.M_AttributeSet_ID)
      WHERE asi.M_AttributeSetInstance_ID=p_M_AttributeSetInstance_ID;
      --
      IF (v_SerNo IS NOT NULL) THEN
          SET v_NameAdd = v_NameAdd || v_SerNoStart || v_SerNo || v_SerNoEnd || ' ';
      END IF;
      IF (v_Lot IS NOT NULL) THEN
          SET v_NameAdd = v_NameAdd || v_LotStart || v_Lot || v_LotEnd || ' ';
      END IF;
      IF (v_GuaranteeDate IS NOT NULL) THEN
          SET v_NameAdd = v_NameAdd || v_GuaranteeDate || ' ';
      END IF;
      --
      OPEN cur_Attributes;
      myLoop: LOOP
        FETCH cur_Attributes INTO cur_Value, cur_Name;

        IF done THEN
          CLOSE cur_Attributes;
          LEAVE myLoop;
        END IF;

        SET v_NameAdd = v_NameAdd || cur_Name || ':' || cur_Value || ' ';
      END LOOP myLoop;
      --
      IF (LENGTH(v_NameAdd) > 0) THEN
          SET v_Name = v_Name || ' (' || TRIM(v_NameAdd) || ')';
      END IF;
  END IF;
    
  RETURN v_Name;
END$$
DELIMITER ;