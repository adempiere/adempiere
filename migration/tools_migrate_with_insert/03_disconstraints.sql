/*************************************************************************
 * The contents of this file are subject to the Compiere License.  You may
 * obtain a copy of the License at    http://www.compiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Compiere ERP+CRM
 * Copyright (C) 1999-2002 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: DBA_Trg_Disable.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 ***
 * Title:	Disable all triggers and felf referencing constraints
 * Description:
 *		- required for initial DB create
 ************************************************************************/

DECLARE
   -- Trigger
   CURSOR cur_trg
   IS
      SELECT   trigger_name
          FROM user_triggers
         WHERE status <> 'DISABLED'
      ORDER BY 1;

   CURSOR cur_constraintr
   IS
      SELECT   table_name, constraint_name
          FROM user_constraints c
         WHERE c.constraint_type <> 'P' AND c.status <> 'DISABLED'
      ORDER BY 1;

   CURSOR cur_constraintp
   IS
      SELECT   table_name, constraint_name
          FROM user_constraints c
         WHERE c.constraint_type = 'P' AND c.status <> 'DISABLED'
      ORDER BY 1;

   v_cmd   VARCHAR2 (256);
BEGIN
   DBMS_OUTPUT.put_line ('Disabling:');

   FOR t IN cur_trg
   LOOP
      DBMS_OUTPUT.put_line ('.. ' || t.trigger_name);
      v_cmd := 'ALTER TRIGGER ' || t.trigger_name || ' DISABLE';

      EXECUTE IMMEDIATE v_cmd;
   END LOOP;

   FOR c IN cur_constraintr
   LOOP
      DBMS_OUTPUT.put_line ('.. ' || c.table_name || ' ' || c.constraint_name);
      v_cmd :=
            'ALTER TABLE '
         || c.table_name
         || ' MODIFY CONSTRAINT '
         || c.constraint_name
         || ' DISABLE';

      EXECUTE IMMEDIATE v_cmd;
   END LOOP;

   FOR c IN cur_constraintp
   LOOP
      DBMS_OUTPUT.put_line ('.. ' || c.table_name || ' ' || c.constraint_name);
      v_cmd :=
            'ALTER TABLE '
         || c.table_name
         || ' MODIFY CONSTRAINT '
         || c.constraint_name
         || ' DISABLE';

      EXECUTE IMMEDIATE v_cmd;
   END LOOP;
END;
