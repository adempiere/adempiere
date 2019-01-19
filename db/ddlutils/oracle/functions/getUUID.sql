CREATE OR REPLACE FUNCTION GETUUID
/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * Copyright (C) 2003-2015 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpya.com                                  *
 *****************************************************************************
 *
 * Title:	Get UUID from DB Function
 * Description:
 *	Get UUID from DB function, it allows get a uuid from db function implemented for postgreSQL
 *
 * Test:
 * 	SELECT getUUID() FROM DUAL; - Get UUID
 ************************************************************************/
RETURN VARCHAR2 AS
    v_UUID			VARCHAR2(36);
BEGIN
    SELECT SYS_GUID() INTO v_UUID FROM dual;
    SELECT substr(v_UUID, 1, 8)  
                || '-' || substr(v_UUID, 9, 4)  
                || '-' || substr(v_UUID, 10, 4) 
                || '-' || substr(v_UUID, 15, 4) 
                || '-' || substr(v_UUID, 20, 12) INTO v_UUID FROM DUAL;
    RETURN v_UUID;
END
/