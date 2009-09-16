-- Aug 11, 2009 3:52:57 PM EEST
-- [2835006] must to reset t all -> you must reset all
UPDATE AD_Tab SET CommitWarning='If you change the currency or costing method, you must reset all accounting transactions.
For changes to become effective, you must re-login and re-start the Application Server. ',Updated=TO_TIMESTAMP('2009-08-11 15:52:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=199
;