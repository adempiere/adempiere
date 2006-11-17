/**
 *	Cursor Test
 *	$Id: cursors.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 */
DECLARE
	--	Global Variables
	v_Name			AD_Client.Name%TYPE;
	--	Counter
	v_no			NUMBER;
BEGIN

	--	========== Implicit Cursor ==========
	v_no := 0;
	DECLARE
		-- The Cursor - The table has a Name and a Description column
		CURSOR cur_client	IS
			SELECT	* 
			FROM	AD_Client
			ORDER BY Name;
	BEGIN
		FOR ptr_client IN cur_client LOOP
			v_no := v_no + 1;			--	Counter
			v_Name := ptr_client.Name;	--	Reading data into local variable
	   	END LOOP;
		DBMS_OUTPUT.PUT_LINE('Implicit Cursor #=' || v_no);
	END;


	--	========== Explicit Cursor (1) ==========
	v_no := 0;
	DECLARE
		-- The Cursor - The table has a Name and a Description column
		CURSOR cur_client	IS
			SELECT	* 
			FROM	AD_Client
			ORDER BY Name;
	   	-- 
	--	ptr_client	cur_client%ROWTYPE;	--	could be used too
		ptr_client	AD_Client%ROWTYPE;	--	all columns in cursor - so the same
	BEGIN
		OPEN cur_client;
		LOOP
			FETCH cur_client INTO ptr_client;
			EXIT WHEN cur_client%NOTFOUND;	--	leaves loop
			v_no := v_no + 1;			--	Counter
			v_Name := ptr_client.Name;	--	Reading data into local variable
 		END LOOP;
		CLOSE cur_client;
		DBMS_OUTPUT.PUT_LINE('Explicit Cursor (1) #=' || v_no);
	END;


	--	========== Explicit Cursor (2) ==========
	v_no := 0;
	DECLARE
		-- The Cursor - The table has a Name and a Description column
		CURSOR cur_client	IS
			SELECT	Name, Description
			FROM	AD_Client
			ORDER BY Name;
	   	-- 
		v_Description		AD_Client.Description%TYPE;
	BEGIN
		OPEN cur_client;
		LOOP
			FETCH cur_client INTO v_Name, v_Description;
			EXIT WHEN cur_client%NOTFOUND;	--	leaves loop
			v_no := v_no + 1;			--	Counter
			--	v_Name has data already
 		END LOOP;
		CLOSE cur_client;
		DBMS_OUTPUT.PUT_LINE('Explicit Cursor (2) #=' || v_no);
	END;


	--	========== Explicit Cursor (3) ==========
	v_no := 0;
	DECLARE
		-- The Cursor - The table has a Name and a Description column
		CURSOR cur_client	IS
			SELECT	Name, Description
			FROM	AD_Client
			ORDER BY Name;
	   	-- 
		TYPE t_client IS RECORD
		(
			Name		AD_Client.Name%TYPE,
			Description	AD_Client.Description%TYPE
		);
		ptr_client	t_client;
	BEGIN
		OPEN cur_client;
		LOOP
			FETCH cur_client INTO ptr_client;
			EXIT WHEN cur_client%NOTFOUND;	--	leaves loop
			v_no := v_no + 1;			--	Counter
			v_Name := ptr_client.Name;
 		END LOOP;
		CLOSE cur_client;
		DBMS_OUTPUT.PUT_LINE('Explicit Cursor (3) #=' || v_no);
	END;


	--	========== Explicit Cursor (4) ==========
	v_no := 0;
	DECLARE
		TYPE t_client IS REF CURSOR RETURN AD_Client%ROWTYPE;	-- strong
		cur_client t_client;
		ptr_client AD_Client%ROWTYPE;
	BEGIN
		OPEN cur_client FOR	
			SELECT	*
			FROM	AD_Client
			ORDER BY Name;
		LOOP
			FETCH cur_client INTO ptr_client;
			EXIT WHEN cur_client%NOTFOUND;	--	leaves loop
			v_no := v_no + 1;			--	Counter
			v_Name := ptr_client.Name;
 		END LOOP;
		CLOSE cur_client;
		DBMS_OUTPUT.PUT_LINE('Explicit Cursor (4) #=' || v_no);
	END;


	--	========== Explicit Cursor (5) ==========
	v_no := 0;
	DECLARE
		TYPE t_client IS REF CURSOR;		--	weak cursor variable
		cur_client t_client;
		v_Description		AD_Client.Description%TYPE;
	BEGIN
		OPEN cur_client FOR	
			SELECT	Name, Description
			FROM	AD_Client
			ORDER BY Name;
		LOOP
			FETCH cur_client INTO v_Name, v_Description;
			EXIT WHEN cur_client%NOTFOUND;	--	leaves loop
			v_no := v_no + 1;			--	Counter
			--	v_Name has data already
 		END LOOP;
		CLOSE cur_client;
		DBMS_OUTPUT.PUT_LINE('Explicit Cursor (5) #=' || v_no);
	END;


	--	========== Explicit Cursor (6) ==========
	v_no := 0;
	DECLARE
		TYPE t_client IS REF CURSOR;
		cur_client t_client;
		v_Description		AD_Client.Description%TYPE;
		v_cmd				VARCHAR2(2000) := 'SELECT Name, Description '
			|| 'FROM AD_Client '
			|| 'ORDER BY Name';
	BEGIN
		OPEN cur_client FOR	v_cmd;
		LOOP
			FETCH cur_client INTO v_Name, v_Description;
			EXIT WHEN cur_client%NOTFOUND;	--	leaves loop
			v_no := v_no + 1;			--	Counter
			--	v_Name has data already
 		END LOOP;
		CLOSE cur_client;
		DBMS_OUTPUT.PUT_LINE('Explicit Cursor (6) #=' || v_no);
	END;

	--	=======================================================================

	--	========== Implicit Cursor with Parameter ==========
	v_no := 0;
	DECLARE
		-- The Cursor - The table has a Name and a Description column
		CURSOR cur_client (par_length NUMBER)	IS
			SELECT	* 
			FROM	AD_Client
			WHERE	LENGTH(Name) > par_length
			ORDER BY Name;
	BEGIN
		FOR ptr_client IN cur_client(10) LOOP
			v_no := v_no + 1;			--	Counter
			v_Name := ptr_client.Name;	--	Reading data into local variable
	   	END LOOP;
		DBMS_OUTPUT.PUT_LINE('Implicit Cursor #=' || v_no);
	END;


	--	========== Explicit Cursor (1) ==========
	v_no := 0;
	DECLARE
		-- The Cursor - The table has a Name and a Description column
		CURSOR cur_client (par_length NUMBER)	IS
			SELECT	* 
			FROM	AD_Client
			WHERE	LENGTH(Name) > par_length
			ORDER BY Name;
	   	-- 
	--	ptr_client	cur_client%ROWTYPE;	--	could be used too
		ptr_client	AD_Client%ROWTYPE;	--	all columns in cursor - so the same
	BEGIN
		OPEN cur_client(10);
		LOOP
			FETCH cur_client INTO ptr_client;
			EXIT WHEN cur_client%NOTFOUND;	--	leaves loop
			v_no := v_no + 1;			--	Counter
			v_Name := ptr_client.Name;	--	Reading data into local variable
 		END LOOP;
		CLOSE cur_client;
		DBMS_OUTPUT.PUT_LINE('Explicit Cursor (1) #=' || v_no);
	END;


	--	========== Explicit Cursor (6) ==========
	v_no := 0;
	DECLARE
		TYPE t_client IS REF CURSOR;
		cur_client t_client;
		v_Description		AD_Client.Description%TYPE;
		v_cmd				VARCHAR2(2000) := 'SELECT Name, Description '
			|| 'FROM AD_Client '
			|| 'WHERE LENGTH(Name) > :par '
			|| 'ORDER BY Name';
	 	v_parameter			NUMBER := 10;
	BEGIN
		OPEN cur_client FOR	v_cmd USING v_parameter;
		LOOP
			FETCH cur_client INTO v_Name, v_Description;
			EXIT WHEN cur_client%NOTFOUND;	--	leaves loop
			v_no := v_no + 1;			--	Counter
			--	v_Name has data already
 		END LOOP;
		CLOSE cur_client;
		DBMS_OUTPUT.PUT_LINE('Explicit Cursor (6) #=' || v_no);
	END;

END;
