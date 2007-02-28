UPDATE ad_process
   SET classname = NULL,
       procedurename = 'M_PriceList_Create'
 WHERE VALUE = 'M_PriceList Create';

UPDATE ad_process
   SET classname = NULL,
       procedurename = 'AD_PrintPaper_Default'
 WHERE VALUE = 'AD_PrintPaper_Default';

UPDATE ad_process
   SET classname = NULL,
       procedurename = 'M_Product_BOM_Check'
 WHERE VALUE = 'M_Product_BOM';

UPDATE ad_process
   SET classname = NULL,
       procedurename = 'M_Production_Run'
 WHERE VALUE = 'M_Production';
 
COMMIT;