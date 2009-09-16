CREATE OR REPLACE PROCEDURE DBA_Cleanup
/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: DBA_Cleanup.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 ***
 * Title:    Cleanup old temporary data
 * Description:
 *
 * Author: Teo Sarca, www.arhipac.ro
 *            * Autodetect temporary tables and delete rows that are older then 7 days
 ************************************************************************/
AS
BEGIN
    DBMS_OUTPUT.PUT_LINE('DBA_Cleanup');
    --  Clean up data
    /**
--    C_Invoice_CheckPaid();
    UPDATE C_Payment_v 
      SET IsAllocated=CASE WHEN paymentAllocated(C_Payment_ID, C_Currency_ID)=PayAmt THEN 'Y' ELSE 'N' END
    WHERE IsAllocated='N';
    UPDATE C_Invoice_v1    
       SET IsPaid = CASE WHEN invoicePaid(C_Invoice_ID,C_Currency_ID,MultiplierAP)=GrandTotal THEN 'Y' ELSE 'N' END 
    WHERE IsPaid='N';
    **/
    
    --  Temporary Tables
    FOR t IN (SELECT t.TableName FROM AD_Table t
                WHERE t.TableName like 'T!_%' ESCAPE '!'
                AND t.IsView='N'
                AND EXISTS (SELECT 1 FROM user_tables ut WHERE UPPER(ut.table_name)=UPPER(t.TableName))
                AND EXISTS (SELECT 1 FROM user_tab_cols uc WHERE UPPER(uc.table_name)=UPPER(t.TableName) AND uc.column_name='AD_PINSTANCE_ID')
                ORDER BY t.TableName)
    LOOP
        EXECUTE IMMEDIATE
            'DELETE FROM '||t.TableName||' t'
            ||' WHERE EXISTS (SELECT 1 FROM AD_PInstance pi WHERE pi.AD_PInstance_ID=t.AD_PInstance_ID AND Created < SysDate-7)'
        ;
        IF (SQL%ROWCOUNT <> 0) THEN
            DBMS_OUTPUT.PUT_LINE(' '||t.TableName||'=' || SQL%ROWCOUNT);
        END IF;
    END LOOP;
    
    /**  Search Info -- AD_Find not used in adempiere
    DELETE FROM AD_Find WHERE Created < SysDate-7;
    IF (SQL%ROWCOUNT <> 0) THEN
        DBMS_OUTPUT.PUT_LINE(' AD_Find=' || SQL%ROWCOUNT);
    END IF;
    */
    
    /**  Processes older than a week -- audit data better configured using HouseKeeping
    DELETE FROM AD_PInstance WHERE Created < SysDate-7;
    IF (SQL%ROWCOUNT <> 0) THEN
        DBMS_OUTPUT.PUT_LINE(' Old AD_PInstance=' || SQL%ROWCOUNT);
    END IF;
    --  Change Log -- audit data better configured using HouseKeeping
    DELETE FROM AD_ChangeLog WHERE Created < SysDate-7;
    IF (SQL%ROWCOUNT <> 0) THEN
        DBMS_OUTPUT.PUT_LINE(' Old AD_ChangeLock=' || SQL%ROWCOUNT);
    END IF;
    --  Old Session (1 Week) -- audit data better configured using HouseKeeping
    DELETE FROM AD_Session WHERE Created < SysDate-7;
    IF (SQL%ROWCOUNT <> 0) THEN
        DBMS_OUTPUT.PUT_LINE(' Old AD_Session=' || SQL%ROWCOUNT);
    END IF;
    */

    /**  Errors older than 1 week -- AD_Error not used in Adempiere
    DELETE FROM AD_Error WHERE Created < SysDate-7;
    IF (SQL%ROWCOUNT <> 0) THEN
        DBMS_OUTPUT.PUT_LINE(' Old AD_Error=' || SQL%ROWCOUNT);
    END IF;
    */
    
    /**  Acknowledged Notes older than one week -- audit data better configured using HouseKeeping
    DELETE FROM AD_Note WHERE Processed='Y' AND Updated < SysDate-7;
    IF (SQL%ROWCOUNT <> 0) THEN
        DBMS_OUTPUT.PUT_LINE(' Processed AD_Note=' || SQL%ROWCOUNT);
    END IF;
    */
    
    --
    COMMIT;
END DBA_Cleanup;
/
