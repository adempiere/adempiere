# ---------------------------------------------------------
# -- This script file sets the CLASSPATH environment variable
# -- for use with Derby products in Network Server mode
# --
# -- To use this script from other locations, change the 
# -- value assigned to DERBY_INSTALL to be an absolute path 
# -- (export DERBY_INSTALL=/opt/derby) instead of the current relative path
# --
# -- This file for use on Unix ksh systems
# -- 
# ---------------------------------------------------------
# DERBY_INSTALL=

export CLASSPATH="${DERBY_INSTALL}/lib/derbyclient.jar:${DERBY_INSTALL}/lib/derbytools.jar:${CLASSPATH}"
