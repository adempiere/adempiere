/**
 *	C_ValidCombination_Check
 *	- Check for duplicate values and de-activate them
 *	- Try to delete de-activated duplicates
 */
BEGIN
--	Checking Duplicates
DECLARE
	CURSOR	Cur_Duplicates IS
		SELECT	Combination 
		FROM	C_ValidCombination
		WHERE	IsActive='Y'
		GROUP BY Combination 
		HAVING COUNT(*) > 1;
	CURSOR	Cur_Records (dupCombination VARCHAR) IS
		SELECT	*
		FROM	C_ValidCombination 
		WHERE 	Combination = dupCombination
		  AND	IsActive='Y'
		ORDER BY Created
		FOR UPDATE;
	--
	IsFirst					BOOLEAN;
	NoDupl					NUMBER;		
BEGIN
	DBMS_OUTPUT.PUT_LINE('Checking for Duplicates ...');
	FOR d IN Cur_Duplicates LOOP
		DBMS_OUTPUT.PUT_LINE('  Duplicate=' || d.Combination);
		IsFirst := TRUE;
		NoDupl := 0;
		FOR r IN Cur_Records (d.Combination) LOOP
			IF (IsFirst) THEN
				IsFirst := FALSE;
		   	ELSE
				UPDATE	C_ValidCombination
				  SET	IsActive='N'
				WHERE	CURRENT OF Cur_Records;
				NoDupl := NoDupl + 1;
			END IF;
		END LOOP;
		DBMS_OUTPUT.PUT_LINE('    #' || NoDupl);
		COMMIT;
	END LOOP;
END;
--	Deleting Duplicates
DECLARE
	CURSOR	Cur_Duplicates IS
		SELECT	Combination 
		FROM	C_ValidCombination
		GROUP BY Combination 
		HAVING COUNT(*) > 1;
	CURSOR	Cur_Records (dupCombination VARCHAR) IS
		SELECT	*
		FROM	C_ValidCombination 
		WHERE 	Combination = dupCombination
		  AND	IsActive='N'
		ORDER BY Created
		FOR UPDATE;
	--
	NoDel					NUMBER;	
	NoNotDel				NUMBER;
BEGIN
	DBMS_OUTPUT.PUT_LINE('Deleting old Duplicates ...');
	FOR d IN Cur_Duplicates LOOP
		DBMS_OUTPUT.PUT_LINE('  Duplicate=' || d.Combination);
		NoDel := 0;
		NoNotDel := 0;
		FOR r IN Cur_Records (d.Combination) LOOP
			BEGIN
				DELETE C_ValidCombination
				WHERE C_ValidCombination_ID=r.C_ValidCombination_ID;
				NoDel := NoDel + 1;
			EXCEPTION
				WHEN OTHERS THEN 
					NoNotDel := NoNotDel + 1;
			END;
		END LOOP;
		DBMS_OUTPUT.PUT_LINE('    deleted=' || NoDel || ', not=' || NoNotDel);
		COMMIT;
	END LOOP;
END;
--
END;

/***
--  Merge ElementValue_Old to ElementValue_New
--  
DECLARE
    v_ElementValue_Old  NUMBER(10) := 354;
    v_ElementValue_New  NUMBER(10) := 508;
    
    v_count_Old          NUMBER;
    v_count_New          NUMBER;
BEGIN
    DBMS_OUTPUT.PUT_LINE ('ElementValue ' || v_ElementValue_Old || ' to ' || v_ElementValue_New);

    --   Get Counts
    SELECT    COUNT(*)
      INTO    v_count_Old
    FROM      C_ValidCombination vc
    WHERE     Account_ID = v_ElementValue_Old;
    SELECT    COUNT(*)
      INTO    v_count_New
    FROM      C_ValidCombination vc8
    WHERE     Account_ID = v_ElementValue_New;
    DBMS_OUTPUT.PUT_LINE ('Count Old=' || v_count_Old || ', New=' || v_count_New);
    
    --   Old Not Used
    IF (v_count_Old = 0) THEN
         --   Correct Facts
         UPDATE    Fact_Acct
           SET     Account_ID = v_ElementValue_New
         WHERE Account_ID = v_ElementValue_Old;
         --   Delete Value
         DELETE C_ElementValue WHERE C_ElementValue_ID = v_ElementValue_Old;
         --
         COMMIT;
         DBMS_OUTPUT.PUT_LINE ('  Not used');
         RETURN;
    END IF;

    --   Old Used and New Exists
    IF (v_count_New <> 0) THEN
         --   Correct Facts
         UPDATE    Fact_Acct
           SET     Account_ID = v_ElementValue_New
         WHERE     Account_ID = v_ElementValue_Old;
         --   Update Account
         UPDATE    C_ValidCombination
           SET     Account_ID = v_ElementValue_New
         WHERE     Account_ID = v_ElementValue_Old;
         --   Delete Value
         DELETE C_ElementValue WHERE C_ElementValue_ID = v_ElementValue_Old;
         --
         COMMIT;
         DBMS_OUTPUT.PUT_LINE ('  Combination Changed');
         RETURN;
    END IF;
    
    --   Delete potential duplicates
    DBMS_OUTPUT.PUT_LINE ('-----');

END;
/
***/
