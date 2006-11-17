#!/bin/bash
#
# FileName:	adempiere.init
# Description:	adempiere erp software startup and shutdown
# Vendor:	K.K. Alice
# Created:	05. April 2004
# Author:	S. Christians
#
# FileTarget:	/etc/init.d/adempiere
# FileOwner:	root.root
# FilePerms:	0755
#
# chkconfig:	2345 97 06
# $Id: serverStart.sh,v 1.2 2004/05/09 04:53:29 jjanke Exp $

# initialization
# adjust these variables to your environment
EXECDIR=/opt/adempiere/Adempiere
ENVFILE=/opt/adempiere/.bash_profile

. /etc/rc.d/init.d/functions
 
RETVAL=0
ADEMPIERESTATUS=

getadempierestatus() {
    ADEMPIERESTATUSSTRING=$(ps -ax | grep -v grep | grep $EXECDIR)
    echo $ADEMPIERESTATUSSTRING | grep $EXECDIR &> /dev/null
    ADEMPIERESTATUS=$?
}

start () {
    getadempierestatus
    if [ $ADEMPIERESTATUS -eq 0 ] ; then
	echo "adempiere is already running"
	return 1
    fi
    echo -n "Starting Adempiere ERP: "
    source $ENVFILE 
    # we need to stay root for logging
    # (adempiere user has no write access to /var/log/...)
    su -c "cd $EXECDIR/utils;$EXECDIR/utils/RUN_Server2.sh &> /var/log/adempiere.log &"
    RETVAL=$?
    if [ $RETVAL -eq 0 ] ; then
	# wait for server to be confirmed as started in logfile
	STATUSTEST=0
	while [ $STATUSTEST -eq 0 ] ; do
	tail -n 1 /var/log/adempiere.log | grep 'INFO.*\[Server\].*Started in' &> /dev/null && STATUSTEST=1
	done
	# avoid race conditions
	sleep 5
	echo_success
	echo
    else
	echo_failure
	echo
    fi
    return $RETVAL
}

stop () {
    getadempierestatus
    if [ $ADEMPIERESTATUS -ne 0 ] ; then
	echo "adempiere is already stopped"
	return 1
    fi
    echo -n "Stopping Adempiere ERP: "
    source $ENVFILE 
    su -c "cd $EXECDIR/utils;$EXECDIR/utils/RUN_Server2Stop.sh &> /dev/null &"
    RETVAL=$?
    if [ $RETVAL -eq 0 ] ; then
	# wait for server to be confirmed as halted in logfile
	STATUSTEST=0
	while [ $STATUSTEST -eq 0 ] ; do
	tail -n 1 /var/log/adempiere.log | grep 'Halting VM' &> /dev/null && STATUSTEST=1
	done
	# avoid race conditions
	sleep 5
	echo_success
	echo
    else
	echo_failure
	echo
    fi
    return $RETVAL
}

restart () {
    stop
    start
}

condrestart () {
    getadempierestatus
    if [ $ADEMPIERESTATUS -eq 0 ] ; then
	restart
    fi
}

rhstatus () {
    getadempierestatus
    if [ $ADEMPIERESTATUS -eq 0 ] ; then
	echo
	echo "adempiere is running:"
	ps -ax | grep -v grep | grep $EXECDIR | sed 's/^[[:space:]]*\([[:digit:]]*\).*:[[:digit:]][[:digit:]][[:space:]]\(.*\)/\1 \2/'
	echo
    else
	echo "adempiere is stopped"
    fi
}

case "$1" in
    start)
	start
        ;;
    stop)
	stop
        ;;
    reload)
	restart
	;;
    restart)
	restart
	;;
    condrestart)
	condrestart
	;;
    status)
	rhstatus
	;;
    *)
        echo $"Usage: $0 {start|stop|reload|restart|condrestart|status}"
        exit 1
esac
 
exit 0

