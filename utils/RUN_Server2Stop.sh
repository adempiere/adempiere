#!/bin/sh
# ADempiere Server Stop script

if [ $ADEMPIERE_HOME ]; then
  cd $ADEMPIERE_HOME/utils
fi

. ./myEnvironment.sh Server
echo Adempiere Server Stop - $ADEMPIERE_HOME \($ADEMPIERE_DB_NAME\)

if [ $ADEMPIERE_APPS_TYPE = "wildfly" ]
then
   if test -f "$WILDFLY_HOME/wildfly.pid"
    then
      export JBOSS_HOME=
      echo sh $WILDFLY_HOME/bin/jboss-cli.sh --connect command=:shutdown
      sh $WILDFLY_HOME/bin/jboss-cli.sh --connect command=:shutdown
      rm $WILDFLY_HOME/wildfly.pid
   fi
fi

if [ $ADEMPIERE_APPS_TYPE = "tomcat" ]
then
   if test -f "$CATALINA_BASE/tomcat.pid"
   then
      echo sh $CATALINA_BASE/bin/shutdown.sh
      sh $CATALINA_BASE/bin/shutdown.sh
   fi
fi

if [ $ADEMPIERE_APPS_TYPE = "jetty" ]
then
    if test -z  "$JETTY_HOME"
      then
        echo "JETTY_HOME not defined"
      else
        export JETTY_BASE=$ADEMPIERE_HOME/jetty
        echo "Jetty Home directory : ${JETTY_HOME}"
        echo "Jetty Base directory : ${JETTY_BASE}"
        if test -f "$JETTY_BASE/jetty.pid"
          then
            $JAVA_HOME/bin/java $JAVA_OPTS -jar $JETTY_HOME/start.jar stop.port=7777 stop.key=$ADEMPIERE_KEYSTOREPASS --stop
            rm $JETTY_BASE/jetty.pid
        fi
    fi
fi
