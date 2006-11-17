@Rem call with parameter
@Rem	1 - sourcepath entry
@Rem	2 - destination entry
@Rem	3 - parameters
@Rem assumes that you have a file packages.txt in the calling directory

@Rem $Id: documentation.bat,v 1.11 2005/09/16 03:51:18 jjanke Exp $
@CALL ..\utils_dev\myDevEnv.bat

@Set CLASSPATH=..\lib\Adempiere.jar;..\lib\CCTools.jar;..\lib\oracle.jar;..\lib\postgresql.jar;..\lib\jPDF.jar
@Set CLASSPATH=%CLASSPATH%;..\lib\CSTools.jar;..\lib\jboss.jar;..\
@Set CLASSPATH=%CLASSPATH%;..\tools\lib\j2ee.jar;..\tools\lib\junit.jar

javadoc -sourcepath %1 -d %2 -use -author -breakiterator -version -link http://java.sun.com/j2se/1.5.0/docs/api -link http://java.sun.com/j2ee/1.4/docs/api -splitindex -windowtitle "Adempiere %ADEMPIERE_VERSION% API Documentation" -doctitle "Adempiere<sup>TM</sup> API Documentation" -header "<b>Adempiere %ADEMPIERE_VERSION%</b>" -bottom "Copyright (c) 1999-2004 ComPiere, Inc. - Author: Jorg Janke" -overview doc\overview.html %3 -J-Xmx180m @packages.txt



