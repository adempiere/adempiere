SET DEFINE OFF;
SET SQLBLANKLINES OFF;
UPDATE ad_alert
   SET alertmessage =
                   'Check that the Adempiere Database has sufficient space:
'
 WHERE ad_alert_id = 100;

UPDATE ad_alert
   SET alertsubject = 'Adempiere Database Space'
 WHERE ad_alert_id = 100;

UPDATE ad_column
   SET description = 'Full URL address - e.g. http://www.adempiere.org'
 WHERE ad_column_id = 14222;

UPDATE ad_column
   SET description = 'Directory service domain name - e.g. adempiere.org'
 WHERE ad_column_id = 12402;

UPDATE ad_column
   SET description = 'Adempiere Request Document No'
 WHERE ad_column_id = 14666;

UPDATE ad_column
   SET description = 'Date when the Adempiere support expires'
 WHERE ad_column_id = 14852;

UPDATE ad_column
   SET description = 'Full URL address - e.g. http://www.adempiere.org'
 WHERE ad_column_id = 15240;

UPDATE ad_column
   SET description =
          'LDAP Server to authenticate and authorize external systems based on Adempiere'
 WHERE ad_column_id = 15930;

UPDATE ad_column
   SET description =
          'LDAP Server to authenticate and authorize external systems based on Adempiere'
 WHERE ad_column_id = 15946;

UPDATE ad_column
   SET description =
          'LDAP Server to authenticate and authorize external systems based on Adempiere'
 WHERE ad_column_id = 15969;

UPDATE ad_column
   SET description = 'Full URL address - e.g. http://www.adempiere.org'
 WHERE ad_column_id = 3081;

UPDATE ad_column
   SET description = 'Full URL address - e.g. http://www.adempiere.org'
 WHERE ad_column_id = 8129;

UPDATE ad_column
   SET description = 'Adempiere Alert'
 WHERE ad_column_id = 9044;

UPDATE ad_column
   SET description = 'Adempiere Alert'
 WHERE ad_column_id = 9054;

UPDATE ad_column
   SET description = 'Adempiere Alert'
 WHERE ad_column_id = 9082;

UPDATE ad_column
   SET description = 'Number of Internal Users for Adempiere Support'
 WHERE ad_column_id = 9327;

UPDATE ad_column
   SET description = 'Full URL address - e.g. http://www.adempiere.org'
 WHERE ad_column_id = 9785;

UPDATE ad_column
   SET description =
          'The day you started the implementation (if implementing) - or production (went life) with Adempiere'
 WHERE ad_column_id = 11816;

UPDATE ad_column
   SET description = 'Full URL address - e.g. http://www.adempiere.org'
 WHERE ad_column_id = 14427;

UPDATE ad_column
   SET HELP =
          'The Java Media Size. Example: "MediaSize.ISO.A4" (the package javax.print.attribute.standard is assumed). If you define your own media size, use the fully qualified name.
If the pattern for your language is not correct, please create a Adempiere support request with the correct information'
 WHERE ad_column_id = 13085;

UPDATE ad_column
   SET HELP =
          'The URL defines an fully qualified web address like http://www.adempiere.org'
 WHERE ad_column_id = 14222;

UPDATE ad_column
   SET HELP =
          'The URL defines an fully qualified web address like http://www.adempiere.org'
 WHERE ad_column_id = 14427;

UPDATE ad_column
   SET HELP =
          'A user defined accounting element referres to a Adempiere table. This allows to use any table content as an accounting dimension (e.g. Project Task).  Note that User Elements are optional and are populated from the context of the document (i.e. not requested)'
 WHERE ad_column_id = 14590;

UPDATE ad_column
   SET HELP =
          'A user defined accounting element referres to a Adempiere table. This allows to use any table content as an accounting dimension (e.g. Project Task).  Note that User Elements are optional and are populated from the context of the document (i.e. not requested) '
 WHERE ad_column_id = 14591;

UPDATE ad_column
   SET HELP =
          'A user defined accounting element referres to a Adempiere table. This allows to use any table content as an accounting dimension (e.g. Project Task).  Note that User Elements are optional and are populated from the context of the document (i.e. not requested)'
 WHERE ad_column_id = 14593;

UPDATE ad_column
   SET HELP =
          'A user defined accounting element referres to a Adempiere table. This allows to use any table content as an accounting dimension (e.g. Project Task).  Note that User Elements are optional and are populated from the context of the document (i.e. not requested) '
 WHERE ad_column_id = 14594;

UPDATE ad_column
   SET HELP =
          'A user defined accounting element referres to a Adempiere table. This allows to use any table content as an accounting dimension (e.g. Project Task).  Note that User Elements are optional and are populated from the context of the document (i.e. not requested)'
 WHERE ad_column_id = 14596;

UPDATE ad_column
   SET HELP =
          'A user defined accounting element referres to a Adempiere table. This allows to use any table content as an accounting dimension (e.g. Project Task).  Note that User Elements are optional and are populated from the context of the document (i.e. not requested) '
 WHERE ad_column_id = 14597;

UPDATE ad_column
   SET HELP =
          'If Adempiere maintains credit status, the status "Credit OK" is moved to "Credit Watch" if the credit available reaches the percent entered.  If not defined, 90% is used.'
 WHERE ad_column_id = 14640;

UPDATE ad_column
   SET HELP =
          'To automate error reporting, submit errors to Adempiere. Only error (stack trace) information is submitted (no data or confidential information).  It helps us to react faster and proactively.  If you have a support contract, we will you inform about corrective measures.  This functionality is experimental at this point.'
 WHERE ad_column_id = 14646;

UPDATE ad_column
   SET HELP =
          'System Issues are created to speed up the resolution of any system related issues (potential bugs).  If enabled, they are automatically reported to Adempiere.  No data or confidential information is transferred.'
 WHERE ad_column_id = 14648;

UPDATE ad_column
   SET HELP =
          'Visual representation of performance by color.  The Schema has often three levels (e.g. red-yellow-green).  Adempiere support two levels (e.g. red-green) or four levels (e.g. gray-bronce-silver-gold).  Note that Measures without a goal are represented white.  The percentages could be beween 0 and unlimited (i.e. above 100%).'
 WHERE ad_column_id = 14738;

UPDATE ad_column
   SET HELP =
          'Visual representation of performance by color.  The Schema has often three levels (e.g. red-yellow-green).  Adempiere support two levels (e.g. red-green) or four levels (e.g. gray-bronce-silver-gold).  Note that Measures without a goal are represented white.  The percentages could be beween 0 and unlimited (i.e. above 100%).'
 WHERE ad_column_id = 14755;

UPDATE ad_column
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_column_id = 14781;

UPDATE ad_column
   SET HELP = 'Check http://www.adempiere.org for support options'
 WHERE ad_column_id = 14852;

UPDATE ad_column
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_column_id = 15023;

UPDATE ad_column
   SET HELP =
          'The URL defines an fully qualified web address like http://www.adempiere.org'
 WHERE ad_column_id = 15240;

UPDATE ad_column
   SET HELP =
          'The Password for this User.  Passwords are required to identify authorized users.  For Adempiere Users, you can change the password via the Process "Reset Password".'
 WHERE ad_column_id = 15243;

UPDATE ad_column
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_column_id = 15592;

UPDATE ad_column
   SET HELP =
          'The entity type determines the ownership of Application Dictionary entries.  The types "Dictionary" and "Adempiere" should not be used and are maintainted by Adempiere (i.e. all changes are reversed during migration to the current definition).'
 WHERE ad_column_id = 15595;

UPDATE ad_column
   SET HELP =
          'If your appplication requires additional jar files, enter them here. The jar files must be located in the $ADEMPIERE_HOME/lib directory.'
 WHERE ad_column_id = 15606;

UPDATE ad_column
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_column_id = 15620;

UPDATE ad_column
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_column_id = 15756;

UPDATE ad_column
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_column_id = 15785;

UPDATE ad_column
   SET HELP =
          'The LDAP Server allows third party software (e.g. Apache) to use the users defined in the system to authentificate and authorize them.  There is only one server per Adempiere system.  The "o" is the Client key and the optional "ou" is the Interest Area key.'
 WHERE ad_column_id = 15930;

UPDATE ad_column
   SET HELP =
          'The LDAP Server allows third party software (e.g. Apache) to use the users defined in the system to authentificate and authorize them.  There is only one server per Adempiere system.  The "o" is the Client key and the optional "ou" is the Interest Area key.'
 WHERE ad_column_id = 15946;

UPDATE ad_column
   SET HELP =
          'The LDAP Server allows third party software (e.g. Apache) to use the users defined in the system to authentificate and authorize them.  There is only one server per Adempiere system.  The "o" is the Client key and the optional "ou" is the Interest Area key.'
 WHERE ad_column_id = 15969;

UPDATE ad_column
   SET HELP =
          'The Password for this User.  Passwords are required to identify authorized users.  For Adempiere Users, you can change the password via the Process "Reset Password".'
 WHERE ad_column_id = 417;

UPDATE ad_column
   SET HELP =
          'The URL defines an fully qualified web address like http://www.adempiere.org'
 WHERE ad_column_id = 3081;

UPDATE ad_column
   SET HELP =
          'The Password for this User.  Passwords are required to identify authorized users.  For Adempiere Users, you can change the password via the Process "Reset Password".'
 WHERE ad_column_id = 5059;

UPDATE ad_column
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_column_id = 6482;

UPDATE ad_column
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_column_id = 6484;

UPDATE ad_column
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_column_id = 6485;

UPDATE ad_column
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_column_id = 6486;

UPDATE ad_column
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_column_id = 6488;

UPDATE ad_column
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_column_id = 6490;

UPDATE ad_column
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_column_id = 7708;

UPDATE ad_column
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_column_id = 7709;

UPDATE ad_column
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_column_id = 7710;

UPDATE ad_column
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_column_id = 7711;

UPDATE ad_column
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_column_id = 7712;

UPDATE ad_column
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_column_id = 7713;

UPDATE ad_column
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_column_id = 7714;

UPDATE ad_column
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_column_id = 7715;

UPDATE ad_column
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_column_id = 7716;

UPDATE ad_column
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_column_id = 7721;

UPDATE ad_column
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_column_id = 7722;

UPDATE ad_column
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_column_id = 7723;

UPDATE ad_column
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_column_id = 7725;

UPDATE ad_column
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_column_id = 7728;

UPDATE ad_column
   SET HELP =
          'The Password for this User.  Passwords are required to identify authorized users.  For Adempiere Users, you can change the password via the Process "Reset Password".'
 WHERE ad_column_id = 7891;

UPDATE ad_column
   SET HELP =
          'The URL defines an fully qualified web address like http://www.adempiere.org'
 WHERE ad_column_id = 8129;

UPDATE ad_column
   SET HELP =
          'Adempiere Alerts allow you define system conditions you want to be alerted of'
 WHERE ad_column_id = 9044;

UPDATE ad_column
   SET HELP =
          'Adempiere Alerts allow you define system conditions you want to be alerted of'
 WHERE ad_column_id = 9054;

UPDATE ad_column
   SET HELP =
          'Adempiere Alerts allow you define system conditions you want to be alerted of'
 WHERE ad_column_id = 9082;

UPDATE ad_column
   SET HELP =
          'You can purchase professioal support from Adempiere, Inc. or their partners.  See http://www.adempiere.com for details.
'
 WHERE ad_column_id = 9327;

UPDATE ad_column
   SET HELP =
          'The Password for this User.  Passwords are required to identify authorized users.  For Adempiere Users, you can change the password via the Process "Reset Password".'
 WHERE ad_column_id = 9340;

UPDATE ad_column
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_column_id = 9343;

UPDATE ad_column
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_column_id = 9365;

UPDATE ad_column
   SET HELP =
          'The URL defines an fully qualified web address like http://www.adempiere.org'
 WHERE ad_column_id = 9785;

UPDATE ad_column
   SET HELP =
          'The System Registration helps Adempiere to help the installed base'
 WHERE ad_column_id = 9924;

UPDATE ad_column
   SET HELP = 'You can restrict the ability to export data from Adempiere.'
 WHERE ad_column_id = 9971;

UPDATE ad_column
   SET HELP = 'You can restrict the ability to export data from Adempiere.'
 WHERE ad_column_id = 9973;

UPDATE ad_column
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_column_id = 10013;

UPDATE ad_column
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_column_id = 10032;

UPDATE ad_column
   SET HELP =
          'The Password for this User.  Passwords are required to identify authorized users.  For Adempiere Users, you can change the password via the Process "Reset Password".'
 WHERE ad_column_id = 10354;

UPDATE ad_column
   SET HELP =
          'Adempiere converts the (string) field values to the attribute data type.  Booleans (Yes-No) may have the values "true" and "false", the date format is YYYY-MM-DD'
 WHERE ad_column_id = 10505;

UPDATE ad_column
   SET HELP =
          'Adempiere converts the (string) field values to the attribute data type.  Booleans (Yes-No) may have the values "true" and "false", the date format is YYYY-MM-DD'
 WHERE ad_column_id = 10531;

UPDATE ad_column
   SET HELP =
          'Adempiere converts the (string) field values to the attribute data type.  Booleans (Yes-No) may have the values "true" and "false", the date format is YYYY-MM-DD'
 WHERE ad_column_id = 10562;

UPDATE ad_column
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_column_id = 10565;

UPDATE ad_column
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_column_id = 10566;

UPDATE ad_column
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_column_id = 10569;

UPDATE ad_column
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_column_id = 10574;

UPDATE ad_column
   SET HELP =
          'Adempiere converts the (string) field values to the attribute data type.  Booleans (Yes-No) may have the values "true" and "false", the date format is YYYY-MM-DD'
 WHERE ad_column_id = 11560;

UPDATE ad_column
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_column_id = 11577;

UPDATE ad_column
   SET HELP =
          'If LDAP Host and Domain is specified, the user is authenticated via LDAP. The password in the User table is not used for connecting to Adempiere.'
 WHERE ad_column_id = 12402;

UPDATE ad_column
   SET HELP = 'LDAP connection string, e.g. ldap://dc.adempiere.org'
 WHERE ad_column_id = 12409;

UPDATE ad_column
   SET HELP =
          'If your bank provides an International Bank Account Number, enter it here
Details ISO 13616 and http://www.ecbs.org. The account number has the maximum length of 22 characters (without spaces). The IBAN is often printed with a apace after 4 characters. Do not enter the spaces in Adempiere.'
 WHERE ad_column_id = 13050;

UPDATE ad_column
   SET HELP =
          'Adempiere allows to automatically create archives of Documents (e.g. Invoices) or Reports. You view the archived material with the Archive Viewer'
 WHERE ad_column_id = 13074;

UPDATE ad_column
   SET HELP =
          'If selected, Numbers are printed with a decimal point "." - otherwise with a decimal comma ",".  The thousand separator is the opposite.
If the pattern for your language is not correct, please create a Adempiere support request with the correct information'
 WHERE ad_column_id = 13080;

UPDATE ad_column
   SET HELP =
          'Option Date pattern in Java notation. Examples: dd.MM.yyyy - dd/MM/yyyy
If the pattern for your language is not correct, please create a Adempiere support request with the correct information'
 WHERE ad_column_id = 13081;

UPDATE ad_column
   SET HELP =
          'Option Time pattern in Java notation. Examples: "hh:mm:ss aaa z" - "HH:mm:ss"
If the pattern for your language is not correct, please create a Adempiere support request with the correct information'
 WHERE ad_column_id = 13082;

UPDATE ad_element
   SET description =
          'LDAP Server to authenticate and authorize external systems based on Adempiere'
 WHERE ad_element_id = 3093;

UPDATE ad_element
   SET description =
          'The day you started the implementation (if implementing) - or production (went life) with Adempiere'
 WHERE ad_element_id = 2488;

UPDATE ad_element
   SET description = 'Adempiere Alert'
 WHERE ad_element_id = 2087;

UPDATE ad_element
   SET description = 'Date when the Adempiere support expires'
 WHERE ad_element_id = 2938;

UPDATE ad_element
   SET description = 'Number of Internal Users for Adempiere Support'
 WHERE ad_element_id = 2124;

UPDATE ad_element
   SET description = 'Adempiere Request Document No'
 WHERE ad_element_id = 2891;

UPDATE ad_element
   SET description = 'Full URL address - e.g. http://www.adempiere.org'
 WHERE ad_element_id = 983;

UPDATE ad_element
   SET description = 'Directory service domain name - e.g. adempiere.org'
 WHERE ad_element_id = 2549;

UPDATE ad_element
   SET HELP =
          'If LDAP Host and Domain is specified, the user is authenticated via LDAP. The password in the User table is not used for connecting to Adempiere.'
 WHERE ad_element_id = 2549;

UPDATE ad_element
   SET HELP = 'LDAP connection string, e.g. ldap://dc.adempiere.org'
 WHERE ad_element_id = 2550;

UPDATE ad_element
   SET HELP =
          'If your bank provides an International Bank Account Number, enter it here
Details ISO 13616 and http://www.ecbs.org. The account number has the maximum length of 22 characters (without spaces). The IBAN is often printed with a apace after 4 characters. Do not enter the spaces in Adempiere.'
 WHERE ad_element_id = 2664;

UPDATE ad_element
   SET HELP =
          'Adempiere allows to automatically create archives of Documents (e.g. Invoices) or Reports. You view the archived material with the Archive Viewer'
 WHERE ad_element_id = 2672;

UPDATE ad_element
   SET HELP =
          'If selected, Numbers are printed with a decimal point "." - otherwise with a decimal comma ",".  The thousand separator is the opposite.
If the pattern for your language is not correct, please create a Adempiere support request with the correct information'
 WHERE ad_element_id = 2674;

UPDATE ad_element
   SET HELP =
          'The Java Media Size. Example: "MediaSize.ISO.A4" (the package javax.print.attribute.standard is assumed). If you define your own media size, use the fully qualified name.
If the pattern for your language is not correct, please create a Adempiere support request with the correct information'
 WHERE ad_element_id = 2675;

UPDATE ad_element
   SET HELP =
          'Option Time pattern in Java notation. Examples: "hh:mm:ss aaa z" - "HH:mm:ss"
If the pattern for your language is not correct, please create a Adempiere support request with the correct information'
 WHERE ad_element_id = 2676;

UPDATE ad_element
   SET HELP =
          'A user defined accounting element referres to a Adempiere table. This allows to use any table content as an accounting dimension (e.g. Project Task).  Note that User Elements are optional and are populated from the context of the document (i.e. not requested)'
 WHERE ad_element_id = 2877;

UPDATE ad_element
   SET HELP =
          'A user defined accounting element referres to a Adempiere table. This allows to use any table content as an accounting dimension (e.g. Project Task).  Note that User Elements are optional and are populated from the context of the document (i.e. not requested) '
 WHERE ad_element_id = 2878;

UPDATE ad_element
   SET HELP =
          'If Adempiere maintains credit status, the status "Credit OK" is moved to "Credit Watch" if the credit available reaches the percent entered.  If not defined, 90% is used.'
 WHERE ad_element_id = 2883;

UPDATE ad_element
   SET HELP =
          'To automate error reporting, submit errors to Adempiere. Only error (stack trace) information is submitted (no data or confidential information).  It helps us to react faster and proactively.  If you have a support contract, we will you inform about corrective measures.  This functionality is experimental at this point.'
 WHERE ad_element_id = 2886;

UPDATE ad_element
   SET HELP =
          'System Issues are created to speed up the resolution of any system related issues (potential bugs).  If enabled, they are automatically reported to Adempiere.  No data or confidential information is transferred.'
 WHERE ad_element_id = 2887;

UPDATE ad_element
   SET HELP =
          'Visual representation of performance by color.  The Schema has often three levels (e.g. red-yellow-green).  Adempiere support two levels (e.g. red-green) or four levels (e.g. gray-bronce-silver-gold).  Note that Measures without a goal are represented white.  The percentages could be beween 0 and unlimited (i.e. above 100%).'
 WHERE ad_element_id = 2903;

UPDATE ad_element
   SET HELP = 'Check http://www.adempiere.org for support options'
 WHERE ad_element_id = 2938;

UPDATE ad_element
   SET HELP =
          'The entity type determines the ownership of Application Dictionary entries.  The types "Dictionary" and "Adempiere" should not be used and are maintainted by Adempiere (i.e. all changes are reversed during migration to the current definition).'
 WHERE ad_element_id = 3052;

UPDATE ad_element
   SET HELP =
          'If your appplication requires additional jar files, enter them here. The jar files must be located in the $ADEMPIERE_HOME/lib directory.'
 WHERE ad_element_id = 3054;

UPDATE ad_element
   SET HELP =
          'Adempiere converts the (string) field values to the attribute data type.  Booleans (Yes-No) may have the values "true" and "false", the date format is YYYY-MM-DD'
 WHERE ad_element_id = 2317;

UPDATE ad_element
   SET HELP = 'You can restrict the ability to export data from Adempiere.'
 WHERE ad_element_id = 2204;

UPDATE ad_element
   SET HELP =
          'The System Registration helps Adempiere to help the installed base'
 WHERE ad_element_id = 2189;

UPDATE ad_element
   SET HELP =
          'You can purchase professioal support from Adempiere, Inc. or their partners.  See http://www.adempiere.com for details.
'
 WHERE ad_element_id = 2124;

UPDATE ad_element
   SET HELP =
          'Adempiere Alerts allow you define system conditions you want to be alerted of'
 WHERE ad_element_id = 2087;

UPDATE ad_element
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_element_id = 1682;

UPDATE ad_element
   SET HELP =
          'The URL defines an fully qualified web address like http://www.adempiere.org'
 WHERE ad_element_id = 983;

UPDATE ad_element
   SET HELP =
          'The Password for this User.  Passwords are required to identify authorized users.  For Adempiere Users, you can change the password via the Process "Reset Password".'
 WHERE ad_element_id = 498;

UPDATE ad_element
   SET HELP =
          'Option Date pattern in Java notation. Examples: dd.MM.yyyy - dd/MM/yyyy
If the pattern for your language is not correct, please create a Adempiere support request with the correct information'
 WHERE ad_element_id = 2673;

UPDATE ad_element
   SET HELP =
          'The LDAP Server allows third party software (e.g. Apache) to use the users defined in the system to authentificate and authorize them.  There is only one server per Adempiere system.  The "o" is the Client key and the optional "ou" is the Interest Area key.'
 WHERE ad_element_id = 3093;

UPDATE ad_entitytype
   SET description = 'Adempiere Ownership ** System Maintained **'
 WHERE ad_entitytype_id = 20;

UPDATE ad_entitytype
   SET NAME = 'Adempiere'
 WHERE ad_entitytype_id = 20;

UPDATE ad_field
   SET description = 'Full URL address - e.g. http://www.adempiere.org'
 WHERE ad_field_id = 2164;

UPDATE ad_field
   SET description =
          'LDAP Server to authenticate and authorize external systems based on Adempiere'
 WHERE ad_field_id = 13749;

UPDATE ad_field
   SET description = 'Full URL address - e.g. http://www.adempiere.org'
 WHERE ad_field_id = 6216;

UPDATE ad_field
   SET description = 'Adempiere Alert'
 WHERE ad_field_id = 6858;

UPDATE ad_field
   SET description = 'Adempiere Alert'
 WHERE ad_field_id = 6866;

UPDATE ad_field
   SET description = 'Number of Internal Users for Adempiere Support'
 WHERE ad_field_id = 6980;

UPDATE ad_field
   SET description = 'Adempiere Alert'
 WHERE ad_field_id = 7002;

UPDATE ad_field
   SET description =
              'Registrations help us to better serve the Adempiere User Base.'
 WHERE ad_field_id = 8330;

UPDATE ad_field
   SET description = 'Full URL address - e.g. http://www.adempiere.org'
 WHERE ad_field_id = 9585;

UPDATE ad_field
   SET description = 'Full URL address - e.g. http://www.adempiere.org'
 WHERE ad_field_id = 9649;

UPDATE ad_field
   SET description = 'Full URL address - e.g. http://www.adempiere.org'
 WHERE ad_field_id = 9694;

UPDATE ad_field
   SET description = 'Full URL address - e.g. http://www.adempiere.org'
 WHERE ad_field_id = 9745;

UPDATE ad_field
   SET description = 'Full URL address - e.g. http://www.adempiere.org'
 WHERE ad_field_id = 9794;

UPDATE ad_field
   SET description = 'Full URL address - e.g. http://www.adempiere.org'
 WHERE ad_field_id = 9862;

UPDATE ad_field
   SET description = 'Full URL address - e.g. http://www.adempiere.org'
 WHERE ad_field_id = 9912;

UPDATE ad_field
   SET description = 'Full URL address - e.g. http://www.adempiere.org'
 WHERE ad_field_id = 9966;

UPDATE ad_field
   SET description =
          'The day you started the implementation (if implementing) - or production (went life) with Adempiere'
 WHERE ad_field_id = 10221;

UPDATE ad_field
   SET description = 'Directory service domain name - e.g. adempiere.org'
 WHERE ad_field_id = 10489;

UPDATE ad_field
   SET description = 'Full URL address - e.g. http://www.adempiere.org'
 WHERE ad_field_id = 10688;

UPDATE ad_field
   SET description = 'Full URL address - e.g. http://www.adempiere.org'
 WHERE ad_field_id = 12363;

UPDATE ad_field
   SET description = 'Report Issue to Adempiere'
 WHERE ad_field_id = 12662;

UPDATE ad_field
   SET description = 'Adempiere Request Document No'
 WHERE ad_field_id = 12664;

UPDATE ad_field
   SET description = 'Full URL address - e.g. http://www.adempiere.org'
 WHERE ad_field_id = 12733;

UPDATE ad_field
   SET description = 'Date when the Adempiere support expires'
 WHERE ad_field_id = 12870;

UPDATE ad_field
   SET description = 'Full URL address - e.g. http://www.adempiere.org'
 WHERE ad_field_id = 13181;

UPDATE ad_field
   SET description = 'Register your extension with Adempiere'
 WHERE ad_field_id = 13507;

UPDATE ad_field
   SET description =
          'LDAP Server to authenticate and authorize external systems based on Adempiere'
 WHERE ad_field_id = 13715;

UPDATE ad_field
   SET description =
          'LDAP Server to authenticate and authorize external systems based on Adempiere'
 WHERE ad_field_id = 13723;

UPDATE ad_field
   SET description =
          'LDAP Server to authenticate and authorize external systems based on Adempiere'
 WHERE ad_field_id = 13738;

UPDATE ad_field
   SET description =
               'Name your Adempiere System installation, e.g. Joe Block, Inc.'
 WHERE ad_field_id = 5899;

UPDATE ad_field
   SET HELP =
          'The Password for this User.  Passwords are required to identify authorized users.  For Adempiere Users, you can change the password via the Process "Reset Password".'
 WHERE ad_field_id = 302;

UPDATE ad_field
   SET HELP =
          'The URL defines an fully qualified web address like http://www.adempiere.org'
 WHERE ad_field_id = 2164;

UPDATE ad_field
   SET HELP =
          'The Password for this User.  Passwords are required to identify authorized users.  For Adempiere Users, you can change the password via the Process "Reset Password".'
 WHERE ad_field_id = 3970;

UPDATE ad_field
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_field_id = 5121;

UPDATE ad_field
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_field_id = 5123;

UPDATE ad_field
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_field_id = 5124;

UPDATE ad_field
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_field_id = 5125;

UPDATE ad_field
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_field_id = 5127;

UPDATE ad_field
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_field_id = 5128;

UPDATE ad_field
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_field_id = 5129;

UPDATE ad_field
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_field_id = 5808;

UPDATE ad_field
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_field_id = 5809;

UPDATE ad_field
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_field_id = 5810;

UPDATE ad_field
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_field_id = 5811;

UPDATE ad_field
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_field_id = 5812;

UPDATE ad_field
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_field_id = 5813;

UPDATE ad_field
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_field_id = 5814;

UPDATE ad_field
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_field_id = 5815;

UPDATE ad_field
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_field_id = 5816;

UPDATE ad_field
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_field_id = 5817;

UPDATE ad_field
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_field_id = 5821;

UPDATE ad_field
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_field_id = 5823;

UPDATE ad_field
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_field_id = 5824;

UPDATE ad_field
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_field_id = 5827;

UPDATE ad_field
   SET HELP =
          'The Password for this User.  Passwords are required to identify authorized users.  For Adempiere Users, you can change the password via the Process "Reset Password".'
 WHERE ad_field_id = 5940;

UPDATE ad_field
   SET HELP =
          'The URL defines an fully qualified web address like http://www.adempiere.org'
 WHERE ad_field_id = 6216;

UPDATE ad_field
   SET HELP =
          'Adempiere Alerts allow you define system conditions you want to be alerted of'
 WHERE ad_field_id = 6858;

UPDATE ad_field
   SET HELP =
          'Adempiere Alerts allow you define system conditions you want to be alerted of'
 WHERE ad_field_id = 6866;

UPDATE ad_field
   SET HELP =
          'You can purchase professioal support from Adempiere, Inc. or their partners.  See http://www.adempiere.com for details.
'
 WHERE ad_field_id = 6980;

UPDATE ad_field
   SET HELP =
          'Adempiere Alerts allow you define system conditions you want to be alerted of'
 WHERE ad_field_id = 7002;

UPDATE ad_field
   SET HELP =
          'The Password for this User.  Passwords are required to identify authorized users.  For Adempiere Users, you can change the password via the Process "Reset Password".'
 WHERE ad_field_id = 7013;

UPDATE ad_field
   SET HELP =
          'The Password for this User.  Passwords are required to identify authorized users.  For Adempiere Users, you can change the password via the Process "Reset Password".'
 WHERE ad_field_id = 7486;

UPDATE ad_field
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_field_id = 7519;

UPDATE ad_field
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_field_id = 7521;

UPDATE ad_field
   SET HELP = 'You can restrict the ability to export data from Adempiere.'
 WHERE ad_field_id = 8314;

UPDATE ad_field
   SET HELP = 'You can restrict the ability to export data from Adempiere.'
 WHERE ad_field_id = 8319;

UPDATE ad_field
   SET HELP = 'You can restrict the ability to export data from Adempiere.'
 WHERE ad_field_id = 8321;

UPDATE ad_field
   SET HELP =
          'We will NOT make the data available to any third party or use the information for other than statistical purposes.  
It will help us, if you would allow to publish your use of Adempiere. We will contact you directly before we publish any information.
'
 WHERE ad_field_id = 8330;

UPDATE ad_field
   SET HELP =
          'The System Registration helps Adempiere to help the installed base'
 WHERE ad_field_id = 8336;

UPDATE ad_field
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_field_id = 8353;

UPDATE ad_field
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_field_id = 8372;

UPDATE ad_field
   SET HELP =
          'The Password for this User.  Passwords are required to identify authorized users.  For Adempiere Users, you can change the password via the Process "Reset Password".'
 WHERE ad_field_id = 8451;

UPDATE ad_field
   SET HELP =
          'The Password for this User.  Passwords are required to identify authorized users.  For Adempiere Users, you can change the password via the Process "Reset Password".'
 WHERE ad_field_id = 8685;

UPDATE ad_field
   SET HELP =
          'Adempiere converts the (string) field values to the attribute data type.  Booleans (Yes-No) may have the values "true" and "false", the date format is YYYY-MM-DD'
 WHERE ad_field_id = 8782;

UPDATE ad_field
   SET HELP =
          'Adempiere converts the (string) field values to the attribute data type.  Booleans (Yes-No) may have the values "true" and "false", the date format is YYYY-MM-DD'
 WHERE ad_field_id = 8838;

UPDATE ad_field
   SET HELP =
          'Adempiere converts the (string) field values to the attribute data type.  Booleans (Yes-No) may have the values "true" and "false", the date format is YYYY-MM-DD'
 WHERE ad_field_id = 8846;

UPDATE ad_field
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_field_id = 8858;

UPDATE ad_field
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_field_id = 8860;

UPDATE ad_field
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_field_id = 8862;

UPDATE ad_field
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_field_id = 8863;

UPDATE ad_field
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_field_id = 8879;

UPDATE ad_field
   SET HELP =
          'The URL defines an fully qualified web address like http://www.adempiere.org'
 WHERE ad_field_id = 9585;

UPDATE ad_field
   SET HELP =
          'The URL defines an fully qualified web address like http://www.adempiere.org'
 WHERE ad_field_id = 9649;

UPDATE ad_field
   SET HELP =
          'The URL defines an fully qualified web address like http://www.adempiere.org'
 WHERE ad_field_id = 9694;

UPDATE ad_field
   SET HELP =
          'The URL defines an fully qualified web address like http://www.adempiere.org'
 WHERE ad_field_id = 9745;

UPDATE ad_field
   SET HELP =
          'The URL defines an fully qualified web address like http://www.adempiere.org'
 WHERE ad_field_id = 9794;

UPDATE ad_field
   SET HELP =
          'The URL defines an fully qualified web address like http://www.adempiere.org'
 WHERE ad_field_id = 9862;

UPDATE ad_field
   SET HELP =
          'The URL defines an fully qualified web address like http://www.adempiere.org'
 WHERE ad_field_id = 9912;

UPDATE ad_field
   SET HELP =
          'The URL defines an fully qualified web address like http://www.adempiere.org'
 WHERE ad_field_id = 9966;

UPDATE ad_field
   SET HELP =
          'Adempiere converts the (string) field values to the attribute data type.  Booleans (Yes-No) may have the values "true" and "false", the date format is YYYY-MM-DD'
 WHERE ad_field_id = 10090;

UPDATE ad_field
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_field_id = 10100;

UPDATE ad_field
   SET HELP =
          'If LDAP Host and Domain is specified, the user is authenticated via LDAP. The password in the User table is not used for connecting to Adempiere.'
 WHERE ad_field_id = 10489;

UPDATE ad_field
   SET HELP = 'LDAP connection string, e.g. ldap://dc.adempiere.org'
 WHERE ad_field_id = 10493;

UPDATE ad_field
   SET HELP =
          'The URL defines an fully qualified web address like http://www.adempiere.org'
 WHERE ad_field_id = 10688;

UPDATE ad_field
   SET HELP =
          'If your bank provides an International Bank Account Number, enter it here
Details ISO 13616 and http://www.ecbs.org. The account number has the maximum length of 22 characters (without spaces). The IBAN is often printed with a apace after 4 characters. Do not enter the spaces in Adempiere.'
 WHERE ad_field_id = 11017;

UPDATE ad_field
   SET HELP =
          'Adempiere allows to automatically create archives of Documents (e.g. Invoices) or Reports. You view the archived material with the Archive Viewer'
 WHERE ad_field_id = 11025;

UPDATE ad_field
   SET HELP =
          'Option Date pattern in Java notation. Examples: dd.MM.yyyy - dd/MM/yyyy
If the pattern for your language is not correct, please create a Adempiere support request with the correct information'
 WHERE ad_field_id = 11172;

UPDATE ad_field
   SET HELP =
          'If selected, Numbers are printed with a decimal point "." - otherwise with a decimal comma ",".  The thousand separator is the opposite.
If the pattern for your language is not correct, please create a Adempiere support request with the correct information'
 WHERE ad_field_id = 11173;

UPDATE ad_field
   SET HELP =
          'Option Time pattern in Java notation. Examples: "hh:mm:ss aaa z" - "HH:mm:ss"
If the pattern for your language is not correct, please create a Adempiere support request with the correct information'
 WHERE ad_field_id = 11175;

UPDATE ad_field
   SET HELP =
          'Option Date pattern in Java notation. Examples: dd.MM.yyyy - dd/MM/yyyy
If the pattern for your language is not correct, please create a Adempiere support request with the correct information'
 WHERE ad_field_id = 11180;

UPDATE ad_field
   SET HELP =
          'If selected, Numbers are printed with a decimal point "." - otherwise with a decimal comma ",".  The thousand separator is the opposite.
If the pattern for your language is not correct, please create a Adempiere support request with the correct information'
 WHERE ad_field_id = 11181;

UPDATE ad_field
   SET HELP =
          'Option Time pattern in Java notation. Examples: "hh:mm:ss aaa z" - "HH:mm:ss"
If the pattern for your language is not correct, please create a Adempiere support request with the correct information'
 WHERE ad_field_id = 11183;

UPDATE ad_field
   SET HELP =
          'The Java Media Size. Example: "MediaSize.ISO.A4" (the package javax.print.attribute.standard is assumed). If you define your own media size, use the fully qualified name.
If the pattern for your language is not correct, please create a Adempiere support request with the correct information'
 WHERE ad_field_id = 11184;

UPDATE ad_field
   SET HELP =
          'The URL defines an fully qualified web address like http://www.adempiere.org'
 WHERE ad_field_id = 12363;

UPDATE ad_field
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_field_id = 12535;

UPDATE ad_field
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_field_id = 12570;

UPDATE ad_field
   SET HELP =
          'A user defined accounting element referres to a Adempiere table. This allows to use any table content as an accounting dimension (e.g. Project Task).  Note that User Elements are optional and are populated from the context of the document (i.e. not requested)'
 WHERE ad_field_id = 12604;

UPDATE ad_field
   SET HELP =
          'A user defined accounting element referres to a Adempiere table. This allows to use any table content as an accounting dimension (e.g. Project Task).  Note that User Elements are optional and are populated from the context of the document (i.e. not requested) '
 WHERE ad_field_id = 12605;

UPDATE ad_field
   SET HELP =
          'A user defined accounting element referres to a Adempiere table. This allows to use any table content as an accounting dimension (e.g. Project Task).  Note that User Elements are optional and are populated from the context of the document (i.e. not requested)'
 WHERE ad_field_id = 12607;

UPDATE ad_field
   SET HELP =
          'A user defined accounting element referres to a Adempiere table. This allows to use any table content as an accounting dimension (e.g. Project Task).  Note that User Elements are optional and are populated from the context of the document (i.e. not requested) '
 WHERE ad_field_id = 12608;

UPDATE ad_field
   SET HELP =
          'A user defined accounting element referres to a Adempiere table. This allows to use any table content as an accounting dimension (e.g. Project Task).  Note that User Elements are optional and are populated from the context of the document (i.e. not requested)'
 WHERE ad_field_id = 12610;

UPDATE ad_field
   SET HELP =
          'A user defined accounting element referres to a Adempiere table. This allows to use any table content as an accounting dimension (e.g. Project Task).  Note that User Elements are optional and are populated from the context of the document (i.e. not requested) '
 WHERE ad_field_id = 12611;

UPDATE ad_field
   SET HELP =
          'If Adempiere maintains credit status, the status "Credit OK" is moved to "Credit Watch" if the credit available reaches the percent entered.  If not defined, 90% is used.'
 WHERE ad_field_id = 12648;

UPDATE ad_field
   SET HELP =
          'To automate error reporting, submit errors to Adempiere. Only error (stack trace) information is submitted (no data or confidential information).  It helps us to react faster and proactively.  If you have a support contract, we will you inform about corrective measures.  This functionality is experimental at this point.'
 WHERE ad_field_id = 12653;

UPDATE ad_field
   SET HELP =
          'System Issues are created to speed up the resolution of any system related issues (potential bugs).  If enabled, they are automatically reported to Adempiere.  No data or confidential information is transferred.'
 WHERE ad_field_id = 12673;

UPDATE ad_field
   SET HELP =
          'The URL defines an fully qualified web address like http://www.adempiere.org'
 WHERE ad_field_id = 12733;

UPDATE ad_field
   SET HELP =
          'Visual representation of performance by color.  The Schema has often three levels (e.g. red-yellow-green).  Adempiere support two levels (e.g. red-green) or four levels (e.g. gray-bronce-silver-gold).  Note that Measures without a goal are represented white.  The percentages could be beween 0 and unlimited (i.e. above 100%).'
 WHERE ad_field_id = 12767;

UPDATE ad_field
   SET HELP =
          'Visual representation of performance by color.  The Schema has often three levels (e.g. red-yellow-green).  Adempiere support two levels (e.g. red-green) or four levels (e.g. gray-bronce-silver-gold).  Note that Measures without a goal are represented white.  The percentages could be beween 0 and unlimited (i.e. above 100%).'
 WHERE ad_field_id = 12774;

UPDATE ad_field
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_field_id = 12821;

UPDATE ad_field
   SET HELP = 'Check http://www.adempiere.org for support options'
 WHERE ad_field_id = 12870;

UPDATE ad_field
   SET HELP =
          'The process connects to the Adempiere Support Services server and validates the support contract.  To sign up for support, please go to http://www.adempiere.org'
 WHERE ad_field_id = 12871;

UPDATE ad_field
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_field_id = 13020;

UPDATE ad_field
   SET HELP =
          'The Password for this User.  Passwords are required to identify authorized users.  For Adempiere Users, you can change the password via the Process "Reset Password".'
 WHERE ad_field_id = 13178;

UPDATE ad_field
   SET HELP =
          'The URL defines an fully qualified web address like http://www.adempiere.org'
 WHERE ad_field_id = 13181;

UPDATE ad_field
   SET HELP =
          'If your appplication requires additional jar files, enter them here. The jar files must be located in the $ADEMPIERE_HOME/lib directory.'
 WHERE ad_field_id = 13498;

UPDATE ad_field
   SET HELP =
          'The entity type determines the ownership of Application Dictionary entries.  The types "Dictionary" and "Adempiere" should not be used and are maintainted by Adempiere (i.e. all changes are reversed during migration to the current definition).'
 WHERE ad_field_id = 13502;

UPDATE ad_field
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_field_id = 13503;

UPDATE ad_field
   SET HELP =
          'You can register the four character extension with Adempiere. This makes sure that your extension can be automatically distributed and implemented.  You will also be able to certify extensions.  Contact Adempiere for details.'
 WHERE ad_field_id = 13507;

UPDATE ad_field
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_field_id = 13513;

UPDATE ad_field
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_field_id = 13575;

UPDATE ad_field
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_field_id = 13597;

UPDATE ad_field
   SET HELP =
          'The LDAP Server allows third party software (e.g. Apache) to use the users defined in the system to authentificate and authorize them.  There is only one server per Adempiere system.  The "o" is the Client key and the optional "ou" is the Interest Area key.'
 WHERE ad_field_id = 13715;

UPDATE ad_field
   SET HELP =
          'The LDAP Server allows third party software (e.g. Apache) to use the users defined in the system to authentificate and authorize them.  There is only one server per Adempiere system.  The "o" is the Client key and the optional "ou" is the Interest Area key.'
 WHERE ad_field_id = 13723;

UPDATE ad_field
   SET HELP =
          'The LDAP Server allows third party software (e.g. Apache) to use the users defined in the system to authentificate and authorize them.  There is only one server per Adempiere system.  The "o" is the Client key and the optional "ou" is the Interest Area key.'
 WHERE ad_field_id = 13738;

UPDATE ad_field
   SET HELP =
          'The LDAP Server allows third party software (e.g. Apache) to use the users defined in the system to authentificate and authorize them.  There is only one server per Adempiere system.  The "o" is the Client key and the optional "ou" is the Interest Area key.'
 WHERE ad_field_id = 13749;

UPDATE ad_ldapprocessor
   SET NAME = 'Adempiere LDAP Server'
 WHERE ad_ldapprocessor_id = 100;

UPDATE ad_menu
   SET description = 'Adempiere Alert'
 WHERE ad_menu_id = 379;

UPDATE ad_menu
   SET description = 'Adempiere Server Maintenance'
 WHERE ad_menu_id = 456;

UPDATE ad_menu
   SET description = 'Adempiere Workflow'
 WHERE ad_menu_id = 501;

UPDATE ad_menu
   SET description =
          'LDAP Server to authenticate and authorize external systems based on Adempiere'
 WHERE ad_menu_id = 594;

UPDATE ad_message
   SET msgtext = 'Adempiere, Inc.'
 WHERE ad_message_id = 125;

UPDATE ad_message
   SET msgtext =
          'Download {0} for {1}

Version = {2} - Lot = {3} - SerNo = {4}
Guarantee Date = {5,date,short}

Thank you for using Adempiere Customer Asset Management
'
 WHERE ad_message_id = 747;

UPDATE ad_message
   SET msgtext = 'Adempiere Online'
 WHERE ad_message_id = 127;

UPDATE ad_message
   SET msgtip = 'http://www.adempiere.org'
 WHERE ad_message_id = 125;

UPDATE ad_message
   SET msgtip = 'Window  - Metal - Adempiere'
 WHERE ad_message_id = 570;

UPDATE ad_message
   SET msgtip =
          'Only for internal Adempiere Dictionary Maintenence - DO NOT SELECT'
 WHERE ad_message_id = 713;

UPDATE ad_message
   SET msgtip =
          'Sign up for Adempiere Support - also supports the product development'
 WHERE ad_message_id = 868;

UPDATE ad_message
   SET msgtip =
          'Create Trace file in Adempiere directory or in user home directory'
 WHERE ad_message_id = 813;

UPDATE ad_message
   SET msgtip =
               'Name your Adempiere System installation, e.g. Joe Block, Inc.'
 WHERE ad_message_id = 867;

UPDATE ad_message
   SET VALUE = 'Adempiere'
 WHERE ad_message_id = 125;

UPDATE ad_process
   SET description = 'Register your extension with Adempiere'
 WHERE ad_process_id = 348;

UPDATE ad_process
   SET description = 'Report Issue to Adempiere'
 WHERE ad_process_id = 339;

UPDATE ad_process
   SET description =
              'Registrations help us to better serve the Adempiere User Base.'
 WHERE ad_process_id = 250;

UPDATE ad_process
   SET HELP =
          'We will NOT make the data available to any third party or use the information for other than statistical purposes.  
It will help us, if you would allow to publish your use of Adempiere. We will contact you directly before we publish any information.
'
 WHERE ad_process_id = 250;

UPDATE ad_process
   SET HELP =
          'To increase performance, Adempiere caches repeatedly used data. This process clears the local cache.'
 WHERE ad_process_id = 205;

UPDATE ad_process
   SET HELP =
          'The process connects to the Adempiere Support Services server and validates the support contract.  To sign up for support, please go to http://www.adempiere.org'
 WHERE ad_process_id = 342;

UPDATE ad_process
   SET HELP =
          'You can register the four character extension with Adempiere. This makes sure that your extension can be automatically distributed and implemented.  You will also be able to certify extensions.  Contact Adempiere for details.'
 WHERE ad_process_id = 348;

UPDATE ad_process_para
   SET HELP =
          'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
 WHERE ad_process_para_id = 630;

UPDATE ad_ref_list
   SET description = 'Adempiere Application (synchronized)'
 WHERE ad_ref_list_id = 488;

UPDATE ad_ref_list
   SET NAME = 'Adempiere'
 WHERE ad_ref_list_id = 488;

UPDATE ad_tab
   SET commitwarning =
          'Please create a support request (http://www.adempiere.org/support/requests.html), if the country data is not correct.'
 WHERE ad_tab_id = 135;

UPDATE ad_tab
   SET commitwarning =
          'Please create a support request (http://www.adempiere.org/support/requests.html), if the region info is not correct/incomplete.'
 WHERE ad_tab_id = 136;

UPDATE ad_tab
   SET description = 'Adempiere Alert'
 WHERE ad_tab_id = 504;

UPDATE ad_tab
   SET description =
          'LDAP Server to authenticate and authorize external systems based on Adempiere'
 WHERE ad_tab_id = 849;

UPDATE ad_tab
   SET HELP =
          'The entity type determines the ownership of Application Dictionary entries.  The types "Dictionary" and "Adempiere" should not be used and are maintainted by Adempiere (i.e. all changes are reversed during migration to the current definition).'
 WHERE ad_tab_id = 831;

UPDATE ad_tab
   SET HELP =
          'Visual representation of performance by color.  The Schema has often three levels (e.g. red-yellow-green).  Adempiere support two levels (e.g. red-green) or four levels (e.g. gray-bronce-silver-gold).  Note that Measures without a goal are represented white.  The percentages could be beween 0 and unlimited (i.e. above 100%).'
 WHERE ad_tab_id = 779;

UPDATE ad_tab
   SET HELP =
          'Adempiere Alerts allow you define system conditions you want to be alerted of.'
 WHERE ad_tab_id = 504;

UPDATE ad_tab
   SET HELP =
          'Before importing, Adempiere checks the Unit of Measure (default if not set), the Product Category (default if not set), the Business Partner, the Currency (defaults to accounting currency if not set), the Product Type (only Items and Services), the uniqueness of UPC, Key and uniqueness and existence of the Vendor Product No.<br>
Adempiere tries to map to existing products, if the UPC, the Key and the Vendor Product No matches (in this sequence). If the imported record could be matched, product field values will only be overwritten, if the corresponding  Import field is explicitly defined.  Example: the Product Category will only be overwritten if explicitly set in the Import.'
 WHERE ad_tab_id = 442;

UPDATE ad_tab
   SET HELP =
          'System Issues are created to speed up the resolution of any system related issues (potential bugs).  If enabled, they are automatically reported to Adempiere.  No data or confidential information is transferred.'
 WHERE ad_tab_id = 777;

UPDATE ad_tab
   SET HELP =
          'The LDAP Server allows third party software (e.g. Apache) to use the users defined in the system to authentificate and authorize them.  There is only one server per Adempiere system.  The "o" is the Client key and the optional "ou" is the Interest Area key.'
 WHERE ad_tab_id = 849;

UPDATE ad_table
   SET description = 'Adempiere Alert'
 WHERE ad_table_id = 594;

UPDATE ad_task
   SET os_command = '@ADEMPIERE_HOME@@/@utils@/@RUN_DBExport.@bat@'
 WHERE ad_task_id = 103;

UPDATE ad_task
   SET os_command = '@ADEMPIERE_HOME@@/@utils@/@RUN_PutAdempiere.@sh@'
 WHERE ad_task_id = 104;

UPDATE ad_usermail
   SET mailtext =
          'Updated By: SuperUser
Date last action: 2005-05-17 22:03:48.0
Request Type: Request for Quotation -> Service Request
---------.----------.----------.----------.----------.----------
Could you please trim the trees for me?

---------.----------.----------.----------.----------.----------
Request: 10000002  [Req#100#ID]
Sent by AdempiereMail
'
 WHERE ad_usermail_id = 100;

UPDATE ad_wf_node
   SET HELP =
          'Visual representation of performance by color.  The Schema has often three levels (e.g. red-yellow-green).  Adempiere support two levels (e.g. red-green) or four levels (e.g. gray-bronce-silver-gold).  Note that Measures without a goal are represented white.  The percentages could be beween 0 and unlimited (i.e. above 100%).'
 WHERE ad_wf_node_id = 147;

UPDATE ad_window
   SET description =
          'LDAP Server to authenticate and authorize external systems based on Adempiere'
 WHERE ad_window_id = 389;

UPDATE ad_window
   SET description = 'Adempiere Alert'
 WHERE ad_window_id = 276;

UPDATE ad_window
   SET HELP =
          'Visual representation of performance by color.  The Schema has often three levels (e.g. red-yellow-green).  Adempiere support two levels (e.g. red-green) or four levels (e.g. gray-bronce-silver-gold).  Note that Measures without a goal are represented white.  The percentages could be beween 0 and unlimited (i.e. above 100%).'
 WHERE ad_window_id = 364;

UPDATE ad_window
   SET HELP =
          'The entity type determines the ownership of Application Dictionary entries.  The types "Dictionary" and "Adempiere" should not be used and are maintainted by Adempiere (i.e. all changes are reversed during migration to the current definition).'
 WHERE ad_window_id = 381;

UPDATE ad_window
   SET HELP =
          'System Issues are created to speed up the resolution of any system related issues (potential bugs).  If enabled, they are automatically reported to Adempiere.  No data or confidential information is transferred.'
 WHERE ad_window_id = 363;

UPDATE ad_window
   SET HELP =
          'The LDAP Server allows third party software (e.g. Apache) to use the users defined in the system to authentificate and authorize them.  There is only one server per Adempiere system.  The "o" is the Client key and the optional "ou" is the Interest Area key.'
 WHERE ad_window_id = 389;

UPDATE ad_window
   SET HELP =
          'A web counter maintains how often a page or item was displayed.  Add a call to
http://www.adempiere.com/wstore/counter to the page (e.g. display a 1x1 image)
'
 WHERE ad_window_id = 274;

UPDATE ad_window
   SET HELP =
          'Web Clicks allow you to track number of clicks.  Example:
&lt;a href="http://www.adempiere.com/wstore/click?http://www.adempiere.de" target="_blank"&gt; where
"http://www.adempiere.com/wstore/click" is your side and "http://www.adempiere.de" is the target page.
If you created a web click for http://www.adempiere.de, you will be able to see details and total of web clicks.
'
 WHERE ad_window_id = 273;

UPDATE ad_window
   SET HELP =
          'Adempiere Alerts allow you define system conditions you want to be alerted of'
 WHERE ad_window_id = 276;

UPDATE ad_workflow
   SET author = 'Adempiere, Inc.'
 WHERE ad_workflow_id = 117;

UPDATE ad_workflow
   SET author = 'Adempiere, Inc.'
 WHERE ad_workflow_id = 118;

UPDATE ad_workflow
   SET author = 'Adempiere, Inc.'
 WHERE ad_workflow_id = 119;

UPDATE ad_workflow
   SET author = 'Adempiere, Inc.'
 WHERE ad_workflow_id = 120;

UPDATE ad_workflow
   SET author = 'Adempiere, Inc.'
 WHERE ad_workflow_id = 121;

UPDATE ad_workflow
   SET author = 'Adempiere, Inc.'
 WHERE ad_workflow_id = 122;

UPDATE ad_workflow
   SET author = 'Adempiere, Inc.'
 WHERE ad_workflow_id = 123;

UPDATE ad_workflow
   SET author = 'Adempiere, Inc.'
 WHERE ad_workflow_id = 124;

UPDATE ad_workflow
   SET author = 'Adempiere, Inc.'
 WHERE ad_workflow_id = 125;

UPDATE ad_workflow
   SET author = 'Adempiere, Inc.'
 WHERE ad_workflow_id = 126;

UPDATE ad_workflow
   SET author = 'Adempiere, Inc.'
 WHERE ad_workflow_id = 127;

UPDATE ad_workflow
   SET author = 'Adempiere, Inc.'
 WHERE ad_workflow_id = 128;

UPDATE ad_workflow
   SET author = 'Adempiere, Inc.'
 WHERE ad_workflow_id = 129;

UPDATE ad_workflow
   SET author = 'Adempiere, Inc.'
 WHERE ad_workflow_id = 130;

UPDATE ad_workflow
   SET author = 'Adempiere, Inc.'
 WHERE ad_workflow_id = 131;

UPDATE ad_workflow
   SET author = 'Adempiere'
 WHERE ad_workflow_id = 101;

UPDATE ad_workflow
   SET author = 'Adempiere'
 WHERE ad_workflow_id = 103;

UPDATE ad_workflow
   SET author = 'Adempiere'
 WHERE ad_workflow_id = 104;

UPDATE ad_workflow
   SET author = 'Adempiere'
 WHERE ad_workflow_id = 105;

UPDATE ad_workflow
   SET author = 'Adempiere'
 WHERE ad_workflow_id = 106;

UPDATE ad_workflow
   SET author = 'Adempiere'
 WHERE ad_workflow_id = 107;

UPDATE ad_workflow
   SET author = 'Adempiere'
 WHERE ad_workflow_id = 108;

UPDATE ad_workflow
   SET author = 'Adempiere'
 WHERE ad_workflow_id = 109;

UPDATE ad_workflow
   SET author = 'Adempiere'
 WHERE ad_workflow_id = 110;

UPDATE ad_workflow
   SET author = 'Adempiere'
 WHERE ad_workflow_id = 111;

UPDATE ad_workflow
   SET author = 'Adempiere'
 WHERE ad_workflow_id = 112;

UPDATE ad_workflow
   SET author = 'Adempiere'
 WHERE ad_workflow_id = 113;

UPDATE ad_workflow
   SET author = 'Adempiere'
 WHERE ad_workflow_id = 114;

UPDATE ad_workflow
   SET author = 'Adempiere'
 WHERE ad_workflow_id = 115;

UPDATE ad_workflow
   SET author = 'Adempiere, Inc.'
 WHERE ad_workflow_id = 116;

UPDATE cm_container
   SET meta_keywords = 'GardenWorld, Adempiere'
 WHERE cm_container_id = 101;

UPDATE cm_cstage
   SET meta_keywords = 'GardenWorld, Adempiere'
 WHERE cm_cstage_id = 101;

UPDATE c_projecttype
   SET description = 'Presales Project to implement Adempiere'
 WHERE c_projecttype_id = 102;

UPDATE c_projecttype
   SET NAME = 'Adempiere Presales'
 WHERE c_projecttype_id = 102;

UPDATE c_task
   SET description = 'Installation of OS, Oracle and Adempiere'
 WHERE c_task_id = 102;

UPDATE m_productdownload
   SET downloadurl = 'http://www.adempiere.org/services/SupportContract.pdf'
 WHERE m_productdownload_id = 100;

UPDATE w_advertisement
   SET adtext =
          'Adempiere, Inc. privides Support and Services for Adempiere, the  premier Open Source business application.  Guaranteed <b>Support</b> is available in Gold/Silver/Bronze level you want to keep your mission critical system running and up-to-date.  All support offerings include all licenses you may need for Adempiere.  We offer Intensive <b>Training</b> to get you started fast end allow you to implement Adempiere efficiently. The Technical Training gives you a head start when extending Adempiere to meet specific business requiredments. <p>Some Unicode test:  <br> Some Extended Characters: ¿¿¿¿¿¿<br> European Characters: ¿¿¿-¿¿¿¿¿¿¿> Characters > 100h: ??<br> Greek: ?????<br> Cyrillic: ?????????<br> Arabic: ??????<br> If you see ? or squares in the above in the Java client, your database character set is not UTF.  '
 WHERE w_advertisement_id = 100;

UPDATE w_advertisement
   SET imageurl = 'http://www1.adempiere.com/images/AdempiereCom120x60.gif'
 WHERE w_advertisement_id = 100;

UPDATE w_advertisement
   SET NAME = 'Adempiere, Inc.'
 WHERE w_advertisement_id = 100;

UPDATE w_advertisement
   SET webparam1 = 'Adempiere Contributor'
 WHERE w_advertisement_id = 100;

UPDATE w_advertisement
   SET webparam2 = 'http://www.adempiere.com/images/partner_x.gif'
 WHERE w_advertisement_id = 100;

UPDATE w_advertisement
   SET webparam3 = 'Using Adempiere Internally'
 WHERE w_advertisement_id = 100;

UPDATE w_advertisement
   SET webparam4 =
          'Adempiere <a href="http://www1.adempiere.org/about/references.html">Success Stories</a>'
 WHERE w_advertisement_id = 100;

UPDATE w_clickcount
   SET NAME = 'Adempiere Click'
 WHERE w_clickcount_id = 100;

UPDATE w_clickcount
   SET targeturl = 'http://www.adempiere.org'
 WHERE w_clickcount_id = 100;

UPDATE w_countercount
   SET pageurl = 'http://www.adempiere.org/aboutus/index.html'
 WHERE w_countercount_id = 100;

COMMIT ;
