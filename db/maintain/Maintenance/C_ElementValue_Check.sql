/**	
 *	C_ElementValue_Check.sql
 *
 *	For C_ElementValue create  
 *	-	Missing Translations
 *	-	Missing ElementValue Tree Structure(s)
 */
BEGIN
    DBMS_OUTPUT.PUT_LINE('Adding to ElementValue Tree');
	DECLARE
		CURSOR	Cur_Tree IS
			SELECT  *
        	FROM    C_Element WHERE AD_Tree_ID IS NOT NULL;
		CURSOR Cur_ElementValue	(Element NUMBER, Tree NUMBER) IS
			SELECT *
			FROM C_ElementValue
			WHERE C_ElementValue_ID NOT IN 
				(SELECT Node_ID FROM AD_TreeNode WHERE AD_Tree_ID=Tree)
			AND C_Element_ID=Element;
	BEGIN
		FOR CT IN Cur_Tree LOOP
		    DBMS_OUTPUT.PUT_LINE('  For Tree ' || CT.AD_Tree_ID || '; Element=' || CT.C_Element_ID);
			--
			FOR CP IN Cur_ElementValue (CT.C_Element_ID, CT.AD_Tree_ID) LOOP
	         	INSERT INTO AD_TreeNode
       		        (AD_Client_ID, AD_Org_ID,
               		IsActive, Created, CreatedBy, Updated, UpdatedBy,
	                AD_Tree_ID, Node_ID, Parent_ID, SeqNo)
       		    VALUES
               		(CP.AD_Client_ID, CP.AD_Org_ID, 
	                CP.IsActive, CP.Created, CP.CreatedBy, CP.Updated, CP.UpdatedBy,
       		        CT.AD_Tree_ID, CP.C_ElementValue_ID, 0, 999);
	            DBMS_OUTPUT.PUT_LINE('    added: ' || CP.NAME);
       		END LOOP;	-- ElementValue Loop
		END LOOP;	--	Tree Loop
	END;	-- Adding to Tree


    DBMS_OUTPUT.PUT_LINE('Adding missing Translations');
    INSERT INTO C_ElementValue_Trl (C_ElementValue_ID, AD_Language, AD_Client_ID, AD_Org_ID,
        IsActive, Created, CreatedBy, Updated, UpdatedBy,
        Name, IsTranslated)
    SELECT m.C_ElementValue_ID, l.AD_Language, m.AD_Client_ID, m.AD_Org_ID,
        m.IsActive, m.Created, m.CreatedBy, m.Updated, m.UpdatedBy,
        m.Name, 'N'
    FROM    C_ElementValue m, AD_Language l
   	WHERE	l.IsActive = 'Y' AND l.IsSystemLanguage = 'Y'
	  AND	C_ElementValue_ID || AD_Language NOT IN 
		(SELECT C_ElementValue_ID || AD_Language FROM C_ElementValue_Trl);
	DBMS_OUTPUT.PUT_LINE('Rows added: ' || SQL%ROWCOUNT);

    COMMIT;
END;