@Title Build Adempiere WebStore
@Rem $Header: /cvsroot/adempiere/webStore/RUN_build.bat,v 1.5 2005/09/16 00:49:17 jjanke Exp $

@CALL ..\utils_dev\myDevEnv.bat

@IF %ADEMPIERE_ENV%==N GOTO NOBUILD
@echo Cleanup ...
@"%JAVA_HOME%\bin\java" -Dant.home="." %ANT_PROPERTIES% org.apache.tools.ant.launch.Launcher clean
@echo Building ...
@"%JAVA_HOME%\bin\java" -Dant.home="." %ANT_PROPERTIES% org.apache.tools.ant.launch.Launcher

@pause
:NOBUILD