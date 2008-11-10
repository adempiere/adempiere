#!/bin/sh
### ====================================================================== ###
##                                                                          ##
##  JBoss Shutdown Script                                                   ##
##                                                                          ##
### ====================================================================== ###

### $Id: twiddle.sh 57032 2006-09-20 22:03:16Z dimitris@jboss.org $ ###

DIRNAME=`dirname $0`
PROGNAME=`basename $0`
GREP="grep"

#
# Helper to complain.
#
die() {
    echo "${PROGNAME}: $*"
    exit 1
}

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
    JBOSS_HOME=`cd $DIRNAME/..; pwd`
fi
export JBOSS_HOME

# Setup the JVM
if [ "x$JAVA_HOME" != "x" ]; then
    JAVA=$JAVA_HOME/bin/java
else
    JAVA="java"
fi

# Setup the classpath
JBOSS_BOOT_CLASSPATH="$JBOSS_HOME/bin/twiddle.jar"

if [ "x$JBOSS_CLASSPATH" = "x" ]; then
    JBOSS_CLASSPATH="$JBOSS_BOOT_CLASSPATH"
    JBOSS_CLASSPATH="$JBOSS_CLASSPATH:$JBOSS_HOME/client/jbossall-client.jar"
    JBOSS_CLASSPATH="$JBOSS_CLASSPATH:$JBOSS_HOME/client/getopt.jar"
    JBOSS_CLASSPATH="$JBOSS_CLASSPATH:$JBOSS_HOME/client/log4j.jar"
    JBOSS_CLASSPATH="$JBOSS_CLASSPATH:$JBOSS_HOME/lib/jboss-jmx.jar"
else
    JBOSS_CLASSPATH="$JBOSS_CLASSPATH:$JBOSS_BOOT_CLASSPATH"
fi

# Setup the java endorsed dirs
JBOSS_ENDORSED_DIRS="$JBOSS_HOME/lib/endorsed"

# For Cygwin, switch paths to Windows format before running java
if $cygwin; then
    JBOSS_HOME=`cygpath --path --windows "$JBOSS_HOME"`
    JAVA_HOME=`cygpath --path --windows "$JAVA_HOME"`
    JBOSS_CLASSPATH=`cygpath --path --windows "$JBOSS_CLASSPATH"`
    JBOSS_ENDORSED_DIRS=`cygpath --path --windows "$JBOSS_ENDORSED_DIRS"`    
fi

# Execute the JVM
exec "$JAVA" \
    $JAVA_OPTS \
    -Djava.endorsed.dirs="$JBOSS_ENDORSED_DIRS" \
    -Dprogram.name="$PROGNAME" \
    -classpath $JBOSS_CLASSPATH \
    org.jboss.console.twiddle.Twiddle "$@"
