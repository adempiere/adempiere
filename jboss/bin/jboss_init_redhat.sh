#!/bin/sh
#
# $Id: jboss_init_redhat.sh 71252 2008-03-25 17:52:00Z dbhole $
#
# JBoss Control Script
#
# To use this script run it as root - it will switch to the specified user
#
# Here is a little (and extremely primitive) startup/shutdown script
# for RedHat systems. It assumes that JBoss lives in /usr/local/jboss,
# it's run by user 'jboss' and JDK binaries are in /usr/local/jdk/bin.
# All this can be changed in the script itself. 
#
# Either modify this script for your requirements or just ensure that
# the following variables are set correctly before calling the script.

#define where jboss is - this is the directory containing directories log, bin, conf etc
JBOSS_HOME=${JBOSS_HOME:-"/usr/local/jboss"}

#define the user under which jboss will run, or use 'RUNASIS' to run as the current user
JBOSS_USER=${JBOSS_USER:-"jboss"}

#make sure java is in your path
JAVAPTH=${JAVAPTH:-"/usr/local/jdk/bin"}

#configuration to use, usually one of 'minimal', 'default', 'all'
JBOSS_CONF=${JBOSS_CONF:-"default"}

#if JBOSS_HOST specified, use -b to bind jboss services to that address
JBOSS_BIND_ADDR=${JBOSS_HOST:+"-b $JBOSS_HOST"}

#define the script to use to start jboss
JBOSSSH=${JBOSSSH:-"$JBOSS_HOME/bin/run.sh -c $JBOSS_CONF $JBOSS_BIND_ADDR"}

if [ "$JBOSS_USER" = "RUNASIS" ]; then
  SUBIT=""
else
  SUBIT="su - $JBOSS_USER -c "
fi

if [ -n "$JBOSS_CONSOLE" -a ! -d "$JBOSS_CONSOLE" ]; then
  # ensure the file exists
  touch $JBOSS_CONSOLE
  if [ ! -z "$SUBIT" ]; then
    chown $JBOSS_USER $JBOSS_CONSOLE
  fi 
fi

if [ -n "$JBOSS_CONSOLE" -a ! -f "$JBOSS_CONSOLE" ]; then
  echo "WARNING: location for saving console log invalid: $JBOSS_CONSOLE"
  echo "WARNING: ignoring it and using /dev/null"
  JBOSS_CONSOLE="/dev/null"
fi

#define what will be done with the console log
JBOSS_CONSOLE=${JBOSS_CONSOLE:-"/dev/null"}

JBOSS_CMD_START="cd $JBOSS_HOME/bin; $JBOSSSH"

if [ -z "`echo $PATH | grep $JAVAPTH`" ]; then
  export PATH=$PATH:$JAVAPTH
fi

if [ ! -d "$JBOSS_HOME" ]; then
  echo JBOSS_HOME does not exist as a valid directory : $JBOSS_HOME
  exit 1
fi

echo JBOSS_CMD_START = $JBOSS_CMD_START

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
    cd $JBOSS_HOME/bin
    if [ -z "$SUBIT" ]; then
        eval $JBOSS_CMD_START >${JBOSS_CONSOLE} 2>&1 &
    else
        $SUBIT "$JBOSS_CMD_START >${JBOSS_CONSOLE} 2>&1 &" 
    fi
    ;;
stop)
    stop
    ;;
restart)
    $0 stop
    $0 start
    ;;
*)
    echo "usage: $0 (start|stop|restart|help)"
esac

