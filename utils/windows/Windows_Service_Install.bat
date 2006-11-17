@echo off

REM $Id: Windows_Service_Install.bat,v 1.2 2003/11/01 20:55:15 comdivisionys Exp $

if (%ADEMPIERE_HOME%) == () (CALL myEnvironment.bat Server) else (CALL %ADEMPIERE_HOME%\utils\myEnvironment.bat Server)

%ADEMPIERE_HOME%\utils\windows\JavaService.exe -install Adempiere %JAVA_HOME%\jre\bin\server\jvm.dll -Xmx256M -Djava.class.path=%JAVA_HOME%\lib\tools.jar;%ADEMPIERE_HOME%\jboss\bin\run.jar -server %ADEMPIERE_JAVA_OPTIONS% -Djetty.port=%ADEMPIERE_WEB_PORT% -Djetty.ssl=%ADEMPIERE_SSL_PORT% -Djetty.keystore=%ADEMPIERE_KEYSTORE% -Djetty.password=%ADEMPIERE_KEYSTORE_PASSWORD% -start org.jboss.Main -params -c adempiere -stop org.jboss.Main -method systemExit -out %ADEMPIERE_HOME%\jboss\bin\out.txt -current %ADEMPIERE_HOME%\jboss\bin
