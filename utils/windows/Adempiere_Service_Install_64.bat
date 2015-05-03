@echo off

REM Adempiere_Service_Install_64.bat for windows 64bit operating system.  Uses the
REM JBoss Native libraries
REM
REM For configuration see the jboss\bin\service.bat

@if not "%ADEMPIERE_HOME%" == "" goto ADEMPIERE_HOME_OK
@Echo ADEMPIERE_HOME is not set.  
@Echo   Set ADEMPIERE_HOME to the directory of Adempiere.
@Echo   You could set it and JAVA_HOME via WinEnv.js e.g.:
@Echo     cscript WinEnv.js C:\Adempiere C:\j2sdk1.4.2_08
@goto END
:ADEMPIERE_HOME_OK

pushd %ADEMPIERE_HOME%\jboss\bin
call service.bat install
popd

:END
