SELECT DBMS_JAVA.LONGNAME(Object_Name) "Invalid", -- Object_Name "Name", 
	Object_Type "Type", Status "Status", TimeStamp "TimeStamp", Owner "Owner"
FROM ALL_Objects
WHERE Object_Type LIKE 'JAVA%' AND Status <> 'VALID'
ORDER BY 1;
--
SELECT DBMS_JAVA.LONGNAME(Object_Name) "LongName", -- Object_Name "Name", 
	Object_Type "Type", Status "Status", TimeStamp "TimeStamp", Owner "Owner"
FROM ALL_Objects
WHERE Object_Type LIKE 'JAVA%' AND Owner='ACCORTO'
ORDER BY 1;
--
SELECT * FROM JAVA$CLASS$MD5$Table
ORDER BY 1;
--
SELECT Name FROM CREATE$JAVA$LOB$Table;
--
