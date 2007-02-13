insert into AD_MESSAGE (
	"AD_MESSAGE_ID","AD_CLIENT_ID","AD_ORG_ID","ISACTIVE","CREATED","CREATEDBY","UPDATED","UPDATEDBY",
	"VALUE","MSGTEXT","MSGTIP","MSGTYPE","ENTITYTYPE"
) 
values (
	50007,0,0,'Y',to_date('12-02-2007','DD-MM-RRRR'),0,to_date('12-02-2007','DD-MM-RRRR'),0,
	'Charset','Charset','Charset used for import / export','I','D'
);

commit;

-- Run sequence check
