SET DEFINE OFF
SET SQLBLANKLINES ON
SET SCAN OFF
-- Jul 13, 2012 1:42:31 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Menu SET Description='With the goal of planning in mind, it is required a correct formation of periods groups, which needs to be measured, the operative calendars allows to set calendars with weekly and monthly periods.',Updated=TO_DATE('2012-07-13 13:42:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Menu_ID=53421
;

-- Jul 13, 2012 1:42:31 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=53421
;

-- Jul 13, 2012 1:42:41 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Window SET Description='The operation calendars are defined to set measurement cycles for planning, forecast calculus and reports.', Help='With the goal of planning in mind, it is required a correct formation of periods groups, which needs to be measured, the operative calendars allows to set calendars with weekly and monthly periods.',Updated=TO_DATE('2012-07-13 13:42:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Window_ID=53181
;

-- Jul 13, 2012 1:42:41 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Window_Trl SET IsTranslated='N' WHERE AD_Window_ID=53181
;

-- Jul 13, 2012 1:42:41 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Menu SET Description='The operation calendars are defined to set measurement cycles for planning, forecast calculus and reports.', IsActive='Y', Name='Operational Calendar',Updated=TO_DATE('2012-07-13 13:42:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Menu_ID=53421
;

-- Jul 13, 2012 1:42:41 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=53421
;

-- Jul 13, 2012 1:43:07 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Tab SET Help='With the goal of planning in mind, it is required a correct formation of periods groups, which needs to be measured, the operative calendars allows to set calendars with weekly and monthly periods.',Updated=TO_DATE('2012-07-13 13:43:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53517
;

-- Jul 13, 2012 1:43:07 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Tab_Trl SET IsTranslated='N' WHERE AD_Tab_ID=53517
;

-- Jul 13, 2012 1:43:36 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Tab SET Description='The period definition, allows to set a calendar year in order to multiple periods can be created.',Updated=TO_DATE('2012-07-13 13:43:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53518
;

-- Jul 13, 2012 1:43:36 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Tab_Trl SET IsTranslated='N' WHERE AD_Tab_ID=53518
;

-- Jul 13, 2012 1:44:13 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Field SET Help='This process creates the calendar periods, based on the period definition with an start date specified, if this date is not recorded, then Jan 01 will be the default. The period name is created based on the start date of each period using the Java SimpleDataFormat pattern.',Updated=TO_DATE('2012-07-13 13:44:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=64459
;

-- Jul 13, 2012 1:44:13 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=64459
;

-- Jul 13, 2012 1:44:24 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET Help='This process creates the calendar periods, based on the period definition with an start date specified, if this date is not recorded, then Jan 01 will be the default. The period name is created based on the start date of each period using the Java SimpleDataFormat pattern.',Updated=TO_DATE('2012-07-13 13:44:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63301
;

-- Jul 13, 2012 1:44:24 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Field SET Name='Process Now', Description=NULL, Help='This process creates the calendar periods, based on the period definition with an start date specified, if this date is not recorded, then Jan 01 will be the default. The period name is created based on the start date of each period using the Java SimpleDataFormat pattern.' WHERE AD_Column_ID=63301 AND IsCentrallyMaintained='Y'
;

-- Jul 13, 2012 1:44:33 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Process SET Help='This process creates the calendar periods, based on the period definition with an start date specified, if this date is not recorded, then Jan 01 will be the default. The period name is created based on the start date of each period using the Java SimpleDataFormat pattern.',Updated=TO_DATE('2012-07-13 13:44:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_ID=53300
;

-- Jul 13, 2012 1:44:33 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Process_Trl SET IsTranslated='N' WHERE AD_Process_ID=53300
;

-- Jul 13, 2012 1:44:59 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Tab SET Help='The periods are created based on period definition, each period has a name, period number, start date and end date, which set the date range for the specified period.',Updated=TO_DATE('2012-07-13 13:44:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53519
;

-- Jul 13, 2012 1:44:59 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Tab_Trl SET IsTranslated='N' WHERE AD_Tab_ID=53519
;

-- Jul 13, 2012 1:45:23 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Menu SET Description='This process allows to generate the sales historical based on the invoicing history.',Updated=TO_DATE('2012-07-13 13:45:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Menu_ID=53426
;

-- Jul 13, 2012 1:45:23 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=53426
;

-- Jul 13, 2012 1:46:35 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Process SET Description='This process allows to generate the sales historical based on the invoicing history.', Help='It is possible to use the business partner, business partner group, business partner location, product category, product classification, product class, product group, warehouse, sales region and project , to generate the records for the sales historical.
<br>
<br>
It is possible to use the option To import the sales history to load the sales statistics from the legacy systems.',Updated=TO_DATE('2012-07-13 13:46:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_ID=53302
;

-- Jul 13, 2012 1:46:35 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Process_Trl SET IsTranslated='N' WHERE AD_Process_ID=53302
;

-- Jul 13, 2012 1:48:26 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Menu SET Description='This process allows to import the sales statistics by using a predefined import format,  inside the application.',Updated=TO_DATE('2012-07-13 13:48:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Menu_ID=53420
;

-- Jul 13, 2012 1:48:26 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=53420
;

-- Jul 13, 2012 1:49:10 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Window SET Description='This process allows to import the sales statistics by using a predefined import format,  inside the application.', Help='The parameters are the default values to import the records.
<br>
<br>
The maintained information inside the sales statistic are: the business partner data, product, sales quantities, prices, costs and reference values.
<br>
<br>
The sales historical information is used to realize the forecast calculus and to get sales statistics reports.',Updated=TO_DATE('2012-07-13 13:49:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Window_ID=53180
;

-- Jul 13, 2012 1:49:10 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Window_Trl SET IsTranslated='N' WHERE AD_Window_ID=53180
;

-- Jul 13, 2012 1:49:34 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Field SET Description='This process allows to import the sales statistics by using a predefined import format,  inside the application.',Updated=TO_DATE('2012-07-13 13:49:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=64438
;

-- Jul 13, 2012 1:49:34 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=64438
;

-- Jul 13, 2012 1:50:03 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Tab SET Description='This process allows to import the sales statistics by using a predefined import format,  inside the application.', Help='The parameters are the default values to import the records.
<br>
<br>
The maintained information inside the sales statistic are: the business partner data, product, sales quantities, prices, costs and reference values.
<br>
<br>
The sales historical information is used to realize the forecast calculus and to get sales statistics reports.',Updated=TO_DATE('2012-07-13 13:50:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53516
;

-- Jul 13, 2012 1:50:03 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Tab_Trl SET IsTranslated='N' WHERE AD_Tab_ID=53516
;

-- Jul 13, 2012 1:50:39 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET Description='This process allows to import the sales statistics by using a predefined import format,  inside the application.', Help='The parameters are the default values to import the records.
<br>
<br>
The maintained information inside the sales statistic are: the business partner data, product, sales quantities, prices, costs and reference values.
<br>
<br>
The sales historical information is used to realize the forecast calculus and to get sales statistics reports.',Updated=TO_DATE('2012-07-13 13:50:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63262
;

-- Jul 13, 2012 1:50:39 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Field SET Name='Process Now', Description='This process allows to import the sales statistics by using a predefined import format,  inside the application.', Help='The parameters are the default values to import the records.
<br>
<br>
The maintained information inside the sales statistic are: the business partner data, product, sales quantities, prices, costs and reference values.
<br>
<br>
The sales historical information is used to realize the forecast calculus and to get sales statistics reports.' WHERE AD_Column_ID=63262 AND IsCentrallyMaintained='Y'
;

-- Jul 13, 2012 1:51:42 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Menu SET Description='The Forecasting Rules define the business logic to calculate the forecast according with a previously implemented algorithm.',Updated=TO_DATE('2012-07-13 13:51:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Menu_ID=53424
;

-- Jul 13, 2012 1:51:42 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=53424
;

-- Jul 13, 2012 1:52:38 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Window SET Description='The Forecasting Rules define the business logic to calculate the forecast according with a previously implemented algorithm', Help='These rules are used in the Forecast Definition to set the rules and forecast calculation ranges.
<br>
<br>
The rules can be identified by a name and a description, to identify the different forecast calculation algorithms.
<br>
<br>
The calculus java class: it’s the implementation of the java interface for each forecast rule.
<br>
<br>
Currently the system supports multiple forecast calculation implementations, which are executed by the forecast engine.',Updated=TO_DATE('2012-07-13 13:52:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Window_ID=53184
;

-- Jul 13, 2012 1:52:38 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Window_Trl SET IsTranslated='N' WHERE AD_Window_ID=53184
;

-- Jul 13, 2012 1:52:38 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Menu SET Description='The Forecasting Rules define the business logic to calculate the forecast according with a previously implemented algorithm', IsActive='Y', Name='Forecast Rule',Updated=TO_DATE('2012-07-13 13:52:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Menu_ID=53424
;

-- Jul 13, 2012 1:52:38 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=53424
;

-- Jul 13, 2012 1:54:13 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Field SET Help='The Calculation Class indicates the Java Class used for calculating measures.
<br>
<br>
Forecast Engine.
<br>
<br>
The forecasting engine has the function to expose the implementations for each forecast rule, the interface ForecastRule.java is the interface to implement each forecast rule.
<br>
<br>
The developers can use this interface to implement their own calculation algorithms.
<br>', IsCentrallyMaintained='N',Updated=TO_DATE('2012-07-13 13:54:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=64562
;

-- Jul 13, 2012 1:54:13 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=64562
;

-- Jul 13, 2012 1:54:39 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Menu SET Description='This window allows to define the valid combinations, used to select the historic sales records. The combinations order is determined by the sequence, where the lower sequence has priority over the higher sequence.',Updated=TO_DATE('2012-07-13 13:54:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Menu_ID=53425
;

-- Jul 13, 2012 1:54:39 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=53425
;

-- Jul 13, 2012 1:55:31 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Window SET Description='This window allows to define the valid combinations, used to select the historic sales records. The combinations order is determined by the sequence, where the lower sequence has priority over the higher sequence.', Help='The information to define combinations are defined by business partner data (business partner, business partner group, sales region and campaign), Product data (product, category, classification, class and group), factor data for calculus (Alpha Factor, Gamma, Multiplier, Scale).
<br>
<br>
The suitable use of the forecast definition, allows to generate calculus with different factors for each main group of data defined for a business partner or product.
<br>
<br>
In this way is possible to get a forecast for each product category, different from another.
<br>
<br>
To set the sequence of the combinations is possible to use the tab of sequences, with which is possible to define the order of each combination.',Updated=TO_DATE('2012-07-13 13:55:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Window_ID=53185
;

-- Jul 13, 2012 1:55:31 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Window_Trl SET IsTranslated='N' WHERE AD_Window_ID=53185
;

-- Jul 13, 2012 1:56:13 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Tab SET Description='This window allows to define the valid combinations, used to select the historic sales records. The combinations order is determined by the sequence, where the lower sequence has priority over the higher sequence.', Help='The information to define combinations are defined by business partner data (business partner, business partner group, sales region and campaign), Product data (product, category, classification, class and group), factor data for calculus (Alpha Factor, Gamma, Multiplier, Scale).',Updated=TO_DATE('2012-07-13 13:56:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53527
;

-- Jul 13, 2012 1:56:13 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Tab_Trl SET IsTranslated='N' WHERE AD_Tab_ID=53527
;

-- Jul 13, 2012 1:57:17 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Tab SET Help='The suitable use of the forecast definition, allows to generate calculus with different factors for each main group of data defined for a business partner or product.
<br>
<br>
In this way is possible to get a forecast for each product category, different from another.
<br>
<br>
To set the sequence of the combinations is possible to use the tab of sequences, with which is possible to define the order of each combination.',Updated=TO_DATE('2012-07-13 13:57:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53528
;

-- Jul 13, 2012 1:57:17 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Tab_Trl SET IsTranslated='N' WHERE AD_Tab_ID=53528
;

-- Jul 13, 2012 1:58:13 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Menu SET Name='Forecast Simulation',Updated=TO_DATE('2012-07-13 13:58:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Menu_ID=53423
;

-- Jul 13, 2012 1:58:13 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=53423
;

-- Jul 13, 2012 1:58:28 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Window SET Help='The forecast simulation window, allows to define the required parameters to process a forecast calculation, these parameters are used for the forecast engine to extract the data from the sales historical, to execute the calculation algorithm based on the forecast rule and to save the forecast results.',Updated=TO_DATE('2012-07-13 13:58:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Window_ID=53183
;

-- Jul 13, 2012 1:58:28 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Window_Trl SET IsTranslated='N' WHERE AD_Window_ID=53183
;

-- Jul 13, 2012 1:58:28 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Menu SET Description='Process to Calculate Forecast simulation', IsActive='Y', Name='Forecast Run',Updated=TO_DATE('2012-07-13 13:58:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Menu_ID=53423
;

-- Jul 13, 2012 1:58:28 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=53423
;

-- Jul 13, 2012 2:01:02 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Tab SET Help='<b>Forecast Definition:</b>Establishes the forecast definition for this simulation.
<br>
<br>
<b>Forecast Rule: </b>Establishes the forecast rule to calculate this simulation.
<br>
<br>
<b>Operational Calendar: </b>Establishes the calendar to use, for the base periods definition and the target period definition.
<br>
<br>
<b>Source Warehouse:</b> Determines the warehouse for which the sales statistics information will be filtered, in this way it is possible to calculate a forecast for an specific warehouse.
<br>
<br>
<b>Base Period Definition: </b>Defines the basic periods to filter the sales history information.
<br>
<br>
<b>Target Periods Definition: </b>Defines the target periods, once the simulation process is executed the calculated values are organized in the order of the target periods definition.
<br>
<br>
<b>Periods Historical: </b>Determines the number of history periods, which must be used for the forecast calculation, the periods number are equivalent to the defined inputs at the operational calendar.
<br>
<br>
<b>Target Warehouse: </b>Determines the destiny warehouse with which the results are generated. In some enterprises the sales historical is generated for each point of sales, by this field is possible to change the source warehouse to a target warehouse with the goal of consolidate the demand in a target warehouse.
<br>
<br>
<b>Calculate Forecast: </b>This process allows to execute, by the forecast engine, the calculus algorithm established by the forecast rule, the forecast engine uses the established factors in the forecast definition. The calculated values for each period are saved as result of the simulation.',Updated=TO_DATE('2012-07-13 14:01:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53521
;

-- Jul 13, 2012 2:01:02 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Tab_Trl SET IsTranslated='N' WHERE AD_Tab_ID=53521
;

-- Jul 13, 2012 2:03:54 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Tab SET Description='The records of this tab are generated as result of applying the combinations set in the forecast definition, each master record is a unique combination of product, warehouse, and the forecast factors used for this calculus.', Help='<b>Alpha Factor: </b>This factor is used for the forecast engine and determines the smoothing constant used for some forecast models of exponential smoothing. It hast to be a value in the range of 0.0-1.0
<br>
<br>
<b>Gamma Factor:</b> This factor is used for the forecast engine and determines the smoothing constant used in second place for some forecast models of exponential smoothing forecast, the Gamma Factor is used to smooth the tendency, it must be a value in the range of 0.0-1.0
<br>
<br>
<b>Multiplied Factor: </b>This factor is used by the forecast engine and determines the percentage in which the calculated quantity of the forecast is increased or decreased. A negative percentage indicates the quantity is reduced.
<br>
<br>
<b>Scalar Factor:</b> This factor is used for the forecast engine and determines the percentage to be multiplied or scale a calculated quantity of the forecast, this value must be absolute.
<br>
<br>', Name='Master of Forecast Simulation',Updated=TO_DATE('2012-07-13 14:03:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53522
;

-- Jul 13, 2012 2:03:54 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Tab_Trl SET IsTranslated='N' WHERE AD_Tab_ID=53522
;

-- Jul 13, 2012 2:04:45 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Tab SET Help='The records of this tab are generated as result of applying the established combinations in the forecast definitions and the number of established periods in the definition of basic periods for each master of forecast simulation, a detail record is created for each period accumulating the invoiced quantities between the range of the start and the period end date.', Name='Forecast Simulation Detail:',Updated=TO_DATE('2012-07-13 14:04:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53523
;

-- Jul 13, 2012 2:04:45 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Tab_Trl SET IsTranslated='N' WHERE AD_Tab_ID=53523
;

-- Jul 13, 2012 2:05:01 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Tab SET Help='Shows the source of the sales historical for each detail.',Updated=TO_DATE('2012-07-13 14:05:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53524
;

-- Jul 13, 2012 2:05:01 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Tab_Trl SET IsTranslated='N' WHERE AD_Tab_ID=53524
;

-- Jul 13, 2012 2:05:17 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Tab SET Description='Shows the source of the sales historical for each detail.', Help=NULL,Updated=TO_DATE('2012-07-13 14:05:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53524
;

-- Jul 13, 2012 2:05:17 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Tab_Trl SET IsTranslated='N' WHERE AD_Tab_ID=53524
;

-- Jul 13, 2012 2:06:19 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Tab SET Description='The records on this tab are generated by the execution of the Forecast Engine, using the implemented algorithm in the Forecast Rule, a record is created for each established period in the target periods definition.', Help='The Forecast Engine uses the Forecast Simulation Detail, the Forecast Rule and the factors, to calculate a resultant forecast for each target period, this allows to use the sales historical of the previous year and to calculate the current year sales forecast', Name='Results of the Forecast Simulation',Updated=TO_DATE('2012-07-13 14:06:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53525
;

-- Jul 13, 2012 2:06:19 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Tab_Trl SET IsTranslated='N' WHERE AD_Tab_ID=53525
;

-- Jul 13, 2012 2:07:33 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Menu SET Name='Forecast Simulation Report',Updated=TO_DATE('2012-07-13 14:07:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Menu_ID=53428
;

-- Jul 13, 2012 2:07:33 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=53428
;

-- Jul 13, 2012 2:07:58 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Process SET Description='This process generates a report containing the result of the forecast calculation, parameters can be used to filter the result of the report. ', Help='The main goal of this report is to evaluate and analyze the result of multiple simulations to determine the most adequate, to be able to generate the forecast.', Name='Forecast Simulation Report',Updated=TO_DATE('2012-07-13 14:07:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_ID=53303
;

-- Jul 13, 2012 2:07:58 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Process_Trl SET IsTranslated='N' WHERE AD_Process_ID=53303
;

-- Jul 13, 2012 2:07:58 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Menu SET Description='This process generates a report containing the result of the forecast calculation, parameters can be used to filter the result of the report. ', IsActive='Y', Name='Forecast Simulation Report',Updated=TO_DATE('2012-07-13 14:07:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Menu_ID=53428
;

-- Jul 13, 2012 2:07:58 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=53428
;

-- Jul 13, 2012 2:11:33 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Process SET Description='This process allows to generate a forecast based on the forecast simulation calculation.', Help='The process uses the resulting simulation values ​ to generate a new forecast.
<br>
<br>
<b>Action Type of the forecast:</b>  It Indicates how the forecast will be generated
<br>
<br>
If the action type is <b>"Replace"</b> all lines of this forecast are going to be  eliminated and will be generated again, based on the simulation products and the selection criteria.
<br>
<br>
If the action type  is <b>"Merge"</b> all lines of this forecast will be combined, based on the unique combination of product, warehouse and period. Therefore, if the combination exists, the forecast quantities are accumulated.
<br>
<br>
<b>The Load Type of forecast: </b>Indicates which date of the period will be used to determine the forecast line promised date.
<br>
<br>
Options:
<b>To Use the Period Start Date: </b>The due date is set  based on the period start date 
<b>To Use the Period End Date:  </b>The due date is set based on the period end date.
<br>
<br>
<b>Days after the due date: </b> Indicates the number of days to be added or subtracted to the due date. If the value is negative, the days are subtracted.
<br>
<br>
<b>Selection Criteria:</b>
<br>
It is possible to use the category, classification, class and group of the product to get the products to be included in the new forecast.',Updated=TO_DATE('2012-07-13 14:11:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_ID=53148
;

-- Jul 13, 2012 2:11:33 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Process_Trl SET IsTranslated='N' WHERE AD_Process_ID=53148
;

-- Jul 13, 2012 2:11:33 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Menu SET Description='This process allows to generate a forecast based on the forecast simulation calculation.', IsActive='Y', Name='Calculate Forecast',Updated=TO_DATE('2012-07-13 14:11:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Menu_ID=53182
;

-- Jul 13, 2012 2:11:33 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=53182
;

-- Jul 13, 2012 2:12:10 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Process SET Name='Generate Forecast', Value='M_Forecast Generate Forecast',Updated=TO_DATE('2012-07-13 14:12:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_ID=53148
;

-- Jul 13, 2012 2:12:10 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Process_Trl SET IsTranslated='N' WHERE AD_Process_ID=53148
;

-- Jul 13, 2012 2:12:10 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Menu SET Description='This process allows to generate a forecast based on the forecast simulation calculation.', IsActive='Y', Name='Generate Forecast',Updated=TO_DATE('2012-07-13 14:12:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Menu_ID=53182
;

-- Jul 13, 2012 2:12:10 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=53182
;

-- Jul 13, 2012 2:13:43 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Process SET Help='The process uses the resulting simulation values ​ to generate a new forecast.
<br>
<br>
<b>Action Type of the forecast:</b>  It Indicates how the forecast will be generated
<br>
<br>
If the action type is <b>"Replace"</b> all lines of this forecast are going to be  eliminated and will be generated again, based on the simulation products and the selection criteria.
<br>
<br>
If the action type  is <b>"Merge"</b> all lines of this forecast will be combined, based on the unique combination of product, warehouse and period. Therefore, if the combination exists, the forecast quantities are accumulated.
<br>
<br>
<b>The Load Type of forecast: </b>Indicates which date of the period will be used to determine the forecast line promised date.
<br>
<br>
<b>Options:</b>
<br>
<br>
<b>To Use the Period Start Date: </b>The due date is set  based on the period start date 
<b>To Use the Period End Date:  </b>The due date is set based on the period end date.
<br>
<br>
<b>Days after the due date: </b> Indicates the number of days to be added or subtracted to the due date. If the value is negative, the days are subtracted.
<br>
<br>
<b>Selection Criteria:</b>
<br>
It is possible to use the category, classification, class and group of the product to get the products to be included in the new forecast.',Updated=TO_DATE('2012-07-13 14:13:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_ID=53148
;

-- Jul 13, 2012 2:13:43 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Process_Trl SET IsTranslated='N' WHERE AD_Process_ID=53148
;

-- Jul 13, 2012 6:17:31 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Menu SET Description='The forecast simulation browser allows to compare base period data with the simulation result of the target period, after executing a forecast simulation. The goal of this query is to validate that the results are considered in the company plans.', Name='Forecast Simulation Result',Updated=TO_DATE('2012-07-13 18:17:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Menu_ID=53427
;

-- Jul 13, 2012 6:17:31 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=53427
;

-- Jul 13, 2012 6:18:21 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Window SET Description='The Forecast window allows to maintain the sales forecast information for an organization.', Help=NULL,Updated=TO_DATE('2012-07-13 18:18:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Window_ID=328
;

-- Jul 13, 2012 6:18:21 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Window_Trl SET IsTranslated='N' WHERE AD_Window_ID=328
;

-- Jul 13, 2012 6:18:21 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Menu SET Description='The Forecast window allows to maintain the sales forecast information for an organization.', IsActive='Y', Name='Forecast',Updated=TO_DATE('2012-07-13 18:18:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Menu_ID=478
;

-- Jul 13, 2012 6:18:21 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=478
;

-- Jul 13, 2012 6:20:32 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Tab SET Help='Inside the forecast window the field  <b>Price List</b> has to be defined to determine the sales goal amounts and to obtain an estimated value for the sales plan by sales representative.
<br>
<br>
The Forecast report show the Sales Plan , the goal amounts which has to be accomplished, the information to be grouped by sales representative, product, warehouse and period.
<br>
<br>
The field <b>Operational Calendar</b> and <b>Periods Definition</b>, must be defined to determine the delivery promised date for the forecast products.
<br>
<br>
The forecast lines can be captured manually entering the sales representative, product, warehouse, quantity, period or it can be generated from a simulation using the Generate forecast process.
<br>
<br>
The products and its quantities are considered by MRP when  the forecast is already  processed, ADempiere allows to have several forecast simultaneously.
<br>
<br>
If you don’t want that MRP considers a Forecast processed,  it should be deactivated.',Updated=TO_DATE('2012-07-13 18:20:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=653
;

-- Jul 13, 2012 6:20:32 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Tab_Trl SET IsTranslated='N' WHERE AD_Tab_ID=653
;

-- Jul 13, 2012 6:21:06 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Tab SET Help='The forecast lines can be captured manually entering the sales representative, product, warehouse, quantity, period or it can be generated from a simulation using the Generate forecast process.',Updated=TO_DATE('2012-07-13 18:21:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=654
;

-- Jul 13, 2012 6:21:06 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Tab_Trl SET IsTranslated='N' WHERE AD_Tab_ID=654
;

-- Jul 13, 2012 6:22:10 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Process SET Description='This process generates a sales forecast detailed report, classified by sales representative, product warehouse, period and promised date, these parameters can be used to create filters at the report result.', Help='The main goal of this report is to analyze the sales plan, considering quantities and amounts.', Value='RV_M_Forecast Report',Updated=TO_DATE('2012-07-13 18:22:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_ID=53144
;

-- Jul 13, 2012 6:22:10 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Process_Trl SET IsTranslated='N' WHERE AD_Process_ID=53144
;

-- Jul 13, 2012 6:22:10 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Menu SET Description='This process generates a sales forecast detailed report, classified by sales representative, product warehouse, period and promised date, these parameters can be used to create filters at the report result.', IsActive='Y', Name='Forecast Report',Updated=TO_DATE('2012-07-13 18:22:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Menu_ID=53183
;

-- Jul 13, 2012 6:22:10 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=53183
;

-- Jul 13, 2012 6:23:00 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Process SET Description='This process generates a report summarized by forecast period , some parameters can be used to filtrate the report results.', Help='The main goal of this report is to analyze the sales plan, considering quantities and amounts by an specific period.',Updated=TO_DATE('2012-07-13 18:23:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_ID=53146
;

-- Jul 13, 2012 6:23:00 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Process_Trl SET IsTranslated='N' WHERE AD_Process_ID=53146
;

-- Jul 13, 2012 6:23:00 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Menu SET Description='This process generates a report summarized by forecast period , some parameters can be used to filtrate the report results.', IsActive='Y', Name='Forecast Report by Period',Updated=TO_DATE('2012-07-13 18:23:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Menu_ID=53181
;

-- Jul 13, 2012 6:23:00 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=53181
;

-- Jul 13, 2012 6:28:53 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Browse SET Description='The forecast simulation browser allows to compare base period data with the simulation result of the target period, after executing a forecast simulation.', Help='The goal of this query is to validate that the results are considered in the company plans.', Name='Forecast Simulation Result', Value='Forecast Simulation Result',Updated=TO_DATE('2012-07-13 18:28:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_ID=50004
;

-- Jul 13, 2012 6:28:53 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Browse_Trl SET IsTranslated='N' WHERE AD_Browse_ID=50004
;

-- Jul 13, 2012 6:29:31 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,63642,208,0,19,720,'C_Project_ID',TO_DATE('2012-07-13 18:29:30','YYYY-MM-DD HH24:MI:SS'),100,'Financial Project','EE01',22,'A Project allows you to track and control internal or external activities.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Project',0,TO_DATE('2012-07-13 18:29:30','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jul 13, 2012 6:29:31 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63642 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jul 13, 2012 6:29:34 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
ALTER TABLE M_Forecast ADD C_Project_ID NUMBER(10) DEFAULT NULL 
;

-- Jul 13, 2012 6:29:56 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,63642,64715,0,653,TO_DATE('2012-07-13 18:29:55','YYYY-MM-DD HH24:MI:SS'),100,'Financial Project',22,'EE01','A Project allows you to track and control internal or external activities.','Y','Y','Y','N','N','N','N','N','Project',TO_DATE('2012-07-13 18:29:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 6:29:56 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64715 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jul 13, 2012 6:31:34 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,63643,2073,0,19,720,'C_ProjectPhase_ID',TO_DATE('2012-07-13 18:31:32','YYYY-MM-DD HH24:MI:SS'),100,'Phase of a Project','EE01',22,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Project Phase',0,TO_DATE('2012-07-13 18:31:32','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jul 13, 2012 6:31:34 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63643 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jul 13, 2012 6:31:37 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
ALTER TABLE M_Forecast ADD C_ProjectPhase_ID NUMBER(10) DEFAULT NULL 
;

-- Jul 13, 2012 6:31:45 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,63643,64716,0,653,TO_DATE('2012-07-13 18:31:44','YYYY-MM-DD HH24:MI:SS'),100,'Phase of a Project',22,'EE01','Y','Y','Y','N','N','N','N','N','Project Phase',TO_DATE('2012-07-13 18:31:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 6:31:45 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64716 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jul 13, 2012 6:32:06 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=64715
;

-- Jul 13, 2012 6:32:06 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=64716
;

-- Jul 13, 2012 6:32:06 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=10300
;

-- Jul 13, 2012 6:32:06 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=10298
;

-- Jul 13, 2012 6:32:06 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=64444
;

-- Jul 13, 2012 6:32:11 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2012-07-13 18:32:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=64716
;

-- Jul 13, 2012 8:29:03 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,63644,550,0,18,143,720,'C_Campaign_ID',TO_DATE('2012-07-13 20:29:02','YYYY-MM-DD HH24:MI:SS'),100,'Marketing Campaign','EE01',22,'The Campaign defines a unique marketing program.  Projects can be associated with a pre defined Marketing Campaign.  You can then report based on a specific Campaign.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Campaign',0,TO_DATE('2012-07-13 20:29:02','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jul 13, 2012 8:29:03 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63644 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jul 13, 2012 8:29:07 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
ALTER TABLE M_Forecast ADD C_Campaign_ID NUMBER(10) DEFAULT NULL 
;

-- Jul 13, 2012 8:29:33 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,63644,64717,0,653,TO_DATE('2012-07-13 20:29:32','YYYY-MM-DD HH24:MI:SS'),100,'Marketing Campaign',22,'EE01','The Campaign defines a unique marketing program.  Projects can be associated with a pre defined Marketing Campaign.  You can then report based on a specific Campaign.','Y','Y','Y','N','N','N','N','N','Campaign',TO_DATE('2012-07-13 20:29:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 8:29:33 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64717 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jul 13, 2012 8:29:49 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=64717
;

-- Jul 13, 2012 8:29:49 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=64442
;

-- Jul 13, 2012 8:29:49 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=64443
;

-- Jul 13, 2012 8:29:49 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=64715
;

-- Jul 13, 2012 8:29:49 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=64716
;

-- Jul 13, 2012 8:29:49 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=10300
;

-- Jul 13, 2012 8:29:49 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=10298
;

-- Jul 13, 2012 8:29:49 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=64444
;

-- Jul 13, 2012 8:30:08 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2012-07-13 20:30:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=10297
;

-- Jul 13, 2012 8:30:13 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Field SET IsSameLine='N',Updated=TO_DATE('2012-07-13 20:30:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56278
;

-- Jul 13, 2012 8:30:17 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2012-07-13 20:30:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=64717
;

-- Jul 13, 2012 8:35:15 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Process_Para SET SeqNo=90,Updated=TO_DATE('2012-07-13 20:35:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53187
;

-- Jul 13, 2012 8:35:25 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Process_Para SET SeqNo=80,Updated=TO_DATE('2012-07-13 20:35:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53186
;

-- Jul 13, 2012 8:35:43 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Process_Para SET SeqNo=60,Updated=TO_DATE('2012-07-13 20:35:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53189
;

-- Jul 13, 2012 8:37:45 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,208,0,53144,53713,19,'C_Project_ID',TO_DATE('2012-07-13 20:37:44','YYYY-MM-DD HH24:MI:SS'),100,'Financial Project','EE01',0,'A Project allows you to track and control internal or external activities.','Y','Y','N','N','Project',100,TO_DATE('2012-07-13 20:37:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 8:37:45 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53713 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Jul 13, 2012 8:39:10 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2073,0,53144,53714,19,'C_ProjectPhase_ID',TO_DATE('2012-07-13 20:39:08','YYYY-MM-DD HH24:MI:SS'),100,'Project Phase','EE02',22,'Phase of a Project','Y','Y','N','N','Project Phase',110,TO_DATE('2012-07-13 20:39:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 8:39:10 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53714 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Jul 13, 2012 8:41:11 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,550,0,53144,53715,19,'C_Campaign_ID',TO_DATE('2012-07-13 20:41:10','YYYY-MM-DD HH24:MI:SS'),100,'Marketing Campaign','EE01',22,'The Campaign defines a unique marketing program.  Projects can be associated with a pre defined Marketing Campaign.  You can then report based on a specific Campaign.','Y','Y','N','N','Campaign',120,TO_DATE('2012-07-13 20:41:10','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 8:41:11 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53715 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Jul 13, 2012 8:41:31 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Process_Para SET SeqNo=70,Updated=TO_DATE('2012-07-13 20:41:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53713
;

-- Jul 13, 2012 8:42:07 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Process_Para SET SeqNo=100,Updated=TO_DATE('2012-07-13 20:42:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53186
;

-- Jul 13, 2012 8:42:32 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Process_Para SET SeqNo=130,Updated=TO_DATE('2012-07-13 20:42:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53187
;

-- Jul 13, 2012 8:42:41 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Process_Para SET SeqNo=80,Updated=TO_DATE('2012-07-13 20:42:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53714
;

-- Jul 13, 2012 8:42:55 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Process_Para SET SeqNo=90,Updated=TO_DATE('2012-07-13 20:42:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53715
;

-- Jul 13, 2012 8:43:51 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Process_Para SET SeqNo=120,Updated=TO_DATE('2012-07-13 20:43:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53187
;

-- Jul 13, 2012 8:45:51 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,208,0,53146,53716,19,'C_Project_ID',TO_DATE('2012-07-13 20:45:49','YYYY-MM-DD HH24:MI:SS'),100,'Financial Project','EE01',22,'A Project allows you to track and control internal or external activities.','Y','Y','N','N','Project',70,TO_DATE('2012-07-13 20:45:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 8:45:51 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53716 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Jul 13, 2012 8:46:46 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2073,0,53146,53717,19,'C_ProjectPhase_ID',TO_DATE('2012-07-13 20:46:45','YYYY-MM-DD HH24:MI:SS'),100,'Phase of a Project','EE01',22,NULL,'Y','Y','N','N','Project Phase',80,TO_DATE('2012-07-13 20:46:45','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 8:46:46 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53717 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Jul 13, 2012 8:47:36 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,550,0,53146,53718,19,'C_Campaign_ID',TO_DATE('2012-07-13 20:47:35','YYYY-MM-DD HH24:MI:SS'),100,'Marketing Campaign','EE01',22,'Y','Y','N','N','Campaign',90,TO_DATE('2012-07-13 20:47:35','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 8:47:36 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53718 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Jul 13, 2012 8:49:05 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Process_Para SET IsMandatory='N',Updated=TO_DATE('2012-07-13 20:49:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53193
;

-- Jul 13, 2012 8:49:17 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Process_Para SET IsMandatory='N',Updated=TO_DATE('2012-07-13 20:49:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53200
;

-- Jul 13, 2012 8:50:40 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,63645,208,0,19,53143,'C_Project_ID',TO_DATE('2012-07-13 20:50:39','YYYY-MM-DD HH24:MI:SS'),100,'Financial Project','EE01',22,'A Project allows you to track and control internal or external activities.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Project',0,TO_DATE('2012-07-13 20:50:39','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jul 13, 2012 8:50:40 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63645 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jul 13, 2012 8:52:56 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,63646,2073,0,19,53143,'C_ProjectPhase_ID',TO_DATE('2012-07-13 20:52:55','YYYY-MM-DD HH24:MI:SS'),100,'Phase of a Project','EE01',22,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Project Phase',0,TO_DATE('2012-07-13 20:52:55','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jul 13, 2012 8:52:56 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63646 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jul 13, 2012 8:53:22 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,63647,550,0,18,143,53143,'C_Campaign_ID',TO_DATE('2012-07-13 20:53:21','YYYY-MM-DD HH24:MI:SS'),100,'Marketing Campaign','EE01',22,'The Campaign defines a unique marketing program.  Projects can be associated with a pre defined Marketing Campaign.  You can then report based on a specific Campaign.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Campaign',0,TO_DATE('2012-07-13 20:53:21','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jul 13, 2012 8:53:22 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63647 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jul 13, 2012 8:53:54 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,63648,208,0,19,53142,'C_Project_ID',TO_DATE('2012-07-13 20:53:53','YYYY-MM-DD HH24:MI:SS'),100,'Financial Project','EE01',22,'A Project allows you to track and control internal or external activities.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Project',0,TO_DATE('2012-07-13 20:53:53','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jul 13, 2012 8:53:54 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63648 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jul 13, 2012 8:54:06 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,63649,2073,0,19,53142,'C_ProjectPhase_ID',TO_DATE('2012-07-13 20:54:06','YYYY-MM-DD HH24:MI:SS'),100,'Phase of a Project','EE01',22,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Project Phase',0,TO_DATE('2012-07-13 20:54:06','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jul 13, 2012 8:54:06 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63649 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jul 13, 2012 8:54:19 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,63650,550,0,18,143,53142,'C_Campaign_ID',TO_DATE('2012-07-13 20:54:19','YYYY-MM-DD HH24:MI:SS'),100,'Marketing Campaign','EE01',22,'The Campaign defines a unique marketing program.  Projects can be associated with a pre defined Marketing Campaign.  You can then report based on a specific Campaign.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Campaign',0,TO_DATE('2012-07-13 20:54:19','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jul 13, 2012 8:54:19 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63650 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jul 13, 2012 8:57:29 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=190,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50800
;

-- Jul 13, 2012 8:57:29 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=200,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50806
;

-- Jul 13, 2012 8:57:37 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
DELETE  FROM  AD_PrintFormatItem_Trl WHERE AD_PrintFormatItem_ID=50800
;

-- Jul 13, 2012 8:57:37 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
DELETE FROM AD_PrintFormatItem WHERE AD_PrintFormatItem_ID=50800
;

-- Jul 13, 2012 8:57:40 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
DELETE  FROM  AD_PrintFormatItem_Trl WHERE AD_PrintFormatItem_ID=50806
;

-- Jul 13, 2012 8:57:40 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
DELETE FROM AD_PrintFormatItem WHERE AD_PrintFormatItem_ID=50806
;

-- Jul 13, 2012 8:57:55 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=110,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50813
;

-- Jul 13, 2012 8:57:55 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=120,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50818
;

-- Jul 13, 2012 8:58:00 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
DELETE  FROM  AD_PrintFormatItem_Trl WHERE AD_PrintFormatItem_ID=50813
;

-- Jul 13, 2012 8:58:00 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
DELETE FROM AD_PrintFormatItem WHERE AD_PrintFormatItem_ID=50813
;

-- Jul 13, 2012 8:58:04 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
DELETE  FROM  AD_PrintFormatItem_Trl WHERE AD_PrintFormatItem_ID=50818
;

-- Jul 13, 2012 8:58:04 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
DELETE FROM AD_PrintFormatItem WHERE AD_PrintFormatItem_ID=50818
;

-- Jul 13, 2012 8:59:05 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintColor_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsSuppressRepeats,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,63648,0,100,53129,50032,0,0,TO_DATE('2012-07-13 20:59:04','YYYY-MM-DD HH24:MI:SS'),100,'D','N','Y','N','N','N','N','N','N','N','Y','N','N','N','Y','N','N','N','Y','Y','N','N','N','N','N','N','X',1,0,0,'Project','C','F',20,190,'N',0,TO_DATE('2012-07-13 20:59:04','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Jul 13, 2012 8:59:05 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=53129 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Jul 13, 2012 8:59:37 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintColor_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsSuppressRepeats,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,63649,0,100,53130,50032,0,0,TO_DATE('2012-07-13 20:59:36','YYYY-MM-DD HH24:MI:SS'),100,'D','N','Y','N','N','N','N','N','N','N','Y','N','N','N','Y','N','N','N','Y','Y','N','N','N','N','N','N','X',1,0,0,'Project Phase','C','F','Project Phase',20,200,'N',0,TO_DATE('2012-07-13 20:59:36','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Jul 13, 2012 8:59:37 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=53130 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Jul 13, 2012 8:59:37 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem_Trl trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=63649) WHERE AD_PrintFormatItem_ID = 53130 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=63649 AND trl.AD_PrintFormatItem_ID = 53130) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Jul 13, 2012 9:00:04 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintColor_ID,AD_PrintFont_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsSuppressRepeats,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,63650,0,100,130,53131,50032,0,0,TO_DATE('2012-07-13 21:00:03','YYYY-MM-DD HH24:MI:SS'),100,'D','N','Y','N','N','N','N','N','N','N','Y','N','N','N','Y','N','N','N','Y','Y','N','N','N','N','N','N','X',1,0,0,'Campaign','C','F','Campaign',20,210,'N',0,TO_DATE('2012-07-13 21:00:03','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Jul 13, 2012 9:00:04 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=53131 AND NOT EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID)
;

-- Jul 13, 2012 9:00:04 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem_Trl trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=63650) WHERE AD_PrintFormatItem_ID = 53131 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=63650 AND trl.AD_PrintFormatItem_ID = 53131) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Jul 13, 2012 9:00:30 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=220,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50792
;

-- Jul 13, 2012 9:00:30 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=230,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50789
;

-- Jul 13, 2012 9:00:37 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
DELETE  FROM  AD_PrintFormatItem_Trl WHERE AD_PrintFormatItem_ID=50792
;

-- Jul 13, 2012 9:00:37 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
DELETE FROM AD_PrintFormatItem WHERE AD_PrintFormatItem_ID=50792
;

-- Jul 13, 2012 9:00:51 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=10,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50789
;

-- Jul 13, 2012 9:00:51 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=20,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50790
;

-- Jul 13, 2012 9:00:51 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=30,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50791
;

-- Jul 13, 2012 9:00:51 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=40,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=53126
;

-- Jul 13, 2012 9:00:51 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=50,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50793
;

-- Jul 13, 2012 9:00:51 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=60,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50794
;

-- Jul 13, 2012 9:00:51 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=70,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50795
;

-- Jul 13, 2012 9:00:51 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=80,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50796
;

-- Jul 13, 2012 9:00:51 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=90,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50797
;

-- Jul 13, 2012 9:00:51 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=100,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50798
;

-- Jul 13, 2012 9:00:51 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=110,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50799
;

-- Jul 13, 2012 9:00:51 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=120,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=53127
;

-- Jul 13, 2012 9:00:51 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=130,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50801
;

-- Jul 13, 2012 9:00:51 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=140,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50802
;

-- Jul 13, 2012 9:00:51 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=150,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50803
;

-- Jul 13, 2012 9:00:51 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=160,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50804
;

-- Jul 13, 2012 9:00:51 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=170,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50805
;

-- Jul 13, 2012 9:00:51 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=180,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=53128
;

-- Jul 13, 2012 9:00:51 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=190,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50807
;

-- Jul 13, 2012 9:00:51 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=200,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=53129
;

-- Jul 13, 2012 9:00:51 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=210,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=53130
;

-- Jul 13, 2012 9:00:51 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=220,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=53131
;

-- Jul 13, 2012 9:01:17 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=10,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50809
;

-- Jul 13, 2012 9:01:17 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=20,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50810
;

-- Jul 13, 2012 9:01:17 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=30,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=53123
;

-- Jul 13, 2012 9:01:17 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=40,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50812
;

-- Jul 13, 2012 9:01:17 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=50,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=53124
;

-- Jul 13, 2012 9:01:17 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=60,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50814
;

-- Jul 13, 2012 9:01:17 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=70,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50816
;

-- Jul 13, 2012 9:01:17 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=80,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50817
;

-- Jul 13, 2012 9:01:17 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=90,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=53125
;

-- Jul 13, 2012 9:01:17 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=100,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50819
;

-- Jul 13, 2012 9:01:17 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=110,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50820
;

-- Jul 13, 2012 9:01:17 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=120,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50811
;

-- Jul 13, 2012 9:01:31 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
DELETE  FROM  AD_PrintFormatItem_Trl WHERE AD_PrintFormatItem_ID=50811
;

-- Jul 13, 2012 9:01:31 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
DELETE FROM AD_PrintFormatItem WHERE AD_PrintFormatItem_ID=50811
;

-- Jul 13, 2012 9:02:11 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=20,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50812
;

-- Jul 13, 2012 9:02:11 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=30,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50819
;

-- Jul 13, 2012 9:02:11 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=40,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=53123
;

-- Jul 13, 2012 9:02:11 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=50,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=53125
;

-- Jul 13, 2012 9:02:11 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=60,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=53124
;

-- Jul 13, 2012 9:02:11 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=70,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50814
;

-- Jul 13, 2012 9:02:11 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=90,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50810
;

-- Jul 13, 2012 9:02:11 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=100,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50816
;

-- Jul 13, 2012 9:03:54 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=20,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50795
;

-- Jul 13, 2012 9:03:54 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=30,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50790
;

-- Jul 13, 2012 9:03:54 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=50,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=53128
;

-- Jul 13, 2012 9:03:54 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=60,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=53127
;

-- Jul 13, 2012 9:03:54 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=70,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50802
;

-- Jul 13, 2012 9:03:54 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=80,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50793
;

-- Jul 13, 2012 9:03:54 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=90,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50796
;

-- Jul 13, 2012 9:03:54 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=100,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50797
;

-- Jul 13, 2012 9:03:54 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=110,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50801
;

-- Jul 13, 2012 9:03:54 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=120,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50791
;

-- Jul 13, 2012 9:03:54 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=130,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50803
;

-- Jul 13, 2012 9:03:54 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=140,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50794
;

-- Jul 13, 2012 9:03:54 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=150,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50798
;

-- Jul 13, 2012 9:03:54 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=160,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50799
;

-- Jul 13, 2012 9:03:54 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=170,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50804
;

-- Jul 13, 2012 9:03:54 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=180,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50805
;

-- Jul 13, 2012 9:03:54 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=200,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=53131
;

-- Jul 13, 2012 9:03:54 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=210,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=53129
;

-- Jul 13, 2012 9:03:54 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SeqNo=220,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=53130
;

-- Jul 13, 2012 9:04:51 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SortNo=0,IsOrderBy='N' WHERE AD_PrintFormatItem_ID=50790
;

-- Jul 13, 2012 9:04:51 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SortNo=10,IsOrderBy='Y' WHERE AD_PrintFormatItem_ID=50789
;

-- Jul 13, 2012 9:04:51 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SortNo=20,IsOrderBy='Y' WHERE AD_PrintFormatItem_ID=50802
;

-- Jul 13, 2012 9:04:51 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SortNo=30,IsOrderBy='Y' WHERE AD_PrintFormatItem_ID=50793
;

-- Jul 13, 2012 9:04:51 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SortNo=40,IsOrderBy='Y' WHERE AD_PrintFormatItem_ID=50796
;

-- Jul 13, 2012 9:04:51 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SortNo=50,IsOrderBy='Y' WHERE AD_PrintFormatItem_ID=50797
;

-- Jul 13, 2012 9:04:51 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SortNo=60,IsOrderBy='Y' WHERE AD_PrintFormatItem_ID=50801
;

-- Jul 13, 2012 9:05:26 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SortNo=10,IsOrderBy='Y' WHERE AD_PrintFormatItem_ID=50809
;

-- Jul 13, 2012 9:05:26 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SortNo=20,IsOrderBy='Y' WHERE AD_PrintFormatItem_ID=53125
;

-- Jul 13, 2012 9:05:26 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SortNo=30,IsOrderBy='Y' WHERE AD_PrintFormatItem_ID=53124
;

-- Jul 13, 2012 9:05:26 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SortNo=40,IsOrderBy='Y' WHERE AD_PrintFormatItem_ID=50814
;

-- Jul 13, 2012 9:05:46 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SortNo=20,IsOrderBy='Y' WHERE AD_PrintFormatItem_ID=53128
;

-- Jul 13, 2012 9:05:46 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SortNo=30,IsOrderBy='Y' WHERE AD_PrintFormatItem_ID=53127
;

-- Jul 13, 2012 9:05:46 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SortNo=40,IsOrderBy='Y' WHERE AD_PrintFormatItem_ID=50802
;

-- Jul 13, 2012 9:05:46 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SortNo=50,IsOrderBy='Y' WHERE AD_PrintFormatItem_ID=50793
;

-- Jul 13, 2012 9:05:46 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SortNo=60,IsOrderBy='Y' WHERE AD_PrintFormatItem_ID=50796
;

-- Jul 13, 2012 9:05:46 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SortNo=70,IsOrderBy='Y' WHERE AD_PrintFormatItem_ID=50797
;

-- Jul 13, 2012 9:05:46 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_PrintFormatItem SET SortNo=80,IsOrderBy='Y' WHERE AD_PrintFormatItem_ID=50801
;

DELETE FROM AD_PrintFormatItem WHERE AD_PrintFormat_ID=50036;
DELETE FROM AD_PrintFormat WHERE AD_PrintFormat_ID=50036;

DROP VIEW RV_M_Forecast_Period ;
DROP VIEW RV_M_Forecast;
CREATE OR REPLACE VIEW RV_M_Forecast AS
SELECT
f.AD_Client_ID ,
fl.AD_Org_ID,
f.M_Forecast_ID ,
f.Name ,
f.PP_Calendar_ID ,
f.PP_PeriodDefinition_ID ,
f.C_Project_ID,
f.C_ProjectPhase_ID,
f.C_Campaign_ID,
fl.PP_Period_ID,
fl.DatePromised,
fl.M_Product_ID ,
fl.SalesRep_ID,
p.C_UOM_ID,
fl.Qty,
fl.QtyCalculated,
 (SELECT pp.PriceList FROM M_PriceList_Version plv INNER JOIN M_ProductPrice pp ON (pp.M_PriceList_Version_ID=plv.M_PriceList_Version_ID ) WHERE plv.IsActive = 'Y' AND pp.M_Product_ID=fl.M_Product_ID AND plv.M_PriceList_ID=pl.M_PriceList_ID)
  AS PriceList,
   (SELECT pp.PriceStd FROM M_PriceList_Version plv INNER JOIN M_ProductPrice pp ON (pp.M_PriceList_Version_ID=plv.M_PriceList_Version_ID) WHERE plv.IsActive = 'Y' AND pp.M_Product_ID=fl.M_Product_ID AND plv.M_PriceList_ID=pl.M_PriceList_ID)
    AS PriceStd,
     (SELECT pp.PriceLimit FROM M_PriceList_Version plv INNER JOIN M_ProductPrice pp ON (pp.M_PriceList_Version_ID=plv.M_PriceList_Version_ID) WHERE plv.IsActive = 'Y' AND pp.M_Product_ID=fl.M_Product_ID AND plv.M_PriceList_ID=pl.M_PriceList_ID)
      AS PriceLimit,
       (SELECT pp.PriceStd FROM M_PriceList_Version plv INNER JOIN M_ProductPrice pp ON (pp.M_PriceList_Version_ID=plv.M_PriceList_Version_ID) WHERE  plv.IsActive = 'Y' AND pp.M_Product_ID=fl.M_Product_ID AND plv.M_PriceList_ID=pl.M_PriceList_ID)
        * fl.Qty AS TotalAmt,
	p.M_Product_Category_ID,
	p.Classification,
	p.Group1,
	p.Group2
	FROM M_Forecast f
	INNER JOIN M_ForecastLine fl ON (f.M_Forecast_ID = fl.M_Forecast_ID)
	INNER JOIN M_Product p ON (p.M_Product_ID=fl.M_Product_ID)
	INNER JOIN M_PriceList pl ON (pl.M_PriceList_ID=f.M_PriceList_ID);

CREATE OR REPLACE VIEW RV_M_Forecast_Period AS
SELECT AD_Client_ID ,
AD_Org_ID,
M_Forecast_ID ,
MAX(Name) AS Name,
PP_Calendar_ID ,
PP_PeriodDefinition_ID ,
PP_Period_ID,
C_Project_ID,
C_ProjectPhase_ID,
C_Campaign_ID,
M_Product_ID ,
C_UOM_ID,
SUM(Qty) AS Qty,
SUM(QtyCalculated) AS QtyCalculated,
SUM(TotalAmt) AS TotalAmt
FROM RV_M_Forecast f GROUP BY AD_Client_ID , AD_Org_ID , M_Forecast_ID , M_Product_ID , C_UOM_ID, PP_Calendar_ID , PP_PeriodDefinition_ID  , PP_Period_ID , C_Project_ID , C_ProjectPhase_ID,C_Campaign_ID
;

CREATE OR REPLACE VIEW RV_PP_ForecastRun AS 
 SELECT 
 frun.ad_client_id,
 frun.ad_org_id,
 frun.pp_forecastrun_id,
 frun.documentno,
 frun.description,
 frun.pp_forecastrule_id,
 frun.pp_calendar_id,
 frun.pp_perioddefinition_id,
 frun.ref_definitionperiod_id,
 frun.periodhistory,
 fmaster.m_product_id,
 p.value,
 p.name, 
 pc.m_product_category_id, 
 pcl.m_product_classification_id, 
 pclass.m_product_class_id, 
 pg.m_product_group_id, 
 frun.m_warehousesource_id, 
 fmaster.factoralpha, 
 fmaster.factorgamma, 
 fmaster.factormultiplier, 
 fmaster.factorscale, 
 pd.name AS periodname, 
 frun.m_warehouse_id, 
 fdetail.qtycalculated AS qtyinvoiced, 
 fresult.pp_period_id, 
 pr.startdate, pr.enddate, 
 fresult.periodno, 
 fresult.description AS linedescription, 
 fresult.qtycalculated, 
 fresult.qtyplan, 
 fresult.qtyabnormal
FROM pp_forecastrun frun
JOIN pp_forecastrunmaster fmaster ON fmaster.pp_forecastrun_id = frun.pp_forecastrun_id
JOIN pp_forecastrundetail fdetail ON fdetail.pp_forecastrunmaster_id = fmaster.pp_forecastrunmaster_id
LEFT JOIN pp_forecastrunresult fresult ON fresult.pp_forecastrunmaster_id = fmaster.pp_forecastrunmaster_id
JOIN pp_period pd ON pd.pp_period_id = fdetail.pp_period_id
JOIN pp_period pr ON pr.pp_period_id = fresult.pp_period_id
JOIN m_product p ON p.m_product_id = fmaster.m_product_id
LEFT JOIN m_product_category pc ON pc.m_product_category_id = p.m_product_category_id
LEFT JOIN m_product_classification pcl ON pcl.m_product_classification_id = p.m_product_classification_id
LEFT JOIN m_product_class pclass ON pclass.m_product_class_id = p.m_product_class_id
LEFT JOIN m_product_group pg ON pg.m_product_group_id = p.m_product_group_id;


