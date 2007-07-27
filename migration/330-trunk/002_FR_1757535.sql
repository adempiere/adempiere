--
-- [ 1757535 ] "Item is already on Bar" should be translated
-- http://sourceforge.net/tracker/index.php?func=detail&aid=1757535&group_id=176962&atid=879335
--
INSERT INTO AD_Message (
	AD_MESSAGE_ID,AD_CLIENT_ID,AD_ORG_ID,ISACTIVE,CREATED,CREATEDBY,UPDATED,UPDATEDBY,
	VALUE,MSGTEXT,MSGTIP,
	MSGTYPE,ENTITYTYPE
)
values (
	53002,0,0,'Y',to_date('2007-07-13 17:33:55', 'yyyy-mm-dd hh24.mi.ss'),0,to_date('2007-07-13 17:36:55', 'yyyy-mm-dd hh24.mi.ss'),0,
	'BookmarkExist','This Bookmark already exist','Selected Menu Item is already registered on Bookmarks Bar',
	'E','A'
);
commit;
-- 
-- NOTE: Don't forget to run: check sequence, add missing translations !!!
--

