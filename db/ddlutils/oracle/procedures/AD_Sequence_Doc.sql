CREATE OR REPLACE PROCEDURE AD_Sequence_Doc 
(
	p_SequenceName	IN	VARCHAR2,
	p_AD_Client_ID	IN	NUMBER,
	o_DocumentNo	OUT VARCHAR2
 )
AS
/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: AD_Sequence_Doc.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 ***
 * Title:	Get the next DocumentNo of TableName
 * Description:
 *		store in parameter o_DocumentNo
 *		if ID < 1000000, use System Doc Sequence
 ************************************************************************/
	v_NextNo			NUMBER;
	v_NextNoSys			NUMBER;
	v_Prefix			VARCHAR2(30);
	v_Suffix			VARCHAR2(30);
BEGIN
	SELECT	CurrentNext, CurrentNextSys, Prefix, Suffix
	  INTO	v_NextNo, v_NextNoSys, v_Prefix, v_Suffix
	FROM	AD_Sequence
	WHERE	Name = p_SequenceName
	  AND	IsActive = 'Y'
	  AND	IsTableID = 'N'
	  AND	IsAutoSequence = 'Y'
	  AND	AD_Client_ID = p_AD_Client_ID
	FOR UPDATE OF CurrentNext, CurrentNextSys;

	IF (v_NextNoSys <> -1 AND p_AD_Client_ID < 1000000) THEN	--	System No
		UPDATE	AD_Sequence
		  SET	CurrentNextSys = CurrentNextSys + IncrementNo,
				Updated = SysDate
		WHERE	Name = p_SequenceName;
		o_DocumentNo := NVL(v_Prefix, '') || v_NextNoSys || NVL(v_Suffix, '');
	ELSE								--	Standard No
		UPDATE	AD_Sequence
		  SET	CurrentNext = CurrentNext + IncrementNo,
				Updated = SysDate
		WHERE	Name = p_SequenceName;
		o_DocumentNo := NVL(v_Prefix, '') || v_NextNo || NVL(v_Suffix, '');
	END IF;

EXCEPTION
	WHEN NO_DATA_FOUND THEN
		RAISE_APPLICATION_ERROR (-20100, 'Document Sequence not found - ' || p_SequenceName);

END AD_Sequence_Doc;
/
