@ECHO OFF
REM
REM Name:		Run_build.bat
REM Description:script to build Adempiere Migrate subproject
REM Sponsor:	K.K. Alice
REM Created:	2010-05-07
REM Author:		Stefan Christians
REM
REM FileTarget:	~/development/sandbox/adempiere/migrate/RUN_build.bat
REM FileOwner:	root.root
REM FilePerms:	0644
REM

SETLOCAL ENABLEDELAYEDEXPANSION

REM java home
CALL :NORMALIZE JAVA_HOME
IF NOT "%JAVA_HOME%"=="" GOTO :JHOMEOK
ECHO JAVA_HOME is not set.
ECHO You may not be able to build Adempiere.
ECHO Set JAVA_HOME to the directory of your local JDK.
GOTO :EOF
:JHOMEOK

REM path
CALL :NORMALIZE PATH
SET PATH=%JAVA_HOME%\bin;%PATH%

REM jdk
IF EXIST "%JAVA_HOME%\lib\tools.jar" GOTO :JDKOK
ECHO ** Need full Java SDK **
GOTO :EOF
:JDKOK

REM classpath
CALL :NORMALIZE CLASSPATH
SET CLASSPATH=%CLASSPATH%;..\tools\lib\ant.jar;..\tools\lib\ant-launcher.jar;%JAVA_HOME%\lib\tools.jar

REM compile using Ant
ECHO "running Ant"
"%JAVA_HOME%\bin\java" -Xms512m -Xmx512m -classpath "%CLASSPATH%" -Dant.home="." %ANT_PROPERTIES% org.apache.tools.ant.Main

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
