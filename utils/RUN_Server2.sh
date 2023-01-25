#!/bin/sh
# ADempiere Server Start

if [ $ADEMPIERE_HOME ]; then
  cd $ADEMPIERE_HOME/utils
fi

. ./myEnvironment.sh Server

# To use your own Encryption class (implementing org.compiere.util.SecureInterface),
# you need to set it here (and in the client start script) - example:
# SECURE=-DADEMPIERE_SECURE=org.compiere.util.Secure
SECURE=

# headless option if you don't have X installed on the server
JAVA_OPTS="-server $ADEMPIERE_JAVA_OPTIONS $SECURE -Djava.awt.headless=true -Dorg.adempiere.server.embedded=true --add-exports java.base/jdk.internal.misc=ALL-UNNAMED --add-opens java.base/java.lang=ALL-UNNAMED"

export JAVA_OPTS

if [ $ADEMPIERE_APPS_TYPE = "wildfly" ]; then
    if test -z  "$WILDFLY_HOME" 
        then
                echo "WILDFLY_HOME not defined"
        else        
          PID="${WILDFLY_HOME}/wildfly.pid"
          if [ -f $PID ] && pgrep -F $PID ; then
            echo "ADempiere's Server is already running .."
          else
            echo "ADempiere Server $ADEMPIERE_APPS_TYPE starting ..."
            echo "ADempiere Server Home $WILDFLY_HOME ..."
                if test -f "$WILDFLY_HOME/login-modules.configured" ; then
                  echo "-> Login modules were configured before"
                else
                  echo "-> Adding Login modules"
                  nohup $WILDFLY_HOME/bin/standalone.sh --admin-only -Djboss.server.base.dir=$WILDFLY_BASE -Djboss.http.port=$ADEMPIERE_WEB_PORT \
                        -Djboss.https.port=$ADEMPIERE_SSL_PORT -Djboss.bind.address=0.0.0.0 >./nohup.out 2>./nohup.err &
                  sleep 7
                  sh $WILDFLY_HOME/bin/jboss-cli.sh --connect command="/subsystem=elytron/key-store=log-server-ks:add(path=${ADEMPIERE_HOME}/keystore/myKeystore, type=JKS, credential-reference={clear-text=${ADEMPIERE_KEYSTOREPASS})"
                  sh $WILDFLY_HOME/bin/jboss-cli.sh --connect command="/subsystem=security/security-domain=custom-security-realm:add"
                  sh $WILDFLY_HOME/bin/jboss-cli.sh --connect command="/subsystem=security/security-domain=custom-security-realm/authentication=classic:add(login-modules=[{"code" => "org.adempiere.as.jboss.AdempiereLoginModule", "flag" => "required", "module-options"=[ ("junauthenticatedIdentity"=>"anonymous")]}])"
                  sh $WILDFLY_HOME/bin/jboss-cli.sh --connect command="/subsystem=undertow/configuration=filter/gzip=gzipFilter/:add"
                  sh $WILDFLY_HOME/bin/jboss-cli.sh --connect command="/subsystem=undertow/server=default-server/host=default-host/filter-ref=gzipFilter:add(predicate=\"exists['%{o,Content-Type}'] and regex[pattern='(?:application/javascript|text/css|tex/html|text/xml|application/json)(;.*)?', value=%{o,Content-Type}, full-match=true]\")"
                  sh $WILDFLY_HOME/bin/jboss-cli.sh --connect command=:shutdown
                  echo "configured" > $WILDFLY_HOME/login-modules.configured
                  echo "-> Added Login modules"
                fi
            echo "-> WildFly Starting the Service"
            nohup $WILDFLY_HOME/bin/standalone.sh --start-mode normal -Djboss.server.base.dir=$WILDFLY_BASE -Djboss.http.port=$ADEMPIERE_WEB_PORT \
                -Djboss.https.port=$ADEMPIERE_SSL_PORT -Djboss.bind.address=0.0.0.0 >./nohup.out 2>./nohup.err &
            echo $! > $WILDFLY_HOME/wildfly.pid
          fi
    fi       
fi

if [ $ADEMPIERE_APPS_TYPE = "tomcat" ]; then
    if test -z  "$CATALINA_HOME"
      then
        echo "CATALINA_HOME not defined"
      else      
        echo "CATALINA_BASE: ${CATALINA_BASE}"
        PID="${CATALINA_BASE}/tomcat.pid"
        if [ -f $PID ] && pgrep -F $PID ; then
         echo "ADempiere's Server is already running .."
        else
          echo "ADempiere Server $ADEMPIERE_APPS_TYPE starting ..."
          $CATALINA_BASE/bin/startup.sh
        fi
    fi    
fi

if [ $ADEMPIERE_APPS_TYPE = "jetty" ]; then
    if test -z  "$JETTY_HOME"
      then
        echo "JETTY_HOME not defined"
      else
        PID="${JETTY_BASE}/jetty.pid"
        if [ -f $PID ] && pgrep -F $PID ; then
          echo "ADempiere's Server is already running .."
        else
          echo "ADempiere Server $ADEMPIERE_APPS_TYPE starting ..."
          echo "Jetty Home directory : ${JETTY_HOME}"
          echo "Jetty Base directory : ${JETTY_BASE}"
          $JAVA_HOME/bin/java $JAVA_OPTS -jar $JETTY_HOME/start.jar jetty.base=$JETTY_BASE --create-start-d --add-modules=server,ext,deploy,jndi,jsp,threadpool,http,ssl,https,gzip \
                 jetty.http.port=$ADEMPIERE_WEB_PORT jetty.ssl.port=$ADEMPIERE_SSL_PORT jetty.sslContext.keyStorePath=$ADEMPIERE_HOME/keystore/myKeystore jetty.sslContext.keyStorePassword=$ADEMPIERE_KEYSTOREPASS jetty.server.stopAtShutdown=true $JETTY_BASE/jetty-ds.xml >./nohup.out 2>./nohup.err
          $JAVA_HOME/bin/java $JAVA_OPTS -jar $JETTY_HOME/start.jar jetty.base=$JETTY_BASE stop.port=7777 stop.key=$ADEMPIERE_KEYSTOREPASS $JETTY_BASE/jetty-ds.xml >./nohup.out 2>./nohup.err &
          echo $! > $JETTY_BASE/jetty.pid
        fi
    fi
fi
