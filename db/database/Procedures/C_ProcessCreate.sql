IF EXISTS (SELECT * FROM sysobjects WHERE type = 'P' AND name = 'C_ProcessCreate')
	BEGIN
		PRINT '<<< DROPPING PROCEDURE C_ProcessCreate >>>'
		DROP Procedure C_ProcessCreate
	END
go
------------------------------------------------------------------------------
CREATE Procedure C_ProcessCreate
(
	@AD_Table_ID		Identifier,
	@Record_ID			Identifier,
	@AD_IProcess_ID		Identifier OUTPUT
)
--WITH ENCRYPTION
AS
/******************************************************************************
 *	Author:		Jorg Janke (c) ClassApps 1999 
 *	Version:	$Header: /cvs/adempiere/db/database/Procedures/C_ProcessCreate.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 *
 *	Description:	
 *		Create process for background execution
 *
 *	Return values:	
 *		Int_Process_ID
 *
 *	Called by:   	
 *
 ******************************************************************************/
BEGIN
	Set NoCount ON
	BEGIN TRANSACTION

	SELECT	@AD_IProcess_ID = ISNULL(MAX(AD_IProcess_ID), 0)+1 
	FROM	AD_IProcess
	---------------------------------
	INSERT INTO AD_IProcess
		(AD_IProcess_ID, AD_Table_ID, Record_ID, IsProcessing, Updated)
	VALUES
		(@AD_IProcess_ID, @AD_Table_ID, @Record_ID, 'N', null)

	IF (@@ERROR <> 0)
	BEGIN
		RAISERROR 60001 'Int_Process insert error'
		ROLLBACK TRAN
		RETURN(1)
	END

	COMMIT TRANSACTION
END -- C_ProcessCreate
go
------------------------------------------------------------------------------
IF OBJECT_ID('C_ProcessCreate') IS NOT NULL
    PRINT '<<< CREATED PROCEDURE C_ProcessCreate >>>'
ELSE
    PRINT '<<< FAILED CREATING PROCEDURE C_ProcessCreate >>>'
go