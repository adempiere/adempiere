@Rem Create Oracle SQLJ
@Rem Author + Copyright 1999-2005 Jorg Janke
@Rem $Id: create.bat,v 1.8 2005/05/31 07:28:22 jjanke Exp $
@Rem
@Rem Parameter: <adempiereDBuser>/<adempiereDBpassword>
@Rem

@Echo .
@Echo Load Oracle SQLJ ...
@SET CLASSPATH=
@call loadjava -user %1@%ADEMPIERE_DB_NAME% -verbose -force -resolve %ADEMPIERE_HOME%\lib\sqlj.jar

@Echo .

@Echo Create Oracle Functions ...
@sqlplus %1@%ADEMPIERE_DB_NAME% @%ADEMPIERE_HOME%\utils\oracle\createSQLJ.sql
