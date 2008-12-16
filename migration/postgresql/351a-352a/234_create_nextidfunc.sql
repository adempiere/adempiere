-- missing function nextIDfunc
CREATE OR REPLACE FUNCTION nextIDfunc(AD_Sequence_ID INTEGER, System VARCHAR)
RETURNS INTEGER
AS 'org.compiere.sqlj.Adempiere.nextIDfunc(int,java.lang.String)'
LANGUAGE java VOLATILE;

ALTER FUNCTION nextIDfunc(AD_Sequence_ID INTEGER, System VARCHAR) OWNER TO adempiere;
