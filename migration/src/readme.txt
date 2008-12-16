/*
 # ADempiere contribution
 # Author: Karsten Thiemann, kthiemann@adempiere.org
 # How to build a Compiere/Adempiere migration script.
*/


Installation of reference database (target)

1.	Create a new Oracle database with the name c253b.
2.	Check the connectivity of the new db with tnsping:
	from console: tnsping c253b
3. 	Download Compiere253b (the version to migrate to)
4.	Import the compiere reference into the new database
	Replace COMPIERE_DB_PASSWORD with the password of the new oracle
	database system user password and COMPIERE253b_DIRECTORY with
	the directory of the downloaded Compiere253b
	
	- create a user compiere with password compiere in the reference db:
	prompt>sqlplus system/COMPIERE_DB_PASSWORD@c253b @"COMPIERE253b_DIRECTORY\utils\oracle\CreateUser.sql" compiere compiere
	
	- import dump
	prompt>imp system/COMPIERE_DB_PASSWORD@c253b FILE="COMPIERE253b_DIRECTORY\data\Compiere.dmp" FROMUSER=(reference) TOUSER=compiere STATISTICS=RECALCULATE
	
	- import sqlj functions
	 prompt>loadjava -user compiere/COMPIERE_DB_PASSWORD@COMPIERE_DB_NAME -verbose -force -resolve "COMPIERE253b_DIRECTORY\lib\sqlj.jar"
	 prompt>sqlplus compiere/COMPIERE_DB_PASSWORD@COMPIERE_DB_NAME @"COMPIERE253b_DIRECTORY\utils\oracle\createSQLJ.sql"
5.	Check database via sqlplus or your favorite sql editor.
6. 	To avoid problems with your customizations I suggest to redo the steps 1-5 for the source
	database. Create a fresh database with the original compiere.dmp of your installation version.
	The main advantage is that you can share the generated migration script with the community and
	that you cannot drop your customizations accidently. 

Run the DBDifference.java

1.	Create a new Eclipse project and copy the java files into the project.
2.	Give access to oracle odbc driver (odbc14.jar) - add to classpath.
3. 	In DBDifference.java you need to adjust some global settings:
	the name of the databases to compare - for example "c253a" (your installation) and "c253b" 
	and their database users and passwords.
	You may want to set AD_ROLE_ID and AD_CLIENT_ID used for the generation
	of *_ACCESS entries for your clients admin (no need to change if you
	have one standard client)
	BEWARE - The application applies the created sql statements to the source db in
	order to find a working sequence for the statements. Make shure that you have
	a DB BACKUP! Or better use a separate (fresh installed) compiere db for the comparison.
4.	Run as Java application and copy the output into a textfile. 
	It needs A LOT OF MEMORY (256-512MB) - it's not optimized yet... This will
	take some minutes! Especially the comparison of the ad_elements take some time.
	Just wait for the 'done.' message :o)
	It will print out a lot of sql errors - don't mind - they only result from the sorting
	of the generated statements.

CHECK THE GENERATED SCRIPT CAREFULLY!
1. 	Drop all lines before the SCRIPT STARTS HERE! marker.
2. 	First look at the top of the script for unappliable statements
	look for: UNABLE TO APPLY THESE STATEMENTS marker.
	If you decided to run the application with two fresh databases, 
	you will find there some INSERT INTO AD_WINDOW_ACCESS/AD_PROCESS_ACCESS entries.
	Thats because the application generates these entries to provide window access for
	the admin of the first custom client - but a fresh compiere database doesn't have
	a client. Just copy these entries at the end of the other INSERT entries.
	You can ignore DROP CONSTRAINT statements - just delete them.
	If some statements are left over - try to apply them at after running the script.
3. 	Sometimes a temporarily default value is needed and the application tries to 
	find an applieable value - but applieable doesn't mean sensible in all cases.
	So please look for REPLACE_ME markers in the generated script and check the
	values.

APPLY THE SCRIPT TO YOUR DATABASE - BUT MAKE A BACKUP FIRST !
If you do this with the compared (source) database you have to re-import the db dump first,
because the db is changed by the application.
And please test it in a testing environment...
After testing - please share script with the community!

THE CLASSES ARE NOT MUCH TESTED YET - BUT AS RED1 SAID TO ME:
RELEASE EARLY - UPDATE OFTEN...

I hope you like it and give some feedback. 
