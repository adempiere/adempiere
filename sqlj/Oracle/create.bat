@ECHO OFF
REM
REM Name:		create.bat
REM Description:script to load SQLJ into oracle
REM Created:	2010-07-04
REM Vendor:		K.K. Alice
REM Author:		Stefan Christians
REM
REM FileTarget:	~/development/sandbox/adempiere/sqlj/oracle/create.bat
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

@Rem Create Oracle SQLJ
@Rem Author + Copyright 1999-2005 Jorg Janke
@Rem $Id: create.bat,v 1.8 2005/05/31 07:28:22 jjanke Exp $
@Rem
@Rem Parameter: <adempiereDBuser>/<adempiereDBpassword>
@Rem

@Echo .
@Echo Load Oracle SQLJ ...
@SET CLASSPATH=
@call loadjava -user %1@%ADEMPIERE_DB_SERVER%/%ADEMPIERE_DB_NAME% -verbose -force -resolve %ADEMPIERE_HOME%\lib\sqlj.jar

@Echo .

@Echo Create Oracle Functions ...
@sqlplus %1@%ADEMPIERE_DB_SERVER%/%ADEMPIERE_DB_NAME% @%ADEMPIERE_HOME%\utils\oracle\createSQLJ.sql

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

REM make sure the sqlj libraries exist
IF EXIST "%dbSqljFile%" GOTO :SQLJOK
ECHO The library file %dbSqljFile% could not be loaded.
ECHO Please make sure it exists and you have read permissions.
EXIT /B %errorNoSqljFile%	
:SQLJOK

REM load oracle sqlj
ECHO Load Oracle SQLJ ...
SET oldCP=%CLASSPATH%
SET CLASSPATH=
CALL loadjava -user %dbUser%/%dbPwd%@%dbHost%:%dbPort%/%dbName% -verbose -force -resolve "%dbSqljFile%"
IF ERRORLEVEL 1 GOTO :SQLJERROR
GOTO :SQLJNOERROR
:SQLJERROR
ECHO loadjava failed with exit code %ERRORLEVEL%
SET CLASSPATH=%oldCP%
SET oldCP=
EXIT /B %errorImportSqlj%
:SQLJNOERROR
SET CLASSPATH=%oldCP%
SET oldCP=

REM create SQLJ functions
SET curDir=%~dp0
CALL :NORMALIZE curDir
IF NOT EXIST "%curDir%\createSQLJ.sql" GOTO :FUNCNOERROR
ECHO Create SQLJ Functions ...
CALL sqlplus -S %dbUser%/%dbPwd%@%dbHost%:%dbPort%/%dbName% @"%curDir%\createSQLJ.sql"
IF ERRORLEVEL 1 GOTO :FUNCERROR
GOTO :FUNCNOERROR
ECHO create SQLJ functions failed with exit code %ERRORLEVEL%
EXIT /B %errorImportSqlj%
:FUNCNOERROR

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
