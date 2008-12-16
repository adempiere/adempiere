INSERT INTO AD_Message
(AD_Message_ID, AD_Client_ID, AD_Org_ID, IsActive,
Created, CreatedBy,
Updated, UpdatedBy,
Value, MsgText, MsgType
)
VALUES (53005, 0, 0, 'Y',
TO_DATE ('10/10/2007 14:00:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
TO_DATE ('10/10/2007 14:00:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
'QueryName','Query Name','I'
);

UPDATE AD_Sequence
SET CurrentNextSys = (SELECT MAX (AD_Message_ID) + 1
FROM AD_Message
WHERE AD_message_ID < 1000000)
WHERE NAME = 'AD_Message';

COMMIT;
