This a template project showing how to organize and store Compiere customizations.

This is work in progress, so please be patient.

Any comments and requests for improvements are wellcome.

Any improvements and patches are very wellcome and highly appreciated.

Target integration between Compiere and Jasper Reports.

Steps that user must do:
01) Copy "build-ID-001.properties.sampe" into "build-ID-001.properties"
02) Set proper setting in "build-ID-001.properties" file.

03) Copy "build-ID-001.[bat|sh].sampe" into "build-ID-001.[bat|sh]"
04) Set proper setting in "build-ID-001.[bat|sh]" file.

05) Open shell console in current directory(_Project-ID-AdempiereJasper) and execute:
      build-ID-001.bat alterDB
      build-ID-001.bat model
      build-ID-001.bat view
      build-ID-001.bat imp_AD_ProcessJasper
      build-ID-001.bat imp_AD_Process_Access
      
Enjoy new functionality.
Records now should be imported into Compiere AD.
