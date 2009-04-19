-- Apr 19, 2009 8:25:02 AM EEST
-- Replication stabilization
INSERT INTO EXP_Format (Processing,EXP_Format_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,Value,Name,AD_Table_ID,Version) VALUES ('N',50000,0,0,'Y',TO_DATE('2009-04-19 08:24:57','YYYY-MM-DD HH24:MI:SS'),100,TO_DATE('2009-04-19 08:24:57','YYYY-MM-DD HH24:MI:SS'),100,'Client_Value','Client Value',112,'3.2.0')
;

-- Apr 19, 2009 8:25:07 AM EEST
-- Replication stabilization
INSERT INTO EXP_FormatLine (EXP_Format_ID,EXP_FormatLine_ID,AD_Client_ID,IsActive,Created,Updated,Value,Name,Position,IsPartUniqueIndex,AD_Org_ID,CreatedBy,UpdatedBy,IsMandatory,Type,AD_Column_ID) VALUES (50000,50000,0,'Y',TO_DATE('2009-04-19 08:25:04','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2009-04-19 08:25:04','YYYY-MM-DD HH24:MI:SS'),'AD_Client_Value','AD_Client_Value',10,'Y',0,100,100,'N','E',4773)
;

-- Apr 19, 2009 8:25:09 AM EEST
-- Replication stabilization
INSERT INTO EXP_Format (Processing,EXP_Format_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,Value,Name,AD_Table_ID,Version) VALUES ('N',50001,0,0,'Y',TO_DATE('2009-04-19 08:25:08','YYYY-MM-DD HH24:MI:SS'),100,TO_DATE('2009-04-19 08:25:08','YYYY-MM-DD HH24:MI:SS'),100,'Org_Value','Organization',155,'3.2.0')
;

-- Apr 19, 2009 8:25:09 AM EEST
-- Replication stabilization
INSERT INTO EXP_FormatLine (EXP_Format_ID,EXP_FormatLine_ID,AD_Client_ID,IsActive,Created,Updated,Value,Name,Position,IsPartUniqueIndex,AD_Org_ID,CreatedBy,UpdatedBy,IsMandatory,Type,AD_Column_ID) VALUES (50001,50001,0,'Y',TO_DATE('2009-04-19 08:25:09','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2009-04-19 08:25:09','YYYY-MM-DD HH24:MI:SS'),'Value','Value',10,'Y',0,100,100,'N','E',2045)
;

-- Apr 19, 2009 8:25:10 AM EEST
-- Replication stabilization
INSERT INTO EXP_FormatLine (EXP_Format_ID,EXP_FormatLine_ID,AD_Client_ID,IsActive,Created,Updated,Value,Name,Position,IsPartUniqueIndex,AD_Org_ID,CreatedBy,UpdatedBy,IsMandatory,Type,AD_Column_ID,EXP_EmbeddedFormat_ID) VALUES (50001,50002,0,'Y',TO_DATE('2009-04-19 08:25:10','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2009-04-19 08:25:10','YYYY-MM-DD HH24:MI:SS'),'AD_Client_ID','Client',20,'Y',0,100,100,'N','R',527,50000)
;

-- Apr 19, 2009 8:25:11 AM EEST
-- Replication stabilization
INSERT INTO EXP_Format (Processing,EXP_Format_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,Value,Name,AD_Table_ID,Version) VALUES ('N',50002,0,0,'Y',TO_DATE('2009-04-19 08:25:11','YYYY-MM-DD HH24:MI:SS'),100,TO_DATE('2009-04-19 08:25:11','YYYY-MM-DD HH24:MI:SS'),100,'User_Name','User',114,'3.2.0')
;

-- Apr 19, 2009 8:25:12 AM EEST
-- Replication stabilization
INSERT INTO EXP_FormatLine (EXP_Format_ID,EXP_FormatLine_ID,AD_Client_ID,IsActive,Created,Updated,Value,Name,Position,IsPartUniqueIndex,AD_Org_ID,CreatedBy,UpdatedBy,IsMandatory,Type,AD_Column_ID) VALUES (50002,50003,0,'Y',TO_DATE('2009-04-19 08:25:12','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2009-04-19 08:25:12','YYYY-MM-DD HH24:MI:SS'),'Name','Name',20,'Y',0,100,100,'Y','E',213)
;

-- Apr 19, 2009 8:25:13 AM EEST
-- Replication stabilization
INSERT INTO EXP_FormatLine (EXP_Format_ID,EXP_FormatLine_ID,AD_Client_ID,IsActive,Created,Updated,Value,Name,Position,IsPartUniqueIndex,AD_Org_ID,CreatedBy,UpdatedBy,IsMandatory,Type,AD_Column_ID,EXP_EmbeddedFormat_ID) VALUES (50002,50004,0,'Y',TO_DATE('2009-04-19 08:25:13','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2009-04-19 08:25:13','YYYY-MM-DD HH24:MI:SS'),'AD_Client_ID','Client key',30,'Y',0,100,100,'Y','R',422,50000)
;

-- Apr 19, 2009 8:25:14 AM EEST
-- Replication stabilization
INSERT INTO EXP_Format (Processing,EXP_Format_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,Value,Name,AD_Table_ID,Version) VALUES ('N',50003,0,0,'Y',TO_DATE('2009-04-19 08:25:13','YYYY-MM-DD HH24:MI:SS'),100,TO_DATE('2009-04-19 08:25:13','YYYY-MM-DD HH24:MI:SS'),100,'C_BP_Group-Key','Business Partner Group',394,'3.2.0')
;

-- Apr 19, 2009 8:25:15 AM EEST
-- Replication stabilization
INSERT INTO EXP_FormatLine (EXP_Format_ID,EXP_FormatLine_ID,AD_Client_ID,IsActive,Created,Updated,Value,Name,Position,IsPartUniqueIndex,AD_Org_ID,CreatedBy,UpdatedBy,IsMandatory,Type,AD_Column_ID) VALUES (50003,50005,0,'Y',TO_DATE('2009-04-19 08:25:15','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2009-04-19 08:25:15','YYYY-MM-DD HH24:MI:SS'),'Value','Value',10,'Y',0,100,100,'Y','E',4969)
;

-- Apr 19, 2009 8:25:16 AM EEST
-- Replication stabilization
INSERT INTO EXP_FormatLine (EXP_Format_ID,EXP_FormatLine_ID,AD_Client_ID,IsActive,Created,Updated,Value,Name,Position,IsPartUniqueIndex,AD_Org_ID,CreatedBy,UpdatedBy,IsMandatory,Type,AD_Column_ID,EXP_EmbeddedFormat_ID) VALUES (50003,50006,0,'Y',TO_DATE('2009-04-19 08:25:15','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2009-04-19 08:25:15','YYYY-MM-DD HH24:MI:SS'),'AD_Client_ID','Client key',30,'Y',0,100,100,'Y','R',4962,50000)
;

-- Apr 19, 2009 8:25:17 AM EEST
-- Replication stabilization
INSERT INTO EXP_Format (Processing,EXP_Format_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,Value,Name,AD_Table_ID,Version) VALUES ('N',50004,0,0,'Y',TO_DATE('2009-04-19 08:25:16','YYYY-MM-DD HH24:MI:SS'),100,TO_DATE('2009-04-19 08:25:16','YYYY-MM-DD HH24:MI:SS'),100,'C_Order-Key','Order key',259,'3.2.0')
;

-- Apr 19, 2009 8:25:18 AM EEST
-- Replication stabilization
INSERT INTO EXP_FormatLine (EXP_Format_ID,EXP_FormatLine_ID,AD_Client_ID,IsActive,Created,Updated,Value,Name,Position,IsPartUniqueIndex,AD_Org_ID,CreatedBy,UpdatedBy,IsMandatory,Type,AD_Column_ID) VALUES (50004,50007,0,'Y',TO_DATE('2009-04-19 08:25:18','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2009-04-19 08:25:18','YYYY-MM-DD HH24:MI:SS'),'DocumentNo','Document number',10,'Y',0,100,100,'N','E',2169)
;

-- Apr 19, 2009 8:25:19 AM EEST
-- Replication stabilization
INSERT INTO EXP_FormatLine (EXP_Format_ID,EXP_FormatLine_ID,AD_Client_ID,IsActive,Created,Updated,Value,Name,Position,IsPartUniqueIndex,AD_Org_ID,CreatedBy,UpdatedBy,IsMandatory,Type,AD_Column_ID) VALUES (50004,50008,0,'Y',TO_DATE('2009-04-19 08:25:18','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2009-04-19 08:25:18','YYYY-MM-DD HH24:MI:SS'),'C_DocType_ID','Document Type',20,'Y',0,100,100,'Y','E',2172)
;

-- Apr 19, 2009 8:25:20 AM EEST
-- Replication stabilization
INSERT INTO EXP_FormatLine (EXP_Format_ID,EXP_FormatLine_ID,AD_Client_ID,IsActive,Created,Updated,Value,Name,Position,IsPartUniqueIndex,AD_Org_ID,CreatedBy,UpdatedBy,IsMandatory,Type,AD_Column_ID) VALUES (50004,50009,0,'Y',TO_DATE('2009-04-19 08:25:19','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2009-04-19 08:25:19','YYYY-MM-DD HH24:MI:SS'),'C_BPartner_ID','Business Partner',30,'Y',0,100,100,'Y','E',2762)
;

-- Apr 19, 2009 8:25:21 AM EEST
-- Replication stabilization
INSERT INTO EXP_Format (Processing,EXP_Format_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,Value,Name,AD_Table_ID,Version) VALUES ('N',50005,0,0,'Y',TO_DATE('2009-04-19 08:25:21','YYYY-MM-DD HH24:MI:SS'),100,TO_DATE('2009-04-19 08:25:21','YYYY-MM-DD HH24:MI:SS'),100,'C_Order','Order',259,'3.2.0')
;

-- Apr 19, 2009 8:25:22 AM EEST
-- Replication stabilization
INSERT INTO EXP_FormatLine (EXP_Format_ID,EXP_FormatLine_ID,AD_Client_ID,IsActive,Created,Updated,Value,Name,Position,IsPartUniqueIndex,AD_Org_ID,CreatedBy,UpdatedBy,IsMandatory,Type,AD_Column_ID,EXP_EmbeddedFormat_ID) VALUES (50005,50010,0,'Y',TO_DATE('2009-04-19 08:25:21','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2009-04-19 08:25:21','YYYY-MM-DD HH24:MI:SS'),'AD_Client_ID','Client',10,'N',0,100,100,'Y','R',2162,50000)
;

-- Apr 19, 2009 8:25:23 AM EEST
-- Replication stabilization
INSERT INTO EXP_FormatLine (EXP_Format_ID,EXP_FormatLine_ID,AD_Client_ID,IsActive,Created,Updated,Value,Name,Position,IsPartUniqueIndex,AD_Org_ID,CreatedBy,UpdatedBy,IsMandatory,Type,AD_Column_ID,EXP_EmbeddedFormat_ID) VALUES (50005,50011,0,'Y',TO_DATE('2009-04-19 08:25:22','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2009-04-19 08:25:22','YYYY-MM-DD HH24:MI:SS'),'AD_Org_ID','Organization',20,'N',0,100,100,'Y','R',2163,50001)
;

-- Apr 19, 2009 8:25:24 AM EEST
-- Replication stabilization
INSERT INTO EXP_FormatLine (EXP_Format_ID,EXP_FormatLine_ID,AD_Client_ID,IsActive,Created,Updated,Value,Name,Position,IsPartUniqueIndex,AD_Org_ID,CreatedBy,UpdatedBy,IsMandatory,Type,AD_Column_ID) VALUES (50005,50012,0,'Y',TO_DATE('2009-04-19 08:25:23','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2009-04-19 08:25:23','YYYY-MM-DD HH24:MI:SS'),'DocumentNo','Document number',30,'Y',0,100,100,'Y','E',2169)
;

-- Apr 19, 2009 8:25:25 AM EEST
-- Replication stabilization
INSERT INTO EXP_FormatLine (EXP_Format_ID,EXP_FormatLine_ID,AD_Client_ID,IsActive,Created,Updated,Value,Name,Position,IsPartUniqueIndex,AD_Org_ID,CreatedBy,UpdatedBy,IsMandatory,Type,AD_Column_ID,DateFormat) VALUES (50005,50013,0,'Y',TO_DATE('2009-04-19 08:25:24','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2009-04-19 08:25:24','YYYY-MM-DD HH24:MI:SS'),'Created','Date Created',40,'N',0,100,100,'Y','E',2165,'MM/dd/yyyy hh:mm:ss')
;

-- Apr 19, 2009 8:25:26 AM EEST
-- Replication stabilization
INSERT INTO EXP_FormatLine (EXP_Format_ID,EXP_FormatLine_ID,AD_Client_ID,IsActive,Created,Updated,Value,Name,Position,IsPartUniqueIndex,AD_Org_ID,CreatedBy,UpdatedBy,IsMandatory,Type,AD_Column_ID,DateFormat) VALUES (50005,50014,0,'Y',TO_DATE('2009-04-19 08:25:25','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2009-04-19 08:25:25','YYYY-MM-DD HH24:MI:SS'),'Updated','Date Updated',50,'N',0,100,100,'Y','E',2167,'MM/dd/yyyy hh:mm:ss')
;

-- Apr 19, 2009 8:25:27 AM EEST
-- Replication stabilization
INSERT INTO EXP_FormatLine (EXP_Format_ID,EXP_FormatLine_ID,AD_Client_ID,IsActive,Created,Updated,Value,Name,Position,IsPartUniqueIndex,AD_Org_ID,CreatedBy,UpdatedBy,IsMandatory,Type,AD_Column_ID,EXP_EmbeddedFormat_ID) VALUES (50005,50015,0,'Y',TO_DATE('2009-04-19 08:25:26','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2009-04-19 08:25:26','YYYY-MM-DD HH24:MI:SS'),'CreatedBy','Created By',60,'N',0,100,100,'Y','R',2166,50002)
;

-- Apr 19, 2009 8:25:28 AM EEST
-- Replication stabilization
INSERT INTO EXP_FormatLine (EXP_Format_ID,EXP_FormatLine_ID,AD_Client_ID,IsActive,Created,Updated,Value,Name,Position,IsPartUniqueIndex,AD_Org_ID,CreatedBy,UpdatedBy,IsMandatory,Type,AD_Column_ID,EXP_EmbeddedFormat_ID) VALUES (50005,50016,0,'Y',TO_DATE('2009-04-19 08:25:27','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2009-04-19 08:25:27','YYYY-MM-DD HH24:MI:SS'),'UpdatedBy','Updated By',70,'N',0,100,100,'Y','R',2168,50002)
;

-- Apr 19, 2009 8:25:29 AM EEST
-- Replication stabilization
INSERT INTO EXP_FormatLine (EXP_Format_ID,EXP_FormatLine_ID,AD_Client_ID,IsActive,Created,Updated,Value,Name,Position,IsPartUniqueIndex,AD_Org_ID,CreatedBy,UpdatedBy,IsMandatory,Type,AD_Column_ID) VALUES (50005,50017,0,'Y',TO_DATE('2009-04-19 08:25:28','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2009-04-19 08:25:28','YYYY-MM-DD HH24:MI:SS'),'GrandTotal','Grand Total',80,'N',0,100,100,'N','E',2201)
;

-- Apr 19, 2009 8:25:30 AM EEST
-- Replication stabilization
INSERT INTO EXP_FormatLine (EXP_Format_ID,EXP_FormatLine_ID,AD_Client_ID,IsActive,Created,Updated,Value,Name,Position,IsPartUniqueIndex,AD_Org_ID,CreatedBy,UpdatedBy,IsMandatory,Type) VALUES (50005,50018,0,'Y',TO_DATE('2009-04-19 08:25:29','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2009-04-19 08:25:29','YYYY-MM-DD HH24:MI:SS'),'C_OrderLine','Order Line',90,'N',0,100,100,'N','M')
;

-- Apr 19, 2009 8:25:30 AM EEST
-- Replication stabilization
INSERT INTO EXP_FormatLine (EXP_Format_ID,EXP_FormatLine_ID,AD_Client_ID,IsActive,Created,Updated,Value,Name,Position,IsPartUniqueIndex,AD_Org_ID,CreatedBy,UpdatedBy,IsMandatory,Type,AD_Column_ID) VALUES (50005,50019,0,'Y',TO_DATE('2009-04-19 08:25:30','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2009-04-19 08:25:30','YYYY-MM-DD HH24:MI:SS'),'C_DocType_ID','Document Type',100,'Y',0,100,100,'Y','E',2172)
;

-- Apr 19, 2009 8:25:31 AM EEST
-- Replication stabilization
INSERT INTO EXP_FormatLine (EXP_Format_ID,EXP_FormatLine_ID,AD_Client_ID,IsActive,Created,Updated,Value,Name,Position,IsPartUniqueIndex,AD_Org_ID,CreatedBy,UpdatedBy,IsMandatory,Type,AD_Column_ID) VALUES (50005,50020,0,'Y',TO_DATE('2009-04-19 08:25:30','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2009-04-19 08:25:30','YYYY-MM-DD HH24:MI:SS'),'C_BPartner_ID','Business Partner',110,'Y',0,100,100,'Y','E',2762)
;

-- Apr 19, 2009 8:25:32 AM EEST
-- Replication stabilization
INSERT INTO EXP_Format (Processing,EXP_Format_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,Value,Name,AD_Table_ID,Version) VALUES ('N',50006,0,0,'Y',TO_DATE('2009-04-19 08:25:31','YYYY-MM-DD HH24:MI:SS'),100,TO_DATE('2009-04-19 08:25:31','YYYY-MM-DD HH24:MI:SS'),100,'C_OrderLine','Order Line',260,'3.2.0')
;

-- Apr 19, 2009 8:25:33 AM EEST
-- Replication stabilization
INSERT INTO EXP_FormatLine (EXP_Format_ID,EXP_FormatLine_ID,AD_Client_ID,IsActive,Created,Updated,Value,Name,Position,IsPartUniqueIndex,AD_Org_ID,CreatedBy,UpdatedBy,IsMandatory,Type,AD_Column_ID,EXP_EmbeddedFormat_ID) VALUES (50006,50021,0,'Y',TO_DATE('2009-04-19 08:25:32','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2009-04-19 08:25:32','YYYY-MM-DD HH24:MI:SS'),'AD_Client_ID','Client',10,'N',0,100,100,'Y','R',2206,50000)
;

-- Apr 19, 2009 8:25:34 AM EEST
-- Replication stabilization
INSERT INTO EXP_FormatLine (EXP_Format_ID,EXP_FormatLine_ID,AD_Client_ID,IsActive,Created,Updated,Value,Name,Position,IsPartUniqueIndex,AD_Org_ID,CreatedBy,UpdatedBy,IsMandatory,Type,AD_Column_ID,EXP_EmbeddedFormat_ID) VALUES (50006,50022,0,'Y',TO_DATE('2009-04-19 08:25:33','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2009-04-19 08:25:33','YYYY-MM-DD HH24:MI:SS'),'AD_Org_ID','Organization',20,'N',0,100,100,'Y','R',2207,50001)
;

-- Apr 19, 2009 8:25:35 AM EEST
-- Replication stabilization
INSERT INTO EXP_FormatLine (EXP_Format_ID,EXP_FormatLine_ID,AD_Client_ID,IsActive,Created,Updated,Value,Name,Position,IsPartUniqueIndex,AD_Org_ID,CreatedBy,UpdatedBy,IsMandatory,Type,AD_Column_ID) VALUES (50006,50023,0,'Y',TO_DATE('2009-04-19 08:25:34','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2009-04-19 08:25:34','YYYY-MM-DD HH24:MI:SS'),'M_Product_ID','Product',30,'N',0,100,100,'N','E',2221)
;

-- Apr 19, 2009 8:25:35 AM EEST
-- Replication stabilization
INSERT INTO EXP_FormatLine (EXP_Format_ID,EXP_FormatLine_ID,AD_Client_ID,IsActive,Created,Updated,Value,Name,Position,IsPartUniqueIndex,AD_Org_ID,CreatedBy,UpdatedBy,IsMandatory,Type,AD_Column_ID) VALUES (50006,50024,0,'Y',TO_DATE('2009-04-19 08:25:35','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2009-04-19 08:25:35','YYYY-MM-DD HH24:MI:SS'),'PriceEntered','Price Entered',40,'N',0,100,100,'N','E',12875)
;

-- Apr 19, 2009 8:25:36 AM EEST
-- Replication stabilization
INSERT INTO EXP_FormatLine (EXP_Format_ID,EXP_FormatLine_ID,AD_Client_ID,IsActive,Created,Updated,Value,Name,Position,IsPartUniqueIndex,AD_Org_ID,CreatedBy,UpdatedBy,IsMandatory,Type,AD_Column_ID,EXP_EmbeddedFormat_ID) VALUES (50006,50025,0,'Y',TO_DATE('2009-04-19 08:25:35','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2009-04-19 08:25:35','YYYY-MM-DD HH24:MI:SS'),'C_Order_ID','Order',50,'Y',0,100,100,'N','R',2213,50004)
;

-- Apr 19, 2009 8:25:37 AM EEST
-- Replication stabilization
INSERT INTO EXP_FormatLine (EXP_Format_ID,EXP_FormatLine_ID,AD_Client_ID,IsActive,Created,Updated,Value,Name,Position,IsPartUniqueIndex,AD_Org_ID,CreatedBy,UpdatedBy,IsMandatory,Type,AD_Column_ID) VALUES (50006,50026,0,'Y',TO_DATE('2009-04-19 08:25:36','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2009-04-19 08:25:36','YYYY-MM-DD HH24:MI:SS'),'Line','Line number',60,'Y',0,100,100,'Y','E',2214)
;

-- Apr 19, 2009 8:25:38 AM EEST
-- Replication stabilization
INSERT INTO EXP_Format (Processing,EXP_Format_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,Value,Name,AD_Table_ID,Version) VALUES ('N',50007,0,0,'Y',TO_DATE('2009-04-19 08:25:37','YYYY-MM-DD HH24:MI:SS'),100,TO_DATE('2009-04-19 08:25:37','YYYY-MM-DD HH24:MI:SS'),100,'C_BPartner','Business Partner',291,'3.2.0')
;

-- Apr 19, 2009 8:25:39 AM EEST
-- Replication stabilization
INSERT INTO EXP_FormatLine (EXP_Format_ID,EXP_FormatLine_ID,AD_Client_ID,IsActive,Created,Updated,Value,Name,Position,IsPartUniqueIndex,AD_Org_ID,CreatedBy,UpdatedBy,IsMandatory,Type,AD_Column_ID,EXP_EmbeddedFormat_ID) VALUES (50007,50027,0,'Y',TO_DATE('2009-04-19 08:25:38','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2009-04-19 08:25:38','YYYY-MM-DD HH24:MI:SS'),'AD_Client_ID','Client',10,'Y',0,100,100,'Y','R',2894,50000)
;

-- Apr 19, 2009 8:25:40 AM EEST
-- Replication stabilization
INSERT INTO EXP_FormatLine (EXP_Format_ID,EXP_FormatLine_ID,AD_Client_ID,IsActive,Created,Updated,Value,Name,Position,IsPartUniqueIndex,AD_Org_ID,CreatedBy,UpdatedBy,IsMandatory,Type,AD_Column_ID,EXP_EmbeddedFormat_ID) VALUES (50007,50028,0,'Y',TO_DATE('2009-04-19 08:25:39','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2009-04-19 08:25:39','YYYY-MM-DD HH24:MI:SS'),'AD_Org_ID','Organization',20,'N',0,100,100,'Y','R',2895,50001)
;

-- Apr 19, 2009 8:25:41 AM EEST
-- Replication stabilization
INSERT INTO EXP_FormatLine (EXP_Format_ID,EXP_FormatLine_ID,AD_Client_ID,IsActive,Created,Updated,Value,Name,Position,IsPartUniqueIndex,AD_Org_ID,CreatedBy,UpdatedBy,IsMandatory,Type,AD_Column_ID) VALUES (50007,50029,0,'Y',TO_DATE('2009-04-19 08:25:40','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2009-04-19 08:25:40','YYYY-MM-DD HH24:MI:SS'),'Value','Business Partner Key',30,'Y',0,100,100,'Y','E',2901)
;

-- Apr 19, 2009 8:25:42 AM EEST
-- Replication stabilization
INSERT INTO EXP_FormatLine (EXP_Format_ID,EXP_FormatLine_ID,AD_Client_ID,IsActive,Created,Updated,Value,Name,Position,IsPartUniqueIndex,AD_Org_ID,CreatedBy,UpdatedBy,IsMandatory,Type,AD_Column_ID) VALUES (50007,50030,0,'Y',TO_DATE('2009-04-19 08:25:41','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2009-04-19 08:25:41','YYYY-MM-DD HH24:MI:SS'),'Name','Business Partner Name',40,'N',0,100,100,'Y','E',2902)
;

-- Apr 19, 2009 8:25:43 AM EEST
-- Replication stabilization
INSERT INTO EXP_FormatLine (EXP_Format_ID,EXP_FormatLine_ID,AD_Client_ID,IsActive,Created,Updated,Value,Name,Position,IsPartUniqueIndex,AD_Org_ID,CreatedBy,UpdatedBy,IsMandatory,Type,AD_Column_ID) VALUES (50007,50031,0,'Y',TO_DATE('2009-04-19 08:25:42','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2009-04-19 08:25:42','YYYY-MM-DD HH24:MI:SS'),'DUNS','DUNS',50,'N',0,100,100,'N','E',2906)
;

-- Apr 19, 2009 8:25:44 AM EEST
-- Replication stabilization
INSERT INTO EXP_FormatLine (EXP_Format_ID,EXP_FormatLine_ID,AD_Client_ID,IsActive,Created,Updated,Value,Name,Position,IsPartUniqueIndex,AD_Org_ID,CreatedBy,UpdatedBy,IsMandatory,Type,AD_Column_ID,EXP_EmbeddedFormat_ID) VALUES (50007,50032,0,'Y',TO_DATE('2009-04-19 08:25:43','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2009-04-19 08:25:43','YYYY-MM-DD HH24:MI:SS'),'C_BP_Group_ID','BP Group',52,'N',0,100,100,'Y','R',4940,50003)
;

-- Apr 19, 2009 8:25:44 AM EEST
-- Replication stabilization
INSERT INTO EXP_FormatLine (EXP_Format_ID,EXP_FormatLine_ID,AD_Client_ID,IsActive,Created,Updated,Value,Name,Position,IsPartUniqueIndex,AD_Org_ID,CreatedBy,UpdatedBy,IsMandatory,Type,AD_Column_ID,DateFormat) VALUES (50007,50033,0,'Y',TO_DATE('2009-04-19 08:25:44','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2009-04-19 08:25:44','YYYY-MM-DD HH24:MI:SS'),'Created','Created',60,'N',0,100,100,'N','E',2897,'MM/dd/yyyy hh:mm:ss')
;

-- Apr 19, 2009 8:25:45 AM EEST
-- Replication stabilization
INSERT INTO EXP_FormatLine (EXP_Format_ID,EXP_FormatLine_ID,AD_Client_ID,IsActive,Created,Updated,Value,Name,Position,IsPartUniqueIndex,AD_Org_ID,CreatedBy,UpdatedBy,IsMandatory,Type,AD_Column_ID,EXP_EmbeddedFormat_ID) VALUES (50007,50034,0,'Y',TO_DATE('2009-04-19 08:25:45','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2009-04-19 08:25:45','YYYY-MM-DD HH24:MI:SS'),'CreatedBy','Created By',70,'N',0,100,100,'N','R',2898,50002)
;

-- Apr 19, 2009 8:25:46 AM EEST
-- Replication stabilization
INSERT INTO EXP_FormatLine (EXP_Format_ID,EXP_FormatLine_ID,AD_Client_ID,IsActive,Created,Updated,Value,Name,Position,IsPartUniqueIndex,AD_Org_ID,CreatedBy,UpdatedBy,IsMandatory,Type,AD_Column_ID,DateFormat) VALUES (50007,50035,0,'Y',TO_DATE('2009-04-19 08:25:45','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2009-04-19 08:25:45','YYYY-MM-DD HH24:MI:SS'),'Updated','Updated',80,'N',0,100,100,'N','E',2899,'MM/dd/yyyy hh:mm:ss')
;

-- Apr 19, 2009 8:25:47 AM EEST
-- Replication stabilization
INSERT INTO EXP_FormatLine (EXP_Format_ID,EXP_FormatLine_ID,AD_Client_ID,IsActive,Created,Updated,Value,Name,Position,IsPartUniqueIndex,AD_Org_ID,CreatedBy,UpdatedBy,IsMandatory,Type,AD_Column_ID,EXP_EmbeddedFormat_ID) VALUES (50007,50036,0,'Y',TO_DATE('2009-04-19 08:25:46','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2009-04-19 08:25:46','YYYY-MM-DD HH24:MI:SS'),'UpdatedBy','Updated By',90,'N',0,100,100,'N','R',2900,50002)
;

-- Apr 19, 2009 8:25:48 AM EEST
-- Replication stabilization
INSERT INTO EXP_Format (Processing,EXP_Format_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,Value,Name,AD_Table_ID,Version) VALUES ('N',50008,11,0,'Y',TO_DATE('2009-04-19 08:25:47','YYYY-MM-DD HH24:MI:SS'),100,TO_DATE('2009-04-19 08:25:47','YYYY-MM-DD HH24:MI:SS'),100,'GardenWorld-C_BPartner','Business Partner',291,'3.2.0.1')
;

-- Apr 19, 2009 8:25:49 AM EEST
-- Replication stabilization
INSERT INTO EXP_FormatLine (EXP_Format_ID,EXP_FormatLine_ID,AD_Client_ID,IsActive,Created,Updated,Value,Name,Position,IsPartUniqueIndex,AD_Org_ID,CreatedBy,UpdatedBy,IsMandatory,Type,AD_Column_ID) VALUES (50008,50037,11,'Y',TO_DATE('2009-04-19 08:25:48','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2009-04-19 08:25:48','YYYY-MM-DD HH24:MI:SS'),'client','Client ID',10,'Y',0,100,100,'Y','R',2894)
;

-- Apr 19, 2009 8:25:50 AM EEST
-- Replication stabilization
INSERT INTO EXP_FormatLine (EXP_Format_ID,EXP_FormatLine_ID,AD_Client_ID,IsActive,Created,Updated,Value,Name,Position,IsPartUniqueIndex,AD_Org_ID,CreatedBy,UpdatedBy,IsMandatory,Type,AD_Column_ID) VALUES (50008,50038,11,'Y',TO_DATE('2009-04-19 08:25:49','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2009-04-19 08:25:49','YYYY-MM-DD HH24:MI:SS'),'Value','Search key',20,'Y',0,100,100,'Y','E',2901)
;

-- Apr 19, 2009 8:25:51 AM EEST
-- Replication stabilization
INSERT INTO EXP_FormatLine (EXP_Format_ID,EXP_FormatLine_ID,AD_Client_ID,IsActive,Created,Updated,Value,Name,Position,IsPartUniqueIndex,AD_Org_ID,CreatedBy,UpdatedBy,IsMandatory,Type,AD_Column_ID) VALUES (50008,50039,11,'Y',TO_DATE('2009-04-19 08:25:50','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2009-04-19 08:25:50','YYYY-MM-DD HH24:MI:SS'),'name','Name',30,'N',0,100,100,'N','E',2902)
;

-- Apr 19, 2009 8:25:51 AM EEST
-- Replication stabilization
INSERT INTO EXP_FormatLine (EXP_Format_ID,EXP_FormatLine_ID,AD_Client_ID,IsActive,Created,Updated,Value,Name,Position,IsPartUniqueIndex,AD_Org_ID,CreatedBy,UpdatedBy,IsMandatory,Type,AD_Column_ID) VALUES (50008,50040,11,'N',TO_DATE('2009-04-19 08:25:51','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2009-04-19 08:25:51','YYYY-MM-DD HH24:MI:SS'),'language','Language',40,'N',0,100,100,'N','E',2914)
;

-- Apr 19, 2009 8:25:52 AM EEST
-- Replication stabilization
INSERT INTO EXP_Format (Processing,EXP_Format_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,Value,Name,AD_Table_ID,Version) VALUES ('N',50009,11,0,'Y',TO_DATE('2009-04-19 08:25:52','YYYY-MM-DD HH24:MI:SS'),100,TO_DATE('2009-04-19 08:25:52','YYYY-MM-DD HH24:MI:SS'),100,'clientPartners','All BPartners for Client/Tenant',112,'3.2.0')
;

-- Apr 19, 2009 8:25:53 AM EEST
-- Replication stabilization
INSERT INTO EXP_FormatLine (EXP_Format_ID,EXP_FormatLine_ID,AD_Client_ID,IsActive,Created,Updated,Value,Name,Position,IsPartUniqueIndex,AD_Org_ID,CreatedBy,UpdatedBy,IsMandatory,Type,AD_Column_ID) VALUES (50009,50041,11,'Y',TO_DATE('2009-04-19 08:25:53','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2009-04-19 08:25:53','YYYY-MM-DD HH24:MI:SS'),'value','Value',10,'Y',0,100,100,'N','E',4773)
;

-- Apr 19, 2009 8:25:54 AM EEST
-- Replication stabilization
INSERT INTO EXP_FormatLine (EXP_Format_ID,EXP_FormatLine_ID,AD_Client_ID,IsActive,Created,Updated,Value,Name,Position,IsPartUniqueIndex,AD_Org_ID,CreatedBy,UpdatedBy,IsMandatory,Type,EXP_EmbeddedFormat_ID) VALUES (50009,50042,11,'Y',TO_DATE('2009-04-19 08:25:53','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2009-04-19 08:25:53','YYYY-MM-DD HH24:MI:SS'),'bPartner','Business partner',20,'N',0,100,100,'N','M',50008)
;




-- Apr 19, 2009 8:30:29 AM EEST
-- Replication stabilization
INSERT INTO EXP_Processor_Type (AD_Client_ID,Created,Description,Help,EXP_Processor_Type_ID,AD_Org_ID,IsActive,CreatedBy,Updated,UpdatedBy,Value,Name,JavaClass) VALUES (11,TO_DATE('2009-04-19 08:30:26','YYYY-MM-DD HH24:MI:SS'),'Adempiere HDD Export Processor Type','HDD Export Processor Type',50000,0,'Y',100,TO_DATE('2009-04-19 08:30:26','YYYY-MM-DD HH24:MI:SS'),100,'HDD Export Processor Type','HDD Export Processor Type','org.adempiere.process.rpl.exp.HDDExportProcessor')
;

-- Apr 19, 2009 8:30:31 AM EEST
-- Replication stabilization
INSERT INTO EXP_Processor_Type (AD_Client_ID,Created,Description,Help,EXP_Processor_Type_ID,AD_Org_ID,IsActive,CreatedBy,Updated,UpdatedBy,Value,Name,JavaClass) VALUES (11,TO_DATE('2009-04-19 08:30:30','YYYY-MM-DD HH24:MI:SS'),'Adempiere JMS Topic Export Processor Type','JMS Topic Export Processor Type',50001,0,'Y',100,TO_DATE('2009-04-19 08:30:30','YYYY-MM-DD HH24:MI:SS'),100,'JMS Topic Export Processor Type','Human Readable name for - JMS Topic Export Processor Type','org.adempiere.process.rpl.exp.TopicExportProcessor')
;




-- Apr 19, 2009 8:32:43 AM EEST
-- Replication stabilization
INSERT INTO EXP_Processor (EXP_Processor_Type_ID,AD_Org_ID,Created,EXP_Processor_ID,AD_Client_ID,IsActive,CreatedBy,Updated,Name,Description,Account,UpdatedBy,Value,Help,Host,PasswordInfo) VALUES (50000,0,TO_DATE('2009-04-19 08:32:40','YYYY-MM-DD HH24:MI:SS'),50000,11,'Y',100,TO_DATE('2009-04-19 08:32:40','YYYY-MM-DD HH24:MI:SS'),'HDD Export Processor','HDD Export Processor Description','exampleAccount',100,'HDD Export Processor','HDD Export Processor Help','www.example.com','examplePassword')
;

-- Apr 19, 2009 8:32:47 AM EEST
-- Replication stabilization
INSERT INTO EXP_ProcessorParameter (AD_Org_ID,IsActive,Created,CreatedBy,EXP_ProcessorParameter_ID,EXP_Processor_ID,AD_Client_ID,Updated,UpdatedBy,Name,Description,ParameterValue,Value,Help) VALUES (0,'Y',TO_DATE('2009-04-19 08:32:44','YYYY-MM-DD HH24:MI:SS'),100,50000,50000,11,TO_DATE('2009-04-19 08:32:44','YYYY-MM-DD HH24:MI:SS'),100,'Name of file under which xml will be exported','Export Processor Parameter Description','example-export.xml','fileName','Processor Parameter Help')
;

-- Apr 19, 2009 8:32:48 AM EEST
-- Replication stabilization
INSERT INTO EXP_ProcessorParameter (AD_Org_ID,IsActive,Created,CreatedBy,EXP_ProcessorParameter_ID,EXP_Processor_ID,AD_Client_ID,Updated,UpdatedBy,Name,Description,ParameterValue,Value,Help) VALUES (0,'Y',TO_DATE('2009-04-19 08:32:47','YYYY-MM-DD HH24:MI:SS'),100,50001,50000,11,TO_DATE('2009-04-19 08:32:47','YYYY-MM-DD HH24:MI:SS'),100,'Name of folder where file will be exported','Export Processor Parameter Description','C:/temp/','folder','Processor Parameter Help')
;

-- Apr 19, 2009 8:32:49 AM EEST
-- Replication stabilization
INSERT INTO EXP_Processor (EXP_Processor_Type_ID,AD_Org_ID,Created,EXP_Processor_ID,AD_Client_ID,IsActive,CreatedBy,Updated,Name,Description,Account,UpdatedBy,Value,Help,Host,Port,PasswordInfo) VALUES (50001,0,TO_DATE('2009-04-19 08:32:48','YYYY-MM-DD HH24:MI:SS'),50001,11,'Y',100,TO_DATE('2009-04-19 08:32:48','YYYY-MM-DD HH24:MI:SS'),'Human Readable name for - JMS Topic Export Processor','JMS Topic Export Processor Description','exampleAccount',100,'JMS Topic Export Processor','JMS Topic Export Processor Help','www.example.com',61616,'examplePassword')
;

-- Apr 19, 2009 8:32:50 AM EEST
-- Replication stabilization
INSERT INTO EXP_ProcessorParameter (AD_Org_ID,IsActive,Created,CreatedBy,EXP_ProcessorParameter_ID,EXP_Processor_ID,AD_Client_ID,Updated,UpdatedBy,Name,Description,ParameterValue,Value,Help) VALUES (0,'Y',TO_DATE('2009-04-19 08:32:49','YYYY-MM-DD HH24:MI:SS'),100,50002,50001,11,TO_DATE('2009-04-19 08:32:49','YYYY-MM-DD HH24:MI:SS'),100,'Name of JMS Topic where xml will be exported','Export Processor Parameter Description','ExampleTopic','topicName','JMS Topic Export Processor Parameter Help')
;

-- Apr 19, 2009 8:32:50 AM EEST
-- Replication stabilization
INSERT INTO EXP_ProcessorParameter (AD_Org_ID,IsActive,Created,CreatedBy,EXP_ProcessorParameter_ID,EXP_Processor_ID,AD_Client_ID,Updated,UpdatedBy,Name,Description,ParameterValue,Value,Help) VALUES (0,'Y',TO_DATE('2009-04-19 08:32:50','YYYY-MM-DD HH24:MI:SS'),100,50003,50001,11,TO_DATE('2009-04-19 08:32:50','YYYY-MM-DD HH24:MI:SS'),100,'ClientID which will be set in JMS connection','Export Processor Parameter Description','ExampleClientID','clientID','JMS Topic Export Processor Parameter Help')
;

-- Apr 19, 2009 8:32:51 AM EEST
-- Replication stabilization
INSERT INTO EXP_ProcessorParameter (AD_Org_ID,IsActive,Created,CreatedBy,EXP_ProcessorParameter_ID,EXP_Processor_ID,AD_Client_ID,Updated,UpdatedBy,Name,Description,ParameterValue,Value,Help) VALUES (0,'Y',TO_DATE('2009-04-19 08:32:50','YYYY-MM-DD HH24:MI:SS'),100,50004,50001,11,TO_DATE('2009-04-19 08:32:50','YYYY-MM-DD HH24:MI:SS'),100,'protocol which will be used for JMS connection','Export Processor Parameter Description','tcp','protocol','JMS Topic Export Processor Parameter Help')
;

-- Apr 19, 2009 8:32:52 AM EEST
-- Replication stabilization
INSERT INTO EXP_ProcessorParameter (AD_Org_ID,IsActive,Created,CreatedBy,EXP_ProcessorParameter_ID,EXP_Processor_ID,AD_Client_ID,Updated,UpdatedBy,Name,Description,ParameterValue,Value,Help) VALUES (0,'Y',TO_DATE('2009-04-19 08:32:51','YYYY-MM-DD HH24:MI:SS'),100,50005,50001,11,TO_DATE('2009-04-19 08:32:51','YYYY-MM-DD HH24:MI:SS'),100,'Time to Live for the JMS Message','Export Processor Parameter Description','10000','timeToLive','JMS Topic Export Processor Parameter Help')
;

-- Apr 19, 2009 8:32:53 AM EEST
-- Replication stabilization
INSERT INTO EXP_ProcessorParameter (AD_Org_ID,IsActive,Created,CreatedBy,EXP_ProcessorParameter_ID,EXP_Processor_ID,AD_Client_ID,Updated,UpdatedBy,Name,Description,ParameterValue,Value,Help) VALUES (0,'Y',TO_DATE('2009-04-19 08:32:52','YYYY-MM-DD HH24:MI:SS'),100,50006,50001,11,TO_DATE('2009-04-19 08:32:52','YYYY-MM-DD HH24:MI:SS'),100,'Is JMS Delivery Mode Persistent','Export Processor Parameter Description','true','isDeliveryModePersistent','JMS Topic Export Processor Parameter Help')
;




-- Apr 19, 2009 8:35:00 AM EEST
-- Replication stabilization
UPDATE EXP_FormatLine SET IsActive='Y', Name='Order Line', Position=90, IsPartUniqueIndex='N', IsMandatory='N', Type='M', EXP_EmbeddedFormat_ID=50006,Updated=TO_DATE('2009-04-19 08:35:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE EXP_FormatLine_ID=50018
;



-- Apr 19, 2009 9:09:35 AM EEST
-- Replication stabilization
INSERT INTO IMP_Processor_Type (IMP_Processor_Type_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,Value,Description,Help,JavaClass,CreatedBy,Updated,UpdatedBy,Name) VALUES (50000,11,0,'Y',TO_DATE('2009-04-19 09:09:32','YYYY-MM-DD HH24:MI:SS'),'HDD Import Processor Type','Adempiere HDD Import Processor Type','HDD Import Processor Type','org.adempiere.server.rpl.imp.FileImportProcessor',100,TO_DATE('2009-04-19 09:09:32','YYYY-MM-DD HH24:MI:SS'),100,'HDD Import Processor Type')
;

-- Apr 19, 2009 9:09:37 AM EEST
-- Replication stabilization
INSERT INTO IMP_Processor_Type (IMP_Processor_Type_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,Value,Description,Help,JavaClass,CreatedBy,Updated,UpdatedBy,Name) VALUES (50001,11,0,'Y',TO_DATE('2009-04-19 09:09:36','YYYY-MM-DD HH24:MI:SS'),'JMS Topic Import Processor Type','Adempiere JMS Topic Import Processor Type','JMS Topic Import Processor Type','org.adempiere.server.rpl.imp.TopicImportProcessor',100,TO_DATE('2009-04-19 09:09:36','YYYY-MM-DD HH24:MI:SS'),100,'Human Readable name for - JMS Topic Import Processor Type')
;



-- Apr 19, 2009 9:12:14 AM EEST
-- Replication stabilization
INSERT INTO IMP_Processor (IMP_Processor_ID,AD_Org_ID,IMP_Processor_Type_ID,AD_Client_ID,IsActive,Updated,UpdatedBy,Name,Description,Created,CreatedBy,Value,Help,Frequency,Processing,Host,FrequencyType,KeepLogDays,Account,PasswordInfo) VALUES (50000,0,50000,11,'Y',TO_DATE('2009-04-19 09:12:09','YYYY-MM-DD HH24:MI:SS'),100,'HDD Import Processor','HDD Import Processor Description',TO_DATE('2009-04-19 09:12:09','YYYY-MM-DD HH24:MI:SS'),100,'HDD Import Processor','HDD Import Processor Help',10,'N','www.example.com','M',7,'exampleAccount','examplePassword')
;

-- Apr 19, 2009 9:12:17 AM EEST
-- Replication stabilization
INSERT INTO IMP_ProcessorParameter (IMP_Processor_ID,AD_Client_ID,IsActive,Created,IMP_ProcessorParameter_ID,AD_Org_ID,CreatedBy,Updated,UpdatedBy,Value,Name,Description,Help,ParameterValue) VALUES (50000,11,'Y',TO_DATE('2009-04-19 09:12:15','YYYY-MM-DD HH24:MI:SS'),50000,0,100,TO_DATE('2009-04-19 09:12:15','YYYY-MM-DD HH24:MI:SS'),100,'fileName','Name of file from where xml will be imported','Import Processor Parameter Description','HDD Import Processor Parameter Help','C_Order')
;

-- Apr 19, 2009 9:12:19 AM EEST
-- Replication stabilization
INSERT INTO IMP_Processor (IMP_Processor_ID,AD_Org_ID,IMP_Processor_Type_ID,AD_Client_ID,IsActive,Updated,UpdatedBy,Name,Description,Created,CreatedBy,Value,Help,Frequency,Processing,Host,Port,FrequencyType,KeepLogDays,Account,PasswordInfo) VALUES (50001,0,50001,11,'Y',TO_DATE('2009-04-19 09:12:18','YYYY-MM-DD HH24:MI:SS'),100,'Human Readable name for - JMS Topic Import Processor','JMS Topic Import Processor Description',TO_DATE('2009-04-19 09:12:18','YYYY-MM-DD HH24:MI:SS'),100,'JMS Topic Import Processor','JMS Topic Import Processor Help',10,'N','www.example.com',61616,'M',7,'exampleAccount','examplePassword')
;

-- Apr 19, 2009 9:12:19 AM EEST
-- Replication stabilization
INSERT INTO IMP_ProcessorParameter (IMP_Processor_ID,AD_Client_ID,IsActive,Created,IMP_ProcessorParameter_ID,AD_Org_ID,CreatedBy,Updated,UpdatedBy,Value,Name,Description,Help,ParameterValue) VALUES (50001,11,'Y',TO_DATE('2009-04-19 09:12:19','YYYY-MM-DD HH24:MI:SS'),50001,0,100,TO_DATE('2009-04-19 09:12:19','YYYY-MM-DD HH24:MI:SS'),100,'topicName','Name of JMS Topic from where xml will be Imported','Import Processor Parameter Description','JMS Topic Import Processor Parameter Help','ExampleTopic')
;

-- Apr 19, 2009 9:12:20 AM EEST
-- Replication stabilization
INSERT INTO IMP_ProcessorParameter (IMP_Processor_ID,AD_Client_ID,IsActive,Created,IMP_ProcessorParameter_ID,AD_Org_ID,CreatedBy,Updated,UpdatedBy,Value,Name,Description,Help,ParameterValue) VALUES (50001,11,'Y',TO_DATE('2009-04-19 09:12:19','YYYY-MM-DD HH24:MI:SS'),50002,0,100,TO_DATE('2009-04-19 09:12:19','YYYY-MM-DD HH24:MI:SS'),100,'protocol','protocol which will be used for JMS connection','Import Processor Parameter Description','JMS Topic Import Processor Parameter Help','tcp')
;

-- Apr 19, 2009 9:12:21 AM EEST
-- Replication stabilization
INSERT INTO IMP_ProcessorParameter (IMP_Processor_ID,AD_Client_ID,IsActive,Created,IMP_ProcessorParameter_ID,AD_Org_ID,CreatedBy,Updated,UpdatedBy,Value,Name,Description,Help,ParameterValue) VALUES (50001,11,'Y',TO_DATE('2009-04-19 09:12:21','YYYY-MM-DD HH24:MI:SS'),50003,0,100,TO_DATE('2009-04-19 09:12:21','YYYY-MM-DD HH24:MI:SS'),100,'isDurableSubscription','Durable Subscription','Import Processor Parameter Description','JMS Topic Import Processor Parameter Help','true')
;

-- Apr 19, 2009 9:12:22 AM EEST
-- Replication stabilization
INSERT INTO IMP_ProcessorParameter (IMP_Processor_ID,AD_Client_ID,IsActive,Created,IMP_ProcessorParameter_ID,AD_Org_ID,CreatedBy,Updated,UpdatedBy,Value,Name,Description,Help,ParameterValue) VALUES (50001,11,'Y',TO_DATE('2009-04-19 09:12:21','YYYY-MM-DD HH24:MI:SS'),50004,0,100,TO_DATE('2009-04-19 09:12:21','YYYY-MM-DD HH24:MI:SS'),100,'subscriptionName','Subscription Name','Import Processor Parameter Description','JMS Topic Import Processor Parameter Help','exampleSubName')
;

-- Apr 19, 2009 9:12:23 AM EEST
-- Replication stabilization
INSERT INTO IMP_ProcessorParameter (IMP_Processor_ID,AD_Client_ID,IsActive,Created,IMP_ProcessorParameter_ID,AD_Org_ID,CreatedBy,Updated,UpdatedBy,Value,Name,Description,Help,ParameterValue) VALUES (50001,11,'Y',TO_DATE('2009-04-19 09:12:22','YYYY-MM-DD HH24:MI:SS'),50005,0,100,TO_DATE('2009-04-19 09:12:22','YYYY-MM-DD HH24:MI:SS'),100,'clientID','JMS Connection Client ID','Import Processor Parameter Description','JMS Topic Import Processor Parameter Help','ImpClientID')
;



-- Apr 19, 2009 9:14:59 AM EEST
-- Replication stabilization
INSERT INTO AD_ReplicationStrategy (Created,AD_Client_ID,AD_Org_ID,AD_ReplicationStrategy_ID,CreatedBy,Description,Updated,UpdatedBy,EntityType,Help,IsActive,Name,Value,EXP_Processor_ID) VALUES (TO_DATE('2009-04-19 09:14:55','YYYY-MM-DD HH24:MI:SS'),11,0,50000,100,'Example POS Replication Strategy - Description',TO_DATE('2009-04-19 09:14:55','YYYY-MM-DD HH24:MI:SS'),100,'D','Example POS Replication Strategy - Help','Y','Example POS Replication Strategy','Example POS',50001)
;

-- Apr 19, 2009 9:15:03 AM EEST
-- Replication stabilization
INSERT INTO AD_ReplicationTable (UpdatedBy,AD_Org_ID,Updated,AD_Client_ID,AD_ReplicationTable_ID,Created,CreatedBy,EntityType,IsActive,ReplicationType,AD_ReplicationStrategy_ID,AD_Table_ID,Description) VALUES (100,0,TO_DATE('2009-04-19 09:15:00','YYYY-MM-DD HH24:MI:SS'),11,50000,TO_DATE('2009-04-19 09:15:00','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','R',50000,291,'Business Partner - Example')
;

-- Apr 19, 2009 9:15:06 AM EEST
-- Replication stabilization
INSERT INTO AD_ReplicationDocument (Updated,AD_ReplicationDocument_ID,AD_ReplicationStrategy_ID,AD_Org_ID,IsActive,AD_Client_ID,Created,C_DocType_ID,CreatedBy,UpdatedBy,Description,ReplicationType,AD_Table_ID) VALUES (TO_DATE('2009-04-19 09:15:03','YYYY-MM-DD HH24:MI:SS'),50000,50000,0,'Y',11,TO_DATE('2009-04-19 09:15:03','YYYY-MM-DD HH24:MI:SS'),132,100,100,'Standard Order - Example','R',259)
;



