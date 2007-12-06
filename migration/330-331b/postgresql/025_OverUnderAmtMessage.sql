INSERT INTO AD_MESSAGE
(AD_Message_ID, AD_Client_ID, AD_Org_ID, IsActive,
Created, CreatedBy,
Updated, UpdatedBy,
VALUE, MsgText, MsgType
)
VALUES (53006, 0, 0, 'Y',
TO_TIMESTAMP ('10/26/2007 01:01:50', 'MM/DD/YYYY HH24:MI:SS'), 100,
TO_TIMESTAMP ('10/26/2007 01:01:50', 'MM/DD/YYYY HH24:MI:SS'), 100,
'OverUnderAmt','Over/Under Payment','I'
);

UPDATE AD_SEQUENCE
SET CurrentNextSys = (SELECT MAX (AD_Message_ID) + 1
FROM AD_MESSAGE
WHERE AD_message_ID < 1000000)
WHERE NAME = 'AD_Message';

COMMIT;
