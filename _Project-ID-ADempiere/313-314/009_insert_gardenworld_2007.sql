INSERT INTO c_year
            (c_year_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             fiscalyear, c_calendar_id, processing
            )
     VALUES (50000, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:01:28', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:01:28', 'MM/DD/YYYY HH24:MI:SS'), 100,
             '2007', 102, 'N'
            );

UPDATE ad_sequence
   SET currentnextsys = (SELECT MAX (c_year_id) + 1
                           FROM c_year
                          WHERE c_year_id < 1000000)
 WHERE NAME = 'C_Year';

INSERT INTO c_period
            (c_period_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, periodno, c_year_id,
             startdate,
             enddate, periodtype,
             processing
            )
     VALUES (50000, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:09', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:09', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Jan-07', 1, 50000,
             TO_DATE ('01/01/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('01/31/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'S',
             'N'
            );

INSERT INTO c_period
            (c_period_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, periodno, c_year_id,
             startdate,
             enddate, periodtype,
             processing
            )
     VALUES (50001, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Feb-07', 2, 50000,
             TO_DATE ('02/01/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('02/28/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'S',
             'N'
            );

INSERT INTO c_period
            (c_period_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, periodno, c_year_id,
             startdate,
             enddate, periodtype,
             processing
            )
     VALUES (50002, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Mar-07', 3, 50000,
             TO_DATE ('03/01/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('03/31/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'S',
             'N'
            );

INSERT INTO c_period
            (c_period_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, periodno, c_year_id,
             startdate,
             enddate, periodtype,
             processing
            )
     VALUES (50003, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Apr-07', 4, 50000,
             TO_DATE ('04/01/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('04/30/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'S',
             'N'
            );

INSERT INTO c_period
            (c_period_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, periodno, c_year_id,
             startdate,
             enddate, periodtype,
             processing
            )
     VALUES (50004, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'May-07', 5, 50000,
             TO_DATE ('05/01/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('05/31/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'S',
             'N'
            );

INSERT INTO c_period
            (c_period_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, periodno, c_year_id,
             startdate,
             enddate, periodtype,
             processing
            )
     VALUES (50005, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Jun-07', 6, 50000,
             TO_DATE ('06/01/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('06/30/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'S',
             'N'
            );

INSERT INTO c_period
            (c_period_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, periodno, c_year_id,
             startdate,
             enddate, periodtype,
             processing
            )
     VALUES (50006, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Jul-07', 7, 50000,
             TO_DATE ('07/01/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('07/31/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'S',
             'N'
            );

INSERT INTO c_period
            (c_period_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, periodno, c_year_id,
             startdate,
             enddate, periodtype,
             processing
            )
     VALUES (50007, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Aug-07', 8, 50000,
             TO_DATE ('08/01/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('08/31/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'S',
             'N'
            );

INSERT INTO c_period
            (c_period_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, periodno, c_year_id,
             startdate,
             enddate, periodtype,
             processing
            )
     VALUES (50008, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Sep-07', 9, 50000,
             TO_DATE ('09/01/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('09/30/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'S',
             'N'
            );

INSERT INTO c_period
            (c_period_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, periodno, c_year_id,
             startdate,
             enddate, periodtype,
             processing
            )
     VALUES (50009, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Oct-07', 10, 50000,
             TO_DATE ('10/01/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('10/31/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'S',
             'N'
            );

INSERT INTO c_period
            (c_period_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, periodno, c_year_id,
             startdate,
             enddate, periodtype,
             processing
            )
     VALUES (50010, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Nov-07', 11, 50000,
             TO_DATE ('11/01/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('11/30/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'S',
             'N'
            );

INSERT INTO c_period
            (c_period_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, periodno, c_year_id,
             startdate,
             enddate, periodtype,
             processing
            )
     VALUES (50011, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Dec-07', 12, 50000,
             TO_DATE ('12/01/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('12/31/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'S',
             'N'
            );

UPDATE ad_sequence
   SET currentnextsys = (SELECT MAX (c_period_id) + 1
                           FROM c_period
                          WHERE c_period_id < 1000000)
 WHERE NAME = 'C_Period';

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50000, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:09', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:09', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50000, 'APC', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50001, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:09', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:09', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50000, 'API', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50002, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:09', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:09', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50000, 'APP', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50003, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:09', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:09', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50000, 'ARC', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50004, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:09', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:09', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50000, 'ARI', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50005, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:09', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:09', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50000, 'ARF', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50006, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:09', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:09', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50000, 'ARR', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50007, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:09', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:09', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50000, 'CMB', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50008, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:09', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:09', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50000, 'CMC', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50009, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:09', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:09', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50000, 'GLD', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50010, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:09', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:09', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50000, 'GLJ', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50011, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:09', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:09', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50000, 'MMR', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50012, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:09', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:09', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50000, 'MMS', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50013, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:09', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:09', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50000, 'MXI', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50014, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:09', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:09', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50000, 'MXP', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50015, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:09', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:09', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50000, 'MMM', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50016, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50000, 'MMI', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50017, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50000, 'MMP', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50018, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50000, 'SOO', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50019, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50000, 'CMA', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50020, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50000, 'PJI', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50021, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50000, 'POO', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50022, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50000, 'POR', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50023, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50001, 'APC', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50024, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50001, 'API', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50025, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50001, 'APP', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50026, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50001, 'ARC', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50027, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50001, 'ARI', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50028, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50001, 'ARF', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50029, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50001, 'ARR', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50030, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50001, 'CMB', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50031, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50001, 'CMC', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50032, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50001, 'GLD', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50033, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50001, 'GLJ', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50034, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50001, 'MMR', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50035, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50001, 'MMS', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50036, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50001, 'MXI', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50037, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50001, 'MXP', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50038, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50001, 'MMM', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50039, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50001, 'MMI', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50040, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50001, 'MMP', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50041, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50001, 'SOO', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50042, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50001, 'CMA', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50043, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50001, 'PJI', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50044, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50001, 'POO', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50045, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50001, 'POR', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50046, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50002, 'APC', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50047, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50002, 'API', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50048, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50002, 'APP', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50049, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50002, 'ARC', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50050, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50002, 'ARI', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50051, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50002, 'ARF', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50052, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50002, 'ARR', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50053, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50002, 'CMB', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50054, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50002, 'CMC', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50055, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50002, 'GLD', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50056, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50002, 'GLJ', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50057, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50002, 'MMR', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50058, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50002, 'MMS', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50059, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50002, 'MXI', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50060, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50002, 'MXP', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50061, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50002, 'MMM', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50062, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50002, 'MMI', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50063, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50002, 'MMP', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50064, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50002, 'SOO', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50065, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50002, 'CMA', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50066, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50002, 'PJI', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50067, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50002, 'POO', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50068, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50002, 'POR', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50069, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50003, 'APC', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50070, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50003, 'API', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50071, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50003, 'APP', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50072, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50003, 'ARC', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50073, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50003, 'ARI', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50074, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50003, 'ARF', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50075, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50003, 'ARR', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50076, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50003, 'CMB', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50077, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50003, 'CMC', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50078, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50003, 'GLD', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50079, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50003, 'GLJ', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50080, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50003, 'MMR', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50081, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50003, 'MMS', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50082, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50003, 'MXI', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50083, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50003, 'MXP', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50084, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50003, 'MMM', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50085, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50003, 'MMI', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50086, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50003, 'MMP', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50087, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50003, 'SOO', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50088, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50003, 'CMA', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50089, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50003, 'PJI', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50090, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50003, 'POO', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50091, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50003, 'POR', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50092, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50004, 'APC', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50093, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50004, 'API', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50094, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50004, 'APP', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50095, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50004, 'ARC', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50096, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50004, 'ARI', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50097, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50004, 'ARF', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50098, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50004, 'ARR', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50099, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50004, 'CMB', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50100, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50004, 'CMC', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50101, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50004, 'GLD', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50102, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50004, 'GLJ', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50103, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50004, 'MMR', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50104, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50004, 'MMS', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50105, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50004, 'MXI', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50106, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50004, 'MXP', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50107, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50004, 'MMM', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50108, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50004, 'MMI', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50109, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50004, 'MMP', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50110, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50004, 'SOO', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50111, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50004, 'CMA', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50112, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50004, 'PJI', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50113, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50004, 'POO', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50114, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50004, 'POR', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50115, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50005, 'APC', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50116, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50005, 'API', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50117, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50005, 'APP', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50118, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50005, 'ARC', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50119, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50005, 'ARI', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50120, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50005, 'ARF', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50121, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50005, 'ARR', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50122, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50005, 'CMB', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50123, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50005, 'CMC', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50124, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50005, 'GLD', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50125, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50005, 'GLJ', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50126, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50005, 'MMR', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50127, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50005, 'MMS', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50128, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50005, 'MXI', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50129, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50005, 'MXP', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50130, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50005, 'MMM', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50131, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50005, 'MMI', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50132, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50005, 'MMP', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50133, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50005, 'SOO', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50134, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50005, 'CMA', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50135, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50005, 'PJI', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50136, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50005, 'POO', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50137, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50005, 'POR', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50138, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50006, 'APC', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50139, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50006, 'API', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50140, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50006, 'APP', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50141, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50006, 'ARC', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50142, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50006, 'ARI', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50143, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50006, 'ARF', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50144, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50006, 'ARR', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50145, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50006, 'CMB', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50146, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50006, 'CMC', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50147, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50006, 'GLD', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50148, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50006, 'GLJ', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50149, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50006, 'MMR', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50150, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50006, 'MMS', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50151, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50006, 'MXI', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50152, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50006, 'MXP', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50153, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50006, 'MMM', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50154, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50006, 'MMI', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50155, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50006, 'MMP', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50156, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50006, 'SOO', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50157, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50006, 'CMA', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50158, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50006, 'PJI', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50159, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50006, 'POO', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50160, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50006, 'POR', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50161, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50007, 'APC', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50162, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50007, 'API', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50163, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50007, 'APP', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50164, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50007, 'ARC', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50165, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50007, 'ARI', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50166, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50007, 'ARF', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50167, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50007, 'ARR', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50168, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50007, 'CMB', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50169, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50007, 'CMC', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50170, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50007, 'GLD', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50171, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50007, 'GLJ', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50172, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50007, 'MMR', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50173, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50007, 'MMS', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50174, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50007, 'MXI', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50175, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50007, 'MXP', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50176, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50007, 'MMM', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50177, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50007, 'MMI', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50178, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50007, 'MMP', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50179, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50007, 'SOO', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50180, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50007, 'CMA', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50181, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50007, 'PJI', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50182, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50007, 'POO', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50183, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50007, 'POR', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50184, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50008, 'APC', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50185, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50008, 'API', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50186, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50008, 'APP', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50187, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50008, 'ARC', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50188, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50008, 'ARI', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50189, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50008, 'ARF', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50190, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50008, 'ARR', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50191, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50008, 'CMB', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50192, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50008, 'CMC', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50193, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50008, 'GLD', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50194, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50008, 'GLJ', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50195, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50008, 'MMR', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50196, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50008, 'MMS', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50197, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50008, 'MXI', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50198, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50008, 'MXP', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50199, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50008, 'MMM', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50200, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50008, 'MMI', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50201, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50008, 'MMP', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50202, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50008, 'SOO', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50203, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50008, 'CMA', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50204, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50008, 'PJI', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50205, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50008, 'POO', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50206, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50008, 'POR', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50207, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50009, 'APC', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50208, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50009, 'API', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50209, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50009, 'APP', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50210, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50009, 'ARC', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50211, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50009, 'ARI', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50212, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50009, 'ARF', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50213, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50009, 'ARR', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50214, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50009, 'CMB', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50215, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50009, 'CMC', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50216, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50009, 'GLD', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50217, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50009, 'GLJ', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50218, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50009, 'MMR', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50219, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50009, 'MMS', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50220, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50009, 'MXI', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50221, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50009, 'MXP', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50222, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50009, 'MMM', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50223, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50009, 'MMI', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50224, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50009, 'MMP', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50225, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50009, 'SOO', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50226, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50009, 'CMA', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50227, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50009, 'PJI', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50228, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50009, 'POO', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50229, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50009, 'POR', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50230, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50010, 'APC', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50231, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50010, 'API', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50232, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50010, 'APP', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50233, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50010, 'ARC', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50234, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50010, 'ARI', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50235, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50010, 'ARF', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50236, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50010, 'ARR', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50237, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50010, 'CMB', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50238, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50010, 'CMC', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50239, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50010, 'GLD', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50240, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50010, 'GLJ', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50241, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50010, 'MMR', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50242, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50010, 'MMS', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50243, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50010, 'MXI', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50244, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50010, 'MXP', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50245, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50010, 'MMM', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50246, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50010, 'MMI', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50247, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50010, 'MMP', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50248, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50010, 'SOO', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50249, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50010, 'CMA', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50250, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50010, 'PJI', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50251, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50010, 'POO', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50252, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50010, 'POR', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50253, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:17', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:17', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50011, 'APC', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50254, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:17', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:17', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50011, 'API', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50255, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:17', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:17', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50011, 'APP', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50256, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:17', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:17', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50011, 'ARC', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50257, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:17', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:17', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50011, 'ARI', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50258, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:17', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:17', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50011, 'ARF', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50259, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:17', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:17', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50011, 'ARR', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50260, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:17', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:17', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50011, 'CMB', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50261, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:17', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:17', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50011, 'CMC', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50262, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:17', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:17', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50011, 'GLD', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50263, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:17', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:17', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50011, 'GLJ', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50264, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:17', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:17', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50011, 'MMR', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50265, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:17', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:17', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50011, 'MMS', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50266, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:17', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:17', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50011, 'MXI', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50267, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:17', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:17', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50011, 'MXP', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50268, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:17', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:17', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50011, 'MMM', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50269, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:17', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:17', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50011, 'MMI', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50270, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:17', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:17', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50011, 'MMP', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50271, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:17', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:17', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50011, 'SOO', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50272, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:17', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:17', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50011, 'CMA', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50273, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:17', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:17', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50011, 'PJI', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50274, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:17', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:17', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50011, 'POO', 'N', 'N', 'N'
            );

INSERT INTO c_periodcontrol
            (c_periodcontrol_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             c_period_id, docbasetype, periodstatus, periodaction, processing
            )
     VALUES (50275, 11, 0, 'Y',
             TO_DATE ('01/24/2007 00:04:17', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:04:17', 'MM/DD/YYYY HH24:MI:SS'), 100,
             50011, 'POR', 'N', 'N', 'N'
            );

UPDATE ad_sequence
   SET currentnextsys = (SELECT MAX (c_periodcontrol_id) + 1
                           FROM c_periodcontrol
                          WHERE c_periodcontrol_id < 1000000)
 WHERE NAME = 'C_PeriodControl';

COMMIT ;
