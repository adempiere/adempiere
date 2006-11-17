@Title Update local Reference DB
@Rem	$Id: UpdateReference.bat,v 1.1 2003/10/11 05:24:26 jjanke Exp $

@dir database\DatabaseBuild.sql

@Echo	requires manual entry of exit

sqlplus reference/reference @database\DatabaseBuild.sql

sqlplus reference/reference @maintain\Maintenance\DBA_Recompile_Run.sql