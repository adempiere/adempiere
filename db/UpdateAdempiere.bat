@Title Update local Adempiere DB
@Rem	$Id: UpdateAdempiere.bat,v 1.4 2004/11/01 06:06:16 jjanke Exp $

@dir database\DatabaseBuild.sql

@Echo	requires manual entry of exit

sqlplus adempiere/adempiere @database\DatabaseBuild.sql

sqlplus adempiere/adempiere @maintain\Maintenance\DBA_Recompile_Run.sql