Wiki page
http://adempiere.com/index.php/Libero_Manufacturing_Official_Extension
Source code
http://github.com/adempiere/extension_libero_manufacturing
Tracker to report bug
https://adempiere.atlassian.net/browse/MFG

How to Install?
1.- Install ADempiere 370LTS 
1.- Download the binary liberoMFG.jar and liberozkMFG.jar files 
http://sourceforge.net/projects/adempiere/files/Adempiere%20Packages/Libero%20Manufacturing/liberoMFG.jar/download 
http://sourceforge.net/projects/adempiere/files/Adempiere%20Packages/Libero%20Manufacturing/liberozkMFG.jar/download
2.- Open Terminal command
3.- Change the ADempiere Home directory
    #cd $ADEMPIERE_HOME
4.- Copy the liberoMFG.jar file to packages directory
    #cp liberoMFG.jar $ADEMPIERE_HOME/packages/liberoMFG/lib/
5.- Copy the liberoMFG.jar file to packages directory
    #cp liberozkMFG.jar $ADEMPIERE_HOME/zkpackages/liberoMFG/lib/    
6.- Run ADempiere setup
    #RUN_setup.sh or RUN_silentsetup.sh
7.- Ready    

