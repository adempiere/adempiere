@echo off
@if (%ADEMPIERE_HOME%) == () (CALL myEnvironment.bat Server) else (CALL %ADEMPIERE_HOME%\utils\myEnvironment.bat Server)
@Title Adempiere Server Start - %ADEMPIERE_HOME% (%ADEMPIERE_APPS_TYPE%)

@Rem $Id: RUN_Server2.bat,v 1.24 2005/10/26 00:38:18 jjanke Exp $

@Rem  To use your own Encryption class (implementing org.compiere.util.SecureInterface),
@Rem  you need to set it here (and in the client start script) - example:
@Rem  SET SECURE=-DADEMPIERE_SECURE=org.compiere.util.Secure
@SET SECURE=


@IF '%ADEMPIERE_APPS_TYPE%' == 'wildfly' GOTO WILDFLY
@IF '%ADEMPIERE_APPS_TYPE%' == 'tomcat'  GOTO TOMCAT
@IF '%ADEMPIERE_APPS_TYPE%' == 'jetty'   GOTO JETTY
@GOTO UNSUPPORTED

:WILDFLY
@Set NOPAUSE=Yes
@Set JAVA_OPTS=-server %ADEMPIERE_JAVA_OPTIONS% %SECURE% -Dorg.adempiere.server.embedded=true
@Echo Start Adempiere Apps Server %ADEMPIERE_HOME% (%ADEMPIERE_DB_NAME%)
IF EXIST %WILDFLY_HOME%\login-modules.configured (
    @Echo "-> Login modules were configured before"
) ELSE ( 
@Echo "-> Adding Login modules"
@Call START %WILDFLY_HOME%\bin\standalone.bat --admin-only -Djboss.server.base.dir=%WILDFLY_BASE% -Djboss.http.port=%ADEMPIERE_WEB_PORT% -Djboss.https.port=%ADEMPIERE_SSL_PORT% -Djboss.bind.address=0.0.0.0
@timeout 5
@Call %WILDFLY_HOME%\bin\jboss-cli.bat --connect command="/subsystem=elytron/key-store=log-server-ks:add(path=%ADEMPIERE_HOME%/keystore/myKeystore, type=JKS, credential-reference={clear-text=%ADEMPIERE_KEYSTOREPASS%)"
@Call %WILDFLY_HOME%\bin\jboss-cli.bat --connect command="/subsystem=security/security-domain=custom-security-realm:add"
@Call %WILDFLY_HOME%\bin\jboss-cli.bat --connect command="/subsystem=security/security-domain=custom-security-realm/authentication=classic:add(login-modules=[{"code" => "org.adempiere.as.jboss.AdempiereLoginModule", "flag" => "required", "module-options"=[ ("junauthenticatedIdentity"=>"anonymous")]}])"
@Call %WILDFLY_HOME%\bin\jboss-cli.bat --connect command="/subsystem=undertow/configuration=filter/gzip=gzipFilter/:add"
@Call %WILDFLY_HOME%\bin\jboss-cli.bat --connect command="/subsystem=undertow/server=default-server/host=default-host/filter-ref=gzipFilter:add(\predicate=""exists['%{o,Content-Type}'] and regex[pattern='(?:application/javascript|text/css|text/html|text/xml|application/json)(;.*)?', value=%{o,Content-Type}, full-match=true]"")"
@Call %WILDFLY_HOME%\bin\jboss-cli.bat --connect command=:shutdown
@Echo "-> Added Login modules"
@Echo configured > %WILDFLY_HOME%\login-modules.configured
@Echo 
)

@Echo "-> WildFly Starting the Service"
@Call START %WILDFLY_HOME%\bin\standalone.bat -Djboss.server.base.dir=%WILDFLY_BASE% --start-mode normal -Djboss.http.port=%ADEMPIERE_WEB_PORT% -Djboss.https.port=%ADEMPIERE_SSL_PORT% -Djboss.bind.address=0.0.0.0

@GOTO END

:TOMCAT
@Set NOPAUSE=Yes
@Set JAVA_OPTS=-server %ADEMPIERE_JAVA_OPTIONS% %SECURE% -Dorg.adempiere.server.embedded=true --add-exports java.base/jdk.internal.misc=ALL-UNNAMED --add-opens java.base/java.lang=ALL-UNNAMED
@Echo Start Adempiere Apps Server %ADEMPIERE_HOME% (%ADEMPIERE_DB_NAME%)
@Call START %CATALINA_BASE%\bin\startup.bat
@Echo Done  Adempiere Apps Server %ADEMPIERE_HOME% (%ADEMPIERE_DB_NAME%)
@GOTO END

:JETTY
@Set NOPAUSE=Yes
@Set JAVA_OPTS=-server %ADEMPIERE_JAVA_OPTIONS% %SECURE% -Dorg.adempiere.server.embedded=true
@Echo Start Adempiere Apps Server %ADEMPIERE_HOME% (%ADEMPIERE_DB_NAME%)
@Call %JAVA_HOME%/bin/java %JAVA_OPTS% -jar %JETTY_HOME%/start.jar jetty.base=%ADEMPIERE_HOME%/jetty --create-start-d --add-modules=server,ext,deploy,jndi,jsp,threadpool,http,ssl,https,gzip jetty.http.port=%ADEMPIERE_WEB_PORT% jetty.ssl.port=%ADEMPIERE_SSL_PORT% jetty.sslContext.keyStorePath=../keystore/myKeystore jetty.sslContext.keyStorePassword=%ADEMPIERE_KEYSTOREPASS% jetty.server.stopAtShutdown=true %JETTY_BASE%/jetty-ds.xml
@Call START %JAVA_HOME%/bin/java %JAVA_OPTS% -jar %JETTY_HOME%/start.jar jetty.base=%JETTY_BASE% stop.port=7777 stop.key=%ADEMPIERE_KEYSTOREPASS% %JETTY_BASE%/jetty-ds.xml &
@Echo Done Adempiere Apps Server %ADEMPIERE_HOME% (%ADEMPIERE_DB_NAME%)
@GOTO END

:UNSUPPORTED
@Echo Apps Server start of %ADEMPIERE_APPS_TYPE% not supported

:END
@Rem Sleep 60
@CHOICE /C YN /T 60 /D N > NUL

@Exit