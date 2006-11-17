# ---------------------------------------------------------
# -- This simple script is an example of how to start dblook in 
# -- a NetworkServer environment.
# --
#-- REQUIREMENTS: 
# -- You must have the Derby libraries in your classpath
# -- 
# -- See the setEmbeddedCP.ksh for an example of
# -- how to do this.
# --
# -- This file for use on Unix ksh systems
# ---------------------------------------------------------

# DERBY_INSTALL=

[ -z "$CLASSPATH" ] && {
  . "$DERBY_INSTALL"/frameworks/NetworkServer/bin/setNetworkClientCP.ksh
}

# ---------------------------------------------------------
# -- start dblook
# ---------------------------------------------------------
java org.apache.derby.tools.dblook $@

# ---------------------------------------------------------
# -- To use a different JVM with a different syntax, simply edit
# -- this file
# ---------------------------------------------------------

