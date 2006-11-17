CREATE OR REPLACE PROCEDURE AD_Synchronize
(
	p_PInstance_ID			IN NUMBER --	DEFAULT NULL
)
/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2003 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: AD_Syncronize.sql,v 1.2 2006/05/03 00:06:58 jjanke Exp $
 ***
 * Title:	Syncronize Application Dictionary
 * Description:
 *		Synchronize Elements
 *		Update Column and Field with Names from Element and Process
 *		Update Process Parameters from Elements
 *		Update Workflow Notes from Windows
 *		Update Menu from Window/Form/Process/Task
 ************************************************************************/
AS
	--	Logistice
	v_ResultStr					VARCHAR2(2000);
	v_Message					VARCHAR2(2000);
	v_Result					NUMBER := 1;	-- 0=failure
	v_Record_ID					NUMBER;
	v_AD_User_ID				NUMBER;
	--	Parameter
	CURSOR Cur_Parameter (pp_PInstance NUMBER) IS
		SELECT i.Record_ID, i.AD_User_ID,
			p.ParameterName, p.P_String, p.P_Number, p.P_Date
		FROM AD_PInstance i, AD_PInstance_Para p
		WHERE i.AD_PInstance_ID=pp_PInstance
		AND i.AD_PInstance_ID=p.AD_PInstance_ID(+)
		ORDER BY p.SeqNo;
	--	Parameter Variables

BEGIN
	IF (p_PInstance_ID IS NOT NULL) THEN
		--  Update AD_PInstance
		DBMS_OUTPUT.PUT_LINE('Updating PInstance - Processing ' || p_PInstance_ID);
		v_ResultStr := 'PInstanceNotFound';
		UPDATE AD_PInstance
		SET Created = SysDate,
			IsProcessing = 'Y'
		WHERE AD_PInstance_ID=p_PInstance_ID;
		COMMIT;

		--	Get Parameters
		v_ResultStr := 'ReadingParameters';
		FOR p IN Cur_Parameter (p_PInstance_ID) LOOP
			v_Record_ID := p.Record_ID;
			v_AD_User_ID := p.AD_User_ID;
		END LOOP;	--	Get Parameter
		DBMS_OUTPUT.PUT_LINE('  Record_ID=' || v_Record_ID);
	END IF;

	---------------------------------------------------------------------------

	DBMS_OUTPUT.PUT_LINE('Adding missing Elements');
	DECLARE
		NextNo		NUMBER;
		CURSOR Cur_Column	IS
			SELECT DISTINCT ColumnName, Name, Description, Help, EntityType
			FROM	AD_Column c
			WHERE NOT EXISTS 
				(SELECT * FROM AD_Element e
				WHERE UPPER(c.ColumnName)=UPPER(e.ColumnName));
		CURSOR Cur_Process	IS
			SELECT DISTINCT ColumnName, Name, Description, Help, EntityType
			FROM	AD_Process_Para p
			WHERE NOT EXISTS 
				(SELECT * FROM AD_Element e
				WHERE UPPER(p.ColumnName)=UPPER(e.ColumnName));
		CC	Cur_Column%ROWTYPE;
	BEGIN
		DBMS_OUTPUT.PUT_LINE('Column:');
		FOR CC IN Cur_Column LOOP
			AD_Sequence_Next('AD_Element', 0, NextNo);	--	get ID
			INSERT INTO AD_ELEMENT
				(AD_ELEMENT_ID, AD_CLIENT_ID, AD_ORG_ID,
				ISACTIVE, CREATED, CREATEDBY, UPDATED, UPDATEDBY,
				ColumnName, Name, PrintName, Description, Help, EntityType)
			VALUES
				(NextNo, 0, 0,
				'Y', SysDate, 0, SysDate, 0,
				CC.ColumnName, CC.Name, CC.Name, CC.Description, CC.Help, CC.EntityType);
			DBMS_OUTPUT.PUT_LINE('  added ' || cc.ColumnName);
			COMMIT;
		END LOOP;
		DBMS_OUTPUT.PUT_LINE('Parameter:');	
		FOR CC IN Cur_Process LOOP
			AD_Sequence_Next('AD_Element', 0, NextNo);	--	get ID
			INSERT INTO AD_ELEMENT
				(AD_ELEMENT_ID, AD_CLIENT_ID, AD_ORG_ID,
				ISACTIVE, CREATED, CREATEDBY, UPDATED, UPDATEDBY,
				ColumnName, Name, PrintName, Description, Help, EntityType)
			VALUES
				(NextNo, 0, 0,
				'Y', SysDate, 0, SysDate, 0,
				CC.ColumnName, CC.Name, CC.Name, CC.Description, CC.Help, CC.EntityType);
			DBMS_OUTPUT.PUT_LINE('  added ' || cc.ColumnName);
			COMMIT;
		END LOOP;		
	END;


	DBMS_OUTPUT.PUT_LINE('Adding missing Element Translations');
	INSERT INTO AD_Element_Trl (AD_Element_ID, AD_Language, AD_Client_ID, AD_Org_ID,
		IsActive, Created, CreatedBy, Updated, UpdatedBy,
		Name, PrintName, Description, Help, IsTranslated)
	SELECT m.AD_Element_ID, l.AD_Language, m.AD_Client_ID, m.AD_Org_ID,
		m.IsActive, m.Created, m.CreatedBy, m.Updated, m.UpdatedBy,
		m.Name, m.PrintName, m.Description, m.Help, 'N'
	FROM	AD_Element m, AD_Language l
	WHERE	l.IsActive = 'Y' AND l.IsSystemLanguage = 'Y'
	  AND	AD_Element_ID || AD_Language NOT IN 
		(SELECT AD_Element_ID || AD_Language FROM AD_Element_Trl);
	DBMS_OUTPUT.PUT_LINE('  rows added: ' || SQL%ROWCOUNT);


	DBMS_OUTPUT.PUT_LINE('Creating link from Element to Column');
	UPDATE	AD_Column c
	SET		AD_Element_id = 
				(SELECT AD_Element_ID FROM AD_Element e 
				WHERE UPPER(c.ColumnName)=UPPER(e.ColumnName))
	WHERE AD_Element_ID IS NULL;
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);
    COMMIT;


	DBMS_OUTPUT.PUT_LINE('Deleting unused Elements');
	DELETE	AD_Element_Trl
	WHERE	AD_Element_ID IN
		(SELECT AD_Element_ID FROM AD_Element e 
		WHERE NOT EXISTS
			(SELECT * FROM AD_Column c WHERE UPPER(e.ColumnName)=UPPER(c.ColumnName))
		AND NOT EXISTS
			(SELECT * FROM AD_Process_Para p WHERE UPPER(e.ColumnName)=UPPER(p.ColumnName)));

	DELETE	AD_Element e
	WHERE NOT EXISTS
		(SELECT * FROM AD_Column c WHERE UPPER(e.ColumnName)=UPPER(c.ColumnName))
	AND NOT EXISTS
		(SELECT * FROM AD_Process_Para p WHERE UPPER(e.ColumnName)=UPPER(p.ColumnName));
	DBMS_OUTPUT.PUT_LINE('  rows deleted: ' || SQL%ROWCOUNT);

	---------------------------------------------------------------------------

	--	Columns
	DBMS_OUTPUT.PUT_LINE('Synchronize Column');
    /*  Identify offending column
SELECT UPPER(ColumnName)
FROM AD_Element
GROUP BY UPPER(ColumnName)
HAVING COUNT(UPPER(ColumnName)) > 1

SELECT c.ColumnName, e.ColumnName
FROM AD_Column c
  INNER JOIN AD_Element e ON (c.AD_Element_ID=e.AD_Element_ID)
WHERE c.ColumnName <> e.ColumnName
    */
	UPDATE AD_Column c
		SET	(ColumnName, Name, Description, Help) = 
                (SELECT ColumnName, Name, Description, Help 
                FROM AD_Element e WHERE c.AD_Element_ID=e.AD_Element_ID),
			Updated = SysDate
	WHERE EXISTS (SELECT * FROM AD_Element e 
				WHERE c.AD_Element_ID=e.AD_Element_ID
				  AND (c.ColumnName <> e.ColumnName OR c.Name <> e.Name 
					OR NVL(c.Description,' ') <> NVL(e.Description,' ') OR NVL(c.Help,' ') <> NVL(e.Help,' ')));
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);

	--	Fields should now be syncronized
	DBMS_OUTPUT.PUT_LINE('Synchronize Field');
	UPDATE AD_Field f
		SET (Name, Description, Help) = 
                (SELECT e.Name, e.Description, e.Help
                FROM AD_Element e, AD_Column c
	    	    WHERE e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=f.AD_Column_ID),
			Updated = SysDate
	WHERE f.IsCentrallyMaintained='Y' AND f.IsActive='Y'
	  AND EXISTS (SELECT * FROM AD_Element e, AD_Column c
				WHERE f.AD_Column_ID=c.AD_Column_ID
				  AND c.AD_Element_ID=e.AD_Element_ID AND c.AD_Process_ID IS NULL
				  AND (f.Name <> e.Name OR NVL(f.Description,' ') <> NVL(e.Description,' ') OR NVL(f.Help,' ') <> NVL(e.Help,' ')));
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);

	--	Field Translations
	DBMS_OUTPUT.PUT_LINE('Synchronize Field Translations');
	UPDATE AD_Field_trl trl
		SET Name = (SELECT e.Name FROM AD_Element_trl e, AD_Column c, AD_Field f
					WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID 
					  AND c.AD_Column_ID=f.AD_Column_ID AND f.AD_Field_ID=trl.AD_Field_ID),
			Description = (SELECT e.Description FROM AD_Element_trl e, AD_Column c, AD_Field f
					WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID 
					  AND c.AD_Column_ID=f.AD_Column_ID AND f.AD_Field_ID=trl.AD_Field_ID),
			Help = (SELECT e.Help FROM AD_Element_trl e, AD_Column c, AD_Field f
					WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID 
					  AND c.AD_Column_ID=f.AD_Column_ID AND f.AD_Field_ID=trl.AD_Field_ID),
			IsTranslated = (SELECT e.IsTranslated FROM AD_Element_trl e, AD_Column c, AD_Field f
					WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID 
					  AND c.AD_Column_ID=f.AD_Column_ID AND f.AD_Field_ID=trl.AD_Field_ID),
			Updated = SysDate
	WHERE EXISTS (SELECT * FROM AD_Field f, AD_Element_trl e, AD_Column c
				WHERE trl.AD_Field_ID=f.AD_Field_ID
				  AND f.AD_Column_ID=c.AD_Column_ID
				  AND c.AD_Element_ID=e.AD_Element_ID AND c.AD_Process_ID IS NULL
				  AND trl.AD_Language=e.AD_Language
				  AND f.IsCentrallyMaintained='Y' AND f.IsActive='Y'
				  AND (trl.Name <> e.Name OR NVL(trl.Description,' ') <> NVL(e.Description,' ') OR NVL(trl.Help,' ') <> NVL(e.Help,' ')));
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);

	--	Fields should now be syncronized
	DBMS_OUTPUT.PUT_LINE('Synchronize PO Field');
	UPDATE AD_Field f
		SET Name = (SELECT e.PO_Name FROM AD_Element e, AD_Column c
					WHERE e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=f.AD_Column_ID),
			Description = (SELECT e.PO_Description FROM AD_Element e, AD_Column c
					WHERE e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=f.AD_Column_ID),
			Help = (SELECT e.PO_Help FROM AD_Element e, AD_Column c
					WHERE e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=f.AD_Column_ID),
			Updated = SysDate
	WHERE f.IsCentrallyMaintained='Y' AND f.IsActive='Y'
	  AND EXISTS (SELECT * FROM AD_Element e, AD_Column c
				WHERE f.AD_Column_ID=c.AD_Column_ID
				  AND c.AD_Element_ID=e.AD_Element_ID AND c.AD_Process_ID IS NULL
				  AND (f.Name <> e.PO_Name OR NVL(f.Description,' ') <> NVL(e.PO_Description,' ') OR NVL(f.Help,' ') <> NVL(e.PO_Help,' '))
				  AND e.PO_Name IS NOT NULL)
	  AND EXISTS (SELECT * FROM AD_Tab t, AD_Window w
				WHERE f.AD_Tab_ID=t.AD_Tab_ID
				  AND t.AD_Window_ID=w.AD_Window_ID
				  AND w.IsSOTrx='N');
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);

	--	Field Translations
	DBMS_OUTPUT.PUT_LINE('Synchronize PO Field Translations');
	UPDATE AD_Field_trl trl
		SET Name = (SELECT e.PO_Name FROM AD_Element_trl e, AD_Column c, AD_Field f
					WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID 
					  AND c.AD_Column_ID=f.AD_Column_ID AND f.AD_Field_ID=trl.AD_Field_ID),
			Description = (SELECT e.PO_Description FROM AD_Element_trl e, AD_Column c, AD_Field f
					WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID 
					  AND c.AD_Column_ID=f.AD_Column_ID AND f.AD_Field_ID=trl.AD_Field_ID),
			Help = (SELECT e.PO_Help FROM AD_Element_trl e, AD_Column c, AD_Field f
					WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID 
					  AND c.AD_Column_ID=f.AD_Column_ID AND f.AD_Field_ID=trl.AD_Field_ID),
			IsTranslated = (SELECT e.IsTranslated FROM AD_Element_trl e, AD_Column c, AD_Field f
					WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID 
					  AND c.AD_Column_ID=f.AD_Column_ID AND f.AD_Field_ID=trl.AD_Field_ID),
			Updated = SysDate
	WHERE EXISTS (SELECT * FROM AD_Field f, AD_Element_trl e, AD_Column c
				WHERE trl.AD_Field_ID=f.AD_Field_ID
				  AND f.AD_Column_ID=c.AD_Column_ID
				  AND c.AD_Element_ID=e.AD_Element_ID AND c.AD_Process_ID IS NULL
				  AND trl.AD_Language=e.AD_Language
				  AND f.IsCentrallyMaintained='Y' AND f.IsActive='Y'
				  AND (trl.Name <> e.PO_Name OR NVL(trl.Description,' ') <> NVL(e.PO_Description,' ') OR NVL(trl.Help,' ') <> NVL(e.PO_Help,' '))
				  AND e.PO_Name IS NOT NULL)
	  AND EXISTS (SELECT * FROM AD_Field f, AD_Tab t, AD_Window w
				WHERE trl.AD_Field_ID=f.AD_Field_ID
				  AND f.AD_Tab_ID=t.AD_Tab_ID
				  AND t.AD_Window_ID=w.AD_Window_ID
				  AND w.IsSOTrx='N');
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);


	--	Fields from Process
	DBMS_OUTPUT.PUT_LINE('Synchronize Field from Process');
	UPDATE AD_Field f
		SET Name = (SELECT p.Name FROM AD_Process p, AD_Column c WHERE p.AD_Process_ID=c.AD_Process_ID
					AND c.AD_Column_ID=f.AD_Column_ID),
			Description = (SELECT p.Description FROM AD_Process p, AD_Column c WHERE p.AD_Process_ID=c.AD_Process_ID
					AND c.AD_Column_ID=f.AD_Column_ID),
			Help = (SELECT p.Help FROM AD_Process p, AD_Column c WHERE p.AD_Process_ID=c.AD_Process_ID
					AND c.AD_Column_ID=f.AD_Column_ID),
			Updated = SysDate
	WHERE f.IsCentrallyMaintained='Y' AND f.IsActive='Y' 
	  AND EXISTS (SELECT * FROM AD_Process p, AD_Column c
				WHERE c.AD_Process_ID=p.AD_Process_ID AND f.AD_Column_ID=c.AD_Column_ID
				AND (f.Name<>p.Name OR NVL(f.Description,' ')<>NVL(p.Description,' ') OR NVL(f.Help,' ')<>NVL(p.Help,' ')));
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);

	--	Field Translations from Process
	DBMS_OUTPUT.PUT_LINE('Synchronize Field Trl from Process Trl');
	UPDATE AD_Field_trl trl
		SET Name = (SELECT p.Name FROM AD_Process_trl p, AD_Column c, AD_Field f 
					WHERE p.AD_Process_ID=c.AD_Process_ID AND c.AD_Column_ID=f.AD_Column_ID
					AND f.AD_Field_ID=trl.AD_Field_ID AND p.AD_Language=trl.AD_Language),
			Description = (SELECT p.Description FROM AD_Process_trl p, AD_Column c, AD_Field f 
					WHERE p.AD_Process_ID=c.AD_Process_ID AND c.AD_Column_ID=f.AD_Column_ID
					AND f.AD_Field_ID=trl.AD_Field_ID AND p.AD_Language=trl.AD_Language),
			Help = (SELECT p.Help FROM AD_Process_trl p, AD_Column c, AD_Field f 
					WHERE p.AD_Process_ID=c.AD_Process_ID AND c.AD_Column_ID=f.AD_Column_ID
					AND f.AD_Field_ID=trl.AD_Field_ID AND p.AD_Language=trl.AD_Language),
			IsTranslated = (SELECT p.IsTranslated FROM AD_Process_trl p, AD_Column c, AD_Field f 
					WHERE p.AD_Process_ID=c.AD_Process_ID AND c.AD_Column_ID=f.AD_Column_ID
					AND f.AD_Field_ID=trl.AD_Field_ID AND p.AD_Language=trl.AD_Language),
			Updated = SysDate
	WHERE EXISTS (SELECT * FROM AD_Process_Trl p, AD_Column c, AD_Field f
				WHERE c.AD_Process_ID=p.AD_Process_ID AND f.AD_Column_ID=c.AD_Column_ID
				AND f.AD_Field_ID=trl.AD_Field_ID AND p.AD_Language=trl.AD_Language
				AND f.IsCentrallyMaintained='Y' AND f.IsActive='Y'
				AND (trl.Name<>p.Name OR NVL(trl.Description,' ')<>NVL(p.Description,' ') OR NVL(trl.Help,' ')<>NVL(p.Help,' ')));
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);

	--	Sync Parameter ColumnName
	UPDATE	AD_Process_Para f
		SET	ColumnName = (SELECT e.ColumnName FROM AD_Element e
					WHERE UPPER(e.ColumnName)=UPPER(f.ColumnName))
	WHERE f.IsCentrallyMaintained='Y' AND f.IsActive='Y'
	  AND EXISTS (SELECT * FROM AD_Element e
		WHERE UPPER(e.ColumnName)=UPPER(f.ColumnName)
		AND e.ColumnName<>f.ColumnName);


	--	Paramenter Fields
	UPDATE	AD_Process_Para p
	  SET	IsCentrallyMaintained = 'N'
	WHERE	IsCentrallyMaintained <> 'N'
	  AND NOT EXISTS (SELECT * FROM AD_Element e WHERE p.ColumnName=e.ColumnName); 

	--	Parameter Fields
	DBMS_OUTPUT.PUT_LINE('Synchronize Process Parameter');
	UPDATE AD_Process_Para f
		SET Name = (SELECT e.Name FROM AD_Element e
					WHERE e.ColumnName=f.ColumnName),
			Description = (SELECT e.Description FROM AD_Element e
					WHERE e.ColumnName=f.ColumnName),
			Help = (SELECT e.Help FROM AD_Element e
					WHERE e.ColumnName=f.ColumnName),
			Updated = SysDate
	WHERE f.IsCentrallyMaintained='Y' AND f.IsActive='Y'
	  AND EXISTS (SELECT * FROM AD_Element e
				WHERE e.ColumnName=f.ColumnName
				  AND (f.Name <> e.Name OR NVL(f.Description,' ') <> NVL(e.Description,' ') OR NVL(f.Help,' ') <> NVL(e.Help,' ')));
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);

	--	Parameter Translations
	DBMS_OUTPUT.PUT_LINE('Synchronize Process Parameter Trl');
	UPDATE AD_Process_Para_Trl trl
		SET Name = (SELECT et.Name FROM AD_Element_Trl et, AD_Element e, AD_Process_Para f
					WHERE et.AD_Language=trl.AD_Language AND et.AD_Element_ID=e.AD_Element_ID
					  AND e.ColumnName=f.ColumnName AND f.AD_Process_Para_ID=trl.AD_Process_Para_ID),
			Description = (SELECT et.Description FROM AD_Element_Trl et, AD_Element e, AD_Process_Para f
					WHERE et.AD_Language=trl.AD_Language AND et.AD_Element_ID=e.AD_Element_ID
					  AND e.ColumnName=f.ColumnName AND f.AD_Process_Para_ID=trl.AD_Process_Para_ID),
			Help = (SELECT et.Help FROM AD_Element_Trl et, AD_Element e, AD_Process_Para f
					WHERE et.AD_Language=trl.AD_Language AND et.AD_Element_ID=e.AD_Element_ID
					  AND e.ColumnName=f.ColumnName AND f.AD_Process_Para_ID=trl.AD_Process_Para_ID),
			IsTranslated = (SELECT et.IsTranslated FROM AD_Element_Trl et, AD_Element e, AD_Process_Para f
					WHERE et.AD_Language=trl.AD_Language AND et.AD_Element_ID=e.AD_Element_ID
					  AND e.ColumnName=f.ColumnName AND f.AD_Process_Para_ID=trl.AD_Process_Para_ID),
			Updated = SysDate
	WHERE EXISTS (SELECT * FROM AD_Element_Trl et, AD_Element e, AD_Process_Para f
					WHERE et.AD_Language=trl.AD_Language AND et.AD_Element_ID=e.AD_Element_ID
					  AND e.ColumnName=f.ColumnName AND f.AD_Process_Para_ID=trl.AD_Process_Para_ID
					  AND f.IsCentrallyMaintained='Y' AND f.IsActive='Y'
					  AND (trl.Name <> et.Name OR NVL(trl.Description,' ') <> NVL(et.Description,' ') OR NVL(trl.Help,' ') <> NVL(et.Help,' ')));
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);


	--	Workflow Node - Window
	DBMS_OUTPUT.PUT_LINE('Synchronize Workflow Node from Window');
	UPDATE AD_WF_Node n
		SET Name = (SELECT w.Name FROM AD_Window w
					WHERE w.AD_Window_ID=n.AD_Window_ID),
			Description = (SELECT w.Description FROM AD_Window w
					WHERE w.AD_Window_ID=n.AD_Window_ID),
			Help = (SELECT w.Help FROM AD_Window w
					WHERE w.AD_Window_ID=n.AD_Window_ID)
	WHERE n.IsCentrallyMaintained = 'Y'
	  AND EXISTS  (SELECT * FROM AD_Window w
				WHERE w.AD_Window_ID=n.AD_Window_ID
				  AND (w.Name <> n.Name OR NVL(w.Description,' ') <> NVL(n.Description,' ') OR NVL(w.Help,' ') <> NVL(n.Help,' ')));
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);

	--	Workflow Translations - Window
	DBMS_OUTPUT.PUT_LINE('Synchronize Workflow Node Trl from Window Trl');
	UPDATE AD_WF_Node_Trl trl
		SET Name = (SELECT t.Name FROM AD_Window_trl t, AD_WF_Node n
					WHERE trl.AD_WF_Node_ID=n.AD_WF_Node_ID AND n.AD_Window_ID=t.AD_Window_ID
					  AND trl.AD_Language=t.AD_Language),
			Description = (SELECT t.Description FROM AD_Window_trl t, AD_WF_Node n
					WHERE trl.AD_WF_Node_ID=n.AD_WF_Node_ID AND n.AD_Window_ID=t.AD_Window_ID
					  AND trl.AD_Language=t.AD_Language),
			Help = (SELECT t.Help FROM AD_Window_trl t, AD_WF_Node n
					WHERE trl.AD_WF_Node_ID=n.AD_WF_Node_ID AND n.AD_Window_ID=t.AD_Window_ID
					  AND trl.AD_Language=t.AD_Language)
	WHERE EXISTS (SELECT * FROM AD_Window_Trl t, AD_WF_Node n
				WHERE trl.AD_WF_Node_ID=n.AD_WF_Node_ID AND n.AD_Window_ID=t.AD_Window_ID
				  AND trl.AD_Language=t.AD_Language AND n.IsCentrallyMaintained='Y' AND n.IsActive='Y'
				  AND (trl.Name <> t.Name OR NVL(trl.Description,' ') <> NVL(t.Description,' ') OR NVL(trl.Help,' ') <> NVL(t.Help,' ')));
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);

	--	Workflow Node - Form
	DBMS_OUTPUT.PUT_LINE('Synchronize Workflow Node from Form');
	UPDATE AD_WF_Node n
		SET (Name, Description, Help) = (SELECT f.Name, f.Description, f.Help 
				FROM AD_Form f
				WHERE f.AD_Form_ID=n.AD_Form_ID)
	WHERE n.IsCentrallyMaintained = 'Y'
	  AND EXISTS  (SELECT * FROM AD_Form f
				WHERE f.AD_Form_ID=n.AD_Form_ID
				  AND (f.Name <> n.Name OR NVL(f.Description,' ') <> NVL(n.Description,' ') OR NVL(f.Help,' ') <> NVL(n.Help,' ')));
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);

	--	Workflow Translations - Form
	DBMS_OUTPUT.PUT_LINE('Synchronize Workflow Node Trl from Form Trl');
	UPDATE AD_WF_Node_Trl trl
		SET (Name, Description, Help) = (SELECT t.Name, t.Description, t.Help
			FROM AD_Form_trl t, AD_WF_Node n
			WHERE trl.AD_WF_Node_ID=n.AD_WF_Node_ID AND n.AD_Form_ID=t.AD_Form_ID
			  AND trl.AD_Language=t.AD_Language)
	WHERE EXISTS (SELECT * FROM AD_Form_Trl t, AD_WF_Node n
				WHERE trl.AD_WF_Node_ID=n.AD_WF_Node_ID AND n.AD_Form_ID=t.AD_Form_ID
				  AND trl.AD_Language=t.AD_Language AND n.IsCentrallyMaintained='Y' AND n.IsActive='Y'
				  AND (trl.Name <> t.Name OR NVL(trl.Description,' ') <> NVL(t.Description,' ') OR NVL(trl.Help,' ') <> NVL(t.Help,' ')));
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);

	--	Workflow Node - Report
	DBMS_OUTPUT.PUT_LINE('Synchronize Workflow Node from Process');
	UPDATE AD_WF_Node n
		SET (Name, Description, Help) = (SELECT f.Name, f.Description, f.Help 
				FROM AD_Process f
				WHERE f.AD_Process_ID=n.AD_Process_ID)
	WHERE n.IsCentrallyMaintained = 'Y'
	  AND EXISTS  (SELECT * FROM AD_Process f
				WHERE f.AD_Process_ID=n.AD_Process_ID
				  AND (f.Name <> n.Name OR NVL(f.Description,' ') <> NVL(n.Description,' ') OR NVL(f.Help,' ') <> NVL(n.Help,' ')));
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);

	--	Workflow Translations - Form
	DBMS_OUTPUT.PUT_LINE('Synchronize Workflow Node Trl from Process Trl');
	UPDATE AD_WF_Node_Trl trl
		SET (Name, Description, Help) = (SELECT t.Name, t.Description, t.Help
			FROM AD_Process_trl t, AD_WF_Node n
			WHERE trl.AD_WF_Node_ID=n.AD_WF_Node_ID AND n.AD_Process_ID=t.AD_Process_ID
			  AND trl.AD_Language=t.AD_Language)
	WHERE EXISTS (SELECT * FROM AD_Process_Trl t, AD_WF_Node n
				WHERE trl.AD_WF_Node_ID=n.AD_WF_Node_ID AND n.AD_Process_ID=t.AD_Process_ID
				  AND trl.AD_Language=t.AD_Language AND n.IsCentrallyMaintained='Y' AND n.IsActive='Y'
				  AND (trl.Name <> t.Name OR NVL(trl.Description,' ') <> NVL(t.Description,' ') OR NVL(trl.Help,' ') <> NVL(t.Help,' ')));
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);

    --  Need centrally maintained flag here!
	DBMS_OUTPUT.PUT_LINE('Synchronize PrintFormatItem Name from Element');
	UPDATE AD_PrintFormatItem pfi
	  SET Name = (SELECT e.Name 
		FROM AD_Element e, AD_Column c
		WHERE e.AD_Element_ID=c.AD_Element_ID
		  AND c.AD_Column_ID=pfi.AD_Column_ID)
	WHERE pfi.IsCentrallyMaintained='Y'
      AND EXISTS (SELECT * 
		FROM AD_Element e, AD_Column c
		WHERE e.AD_Element_ID=c.AD_Element_ID
		  AND c.AD_Column_ID=pfi.AD_Column_ID
		  AND e.Name<>pfi.Name)
	  AND EXISTS (SELECT * FROM AD_Client 
		WHERE AD_Client_ID=pfi.AD_Client_ID AND IsMultiLingualDocument='Y');
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);

	DBMS_OUTPUT.PUT_LINE('Synchronize PrintFormatItem PrintName from Element');
	UPDATE AD_PrintFormatItem pfi
	  SET PrintName = (SELECT e.PrintName 
		FROM AD_Element e, AD_Column c
		WHERE e.AD_Element_ID=c.AD_Element_ID
		  AND c.AD_Column_ID=pfi.AD_Column_ID)
	WHERE pfi.IsCentrallyMaintained='Y'
      AND EXISTS (SELECT * 
		FROM AD_Element e, AD_Column c, AD_PrintFormat pf
		WHERE e.AD_Element_ID=c.AD_Element_ID
		  AND c.AD_Column_ID=pfi.AD_Column_ID
		  AND LENGTH(pfi.PrintName) > 0
		  AND e.PrintName<>pfi.PrintName
		  AND pf.AD_PrintFormat_ID=pfi.AD_PrintFormat_ID
		  AND pf.IsForm='N' AND IsTableBased='Y')
	  AND EXISTS (SELECT * FROM AD_Client 
		WHERE AD_Client_ID=pfi.AD_Client_ID AND IsMultiLingualDocument='Y');
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);

	DBMS_OUTPUT.PUT_LINE('Synchronize PrintFormatItem Trl from Element Trl (Multi-Lingual)');
	UPDATE AD_PrintFormatItem_Trl trl
	  SET PrintName = (SELECT e.PrintName 
		FROM AD_Element_Trl e, AD_Column c, AD_PrintFormatItem pfi
		WHERE e.AD_Language=trl.AD_Language
		  AND e.AD_Element_ID=c.AD_Element_ID
		  AND c.AD_Column_ID=pfi.AD_Column_ID
		  AND pfi.AD_PrintFormatItem_ID=trl.AD_PrintFormatItem_ID)
	WHERE EXISTS (SELECT * 
		FROM AD_Element_Trl e, AD_Column c, AD_PrintFormatItem pfi, AD_PrintFormat pf
		WHERE e.AD_Language=trl.AD_Language
		  AND e.AD_Element_ID=c.AD_Element_ID
		  AND c.AD_Column_ID=pfi.AD_Column_ID
		  AND pfi.AD_PrintFormatItem_ID=trl.AD_PrintFormatItem_ID
          AND pfi.IsCentrallyMaintained='Y'
		  AND LENGTH(pfi.PrintName) > 0
		  AND (e.PrintName<>trl.PrintName OR trl.PrintName IS NULL)
		  AND pf.AD_PrintFormat_ID=pfi.AD_PrintFormat_ID 
		  AND pf.IsForm='N' AND IsTableBased='Y')
	  AND EXISTS (SELECT * FROM AD_Client 
		WHERE AD_Client_ID=trl.AD_Client_ID AND IsMultiLingualDocument='Y');
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);

	DBMS_OUTPUT.PUT_LINE('Synchronize PrintFormatItem Trl (Not Multi-Lingual)');
	UPDATE AD_PrintFormatItem_Trl trl
	  SET PrintName = (SELECT pfi.PrintName 
		FROM AD_PrintFormatItem pfi
		WHERE pfi.AD_PrintFormatItem_ID=trl.AD_PrintFormatItem_ID)
	WHERE EXISTS (SELECT * 
		FROM AD_PrintFormatItem pfi, AD_PrintFormat pf
		WHERE pfi.AD_PrintFormatItem_ID=trl.AD_PrintFormatItem_ID
          AND pfi.IsCentrallyMaintained='Y'
		  AND LENGTH(pfi.PrintName) > 0
		  AND pfi.PrintName<>trl.PrintName
		  AND pf.AD_PrintFormat_ID=pfi.AD_PrintFormat_ID 
		  AND pf.IsForm='N' AND pf.IsTableBased='Y')
	  AND EXISTS (SELECT * FROM AD_Client 
		WHERE AD_Client_ID=trl.AD_Client_ID AND IsMultiLingualDocument='N');
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);

	DBMS_OUTPUT.PUT_LINE('Reset PrintFormatItem Trl where not used in base table');
	UPDATE AD_PrintFormatItem_Trl trl
	  SET PrintName = NULL
	WHERE PrintName IS NOT NULL
	  AND EXISTS (SELECT *
		FROM AD_PrintFormatItem pfi
		WHERE pfi.AD_PrintFormatItem_ID=trl.AD_PrintFormatItem_ID 
          AND pfi.IsCentrallyMaintained='Y'
		  AND (LENGTH (pfi.PrintName) = 0 OR pfi.PrintName IS NULL));
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);

/**
SELECT 	e.PrintName "Element", pfi.PrintName "FormatItem", trl.AD_Language, trl.PrintName "Trl"
FROM 	AD_Element e
  INNER JOIN AD_Column c ON (e.AD_Element_ID=c.AD_Element_ID)
  INNER JOIN AD_PrintFormatItem pfi ON (c.AD_Column_ID=pfi.AD_Column_ID)
  INNER JOIN AD_PrintFormatItem_Trl trl ON (pfi.AD_PrintFormatItem_ID=trl.AD_PrintFormatItem_ID)
WHERE pfi.AD_PrintFormatItem_ID=?
**/

	--	Sync Names - Window
	DBMS_OUTPUT.PUT_LINE('Synchronizing Menu with Window');
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
	DBMS_OUTPUT.PUT_LINE('Synchronizing Menu with Processes');
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
	DBMS_OUTPUT.PUT_LINE('Synchronizing Menu with Forms');
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
	DBMS_OUTPUT.PUT_LINE('Synchronizing Menu with Workflows');
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
	DBMS_OUTPUT.PUT_LINE('Synchronizing Menu with Tasks');
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

    --  Column Name + Element
	DBMS_OUTPUT.PUT_LINE('Synchronizing Column with Element');
    UPDATE AD_Column c
      SET (Name,Description,Help) = 
        (SELECT e.Name,e.Description,e.Help 
        FROM AD_Element e WHERE c.AD_Element_ID=e.AD_Element_ID)
    WHERE EXISTS 
        (SELECT * FROM AD_Element e 
        WHERE c.AD_Element_ID=e.AD_Element_ID
          AND c.Name<>e.Name);
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);
    UPDATE AD_Column_Trl ct
      SET Name = (SELECT e.Name
        FROM AD_Column c INNER JOIN AD_Element_Trl e ON (c.AD_Element_ID=e.AD_Element_ID)
        WHERE ct.AD_Column_ID=c.AD_Column_ID AND ct.AD_Language=e.AD_Language)
    WHERE EXISTS 
        (SELECT * FROM AD_Column c INNER JOIN AD_Element_Trl e ON (c.AD_Element_ID=e.AD_Element_ID)
        WHERE ct.AD_Column_ID=c.AD_Column_ID AND ct.AD_Language=e.AD_Language
          AND ct.Name<>e.Name);
	DBMS_OUTPUT.PUT_LINE('  trl rows updated: ' || SQL%ROWCOUNT);
    
    
    --  Table Name + Element
	DBMS_OUTPUT.PUT_LINE('Synchronizing Table with Element');
    UPDATE AD_Table t
      SET (Name,Description) = (SELECT e.Name,e.Description FROM AD_Element e 
        WHERE t.TableName||'_ID'=e.ColumnName)
    WHERE EXISTS (SELECT * FROM AD_Element e 
        WHERE t.TableName||'_ID'=e.ColumnName
          AND t.Name<>e.Name);
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);
    UPDATE AD_Table_Trl tt
      SET Name = (SELECT e.Name 
        FROM AD_Table t INNER JOIN AD_Element ex ON (t.TableName||'_ID'=ex.ColumnName)
          INNER JOIN AD_Element_Trl e ON (ex.AD_Element_ID=e.AD_Element_ID)
        WHERE tt.AD_Table_ID=t.AD_Table_ID AND tt.AD_Language=e.AD_Language)
    WHERE EXISTS (SELECT * 
        FROM AD_Table t INNER JOIN AD_Element ex ON (t.TableName||'_ID'=ex.ColumnName)
          INNER JOIN AD_Element_Trl e ON (ex.AD_Element_ID=e.AD_Element_ID)
        WHERE tt.AD_Table_ID=t.AD_Table_ID AND tt.AD_Language=e.AD_Language
          AND tt.Name<>e.Name);
	DBMS_OUTPUT.PUT_LINE('  trl rows updated: ' || SQL%ROWCOUNT);

    --  Trl Table Name + Element
    UPDATE AD_Table t
      SET (Name,Description) = (SELECT e.Name||' Trl', e.Description 
        FROM AD_Element e 
        WHERE SUBSTR(t.TableName,1,LENGTH(t.TableName)-4)||'_ID'=e.ColumnName)
    WHERE TableName LIKE '%_Trl'
      AND EXISTS (SELECT * FROM AD_Element e 
        WHERE SUBSTR(t.TableName,1,LENGTH(t.TableName)-4)||'_ID'=e.ColumnName
          AND t.Name<>e.Name);
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);
    UPDATE AD_Table_Trl tt
      SET Name = (SELECT e.Name || ' **'
        FROM AD_Table t INNER JOIN AD_Element ex ON (SUBSTR(t.TableName,1,LENGTH(t.TableName)-4)||'_ID'=ex.ColumnName)
          INNER JOIN AD_Element_Trl e ON (ex.AD_Element_ID=e.AD_Element_ID)
        WHERE tt.AD_Table_ID=t.AD_Table_ID AND tt.AD_Language=e.AD_Language)
    WHERE EXISTS (SELECT * 
        FROM AD_Table t INNER JOIN AD_Element ex ON (SUBSTR(t.TableName,1,LENGTH(t.TableName)-4)||'_ID'=ex.ColumnName)
          INNER JOIN AD_Element_Trl e ON (ex.AD_Element_ID=e.AD_Element_ID)
        WHERE tt.AD_Table_ID=t.AD_Table_ID AND tt.AD_Language=e.AD_Language
          AND t.TableName LIKE '%_Trl'
          AND tt.Name<>e.Name);
	DBMS_OUTPUT.PUT_LINE('  trl rows updated: ' || SQL%ROWCOUNT);

    /** Remaining tables
    SELECT Name, TableName FROM AD_Table t WHERE Name=TableName ORDER BY 1
    **/


<<FINISH_PROCESS>>
	IF (p_PInstance_ID IS NOT NULL) THEN
		--  Update AD_PInstance
		DBMS_OUTPUT.PUT_LINE('Updating PInstance - Finished ' || v_Message);
		UPDATE	AD_PInstance
		SET Updated = SysDate,
			IsProcessing = 'N',
			Result = v_Result,			-- 1=success
			ErrorMsg = v_Message
		WHERE	AD_PInstance_ID=p_PInstance_ID;
	END IF;
	COMMIT;
	RETURN;

EXCEPTION
	WHEN  OTHERS THEN
		v_ResultStr := v_ResultStr || ': ' || SQLERRM || ' - ' || v_Message;
		DBMS_OUTPUT.PUT_LINE(v_ResultStr);
		ROLLBACK;
		IF (p_PInstance_ID IS NOT NULL) THEN
			UPDATE	AD_PInstance
			SET Updated = SysDate,
				IsProcessing = 'N',
				Result = 0,				-- failure
				ErrorMsg = v_ResultStr
			WHERE	AD_PInstance_ID=p_PInstance_ID;
			COMMIT;
		END IF;
		RETURN;

END AD_Synchronize;
/
