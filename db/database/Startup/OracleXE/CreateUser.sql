SET ECHO ON
/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: CreateUser.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 ***
 * Title:	Drop User and re-create new
 * Description:	
 *	Parameter: UserID UserPwd
 *	Run as system
 ************************************************************************/
DROP USER &1 CASCADE
/
CREATE USER &1 IDENTIFIED BY &2
    DEFAULT TABLESPACE USERS
    TEMPORARY TABLESPACE TEMP
    PROFILE DEFAULT
    ACCOUNT UNLOCK
/
GRANT CONNECT TO &1
/
GRANT DBA TO &1
/
GRANT RESOURCE TO &1
/
GRANT UNLIMITED TABLESPACE TO &1
/
ALTER USER &1 DEFAULT ROLE CONNECT, RESOURCE, DBA
/
GRANT CREATE TABLE TO &1
/
EXIT
