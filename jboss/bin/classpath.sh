#!/bin/sh
### ====================================================================== ###
##                                                                          ##
##  A tool to determine JBoss classpaths.                                   ##
##                                                                          ##
### ====================================================================== ###

### $Id: classpath.sh 7809 2002-05-22 02:39:56Z user57 $ ###

DIRNAME=`dirname $0`
PROGNAME=`basename $0`
CAT="cat"

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

#
# Print the command line help message.
#
printHelpMessage() {
    $CAT <<EOF
usage: $PROGNAME [options] <classpath>

options:
    -h, --help            Print this help message.
    --                    Stop processing options.
    -r, --relative        Use relative paths.

classpath:
    -c, --client          Client classpath (client/*).
    -s, --server          Server classpath (lib/*).
    -b, --both            Both the client and server classpaths.
EOF
}

RELATIVE=false

#
# Process command line options.
#
processCommandLine() {
    while [ "x$1" != "x" ]; do
	case "$1" in
	    --help|-h)
		printHelpMessage
		exit 1
		;;

	    --relative|-r)
		RELATIVE=true
		;;

	    --)
		shift
		if [ "x$argv" = "x" ]; then
		    argv="$1"
		else
		    argv="$argv $1"
		fi
		break
		;;

	    *)
		if [ "x$argv" = "x" ]; then
		    argv="$1"
		else
		    argv="$argv $1"
		fi
		;;
	esac
	shift
    done
}

if [ -n "$CLASSPATH" ] ; then
  LOCALCLASSPATH=$CLASSPATH
fi

#
# Print the class path for the given type.
#
printClassPath() {

#
# jason: should be fixed to only include proper classpath bits
#

    case "$1" in
	-c|--client)
            DIRLIBS="`ls $JBOSS_HOME/client/*`:$JBOSS_HOME/client"
	    ;;

	-s|--server)
            DIRLIBS=`ls $JBOSS_HOME/lib/*`
	    ;;

	-b|--both)
	    DIRLIBS="`ls $JBOSS_HOME/client/*`:$JBOSS_HOME/client"
            DIRLIBS="${DIRLIBS}:`ls $JBOSS_HOME/lib/*`"
	    ;;

	*)
	    printHelpMessage
	    exit 0
	    ;;
    esac

    for i in ${DIRLIBS}; do 
	if [ "$i" != "${DIRLIBS}" ] ; then
	    if [ -z "$LOCALCLASSPATH" ] ; then
		LOCALCLASSPATH=$i
	    else
		LOCALCLASSPATH="$i":$LOCALCLASSPATH
	    fi
	fi
    done

    # For Cygwin, switch paths to Windows format before running java
    if $cygwin; then
	LOCALCLASSPATH=`cygpath --path --windows "$LOCALCLASSPATH"`
    fi

    echo $LOCALCLASSPATH
}

#
# Main entry-point.
#
main() {
    argv=""

    # process the command line
    processCommandLine "$@"

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
	if $RELATIVE ; then
	    JBOSS_HOME="$DIRNAME/.."
	else
	    # get the full path (without any relative bits)
	    JBOSS_HOME=`cd $DIRNAME/..; pwd`
	fi
    fi

    if [ "x$argv" = "x" ]; then
	printHelpMessage
	exit 0
    fi

    printClassPath $argv
}

# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #

#
# Boot-strap
#
main "$@"
