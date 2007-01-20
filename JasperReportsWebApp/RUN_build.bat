@CALL ..\utils_dev\myDevEnv.bat
@IF NOT %COMPIERE_ENV%==Y GOTO NOBUILD

@echo Cleanup ...
@%JAVA_HOME%\bin\java -Dant.home="." %ANT_PROPERTIES% org.apache.tools.ant.Main clean

@echo Building ...
@%JAVA_HOME%\bin\java -Dant.home="." %ANT_PROPERTIES% org.apache.tools.ant.Main main

@REM @pause
@REM @exit

:NOBUILD
@Echo Check myDevEnv.bat (copy from myDevEnvTemplate.bat)
@REM @Pause
