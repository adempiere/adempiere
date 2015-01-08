@ECHO OFF
REM
REM Name:		ImportAdempiere.bat
REM Description:script to import database into oracle
REM Created:	2010-05-12
REM Vendor:		K.K. Alice
REM Author:		Stefan Christians
REM
REM FileTarget:	~/development/sandbox/adempiere/utils/oracle/ImportAdempiere.bat
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
	
@Echo	Adempiere Database Import		$Revision: 1.9 $

@Rem $Id: ImportAdempiere.bat,v 1.9 2005/09/24 01:50:41 jjanke Exp $

@Echo	Importing Adempiere DB from %ADEMPIERE_HOME%\data\Adempiere.dmp (%ADEMPIERE_DB_NAME%)

@if (%ADEMPIERE_HOME%) == () goto environment
@if (%ADEMPIERE_DB_NAME%) == () goto environment
@if (%ADEMPIERE_DB_SERVER%) == () goto environment
@if (%ADEMPIERE_DB_PORT%) == () goto environment
@Rem Must have parameters systemAccount AdempiereID AdempierePwd
@if (%1) == () goto usage
@if (%2) == () goto usage
@if (%3) == () goto usage

@echo -------------------------------------
@echo Re-Create DB user
@echo -------------------------------------
@sqlplus %1@%ADEMPIERE_DB_SERVER%:%ADEMPIERE_DB_PORT%/%ADEMPIERE_DB_NAME% @%ADEMPIERE_HOME%\Utils\%ADEMPIERE_DB_PATH%\CreateUser.sql %2 %3

@echo -------------------------------------
@echo Import Adempiere.dmp
@echo -------------------------------------
@imp %1@%ADEMPIERE_DB_SERVER%:%ADEMPIERE_DB_PORT%/%ADEMPIERE_DB_NAME% FILE=%ADEMPIERE_HOME%\data\Adempiere.dmp FROMUSER=(reference) TOUSER=%2 STATISTICS=RECALCULATE

echo -------------------------------------
echo Create SQLJ 
echo -------------------------------------
call %ADEMPIERE_HOME%\Utils\%ADEMPIERE_DB_PATH%\create %ADEMPIERE_DB_USER%/%ADEMPIERE_DB_PASSWORD%

@echo --------========--------========--------========--------
@echo System Check - The Import phase showed warnings. 
@echo This is OK as long as the following does not show errors
@echo --------========--------========--------========--------
@sqlplus %2/%3@%ADEMPIERE_DB_SERVER%:%ADEMPIERE_DB_PORT%/%ADEMPIERE_DB_NAME% @%ADEMPIERE_HOME%\Utils\%ADEMPIERE_DB_PATH%\AfterImport.sql

@goto end

:environment
@Echo Please make sure that the enviroment variables are set correctly:
@Echo		ADEMPIERE_HOME	e.g. D:\Adempiere
@Echo		ADEMPIERE_DB_NAME	e.g. dev1.adempiere.org

:usage
@echo Usage:		%0 <systemAccount> <AdempiereID> <AdempierePwd>
@echo Example:	%0 system/manager Adempiere Adempiere

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
IF "%dbVendor%"=="oracle" GOTO :VENDOROK 
ECHO wrong vendor
EXIT /B %errorWrongVendor%
:VENDOROK

REM drop existing user
ECHO drop user "%dbUser%"
(ECHO CONNECT %sysUser%/%sysPwd%@%dbHost%:%dbPort%/%dbName%;
ECHO DROP USER %dbUser% CASCADE;
ECHO EXIT; ) | sqlplus -S /nolog > NUL 
SET result=%ERRORLEVEL%
IF %result%==0 GOTO :USERDROPPED
ECHO drop user failed with exit code %result%
EXIT /B %errorDropUser%
:USERDROPPED

REM recreate user 
ECHO recreate user "%dbUser%"
(ECHO CONNECT %sysUser%/%sysPwd%@%dbHost%:%dbPort%/%dbName%;
ECHO CREATE USER %dbUser% IDENTIFIED BY %dbPwd%
ECHO DEFAULT TABLESPACE USERS
ECHO TEMPORARY TABLESPACE TEMP
ECHO PROFILE DEFAULT
ECHO ACCOUNT UNLOCK;
ECHO GRANT CONNECT TO %dbUser%;
ECHO GRANT DBA TO %dbUser%;
ECHO GRANT RESOURCE TO %dbUser%;
ECHO GRANT UNLIMITED TABLESPACE TO %dbUser%;
ECHO ALTER USER %dbUser% DEFAULT ROLE CONNECT, RESOURCE, DBA;
ECHO GRANT CREATE TABLE TO %dbUser%;
ECHO EXIT;) | sqlplus -S /nolog >NUL 
SET result=%ERRORLEVEL%
IF %result%==0 GOTO :USERCREATED
ECHO create user failed with exit code %result%
EXIT /B %errorCreateUser%
:USERCREATED

REM import database
ECHO ----------------------------------------
ECHO Importing %dbSeedFile%
ECHO ----------------------------------------
SET result=0
imp %sysUser%/%sysPwd%@%dbHost%:%dbPort%/%dbName% SILENT=Y FILE='%dbSeedFile%' FROMUSER=(reference) TOUSER=%dbUser%
IF ERRORLEVEL 4 GOTO :SEEDFAILED
GOTO :SEEDIMPORTED
:SEEDFAILED
SET result=%ERRORLEVEL%
ECHO import failed with exit code %result%
EXIT /B %errorImportData%
:SEEDIMPORTED

REM create sqlj
SET curDir=%~dp0
CALL :NORMALIZE curDir
SET createScript=%curDir%create.bat
IF NOT EXIST "%createScript%" GOTO :SQLJCREATED
ECHO ----------------------------------------
ECHO Creating SQLJ
ECHO ----------------------------------------
CALL "%createScript%" "%dbVendor%" "%dbHost%" "%dbPort%" "%dbUser%" "%dbPwd%" "%sysUser%" "%sysPwd%" "%dbName%" "%dbCatalog%" "%dbSchema%" "%dbSeedFile%" "%dbSqljFile%"
SET result=%ERRORLEVEL%
IF %result%==0 GOTO :SQLJCREATED
ECHO create sqlj failed with exit code %result%
EXIT /B %errorImportSqlj%
:SQLJCREATED

REM check system
REM call AfterImport.sql
SET curDir=%~dp0
CALL :NORMALIZE curDir
SET afterScript=%curDir%AfterImport.sql
IF NOT EXIST "%afterScript%" GOTO :AFTERCHECKED
ECHO ----------------------------------------
ECHO Check System
ECHO Import may show some warnings. This is OK as long as the following does not show errors
ECHO ----------------------------------------
sqlplus -S %dbUser%/%dbPwd%@%dbHost%:%dbPort%/%dbName% @"%afterScript%"
SET result=%ERRORLEVEL%
IF %result%==0 GOTO :AFTERCHECKED
ECHO check system failed with exit code %result%
EXIT /B %errorImportData%
:AFTERCHECKED


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
