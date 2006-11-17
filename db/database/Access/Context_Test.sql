DECLARE
    string		VARCHAR2(60);
	no			NUMBER;
BEGIN
--	Adempiere_Context.Login('SuperUser','System','Friedenburg Mandant','GERGER');
--	Adempiere_Context.Login('SuperUser','System','System Administrator');
--	Adempiere_Context.Login('System','System','System Administrator');
	Adempiere_Context.Login('Adempiere','Internal','Server');
    --
	SELECT COUNT(*) INTO No FROM C_DocType;
    DBMS_OUTPUT.PUT_LINE('Count= ' || No);
END;

