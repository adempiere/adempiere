@Rem Create Derby SQLJ
@Rem 
@Rem
@Rem runDefault first
@Rem

@Echo on
@if !"%SQLJPATH%"==! call setDefault.bat

@Echo Load Derby SQLJ and functions...
@REM SET CLASSPATH=

@java -Dij.driver=org.apache.derby.jdbc.ClientDriver -Dij.connection.Adempiere=jdbc:derby:%Derby_path%\Adempiere;create=true -Dij.user=%IJ_USER% -Dij.password=%IJ_PASSWORD%  org.apache.derby.tools.ij < %SQLJPATH%\derby\createSQLJ.sql
pause
