CREATE OR REPLACE PROCEDURE Cleanup
/******************************************************************************
 * ** Adempiere Product **             Copyright (c) 1999-2001 Accorto, Inc. USA
 * Open  Source  Software        Provided "AS IS" without warranty or liability
 * When you use any parts (changed or unchanged), add  "Powered by Adempiere" to
 * your product name;  See license details http://www.adempiere.org/license.html
 ******************************************************************************
 *	Cleanup old temporary data
 */
AS
BEGIN
	--	Processes
	DELETE FROM AD_PInstance;
	--	Search Info
	DELETE FROM AD_Find;
	--	Errors older than 1 week
	DELETE AD_ERROR WHERE Created < SysDate-7;
	--
	COMMIT;
END Cleanup;
/
