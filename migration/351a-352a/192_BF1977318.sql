-- Jun 2, 2008 9:43:00 PM EST
-- Default comment for updating dictionary
UPDATE AD_COLUMN SET FieldLength=22, IsUpdateable='N',Updated=TO_DATE('2008-06-02 21:43:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=14866
;

-- Jun 2, 2008 9:43:14 PM EST
-- Default comment for updating dictionary
ALTER TABLE R_ISSUERECOMMENDATION MODIFY R_IssueRecommendation_ID NUMBER(10)
;

--ALTER TABLE R_ISSUERECOMMENDATION MODIFY R_IssueRecommendation_ID NOT NULL 
--;

--ALTER TABLE R_ISSUERECOMMENDATION ADD (CONSTRAINT r_issuerecommendation_pkey PRIMARY KEY (r_issuerecommendation_id))
--;

ALTER TABLE R_ISSUEKNOWN ADD (CONSTRAINT RIssueRecommendation_RIssueKno FOREIGN KEY (R_IssueRecommendation_ID) REFERENCES R_ISSUERECOMMENDATION)
;
