CREATE OR REPLACE FUNCTION bpartnerRemitLocation
(
	p_C_BPartner_ID	  C_BPartner.C_BPartner_ID%TYPE
)
RETURN NUMBER
/*************************************************************************
 * The contents of this file are subject to the Compiere License.  You may
 * obtain a copy of the License at    http://www.compiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Compiere ERP+CRM
 * Copyright (C) 1999-2002 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: C_BPartner_RemitLocation.SQL,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 ***
 * Title:   Return the first RemitTo C_Location_ID of a Business Partner
 * Description:
 *      
 ************************************************************************/
AS
	v_C_Location_ID			NUMBER := NULL;
	CURSOR	CUR_BPLoc	IS
		SELECT	IsRemitTo, C_Location_ID
		FROM	C_BPartner_Location
		WHERE	C_BPartner_ID=p_C_BPartner_ID
		ORDER BY IsRemitTo DESC;
BEGIN
	FOR l IN CUR_BPLoc LOOP
		IF (v_C_Location_ID IS NULL) THEN
			v_C_Location_ID := l.C_Location_ID;
		END IF;
	END LOOP;
	RETURN v_C_Location_ID;
END bpartnerRemitLocation;
/