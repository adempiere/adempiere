/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html 
 * Software is on an "AS IS" basis,   WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: M_Storage_CheckReserved.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 * $Source: /cvs/adempiere/db/maintain/Maintenance/M_Storage_CheckReserved.sql,v $
 ***
 * Title:	Storage Check for reserved ore ordered Quantity
 * Description:
 * - Check that Sum of Reserved Order Lines is same as 
 *   QtyReserved/QtyOrdered in M_Storage
 * - Optionally to fix it
 * Attention:
 * >> Product may have multiple Locations
 ************************************************************************/

-- Temp Table
CREATE TABLE TEMP_StorageCheck_Res
(
M_Product_ID 	NUMBER(10), 
M_Locator_ID	NUMBER(10),
M_Warehouse_ID	NUMBER(10),
QtyReserved		NUMBER,
QtyResOrder		NUMBER,
QtyOrdered		NUMBER,
QtyOrdOrder		NUMBER
)
/

-- Sales Side
INSERT INTO TEMP_StorageCheck_Res
(M_Product_ID, M_Locator_ID, M_Warehouse_ID, QtyReserved, QtyResOrder)
SELECT s.M_Product_ID, s.M_Locator_ID, l.M_Warehouse_ID, s.QtyReserved, ol.QtyRes
FROM M_Locator l, M_Storage s, (
SELECT M_Product_ID, M_Warehouse_ID, SUM(QtyReserved) AS QtyRes FROM C_OrderLine ol
WHERE EXISTS (SELECT * FROM C_Order o, C_DocType dt WHERE o.C_Order_ID=ol.C_Order_ID
	AND o.C_DocType_ID=dt.C_DocType_ID AND dt.IsSOTrx='Y')
GROUP BY M_Product_ID, M_Warehouse_ID
) ol
WHERE l.M_Locator_ID=s.M_Locator_ID
AND s.M_Product_ID=ol.M_Product_ID
AND l.M_Warehouse_ID=ol.M_Warehouse_ID
AND s.QtyReserved <> ol.QtyRes
/
-- Purchase Side
INSERT INTO TEMP_StorageCheck_Res
(M_Product_ID, M_Locator_ID, M_Warehouse_ID, QtyOrdered, QtyOrdOrder)
SELECT s.M_Product_ID, s.M_Locator_ID, l.M_Warehouse_ID, s.QtyOrdered, ol.QtyOrd
FROM M_Locator l, M_Storage s, (
SELECT M_Product_ID, M_Warehouse_ID, SUM(QtyReserved) AS QtyOrd FROM C_OrderLine ol
WHERE EXISTS (SELECT * FROM C_Order o, C_DocType dt WHERE o.C_Order_ID=ol.C_Order_ID
	AND o.C_DocType_ID=dt.C_DocType_ID AND dt.IsSOTrx='N')
GROUP BY M_Product_ID, M_Warehouse_ID
) ol
WHERE l.M_Locator_ID=s.M_Locator_ID
AND s.M_Product_ID=ol.M_Product_ID
AND l.M_Warehouse_ID=ol.M_Warehouse_ID
AND s.QtyOrdered <> ol.QtyOrd
/

------------
-- List info
SELECT *
FROM TEMP_StorageCheck_Res
ORDER BY M_Product_ID, M_Warehouse_ID, M_Locator_ID
/

---------
-- Fix it
UPDATE M_Storage s
SET QtyReserved = 
	(SELECT QtyResOrder FROM TEMP_StorageCheck_Res t
	WHERE s.M_Product_ID=t.M_Product_ID AND s.M_Locator_ID=t.M_Locator_ID)
WHERE EXISTS
	(SELECT * FROM TEMP_StorageCheck_Res t
	WHERE s.M_Product_ID=t.M_Product_ID AND s.M_Locator_ID=t.M_Locator_ID
	AND s.QtyReserved<>t.QtyResOrder)
/
UPDATE M_Storage s
SET QtyOrdered = 
	(SELECT QtyOrdOrder FROM TEMP_StorageCheck_Res t
	WHERE s.M_Product_ID=t.M_Product_ID AND s.M_Locator_ID=t.M_Locator_ID)
WHERE EXISTS
	(SELECT * FROM TEMP_StorageCheck_Res t
	WHERE s.M_Product_ID=t.M_Product_ID AND s.M_Locator_ID=t.M_Locator_ID
	AND s.QtyOrdered<>t.QtyOrdOrder)
/
----------
ROLLBACK
--COMMIT
/

-- Drop Table
DROP TABLE TEMP_StorageCheck_Res
/
