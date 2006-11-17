@REM ---------------------------------------------------------
@REM -- This batch file is an example of how to start dblook in 
@REM -- an NetworkServer environment.
@REM --
@REM -- REQUIREMENTS: 
@REM -- You must have the Derby libraries in your classpath
@REM -- 
@REM -- See the setNetworkClientCP.bat for an example of
@REM -- how to do this.
@REM --
@REM -- This file for use on Windows systems
@REM ---------------------------------------------------------

rem set DERBY_INSTALL=
 
@if !"%CLASSPATH%"==! call "%DERBY_INSTALL%"/frameworks/NetworkServer/bin/setNetworkClientCP.bat
@if "%CLASSPATH%" == "" call "%DERBY_INSTALL%"/frameworks/NetworkServer/bin/setNetworkClientCP.bat

@REM ---------------------------------------------------------
@REM -- start dblook
@REM ---------------------------------------------------------
java org.apache.derby.tools.dblook %*

@REM ---------------------------------------------------------
@REM -- To use a different JVM with a different syntax, simply edit
@REM -- this file
@REM ---------------------------------------------------------

