How to Install?

Apply the costing engine script in a new ADempiere Instance based on 360 from: Costing Engine SQL Script
Download the binary liberoCE.jar and patchs.jar files
Open Terminal command
Change the ADempiere Home directory
    #cd $ADEMPIERE_HOME
Copy the liberoCE.jar file to packages directory
    #cp liberoCE.jar $ADEMPIERE_HOME/packages/liberoCE/lib
Copy the patchs.jar file to Lib directory
    #cp patches.jar $ADEMPIERE_HOME/lib/customization.jar
Run ADempiere setup
    #RUN_silentsetup.sh
Login using SuperUser , and execute the System Admin -> General Rules -> Security -> Role Access Update,after you need Login again, now you would see the new option in menu
