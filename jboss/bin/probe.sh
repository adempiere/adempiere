#!/bin/sh
### ====================================================================== ###
##                                                                          ##
##  JGroups Cluster Discovery Script                                        ##
##                                                                          ##
### ====================================================================== ###

# Discovers all UDP-based members running on a certain mcast address (use -help for help)
# Probe [-help] [-addr <addr>] [-port <port>] [-ttl <ttl>] [-timeout <timeout>]

CLASSPATH=.:../lib/commons-logging.jar:../server/all/lib/jgroups.jar:$CLASSPATH

# OS specific support (must be 'true' or 'false').
cygwin=false;
case "`uname`" in
    CYGWIN*)
        cygwin=true
        ;;
esac

if [ $cygwin = "true" ]; then
   CP=`cygpath -wp $CLASSPATH`
else
   CP=$CLASSPATH
fi

java -cp $CP org.jgroups.tests.Probe $*
