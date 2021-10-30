@if (%ADEMPIERE_HOME%) == () (CALL myEnvironment.bat Server) else (CALL %ADEMPIERE_HOME%\utils\myEnvironment.bat Server)
@Title Adempiere Server Stop - %ADEMPIERE_HOME%

@Rem $Id: RUN_Server2Stop.bat,v 1.12 2005/09/06 02:46:16 jjanke Exp $

@IF '%ADEMPIERE_APPS_TYPE%' == 'wildfly' GOTO WILDFLY
@IF '%ADEMPIERE_APPS_TYPE%' == 'tomcat'  GOTO TOMCAT
@IF '%ADEMPIERE_APPS_TYPE%' == 'jetty'   GOTO JETTY
@GOTO UNSUPPORTED

:WILDFLY
@Set NOPAUSE=Yes

:TOMCAT
@Set NOPAUSE=Yes
Call %CATALINA_BASE%/tomcat/bin/shutdown.bat

:JETTY
@Set NOPAUSE=Yes
Call java $JAVA_OPTS -jar %JETTY_HOME%/start.jar jetty.base=%JETTY_BASE% --stop stop.port=%ADEMPIERE_WEB_PORT%

@CD %WILDFLY_HOME%\bin
Call jboss-cli.bat --connect command=:shutdown

@Echo Done Stopping Adempiere Apps Server %ADEMPIERE_HOME% (%ADEMPIERE_DB_NAME%)
@GOTO END

:UNSUPPORTED
@Echo Apps Server stop of %ADEMPIERE_APPS_TYPE% not supported

:END
@Rem Sleep 30
@CHOICE /C YN /T 30 /D N > NUL

@Exit