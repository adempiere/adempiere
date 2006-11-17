/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2003 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: M_AttributeSet_Check.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 ***
 * Title:   Chack Product Attribute Set
 * Description:
 *  - Set Instance Attribute.
 ************************************************************************/
UPDATE M_AttributeSet
SET IsInstanceAttribute = 
(CASE WHEN (IsGuaranteeDate='Y' OR IsSerNo='Y' OR IsLot='Y') THEN 'Y' ELSE 'N' END)
/
UPDATE M_AttributeSet mas
SET IsInstanceAttribute = 'Y'
WHERE IsInstanceAttribute = 'N'
AND EXISTS 
(SELECT * FROM M_AttributeUse mau 
INNER JOIN M_Attribute ma ON (ma.M_Attribute_ID=mau.M_Attribute_ID)
WHERE mau.M_AttributeSet_ID=mas.M_AttributeSet_ID 
AND mau.IsActive='Y'
AND ma.IsInstanceAttribute='Y' AND ma.IsActive='Y')
/
COMMIT
