@Title PLAF Test

@Rem $Id: PLAF_Test.bat,v 1.3 2002/03/16 06:00:11 jjanke Exp $
@Rem Put the swing.properties file in the \rje\lib directory
@set JAVA_HOME=D:\j2sdk1.4.0

@Rem 
%JAVA_HOME%\jre\bin\java -cp D:\Adempiere\looks\CLooks.jar;%JAVA_HOME%\demo\jfc\SwingSet2\SwingSet2.jar org.compiere.plaf.AdempierePLAF SwingSet2

@Pause