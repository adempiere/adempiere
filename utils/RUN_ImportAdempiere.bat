@ECHO OFF
REM
REM Name:		RUN_ImportAdempiere.bat
REM Description:script to import Adempiere database
REM Created:	2010-05-11
REM Vendor:		K.K. Alice
REM Author:		Stefan Christians
REM
REM FileTarget:	~/development/sandbox/adempiere/utils/RUN_ImportAdempiere.bat
REM FileOwner:	spc.dvp
REM FilePerms:	0644
REM

REM database binaries are expected to be in the PATH
REM for oracle: sqlplus, imp, loadjava ...
REM for postgresql: psql, dropdb, createdb, dropuser ...

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



REM if called with any argument, import the reference database
REM otherwise import the seed database
SET importMode=Seed
SET argCount=0
FOR %%a IN (%*) DO SET /A argCount+=1
IF NOT %argCount%==0 SET importMode=Reference

REM identify this script
ECHO.
ECHO Import Adempiere %importMode% Database
ECHO.

REM change to directory in which this script resides
SET DIR_SAV=%CD%
CALL :NORMALIZE DIR_SAV
CD %~dp0

REM load environment
IF NOT EXIST myEnvironment.bat GOTO NOENV
CALL myEnvironment.bat nosave > nul
:NOENV

REM set local variables
CALL :NORMALIZE ADEMPIERE_HOME
CALL :NORMALIZE ADEMPIERE_DB_PATH
CALL :NORMALIZE ADEMPIERE_DB_SERVER
CALL :NORMALIZE ADEMPIERE_DB_PORT
CALL :NORMALIZE ADEMPIERE_DB_USER
CALL :NORMALIZE ADEMPIERE_DB_PASSWORD
CALL :NORMALIZE ADEMPIERE_DB_SYSTEM
CALL :NORMALIZE ADEMPIERE_DB_NAME
SET dbImportScript=%ADEMPIERE_DB_PATH%\ImportAdempiere.bat
SET dbSignScript=RUN_SignDatabaseBuild.bat
SET dbSeedFile=
SET dbSqljFile=%ADEMPIERE_HOME%\lib\sqlj.jar
SET dbVendor=%ADEMPIERE_DB_PATH%
SET dbHost=%ADEMPIERE_DB_SERVER%
SET dbPort=%ADEMPIERE_DB_PORT%
SET dbUser=%ADEMPIERE_DB_USER%
SET dbPwd=%ADEMPIERE_DB_PASSWORD%
SET sysUser=
SET sysPwd=%ADEMPIERE_DB_SYSTEM%
SET dbName=%ADEMPIERE_DB_NAME%
SET dbCatalog=
SET dbSchema=%ADEMPIERE_DB_USER%

REM variables depending on database vendor
SET unSupportedVendor=0
IF NOT "%dbVendor%"=="PostgreSQL" GOTO :NOPGSQL
SET dbSeedFile=%ADEMPIERE_HOME%\data\Adempiere_pg.dmp
SET sysUser=postgres
GOTO :VENDORSET
:NOPGSQL
IF NOT "%dbVendor%"=="oracle" GOTO :NOORACLE
SET dbSeedFile=%ADEMPIERE_HOME%\data\Adempiere.dmp
SET sysUser=system
GOTO :VENDORSET
:NOORACLE
IF NOT "%dbVendor%"=="oracleXE" GOTO :NOXE
SET dbSeedFile=%ADEMPIERE_HOME%\data\Adempiere.dmp
SET sysUser=system
GOTO :VENDORSET
:NOXE
SET unSupportedVendor=1	
:VENDORSET

REM variables depending on import mode
IF NOT "%importMode%"=="Reference" GOTO :NOREF
SET dbUser=reference
SET dbPwd=adempiere
SET dbSchema=reference
IF NOT "%dbVendor%"=="PostgreSQL" GOTO :REFNOPG
SET dbName=reference
GOTO :NOREF
:REFNOPG
IF NOT "%dbVendor%"=="oracle" GOTO :REFNOORACLE
SET dbName=%ADEMPIERE_DB_NAME%
GOTO :NOREF
:REFNOORACLE
IF NOT "%dbVendor%"=="oracleXE" GOTO :NOREF 
SET dbName=%ADEMPIERE_DB_NAME%
:NOREF

REM sanity checks
SET sanityCheck=%errorSuccess%

REM make sure environment is properly defined
IF "%ADEMPIERE_HOME%"=="" GOTO :ENVNOK
IF "%ADEMPIERE_DB_PATH%"=="" GOTO :ENVNOK
IF "%ADEMPIERE_DB_NAME%"=="" GOTO :ENVNOK
IF "%ADEMPIERE_DB_SERVER%"=="" GOTO :ENVNOK
IF "%ADEMPIERE_DB_PORT%"=="" GOTO :ENVNOK
IF "%ADEMPIERE_DB_SYSTEM%"=="" GOTO :ENVNOK
IF "%ADEMPIERE_DB_USER%"=="" GOTO :ENVNOK
IF "%ADEMPIERE_DB_PASSWORD%"=="" GOTO :ENVNOK
GOTO :ENVOK
:ENVNOK
ECHO Please make sure that the environment variables are set correctly:
ECHO ADEMPIERE_HOME	e.g. "C:\Adempiere"
ECHO ADEMPIERE_DB_PATH	e.g. "PostgreSQL" or "oracle"
ECHO ADEMPIERE_DB_NAME	e.g. "adempiere" or "xe"
ECHO ADEMPIERE_DB_SERVER	e.g. "dbserver.adempiere.org"
ECHO ADEMPIERE_DB_PORT	e.g. "5432" or "1521"
ECHO ADEMPIERE_DB_SYSTEM	system user password, e.g. "postgres" or "manager"
ECHO ADEMPIERE_DB_USER	e.g. "adempiere"
ECHO ADEMPIERE_DB_PASSWORD	e.g. "adempiere"
ECHO When in doubt, please run RUN_Setup.bat
SET sanityCheck=%errorNoEnvironment%
:ENVOK

REM make sure the database vendor is supported
IF NOT %unSupportedVendor%==1 GOTO :VENDOROK
ECHO The database vendor %dbVendor% is not supported.
SET sanityCheck=%errorWrongVendor%
:VENDOROK

REM make sure the database seed file exists
IF EXIST "%dbSeedFile%" GOTO :SEEDOK
ECHO The seed file %dbSeedFile% could not be loaded.
ECHO Please make sure it exists and you have read permissions.
SET sanityCheck=%errorNoSeedFile%	
:SEEDOK

REM make sure the database dependent import script can be executed
IF EXIST "%dbImportScript%" GOTO :IMPOK
ECHO The import script %dbImportScript% could not be run.
ECHO Please make sure it exists and you have execute permissions.
SET sanityCheck=%errorNoImportScript%
:IMPOK


REM call database dependent import script
IF NOT %sanityCheck%==%errorSuccess% GOTO :INSANE

IF NOT "%importMode%"=="Seed" GOTO :NOWARNING
ECHO.
ECHO WARNING: The live database will be overwritten!
ECHO Make sure you have a backup!
ECHO.
:NOWARNING
ECHO Re-create user %dbUser%
ECHO and import %dbSeedFile%
ECHO.
ECHO == The import will show warnings. This is OK ==
PAUSE
	
CALL "%dbImportScript%" "%dbVendor%" "%dbHost%" "%dbPort%" "%dbUser%" "%dbPwd%" "%sysUser%" "%sysPwd%" "%dbName%" "%dbCatalog%" "%dbSchema%" "%dbSeedFile%" "%dbSqljFile%"
SET result=%ERRORLEVEL% 

IF NOT %result%==%errorSuccess% GOTO :SANE
IF NOT "%importMode%"=="Seed" GOTO :SANE
IF NOT EXIST "%dbSignScript%" GOTO :SANE
CALL "%dbSignScript%"
GOTO :SANE

:INSANE
SET result=%sanityCheck%
:SANE

REM change back to calling directory
IF "%DIR_SAV%"=="" GOTO NOSAVEDDIR
CD "%DIR_SAV%"
:NOSAVEDDIR

REM end of script
ECHO.
IF "%result%"=="" GOTO NORESULT
IF %result%==0 GOTO NORESULT
ECHO terminated abnormally
GOTO SKIPEND
:NORESULT
SET result=0
ECHO done.
:SKIPEND

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
