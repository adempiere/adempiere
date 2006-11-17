--	AD_Message Reorg --

--
--	Save Data *******
--
DROP TABLE AD_Message_Old CASCADE CONSTRAINTS
/
DROP TABLE AD_Message_Trl_Old CASCADE CONSTRAINTS
/
CREATE TABLE AD_Message_OLD
AS SELECT * FROM AD_Message ORDER BY AD_Message
/
CREATE TABLE AD_Message_Trl_OLD
AS SELECT * FROM AD_Message_Trl ORDER BY AD_Message
/
--	Delete Unused Column
ALTER TABLE AD_Val_Rule DROP COLUMN AD_Message
/

--
--	Recreate Tables ******
--
DROP TABLE AD_Message CASCADE CONSTRAINTS
/
DROP TABLE AD_Message_Trl CASCADE CONSTRAINTS
/
DROP TABLE AD_Note CASCADE CONSTRAINTS
/
-- 
-- TABLE: AD_Message 
--

CREATE TABLE AD_Message(
	AD_Message_ID	 NUMBER(10, 0)	   NOT NULL,
	AD_Client_ID	 NUMBER(10, 0)	   NOT NULL,
	AD_Org_ID		 NUMBER(10, 0)	   NOT NULL,
	IsActive		 CHAR(1)		   DEFAULT 'Y' NOT NULL
					 CHECK (IsActive in ('Y','N')),
	Created			 DATE			   DEFAULT SYSDATE NOT NULL,
	CreatedBy		 NUMBER(10, 0)	   NOT NULL,
	Updated			 DATE			   DEFAULT SYSDATE NOT NULL,
	UpdatedBy		 NUMBER(10, 0)	   NOT NULL,
	Value			 VARCHAR2(40)	   NOT NULL,
	MsgText			 VARCHAR2(2000)	   NOT NULL,
	MsgTip			 VARCHAR2(2000),
	MsgType			 CHAR(1)		   NOT NULL,
	CONSTRAINT AD_Message_Key PRIMARY KEY (AD_Message_ID)
) 
/


-- 
-- TABLE: AD_Message_Trl 
--

CREATE TABLE AD_Message_Trl(
	AD_Message_ID	 NUMBER(10, 0)	   NOT NULL,
	AD_Language		 VARCHAR2(6)	   NOT NULL,
	AD_Client_ID	 NUMBER(10, 0)	   NOT NULL,
	AD_Org_ID		 NUMBER(10, 0)	   NOT NULL,
	IsActive		 CHAR(1)		   DEFAULT 'Y' NOT NULL
					 CHECK (IsActive in ('Y','N')),
	Created			 DATE			   DEFAULT SYSDATE NOT NULL,
	CreatedBy		 NUMBER(10, 0)	   NOT NULL,
	Updated			 DATE			   DEFAULT SYSDATE NOT NULL,
	UpdatedBy		 NUMBER(10, 0)	   NOT NULL,
	MsgText			 VARCHAR2(2000)	   NOT NULL,
	MsgTip			 VARCHAR2(2000),
	IsTranslated	 CHAR(1)		   DEFAULT 'N' NOT NULL
					 CHECK (IsTranslated in ('Y','N')),
	CONSTRAINT AD_Message_Trl_Key PRIMARY KEY (AD_Message_ID, AD_Language)
) 
/


-- 
-- TABLE: AD_Note 
--

CREATE TABLE AD_Note(
	AD_Note_ID		 NUMBER(10, 0)	   NOT NULL,
	AD_Client_ID	 NUMBER(10, 0)	   NOT NULL,
	AD_Org_ID		 NUMBER(10, 0)	   NOT NULL,
	IsActive		 CHAR(1)		   DEFAULT 'Y' NOT NULL
					 CHECK (IsActive in ('Y','N')),
	Created			 DATE			   DEFAULT SYSDATE NOT NULL,
	CreatedBy		 NUMBER(10, 0)	   NOT NULL,
	Updated			 DATE			   DEFAULT SYSDATE NOT NULL,
	UpdatedBy		 NUMBER(10, 0)	   NOT NULL,
	AD_User_ID		 NUMBER(10, 0),
	AD_Message_ID	 NUMBER(10, 0)	   NOT NULL,
	Reference		 VARCHAR2(60),
	AD_Table_ID		 NUMBER(10, 0),
	Record_ID		 NUMBER(10, 0),
	Text			 VARCHAR2(2000),
	Processed		 CHAR(1)		   DEFAULT 'N' 
					 CHECK (Processed in ('Y','N')),
	CONSTRAINT AD_Note_Ley PRIMARY KEY (AD_Note_ID)
) 
/


COMMENT ON COLUMN AD_Note.AD_Table_ID IS 'AD_Table_ID Definition'
/
-- 
-- INDEX: AD_Message_Value 
--

CREATE UNIQUE INDEX AD_Message_Value ON AD_Message(Value)
/
-- 
-- TABLE: AD_Message 
--

ALTER TABLE AD_Message ADD CONSTRAINT MessageClient 
	FOREIGN KEY (AD_Client_ID)
	REFERENCES AD_Client(AD_Client_ID)
/

ALTER TABLE AD_Message ADD CONSTRAINT MessageOrg 
	FOREIGN KEY (AD_Org_ID)
	REFERENCES AD_Org(AD_Org_ID)
/


-- 
-- TABLE: AD_Message_Trl 
--

ALTER TABLE AD_Message_Trl ADD CONSTRAINT AD_Language_MessageTrl 
	FOREIGN KEY (AD_Language)
	REFERENCES AD_Language(AD_Language)
/

ALTER TABLE AD_Message_Trl ADD CONSTRAINT AD_MessageTrl 
	FOREIGN KEY (AD_Message_ID)
	REFERENCES AD_Message(AD_Message_ID) ON DELETE CASCADE
/


-- 
-- TABLE: AD_Note 
--

ALTER TABLE AD_Note ADD CONSTRAINT ADMessage_ADNote 
	FOREIGN KEY (AD_Message_ID)
	REFERENCES AD_Message(AD_Message_ID)
/

ALTER TABLE AD_Note ADD CONSTRAINT ADTable_ADNote 
	FOREIGN KEY (AD_Table_ID)
	REFERENCES AD_Table(AD_Table_ID)
/

ALTER TABLE AD_Note ADD CONSTRAINT ADUser_ADNote 
	FOREIGN KEY (AD_User_ID)
	REFERENCES AD_User(AD_User_ID)
/

--
--	Re-Populate Data
--
CREATE SEQUENCE TEMP_SEQ START WITH 100
/
UPDATE AD_Message_Old 
	SET MsgType='I'
WHERE MsgType IS NULL
/
INSERT INTO AD_MESSAGE
	(AD_MESSAGE_ID, AD_CLIENT_ID, AD_ORG_ID,
	ISACTIVE, CREATED, CREATEDBY, UPDATED, UPDATEDBY,
	VALUE, MSGTEXT, MSGTIP, MSGTYPE)
SELECT Temp_Seq.NextVal, AD_CLIENT_ID, AD_ORG_ID,
	ISACTIVE, CREATED, CREATEDBY, UPDATED, UPDATEDBY,
	AD_Message, MSGTEXT, MSGTIP, MSGTYPE
FROM AD_Message_Old
/
COMMIT
/
UPDATE AD_Message_Trl t
  SET (MSGTEXT, MSGTIP,IsTranslated) = 
	(SELECT o.MSGTEXT, o.MSGTIP, o.IsTransLated 
	FROM AD_Message_Trl_Old o, AD_Message m
	WHERE m.AD_MESSAGE_ID=t.AD_MESSAGE_ID
	AND m.Value=o.AD_Message
	AND t.AD_Language=o.AD_Language)
/
COMMIT
/
--
--	Cleanup
--
DROP SEQUENCE TEMP_SEQ
/
DROP TABLE AD_Message_Old CASCADE CONSTRAINTS
/
DROP TABLE AD_Message_Trl_Old CASCADE CONSTRAINTS
/

