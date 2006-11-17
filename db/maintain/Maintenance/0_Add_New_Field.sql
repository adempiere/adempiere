/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: 0_Add_New_Field.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 ***
 * Title:	Add new Window Fields
 * Description:
 *		For existing Tabs from Table/Column
 *      You should change the entity type variables from D to User or Application.
    SELECT * FROM AD_Field WHERE Created > SysDate-.1
 *
 ************************************************************************/
BEGIN
	DBMS_OUTPUT.ENABLE(80000);
	DBMS_OUTPUT.PUT_LINE('For all Tabs create missing Fields');
	DECLARE
        v_EntityType    CHAR(1) := 'D';     -- change to User
        --
		CURSOR	Cur_Tab IS
			SELECT t.AD_Table_ID, t.AD_Tab_ID, w.Name || ' - ' || t.Name AS Name
			FROM AD_Window w
              INNER JOIN AD_Tab t ON (w.AD_Window_ID=t.AD_Window_ID)
			WHERE t.IsSortTab='N'
         --   AND t.AD_Table_ID != 291   -- C_BPartner (multiple tabs)
         --   AND AD_Table_ID NOT IN (SELECT AD_Table_ID FROM AD_Table WHERE TableName LIKE 'I\_%' ESCAPE '\')
            ORDER BY w.Name, t.SeqNo;
		CURSOR	Cur_Column (Tab NUMBER, TableID NUMBER)	 IS
			SELECT	Name, Description, AD_Column_ID, FieldLength 
			FROM	AD_Column c
			WHERE NOT EXISTS 
				(SELECT * FROM AD_Field f 
				WHERE c.AD_Column_ID=f.AD_Column_ID
				AND c.AD_Table_ID=TableID
				AND f.AD_Tab_ID=Tab) 
			AND AD_Table_ID=TableID
			AND NOT (UPPER(Name) LIKE 'CREATED%' OR UPPER(Name) LIKE 'UPDATED%')
			AND IsActive='Y';
		--
		NextNo		NUMBER;
	BEGIN
		FOR CT IN Cur_Tab LOOP
			FOR CC IN Cur_Column (CT.AD_Tab_ID, CT.AD_Table_ID) LOOP
				AD_Sequence_Next('AD_Field', 0, NextNo);	--	get ID
				INSERT INTO AD_Field
					(ad_field_id, ad_client_id, ad_org_id,
					isactive, created, createdby, updated, updatedby,
					name, description, EntityType,
					seqno, AD_Tab_ID, AD_Column_ID, DisplayLength, IsCentrallyMaintained)
				VALUES
					(NextNo, 0, 0,
					'Y', SysDate, 0, SysDate, 0,
					CC.Name, CC.Description, v_EntityType,
					0, CT.AD_Tab_ID, CC.AD_Column_ID, CC.FieldLength, 'Y');
				DBMS_OUTPUT.PUT_LINE('Tab ' || CT.Name || ' adding ' || CC.Name);
			END LOOP;	--  for all columns
		END LOOP;	--  for all tabs
        COMMIT;
	END;
	COMMIT;
END;

