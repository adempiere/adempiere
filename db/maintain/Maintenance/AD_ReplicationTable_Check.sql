/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2003 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: AD_ReplicationTable_Check.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 ***
 * Title:   Replication Check
 * Description:
 ************************************************************************/
DECLARE
    CURSOR CUR_Replication IS
        SELECT r.AD_ReplicationStrategy_ID, 
            t.AD_Table_ID, t.EntityType, t.AD_Client_ID, t.AD_Org_ID, t.TableName
        FROM AD_ReplicationStrategy r, AD_Table t
        WHERE t.IsActive='Y' AND t.IsView='N' AND r.IsActive='Y' 
         AND NOT EXISTS (SELECT * FROM AD_ReplicationTable rt
            WHERE rt.AD_ReplicationStrategy_ID=r.AD_ReplicationStrategy_ID
              AND rt.AD_Table_ID=t.AD_Table_ID);
    v_NextNo                NUMBER(10);
BEGIN
    FOR r IN CUR_Replication LOOP
        DBMS_OUTPUT.PUT_LINE(r.TableName);
        AD_Sequence_Next('AD_ReplicationTable', r.AD_Table_ID, v_NextNo);
        INSERT INTO AD_ReplicationTable
            (AD_ReplicationTable_ID, AD_Client_ID, AD_Org_ID,
            IsActive, Created, CreatedBy, Updated, UpdatedBy,
            AD_ReplicationStrategy_ID, AD_TABLE_ID, REPLICATIONTYPE, EntityType)
        VALUES (v_NextNo, r.AD_Client_ID, r.AD_Org_ID,
            'Y', SysDate, 0, SysDate, 0,
            r.AD_ReplicationStrategy_ID, r.AD_Table_ID, 'L', r.EntityType);
    END LOOP;
    COMMIT;
END;
/