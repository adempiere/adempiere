update ad_element set entitytype = 'D' where entitytype='U' and AD_Element_id < 1000000;

update ad_field set entitytype = 'D' where entitytype='U' and AD_Field_id < 1000000;

update AD_Column set entitytype = 'D' where entitytype='U' and AD_Column_id < 1000000;

update AD_Val_Rule set entitytype = 'D' where entitytype='U' and AD_Val_Rule_id < 1000000;