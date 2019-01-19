This folder contains all xml files required to migrate from the current seed database to the release 
version.
  
To migrate the current seed to the release state, build and install the ADempiere application, import 
the current seed and execute 

$ADEMPIERE_HOME/utils/RUN_Migrate.[sh|bat]   

To create a new seed, export the migrated database and add to the repository.  Then, all migration files 
applied and included in the seed should be moved to the specific migration folder using the naming 
convention:

<old seed>-<new seed>

This folder should be renamed as follows:

<current seed>-release

To migrate a production database, please use the Migration Tool.  
See http://wwww.adempiere.com/Migrate

When adding migration files, please follow the number sequence of the existing files to ensure
migrations are loaded and applied in order.  

