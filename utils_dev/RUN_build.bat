@Title Build Adempiere Clean
@Rem $Header: /cvsroot/adempiere/utils_dev/RUN_build.bat,v 1.22 2005/09/08 21:56:11 jjanke Exp $

@CALL myDevEnv.bat
@IF NOT %ADEMPIERE_ENV%==Y GOTO NOBUILD

@echo Cleanup ...
@"%JAVA_HOME%\bin\java" -Dant.home="." %ANT_PROPERTIES% org.apache.tools.ant.Main clean

@echo Building ...
@"%JAVA_HOME%\bin\java" -Dant.home="." %ANT_PROPERTIES% org.apache.tools.ant.Main -logger org.apache.tools.ant.listener.MailLogger complete
@IF ERRORLEVEL 1 goto ERROR

dir %ADEMPIERE_INSTALL%

@Echo Done ...
@Pause
@exit

:NOBUILD
@Echo Check myDevEnv.bat (copy from myDevEnvTemplate.bat)

:ERROR
@Color fc

@Pause