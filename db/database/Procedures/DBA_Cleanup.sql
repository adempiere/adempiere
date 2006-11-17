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
 * Title:	Cleanup old temporary data
 * Description:
 ************************************************************************/
AS
BEGIN
	DBMS_OUTPUT.PUT_LINE('DBA_Cleanup');
	--  Clean up data
    /**
--	C_Invoice_CheckPaid();
    UPDATE C_Payment_v 
      SET IsAllocated=CASE WHEN paymentAllocated(C_Payment_ID, C_Currency_ID)=PayAmt THEN 'Y' ELSE 'N' END
    WHERE IsAllocated='N';
    UPDATE C_Invoice_v1	
 	  SET IsPaid = CASE WHEN invoicePaid(C_Invoice_ID,C_Currency_ID,MultiplierAP)=GrandTotal THEN 'Y' ELSE 'N' END 
    WHERE IsPaid='N';
    **/
    
	--  Temporary Tables
	DELETE FROM T_Aging;
    IF (SQL%ROWCOUNT <> 0) THEN
    	DBMS_OUTPUT.PUT_LINE(' T_Aging=' || SQL%ROWCOUNT);
    END IF;
    
	DELETE FROM T_DistributionRunDetail;
    IF (SQL%ROWCOUNT <> 0) THEN
    	DBMS_OUTPUT.PUT_LINE(' T_DistributionRunDetail=' || SQL%ROWCOUNT);
    END IF;

	DELETE FROM T_InventoryValue;
    IF (SQL%ROWCOUNT <> 0) THEN
    	DBMS_OUTPUT.PUT_LINE(' T_InventoryValue=' || SQL%ROWCOUNT);
    END IF;
    
	DELETE FROM T_Replenish;
    IF (SQL%ROWCOUNT <> 0) THEN
    	DBMS_OUTPUT.PUT_LINE(' T_Replenish=' || SQL%ROWCOUNT);
    END IF;
    
	DELETE FROM T_Report;
    IF (SQL%ROWCOUNT <> 0) THEN
    	DBMS_OUTPUT.PUT_LINE(' T_Report=' || SQL%ROWCOUNT);
    END IF;
    
	DELETE FROM T_ReportStatement;
    IF (SQL%ROWCOUNT <> 0) THEN
    	DBMS_OUTPUT.PUT_LINE(' T_ReportStatement=' || SQL%ROWCOUNT);
    END IF;
    
	DELETE FROM T_TrialBalance;
    IF (SQL%ROWCOUNT <> 0) THEN
    	DBMS_OUTPUT.PUT_LINE(' T_TrialBalance=' || SQL%ROWCOUNT);
    END IF;

	DELETE FROM T_Selection;
    IF (SQL%ROWCOUNT <> 0) THEN
    	DBMS_OUTPUT.PUT_LINE(' T_Selection=' || SQL%ROWCOUNT);
    END IF;
    
	DELETE FROM T_Selection2;
    IF (SQL%ROWCOUNT <> 0) THEN
    	DBMS_OUTPUT.PUT_LINE(' T_Selection2=' || SQL%ROWCOUNT);
    END IF;
    
	DELETE FROM T_Spool;
    IF (SQL%ROWCOUNT <> 0) THEN
    	DBMS_OUTPUT.PUT_LINE(' T_Spool=' || SQL%ROWCOUNT);
    END IF;
    
	--	Search Info
	DELETE FROM AD_Find;
    IF (SQL%ROWCOUNT <> 0) THEN
    	DBMS_OUTPUT.PUT_LINE(' AD_Find=' || SQL%ROWCOUNT);
    END IF;
    
	--	Processes older than a week
	DELETE FROM AD_PInstance WHERE Created < SysDate-7;
    IF (SQL%ROWCOUNT <> 0) THEN
    	DBMS_OUTPUT.PUT_LINE(' Old AD_PInstance=' || SQL%ROWCOUNT);
    END IF;
    
    /**  Old Session (1 Week) 
    DELETE FROM AD_ChangeLog WHERE Created < SysDate-7;
    IF (SQL%ROWCOUNT <> 0) THEN
    	DBMS_OUTPUT.PUT_LINE(' Old AD_ChangeLock=' || SQL%ROWCOUNT);
    END IF;
    DELETE FROM AD_Session WHERE Created < SysDate-7;
    IF (SQL%ROWCOUNT <> 0) THEN
    	DBMS_OUTPUT.PUT_LINE(' Old AD_Session=' || SQL%ROWCOUNT);
    END IF;
    /**    */

	--	Errors older than 1 week
	DELETE FROM AD_Error WHERE Created < SysDate-7;
    IF (SQL%ROWCOUNT <> 0) THEN
    	DBMS_OUTPUT.PUT_LINE(' Old AD_Error=' || SQL%ROWCOUNT);
    END IF;
    
	--  Acknowledged Notes older than a day
	DELETE FROM AD_Note WHERE Processed='Y' AND Updated < SysDate-1;
    IF (SQL%ROWCOUNT <> 0) THEN
    	DBMS_OUTPUT.PUT_LINE(' Processed AD_Note=' || SQL%ROWCOUNT);
    END IF;
    
	--
	COMMIT;
END DBA_Cleanup;
/
