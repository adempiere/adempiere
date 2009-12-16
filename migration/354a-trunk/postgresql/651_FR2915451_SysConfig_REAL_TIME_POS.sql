-- Dec 16, 2009 10:33:55 AM CET
-- FR [2915451] - Add Sys Config variable: REAL_TIME_POS
-- https://sourceforge.net/tracker/?func=detail&aid=2915451&group_id=176962&atid=879335
INSERT INTO AD_SysConfig (AD_Client_ID,AD_Org_ID,AD_SysConfig_ID,ConfigurationLevel,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,50039,'O',TO_TIMESTAMP('2009-12-16 10:33:53','YYYY-MM-DD HH24:MI:SS'),100,'Enable Overwrite of Date on POS generated Invoice. When a POS Order is not completed immediately then on Completion ADempiere generated Invoice (Customer) with Date from the POS Order. In some cases this is not desired.','D','Y','REAL_TIME_POS',TO_TIMESTAMP('2009-12-16 10:33:53','YYYY-MM-DD HH24:MI:SS'),100,'N')
;

