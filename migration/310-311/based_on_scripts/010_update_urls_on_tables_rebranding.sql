UPDATE w_store
   SET webparam1 = 'ADempiere120x60.gif'
 WHERE w_store_id = 11;

UPDATE w_store_trl
   SET webparam1 = 'ADempiere120x60.gif'
 WHERE w_store_id = 11;

COMMIT ;

UPDATE m_product
   SET descriptionurl = 'http://www.adempiere.com/partner/index.html'
 WHERE descriptionurl = 'http://www.compiere.org/partner/index.html';

UPDATE m_product
   SET imageurl = 'http://www.adempiere.org/product/icons/C32.gif'
 WHERE imageurl = 'http://www.compiere.org/product/icons/C32.gif';

COMMIT ;