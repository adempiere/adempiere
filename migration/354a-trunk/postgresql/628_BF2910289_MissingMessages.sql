-- Dec 9, 2009 3:20:29 PM COT
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,523848,0,TO_TIMESTAMP('2009-12-09 15:20:29','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','The parent record (document) is already processed','I',TO_TIMESTAMP('2009-12-09 15:20:29','YYYY-MM-DD HH24:MI:SS'),100,'ParentComplete')
;

-- Dec 9, 2009 3:20:29 PM COT
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=523848 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- Dec 9, 2009 3:20:41 PM COT
UPDATE AD_Message_Trl SET IsTranslated='Y',MsgText='El registro padre (documento) ya está procesado',Updated=TO_TIMESTAMP('2009-12-09 15:20:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Message_ID=523848 AND AD_Language LIKE 'es_%'
;

-- Dec 9, 2009 3:20:57 PM COT
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,523849,0,TO_TIMESTAMP('2009-12-09 15:20:57','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Shipment/Receipt Line or charge should be entered','E',TO_TIMESTAMP('2009-12-09 15:20:57','YYYY-MM-DD HH24:MI:SS'),100,'FillShipLineOrCharge')
;

-- Dec 9, 2009 3:20:57 PM COT
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=523849 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- Dec 9, 2009 3:21:00 PM COT
UPDATE AD_Message SET MsgType='E',Updated=TO_TIMESTAMP('2009-12-09 15:21:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Message_ID=523848
;

-- Dec 9, 2009 3:21:22 PM COT
UPDATE AD_Message_Trl SET IsTranslated='Y',MsgText='Debe ingresar línea de recibo/entrega o cargo',Updated=TO_TIMESTAMP('2009-12-09 15:21:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Message_ID=523849 AND AD_Language LIKE 'es_%'
;

-- Dec 9, 2009 3:21:38 PM COT
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,523850,0,TO_TIMESTAMP('2009-12-09 15:21:38','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Either shipment/receipt line or charge should be selected','E',TO_TIMESTAMP('2009-12-09 15:21:38','YYYY-MM-DD HH24:MI:SS'),100,'JustShipLineOrCharge')
;

-- Dec 9, 2009 3:21:38 PM COT
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=523850 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- Dec 9, 2009 3:22:01 PM COT
UPDATE AD_Message_Trl SET IsTranslated='Y',MsgText='Debe elegir solamente línea de recibo/entrega o cargo, no ambos',Updated=TO_TIMESTAMP('2009-12-09 15:22:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Message_ID=523850 AND AD_Language LIKE 'es_%'
;

-- Dec 9, 2009 3:22:20 PM COT
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,523851,0,TO_TIMESTAMP('2009-12-09 15:22:20','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Amount to be returned is greater than the amount shipped','E',TO_TIMESTAMP('2009-12-09 15:22:20','YYYY-MM-DD HH24:MI:SS'),100,'AmtReturned>Shipped')
;

-- Dec 9, 2009 3:22:20 PM COT
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=523851 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- Dec 9, 2009 3:22:34 PM COT
UPDATE AD_Message_Trl SET IsTranslated='Y',MsgText='Cantidad a devolver es mayor que la cantidad despachada',Updated=TO_TIMESTAMP('2009-12-09 15:22:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Message_ID=523851 AND AD_Language LIKE 'es_%'
;

-- Dec 9, 2009 3:23:02 PM COT
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,523852,0,TO_TIMESTAMP('2009-12-09 15:23:02','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Shipment/Receipt line is already defined in another line','E',TO_TIMESTAMP('2009-12-09 15:23:02','YYYY-MM-DD HH24:MI:SS'),100,'InOutLineAlreadyEntered')
;

-- Dec 9, 2009 3:23:02 PM COT
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=523852 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- Dec 9, 2009 3:23:20 PM COT
UPDATE AD_Message_Trl SET IsTranslated='Y',MsgText='Línea de recibo/entrega ya fue definida en otro renglón',Updated=TO_TIMESTAMP('2009-12-09 15:23:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Message_ID=523852 AND AD_Language LIKE 'es_%'
;

-- Dec 9, 2009 3:23:35 PM COT
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,523853,0,TO_TIMESTAMP('2009-12-09 15:23:35','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Shipment/Receipt has different Sales/Purchase transaction than RMA','E',TO_TIMESTAMP('2009-12-09 15:23:35','YYYY-MM-DD HH24:MI:SS'),100,'RMA.IsSOTrx <> InOut.IsSOTrx')
;

-- Dec 9, 2009 3:23:35 PM COT
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=523853 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- Dec 9, 2009 3:24:05 PM COT
UPDATE AD_Message_Trl SET IsTranslated='Y',MsgText='Recibo/Entrega tiene diferente transacción de ventas/compras que la autorización de devolución',Updated=TO_TIMESTAMP('2009-12-09 15:24:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Message_ID=523853 AND AD_Language LIKE 'es_%'
;

