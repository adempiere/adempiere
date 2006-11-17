/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: Temporary.sql,v 1.1 2006/04/21 17:51:59 jjanke Exp $
 ***
 * Title:	Temporary Tables
 * Description:	
 ************************************************************************/

DROP TABLE T_Selection CASCADE CONSTRAINTS
/
--	Truely temporary table
CREATE GLOBAL TEMPORARY TABLE T_Selection	
(
	T_Selection_ID	NUMBER(10, 0) NOT NULL	
		CONSTRAINT T_Selection_Key PRIMARY KEY
)
ON COMMIT DELETE ROWS
/


DROP TABLE T_Selection2
/
--	Temporary table over commit
CREATE GLOBAL TEMPORARY TABLE T_Selection2 
(
	Query_ID	   NUMBER	  NOT NULL,
	T_Selection_ID NUMBER(10) NOT NULL,
	CONSTRAINT T_Selection2_Key PRIMARY KEY (Query_ID,T_Selection_ID)
)
ON COMMIT PRESERVE ROWS 
/


/**
 *	Spool Table
 */
DROP SEQUENCE T_Spool_Seq
/
CREATE SEQUENCE T_Spool_Seq
	INCREMENT BY 1
	START WITH	 1
/
-- INSERT INTO T_Spool (AD_PInstance_ID, SeqNo, Msg) VALUES (123, T_Spool_Seq.NextVal, 'ggg');

DROP TABLE T_InventoryValue
/
CREATE TABLE T_InventoryValue(
    AD_PInstance_ID              NUMBER(10, 0)    NOT NULL,
    M_Warehouse_ID               NUMBER(10, 0)    NOT NULL,
    M_Product_ID                 NUMBER(10, 0)    NOT NULL,
    M_AttributeSetInstance_ID    NUMBER(10, 0)    NOT NULL,
    AD_Client_ID                 NUMBER(10, 0),
    AD_Org_ID                    NUMBER(10, 0),
    M_PriceList_Version_ID       NUMBER(10, 0),
    DateValue                    DATE,
    C_Currency_ID                NUMBER(10, 0),
    QtyOnHand                    NUMBER           DEFAULT 0,
    PricePO                      NUMBER           DEFAULT 0,
    PriceList                    NUMBER           DEFAULT 0,
    PriceStd                     NUMBER           DEFAULT 0,
    PriceLimit                   NUMBER           DEFAULT 0,
    CostStandard                 NUMBER           DEFAULT 0,
    Cost                         NUMBER           DEFAULT 0,
    PricePOAmt                   NUMBER           DEFAULT 0,
    PriceListAmt                 NUMBER           DEFAULT 0,
    PriceStdAmt                  NUMBER           DEFAULT 0,
    PriceLimitAmt                NUMBER           DEFAULT 0,
    CostStandardAmt              NUMBER           DEFAULT 0,
    CostAmt                      NUMBER           DEFAULT 0,
    M_CostElement_ID             NUMBER(10, 0),
    CONSTRAINT T_InventoryValue_Key PRIMARY KEY (AD_PInstance_ID, M_Warehouse_ID, M_Product_ID, M_AttributeSetInstance_ID), 
    CONSTRAINT MPLVersion_TInventoryValue FOREIGN KEY (M_PriceList_Version_ID)
    REFERENCES M_PriceList_Version(M_PriceList_Version_ID),
    CONSTRAINT CCurrency_TInventoryValue FOREIGN KEY (C_Currency_ID)
    REFERENCES C_Currency(C_Currency_ID),
    CONSTRAINT MWarehouse_TInventoryValue FOREIGN KEY (M_Warehouse_ID)
    REFERENCES M_Warehouse(M_Warehouse_ID) ON DELETE CASCADE,
    CONSTRAINT MProduct_TInventoryValue FOREIGN KEY (M_Product_ID)
    REFERENCES M_Product(M_Product_ID) ON DELETE CASCADE,
    CONSTRAINT ADPInstance_TInventoryValue FOREIGN KEY (AD_PInstance_ID)
    REFERENCES AD_PInstance(AD_PInstance_ID) ON DELETE CASCADE,
    CONSTRAINT MCostElement_TInventoryValue FOREIGN KEY (M_CostElement_ID)
    REFERENCES M_CostElement(M_CostElement_ID),
    CONSTRAINT MASI_TInventoryValue FOREIGN KEY (M_AttributeSetInstance_ID)
    REFERENCES M_AttributeSetInstance(M_AttributeSetInstance_ID)
)
/

