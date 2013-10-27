Wiki page
http://www.adempiere.com/index.php/Sponsored_Development:_Libero_Smart_Browse
Tracker to report bug
https://sourceforge.net/tracker/?group_id=176962&atid=1179552

How to Install?
1.- unzip and apply the migration script for Libero Smart Browser a new ADempiere Instance based on 360TLS

https://sourceforge.net/projects/adempiere/files/Adempiere%20Packages/Libero%20Smart%20Browser/scripts.zip/download

2.- Download the binary liberoSB.jar, liberozkSB.jar , patches.jar and zkpatches.jar files

https://sourceforge.net/projects/adempiere/files/Adempiere%20Packages/Libero%20Smart%20Browser/

3.- Open Terminal command
4.- Change the ADempiere Home directory
    #cd $ADEMPIERE_HOME
5.- Copy the liberoSB.jar file to packages directory
    #cp liberoSB.jar $ADEMPIERE_HOME/packages/liberoSB/lib
6.- Copy the liberozkSB.jar file to packages directory
    #cp liberozkSB.jar $ADEMPIERE_HOME/zkpackages/liberoSB/lib    
7.- Copy the patches.jar and  zkpatches.jar file to lib directory
    #cp patches.jar $ADEMPIERE_HOME/lib
    #cp zkpatches.jar $ADEMPIERE_HOME/lib
8.- Run ADempiere setup
    #RUN_setup.sh or RUN_silentsetup.sh
9.- Login using System , and execute the System Admin -> General Rules -> Security -> Role Access Update,after you need Login again, now you would see the new option in menu
