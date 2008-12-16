INSERT INTO ad_sequence
            (ad_sequence_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME,
             description, isautosequence, incrementno, startno,
             currentnext, currentnextsys, isaudited, istableid, startnewyear
            )
     VALUES (50014, 0, 0, 'Y',
             TO_DATE ('02/28/2007 17:06:30', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 17:06:30', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'DocumentNo_R_InterestArea',
             'DocumentNo/Value for Table R_InterestArea', 'Y', 1, 1000000,
             1000000, 50000, 'N', 'N', 'N'
            );

INSERT INTO ad_sequence
            (ad_sequence_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME,
             description, isautosequence, incrementno, startno,
             currentnext, currentnextsys, isaudited, istableid, startnewyear
            )
     VALUES (50013, 0, 0, 'Y',
             TO_DATE ('02/28/2007 17:06:30', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 17:06:30', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'DocumentNo_AD_SysConfig',
             'DocumentNo/Value for Table AD_SysConfig', 'Y', 1, 1000000,
             1000000, 50000, 'N', 'N', 'N'
            );

INSERT INTO ad_sequence
            (ad_sequence_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME,
             description, isautosequence, incrementno, startno,
             currentnext, currentnextsys, isaudited, istableid, startnewyear
            )
     VALUES (50012, 11, 0, 'Y',
             TO_DATE ('02/28/2007 17:06:30', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 17:06:30', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'DocumentNo_AD_SysConfig',
             'DocumentNo/Value for Table AD_SysConfig', 'Y', 1, 1000000,
             1000000, 50000, 'N', 'N', 'N'
            );

INSERT INTO ad_sequence
            (ad_sequence_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, description, isautosequence,
             incrementno, startno, currentnext, currentnextsys, isaudited,
             istableid, startnewyear
            )
     VALUES (50011, 11, 0, 'Y',
             TO_DATE ('02/28/2007 17:06:30', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 17:06:30', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'DocumentNo_AD_User', 'DocumentNo/Value for Table AD_User', 'Y',
             1, 1000000, 1000000, 50000, 'N',
             'N', 'N'
            );

INSERT INTO ad_sequence
            (ad_sequence_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME,
             description, isautosequence, incrementno, startno,
             currentnext, currentnextsys, isaudited, istableid, startnewyear
            )
     VALUES (50010, 11, 0, 'Y',
             TO_DATE ('02/28/2007 17:06:30', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 17:06:30', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'DocumentNo_R_InterestArea',
             'DocumentNo/Value for Table R_InterestArea', 'Y', 1, 1000000,
             1000000, 50000, 'N', 'N', 'N'
            );

UPDATE ad_sequence
   SET currentnextsys = (SELECT MAX (ad_sequence_id) + 1
                           FROM ad_sequence
                          WHERE ad_sequence_id < 1000000)
 WHERE NAME = 'AD_Sequence';

COMMIT ;
