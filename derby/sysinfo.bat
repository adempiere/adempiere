@REM ---------------------------------------------------------
@REM -- This batch file is an example of how to use sysinfo to get
@REM -- important system information
@REM --
@REM -- REQUIREMENTS:
@REM --
@REM --  This utility will report important system info about 
@REM --  jar files which are in your classpath. Jar files which are not
@REM --  if your classpath will not be reported. 
@REM --
@REM -- Check the setNetworkServerCP.bat to see an example of adding the
@REM -- the Derby jars to your classpath.
@REM -- 
@REM -- This file for use on Windows systems
@REM ---------------------------------------------------------

rem set DERBY_INSTALL=
 
@if !"%CLASSPATH%"==! call "%DERBY_INSTALL%"/frameworks/NetworkServer/bin/setNetworkServerCP.bat
@if "%CLASSPATH%" == "" call "%DERBY_INSTALL%"/frameworks/NetworkServer/bin/setNetworkServerCP.bat

@REM ---------------------------------------------------------
@REM -- start sysinfo
@REM ---------------------------------------------------------
java org.apache.derby.drda.NetworkServerControl sysinfo %*

@REM ---------------------------------------------------------
@REM -- To use a different JVM with a different syntax, simply edit
@REM -- this file
@REM ---------------------------------------------------------


