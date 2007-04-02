Insert into "ad_ref_list"
	("ad_ref_list_id","ad_client_id","ad_org_id","isactive","created","createdby",
	"updated","updatedby","value","name","description","ad_reference_id","validfrom",
	"validto","entitytype")
	values
	(50040,0,0,'Y',to_date('02.03.07','DD.MM.RR'),0,to_date('02.03.07','DD.MM.RR'),
	0,'S','Run silently - Take Defaults',null,50007,null,null,'D');

COMMIT;
