/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: AD_Menu_Check.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 ***
 * Title:	Menu Check
 * Description:
 *		For AD_Menu create  
 *		-	Missing Translations
 *		-	Missing Windows in Main Menu 
 *		-	Missing Menu Tree Structure(s)
 *  	-   Synchronize Window Names with 
 *			AD_Window/AD_Process/AD_Form/AD_Workflow/AD_Task
 ************************************************************************/
BEGIN
	DBMS_OUTPUT.PUT_LINE('Clean up Nodes');
	UPDATE AD_Menu
		SET Action = NULL
	WHERE IsSummary='Y'
		AND Action IS NOT NULL;		   

	UPDATE AD_Menu
	SET AD_Workbench_ID=NULL
	WHERE Action <> 'B'	 AND AD_Workbench_ID IS NOT NULL;

	UPDATE AD_Menu
	SET AD_WorkFlow_ID=NULL
	WHERE (Action <> 'F' OR Action IS NULL) AND AD_WorkFlow_ID IS NOT NULL;

	UPDATE AD_Menu
	SET AD_Process_ID=NULL
	WHERE (Action NOT IN ('P','R') OR Action IS NULL) AND AD_Process_ID IS NOT NULL;

	UPDATE AD_Menu
	SET AD_Task_ID=NULL
	WHERE (Action <> 'T' OR Action IS NULL) AND AD_Task_ID IS NOT NULL;

	UPDATE AD_Menu
	SET AD_Window_ID=NULL
	WHERE (Action <> 'W' OR Action IS NULL) AND AD_Window_ID IS NOT NULL;

	UPDATE AD_Menu
	SET AD_Form_ID=NULL
	WHERE (Action <> 'X' OR Action IS NULL) AND AD_Form_ID IS NOT NULL;


	DBMS_OUTPUT.PUT_LINE('Adding Windows to Menu');
	DECLARE
		NextNo		NUMBER;
		CURSOR Cur_Window	IS
			SELECT *
			FROM AD_Window	   --  add only non existing menus 
			WHERE NOT EXISTS (SELECT * FROM AD_Menu m 
							  WHERE AD_Window.AD_Window_ID=m.AD_Window_ID);
	BEGIN
		FOR CW IN Cur_Window LOOP
			AD_Sequence_Next('AD_Menu', 0, NextNo);	--	get ID
			INSERT INTO AD_Menu
				(AD_MENU_ID, AD_CLIENT_ID, AD_ORG_ID, 
				ISACTIVE, CREATED, CREATEDBY, UPDATED, UPDATEDBY,
				NAME, DESCRIPTION, 
				ISSUMMARY, ACTION, AD_WINDOW_ID)
			VALUES
				(NextNo, CW.AD_CLIENT_ID, CW.AD_ORG_ID,
				CW.ISACTIVE, CW.CREATED, CW.CREATEDBY, CW.UPDATED, CW.UPDATEDBY,
				CW.NAME, CW.DESCRIPTION,
				'N', 'W', CW.AD_WINDOW_ID);
			DBMS_OUTPUT.PUT_LINE('  added: ' || CW.NAME);
		END LOOP;
	END;


	DBMS_OUTPUT.PUT_LINE('Adding to Base Menu Tree');
	DECLARE
		CURSOR	Cur_Tree IS
			SELECT	*
			FROM	AD_ClientInfo;
		CURSOR Cur_Menu	(Client NUMBER, Tree NUMBER) IS
			SELECT *
			FROM AD_Menu
			WHERE AD_Menu_ID NOT IN 
				(SELECT Node_ID FROM AD_TreeNodeMM WHERE AD_Tree_ID=Tree)
			AND AD_Client_ID=Client;
	BEGIN
		FOR CT IN Cur_Tree LOOP
			DBMS_OUTPUT.PUT_LINE('  For Tree ' || CT.AD_Tree_Menu_ID);
			--
			FOR CM IN Cur_Menu (CT.AD_Client_ID, CT.AD_Tree_Menu_ID) LOOP
				INSERT INTO AD_TreeNodeMM
					(AD_Client_ID, AD_Org_ID,
					IsActive, Created, CreatedBy, Updated, UpdatedBy,
					AD_Tree_ID, Node_ID, Parent_ID, SeqNo)
				VALUES
					(CM.AD_Client_ID, CM.AD_Org_ID, 
					CM.IsActive, CM.Created, CM.CreatedBy, CM.Updated, CM.UpdatedBy,
					CT.AD_Tree_Menu_ID, CM.AD_Menu_ID, 0, 999);
			   DBMS_OUTPUT.PUT_LINE('    added: ' || CM.NAME);
			END LOOP;	-- Menu Loop
		END LOOP;	--	Tree Loop
	END;	-- Adding to Tree

	--	Menu Trl
	DBMS_OUTPUT.PUT_LINE('Adding missing Menu Translations');
	INSERT INTO AD_Menu_Trl (AD_Menu_ID, AD_Language, AD_Client_ID, AD_Org_ID,
		IsActive, Created, CreatedBy, Updated, UpdatedBy,
		Name, Description, IsTranslated)
	SELECT m.AD_Menu_ID, l.AD_Language, m.AD_Client_ID, m.AD_Org_ID,
		m.IsActive, m.Created, m.CreatedBy, m.Updated, m.UpdatedBy,
		m.Name, m.Description, 'N'
	FROM	AD_Menu m, AD_Language l
	WHERE	l.IsActive = 'Y' AND l.IsSystemLanguage = 'Y'
	  AND	AD_Menu_ID || AD_Language NOT IN 
		(SELECT AD_Menu_ID || AD_Language FROM AD_Menu_Trl);
	DBMS_OUTPUT.PUT_LINE('  rows added: ' || SQL%ROWCOUNT);

	--	Sync Names - Window
	DBMS_OUTPUT.PUT_LINE('Synchronizing Names with Window Names');
	UPDATE	AD_Menu m
	SET		Name = (SELECT Name FROM AD_Window w WHERE m.AD_Window_ID=w.AD_Window_ID),
			Description = (SELECT Description FROM AD_Window w WHERE m.AD_Window_ID=w.AD_Window_ID)
	WHERE	AD_Window_ID IS NOT NULL
		AND Action = 'W';
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);

	UPDATE	AD_Menu_Trl mt
	SET		Name = (SELECT wt.Name FROM AD_Window_Trl wt, AD_Menu m 
					WHERE mt.AD_Menu_ID=m.AD_Menu_ID AND m.AD_Window_ID=wt.AD_Window_ID 
					AND mt.AD_Language=wt.AD_Language),
			Description = (SELECT wt.Description FROM AD_Window_Trl wt, AD_Menu m 
					WHERE mt.AD_Menu_ID=m.AD_Menu_ID AND m.AD_Window_ID=wt.AD_Window_ID 
					AND mt.AD_Language=wt.AD_Language),
			IsTranslated = (SELECT wt.IsTranslated FROM AD_Window_Trl wt, AD_Menu m 
					WHERE mt.AD_Menu_ID=m.AD_Menu_ID AND m.AD_Window_ID=wt.AD_Window_ID 
					AND mt.AD_Language=wt.AD_Language)
	WHERE EXISTS (SELECT * FROM AD_Window_Trl wt, AD_Menu m 
					WHERE mt.AD_Menu_ID=m.AD_Menu_ID AND m.AD_Window_ID=wt.AD_Window_ID 
					AND mt.AD_Language=wt.AD_Language
					AND m.AD_Window_ID IS NOT NULL
					AND m.Action = 'W');
	DBMS_OUTPUT.PUT_LINE('  trl rows updated: ' || SQL%ROWCOUNT);

	--	Sync Names - Process
	DBMS_OUTPUT.PUT_LINE('Synchronizing Names with Process Names');
	UPDATE	AD_Menu m
	SET		Name = (SELECT p.Name FROM AD_Process p WHERE m.AD_Process_ID=p.AD_Process_ID),
			Description = (SELECT p.Description FROM AD_Process p WHERE m.AD_Process_ID=p.AD_Process_ID)
	WHERE	m.AD_Process_ID IS NOT NULL
	  AND	m.Action IN ('R', 'P');
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);

	UPDATE	AD_Menu_Trl mt
	SET		Name = (SELECT pt.Name FROM AD_Process_Trl pt, AD_Menu m
					WHERE mt.AD_Menu_ID=m.AD_Menu_ID AND m.AD_Process_ID=pt.AD_Process_ID
					AND mt.AD_Language=pt.AD_Language),
			Description = (SELECT pt.Description FROM AD_Process_Trl pt, AD_Menu m
					WHERE mt.AD_Menu_ID=m.AD_Menu_ID AND m.AD_Process_ID=pt.AD_Process_ID
					AND mt.AD_Language=pt.AD_Language),
			IsTranslated = (SELECT pt.IsTranslated FROM AD_Process_Trl pt, AD_Menu m
					WHERE mt.AD_Menu_ID=m.AD_Menu_ID AND m.AD_Process_ID=pt.AD_Process_ID
					AND mt.AD_Language=pt.AD_Language)
	WHERE EXISTS (SELECT * FROM AD_Process_Trl pt, AD_Menu m
					WHERE mt.AD_Menu_ID=m.AD_Menu_ID AND m.AD_Process_ID=pt.AD_Process_ID
					AND mt.AD_Language=pt.AD_Language
					AND m.AD_Process_ID IS NOT NULL
					AND	Action IN ('R', 'P'));
	DBMS_OUTPUT.PUT_LINE('  trl rows updated: ' || SQL%ROWCOUNT);

	--	Sync Names = Form
	DBMS_OUTPUT.PUT_LINE('Synchronizing Names with Form Names');
	UPDATE	AD_Menu m
	SET		Name = (SELECT Name FROM AD_Form f WHERE m.AD_Form_ID=f.AD_Form_ID),
			Description = (SELECT Description FROM AD_Form f WHERE m.AD_Form_ID=f.AD_Form_ID)
	WHERE	AD_Form_ID IS NOT NULL
	  AND	Action = 'X';
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);

	UPDATE	AD_Menu_Trl mt
	SET		Name = (SELECT ft.Name FROM AD_Form_Trl ft, AD_Menu m
					WHERE mt.AD_Menu_ID=m.AD_Menu_ID AND m.AD_Form_ID=ft.AD_Form_ID
					AND mt.AD_Language=ft.AD_Language),
			Description = (SELECT ft.Description FROM AD_Form_Trl ft, AD_Menu m
					WHERE mt.AD_Menu_ID=m.AD_Menu_ID AND m.AD_Form_ID=ft.AD_Form_ID
					AND mt.AD_Language=ft.AD_Language),
			IsTranslated = (SELECT ft.IsTranslated FROM AD_Form_Trl ft, AD_Menu m
					WHERE mt.AD_Menu_ID=m.AD_Menu_ID AND m.AD_Form_ID=ft.AD_Form_ID
					AND mt.AD_Language=ft.AD_Language)
	WHERE EXISTS (SELECT * FROM AD_Form_Trl ft, AD_Menu m
					WHERE mt.AD_Menu_ID=m.AD_Menu_ID AND m.AD_Form_ID=ft.AD_Form_ID
					AND mt.AD_Language=ft.AD_Language
					AND m.AD_Form_ID IS NOT NULL
					AND	Action = 'X');
	DBMS_OUTPUT.PUT_LINE('  trl rows updated: ' || SQL%ROWCOUNT);

	--	Sync Names - Workflow
	DBMS_OUTPUT.PUT_LINE('Synchronizing Names with Workflow Names');
	UPDATE	AD_Menu m
	SET		Name = (SELECT p.Name FROM AD_Workflow p WHERE m.AD_Workflow_ID=p.AD_Workflow_ID),
			Description = (SELECT p.Description FROM AD_Workflow p WHERE m.AD_Workflow_ID=p.AD_Workflow_ID)
	WHERE	m.AD_Workflow_ID IS NOT NULL
	  AND	m.Action = 'F';
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);

	UPDATE	AD_Menu_Trl mt
	SET		Name = (SELECT pt.Name FROM AD_Workflow_Trl pt, AD_Menu m
					WHERE mt.AD_Menu_ID=m.AD_Menu_ID AND m.AD_Workflow_ID=pt.AD_Workflow_ID
					AND mt.AD_Language=pt.AD_Language),
			Description = (SELECT pt.Description FROM AD_Workflow_Trl pt, AD_Menu m
					WHERE mt.AD_Menu_ID=m.AD_Menu_ID AND m.AD_Workflow_ID=pt.AD_Workflow_ID
					AND mt.AD_Language=pt.AD_Language),
			IsTranslated = (SELECT pt.IsTranslated FROM AD_Workflow_Trl pt, AD_Menu m
					WHERE mt.AD_Menu_ID=m.AD_Menu_ID AND m.AD_Workflow_ID=pt.AD_Workflow_ID
					AND mt.AD_Language=pt.AD_Language)
	WHERE EXISTS (SELECT * FROM AD_Workflow_Trl pt, AD_Menu m
					WHERE mt.AD_Menu_ID=m.AD_Menu_ID AND m.AD_Workflow_ID=pt.AD_Workflow_ID
					AND mt.AD_Language=pt.AD_Language
					AND m.AD_Workflow_ID IS NOT NULL
					AND	Action = 'F');
	DBMS_OUTPUT.PUT_LINE('  trl rows updated: ' || SQL%ROWCOUNT);

	--	Sync Names = Task
	DBMS_OUTPUT.PUT_LINE('Synchronizing Names with Task Names');
	UPDATE	AD_Menu m
	SET		Name = (SELECT Name FROM AD_Task f WHERE m.AD_Task_ID=f.AD_Task_ID),
			Description = (SELECT Description FROM AD_Task f WHERE m.AD_Task_ID=f.AD_Task_ID)
	WHERE	AD_Task_ID IS NOT NULL
	  AND	Action = 'T';
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);

	UPDATE	AD_Menu_Trl mt
	SET		Name = (SELECT ft.Name FROM AD_Task_Trl ft, AD_Menu m
					WHERE mt.AD_Menu_ID=m.AD_Menu_ID AND m.AD_Task_ID=ft.AD_Task_ID
					AND mt.AD_Language=ft.AD_Language),
			Description = (SELECT ft.Description FROM AD_Task_Trl ft, AD_Menu m
					WHERE mt.AD_Menu_ID=m.AD_Menu_ID AND m.AD_Task_ID=ft.AD_Task_ID
					AND mt.AD_Language=ft.AD_Language),
			IsTranslated = (SELECT ft.IsTranslated FROM AD_Task_Trl ft, AD_Menu m
					WHERE mt.AD_Menu_ID=m.AD_Menu_ID AND m.AD_Task_ID=ft.AD_Task_ID
					AND mt.AD_Language=ft.AD_Language)
	WHERE EXISTS (SELECT * FROM AD_Task_Trl ft, AD_Menu m
					WHERE mt.AD_Menu_ID=m.AD_Menu_ID AND m.AD_Task_ID=ft.AD_Task_ID
					AND mt.AD_Language=ft.AD_Language
					AND m.AD_Task_ID IS NOT NULL
					AND	Action = 'T');
	DBMS_OUTPUT.PUT_LINE('  trl rows updated: ' || SQL%ROWCOUNT);

	--
	COMMIT;
END;
/
