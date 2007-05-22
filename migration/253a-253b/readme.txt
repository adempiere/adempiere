 Written by Karsten Thiemann, kthiemann@adempiere.org
 Feel free to change everything you want, but you are using it at your own risk!
 
 Preparations
 1.	Make a database backup! Don't complain if something goes wrong,
 	just reapply your backup and report the error to the Adempiere forums
 	at: http://sourceforge.net/forum/?group_id=176962
 	You can use RUN_DBExport.bat in the Compiere2/utils folder.
 
 2.	Read and follow the instructions in the migration-structure.sql file.
 	You have to set some DEFAULT VALUES for some new columns. But it's 
 	done easily.
 	
 Migration of Compiere database
 1.	This script only migrates the database from Compiere253a to 253b!
 
 2.	It's only tested with Oracle - but it should work with other databases
 	as well because it is just sql. If you try it with a different database
 	(successful or not) then please report it to the Adempiere forums
 	at: http://sourceforge.net/forum/?group_id=176962

 3. 	First apply the migration-structure.sql via any SQL tool. 
 	
 	MAKE SHURE THAT YOU FOLLOWED THE INSTRUCTIONS IN THERE FIRST!
 	
 	I use the Oracle SQL Developer (it's free but you have to register), you
 	can find it here:
 	http://www.oracle.com/technology/products/database/sql_developer/index.html
 	Of course you can also use Toad or even just SQLPlus:
 	(from the folder with the migration*.sql)
 	sqlplus compiere@COMPIERE_DB_NAME @migration-structure.sql
 	
 	Dependent on the tool you use you will get some errors. They should
 	derive only from unrecognized comments (-> unknown command), thats
 	just because the sql tool treats the comments as sql commands - don't worry.
 
 4.	Apply the remaining scripts in the order
     migration-replace-views.sql 
     migration-newLines.sql, 
     migration-updateElements.sql, 
 	 and finally old_migration_missing.sql the same way.
  
 
 Installation of Compiere253b
 
 1. 	Rename (backup) your Compiere folder (e.g. from Compiere2 to _Compiere2)
 2. 	Extract Compiere253b to your former Compiere folder (Compiere2)
 3. 	Copy the CompiereEnv.properties and Compiere.properties from your old
 	installation folder (the renamed _Compiere2) into the new Compiere2 folder
 4.	Run RUN_Setup.bat. It should give you the setup screen with all correct
 	settings (derived from your old *.properties). Select save.
 5. 	Recreate all sqlj functions (from console):
 	prompt>loadjava -user compiere/COMPIERE_DB_PASSWORD@COMPIERE_DB_NAME -verbose -force 
 	-resolve "PATH_TO_NEW_COMPIERE\lib\sqlj.jar"
 	prompt>@sqlplus compiere/COMPIERE_DB_PASSWORD@COMPIERE_DB_NAME @"EPATH_TO_NEW_COMPIERE\utils
 	\oracle\createSQLJ.sql"
 6. 	Start Adempiere and login as System Administrator (SuperUser, System -> role System Administrator)
 	Run System Admin -> Sequence Check (you may have to run it more than once)
 7.	If you use additional languages: login with the standard english language as System Administrator, 
 	open General Rules -> System Rules -> Language, select your language and push 
 	'Language Maintenance' button. Choose 'add missing translation' and run the process.
 8. 	Start Adempiere.
 9.	Try to find new Windows (search for Issue). You got it? Welcome to 253b!
 10.	Test the installation and PLEASE REPORT ERRORS to the Adempiere forums
 	at: http://sourceforge.net/forum/?group_id=176962