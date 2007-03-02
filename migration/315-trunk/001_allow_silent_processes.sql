Insert into "AD_REF_LIST"
	("AD_REF_LIST_ID","AD_CLIENT_ID","AD_ORG_ID","ISACTIVE","CREATED","CREATEDBY",
	"UPDATED","UPDATEDBY","VALUE","NAME","DESCRIPTION","AD_REFERENCE_ID","VALIDFROM",
	"VALIDTO","ENTITYTYPE")
	values
	(50040,0,0,'Y',to_date('02.03.07','DD.MM.RR'),0,to_date('02.03.07','DD.MM.RR'),
	0,'S','Run silently - Take Defaults',null,50007,null,null,'D');

COMMIT;
