/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2002 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: BeforeExport.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 ***
 * Title:	Before Export
 * Description:
 *		- Delete Temporary Data
 ************************************************************************/

DELETE T_Report
/
DELETE T_Replenish
/
DELETE T_Spool
/
EXIT