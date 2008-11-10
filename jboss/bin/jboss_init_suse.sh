#!/bin/sh
#
# JBoss Control Script
#
# To use this script
# run it as root - it will switch to the specified user
# It loses all console output - use the log.
#
# Here is a little (and extremely primitive)
# startup/shutdown script for SuSE systems. It assumes
# that JBoss lives in /usr/local/jboss, it's run by user
# 'jboss' and JDK binaries are in /usr/local/jdk/bin. All
# this can be changed in the script itself.
#
# Either amend this script for your requirements
# or just ensure that the following variables are set correctly
# before calling the script.
#
### BEGIN INIT INFO
# Provides: jboss
# Default-Start: 3 5
# Default-Stop: 0 1 2 6
# Description: Start the JBoss application server.
### END INIT INFO

#define where jboss is - this is the directory containing directories log, bin, conf etc
JBOSS_HOME=${JBOSS_HOME:-"/opt/jboss"}

#make java is on your path
JAVAPTH=${JAVAPTH:-"/usr/java/j2sdk1.4.1/bin"}

#define the script to use to start jboss
JBOSSSH=${JBOSSSH:-"$JBOSS_HOME/bin/run.sh -c default"}

# Shell functions sourced from /etc/rc.status:
#      rc_check         check and set local and overall rc status
#      rc_status        check and set local and overall rc status
#      rc_status -v     ditto but be verbose in local rc status
#      rc_status -v -r  ditto and clear the local rc status
#      rc_failed        set local and overall rc status to failed
#      rc_reset         clear local rc status (overall remains)
#      rc_exit          exit appropriate to overall rc status
. /etc/rc.status

# First reset status of this service
rc_reset

# Return values acc. to LSB for all commands but status:
# 0 - success
# 1 - misc error
# 2 - invalid or excess args
# 3 - unimplemented feature (e.g. reload)
# 4 - insufficient privilege
# 5 - program not installed
# 6 - program not configured
#
# Note that starting an already running service, stopping
# or restarting a not-running service as well as the restart
# with force-reload (in case signalling is not supported) are
# considered a success.

if [ -n "$JBOSS_CONSOLE" -a ! -d "$JBOSS_CONSOLE" ]; then
  # ensure the file exists
  touch $JBOSS_CONSOLE
fi

if [ -n "$JBOSS_CONSOLE" -a ! -f "$JBOSS_CONSOLE" ]; then
  echo "WARNING: location for saving console log invalid: $JBOSS_CONSOLE"
  echo "WARNING: ignoring it and using /dev/null"
  JBOSS_CONSOLE="/dev/null"
fi

#define what will be done with the console log
JBOSS_CONSOLE=${JBOSS_CONSOLE:-"/opt/jboss/log/jboss.log"}

#define the user under which jboss will run, or use RUNASIS to run as the current user
JBOSSUS=${JBOSSUS:-"jboss"}

CMD_START="cd $JBOSS_HOME/bin; $JBOSSSH"

if [ "$JBOSSUS" = "RUNASIS" ]; then
  SUBIT=""
else
  SUBIT="su - $JBOSSUS -c "
fi

if [ -z "`echo $PATH | grep $JAVAPTH`" ]; then
  export PATH=$PATH:$JAVAPTH
fi

if [ ! -d "$JBOSS_HOME" ]; then
  echo JBOSS_HOME does not exist as a valid directory : $JBOSS_HOME
  exit 1
fi

function procrunning() {
   procid=0
   JBOSSSCRIPT=$(echo $JBOSSSH | awk '{print $1}' | sed 's/\//\\\//g')
   for procid in `/sbin/pidof -x "$JBOSSSCRIPT"`; do
       ps -fp $procid | grep "${JBOSSSH% *}" > /dev/null && pid=$procid
   done
}


stop() {
    pid=0
    procrunning
    if [ $pid = '0' ]; then
        echo -n -e "\nNo JBossas is currently running\n"
        exit 1
    fi

    RETVAL=1

    # If process is still running

    # First, try to kill it nicely
    for id in `ps --ppid $pid | awk '{print $1}' | grep -v "^PID$"`; do
       if [ -z "$SUBIT" ]; then
           kill -15 $id
       else
           $SUBIT "kill -15 $id"
       fi
    done

    sleep=0
    while [ $sleep -lt 120 -a $RETVAL -eq 1 ]; do
        echo -n -e "\nwaiting for processes to stop";
        sleep 10
        sleep=`expr $sleep + 10`
        pid=0
        procrunning
        if [ $pid == '0' ]; then
            RETVAL=0
        fi
    done

    # Still not dead... kill it

    count=0
    pid=0
    procrunning

    if [ $RETVAL != 0 ] ; then
        echo -e "\nTimeout: Shutdown command was sent, but process is still running with PID $pid"
        exit 1
    fi

    echo
    exit 0
}


case "$1" in
start)
    echo -n "Starting JBoss application server: "
    cd $JBOSS_HOME/bin
    if [ -z "$SUBIT" ]; then
        eval $CMD_START >${JBOSS_CONSOLE} 2>&1 &
    else
        $SUBIT "$CMD_START >${JBOSS_CONSOLE} 2>&1 &"
    fi

    # Remember status and be verbose
    rc_status -v
    ;;
stop)
    echo -n "Shutting down JBoss application server: "
    stop

    # Remember status and be verbose
    rc_status -v
    ;;
restart)
    $0 stop
    $0 start

    # Remember status and be quiet
    rc_status
    ;;
*)
    echo "usage: $0 (start|stop|restart|help)"
esac
