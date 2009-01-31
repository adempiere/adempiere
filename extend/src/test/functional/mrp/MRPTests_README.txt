MRPTests Format/Rules:

* Tests are separated by a completelly empty line
* First line from each test is containing the product data planning parameters;
  The CSV column names are identical with those from PP_Product_Planning table.
* Next lines are the MRP records; The CSV column names are identical with those from PP_MRP table.
* The "Generated" column (first column from CSV file) is applicable just for MRP record lines and means:
		N = This record is not generated and it will be created before running the MRP engine.
		Y = This record IS generated. We expect this as a resulting MRP record after MRP engine runs.
* The "MRP_Notice" column specify that we expect that MRP notice to be throwed by MRP engine.
  For more informations regarding MRP notices please run following SQL query:
			SELECT Value, MsgText, MsgTip FROM AD_Message WHERE value LIKE 'MRP-%'
			ORDER BY Value
* The "Today" column specify which is the "Today" date for MRP engine. In a normal environment (i.e. running Adempiere client),
  MRP engine assumes that Today is system date
* The "LeadTime" column is PP_ProductPlanning.DeliveryTime_Promised


For any other questions, please ask in ADempiere forums ;)

Best regards,
Teo Sarca - www.arhipac.ro
