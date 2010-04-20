/*
 *This file is part of Adempiere ERP Bazaar
 *http://www.adempiere.org
 *Copyright (C) 2006-2008 victor.perez@e-evolution.com, e-Evolution
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
 *
 * Author: Carlos Ruiz (globalqss)
 */
CREATE OR REPLACE FUNCTION get_Sysconfig
(
    sysconfig_name IN ad_sysconfig.name%TYPE,
    defaultvalue IN ad_sysconfig.value%TYPE,
    client_id IN ad_sysconfig.ad_client_id%TYPE,
    org_id IN ad_sysconfig.ad_org_id%TYPE
)
RETURN ad_sysconfig.value%TYPE
AS
     v_value ad_sysconfig.value%TYPE;
BEGIN
    BEGIN
        SELECT Value
          INTO v_value FROM
            (SELECT Value 
           FROM AD_SysConfig WHERE Name=sysconfig_name AND AD_Client_ID IN (0, client_id) AND AD_Org_ID IN (0, org_id) AND IsActive='Y' 
              ORDER BY AD_Client_ID DESC, AD_Org_ID DESC)
           WHERE ROWNUM = 1;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            v_value := defaultvalue;
    END;
    RETURN v_value;
END get_Sysconfig;
/
