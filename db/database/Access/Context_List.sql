/**
 *	List Context
 */
select * from dba_context order by 1,2
/
select * from dba_policies order by 1,2
/
--	Context
SELECT SYS_CONTEXT('USERENV', 'CURRENT_USER') "Current User",
	SYS_CONTEXT('USERENV', 'CURRENT_USERID') "Current UserID",
	SYS_CONTEXT('USERENV', 'DB_NAME') "DB Name",
	SYS_CONTEXT('USERENV', 'LANGUAGE') "Language",
	SYS_CONTEXT('USERENV', 'SESSIONID') "Session ID",
	SYS_CONTEXT('USERENV', 'HOST') "Host",
	SYS_CONTEXT('USERENV', 'IP_ADDRESS') "IP Address" 
FROM DUAL
/
BEGIN
	DBMS_SESSION.SET_IDENTIFIER('A123');
END;
/
SELECT SYS_CONTEXT('USERENV', 'CLIENT_IDENTIFIER') FROM DUAL
/
