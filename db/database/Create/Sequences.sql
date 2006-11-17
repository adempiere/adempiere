/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: Sequences.sql,v 1.1 2006/04/21 17:51:59 jjanke Exp $
 ***
 * Title:	Sequences
 * Description:	
 *			(Re)Create  Sequences
 ************************************************************************/



/**
 *	Error Messages (deleteable) Primary Key
 */
TRUNCATE TABLE AD_Error
/
DROP SEQUENCE AD_Error_Seq
/
CREATE SEQUENCE AD_Error_Seq 
	START WITH 1
	INCREMENT BY 1
/


/**
 * Process Log
 */
DELETE FROM T_Report
/
DELETE FROM AD_PInstance
/
DROP SEQUENCE AD_PInstance_Seq
/
CREATE SEQUENCE AD_PInstance_Seq 
	START WITH 1
	INCREMENT BY 1
/

/**
 *	T_Spool (Global Temporary Table) Primary Key
 */
DROP SEQUENCE T_Spool_Seq
/
CREATE SEQUENCE T_Spool_Seq 
	START WITH 1
	INCREMENT BY 1
/
