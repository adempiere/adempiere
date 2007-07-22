
---
-- FEATURE: 1741222 - Add Post code lookup infrastructure
-- http://sourceforge.net/tracker/index.php?func=detail&aid=1741222&group_id=176962&atid=879335
-- Update additional fields per Carlos request

-- Update Element Definitions
UPDATE ad_element SET description = 'Does this country have a post code web service', help = 'Enable the IsPostcodeLookup if you wish to configure a post code lookup web service' WHERE ad_element_id = 51000;
UPDATE ad_element SET description = 'The class name of the postcode lookup plugin', help = 'Enter the class name of the post code lookup plugin for your postcode web service provider' WHERE ad_element_id = 51001;
UPDATE ad_element SET description = 'The ClientID or Login submitted to the Lookup URL', help ='Enter the ClientID or Login for your account provided by the post code web service provider' WHERE ad_element_id = 51002;
UPDATE ad_element SET description = 'The password submitted to the Lookup URL', help = 'Enter the password for your account provided by the post code web service provider' WHERE ad_element_id = 51004;
UPDATE ad_element SET description = 'The URL of the web service that the plugin connects to in order to retrieve postcode data', help = 'Enter the URL of the web service that the plugin connects to in order to retrieve postcode data' WHERE ad_element_id = 51003;

-- Update Column Definitions
UPDATE ad_column SET description = 'Does this country have a post code web service', help = 'Enable the IsPostcodeLookup if you wish to configure a post code lookup web service' WHERE ad_column_id = 51000;
UPDATE ad_column SET description = 'The class name of the postcode lookup plugin', help = 'Enter the class name of the post code lookup plugin for your postcode web service provider' WHERE ad_column_id = 51001;
UPDATE ad_column SET description = 'The ClientID or Login submitted to the Lookup URL', help = 'Enter the ClientID or Login for your account provided by the post code web service provider' WHERE ad_column_id = 51002; 
UPDATE ad_column SET description = 'The password submitted to the Lookup URL', help = 'Enter the password for your account provided by the post code web service provider' WHERE ad_column_id = 51004;
UPDATE ad_column SET description = 'The URL of the web service that the plugin connects to in order to retrieve postcode data', help = 'Enter the URL of the web service that the plugin connects to in order to retrieve postcode data' WHERE ad_column_id = 51003;  

-- Update Field Definitions
UPDATE ad_field SET seqno = 225 WHERE ad_field_id = 51000;

COMMIT;
