@echo off
rem -------------------------------------------------------------------------
rem JGroups Cluster Discovery Script for Win32
rem -------------------------------------------------------------------------

REM Discovers all UDP-based members running on a certain mcast address (use -help for help)
REM Probe [-help] [-addr <addr>] [-port <port>] [-ttl <ttl>] [-timeout <timeout>]

set CLASSPATH=..\lib\commons-logging.jar;..\server\all\lib\jgroups.jar

set CP=%CLASSPATH%

java -cp %CP% org.jgroups.tests.Probe %*
