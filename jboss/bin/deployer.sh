#!/bin/sh
### ====================================================================== ###
##                                                                          ##
##  JBoss Bootstrap Script                                                  ##
##                                                                          ##
### ====================================================================== ###

### $Id: deployer.sh,v 1.3 2005/09/04 17:52:33 jjanke Exp $ ###

DIRNAME=`dirname $0`
PROGNAME=`basename $0`
GREP="grep"

#
# Helper to complain.
#
warn() {
    echo "${PROGNAME}: $*"
}

#
# Helper to puke.
#
die() {
    warn $*
    exit 1
}

# OS specific support (must be 'true' or 'false').
cygwin=false;
darwin=false;
case "`uname`" in
    CYGWIN*)
        cygwin=true
        ;;

    Darwin*)
        darwin=true
        ;;
esac

# For Cygwin, ensure paths are in UNIX format before anything is touched
if $cygwin ; then
    [ -n "$JBOSS_HOME" ] &&
        JBOSS_HOME=`cygpath --unix "$JBOSS_HOME"`
    [ -n "$JAVA_HOME" ] &&
        JAVA_HOME=`cygpath --unix "$JAVA_HOME"`
    [ -n "$JAVAC_JAR" ] &&
        JAVAC_JAR=`cygpath --unix "$JAVAC_JAR"`
fi

# Setup JBOSS_HOME
if [ "x$JBOSS_HOME" = "x" ]; then
    # get the full path (without any relative bits)
    JBOSS_HOME=`cd $DIRNAME/..; pwd`
fi
export JBOSS_HOME

# Setup the JVM
if [ "x$JAVA_HOME" != "x" ]; then
    JAVA="$JAVA_HOME/bin/java"
else
    JAVA="java"
fi

# Setup the classpath
programjar="$JBOSS_HOME/bin/deployer.jar"
if [ ! -f $programjar ]; then
    die "Missing required file: $programjar"
fi
JBOSS_BOOT_CLASSPATH="$programjar"

if [ "x$JBOSS_CLASSPATH" = "x" ]; then
    JBOSS_CLASSPATH="$JBOSS_BOOT_CLASSPATH:$JAVAC_JAR"
else
    JBOSS_CLASSPATH="$JBOSS_CLASSPATH:$JBOSS_BOOT_CLASSPATH:$JAVAC_JAR"
fi

# Check for SUN(tm) JVM w/ HotSpot support
if [ "x$HAS_HOTSPOT" = "x" ]; then
    HAS_HOTSPOT=`$JAVA -version 2>&1 | $GREP HotSpot`
fi

# If JAVA_OPTS is not set and the JVM is HOTSPOT enabled, then the server mode
if [ "x$JAVA_OPTS" = "x" -a "x$HAS_HOTSPOT" != "x" ]; then
    # MacOS does not support -server flag
    if [ "$darwin" != "true" ]; then
        JAVA_OPTS="-server"
    fi
fi

# Setup JBoss sepecific properties
JAVA_OPTS="$JAVA_OPTS -Dprogram.name=$PROGNAME"

# For Cygwin, switch paths to Windows format before running java
if $cygwin; then
    JBOSS_HOME=`cygpath --path --windows "$JBOSS_HOME"`
    JAVA_HOME=`cygpath --path --windows "$JAVA_HOME"`
    JBOSS_CLASSPATH=`cygpath --path --windows "$JBOSS_CLASSPATH"`
fi

# Execute the JVM
exec $JAVA $JAVA_OPTS \
    -classpath "$JBOSS_CLASSPATH" \
    org.jboss.jmx.service.RemoteDeployer "$@"
