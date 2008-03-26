-- [ 1924645 ] Index ad_wf_activity_status too slow

DROP INDEX ad_wf_activity_status;

CREATE INDEX AD_WF_ACTIVITY_STATUS ON AD_WF_ACTIVITY(WFSTATE, PROCESSED);
