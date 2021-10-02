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

if [ $ADEMPIERE_APPS_TYPE = "wildfly" ]
then
  if test -f "$WILDFLY_HOME/wildfly.pid"
    then
      echo "Adempiere's Server is already running .."
    else
      export JBOSS_HOME=
      export WILDFLY_BASE=$ADEMPIERE_HOME/wildfly
      echo "Adempiere Server $ADEMPIERE_APPS_TYPE starting ..."
      $WILDFLY_HOME/bin/standalone.sh -Djboss.server.base.dir=$WILDFLY_BASE --start-mode normal -Djboss.http.port=$ADEMPIERE_WEB_PORT -Djboss.https.port=$ADEMPIERE_SSL_PORT -Djboss.bind.address=0.0.0.0
      echo $! > $WILDFLY_HOME/wildfly.pid
  fi
fi

if [ $ADEMPIERE_APPS_TYPE = "tomcat" ]
then
  if test -f "$TOMCAT_HOME/tomcat.pid"
    then
      echo "Adempiere's Server is already running .."
    else
      echo "Adempiere Server $ADEMPIERE_APPS_TYPE starting ..."
      $TOMCAT_HOME/bin/startup.sh
      echo $! > $TOMCAT_HOME/tomcat.pid
   fi
fi

if [ $ADEMPIERE_APPS_TYPE = "jetty" ]
then
    if test -z  "$JETTY_HOME"
      then
        echo "JETTY_HOME not defined"
      else
        if test -f "$JETTY_BASE/jetty.pid"
        then
          echo "Adempiere's Server is already running .."
        else
          echo "Adempiere Server $ADEMPIERE_APPS_TYPE starting ..."
          export JETTY_BASE=$ADEMPIERE_HOME/jetty
          echo "Jetty Home directory : ${JETTY_HOME}"
          echo "Jetty Base directory : ${JETTY_BASE}"
          java $JAVA_OPTS -jar $JETTY_HOME/start.jar jetty.base=$ADEMPIERE_HOME/jetty --create-start-d --add-modules=server,ext,deploy,jndi,jsp,http jetty.http.port=$ADEMPIERE_WEB_PORT jetty.server.stopAtShutdown=true $JETTY_BASE/jetty-ds.xml
          java $JAVA_OPTS -jar $JETTY_HOME/start.jar jetty.base=$ADEMPIERE_HOME/jetty $JETTY_BASE/jetty-ds.xml
          echo $! > $JETTY_BASE/jetty.pid
        fi
    fi
fi
