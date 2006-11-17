/**
 *	Access Forms for All
 */
DECLARE
	CURSOR	Cur_Role	IS
		SELECT * FROM AD_Role;
	No			NUMBER;
BEGIN

	FOR r IN Cur_Role LOOP
		DBMS_OUTPUT.PUT_LINE('Role: ' || r.Name || ', Level=' || r.UserLevel);
		--	--
		IF (r.UserLevel='S') THEN
			DELETE AD_Form_Access
			WHERE AD_Role_ID = r.AD_Role_ID;
			--
			INSERT INTO AD_Form_Access
				(AD_Form_ID, AD_Role_ID,
				AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
				IsReadWrite)
			SELECT DISTINCT p.AD_Form_ID, r.AD_Role_ID,
				r.AD_CLIENT_ID, r.AD_ORG_ID, 'Y', SysDate, 0, SysDate, 0,
				'Y'
			FROM	AD_Form p
			WHERE	p.AccessLevel IN ('4','7','6');
			No := SQL%ROWCOUNT;
			DBMS_OUTPUT.PUT_LINE(' > System Forms ' || No);
		--	--
		ELSIF (r.UserLevel=' CO') THEN
			DELETE AD_Form_Access
			WHERE AD_Role_ID = r.AD_Role_ID;
			--
			INSERT INTO AD_Form_Access
				(AD_Form_ID, AD_Role_ID,
				AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
				IsReadWrite)
			SELECT DISTINCT p.AD_Form_ID, r.AD_Role_ID,
				r.AD_CLIENT_ID, r.AD_ORG_ID, 'Y', SysDate, 0, SysDate, 0,
				'Y'
			FROM	AD_Form p
			WHERE	p.AccessLevel IN ('7','6','3','2','1');
			No := SQL%ROWCOUNT;
			DBMS_OUTPUT.PUT_LINE(' > Client/Org Forms ' || No);
		--	--
		ELSIF (r.UserLevel='  O') THEN
			DELETE AD_Form_Access
			WHERE AD_Role_ID = r.AD_Role_ID;
			--
			INSERT INTO AD_Form_Access
				(AD_Form_ID, AD_Role_ID,
				AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
				IsReadWrite)
			SELECT DISTINCT p.AD_Form_ID, r.AD_Role_ID,
				r.AD_CLIENT_ID, r.AD_ORG_ID, 'Y', SysDate, 0, SysDate, 0,
				'Y'
			FROM	AD_Form p
			WHERE	p.AccessLevel IN ('3','1','7');
			No := SQL%ROWCOUNT;
			DBMS_OUTPUT.PUT_LINE(' > Org Forms ' || No);
		END IF;
		--
		COMMIT;
	END LOOP;
END;
/
