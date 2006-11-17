CREATE OR REPLACE PROCEDURE AD_Sequence_DocType
(
	p_DocType_ID		IN	NUMBER,
	p_ID				IN	NUMBER,
	p_DocumentNo		OUT	VARCHAR2
 )
AS
/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: AD_Sequence_DocType.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 ***
 * Title:	Get the next DocumentNo of Document Type
 * Description:
 *		store in parameter p_DocumentNo
 *		If ID < 1000000, use System Doc Sequence
 *		If no Document Sequence is defined, return null !
 *			Use AD_Sequence_Doc('DocumentNo_myTable',.. to get it directly
 ************************************************************************/

	v_NextNo			NUMBER;
	v_NextNoSys			NUMBER;
	v_Sequence_ID		NUMBER	:= NULL;
	v_Prefix			VARCHAR2(30);
	v_Suffix			VARCHAR2(30);
BEGIN
	--	Is a document Sequence defined and valid?
	BEGIN
		SELECT	DocNoSequence_ID
		  INTO	v_Sequence_ID
		FROM	C_DocType
		WHERE	C_DocType_ID=p_DocType_ID	--	parameter
		  AND	IsDocNoControlled='Y'
		  AND	IsActive='Y';
	EXCEPTION
		WHEN OTHERS THEN
			NULL;
	END;
    
	IF (v_Sequence_ID IS NULL) THEN		--	No Sequence Number
		p_DocumentNo := '';				--	Return NULL
		DBMS_OUTPUT.PUT_LINE('[AD_Sequence_DocType: not found - C_DocType_ID=' || p_DocType_ID || ']');
		RETURN;
	END IF;

	--	Get the numbers
	SELECT	s.AD_Sequence_ID, s.CurrentNext, s.CurrentNextSys, s.Prefix, s.Suffix
	  INTO	v_Sequence_ID, v_NextNo, v_NextNoSys, v_Prefix, v_Suffix
	FROM	C_DocType d, AD_Sequence s
	WHERE	d.C_DocType_ID=p_DocType_ID	--	parameter
	  AND	d.DocNoSequence_ID=s.AD_Sequence_ID
	  AND	s.IsActive = 'Y'
	  AND	s.IsTableID = 'N'
	  AND	s.IsAutoSequence = 'Y'
	FOR UPDATE OF CurrentNext, CurrentNextSys;

	IF (v_NextNoSys <> -1 AND p_ID < 1000000) THEN	--	System No
		UPDATE	AD_Sequence
		  SET	CurrentNextSys = CurrentNextSys + IncrementNo
		WHERE	AD_Sequence_ID = v_Sequence_ID;
		p_DocumentNo := NVL(v_Prefix, '') || v_NextNoSys || NVL(v_Suffix, '');
	ELSE						--	Standard No
		UPDATE AD_Sequence
		  SET CurrentNext = CurrentNext + IncrementNo
		WHERE AD_Sequence_ID = v_Sequence_ID;
		p_DocumentNo := NVL(v_Prefix, '') || v_NextNo || NVL(v_Suffix, '');
	END IF;
--	DBMS_OUTPUT.PUT_LINE(p_DocumentNo);

EXCEPTION
	WHEN NO_DATA_FOUND THEN
		RAISE_APPLICATION_ERROR (-20100, 'AD_Sequence_DocType: not found - DocType_ID='
			|| p_DocType_ID || ', Sequence_ID=' || v_Sequence_ID);

END AD_Sequence_DocType;
/
