@ECHO OFF
REM
REM Name:		RUN_Migrate.bat
REM Description:script to migrate version
REM Created:	2010-05-12
REM Vendor:		K.K. Alice
REM Author:		Stefan Christians
REM
REM FileTarget:	~/development/sandbox/adempiere/utils/RUN_Migrate.bat
REM FileOwner:	spc.dvp
REM FilePerms:	0644
REM

SETLOCAL ENABLEDELAYEDEXPANSION

REM exit codes:
SET errorSuccess=0
SET errorGeneral=1
SET errorNoEnvironment=10
SET errorNoJarFile=11
SET errorNoLibraries=12

REM possible parameters:
REM text	run in text mode (no GUI, output on console)
REM silent	run in silent mode (suppress console output, implies text mode)
SET isText=
SET isSilent=
:ARGUMENTLOOP
SET arg=%1
CALL :NORMALIZE arg
IF "%arg%"=="" GOTO ENDARGUMENTLOOP
IF NOT "%arg%"=="text" GOTO NOTEXT
SET isText=-DisText
GOTO :ARGSOK
:NOTEXT
IF NOT "%arg%"=="silent" GOTO NOSILENT
SET isText=-DisText
SET isSilent=-DisSilent
GOTO :ARGSOK
:NOSILENT
ECHO usage: %~n0          run migration interactively
ECHO        %~n0 text     run migration in text mode
ECHO        %~n0 silent   run migration without any output
EXIT /B %errorGeneral%
:ARGSOK
SHIFT
GOTO ARGUMENTLOOP
:ENDARGUMENTLOOP

REM identify this script
IF "%isSilent%"=="-DisSilent" GOTO SKIPTITLE
ECHO Adempiere Migration Tool
:SKIPTITLE

REM change to directory in which this script resides
SET DIR_SAV=%CD%
CALL :NORMALIZE DIR_SAV
CD %~dp0

REM load environment
IF NOT EXIST myEnvironment.bat GOTO NOENV
CALL myEnvironment.bat nosave > nul
:NOENV

REM sanity checks
SET sanityCheck=%errorSuccess%

REM make sure environment is properly defined
CALL :NORMALIZE ADEMPIERE_HOME
CALL :NORMALIZE ADEMPIERE_JAVA
CALL :NORMALIZE ADEMPIERE_JAVA_OPTIONS
CALL :NORMALIZE CLASSPATH
IF "%ADEMPIERE_HOME%"=="" GOTO ENVNOK
IF "%ADEMPIERE_JAVA%"=="" GOTO ENVNOK
GOTO ENVOK
:ENVNOK
ECHO Please make sure that the environment variables are set correctly:
ECHO   ADEMPIERE_HOME	e.g. "C:\Adempiere"
ECHO   ADEMPIERE_JAVA	e.g. "%%JAVA_HOME%%\bin\java"
ECHO When in doubt, please run RUN_Setup.sh
SET sanityCheck=%errorNoEnvironment%
:ENVOK

REM set local variables
SET dbSignScript=RUN_SignDatabaseBuild.bat
SET javaCommand=%ADEMPIERE_JAVA%
SET javaOptions=%ADEMPIERE_JAVA_OPTIONS%
SET javaLibs=%ADEMPIERE_HOME%\lib
SET javaJar=%javaLibs%\migrate.jar
SET javaClass=com.kkalice.adempiere.migrate.Migrate
SET myClassPath=

REM make sure the migration tool exists
IF EXIST "%javaJar%" GOTO TOOLOK
ECHO The migration tool %javaJar% could not be loaded.
ECHO Please make sure it exists and you have read permissions.
SET sanityCheck=%errorNoJarFile%
:TOOLOK

REM make sure required libraries exist
IF EXIST "%javaLibs%\*.jar" GOTO LIBSOK
ECHO The required libraries could not be found in %javaLibs%.
ECHO Please make sure the directory exists and you have read permissions for the jar files.
SET sanityCheck=%errorNoLibraries%
:LIBSOK

REM make sure at least memory usage is set in java options
IF NOT "%javaOptions%"=="" GOTO OPTSOK
SET javaOptions=-Xms64M -Xmx512M
:OPTSOK


REM start migration tool
IF NOT %sanityCheck%==%errorSuccess% GOTO SANITYNOK

REM construct the classpath

REM first point to our migration tool
SET myClassPath=%javaJar%

REM then add jar files in ADEMPIERE_HOME\lib
REM exclude known patterns of other files so that only database drivers are included
REM (it does not matter if more than the database drivers are included,
REM things just speed up if the classpath is limited to only a few files)

FOR %%F IN ("%javaLibs%\*.jar") DO (
	ECHO %%~nF | FINDSTR /R /I "migrate adempiere packages jboss tools install patches jasper posterita ^zk.* glassfish custom sqlj" > nul
	IF ERRORLEVEL 1 SET myClassPath=!myClassPath!;%%F
)

REM then add any already existing classpath
IF "%CLASSPATH%"=="" GOTO NOCP
SET myClassPath=%myClassPath%;%CLASSPATH%
:NOCP

REM run the migration tool
"%javaCommand%" %javaOptions% -cp "%myClassPath%" %isText% %isSilent% %javaClass%
SET result=%ERRORLEVEL%

REM sign the database
IF NOT EXIST "%dbSignScript%" GOTO :END
CALL "%dbSignScript%" > NUL 2>&1

GOTO END

:SANITYNOK
SET result=%sanityCheck%

:END

REM change back to calling directory
IF "%DIR_SAV%"=="" GOTO NOSAVEDDIR
CD "%DIR_SAV%"
:NOSAVEDDIR

REM end of script
IF "%isSilent%"=="-DisSilent" GOTO SKIPEND
IF "%result%"=="" GOTO NORESULT
IF "%result%"=="0" GOTO NORESULT
ECHO terminated abnormally
GOTO SKIPEND
:NORESULT
set result=0
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
