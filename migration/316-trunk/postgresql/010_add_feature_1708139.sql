-- Feature Request - Allow rounding up of price list (schema) to nearest 5 or 9 unit of currency
-- http://sourceforge.net/tracker/index.php?func=detail&aid=1708139&group_id=176962&atid=879335

INSERT INTO AD_REF_LIST
	(ad_ref_list_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby,
		value, name, description, ad_reference_id, validfrom, validto, entitytype )
	VALUES (1000015, 0, 0, 'Y', TO_TIMESTAMP('2007-04-26','YYYY-MM-DD'),100, TO_TIMESTAMP('2007-04-26','YYYY-MM-DD'), 100,
		9, 'Ending in 9/5', 'The price ends in either a 5 or 9 whole unit',155,'','','D');

COMMIT;
