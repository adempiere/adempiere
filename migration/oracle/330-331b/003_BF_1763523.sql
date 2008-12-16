--
-- BF [ 1763523 ] Translate errors from Cash Line
-- http://sourceforge.net/tracker/index.php?func=detail&aid=1763523&group_id=176962&atid=879332
--
insert into AD_Message (
	AD_MESSAGE_ID,AD_CLIENT_ID,AD_ORG_ID,ISACTIVE,CREATED,CREATEDBY,UPDATED,UPDATEDBY,
	VALUE,MSGTEXT,MSGTIP,MSGTYPE,ENTITYTYPE)
values (
	53003,0,0,'Y',to_date('30-07-2007','DD-MM-RRRR'),0,to_date('30-07-2007','DD-MM-RRRR'),0,
	'CannotDeleteCashGenInvoice','Cannot delete line with generated Invoice',null,'E','D'
);
--
insert into AD_Message (
	AD_MESSAGE_ID,AD_CLIENT_ID,AD_ORG_ID,ISACTIVE,CREATED,CREATEDBY,UPDATED,UPDATEDBY,
	VALUE,MSGTEXT,MSGTIP,MSGTYPE,ENTITYTYPE)
values (
	53004,0,0,'Y',to_date('30-07-2007','DD-MM-RRRR'),0,to_date('30-07-2007','DD-MM-RRRR'),0,
	'CannotChangeCashGenInvoice','Cannot change line with generated Invoice',null,'E','D'
);
--
commit;
--
-- NOTE: don't forget to run add missing translations and sequence check
