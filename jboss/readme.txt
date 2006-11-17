$Id: readme.txt,v 1.1 2006/04/21 17:58:42 jjanke Exp $

-----------------
Base: JBoss 3.2.3
-----------------

jboss-service.xml
=================
1099 -> @ADEMPIERE_JNP_PORT@ = JNDI JNP invoker listening port
1098 = RmiPort
-
8083 = WebService Class Loading
-
4444 = JMX RMI/JRMP invoker
4445 = Pooled invoker


login-config.xml
================
- no changes

http-invoker.sar/META-INF/jboss-service.xml
===========================================
8080 -> @ADEMPIERE_WEB_PORT@ = HTTP Invoker

jbossweb-tomcat41.sar/META-INF/jboss-service.xml
================================================
8080 -> @ADEMPIERE_WEB_PORT@ = HTTP/1.1 CoyoteConnector
8443 -> @ADEMPIERE_SSL_PORT@ = SSL HTTP/1.1 CoyoteConnector
-> @ADEMPIERE_KEYSTORE@ = keystoreFile
-> @ADEMPIERE_KEYSTOREPASS@ = keystorePass
--
8009 = AJP 1.3
8443 = AJP 1.3 redirect port


Server Libraries used:
----------------------
jboss-jmx.jar
jboss-system.jar


Client Librarties used:
-----------------------
jboss-client.jar
jboss-common-client.jar
jboss-j2ee.jar
jboss.client.jar
jboss-transactions-client.jar
jmx-rmi-connector-client.jar
jnp-client.jar
