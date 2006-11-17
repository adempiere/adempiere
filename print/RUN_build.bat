@Title Build Print
@Rem   $Header: /cvsroot/adempiere/print/RUN_build.bat,v 1.9 2005/09/16 00:50:35 jjanke Exp $

@CALL ..\utils_dev\myDevEnv.bat

@IF %ADEMPIERE_ENV%==N GOTO NOBUILD
@IF NOT %ADEMPIERE_ENV%==Y GOTO NOBUILD

@echo Cleanup ...
@"%JAVA_HOME%\bin\java" -Dant.home="." %ANT_PROPERTIES% org.apache.tools.ant.Main clean

@echo Building ...
@"%JAVA_HOME%\bin\java" -Dant.home="." %ANT_PROPERTIES% org.apache.tools.ant.Main printDistribute

@Echo Done ...
@sleep 60
@exit

:NOBUILD
@Echo Check myDevEnv.bat (copy from myDevEnvTemplate.bat)
@Pause