-- Jun 2, 2008 9:43:00 PM EST
-- Default comment for updating dictionary
UPDATE AD_Column SET FieldLength=22, IsUpdateable='N',Updated=TO_TIMESTAMP('2008-06-02 21:43:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=14866
;

-- Jun 2, 2008 9:43:14 PM EST
-- Default comment for updating dictionary
ALTER TABLE R_IssueRecommendation DROP COLUMN R_IssueRecommendation_ID
;
ALTER TABLE R_IssueRecommendation ADD COLUMN R_IssueRecommendation_ID NUMERIC(10) NOT NULL
;
