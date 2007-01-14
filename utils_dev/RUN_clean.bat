@Title Adempiere Clean
@Rem $Header: /cvsroot/adempiere/utils_dev/RUN_clean.bat,v 1.4 2005/09/16 00:49:29 jjanke Exp $

@CALL myDevEnv.bat
@IF NOT %ADEMPIERE_ENV%==Y GOTO NOBUILD

@echo Cleanup ...
@"%JAVA_HOME%\bin\java" -Dant.home="." %ANT_PROPERTIES% org.apache.tools.ant.Main clean

@Pause
@exit
:NOBUILD
@Echo Check myDevEnv.bat (copy from myDevEnvTemplate.bat)
@Pause