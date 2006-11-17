/**
 *	PeriodControl Check
 *	- Make sure that for all periods, we have all BaseDocTypes
 */
DECLARE
	CURSOR	CUR_Periods	IS
		SELECT	C_Period_ID, AD_Client_ID, AD_Org_ID, Name
		FROM	C_Period
		ORDER BY 2,1;
BEGIN
	--	For all Periods
	FOR p IN CUR_Periods LOOP
		DBMS_OUTPUT.PUT_LINE(p.Name);
		DECLARE
			v_NextNo				NUMBER;
			--
		 	CURSOR	CUR_DocType IS
				SELECT	Value
				FROM	AD_Ref_List
				WHERE 	AD_Reference_ID=183
				  AND 	Value NOT IN 
				  			(SELECT DocBaseType FROM C_PeriodControl pc
							WHERE 	pc.C_Period_ID = p.C_Period_ID)
				ORDER BY 1;
		BEGIN
			--	Missing Base Document Types
			FOR dt IN CUR_DocType LOOP
				DBMS_OUTPUT.PUT_LINE('  ' || dt.Value);
				AD_Sequence_Next('C_PeriodControl', p.AD_Client_ID, v_NextNo);
				INSERT INTO C_PeriodControl
					(C_PeriodControl_ID,
					AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
					C_Period_ID, DocBaseType,
					PeriodStatus, PeriodAction, Processing)
				VALUES
					(v_NextNo,
					p.AD_Client_ID, p.AD_Org_ID, 'Y', SysDate, 0, SysDate, 0,
					p.C_Period_ID, dt.Value,
					'N', 'N', NULL);	--	NeverOpened, NoAction
			END LOOP;
		END;
	END LOOP;
END;
