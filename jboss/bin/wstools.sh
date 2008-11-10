#!/bin/sh

# $Id: wstools.sh 3813 2008-03-31 12:03:40Z richard.opalka@jboss.com $

DIRNAME=`dirname $0`
PROGNAME=`basename $0`

# OS specific support (must be 'true' or 'false').
cygwin=false;
case "`uname`" in
    CYGWIN*)
        cygwin=true
        ;;
esac

# For Cygwin, ensure paths are in UNIX format before anything is touched
if $cygwin ; then
    [ -n "$JBOSS_HOME" ] &&
        JBOSS_HOME=`cygpath --unix "$JBOSS_HOME"`
    [ -n "$JAVA_HOME" ] &&
        JAVA_HOME=`cygpath --unix "$JAVA_HOME"`
fi

# Setup JBOSS_HOME
if [ "x$JBOSS_HOME" = "x" ]; then
    # get the full path (without any relative bits)
    JBOSS_HOME=`cd $DIRNAME/..; pwd`
fi
export JBOSS_HOME

# Setup the JVM
if [ "x$JAVA" = "x" ]; then
    if [ "x$JAVA_HOME" != "x" ]; then
	JAVA="$JAVA_HOME/bin/java"
    else
	JAVA="java"
    fi
fi

#JPDA options. Uncomment and modify as appropriate to enable remote debugging .
#JAVA_OPTS="-classic -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8787,server=y,suspend=n $JAVA_OPTS"

# Setup JBoss sepecific properties
JAVA_OPTS="$JAVA_OPTS"

# Setup the java endorsed dirs
JBOSS_ENDORSED_DIRS="$JBOSS_HOME/lib/endorsed"

# Setup the wstools classpath
# shared libs
WSTOOLS_CLASSPATH="$WSTOOLS_CLASSPATH:$JAVA_HOME/lib/tools.jar"
WSTOOLS_CLASSPATH="$WSTOOLS_CLASSPATH:$JBOSS_HOME/client/activation.jar"
WSTOOLS_CLASSPATH="$WSTOOLS_CLASSPATH:$JBOSS_HOME/client/getopt.jar"
WSTOOLS_CLASSPATH="$WSTOOLS_CLASSPATH:$JBOSS_HOME/client/wstx.jar"
WSTOOLS_CLASSPATH="$WSTOOLS_CLASSPATH:$JBOSS_HOME/client/wsdl4j.jar"
WSTOOLS_CLASSPATH="$WSTOOLS_CLASSPATH:$JBOSS_HOME/client/jbossall-client.jar"
WSTOOLS_CLASSPATH="$WSTOOLS_CLASSPATH:$JBOSS_HOME/client/log4j.jar"
WSTOOLS_CLASSPATH="$WSTOOLS_CLASSPATH:$JBOSS_HOME/client/mail.jar"
WSTOOLS_CLASSPATH="$WSTOOLS_CLASSPATH:$JBOSS_HOME/client/concurrent.jar" 
WSTOOLS_CLASSPATH="$WSTOOLS_CLASSPATH:$JBOSS_HOME/client/jbossws-spi.jar"

WSTOOLS_CLASSPATH="$WSTOOLS_CLASSPATH:$JBOSS_HOME/client/javassist.jar"
WSTOOLS_CLASSPATH="$WSTOOLS_CLASSPATH:$JBOSS_HOME/client/jboss-xml-binding.jar"
WSTOOLS_CLASSPATH="$WSTOOLS_CLASSPATH:$JBOSS_HOME/client/jbossws-client.jar"
WSTOOLS_CLASSPATH="$WSTOOLS_CLASSPATH:$JBOSS_HOME/client/jboss-jaxws.jar"
WSTOOLS_CLASSPATH="$WSTOOLS_CLASSPATH:$JBOSS_HOME/client/jboss-jaxrpc.jar"
WSTOOLS_CLASSPATH="$WSTOOLS_CLASSPATH:$JBOSS_HOME/client/jboss-saaj.jar"


# For Cygwin, switch paths to Windows format before running java
if $cygwin; then
    JBOSS_HOME=`cygpath --path --windows "$JBOSS_HOME"`
    JAVA_HOME=`cygpath --path --windows "$JAVA_HOME"`
    WSTOOLS_CLASSPATH=`cygpath --path --windows "$WSTOOLS_CLASSPATH"`
    JBOSS_ENDORSED_DIRS=`cygpath --path --windows "$JBOSS_ENDORSED_DIRS"`
fi

# Display our environment
echo "========================================================================="
echo ""
echo "  WSTools Environment"
echo ""
echo "  JBOSS_HOME: $JBOSS_HOME"
echo ""
echo "  JAVA: $JAVA"
echo ""
echo "  JAVA_OPTS: $JAVA_OPTS"
echo ""
#echo "  CLASSPATH: $WSTOOLS_CLASSPATH"
#echo ""
echo "========================================================================="
echo ""

# Execute the JVM
"$JAVA" $JAVA_OPTS \
   -Djava.endorsed.dirs="$JBOSS_ENDORSED_DIRS" \
   -Dlog4j.configuration=wstools-log4j.xml \
   -classpath "$WSTOOLS_CLASSPATH" \
   org.jboss.ws.tools.WSTools "$@"
