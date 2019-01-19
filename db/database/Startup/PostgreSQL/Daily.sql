/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2002 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: Daily.sql,v 1.1 2006/12/27 globalqss Exp $
 ***
 * Title:   Daily Tasks
 * Description:
 *  - Cleanup
 ************************************************************************/

--  Temporary Tables
DELETE FROM T_Aging;
    
DELETE FROM T_DistributionRunDetail;

DELETE FROM T_InventoryValue;
    
DELETE FROM T_Replenish;
    
DELETE FROM T_Report;
    
DELETE FROM T_ReportStatement;
    
DELETE FROM T_TrialBalance;

DELETE FROM T_Selection;
    
DELETE FROM T_Selection2;
    
DELETE FROM T_Spool;
    
--	Search Info
DELETE FROM AD_Find;
    
--	Processes older than a week
DELETE FROM AD_PInstance WHERE Created < now()-7;
    
--	Errors older than 1 week
DELETE FROM AD_Error WHERE Created < now()-7;
    
--  Acknowledged Notes older than a day
DELETE FROM AD_Note WHERE Processed='Y' AND Updated < now()-1;
    
--
COMMIT;