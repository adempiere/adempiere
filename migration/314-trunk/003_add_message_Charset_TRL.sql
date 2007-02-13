insert into AD_MESSAGE_TRL (
	"AD_MESSAGE_ID", "AD_LANGUAGE", "AD_CLIENT_ID","AD_ORG_ID","ISACTIVE","CREATED","CREATEDBY","UPDATED","UPDATEDBY",
	"MSGTEXT","MSGTIP","ISTRANSLATED"
) 
select
	m.AD_Message_ID, lang.AD_Language, m.AD_Client_ID, m.AD_Org_ID, 'Y', m.Created, m.CreatedBy, m.Updated, m.UpdatedBy,
	m.MsgText, m.MsgTip, 'N'
from AD_Message m, AD_Language lang
where 
	m.AD_Message_ID = 50007
	and lang.IsSystemLanguage='Y' and lang.IsBaseLanguage='N'
	and not exists (select * from AD_Message_TRL m2 where m2.AD_Message_ID=m.AD_Message_ID and m2.AD_Language=lang.AD_Language)
;

commit;
