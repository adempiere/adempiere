@Title Copy Database after backup
@Rem $Id: myDBcopyTemplate.bat,v 1.4 2003/12/04 04:38:27 jjanke Exp $

@Echo Modify the script myDBcopy to copy the database backup

@Rem 	This example creates one inique file per day in a month
@Rem	You may want to copy it to another disk first
@Rem	Note that the %DATE% parameter is local specific.
@Rem	In Germany, it is %DATE:~3,2%
@Rem	When called, the following files were created:
@Rem		%ADEMPIERE_HOME%\data\ExpDat.dmp
@Rem		%ADEMPIERE_HOME%\data\ExpDat.log
@Rem		%ADEMPIERE_HOME%\data\ExpDat.jar (containing the above)

@Echo Creating ExpDat_%DATE:~7,2%.jar
jar cvfM ExpDat_%DATE:~7,2%.jar %ADEMPIERE_HOME%\data\ExpDat.dmp
@dir ExpDat_%DATE:~7,2%.jar

@Echo Copy comes here ...

@sleep 30