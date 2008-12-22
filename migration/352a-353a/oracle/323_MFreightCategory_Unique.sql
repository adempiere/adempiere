-- Make combination of AD_Client_ID and Value unique! 
ALTER TABLE M_FreightCategory
 ADD Constraint M_FreightCat_Value UNIQUE(AD_Client_ID, Value)
;