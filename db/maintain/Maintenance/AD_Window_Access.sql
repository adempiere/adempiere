/**
 *	Fill AD_Window_Access for all Roles
 *	---------------------------------------------------------------------------
 *	SCO# Levels			S__ 100		4	System info
 *						SCO	111		7	System shared info
 *						SC_ 110		6	System/Client info
 *						_CO	011		3	Client shared info
 *						__O	001		1	Organization info
 *	Roles:
 *		S		4,7,6
 *		_CO		7,6,3,1
 *		__O		3,1,7
 */
DECLARE
	CURSOR	Cur_Role	IS
		SELECT * FROM AD_Role;
BEGIN

	FOR r IN Cur_Role LOOP
		DBMS_OUTPUT.PUT_LINE('Role: ' || r.Name || ', Level=' || r.UserLevel);
		--	--
		IF (r.UserLevel='S') THEN
			DELETE AD_Window_Access
			WHERE AD_Role_ID = r.AD_Role_ID;
			--
			INSERT INTO AD_Window_Access
				(AD_Window_ID, AD_Role_ID,
				AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
				IsReadWrite)
			SELECT DISTINCT w.AD_Window_ID, r.AD_Role_ID,
				r.AD_CLIENT_ID, r.AD_ORG_ID, 'Y', SysDate, 0, SysDate, 0,
				'Y'
			FROM	AD_Window w, AD_Tab t, AD_Table tt
			WHERE	w.AD_Window_ID=t.AD_Window_ID
			  AND	t.AD_Table_ID=tt.AD_Table_ID
			  AND	tt.AccessLevel IN ('4','7','6')
			  AND	t.SeqNo=(SELECT MIN(SeqNo) FROM AD_Tab xt -- only check first tab
					WHERE xt.AD_Window_ID=w.AD_Window_ID)
			  AND NOT EXISTS (SELECT * FROM AD_Window_Access wa
				WHERE wa.AD_Window_ID=w.AD_Window_ID AND wa.AD_Role_ID=r.AD_Role_ID);
			DBMS_OUTPUT.PUT_LINE(' > Windows ' || SQL%ROWCOUNT);
		--	--
		ELSIF (r.UserLevel=' CO') THEN
			DELETE AD_Window_Access
			WHERE AD_Role_ID = r.AD_Role_ID;
			--
			INSERT INTO AD_Window_Access
				(AD_Window_ID, AD_Role_ID,
				AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
				IsReadWrite)
			SELECT DISTINCT w.AD_Window_ID, r.AD_Role_ID,
				r.AD_CLIENT_ID, r.AD_ORG_ID, 'Y', SysDate, 0, SysDate, 0,
				'Y'
			FROM	AD_Window w, AD_Tab t, AD_Table tt
			WHERE	w.AD_Window_ID=t.AD_Window_ID
			  AND	t.AD_Table_ID=tt.AD_Table_ID
			  AND	tt.AccessLevel IN ('7','6','2','3','1')
			  AND	t.SeqNo=(SELECT MIN(SeqNo) FROM AD_Tab xt -- only check first tab
					WHERE xt.AD_Window_ID=w.AD_Window_ID)
			  AND NOT EXISTS (SELECT * FROM AD_Window_Access wa
				WHERE wa.AD_Window_ID=w.AD_Window_ID AND wa.AD_Role_ID=r.AD_Role_ID);
			DBMS_OUTPUT.PUT_LINE(' > Windows ' || SQL%ROWCOUNT);
		--	--
		ELSIF (r.UserLevel='  O') THEN
			DELETE AD_Window_Access
			WHERE AD_Role_ID = r.AD_Role_ID;
			--
			INSERT INTO AD_Window_Access
				(AD_Window_ID, AD_Role_ID,
				AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
				IsReadWrite)
			SELECT DISTINCT w.AD_Window_ID, r.AD_Role_ID,
				r.AD_CLIENT_ID, r.AD_ORG_ID, 'Y', SysDate, 0, SysDate, 0,
				'Y'
			FROM	AD_Window w, AD_Tab t, AD_Table tt
			WHERE	w.AD_Window_ID=t.AD_Window_ID
			  AND	t.AD_Table_ID=tt.AD_Table_ID
			  AND	tt.AccessLevel IN ('3','1','7')
			  AND	t.SeqNo=(SELECT MIN(SeqNo) FROM AD_Tab xt -- only check first tab
					WHERE xt.AD_Window_ID=w.AD_Window_ID)
			  AND NOT EXISTS (SELECT * FROM AD_Window_Access wa
				WHERE wa.AD_Window_ID=w.AD_Window_ID AND wa.AD_Role_ID=r.AD_Role_ID);
			DBMS_OUTPUT.PUT_LINE(' > Windows ' || SQL%ROWCOUNT);
		END IF;
		--
		COMMIT;
	END LOOP;
END;

