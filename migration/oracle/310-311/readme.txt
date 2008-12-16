 Written by Karsten Thiemann, kthiemann@adempiere.org
 Feel free to change everything you want, but you are using it at your own risk!
 
 Preparations
 1.	Make a database backup! Don't complain if something goes wrong,
 	just reapply your backup and report the error to the Adempiere forums
 	at: http://sourceforge.net/forum/?group_id=176962
 	You can use RUN_DBExport.bat in the Compiere2/utils folder.
 
 2.	Read and follow the INSTRUCTIONS in the migration-3.1.0-3.1.1.sql file.
 	You have to set some DEFAULT VALUES for some new columns. But it's 
 	done easily.
 	
 Migration of Compiere database
 1.	This script only migrates the database from Adempiere3.1.0 (Compiere253b)
 	to Adempiere3.1.1 (253d)!
 
 2.	It's only tested with Oracle - but it may work with other databases
 	as well because it is just sql. If you try it with a different database
 	(successful or not) then please report it to the Adempiere forums
 	at: http://sourceforge.net/forum/?group_id=176962

 3. 	Apply the migration-3.1.0-3.1.1.sql via any SQL tool. 
 	
 	BUT MAKE SHURE THAT YOU HAVE FOLLOWED THE INSTRUCTIONS IN THERE FIRST!
 	
 	I use the Oracle SQL Developer (it's free but you have to register), you
 	can find it here:
 	http://www.oracle.com/technology/products/database/sql_developer/index.html
 	Of course you can also use Toad or even just SQLPlus:
 	(from the folder with the migration*.sql)
 	sqlplus compiere@COMPIERE_DB_NAME @migration-3.1.0-3.1.1.sql
 	but sqlplus is not commended because it has some line length limitations that
 	can causes errors with long insert statements.
 	

 
 Installation of Adempiere 3.1.1
 
 1. 	Rename (backup) your Adempiere folder (e.g. from Adempiere to _Adempiere)
 2. 	Follow the installation instructions :)
 3. 	Start Adempiere and login as System Administrator (SuperUser, System -> role System Administrator)
	Run System Admin -> Sequence Check (you may have to run it more than once)
 4.	If you use additional languages: login with the standard english language as System Administrator, 
	open General Rules -> System Rules -> Language, select your language and push 
	'Language Maintenance' button. Choose 'add missing translation' and run the process.
 5. 	Start Adempiere.
 6.	Try to find new Windows (search for Issue). You got it? Welcome to 253b!
 7.	Test the installation and PLEASE REPORT ERRORS to the Adempiere forums
 	at: http://sourceforge.net/forum/?group_id=176962