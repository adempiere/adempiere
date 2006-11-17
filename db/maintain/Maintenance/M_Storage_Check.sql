/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html 
 * Software is on an "AS IS" basis,   WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: M_Storage_Check.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 * $Source: /cvs/adempiere/db/maintain/Maintenance/M_Storage_Check.sql,v $
 ***
 * Title:	Storage Check for Quantity on Hand
 * Description:
 * - Check that Sum of MovementQty in Transaction Lines is same as 
 *   QtyOnHand in M_Storage
 * - Suggestions on how to fix it
 ************************************************************************/

--	Create Temporary Table
CREATE TABLE TEMP_StorageCheck AS
SELECT 	M_Locator_ID, M_Product_ID, SUM(MovementQty) AS Trx, 0 AS Info
FROM 	M_Transaction
GROUP BY M_Locator_ID, M_Product_ID
/

-- No Transactions for Storage
SELECT 	M_Locator_ID, M_Product_ID, QtyOnHand "No Trx for Info"
FROM 	M_Storage s
WHERE QtyOnHand <> 0
  AND NOT EXISTS 
	(SELECT * FROM TEMP_StorageCheck c 
	WHERE s.M_Locator_ID=c.M_Locator_ID AND s.M_Product_ID=c.M_Product_ID)
/
--	No Storage Records for Transaction
SELECT 	M_Locator_ID, M_Product_ID, Trx "No Info"
FROM 	TEMP_StorageCheck c
WHERE Trx <> 0
  AND NOT EXISTS 
	(SELECT * FROM M_Storage s 
	WHERE s.M_Locator_ID=c.M_Locator_ID AND s.M_Product_ID=c.M_Product_ID)
/

UPDATE 	TEMP_StorageCheck c
  SET	Info = (SELECT QtyOnHand FROM M_Storage s 
  					WHERE s.M_Locator_ID=c.M_Locator_ID AND s.M_Product_ID=c.M_Product_ID)
WHERE EXISTS (SELECT QtyOnHand FROM M_Storage s 
 			 		WHERE s.M_Locator_ID=c.M_Locator_ID AND s.M_Product_ID=c.M_Product_ID)
/
DELETE	TEMP_StorageCheck 
WHERE	Info = Trx
/

SELECT 	
		l.Value, p.Name, l.M_Locator_ID, p.M_Product_ID,
		c.Info, c.Trx, c.Trx-c.Info "Differenz"
FROM 	TEMP_StorageCheck c, M_Locator l, M_Product p
WHERE 	c.M_Locator_ID=l.M_Locator_ID AND c.M_Product_ID=p.M_Product_ID
ORDER BY l.Value, p.Name
/

DROP TABLE TEMP_StorageCheck;

------ Examples to fix
--update m_transaction set movementqty=0
--where m_transaction_id=1000767 and movementqty=4;

--Update m_inOutLine set movementqty=4
--where m_InOutLine_ID=1000470 and movementqty=40;

--Update m_inventoryline set qtycount=0
--where m_Inventoryline_ID=1000551 and qtycount=4;

--commit;


