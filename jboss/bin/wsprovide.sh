#!/bin/sh

#
# Invoke wsprovide with dynamic classpath
# depending on the deployed stack and the location
#
# @author Heiko.Braun@jboss.com
# @version $Id: wsprovide.sh 2885 2008-03-22 23:05:16Z richard.opalka@jboss.com $
#

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
#JAVA_OPTS="-classic -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8787,server=y,suspend=y $JAVA_OPTS"

# Setup JBoss sepecific properties
JAVA_OPTS="$JAVA_OPTS"

# Setup the java endorsed dirs
JBOSS_ENDORSED_DIRS="$JBOSS_HOME/lib/endorsed"

###
# Setup the LIBDIR
# This script maybe used form within the jbossws distribution
# or installed under JBOSS_HOME/bin
###

PARENT=`cd $DIRNAME/..; pwd`
if [ -d $PARENT/client ]; then
	LIBDIR=$JBOSS_HOME/client
else
	LIBDIR=$PARENT/lib
fi

# is it a JBossWS-native or SunRI installation?
if [ -f $LIBDIR/jbossws-client.jar ]; then
    JBOSSWS_NATIVE="true"
fi

###
# Setup the wsprovide classpath
# The classpath is dynamically build depending on the stack that
# is deployed. See $JBOSSWS_NATIVE above.
###

# shared libs
WSPROVIDE_CLASSPATH="$WSPROVIDE_CLASSPATH:$JAVA_HOME/lib/tools.jar"
WSPROVIDE_CLASSPATH="$WSPROVIDE_CLASSPATH:$LIBDIR/jbossws-spi.jar"
WSPROVIDE_CLASSPATH="$WSPROVIDE_CLASSPATH:$LIBDIR/activation.jar"
WSPROVIDE_CLASSPATH="$WSPROVIDE_CLASSPATH:$LIBDIR/getopt.jar"
WSPROVIDE_CLASSPATH="$WSPROVIDE_CLASSPATH:$LIBDIR/jbossall-client.jar"
WSPROVIDE_CLASSPATH="$WSPROVIDE_CLASSPATH:$LIBDIR/log4j.jar"
WSPROVIDE_CLASSPATH="$WSPROVIDE_CLASSPATH:$LIBDIR/mail.jar"

# shared jaxws libs
WSPROVIDE_CLASSPATH="$WSPROVIDE_CLASSPATH:$LIBDIR/jaxb-api.jar"
WSPROVIDE_CLASSPATH="$WSPROVIDE_CLASSPATH:$LIBDIR/jaxb-impl.jar"
WSPROVIDE_CLASSPATH="$WSPROVIDE_CLASSPATH:$LIBDIR/jaxb-xjc.jar"
WSPROVIDE_CLASSPATH="$WSPROVIDE_CLASSPATH:$LIBDIR/jaxws-tools.jar"
WSPROVIDE_CLASSPATH="$WSPROVIDE_CLASSPATH:$LIBDIR/jaxws-rt.jar"
WSPROVIDE_CLASSPATH="$WSPROVIDE_CLASSPATH:$LIBDIR/streambuffer.jar"
WSPROVIDE_CLASSPATH="$WSPROVIDE_CLASSPATH:$LIBDIR/stax-ex.jar"

###
# stack specific dependencies
###

if [ "x$JBOSSWS_NATIVE" = "x" ]; then
   # JBossWS-Metro stack libraries
   echo "JBossWS-Metro stack deployed"
   WSPROVIDE_CLASSPATH="$WSPROVIDE_CLASSPATH:$LIBDIR/jbossws-metro-client.jar"
   WSPROVIDE_CLASSPATH="$WSPROVIDE_CLASSPATH:$LIBDIR/jaxws-api.jar"
   WSPROVIDE_CLASSPATH="$WSPROVIDE_CLASSPATH:$LIBDIR/jsr181-api.jar"
   WSPROVIDE_CLASSPATH="$WSPROVIDE_CLASSPATH:$LIBDIR/saaj-api.jar"
   WSPROVIDE_CLASSPATH="$WSPROVIDE_CLASSPATH:$LIBDIR/saaj-impl.jar"
else
   # JBossWS-Native stack libraries
   echo "JBossWS-Native stack deployed"
   WSPROVIDE_CLASSPATH="$WSPROVIDE_CLASSPATH:$LIBDIR/jboss-xml-binding.jar"
   WSPROVIDE_CLASSPATH="$WSPROVIDE_CLASSPATH:$LIBDIR/javassist.jar"
   WSPROVIDE_CLASSPATH="$WSPROVIDE_CLASSPATH:$LIBDIR/jbossall-client.jar"
   WSPROVIDE_CLASSPATH="$WSPROVIDE_CLASSPATH:$LIBDIR/jbossws-client.jar"
   WSPROVIDE_CLASSPATH="$WSPROVIDE_CLASSPATH:$LIBDIR/jboss-jaxws.jar"
   WSPROVIDE_CLASSPATH="$WSPROVIDE_CLASSPATH:$LIBDIR/jboss-jaxrpc.jar"
   WSPROVIDE_CLASSPATH="$WSPROVIDE_CLASSPATH:$LIBDIR/jboss-saaj.jar"
   WSPROVIDE_CLASSPATH="$WSPROVIDE_CLASSPATH:$LIBDIR/policy.jar"
   WSPROVIDE_CLASSPATH="$WSPROVIDE_CLASSPATH:$LIBDIR/wsdl4j.jar"
fi

###
# Execute the JVM
###

# For Cygwin, switch paths to Windows format before running java
if $cygwin; then
    JBOSS_HOME=`cygpath --path --windows "$JBOSS_HOME"`
    JAVA_HOME=`cygpath --path --windows "$JAVA_HOME"`
    WSPROVIDE_CLASSPATH=`cygpath --path --windows "$WSPROVIDE_CLASSPATH"`
    JBOSS_ENDORSED_DIRS=`cygpath --path --windows "$JBOSS_ENDORSED_DIRS"`
fi

"$JAVA" $JAVA_OPTS \
   -Djava.endorsed.dirs="$JBOSS_ENDORSED_DIRS" \
   -Dlog4j.configuration=wstools-log4j.xml \
   -classpath "$WSPROVIDE_CLASSPATH" \
   org.jboss.wsf.spi.tools.cmd.WSProvide "$@"
