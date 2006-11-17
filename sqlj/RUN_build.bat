@Title Build SQLJ
@Rem   $Header: /cvsroot/adempiere/sqlj/RUN_build.bat,v 1.5 2005/09/16 00:50:04 jjanke Exp $
@Rem
@Rem	Note that the SQLJ build is not part of the normal build cycle.
@Rem	You need to build the sqlj.jar file either with this script
@Rem	or with the 'compile' script for older Java versions
@Rem	You then deploy it with the database dependent 'create' script

@CALL ..\utils_dev\myDevEnv.bat
@IF NOT %ADEMPIERE_ENV%==Y GOTO NOBUILD

@echo Cleanup ...
@"%JAVA_HOME%\bin\java" -Dant.home="." %ANT_PROPERTIES% org.apache.tools.ant.Main clean

@echo Building ...
@"%JAVA_HOME%\bin\java" -Dant.home="." %ANT_PROPERTIES% org.apache.tools.ant.Main sqljDist

@Echo Done ...
@sleep 60
@exit

:NOBUILD
@Echo Check myDevEnv.bat (copy from myDevEnvTemplate.bat)
@Pause
