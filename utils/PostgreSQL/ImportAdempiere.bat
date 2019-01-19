@ECHO OFF
REM
REM Name:		ImportAdempiere.bat
REM Description:script to import database into postgresql
REM Created:	2010-05-11
REM Vendor:		K.K. Alice
REM Author:		Stefan Christians
REM
REM FileTarget:	~/development/sandbox/adempiere/utils/postgresql/ImportAdempiere.bat
REM FileOwner:	spc.dvp
REM FilePerms:	0644
REM

SETLOCAL ENABLEDELAYEDEXPANSION

REM if 12 parameters are passed, all information we need is included in the parameters
REM and we can run the "new style" script.
REM otherwise just execute the "old" code to ensure backward compatibility

SET argCount=0
FOR %%a IN (%*) DO SET /A argCount+=1
IF %argCount%==12 GOTO :NEWSCRIPT

REM begin original "old style" script --->>>

@Echo	Adempiere Database Import		$Revision: 1.3 $

@Rem $Id: ImportAdempiere.bat,v 1.3 2005/01/22 21:59:15 jjanke Exp $

@Echo	Importing Adempiere DB from %ADEMPIERE_HOME%\data\Adempiere_pg.dmp (%ADEMPIERE_DB_NAME%)

@if (%ADEMPIERE_HOME%) == () goto environment
@if (%ADEMPIERE_DB_NAME%) == () goto environment
@if (%ADEMPIERE_DB_SERVER%) == () goto environment
@if (%ADEMPIERE_DB_PORT%) == () goto environment
@Rem Must have parameters systemAccount AdempiereID AdempierePwd
@if (%1) == () goto usage
@if (%2) == () goto usage
@if (%3) == () goto usage

@set PGPASSWORD=%4
@echo -------------------------------------
@echo Re-Create user and database
@echo -------------------------------------
@dropdb -h %ADEMPIERE_DB_SERVER% -p %ADEMPIERE_DB_PORT% -U postgres %ADEMPIERE_DB_NAME%
@dropuser -h %ADEMPIERE_DB_SERVER% -p %ADEMPIERE_DB_PORT% -U postgres %2
@set ADEMPIERE_CREATE_ROLE_SQL=CREATE ROLE %2 SUPERUSER LOGIN PASSWORD '%3'
@psql -h %ADEMPIERE_DB_SERVER% -p %ADEMPIERE_DB_PORT% -U postgres -c "%ADEMPIERE_CREATE_ROLE_SQL%"
@set ADEMPIERE_CREATE_ROLE_SQL=

@set PGPASSWORD=%3
@createdb -h %ADEMPIERE_DB_SERVER% -p %ADEMPIERE_DB_PORT% -E UNICODE -O %2 -U %2 %ADEMPIERE_DB_NAME%

@echo -------------------------------------
@echo Import Adempiere_pg.dmp
@echo -------------------------------------
@set ADEMPIERE_ALTER_ROLE_SQL=ALTER ROLE %2 SET search_path TO adempiere, sqlj, pg_catalog
@psql -h %ADEMPIERE_DB_SERVER% -p %ADEMPIERE_DB_PORT% -d %ADEMPIERE_DB_NAME% -U %2 -c "%ADEMPIERE_ALTER_ROLE_SQL%"
@psql -h %ADEMPIERE_DB_SERVER% -p %ADEMPIERE_DB_PORT% -d %ADEMPIERE_DB_NAME% -U %2 -c "drop schema sqlj cascade"
@psql -h %ADEMPIERE_DB_SERVER% -p %ADEMPIERE_DB_PORT% -d %ADEMPIERE_DB_NAME% -U %2 -f %ADEMPIERE_HOME%/data/Adempiere_pg.dmp
@set ADEMPIERE_ALTER_ROLE_SQL=


@set PGPASSWORD=
@goto end

:environment
@Echo Please make sure that the enviroment variables are set correctly:
@Echo		ADEMPIERE_HOME	e.g. D:\ADEMPIERE2
@Echo		ADEMPIERE_DB_NAME 	e.g. adempiere or xe
@Echo		ADEMPIERE_DB_SERVER 	e.g. dbserver.adempiere.org
@Echo		ADEMPIERE_DB_PORT 	e.g. 5432 or 1521

:usage
@echo Usage:		%0 <systemAccount> <AdempiereID> <AdempierePwd> <PostgresPwd>
@echo Example:	%0 postgres Adempiere Adempiere postgresPwd

:end

REM <<<--- end original "old style" script
GOTO :EOF
:NEWSCRIPT


REM NEW STYLE SCRIPT STARTS HERE
REM ============================

REM exit codes:
SET errorSuccess=0
SET errorGeneral=1
SET errorNoEnvironment=10
SET errorNoSeedFile=11
SET errorNoImportScript=12
SET errorNoSqljFile=13
SET errorWrongVendor=20
SET errorDropDatabase=30
SET errorCreateDatabase=31
SET errorRenameDatabase=32
SET errorModifyDatabase=33
SET errorDropUser=40
SET errorCreateUser=41
SET errorRenameUser=42
SET errorModifyUser=43
SET errorDropSchema=50
SET errorCreateSchema=51
SET errorRenameSchema=52
SET errorModifySchema=53
SET errorImportData=60
SET errorImportSqlj=61

REM set local variables
SET argCount=0
:ARGLOOP
SET /A argCount+=1
IF %argCount%==13 GOTO :ENDARGLOOP
SET arg=%1
CALL :NORMALIZE arg
IF %argCount%==1 SET dbVendor=%arg%
IF %argCount%==2 SET dbHost=%arg%
IF %argCount%==3 SET dbPort=%arg%
IF %argCount%==4 SET dbUser=%arg%
IF %argCount%==5 SET dbPwd=%arg%
IF %argCount%==6 SET sysUser=%arg%
IF %argCount%==7 SET sysPwd=%arg%
IF %argCount%==8 SET dbName=%arg%
IF %argCount%==9 SET dbCatalog=%arg%
IF %argCount%==10 SET dbSchema=%arg%
IF %argCount%==11 SET dbSeedFile=%arg%
IF %argCount%==12 SET dbSqljFile=%arg%
SHIFT /1
GOTO :ARGLOOP
:ENDARGLOOP

REM make sure this script is called for the correct vendor
IF "%dbVendor%"=="PostgreSQL" GOTO :VENDOROK
ECHO wrong vendor
EXIT /B %errorWrongVendor%
:VENDOROK

REM drop existing database
REM (allowed to fail in case database does not exist)
ECHO drop database "%dbName%"
SET PGPASSWORD=%sysPwd%
dropdb -h %dbHost% -p %dbPort% -U %sysUser% %dbName% 2> NUL
SET PGPASSWORD=

REM drop existing user
REM (allowed to fail in case user does not exist)
ECHO drop user "%dbUser%"
SET PGPASSWORD=%sysPwd%
dropuser -h %dbHost% -p %dbPort% -U %sysUser% %dbUser% 2> NUL
SET PGPASSWORD=

REM recreate user
REM (using psql instead of createuser so that we can supply user's password)
REM (Allowed to fail if user role still exists - may be associated with other databases.)
ECHO recreate user "%dbUser%"
SET PGPASSWORD=%sysPwd%
psql -h %dbHost% -p %dbPort% -U %sysUser% -c "CREATE ROLE %dbUser% SUPERUSER LOGIN PASSWORD '%dbPwd%'" 1>NUL
SET result=%ERRORLEVEL%
IF %result%==0 GOTO :USERCREATED
IF %result%==1 GOTO :USERCREATED
ECHO create user failed with exit code %result%
EXIT /B %errorCreateUser%
:USERCREATED
SET PGPASSWORD=

REM recreate database
ECHO recreate database "%dbName%"
SET PGPASSWORD=%dbPwd%
createdb -h %dbHost% -p %dbPort% -E UNICODE -O %dbUser% -U %dbUser% %dbName%
SET result=%ERRORLEVEL%
IF %result%==0 GOTO :DBCREATED
ECHO create database failed with exit code %result%
EXIT /B %errorCreateDatabase%
:DBCREATED
SET PGPASSWORD=

REM create plpgsql language
ECHO create language "plpgsql"
SET PGPASSWORD=%dbPwd%
psql -h %dbHost% -p %dbPort% -d %dbName% -U %dbUser% -c "CREATE LANGUAGE plpgsql" 1>NUL 2>&1
SET PGPASSWORD=

REM import database
ECHO ----------------------------------------
ECHO Importing %dbSeedFile%
ECHO ----------------------------------------
SET PGPASSWORD=%dbPwd%
psql -h %dbHost% -p %dbPort% -d %dbName% -U %dbUser% -f "%dbSeedFile%"
SET result=%ERRORLEVEL%
IF %result%==0 GOTO :DBIMPORTED
ECHO import failed with exit code %result%
EXIT /B %errorImportData%
:DBIMPORTED
SET PGPASSWORD=

REM Adempiere_pg.dmp always imports to schema "adempiere" owned by user "adempiere"
REM so we need to rename it to %dbSchema% and change ownership to %dbUser%

REM rename schema
IF "%dbSchema%"=="adempiere" GOTO :NORENAME
ECHO rename schema "adempiere" to "%dbSchema%"
SET PGPASSWORD=%dbPwd%
psql -h %dbHost% -p %dbPort% -d %dbName% -U %dbUser% -c "ALTER SCHEMA adempiere RENAME TO %dbSchema%" 1>NUL
SET result=%ERRORLEVEL%
IF %result%==0 GOTO :SCHEMARENAMED
ECHO rename schema failed with exit code %result%
EXIT /B %errorRenameSchema%
:SCHEMARENAMED
SET PGPASSWORD=
:NORENAME

REM change schema ownership
IF "%dbUser%"=="adempiere" GOTO :NOREOWN
ECHO change schema ownership to "%dbUser%"
REM change all objects in %dbName% database owned by adempiere to ownership of %dbUser%
SET PGPASSWORD=%dbPwd%
psql -h %dbHost% -p %dbPort% -d %dbName% -U %dbUser% -c "REASSIGN OWNED BY adempiere TO %dbUser%" 1>NUL
SET result=%ERRORLEVEL%
IF %result%==0 GOTO :REOWNED
ECHO change schema ownership failed with exit code %result%
EXIT /B %errorModifySchema%
:REOWNED
SET PGPASSWORD=
:NOREOWN

REM set searchpath
ECHO set default schema to "%dbSchema%"
SET PGPASSWORD=%dbPwd%
psql -h %dbHost% -p %dbPort% -d %dbName% -U %dbUser% -c "ALTER ROLE %dbUser% SET search_path TO %dbSchema%, pg_catalog" 1>NUL
SET result=%ERRORLEVEL%
IF %result%==0 GOTO :SEARCHPATHSET
ECHO set default schema failed with exit code %result%
EXIT /B %errorModifyUser%
:SEARCHPATHSET
SET PGPASSWORD=

REM done
EXIT /B %errorSuccess%


GOTO :EOF



:NORMALIZE
REM function to normalize variable contents
REM parameter 1 : name of variable to normalize

REM load contents of variable
SET nrmContent=!%~1%!

REM remove surrounding quotes
ECHO.%nrmContent%|FindStr/brv ""^">NUL:&&Goto :nrmJ1
ECHO.%nrmContent%|FindStr/erv ""^">NUL:&&Goto :nrmJ1
SET nrmContent=####%nrmContent%####
SET nrmContent=%nrmContent:####"=%
SET nrmContent=%nrmContent:"####=%
:nrmJ1

REM return normalized variable contents
IF "%~1" NEQ "" SET "%~1=%nrmContent%"
SET nrmContent=
GOTO :EOF
