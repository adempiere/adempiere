Wiki page
http://adempiere.com/index.php/Sponsored_Development:_Libero_HR_%26_Payroll
Tracker to report bug
https://sourceforge.net/tracker/?group_id=176962&atid=934929

How to Install?
1.- unzip and apply the migration script for Libero HR & Payroll in a new ADempiere Instance based on 360TLS

http://sourceforge.net/projects/adempiere/files/Adempiere%20Packages/Libero%20Human%20Resource%20and%20Payroll/migration_script.zip/download

2.- Download the binary liberoHR.jar, liberozkHR.jar and patches.jar files

http://sourceforge.net/projects/adempiere/files/Adempiere%20Packages/Libero%20Human%20Resource%20and%20Payroll/liberozkHR.jar/download
http://sourceforge.net/projects/adempiere/files/Adempiere%20Packages/Libero%20Human%20Resource%20and%20Payroll/liberoHR.jar/download
http://sourceforge.net/projects/adempiere/files/Adempiere%20Packages/Libero%20Human%20Resource%20and%20Payroll/patches.jar/download

3.- Open Terminal command
4.- Change the ADempiere Home directory
    #cd $ADEMPIERE_HOME
5.- Copy the liberoHR.jar file to packages directory
    #cp liberoHR.jar $ADEMPIERE_HOME/packages/liberoHR/lib
6.- Copy the liberozkHR.jar file to packages directory
    #cp liberoHR.jar $ADEMPIERE_HOME/zkpackages/liberoHR/lib    
7.- Copy the patches.jar file to lib directory
    #cp patches.jar $ADEMPIERE_HOME/lib
8.- Run ADempiere setup
    #RUN_setup.sh or RUN_silentsetup.sh
9.- Login using SuperUser , and execute the System Admin -> General Rules -> Security -> Role Access Update,after you need Login again, now you would see the new option in menu
