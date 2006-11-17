/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: AD_Field_ReSeq.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 ***
 * Title:	Resequence Window Tab Fields
 * Description:
 ************************************************************************/
DECLARE
	CURSOR CUR_Win	IS
		SELECT 	AD_Window_ID, Name
		FROM 	AD_Window
        ORDER BY 1;
	--	The Tab cursor
	CURSOR Cur_Tab (Win_ID NUMBER)	IS
		SELECT 	AD_Tab_ID, Name
		FROM 	AD_Tab
		WHERE	AD_Window_ID=Win_ID
		ORDER BY SeqNo
		FOR UPDATE OF SeqNo;
	--	The Field cursor
	CURSOR Cur_Field (Tab_ID NUMBER) IS
		SELECT 	AD_Field_ID
		FROM	AD_Field
		WHERE	AD_Tab_ID=Tab_ID
		ORDER BY SeqNo
		FOR UPDATE OF SeqNo;
	--
	NewSeqNo 	NUMBER;
	NewTabNo	NUMBER;
BEGIN
	--	Window Loop
	FOR w IN CUR_Win LOOP
		DBMS_OUTPUT.PUT(w.Name);
		NewTabNo := 0;
		--	Tab Loop
		FOR ct IN Cur_Tab (w.AD_Window_ID) LOOP
			DBMS_OUTPUT.PUT('  ' || ct.Name);
			NewTabNo := NewTabNo + 10;
			UPDATE	AD_Tab
			  SET	SeqNo = NewTabNo
		   	WHERE CURRENT OF Cur_Tab;
			--
			NewSeqNo := 0;
			FOR cf IN Cur_Field(ct.AD_Tab_ID) LOOP
				NewSeqNo := NewSeqNo + 10;
				UPDATE 	AD_FIELD
				SET		SeqNo = NewSeqNo
				WHERE CURRENT OF Cur_Field;	
			END LOOP;	--	AD_Field inner loop
			DBMS_OUTPUT.PUT_LINE(' ... ' || NewSeqNo);
		END LOOP;	--	Tab
	END LOOP;	--	 Window
END;	--	Resequence AD_Field

COMMIT;
