CAUTION, THIS SCRIPTS ARE GENERATED BUT NOT FULLY TESTED!!!

 Written by Carlos Ruiz
 Feel free to change everything you want, but you are using it at your own risk!

 01_FromCompiere252dTo253a.sql generated with TOAD
 02_migrationDictionary252dTo253a.sql generated with Karsten migration tool
 
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

 3. 	First apply the 01_FromCompiere252dTo253a.sql via any SQL tool. 
 	
 	MAKE SHURE THAT YOU FOLLOWED THE INSTRUCTIONS IN THERE FIRST!
 	
 	I use the Oracle SQL Developer (it's free but you have to register), you
 	can find it here:
 	http://www.oracle.com/technology/products/database/sql_developer/index.html
 	Of course you can also use Toad or even just SQLPlus:
 	(from the folder with the migration*.sql)
 	sqlplus compiere@COMPIERE_DB_NAME @01_FromCompiere252dTo253a.sql
 	
 	Dependent on the tool you use you will get some errors. They should
 	derive only from unrecognized comments (-> unknown command), thats
 	just because the sql tool treats the comments as sql commands - don't worry.
 
 4.	Apply 02_migrationDictionary252dTo253a.sql the same way.