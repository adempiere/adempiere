@ECHO OFF
REM
REM Name:		RUN_ImportReference.bat
REM Description:script to import Reference database
REM Created:	2010-05-11
REM Vendor:		K.K. Alice
REM Author:		Stefan Christians
REM
REM FileTarget:	~/development/sandbox/adempiere/utils/RUN_ImportReference.bat
REM FileOwner:	spc.dvp
REM FilePerms:	0644
REM

SETLOCAL ENABLEDELAYEDEXPANSION

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


REM local variables
SET curDir=%~dp0
CALL :NORMALIZE curDir
SET importAdempiereScript=%curDir%RUN_ImportAdempiere.bat

REM execute the general import script
IF NOT EXIST "%importAdempiereScript%" GOTO :NOIMPSCRIPT
CALL "%importAdempiereScript%" "reference"
SET result=%ERRORLEVEL%
GOTO :IMPDONE
:NOIMPSCRIPT
ECHO.
ECHO Import Reference
ECHO.
ECHO The script %importAdempiereScript% could not be run.
ECHO Please make sure it exists and you have execute permissions.
ECHO.
SET result=%errorGeneral%
:IMPDONE

REM end of script
EXIT /B %result%

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
