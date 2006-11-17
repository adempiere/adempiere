@REM ---------------------------------------------------------
@REM -- This batch file sets the CLASSPATH environment variable
@REM -- for use with Derby products in NetworkServer mode
@REM --
@REM -- To use this script from other locations, change the 
@REM -- value assigned to DERBY_INSTALL to be an absolute path 
@REM -- (set DERBY_INSTALL=C:\derby) instead of the current relative path
@REM --
@REM -- This file for use on Windows systems
@REM -- 
@REM ---------------------------------------------------------

rem set DERBY_INSTALL=

FOR %%X in ("%DERBY_INSTALL%") DO SET DERBY_INSTALL=%%~sX

set CLASSPATH=%DERBY_INSTALL%\lib\derby.jar;%DERBY_INSTALL%\lib\derbytools.jar;%DERBY_INSTALL%\lib\derbynet.jar;%CLASSPATH%
