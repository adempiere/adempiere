CREATE OR REPLACE PROCEDURE AD_PrintPaper_Default
(
	p_AD_PInstance_ID    		IN NUMBER
)
/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: AD_PrintPaper_Default.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 ***
 * Title:	Set Current Format as Default
 * Description:
 ************************************************************************/
AS
	--	Logistice
	v_ResultStr						VARCHAR2(2000);
	v_Message						VARCHAR2(2000);
	p_Record_ID						NUMBER;
	--	Parameter
	CURSOR Cur_Parameter (PInstance NUMBER) IS
		SELECT i.Record_ID, p.ParameterName, p.P_String, p.P_Number, p.P_Date
		FROM AD_PInstance i, AD_PInstance_Para p
		WHERE i.AD_PInstance_ID=PInstance
		AND i.AD_PInstance_ID=p.AD_PInstance_ID(+)
		ORDER BY p.SeqNo;
	--	Parameter Variables
	p_AD_Client_ID					NUMBER := NULL;

BEGIN
    --  Update AD_PInstance
	DBMS_OUTPUT.PUT_LINE('Updating PInstance - Processing ' || p_AD_PInstance_ID);
    v_ResultStr := 'PInstanceNotFound';
    UPDATE AD_PInstance
    SET Created = SysDate,
        IsProcessing = 'Y'
    WHERE AD_PInstance_ID=p_AD_PInstance_ID;
    COMMIT;

	--	Get Parameters
	v_ResultStr := 'ReadingParameters';
	FOR p IN Cur_Parameter (p_AD_PInstance_ID) LOOP
		p_Record_ID := p.Record_ID;
		IF (p.ParameterName = 'AD_Client_ID') THEN
 			p_AD_Client_ID := p.P_Number;
			DBMS_OUTPUT.PUT_LINE('  AD_Client_ID=' || p_AD_Client_ID);
		ELSE
			DBMS_OUTPUT.PUT_LINE('*** Unknown Parameter=' || p.ParameterName);
	 	END IF;
	END LOOP;	--	Get Parameter
	DBMS_OUTPUT.PUT_LINE('  Record_ID=' || p_Record_ID);


	v_ResultStr := 'Updating';
	UPDATE	AD_PrintFormat pf
	  SET	AD_PrintPaper_ID = p_Record_ID
	WHERE	(AD_Client_ID = p_AD_Client_ID OR p_AD_Client_ID IS NULL)
	  AND EXISTS (SELECT * FROM AD_PrintPaper pp
 	  	WHERE 	pf.AD_PrintPaper_ID=pp.AD_PrintPaper_ID
		  AND	IsLandscape = (SELECT IsLandscape FROM AD_PrintPaper 
		  		WHERE AD_PrintPaper_ID=p_Record_ID));
	v_Message := '@Copied@=' || SQL%ROWCOUNT;

<<FINISH_PROCESS>>
	--  Update AD_PInstance
	DBMS_OUTPUT.PUT_LINE('Updating PInstance - Finished ' || v_Message);
    UPDATE  AD_PInstance
    SET Updated = SysDate,
        IsProcessing = 'N',
        Result = 1,                 -- success
        ErrorMsg = v_Message
    WHERE   AD_PInstance_ID=p_AD_PInstance_ID;
    COMMIT;
    RETURN;

EXCEPTION
    WHEN  OTHERS THEN
		v_ResultStr := v_ResultStr || ': ' || SQLERRM || ' - ' || v_Message;
		DBMS_OUTPUT.PUT_LINE(v_ResultStr);
        UPDATE  AD_PInstance
        SET Updated = SysDate,
            IsProcessing = 'N',
            Result = 0,             -- failure
            ErrorMsg = v_ResultStr
        WHERE   AD_PInstance_ID=p_AD_PInstance_ID;
        COMMIT;
        RETURN;

END AD_PrintPaper_Default;
/
