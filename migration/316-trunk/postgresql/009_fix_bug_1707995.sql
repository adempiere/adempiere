-- Bug Fix
-- http://sourceforge.net/tracker/index.php?func=detail&aid=1707995&group_id=176962&atid=879332

INSERT INTO AD_VAL_RULE (ad_val_rule_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, name, description, 
		 type, code, entitytype )
	VALUES(271, 0, 0, 'Y', TO_TIMESTAMP('2007-04-26','YYYY-MM-DD'), 100, TO_TIMESTAMP('2007-04-26','YYYY-MM-DD'), 100, 'M_PriceList is SO/PO', 'Limits the Sales & Purchase Order window to the correct price lsits',
		'S', '(M_PriceList.IsSOPriceList = ''Y'' AND ''@IsSOTrx@''=''Y'') OR (M_PriceList.IsSOPriceList = ''N'' AND ''@IsSOTrx@''=''N'')', 'D');

UPDATE AD_COLUMN set ad_val_rule_id = 271 where ad_column_id = 2204;


COMMIT;

UPDATE AD_SEQUENCE
   SET currentnextsys = (SELECT MAX (ad_val_rule_id) + 1
                           FROM AD_VAL_RULE
                          WHERE ad_val_rule_id < 1000000)
 WHERE NAME = 'AD_Val_Rule';

COMMIT;
