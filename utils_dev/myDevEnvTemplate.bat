@Rem	My Development Environment
@Rem
@Rem	This script sets variable for compiling Adempiere from source
@Rem	
@Rem	
@Rem 	$Header: /cvsroot/adempiere/utils_dev/myDevEnvTemplate.bat,v 1.19 2005/09/10 01:51:13 jjanke Exp $


@Rem  Check the following parameters:
@Rem  -------------------------------

@Rem	Set Java Home
@SET JAVA_HOME=C:\Program Files\Java\jdk1.5.0_04
@IF NOT EXIST "%JAVA_HOME%\bin" ECHO "** JAVA_HOME NOT found"
@SET PATH=%JAVA_HOME%\bin;%PATH%

@Rem	Set Adempiere Source Directory
@SET ADEMPIERE_SOURCE=C:\Adempiere\adempiere-all
@IF NOT EXIST "%ADEMPIERE_SOURCE%" ECHO "** ADEMPIERE_SOURCE NOT found"

@Rem	Passwords for the keystore
@SET KEYTOOL_PASS=%KEY_PASSWORD%
@IF "%KEYTOOL_PASS%"=="" SET KEYTOOL_PASS=myPassword

@Rem	Keystore & FTP Password
@SET ANT_PROPERTIES=-Dpassword=%KEYTOOL_PASS% -DftpPassword=%FTP_PASSWORD%

@Rem	Ant to send email after completion - change or delete
@SET ANT_PROPERTIES=%ANT_PROPERTIES% -DMailLogger.mailhost=xxx -DMailLogger.from=xxxx -DMailLogger.failure.to=xxxx -DMailLogger.success.to=xxxx

@Rem	Automatic Installation - Where Adempiere will be unzipped
@SET ADEMPIERE_ROOT=C:\
@Rem	Automatic Installation - Resulting Home Directory
@SET ADEMPIERE_HOME=%ADEMPIERE_ROOT%Adempiere
@Rem	Automatic Installation - Share for final Installers
@SET ADEMPIERE_INSTALL=C:\Install
@IF NOT EXIST %ADEMPIERE_INSTALL% Mkdir %ADEMPIERE_INSTALL%


@Rem  ---------------------------------------------------------------
@Rem  In most cases you don't need to change anything below this line
@Rem  If you need to define something manually do it above this line,
@Rem  it should work, since most variables are checked before set.
@Rem  ---------------------------------------------------------------

@SET CURRENTDIR=%CD%

@Rem Set Version
@SET ADEMPIERE_VERSION=ADempiere
@SET ADEMPIERE_VERSION_FILE=312
@SET ADEMPIERE_VENDOR=ADempiere

@SET ENCODING=UTF-8

@Rem	ClassPath
@IF NOT EXIST "%JAVA_HOME%\lib\tools.jar" ECHO "** Need Full Java SDK **"
@SET CLASSPATH=%CLASSPATH%;%JAVA_HOME%\lib\tools.jar

@IF NOT EXIST %ADEMPIERE_SOURCE%\tools\lib\ant.jar ECHO "** Ant.jar NOT found **"
@SET CLASSPATH=%CLASSPATH%;%ADEMPIERE_SOURCE%\tools\lib\ant.jar;%ADEMPIERE_SOURCE%\tools\lib\ant-launcher.jar;%ADEMPIERE_SOURCE%\tools\lib\ant-swing.jar;%ADEMPIERE_SOURCE%\tools\lib\ant-commons-net.jar;%ADEMPIERE_SOURCE%\tools\lib\commons-net-1.4.0.jar
@Rem SET CLASSPATH=%CLASSPATH%;%ADEMPIERE_SOURCE%\jboss\lib\xml-apis.jar


@Rem	Set XDoclet 1.1.2 Environment
@SET XDOCLET_HOME=%ADEMPIERE_SOURCE%\tools

@Rem	Java Keystore for signing jars
@IF NOT EXIST %ADEMPIERE_SOURCE%\keystore MKDIR %ADEMPIERE_SOURCE%\keystore
@IF EXIST "%ADEMPIERE_SOURCE%\keystore\myKeystore" GOTO CHECKKEYVALUE

:CREATEADEMPIEREKEY
@Echo No Java Keystore found, creating ...
@Rem	.
@Rem	This is the keystore for code signing.
@Rem	Replace it with the official certificate.
@Rem	Note that this is not the SSL certificate.
@Rem	.

SET KEYTOOL_DNAME="CN=myName, OU=myName, O=myOrg, L=myTown, ST=myState, C=US"

"%JAVA_HOME%\bin\keytool" -genkey -keyalg rsa -alias adempiere -dname %KEYTOOL_DNAME% -keypass %KEYTOOL_PASS% -validity 365 -keystore %ADEMPIERE_SOURCE%\keystore\myKeystore -storepass %KEYTOOL_PASS%

"%JAVA_HOME%\bin\keytool" -selfcert -alias adempiere -dname %KEYTOOL_DNAME% -keypass %KEYTOOL_PASS% -validity 180 -keystore %ADEMPIERE_SOURCE%\keystore\myKeystore -storepass %KEYTOOL_PASS%

:CHECKKEYVALUE
@"%JAVA_HOME%\bin\keytool" -list -alias adempiere -keyStore %ADEMPIERE_SOURCE%\keystore\myKeystore -storepass %KEYTOOL_PASS%
@IF ERRORLEVEL 1 GOTO :CREATEADEMPIEREKEY

@Rem Set ADEMPIERE_ENV for all other scripts.
@SET ADEMPIERE_ENV=Y
