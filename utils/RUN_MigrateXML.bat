@Echo off
REM
REM Name:		RUN_MigrateXML.bat
REM Description:script to update the database to the next seed.
REM
REM XML files are expected to be found in the %ADEMPIERE_HOME%/migration folder

SETLOCAL ENABLEDELAYEDEXPANSION

REM exit codes:
SET errorSuccess=0
SET errorGeneral=1
SET errorNoEnvironment=10
SET errorNoXMLFiles=11

REM Can be called with argument "clean" to mark the applied dictionary migrations as processed
REM and delete the steps and data to reduce the database size.
SET cleanMode=%1

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

REM sanity checks
SET sanityCheck=%errorSuccess%

REM make sure environment is properly defined
IF "%ADEMPIERE_HOME%"=="" GOTO :ENVNOK
IF "%JAVA_HOME%"=="" GOTO :ENVNOK
GOTO :ENVOK
:ENVNOK
ECHO Please make sure that the environment variables are set correctly:
ECHO ADEMPIERE_HOME	e.g. "C:\Adempiere"
ECHO JAVA_HOME e.g. "C:\Program Files\Java\jdk1.7.0_71"
ECHO When in doubt, please run RUN_Setup.bat
SET sanityCheck=%errorNoEnvironment%
:ENVOK

REM make sure migration files exist
FOR /F %%i in ('dir /b /S "%ADEMPIERE_HOME%\migration\*.xml"') do (
   goto :XMLOK
)
ECHO Could not find XML files in %ADEMPIERE_HOME%\migration folders which could 
ECHO mean that the seed database is up-to-date.  Please check the migration build 
ECHO parameters in the source repository.
ECHO.
SET sanityCheck=%errorNoXMLFiles%	
:XMLOK

REM call database dependent import script
IF NOT %sanityCheck%==%errorSuccess% GOTO :INSANE

Echo =======================================
Echo Migrate Adempiere using XML
Echo =======================================
Echo.
Echo It is recommended that production databases be migrated using 
Echo RUN_Migrate.bat.
Echo.
Echo Usage: RUN_Migrate.bat
Echo.
Echo Optional argument "clean" will mark all dictionary migrations that have
Echo been applied as processed and will delete the steps and data to save 
Echo space.
Echo.
Echo WARNING: If the database is not a fresh import of the seed, make sure 
Echo you have a backup!
Echo.
PAUSE

Set JAVA=%JAVA_HOME%\bin\java
SET CP=%ADEMPIERE_HOME%\lib\CInstall.jar;%ADEMPIERE_HOME%\lib\Adempiere.jar;%ADEMPIERE_HOME%\lib\CCTools.jar;%ADEMPIERE_HOME%\lib\oracle.jar;%ADEMPIERE_HOME%\lib\jboss.jar;%ADEMPIERE_HOME%\lib\postgresql.jar;

"%JAVA%" -classpath %CP% -DADEMPIERE_HOME=%ADEMPIERE_HOME% org.adempiere.process.MigrationLoader %cleanMode%
SET result=%ERRORLEVEL% 

IF NOT %result%==%errorSuccess% GOTO :SANE
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
