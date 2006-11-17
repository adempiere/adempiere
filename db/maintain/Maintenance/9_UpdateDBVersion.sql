/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: 9_UpdateDBVersion.sql,v 1.5 2006/08/11 03:20:00 jjanke Exp $
 ***
 * Title:	Update Database Version
 * Description:
 ************************************************************************/

UPDATE AD_System 
-----------------==========-----
  SET	ReleaseNo = '253d',
        Version='2006-08-10',
-----------------==========----- 
        Created=Sysdate,
		Updated=SysDate;
--
COMMIT;
--
SELECT * FROM AD_System;

