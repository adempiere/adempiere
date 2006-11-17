CREATE OR REPLACE PROCEDURE AD_Sequence_Next 
(
	p_TableName		IN	VARCHAR2,
	p_ID			IN	NUMBER,
	p_NextNo		OUT	NUMBER
 )
AS
/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: AD_Sequence_Next.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 ***
 * Title:	Get the next sequence number of TableName
 * Description:
 *		store in parameter p_NextNo
 *		if ID < 1000000, use System Doc Sequence
 ************************************************************************/

	v_NextNoSys		NUMBER;
	v_ResultStr		VARCHAR(255);
BEGIN
	v_ResultStr := 'Read';
	SELECT CurrentNext, CurrentNextSys
	  INTO p_NextNo, v_NextNoSys
	FROM AD_Sequence
	WHERE Name = p_TableName
	  AND IsActive = 'Y'
	  AND IsTableID = 'Y'
	  AND IsAutoSequence = 'Y'
	FOR UPDATE OF CurrentNext, CurrentNextSys;

	v_ResultStr := 'Write';
	IF (v_NextNoSys <> -1 AND p_ID < 1000000) THEN	--	System No
		UPDATE 	AD_Sequence
		  SET 	CurrentNextSys = CurrentNextSys + IncrementNo,
				Updated = SysDate
		WHERE 	Name = p_TableName;
		p_NextNo := v_NextNoSys;
	ELSE						--	Standard No
		UPDATE 	AD_Sequence
		  SET	CurrentNext = CurrentNext + IncrementNo,
				Updated = SysDate
		WHERE Name = p_TableName;
	END IF;

EXCEPTION
  WHEN NO_DATA_FOUND THEN
	RAISE_APPLICATION_ERROR (-20100, 'Table Sequence not found ');
-- 		|| v_ResultStr || ': ' || p_TableName);

END AD_Sequence_Next;
/
