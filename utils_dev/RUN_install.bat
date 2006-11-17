@Title Adempiere Install
@Rem $Header: /cvsroot/adempiere/utils_dev/RUN_install.bat,v 1.3 2005/09/16 00:49:29 jjanke Exp $

@CALL myDevEnv.bat
@IF NOT %ADEMPIERE_ENV%==Y GOTO NOBUILD

@"%JAVA_HOME%\bin\java" -Dant.home="." %ANT_PROPERTIES% org.apache.tools.ant.Main install

@Pause
@exit
:NOBUILD
@Echo Check myDevEnv.bat (copy from myDevEnvTemplate.bat)
@Pause
