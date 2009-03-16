-- Mar 16, 2009 1:23:18 PM COT
UPDATE AD_Tab SET Help='Define Product Attribute Sets to add additional attributes and values to the product. You need to define an Attribute Set if you want to enable Serial and Lot Number and Guarantee Date tracking.  Note that the Guarantee Days here determine the Shelf Life of a product instance after manufacturing (the Guarantee Days on the product determines a Customer Service date after selling)
If the Attribute Set is mandatory, a product instance needs to be selected/created before shipping.',Updated=TO_TIMESTAMP('2009-03-16 13:23:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=461
;

-- Mar 16, 2009 1:23:18 PM COT
UPDATE AD_Tab_Trl SET IsTranslated='N' WHERE AD_Tab_ID=461
;

