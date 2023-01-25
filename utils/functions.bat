@echo on
@REM Identify if the script runs from cmd console
@REM Modified by Horacio Miranda, hmiranda@prolinux.cl, 2021-10-30
@REM Variable CALLED_WITH_CMD_C is set when the bat is called from cmd
@REM CALLED_WITH_CMD_C [YES|NO]

@REM Example
@REM IF "%CALLED_WITH_CMD_C%"=="NO"  (
@REM @pause
@REM @exit
@REM )

@Rem Check java home
@SET JAVA_HOME=%JAVA_HOME:Program Files=PROGRA~1%
@IF EXIST %JAVA_HOME%\bin (
  GOTO JAVAOK
)
@SET PATH=%JAVA_HOME%\bin;%PATH%

:JAVAHOME
echo %JAVA_HOME%
@echo JAVA_HOME is not set.
@echo You may not be able to build Adempiere
@echo Set JAVA_HOME to the directory of your local JDK.
@pause
@exit

:JAVAOK
@Rem Set classpath
@SET ANT_HOME=..\tools\lib\ant\apache-ant-1.10.10
@SET JAVA_CLASSPATH=%CLASSPATH%;%ANT_HOME%\lib\ant-launcher.jar;..\tools\lib\commons-net-1.4.0.jar

@:pauseIfDoubleClicked
@setlocal enabledelayedexpansion
@set testl=%cmdcmdline:"=%
@set testr=!testl:%~nx0=!
@if "%testl%" == "%testr%" (
@  set CALLED_WITH_CMD_C=YES
) ELSE (
@  set CALLED_WITH_CMD_C=NO
)